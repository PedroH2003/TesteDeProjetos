CREATE DATABASE teste_crud;

SELECT * FROM vendas;

CREATE TABLE vendas(
	id INT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(40) NOT NULL,
    valor DECIMAL(8,2),
    
    PRIMARY KEY (id)
)

    