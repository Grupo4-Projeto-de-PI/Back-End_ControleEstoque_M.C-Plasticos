INSERT INTO tipo_usuario (id, tipo) VALUES
(default, 'Administrador'),
(default, 'Vendedor'),
(default, 'Estoquista');

INSERT INTO tipo_comercial (id, tipo) VALUES
(default, 'PF'),
(default, 'PJ'),
(default, 'PFJ');

INSERT INTO papel_comercial (id, tipo) VALUES
(default, 'CL'),
(default, 'FN'),
(default, 'CLFN');

INSERT INTO tipo_produto (id, tipo) VALUES
(default, 'Plástico'),
(default, 'Metal'),
(default, 'Vidro'),
(default, 'Papel'),
(default, 'Madeira');


INSERT INTO usuario (
codigo_funcionario, nome, senha, data_criacao, primeiro_acesso, tipo_usuario, ativo, online
) VALUES
(default, 'Joao Silva', 12345678, CURRENT_TIMESTAMP, false, 1, true, false),
(default, 'Maria Souza', 87654321, CURRENT_TIMESTAMP, false, 2, true, false);

INSERT INTO ator_comercial (id, nome, telefone, tipo_comercial, fk_papel_comercial) VALUES
(default, 'Empresa ABC LTDA', '11987654321', 2, 2), -- Empresa (PJ) com papel FN
(default, 'João da Silva', '11999998888', 1, 1),-- Cliente físico (PF) com papel CL
(default, 'Empresa XYZ ME', '11345678901', 2, 3), -- Empresa (PJ) com papel CLFN
(default, 'Maria Oliveira', '11988887777', 1, 1), -- Cliente físico (PF) com papel CL
(default, 'Comércio Zeta', '1144445555', 3, 2); -- Empresa (PFJ) com papel FN

INSERT INTO produto (nome, tipo_produto, preco_medio, fk_usuario, data_cadastro)
VALUES
('PP Branca', 1, 3.50, 1, CURRENT_TIMESTAMP),
('PET Cristal', 1, 2.80, 1, CURRENT_TIMESTAMP),
('PEAD Azul', 1, 3.00, 1, CURRENT_TIMESTAMP),
('PVC Rígido', 1, 2.20, 1, CURRENT_TIMESTAMP),
('PP Colorido', 1, 2.90, 1, CURRENT_TIMESTAMP);
