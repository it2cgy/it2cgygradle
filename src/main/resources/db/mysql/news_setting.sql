drop table if exists pvmts_news_setting;

/*==============================================================*/
/* Table: pvmts_news_setting                                    */
/*==============================================================*/
create table pvmts_news_setting
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   push                 int(1),
   sound                int(1),
   vibrate              int(1),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);