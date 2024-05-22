document.getElementById('deleteSelected').addEventListener('click', function() {
    var checkedBoxes = document.querySelectorAll('[id^=checkbox_]:checked');
    if (checkedBoxes.length > 0) {
        if (confirm("선택한 항목을 삭제하시겠습니까?")) {
            var faqNums = [];
            checkedBoxes.forEach(function(checkbox) {
                faqNums.push(checkbox.value);
            });
            deleteFaq(faqNums);
        }
    } else {
        alert("삭제할 항목을 선택해주세요.");
    }
});

function deleteFaq(faqNums) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/platform/deleteFaq", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // 삭제 요청이 성공했을 때 처리할 작업
                alert("삭제되었습니다.");
                location.reload(); // 페이지 새로고침
            } else {
                // 삭제 요청이 실패했을 때 처리할 작업
                console.error("삭제 실패");
            }
        }
    };
    xhr.send(JSON.stringify({faqNums: faqNums}));
}