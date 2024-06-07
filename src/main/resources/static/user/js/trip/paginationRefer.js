const request = {
    get(url) {
        return fetch(url);
    },
    post(url, payload) {
        return fetch(url, {
            method: 'POST'
            , headers: {'content-Type': 'application/json'}
            , body: JSON.stringify(payload)
        });
    }
}

// 초기 페이지 번호를 저장할 변수를 전역으로 선언
let currentPageNum = 1;

function action(data, pageNum, amount) {
    const $tbodyEle = document.getElementById('tbody');
    const $tfootEle = document.getElementById('tfoot');
    const $tfootTr = document.createElement('tr');
    const $tfootTd = document.createElement('td');
    const $searchText = document.getElementById('searchText');
    let searchValue = $searchText.value;

    pageNum = currentPageNum;

    if (searchValue !== '') {
        data = data.filter(function (item) {
            let isSearched = false;
            for (let key in item) {
                isSearched = String(item[key]).search(searchValue) > -1;
                if (isSearched) break;
            }
            return isSearched;
        });
    }

    // 페이징 처리를 위한 변수 선언 및 초기화
    let lastPage = Math.ceil(data.length / Number(amount));
    let startIndex = (pageNum - 1) * Number(amount);
    let endIndex = startIndex + Number(amount);
    let startNum = 1;
    let lastNum = 10;

    // 페이지 번호 (동적) 10페이지 미만일 경우 처리
    if (pageNum > 6 && lastPage > 9) {
        startNum = Number(pageNum) - 5;
        lastNum = Number(pageNum) + 4;
        if (lastNum >= lastPage) {
            startNum = lastPage - 9;
            lastNum = lastPage;
        }
    }

    // tbody 모든 행 제거 (자식요소 제거) 초기화
    $tbodyEle.replaceChildren();
    $tfootEle.replaceChildren();

    const $firstPage = document.createElement('a');
    $firstPage.href = '#';
    $firstPage.textContent = '[처음으로]';
    $firstPage.dataset.pageNum = (1).toString();

    const $prePage = document.createElement('a');
    $prePage.href = '#';
    $prePage.textContent = '[이전]';
    $prePage.dataset.pageNum = (Number(pageNum) - 1).toString();

    const $postPage = document.createElement('a');
    $postPage.href = '#';
    $postPage.textContent = '[다음]';
    $postPage.dataset.pageNum = (Number(pageNum) + 1).toString();

    const $lastPage = document.createElement('a');
    $lastPage.href = '#';
    $lastPage.textContent = '[마지막으로]';
    $lastPage.dataset.pageNum = lastPage.toString();

    $tfootTd.appendChild($firstPage);
    $tfootTd.appendChild($prePage);
    $tfootTd.appendChild($postPage);
    $tfootTd.appendChild($lastPage);

    //목록 tbody
    const memberList = data.slice(startIndex, endIndex)
    memberList.forEach((item, idx) => {
        const tbodyTr = document.createElement('tr');
        for (let key in item) {
            const tbodyTd = document.createElement('td');
            tbodyTd.textContent = item[key];
            tbodyTr.appendChild(tbodyTd);
        }
        $tbodyEle.appendChild(tbodyTr);
    });

    if (data.length > 0) {
        $tfootTd.colSpan = Object.keys(data[0]).length;
        let pageCount = lastPage < 10 ? lastPage : 10;
        // 페이지 번호 tfoot
        [...Array(pageCount).keys()].map(key => key + Number(startNum)).forEach(function (item) {
            const $pageNum = document.createElement('a');
            $pageNum.textContent = `[${item}]`;
            $pageNum.dataset.pageNum = item.toString();
            const firstPage = 1;

            // 240607: 현재 페이지이면 href 제거
            if (item === currentPageNum) {
                $pageNum.removeAttribute('href');
            } else {
                $pageNum.href = 'javascript:void(0);';
            }
            // 240607: 현재 페이지에서 href를 제거하기 위해 기존 코드 주석 처리
            // if (pageNum !== $pageNum.dataset.pageNum) $pageNum.href = '#';
            $tfootTd.insertBefore($pageNum, $tfootTd.lastElementChild.previousElementSibling);
            $tfootTr.appendChild($tfootTd);
            $tfootEle.appendChild($tfootTr);

            // 첫 페이지와 마지막 페이지에 따른 이전/다음 버튼 비활성화
            if (pageNum === firstPage) $prePage.removeAttribute('href');
            if (pageNum === lastPage) $postPage.removeAttribute('href');
        })
    }

}

// const $btnAjax = document.getElementById('ajaxBtn');
const $tfootEle = document.getElementById('tfoot');
const $contentAmount = document.getElementById('contentAmount');
const $searchText = document.getElementById('searchText');


// 데이터 요청을 테스트하기 위해 btnAjax 버튼 호출
/*$btnAjax.addEventListener('click', function(){
const amount = $contentAmount.value;

request.get('/user/data/MOCK_DATA.json')
.then(response => response.json())
.then(todos => action(todos, this.dataset.pageNum, amount))
.catch(err => console.error(err));

});*/

$tfootEle.addEventListener('click', function () {
    const amount = $contentAmount.value;
    const hasHref = event.target.getAttribute('href');
    if (hasHref != null) {
        currentPageNum = parseInt(event.target.dataset.pageNum);
        const pageNum = event.target.dataset.pageNum;
        request.get('/user/data/MOCK_DATA.json')
            .then(response => response.json())
            .then(todos => action(todos, currentPageNum, amount))
            .catch(err => console.error(err));
    }
});

$contentAmount.addEventListener('change', function () {
    const amount = $contentAmount.value;
    request.get('/user/data/MOCK_DATA.json')
        .then(response => response.json())
        .then(todos => action(todos, currentPageNum, amount))
        .catch(err => console.error(err));
});

const debounce = (callback, delay = 500) => {
    let timer;
    return event => {
        if (timer) clearTimeout(timer);
        timer = setTimeout(callback, delay, event);
    }
}
const inputSearch = e => {
    const pattern = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>\#$%&\\\=\(\'\"]/gi;
    const value = e.target.value.replace(pattern, '');
    const amount = $contentAmount.value;
    request.get('/user/data/MOCK_DATA.json')
        .then(response => response.json())
        .then(todos => action(todos, currentPageNum, amount))
        .catch(err => console.error(err));
}

$searchText.addEventListener('input', debounce(inputSearch));
