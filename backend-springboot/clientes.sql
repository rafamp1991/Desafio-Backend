CREATE TABLE clientes(
		    id_cliente SERIAL NOT NULL,
		    nome VARCHAR(40) NOT NULL,
		    sobrenome VARCHAR(60)  NOT NULL,
		    sexo VARCHAR(40) NOT NULL,
		    dataNascimento DATE NOT NULL,
		    idade INT NOT NULL,
		    id_cidade INT NOT NULL,
		    PRIMARY KEY (id_cliente),
		    FOREIGN KEY (id_cidade) REFERENCES cidades (id_cidade)
		);