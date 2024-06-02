function getData(map, contentTypeId) {

    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center: new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표
        level: 20 // 지도의 확대 레벨
    });

    const contentTypeObject = {
        '12': 'tourOptions',
        '32': 'accommodationOptions',
        '39': 'restaurantOptions'
    }
    const $tabOption = $(`#${contentTypeObject[contentTypeId]}`)

    // 마커 클러스터러를 생성합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 10, // 클러스터 할 최소 지도 레벨
        disableClickZoom: true // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
    });
    let markers;

    // 현재 열려있는 인포윈도우를 저장할 변수
    let currentInfowindow = null;
    let clusterListItems = [];

    const contentTypeListObject = {
        '12': 'tour',
        '32': 'accommodation',
        '39': 'restaurant'
    }

    // 리스트 아이템을 생성하는 함수
    function createListItem(item, idx) {
        const title = item.getTitle();
        const addr = item.addr;
        const telNum = item.telNum;
        const contentId = item.contentId;
        const dataIdType = contentTypeListObject[contentTypeId]

        const $li = $('<li></li>', {
            class: 'list-group-item',
            'data-id': dataIdType + (idx + 1)
        });

        const $title = $('<h5></h5>').text(title);
        $li.append($title);

        const $scheduleContainer = $('<p></p>', {
            text: '주소 : '
        }).append(
            $('<span></span>', {
                type: 'text',
                class: 'schedule-input',
                text: addr
            })
        );
        $li.append($scheduleContainer);

        const $additionalContainer = $('<p></p>', {
            text: '연락처 : '
        }).append(
            $('<small></small>', {
                type: 'text',
                class: 'additional-input',
                text: telNum ? telNum.replaceAll('<br />', ', ') : '없음'
            })
        );
        $li.append($additionalContainer);

        const $detailBtn = $('<button></button>', {
            class: 'btn btn-outline-facebook btn-sm detail-modal me-2',
            'data-bs-toggle': 'modal',
            'data-detail-content-id': contentId,
            text: '상세보기'
        });
        $li.append($detailBtn);

        const $addPlanBtn = $('<button></button>', {
            class: 'btn btn-outline-primary btn-addPlan btn-sm',
            text: '담기'
        });
        $li.append($addPlanBtn);

        return $li;
    }


    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get(`/trip/clusterer/${contentTypeId}`, function (data) {

        // 프로토 타입으로 선언하기 위한 코드
        const kakaoProto = kakao.maps.Marker.prototype;

        kakaoProto.setContentInfo = function(contentInfo){
            for(const key in contentInfo){
                this[key] = `${contentInfo[key]}`;
            }
        }
        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
        markers = $(data.positions).map(function (i, position) {
            const option = {
                contentTypeId: position.contentTypeId,
                contentId: position.contentId,
                overview: position.overview,
                imageLink: position.imageLink,
                addr: position.addr,
                telName: position.telName,
                telNum: position.telNum
            }



            let marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(position.lat, position.lng),
                clickable: true,
                title: position.name
            });
            marker.setContentInfo(option);


            return marker;
        }).get();
            /*return new kakao.maps.Marker({
                position: new kakao.maps.LatLng(position.lat, position.lng),
                clickable: true,
                title: JSON.stringify(option)
            });

        });*/






        for (let i = 0; markers.length > i; i++) {
            // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
            const title = markers[i].getTitle();
            const addr = markers[i].addr;
            const telNum = markers[i].telNum;
            const iwContent =
                    `<div class="pe-2 py-2 px-2">${title}</div>
                    <div class="pe-5 py-2 px-2">${addr}</div>
                    <div class="pe-2 py-2 px-2 pb-5">${telNum}</div>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

            // 인포윈도우를 생성합니다
            const infowindow = new kakao.maps.InfoWindow({
                content: iwContent,
                removable: iwRemoveable
            });


            // 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener(markers[i], 'click', () => {
                // 현재 열려있는 인포윈도우가 있으면 닫기
                if (currentInfowindow) {
                    currentInfowindow.close();
                }

                // 새로운 인포윈도우 열기
                infowindow.open(map, markers[i]);

                // 현재 열린 인포윈도우 저장
                currentInfowindow = infowindow;

                // 리스트의 상위로 올리는 함수
                const $li = createListItem(markers[i], i);
                const $liList = $tabOption.find('li');
                const $existingLi = $liList.filter((idx, data) => $(data).find('h5').text() === $li.find('h5').text());

                // 기존 요소가 있으면 제거
                if ($existingLi.length > 0) {
                    $existingLi.remove();
                }
                // 요소 추가
                $tabOption.prepend($li);
            });
        }
        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
    });


    // 마커 클러스터러에 클릭이벤트를 등록합니다
    // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우





    // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
    kakao.maps.event.addListener(clusterer, 'clusterclick', function (cluster) {
        const markerArr = cluster.getMarkers();
        // const contentTypeId = arr[0].getAttribute('contentTypeId');

        $tabOption.empty();

        clusterListItems = [];

        markerArr.forEach((item, idx)=>{
            const $li = createListItem(item, idx);
            clusterListItems.push($li);

        });

        clusterListItems.forEach($li => $tabOption.append($li));


        // console.log(clusterer['_markers']);
        // 현재 지도 레벨에서 1레벨 확대한 레벨
        var level = map.getLevel() - 1;

        // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
        map.setLevel(level, {anchor: cluster.getCenter()});
    });

}
