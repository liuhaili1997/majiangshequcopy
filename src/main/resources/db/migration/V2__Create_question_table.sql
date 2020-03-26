create table question
(
	id int auto_increment,
	title varchar(50),
	description text,
	gmt_create bigint,
	gmt_modified bigint,
	creator int,
	comment_count int,
	view_count int,
	like_count int,
	tag varchar(256),
	constraint question_pk
		primary key (id)
);

comment on table question is '问题发布表';

comment on column question.title is '标题';

comment on column question.description is '描述问题';

comment on column question.gmt_create is '创建时间';

comment on column question.gmt_modified is '更新时间';

comment on column question.creator is '发布者';

comment on column question.comment_count is '评论数';

comment on column question.view_count is '浏览人数';

comment on column question.like_count is '点赞数';

comment on column question.tag is '标签';

