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
-- Temporary view structure for view `취업률외부view`
--

DROP TABLE IF EXISTS `취업률외부view`;
/*!50001 DROP VIEW IF EXISTS `취업률외부view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `취업률외부view` AS SELECT 
 1 AS `대학명`,
 1 AS `학과명`,
 1 AS `졸업자`,
 1 AS `2차유지취업률`,
 1 AS `T점수`,
 1 AS `취업률`,
 1 AS `건강보험db연계취업자`,
 1 AS `해외취업자`,
 1 AS `영농업취업자`,
 1 AS `개인창작활동조사서`,
 1 AS `1인창업자`,
 1 AS `프리랜서`,
 1 AS `취업합계`,
 1 AS `진학자`,
 1 AS `입대자`,
 1 AS `취업불가능자`,
 1 AS `외국인유학생`,
 1 AS `건강보험직장가입제외대상`,
 1 AS `입학당시기취업자`,
 1 AS `제외자합계`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `강의담당외부view`
--

DROP TABLE IF EXISTS `강의담당외부view`;
/*!50001 DROP VIEW IF EXISTS `강의담당외부view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `강의담당외부view` AS SELECT 
 1 AS `대학명`,
 1 AS `학과명`,
 1 AS `개설전공과목`,
 1 AS `개설교양필수과목`,
 1 AS `개설자유선택과목`,
 1 AS `연번`,
 1 AS `강의담당비율`,
 1 AS `T점수`,
 1 AS `입력부서`,
 1 AS `전공과목`,
 1 AS `교양필수과목`,
 1 AS `자유선택과목`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `설문조사view`
--

DROP TABLE IF EXISTS `설문조사view`;
/*!50001 DROP VIEW IF EXISTS `설문조사view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `설문조사view` AS SELECT 
 1 AS `단과대학`,
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `참여학생수`,
 1 AS `설문조사총점`,
 1 AS `연번`,
 1 AS `학생만족도평가`,
 1 AS `T점수`,
 1 AS `입력부서`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `특허등록view`
--

DROP TABLE IF EXISTS `특허등록view`;
/*!50001 DROP VIEW IF EXISTS `특허등록view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `특허등록view` AS SELECT 
 1 AS `년도`,
 1 AS `일학기`,
 1 AS `단과대학`,
 1 AS `학과명`,
 1 AS `기술이전`,
 1 AS `국제`,
 1 AS `국내`,
 1 AS `T점수`,
 1 AS `수입료`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `강의공개view`
--

DROP TABLE IF EXISTS `강의공개view`;
/*!50001 DROP VIEW IF EXISTS `강의공개view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `강의공개view` AS SELECT 
 1 AS `단과대학`,
 1 AS `전임교원수`,
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `강의동영상B`,
 1 AS `이러닝강의C`,
 1 AS `강의자료D`,
 1 AS `강의동영상E`,
 1 AS `이러닝강의F`,
 1 AS `강의자료G`,
 1 AS `연번`,
 1 AS `입력부서`,
 1 AS `강의공개실적`,
 1 AS `T점수`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `중도탈락률view`
--

DROP TABLE IF EXISTS `중도탈락률view`;
/*!50001 DROP VIEW IF EXISTS `중도탈락률view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `중도탈락률view` AS SELECT 
 1 AS `단과대학`,
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `재적학생수`,
 1 AS `미등록`,
 1 AS `미복학`,
 1 AS `자퇴`,
 1 AS `학사경고`,
 1 AS `기타`,
 1 AS `연번`,
 1 AS `계`,
 1 AS `중도탈락률`,
 1 AS `T점수`,
 1 AS `입력부서`,
 1 AS `타학과전과자`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `교외장학금view`
--

DROP TABLE IF EXISTS `교외장학금view`;
/*!50001 DROP VIEW IF EXISTS `교외장학금view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `교외장학금view` AS SELECT 
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `교외장학금`,
 1 AS `1인당교외장학금`,
 1 AS `T점수`,
 1 AS `연번`,
 1 AS `입력부서`,
 1 AS `단과대학`,
 1 AS `학과`,
 1 AS `1학기`,
 1 AS `2학기`,
 1 AS `평균`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `봉사실적view`
--

DROP TABLE IF EXISTS `봉사실적view`;
/*!50001 DROP VIEW IF EXISTS `봉사실적view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `봉사실적view` AS SELECT 
 1 AS `년도`,
 1 AS `단과대학`,
 1 AS `학과명`,
 1 AS `이수1학기`,
 1 AS `재학생1학기`,
 1 AS `이수2학기`,
 1 AS `재학생2학기`,
 1 AS `이수학점`,
 1 AS `T점수`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `연구실적view`
--

DROP TABLE IF EXISTS `연구실적view`;
/*!50001 DROP VIEW IF EXISTS `연구실적view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `연구실적view` AS SELECT 
 1 AS `단과대학`,
 1 AS `학문계열1`,
 1 AS `_5대계열`,
 1 AS `전임교원수`,
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `저서`,
 1 AS `역서`,
 1 AS `연구재단등재지`,
 1 AS `연구재단등재후보`,
 1 AS `SCI급`,
 1 AS `SCOPUS학술지`,
 1 AS `연구실적`,
 1 AS `T점수`,
 1 AS `연번`,
 1 AS `입력부서`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `발전기금view`
--

DROP TABLE IF EXISTS `발전기금view`;
/*!50001 DROP VIEW IF EXISTS `발전기금view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `발전기금view` AS SELECT 
 1 AS `발전기금모금액`,
 1 AS `전임교원수`,
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `지정기부금`,
 1 AS `발전기금조성액`,
 1 AS `T점수`,
 1 AS `연번`,
 1 AS `입력부서`,
 1 AS `단과대학`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `국책사업수주실적view`
--

DROP TABLE IF EXISTS `국책사업수주실적view`;
/*!50001 DROP VIEW IF EXISTS `국책사업수주실적view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `국책사업수주실적view` AS SELECT 
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `대학명의국책사업수주총액`,
 1 AS `전임교원1인당국책사업수주총액`,
 1 AS `T점수`,
 1 AS `연번`,
 1 AS `입력부서`,
 1 AS `전임교원수`,
 1 AS `단과대학`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `취업률view`
--

DROP TABLE IF EXISTS `취업률view`;
/*!50001 DROP VIEW IF EXISTS `취업률view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `취업률view` AS SELECT 
 1 AS `학문계열1`,
 1 AS `단과대학`,
 1 AS `학과명`,
 1 AS `년도`,
 1 AS `졸업자`,
 1 AS `2차유지취업률`,
 1 AS `T점수`,
 1 AS `취업률`,
 1 AS `건강보험db연계취업자`,
 1 AS `해외취업자`,
 1 AS `영농업취업자`,
 1 AS `취업인정자`,
 1 AS `개인창작활동조사서`,
 1 AS `1인창업자`,
 1 AS `프리랜서`,
 1 AS `취업합계`,
 1 AS `진학자`,
 1 AS `입대자`,
 1 AS `취업불가능자`,
 1 AS `외국인유학생`,
 1 AS `건강보험직장가입제외대상`,
 1 AS `입학당시기취업자`,
 1 AS `제외자합계`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `재학생현황view`
--

DROP TABLE IF EXISTS `재학생현황view`;
/*!50001 DROP VIEW IF EXISTS `재학생현황view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `재학생현황view` AS SELECT 
 1 AS `단과대학`,
 1 AS `년도`,
 1 AS `기준`,
 1 AS `학과명`,
 1 AS `신설연도`,
 1 AS `학생정원`,
 1 AS `군휴학자`,
 1 AS `정원내`,
 1 AS `정원외`,
 1 AS `계`,
 1 AS `연번`,
 1 AS `전체재학생충원율`,
 1 AS `정원내재학생충원율`,
 1 AS `재학생충원율`,
 1 AS `T점수`,
 1 AS `입력부서`,
 1 AS `타학과전과자`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `전임교원확보율view`
--

DROP TABLE IF EXISTS `전임교원확보율view`;
/*!50001 DROP VIEW IF EXISTS `전임교원확보율view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `전임교원확보율view` AS SELECT 
 1 AS `단과대학`,
 1 AS `_5대계열`,
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `대학원생정원`,
 1 AS `대학원재학생`,
 1 AS `학생정원기준전임교원`,
 1 AS `재학생기준전임교원`,
 1 AS `전임교원확보율`,
 1 AS `T점수`,
 1 AS `연번`,
 1 AS `인정학생정원`,
 1 AS `교원_학부_정원`,
 1 AS `교원_학부_재학생`,
 1 AS `교원_대학원_정원`,
 1 AS `교원_대학원_재학생`,
 1 AS `교원_계_정원`,
 1 AS `교원_계_재학생`,
 1 AS `학생수_정원`,
 1 AS `학생수_재학생`,
 1 AS `확보율_정원`,
 1 AS `확보율_재학생`,
 1 AS `입력부서`,
 1 AS `신설연도`,
 1 AS `군휴학자`,
 1 AS `학생정원`,
 1 AS `재학생`,
 1 AS `학생정원계`,
 1 AS `재학생계`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `동아리view`
--

DROP TABLE IF EXISTS `동아리view`;
/*!50001 DROP VIEW IF EXISTS `동아리view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `동아리view` AS SELECT 
 1 AS `년도`,
 1 AS `단과대학`,
 1 AS `학과명`,
 1 AS `동아리참여비율`,
 1 AS `T점수`,
 1 AS `취업`,
 1 AS `학습`,
 1 AS `창업`,
 1 AS `문예`,
 1 AS `봉사`,
 1 AS `취미`,
 1 AS `기타`,
 1 AS `계`,
 1 AS `재학생수`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `연구비view`
--

DROP TABLE IF EXISTS `연구비view`;
/*!50001 DROP VIEW IF EXISTS `연구비view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `연구비view` AS SELECT 
 1 AS `단과대학`,
 1 AS `학문계열1`,
 1 AS `전임교원수`,
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `중앙정부`,
 1 AS `지자체`,
 1 AS `민간`,
 1 AS `외국`,
 1 AS `소계`,
 1 AS `연구비`,
 1 AS `T점수`,
 1 AS `연번`,
 1 AS `입력부서`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `강의담당view`
--

DROP TABLE IF EXISTS `강의담당view`;
/*!50001 DROP VIEW IF EXISTS `강의담당view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `강의담당view` AS SELECT 
 1 AS `단과대학`,
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `개설전공과목`,
 1 AS `개설교양필수과목`,
 1 AS `개설자유선택과목`,
 1 AS `연번`,
 1 AS `강의담당비율`,
 1 AS `T점수`,
 1 AS `입력부서`,
 1 AS `전공과목`,
 1 AS `교양필수과목`,
 1 AS `자유선택과목`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `교육프로그램view`
--

DROP TABLE IF EXISTS `교육프로그램view`;
/*!50001 DROP VIEW IF EXISTS `교육프로그램view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `교육프로그램view` AS SELECT 
 1 AS `단과대학`,
 1 AS `학과명`,
 1 AS `년도`,
 1 AS `총학생과`,
 1 AS `교수학습개발원`,
 1 AS `국제교류팀`,
 1 AS `취업창업지원과`,
 1 AS `학생과`,
 1 AS `입학과`,
 1 AS `기타`,
 1 AS `계`,
 1 AS `재학생수`,
 1 AS `T점수`,
 1 AS `교육프로그램참여비율`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `수상실적view`
--

DROP TABLE IF EXISTS `수상실적view`;
/*!50001 DROP VIEW IF EXISTS `수상실적view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `수상실적view` AS SELECT 
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `수상실적`,
 1 AS `T점수`,
 1 AS `단과대학`,
 1 AS `전임교원수`,
 1 AS `재학생수`,
 1 AS `교원국제`,
 1 AS `교원전국`,
 1 AS `교원지역`,
 1 AS `학생국제`,
 1 AS `학생전국`,
 1 AS `학생지역`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `신입생view`
--

DROP TABLE IF EXISTS `신입생view`;
/*!50001 DROP VIEW IF EXISTS `신입생view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `신입생view` AS SELECT 
 1 AS `단과대학`,
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `정원내입학자수`,
 1 AS `정원내모집인원`,
 1 AS `신입생충원율`,
 1 AS `T점수`,
 1 AS `연번`,
 1 AS `입력부서`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `외국인학생view`
--

DROP TABLE IF EXISTS `외국인학생view`;
/*!50001 DROP VIEW IF EXISTS `외국인학생view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `외국인학생view` AS SELECT 
 1 AS `년도`,
 1 AS `단과대학`,
 1 AS `학과명`,
 1 AS `외국인학생수`,
 1 AS `T점수`,
 1 AS `외국인학생비율`,
 1 AS `재학생수`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `현장실습view`
--

DROP TABLE IF EXISTS `현장실습view`;
/*!50001 DROP VIEW IF EXISTS `현장실습view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `현장실습view` AS SELECT 
 1 AS `단과대학`,
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `1학기`,
 1 AS `2학기`,
 1 AS `합계`,
 1 AS `장기1학기`,
 1 AS `장기2학기`,
 1 AS `장기합계`,
 1 AS `입력부서`,
 1 AS `대상학생수`,
 1 AS `이수학생비율`,
 1 AS `T점수`,
 1 AS `연번`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `캡스톤view`
--

DROP TABLE IF EXISTS `캡스톤view`;
/*!50001 DROP VIEW IF EXISTS `캡스톤view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `캡스톤view` AS SELECT 
 1 AS `단과대학`,
 1 AS `년도`,
 1 AS `학과명`,
 1 AS `이수1학기`,
 1 AS `이수2학기`,
 1 AS `이수합계`,
 1 AS `대상1학기`,
 1 AS `대상2학기`,
 1 AS `대상합계`,
 1 AS `연번`,
 1 AS `이수학생비율`,
 1 AS `T점수`,
 1 AS `입력부서`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `취업률외부view`
--

/*!50001 DROP VIEW IF EXISTS `취업률외부view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `취업률외부view` AS select `현황`.`대학명` AS `대학명`,`현황`.`학과명` AS `학과명`,`현황`.`졸업자` AS `졸업자`,`현황`.`2차유지취업률` AS `2차유지취업률`,`현황`.`T점수` AS `T점수`,round(`현황`.`취업률`,2) AS `취업률`,`취업자`.`건강보험db연계취업자` AS `건강보험db연계취업자`,`취업자`.`해외취업자` AS `해외취업자`,`취업자`.`영농업취업자` AS `영농업취업자`,`취업자`.`개인창작활동조사서` AS `개인창작활동조사서`,`취업자`.`1인창업자` AS `1인창업자`,`취업자`.`프리랜서` AS `프리랜서`,`취업자`.`계` AS `취업합계`,`제외자`.`진학자` AS `진학자`,`제외자`.`입대자` AS `입대자`,`제외자`.`취업불가능자` AS `취업불가능자`,`제외자`.`외국인유학생` AS `외국인유학생`,`제외자`.`건강보험직장가입제외대상` AS `건강보험직장가입제외대상`,`제외자`.`입학당시기취업자` AS `입학당시기취업자`,`제외자`.`계` AS `제외자합계` from ((`취업현황외부` `현황` left join `취업자외부` `취업자` on((`현황`.`학과명` = `취업자`.`학과명`))) join `취업제외자외부` `제외자` on((`현황`.`학과명` = `제외자`.`학과명`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `강의담당외부view`
--

/*!50001 DROP VIEW IF EXISTS `강의담당외부view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `강의담당외부view` AS select `개설`.`대학명` AS `대학명`,`개설`.`학과명` AS `학과명`,`개설`.`개설전공과목` AS `개설전공과목`,`개설`.`개설교양필수과목` AS `개설교양필수과목`,`개설`.`개설자유선택과목` AS `개설자유선택과목`,`개설`.`연번` AS `연번`,`개설`.`강의담당비율` AS `강의담당비율`,`개설`.`T점수` AS `T점수`,`개설`.`입력부서` AS `입력부서`,round(sum(if((`교원`.`구분` = '개설과목'),`교원`.`학점`,0)),1) AS `전공과목`,round(sum(if((`교원`.`구분` = '교양필수'),`교원`.`학점`,0)),1) AS `교양필수과목`,round(sum(if((`교원`.`구분` = '자유선택'),`교원`.`학점`,0)),1) AS `자유선택과목` from (`교원강의담당외부비율` `교원` join `개설강의담당외부비율` `개설` on((`교원`.`학과명` = `개설`.`학과명`))) group by `개설`.`학과명` order by `개설`.`학과명` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `설문조사view`
--

/*!50001 DROP VIEW IF EXISTS `설문조사view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `설문조사view` AS select `학과현황`.`단과대학` AS `단과대학`,`학과현황`.`년도` AS `년도`,`학과현황`.`학과명` AS `학과명`,`설문조사`.`참여학생수` AS `참여학생수`,`설문조사`.`설문조사총점` AS `설문조사총점`,`설문조사`.`연번` AS `연번`,round(`설문조사`.`학생만족도평가`,2) AS `학생만족도평가`,`설문조사`.`T점수` AS `T점수`,`설문조사`.`입력부서` AS `입력부서` from (`학과현황` left join `설문조사` on((`설문조사`.`학과명` = `학과현황`.`학과명`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `특허등록view`
--

/*!50001 DROP VIEW IF EXISTS `특허등록view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `특허등록view` AS select `schoolData`.`학과현황`.`년도` AS `년도`,`schoolData`.`교원현황`.`일학기` AS `일학기`,`schoolData`.`학과현황`.`단과대학` AS `단과대학`,`schoolData`.`학과현황`.`학과명` AS `학과명`,round((`a`.`기술정액` + `b`.`특허정액`),0) AS `기술이전`,`특허`.`국제` AS `국제`,`특허`.`국내` AS `국내`,`특허`.`T점수` AS `T점수`,`특허`.`수입료` AS `수입료` from ((`schoolData`.`교원현황` join `schoolData`.`학과현황`) join ((`schoolData`.`특허등록및기술이전수입료` `특허` left join (select `schoolData`.`기술이전`.`학과명` AS `학과명`,sum(`schoolData`.`기술이전`.`정액기술료`) AS `기술정액` from `schoolData`.`기술이전` group by `schoolData`.`기술이전`.`학과명`) `a` on((`특허`.`학과명` = `a`.`학과명`))) left join (select `schoolData`.`특허등록`.`학과명` AS `학과명`,sum(`schoolData`.`특허등록`.`정액기술료`) AS `특허정액` from `schoolData`.`특허등록` group by `schoolData`.`특허등록`.`학과명`) `b` on((`특허`.`학과명` = `b`.`학과명`)))) where ((`schoolData`.`학과현황`.`학과명` = `특허`.`학과명`) and (`schoolData`.`학과현황`.`학과명` = `schoolData`.`교원현황`.`학과명`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `강의공개view`
--

/*!50001 DROP VIEW IF EXISTS `강의공개view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `강의공개view` AS select `학과`.`단과대학` AS `단과대학`,`교원`.`일학기` AS `전임교원수`,`학과`.`년도` AS `년도`,`학과`.`학과명` AS `학과명`,`강의`.`강의동영상B` AS `강의동영상B`,`강의`.`이러닝강의C` AS `이러닝강의C`,`강의`.`강의자료D` AS `강의자료D`,`강의`.`강의동영상E` AS `강의동영상E`,`강의`.`이러닝강의F` AS `이러닝강의F`,`강의`.`강의자료G` AS `강의자료G`,`강의`.`연번` AS `연번`,`강의`.`입력부서` AS `입력부서`,round(`강의`.`강의공개실적`,2) AS `강의공개실적`,`강의`.`T점수` AS `T점수` from ((`학과현황` `학과` left join `교원현황` `교원` on(((`학과`.`학과명` = `교원`.`학과명`) and (`학과`.`년도` = `교원`.`년도`)))) left join `강의공개실적` `강의` on((`학과`.`학과명` = `강의`.`학과명`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `중도탈락률view`
--

/*!50001 DROP VIEW IF EXISTS `중도탈락률view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `중도탈락률view` AS select `학과`.`단과대학` AS `단과대학`,`학과`.`년도` AS `년도`,`학과`.`학과명` AS `학과명`,`중도탈락률`.`재적학생수` AS `재적학생수`,`중도탈락률`.`미등록` AS `미등록`,`중도탈락률`.`미복학` AS `미복학`,`중도탈락률`.`자퇴` AS `자퇴`,`중도탈락률`.`학사경고` AS `학사경고`,`중도탈락률`.`기타` AS `기타`,`중도탈락률`.`연번` AS `연번`,`중도탈락률`.`계` AS `계`,round(`중도탈락률`.`중도탈락률`,2) AS `중도탈락률`,`중도탈락률`.`T점수` AS `T점수`,`중도탈락률`.`입력부서` AS `입력부서`,`중도탈락률`.`타학과전과자` AS `타학과전과자` from (`학과현황` `학과` left join `중도탈락률` on((`중도탈락률`.`학과명` = `학과`.`학과명`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `교외장학금view`
--

/*!50001 DROP VIEW IF EXISTS `교외장학금view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `교외장학금view` AS select `장학금`.`년도` AS `년도`,`장학금`.`학과명` AS `학과명`,`장학금`.`교외장학금` AS `교외장학금`,round(`장학금`.`1인당교외장학금`,2) AS `1인당교외장학금`,`장학금`.`T점수` AS `T점수`,`장학금`.`연번` AS `연번`,`장학금`.`입력부서` AS `입력부서`,`학과`.`단과대학` AS `단과대학`,`학과`.`학과명` AS `학과`,sum(if((`재학생현황`.`기준` = '04.01'),`재학생현황`.`계`,0)) AS `1학기`,sum(if((`재학생현황`.`기준` = '10.01'),`재학생현황`.`계`,0)) AS `2학기`,round(avg(`재학생현황`.`계`),0) AS `평균` from ((`학과현황` `학과` left join `재학생현황` on((`재학생현황`.`학과명` = `학과`.`학과명`))) left join `교외장학금` `장학금` on((`학과`.`학과명` = `장학금`.`학과명`))) group by `학과`.`학과명` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `봉사실적view`
--

/*!50001 DROP VIEW IF EXISTS `봉사실적view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `봉사실적view` AS select `학과`.`년도` AS `년도`,`학과`.`단과대학` AS `단과대학`,`학과`.`학과명` AS `학과명`,sum(if((`실적`.`학기` = '1학기'),`실적`.`이수학점`,0)) AS `이수1학기`,(select `재학생현황`.`계` from `재학생현황` where ((`재학생현황`.`학과명` = `비율`.`학과명`) and (`재학생현황`.`기준` = '04.01'))) AS `재학생1학기`,sum(if((`실적`.`학기` = '2학기'),`실적`.`이수학점`,0)) AS `이수2학기`,(select `재학생현황`.`계` from `재학생현황` where ((`재학생현황`.`학과명` = `비율`.`학과명`) and (`재학생현황`.`기준` = '10.01'))) AS `재학생2학기`,round(`비율`.`이수학점`,3) AS `이수학점`,`비율`.`T점수` AS `T점수` from ((`학과현황` `학과` left join `봉사실적` `실적` on((`학과`.`학과명` = `실적`.`학과명`))) left join `봉사실적비율` `비율` on((`학과`.`학과명` = `비율`.`학과명`))) group by `학과`.`학과명` order by `실적`.`년도`,`학과`.`단과대학`,`학과`.`학과명` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `연구실적view`
--

/*!50001 DROP VIEW IF EXISTS `연구실적view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `연구실적view` AS select `학과`.`단과대학` AS `단과대학`,`학과`.`학문계열1` AS `학문계열1`,`학과`.`_5대계열` AS `_5대계열`,`교원`.`일학기` AS `전임교원수`,`학과`.`년도` AS `년도`,`학과`.`학과명` AS `학과명`,`연구실적`.`저서` AS `저서`,`연구실적`.`역서` AS `역서`,`연구실적`.`연구재단등재지` AS `연구재단등재지`,`연구실적`.`연구재단등재후보` AS `연구재단등재후보`,`연구실적`.`SCI급` AS `SCI급`,`연구실적`.`SCOPUS학술지` AS `SCOPUS학술지`,`연구실적`.`연구실적` AS `연구실적`,`연구실적`.`T점수` AS `T점수`,`연구실적`.`연번` AS `연번`,`연구실적`.`입력부서` AS `입력부서` from (`교원현황` `교원` join (`학과현황` `학과` left join `연구실적` on((`연구실적`.`학과명` = `학과`.`학과명`)))) where ((`학과`.`학과명` = `교원`.`학과명`) and (`학과`.`년도` = `교원`.`년도`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `발전기금view`
--

/*!50001 DROP VIEW IF EXISTS `발전기금view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `발전기금view` AS select (sum(`상세`.`금액`) DIV 1000) AS `발전기금모금액`,`교원`.`일학기` AS `전임교원수`,`학과`.`년도` AS `년도`,`학과`.`학과명` AS `학과명`,`발전`.`지정기부금` AS `지정기부금`,round(`발전`.`발전기금조성액`,0) AS `발전기금조성액`,`발전`.`T점수` AS `T점수`,`발전`.`연번` AS `연번`,`발전`.`입력부서` AS `입력부서`,`학과`.`단과대학` AS `단과대학` from (`교원현황` `교원` join ((`학과현황` `학과` left join `발전기금조성액` `발전` on((`발전`.`학과명` = `학과`.`학과명`))) left join `발전기금조성액상세자료` `상세` on((`학과`.`학과명` = `상세`.`학과명`)))) where ((`교원`.`년도` = `학과`.`년도`) and (`교원`.`학과명` = `학과`.`학과명`)) group by `학과`.`학과명` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `국책사업수주실적view`
--

/*!50001 DROP VIEW IF EXISTS `국책사업수주실적view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `국책사업수주실적view` AS select `학과현황`.`년도` AS `년도`,`학과현황`.`학과명` AS `학과명`,`실적`.`대학명의국책사업수주총액` AS `대학명의국책사업수주총액`,`실적`.`전임교원1인당국책사업수주총액` AS `전임교원1인당국책사업수주총액`,`실적`.`T점수` AS `T점수`,`실적`.`연번` AS `연번`,`실적`.`입력부서` AS `입력부서`,`교원현황`.`일학기` AS `전임교원수`,`학과현황`.`단과대학` AS `단과대학` from ((`학과현황` left join `전임교원1인당국책사업수주실적` `실적` on((`실적`.`학과명` = `학과현황`.`학과명`))) left join `교원현황` on((`실적`.`학과명` = `교원현황`.`학과명`))) order by `실적`.`년도`,`학과현황`.`단과대학`,`학과현황`.`학과명` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `취업률view`
--

/*!50001 DROP VIEW IF EXISTS `취업률view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `취업률view` AS select `학과`.`학문계열1` AS `학문계열1`,`학과`.`단과대학` AS `단과대학`,`학과`.`학과명` AS `학과명`,`학과`.`년도` AS `년도`,`현황`.`졸업자` AS `졸업자`,`현황`.`2차유지취업률` AS `2차유지취업률`,`현황`.`T점수` AS `T점수`,round(`현황`.`취업률`,2) AS `취업률`,`취업자`.`건강보험db연계취업자` AS `건강보험db연계취업자`,`취업자`.`해외취업자` AS `해외취업자`,`취업자`.`영농업취업자` AS `영농업취업자`,`취업자`.`취업인정자` AS `취업인정자`,`취업자`.`개인창작활동조사서` AS `개인창작활동조사서`,`취업자`.`1인창업자` AS `1인창업자`,`취업자`.`프리랜서` AS `프리랜서`,`취업자`.`계` AS `취업합계`,`제외자`.`진학자` AS `진학자`,`제외자`.`입대자` AS `입대자`,`제외자`.`취업불가능자` AS `취업불가능자`,`제외자`.`외국인유학생` AS `외국인유학생`,`제외자`.`건강보험직장가입제외대상` AS `건강보험직장가입제외대상`,`제외자`.`입학당시기취업자` AS `입학당시기취업자`,`제외자`.`계` AS `제외자합계` from (((`학과현황` `학과` left join `취업현황` `현황` on((`학과`.`학과명` = `현황`.`학과명`))) join `취업자` on((`현황`.`학과명` = `취업자`.`학과명`))) join `취업제외자` `제외자` on((`학과`.`학과명` = `제외자`.`학과명`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `재학생현황view`
--

/*!50001 DROP VIEW IF EXISTS `재학생현황view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `재학생현황view` AS select `학과`.`단과대학` AS `단과대학`,`학과`.`년도` AS `년도`,`재학생`.`기준` AS `기준`,`학과`.`학과명` AS `학과명`,`학과`.`신설연도` AS `신설연도`,`재학생`.`학생정원` AS `학생정원`,`재학생`.`군휴학자` AS `군휴학자`,`재학생`.`정원내` AS `정원내`,`재학생`.`정원외` AS `정원외`,`재학생`.`계` AS `계`,`재학생`.`연번` AS `연번`,round(`재학생`.`전체재학생충원율`,2) AS `전체재학생충원율`,round(`재학생`.`정원내재학생충원율`,2) AS `정원내재학생충원율`,round(`재학생`.`재학생충원율`,2) AS `재학생충원율`,`재학생`.`T점수` AS `T점수`,`재학생`.`입력부서` AS `입력부서`,`재학생`.`타학과전과자` AS `타학과전과자` from (`학과현황` `학과` left join `재학생현황` `재학생` on(((`학과`.`학과명` = `재학생`.`학과명`) and (`재학생`.`기준` = '04.01')))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `전임교원확보율view`
--

/*!50001 DROP VIEW IF EXISTS `전임교원확보율view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `전임교원확보율view` AS select `학과`.`단과대학` AS `단과대학`,`학과`.`_5대계열` AS `_5대계열`,`학과`.`년도` AS `년도`,`학과`.`학과명` AS `학과명`,`교원`.`대학원생정원` AS `대학원생정원`,`교원`.`대학원재학생` AS `대학원재학생`,`교원`.`학생정원기준전임교원` AS `학생정원기준전임교원`,`교원`.`재학생기준전임교원` AS `재학생기준전임교원`,round(`교원`.`전임교원확보율`,2) AS `전임교원확보율`,`교원`.`T점수` AS `T점수`,`교원`.`연번` AS `연번`,`교원`.`인정학생정원` AS `인정학생정원`,`교원`.`교원_학부_정원` AS `교원_학부_정원`,`교원`.`교원_학부_재학생` AS `교원_학부_재학생`,`교원`.`교원_대학원_정원` AS `교원_대학원_정원`,`교원`.`교원_대학원_재학생` AS `교원_대학원_재학생`,`교원`.`교원_계_정원` AS `교원_계_정원`,`교원`.`교원_계_재학생` AS `교원_계_재학생`,round(`교원`.`학생수_정원`,1) AS `학생수_정원`,round(`교원`.`학생수_재학생`,1) AS `학생수_재학생`,round(`교원`.`확보율_정원`,2) AS `확보율_정원`,round(`교원`.`확보율_재학생`,2) AS `확보율_재학생`,`교원`.`입력부서` AS `입력부서`,`학과`.`신설연도` AS `신설연도`,`재학생`.`군휴학자` AS `군휴학자`,`재학생`.`학생정원` AS `학생정원`,`재학생`.`계` AS `재학생`,(`교원`.`인정학생정원` + `교원`.`대학원생정원`) AS `학생정원계`,(`재학생`.`계` + `교원`.`대학원재학생`) AS `재학생계` from ((`학과현황` `학과` left join `전임교원확보율` `교원` on((`교원`.`학과명` = `학과`.`학과명`))) left join `재학생현황` `재학생` on(((`학과`.`년도` = `재학생`.`년도`) and (`재학생`.`학과명` = `학과`.`학과명`) and (`재학생`.`기준` = '04.01')))) order by `교원`.`년도`,`학과`.`단과대학`,`학과`.`학과명` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `동아리view`
--

/*!50001 DROP VIEW IF EXISTS `동아리view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `동아리view` AS select `학과`.`년도` AS `년도`,`학과`.`단과대학` AS `단과대학`,`학과`.`학과명` AS `학과명`,`참여`.`동아리참여비율` AS `동아리참여비율`,`참여`.`T점수` AS `T점수`,sum(if((`동아리`.`구분` = '취업'),`동아리`.`회원수`,0)) AS `취업`,sum(if((`동아리`.`구분` = '학습'),`동아리`.`회원수`,0)) AS `학습`,sum(if((`동아리`.`구분` = '창업'),`동아리`.`회원수`,0)) AS `창업`,sum(if((`동아리`.`구분` = '문예'),`동아리`.`회원수`,0)) AS `문예`,sum(if((`동아리`.`구분` = '봉사'),`동아리`.`회원수`,0)) AS `봉사`,sum(if((`동아리`.`구분` = '취미'),`동아리`.`회원수`,0)) AS `취미`,sum(if((`동아리`.`구분` = '기타'),`동아리`.`회원수`,0)) AS `기타`,sum(`동아리`.`회원수`) AS `계`,`재학생현황`.`계` AS `재학생수` from (((`학과현황` `학과` left join `동아리참여비율` `참여` on((`참여`.`학과명` = `학과`.`학과명`))) left join `동아리` on((`학과`.`학과명` = `동아리`.`학과명`))) left join `재학생현황` on(((`학과`.`학과명` = `재학생현황`.`학과명`) and (`학과`.`년도` = `재학생현황`.`년도`) and (`재학생현황`.`기준` = '04.01')))) group by `학과`.`학과명` order by `동아리`.`년도`,`학과`.`단과대학`,`학과`.`학과명` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `연구비view`
--

/*!50001 DROP VIEW IF EXISTS `연구비view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `연구비view` AS select `학과`.`단과대학` AS `단과대학`,`학과`.`학문계열1` AS `학문계열1`,`교원`.`일학기` AS `전임교원수`,`학과`.`년도` AS `년도`,`학과`.`학과명` AS `학과명`,`연구비`.`중앙정부` AS `중앙정부`,`연구비`.`지자체` AS `지자체`,`연구비`.`민간` AS `민간`,`연구비`.`외국` AS `외국`,(((`연구비`.`중앙정부` + `연구비`.`지자체`) + `연구비`.`민간`) + `연구비`.`외국`) AS `소계`,`연구비`.`연구비` AS `연구비`,`연구비`.`T점수` AS `T점수`,`연구비`.`연번` AS `연번`,`연구비`.`입력부서` AS `입력부서` from (`교원현황` `교원` join (`학과현황` `학과` left join `연구비` on((`연구비`.`학과명` = `학과`.`학과명`)))) where ((`학과`.`학과명` = `교원`.`학과명`) and (`학과`.`년도` = `교원`.`년도`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `강의담당view`
--

/*!50001 DROP VIEW IF EXISTS `강의담당view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `강의담당view` AS select `학과`.`단과대학` AS `단과대학`,`학과`.`년도` AS `년도`,`학과`.`학과명` AS `학과명`,`개설`.`개설전공과목` AS `개설전공과목`,`개설`.`개설교양필수과목` AS `개설교양필수과목`,`개설`.`개설자유선택과목` AS `개설자유선택과목`,`개설`.`연번` AS `연번`,round(`개설`.`강의담당비율`,2) AS `강의담당비율`,`개설`.`T점수` AS `T점수`,`개설`.`입력부서` AS `입력부서`,round(sum(if((`교원`.`구분` = '전공과목'),`교원`.`학점`,0)),1) AS `전공과목`,round(sum(if((`교원`.`구분` = '교양필수과목'),`교원`.`학점`,0)),1) AS `교양필수과목`,round(sum(if((`교원`.`구분` = '자유선택과목'),`교원`.`학점`,0)),1) AS `자유선택과목` from ((`학과현황` `학과` left join `교원강의담당비율` `교원` on(((`교원`.`년도` = `학과`.`년도`) and (`교원`.`학과명` = `학과`.`학과명`)))) left join `개설강의담당비율` `개설` on((`학과`.`학과명` = `개설`.`학과명`))) group by `학과`.`학과명` order by `교원`.`년도`,`학과`.`단과대학`,`학과`.`학과명` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `교육프로그램view`
--

/*!50001 DROP VIEW IF EXISTS `교육프로그램view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `교육프로그램view` AS select `학과`.`단과대학` AS `단과대학`,`학과`.`학과명` AS `학과명`,`학과`.`년도` AS `년도`,count(if((`운영`.`운영부서명` = '학생과'),`운영`.`연번`,NULL)) AS `총학생과`,count(if((`운영`.`운영부서명` = '교수학습개발원'),`운영`.`연번`,NULL)) AS `교수학습개발원`,count(if((`운영`.`운영부서명` = '국제교류팀'),`운영`.`연번`,NULL)) AS `국제교류팀`,count(if((`운영`.`운영부서명` = '취업창업지원과'),`운영`.`연번`,NULL)) AS `취업창업지원과`,count(if((`운영`.`운영부서명` = '교수학습개발원'),`운영`.`연번`,NULL)) AS `학생과`,count(if((`운영`.`운영부서명` = '글로벌입학본부 입학사정관실'),`운영`.`연번`,NULL)) AS `입학과`,count(if((`운영`.`운영부서명` = ' '),`운영`.`연번`,NULL)) AS `기타`,count(`운영`.`연번`) AS `계`,`재학생`.`계` AS `재학생수`,`비율`.`T점수` AS `T점수`,round(`비율`.`교육프로그램참여비율`,2) AS `교육프로그램참여비율` from (((`학과현황` `학과` left join `교육프로그램비율` `비율` on((`비율`.`학과명` = `학과`.`학과명`))) left join `교육프로그램운영내역` `운영` on((`운영`.`학과명` = `학과`.`학과명`))) left join `재학생현황` `재학생` on(((`학과`.`학과명` = `재학생`.`학과명`) and (`재학생`.`기준` = '04.01')))) group by `학과`.`학과명` order by `운영`.`년도`,`학과`.`단과대학`,`학과`.`학과명` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `수상실적view`
--

/*!50001 DROP VIEW IF EXISTS `수상실적view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `수상실적view` AS select `학과현황`.`년도` AS `년도`,`학과현황`.`학과명` AS `학과명`,round(`비율`.`수상실적`,2) AS `수상실적`,`비율`.`T점수` AS `T점수`,`학과현황`.`단과대학` AS `단과대학`,`교원현황`.`일학기` AS `전임교원수`,`재학생현황`.`계` AS `재학생수`,sum(if(((`수상실적`.`구분` = '국제대회') and (`수상실적`.`수상대상자` = '교원')),1.0,if(((`수상실적`.`구분` = '국제대회') and (`수상실적`.`수상대상자` = '교원재학생')),0.5,0))) AS `교원국제`,sum(if(((`수상실적`.`구분` = '전국대회') and (`수상실적`.`수상대상자` = '교원')),1.0,if(((`수상실적`.`구분` = '전국대회') and (`수상실적`.`수상대상자` = '교원재학생')),0.5,0))) AS `교원전국`,sum(if(((`수상실적`.`구분` = '지역또는교내대회') and (`수상실적`.`수상대상자` = '교원')),1.0,if(((`수상실적`.`구분` = '지역또는교내대회') and (`수상실적`.`수상대상자` = '교원재학생')),0.5,0))) AS `교원지역`,sum(if(((`수상실적`.`구분` = '국제대회') and (`수상실적`.`수상대상자` = '학생')),1.0,if(((`수상실적`.`구분` = '국제대회') and (`수상실적`.`수상대상자` = '교원재학생')),0.5,0))) AS `학생국제`,sum(if(((`수상실적`.`구분` = '전국대회') and (`수상실적`.`수상대상자` = '학생')),1.0,if(((`수상실적`.`구분` = '전국대회') and (`수상실적`.`수상대상자` = '교원재학생')),0.5,0))) AS `학생전국`,sum(if(((`수상실적`.`구분` = '지역또는교내대회') and (`수상실적`.`수상대상자` = '학생')),1.0,if(((`수상실적`.`구분` = '지역또는교내대회') and (`수상실적`.`수상대상자` = '교원재학생')),0.5,0))) AS `학생지역` from ((((`학과현황` left join `수상실적` on(((`학과현황`.`학과명` = `수상실적`.`학과명`) and (`수상실적`.`인정여부` = '인정')))) left join `수상실적비율` `비율` on((`수상실적`.`학과명` = `비율`.`학과명`))) left join `교원현황` on(((`학과현황`.`학과명` = `교원현황`.`학과명`) and (`학과현황`.`년도` = `교원현황`.`년도`)))) left join `재학생현황` on(((`학과현황`.`년도` = `재학생현황`.`년도`) and (`학과현황`.`학과명` = `재학생현황`.`학과명`) and (`재학생현황`.`기준` = '04.01')))) group by `학과현황`.`학과명` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `신입생view`
--

/*!50001 DROP VIEW IF EXISTS `신입생view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `신입생view` AS select `학과`.`단과대학` AS `단과대학`,`학과`.`년도` AS `년도`,`학과`.`학과명` AS `학과명`,`신입생`.`정원내입학자수` AS `정원내입학자수`,`신입생`.`정원내모집인원` AS `정원내모집인원`,round(`신입생`.`신입생충원율`,2) AS `신입생충원율`,`신입생`.`T점수` AS `T점수`,`신입생`.`연번` AS `연번`,`신입생`.`입력부서` AS `입력부서` from (`학과현황` `학과` left join `신입생현황` `신입생` on((`학과`.`학과명` = `신입생`.`학과명`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `외국인학생view`
--

/*!50001 DROP VIEW IF EXISTS `외국인학생view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `외국인학생view` AS select `학과`.`년도` AS `년도`,`학과`.`단과대학` AS `단과대학`,`학과`.`학과명` AS `학과명`,count(if((`학과`.`학과명` = `현황`.`학과명`),`현황`.`연번`,NULL)) AS `외국인학생수`,`비율`.`T점수` AS `T점수`,round(`비율`.`외국인학생비율`,2) AS `외국인학생비율`,`재학생`.`계` AS `재학생수` from (((`학과현황` `학과` left join `외국인학생현황` `현황` on((`학과`.`학과명` = `현황`.`학과명`))) left join `재학생현황` `재학생` on(((`학과`.`학과명` = `재학생`.`학과명`) and (`학과`.`년도` = `재학생`.`년도`) and (`재학생`.`기준` = '04.01')))) join `외국인학생비율` `비율`) where (`비율`.`학과명` = `학과`.`학과명`) group by `학과`.`학과명` order by `비율`.`년도`,`학과`.`단과대학`,`학과`.`학과명` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `현장실습view`
--

/*!50001 DROP VIEW IF EXISTS `현장실습view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `현장실습view` AS select `학과현황`.`단과대학` AS `단과대학`,`학과현황`.`년도` AS `년도`,`학과현황`.`학과명` AS `학과명`,`현장실습`.`1학기` AS `1학기`,`현장실습`.`2학기` AS `2학기`,(`현장실습`.`1학기` + `현장실습`.`2학기`) AS `합계`,`현장실습`.`장기1학기` AS `장기1학기`,`현장실습`.`장기2학기` AS `장기2학기`,(`현장실습`.`장기1학기` + `현장실습`.`장기2학기`) AS `장기합계`,`현장실습`.`입력부서` AS `입력부서`,`현장실습`.`대상학생수` AS `대상학생수`,round(`현장실습`.`이수학생비율`,2) AS `이수학생비율`,`현장실습`.`T점수` AS `T점수`,`현장실습`.`연번` AS `연번` from (`학과현황` left join `현장실습` on((`현장실습`.`학과명` = `학과현황`.`학과명`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `캡스톤view`
--

/*!50001 DROP VIEW IF EXISTS `캡스톤view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `캡스톤view` AS select `학과현황`.`단과대학` AS `단과대학`,`학과현황`.`년도` AS `년도`,`학과현황`.`학과명` AS `학과명`,`캡스톤디자인`.`이수1학기` AS `이수1학기`,`캡스톤디자인`.`이수2학기` AS `이수2학기`,(`캡스톤디자인`.`이수1학기` + `캡스톤디자인`.`이수2학기`) AS `이수합계`,`캡스톤디자인`.`대상1학기` AS `대상1학기`,`캡스톤디자인`.`대상2학기` AS `대상2학기`,(`캡스톤디자인`.`대상1학기` + `캡스톤디자인`.`대상2학기`) AS `대상합계`,`캡스톤디자인`.`연번` AS `연번`,round(`캡스톤디자인`.`이수학생비율`,2) AS `이수학생비율`,`캡스톤디자인`.`T점수` AS `T점수`,`캡스톤디자인`.`입력부서` AS `입력부서` from (`학과현황` left join `캡스톤디자인` on((`캡스톤디자인`.`학과명` = `학과현황`.`학과명`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-27 11:55:06
