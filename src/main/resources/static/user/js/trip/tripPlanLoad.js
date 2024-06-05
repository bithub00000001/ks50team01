// 페이지 로드 시 maxPeople 값을 업데이트
$(document).ready(function() {
    updateMaxPeople();

    $('#startDate, #endDate').blur(function() {
        const startDate = $('#startDate').val();
        const endDate = $('#endDate').val();

        if (startDate && endDate) {
            const start = new Date(startDate);
            const end = new Date(endDate);
            const diff = new Date(end - start);
            const days = diff / 1000 / 60 / 60 / 24 + 1; // 일차 계산

            // 종료 날짜가 시작 날짜보다 앞선 경우를 처리
            if(days < 1) {
                alert('종료 날짜가 시작 날짜보다 앞설 수 없습니다.');
                setTimeout(function (){
                    $('#endDate').val('');
                }, 30);
            } else {
                $('#numDate').val(days);
            }
        } else {
            $('#numDate').val(''); // 일차 입력 필드를 초기화
        }
    });

    $(document).on('click', '.detail-modal', function () {
        const contentId = this.dataset.detailContentId;
        clearModalContent();
        $.get(`/trip/detailContentId/${contentId}`, function (data) {
            fillModalContent(data);
        });
        $('#overviewModal').modal('show');

    });
});

// 모달 내용 초기화 함수
function clearModalContent() {
    $('#overviewModal .modal-title').text('');
    $('#overviewModal .modal-body p').text('');
}

// JSON 데이터로 모달 내용 채우기 함수
function fillModalContent(data) {
    // 이미지 태그 생성
    /*const imageUrl = data.positions[0].imageLink; // data에서 이미지 URL 가져오기
    const imageElement = $('<img>').attr('src', imageUrl).attr('alt', data.positions[0].name).css({
        width: '100%', // 필요에 따라 스타일 설정
        height: 'auto'
    });*/

    // 모달 타이틀 설정
    $('#overviewModal .modal-title').text(data.positions[0].name);

    // 모달 바디의 첫 번째 위치에 이미지 추가
    // $('#overviewModal .modal-body').prepend(imageElement);

    // 모달 바디의 p 요소에 텍스트 설정
    $('#overviewModal .modal-body p').text(data.positions[0].overview);
}

/*const kakaoMap = (() => {
    const map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center: new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표
        level: 20 // 지도의 확대 레벨
    });
    return map;
})();*/



// 실제 회원 데이터 (예시)
const realMembers = [
    /*{id: 1, name: '실제 회원 예시 1'},
    {id: 2, name: '실제 회원 예시 2'},
    {id: 3, name: '실제 회원 예시 3'},*/
];

// 실제 회원 데이터 삽입 ajax
/*$.ajax({
    url: "/trip/user-member",
    type: "GET",
    success: function (response) {
        response.forEach(function (user) {
            const userMember = {
                id: user.id,
                name: user.nickname
            };
            realMembers.push(userMember);
        });
        console.log(realMembers);
    }
});*/

// 인원 수 입력 필드에 이벤트 리스너 추가
const numPeopleInput = $('#numPeople');
let maxPeople = 1; // 초기 최대 인원 수

// maxPeople 값을 업데이트하는 함수 정의
function updateMaxPeople() {
    // 입력된 값을 정수로 변환하여 maxPeople에 저장하고, 변환할 수 없는 값이라면 1을 사용
    maxPeople = parseInt(numPeopleInput.val(), 10) || 1;
}

// numPeople input 필드에 입력이 있을 때마다 maxPeople 값을 업데이트
numPeopleInput.on('input', updateMaxPeople);

// 초대된 회원 목록
const invitedMembers = [];

// 가상 회원 목록
const virtualMembers = [];

// 회원 유형 체크박스 이벤트 리스너 설정
const realMemberCheckbox = $('#realMember');
const virtualMemberCheckbox = $('#virtualMember');
const realMemberSearch = $('#realMemberSearch');
const virtualMemberAdjust = $('#virtualMemberAdjust');

// 실제 회원 검색창 표시 여부 설정
realMemberCheckbox.change(() => {
    realMemberSearch.css('display', realMemberCheckbox.is(':checked') ? 'block' : 'none');
});

// 가상 회원 조정창 표시 여부 설정
virtualMemberCheckbox.change(() => {
    virtualMemberAdjust.css('display', virtualMemberCheckbox.is(':checked') ? 'block' : 'none');
});

// 회원 검색 모달 열기 이벤트 설정
const searchMemberBtn = $('#searchMemberBtn');
const searchMemberModal = $(new bootstrap.Modal($('#searchMemberModal'), {}));

searchMemberBtn.click(() => {
    searchResultContainer.empty();
    searchMemberModal.show();
});

// 회원 검색 및 초대 기능
const inviteMembersBtn = $('#inviteMembers');
const searchMemberInput = $('#searchMemberInput');
const searchResultContainer = $('#searchResultContainer');
const invitedMembersList = $('#invitedMembersList');

inviteMembersBtn.click(() => {
    const searchQuery = searchMemberInput.val().trim().toLowerCase();
    const searchResults = realMembers.filter(
        (member) => member.name.toLowerCase().includes(searchQuery)
    );

    const currentMemberCount = invitedMembers.length + virtualMembers.length;

    // 검색 결과 리스트 표시
    searchResultContainer.empty();
    searchResults.forEach((member, idx) => {
        const li = $('<li></li>', {
            class: 'list-group-item d-flex justify-content-between align-items-center mb-1',
            text: member.name
        });

        const inviteBtn = $('<button></button>', {
            class: 'btn btn-primary btn-xs',
            text: '초대'
        });
        // 최대 인원 수를 초과하지 않는 경우에만 초대 버튼 활성화
        if (currentMemberCount < maxPeople || maxPeople === 0) {
            inviteBtn.prop('disabled', invitedMembers.some(invited => invited.id === member.id));
        } else {
            inviteBtn.prop('disabled', true);
        }

        // 이미 초대된 회원은 초대 버튼 비활성화
        inviteBtn.prop('disabled', invitedMembers.some(invited => invited.id === member.id));

        // 초대 버튼 클릭 시 초대된 회원 목록에 추가
        inviteBtn.click(() => {
            if (!invitedMembers.some(invited => invited.id === member.id)) {
                const updatedCurrentMemberCount = invitedMembers.length + virtualMembers.length;
                if (updatedCurrentMemberCount + 1 > maxPeople && maxPeople > 0) {
                    alert(`최대 인원 수(${maxPeople}명)를 초과할 수 없습니다.`);
                    return;
                }
                invitedMembers.push(member);
                renderInvitedMembers();
                inviteBtn.prop('disabled', true);
                // 총 인원 수 재계산 및 렌더링
                renderTotalMemberCount();
            }
        });

        li.append(inviteBtn);
        searchResultContainer.append(li);
    });
});

// 초대된 회원 목록 렌더링 함수
function renderInvitedMembers() {
    invitedMembersList.empty();
    invitedMembers.forEach((member, index) => {
        const li = $('<li></li>', {
            class: 'list-group-item d-flex justify-content-between align-items-center',
            text: member.name
        });

        // 240527: 회원 아이디를 추가하기 위한 input hidden 추가
        const idInput = $('<input>', {
            hidden: 'hidden',
            id: 'inviteeId' + index,
            name: 'inviteeName' + index,
            value: member.id
        });

        // 제거 버튼 생성
        const removeBtn = $('<button>', {
            class: 'btn btn-danger btn-xs float-end text-white',
            text: '제거'
        });
        // 위 와 같이 작성해도 된다
        /*const removeBtn = document.createElement('button');
        removeBtn.classList.add('btn', 'btn-danger', 'btn-xs', 'float-end');
        removeBtn.textContent = '제거';*/

        // 제거 버튼 이벤트 리스너
        removeBtn.click(() => {
            invitedMembers.splice(index, 1); // 초대된 회원 목록에서 해당 멤버 제거
            renderInvitedMembers(); // 변경된 목록 다시 렌더링
        });

        li.append(idInput);
        li.append(removeBtn);
        invitedMembersList.append(li);
    });
}

$('#addMember').on('click', function () {
    addVirtualMembers();
});

$('#virtualMemberNickname').on('keydown', function (event) {
    if (event.key === 'Enter') {
        addVirtualMembers();
        event.preventDefault(); // 기본 동작 (엔터에 의한 페이지 새로고침) 방지
    }
});

function addVirtualMembers() {
    const virtualMemberInput = $('#virtualMemberNickname');
    const virtualMembersList = $('#virtualMembersList');
    const invitedMembersList = $('#invitedMembersList');

    // 가상 회원 추가
    const virtualNicknames = virtualMemberInput.val().split(',').map(name => name.trim());
    const currentMemberCount = invitedMembers.length + virtualMembers.length;

    if (currentMemberCount + virtualNicknames.length > maxPeople && maxPeople > 0) {
        // 최대 인원 수를 초과하면 경우 경고 메시지 표시
        alert(`최대 인원 수(${maxPeople}명)를 초과할 수 없습니다.`);
        return;
    }

    virtualNicknames.forEach((nickname, index) => {
        if (nickname && !virtualMembers.includes(nickname)) {
            virtualMembers.push(nickname); // 배열에 추가
            const listItem = $('<li></li>', {
                class: 'list-group-item d-flex justify-content-between align-items-center',
                text: nickname
            });

            // 240527: 가상 회원 이름을 추가하기 위한 input hidden 추가
            const virtualIdInput = $('<input>', {
                hidden: 'hidden',
                id: 'addVirtualId' + index,
                name: 'addVirtualId' + index,
                value: nickname
            });

            const removeButton = $('<button></button>', {
                class: 'btn btn-outline-danger btn-xs float-end',
                text: '제거'
            });

            // 가상 회원 제거 기능 수정
            removeButton.click(function () {
                $(this).parent().remove();
                const index = virtualMembers.indexOf(nickname);
                if (index > -1) {
                    virtualMembers.splice(index, 1);
                }
                // 총 인원 수 재계산 및 렌더링
                renderTotalMemberCount();
            });

            listItem.append(virtualIdInput);
            listItem.append(removeButton);
            virtualMembersList.append(listItem);
        }
    });
    // 입력 필드 초기화
    virtualMemberInput.val('');
    renderTotalMemberCount(); // 총 회원 수 재계산 및 렌더링
}

// 총 회원 수 렌더링 함수
function renderTotalMemberCount() {
    // 'totalMemberCount' ID를 가진 HTML 요소를 찾아서 변수에 저장합니다.
    const totalMemberCount = $('#totalMemberCount');
    // 초대된 멤버(invitedMembers)와 가상 멤버(virtualMembers)의 길이(개수)를 더한 값을 문자열로 변환하여
    // 'totalMemberCount' 요소의 텍스트 내용으로 설정합니다.
    // 이를 통해 전체 멤버 수를 화면에 표시합니다.
    totalMemberCount.text((invitedMembers.length + virtualMembers.length).toString());
}

$('.accordion-button').on('click', function () {
    // 특정 탭에 'active' 클래스 추가
    //$('#day1-tab').addClass('active');


    const $mapContainer = document.getElementById('map').parentNode;
    // console.log($mapContainer)
    document.getElementById('map').remove();
    const $newMap = document.createElement('div');
    $newMap.id = 'map';
    // console.log($newMap)
    $mapContainer.prepend($newMap);
    setTimeout(function(){
        const map = new kakao.maps.Map($newMap, { // 지도를 표시할 div
            center: new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표
            level: 20 // 지도의 확대 레벨
        });
    },50);
});

// 디바운스
const debounce = (callback, delay=500) => {
    let timer;
    return event => {
        if(timer) clearTimeout(timer);
        timer = setTimeout(callback, delay, event);
    }
}

// 디바운스를 이용해 검색어 지연 이벤트 등록
$('#searchMemberInput').on('input', debounce(e => {
    console.log(e.target.value);
    const target = e.target;
    const value = $(target).val();
    if(!value) console.log('검색어를 입력해주세요.')
    const request = $.ajax({
        url : "/trip/search-user-member",
        method: "GET",
        data: {
            nickname: value
        },
        dataType : "json"
    });
    request.done(function(data){
        // console.log(data);
        data.forEach(function (user) {
            const userMember = {
                id: user.id,
                name: user.nickname
            };
            realMembers.push(userMember);
        });

    });
    request.fail(function(jqXHR, status, error){
        console.log(error);
    });
}));

$('#tmpSave').click(function () {

});

// 거리&시간 계산 버튼을 누르면 tmap API에서 거리와 소요시간을 요청
$('#distanceCalBTN').click(function() {
    const locations = [];



    $('#myDayTabContent > .tab-pane').each(function() {
        const dayNumber = $(this).attr('id').replace('day', '');
        // console.log($(this).attr('id'));
        const planDayId = `planDay${dayNumber}`;
        const $planDay = $(`#${planDayId}`);
        // console.log($planDay);

        const day = {
            day: dayNumber,
            locations: []
        };

        $planDay.find('li').each(function(idx, element) {
            const contentId =  element.querySelector('h4').dataset.contentId;
            const $contentId = $(element).find('h4')[0].dataset.contentId;
            // console.log($(element).find('h4'));
            // console.log($contentId);
            //$(this).closest('.planDay').find('h4')[0].dataset.contentId;
            // console.log(contentId);
            const order = $(element).find('span.number').text();

            day.locations.push({
                contentId,
                order
            });
        });
        locations.push(day);
    });

    function formatDuration(duration) {
        const hours = Math.floor(duration / 3600);
        const minutes = Math.floor((duration % 3600) / 60);
        if (hours > 0) {
            return `${hours} 시간 ${minutes} 분`;
        } else {
            return `${minutes} 분`;
        }
    }

    // Ajax 요청 보내기
    $.ajax({
        url: '/trip/calculate-info',
        type: 'POST',
        data: JSON.stringify(locations),
        contentType: 'application/json',
        dataType: 'json',
        success: function (response) {
            // 계산 결과 렌더링 로직 작성 필요
            // console.log('error' in response);
            const isError = 'error' in response;
            // Object 내에 제공되는 메서드 활용: 같은 의미
            // console.log(response.hasOwnProperty('error'));
            if (isError) {
                const startTitle = response.startTitle;
                const endTitle = response.endTitle;
                const errorDays = response.days;

                let alertMsg = `${errorDays}일차 ${startTitle} ~ ${endTitle} 경로는 `
                const message = response.error.message.replaceAll('해당 서비스가', '보행자의 시간, 거리 계산이').trim();
                alertMsg += message.includes('(') ? message.substring(0, message.indexOf('(')) : message;
                alert(alertMsg);
                return;
            }
            // console.log(response);
            let totalDistance = 0;
            let totalDuration = 0;

            $('#totalInfo').empty();

            for (const responseKey in response) {
                const days = response[responseKey];
                const day = days.day;
                const locations = days.locations;

                totalDistance = 0;
                totalDuration = 0;

                locations.forEach(data => {
                    const endContentId = data.endContentId;
                    totalDistance += Number(data.distance);
                    totalDuration += Number(data.duration);
                    const $li = $(`h4[data-content-id="${endContentId}"]`).closest('li')
                    const $timeDiff = $li.find('.time-diff');
                    const $distanceDiff = $li.find('.distance-diff');
                    $timeDiff.text(formatDuration(data.duration)).css('visibility', 'visible');
                    $distanceDiff.text(`${(data.distance / 1000).toFixed(2)} km`).css('visibility', 'visible');
                })
                // 매 일자마다 DOM 객체 생성
                const $dayInfo = $('<div>').addClass('day-info');
                const $dayNumber = $('<h3>').text(`${day}일자`);
                const $totalDurationSpan = $('<span>').addClass('total-duration').text(formatDuration(totalDuration));
                const $totalDistanceSpan = $('<span>').addClass('total-distance').text(`${(totalDistance / 1000).toFixed(2)} km`);

                $dayInfo.append($dayNumber);
                $dayInfo.append($('<p>').text('총 거리: ').append($totalDistanceSpan));
                $dayInfo.append($('<p>').text('총 소요 시간: ').append($totalDurationSpan));

                // #totalInfo에 추가
                $('#totalInfo').append($dayInfo);
            }
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
});
