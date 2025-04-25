# Git_onbording
깃 기초 활용 연습용
* * *
- Git 설치 및 환경설정
  - git bash 실행
  - git -v 명령 실행
    - 설치된 깃 버전이 출력되면 정상 설치 및 환경변수 설정 완료
  - git confing --global user.name `<깃허브 닉네임>` [아이디인 이메일이 아닌 유저네임]
  - git confing --global user.email `<깃허브 아이디>` [가입/로그인에 사용한 이메일]
    - 아마 바로 로그인할 필요는 없이 git push(업로드) 할때 로그인
- Git repository 생성 및 설정
  - Git은 정해진 디렉토리를 초기화(init)해서 작업 공간으로 만들고, 해당 공간에서 작업 수행
  - 작업 공간(프로젝트 생성, 프로젝트 작업 등을 진행할 디렉토리) 생성 및 해당 페이지로 이동
    - ex) C:\\User 이 작업 공간이라면 git bash 실행 후 cd C:\\User 명령 실행
  - git clone https://github.com/Fire-Detector/Git_onbording/`.git` [.git - URL이 Git 저장소임을 명시]
  - 자동으로 해당 URL에 해당하는 레포지토리 복사, origin 등 설정
- Git 활용
  - cd 명령 사용해서 clone으로생성된 디렉토리 내부로 이동, git status 명령 정상 작동 여부 및 README.md 파일(이 파일) 생성 확인
  - 정상적으로 생성됐을 경우 파일 수정해서 아래에 내용 이어서 작성
  - 이어서 작성 후 저장, git status 명령 실행해서 파일 변경 반영 확인
  - git add README.md 명령 실행, git status 명령 실행해서 파일이 깃에 추가됐는지 확인
  - git commit -m 'Docs : `<이름>` - 테스트 완료' 명령 실행 || git commit 명령 실행 후 커밋 메세지 작성, 저장 후 편집기 종료
  - 커밋 완료 확인 - git bash에서 커밋 실행 결과 표시됨
  - git push origin main 명령 실행
  - push 정상 싱행 확인, 깃허브에서 정상 반영 확인
```
! 진행 과정에서 발생한 에러는 에러 메시지 확인 및 자체 해결 시도 우선, 불가할 경우 웹/AI 검색, 해결 실패시 질문
```
* * *
- 김윤서
  - 테스트 완료
<<<<<<< HEAD
-이재준
  -테스트 완료
=======
- 나세종
  - 테스트 완료
=======
- 호재영
 - 테스트 완료
=======
- 김정연
  - 테스트 완료
>>>>>>> 0531390248c4c3eec5a7fc3a3eaebfdb5cb2d44f
