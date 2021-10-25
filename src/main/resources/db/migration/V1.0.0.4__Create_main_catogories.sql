insert into category(name, description)
 values ('Бытова техника', 'Вся бытовая техника');

insert into category(name, description, parent_id)
 values ('Телевизоры', 'Вся бытовая техника',
  (select id from category where name = 'Бытова техника'));

insert into category(name, description, parent_id)
 values ('Кухонные плиты', 'Кухонные плиты',
  (select id from category where name = 'Бытова техника'));