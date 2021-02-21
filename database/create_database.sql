CREATE TABLE `likes` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `review_id` int NOT NULL,
  `is_like`   tinyint NULL,
  PRIMARY KEY (`user_id`,`review_id`),
  KEY `like_review_id_fk_idx` (`review_id`),
  CONSTRAINT `like_review_id_fk` FOREIGN KEY (`review_id`) REFERENCES `reviews` (`review_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `like_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `movies` (
  `movie_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(120) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `genre` enum('ACTION','DETECTIVE','DRAMA','MUSICAL','ADVENTURE','FANTASTIC','HORROR','COMEDY') DEFAULT NULL,
  `movie_type` enum('FILM','SERIES','ANIME','CARTOON') DEFAULT NULL,
  `age_category` int DEFAULT NULL,
  `short_description` varchar(150) DEFAULT NULL,
  `description` mediumtext,
  `youtubeTrailer` varchar(100) DEFAULT NULL,
  `image_path` varchar(300) DEFAULT NULL,
  `status` enum('ACTIVE','BLOCKED') DEFAULT NULL,
  PRIMARY KEY (`movie_id`),
  UNIQUE KEY `movie_id_UNIQUE` (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `reviews` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `movie_id` int DEFAULT NULL,
  `text` mediumtext,
  `status` enum('ACTIVE','BLOCKED') DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `review_user_id_fk_idx` (`user_id`),
  KEY `review_movie_id_fk_idx` (`movie_id`),
  CONSTRAINT `review_movie_id_fk` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `review_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `scores` (
  `user_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `score` tinyint DEFAULT NULL,
  PRIMARY KEY (`movie_id`,`user_id`),
  KEY `movie_id_index` (`movie_id`) /*!80000 INVISIBLE */,
  KEY `score_user_id_fk_idx` (`user_id`),
  CONSTRAINT `score_movie_id_fk` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `score_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `role` enum('GUEST','SPECTATOR','REVIEWER','ADMIN') DEFAULT NULL,
  `avatar_path` varchar(300) DEFAULT NULL,
  `email_confirmed` tinyint DEFAULT NULL,
  `user_hash` varchar(60) DEFAULT NULL,
  `first_name` varchar(60) DEFAULT NULL,
  `second_name` varchar(60) DEFAULT NULL,
  `status` enum('ACTIVE','BLOCKED') DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
