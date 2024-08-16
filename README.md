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