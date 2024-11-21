CREATE TABLE Cliente (
                         cod_cliente SERIAL PRIMARY KEY,
                         nme_cliente VARCHAR(100) NOT NULL,
                         dta_nascimento DATE NOT NULL,
                         nro_cpf VARCHAR(11) UNIQUE NOT NULL,
                         dta_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Endereco (
                          cod_endereco SERIAL PRIMARY KEY,
                          nme_logradouro VARCHAR(200) NOT NULL,
                          nme_bairro VARCHAR(100) NOT NULL,
                          nro_cep VARCHAR(10) NOT NULL,
                          nme_cidade VARCHAR(100) NOT NULL,
                          nme_estado VARCHAR(2) NOT NULL
);

CREATE TYPE estado_ocorrencia AS ENUM ('PENDENTE', 'FINALIZADA');

CREATE TABLE Ocorrencia (
                            cod_ocorrencia SERIAL PRIMARY KEY,
                            cod_endereco INT REFERENCES Endereco(cod_endereco) ON DELETE CASCADE,
                            cod_cliente INT REFERENCES Cliente(cod_cliente) ON DELETE CASCADE,
                            dta_ocorrencia TIMESTAMP NOT NULL,
                            sta_ocorrencia estado_ocorrencia NOT NULL DEFAULT 'PENDENTE'
);

CREATE TABLE FotoOcorrencia (
                                cod_foto_ocorrencia SERIAL PRIMARY KEY,
                                cod_ocorrencia INT REFERENCES Ocorrencia(cod_ocorrencia) ON DELETE CASCADE,
                                dta_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                path_bucket VARCHAR(255) NOT NULL,
                                hash VARCHAR(64) NOT NULL
);
