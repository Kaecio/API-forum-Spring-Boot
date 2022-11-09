INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@gmail.com', '123456')

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação')
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end')

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao inicializar o projeto', '2022-10-31 20:10:000','NAO_RESPONDIDO', 1, 1 )
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida','Tag <div> está em vermelho', '2022-05-10 08:00:000','NAO_RESPONDIDO', 1, 1)
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Projeto nao inicializa como o comando X','2022-01-29 21:00:000','NAO_RESPONDIDO', 1, 2)