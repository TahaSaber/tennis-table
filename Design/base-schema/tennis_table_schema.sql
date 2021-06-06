use `tennis_table`;

 CREATE TABLE `tennis_table`.`participant_group` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));
  
  
  CREATE TABLE `tennis_table`.`participant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `frist_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `user_name` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `champion` BIT(1) NOT NULL DEFAULT b'0',
  `join_time` DATETIME NULL,
  `modification_time` DATETIME NULL,
  `group_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_participant_1_idx` (`group_id` ASC) VISIBLE,
  CONSTRAINT `fk_participant_1`
    FOREIGN KEY (`group_id`)
    REFERENCES `tennis_table`.`participant_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    CREATE TABLE `tennis_table`.`round_state` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `state` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));
  
  
  
  CREATE TABLE `tennis_table`.`round` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `state` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_round_1_idx` (`state` ASC) VISIBLE,
  CONSTRAINT `fk_round_1`
    FOREIGN KEY (`state`)
    REFERENCES `tennis_table`.`round_state` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
  
  CREATE TABLE `tennis_table`.`round_match` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_player` INT NULL,
  `second_player` INT NULL,
  `first_player_score` INT NULL,
  `second_player_score` INT NULL,
  `launch_time` DATETIME NULL,
  `state` INT NULL,
  `round` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_round_match_1_idx` (`first_player` ASC) VISIBLE,
  INDEX `fk_round_match_2_idx` (`second_player` ASC) VISIBLE,
  INDEX `fk_round_match_3_idx` (`round` ASC) VISIBLE,
  INDEX `fk_round_match_4_idx` (`state` ASC) VISIBLE,
  CONSTRAINT `fk_round_match_1`
    FOREIGN KEY (`first_player`)
    REFERENCES `tennis_table`.`participant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_round_match_2`
    FOREIGN KEY (`second_player`)
    REFERENCES `tennis_table`.`participant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_round_match_3`
    FOREIGN KEY (`round`)
    REFERENCES `tennis_table`.`round` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


 
ALTER TABLE `round_state` AUTO_INCREMENT = 1   
INSERT INTO `tennis_table`.`round_state` (`state`, `description`) VALUES ('Running', 'Leauge round has been started');
INSERT INTO `tennis_table`.`round_state` (`state`, `description`) VALUES ('Ended', 'League round has beed ended');






ALTER TABLE `participant_group` AUTO_INCREMENT = 1
INSERT INTO `participant_group` VALUES (1,'A','A group'),(2,'B','B group'),(3,'C','C group'),(4,'D','D group'),(5,'E','E group'),(6,'F','F group');

ALTER TABLE `round` AUTO_INCREMENT = 1
INSERT INTO `round` VALUES (1,'First-Round',1),(2, 'Second-Round',1),(3,'Third-Round',1);









  
 


