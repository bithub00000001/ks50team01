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


// 여행 계획 탭 작성, 드래그드랍 구현
$(document).ready(function() {
    function assignNumbers() {
        const items = document.querySelectorAll('#plan .sortable-item');
        for (let i = 0; i < items.length; ++i) {
            const numberSpan = items[i].querySelector('.number');
            numberSpan.textContent = i + 1;
        }
    }
    // Sortable 초기화
    const sortableList = new Sortable(document.getElementById('plan'), {
        animation: 150,
        ghostClass: 'sortable-ghost',
        // removeOnSpill: true,
        removeOnSpill: false, // 밖으로 빠져나가지 않도록 변경
        forceFallback: true,  // 추가된 줄
        onUpdate: function (evt) {
            updatePlan();
            assignNumbers();
                // updateDistanceInfo(selectedOptions);
        },
        onSpill: function (evt) {
            evt.item.parentElement.appendChild(evt.item);
        }
    });

    // 방지 코드 삽입
    const planItems = document.querySelectorAll('.sortable-item');
    planItems.forEach(function(item) {
        item.addEventListener('dragstart', function(e) {
            e.preventDefault();

        });
    });

    function updatePlan() {
        const planList = document.getElementById('plan');
        const selectedOptions = Array.from(planList.getElementsByTagName('li')).map(option => option.innerText);
        // selectedOptions 변수를 업데이트하고 이 변수를 사용하여 updateDistanceInfo 함수를 호출합니다.
        //updateDistanceInfo(selectedOptions);
    }
    // 처음 한 번은 수동으로 실행
    updatePlan();
    assignNumbers();
});


// 담기 버튼 함수
$(document).ready(function() {
    // 예시 데이터 가져오기
    const accommodationData = getDataFromHTML('#accommodationOptions');
    const restaurantData = getDataFromHTML('#restaurantOptions');
    const tourData = getDataFromHTML('#tourOptions');


    // 예시 데이터 렌더링
    renderOptions('#accommodationOptions', accommodationData);
    renderOptions('#restaurantOptions', restaurantData);
    renderOptions('#tourOptions', tourData);


    // HTML에서 데이터 가져오기 함수
    function getDataFromHTML(selector) {
        const data = [];
        $(selector + ' .list-group-item').each(function() {
            const name = $(this).find('h5').text();
            const description = $(this).find('p:first').text();
            const schedule = $(this).find('input[type="text"]:first').val();
            const additional = $(this).find('input[type="text"]:last').val();
            const id = $(this).attr('data-id');
            data.push({ name, description, schedule, additional, id });
        });
        return data;
    }


    // 옵션 렌더링 함수
    function renderOptions(selector, data) {
        let options = '';
        $.each(data, function (index, item) {
            const accommodationType = getAccommodationType(selector);
            options += `<li class="list-group-item" data-id="${item._id}">
            <h5>${item.name}</h5>
            <p>${item.description}</p>
            <p>세부 스케줄: <input type="text" value="${item.schedule}" class="schedule-input"></p>
            <p>추가 사항: <input type="text" value="${item.additional}" class="additional-input"></p>
            <button class="btn btn-primary btn-addPlan btn-sm">담기</button>
          </li>`;
        });
        $(selector).html(options);
        // Add click event to button "btn-add" to add to the plan
        $(selector).on("click", ".btn-addPlan", function() {
            const element = $(this).closest('li');
            const name = element.find("h5").text();
            const schedule = element.find(".schedule-input").val();
            const additional = element.find(".additional-input").val();
            const id = element.data('id');
            const category = getAccommodationType(selector);
            addToPlan(name, category, schedule, additional, id);
            $(this).blur() // 담기 버튼 클릭 후 포커스 제거
        });
    }

    // 여행지 분류
    function getAccommodationType(selector) {
        return selector.includes('accommodation') ? '숙박' : (selector.includes('restaurant') ? '식당' : '관광지');
    }


    // 여행 계획 추가 함수
    function addToPlan(name, category, schedule, additional, id) {
        const newItem = `
        <li class="sortable-item list-group-item list-group-item-action" data-id="${id}">
            <div class="d-flex w-100 justify-content-between">
                <span class="number">${$('#plan .sortable-item').length + 1}</span>
                <h4 class="col-9">${name}</h4>
                <small>여기에 시간 입력</small>
            </div>
            <p class="text-black mb-1">${schedule}</p>
            <small>${category}</small>
            <small>${additional}</small>
         </li>`;
        $('#plan').append(newItem);
    }
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
