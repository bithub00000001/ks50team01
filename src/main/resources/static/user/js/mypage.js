function confirmDelete() {
    if (confirm("정말로 탈퇴하시겠습니까?")) {
        // 회원 탈퇴 요청 보내기
        var test = '[[${session.loginId}]]';
        console.log('[[${session.loginId}]]');
        console.log("[[${session.loginId}]]");
        var test = /*[[${sample}]]*/'[[${session.loginId}]]';
        console.log(test)
        /*const memberId = [[${session.loginId}]] null;
        window.location.href = "/mypage/delMember?memberId=" + memberId;*/
    }
}