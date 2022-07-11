CREATE TABLE `tb_usuarios` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `data_nascimento_usuario` date COLLATE utf8_swedish_ci DEFAULT NULL, 
  `email_usuario` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `nome_usuario` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `sobrenome_usuario` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `uf_usuario` varchar(2) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
)