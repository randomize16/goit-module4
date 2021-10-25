create table groups (
	id serial primary key,
	name varchar(50),
	description varchar(50)
);

create table users (
	id serial primary key,
	name varchar(50)
);

alter table groups
	alter column description type varchar(250) ,
	add column just_text text
;

create table group_to_user (
	user_id integer not null,
	group_id integer not null,

	constraint user_id_fk foreign key (user_id) references users(id),
	constraint group_id_fk foreign key (group_id) references groups(id)
);

CREATE TABLE category (
	id serial4 NOT NULL,
	name varchar(50) NULL,
	description varchar(50) NULL,
	parent_id integer NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id),
	CONSTRAINT parent_id_fk FOREIGN KEY (parent_id) REFERENCES public.category(id)
);

create table Items (
	id serial PRIMARY KEY,
	name varchar(50),
	description varchar(250),
	count integer,
	user_id integer not null,
	CONSTRAINT items_fk foreign key (user_id) REFERENCES users(id)
);

CREATE TABLE orders (
	id serial4 NOT NULL,
	"number" integer NOT NULL,
	descr varchar(250) NULL,
	user_id integer NOT NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id),
	CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_lines (
	id serial4 NOT NULL,
	order_id integer NOT NULL,
	item_id integer NOT NULL,
	item_count integer NULL,
	CONSTRAINT order_lines_pkey PRIMARY KEY (id),
	CONSTRAINT item_id_fk FOREIGN KEY (item_id) REFERENCES Items(id),
	CONSTRAINT oreder_id_fk FOREIGN KEY (order_id) REFERENCES orders(id)
);

