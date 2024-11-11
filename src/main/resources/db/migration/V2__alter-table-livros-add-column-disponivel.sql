-- Criando a columa "disponivel"
alter table livros add disponivel tinyint;

-- Atualizando os livros já registrados
update livros set disponivel = 1;