INSERT INTO accounts (account_number, email, first_name, last_name, password) VALUES ('6333885409222339','admin@fake.com', 'admin', 'one', '$2a$10$57VDThG1aa./iRozB98QeuPtzADsVY0vfqcKehoDZ9USKe4pu3b22'); 
INSERT INTO accounts (account_number, email, first_name, last_name, password) VALUES ('9407693045616462','user@fake.com', 'user', 'one', '$2a$10$57VDThG1aa./iRozB98QeuPtzADsVY0vfqcKehoDZ9USKe4pu3b22');

INSERT INTO users (acc_number, email, password, role) VALUES ('6333885409222339','admin@fake.com','$2a$10$57VDThG1aa./iRozB98QeuPtzADsVY0vfqcKehoDZ9USKe4pu3b22', 'ADMIN');
INSERT INTO users (acc_number, email, password, role) VALUES ('9407693045616462','user@fake.com','$2a$10$57VDThG1aa./iRozB98QeuPtzADsVY0vfqcKehoDZ9USKe4pu3b22', 'USER'); 