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
-- Table structure for table `수상실적`
--

DROP TABLE IF EXISTS `수상실적`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `수상실적` (
  `년도` int(11) NOT NULL DEFAULT '2015',
  `학과명` varchar(15) NOT NULL,
  `구분` varchar(10) NOT NULL,
  `수상일자` varchar(40) DEFAULT NULL,
  `대회명` varchar(40) NOT NULL,
  `수상내용` varchar(40) NOT NULL,
  `인정여부` char(10) NOT NULL,
  `수상대상자` varchar(5) NOT NULL,
  `연번` int(11) NOT NULL AUTO_INCREMENT,
  `입력부서` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`연번`),
  UNIQUE KEY `연번_UNIQUE` (`연번`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `수상실적`
--

LOCK TABLES `수상실적` WRITE;
/*!40000 ALTER TABLE `수상실적` DISABLE KEYS */;
INSERT INTO `수상실적` VALUES (2017,'음악학과','국제대회','2017-10-26','2017한국 영아티스트 국제음악콩쿠르','목관부분 3위','인정','학생',36,'음악학과'),(2017,'음악학과','전국대회','2017-06-06','제59회 학생음악경연대회','금상','인정','학생',37,'음악학과'),(2017,'음악학과','전국대회','2017-11-04','전국학생음악콩쿠르','금상','인정','학생',38,'음악학과'),(2017,'음악학과','전국대회','2017-11-04','전국학생음악콩쿠르','금상','인정','학생',40,'음악학과'),(2017,'음악학과','지역또는교내대회','2017-09-21','국원가요제','1위','인정','학생',41,'음악학과');
/*!40000 ALTER TABLE `수상실적` ENABLE KEYS */;
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
