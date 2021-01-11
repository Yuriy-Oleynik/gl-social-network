create table comment (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    created_date datetime(6),
    text varchar(2048),
    post_id bigint,
    user_id bigint,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table community (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    created_date datetime(6),
    description varchar(2048),
    name varchar(255),
    user_user_id bigint,
    primary key (id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table community_posts (
    community_id  bigint NOT NULL,
    posts_post_id bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table post (
    post_id bigint(20) NOT NULL AUTO_INCREMENT,
    created_date datetime(6),
    description longtext,
    image_key longtext,
    post_name varchar(255),
    url varchar(255),
    vote_count integer,
    id bigint,
    user_id bigint,
    primary key (post_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table refresh_token (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    created_date datetime(6),
    token varchar(255),
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table token (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    expiry_date datetime(6),
    token varchar(255),
    user_user_id bigint(20),
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table user (
    user_id bigint(20) NOT NULL AUTO_INCREMENT,
    created datetime(6),
    email varchar(255),
    enabled bit NOT NULL ,
    password varchar(255),
    username varchar(255),
    primary key (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table vote (
    vote_id   bigint(20) NOT NULL AUTO_INCREMENT,
    vote_type integer,
    post_id bigint(20) NOT NULL ,
    user_id bigint(20),
    primary key (vote_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table community_posts
    add constraint UK_8ov8i6i2a9t0a4xs8gdhrnngq unique (posts_post_id);

alter table comment
    add constraint FKs1slvnkuemjsq2kj4h3vhx7i1
    foreign key (post_id)
    references post (post_id);

alter table comment
    add constraint FK8kcum44fvpupyw6f5baccx25c
    foreign key (user_id)
    references user (user_id);

alter table community
    add constraint FK1umq1xso6klt62g57bnt8ey7k
    foreign key (user_user_id)
    references user (user_id);

alter table community_posts
    add constraint FKknbj0aag8x2jj2e2uh3355bqo
    foreign key (posts_post_id)
    references post (post_id);

alter table community_posts
    add constraint FKhvs127iyy8jrb5fqp8lhj8cpx
    foreign key (community_id)
    references community (id);

alter table post
    add constraint FK2tjpr1utvgmqx36crhxdoerfu
    foreign key (id)
    references community (id);

alter table post
    add constraint FK72mt33dhhs48hf9gcqrq4fxte
    foreign key (user_id)
    references user (user_id);

alter table token
    add constraint FK79keudebybjlldk2o4i0nwqev
    foreign key (user_user_id)
    references user (user_id);

alter table vote
    add constraint FKl3c067ewaw5xktl5cjvniv3e9
    foreign key (post_id)
    references post (post_id);

alter table vote
    add constraint FKcsaksoe2iepaj8birrmithwve
    foreign key (user_id)
    references user (user_id);
