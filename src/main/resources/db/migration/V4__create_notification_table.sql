-- auto-generated definition
create table NOTIFICATION
(
    ID            BIGINT auto_increment,
    NOTIFIER      VARCHAR(128)  not null,
    RECEIVER      VARCHAR(128)  not null,
    OUTER_ID      BIGINT        not null,
    TYPE          INT           not null,
    GMT_CREATE    BIGINT        not null,
    STATUS        INT default 0 not null,
    NOTIFIER_NAME VARCHAR(128)  not null,
    OUTER_TITLE   VARCHAR(2048) not null,
    constraint NOTIFICATION_PK
        primary key (ID)
);

comment on table NOTIFICATION is '通知';

comment on column NOTIFICATION.ID is 'id';

comment on column NOTIFICATION.NOTIFIER is '发通知的人的accountID';

comment on column NOTIFICATION.RECEIVER is '接受通知人的accountID';

comment on column NOTIFICATION.OUTER_ID is '是回复的问题ID还是评论ID';

comment on column NOTIFICATION.TYPE is '0:问题  1:评论';

comment on column NOTIFICATION.GMT_CREATE is '创建时间';

comment on column NOTIFICATION.STATUS is '0表示未读的信息，1表示已读的信息';

comment on column NOTIFICATION.NOTIFIER_NAME is '发送人名';

comment on column NOTIFICATION.OUTER_TITLE is '可是问题标题，也可以是评论内容';

