create table QUESTION
(
    ID            INT auto_increment,
    TITLE         VARCHAR(50)  not null,
    DESCRIPTION   TEXT         not null,
    GMT_CREATE    BIGINT       not null,
    GMT_MODIFIED  BIGINT       not null,
    CREATOR       VARCHAR(128) not null,
    COMMENT_COUNT INT default 0,
    VIEW_COUNT    INT default 0,
    LIKE_COUNT    INT default 0,
    TAG           VARCHAR(256) not null,
    constraint QUESTION_PK
        primary key (ID)
);

comment on table QUESTION is '问题发布表';

comment on column QUESTION.TITLE is '标题';

comment on column QUESTION.DESCRIPTION is '描述问题';

comment on column QUESTION.GMT_CREATE is '创建时间';

comment on column QUESTION.GMT_MODIFIED is '更新时间';

comment on column QUESTION.COMMENT_COUNT is '评论数';

comment on column QUESTION.VIEW_COUNT is '浏览人数';

comment on column QUESTION.LIKE_COUNT is '点赞数';

comment on column QUESTION.TAG is '标签';
