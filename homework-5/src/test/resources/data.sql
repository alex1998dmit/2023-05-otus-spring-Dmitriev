insert into authors (`firstName`, `lastName`, `middleName`)
values ('Tolkien', 'John', 'Ronald');
insert into authors (`firstName`, `lastName`, `middleName`)
values ('Pushkin', 'Alexander', 'Sergeevich');

insert into genres (`title`) values ('Lyrics');
insert into genres (`title`) values ('Fantasy');

insert into books (`title`, author_id, genre_id) values ('Lord of the rings', 1, 2);
insert into books (`title`, author_id, genre_id) values ('U Lukomorya', 2, 1);