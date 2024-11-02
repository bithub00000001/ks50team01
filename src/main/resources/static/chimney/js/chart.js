$(document).ready(function() {
    // 상수 정의
    const CONFIG = {
        CACHE_TTL: 7 * 24 * 60 * 60 * 1000, // 7일(밀리초 단위)
        TRACE_COLORS: {
            TSP: 'rgba(136,132,216,1)',
            SOX: 'rgba(130,202,157,1)',
            NOX: 'rgba(255,198,88,1)',
            HCL: 'rgba(255,115,0,1)',
            CO: 'rgba(56,121,8,1)'
        }
    };

    // 차트 상태 관리
    let chartData = [];
    let layout = {};

    // 트레이스 정의
    let tspTrace = {
        x: [], y: [],
        mode: 'lines+markers',
        name: "TSP 먼지",
        line: { color: CONFIG.TRACE_COLORS.TSP },
        marker: { size: 6 }
    };

    let soxTrace = {
        x: [], y: [],
        mode: 'lines+markers',
        name: "SOX 황산화물",
        line: { color: CONFIG.TRACE_COLORS.SOX },
        marker: { size: 6 }
    };

    let noxTrace = {
        x: [], y: [],
        mode: 'lines+markers',
        name: "NOX 질소산화물",
        line: { color: CONFIG.TRACE_COLORS.NOX },
        marker: { size: 6 }
    };

    let hclTrace = {
        x: [], y: [],
        mode: 'lines+markers',
        name: "HCL 염화수소",
        line: { color: CONFIG.TRACE_COLORS.HCL },
        marker: { size: 6 }
    };

    let coTrace = {
        x: [], y: [],
        mode: 'lines+markers',
        name: "CO 일산화탄소",
        line: { color: CONFIG.TRACE_COLORS.CO },
        marker: { size: 6 }
    };

    // 캐시 관리 함수들
    function setCache(key, data) {
        const item = {
            data,
            expiry: Date.now() + CONFIG.CACHE_TTL
        };
        try {
            localStorage.setItem(key, JSON.stringify(item));
        } catch (e) {
            console.warn('캐시 저장 실패:', e);
        }
    }

    function getCache(key) {
        try {
            const itemStr = localStorage.getItem(key);
            if (!itemStr) return null;

            const item = JSON.parse(itemStr);
            if (Date.now() > item.expiry) {
                localStorage.removeItem(key);
                return null;
            }
            return item.data;
        } catch (e) {
            console.warn('캐시 조회 실패:', e);
            return null;
        }
    }

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

    // API 호출 함수들
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

    // UI 관련 함수들
    function createOptionElement(value) {
        const option = document.createElement('option');
        option.value = value;
        option.textContent = value;
        return option;
    }

    function resetTraces() {
        [tspTrace, soxTrace, noxTrace, hclTrace, coTrace].forEach(trace => {
            trace.x = [];
            trace.y = [];
        });
    }

    function updateChart() {
        const visibleDates = [];
        $('.date-toggle:checked').each(function() {
            visibleDates.push({
                date: $(this).data('date'),
                tspMesureValue: $(this).data('tsp'),
                soxMesureValue: $(this).data('sox'),
                noxMesureValue: $(this).data('nox'),
                hclMesureValue: $(this).data('hcl'),
                coMesureValue: $(this).data('co')
            });
        });

        resetTraces();

        visibleDates.forEach(item => {
            tspTrace.x.push(item.date);
            tspTrace.y.push(item.tspMesureValue);
            soxTrace.y.push(item.soxMesureValue);
            noxTrace.y.push(item.noxMesureValue);
            hclTrace.y.push(item.hclMesureValue);
            coTrace.y.push(item.coMesureValue);
        });

        Plotly.redraw('measurementChart');
    }

    // 이벤트 핸들러들
    $('#regionSelect').change(async function() {
        const selectedRegion = $(this).val();
        if (!selectedRegion) return;

        try {
            const businesses = await getCachedData(
                `businesses-${selectedRegion}`,
                fetchBusinesses,
                selectedRegion
            );

            const businessSelect = $('#businessSelect')
                .empty()
                .append('<option value="">사업장 선택</option>');

            $('#stackSelect')
                .empty()
                .append('<option value="">배출구 선택</option>');

            businesses.forEach(business => {
                businessSelect.append(createOptionElement(business));
            });
        } catch (error) {
            alert('사업장 목록을 불러오는데 실패했습니다.');
        }
    });

    $('#businessSelect').change(async function() {
        const selectedBusiness = $(this).val();
        if (!selectedBusiness) return;

        try {
            const stacks = await getCachedData(
                `stacks-${selectedBusiness}`,
                fetchStacks,
                selectedBusiness
            );

            const stackSelect = $('#stackSelect')
                .empty()
                .append('<option value="">배출구 선택</option>');

            stacks.forEach(stack => {
                stackSelect.append(createOptionElement(stack));
            });
        } catch (error) {
            alert('배출구 목록을 불러오는데 실패했습니다.');
        }
    });

    $('#searchButton').click(async function() {
        const selectedBusiness = $('#businessSelect').val();
        const selectedStack = $('#stackSelect').val();

        if (!selectedBusiness || !selectedStack) {
            alert('사업장과 배출구를 모두 선택해주세요.');
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
                alert('조회된 측정 데이터가 없습니다.');
                return;
            }

            processAndDisplayData(data);
        } catch (error) {
            console.error('측정 데이터 조회 실패:', error);
            alert('측정 데이터를 불러오는데 실패했습니다.');
        } finally {
            $('#loadingSpinner').hide();
        }
    });

    function processAndDisplayData(data) {
        resetTraces();
        $('#dateCheckboxes').empty();

        const groupedDataByDate = data.reduce((acc, item) => {
            const dateLabel = moment(item.mesure_dt).format("YYYY-MM-DD");
            if (!acc[dateLabel]) acc[dateLabel] = [];
            acc[dateLabel].push(item);
            return acc;
        }, {});

        Object.entries(groupedDataByDate).forEach(([dateLabel, items]) => {
            const timeValues = items
                .map(item => moment(item.mesure_dt).format("HH:mm"))
                .join(',');

            const colDiv = $('<div>')
                .addClass('col-md-3 col-sm-6')
                .appendTo('#dateCheckboxes');

            $('<label>')
                .addClass('form-check-label')
                .append(
                    $('<input>')
                        .attr('type', 'checkbox')
                        .addClass('form-check-input date-toggle')
                        .attr('data-date', dateLabel)
                        .prop('checked', true)
                )
                .append(` ${dateLabel} (${timeValues})`)
                .appendTo(colDiv);

            items.forEach(item => {
                tspTrace.x.push(item.mesure_dt);
                tspTrace.y.push(item.tsp_mesure_value);
                soxTrace.x.push(item.mesure_dt);
                soxTrace.y.push(item.sox_mesure_value);
                noxTrace.x.push(item.mesure_dt);
                noxTrace.y.push(item.nox_mesure_value);
                hclTrace.x.push(item.mesure_dt);
                hclTrace.y.push(item.hcl_mesure_value);
                coTrace.x.push(item.mesure_dt);
                coTrace.y.push(item.co_mesure_value);
            });
        });

        chartData = [tspTrace, soxTrace, noxTrace, hclTrace, coTrace];
        layout = {
            title: '측정 그래프',
            xaxis: {
                title: '측정 시간',
                type: 'date',
                tickformat: '%Y-%m-%d %H:%M'
            },
            yaxis: {
                title: '측정값',
                rangemode: 'tozero',
                autorange: true
            }
        };

        Plotly.newPlot('measurementChart', chartData, layout);
        attachCheckboxHandlers();
    }

    function attachCheckboxHandlers() {
        $('.date-toggle').change(function() {
            const checkedCount = $('.date-toggle:checked').length;
            if (checkedCount === 0) {
                alert('최소 하나의 날짜는 선택해야 합니다.');
                $(this).prop('checked', true);
                return;
            }

            const isChecked = $(this).is(':checked');
            $(this).closest('div')
                .find('.hidden-checkboxes input')
                .prop('checked', isChecked);

            updateChart();
        });
    }
});
