create table livros(
	id bigint not null auto_increment,
	nome varchar(100) not null,
	genero varchar(100) not null,
	autor varchar(100) not null,
	editora varchar(100) not null,
	
	primary key (id)
);