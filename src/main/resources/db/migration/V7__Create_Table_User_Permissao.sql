CREATE TABLE IF NOT EXISTS `login_permissao` (
  `id_login` bigint(20) NOT NULL,
  `id_permissao` bigint(20) NOT NULL,
  PRIMARY KEY (`id_login`,`id_permissao`),
  KEY `fk_login_permissao_permissao` (`id_permissao`),
  CONSTRAINT `fk_login_permissao` FOREIGN KEY (`id_login`) REFERENCES `login` (`id`),
  CONSTRAINT `fk_login_permissao_permissao` FOREIGN KEY (`id_permissao`) REFERENCES `permissoes` (`id`)
) ENGINE=InnoDB;
