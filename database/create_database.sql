-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema movie_star_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema movie_star_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `movie_star_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `movie_star_db` ;

-- -----------------------------------------------------
-- Table `movie_star_db`.`movies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_star_db`.`movies` (
  `movie_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(120) NULL DEFAULT NULL,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `year` INT NULL DEFAULT NULL,
  `genre` ENUM('ACTION', 'DETECTIVE', 'DRAMA', 'MUSICAL', 'ADVENTURE', 'FANTASTIC', 'HORROR', 'COMEDY') NULL DEFAULT NULL,
  `movie_type` ENUM('FILM', 'SERIES', 'ANIME', 'CARTOON') NULL DEFAULT NULL,
  `age_category` INT NULL DEFAULT NULL,
  `description` MEDIUMTEXT NULL DEFAULT NULL,
  `youtubeTrailer` VARCHAR(100) NULL DEFAULT NULL,
  `image_path` VARCHAR(300) NULL DEFAULT NULL,
  `status` ENUM('ACTIVE', 'BLOCKED') NULL DEFAULT NULL,
  PRIMARY KEY (`movie_id`),
  UNIQUE INDEX `movie_id_UNIQUE` (`movie_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `movie_star_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_star_db`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `password_hash` VARCHAR(60) NULL DEFAULT NULL,
  `role` ENUM('GUEST', 'SPECTATOR', 'REVIEWER', 'ADMIN') NULL DEFAULT NULL,
  `avatar_path` VARCHAR(300) NULL DEFAULT NULL,
  `email_confirmed` TINYINT NULL DEFAULT NULL,
  `user_hash` VARCHAR(60) NULL DEFAULT NULL,
  `first_name` VARCHAR(60) NULL DEFAULT NULL,
  `second_name` VARCHAR(60) NULL DEFAULT NULL,
  `status` ENUM('ACTIVE', 'BLOCKED') NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `id_UNIQUE` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `movie_star_db`.`reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_star_db`.`reviews` (
  `review_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  `movie_id` INT NULL DEFAULT NULL,
  `text` MEDIUMTEXT NULL DEFAULT NULL,
  `status` ENUM('ACTIVE', 'BLOCKED') NULL DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `review_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `review_movie_id_fk_idx` (`movie_id` ASC) VISIBLE,
  CONSTRAINT `review_movie_id_fk`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie_star_db`.`movies` (`movie_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `review_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `movie_star_db`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `movie_star_db`.`likes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_star_db`.`likes` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `review_id` INT NOT NULL,
  `is_like` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `review_id`),
  INDEX `like_review_id_fk_idx` (`review_id` ASC) VISIBLE,
  CONSTRAINT `like_review_id_fk`
    FOREIGN KEY (`review_id`)
    REFERENCES `movie_star_db`.`reviews` (`review_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `like_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `movie_star_db`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `movie_star_db`.`ratings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_star_db`.`ratings` (
  `user_id` INT NOT NULL,
  `movie_id` INT NOT NULL,
  `value` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`movie_id`, `user_id`),
  INDEX `movie_id_index` (`movie_id` ASC) INVISIBLE,
  INDEX `score_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `score_movie_id_fk`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie_star_db`.`movies` (`movie_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `score_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `movie_star_db`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
