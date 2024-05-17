$(document).ready(function() {
  
});


function fristStepSubmit(){
	confirm("입력된 정보로 임시저장 하시겠습니까?")

	const numDate = ($('#numDate').val() == 0) ? 1 : $('#numDate').val();
	if(numDate > 5){
		alert("최대 5일차로 입력 가능합니다.");
		return false;
	}
	addTap(numDate)
	$("#headingTwo").find("button").click();
	
}

function addTap(tapCnt){
	for(let i=1; tapCnt > i; i++){
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
	            'style': `padding-left: 0;`
	        })
	    }).appendTo('#myDayTabContent');
	}
}