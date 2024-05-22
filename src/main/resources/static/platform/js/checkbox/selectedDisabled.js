document.getElementById('disabled').addEventListener('click', function() {
    var selectedPosts = [];
    var checkboxes = document.querySelectorAll('[id^=checkbox_]:checked');
    checkboxes.forEach(function(checkbox) {
        selectedPosts.push(checkbox.value);
    });

    if (selectedPosts.length === 0) {
        alert('비활성화할 게시물을 선택해주세요.');
        return;
    }

    var confirmation = confirm('선택한 게시물을 비활성화하시겠습니까?');
    if (confirmation) {
		console.log("비활성화 요청: ", selectedPosts); 
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/platform/deactivatePost', true);
        xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    alert('게시물이 비활성화되었습니다.');
                    location.reload();
                } else {
                    alert('게시물을 비활성화하는 동안 오류가 발생했습니다.');
                }
            }
        };
        xhr.send(JSON.stringify(selectedPosts));
    }
});

document.getElementById('checkAll').addEventListener('change', function() {
    var checkboxes = document.querySelectorAll('[id^=checkbox_]');
    checkboxes.forEach(function(checkbox) {
        checkbox.checked = document.getElementById('checkAll').checked;
    });
});
