# RepositoryCheck
깃허브 레포지토리 조회 앱
  - Retrofit
  - Github Open API
  - RecyclerView
  - ListAdapter
  - Handler

## [Retrofit](https://square.github.io/retrofit/)
  - OkHttp를 네트워크 레이어로 활용하는 상위호환 라이브러리
  - 네트워크 통신을 편하게 할 수 있도록 도와주는 기능이 많이 있음
  - Json형식으로 데이터 받아올 때 Gson으로 직접 받아오는 것 대신에 GsonConverter를 이용해서 내부에서 매핑 가능

## [Github Open API](https://docs.github.com/ko/rest?apiVersion=2022-11-28) version: 2022-11-28
  - API: 어플리케이션 프로그래밍 인터페이스
  - REST: Representational State Transfer, 일종의 네트워크에서 통신을 관리하기 위한 아키텍쳐
  - Github에서 제공하는 API를 자유롭게 활용할 수 있다.

## [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=ko)
  - 항목이 스크롤되어 화면에서 벗어나더라도 RecyclerView는 View를 제거하지 않는다. 대신 RecyclerView는 화면에서 스크롤 된 새 항목의 View를 재사용한다
  - LayoutManager 클래스를 통해 정렬
  - Layout을 정했으면 Adapter 및 ViewHolder를 구현
      - ViewHolder: 목록에 있는 개별 항목의 Layout을 포함하는 View의 래퍼
      - Adapter는 필요에 따라 ViewHolder 객체를 만들고 이러한 View에 데이터를 설정하기도 한다. View를 데이터에 연결하는 Process를 바인딩이라고 한다
