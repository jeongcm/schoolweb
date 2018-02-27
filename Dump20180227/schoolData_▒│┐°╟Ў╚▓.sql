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
-- Table structure for table `교원현황`
--

DROP TABLE IF EXISTS `교원현황`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `교원현황` (
  `년도` int(11) NOT NULL,
  `학과명` varchar(15) NOT NULL,
  `일학기` int(11) NOT NULL,
  `이학기` int(11) NOT NULL,
  `연번` int(11) NOT NULL AUTO_INCREMENT,
  `입력부서` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`년도`,`학과명`),
  UNIQUE KEY `연번_UNIQUE` (`연번`)
) ENGINE=InnoDB AUTO_INCREMENT=418 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `교원현황`
--

LOCK TABLES `교원현황` WRITE;
/*!40000 ALTER TABLE `교원현황` DISABLE KEYS */;
INSERT INTO `교원현황` VALUES (2016,'간호학과',7,6,367,'admin'),(2016,'건축공학과',6,7,368,'admin'),(2016,'건축학과',6,6,369,'admin'),(2016,'경영정보학과',4,4,370,'admin'),(2016,'경영학과',6,6,371,'admin'),(2016,'국제통상학과',4,4,372,'admin'),(2016,'기계공학과',12,13,373,'admin'),(2016,'나노화학소재공학과',9,9,374,'admin'),(2016,'도시교통공학과',6,6,375,'admin'),(2016,'물리치료학과',5,5,376,'admin'),(2016,'비즈니스영어전공',5,5,377,'admin'),(2016,'사회복지학과',4,4,378,'admin'),(2016,'산업경영공학과',8,8,379,'admin'),(2016,'산업디자인전공',3,3,380,'admin'),(2016,'생명공학과',5,5,381,'admin'),(2016,'소프트웨어전공',7,6,382,'admin'),(2016,'스포츠건강관리학전공',3,4,383,'admin'),(2016,'스포츠산업학전공',3,4,384,'admin'),(2016,'식품공학과',4,4,385,'admin'),(2016,'식품영양학과',4,4,386,'admin'),(2016,'신소재공학과',7,8,387,'admin'),(2016,'안전공학과',8,7,388,'admin'),(2016,'에너지시스템공학과',8,8,389,'admin'),(2016,'영어영문학전공',7,7,390,'admin'),(2016,'유아교육학과',4,4,391,'admin'),(2016,'유아특수교육학과',2,3,392,'admin'),(2016,'음악학과',5,5,393,'admin'),(2016,'응급구조학과',5,5,394,'admin'),(2016,'의료IT공학과',4,4,395,'admin'),(2016,'전기공학과',5,7,396,'admin'),(2016,'전자공학과',9,9,397,'admin'),(2016,'정보통신공학전공',6,7,398,'admin'),(2016,'제어계측공학과',7,7,399,'admin'),(2016,'중국어과',6,6,400,'admin'),(2016,'철도경영물류학과',6,6,401,'admin'),(2016,'철도시설공학과',6,6,402,'admin'),(2016,'철도운전시스템공학과',4,4,403,'admin'),(2016,'철도전기전자공학과',4,4,404,'admin'),(2016,'철도차량시스템공학과',4,4,405,'admin'),(2016,'커뮤니케이션디자인전공',3,3,406,'admin'),(2016,'컴퓨터공학과',6,6,407,'admin'),(2016,'컴퓨터정보공학과',5,6,408,'admin'),(2016,'토목공학과',10,10,409,'admin'),(2016,'한국어문학과',4,5,410,'admin'),(2016,'항공기계설계학과',7,8,411,'admin'),(2016,'항공서비스학과',4,4,412,'admin'),(2016,'항공운항학과',4,4,413,'admin'),(2016,'행정정보학과',5,5,414,'admin'),(2016,'행정학과',7,7,415,'admin'),(2016,'화공생물공학과',7,7,416,'admin'),(2016,'환경공학과',8,8,417,'admin'),(2017,'간호학과',8,8,354,'admin'),(2017,'건축공학전공',7,8,331,'admin'),(2017,'건축학전공',6,6,332,'admin'),(2017,'경영학전공',6,6,344,'admin'),(2017,'국제통상전공',4,4,346,'admin'),(2017,'기계공학전공',13,13,317,'admin'),(2017,'나노고분자공학전공',9,9,328,'admin'),(2017,'도시교통공학전공',6,6,325,'admin'),(2017,'물리치료학과',4,4,358,'admin'),(2017,'비즈니스영어전공',5,5,339,'admin'),(2017,'사회복지학전공',4,4,347,'admin'),(2017,'산업경영공학전공',8,8,329,'admin'),(2017,'산업디자인전공',3,3,333,'admin'),(2017,'생명공학전공',5,5,357,'admin'),(2017,'소프트웨어학전공',7,7,336,'admin'),(2017,'스포츠건강관리학전공',4,4,349,'admin'),(2017,'스포츠산업학전공',4,4,350,'admin'),(2017,'식품공학전공',5,4,355,'admin'),(2017,'식품영양학전공',4,4,356,'admin'),(2017,'신소재공학전공',8,7,327,'admin'),(2017,'안전공학전공',9,7,330,'admin'),(2017,'영어영문학전공',7,7,338,'admin'),(2017,'유아교육학과',5,5,353,'admin'),(2017,'유아특수교육학과',2,2,360,'admin'),(2017,'융합경영전공',4,4,345,'admin'),(2017,'음악학과',5,5,348,'admin'),(2017,'응급구조학과',5,5,359,'admin'),(2017,'의료IT공학전공',5,4,337,'admin'),(2017,'자동차공학전공',8,8,318,'admin'),(2017,'전기공학전공',8,8,320,'admin'),(2017,'전자공학전공',9,9,321,'admin'),(2017,'정보통신로봇공학전공',15,14,322,'admin'),(2017,'중국어전공',6,6,340,'admin'),(2017,'철도경영물류학전공',6,14,361,'admin'),(2017,'철도운전시스템전공',4,3,363,'admin'),(2017,'철도인프라시스템공학전공',6,7,365,'admin'),(2017,'철도전기전자전공',4,4,366,'admin'),(2017,'철도차량시스템전공',4,4,364,'admin'),(2017,'커뮤니케이션디자인전공',3,3,334,'admin'),(2017,'컴퓨터공학전공',6,6,335,'admin'),(2017,'컴퓨터정보공학전공',6,7,362,'admin'),(2017,'토목공학전공',10,9,323,'admin'),(2017,'한국어문학전공',5,5,341,'admin'),(2017,'항공기계설계전공',7,7,319,'admin'),(2017,'항공서비스학과',4,4,351,'admin'),(2017,'항공운항학과',4,4,352,'admin'),(2017,'행정정보학전공',6,6,343,'admin'),(2017,'행정학전공',7,7,342,'admin'),(2017,'화공생물공학전공',7,8,326,'admin'),(2017,'환경공학전공',8,8,324,'admin');
/*!40000 ALTER TABLE `교원현황` ENABLE KEYS */;
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
