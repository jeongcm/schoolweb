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
-- Table structure for table `재학생현황`
--

DROP TABLE IF EXISTS `재학생현황`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `재학생현황` (
  `년도` int(11) NOT NULL DEFAULT '0',
  `기준` char(5) NOT NULL DEFAULT 'null',
  `학과명` varchar(15) NOT NULL DEFAULT 'null',
  `학생정원` int(11) DEFAULT '0',
  `군휴학자` int(11) DEFAULT '0',
  `정원내` int(11) DEFAULT '0',
  `정원외` int(11) DEFAULT '0',
  `계` int(11) DEFAULT '0',
  `연번` int(11) NOT NULL AUTO_INCREMENT,
  `전체재학생충원율` float DEFAULT '0',
  `정원내재학생충원율` float DEFAULT '0',
  `재학생충원율` float DEFAULT '0',
  `T점수` float DEFAULT '0',
  `입력부서` varchar(45) NOT NULL,
  `타학과전과자` int(11) DEFAULT NULL,
  PRIMARY KEY (`기준`,`학과명`),
  UNIQUE KEY `연번` (`연번`)
) ENGINE=InnoDB AUTO_INCREMENT=2216 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `재학생현황`
--

LOCK TABLES `재학생현황` WRITE;
/*!40000 ALTER TABLE `재학생현황` DISABLE KEYS */;
INSERT INTO `재학생현황` VALUES (2016,'10.01','간호학과',0,0,0,0,222,2183,0,0,0,0,'admin',NULL),(2017,'10.01','건설환경도시교통공학부',0,0,0,0,133,2209,0,0,0,0,'admin',NULL),(2016,'10.01','건축공학과',0,0,0,0,190,2159,0,0,0,0,'admin',NULL),(2016,'10.01','건축학과',0,0,0,0,136,2163,0,0,0,0,'admin',NULL),(2016,'10.01','경영정보학과',0,0,0,0,124,2180,0,0,0,0,'admin',NULL),(2016,'10.01','경영학과',0,0,0,0,173,2179,0,0,0,0,'admin',NULL),(2016,'10.01','국제통상학과',0,0,0,0,92,2192,0,0,0,0,'admin',NULL),(2016,'10.01','기계공학과',0,0,0,0,362,2149,0,0,0,0,'admin',NULL),(2017,'10.01','기계공학전공',0,0,0,0,275,2202,0,0,0,0,'admin',NULL),(2017,'10.01','기계자동차항공공학부',0,0,0,0,172,2201,0,0,0,0,'admin',NULL),(2016,'10.01','나노화학소재공학과',0,0,0,0,268,2157,0,0,0,0,'admin',NULL),(2016,'10.01','도시교통공학과',0,0,0,0,93,2162,0,0,0,0,'admin',NULL),(2017,'10.01','도시교통공학전공',0,0,0,0,62,2212,0,0,0,0,'admin',NULL),(2016,'10.01','물리치료학과',0,0,0,0,171,2184,0,0,0,0,'admin',NULL),(2016,'10.01','비즈니스영어전공',0,0,0,0,120,2171,0,0,0,0,'admin',NULL),(2016,'10.01','사회복지학과',0,0,0,0,127,2191,0,0,0,0,'admin',NULL),(2016,'10.01','산업경영공학과',0,0,0,0,233,2152,0,0,0,0,'admin',NULL),(2016,'10.01','산업디자인전공',0,0,0,0,41,2160,0,0,0,0,'admin',NULL),(2016,'10.01','생명공학과',0,0,0,0,96,2187,0,0,0,0,'admin',NULL),(2016,'10.01','소프트웨어학과',0,0,0,0,126,2169,0,0,0,0,'admin',NULL),(2016,'10.01','스포츠건강관리학전공',0,0,0,0,73,2175,0,0,0,0,'admin',NULL),(2016,'10.01','스포츠산업학전공',0,0,0,0,52,2176,0,0,0,0,'admin',NULL),(2016,'10.01','식품공학과',0,0,0,0,128,2186,0,0,0,0,'admin',NULL),(2016,'10.01','식품영양학과',0,0,0,0,105,2189,0,0,0,0,'admin',NULL),(2016,'10.01','신소재공학과',0,0,0,0,201,2156,0,0,0,0,'admin',NULL),(2017,'10.01','신소재공학전공',0,0,0,0,146,2215,0,0,0,0,'admin',NULL),(2016,'10.01','안전공학과',0,0,0,0,222,2153,0,0,0,0,'admin',NULL),(2016,'10.01','에너지시스템공학과',0,0,0,0,209,2151,0,0,0,0,'admin',NULL),(2016,'10.01','영어영문학전공',0,0,0,0,215,2170,0,0,0,0,'admin',NULL),(2016,'10.01','유아교육학과',0,0,0,0,116,2190,0,0,0,0,'admin',NULL),(2016,'10.01','유아특수교육학과',0,0,0,0,53,2193,0,0,0,0,'admin',NULL),(2016,'10.01','음악학과',0,0,0,0,110,2173,0,0,0,0,'admin',NULL),(2016,'10.01','응급구조학과',0,0,0,0,129,2185,0,0,0,0,'admin',NULL),(2016,'10.01','의료IT공학과',0,0,0,0,87,2188,0,0,0,0,'admin',NULL),(2017,'10.01','자동차공학전공',0,0,0,0,154,2203,0,0,0,0,'admin',NULL),(2016,'10.01','전기공학과',0,0,0,0,308,2164,0,0,0,0,'admin',NULL),(2017,'10.01','전기공학전공',0,0,0,0,203,2206,0,0,0,0,'admin',NULL),(2017,'10.01','전기전자로봇통신공학부',0,0,0,0,197,2205,0,0,0,0,'admin',NULL),(2016,'10.01','전자공학과',0,0,0,0,235,2165,0,0,0,0,'admin',NULL),(2017,'10.01','전자공학전공',0,0,0,0,172,2207,0,0,0,0,'admin',NULL),(2016,'10.01','정보통신공학과',0,0,0,0,212,2168,0,0,0,0,'admin',NULL),(2017,'10.01','정보통신로봇공학전공',0,0,0,0,315,2208,0,0,0,0,'admin',NULL),(2016,'10.01','제어계측공학과',0,0,0,0,223,2167,0,0,0,0,'admin',NULL),(2016,'10.01','중국어과',0,0,0,0,208,2172,0,0,0,0,'admin',NULL),(2016,'10.01','철도경영물류학과',0,0,0,0,138,2194,0,0,0,0,'admin',NULL),(2016,'10.01','철도시설공학과',0,0,0,0,156,2197,0,0,0,0,'admin',NULL),(2016,'10.01','철도운전시스템공학과',0,0,0,0,109,2195,0,0,0,0,'admin',NULL),(2016,'10.01','철도전기전자공학과',0,0,0,0,135,2198,0,0,0,0,'admin',NULL),(2016,'10.01','철도차량시스템공학과',0,0,0,0,113,2196,0,0,0,0,'admin',NULL),(2016,'10.01','커뮤니케이션디자인전공',0,0,0,0,57,2161,0,0,0,0,'admin',NULL),(2016,'10.01','컴퓨터공학과',0,0,0,0,196,2166,0,0,0,0,'admin',NULL),(2016,'10.01','컴퓨터정보공학과',0,0,0,0,148,2199,0,0,0,0,'admin',NULL),(2016,'10.01','토목공학과',0,0,0,0,265,2158,0,0,0,0,'admin',NULL),(2017,'10.01','토목공학전공',0,0,0,0,179,2210,0,0,0,0,'admin',NULL),(2016,'10.01','한국어문학과',0,0,0,0,132,2174,0,0,0,0,'admin',NULL),(2017,'10.01','항공기계설계전공',0,0,0,0,206,2204,0,0,0,0,'admin',NULL),(2016,'10.01','항공기계설계학과',0,0,0,0,251,2155,0,0,0,0,'admin',NULL),(2016,'10.01','항공서비스학과',0,0,0,0,86,2181,0,0,0,0,'admin',NULL),(2016,'10.01','항공운항학과',0,0,0,0,108,2182,0,0,0,0,'admin',NULL),(2016,'10.01','행정정보학과',0,0,0,0,127,2178,0,0,0,0,'admin',NULL),(2016,'10.01','행정학과',0,0,0,0,178,2177,0,0,0,0,'admin',NULL),(2016,'10.01','화공생물공학과',0,0,0,0,245,2150,0,0,0,0,'admin',NULL),(2017,'10.01','화공생물공학전공',0,0,0,0,181,2214,0,0,0,0,'admin',NULL),(2017,'10.01','화공신소재고분자공학부',0,0,0,0,163,2213,0,0,0,0,'admin',NULL),(2016,'10.01','환경공학과',0,0,0,0,234,2154,0,0,0,0,'admin',NULL),(2017,'10.01','환경공학전공',0,0,0,0,187,2211,0,0,0,0,'admin',NULL);
/*!40000 ALTER TABLE `재학생현황` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-27 11:55:05
