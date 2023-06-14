# BE-purchase
구매 서비스 입니다.


### 환경 변수
<table>
  <tr>
    <td><b>environment</b></td>
    <td><b>description</b></td>
  </tr>
  <tr>
    <td><b>{DB_MASTER_URL}</b></td>
    <td><b>MY SQL MASTER DB URL을 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{DB_SLAVE_URL}</b></td>
    <td><b>MY SQL SLAVE DB URL을 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{DB_USERNAME}</b></td>
    <td><b>MY SQL DB USERNAME을 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{DB_PASSWORD}</b></td>
    <td><b>MY SQL DB PASSWORD를 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{EUREKA_DEFAULT_ZONE}</b></td>
    <td><b>EUREKA DEFAULT ZONE을 입력해주세요</b></td>
  </tr>
  
  <tr>
    <td><b>{KAFKA_BOOTSTRAP_ADDRESS}</b></td>
    <td><b>Kafka bootstrap 주소를 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{REDIS_HOST}</b></td>
    <td><b>REDIS HOST를 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{DOCKERHUB_USERNAME}</b></td>
    <td><b>DOCKERHUB 명을 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{DOCKERHUB_PASSWORD}</b></td>
    <td><b>DOCKERHUB 비밀번호를 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{VM_INSTANCE_HOST}</b></td>
    <td><b>VM 인스턴스 Host를 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{SSH_USERNAME}</b></td>
    <td><b>SSH USERNAME을 입력해주세요</b></td>
  </tr>
  <tr>
    <td><b>{SSH_PRIVATE_KEY}</b></td>
    <td><b>SSH PRIVATE KEY를 입력해주세요</b></td>
  </tr>
</table>

* 환경 변수를 바탕으로 application.yml 파일을 생성합니다.

```yml
server:
  port: 8081

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: ${ eureka.client.service-url.defaultZone }

spring:
  application:
    name: purchase-service

  datasource:
    master:
      driver-class-name: com.mysql.cj.jdbc.Driver
      jdbc-url: ${spring.datasource.master.jdbc-url}
      username: ${spring.datasource.master.username}
      password: ${spring.datasource.master.password}

    slave:
      driver-class-name: com.mysql.cj.jdbc.Driver
      jdbc-url: ${spring.datasource.slave.jdbc-url}
      username: ${spring.datasource.slave.username}
      password: ${spring.datasource.slave.password}

  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true
    hibernate:
      ddl-auto: update
    show-sql: true

  kafka:
    bootstrapAddress: ${spring.kafka.bootstrapAddress}
    topicName: viewCount

block:
  service:
    api:
      content-type: application/json

member:
  service:
    api:
      content-type: application/json

logging:
  level:
    root: info
    com.blockpage.purchaseservice.adaptor.infrastructure.message: debug

```
* 위의 과정을 마치고 프로그램을 실행합니다.

<br>

<br>
