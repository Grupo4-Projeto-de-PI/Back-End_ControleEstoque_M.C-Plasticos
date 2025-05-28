INSERT INTO tipo_produto (id, tipo) VALUES
(default, 'Plástico'),
(default, 'Metal'),
(default, 'Vidro'),
(default, 'Papel'),
(default, 'Madeira');

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

INSERT INTO produto (nome, tipo_produto, preco, fk_usuario, data_cadastro)
VALUES
('PP Branca', 1, 3.50, 1, CURRENT_TIMESTAMP),
('PET Cristal', 1, 2.80, 1, CURRENT_TIMESTAMP),
('PEAD Azul', 1, 3.00, 1, CURRENT_TIMESTAMP),
('PVC Rígido', 1, 2.20, 1, CURRENT_TIMESTAMP),
('PP Colorido', 1, 2.90, 1, CURRENT_TIMESTAMP);
