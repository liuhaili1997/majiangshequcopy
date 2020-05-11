create table manager
(
	id              bigint auto_increment,
	account_id      varchar(128) not null,
	name            varchar(50) not null,
	password        varchar(128) not null,
	email           varchar(128) not null,
	avatar          varchar(512) not null,
	token           char(36) not null,
	gmt_create      bigint not null,
	gmt_modified    bigint not null,
	constraint manager_pk
		primary key (id)
);

comment on column manager.account_id is '账户id';

comment on column manager.name is '姓名';

comment on column manager.password is '密码';

comment on column manager.email is '邮箱';

comment on column manager.avatar is '图片位置';

comment on column manager.token is '唯一标识';

comment on column manager.gmt_create is '创建时间';

comment on column manager.gmt_modified is '更新时间';

