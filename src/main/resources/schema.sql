------- INICIO creación la tabla users
CREATE TABLE users (
	user_id uuid NOT NULL,
	user_name varchar NOT NULL,
	user_email varchar NOT NULL,
	user_password varchar NOT NULL,
	user_created timestamp NOT NULL,
	user_modified timestamp NOT NULL,
	user_last_login timestamp NOT NULL,
	user_token varchar NOT NULL,
	user_active boolean NOT NULL
);

--Creamos la llave primaria para tabla users
ALTER TABLE	users ADD CONSTRAINT users_pk PRIMARY KEY (user_id);

--Creamos la llave única para tabla users
ALTER TABLE	users ADD CONSTRAINT users_email UNIQUE (user_email);

--Agregamos comentarios
COMMENT ON COLUMN users.user_id IS 'Identificador único de la tabla generado UUID';
COMMENT ON COLUMN users.user_name IS 'Nombre del usuario';
COMMENT ON COLUMN users.user_email IS 'Email del usuario, debe ser único';
COMMENT ON COLUMN users.user_password IS 'Contraseña del usuario';
COMMENT ON COLUMN users.user_created IS 'Fecha de creación del usuario';
COMMENT ON COLUMN users.user_modified IS 'Fecha de modificación';
COMMENT ON COLUMN users.user_last_login IS 'Fecha del ultimo login';
COMMENT ON COLUMN users.user_token IS 'Token del usuario JWT';
COMMENT ON COLUMN users.user_active IS 'Indica si el usuario esta activo en el sistema';

------- FIN creación la tabla users


------- INICIO creación la tabla phone
CREATE TABLE phone (
	phone_id bigint NOT NULL AUTO_INCREMENT,
	phone_number varchar NOT NULL,
	phone_city_code varchar NOT NULL,
	phone_country_code varchar NOT NULL,
	users_user_id varchar NOT NULL
);

--Creamos la llave primaria para tabla phone
ALTER TABLE	phone ADD CONSTRAINT phone_pk PRIMARY KEY (phone_id);

--Creamos la llave foraneas para tabla phone
ALTER TABLE	phone ADD CONSTRAINT phone_fk FOREIGN KEY (users_user_id) REFERENCES users(user_id);

--Agregamos comentarios tabla phone
COMMENT ON COLUMN phone.phone_id IS 'Identificador único de la tabla';
COMMENT ON COLUMN phone.phone_number IS 'Número de telefono';
COMMENT ON COLUMN phone.phone_city_code IS 'Código de la ciudad';
COMMENT ON COLUMN phone.phone_country_code IS 'Código del país';
COMMENT ON COLUMN phone.users_user_id IS 'Id del usuario al que pertenece el teléfono';

------- FIN creación la tabla phone

------- INICIO creación la tabla parameter
CREATE TABLE parameter (
	parameter_id bigint NOT NULL AUTO_INCREMENT,
	parameter_name varchar NOT NULL,
	parameter_value varchar NOT NULL
);

--Creamos la llave primaria para tabla phone
ALTER TABLE	parameter ADD CONSTRAINT parameter_pk PRIMARY KEY (parameter_id);

--Creamos la llave única para tabla phone
ALTER TABLE	parameter ADD CONSTRAINT parameter_name UNIQUE (parameter_name);

COMMENT ON COLUMN parameter.parameter_id IS 'Identificador único de la tabla';
COMMENT ON COLUMN parameter.parameter_name IS 'Nombre único para identificar el parametro';
COMMENT ON COLUMN parameter.parameter_value IS 'Valor del parametro';

------- FIN creación la tabla parameter