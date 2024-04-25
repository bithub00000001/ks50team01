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
    // Sortable 초기화
    var sortableList = new Sortable(document.getElementById('plan'), {
        animation: 150,
        ghostClass: 'sortable-ghost',
        // removeOnSpill: true,
        removeOnSpill: false, // 밖으로 빠져나가지 않도록 변경
        forceFallback: true,  // 추가된 줄
        onUpdate: function(evt) {
            updatePlan();
            // updateDistanceInfo(selectedOptions);
        },
        onSpill: function (evt) {
            evt.item.parentElement.appendChild(evt.item);
        }
    });

    // 방지 코드 삽입
    var planItems = document.querySelectorAll('.sortable-item');
    planItems.forEach(function(item) {
        item.addEventListener('dragstart', function(e) {
            e.preventDefault();
        });
    });

    function updatePlan() {
        var planList = document.getElementById('plan');
        var selectedOptions = Array.from(planList.getElementsByTagName('li')).map(option => option.innerText);
        // selectedOptions 변수를 업데이트하고 이 변수를 사용하여 updateDistanceInfo 함수를 호출합니다.
        //updateDistanceInfo(selectedOptions);
    }
    // 처음 한 번은 수동으로 실행
    updatePlan();
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
