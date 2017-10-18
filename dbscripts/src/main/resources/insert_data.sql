INSERT INTO `User`(`firstName`,`lastName`,`username`,`password`)
VALUES ('Elsa', 'Siegel', 'elsasiegel','123');
INSERT INTO `User`(`firstName`,`lastName`,`username`,`password`)
VALUES ('Delinke', 'Siegel', 'desiegel','123');

INSERT INTO `Question` (`question`, `answer_a`, `answer_b`, `answer_c`, `answer_d`, `correct_answer`)
VALUES ('Mennyi 1+1?', '2', '3', '4', '5', '2');
INSERT INTO `Question` (`question`, `answer_a`, `answer_b`, `answer_c`, `answer_d`, `correct_answer`)
VALUES ('Mennyi 1+4?', '2', '3', '4', '5', '5');
INSERT INTO `Question` (`question`, `answer_a`, `answer_b`, `answer_c`, `answer_d`, `correct_answer`)
VALUES ('Piroskat megette a?', 'farkas', 'roka', 'senki', 'nagymami', 'senki');
INSERT INTO `Question` (`question`, `answer_a`, `answer_b`, `answer_c`, `answer_d`, `correct_answer`)
VALUES ('Delinke férje?', 'ügyes', 'okos', 'szép', 'mindegyik', 'mindegyik');

INSERT INTO `Help` (`name`) VALUES ('INCORRECT');
INSERT INTO `Help` (`name`) VALUES ('NEXT_QUESTION');
INSERT INTO `Help` (`name`) VALUES ('HALVING');