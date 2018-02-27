-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: schoolData
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
-- Table structure for table `전임교원확보율`
--

DROP TABLE IF EXISTS `전임교원확보율`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `전임교원확보율` (
  `년도` int(11) NOT NULL,
  `학과명` varchar(15) NOT NULL,
  `대학원생정원` int(11) DEFAULT NULL,
  `대학원재학생` float DEFAULT NULL,
  `학생정원기준전임교원` int(11) NOT NULL,
  `재학생기준전임교원` int(11) NOT NULL,
  `전임교원확보율` float DEFAULT NULL,
  `T점수` float DEFAULT '0',
  `연번` int(11) NOT NULL AUTO_INCREMENT,
  `인정학생정원` int(11) DEFAULT NULL,
  `교원_학부_정원` int(11) DEFAULT NULL,
  `교원_학부_재학생` int(11) DEFAULT NULL,
  `교원_대학원_정원` int(11) DEFAULT NULL,
  `교원_대학원_재학생` int(11) DEFAULT NULL,
  `교원_계_정원` int(11) DEFAULT NULL,
  `교원_계_재학생` int(11) DEFAULT NULL,
  `학생수_정원` float DEFAULT NULL,
  `학생수_재학생` float DEFAULT NULL,
  `확보율_정원` float DEFAULT NULL,
  `확보율_재학생` float DEFAULT NULL,
  `입력부서` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`년도`,`학과명`),
  UNIQUE KEY `연번` (`연번`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `전임교원확보율`
--

LOCK TABLES `전임교원확보율` WRITE;
/*!40000 ALTER TABLE `전임교원확보율` DISABLE KEYS */;
/*!40000 ALTER TABLE `전임교원확보율` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-27 11:55:04
