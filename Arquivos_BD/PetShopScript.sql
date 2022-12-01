-- MySQL Workbench Forward Engineering
-- -----------------------------------------------------
-- Schema Project_PetShop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Project_PetShop
-- -----------------------------------------------------
#DROP SCHEMA IF EXISTS `Project_PetShop`;
CREATE SCHEMA IF NOT EXISTS `Project_PetShop` DEFAULT CHARACTER SET utf8 ;
USE `Project_PetShop` ;

-- -----------------------------------------------------
-- Table `Project_PetShop`.`PetShop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project_PetShop`.`PetShop` (
  `idPetShop` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `City_Name` VARCHAR(45) NOT NULL,
  `Number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPetShop`));

-- -----------------------------------------------------
-- Table `Project_PetShop`.`Animal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project_PetShop`.`Animal` (
  `idAnimal` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Age` INT NOT NULL,
  `Breed` VARCHAR(45) NOT NULL,
  `Color` VARCHAR(45) NOT NULL,
  `Sex` VARCHAR(45) NOT NULL,
  `Animal_idPetShop` INT NOT NULL,
  `Category` VARCHAR(45) NOT NULL,
  `Status` VARCHAR(45) NOT NULL,
  `Weight` INT NOT NULL,
  PRIMARY KEY (`idAnimal`),
  CONSTRAINT `fk_Animal_PetShop`
    FOREIGN KEY (`Animal_idPetShop`)
    REFERENCES `Project_PetShop`.`PetShop` (`idPetShop`)
    ON DELETE cascade
    ON UPDATE cascade);


-- -----------------------------------------------------
-- Table `Project_PetShop`.`OwnerPet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project_PetShop`.`OwnerPet` (
  `OwnerId` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Number` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `Payment` VARCHAR(45) NOT NULL,
  `PetShop_idPetShop` INT NOT NULL,
  PRIMARY KEY (`OwnerId`),
  CONSTRAINT `fk_OwnerPet_PetShop1`
    FOREIGN KEY (`PetShop_idPetShop`)
    REFERENCES `Project_PetShop`.`PetShop` (`idPetShop`)
    ON DELETE cascade
    ON UPDATE cascade);


-- -----------------------------------------------------
-- Table `Project_PetShop`.`OwnerPetShop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project_PetShop`.`OwnerPetShop` (
  `idOwnerPetShop` INT NOT NULL AUTO_INCREMENT,
  `NameOP` VARCHAR(45) NOT NULL,
  `PetShop_idPetShop` INT NOT NULL,
  PRIMARY KEY (`idOwnerPetShop`),
  CONSTRAINT `fk_OwnerPetShop_PetShop1`
    FOREIGN KEY (`PetShop_idPetShop`)
    REFERENCES `Project_PetShop`.`PetShop` (`idPetShop`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `Project_PetShop`.`OwnerPet_has_Animal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project_PetShop`.`OwnerPet_has_Animal` (
  `OwnerPet_OwnerId` INT NOT NULL,
  `Animal_idAnimal` INT NOT NULL,
  PRIMARY KEY (`OwnerPet_OwnerId`, `Animal_idAnimal`),
  CONSTRAINT `fk_OwnerPet_has_Animal_OwnerPet1`
    FOREIGN KEY (`OwnerPet_OwnerId`)
    REFERENCES `Project_PetShop`.`OwnerPet` (`OwnerId`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_OwnerPet_has_Animal_Animal1`
    FOREIGN KEY (`Animal_idAnimal`)
    REFERENCES `Project_PetShop`.`Animal` (`idAnimal`)
    ON DELETE cascade
    ON UPDATE cascade);

select * from `Project_PetShop`.`PetShop`;
select * from `Project_PetShop`.`OwnerPet`;
select * from `Project_PetShop`.`OwnerPetShop`;
select * from `Project_PetShop`.`OwnerPet_has_Animal`;
select * from `Project_PetShop`.`Animal`;
