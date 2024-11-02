$(document).ready(function() {
    const CACHE_TTL = 7 * 24 * 60 * 60 * 1000; // 7일(밀리초 단위)
    let chartData = []; // Plotly 데이터 저장 변수
    let layout = {}; // Plotly 레이아웃 저장 변수

    // 전역 트레이스 선언
    let tspTrace = { x: [], y: [], mode: 'lines+markers', name: "TSP 먼지", line: { color: 'rgba(136,132,216,1)' }, marker: { size: 6 } };
    let soxTrace = { x: [], y: [], mode: 'lines+markers', name: "SOX 황산화물", line: { color: 'rgba(130,202,157,1)' }, marker: { size: 6 } };
    let noxTrace = { x: [], y: [], mode: 'lines+markers', name: "NOX 질소산화물", line: { color: 'rgba(255,198,88,1)' }, marker: { size: 6 } };
    let hclTrace = { x: [], y: [], mode: 'lines+markers', name: "HCL 염화수소", line: { color: 'rgba(255,115,0,1)' }, marker: { size: 6 } };
    let coTrace = { x: [], y: [], mode: 'lines+markers', name: "CO 일산화탄소", line: { color: 'rgba(56,121,8,1)' }, marker: { size: 6 } };

    // 지역 선택 시 사업장 목록 로드 함수입니다.
    $('#regionSelect').change(async function() {
        const selectedRegion = $(this).val();
        if (!selectedRegion) return;

        $('#businessSelect').empty().append('<option value="">사업장 선택</option>');
        $('#stackSelect').empty().append('<option value="">배출구 선택</option>');

        // 캐시에서 데이터 가져오기 또는 서버에서 새로 가져오기
        const businesses = await getCachedData(`businesses-${selectedRegion}`, fetchBusinesses, selectedRegion);
        businesses.forEach(business => {
            $('#businessSelect').append(`<option value="${business}">${business}</option>`);
        });
    });

    // 사업장 선택 시 배출구 목록 로드 함수입니다.
    $('#businessSelect').change(async function() {
        const selectedBusiness = $(this).val();
        if (!selectedBusiness) return;

        $('#stackSelect').empty().append('<option value="">배출구 선택</option>');

        // 캐시에서 데이터 가져오기 또는 서버에서 새로 가져오기
        const stacks = await getCachedData(`stacks-${selectedBusiness}`, fetchStacks, selectedBusiness);
        stacks.forEach(stack => {
            $('#stackSelect').append(`<option value="${stack}">${stack}</option>`);
        });
    });

    // 사업장 목록을 가져오는 비동기 함수
    async function fetchBusinesses(region) {
        const response = await $.ajax({
            url: '/api/businesses',
            data: { region },
            method: 'GET',
            cache: false
        });
        return response;
    }

    // 배출구 목록을 가져오는 비동기 함수
    async function fetchStacks(business) {
        const response = await $.ajax({
            url: '/api/stacks',
            data: { business },
            method: 'GET',
            cache: false
        });
        return response;
    }

    // 캐시에 데이터를 저장하는 함수 (TTL 포함)
    function setCache(key, data, ttl) {
        const now = new Date();
        const item = {
            data,
            expiry: now.getTime() + ttl // TTL 이후 만료
        };
        localStorage.setItem(key, JSON.stringify(item));
    }

    // 캐시에서 데이터를 가져오는 함수 (만료 시간 체크)
    function getCache(key) {
        const itemStr = localStorage.getItem(key);

        if (!itemStr) return null;

        const item = JSON.parse(itemStr);
        const now = new Date();

        // 만료 시간이 지났으면 null 반환 (캐시 무효화)
        if (now.getTime() > item.expiry) {
            localStorage.removeItem(key); // 캐시 삭제
            return null;
        }

        return item.data;
    }

    // 캐시에서 데이터 가져오기 또는 서버에서 새로 가져오는 함수
    async function getCachedData(cacheKey, fetchFunction, param) {
        const cachedData = getCache(cacheKey);

        if (cachedData) {
            return cachedData; // 캐시된 데이터 사용
        }

        const freshData = await fetchFunction(param); // 서버에서 새 데이터 가져오기
        setCache(cacheKey, freshData, CACHE_TTL); // 7일 TTL 설정

        return freshData;
    }

    /**
     * searchButton 클릭 시 차트 그리기 및 체크박스에 데이터 저장
     */
    $('#searchButton').click(function() {
        const selectedBusiness = $('#businessSelect').val();
        const selectedStack = $('#stackSelect').val();

        if (!selectedBusiness || !selectedStack) {
            alert('사업장과 배출구를 모두 선택해주세요.');
            return;
        }

        $.ajax({
            url : '/api/measurements',
            data : { business : selectedBusiness, stackCode : selectedStack },
            success : function(data) {
                chartData.length = 0;  // 기존 차트 데이터 초기화

                tspTrace.x.length = tspTrace.y.length = 0;
                soxTrace.x.length = soxTrace.y.length = 0;
                noxTrace.x.length = noxTrace.y.length = 0;
                hclTrace.x.length = hclTrace.y.length = 0;
                coTrace.x.length = coTrace.y.length = 0;

                $('#dateCheckboxes').empty();  // 기존 체크박스 초기화

                data.forEach(function(item) {
                    const dateLabel = moment(item.mesure_dt).format("YYYY-MM-DD HH:mm:ss");

                    tspTrace.x.push(dateLabel);
                    tspTrace.y.push(item.tsp_mesure_value);

                    soxTrace.x.push(dateLabel);
                    soxTrace.y.push(item.sox_mesure_value);

                    noxTrace.x.push(dateLabel);
                    noxTrace.y.push(item.nox_mesure_value);

                    hclTrace.x.push(dateLabel);
                    hclTrace.y.push(item.hcl_mesure_value);

                    coTrace.x.push(dateLabel);
                    coTrace.y.push(item.co_mesure_value);

                    // 체크박스에 데이터 저장
                    $('#dateCheckboxes').append(
                        `<label><input type="checkbox" class="date-toggle" 
                             data-date="${dateLabel}"
                             data-tsp="${item.tsp_mesure_value}"
                             data-sox="${item.sox_mesure_value}"
                             data-nox="${item.nox_mesure_value}"
                             data-hcl="${item.hcl_mesure_value}"
                             data-co="${item.co_mesure_value}" checked> ${dateLabel}</label><br>`
                    );
                });

                chartData.push(tspTrace, soxTrace, noxTrace, hclTrace, coTrace);  // 차트 데이터 추가

                layout = {
                    title : '측정 그래프',
                    xaxis : {
                        title : '측정 시간',
                        type : 'date',
                        tickformat : '%Y-%m-%d %H:%M'
                    },
                    yaxis : {
                        title : '측정값',
                        rangemode : 'tozero',
                        autorange : true
                    }
                };

                Plotly.newPlot('measurementChart', chartData);  // 차트 그리기

                attachCheckboxHandlers();  // 체크박스 핸들러 연결
            },
            error : function() {
                alert('측정 데이터를 불러오는 데 실패했습니다.');
            }
        });
    });

    /**
     * 체크박스 상태 변경 시 차트 동적 업데이트 및 마지막 체크 해제 방지
     */
    function attachCheckboxHandlers() {
        $('.date-toggle').change(function() {
            const checkedCount = $('.date-toggle:checked').length;

            if (checkedCount === 0) {
                alert('최소 하나의 날짜는 선택해야 합니다.');
                $(this).prop('checked', true);  // 마지막 체크 해제를 방지하고 다시 체크 상태로 유지
                return;
            }

            updateChart();  // 차트 업데이트 호출
        });
    }

    /**
     * 차트 업데이트 함수 - 선택된 날짜에 맞춰 차트를 다시 그립니다.
     */
    function updateChart() {
        let visibleDates = [];  // 선택된 날짜들을 저장할 배열

        $('.date-toggle').each(function() {
            if ($(this).is(':checked')) {  // 체크된 항목만 수집
                visibleDates.push({
                    date : $(this).data('date'),
                    tspMesureValue : $(this).data('tsp'),
                    soxMesureValue : $(this).data('sox'),
                    noxMesureValue : $(this).data('nox'),
                    hclMesureValue : $(this).data('hcl'),
                    coMesureValue : $(this).data('co')
                });
            }
        });

        // 차트 데이터 초기화
        tspTrace.x.length = tspTrace.y.length = 0;
        soxTrace.x.length = soxTrace.y.length = 0;
        noxTrace.x.length = noxTrace.y.length = 0;
        hclTrace.x.length = hclTrace.y.length = 0;
        coTrace.x.length = coTrace.y.length = 0;

        // 선택된 날짜들만 사용하여 차트 데이터 생성
        visibleDates.forEach(function(item) {

            tspTrace.x.push(item.date);
            tspTrace.y.push(item.tspMesureValue);

            soxTrace.x.push(item.date);
            soxTrace.y.push(item.soxMesureValue);

            noxTrace.x.push(item.date);
            noxTrace.y.push(item.noxMesureValue);

            hclTrace.x.push(item.date);
            hclTrace.y.push(item.hclMesureValue);

            coTrace.x.push(item.date);
            coTrace.y.push(item.coMesureValue);
        });

        Plotly.redraw('measurementChart');  // 변경 사항 반영하여 다시 그리기
    }
});
