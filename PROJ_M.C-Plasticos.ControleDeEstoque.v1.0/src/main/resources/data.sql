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

INSERT INTO produto (nome, preco, prioridade, tipo_produto, fk_usuario, data_cadastro)
VALUES
('PP Branca', 3.50, 1, 1, 1, CURRENT_TIMESTAMP),
('PET Cristal', 2.80, 1, 1, 1, CURRENT_TIMESTAMP),
('PEAD Azul', 3.00, 1, 1, 1, CURRENT_TIMESTAMP),
('PVC Rígido', 2.20, 1, 1, 1, CURRENT_TIMESTAMP),
('PP Colorido', 2.90, 1, 1, 1, CURRENT_TIMESTAMP);

INSERT INTO transacao (fk_usuario, fk_parceiro_comercial, fk_produto, categoria, tipo_operacao, peso, valor_total, data)
VALUES
(1, 1, 1, 1, 2, 100.5, 350.75, CURRENT_TIMESTAMP),
(2, 2, 3, 2, 1, 200.0, 500.00, CURRENT_TIMESTAMP),
(1, 3, 5, 1, 2, 50.0, 150.25, CURRENT_TIMESTAMP),
(2, 4, 2, 2, 1, 300.0, 900.00, CURRENT_TIMESTAMP),
(1, 5, 4, 1, 2, 120.0, 400.00, CURRENT_TIMESTAMP),
(2, 1, 3, 2, 1, 250.0, 750.00, CURRENT_TIMESTAMP),
(1, 2, 1, 1, 2, 80.0, 280.00, CURRENT_TIMESTAMP),
(2, 3, 5, 2, 1, 180.0, 600.00, CURRENT_TIMESTAMP),
(1, 4, 2, 1, 2, 90.0, 320.00, CURRENT_TIMESTAMP),
(2, 5, 4, 2, 1, 300.0, 1000.00, CURRENT_TIMESTAMP),
(1, 1, 3, 1, 2, 110.0, 370.00, CURRENT_TIMESTAMP),
(2, 2, 5, 2, 1, 220.0, 800.00, CURRENT_TIMESTAMP),
(1, 3, 1, 1, 2, 70.0, 250.00, CURRENT_TIMESTAMP),
(2, 4, 3, 2, 1, 310.0, 1100.00, CURRENT_TIMESTAMP),
(1, 5, 2, 1, 2, 130.0, 450.00, CURRENT_TIMESTAMP),
(2, 1, 4, 2, 1, 260.0, 850.00, CURRENT_TIMESTAMP),
(1, 2, 5, 1, 2, 100.0, 300.00, CURRENT_TIMESTAMP),
(2, 3, 2, 2, 1, 200.0, 700.00, CURRENT_TIMESTAMP),
(1, 4, 3, 1, 2, 150.0, 500.00, CURRENT_TIMESTAMP),
(2, 5, 1, 2, 1, 350.0, 1200.00, CURRENT_TIMESTAMP);
