/**
 * @file chart.js
 * @description 대기오염 측정 데이터 시각화 및 관리를 위한 핵심 스크립트
 * @author Hanbit Kang
 * @version 1.0.0
 * @lastModified 2024-11-03
 *
 * @architecture
 * - 데이터 관리: 캐시 시스템을 통한 API 호출 최적화
 * - UI 컨트롤: 동적 차트 및 테이블 생성
 * - 이벤트 처리: 사용자 인터랙션에 따른 데이터 갱신
 *
 * @improvements
 * 1. 데이터 정규화 추가
 * 2. DocumentFragment를 사용한 DOM 조작 최적화
 * 3. 통합 에러 처리 시스템
 * 4. 로딩 상태 관리 개선
 */

$(document).ready(function() {
    /**
     * @constant {Object} CONFIG - 전역 설정 상수
     * @property {number} CACHE_TTL - 캐시 유효기간 (7일)
     * @property {Object} TRACE_COLORS - 차트 색상 정의
     * @property {Object} ERROR_MESSAGES - 에러 메시지 정의
     */
    const CONFIG = {
        CACHE_TTL: 7 * 24 * 60 * 60 * 1000,
        TRACE_COLORS: {
            TSP: 'rgba(136,132,216,1)',
            SOX: 'rgba(130,202,157,1)',
            NOX: 'rgba(255,198,88,1)',
            HCL: 'rgba(255,115,0,1)',
            CO: 'rgba(56,121,8,1)'
        },
        ERROR_MESSAGES: {
            FETCH_BUSINESS: '사업장 목록을 불러오는데 실패했습니다.',
            FETCH_STACK: '배출구 목록을 불러오는데 실패했습니다.',
            FETCH_DATA: '측정 데이터를 불러오는데 실패했습니다.',
            NO_DATA: '조회된 측정 데이터가 없습니다.',
            VALIDATION: '사업장과 배출구를 모두 선택해주세요.'
        }
    };

    /**
     * @type {Array} chartData
     * @description 차트 데이터 저장소
     */
    let chartData = [];

    /**
     * @type {Object} layout
     * @description 차트 레이아웃 설정
     */
    let layout = {};

    /**
     * @function createTrace 트레이스 객체 생성 함수
     * @description 오염물질별 트레이스 객체 생성
     * @param {string} name - 트레이스 이름
     * @param {string} color - 트레이스 색상
     * @returns {Object} 트레이스 객체
     */
    function createTrace(name, color) {
        return {
            x: [],
            y: [],
            mode: 'lines+markers',
            name,
            line: { color },
            marker: { size: 6 }
        };
    }

    // 각 오염물질별 트레이스 초기화
    let tspTrace = createTrace("TSP 먼지", CONFIG.TRACE_COLORS.TSP);
    let soxTrace = createTrace("SOX 황산화물", CONFIG.TRACE_COLORS.SOX);
    let noxTrace = createTrace("NOX 질소산화물", CONFIG.TRACE_COLORS.NOX);
    let hclTrace = createTrace("HCL 염화수소", CONFIG.TRACE_COLORS.HCL);
    let coTrace = createTrace("CO 일산화탸소", CONFIG.TRACE_COLORS.CO);

    /**
     * @description 데이터 정규화 유틸리티
     */
    const DataUtils = {
        /**
         * @function normalizeValue
         * @param {*} value - 정규화할 값
         * @returns {string|number} - 정규화된 값
         */
        normalizeValue(value) {
            return (value === null || value === undefined || value === '') ? '0' : value;
        },

        /**
         * @function formatDate
         * @param {string} dateStr - 날짜 문자열
         * @returns {string} - 포맷된 날짜 문자열
         */
        formatDate(dateStr) {
            return moment(dateStr).format("YYYY.MM.DD HH:mm");
        },


        /**
         * @function normalizeChartValue
         * @param {*} value - 정규화할 차트 데이터 값
         * @returns {number} - 정규화된 숫자 값
         */
        normalizeChartValue(value) {
            const normalized = this.normalizeValue(value);
            return typeof normalized === 'string' ? parseFloat(normalized) : normalized;
        }
    };

    /**
     * @function setCache
     * @description 로컬 스토리지를 사용한 데이터 캐싱
     * @param {string} key - 캐시 키
     * @param {any} data - 저장할 데이터
     * @throws {Error} 스토리지 접근 실패 시 발생
     */
    function setCache(key, data) {
        try {
            const item = {
                data,
                expiry: Date.now() + CONFIG.CACHE_TTL
            };
            localStorage.setItem(key, JSON.stringify(item));
            console.log(`캐시 저장 성공: ${key}`);
        } catch (e) {
            console.warn('캐시 저장 실패:', e);
        }
    }

    /**
     * @function getCache
     * @description 캐시된 데이터 조회
     * @param {string} key - 캐시 키
     * @returns {any|null} 캐시된 데이터 또는 null
     */
    function getCache(key) {
        try {
            const itemStr = localStorage.getItem(key);
            if (!itemStr) {
                console.log(`캐시 미존재: ${key}`);
                return null;
            }

            const item = JSON.parse(itemStr);
            if (Date.now() > item.expiry) {
                console.log(`캐시 만료: ${key}`);
                localStorage.removeItem(key);
                return null;
            }

            console.log(`캐시 조회 성공: ${key}`);
            return item.data;
        } catch (e) {
            console.warn('캐시 조회 실패:', e);
            return null;
        }
    }

    /**
     * @function getCachedData
     * @description 캐시 또는 API를 통한 데이터 조회
     * @param {string} cacheKey - 캐시 키
     * @param {Function} fetchFunction - API 호출 함수
     * @param {any} param - API 파라미터
     * @returns {Promise<any>} 조회된 데이터
     */
    async function getCachedData(cacheKey, fetchFunction, param) {
        const cachedData = getCache(cacheKey);
        if (cachedData) return cachedData;

        try {
            const freshData = await fetchFunction(param);
            setCache(cacheKey, freshData);
            return freshData;
        } catch (error) {
            console.error('데이터 조회 실패:', error);
            throw error;
        }
    }

    /**
     * @function fetchBusinesses API 호출 함수들
     * @description 사업장 목록 조회
     * @param {string} region - 지역명
     * @returns {Promise<Array>} 사업장 목록
     */
    async function fetchBusinesses(region) {
        try {
            return await $.ajax({
                url: '/api/businesses',
                data: { region },
                method: 'GET',
                cache: false
            });
        } catch (error) {
            console.error('사업장 조회 실패:', error);
            throw error;
        }
    }

    /**
     * @function fetchStacks
     * @description 배출구 목록 조회
     * @param {string} business - 사업장명
     * @returns {Promise<Array>} 배출구 목록
     */
    async function fetchStacks(business) {
        try {
            return await $.ajax({
                url: '/api/stacks',
                data: { business },
                method: 'GET',
                cache: false
            });
        } catch (error) {
            console.error('배출구 조회 실패:', error);
            throw error;
        }
    }

    /**
     * @function createOptionElement
     * @description select 옵션 요소 생성
     * @param {string} value - 옵션 값
     * @returns {HTMLOptionElement} option 요소
     */
    function createOptionElement(value) {
        const option = document.createElement('option');
        option.value = value;
        option.textContent = value;
        return option;
    }

    /**
     * @function resetTraces
     * @description 모든 트레이스 데이터 초기화
     */
    function resetTraces() {
        [tspTrace, soxTrace, noxTrace, hclTrace, coTrace].forEach(trace => {
            trace.x = [];
            trace.y = [];
        });
    }

    /**
     * @description select 요소 초기화
     * @param {string} selectId - select 요소의 ID
     */
    function resetSelect(selectId) {
        const select = document.getElementById(selectId);
        while (select.firstChild) {
            select.removeChild(select.firstChild);
        }
        const defaultOption = document.getElementById('defaultOptions')
            .content.cloneNode(true);
        select.appendChild(defaultOption);
    }

    /**
     * @function generateDataTable
     * @description 측정 데이터 테이블 생성 (null 값은 0으로 표시)
     * @param {Array<Object>} data - 측정 데이터 배열
     */
    function generateDataTable(data) {
        const tbody = document.querySelector('#dataTable tbody');
        const template = document.getElementById('tableRowTemplate');

        // 기존 데이터 제거
        while (tbody.firstChild) {
            tbody.removeChild(tbody.firstChild);
        }

        const fragment = document.createDocumentFragment();

        data.forEach(item => {
            // 템플릿 복제
            const row = template.content.cloneNode(true);
            const cells = row.querySelectorAll('td');

            // 각 셀에 데이터 매핑
            cells.forEach(cell => {
                const field = cell.dataset.field;

                let value;

                // 측정시간 필드인 경우 날짜 포맷 적용
                if (field === 'mesure_dt') {
                    value = DataUtils.formatDate(item[field]);
                } else {
                    // null 값 처리를 포함한 데이터 정규화
                    value = DataUtils.normalizeValue(item[field]);
                }
                cell.textContent = value;
            });

            fragment.appendChild(row);
        });

        tbody.appendChild(fragment);
    }

    /**
     * @function generateDynamicHeader
     * @description 테이블 헤더와 엑셀 버튼 생성
     */
    function generateDynamicHeader() {
        const headerDiv = document.getElementById('dynamicTableHeader');

        // 기존 내용 제거
        while (headerDiv.firstChild) {
            headerDiv.removeChild(headerDiv.firstChild);
        }

        // 제목 생성
        const title = document.createElement('h3');
        title.textContent = '측정 데이터 테이블';

        // 엑셀 버튼 생성
        const exportButton = document.createElement('button');
        exportButton.id = 'exportButtonTable';
        exportButton.className = 'btn btn-success';
        exportButton.textContent = '엑셀로 추출하기';

        // 엑셀 추출 이벤트 리스너 추가
        exportButton.addEventListener('click', function() {
            const selectedRegion = document.getElementById('regionSelect').value;
            const selectedBusiness = document.getElementById('businessSelect').value;

            if (confirm('선택하신 데이터를 엑셀 파일로 저장하시겠습니까?')) {
                const wb = XLSX.utils.book_new();
                const ws = XLSX.utils.table_to_sheet(document.getElementById('dataTable'));

                // 열 너비 설정
                ws['!cols'] = [
                    { wch: 20 },  // 측정시간
                    { wch: 10 },  // TSP
                    { wch: 10 },  // SOX
                    { wch: 10 },  // NOX
                    { wch: 10 },  // HCL
                    { wch: 10 }   // CO
                ];

                // 기존 데이터를 아래로 이동
                const range = XLSX.utils.decode_range(ws['!ref']);
                const rowShift = 2;

                // 기존 데이터를 이동
                for (let R = range.e.r; R >= range.s.r; --R) {
                    for (let C = range.s.c; C <= range.e.c; ++C) {
                        const oldCell = XLSX.utils.encode_cell({r: R, c: C});
                        const newCell = XLSX.utils.encode_cell({r: R + rowShift, c: C});
                        if (ws[oldCell]) {
                            ws[newCell] = ws[oldCell];
                            delete ws[oldCell];
                        }
                    }
                }

                // 상단에 지역명과 사업장명 추가
                XLSX.utils.sheet_add_aoa(ws, [
                    [`지역명: ${selectedRegion}`, `사업장명: ${selectedBusiness}`],
                    [] // 빈 줄 추가
                ], { origin: 'A1' });

                // 범위 업데이트
                const newRange = XLSX.utils.decode_range(ws['!ref']);
                newRange.e.r += 2;
                ws['!ref'] = XLSX.utils.encode_range(newRange);

                // 날짜 셀 서식 설정 (데이터 이동 후 처리)
                for (let R = rowShift; R <= newRange.e.r; R++) {
                    const cellAddress = XLSX.utils.encode_cell({r: R, c: 0}); // 첫 번째 열(측정시간)
                    const cell = ws[cellAddress];
                    if (cell && cell.v) {
                        cell.z = 'yyyy.mm.dd h:mm';  // 날짜 표시 형식만 설정
                    }
                }

                XLSX.utils.book_append_sheet(wb, ws, '측정 데이터');
                XLSX.writeFile(wb, `측정데이터_${selectedRegion}_${selectedBusiness}.xlsx`);
            }
        });

        // 요소들을 헤더에 추가
        headerDiv.appendChild(title);
        headerDiv.appendChild(exportButton);
    }

    /**
     * @function exportToExcel
     * @description 측정 데이터 엑셀 파일 생성 및 다운로드
     */
    function exportToExcel() {
        const selectedRegion = document.getElementById('regionSelect').value;
        const selectedBusiness = document.getElementById('businessSelect').value;

        const wb = XLSX.utils.book_new();
        const ws = XLSX.utils.table_to_sheet(document.getElementById('dataTable'));

        XLSX.utils.sheet_add_aoa(ws, [
            [`지역명: ${selectedRegion}`, `사업장명: ${selectedBusiness}`]
        ], { origin: 'A1' });

        XLSX.utils.book_append_sheet(wb, ws, '측정 데이터');
        XLSX.writeFile(wb, `측정데이터_${selectedRegion}_${selectedBusiness}.xlsx`);
    }

    /**
     * @function processAndDisplayData
     * @description 측정 데이터 처리 및 시각화 (null 값은 0으로 처리)
     * @param {Array<Object>} data - 측정 데이터 배열
     */
    async function processAndDisplayData(data) {
        resetTraces();

        data.forEach(item => {
            const timestamp = item.mesure_dt;
            tspTrace.x.push(timestamp);
            tspTrace.y.push(DataUtils.normalizeChartValue(item.tsp_mesure_value));
            soxTrace.x.push(timestamp);
            soxTrace.y.push(DataUtils.normalizeChartValue(item.sox_mesure_value));
            noxTrace.x.push(timestamp);
            noxTrace.y.push(DataUtils.normalizeChartValue(item.nox_mesure_value));
            hclTrace.x.push(timestamp);
            hclTrace.y.push(DataUtils.normalizeChartValue(item.hcl_mesure_value));
            coTrace.x.push(timestamp);
            coTrace.y.push(DataUtils.normalizeChartValue(item.co_mesure_value));
        });

        chartData = [tspTrace, soxTrace, noxTrace, hclTrace, coTrace];
        layout = {
            title: "측정 그래프",
            xaxis: {
                title: "측정 시간",
                type: "date",
                // 241104 일자별로 그룹화 추가
                tickformat: "%Y.%m.%d", // '-' 를 '.' 으로 변경
                // tickangle: -45, // x축의 각도 조정
                automargin: true,
                hoverformat: "%Y.%m.%d %H:%M", // hover 형식도 '.' 으로 변경
                dtick: 86400000,  // 1일 간격으로 눈금 표시 (밀리초 단위)
                tickmode: 'linear',  // 선형 간격으로 눈금 표시
                tickvals: tspTrace.x,
                ticktext: tspTrace.x.map(x => moment(x).format("YYYY.MM.DD HH:mm")) // moment 형식도 변경
            },
            yaxis: {
                title: "측정값",
                rangemode: "tozero",
                autorange: true
            },
            margin: { b: 120 },
            hovermode: 'x unified',
            hoverlabel: {
                bgcolor: "#FFF",
                bordercolor: "#888",
                font: { size: "12" }
            }
        };

        await Plotly.newPlot("measurementChart", chartData, layout);
        generateDataTable(data);
        generateDynamicHeader();
        document.getElementById('dataTableContainer').style.display = 'block';
    }

    /**
     * @description 이벤트 리스너 설정
     * - 지역 선택 시 사업장 목록 조회
     * - 사업장 선택 시 배출구 목록 조회
     * - 조회 버튼 클릭 시 측정 데이터 조회
     */
    $('#regionSelect').change(async function() {
        const selectedRegion = $(this).val();
        if (!selectedRegion) return;

        try {
            $('#loadingSpinner').show();
            const businesses = await getCachedData(
                `businesses-${selectedRegion}`,
                fetchBusinesses,
                selectedRegion
            );

            // select 요소들 초기화
            resetSelect('businessSelect');
            resetSelect('stackSelect');

            // 사업장 옵션 추가
            const businessSelect = document.getElementById('businessSelect');
            businesses.forEach(business => {
                const option = document.createElement('option');
                option.value = business;
                option.textContent = business;
                businessSelect.appendChild(option);
            });
        } catch (error) {
            alert('사업장 목록을 불러오는데 실패했습니다.');
        } finally {
            $('#loadingSpinner').hide();
        }
    });

    $('#businessSelect').change(async function() {
        const selectedBusiness = $(this).val();
        if (!selectedBusiness) return;

        try {
            $('#loadingSpinner').show();
            const stacks = await getCachedData(
                `stacks-${selectedBusiness}`,
                fetchStacks,
                selectedBusiness
            );

            // select 요소 초기화
            resetSelect('stackSelect');

            // 배출구 옵션 추가
            const stackSelect = document.getElementById('stackSelect');
            stacks.forEach(stack => {
                const option = document.createElement('option');
                option.value = stack;
                option.textContent = stack;
                stackSelect.appendChild(option);
            });
        } catch (error) {
            alert('배출구 목록을 불러오는데 실패했습니다.');
        } finally {
            $('#loadingSpinner').hide();
        }
    });


    $('#searchButton').click(async function() {
        const selectedBusiness = $('#businessSelect').val();
        const selectedStack = $('#stackSelect').val();

        if (!selectedBusiness || !selectedStack) {
            alert(CONFIG.ERROR_MESSAGES.VALIDATION);
            return;
        }

        try {
            $('#loadingSpinner').show();

            const data = await $.ajax({
                url: '/api/measurements',
                data: {
                    business: selectedBusiness,
                    stackCode: selectedStack
                },
                method: 'GET'
            });

            if (!data || data.length === 0) {
                alert(CONFIG.ERROR_MESSAGES.NO_DATA);
                return;
            }

            await processAndDisplayData(data);
        } catch (error) {
            console.error("데이터 조회 실패:", error);
            alert(CONFIG.ERROR_MESSAGES.FETCH_DATA);
        } finally {
            $('#loadingSpinner').hide();
        }
    });
});
