create table notification
(
	id          bigint auto_increment,
	notifier    varchar(128) not null,
	receiver    varchar(128) not null,
	outer_id    bigint not null,
	type        int not null,
	gmt_create  bigint not null,
	status      int default 0 not null,
	constraint notification_pk
		primary key (id)
);

comment on table notification is '通知';

comment on column notification.id is 'id';

comment on column notification.notifier is '发通知的人的accountID';

comment on column notification.receiver is '接受通知人的accountID';

comment on column notification.outer_id is '是回复的问题ID还是评论ID';

comment on column notification.type is '0:问题  1:评论';

comment on column notification.gmt_create is '创建时间';

comment on column notification.status is '0表示未读的信息，1表示已读的信息';