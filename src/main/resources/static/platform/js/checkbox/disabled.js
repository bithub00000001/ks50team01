document.getElementById('disableButton').addEventListener('click', function() {
    var selectedPosts = [];
    var checkboxes = document.querySelectorAll('[id^=checkbox_]:checked');
    checkboxes.forEach(function(checkbox) {
        selectedPosts.push(checkbox.value);
    });

    // 선택된 게시물이 있는지 확인
    if (selectedPosts.length === 0) {
        alert('비활성화할 게시물을 선택해주세요.');
        return;
    }

    // 사용자에게 확인을 받음
    var confirmation = confirm('선택한 게시물을 비활성화하시겠습니까?');
    if (confirmation) {
        // AJAX 요청을 보냄
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/posts/disable', true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    alert('게시물이 비활성화되었습니다.');
                    // 페이지 새로고침 또는 다른 동작 수행
                } else {
                    alert('게시물을 비활성화하는 동안 오류가 발생했습니다.');
                }
            }
        };
        xhr.send(JSON.stringify(selectedPosts));
    }
});