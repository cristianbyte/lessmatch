-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: localhost    Database: lessMatch
-- ------------------------------------------------------
-- Server version	8.0.41-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pairings`
--

DROP TABLE IF EXISTS `pairings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pairings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `creator_lines` varbinary(255) DEFAULT NULL,
  `paired_lines` varbinary(255) DEFAULT NULL,
  `pairing_code` varchar(6) NOT NULL,
  `creator_user_id` varchar(255) NOT NULL,
  `paired_user_id` varchar(255) DEFAULT NULL,
  `song_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fuj890a78u6re3xsjrou9evdm` (`pairing_code`),
  KEY `idx_creator_user` (`creator_user_id`),
  KEY `idx_paired_user` (`paired_user_id`),
  KEY `idx_pairing_code` (`pairing_code`),
  KEY `FKjexptr00jg3pb57fkwxf7eru6` (`song_id`),
  CONSTRAINT `FK8yqnskglrostqvwg3lwdexti2` FOREIGN KEY (`paired_user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKjexptr00jg3pb57fkwxf7eru6` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`),
  CONSTRAINT `FKng3nlxgprrcehtg7p7ayu3s14` FOREIGN KEY (`creator_user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pairings`
--

LOCK TABLES `pairings` WRITE;
/*!40000 ALTER TABLE `pairings` DISABLE KEYS */;
INSERT INTO `pairings` VALUES (1,'2025-02-27 22:45:55.111499',NULL,NULL,'481AC2','671db6b2-c323-4d49-97b8-d41d46836259','013631b1-5c82-4824-99e3-514adc5e687f',3);
/*!40000 ALTER TABLE `pairings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songs`
--

DROP TABLE IF EXISTS `songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `songs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `album_image` varchar(255) NOT NULL,
  `artist` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `verse_count` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs`
--

LOCK TABLES `songs` WRITE;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
INSERT INTO `songs` VALUES (3,'https://images.com/amarillo','J Balvin','Amarillo',72);
/*!40000 ALTER TABLE `songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `icon` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('013631b1-5c82-4824-99e3-514adc5e687f','spider.png','JackJack'),('671db6b2-c323-4d49-97b8-d41d46836259','pikachu.png','Sam123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-27 22:56:34
