# 만약 lolHiT 데이터베이스가 존재하다면 삭제
DROP DATABASE IF EXISTS lolHiT;

# lolHiT 데이터 베이스 생성
CREATE DATABASE lolHiT;

# lolHiT 사용 선언
USE lolHiT;


# 게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    delDate DATETIME,
    displayStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    title CHAR(200) NOT NULL,
    `body` LONGTEXT NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL
    
);

# memberId 아직 사용하지 않아서 칼럼 삭제 
ALTER TABLE article DROP memberId;

SELECT *
FROM article;


# 회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(100) NOT NULL,
    loginPw CHAR(200) NOT NULL,
    `name` CHAR(100) NOT NULL,
    email CHAR(100) NOT NULL
);

SELECT *
FROM `member`;
