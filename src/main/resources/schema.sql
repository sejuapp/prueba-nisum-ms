-- Creamos la tabla users
CREATE TABLE users (
	user_id uuid NOT NULL,
	user_name varchar NOT NULL,
	user_email varchar NOT NULL,
	user_password varchar NOT NULL,
	user_created timestamp NOT NULL,
	user_modified timestamp NOT NULL,
	user_last_login timestamp NOT NULL,
	user_token varchar NOT NULL,
	user_active boolean NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (user_id),
	CONSTRAINT users_email UNIQUE (user_email)
);

COMMENT ON COLUMN users.user_id IS 'Identificador único de la tabla generado UUID';
COMMENT ON COLUMN users.user_name IS 'Nombre del usuario';
COMMENT ON COLUMN users.user_email IS 'Email del usuario, debe ser único';
COMMENT ON COLUMN users.user_password IS 'Contraseña del usuario';
COMMENT ON COLUMN users.user_created IS 'Fecha de creación del usuario';
COMMENT ON COLUMN users.user_modified IS 'Fecha de modificación';
COMMENT ON COLUMN users.user_last_login IS 'Fecha del ultimo login';
COMMENT ON COLUMN users.user_token IS 'Token del usuario JWT';
COMMENT ON COLUMN users.user_active IS 'Indica si el usuario esta activo en el sistema';


-- Creamos la tabla phone
CREATE TABLE phone (
	phone_id bigint NOT NULL AUTO_INCREMENT,
	phone_number varchar NOT NULL,
	phone_city_code varchar NOT NULL,
	phone_country_code varchar NOT NULL,
	users_user_id varchar NOT NULL,
	CONSTRAINT phone_pk PRIMARY KEY (phone_id),
	CONSTRAINT phone_fk FOREIGN KEY (users_user_id) REFERENCES users(user_id)
);

COMMENT ON COLUMN phone.phone_id IS 'Identificador único de la tabla';
COMMENT ON COLUMN phone.phone_number IS 'Número de telefono';
COMMENT ON COLUMN phone.phone_city_code IS 'Código de la ciudad';
COMMENT ON COLUMN phone.phone_country_code IS 'Código del país';
COMMENT ON COLUMN phone.users_user_id IS 'Id del usuario al que pertenece el teléfono';


-- Creamos la tabla parameter
CREATE TABLE parameter (
	parameter_id bigint NOT NULL AUTO_INCREMENT,
	parameter_name varchar NOT NULL,
	parameter_value varchar NOT NULL,
	CONSTRAINT parameter_pk PRIMARY KEY (parameter_id),
	CONSTRAINT parameter_name UNIQUE (parameter_name)
);

COMMENT ON COLUMN parameter.parameter_id IS 'Identificador único de la tabla';
COMMENT ON COLUMN parameter.parameter_name IS 'Nombre único para identificar el parametro';
COMMENT ON COLUMN parameter.parameter_value IS 'Valor del parametro';