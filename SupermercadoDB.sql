-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema supermercado
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema supermercado
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `supermercado` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `supermercado` ;

-- -----------------------------------------------------
-- Table `supermercado`.`Rubro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supermercado`.`Rubro` (
  `id` INT(4) NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `supermercado`.`articulos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supermercado`.`articulos` (
  `id` INT(4) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(25) NOT NULL,
  `id_rubro` INT(4) NOT NULL,
  `stock` INT(4) NOT NULL,
  `precio` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idRubro` (`id_rubro` ASC) ,
  CONSTRAINT `id_Rubro`
    FOREIGN KEY (`id_rubro`)
    REFERENCES `supermercado`.`Rubro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `supermercado`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supermercado`.`usuarios` (
  `id` INT(8) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(25) NOT NULL,
  `apellido` VARCHAR(25) NOT NULL,
  `dni` INT(8) NOT NULL,
  `domicilio` VARCHAR(25) NOT NULL,
  `correo` VARCHAR(25) NOT NULL,
  `pass` INT(4) NOT NULL,
  `es_frecuente` TINYINT(1) NOT NULL,
  `es_admin` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `supermercado`.`formadepago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supermercado`.`formadepago` (
  `id` INT(2) NOT NULL,
  `descripcion` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `supermercado`.`carrito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supermercado`.`carrito` (
  `id` INT(4) NOT NULL AUTO_INCREMENT,
  `id_usuairo` INT(8) NOT NULL,
  `fecha` DATE NOT NULL,
  `id_articulos` INT(4) NOT NULL,
  `cantidad` INT(2) NOT NULL,
  `subtotal` DOUBLE NOT NULL,
  `id_fp` INT(2) NOT NULL,
  `estado` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `FK_cliente` USING BTREE (`id_usuairo`),
  INDEX `FK_Articulos` (`id_articulos` ASC),
  INDEX `FK_FPago` USING BTREE (`id_fp`),
  CONSTRAINT `id_articulo`
    FOREIGN KEY (`id_articulos`)
    REFERENCES `supermercado`.`articulos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_clientes`
    FOREIGN KEY (`id_usuairo`)
    REFERENCES `supermercado`.`usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_formadepago`
    FOREIGN KEY (`id_fp`)
    REFERENCES `supermercado`.`formadepago` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
