-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: depart
-- ------------------------------------------------------
-- Server version	5.7.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `common_index`
--

DROP TABLE IF EXISTS `common_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `common_index` (
  `mid_long_develop` int(11) DEFAULT NULL,
  `develop_fund` int(11) DEFAULT NULL,
  `edu_assess` int(11) DEFAULT NULL,
  `system_improve` int(11) DEFAULT NULL,
  `edu_consume` int(11) DEFAULT NULL,
  `find_sponsor` int(11) DEFAULT NULL,
  `com_service_part` int(11) DEFAULT NULL,
  `disclosure_error` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `common_index`
--

LOCK TABLES `common_index` WRITE;
/*!40000 ALTER TABLE `common_index` DISABLE KEYS */;
INSERT INTO `common_index` VALUES (0,0,0,0,0,0,0,0,1),(0,0,0,0,0,0,0,0,2),(0,0,0,0,0,0,0,0,3),(0,0,0,0,0,0,0,0,4),(0,0,0,0,0,0,0,0,5),(0,0,0,0,0,0,0,0,6),(0,0,0,0,0,0,0,0,7),(0,0,0,0,0,0,0,0,8),(0,0,0,0,0,0,0,0,9),(0,0,0,0,0,0,0,0,10),(0,0,0,0,0,0,0,0,11),(0,0,0,0,0,0,0,0,12),(0,0,0,0,0,0,0,0,13),(0,0,0,0,0,0,0,0,14),(0,0,0,0,0,0,0,0,15),(13,14,0,0,0,0,0,0,16),(0,0,0,0,0,0,0,0,17),(0,0,0,0,0,0,0,0,18),(0,0,0,0,0,0,0,0,19),(0,0,0,0,0,0,0,0,20),(0,0,0,0,0,0,0,0,21),(0,0,0,0,0,0,0,0,22),(0,0,0,0,0,0,0,0,23),(0,0,0,0,0,0,0,0,24),(0,0,0,0,0,0,0,0,25),(0,0,0,0,0,0,0,0,26);
/*!40000 ALTER TABLE `common_index` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-27 11:55:02
