CREATE TABLE Usuario (
                        id SERIAL PRIMARY KEY,
                        login VARCHAR(100) NOT NULL UNIQUE,
                        password VARCHAR(100) NOT NULL,
                        role VARCHAR(100) NOT NULL
);