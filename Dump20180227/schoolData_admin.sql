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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` varchar(45) NOT NULL,
  `pw` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin','456456'),('간호학과','123'),('건축학전공','123'),('경영정보학과','123'),('경영학전공','5431'),('교무과','123'),('기초교육원','123'),('국제교류','123'),('국제통상전공','123'),('기계공학전공','5121'),('기타','123'),('기획과','123'),('나노고분자공학전공','123'),('대학원','123'),('도시교통공학전공','123'),('디자인학부','123'),('물리치료학과','123'),('비즈니스영어전공','123'),('사회복지학전공','123'),('산업경영공학전공','123'),('산업디자인전공','123'),('산학협력단','123'),('생명공학전공','123'),('소프트웨어학전공','123'),('스포츠건강관리학전공','123'),('스포츠산업학전공','123'),('식품공학전공','123'),('식품영양학전공','123'),('신소재공학전공','123'),('안전학전공','123'),('자동차공학전공','tnsrnjs@12'),('영어영문학전공','123'),('유아교육학과','123'),('유아특수교육학과','123'),('음악학과','123'),('응급구조학과','123'),('의료IT공학전공','123'),('입학관리과','123'),('정보기술융합학부','123'),('중국어전공','123'),('창업보육센터','123'),('철도경영물류학전공','123'),('철도인프라시스템공학전공','123'),('철도운전시스템전공','123'),('철도전기전자공학전공','123'),('철도차량시스템전공','123'),('취업창업지원과','123'),('커뮤니케이션디자인전공','123'),('컴퓨터공학전공','123'),('컴퓨터정보공학전공','123'),('토목공학전공','123'),('학사관리과','123'),('학생과','123'),('한국어문학전공','5981'),('항공기계설계전공','123'),('항공서비스학과','123'),('항공운항학과','123'),('행정정보학전공','123'),('행정학전공','123'),('화공생물공학전공','123'),('환경공학전공','123'),('전기공학전공','5141'),('전자공학전공','123'),('건축공학전공','123'),('정보통신로봇공학전공','123'),('기계자동차항공공학부','123'),('전기전자로봇통신공학부','123'),('건설환경도시교통공학부','123'),('화공신소재고분자공학부','123'),('산업경영안전공학부','123'),('건축학부','123'),('디자인학부','123'),('컴퓨터정보기술공학부','123'),('융합경영전공','123'),('글로벌어문학부','123'),('행정학부','123'),('경영통상복지학부','123'),('스포츠학부','123'),('식품생명학부','123'),('철도경영물류컴퓨터학부','123'),('철도공학부','123');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-27 11:55:06
