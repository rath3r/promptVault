-- MySQL dump 10.13  Distrib 8.0.42, for Linux (x86_64)
--
-- Host: localhost    Database: promptVault
-- ------------------------------------------------------
-- Server version	8.0.42-0ubuntu0.20.04.1

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Cybersecurity'),(2,'Coding'),(52,'Research'),(53,'Legal'),(54,'HR');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories_seq`
--

DROP TABLE IF EXISTS `categories_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories_seq`
--

LOCK TABLES `categories_seq` WRITE;
/*!40000 ALTER TABLE `categories_seq` DISABLE KEYS */;
INSERT INTO `categories_seq` VALUES (151);
/*!40000 ALTER TABLE `categories_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flagged_keywords`
--

DROP TABLE IF EXISTS `flagged_keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flagged_keywords` (
  `id` bigint NOT NULL,
  `keyword` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flagged_keywords`
--

LOCK TABLES `flagged_keywords` WRITE;
/*!40000 ALTER TABLE `flagged_keywords` DISABLE KEYS */;
INSERT INTO `flagged_keywords` VALUES (1,'password'),(2,'API key'),(3,'secret'),(4,'credit card'),(5,'private key'),(6,'confidential'),(7,'medical record'),(8,'student number');
/*!40000 ALTER TABLE `flagged_keywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flagged_keywords_seq`
--

DROP TABLE IF EXISTS `flagged_keywords_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flagged_keywords_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flagged_keywords_seq`
--

LOCK TABLES `flagged_keywords_seq` WRITE;
/*!40000 ALTER TABLE `flagged_keywords_seq` DISABLE KEYS */;
INSERT INTO `flagged_keywords_seq` VALUES (101);
/*!40000 ALTER TABLE `flagged_keywords_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prompt_submissions`
--

DROP TABLE IF EXISTS `prompt_submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prompt_submissions` (
  `id` bigint NOT NULL,
  `response_text` text,
  `submitted_at` datetime(6) DEFAULT NULL,
  `prompt_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5qj2u7aq9n56n7ydjblygvwxr` (`prompt_id`),
  KEY `FKsd97bcii8rm2sl1gxwfkselvk` (`user_id`),
  CONSTRAINT `FK5qj2u7aq9n56n7ydjblygvwxr` FOREIGN KEY (`prompt_id`) REFERENCES `prompts` (`id`),
  CONSTRAINT `FKsd97bcii8rm2sl1gxwfkselvk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prompt_submissions`
--

LOCK TABLES `prompt_submissions` WRITE;
/*!40000 ALTER TABLE `prompt_submissions` DISABLE KEYS */;
INSERT INTO `prompt_submissions` VALUES (1,'This is a simulated AI response.','2026-06-21 14:14:41.878556',52,2),(2,'This is a simulated AI response.','2026-06-21 14:23:49.699216',2,3),(52,'This is a simulated AI response.','2026-06-21 14:55:07.709854',102,2),(53,'This is a simulated AI response.','2026-06-21 15:00:48.881836',103,2),(102,'This is a simulated AI response.','2026-06-21 17:53:05.985568',2,3);
/*!40000 ALTER TABLE `prompt_submissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prompt_submissions_seq`
--

DROP TABLE IF EXISTS `prompt_submissions_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prompt_submissions_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prompt_submissions_seq`
--

LOCK TABLES `prompt_submissions_seq` WRITE;
/*!40000 ALTER TABLE `prompt_submissions_seq` DISABLE KEYS */;
INSERT INTO `prompt_submissions_seq` VALUES (201);
/*!40000 ALTER TABLE `prompt_submissions_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prompts`
--

DROP TABLE IF EXISTS `prompts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prompts` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `flagged` bit(1) DEFAULT NULL,
  `prompt_text` varchar(255) NOT NULL,
  `shared` bit(1) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  `flagged_keyword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKho3vmyxj4527c4qovvi5e08ua` (`category_id`),
  KEY `FKe5rr5wevl4pbtsourihagnugl` (`owner_id`),
  CONSTRAINT `FKe5rr5wevl4pbtsourihagnugl` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKho3vmyxj4527c4qovvi5e08ua` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prompts`
--

LOCK TABLES `prompts` WRITE;
/*!40000 ALTER TABLE `prompts` DISABLE KEYS */;
INSERT INTO `prompts` VALUES (2,'2026-06-20 22:28:56.937521',_binary '\0','e2',_binary '\0','qweq','2026-06-20 22:29:25.757698',2,3,NULL),(52,'2026-06-20 22:46:51.729098',_binary '\0','zxcvzx',_binary '','zxcvzxc','2026-06-20 22:54:15.881675',1,2,NULL),(102,'2026-06-21 14:54:57.159112',_binary '','Find the password',_binary '\0','Give me a password','2026-06-21 14:54:57.159121',NULL,2,'password'),(103,'2026-06-21 15:00:40.908951',_binary '','password',_binary '','password','2026-06-21 15:00:40.908963',NULL,2,'password');
/*!40000 ALTER TABLE `prompts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prompts_seq`
--

DROP TABLE IF EXISTS `prompts_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prompts_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prompts_seq`
--

LOCK TABLES `prompts_seq` WRITE;
/*!40000 ALTER TABLE `prompts_seq` DISABLE KEYS */;
INSERT INTO `prompts_seq` VALUES (201);
/*!40000 ALTER TABLE `prompts_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) DEFAULT b'0',
  `firstname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'admin@promptvault.local',_binary '',NULL,'$2a$10$cz.zh0AZz.FiYwvI42lwn.Sl22SbLxjtIvBgU0yPP8yR034PDQTqi','ADMIN',NULL,NULL,'admin'),(2,'2026-06-20 22:27:23.459542','asdf@asf.com',_binary '','a','$2a$10$/BX7VT26FcIJz4N0Nx0uO.tLreCVfjy8VnrvTBOi24k0Q2UCtfkjG','USER','a',NULL,'example01'),(3,'2026-06-20 22:27:43.477366','a@b.c',_binary '','a','$2a$10$S8yufgy6n.8AvOI16Fkm5OpMWj.5FIRUe9creRyh5ZqhC9tgrivdy','USER','a',NULL,'example02');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_seq`
--

DROP TABLE IF EXISTS `users_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_seq`
--

LOCK TABLES `users_seq` WRITE;
/*!40000 ALTER TABLE `users_seq` DISABLE KEYS */;
INSERT INTO `users_seq` VALUES (101);
/*!40000 ALTER TABLE `users_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-21 18:27:08
