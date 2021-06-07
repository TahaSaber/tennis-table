-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: tennis_table
-- ------------------------------------------------------
-- Server version	8.0.25-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `frist_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `champion` bit(1) NOT NULL DEFAULT b'0',
  `join_time` datetime DEFAULT NULL,
  `modification_time` datetime DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_participent_1_idx` (`group_id`),
  CONSTRAINT `fk_participent_1` FOREIGN KEY (`group_id`) REFERENCES `participant_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participant`
--

LOCK TABLES `participant` WRITE;
/*!40000 ALTER TABLE `participant` DISABLE KEYS */;
INSERT INTO `participant` VALUES (7,'Ali','Adel','amr_adel','amr@gmail.com',_binary '\0','2021-06-05 01:42:17','2021-06-06 05:06:10',3),(8,'Ahmed','Yasser','ahmed_yasser','ahmed_yasser@gmail.com',_binary '','2021-06-05 04:41:06','2021-06-06 05:06:10',2),(9,'Ahmed','amin','ahmed_amin','ahmedAmin@gmail.com',_binary '\0','2021-06-05 04:41:52','2021-06-06 05:06:10',1),(10,'taha','saber','taha_saber','taha@gmail.com',_binary '\0','2021-06-05 04:42:53','2021-06-06 05:06:10',4),(13,'Mohamed','saber','mohamed_saber','mohamed_saber@gmail.com',_binary '\0','2021-06-05 11:53:41','2021-06-06 05:06:10',2),(14,'ameer','ali','amir_ali','amir_ali@gmail.com',_binary '\0','2021-06-05 15:35:09','2021-06-06 05:06:10',4),(15,'alaa','samir','alaa_samir','alaa_samir@gmail.com',_binary '\0','2021-06-05 22:58:00','2021-06-06 05:06:10',3),(16,'mostafa','samir','m_samir','m_samir@gmail.com',_binary '\0','2021-06-05 23:03:22','2021-06-06 05:06:10',1);
/*!40000 ALTER TABLE `participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participant_group`
--

DROP TABLE IF EXISTS `participant_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participant_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participant_group`
--

LOCK TABLES `participant_group` WRITE;
/*!40000 ALTER TABLE `participant_group` DISABLE KEYS */;
INSERT INTO `participant_group` VALUES (1,'A','A group'),(2,'B','B group'),(3,'C','C group'),(4,'D','D group'),(5,'E','E group'),(6,'F','F group');
/*!40000 ALTER TABLE `participant_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `round`
--

DROP TABLE IF EXISTS `round`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `round` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `state` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_round_1_idx` (`state`),
  CONSTRAINT `fk_round_1` FOREIGN KEY (`state`) REFERENCES `round_state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `round`
--

LOCK TABLES `round` WRITE;
/*!40000 ALTER TABLE `round` DISABLE KEYS */;
INSERT INTO `round` VALUES (1,'First-Round',1),(2,'Second-Round',1),(3,'Third-Round',1);
/*!40000 ALTER TABLE `round` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `round_match`
--

DROP TABLE IF EXISTS `round_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `round_match` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_player` int DEFAULT NULL,
  `second_player` int DEFAULT NULL,
  `first_player_score` int DEFAULT NULL,
  `second_player_score` int DEFAULT NULL,
  `launch_time` datetime DEFAULT NULL,
  `round` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_round_match_1_idx` (`first_player`),
  KEY `fk_round_match_2_idx` (`second_player`),
  KEY `fk_round_match_3_idx` (`round`),
  CONSTRAINT `fk_round_match_1` FOREIGN KEY (`first_player`) REFERENCES `participant` (`id`),
  CONSTRAINT `fk_round_match_2` FOREIGN KEY (`second_player`) REFERENCES `participant` (`id`),
  CONSTRAINT `fk_round_match_3` FOREIGN KEY (`round`) REFERENCES `round` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `round_match`
--

LOCK TABLES `round_match` WRITE;
/*!40000 ALTER TABLE `round_match` DISABLE KEYS */;
/*!40000 ALTER TABLE `round_match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `round_state`
--

DROP TABLE IF EXISTS `round_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `round_state` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `round_state`
--

LOCK TABLES `round_state` WRITE;
/*!40000 ALTER TABLE `round_state` DISABLE KEYS */;
INSERT INTO `round_state` VALUES (1,'Running','Leauge round has been started'),(2,'Ended','League round has beed ended');
/*!40000 ALTER TABLE `round_state` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-06  5:08:47
