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
-- Table structure for table `동아리`
--

DROP TABLE IF EXISTS `동아리`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `동아리` (
  `년도` int(11) NOT NULL,
  `관리부서` varchar(20) NOT NULL,
  `학과명` varchar(15) NOT NULL,
  `동아리명` varchar(45) NOT NULL,
  `구분` char(2) NOT NULL,
  `지도교수` varchar(10) NOT NULL,
  `학생대표학년` varchar(10) NOT NULL,
  `학생대표이름` varchar(10) NOT NULL,
  `회원수` int(11) DEFAULT NULL,
  `인정여부` char(2) NOT NULL,
  `실적서류` varchar(45) NOT NULL,
  `비고` varchar(45) DEFAULT NULL,
  `연번` int(11) NOT NULL AUTO_INCREMENT,
  `예산지원액` int(11) DEFAULT NULL,
  `입력부서` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`연번`),
  KEY `학과명,년도_idx` (`년도`,`학과명`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `동아리`
--

LOCK TABLES `동아리` WRITE;
/*!40000 ALTER TABLE `동아리` DISABLE KEYS */;
/*!40000 ALTER TABLE `동아리` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-27 11:55:03
