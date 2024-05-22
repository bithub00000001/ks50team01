//import {addTabFunction} from "./tripPlanner.js";

function fristStepSubmit(){
	confirm("입력된 정보로 임시저장 하시겠습니까?")

	const numDate = ($('#numDate').val() === 0) ? 1 : $('#numDate').val();
	/*if(numDate > 5){
		alert("최대 5일차로 입력 가능합니다.");
		return false;
	}*/
	addTap(numDate)
	$("#headingTwo").find("button").click();

}

function addTap(tapCnt){
	for(let i=1; tapCnt > i; i++){
		addTabFunction();
	}
}
$(document).ready(function() {

});



