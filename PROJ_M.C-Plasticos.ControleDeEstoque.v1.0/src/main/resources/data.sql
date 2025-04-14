INSERT INTO Usuario VALUES
(default, false, 12345678, 'Joao Silva',CURRENT_TIMESTAMP, 'Administrador'),
(default, false, 87654321, 'Maria Souza', CURRENT_TIMESTAMP, 'Comprador');

INSERT INTO Produto (nome, tipo, preco, cadastrante, data_cadastro)
VALUES
    ('PP Branca', 'Plástico', 3.50, 'Maria Souza', CURRENT_TIMESTAMP),
    ('PET Cristal', 'Plástico', 2.80, 'Maria Souza', CURRENT_TIMESTAMP),
    ('PEAD Azul', 'Plástico', 3.00, 'Maria Souza', CURRENT_TIMESTAMP),
    ('PVC Rígido', 'Plástico', 2.20, 'Maria Souza', CURRENT_TIMESTAMP),
    ('PP Colorido', 'Plástico', 2.90, 'Maria Souza', CURRENT_TIMESTAMP);

--
--INSERT INTO Entrada (fkCategoria, fkProduto, peso, valorTotal, fkUsuario, fkFornecedor, data)
--VALUES
--    (1, 1, 3.50, 20.0, 1, 1, CURRENT_TIMESTAMP);