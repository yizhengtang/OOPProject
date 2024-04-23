-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: shoedatabase
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(45) NOT NULL,
  `postal_code` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Eyre Square','h123','Galway','Ireland'),(2,'ATU','h111','Galway','Ireland'),(3,'Glasan','abcd','Galway','Ireland'),(4,'Renmore','qwer','Galway','Ireland'),(5,'abc','defg','Galway','Ireland'),(6,'atu','g123','Galway','Ireland'),(7,'tuam','g345','Galway','Ireland'),(8,'glasan','g004','Galway','Ireland'),(9,'lios','cxy','Galway','Ireland'),(10,'lakers','nba','Los Angeles','US'),(11,'somewhere','xyz','New York','US'),(12,'galway','h234','galway','ireland'),(13,'ATU','h234','Galway','Ireland'),(14,'ATU','h91','Galway','Ireland'),(15,'atu','h09','Galway','Ireland'),(16,'ATU','h91','galway','ireland');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colorway`
--

DROP TABLE IF EXISTS `colorway`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colorway` (
  `colorway_id` int NOT NULL AUTO_INCREMENT,
  `colour` varchar(45) NOT NULL,
  PRIMARY KEY (`colorway_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colorway`
--

LOCK TABLES `colorway` WRITE;
/*!40000 ALTER TABLE `colorway` DISABLE KEYS */;
INSERT INTO `colorway` VALUES (1,'Black'),(2,'White'),(3,'Grey'),(4,'Red'),(5,'Orange'),(6,'Yellow'),(7,'Green'),(8,'Blue'),(9,'Indigo'),(10,'Purple');
/*!40000 ALTER TABLE `colorway` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `address_id` int DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `fk_customer_address_idx` (`address_id`),
  CONSTRAINT `fk_customer_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,5,'Johnny','leeren','johnny123'),(2,8,'Drake','aubrey','drizzy'),(3,10,'Lebron','james','kingjames'),(4,11,'Mike Tyson','mikey','tyson123'),(5,12,'Bob Marley','marley','marley123'),(6,13,'Adam','adam@gmail.com','adam123'),(7,14,'Adam Silver','silver@gmail.com','silver123'),(8,16,'Curry','curry@gmail.com','steph123');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `inventory_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `store_id` int NOT NULL,
  `colorway_id` int NOT NULL,
  `size_id` int NOT NULL,
  PRIMARY KEY (`inventory_id`),
  KEY `fk_inventory_store_idx` (`store_id`),
  KEY `fk_inventory_colour_idx` (`colorway_id`),
  KEY `fk_inventory_size_idx` (`size_id`),
  KEY `fk_inventory_product_idx` (`product_id`),
  CONSTRAINT `fk_inventory_colorway` FOREIGN KEY (`colorway_id`) REFERENCES `colorway` (`colorway_id`),
  CONSTRAINT `fk_inventory_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `fk_inventory_size` FOREIGN KEY (`size_id`) REFERENCES `size` (`size_id`),
  CONSTRAINT `fk_inventory_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,1,1,4,5),(2,1,1,4,6),(3,1,2,4,7),(4,1,2,4,8),(5,2,1,1,8),(6,2,1,1,8),(7,2,1,1,9),(8,2,1,1,8),(9,2,1,1,9),(10,2,2,2,7),(11,2,2,2,7),(12,2,2,2,8),(13,2,2,2,7),(14,2,2,2,8),(15,3,3,9,10),(16,3,3,9,10),(17,3,3,10,4),(18,3,3,10,4),(19,3,3,10,5),(20,3,3,10,4),(21,3,3,10,5),(22,3,3,10,6),(23,3,3,10,4),(24,3,3,10,5),(25,3,3,10,6),(26,4,2,4,8),(27,4,2,4,8),(28,4,2,4,9),(29,4,2,4,8),(30,4,2,4,9),(31,4,2,8,8),(32,4,2,8,8),(33,4,2,8,9),(34,4,2,8,8),(35,4,2,8,9),(36,4,3,7,12),(37,4,3,7,12),(38,4,3,7,13),(39,4,3,7,12),(40,4,3,7,13),(41,5,1,1,1),(42,5,1,1,1),(43,5,1,2,2),(44,5,1,2,2),(45,5,2,1,1),(46,5,2,1,1),(47,6,4,1,1),(48,6,4,1,1),(49,6,4,2,5),(50,6,4,2,5);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `release_date` date NOT NULL,
  `description` varchar(200) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Jordan 1 Retro High Spiderman Origin Story','Jordan',160.00,'2018-12-14','This AJ1 comes with a white upper plus red accents, black Nike \"Swoosh\", white midsole, and translucent sole.','out of stock'),(2,'Air Force 1','Nike',100.00,'2003-01-01','This is an Air Force 1','In stock'),(3,'Slippers','Nike',200.00,'2001-01-01','This is the most expensive slippers ever existed','In stock'),(4,'Dunk High','Nike',100.00,'2005-05-05','This is nike dunk highs','In stock'),(5,'Slippers','nike',20.00,'2024-04-23','This is a slippers','In Stock'),(6,'Jordan 2','nike',200.00,'2024-04-23','This is jordan 2','In stock');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size` (
  `size_id` int NOT NULL AUTO_INCREMENT,
  `size` decimal(5,1) NOT NULL,
  PRIMARY KEY (`size_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (1,4.0),(2,4.5),(3,5.0),(4,5.5),(5,6.0),(6,6.5),(7,7.0),(8,7.5),(9,8.0),(10,8.5),(11,9.0),(12,9.5),(13,10.0),(14,10.5),(15,11.0),(16,11.5),(17,12.0),(18,12.5),(19,13.0),(20,13.5);
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_id` int NOT NULL AUTO_INCREMENT,
  `address_id` int NOT NULL,
  `store_id` int DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`staff_id`),
  KEY `fk_staff_address_idx` (`address_id`),
  KEY `fk_staff_store_idx` (`store_id`),
  CONSTRAINT `fk_staff_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `fk_staff_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,3,1,'Yi Zheng','yztang','tyz'),(2,4,2,'Tamer','zraiq','tamer123'),(3,6,1,'David','jacuzzi','david123'),(4,9,3,'ChoonXiang','rick','choon123');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `store_id` int NOT NULL AUTO_INCREMENT,
  `address_id` int DEFAULT NULL,
  `store` varchar(45) NOT NULL,
  PRIMARY KEY (`store_id`),
  KEY `fk_store_address_idx` (`address_id`),
  CONSTRAINT `fk_store_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,1,'Minecraft'),(2,2,'Roblox'),(3,7,'Fortnite'),(4,15,'ATU');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-23 18:14:24
