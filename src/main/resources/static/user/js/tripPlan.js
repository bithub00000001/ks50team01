const headingOneButton = $('#headingOne').find("button");
const headingTwoButton = $('#headingTwo').find("button");

// 첫번째 아코디언에서 임시 저장 버튼을 눌렀을때 DB로 값을 저장하는 함수
function firstStepSubmit() {

    // 사용자에게 확인 메시지를 보여주고, 확인 시에만 계속 진행
    if (confirm("입력된 정보로 임시저장 하시겠습니까?")) {

        saveTripInfo(); // AJAX 요청 보내기
        moveToNextAccordion();
    }
}

// 다음 아코디언으로 이동하기 위한 절차를 실행하는 함수
function moveToNextAccordion() {
    // numDate 값을 가져오고, 값이 0이면 1로 설정
    const numDate = ($('#numDate').val() === 0) ? 1 : $('#numDate').val();

    // 현재 탭의 개수를 확인
    const currentTabCount = getCurrentTabCount();

    // 기본 탭 1개를 고려하여 추가할 탭의 개수가 현재 탭의 개수보다 클 경우에만 추가
    if (numDate > currentTabCount) {
        addTap(numDate - currentTabCount);
    }

    // 첫번째 아코디언 버튼을 비활성화, 두번째 아코디언 버튼 활성화
    headingOneButton.prop("disabled", true);
    headingTwoButton.prop("disabled", false);

    // 가장 마지막 탭을 활성화
    const lastTab = $("#myDayTab .nav-item:not(.add-day)").last();
    lastTab.find('a[data-bs-toggle="tab"]').tab('show');

    // 버튼을 클릭하여 다른 섹션으로 이동
    headingTwoButton.click();

    // 버튼 클릭 이벤트를 콜백 함수로 래핑
    setTimeout(function () {
        headingTwoButton.prop("disabled", true);
    }, 0);
}

// 첫번째 아코디언으로 되돌아가는 함수
function backToFirstStepStage() {
    // 사용자에게 확인 메시지를 보여주고, 확인 시에만 계속 진행
    if (confirm("생성한 정보는 초기화됩니다.")) {

        $('#accommodationOptions').empty();
        $('#restaurantOptions').empty();
        $('#tourOptions').empty();
        $('#myDayTabContent').empty();
        $('#totalInfo').empty();

        // 추가된 탭 제거
        removeAddedTabs();


        // 첫번째 아코디언 버튼을 활성화, 두번째 아코디언 버튼 비활성화
        headingOneButton.prop('disabled', false);
        headingTwoButton.prop('disabled', true);

        // 현재 선택된 탭 링크의 aria-selected 속성을 false로 설정
        const $selectedTab = $('.nav-link.active[role="tab"]');
        $selectedTab.attr('aria-selected', 'false');
        $selectedTab.removeClass('active');

        // 버튼을 클릭하여 다른 섹션으로 이동
        headingOneButton.click();

        // 버튼 클릭 이벤트를 콜백 함수로 래핑해서 최후순위로 실행
        setTimeout(function () {
            headingOneButton.prop('disabled', true);
        }, 0);

    }
}

// 기본 탭을 제외한 추가된 탭 제거하는 함수
function removeAddedTabs() {
    $("#myDayTab .nav-item:not(:first-child):not(.add-day)").remove();
}

// 일자 탭을 추가하는 함수
function addTap(tapCnt) {
    for (let i = 0; tapCnt > i; i++) {
        addTabFunction();
    }
}

// 현재 활성화 된 탭의 갯수를 반환하는 함수
function getCurrentTabCount() {
    // 기본 탭을 포함하여 현재 존재하는 탭의 개수를 반환
    return $("#myDayTab .nav-item:not(.add-day)").length;
}

// 여행지 제목 등 큰 분류의 계획을 저장하는 함수
function saveTripInfo() {
    const formData = $('form').serializeArray(); // 폼 데이터를 배열로 변환
    const jsonData = {}; // JSON 객체 생성

    // 폼 데이터를 JSON 객체로 변환 1
    /*$.each(formData, function() {
        if (jsonData[this.name]) {
            // jsonData[this.name]에 push 메서드가 있는지 검사
            // if (!jsonData[this.name].push) {
            // 이렇게 작성하는게 ES5 문법에 맞는 방식이고 위와 같이 작성하는게 옛날 방식
            if (!Array.isArray(jsonData[this.name])) {
                jsonData[this.name] = [jsonData[this.name]];
            }
            jsonData[this.name].push(this.value || '');
        } else {
            jsonData[this.name] = this.value || '';
        }
    });*/

    // 폼 데이터를 JSON 객체로 변환 2: ES6에 맞도록 수정
    $.each(formData, function () {
        if (jsonData[this.name]) {
            jsonData[this.name] = Array.isArray(jsonData[this.name])
                ? [...jsonData[this.name], this.value || '']
                : [jsonData[this.name], this.value || ''];
        } else {
            jsonData[this.name] = this.value || '';
        }
    });

    $.ajax({
        type: 'POST',
        url: '/trip/save-temp-plan-info',
        data: JSON.stringify(jsonData), // JSON 데이터로 변환
        contentType: 'application/json; charset=utf-8', // JSON 형식 명시
        dataType: 'json', // 응답 데이터 형식 명시 (선택적)
        success: function (response) {
            firstStepSubmit();
        },
        error: function (xhr, status, error) {
            alert('임시 저장에 실패했습니다.');
        }
    });
}



