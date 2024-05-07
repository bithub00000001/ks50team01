function deleteConfirm(element) {
    var memberId = element.getAttribute('data-member-id');
    if (confirm("정말 회원을 탈퇴시키시겠습니까?")) {
        location.href = "/platform/member/delMember?memberId=" + memberId;
    }
}