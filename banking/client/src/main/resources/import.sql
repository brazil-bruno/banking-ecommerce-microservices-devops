INSERT INTO tb_client (clientID, client_name, email, client_phone, password, created_at) VALUES ('73ff47d5-25e1-4516-9889-adb694bfbf17', 'Maria', 'maria@gmail.com', '+55 98765 9786', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', '2024-06-13 10:29:42.97');
INSERT INTO tb_client (clientID, client_name, email, client_phone, password, created_at) VALUES ('15cf9aff-79b6-43aa-8c61-699f7eb92226', 'Alex', 'alex@gmail.com', '+55 98765 9786', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', '2024-06-13 10:29:42.97');

INSERT INTO tb_role (roleID, authority) VALUES ('0660f752-1d92-403c-90d6-442551388b38', 'ROLE_ADMIN');
INSERT INTO tb_role (roleID, authority) VALUES ('1a378695-d000-4735-b521-fd91f454dc97', 'ROLE_CLIENT');

INSERT INTO tb_client_role (client_id, role_id) VALUES ('73ff47d5-25e1-4516-9889-adb694bfbf17', '0660f752-1d92-403c-90d6-442551388b38');
INSERT INTO tb_client_role (client_id, role_id) VALUES ('15cf9aff-79b6-43aa-8c61-699f7eb92226', '1a378695-d000-4735-b521-fd91f454dc97');