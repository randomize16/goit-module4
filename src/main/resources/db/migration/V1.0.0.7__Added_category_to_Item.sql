alter table items
    add column category_id integer not null;

alter table items
    add constraint category_fk foreign key (category_id) references category(id);