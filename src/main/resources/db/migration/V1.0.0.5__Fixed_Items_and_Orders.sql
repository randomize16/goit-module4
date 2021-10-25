alter table items
    drop column count;

alter table orders
    rename column descr to description;