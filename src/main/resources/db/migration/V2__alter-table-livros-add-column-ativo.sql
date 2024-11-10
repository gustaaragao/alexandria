-- Criando a columa "ativo"
alter table livros add ativo tinyint;

-- Atualizando os livros já registrados
update livros set ativo = 1;