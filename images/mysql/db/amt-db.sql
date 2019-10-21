-- MySQL Script generated by MySQL Workbench
-- Wed Oct 16 09:47:47 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema amt-db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `amt-db` ;

-- -----------------------------------------------------
-- Schema amt-db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `amt-db` DEFAULT CHARACTER SET utf8 ;
USE `amt-db` ;

-- -----------------------------------------------------
-- Table `amt-db`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amt-db`.`User` ;

CREATE TABLE IF NOT EXISTS `amt-db`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `fullname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(255) NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amt-db`.`City`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amt-db`.`City` ;

CREATE TABLE IF NOT EXISTS `amt-db`.`City` (
  `idCity` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  PRIMARY KEY (`idCity`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amt-db`.`Trip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amt-db`.`Trip` ;

CREATE TABLE IF NOT EXISTS `amt-db`.`Trip` (
  `User_idUser` INT NOT NULL,
  `City_idCity` INT NOT NULL,
  `visited` TINYINT NULL,
  `date` DATE NULL,
  PRIMARY KEY (`User_idUser`, `City_idCity`),
  INDEX `fk_User_has_City_City1_idx` (`City_idCity` ASC) VISIBLE,
  INDEX `fk_User_has_City_User_idx` (`User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_City_User`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `amt-db`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_City_City1`
    FOREIGN KEY (`City_idCity`)
    REFERENCES `amt-db`.`City` (`idCity`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
