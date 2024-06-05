function confirmDeactivate(element) {
	var postNum = element.getAttribute("data-post-num");
	if (confirm("정말 비활성화하시겠습니까?")) {
		$.ajax({
			type: "POST",
			url: "/platform/board/deactivatePost",
			data: { postNum: postNum },
			success: function(response) {
				if (response.success) {
					alert(response.message);
					location.reload();
				} else {
					alert(response.message);
				}
			},
			error: function() {
				alert("비활성화 처리 중 오류가 발생했습니다.");
			}
		});
	}
}

$(document).on('click', '.deactivateBtn', function() {
	confirmDeactivate(this);
});