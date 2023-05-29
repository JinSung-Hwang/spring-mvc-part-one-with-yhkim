김영한의 스프링 MVC 기본편을 수강하며 작성한 Code Repository입니다.


### Build 세팅

1. preference 
2. gradle 검색
3. build and running using: ```intellij idea``` 선택
4. run tests using: ```intellij idea``` 선택
5. OK 선택

### Lombok 세팅

1. preference 
2. plugins 선택 
3. market 탭 선택
4. lombok 설치 
4. Apply 선택
5. OK 선택

1. preference 
2. annotation processors 검색  
3. enable annotation processing 체크 (체크해야 lombok이 자동 동작한다.)
4. Apply 선택
5. OK 선택

### Working Directory 세팅

멀티 모듈에서 web module이 인식이 안될때는 working directory를 설정해줘야한다. </br>
intellij에서 기본적을 working directory가 최상위 프로젝트 위치로 잡히게 된다. </br>
하지만 멀티 모듈 구조에서는 web module을 인식하기 위해서는 working directory를 각 모듈에 맞춰서 설정해줘야한다. </br>

1. Run
2. Edit Configurations (run/debug Configurations)
3. 하나의 프로젝트 선택
4. modify options 
5. working directory 
6. 각 프로젝트의 최상위 위치 설정 
4. Apply 선택
5. OK 선택
