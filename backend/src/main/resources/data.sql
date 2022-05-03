INSERT INTO tb_doador (nome, cpf , rg, data_nascimento, grupo_sanguineo, peso, genero, estado_civil, celular, telefone, data_cadastro)
values ('José Maria', '362.389.160-60', '45.359.598', TIMESTAMP WITH TIME ZONE '1964-03-26T03:00:00.00000Z', 'A+', 87.5, 'M', 'CAS', '(34)98357-9114', '(34)3944-9251', NOW()),
('Maria José', '466.747.936-12', '40.360.043', TIMESTAMP WITH TIME ZONE '1942-01-14T03:00:00.00000Z', 'AB-', 57, 'F', 'VIU', '(34)99578-3326', '(34)2609-7261', NOW());

INSERT INTO tb_endereco (logradouro, numero, complemento, bairro, cep, localidade, estado, doador_id)
VALUES ('Rua Rio Grande do Norte', '733', 'n/d', 'Nossa Senhora das Graças', '38402-016', 'Uberlândia', 'MG', 1),
('Rua das Petúnias', '753', 'n/d', 'Cidade Jardim', '38412-112', 'Uberlândia', 'MG', 2);
