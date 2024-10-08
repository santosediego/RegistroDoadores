INSERT INTO tb_user (NAME, PASSWORD, USERNAME) VALUES ('Admin', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'admin');

INSERT INTO tb_doador (nome, cpf , rg, data_nascimento, grupo_sanguineo, peso, genero, estado_civil, celular, data_cadastro)
values ('José Maria', '362.389.160-60', '45.359.598', TIMESTAMP WITH TIME ZONE '1964-03-26T03:00:00.00000Z', 'A+', 87.5, 'M', 'CAS', '(34)98357-9114', NOW()),
('Maria José', '466.747.936-12', '40.360.043', TIMESTAMP WITH TIME ZONE '1942-01-14T03:00:00.00000Z', 'AB-', 57, 'F', 'VIU', '(34)99578-3326', NOW()),
('Alícia Caroline Moraes', '184.794.576-78', '25.662.132-9', TIMESTAMP WITH TIME ZONE '1979-03-06T03:00:00.00000Z', 'A+', 71, 'F', 'VIU', '(34)99292-6504', NOW()),
('Benjamin Levi Ribeiro', '079.074.056-70', '33.325.574-4', TIMESTAMP WITH TIME ZONE '1964-04-22T03:00:00.00000Z', 'O+', 60, 'M', 'CAS', '(34)98490-6086', NOW()),
('Renan Enrico Ruan Teixeira', '030.865.016-69', '26.475.268-5', TIMESTAMP WITH TIME ZONE '1985-05-02T03:00:00.00000Z', 'O-', 70, 'M', 'DIV', '(34)99292-6504', NOW()),
('Noah Lucas Cavalcanti', '262.751.836-40', '21.115.188-9', TIMESTAMP WITH TIME ZONE '1994-09-07T03:00:00.00000Z', 'A+', 52, 'M', 'SOL', '(34)98541-3724', NOW()),
('Clara Porto', '584.280.966-43', '27.201.687-1', TIMESTAMP WITH TIME ZONE '1997-08-09T03:00:00.00000Z', 'A+', 52.1, 'F', 'SOL', '(34)98594-5321', NOW()),
('Daniel Francisco Campos', '486.742.906-63', '26.689.820-8', TIMESTAMP WITH TIME ZONE '1981-03-11T03:00:00.00000Z', 'AB+', 60, 'M', 'CAS', '(34)99220-3269', NOW()),
('Antonella Lima', '581.603.126-01', '28.530.555-4', TIMESTAMP WITH TIME ZONE '1990-10-09T03:00:00.00000Z', 'A+', 58, 'F', 'CAS', '(34)99421-3977', NOW()),
('Jorge Farias', '171.188.716-13', '28.571.976-2', TIMESTAMP WITH TIME ZONE '1980-01-10T03:00:00.00000Z', 'O-', 74, 'M', 'CAS', '(34)99292-6504', NOW()),
('Mário Bruno Pereira', '790.038.526-69', '49.149.488-9', TIMESTAMP WITH TIME ZONE '2004-04-17T03:00:00.00000Z', 'B+', 79, 'M', 'SOL', '(34)99977-2287', NOW()),
('Iago Moura', '761.316.766-66', '35.180.301-4', TIMESTAMP WITH TIME ZONE '1950-04-06T03:00:00.00000Z', 'AB+', 67, 'M', 'CAS', '(34)98892-1080', NOW()),
('Heloise Silvana Drumond', '121.218.786-53', '41.752.868-1', TIMESTAMP WITH TIME ZONE '1980-11-20T03:00:00.00000Z', 'O+', 68, 'F', 'CAS', '(34)98777-7922', NOW()),
('Malu Vanessa Silveira', '312.295.106-13', '38.192.624-2', TIMESTAMP WITH TIME ZONE '2000-05-15T03:00:00.00000Z', 'B-', 50, 'F', 'SOL', '(34)98137-8516', NOW()),
('Vanessa Eloá Souza', '973.342.236-32', '11.554.543-8', TIMESTAMP WITH TIME ZONE '1997-06-30T03:00:00.00000Z', 'A-', 52, 'F', 'CAS', '(34)98548-3482', NOW()),
('Bárbara Heloise da Cruz', '079.715.436-11', '43.682.805-4', TIMESTAMP WITH TIME ZONE '1988-03-19T03:00:00.00000Z', 'A-', 83, 'F', 'CAS', '(34)99889-4786', NOW());

INSERT INTO tb_endereco (logradouro, numero, complemento, bairro, cep, localidade, estado, doador_id)
VALUES ('Rua Rio Grande do Norte', '733', 'n/d', 'Nossa Senhora das Graças', '38402-016', 'Uberlândia', 'MG', 1),
('Rua das Petúnias', '753', 'n/d', 'Cidade Jardim', '38412-112', 'Uberlândia', 'MG', 2),
('Rua Pedro C. Carvalho', '901', NULL, 'Centro', '38550-977', 'Coromandel', 'MG', 3),
('Rua João Pinheiro', '101', NULL, 'Centro', '38550-970', 'Coromandel', 'MG', 4),
('Rua Principal', '948', NULL, 'Centro', '38550-975', 'Coromandel', 'MG', 5),
('Rua Dois', '342', NULL, 'Centro', '38550-976', 'Coromandel', 'MG', 6),
('Rua Pedro C. Carvalho', '536', NULL, 'Centro', '38550-977', 'Coromandel', 'MG', 7),
('Rua João Pinheiro', '761', NULL, 'Centro', '38550-971', 'Coromandel', 'MG', 8),
('Rua João Pinheiro', '607', NULL, 'Centro', '38550-970', 'Coromandel', 'MG', 9),
('Rua João Pinheiro', '499', NULL, 'Centro', '38550-970', 'Coromandel', 'MG', 10),
('Rua Dois', '961', NULL, 'Centro', '38550-976', 'Coromandel', 'MG', 11),
('Rua João Pinheiro', '755', 'Em frente a pizzaria', 'Centro', '38550-970', 'Coromandel', 'MG', 12),
('Rua João Pinheiro', '838', NULL, 'Centro', '38550-970', 'Coromandel', 'MG', 13),
('Rua Pedro C. Carvalho', '431', NULL, 'Centro', '38550-977', 'Coromandel', 'MG', 14),
('Rua Dois', '659', NULL, 'Centro', '38550-976', 'Coromandel', 'MG', 15),
('Rua Principal', '15', NULL, 'Centro', '38500-974', 'Monte Carmelo', 'MG', 16);
