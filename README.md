## 토닥 프로젝트

토닥 프로젝트입니다.

### 스펙 

[토닥 스펙 정리](https://boostnote.io/shared/79e57ea8-8f5f-4f38-b283-3bf2f09b4a6c)

현재 로그인 (Oauth, jwt 인증 기반) 기능이 구현되어 있어 프로젝트 실행하려면 사전 작업이 필요합니다.

### 프로젝트 실행

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/todak?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true
    username: root
    password: root
```

프로젝트 실행 시, 저희는 공통 개발 DB가 없기 때문에, datasource 설정을 위와 같이 자신의 로컬 환경에
맞도록 설정해주셔야 합니다.

1. 로컬 mysql 8.x 버전을 설치 (추천)
2. h2 메모리 DB 사용 (추천하진 않지만 mysql 설치가 귀찮으신 경우 사용)

사용하시는 DB에 아래와 같이 테이블을 생성해주시면 됩니다.

```sql
create table todak_user (
    user_seq INT(11) NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(64) NOT NULL,
    username VARCHAR(100) NOT NULL DEFAULT 'NONAME',
    password VARCHAR(128) NOT NULL,
    email VARCHAR(512) NOT NULL,
    email_verified_yn CHAR(1) NOT NULL DEFAULT 'N',
    profile_image_url VARCHAR(512) NOT NULL DEFAULT '',
    phone VARCHAR(20) NOT NULL DEFAULT '',
    user_type VARCHAR(20) NOT NULL DEFAULT 'TODAK',
    provider_type VARCHAR(20) NOT NULL DEFAULT 'TODAK',
    role_type VARCHAR(20) NOT NULL DEFAULT 'UNIDENTIFIED',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    primary key (user_seq),
    UNIQUE KEY (user_id)
);

create table todak_user_refresh_token (
    refresh_token_seq INT(11) NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(64) NOT NULL,
    refresh_token VARCHAR(256) NOT NULL DEFAULT '',
    primary key (refresh_token_seq),
    UNIQUE KEY (user_id)
);
```
