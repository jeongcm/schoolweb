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
-- Table structure for table `외국인학생비율`
--

DROP TABLE IF EXISTS `외국인학생비율`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `외국인학생비율` (
  `년도` int(11) NOT NULL,
  `학과명` varchar(15) NOT NULL,
  `외국인학생비율` float DEFAULT NULL,
  `T점수` float DEFAULT '20',
  PRIMARY KEY (`년도`,`학과명`),
  KEY `fk_학과명_idx` (`학과명`),
  CONSTRAINT `fk_년도_학과명` FOREIGN KEY (`년도`, `학과명`) REFERENCES `학과현황` (`년도`, `학과명`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `외국인학생비율`
--

LOCK TABLES `외국인학생비율` WRITE;
/*!40000 ALTER TABLE `외국인학생비율` DISABLE KEYS */;
INSERT INTO `외국인학생비율` VALUES (2017,'간호학과',NULL,20),(2017,'건설환경도시교통공학부',NULL,20),(2017,'건축공학전공 ',NULL,20),(2017,'건축학부',NULL,20),(2017,'건축학전공',NULL,20),(2017,'경영통상복지학부',NULL,20),(2017,'경영학전공',NULL,20),(2017,'국제통상전공',NULL,20),(2017,'글로벌어문학부',NULL,20),(2017,'기계공학전공',NULL,20),(2017,'기계자동차항공공학부',NULL,20),(2017,'나노고분자공학전공',NULL,20),(2017,'도시교통공학전공',NULL,20),(2017,'디자인학부',NULL,20),(2017,'물리치료학과',NULL,20),(2017,'비즈니스영어전공',NULL,20),(2017,'사회복지학전공',NULL,20),(2017,'산업경영공학전공',NULL,20),(2017,'산업경영안전공학부',NULL,20),(2017,'산업디자인전공',NULL,20),(2017,'생명공학전공',NULL,20),(2017,'소프트웨어학전공',NULL,20),(2017,'스포츠건강관리학전공',NULL,20),(2017,'스포츠산업학전공',NULL,20),(2017,'스포츠학부',NULL,20),(2017,'식품공학전공',NULL,20),(2017,'식품생명학부',NULL,20),(2017,'식품영양학전공',NULL,20),(2017,'신소재공학전공',NULL,20),(2017,'안전공학전공',NULL,20),(2017,'영어영문학전공',NULL,20),(2017,'유아교육학과',NULL,20),(2017,'유아특수교육학과',NULL,20),(2017,'융합경영전공',NULL,20),(2017,'음악학과',NULL,20),(2017,'응급구조학과',NULL,20),(2017,'의료IT공학전공',NULL,20),(2017,'자동차공학전공',NULL,20),(2017,'전기공학전공',NULL,20),(2017,'전기전자로봇통신공학부',NULL,20),(2017,'전자공학전공',NULL,20),(2017,'정보통신∙로봇공학전공',NULL,20),(2017,'중국어전공',NULL,20),(2017,'철도경영물류컴퓨터학부',NULL,20),(2017,'철도경영물류학전공',NULL,20),(2017,'철도공학부',NULL,20),(2017,'철도운전시스템전공',NULL,20),(2017,'철도인프라시스템공학전공',NULL,20),(2017,'철도전기전자전공',NULL,20),(2017,'철도차량시스템전공',NULL,20),(2017,'커뮤니케이션디자인전공',NULL,20),(2017,'컴퓨터공학전공',NULL,20),(2017,'컴퓨터정보공학전공',NULL,20),(2017,'컴퓨터정보기술공학부',NULL,20),(2017,'토목공학전공',NULL,20),(2017,'한국어문학전공',NULL,20),(2017,'항공기계설계전공',NULL,20),(2017,'항공서비스학과',NULL,20),(2017,'항공운항학과',NULL,20),(2017,'행정정보학전공',NULL,20),(2017,'행정학부',NULL,20),(2017,'행정학전공',NULL,20),(2017,'화공생물공학전공',NULL,20),(2017,'화공신소재고분자공학부',NULL,20),(2017,'환경공학전공',NULL,20);
/*!40000 ALTER TABLE `외국인학생비율` ENABLE KEYS */;
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
