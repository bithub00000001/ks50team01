function getMemberInfo() {
	$.ajax({
		url: "/mypage/mypageManage",
		type: "GET",
		dataType: "json",
		success: function(data) {
			$("#memberPw").val(data.memberPw);
			$("#memberName").val(data.memberName);
			$("#memberNickname").val(data.memberNickname);
			$("#memberEmail").val(data.memberEmail);
			$("#memberTelNum").val(data.memberTelNum);
		},
		error: function(xhr, status, error) {
			console.log("Error: " + error);
		}
	});
}

function submitForm(form) {
	$.ajax({
		url: form.action,
		type: form.method,
		data: $(form).serialize(),
		success: function(response) {
			alert("수정이 완료되었습니다.");
			window.location.href = "/trip";
		},
		error: function(xhr, status, error) {
			console.log("Error: " + error);
		}
	});
	return false;
}

function confirmDelete() {
    if (confirm("정말로 탈퇴하시겠습니까?")) {
        $.ajax({
            url: "/mypage/delMember",
            type: "POST",
            success: function(response) {
                alert("탈퇴 처리되었습니다.");
                window.location.href = "/trip";
            },
            error: function(xhr, status, error) {
                console.log("Error: " + error);
            }
        });
    }
}