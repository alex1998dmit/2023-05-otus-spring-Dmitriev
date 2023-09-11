insert into authors (`firstName`, `lastName`, `middleName`)
values ('Tolkien', 'John', 'Ronald');
insert into authors (`firstName`, `lastName`, `middleName`)
values ('Pushkin', 'Alexander', 'Sergeevich');
insert into authors (`firstName`, `lastName`, `middleName`)
values ('Esenin', 'Sergey', 'Alexandrovich');
insert into authors (`firstName`, `lastName`, `middleName`)
values ('Turgenev', 'Ivan', 'Sergeevich');

insert into genres (`title`)
values ('Lyrics');
insert into genres (`title`)
values ('Novel');
insert into genres (`title`)
values ('Fantasy');

insert into books (`title`, author_id, genre_id)
values ('Lord of the rings', 1, 3);
insert into books (`title`, author_id, genre_id)
values ('Fathers and kids', 4, 2);
insert into books (`title`, author_id, genre_id)
values ('U Lukomorya', 2, 1);