-- User
INSERT INTO "user"(name, username, password)     --Password: Test
    VALUES ('John Admin PostScriptum', 'john.admin@ps.org', '$2a$10$OgnLX2gAcwR/iqfTJvkvO.t5CG00SPUyEUSC0ibH6SZJJ6zlj.pFu');

INSERT INTO "user"(name, username, password)     --Password: Test
    VALUES ('Ana Guest PostScriptum', 'ana.guest@ps.org', '$2a$10$OgnLX2gAcwR/iqfTJvkvO.t5CG00SPUyEUSC0ibH6SZJJ6zlj.pFu');

INSERT INTO "user"(name, username, password)     --Password: Test
    VALUES ('Mike Operator PostScriptum', 'mike.operator@ps.org', '$2a$10$OgnLX2gAcwR/iqfTJvkvO.t5CG00SPUyEUSC0ibH6SZJJ6zlj.pFu');

INSERT INTO "user"(name, username, password)     --Password: Test
    VALUES ('Custom User PostScriptum', 'custom.user@ps.org', '$2a$10$OgnLX2gAcwR/iqfTJvkvO.t5CG00SPUyEUSC0ibH6SZJJ6zlj.pFu');

-- User Group
INSERT INTO usergroup(name) VALUES ('Root');

INSERT INTO usergroup(name) VALUES ('Operator');
    
-- Roles
INSERT INTO role(name) VALUES ('Guest');

INSERT INTO role(name) VALUES ('Company');

INSERT INTO role(name) VALUES ('Admin');

-- Privileges
INSERT INTO privilege(name) VALUES ('PROFILE_GET');

INSERT INTO privilege(name) VALUES ('COMPANY_SAVE');

INSERT INTO privilege(name) VALUES ('COMPANY_LIST');

INSERT INTO privilege(name) VALUES ('COMPANY_GET');

INSERT INTO privilege(name) VALUES ('COMPANY_DELETE');