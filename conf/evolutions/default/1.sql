# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table balls (
  id                        integer not null,
  color                     varchar(255) not null,
  created_at                timestamp,
  updated_at                timestamp,
  constraint pk_balls primary key (id))
;

create table cms_basic_pages (
  id                        integer not null,
  title                     varchar(255) not null,
  content                   TEXT not null,
  created_at                timestamp,
  updated_at                timestamp,
  constraint pk_cms_basic_pages primary key (id))
;

create table comments (
  id                        integer not null,
  commentable_id            integer not null,
  commentable_type          varchar(255) not null,
  content                   TEXT not null,
  created_at                timestamp,
  updated_at                timestamp,
  constraint pk_comments primary key (id))
;

create table divisions (
  custom_id                 integer not null,
  name                      varchar(50),
  created_at                timestamp,
  updated_at                timestamp,
  custom_league_id          integer not null,
  constraint pk_divisions primary key (custom_id))
;

create table drafts (
  id                        integer not null,
  created_at                timestamp,
  updated_at                timestamp,
  player_id                 integer,
  team_id                   integer,
  date                      timestamp not null,
  round                     integer not null,
  pick                      integer not null,
  overall                   integer not null,
  college                   varchar(100) not null,
  notes                     TEXT not null,
  constraint pk_drafts primary key (id))
;

create table fans (
  id                        integer not null,
  created_at                timestamp,
  updated_at                timestamp,
  name                      varchar(100),
  constraint pk_fans primary key (id))
;

create table field_tests (
  id                        integer not null,
  string_field              varchar(255) not null,
  text_field                TEXT not null,
  integer_field             integer not null,
  float_field               float not null,
  decimal_field             decimal(38) not null,
  datetime_field            timestamp not null,
  timestamp_field           timestamp not null,
  time_field                time not null,
  date_field                timestamp not null,
  boolean_field             boolean,
  created_at                timestamp,
  updated_at                timestamp,
  format                    varchar(255) not null,
  restricted_field          varchar(255) not null,
  protected_field           varchar(255) not null,
  paperclip_asset_file_name varchar(255) not null,
  dragonfly_asset_uid       varchar(255) not null,
  carrierwave_asset         varchar(255) not null,
  constraint pk_field_tests primary key (id))
;

create table leagues (
  id                        integer not null,
  created_at                timestamp,
  updated_at                timestamp,
  name                      varchar(50),
  constraint pk_leagues primary key (id))
;

create table nested_field_tests (
  id                        integer not null,
  title                     varchar(255) not null,
  field_test_id             integer,
  created_at                timestamp,
  updated_at                timestamp,
  constraint pk_nested_field_tests primary key (id))
;

create table players (
  id                        integer not null,
  created_at                timestamp,
  updated_at                timestamp,
  deleted_at                timestamp not null,
  team_id                   integer not null,
  name                      varchar(100),
  position                  varchar(50) not null,
  number                    integer,
  retired                   boolean,
  injured                   boolean,
  born_on                   timestamp not null,
  notes                     TEXT not null,
  suspended                 boolean,
  constraint pk_players primary key (id))
;

create table rel_tests (
  id                        integer not null,
  league_id                 integer,
  division_custom_id        integer,
  player_id                 integer,
  created_at                timestamp,
  updated_at                timestamp,
  constraint pk_rel_tests primary key (id))
;

create table sapsan_histories (
  id                        integer not null,
  message                   varchar(255),
  user_name                 varchar(255),
  item                      integer,
  table_name                varchar(255),
  created_at                timestamp not null,
  constraint pk_sapsan_histories primary key (id))
;

create table teams (
  id                        integer not null,
  created_at                timestamp,
  updated_at                timestamp,
  division_custom_id        integer,
  name                      varchar(50) not null,
  logo_url                  varchar(255) not null,
  manager                   varchar(100),
  ballpark                  varchar(100) not null,
  mascot                    varchar(100) not null,
  founded                   integer not null,
  wins                      integer not null,
  losses                    integer not null,
  win_percentage            float not null,
  revenue                   decimal(38) not null,
  color                     varchar(255) not null,
  constraint pk_teams primary key (id))
;

create table unscoped_pages (
  id                        integer not null,
  title                     varchar(255) not null,
  created_at                timestamp,
  updated_at                timestamp,
  constraint pk_unscoped_pages primary key (id))
;

create table users (
  id                        integer not null,
  email                     varchar(255),
  encrypted_password        bytea,
  reset_password_token      bytea not null,
  remember_token            varchar(255) not null,
  remember_created_at       timestamp not null,
  sign_in_count             integer not null,
  current_sign_in_at        timestamp not null,
  last_sign_in_at           timestamp not null,
  current_sign_in_ip        varchar(255) not null,
  last_sign_in_ip           varchar(255) not null,
  password_salt             bytea not null,
  created_at                timestamp,
  updated_at                timestamp,
  avatar_file_name          varchar(255) not null,
  avatar_content_type       varchar(255) not null,
  avatar_file_size          integer not null,
  avatar_updated_at         timestamp not null,
  roles                     varchar(255) not null,
  constraint uq_users_email unique (email),
  constraint uq_users_reset_password_token unique (reset_password_token),
  constraint pk_users primary key (id))
;


create table fans_teams (
  fans_id                        integer not null,
  teams_id                       integer not null,
  constraint pk_fans_teams primary key (fans_id, teams_id))
;

create table players_teams (
  players_id                     integer not null,
  teams_id                       integer not null,
  constraint pk_players_teams primary key (players_id, teams_id))
;
create sequence balls_seq;

create sequence cms_basic_pages_seq;

create sequence comments_seq;

create sequence divisions_seq;

create sequence drafts_seq;

create sequence fans_seq;

create sequence field_tests_seq;

create sequence leagues_seq;

create sequence nested_field_tests_seq;

create sequence players_seq;

create sequence rel_tests_seq;

create sequence sapsan_histories_seq;

create sequence teams_seq;

create sequence unscoped_pages_seq;

create sequence users_seq;

alter table drafts add constraint fk_drafts_player_1 foreign key (player_id) references players (id);
create index ix_drafts_player_1 on drafts (player_id);
alter table drafts add constraint fk_drafts_team_2 foreign key (team_id) references teams (id);
create index ix_drafts_team_2 on drafts (team_id);
alter table nested_field_tests add constraint fk_nested_field_tests_fieldTes_3 foreign key (field_test_id) references field_tests (id);
create index ix_nested_field_tests_fieldTes_3 on nested_field_tests (field_test_id);
alter table rel_tests add constraint fk_rel_tests_league_4 foreign key (league_id) references leagues (id);
create index ix_rel_tests_league_4 on rel_tests (league_id);
alter table rel_tests add constraint fk_rel_tests_division_5 foreign key (division_custom_id) references divisions (custom_id);
create index ix_rel_tests_division_5 on rel_tests (division_custom_id);
alter table rel_tests add constraint fk_rel_tests_player_6 foreign key (player_id) references players (id);
create index ix_rel_tests_player_6 on rel_tests (player_id);
alter table teams add constraint fk_teams_division_7 foreign key (division_custom_id) references divisions (custom_id);
create index ix_teams_division_7 on teams (division_custom_id);



alter table fans_teams add constraint fk_fans_teams_fans_01 foreign key (fans_id) references fans (id);

alter table fans_teams add constraint fk_fans_teams_teams_02 foreign key (teams_id) references teams (id);

alter table players_teams add constraint fk_players_teams_players_01 foreign key (players_id) references players (id);

alter table players_teams add constraint fk_players_teams_teams_02 foreign key (teams_id) references teams (id);

# --- !Downs

drop table if exists balls cascade;

drop table if exists cms_basic_pages cascade;

drop table if exists comments cascade;

drop table if exists divisions cascade;

drop table if exists drafts cascade;

drop table if exists fans cascade;

drop table if exists fans_teams cascade;

drop table if exists field_tests cascade;

drop table if exists leagues cascade;

drop table if exists nested_field_tests cascade;

drop table if exists players cascade;

drop table if exists players_teams cascade;

drop table if exists rel_tests cascade;

drop table if exists sapsan_histories cascade;

drop table if exists teams cascade;

drop table if exists unscoped_pages cascade;

drop table if exists users cascade;

drop sequence if exists balls_seq;

drop sequence if exists cms_basic_pages_seq;

drop sequence if exists comments_seq;

drop sequence if exists divisions_seq;

drop sequence if exists drafts_seq;

drop sequence if exists fans_seq;

drop sequence if exists field_tests_seq;

drop sequence if exists leagues_seq;

drop sequence if exists nested_field_tests_seq;

drop sequence if exists players_seq;

drop sequence if exists rel_tests_seq;

drop sequence if exists sapsan_histories_seq;

drop sequence if exists teams_seq;

drop sequence if exists unscoped_pages_seq;

drop sequence if exists users_seq;

