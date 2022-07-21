CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `sobrenome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL UNIQUE,
  `senha` varchar(255) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `data_nascimento` date NOT NULL,
  `perfil` varchar(11) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)