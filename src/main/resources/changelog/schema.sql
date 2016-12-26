CREATE TABLE privilege (
	id          SERIAL  PRIMARY KEY,
	name        VARCHAR(100) NOT NULL UNIQUE
);

ALTER TABLE privilege OWNER TO postgres;

CREATE TABLE role (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE
);

ALTER TABLE role OWNER TO postgres;

CREATE TABLE role_priv (
	role_id      INT NOT NULL REFERENCES role (id),
	privilege_id INT NOT NULL REFERENCES privilege (id),
	PRIMARY KEY (role_id, privilege_id)
);

ALTER TABLE role_priv OWNER TO postgres;

CREATE TABLE "user" (
    id               BIGSERIAL    PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    username         VARCHAR(200) NOT NULL UNIQUE,
    password         VARCHAR(200) NOT NULL,
    refresh_token    TEXT         NULL
);

ALTER TABLE "user" OWNER TO postgres;

CREATE TABLE usergroup (
    id          SERIAL       PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE
);

ALTER TABLE usergroup OWNER TO postgres;

CREATE TABLE user_usergrp (
	user_id    BIGINT NOT NULL REFERENCES "user" (id),
	usergrp_id INT    NOT NULL REFERENCES usergroup (id),
	PRIMARY KEY (user_id, usergrp_id)
);

ALTER TABLE user_usergrp OWNER TO postgres;

CREATE TABLE user_role (
	user_id BIGINT NOT NULL REFERENCES "user" (id),
	role_id INT    NOT NULL REFERENCES role (id),
	PRIMARY KEY (user_id, role_id)
);

ALTER TABLE user_role OWNER TO postgres;

CREATE TABLE usergrp_role (
	usergrp_id INT NOT NULL REFERENCES usergroup (id),
	role_id    INT NOT NULL REFERENCES role (id),
	PRIMARY KEY (usergrp_id, role_id)
);

ALTER TABLE usergrp_role OWNER TO postgres;
