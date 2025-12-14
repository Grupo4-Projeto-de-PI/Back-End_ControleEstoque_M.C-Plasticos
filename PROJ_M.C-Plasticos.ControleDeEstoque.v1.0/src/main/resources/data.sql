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

INSERT INTO produto (nome, preco, prioridade, tipo_produto, fk_usuario, data_cadastro, foto_produto) VALUES

('Papelão', 2.50, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Papelão'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/papelao.webp')),


('Ferro', 2.50, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/ferro.jpg')),


('Plástico', 3.00, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/plastico.webp')),


('Aluminio', 4.00, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/aluminio.jpg')),


('Latinha', 3.20, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/latinha.webp')),


('Bloco', 1.80, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Madeira'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/bloco.jpeg')),


('Metal', 2.70, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/metal.jpg')),


('Cobre CABO', 5.50, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/cobre-cabo.jpg')),


('Cobre Misto', 4.80, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/cobre-misto.jpg')),


('Fio', 3.10, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Metal'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fios.jpg')),


('Garimpo', 6.00, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Garimpo'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/garimpo.jpg')),


('Bomba', 7.50, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Equipamento'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/bomba.webp')),


('Litro', 2.00, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Liquido'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/litro.jpg')),


('Caco', 1.20, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Vidro'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/caco.webp')),


('Garrafão', 2.40, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Recipiente'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/garrafao.jpg')),


('Motor', 8.00, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Equipamento'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/motor.webp')),


('Arquivo', 1.00, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Papel'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/arquivo.jpg')),


('Jornal', 0.90, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Papel'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/jornal.jpeg')),


('PVC', 3.30, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/pvc.jpg')),


('ABS/PS', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/ABS-PS.jpeg')),


('PET', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/pet.webp')),


('PET LARANJA', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/pet-laranja.jpeg')),


('PET Laminada', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/pet-laminada.jpeg')),


('PPC', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/PPC.png')),


('PPB', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/ppb.jpg')),


('PPP', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/ppp.jpg')),


('PADB', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/padb.jpeg')),


('PADC', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/padc.jpeg')),


('APARA Limpa Branca', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/apara-limpa-branca.jpg')),


('Apara Colorida Limpa', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/apara-colorida-limpa.webp')),


('Apara SujaBranca', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/apara-limpa-branca.jpg')),


('Apara Suja Colorida', 3.60, 1, (SELECT id FROM tipo_produto WHERE tipo = 'Plástico'), 1, CURRENT_TIMESTAMP,

 LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/apara-su
ja-colorida.png'));




