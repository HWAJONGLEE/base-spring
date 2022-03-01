

/**
 * 출근신청시
 * 위도 mapUtil.curLat 경도 mapUtil.curLon 로 값 체크
 * gps 호출시 위도 경도는
 * mapUtil.curLat, mapUtil.curLon 에 대입해주면 됩니다. 위도가 변경될 경우 센터 마커가 새로고침 됩니다.
 * 맵이 생성 되는 container default로 id="kakaoMap"입니다. 변경하실 경우 mapUtil.$target에 할당하시면 됩니다.
 * $refreshBtn 이 바라보는 버튼은 defalut로 id="refreshBtn"입니다. 변경하실 경우 mapUiil.$refreshBtn 에 할당하시면 됩니다.
 */
//https://apis.map.kakao.com/web/sample/drawShape/
$(document).ready(function() {
  mapUtil.init();
});

const mapUtil = {
  //default 위도 경도 TODO 삭제 또는 DB 조회
  latitude : '37.4852791',
  //default 위도 경도 TODO 삭제 또는 DB 조회
  longitude : '126.8955036',
  //센터 표시 여부
  isCenter : true,
  //지도가 생성될 DOM
  $target : '#kakaoMap',
  //gps새로고침버튼 DOM
  $refreshBtn : '#refreshBtn',
  //카카오맵객체
  map : {},
  //사용자가 현재 위치한 위도
  get curLat() {
    return this._curLat || '';
  },
  //사용자가 현재 위치한 위도,변경될 경우 마커 세팅
  set curLat(curLat) {
    this._curLat = curLat;
    setTimeout(() => {
      mapUtil.setCenterMaker();  
    }, 0);
  },
  //현재 위치한 경도 TODO???
  curLon : '',
  init () { //TODO 변경
    this.createMap();
    this.eventBinding();
  },
  eventBinding() {
    
  },
  /**
   * 좌표에 원을 그립니다
   * @param {위도} latitude
   * @param {경도} longitude 
   * @param {서클(미터)} commutingRange
   */
  setCircle(latitude = mapUtil.latitude,
          longitude = mapUtil.longitude,
          commutingRange) {
    // 좌표 세팅
    const positions = new kakao.maps.LatLng(latitude, longitude);
    // 원 생성
    const circle = new kakao.maps.Circle({
        center : positions,      // 원의 중심좌표 입니다 
        radius: commutingRange, // 미터 단위의 원의 반지름입니다 
        strokeWeight: 5,        // 선의 두께입니다 
        strokeColor: '#75B8FA', // 선의 색깔입니다
        strokeOpacity: 1,       // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: 'dashed',  // 선의 스타일 입니다
        fillColor: '#CFE7FF',   // 채우기 색깔입니다
        fillOpacity: 0.7        // 채우기 불투명도 입니다   
    });

    // 지도에 원을 표시합니다 
    circle.setMap(this.map);
  },
  setMarker(latitude = mapUtil.latitude,
          longitude = mapUtil.longitude,) {
     // 마커 생성 위치 셋팅
    const positions = new kakao.maps.LatLng(latitude, longitude);
    const marker = new kakao.maps.Marker({
      position: positions
    });

    // 마커 생성
    marker.setMap(this.map);
  },
  /**
   * 초기 지도를 생성합니다 TODO default 위치 설정 div 위치 동적으로 받을지?
   * @param {위도} latitude
   * @param {경도} longitude
   */
  createMap(latitude = mapUtil.latitude,
          longitude = mapUtil.longitude,
          option) {
    const mapContainer = document.querySelector(this.$target)
    
    const mapOption = { 
        center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

    this.map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    //렌더링할 데이터가 있을 경우 마커 및 서클을 랜더링 TODO 네이밍 정의
    if (!!option) {
      this.setProejctMap(option);
    }
  },

  /**
   * 프로젝트 별 마커 및 서클세팅
   * @param {*} option
   */
  setProejctMap(option) {
    const _this = this;
    option.projects = option.projects || [];
    projects.forEach(function (pjt) {
      _this.setCircle(pjt.latitude, pjt.longitude, pjt.commutingRange);
      _this.setMarker(pjt.latitude, pjt.longitude);
    });
  },
  /**
   * 사용자의 위도 경도값이 변경 될 경우 마커 셋팅 후 센터로 화면 이동
   * @param {현재 위치한 위도} curLat 
   * @param {현재 위치한 경도} curLon 
   */
  setCenterMaker() {
    if (Object.prototype.toString.call(this.centerMaker) === '[object Object]') {
      this.centerMaker.setMap(null);
    }

    this.setMarker(this.curLat, this.curLon);
    map.panTo(moveLatLon);

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    var moveLatLon = new kakao.maps.LatLng(this.curLat, this.curLon);
    
    map.panTo(moveLatLon);
  }

}