# 모듈 만들시 각각의 gradle 에서 다 bootjar와 jar를 꼭 설정해주고 build 해줘야 한다 

bootJar { 
enabled = true,false
}

jar {
enabled = true,false
}

# swagger 접속 경로 
http://localhost:8080/swagger-ui/index.html


# exception 처리
//컨트롤러,서비스에서는 항상 성공처리만 바라보게 하고 여기서 에러가 발생하는거는 ExcetpionHandler에서 처리해준다 


# 인텔리제이 업그레이드 할시 무조건 재빌드 해줘야 내가 했던 라이브러리가 다시 적용된다

# 토큰 만료시 
- 일단 로그인을 먼저 요청후 그다음에 토큰값을 복사해서

- modheader에다가 토큰값을 넣으면 적용된다