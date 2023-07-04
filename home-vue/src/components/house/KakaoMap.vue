<template>
    <div class="text-center">
        <!-- <div id="roadview" style="width:100%;height:300px;"></div> -->
        <v-row>
            <v-col cols="3" style="height:600px;">
                <div>
                    <b-card class="mb-2">
                        <div id="roadview" style="width:100%; height:200px"></div>
                        <p></p>
                        <h3>{{ house.apartmentName }} <b-button @click="registBookmark" variant="info">북마크</b-button></h3>
                        <b-card-text>
                            <p>{{ house.dongName }} {{ house.buildYear }}</p>
                            <p>매매정보</p>
                            <b-table v-if="dealInfo" :items="dealInfo" :fields="fields" :key="dealInfo.dealYear"></b-table>
                            <!-- <v-table>
                                <tr>
                                    <th>매매년도</th>
                                    <th>최고 매매가</th>
                                    <th>최저 매매가</th>
                                </tr>
                                <tr v-for="info in dealInfo" :key="info.dealYear">
                                    <td>{{ info.dealYear }}</td>
                                    <td>{{ info.maxAmount }}</td>
                                    <td>{{ info.minAmount }}</td>
                                </tr>
                            </v-table> -->
                        </b-card-text>
                    </b-card>
                </div>
            </v-col>
            <v-col>
                <!-- <map-category-bar v-if="loaded" :map="map"></map-category-bar> -->
                <div class="map_wrap">
                    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
                    <ul id="category">
                        <li id="BK9" data-order="0"> 
                            <span class="category_bg bank"></span>
                            은행
                        </li>
                        <li id="MT1" data-order="1"> 
                            <span class="category_bg mart"></span>
                            마트
                        </li>
                        <li id="PM9" data-order="2"> 
                            <span class="category_bg pharmacy"></span>
                            약국
                        </li>
                        <li id="OL7" data-order="3"> 
                            <span class="category_bg oil"></span>
                            주유소
                        </li>
                        <li id="CE7" data-order="4"> 
                            <span class="category_bg cafe"></span>
                            카페
                        </li>
                        <li id="CS2" data-order="5"> 
                            <span class="category_bg store"></span>
                            편의점
                        </li>
                    </ul>
                </div>
            </v-col>
        </v-row>
    </div>
</template>

<script>

// const vueThis = this;
import { mapActions, mapGetters, mapMutations } from 'vuex';
// import MapCategoryBar from './item/MapCategoryBar.vue';
export default {
    name: 'KakaoMap',
    components: {
        // MapCategoryBar,
    },
    data() {
        return {
            map : null,
            markers: [],
            bounds: null, //지도의 영역 범위 재설정을 위한 변수
            selectedMarker: null, //마커 클릭
            //로드뷰
            roadview: null,
            roadviewClient: null,
            //로딩
            loaded: false,
            
            placeOverlay: null,
            contentNode: null,
            sideMarkers: [],
            currCategory: '',
            ps: null,

            //'매매년도', '최고 매매가', '최저 매매가'
            fields: [
                {
                    key: 'dealYear',
                    label: '매매년도',
                    variant: 'danger'
                },
                {
                    key: 'maxAmount',
                    label: '최고 매매가(천)'
                },
                {
                    key: 'minAmount',
                    label: '최저 매매가(천)'
                },
            ],
        };
    },
    created() {
        this.loaded = false;
    },
    async mounted(){

        if(window.kakao && window.kakao.maps){
            this.loadMap();
        }
        else{
            this.loadScript();
        }
        

    },
    methods: {
        ...mapActions(["setHouse", "getDealInfo", "addBookmark"]),
        ...mapMutations(["CLEAR_HOUSE", "CLEAR_DEAL_INFO"]),
        loadScript() {
            const script = document.createElement("script");
            script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=74f1e792cc4d7d72169a67ae909e2b50&libraries=services,clusterer,drawing"
            /* global kakao */
            script.onload = () => kakao.maps.load(this.loadMap);
            document.head.appendChild(script);
            // this.loadRoadView();
        },
        loadMap(){
            const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
            const options = { //지도를 생성할 때 필요한 기본 옵션
                center: new window.kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
                level: 3 //지도의 레벨(확대, 축소 정도)
            };

            this.map = new window.kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
            
            //로드뷰를 해당 아이디 div에 생성합니다
            console.log("created roadView")
            var roadviewContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
            this.roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
            this.roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
            
            //상단바
            console.log(window.kakao.maps)
            this.placeOverlay = new window.kakao.maps.CustomOverlay({zIndex:1})
            this.contentNode = document.createElement('div')

            // 장소 검색 객체를 생성합니다
            this.ps = new window.kakao.maps.services.Places(this.map); 
            // console.log(this.ps)
            // 지도에 idle 이벤트를 등록합니다
            window.kakao.maps.event.addListener(this.map, 'idle', this.searchPlaces);

            // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다 
            this.contentNode.className = 'placeinfo_wrap';

            // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
            // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다 
            this.addEventHandle(this.contentNode, 'mousedown', window.kakao.maps.event.preventMap);
            this.addEventHandle(this.contentNode, 'touchstart', window.kakao.maps.event.preventMap);

            // 커스텀 오버레이 컨텐츠를 설정합니다
            this.placeOverlay.setContent(this.contentNode);  
            // console.log(this.placeOverlay)

            // 각 카테고리에 클릭 이벤트를 등록합니다
            this.addCategoryClickEvent();

            
            this.loaded = true;
            console.log(window.kakao)
        },

        // 마커를 생성하고 지도위에 표시하는 함수입니다
        addMarker(position, aptName, house) {
            // 마커를 생성합니다
            // console.log(position)
            var marker = new window.kakao.maps.Marker({
                position: position,
            });
            marker.setMap(this.map)
            console.log(aptName)
            //이벤트 등록
            //아파트 이름 커스텀 오버레이 이벤트 등록
            var customOverlay = new window.kakao.maps.CustomOverlay({
                position: marker.getPosition(),   
                content: `<div class ="label"><span class="left"></span><span class="center">${aptName}</span><span class="right"></span></div>`,
                yAnchor: 2,
            });
            window.kakao.maps.event.addListener(marker, 'mouseover', this.makeOverListener(this.map, customOverlay));
            window.kakao.maps.event.addListener(marker, 'mouseout', this.makeOutListener(customOverlay));

            //클릭 이벤트 등록 : 중심좌표 이동 + 로드뷰 및 정보 띄우기
            const vueThis = this;
            console.log(house)
            window.kakao.maps.event.addListener(marker, 'click', function() {
                // 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
                vueThis.selectedMarker = marker;
                // 클릭된 집을 현재 집으로 설정합니다. : 그냥 변수에 설정하는 게
                // this.house = house
                console.log("in", house)
                vueThis.setHouse(house);
                // console.log(marker)
            });

            // 생성된 마커를 배열에 추가합니다
            this.markers.push(marker);
            //바운드 배열에도 위치를 추가
            this.bounds.extend(position);
        },// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 


        makeOverListener(map, customOverlay) {
            return function() {
                customOverlay.setMap(map)
            };
        },

        // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
        makeOutListener(customOverlay) {
            return function() {
                customOverlay.setMap(null);
            };
        },
        removeMarker2(){
            for (var marker of this.markers){
                marker.setMap(null);
            }
            this.markers = []
        },
        
        /////////검색
        // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
        addEventHandle(target, type, callback) {
            if (target.addEventListener) {
                target.addEventListener(type, callback);
            } else {
                target.attachEvent('on' + type, callback);
            }
        },

        // 카테고리 검색을 요청하는 함수입니다
        searchPlaces() {
            console.log("searchPlaces" + this.currCategory)
            if (!this.currCategory) {
                return;
            }
            
            // 커스텀 오버레이를 숨깁니다 
            this.placeOverlay.setMap(null);

            // 지도에 표시되고 있는 마커를 제거합니다
            this.removeMarker();
            
            this.ps.categorySearch(this.currCategory, this.placesSearchCB, {useMapBounds:true}); 
        },

        // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
        placesSearchCB(data, status) {
            if (status === window.kakao.maps.services.Status.OK) {

                console.log("ok")
                // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
                this.displayPlaces(data);
            } else if (status === window.kakao.maps.services.Status.ZERO_RESULT) {
                // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
                console.log("no result")

            } else if (status === window.kakao.maps.services.Status.ERROR) {
                // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
                
                console.log("error")
            }
        },

        // 지도에 마커를 표출하는 함수입니다
        displayPlaces(places) {

            // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
            // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
            var order = document.getElementById(this.currCategory).getAttribute('data-order');

            for ( var i=0; i<places.length; i++ ) {

                    // 마커를 생성하고 지도에 표시합니다
                    var marker = this.addMarker2(new window.kakao.maps.LatLng(places[i].y, places[i].x), order);

                    // 마커와 검색결과 항목을 클릭 했을 때
                    // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
                    const vueThis = this;
                    (function(marker, place) {
                        window.kakao.maps.event.addListener(marker, 'click', function() {
                            vueThis.displayPlaceInfo(place);
                        });
                    })(marker, places[i]);
            }
        },

        // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
        addMarker2(position, order) {
            var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
                imageSize = new window.kakao.maps.Size(27, 28),  // 마커 이미지의 크기
                imgOptions =  {
                    spriteSize : new window.kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
                    spriteOrigin : new window.kakao.maps.Point(46, (order*36)), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                    offset: new window.kakao.maps.Point(11, 28) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
                },
                markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
                    marker = new window.kakao.maps.Marker({
                    position: position, // 마커의 위치
                    image: markerImage 
                });

            marker.setMap(this.map); // 지도 위에 마커를 표출합니다
            this.sideMarkers.push(marker);  // 배열에 생성된 마커를 추가합니다

            return marker;
        },

        // 지도 위에 표시되고 있는 마커를 모두 제거합니다
        removeMarker() {
            for ( var i = 0; i < this.sideMarkers.length; i++ ) {
                this.sideMarkers[i].setMap(null);
            }   
            this.sideMarkers = [];
        },

        // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
        displayPlaceInfo (place) {
            var content = '<div class="placeinfo">' +
                            '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a>';   

            if (place.road_address_name) {
                content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
                            '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
            }  else {
                content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
            }                
        
            content += '    <span class="tel">' + place.phone + '</span>' + 
                        '</div>' + 
                        '<div class="after"></div>';

            this.contentNode.innerHTML = content;
            this.placeOverlay.setPosition(new window.kakao.maps.LatLng(place.y, place.x));
            this.placeOverlay.setMap(this.map);  
        },


        // 각 카테고리에 클릭 이벤트를 등록합니다
        addCategoryClickEvent() {
            var category = document.getElementById('category'),
                children = category.children;
            for (var i=0; i<children.length; i++) {
                children[i].onclick = this.onClickCategory;
            }
            console.log(children)
        },

        // 카테고리를 클릭했을 때 호출되는 함수입니다 : 콜백
        onClickCategory(el) {
            console.log(this)
            console.log(el)
            console.log("parent: ")
            console.log(el.target.parentNode)
            console.log("target: ")
            console.log(el.target)

            
            let e = el.target;
            console.log(e.id)
            console.log(e.className)


            // console.log("event: ", id)
            // console.log("event: ", className)
            // console.log(this)
            this.placeOverlay.setMap(null);

            if (!e.id){ //아이콘(이미지)
                e = e.parentNode;
            }
            
            let id = e.id,
                className = e.className;
                if (className === 'on') {
                    this.currCategory = '';
                    this.changeCategoryClass();
                    this.removeMarker();
                } else {
                    this.currCategory = id;
                    this.changeCategoryClass(e);
                    this.searchPlaces();
                }
        },

        // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
        changeCategoryClass(el) {
            var category = document.getElementById('category'),
                children = category.children,
                i;

            for ( i=0; i<children.length; i++ ) {
                children[i].className = '';
            }

            if (el) {
                el.className = 'on';
                console.log("class")
            } 
        },
        //북마크
        registBookmark(){
            console.log(this.house)
            this.addBookmark({
                userId : this.loginUser.id,
                aptCode : this.house.aptCode,
            });
            this.$bvToast.toast("북마크 등록을 완료했습니다", {
                    title: "북마크 알림",
                    variant: "info", // 색상
                    toaster: "b-toaster-bottom-center", // 위치
                    autoHideDelay: 2000, // 3초 뒤 사라짐
                    solid: true, // 불투명처리
                    });
        }
    },
    computed: {
        ...mapGetters(["houses", "searchInfo", "house", "dealInfo", "loginUser"])
    },
    watch:{
        houses : function(newHouse) {
            if (this.loaded){
                console.log(newHouse)
                // //기존 마커 지우기
                this.removeMarker2();
                //바운드 객체
                this.bounds = new window.kakao.maps.LatLngBounds();
                // console.log("newHouse: " +newHouse + typeof(newHouse)) //string
                if (newHouse.length != 0){//.length != 0 && newHouse != undefined && newHouse != null && newHouse
                    // console.log("sear",this.searchInfo)
                    // const area = this.searchInfo.sidoName + " " + this.searchInfo.gugunName + " " + this.searchInfo.dongName;
                    // console.log(area)

                    //그리기
                    //초기화
                    this.sumLat = 0;
                    this.sumLng = 0;
                    newHouse.forEach((house) => {
                        // console.log(house)
                        this.addMarker(new window.kakao.maps.LatLng(house.lat, house.lng), house.apartmentName, house)
                    })
                    this.map.setBounds(this.bounds); //지도 영역 재지정
                }
            }
        },
        house: function(newHouse){
            if (this.loaded){
                // this.map.panTo()
                //지도 이동
                console.log(newHouse);
                this.map.setLevel(3)
                this.map.panTo(new window.kakao.maps.LatLng(newHouse.lat, newHouse.lng));

                //선택된 apt의 매매정보 가져오기 : aptCode
                this.getDealInfo(newHouse.aptCode)

                //로드뷰 띄우기
                var position = new window.kakao.maps.LatLng(newHouse.lat, newHouse.lng)
                console.log("로드뷰 포지션!!: "+ position) //33.450701, 126.570667

                // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
                console.log("this")
                console.log(this) //yes
                // console.log(vueThis); //no
                const vueThis = this;
                this.roadviewClient.getNearestPanoId(position, 50, function(panoId) {
                    vueThis.roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
                });
            }
        },
        selectedMarker: function(newSel){
            //로드뷰 띄우기
            var position = newSel.getPosition();
            console.log("로드뷰 포지션: "+ position) //33.450701, 126.570667

            // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
            const vueThis = this;
            console.log(this)
            vueThis.roadviewClient.getNearestPanoId(position, 50, function(panoId) {
                vueThis.roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
            });
        }
    }
};
</script>

<style>
/* #map{
    width: 100%;
    height: 600px;
} */
.label{
    padding: 5px 10px;
    background-color: white;
    border-color: black;
    -webkit-border-radius:15px;
    color: black;
}
.map_wrap, .map_wrap * {margin:0; padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap {position:relative;width:100%;height:600px;}
#category {position:absolute;top:10px;left:10px;border-radius: 5px; border:1px solid #909090;box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);background: #fff;overflow: hidden;z-index: 2;}
#category li {float:left;list-style: none;width:50px;border-right:1px solid #acacac;padding:6px 0;text-align: center; cursor: pointer;}
#category li.on {background: #eee;}
#category li:hover {background: #ffe6e6;border-left:1px solid #acacac;margin-left: -1px;}
#category li:last-child{margin-right:0;border-right:0;}
#category li span {display: block;margin:0 auto 3px;width:27px;height: 28px;}
#category li .category_bg {background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png) no-repeat;}
#category li .bank {background-position: -10px 0;}
#category li .mart {background-position: -10px -36px;}
#category li .pharmacy {background-position: -10px -72px;}
#category li .oil {background-position: -10px -108px;}
#category li .cafe {background-position: -10px -144px;}
#category li .store {background-position: -10px -180px;}
#category li.on .category_bg {background-position-x:-46px;}
.placeinfo_wrap {position:absolute;bottom:28px;left:-150px;width:300px;}
.placeinfo {position:relative;width:100%;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;padding-bottom: 10px;background: #fff;}
.placeinfo:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
.placeinfo_wrap .after {content:'';position:relative;margin-left:-12px;left:50%;width:22px;height:12px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
.placeinfo a, .placeinfo a:hover, .placeinfo a:active{color:#fff;text-decoration: none;}
.placeinfo a, .placeinfo span {display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
.placeinfo span {margin:5px 5px 0 5px;cursor: default;font-size:13px;}
.placeinfo .title {font-weight: bold; font-size:14px;border-radius: 6px 6px 0 0;margin: -1px -1px 0 -1px;padding:10px; color: #fff;background: #d95050;background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
.placeinfo .tel {color:#0f7833;}
.placeinfo .jibun {color:#999;font-size:11px;margin-top:0;}
</style>