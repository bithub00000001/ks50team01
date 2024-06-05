const headingOneButton = $('#headingOne').find("button");
const headingTwoButton = $('#headingTwo').find("button");

// 첫번째 아코디언에서 임시 저장 버튼을 눌렀을때 DB로 값을 저장하는 함수
function firstStepSubmit() {
    const sessionLoginId = $('#sessionId').val();
    if (!sessionLoginId) {
        alert("로그인되지 않았습니다");
        return;
    }

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

// uuid가 없을 경우에만 생성하도록 전역 변수로 선언
let uuid;

// 여행지 제목 등 큰 분류의 계획을 저장하는 함수
function saveTripInfo() {
    // UUID 생성 및 jsonData에 추가
    if (!uuid) { // UUID가 없는 경우에만 생성
        uuid = generateUUID();
    }

    const formData = $('#tripPlanForm').serializeArray(); // 폼 데이터를 배열로 변환
    console.log(formData);
    const jsonData = {}; // JSON 객체 생성

    // 폼 데이터를 JSON 객체로 변환 1
    /*$.each(formData, function() {
        if (jsonData[this.name]) {
            // jsonData[this.name]에 push 메서드가 있는지 검사
            // if (!jsonData[this.name].push) {
            // 아래와 같이 작성하는게 ES5 문법에 맞는 방식이고 위와 같이 작성하는게 옛날 방식
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
        if (this.name.startsWith('addVirtualId')) {
            // addVirtualId로 시작하는 경우 virtualMembers 배열에 추가
            const index = this.name.replace('addVirtualId', '');
            if (!jsonData.virtualMembers) {
                jsonData.virtualMembers = [];
            }
            jsonData.virtualMembers[index] = this.value;
        } else if (this.name.startsWith('inviteeName')) {
            // inviteeName로 시작하는 경우 invitedMembers 배열에 추가
            const index = this.name.replace('inviteeName', '');
            if (!jsonData.invitedMembers) {
                jsonData.invitedMembers = [];
            }
            jsonData.invitedMembers[index] = this.value;
        } else {
            // 그 외의 경우 기존 로직 유지
            if (jsonData[this.name]) {
                jsonData[this.name] = Array.isArray(jsonData[this.name])
                    ? [...jsonData[this.name], this.value || '']
                    : [jsonData[this.name], this.value || ''];
            } else {
                jsonData[this.name] = this.value || '';
            }
        }
    });

    if (jsonData.virtualMembers) jsonData.numVirtualMembers = jsonData.virtualMembers.length;
    if (jsonData.invitedMembers) jsonData.numRealMembers = jsonData.invitedMembers.length;

    jsonData.planUUID = uuid;

    console.log(jsonData);

    $.ajax({
        type: 'POST',
        url: '/trip/save-temp-plan-info',
        data: JSON.stringify(jsonData), // JSON 데이터로 변환
        contentType: 'application/json; charset=utf-8', // JSON 형식 명시
        success: function (response) {
            alert(response);
            // firstStepSubmit();
        },
        error: function (xhr, status, error) {
            alert('임시 저장에 실패했습니다.');
        }
    });
}

// 240605: UUID 생성 함수: 표준에 따른 UUID를 생성하는 함수(v4 표준을 지키려면 3번째 세그먼트가 4로 시작해야하고 4번째 세그먼트가 8,9,A,B여야함)
function generateUUID() {
    let dt = new Date().getTime();
    const generateUuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        const r = (dt + Math.random() * 16) % 16 | 0;
        dt = Math.floor(dt / 16);
        return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return generateUuid;
}


