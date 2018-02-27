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
-- Table structure for table `취업자`
--

DROP TABLE IF EXISTS `취업자`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `취업자` (
  `년도` int(11) NOT NULL,
  `학과명` varchar(15) NOT NULL,
  `건강보험db연계취업자` int(11) DEFAULT '0',
  `해외취업자` int(11) DEFAULT '0',
  `영농업취업자` int(11) DEFAULT '0',
  `취업인정자` int(11) DEFAULT '0',
  `계` int(11) DEFAULT '0',
  `연번` int(11) NOT NULL AUTO_INCREMENT,
  `입력부서` varchar(45) DEFAULT NULL,
  `개인창작활동조사서` int(11) DEFAULT '0',
  `1인창업자` int(11) DEFAULT '0',
  `프리랜서` int(11) DEFAULT '0',
  PRIMARY KEY (`년도`,`학과명`),
  UNIQUE KEY `연번` (`연번`)
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `취업자`
--

LOCK TABLES `취업자` WRITE;
/*!40000 ALTER TABLE `취업자` DISABLE KEYS */;
INSERT INTO `취업자` VALUES (2016,'간호학과',121,0,0,0,121,240,'admin',0,0,0),(2016,'건축공학과',58,0,0,0,58,216,'admin',0,0,0),(2016,'건축학과',32,0,0,0,32,220,'admin',0,0,0),(2016,'경영정보학과',28,0,0,0,29,237,'admin',0,0,1),(2016,'경영학과',50,0,0,0,52,236,'admin',0,2,0),(2016,'국제통상학과',19,0,0,0,19,249,'admin',0,0,0),(2016,'기계공학과',90,0,0,0,92,206,'admin',0,1,1),(2016,'나노화학소재공학과',47,0,0,0,48,214,'admin',0,0,1),(2016,'도시·교통공학과',33,0,0,0,33,219,'admin',0,0,0),(2016,'물리치료학과',84,0,0,0,84,241,'admin',0,0,0),(2016,'비즈니스영어전공',13,0,0,0,13,228,'admin',0,0,0),(2016,'사회복지학과',33,0,0,0,34,248,'admin',0,0,1),(2016,'산업경영공학과',59,0,0,0,61,209,'admin',0,2,0),(2016,'산업디자인전공',17,0,0,0,19,217,'admin',0,0,2),(2016,'생명공학과',19,0,0,0,20,244,'admin',0,0,1),(2016,'소프트웨어학과',43,0,0,0,46,226,'admin',0,0,3),(2016,'스포츠건강관리학전공',30,0,0,0,39,232,'admin',0,0,9),(2016,'스포츠산업학전공',10,0,0,0,10,233,'admin',0,0,0),(2016,'식품공학과',55,0,0,0,55,243,'admin',0,0,0),(2016,'식품영양학과',27,0,0,0,28,246,'admin',0,0,1),(2016,'신소재공학과',60,0,0,0,62,213,'admin',0,1,1),(2016,'안전공학과',80,0,0,0,81,210,'admin',0,0,1),(2016,'에너지시스템공학과',68,0,0,0,68,208,'admin',0,0,0),(2016,'영어영문학전공',43,0,0,0,44,227,'admin',0,0,1),(2016,'유아교육학과',42,0,0,0,42,247,'admin',0,0,0),(2016,'유아특수교육학과',9,0,0,0,9,250,'admin',0,0,0),(2016,'음악학과',7,0,0,0,11,230,'admin',0,1,3),(2016,'응급구조학과',34,0,0,0,34,242,'admin',0,0,0),(2016,'의료IT공학과',29,0,0,0,29,245,'admin',0,0,0),(2016,'전기공학과',80,0,0,0,81,221,'admin',0,1,0),(2016,'전자공학과',62,0,0,0,64,222,'admin',0,1,1),(2016,'정보통신공학과',62,0,0,0,65,225,'admin',0,0,3),(2016,'제어계측공학과',60,0,0,0,61,224,'admin',0,0,1),(2016,'중국어과',43,0,0,0,45,229,'admin',0,0,2),(2016,'철도경영·물류학과',7,0,0,0,7,251,'admin',0,0,0),(2016,'철도시설공학과',10,0,0,0,10,254,'admin',0,0,0),(2016,'철도운전시스템공학과',4,0,0,0,4,252,'admin',0,0,0),(2016,'철도전기전자공학과',1,0,0,0,1,255,'admin',0,0,0),(2016,'철도차량시스템공학과',0,0,0,0,0,253,'admin',0,0,0),(2016,'커뮤니케이션디자인전공',14,0,0,0,16,218,'admin',0,0,2),(2016,'컴퓨터공학과',62,0,0,0,63,223,'admin',0,1,0),(2016,'컴퓨터정보공학과',62,0,0,0,64,256,'admin',0,0,2),(2016,'토목공학과',93,0,0,0,95,215,'admin',0,0,2),(2016,'한국어문학과',21,0,0,0,24,231,'admin',0,1,2),(2016,'항공·기계설계학과',75,0,0,0,75,212,'admin',0,0,0),(2016,'항공서비스학과',17,0,0,0,17,238,'admin',0,0,0),(2016,'항공운항학과',33,0,0,0,34,239,'admin',0,0,1),(2016,'행정정보학과',28,0,0,0,29,235,'admin',0,0,1),(2016,'행정학과',46,0,0,0,49,234,'admin',0,0,3),(2016,'화공생물공학과',67,0,0,0,67,207,'admin',0,0,0),(2016,'환경공학과',82,0,0,0,84,211,'admin',0,0,2);
/*!40000 ALTER TABLE `취업자` ENABLE KEYS */;
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
