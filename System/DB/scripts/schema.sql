-- -----------------------------------------------------
-- Schema RadiusManager
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `RadiusManager` ;

-- -----------------------------------------------------
-- Schema RadiusManager
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `RadiusManager` ;
USE `RadiusManager` ;


-- -----------------------------------------------------
-- Table `RadiusManager`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RadiusManager`.`Users` ;

CREATE TABLE IF NOT EXISTS `RadiusManager`.`Users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `enabled` TINYINT NULL,
  `first_name` VARCHAR(15) NOT NULL,
  `middle_name` VARCHAR(15) NULL,
  `last_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RadiusManager`.`Authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RadiusManager`.`Authorities` ;

CREATE TABLE IF NOT EXISTS `RadiusManager`.`Authorities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RadiusManager`.`Users_Authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RadiusManager`.`Users_Authorities` ;

CREATE TABLE IF NOT EXISTS `RadiusManager`.`Users_Authorities` (
  `user` INT NOT NULL,
  `authority` INT NOT NULL,
  INDEX `fk_Users_Roles_Users_idx` (`user` ASC),
  INDEX `fk_Users_Roles_Roles_idx` (`authority` ASC),
  CONSTRAINT `fk_Users_Authorities_Users`
    FOREIGN KEY (`user`)
    REFERENCES `RadiusManager`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_Authorities_Authorities`
    FOREIGN KEY (`authority`)
    REFERENCES `RadiusManager`.`Authorities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
 
