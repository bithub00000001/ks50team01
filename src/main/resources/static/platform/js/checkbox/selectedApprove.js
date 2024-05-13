document.getElementById('approveSelected').addEventListener('click', function() {
    var selectedReports = [];
    var checkboxes = document.querySelectorAll('[id^=checkbox_]:checked');
    checkboxes.forEach(function(checkbox) {
        selectedReports.push(checkbox.value);
    });

    // 선택된 항목이 없을 때 경고창 표시
    if (selectedReports.length === 0) {
        alert('선택된 항목이 없습니다. 신고를 선택해주세요.');
        return;
    }

    // AJAX 요청을 보냄
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/reports/approve', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                alert('선택한 신고가 승인되었습니다.');
                // 페이지 새로고침 또는 다른 동작 수행
            } else {
                alert('신고를 승인하는 동안 오류가 발생했습니다.');
            }
        }
    };
    xhr.send(JSON.stringify(selectedReports));
});