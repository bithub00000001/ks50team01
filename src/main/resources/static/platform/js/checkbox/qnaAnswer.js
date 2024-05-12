function showResponseForm() {
    var checkboxes = document.querySelectorAll('[id^=checkbox_]:checked');
    if (checkboxes.length === 0) {
        alert('답변할 게시물을 선택해주세요.');
        return;
    }
    document.getElementById('responseForm').style.display = 'block';
}
