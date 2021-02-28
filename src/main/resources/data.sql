CREATE TABLE users (
  id_usuario bigint(20) NOT NULL AUTO_INCREMENT,
  enabled bit(1) DEFAULT TRUE,
  password varchar(255) DEFAULT NULL,
  username varchar(255) DEFAULT NULL,
  PRIMARY KEY (id_usuario)
);

INSERT INTO users (id_usuario, enabled, password, username) VALUES
	(1, TRUE, '$2a$10$NCec2r/ERfhH3f7oPPW8ueh6xdAkI6vQsvdqszblw95D3bbwHKA5.', 'root');


CREATE TABLE authorities (
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  id_usuario bigint(20) NOT NULL AUTO_INCREMENT,
  UNIQUE KEY ix_auth_username (id_usuario,authority),
  CONSTRAINT fk_authorities_users FOREIGN KEY (id_usuario) REFERENCES users (id_usuario)
);


INSERT INTO authorities (username, authority, id_usuario) VALUES
	('root', 'ROLE_USER', 1);



