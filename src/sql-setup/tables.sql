drop table chrctr;
drop table monster;
drop table creature_weapon;
drop table creature;
drop table weapon;
drop table player;
drop table initiative;
drop table chat_message;
drop table password;
drop table usr;
drop table game_lobby;


-- create tables

create table creature(
    creature_id integer not null,
    HP smallint,
    AC smallint,
    attack_bonus smallint,
    movement smallint,
    attack_turn smallint,
    damage_bonus smallint,
    pos_x integer default 0,
    pos_y integer default 0,
    creature_lv integer default 1,
    constraint creature_pk primary key(creature_id));

create table chrctr(
    character_id integer not null references creature(creature_id),
    character_name varchar(20),
    back_story text,
    constraint chrctr_pk primary key(character_id));

create table monster(
    monster_id integer not null references creature(creature_id),
    monster_name varchar(20),
    monster_lv smallint default 0,
    constraint monster_pk primary key(monster_id));

create table weapon(
    weapon_id integer not null,
    weapon_name varchar(20),
    damage_dice smallint default 0,
    dice_amount smallint default 0,
    description varchar(20),
    constraint weapon_pk primary key(weapon_id));

create table creature_weapon(
    weapon_id integer not null,
    creature_id integer not null default 0,
    constraint creature_weapon_pk primary key(weapon_id, creature_id));

create table usr(
    user_id integer not null auto_increment,
    username varchar(30),
    rank integer default 0,
    email varchar(30),
    lobby_key integer,
    constraint user_pk primary key(user_id));

create table password(
    user_id integer not null references usr(user_id),
    salt_pass char(64) not null,
    hash_pass char(64) not null,
    constraint password_pk primary key(user_id));
)

create table player(
    player_id integer not null,
    player_lv integer,
    constraint player_pk primary key(player_id));

create table initiative(
    lobby_key integer not null references game_lobby(lobby_key),
    user_id integer not null references usr(user_id),
    initiative_turn smallint,
    constraint initiative_pk primary key(lobby_key, user_id));

create table game_lobby(
    lobby_key integer not null auto_increment,
    player_turn smallint,
    constraint game_lobby_pk primary key(lobby_key));

create table chat_message(
    lobby_key integer not null references game_lobby(lobby_key),
    message_id integer not null,
    user_id integer not null references usr(user_id),
    message text NOT NULL,
    time_stamp time NOT NULL,
    constraint chat_message_pk primary key(lobby_key, message_id));


    -- Foreign keys

    ALTER TABLE chat_message ADD CONSTRAINT chat_message_fk1 FOREIGN KEY(lobby_key) REFERENCES game_lobby(lobby_key);
    ALTER TABLE chat_message ADD CONSTRAINT chat_message_fk2 FOREIGN KEY(user_id) REFERENCES usr(user_id);

    ALTER TABLE usr ADD CONSTRAINT usr_fk FOREIGN KEY(lobby_key) REFERENCES game_lobby(lobby_key);


    -- Insert data for characters, monsters and weapons

    insert into creature values(1, 32, 16, 9, 7, 2, 5, default, default, default);
    insert into creature values(2, 23, 16, 7, 6, 2, 7, default, default, default);
    insert into creature values(3, 36, 18, 8, 6, 2, 5, default, default, default);
    insert into creature values(4, 22, 15, 8, 6, 1, 0, default, default, default);
    insert into creature values(5, 45, 15, 5, 10, 1, 3, default, default, default);
    insert into creature values(6, 126, 17, 8, 6, 2, 5, default, default, default);
    insert into creature values(7, 75, 18, 7, 6, 2, 4, default, default, default);
    insert into creature values(8, 127, 16, 7, 6, 3, 4, default, default, default);
    insert into creature values(9, 178, 18, 10, 8, 3, 6, default, default, default);

    insert into chrctr values(1, 'Ranger', '');
    insert into chrctr values(2, 'Rouge', '');
    insert into chrctr values(3, 'Fighter', '');
    insert into chrctr values(4, 'Wizard', '');

    insert into monster values(5, 'Hell bound', default);
    insert into monster values(6, 'Earth elemental', default);
    insert into monster values(7, 'Grick alpha', default);
    insert into monster values(8, 'Green slaad', default);
    insert into monster values(9, 'Red Dragon', default);

    insert into weapon values(1, 'Long sword', 8, 1, 'Melee');
    insert into weapon values(2, 'Javelin', 6, 1, 'Ranged');
    insert into weapon values(3, 'Longbow', 8, 1, 'Ranged');
    insert into weapon values(4, 'Short sword', 6, 1, 'Melee');
    insert into weapon values(5, 'Dagger', 4, 1, 'Melee');
    insert into weapon values(6, 'Hand crossbow', 6, 1, 'Ranged');
    insert into weapon values(7, 'Chromatic Orb', 8, 3, 'Ranged');
    insert into weapon values(8, 'Fireball', 6, 6, 'Ranged');
    insert into weapon values(9, 'Slaad bite', 6, 2, 'Melee');
    insert into weapon values(10, 'Slaad claw', 6, 1, 'Melee');
    insert into weapon values(11, 'Slaad hurl flame ', 6, 3, 'Ranged');
    insert into weapon values(12, 'Elemental slam', 8, 2, 'Melee');
    insert into weapon values(13, 'Grick tail', 6, 2, 'Melee');
    insert into weapon values(14, 'Grick tentacles', 8, 4, 'Melee');
    insert into weapon values(15, 'Grick beak', 8, 2, 'Melee');
    insert into weapon values(16, 'Hound bite', 8, 1, 'Melee');
    insert into weapon values(17, 'Hound breath', default, default, default);
    insert into weapon values(18, 'Dragon bite', 10, 2, 'Melee');
    insert into weapon values(19, 'Dragon claw', 6, 2, 'Melee');
    insert into weapon values(20, 'Dragon breath', default, default, default);

    insert into creature_weapon values(1, 3);
    insert into creature_weapon values(2, 3);
    insert into creature_weapon values(3, 1);
    insert into creature_weapon values(4, 1);
    insert into creature_weapon values(5, 2);
    insert into creature_weapon values(6, 2);
    insert into creature_weapon values(7, 4);
    insert into creature_weapon values(8, 4);
    insert into creature_weapon values(9, 8);
    insert into creature_weapon values(10, 8);
    insert into creature_weapon values(11, 8);
    insert into creature_weapon values(12, 6);
    insert into creature_weapon values(13, 7);
    insert into creature_weapon values(14, 7);
    insert into creature_weapon values(15, 7);
    insert into creature_weapon values(16, 5);
    insert into creature_weapon values(17, default);
    insert into creature_weapon values(18, 9);
    insert into creature_weapon values(19, 9);
    insert into creature_weapon values(20, default);
