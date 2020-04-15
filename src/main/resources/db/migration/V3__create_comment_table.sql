create table comment
(
	id              bigint auto_increment not null,
	parent_id       bigint not null,
	type            int not null,
	commentator     varchar(128) not null,
	gmt_create      bigint not null,
	gmt_modified    bigint not null,
	like_count      bigint default 0 not null,
	comment_count   bigint default 0 not null,
	CONTENT      VARCHAR(2048),
	constraint comment_pk
		primary key (id)
);

comment on table comment is '评论';
comment on column comment.parent_id is '父类id';
comment on column comment.type is '父类类型';
comment on column comment.commentator is '评论人的id对应user中的account_id';
comment on column comment.gmt_create is '创建时间';
comment on column comment.gmt_modified is '更新时间';
comment on column comment.like_count is '点赞数';
comment on column comment.comment_count is '评论的评论数';
comment on column COMMENT.CONTENT is '回复内容';