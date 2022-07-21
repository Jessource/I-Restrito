CREATE TABLE `produtos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `marca` varchar(255) NOT NULL UNIQUE,
  `origem` varchar(255) NOT NULL,
  `adicao` varchar(255) NOT NULL,
  `tipo_produto` varchar(255) NOT NULL,
  `restricao` varchar(2) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)