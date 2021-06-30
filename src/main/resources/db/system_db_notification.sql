-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: system_db
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
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `body` varchar(500) NOT NULL,
  `provider` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `processor_job_uuid` varchar(100) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `creation_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FKb0yvoep4h4k92ipon31wmdf7e` (`user_id`),
  CONSTRAINT `FKb0yvoep4h4k92ipon31wmdf7e` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (6,'Congrats :D you got a promo ','Dear Customer, you got a discount up to 10 for the next 2 ride till 2021-06-29 15:50:00 ','Push','Processed','c0e81da2-aef3-43c0-93ac-f57192bd05fa',2,'2021-06-26 20:56:09'),(7,'Congrats :D you got a promo ','Dear Customer, you got a discount up to 10 for the next 2 ride till 2021-06-29 15:50:00 ','Push','Processed','c0e81da2-aef3-43c0-93ac-f57192bd05fa',5,'2021-06-26 20:56:09'),(8,'Congrats :D you got a promo ','Dear Customer, you got a discount up to 10 for the next 2 ride till 2021-06-29 15:50:00 ','Push','Processed','37130bb3-0647-4571-a5ea-0a2e24beab7b',6,'2021-06-26 20:56:09'),(9,'معذرة','عذرا سيتأخر الباص ','Push','Processed','37130bb3-0647-4571-a5ea-0a2e24beab7b',5,'2021-06-26 20:56:09'),(10,'معذرة','عذرا سيتأخر الباص ','Push','Processed','3bb0157c-5989-40cf-b83c-c4953081f5ab',6,'2021-06-26 20:56:09'),(11,'معذرة','عذرا سيتأخر الباص ','SMS','Processed','3bb0157c-5989-40cf-b83c-c4953081f5ab',5,'2021-06-26 20:56:09'),(12,'معذرة','عذرا سيتأخر الباص ','SMS','Processed','78e1a49d-db59-45d7-b54e-3f4653a79571',6,'2021-06-26 20:56:09');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-26 23:32:43
