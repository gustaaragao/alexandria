-- Criando a columa "available"
alter table books add available tinyint;

-- Atualizando os livros já registrados
update books set available = 1;