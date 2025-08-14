INSERT INTO tipo_produto (id, tipo) VALUES
(default, 'Plástico'),
(default, 'Metal'),
(default, 'Vidro'),
(default, 'Papel'),
(default, 'Madeira'),
(default, 'Papelão'),
(default, 'Equipamento'),
(default, 'Recipiente'),
(default, 'Garimpo'),
(default, 'Liquido');

INSERT INTO usuario (
codigo_funcionario, nome, senha, data_criacao, primeiro_acesso, tipo_usuario, ativo, online
) VALUES
(default, 'Joao Silva', 12345678, CURRENT_TIMESTAMP, false, 0, true, false), -- Admin
(default, 'Maria Souza', 87654321, CURRENT_TIMESTAMP, false, 2, true, false); -- Vendedor

INSERT INTO parceiro_comercial (id, nome, telefone, tipo_comercial, papel_comercial) VALUES
(default, 'Empresa ABC LTDA', '11987654321', 1, 1), -- PJ (1) com papel FN (1)
(default, 'João da Silva', '11999998888', 0, 0), -- PF (0) com papel CL (0)
(default, 'Empresa XYZ ME', '11345678901', 1, 2), -- PJ (1) com papel CLFN (2)
(default, 'Maria Oliveira', '11988887777', 0, 0), -- PF (0) com papel CL (0)
(default, 'Comércio Zeta', '1144445555', 2, 1); -- PFJ (2) com papel FN (1)

INSERT INTO produto (nome, preco, prioridade, tipo_produto, fk_usuario, data_cadastro) VALUES
('Papelão', 2.50, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Papelão'), 1, CURRENT_TIMESTAMP),
('Ferro', 2.50, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP),
('Plástico', 3.00, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP),
('Aluminio', 4.00, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP),
('Latinha', 3.20, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP),
('Bloco', 1.80, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Madeira'), 1, CURRENT_TIMESTAMP),
('Metal', 2.70, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP),
('Cobre CABO', 5.50, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP),
('Cobre Misto', 4.80, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP),
('Fio', 3.10, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP),
('Garimpo', 6.00, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Garimpo'), 1, CURRENT_TIMESTAMP),
('Bomba', 7.50, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Equipamento'), 1, CURRENT_TIMESTAMP),
('Litro', 2.00, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Liquido'), 1, CURRENT_TIMESTAMP),
('Caco', 1.20, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Vidro'), 1, CURRENT_TIMESTAMP),
('Garrafão', 2.40, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Recipiente'), 1, CURRENT_TIMESTAMP),
('Motor', 8.00, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Equipamento'), 1, CURRENT_TIMESTAMP),
('Arquivo', 1.00, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Papel'), 1, CURRENT_TIMESTAMP),
('Jornal', 0.90, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Papel'), 1, CURRENT_TIMESTAMP),
('PVC', 3.30, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP),
('ABS/PS', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP);

