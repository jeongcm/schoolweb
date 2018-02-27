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
-- Table structure for table `입력확인`
--

DROP TABLE IF EXISTS `입력확인`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `입력확인` (
  `부서` varchar(45) DEFAULT NULL,
  `상태` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `입력확인`
--

LOCK TABLES `입력확인` WRITE;
/*!40000 ALTER TABLE `입력확인` DISABLE KEYS */;
INSERT INTO `입력확인` VALUES ('admin','입력중'),('간호학과','입력중'),('건축공학전공','입력중'),('건축학전공','입력중'),('경영정보학과','입력중'),('경영학전공','입력중'),('교무과','입력중'),('교수학습개발원','입력중'),('국제교류본부','입력중'),('국제통상학과','입력중'),('기계공학전공','입력중'),('기타','입력중'),('기획과','입력중'),('나노고분자공학전공','입력중'),('대학원','입력중'),('도시교통공학전공','입력중'),('디자인학부','입력중'),('물리치료학과','입력중'),('비즈니스영어전공','입력중'),('사회복지학전공','입력중'),('산업경영공학전공','입력중'),('산업디자인전공','입력중'),('산학협력단','입력중'),('생명공학전공','입력중'),('소프트웨어학전공','입력중'),('스포츠건강관리학전공','입력중'),('스포츠산업학전공','입력중'),('식품공학전공','입력중'),('식품영양학전공','입력중'),('신소재공학전공','입력중'),('안전학전공','입력중'),('자동차공학전공','입력중'),('영어영문학전공','입력중'),('유아교육학과','입력중'),('유아특수교육학과','입력중'),('음악학과','입력중'),('응급구조학과','입력중'),('의료IT공학전공','입력중'),('입학관리과','입력중'),('전기공학전공','입력중'),('전자공학전공','입력중'),('정보기술융합학부','입력중'),('정보통신로봇공학전공','입력중'),('제어계측공학과','입력중'),('중국어전공','입력중'),('창업보육센터','입력중'),('철도경영물류학전공','입력중'),('철도인프라시스템공학전공','입력중'),('철도운전시스템공학전공','입력중'),('철도전기전자공학전공','입력중'),('철도차량시스템공학전공','입력중'),('취업창업지원과','입력중'),('커뮤니케이션디자인전공','입력중'),('컴퓨터공학전공','입력중'),('컴퓨터정보공학전공','입력중'),('토목공학전공','입력중'),('학사관리과','입력중'),('학생과','입력중'),('한국어문학전공','입력중'),('항공기계설계전공','입력중'),('항공서비스학과','입력중'),('항공운항학과','입력중'),('행정정보학전공','입력중'),('행정학전공','입력중'),('화공생물공학전공','입력중'),('환경공학전공','입력중');
/*!40000 ALTER TABLE `입력확인` ENABLE KEYS */;
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
