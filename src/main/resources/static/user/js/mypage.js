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

function getMemberPost() {
     $.ajax({
        url: "/mypage/mypage2",
        type: "GET",
        dataType: "json",
        success: function(data) {
            var postList = "";
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    postList += "<tr>" +
                                    "<td>" + data[i].pstTtl + "</td>" +
                                    "<td><a href='http://localhost/community/postDetail?postNum=" + data[i].pstNum + "' class='btn btn-primary btn-sm' target='_blank'>바로가기</a></td>" +
                                "</tr>";
                }
            } else {
                postList = "<tr><td colspan='2'>작성한 게시글이 없습니다.</td></tr>";
            }
            $("#postTableBody").html(postList);
        },
        error: function(xhr, status, error) {
            console.log("Error: " + error);
        }
    });
}

function getMemberCmtPost() {
    $.ajax({
        url: "/mypage/postCmt",
        type: "GET",
        dataType: "json",
        success: function(data) {
            var commentList = "";
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    commentList += "<tr>" +
                                    "<td>" + data[i].pstCmtCn + "</td>" +
                                    "<td><a href='http://localhost/community/postDetail?postNum=" + data[i].pstNum + "' class='btn btn-primary btn-sm' target='_blank'>바로가기</a></td>" +
                                "</tr>";
                }
            } else {
                commentList = "<tr><td colspan='2'>작성한 댓글이 없습니다.</td></tr>";
            }
            $("#commentTableBody").html(commentList);
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