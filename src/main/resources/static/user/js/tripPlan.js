
function fristStepSubmit(){
	// 사용자에게 확인 메시지를 보여주고, 확인 시에만 계속 진행
	if (confirm("입력된 정보로 임시저장 하시겠습니까?")) {
		// numDate 값을 가져오고, 값이 0이면 1로 설정
		const numDate = ($('#numDate').val() == 0) ? 1 : $('#numDate').val();

		// 현재 탭의 개수를 확인
		const currentTabCount = getCurrentTabCount();

		// 기본 탭 1개를 고려하여 추가할 탭의 개수가 현재 탭의 개수보다 클 경우에만 추가
		if (numDate > currentTabCount) {
			addTap(numDate - currentTabCount);
		}

		// 버튼을 클릭하여 다른 섹션으로 이동
		$("#headingTwo").find("button").click();
	}

}

function addTap(tapCnt){
	for(let i= 0; tapCnt > i; i++){
		addTabFunction();
	}
}

function getCurrentTabCount() {
	// 기본 탭을 포함하여 현재 존재하는 탭의 개수를 반환
	return $("#myDayTab .nav-item:not(.add-day)").length;
}
$(document).ready(function() {

});



