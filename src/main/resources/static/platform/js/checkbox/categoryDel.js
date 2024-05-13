function deleteConfirm(element) {
    var cateNum = element.getAttribute('data-category-num');
    if (confirm("정말 삭제하시겠습니까?")) {
        location.href = "/platform/category/delete?cateNum" + cateNum;
    }
}