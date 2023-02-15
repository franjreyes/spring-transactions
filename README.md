# Transacciones
Transacciones con @Transactional y TransactionManager de Spring

## superheroes-api
El proyecto es un API REST de Superhéroes desarrollado con Spring Boot y Spring Transactions.

Para poder usarlo necesitarás una base de datos donde tendrás que crear el esquema y las tablas necesarias, esto ya lo tuviste que hacer durante las horas de clase.

Usa el application.properties para configurar la conexión con tú base de datos.

tablas sql-server

CREATE TABLE debilidades (
id INT NOT NULL IDENTITY PRIMARY KEY,
debilidad VARCHAR(255) NOT NULL
);

CREATE TABLE superpoderes (
id INT NOT NULL IDENTITY PRIMARY KEY,
superpoder VARCHAR(255) NOT NULL
);

CREATE TABLE superheroes (
id INT NOT NULL IDENTITY PRIMARY KEY,
nombre VARCHAR(255) NOT NULL,
alterego VARCHAR(255) NOT NULL,
imagen VARCHAR(255)
);

CREATE TABLE superheroe_debilidad (
superheroe_id INT NOT NULL,
debilidad_id INT NOT NULL,
PRIMARY KEY (superheroe_id,debilidad_id),
FOREIGN KEY (superheroe_id) REFERENCES superheroes (id),
FOREIGN KEY (debilidad_id) REFERENCES debilidades (id)
);

CREATE TABLE superheroe_superpoder (
superheroe_id INT NOT NULL,
superpoder_id INT NOT NULL,
PRIMARY KEY (superheroe_id,superpoder_id),
FOREIGN KEY (superheroe_id) REFERENCES superheroes (id),
FOREIGN KEY (superpoder_id) REFERENCES superpoderes (id)
);

insert into debilidades (debilidad) values ('Lazo de la verdad'),('Anillo amarillo'),('Kriptonita'),('Su nombre'),('Fuego');
insert into superpoderes (superpoder) values ('volar'),('fuerza'),('color'),('comer'),('agua'),('velocidad');