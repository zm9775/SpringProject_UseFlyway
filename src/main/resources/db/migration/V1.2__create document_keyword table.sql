create table document_keyword(
    doc_id int not null ,
    keyword_id int not null ,
    primary key (doc_id,keyword_id),
    foreign key (doc_id) references documents(id) on update cascade,
    foreign key (keyword_id) references keywords(id) on delete cascade on update cascade
)