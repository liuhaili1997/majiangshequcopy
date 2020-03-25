create table USER
(
    ID           INT AUTO_INCREMENT primary key not null ,
    ACCOUNT_ID   VARCHAR(128),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT
);