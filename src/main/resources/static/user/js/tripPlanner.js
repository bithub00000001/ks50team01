/*// 지역 선택 이벤트 핸들러
$('#region').change(function() {
    const selectedRegion = $(this).val();
    updateOptions(selectedRegion);
});

// 옵션 업데이트 함수
function updateOptions(region) {
    $('#accommodationOptions').empty();
    $('#restaurantOptions').empty();
    $('#attractionOptions').empty();

    if (!region) return;

    fetchOptions(region, 'accommodation')
        .then(options => {
            options.forEach(option => {
                const $option = $('<li>').text(option);
                $('#accommodationOptions').append($option);
            });
        })
        .catch(error => console.error(error));

    fetchOptions(region, 'restaurant')
        .then(options => {
            options.forEach(option => {
                const $option = $('<li>').text(option);
                $('#restaurantOptions').append($option);
            });
        })
        .catch(error => console.error(error));

    fetchOptions(region, 'attraction')
        .then(options => {
            options.forEach(option => {
                const $option = $('<li>').text(option);
                $('#attractionOptions').append($option);
            });
        })
        .catch(error => console.error(error));
}

// 서버에서 옵션 데이터 가져오기
function fetchOptions(region, category) {
    return fetch(`/options/${region}/${category}`)
        .then(response => response.json())
        .catch(error => console.error(error));
}*/

/*// 예시 데이터
const exampleData = ['경복궁', 'N서울타워', '동대문디자인플라자'];

// 여행 계획 업데이트 함수
function updatePlan(selectedOptions) {
    $('#plan').empty();
    $('#distanceInfo').empty();

    const options = selectedOptions.concat(exampleData);

    options.forEach(option => {
        const $item = $('<li class="sortable-item">').text(option);
        $('#plan').append($item);
    });

    // 순서 변경 가능하도록 Sortable 플러그인 초기화
    new Sortable(document.getElementById('plan'), {
        animation: 150,
        ghostClass: 'sortable-ghost',
        removeOnSpill: true, // 이 옵션을 추가합니다.
        onUpdate: function(evt) {
            updateDistanceInfo(selectedOptions);
        }
    });
    // 방지 코드 삽입
    const planItems = document.querySelectorAll('.sortable-item');
    planItems.forEach(item => {
        item.addEventListener('dragstart', function (e){
            e.preventDefault();
        });
    });
    updateDistanceInfo(options);
}*/
$(document).ready(function() {
    // 페이지 로딩시 모든 sortable 항목에 대해 드래그 기능 초기화
    $('.sortable').each(function (i, el) {
        initializeSortable(el.id);
    });
    $(document).on('click', '.btn-addPlan', function() {
        const element = $(this).closest('li');
        const name = element.find('h5').text();
        const schedule = element.find('.schedule-input').val();
        const additional = element.find('.additional-input').val();
        const id = element.data('id');
        const category = getAccommodationType($(this).closest('.tab-pane').attr('id'));

        addToPlan(name, category, schedule, additional, id);
    });

});

function getAccommodationType(selector) {
    return selector.includes('accommodation') ? '숙박' : (selector.includes('restaurant') ? '식당' : '관광지');
}

function addToPlan(name, category, schedule, additional, id) {
    const activeTabLink = $('#myDayTab .nav-link.active');
    const activeTabContentId = activeTabLink.attr('href');
    const activeTabContent = $(activeTabContentId);
    const planDayX = activeTabContent.find('ul.sortable').attr('id');

    const newItem = `
    <li class="sortable-item list-group-item list-group-item-action" data-id="${id}">
        <div class="row align-items-center">
            <div class="col-1">
                <div class="border border-dark bg-facebook text-white rounded-circle d-flex justify-content-center align-items-center mt-1" style="height:22px;width:22px;">
                    <span class="number">${$(`#${planDayX} .sortable-item`).length + 1}</span>
                </div>
            </div>
            <div class="col-7 mt-2">
                <h4 class="col-9">${name}</h4>
            </div>
            <div class="col-4 text-end">
                <small>여기에 시간 입력</small>
            </div>
        </div>
        <button type="button" class="btn btn-outline-danger btn-delete btn-xs float-end">제거</button>
        <p class="text-black mb-1">${schedule}</p>
                <small>${category}</small>
                <small>${additional}</small>
            </div>
        </div>
    </li>`;

    $(`#${planDayX}`).append(newItem);
    initializeSortable(planDayX);
}

function initializeSortable(id) {
    const el = document.getElementById(id);
    const sortable = Sortable.create(el, {
        animation: 150,
        ghostClass: 'sortable-ghost',
        chosenClass: 'sortable-chosen',
        dragClass: 'sortable-drag',
        onUpdate: function (evt) {
            $(`#${id} .sortable-item`).each(function (index, item) {
                $(item).find('.number').text(index + 1);
            });
        }
    });
}



// 현재 일차 계산
$('.add-day').click(function () {
    let dayCount = $("#myDayTab .nav-item:not(.add-day)").length + 1;
    let newTabId = "day" + dayCount + "-tab";

    // 새로운 탭 메뉴 항목 생성
    let newTab = $('<li/>', {
        'class': 'nav-item',
        'role': 'presentation',
        'html': $('<a/>', {
            'id': newTabId,
            'class': 'nav-link',
            'data-bs-toggle': 'tab',
            'href': `#day${dayCount}`,
            'role': 'tab',
            'aria-controls': `day${dayCount}`,
            'aria-selected': false,
            'text': `${dayCount}일차`
        })
    }).insertBefore('.add-day');

    // 새로운 탭 내용 생성
    $('<div/>', {
        'class': 'tab-pane fade show active', // 여기서 fade 클래스 추가
        'id': `day${dayCount}`,
        'role': 'tabpanel',
        'aria-labelledby': newTabId,
        'html': $('<ul/>', {
            'class': 'sortable',
            'id': `planDay${dayCount}`,
        })
    }).appendTo('#myDayTabContent');

    // 새로 생성한 탭 활성화
    newTab.find('.nav-link').tab('show');
});

/*
// 옵션 업데이트 함수
function updateOptions(region) {
  $('#accommodationOptions').empty();
  $('#restaurantOptions').empty();
  $('#attractionOptions').empty();
  $('#plan').empty();

  if (!region) return;

  fetchOptions(region, 'accommodation')
    .then(options => {
      options.forEach(option => {
        const $option = $('<li>').text(option);
        $('#accommodationOptions').append($option);
      });
    })
    .catch(error => console.error(error));

  fetchOptions(region, 'restaurant')
    .then(options => {
      options.forEach(option => {
        const $option = $('<li>').text(option);
        $('#restaurantOptions').append($option);
      });
    })
    .catch(error => console.error(error));

  fetchOptions(region, 'attraction')
    .then(options => {
      options.forEach(option => {
        const $option = $('<li>').text(option);
        $('#attractionOptions').append($option);
      });
    })
    .catch(error => console.error(error));

  const options = exampleData;

  options.forEach(option => {
    const $item = $('<li class="sortable-item">').text(option);
    $('#plan').append($item);
  });

  // 순서 변경 가능하도록 Sortable 플러그인 초기화
  new Sortable(document.getElementById('plan'), {
    animation: 150,
    ghostClass: 'sortable-ghost',
    removeOnSpill: true, // 이 옵션을 추가합니다.
    onUpdate: function(evt) {
      updateDistanceInfo(options);
    }
  });

  updateDistanceInfo(options);
}
}*/

/*
// 거리 정보 업데이트 함수
function updateDistanceInfo(options) {
    const $sortedItems = $('#plan .sortable-item');
    const locations = $sortedItems.map(function() {
        return $(this).text();
    }).get();

    $('#distanceInfo').empty();

    for (let i = 0; i < locations.length - 1; i++) {
        const start = locations[i];
        const end = locations[i + 1];
        const distance = calculateDistance(start, end); // 실제 거리 계산 로직 구현 필요

        const $distanceItem = $('<div>').text(`${start} → ${end}: ${distance}`);
        $('#distanceInfo').append($distanceItem);
    }
}

// 거리 계산 함수 (예시)
function calculateDistance(start, end) {
    // 실제 거리 계산 로직 구현 필요
    return `${Math.floor(Math.random() * 100)} km`;

    // 여기에 지도 API 및 거리/금액 계산 기능을 추가하면 된다
}*/
