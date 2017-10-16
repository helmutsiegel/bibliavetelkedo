DROP TABLE IF EXISTS `Used_help`;
DROP TABLE IF EXISTS `Help`;
DROP TABLE IF EXISTS `Answer`;
DROP TABLE IF EXISTS `Question`;
DROP TABLE IF EXISTS `Game`;
DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `id`        INT                NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45)        NULL,
  `lastName`  VARCHAR(45)        NULL,
  `username`  VARCHAR(45)        NOT NULL,
  `password`  VARCHAR(100)       NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC)
);

CREATE TABLE `Game` (
  `id`         INT NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(45),
  PRIMARY KEY (`id`),
  `userId`     INT REFERENCES `User`.`id`,
  CONSTRAINT `user_f`
  FOREIGN KEY (`userId`)
  REFERENCES `User` (`id`)
);


CREATE TABLE `Question` (
  `id`             INT         NOT NULL AUTO_INCREMENT,
  `question`       VARCHAR(100),
  `answer_a`       VARCHAR(45),
  `answer_b`       VARCHAR(45),
  `answer_c`       VARCHAR(45),
  `answer_d`       VARCHAR(45),
  `correct_answer` VARCHAR(45),
  PRIMARY KEY (`id`)
);

CREATE TABLE `Answer` (
  `id`             INT         NOT NULL AUTO_INCREMENT,

  `gameId`     INT REFERENCES `Game`.`id`,
  CONSTRAINT `game_f`
  FOREIGN KEY (`gameId`)
  REFERENCES `Game` (`id`),

  `questionId`     INT REFERENCES `Question`.`id`,
  CONSTRAINT `question_f`
  FOREIGN KEY (`questionId`)
  REFERENCES `User` (`id`),

  `answer` VARCHAR(45),
  PRIMARY KEY (`id`)
);

CREATE TABLE `Help` (
  `id`             INT         NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(45),
  PRIMARY KEY (`id`)
);

CREATE TABLE `Used_help` (
  `gameId`     INT NOT NULL,
  `helpId`     INT NOT NULL,
  PRIMARY KEY (gameId,helpId),
  FOREIGN KEY (`helpId`) REFERENCES `Help` (`id`),
  FOREIGN KEY (`gameId`) REFERENCES `Game` (`id`)
);