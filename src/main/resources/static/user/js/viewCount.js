// 상세 페이지 진입 시 실행되는 함수
function updateViewCount() {
    // Ajax를 통해 서버에서 조회수를 가져옴
    $.ajax({
        type: "GET",
        url: "/getPostViewCount", // 조회수를 가져올 엔드포인트 URL
        data: {
            postNum: postId // 게시글 번호를 서버에 전달
        },
        success: function(response) {
            // 성공적으로 조회수를 가져왔을 때, 가져온 조회수를 화면에 표시
            $("#viewCount").text("조회수: " + response);
        },
        error: function(xhr, status, error) {
            // 조회수를 가져오지 못했을 때의 처리 (예: 에러 메시지 표시)
            console.error("Failed to fetch view count:", error);
        }
    });
}

// 페이지가 로드될 때 조회수를 업데이트
$(document).ready(function() {
    updateViewCount();
});
