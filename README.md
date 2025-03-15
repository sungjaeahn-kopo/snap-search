## 🌐 [스냅서치(Snap-Search)](https://www.toomuchoffside.site/)
![Mockup (3)](https://github.com/user-attachments/assets/4047420b-12e1-4152-ab0e-fe0a68249947)


## 🚀 개요
api-football API와 csv 데이터를 기반으로 **해외 축구의 리그 일정 및 결과 제공**을 목적으로 하는 플랫폼입니다.

더미 데이터를 기반으로 이전 데이터를 가져오고, API 데이터를 배치서비스를 통해 주기적으로 갱신하는 방식을 사용하고 있습니다.

ElasticSearch를 이용한 검색 서비스를 제공할 예정이나, 서버 증설할 방법을 모색중에 있습니다.



## 🎯 주요 기능
 - **시즌별, 소속국가별, 팀 검색 기능**
 - **배치 작업을 통한 경기결과 데이터 자동 수집 및 처리**
 - **팀 상세 정보**
   - 팀 로고, 팀 이름
   - 다가올 경기
   - 경기 정보
   - 소속 선수
   - 소속 감독 및 커리어 리스트 (모달)
   - 팀 로고 기반 대표 색상 및 보색 추출 기능
 - **경기 상세 정보**
   - 경기 개요
   - 이벤트별 타임라인 기능
 - **이미지 서버 구축 및 렌더링 최적화**
   

## 📝 기술 스택
### 📂 [Front-end](https://github.com/sungjaeahn-kopo/snap-search-fe)
  - JavaScript(TypeScript)
  - React.js 18
  - Next.js 14 App Router
  - MobX
  - React-query

### 📂 [Back-end](https://github.com/sungjaeahn-kopo/snap-search)
  - Java17
  - Spring Boot5
  - Spring Data JPA
  - Spring Scheduling
  - MySQL
  - Nginx
  - SLF4J
  - Gradle

### 📂 CI/CD
  - Github Actions + Docker를 이용한 배포 자동화

### 📂 CLOUD
  - Azure VM
  - Azure Image Storage



