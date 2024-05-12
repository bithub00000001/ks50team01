document.getElementById('checkAll').addEventListener('change', function() {
    var checkboxes = document.querySelectorAll('[id^=checkbox_]');
    checkboxes.forEach(function(checkbox) {
        checkbox.checked = document.getElementById('checkAll').checked;
    });
});

document.getElementById('deleteSelected').addEventListener('click', function() {
    var checkedBoxes = document.querySelectorAll('[id^=checkbox_]:checked');
    if (checkedBoxes.length > 0) {
        if (confirm("선택한 항목을 삭제하시겠습니까?")) {
            // 삭제 로직을 여기에 추가하세요.
            alert("삭제되었습니다.");
        }
    } else {
        alert("삭제할 항목을 선택해주세요.");
    }
});
