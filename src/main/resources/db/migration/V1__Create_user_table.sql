create table USER
(
    ID           BIGINT auto_increment not null,
    ACCOUNT_ID   VARCHAR(128) not null,
    NAME         VARCHAR(50),
    TOKEN        CHAR(36)     not null,
    GMT_CREATE   BIGINT       not null,
    GMT_MODIFIED BIGINT       not null,
    BIO          VARCHAR(256),
    AVATAR       VARCHAR(256),
    constraint USER_PK
        primary key (ID)
);

comment on column USER.ID is 'id';

comment on column USER.ACCOUNT_ID is '帐号id';

comment on column USER.NAME is '姓名';

comment on column USER.TOKEN is 'cookie';

comment on column USER.GMT_CREATE is '创建时间';

comment on column USER.GMT_MODIFIED is '更新时间';

comment on column USER.BIO is '描述';

comment on column USER.AVATAR is '头像路径';