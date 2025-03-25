CREATE DATABASE IF NOT EXISTS ks50team01db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ks50team01db;

-- MySQL dump 10.13  Distrib 9.2.0, for macos15.2 (arm64)
--
-- Host: 158.180.89.213    Database: ks50team01db
-- ------------------------------------------------------
-- Server version	8.0.41-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `PAYMENT_AUTHENTICATION_DATA`
--

DROP TABLE IF EXISTS `PAYMENT_AUTHENTICATION_DATA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PAYMENT_AUTHENTICATION_DATA` (
                                               `STLM_CERT_DATA_CD` varchar(200) NOT NULL,
                                               `STLM_CERT_FILE_CD` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                               `STLM_CERT_DATA_TYPE` varchar(200) NOT NULL,
                                               `FRCS_ADDR` varchar(200) NOT NULL,
                                               `FRCS_TELNO` varchar(11) NOT NULL,
                                               `ALLSTLM_PMNT` int NOT NULL,
                                               PRIMARY KEY (`STLM_CERT_DATA_CD`),
                                               KEY `FK_PAYMENT_AUTHENTICATION_DATA_PAYMENT_AUTHENTICATION_FILE` (`STLM_CERT_FILE_CD`),
                                               CONSTRAINT `FK_PAYMENT_AUTHENTICATION_DATA_PAYMENT_AUTHENTICATION_FILE` FOREIGN KEY (`STLM_CERT_FILE_CD`) REFERENCES `PAYMENT_AUTHENTICATION_FILE` (`STLM_CERT_FILE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAYMENT_AUTHENTICATION_DATA`
--

LOCK TABLES `PAYMENT_AUTHENTICATION_DATA` WRITE;
/*!40000 ALTER TABLE `PAYMENT_AUTHENTICATION_DATA` DISABLE KEYS */;
INSERT INTO `PAYMENT_AUTHENTICATION_DATA` VALUES ('STLM_CERT_DATA_001','STLM_CERT_FILE_001','전주박물관','(우)55070 전라북도 전주시 완산구 쑥고개로 249','063-223-565',10000),('STLM_CERT_DATA_002','STLM_CERT_FILE_002','한국소리문화의전당','전북특별자치도 전주시 덕진구 소리로31','063-270-800',20000),('STLM_CERT_DATA_003','STLM_CERT_FILE_003','한옥마을','전주시 완산구 기린대로 99','063-282-133',30000),('STLM_CERT_DATA_004','STLM_CERT_FILE_004','전주부채문화관','전북 전주시 완산구 경기전길 93','063-231-177',10000),('STLM_CERT_DATA_005','STLM_CERT_FILE_005','한국전통문화전당','전북 전주시 완산구 현무1길 20','063-281-150',20000),('STLM_CERT_DATA_006','STLM_CERT_FILE_006','전주시 양묘장','전북 전주시 덕진구 우아동1가 851','063-281-288',30000);
/*!40000 ALTER TABLE `PAYMENT_AUTHENTICATION_DATA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FAQ_CATEGORY`
--

DROP TABLE IF EXISTS `FAQ_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FAQ_CATEGORY` (
                                `FAQ_CTGRY_NO` varchar(20) NOT NULL,
                                `REG_PF_ID` varchar(20) NOT NULL,
                                `FAQ_CTGRY_TYPE` varchar(20) NOT NULL,
                                `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                `REG_YMD` timestamp NOT NULL,
                                `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                PRIMARY KEY (`FAQ_CTGRY_NO`),
                                KEY `FK_MM_FC` (`REG_PF_ID`),
                                CONSTRAINT `FK_MM_FC` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FAQ_CATEGORY`
--

LOCK TABLES `FAQ_CATEGORY` WRITE;
/*!40000 ALTER TABLE `FAQ_CATEGORY` DISABLE KEYS */;
INSERT INTO `FAQ_CATEGORY` VALUES ('FAQ_CTGRY_1','admin001','계정 관리','admin001','Y','2023-12-31 15:00:00',NULL),('FAQ_CTGRY_2','admin001','이용 문의','admin001','Y','2023-12-31 15:00:00',NULL),('FAQ_CTGRY_3','admin001','건의 / 제안','admin001','Y','2023-12-31 15:00:00',NULL),('FAQ_CTGRY_4','admin001','결제 / 환불','admin001','Y','2023-12-31 15:00:00',NULL);
/*!40000 ALTER TABLE `FAQ_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `code` varchar(10) NOT NULL,
                        `name` varchar(50) NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'1','서울'),(2,'2','인천'),(3,'3','대전'),(4,'4','대구'),(5,'5','광주'),(6,'6','부산'),(7,'7','울산'),(8,'8','세종특별자치시'),(9,'31','경기도'),(10,'32','강원특별자치도'),(11,'33','충청북도'),(12,'34','충청남도'),(13,'35','경상북도'),(14,'36','경상남도'),(15,'37','전북특별자치도'),(16,'38','전라남도'),(17,'39','제주도');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PLATFORM_PARTNERSHIP`
--

DROP TABLE IF EXISTS `PLATFORM_PARTNERSHIP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PLATFORM_PARTNERSHIP` (
                                        `PF_MNGR_RANK` varchar(20) NOT NULL,
                                        `REG_PF_ID` varchar(20) NOT NULL,
                                        `PRTNR_BPLC_CD` varchar(20) NOT NULL,
                                        `TRIP_LCTGRY_CD` varchar(20) NOT NULL,
                                        `TRIP_LCTGRY_NM` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                        `BZMN_CONM` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                        `RGN_SCTGRY_NM` varchar(50) NOT NULL,
                                        `PRTNR_BRD_NM` varchar(50) NOT NULL,
                                        `ARTCL_RANK_TOP_FIVE` tinyint NOT NULL,
                                        `BRD_CTRT_STRT_YMD` timestamp NOT NULL,
                                        `BRD_CTRT_END_YMD` timestamp NOT NULL,
                                        `BRD_TYPE` varchar(50) NOT NULL,
                                        `BRD_CN` varchar(50) NOT NULL,
                                        `AD_STRT_YMD` timestamp NOT NULL,
                                        `AD_END_YMD` timestamp NOT NULL,
                                        `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                        `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                        `REG_YMD` timestamp NOT NULL,
                                        `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                        PRIMARY KEY (`PF_MNGR_RANK`),
                                        KEY `FK_MEMBER_MANAGE_TO_PLATFORM_PARTNERSHIP_1` (`REG_PF_ID`),
                                        KEY `FK_PARTNER_BUSINESS_PLACE_TO_PLATFORM_PARTNERSHIP_1` (`PRTNR_BPLC_CD`),
                                        KEY `FK_TRIP_LARGE_CATEGORY_TO_PLATFORM_PARTNERSHIP_1` (`TRIP_LCTGRY_CD`),
                                        CONSTRAINT `FK_MEMBER_MANAGE_TO_PLATFORM_PARTNERSHIP_1` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                        CONSTRAINT `FK_PARTNER_BUSINESS_PLACE_TO_PLATFORM_PARTNERSHIP_1` FOREIGN KEY (`PRTNR_BPLC_CD`) REFERENCES `PARTNER_BUSINESS_PLACE` (`PRTNR_BPLC_CD`),
                                        CONSTRAINT `FK_TRIP_LARGE_CATEGORY_TO_PLATFORM_PARTNERSHIP_1` FOREIGN KEY (`TRIP_LCTGRY_CD`) REFERENCES `TRIP_LARGE_CATEGORY` (`TRIP_LCTGRY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PLATFORM_PARTNERSHIP`
--

LOCK TABLES `PLATFORM_PARTNERSHIP` WRITE;
/*!40000 ALTER TABLE `PLATFORM_PARTNERSHIP` DISABLE KEYS */;
INSERT INTO `PLATFORM_PARTNERSHIP` VALUES ('pp_1','admin001','hotel_001','TRIP_LCTGRY_03','숙소','전주 그랜드힐스턴','중앙동','한국관광공사',1,'2020-02-29 15:00:00','2025-02-28 15:00:00','광고제휴','예약할인','2020-02-29 15:00:00','2025-02-28 15:00:00',NULL,'Y','2024-05-20 08:17:48',NULL),('pp_2','admin001','hotel_002','TRIP_LCTGRY_03','숙소','전주 더메이 호텔','중화산2동','야놀자',2,'2020-02-29 15:00:00','2025-02-28 15:00:00','광고 제휴','시즌 할인','2024-02-29 15:00:00','2024-03-31 15:00:00',NULL,'Y','2024-01-31 15:00:00',NULL),('pp_3','admin001','hotel_003','TRIP_LCTGRY_03','숙소','전주 그랜드힐스턴','서신동','야놀자',3,'2020-02-29 15:00:00','2025-02-28 15:00:00','광고 제휴','예약 할인','2024-02-29 15:00:00','2024-03-31 15:00:00',NULL,'Y','2024-01-31 15:00:00',NULL),('pp_4','admin001','RSTRN_001','TRIP_LCTGRY_02','음식점','김해뒷고기','서신동','배달의민족',1,'2020-02-29 15:00:00','2025-02-28 15:00:00','광고 제휴','시즌 할인','2024-02-29 15:00:00','2024-03-31 15:00:00',NULL,'Y','2024-01-31 15:00:00',NULL),('pp_5','admin001','RSTRN_002','TRIP_LCTGRY_02','음식점','오일내','평화1동','요기요',2,'2020-02-29 15:00:00','2025-02-28 15:00:00','광고 제휴','시즌 할인','2024-02-29 15:00:00','2024-03-31 15:00:00',NULL,'Y','2024-01-31 15:00:00',NULL),('pp_6','admin001','RSTRN_003','TRIP_LCTGRY_02','음식점','트럼펫','효자1동','요기요',3,'2020-02-29 15:00:00','2025-02-28 15:00:00','광고 제휴','시즌 할인','2024-02-29 15:00:00','2024-03-31 15:00:00',NULL,'Y','2024-01-31 15:00:00',NULL),('pp_7','admin001','tour_001','TRIP_LCTGRY_01','관광지','경기전','평화1동','한국관광공사',1,'2020-02-29 15:00:00','2025-02-28 15:00:00','광고 제휴','예약 할인','2024-02-29 15:00:00','2024-03-31 15:00:00',NULL,'Y','2024-01-31 15:00:00',NULL),('pp_8','admin001','tour_002','TRIP_LCTGRY_01','관광지','수목원','효자1동','한국관광공사',2,'2020-02-29 15:00:00','2025-02-28 15:00:00','광고 제휴','예약 할인','2024-02-29 15:00:00','2024-03-31 15:00:00',NULL,'Y','2024-01-31 15:00:00',NULL),('pp_9','admin001','hotel_001','TRIP_LCTGRY_01','관광지','김밥천국','중앙동','한국관광공사',10,'2020-02-29 15:00:00','2025-02-28 15:00:00','광고제휴','예약할인','2020-02-29 15:00:00','2025-02-28 15:00:00',NULL,'Y','2024-05-28 07:18:43',NULL);
/*!40000 ALTER TABLE `PLATFORM_PARTNERSHIP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOGIN_LOG`
--

DROP TABLE IF EXISTS `LOGIN_LOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LOGIN_LOG` (
                             `LGN_NO` varchar(20) NOT NULL,
                             `ALL_ID` varchar(20) NOT NULL,
                             `LGN_DT` timestamp NOT NULL,
                             `LGT_DT` timestamp NULL DEFAULT NULL,
                             `CNTN_DT` timestamp NULL DEFAULT NULL,
                             `LGN_IP_ADDR` char(15) NOT NULL,
                             PRIMARY KEY (`LGN_NO`),
                             KEY `FK_MEMBER_MANAGE_TO_LOGIN_LOG_1` (`ALL_ID`),
                             CONSTRAINT `FK_MEMBER_MANAGE_TO_LOGIN_LOG_1` FOREIGN KEY (`ALL_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOGIN_LOG`
--

LOCK TABLES `LOGIN_LOG` WRITE;
/*!40000 ALTER TABLE `LOGIN_LOG` DISABLE KEYS */;
INSERT INTO `LOGIN_LOG` VALUES ('LGN_1','admin001','2024-05-03 00:11:43','2024-05-09 03:07:44','2024-05-09 03:07:44','101.183.187.46'),('LGN_10','business003','2024-05-03 00:11:51','2024-05-09 03:07:45',NULL,'42.222.78.249'),('LGN_11','business004','2024-05-02 15:11:57','2024-05-09 03:07:46',NULL,'221.133.90.193'),('LGN_12','business005','2024-05-02 15:12:03','2024-05-09 03:07:46',NULL,'113.19.7.107'),('LGN_2','member001','2024-05-03 00:12:08','2024-05-09 03:07:47',NULL,'208.174.189.183'),('LGN_3','member002','2024-05-03 00:12:15','2024-05-09 03:07:47',NULL,'54.166.10.60'),('LGN_4','member003','2024-05-03 00:12:18','2024-05-09 03:07:48',NULL,'82.62.128.172'),('LGN_5','admin001','2024-05-03 00:12:23','2024-05-09 03:07:48',NULL,'76.37.234.41'),('LGN_6','member005','2024-05-03 00:12:26','2024-05-09 03:07:48',NULL,'125.225.209.36'),('LGN_7','member006','2024-05-03 00:12:29','2024-05-09 03:07:51',NULL,'102.90.48.142'),('LGN_8','business001','2024-05-03 00:12:32','2024-05-09 03:07:50',NULL,'138.89.136.103'),('LGN_9','business002','2024-05-03 00:12:36','2024-05-09 03:07:49',NULL,'233.203.50.143');
/*!40000 ALTER TABLE `LOGIN_LOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REGION_LARGE_CATEGORY`
--

DROP TABLE IF EXISTS `REGION_LARGE_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REGION_LARGE_CATEGORY` (
                                         `RGN_LCTGRY_CD` varchar(200) NOT NULL,
                                         `REG_PF_ID` varchar(20) NOT NULL,
                                         `RGN_LCTGRY_NM` varchar(200) NOT NULL,
                                         `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                         `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                         `REG_YMD` timestamp NOT NULL,
                                         `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                         PRIMARY KEY (`RGN_LCTGRY_CD`),
                                         KEY `FK_MEMBER_MANAGE_TO_REGION_LARGE_CATEGORY_1` (`REG_PF_ID`),
                                         CONSTRAINT `FK_MEMBER_MANAGE_TO_REGION_LARGE_CATEGORY_1` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REGION_LARGE_CATEGORY`
--

LOCK TABLES `REGION_LARGE_CATEGORY` WRITE;
/*!40000 ALTER TABLE `REGION_LARGE_CATEGORY` DISABLE KEYS */;
INSERT INTO `REGION_LARGE_CATEGORY` VALUES ('RGN_LCTGRY_002','admin001','부산광역시','','Y','2023-01-14 15:00:00',NULL),('RGN_LCTGRY_003','admin001','대구광역시','','Y','2023-01-15 15:00:00',NULL),('RGN_LCTGRY_004','admin001','인천광역시','','Y','2023-01-16 15:00:00',NULL),('RGN_LCTGRY_005','admin001','광주광역시','','Y','2023-01-17 15:00:00',NULL),('RGN_LCTGRY_006','admin001','대전광역시','','Y','2023-01-18 15:00:00',NULL),('RGN_LCTGRY_007','admin001','울산광역시','','Y','2023-01-19 15:00:00',NULL),('RGN_LCTGRY_008','admin001','세종특별자치시','','Y','2023-01-20 15:00:00',NULL),('RGN_LCTGRY_009','admin001','경기도','','Y','2023-01-21 15:00:00',NULL),('RGN_LCTGRY_010','admin001','충청북도','','Y','2023-01-22 15:00:00',NULL),('RGN_LCTGRY_011','admin001','충청남도','','Y','2023-01-23 15:00:00',NULL),('RGN_LCTGRY_012','admin001','전북특별자치도','','Y','2023-01-24 15:00:00',NULL),('RGN_LCTGRY_013','admin001','전라남도','','Y','2023-01-25 15:00:00',NULL),('RGN_LCTGRY_014','admin001','경상북도','','Y','2023-01-26 15:00:00',NULL),('RGN_LCTGRY_015','admin001','경상남도','','Y','2023-01-27 15:00:00',NULL),('RGN_LCTGRY_016','admin001','제주특별자치도','','Y','2023-01-28 15:00:00',NULL),('RGN_LCTGRY_017','admin001','강원특별자치도','','Y','2023-01-29 15:00:00',NULL);
/*!40000 ALTER TABLE `REGION_LARGE_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QNA_PROGRESS`
--

DROP TABLE IF EXISTS `QNA_PROGRESS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QNA_PROGRESS` (
                                `QNA_PRGRS_NO` varchar(20) NOT NULL,
                                `REG_PF_ID` varchar(20) NOT NULL,
                                `QNA_PRGRS_CTGRY` varchar(20) NOT NULL,
                                `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                `REG_YMD` timestamp NOT NULL,
                                `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                PRIMARY KEY (`QNA_PRGRS_NO`),
                                KEY `FK_MM_QP` (`REG_PF_ID`),
                                CONSTRAINT `FK_MM_QP` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QNA_PROGRESS`
--

LOCK TABLES `QNA_PROGRESS` WRITE;
/*!40000 ALTER TABLE `QNA_PROGRESS` DISABLE KEYS */;
INSERT INTO `QNA_PROGRESS` VALUES ('QNA_PRGRS_1','admin001','접수','admin001','2024-05-21 04:58:44',NULL),('QNA_PRGRS_2','admin001','검토중','admin001','2024-05-21 04:58:56',NULL),('QNA_PRGRS_3','admin001','답변완료','admin001','2024-05-21 04:59:08',NULL);
/*!40000 ALTER TABLE `QNA_PROGRESS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FREQUENTLY_ASKED_QUESTIONS`
--

DROP TABLE IF EXISTS `FREQUENTLY_ASKED_QUESTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FREQUENTLY_ASKED_QUESTIONS` (
                                              `FAQ_NO` varchar(20) NOT NULL,
                                              `REG_PF_ID` varchar(20) NOT NULL,
                                              `FAQ_CTGRY_NO` varchar(20) NOT NULL,
                                              `FAQ_TTL` varchar(50) NOT NULL,
                                              `FAQ_CN` varchar(500) NOT NULL,
                                              `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                              `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                              `REG_YMD` timestamp NOT NULL,
                                              `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                              PRIMARY KEY (`FAQ_NO`),
                                              KEY `REG_PF_ID` (`REG_PF_ID`),
                                              KEY `FAQ_CTGRY_NO` (`FAQ_CTGRY_NO`),
                                              CONSTRAINT `FK_FREQUENTLY_ASKED_QUESTIONS_FAQ_CATEGORY` FOREIGN KEY (`FAQ_CTGRY_NO`) REFERENCES `FAQ_CATEGORY` (`FAQ_CTGRY_NO`),
                                              CONSTRAINT `FK_FREQUENTLY_ASKED_QUESTIONS_MEMBER_MANAGE` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FREQUENTLY_ASKED_QUESTIONS`
--

LOCK TABLES `FREQUENTLY_ASKED_QUESTIONS` WRITE;
/*!40000 ALTER TABLE `FREQUENTLY_ASKED_QUESTIONS` DISABLE KEYS */;
INSERT INTO `FREQUENTLY_ASKED_QUESTIONS` VALUES ('FAQ_1','id001','FAQ_CTGRY_1','회원 탈퇴는 어떻게 하나요?','로그인 후 [마이 페이지] > [회원탈퇴]에서 회원 탈퇴를 하실 수 있습니다.\r<br>\r<br>회원탈퇴를 하시면 포인트와 상품은 소멸되어 재가입 하시더라도 복구되지 않으므로 유의하시기 바랍니다.\r<br>한번 탈퇴하신 후에는 해당 아이디로 재가입이 어렵습니다.\r<br>','id001','Y','2024-05-28 00:16:52','2024-05-29 03:39:03'),('FAQ_2','id001','FAQ_CTGRY_1','아이디 비밀번호를 분실했어요!','홈페이지 메인 로그인 화면 하단에 보이는 [아이디/비밀번호 찾기] 버튼을 통해 \r<br>가입 당시 등록하신 이름과 이메일을 입력하시면 아이디와 비밀번호를 찾으실 수 있습니다.','id001','Y','2024-05-28 00:17:29','2024-05-29 03:50:15'),('FAQ_3','id001','FAQ_CTGRY_1','계정의 비밀번호를 분실했어요','계정의 비밀번호를 아래의 방법으로 수정할 수 있습니다. \r<br>로그인 > [마이페이지] > [회원정보 수정]\r<br>',NULL,'Y','2024-05-28 00:18:25',NULL),('FAQ_4','id001','FAQ_CTGRY_1','계정의 비밀번호를 수정하고 싶어요','계정의 비밀번호를 다음 방법으로 수정할 수 있습니다.\r<br>로그인 > [마이페이지] > [회원정보 수정]',NULL,'Y','2024-05-28 00:19:07',NULL),('FAQ_5','id001','FAQ_CTGRY_4','환불 신청을 했는데 아직 환불 받지 못했어요','예약하신 숙소 / 투어 / 티켓 예약이 취소되었더라도 결제 금액 환불까지 소요되는 시간은 카드사에 따라 다를 수 있습니다. \r<br>\r<br>일반적으로 신용카드는 승인 취소 및 환불 처리까지 카드사의 영업일을 기준으로 길게는 약 10~15일 가량이 소요되지만, \r<br>일부 카드사의 경우 영업일을 기준으로 최대 30~45일까지 소요될 수 있습니다.\r<br>\r<br>','id001','Y','2024-05-28 00:20:08','2024-05-28 06:08:26'),('FAQ_6','id001','FAQ_CTGRY_1','계정의 비밀번호를 찾고 싶어요','다음 방법으로 비밀번호를 찾을 수 있습니다. \r\n<br>1. 로그인을 클릭합니다. \r\n<br>2. 비밀번호 찾기 버튼을 클릭합니다. \r\n<br>3. 아이디와 이메일 입력 후 이메일로 임시 비밀번호를 확인합니다.','id001','Y','2024-05-28 00:21:14','2024-05-28 00:23:27'),('FAQ_7','id001','FAQ_CTGRY_2','예약 확정 후, 예약 정보를 수정할 수 있나요?','해당 문의 사항은 고객센터로 문의해주세요. \r<br>문제 해결을 위해 최선을 다해 도와드리겠습니다.\r<br>',NULL,'Y','2024-05-28 00:22:29',NULL),('FAQ_8','id001','FAQ_CTGRY_3','장소 정보가 잘못 표기되어 있어요','정보가 잘못된 장소를 발견하셨다면, 해당 장소의 우측 상단 > [정보 수정 제안]을 통해 알려주세요.','id001','Y','2024-05-28 00:30:47','2024-05-28 00:35:19');
/*!40000 ALTER TABLE `FREQUENTLY_ASKED_QUESTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INDIVIDUAL_TRIP_PLAN`
--

DROP TABLE IF EXISTS `INDIVIDUAL_TRIP_PLAN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INDIVIDUAL_TRIP_PLAN` (
                                        `TRIP_PLN_NO` varchar(20) NOT NULL,
                                        `MBR_ID` varchar(20) NOT NULL,
                                        `RGN_SCTGRY_CD` varchar(20) NOT NULL,
                                        `TRIP_PLN_BGNG_YMD` date DEFAULT NULL,
                                        `TRIP_PLN_END_YMD` date DEFAULT NULL,
                                        `TRIP_PLN_DAY` tinyint NOT NULL,
                                        `TRIP_PLN_TTL` varchar(50) NOT NULL,
                                        `TRIP_PLN_THEME` varchar(20) DEFAULT NULL,
                                        `TRIP_PLN_NOPE` tinyint NOT NULL,
                                        `TRIP_PLN_GROUP` varchar(20) DEFAULT NULL,
                                        `TRIP_PLN_PART_MBR` varchar(20) DEFAULT NULL,
                                        `AVAILABLE_BUDGET` int NOT NULL,
                                        `TOTAL_AVAIL_BUDGET` int NOT NULL,
                                        `TOTAL_PLNNED_BUDGET` int NOT NULL,
                                        `REMAINING_BUDGET` int NOT NULL,
                                        `IN_PRGRS` varchar(10) NOT NULL,
                                        `SHARED_YN` char(1) NOT NULL DEFAULT 'Y',
                                        `MDFCN_MBR_ID` varchar(20) NOT NULL,
                                        `REG_YMD` timestamp NOT NULL,
                                        `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                        PRIMARY KEY (`TRIP_PLN_NO`),
                                        KEY `FK_MEMBER_MANAGE_TO_INDIVIDUAL_TRIP_PLAN_1` (`MBR_ID`),
                                        KEY `FK_REGION_SMALL_CATEGORY_TO_INDIVIDUAL_TRIP_PLAN_1` (`RGN_SCTGRY_CD`),
                                        CONSTRAINT `FK_MEMBER_MANAGE_TO_INDIVIDUAL_TRIP_PLAN_1` FOREIGN KEY (`MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                        CONSTRAINT `FK_REGION_SMALL_CATEGORY_TO_INDIVIDUAL_TRIP_PLAN_1` FOREIGN KEY (`RGN_SCTGRY_CD`) REFERENCES `REGION_SMALL_CATEGORY` (`RGN_SCTGRY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INDIVIDUAL_TRIP_PLAN`
--

LOCK TABLES `INDIVIDUAL_TRIP_PLAN` WRITE;
/*!40000 ALTER TABLE `INDIVIDUAL_TRIP_PLAN` DISABLE KEYS */;
INSERT INTO `INDIVIDUAL_TRIP_PLAN` VALUES ('ind_trip_1','id001','RGN_SCTGRY_0012','2023-06-01','2023-06-03',3,'여름 전주 여행',NULL,4,NULL,NULL,3000000,12000000,10000000,2000000,'작성중','Y','id001','2023-03-01 03:39:22','2023-04-15 03:39:22'),('ind_trip_2','member004','RGN_SCTGRY_005','2023-07-15','2023-07-20',5,'제주도 자연 탐방',NULL,2,NULL,NULL,500000,1000000,800000,200000,'작성 완료','N','member004','2023-04-09 15:00:00','2023-05-19 15:00:00'),('ind_trip_3','member005','RGN_SCTGRY_008','2023-08-10','2023-08-13',3,'부산 해변에서의 휴식',NULL,1,NULL,NULL,2000000,2000000,1500000,500000,'작성 완료','Y','member005','2023-05-14 15:00:00','2023-05-31 15:00:00');
/*!40000 ALTER TABLE `INDIVIDUAL_TRIP_PLAN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LODGING_INFO`
--

DROP TABLE IF EXISTS `LODGING_INFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LODGING_INFO` (
                                `LODGING_INFO_CD` varchar(200) NOT NULL,
                                `REG_PF_BIZ_ID` varchar(20) NOT NULL,
                                `TRIP_SCTGRY_CD` varchar(200) NOT NULL,
                                `PRTNR_BPLC_CD` varchar(200) NOT NULL,
                                `PRTNR_BRD_NM` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                `RGN_SCTGRY_NM` varchar(200) NOT NULL,
                                `LODGING_NAME` varchar(200) NOT NULL,
                                `LODGING_ADDR` varchar(200) NOT NULL,
                                `LODGING_DADDR` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                `LODGING_CONTACT1` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                `LODGING_CONTACT2` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                `LODGING_SITE` varchar(200) DEFAULT NULL,
                                `LODGING_ETC` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                `LODGING_FAVS_TOTAL` varchar(200) DEFAULT NULL,
                                `MDFCN_PF_BIZ_ID` varchar(20) DEFAULT NULL,
                                `ACT_YN` char(1) DEFAULT 'Y',
                                `REG_YMD` timestamp NOT NULL,
                                `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                PRIMARY KEY (`LODGING_INFO_CD`),
                                KEY `FK_MM_LI` (`REG_PF_BIZ_ID`),
                                KEY `FK_TSC_LI` (`TRIP_SCTGRY_CD`),
                                KEY `FK_PBP_LI` (`PRTNR_BPLC_CD`),
                                CONSTRAINT `FK_MM_LI` FOREIGN KEY (`REG_PF_BIZ_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                CONSTRAINT `FK_PBP_LI` FOREIGN KEY (`PRTNR_BPLC_CD`) REFERENCES `PARTNER_BUSINESS_PLACE` (`PRTNR_BPLC_CD`),
                                CONSTRAINT `FK_TSC_LI` FOREIGN KEY (`TRIP_SCTGRY_CD`) REFERENCES `TRIP_SMALL_CATEGORY` (`TRIP_SCTGRY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LODGING_INFO`
--

LOCK TABLES `LODGING_INFO` WRITE;
/*!40000 ALTER TABLE `LODGING_INFO` DISABLE KEYS */;
INSERT INTO `LODGING_INFO` VALUES ('ln_1','admin001','TRIP_SCTGRY_020','hotel_001','','덕진동','전주 스테이리봄','전북 전주시 덕진구 두간로 10','스테이리봄','050-5053-7356','010-7667-8934','https://www.instagram.com/stay_rebom/','','','','Y','2020-09-02 15:00:00','2021-05-31 15:00:00'),('ln_10','admin001','TRIP_SCTGRY_021','hotel_003','','덕진동','전주 바른숙박','전북 전주시 덕진구 산정1길 32','바른숙박','0503-5051-5334','','https://jjbarunstay.modoo.at/','','','','Y','2020-09-09 15:00:00','2021-06-07 15:00:00'),('ln_11','admin001','TRIP_SCTGRY_022','hotel_001','','덕진동','전주 바라한','전라북도 전주시 덕진구 용산2길 17-5','바라한호텔','0503-5050-0927','0507-1393-0118','','','','','Y','2020-09-09 15:00:00','2021-06-07 15:00:00'),('ln_12','admin001','TRIP_SCTGRY_023','hotel_002','','덕진동','전주 라베호텔','전라북도 전주시 완산구 한두평2길 8-5','라베호텔','063-225-0125','0507-1493-0169','','','','','Y','2020-09-09 15:00:00','2021-06-07 15:00:00'),('ln_13','admin001','TRIP_SCTGRY_023','hotel_003','','덕진동','전주 관광호텔 꽃심','전라북도 전주시 완산구 팔달로 171','호텔 꽃심','1600-3886','','','','','','Y','2020-09-09 15:00:00','2021-06-07 15:00:00'),('ln_2','admin001','TRIP_SCTGRY_021','hotel_001','','덕진동','전주 그랜드힐스턴','전라북도 전주시 완산구 서곡5길 22-5','그랜드힐스턴','063-251-8000','','grandhillstone.com','','','','Y','2020-09-03 15:00:00','2021-06-01 15:00:00'),('ln_3','admin001','TRIP_SCTGRY_022','hotel_003','','덕진동','전주 Stay테오리아','전라북도 전주시 완산구 동문길 51-8','테오리아','나는바보입니다','','https://www.instagram.com/stay_theoria/','','','','Y','2020-09-04 15:00:00','2024-05-29 02:07:11'),('ln_4','admin001','TRIP_SCTGRY_023','hotel_001','','덕진동','전주 더메이 호텔','전라북도 전주시 덕진구 기린대로 800','더메이 호텔','063-246-5555','','https://themay.co.kr/','체크인 15:00 이후, 체크아웃 11:00 이전엑스트라베드 추가 시 55,000원부모 동반 만 4세 이하 아동 무료 동반 투숙 가능  치약, 칫솔 유료  디파짓 제도  무료 주차  무료 Wi-Fi\r\n  전 객실 금연','','','Y','2020-09-05 15:00:00','2021-06-03 15:00:00'),('ln_5','admin001','TRIP_SCTGRY_020','hotel_002','','덕진동','전주 브라운도트 전주덕진점','전라북도 전주시 덕진구 기린대로 534','브라운도트 덕진점','0503-5052-9743','0507-1423-7002','','','','','Y','2020-09-06 15:00:00','2021-06-04 15:00:00'),('ln_55','admin001','TRIP_SCTGRY_020','hotel_003',NULL,'중화산동','우리집집집','으로가자','투피엠','123','456','test','',NULL,NULL,'Y','2024-05-29 05:38:42','2024-05-29 06:30:08'),('ln_6','admin001','TRIP_SCTGRY_021','hotel_001','','덕진동','전주 핀앤핏하우스','전라북도 전주시 덕진구 덕진광장로 8-1','핀앤핏하우스','0503-5052-4497','063-255-5757','','','','','Y','2020-09-07 15:00:00','2021-06-05 15:00:00'),('ln_7','admin001','TRIP_SCTGRY_022','hotel_002','','덕진동','전주 YAJA 전주터미널점','전라북도 전주시 덕진구 용산2길 13','호텔야자 전주터미널','0503-5052-8932','0503-5050-0068','','','','','Y','2020-09-08 15:00:00','2021-06-06 15:00:00'),('ln_8','admin001','TRIP_SCTGRY_023','hotel_003','','덕진동','전주 늘품호텔','전라북도 전주시 완산구 기린대로 90','늘품호텔','063-284-8688','','','','','','Y','2020-09-09 15:00:00','2021-06-07 15:00:00'),('ln_9','admin001','TRIP_SCTGRY_020','hotel_003','','덕진동','전주 호텔다빈','전라북도 전주시 완산구 팔달로 160','호텔다빈','063-229-8000','','https://www.instagram.com/jeonju_davin_hotel/','','','','Y','2020-09-09 15:00:00','2021-06-07 15:00:00'),('ln_99','admin001','TRIP_SCTGRY_020','tour_001',NULL,'중앙동','우리집집집은아님','중화산동','테오리아','나는바보입니다','123','ㅁㄴㅇ',NULL,NULL,NULL,'Y','2024-05-29 07:01:10',NULL);
/*!40000 ALTER TABLE `LODGING_INFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SIGUNGU_CODE`
--

DROP TABLE IF EXISTS `SIGUNGU_CODE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SIGUNGU_CODE` (
                                `sigungu_id` int NOT NULL AUTO_INCREMENT,
                                `sigungu_code` varchar(10) NOT NULL,
                                `sigungu_name` varchar(50) NOT NULL,
                                `area_id` varchar(10) NOT NULL,
                                `REG_YMD` timestamp NOT NULL,
                                `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                PRIMARY KEY (`sigungu_id`),
                                UNIQUE KEY `idx_sigungu_name_area_id` (`area_id`,`sigungu_name`) USING BTREE,
                                CONSTRAINT `fk_area_id` FOREIGN KEY (`area_id`) REFERENCES `AREA_CODE` (`area_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SIGUNGU_CODE`
--

LOCK TABLES `SIGUNGU_CODE` WRITE;
/*!40000 ALTER TABLE `SIGUNGU_CODE` DISABLE KEYS */;
INSERT INTO `SIGUNGU_CODE` VALUES (1,'1','경산시','35','2024-05-16 05:09:40','2024-05-26 14:34:21'),(2,'1','괴산군','33','2024-05-16 05:09:40','2024-05-26 14:34:21'),(3,'2','경주시','35','2024-05-16 05:09:40','2024-05-26 14:34:21'),(4,'2','단양군','33','2024-05-16 05:09:40','2024-05-26 14:34:21'),(5,'1','공주시','34','2024-05-16 05:09:40','2024-05-26 14:34:21'),(6,'3','고령군','35','2024-05-16 05:09:40','2024-05-26 14:34:21'),(7,'3','보은군','33','2024-05-16 05:09:40','2024-05-26 14:34:21'),(8,'2','금산군','34','2024-05-16 05:09:40','2024-05-26 14:34:21'),(9,'4','구미시','35','2024-05-16 05:09:40','2024-05-26 14:34:21'),(10,'4','영동군','33','2024-05-16 05:09:40','2024-05-26 14:34:21'),(11,'3','논산시','34','2024-05-16 05:09:40','2024-05-26 14:34:21'),(12,'6','김천시','35','2024-05-16 05:09:40','2024-05-26 14:34:21'),(13,'5','옥천군','33','2024-05-16 05:09:40','2024-05-26 14:34:21'),(14,'4','당진시','34','2024-05-16 05:09:40','2024-05-26 14:34:21'),(15,'7','문경시','35','2024-05-16 05:09:40','2024-05-26 14:34:21'),(16,'5','보령시','34','2024-05-16 05:09:40','2024-05-26 14:34:21'),(17,'6','음성군','33','2024-05-16 05:09:40','2024-05-26 14:34:21'),(18,'8','봉화군','35','2024-05-16 05:09:40','2024-05-26 14:34:21'),(19,'6','부여군','34','2024-05-16 05:09:40','2024-05-26 14:34:21'),(20,'7','제천시','33','2024-05-16 05:09:40','2024-05-26 14:34:21'),(21,'9','상주시','35','2024-05-16 05:09:40','2024-05-26 14:34:21'),(22,'7','서산시','34','2024-05-16 05:09:40','2024-05-26 14:34:21'),(23,'8','진천군','33','2024-05-16 05:09:40','2024-05-26 14:34:21'),(24,'10','성주군','35','2024-05-16 05:09:40','2024-05-26 14:34:21'),(25,'8','서천군','34','2024-05-16 05:09:40','2024-05-26 14:34:21'),(26,'9','청원군','33','2024-05-16 05:09:40','2024-05-26 14:34:21'),(27,'1','강진군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(28,'11','안동시','35','2024-05-16 05:09:40','2024-05-26 14:34:21'),(29,'9','아산시','34','2024-05-16 05:09:40','2024-05-26 14:34:21'),(30,'12','영덕군','35','2024-05-16 05:09:40','2024-05-26 14:34:21'),(31,'10','청주시','33','2024-05-16 05:09:40','2024-05-26 14:34:21'),(32,'2','고흥군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(33,'13','영양군','35','2024-05-16 05:09:40','2024-05-26 14:34:22'),(34,'11','예산군','34','2024-05-16 05:09:40','2024-05-26 14:34:21'),(35,'1','강남구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(36,'3','곡성군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(37,'11','충주시','33','2024-05-16 05:09:40','2024-05-26 14:34:21'),(38,'14','영주시','35','2024-05-16 05:09:40','2024-05-26 14:34:22'),(39,'1','가평군','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(40,'12','천안시','34','2024-05-16 05:09:40','2024-05-26 14:34:21'),(41,'2','강동구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(42,'4','광양시','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(43,'12','증평군','33','2024-05-16 05:09:40','2024-05-26 14:34:21'),(44,'15','영천시','35','2024-05-16 05:09:40','2024-05-26 14:34:22'),(45,'3','강북구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(46,'13','청양군','34','2024-05-16 05:09:40','2024-05-26 14:34:22'),(47,'2','고양시','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(48,'5','구례군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(49,'16','예천군','35','2024-05-16 05:09:40','2024-05-26 14:34:22'),(50,'4','강서구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(51,'14','태안군','34','2024-05-16 05:09:40','2024-05-26 14:34:22'),(52,'3','과천시','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(53,'1','강서구','6','2024-05-16 05:09:40','2024-05-26 14:34:21'),(54,'1','거제시','36','2024-05-16 05:09:40','2024-05-26 14:34:21'),(55,'6','나주시','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(56,'1','광산구','5','2024-05-16 05:09:40','2024-05-26 14:34:21'),(57,'17','울릉군','35','2024-05-16 05:09:40','2024-05-26 14:34:22'),(58,'5','관악구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(59,'15','홍성군','34','2024-05-16 05:09:40','2024-05-26 14:34:22'),(60,'2','금정구','6','2024-05-16 05:09:40','2024-05-26 14:34:21'),(61,'2','거창군','36','2024-05-16 05:09:40','2024-05-26 14:34:21'),(62,'4','광명시','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(63,'7','담양군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(64,'2','남구','5','2024-05-16 05:09:40','2024-05-26 14:34:21'),(65,'18','울진군','35','2024-05-16 05:09:40','2024-05-26 14:34:22'),(66,'6','광진구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(67,'16','계룡시','34','2024-05-16 05:09:40','2024-05-26 14:34:22'),(68,'3','기장군','6','2024-05-16 05:09:40','2024-05-26 14:34:21'),(69,'3','고성군','36','2024-05-16 05:09:40','2024-05-26 14:34:21'),(70,'8','목포시','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(71,'5','광주시','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(72,'3','동구','5','2024-05-16 05:09:40','2024-05-26 14:34:21'),(73,'19','의성군','35','2024-05-16 05:09:40','2024-05-26 14:34:22'),(74,'7','구로구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(75,'4','남구','6','2024-05-16 05:09:40','2024-05-26 14:34:21'),(76,'4','김해시','36','2024-05-16 05:09:40','2024-05-26 14:34:21'),(77,'4','북구','5','2024-05-16 05:09:40','2024-05-26 14:34:21'),(78,'9','무안군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(79,'6','구리시','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(80,'20','청도군','35','2024-05-16 05:09:40','2024-05-26 14:34:22'),(81,'1','남제주군','39','2024-05-16 05:09:40','2024-05-26 14:34:21'),(82,'8','금천구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(83,'5','남해군','36','2024-05-16 05:09:40','2024-05-26 14:34:21'),(84,'1','고창군','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(85,'5','서구','5','2024-05-16 05:09:40','2024-05-26 14:34:21'),(86,'5','동구','6','2024-05-16 05:09:40','2024-05-26 14:34:21'),(87,'10','보성군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(88,'7','군포시','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(89,'9','노원구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(90,'21','청송군','35','2024-05-16 05:09:40','2024-05-26 14:34:22'),(91,'2','북제주군','39','2024-05-16 05:09:40','2024-05-26 14:34:21'),(92,'6','마산시','36','2024-05-16 05:09:40','2024-05-26 14:34:21'),(93,'2','군산시','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(94,'1','강화군','2','2024-05-16 05:09:40','2024-05-26 14:34:21'),(95,'6','동래구','6','2024-05-16 05:09:40','2024-05-26 14:34:21'),(96,'11','순천시','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(97,'8','김포시','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(98,'10','도봉구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(99,'22','칠곡군','35','2024-05-16 05:09:40','2024-05-26 14:34:22'),(100,'3','서귀포시','39','2024-05-16 05:09:40','2024-05-26 14:34:21'),(101,'1','강릉시','32','2024-05-16 05:09:40','2024-05-26 14:34:21'),(102,'7','밀양시','36','2024-05-16 05:09:40','2024-05-26 14:34:21'),(103,'3','김제시','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(104,'2','계양구','2','2024-05-16 05:09:40','2024-05-26 14:34:21'),(105,'12','신안군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(106,'11','동대문구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(107,'7','부산진구','6','2024-05-16 05:09:40','2024-05-26 14:34:21'),(108,'23','포항시','35','2024-05-16 05:09:40','2024-05-26 14:34:22'),(109,'4','제주시','39','2024-05-16 05:09:40','2024-05-26 14:34:21'),(110,'9','남양주시','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(111,'2','고성군','32','2024-05-16 05:09:40','2024-05-26 14:34:21'),(112,'4','남원시','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(113,'8','사천시','36','2024-05-16 05:09:40','2024-05-26 14:34:21'),(114,'3','미추홀구','2','2024-05-16 05:09:40','2024-05-26 14:34:21'),(115,'12','동작구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(116,'8','북구','6','2024-05-16 05:09:40','2024-05-26 14:34:21'),(117,'13','여수시','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(118,'10','동두천시','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(119,'3','동해시','32','2024-05-16 05:09:40','2024-05-26 14:34:21'),(120,'5','무주군','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(121,'4','남동구','2','2024-05-16 05:09:40','2024-05-26 14:34:22'),(122,'13','마포구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(123,'9','산청군','36','2024-05-16 05:09:40','2024-05-26 14:34:21'),(124,'16','영광군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(125,'9','사상구','6','2024-05-16 05:09:40','2024-05-26 14:34:21'),(126,'11','부천시','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(127,'4','삼척시','32','2024-05-16 05:09:40','2024-05-26 14:34:21'),(128,'6','부안군','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(129,'14','서대문구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(130,'5','동구','2','2024-05-16 05:09:40','2024-05-26 14:34:22'),(131,'10','양산시','36','2024-05-16 05:09:40','2024-05-26 14:34:21'),(132,'17','영암군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(133,'10','사하구','6','2024-05-16 05:09:40','2024-05-26 14:34:21'),(134,'12','성남시','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(135,'5','속초시','32','2024-05-16 05:09:40','2024-05-26 14:34:21'),(136,'15','서초구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(137,'7','순창군','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(138,'6','부평구','2','2024-05-16 05:09:40','2024-05-26 14:34:22'),(139,'12','의령군','36','2024-05-16 05:09:40','2024-05-26 14:34:22'),(140,'18','완도군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(141,'11','서구','6','2024-05-16 05:09:40','2024-05-26 14:34:21'),(142,'13','수원시','31','2024-05-16 05:09:40','2024-05-26 14:34:21'),(143,'16','성동구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(144,'6','양구군','32','2024-05-16 05:09:40','2024-05-26 14:34:21'),(145,'8','완주군','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(146,'7','서구','2','2024-05-16 05:09:40','2024-05-26 14:34:22'),(147,'13','진주시','36','2024-05-16 05:09:40','2024-05-26 14:34:22'),(148,'19','장성군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(149,'12','수영구','6','2024-05-16 05:09:40','2024-05-26 14:34:22'),(150,'14','시흥시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(151,'17','성북구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(152,'7','양양군','32','2024-05-16 05:09:40','2024-05-26 14:34:21'),(153,'9','익산시','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(154,'8','연수구','2','2024-05-16 05:09:40','2024-05-26 14:34:22'),(155,'14','진해시','36','2024-05-16 05:09:40','2024-05-26 14:34:22'),(156,'20','장흥군','38','2024-05-16 05:09:40','2024-05-26 14:34:21'),(157,'13','연제구','6','2024-05-16 05:09:40','2024-05-26 14:34:22'),(158,'15','안산시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(159,'18','송파구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(160,'8','영월군','32','2024-05-16 05:09:40','2024-05-26 14:34:21'),(161,'10','임실군','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(162,'9','옹진군','2','2024-05-16 05:09:40','2024-05-26 14:34:22'),(163,'15','창녕군','36','2024-05-16 05:09:40','2024-05-26 14:34:22'),(164,'21','진도군','38','2024-05-16 05:09:40','2024-05-26 14:34:22'),(165,'14','영도구','6','2024-05-16 05:09:40','2024-05-26 14:34:22'),(166,'16','안성시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(167,'19','양천구','1','2024-05-16 05:09:40','2024-05-26 14:34:21'),(168,'9','원주시','32','2024-05-16 05:09:40','2024-05-26 14:34:21'),(169,'10','중구','2','2024-05-16 05:09:40','2024-05-26 14:34:22'),(170,'11','장수군','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(171,'22','함평군','38','2024-05-16 05:09:40','2024-05-26 14:34:22'),(172,'16','창원시','36','2024-05-16 05:09:40','2024-05-26 14:34:22'),(173,'15','중구','6','2024-05-16 05:09:40','2024-05-26 14:34:22'),(174,'20','영등포구','1','2024-05-16 05:09:40','2024-05-26 14:34:22'),(175,'17','안양시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(176,'12','전주시','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(177,'10','인제군','32','2024-05-16 05:09:40','2024-05-26 14:34:21'),(178,'23','해남군','38','2024-05-16 05:09:40','2024-05-26 14:34:22'),(179,'17','통영시','36','2024-05-16 05:09:40','2024-05-26 14:34:22'),(180,'21','용산구','1','2024-05-16 05:09:40','2024-05-26 14:34:22'),(181,'16','해운대구','6','2024-05-16 05:09:40','2024-05-26 14:34:22'),(182,'18','양주시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(183,'13','정읍시','37','2024-05-16 05:09:40','2024-05-26 14:34:21'),(184,'11','정선군','32','2024-05-16 05:09:40','2024-05-26 14:34:22'),(185,'24','화순군','38','2024-05-16 05:09:40','2024-05-26 14:34:22'),(186,'18','하동군','36','2024-05-16 05:09:40','2024-05-26 14:34:22'),(187,'22','은평구','1','2024-05-16 05:09:40','2024-05-26 14:34:22'),(188,'14','진안군','37','2024-05-16 05:09:40','2024-05-26 14:34:22'),(189,'12','철원군','32','2024-05-16 05:09:40','2024-05-26 14:34:22'),(190,'1','대덕구','3','2024-05-16 05:09:40','2024-05-26 14:34:21'),(191,'19','양평군','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(192,'19','함안군','36','2024-05-16 05:09:40','2024-05-26 14:34:22'),(193,'23','종로구','1','2024-05-16 05:09:40','2024-05-26 14:34:22'),(194,'13','춘천시','32','2024-05-16 05:09:40','2024-05-26 14:34:22'),(195,'2','동구','3','2024-05-16 05:09:40','2024-05-26 14:34:21'),(196,'20','여주시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(197,'20','함양군','36','2024-05-16 05:09:40','2024-05-26 14:34:22'),(198,'24','중구','1','2024-05-16 05:09:40','2024-05-26 14:34:22'),(199,'14','태백시','32','2024-05-16 05:09:40','2024-05-26 14:34:22'),(200,'3','서구','3','2024-05-16 05:09:40','2024-05-26 14:34:21'),(201,'21','연천군','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(202,'25','중랑구','1','2024-05-16 05:09:40','2024-05-26 14:34:22'),(203,'21','합천군','36','2024-05-16 05:09:40','2024-05-26 14:34:22'),(204,'15','평창군','32','2024-05-16 05:09:40','2024-05-26 14:34:22'),(205,'4','유성구','3','2024-05-16 05:09:40','2024-05-26 14:34:21'),(206,'22','오산시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(207,'1','남구','4','2024-05-16 05:09:40','2024-05-26 14:34:22'),(208,'16','홍천군','32','2024-05-16 05:09:40','2024-05-26 14:34:22'),(209,'5','중구','3','2024-05-16 05:09:40','2024-05-26 14:34:21'),(210,'23','용인시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(211,'2','달서구','4','2024-05-16 05:09:40','2024-05-26 14:34:22'),(212,'17','화천군','32','2024-05-16 05:09:40','2024-05-26 14:34:22'),(213,'24','의왕시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(214,'3','달성군','4','2024-05-16 05:09:40','2024-05-26 14:34:22'),(215,'18','횡성군','32','2024-05-16 05:09:40','2024-05-26 14:34:22'),(216,'4','동구','4','2024-05-16 05:09:40','2024-05-26 14:34:22'),(217,'25','의정부시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(218,'1','세종특별자치시','8','2024-05-16 05:09:40','2024-05-26 14:34:21'),(219,'5','북구','4','2024-05-16 05:09:40','2024-05-26 14:34:22'),(220,'26','이천시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(221,'6','서구','4','2024-05-16 05:09:40','2024-05-26 14:34:22'),(222,'27','파주시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(223,'7','수성구','4','2024-05-16 05:09:40','2024-05-26 14:34:22'),(224,'28','평택시','31','2024-05-16 05:09:40','2024-05-26 14:34:22'),(225,'8','중구','4','2024-05-16 05:09:41','2024-05-26 14:34:22'),(226,'29','포천시','31','2024-05-16 05:09:41','2024-05-26 14:34:22'),(227,'9','군위군','4','2024-05-16 05:09:41','2024-05-26 14:34:22'),(228,'30','하남시','31','2024-05-16 05:09:41','2024-05-26 14:34:22'),(229,'31','화성시','31','2024-05-16 05:09:41','2024-05-26 14:34:22'),(230,'1','중구','7','2024-05-16 05:09:41','2024-05-26 14:34:21'),(231,'2','남구','7','2024-05-16 05:09:41','2024-05-26 14:34:21'),(232,'3','동구','7','2024-05-16 05:09:41','2024-05-26 14:34:21'),(233,'4','북구','7','2024-05-16 05:09:41','2024-05-26 14:34:21'),(234,'5','울주군','7','2024-05-16 05:09:41','2024-05-26 14:34:21');
/*!40000 ALTER TABLE `SIGUNGU_CODE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESTAURANT_MENU`
--

DROP TABLE IF EXISTS `RESTAURANT_MENU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RESTAURANT_MENU` (
                                   `RSTRN_MENU_MNG_CD` varchar(200) NOT NULL,
                                   `REG_PF_BIZ_ID` varchar(20) NOT NULL,
                                   `RSTRN_INFO_CD` varchar(200) NOT NULL,
                                   `PRTNR_BPLC_CD` varchar(200) NOT NULL,
                                   `RGN_SCTGRY_NM` varchar(200) NOT NULL,
                                   `RSTRN_MENU` varchar(200) NOT NULL,
                                   `RSTRN_MENU_PRICE` varchar(200) NOT NULL,
                                   `FAVS_TOTAL` varchar(200) DEFAULT NULL,
                                   `MDFCN_PF_BIZ_ID` varchar(20) DEFAULT NULL,
                                   `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                   `REG_YMD` timestamp NOT NULL,
                                   `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                   PRIMARY KEY (`RSTRN_MENU_MNG_CD`),
                                   KEY `FK_MEMBER_MANAGE_TO_RESTAURANT_MENU_1` (`REG_PF_BIZ_ID`),
                                   KEY `FK_RESTAURANT_INFO_TO_RESTAURANT_MENU_1` (`RSTRN_INFO_CD`),
                                   CONSTRAINT `FK_MEMBER_MANAGE_TO_RESTAURANT_MENU_1` FOREIGN KEY (`REG_PF_BIZ_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                   CONSTRAINT `FK_RESTAURANT_INFO_TO_RESTAURANT_MENU_1` FOREIGN KEY (`RSTRN_INFO_CD`) REFERENCES `RESTAURANT_INFO` (`RSTRN_INFO_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESTAURANT_MENU`
--

LOCK TABLES `RESTAURANT_MENU` WRITE;
/*!40000 ALTER TABLE `RESTAURANT_MENU` DISABLE KEYS */;
INSERT INTO `RESTAURANT_MENU` VALUES ('rmm_1','admin001','rn_1','RSTRN_001','덕진동','군만두','백만원','','admin001','Y','2020-09-15 15:00:00','2024-03-13 15:00:00'),('rmm_10','admin001','rn_3','RSTRN_005','효자1동','생연어초밥(8P)','16000','','admin001','Y','2020-09-17 15:00:00','2024-03-13 15:00:00'),('rmm_11','admin001','rn_3','RSTRN_001','효자1동','특모듬회','60000','','admin001','Y','2020-09-17 15:00:00','2024-03-13 15:00:00'),('rmm_12','admin001','rn_3','RSTRN_002','효자1동','어린이 초밥세트','23000','','admin001','Y','2020-09-17 15:00:00','2024-03-13 15:00:00'),('rmm_13','admin001','rn_4','RSTRN_003','효자1동','숙성 목살','16000','','admin001','Y','2020-09-19 15:00:00','2024-03-13 15:00:00'),('rmm_14','admin001','rn_4','RSTRN_004','효자1동','숙성 삼겹살','16000','','admin001','Y','2020-09-19 15:00:00','2024-03-13 15:00:00'),('rmm_15','admin001','rn_4','RSTRN_005','효자1동','숙성가브리살','17000','','admin001','Y','2020-09-19 15:00:00','2024-03-13 15:00:00'),('rmm_16','admin001','rn_4','RSTRN_001','효자1동','숙성 항정살','17000','','admin001','Y','2020-09-19 15:00:00','2024-03-13 15:00:00'),('rmm_17','admin001','rn_5','RSTRN_002','중앙동','갈비전골','18000','','admin001','Y','2020-09-20 15:00:00','2024-03-13 15:00:00'),('rmm_18','admin001','rn_5','RSTRN_003','중앙동','부추볶음밥','3000','','admin001','Y','2020-09-20 15:00:00','2024-03-13 15:00:00'),('rmm_19','admin001','rn_5','RSTRN_004','중앙동','새우부추전','15000','','admin001','Y','2020-09-20 15:00:00','2024-03-13 15:00:00'),('rmm_2','admin001','rn_1','RSTRN_002','덕진동','잡채밥','10000','','admin001','Y','2020-09-16 15:00:00','2024-03-13 15:00:00'),('rmm_20','admin001','rn_5','RSTRN_005','중앙동','계란찜','5000','','admin001','Y','2020-09-20 15:00:00','2024-03-13 15:00:00'),('rmm_21','admin001','rn_6','RSTRN_001','중앙동','텐동','13000','','admin001','Y','2020-09-21 15:00:00','2024-03-13 15:00:00'),('rmm_22','admin001','rn_6','RSTRN_002','중앙동','가츠동','14000','','admin001','Y','2020-09-21 15:00:00','2024-03-13 15:00:00'),('rmm_23','admin001','rn_6','RSTRN_003','중앙동','스테이크동','16000','','admin001','Y','2020-09-21 15:00:00','2024-03-13 15:00:00'),('rmm_24','admin001','rn_6','RSTRN_004','중앙동','호르몬동','16000','','admin001','Y','2020-09-21 15:00:00','2024-03-13 15:00:00'),('rmm_25','admin001','rn_6','RSTRN_005','중앙동','사케동','15000','','admin001','Y','2020-09-21 15:00:00','2024-03-13 15:00:00'),('rmm_26','admin001','rn_6','RSTRN_001','중앙동','김치나베돈까스','12000','','admin001','Y','2020-09-21 15:00:00','2024-03-13 15:00:00'),('rmm_27','admin001','rn_7','RSTRN_002','중앙동','칼국수','9000','','admin001','Y','2020-09-22 15:00:00','2024-03-13 15:00:00'),('rmm_28','admin001','rn_7','RSTRN_003','중앙동','쫄면','8000','','admin001','Y','2020-09-22 15:00:00','2024-03-13 15:00:00'),('rmm_29','admin001','rn_7','RSTRN_004','중앙동','고기만두','7000','','admin001','Y','2020-09-22 15:00:00','2024-03-13 15:00:00'),('rmm_3','admin001','rn_1','RSTRN_003','덕진동','짜장면','7000','','admin001','Y','2020-09-15 15:00:00','2024-03-13 15:00:00'),('rmm_30','admin001','rn_8','RSTRN_005','중앙동','바게트버거','5500','','admin001','Y','2020-09-23 15:00:00','2024-03-13 15:00:00'),('rmm_31','admin001','rn_9','RSTRN_001','중앙동','아메리카노','6500','','admin001','Y','2020-09-24 15:00:00','2024-03-13 15:00:00'),('rmm_32','admin001','rn_9','RSTRN_002','중앙동','핸드드립커피','10000','','admin001','Y','2020-09-24 15:00:00','2024-03-13 15:00:00'),('rmm_33','admin001','rn_9','RSTRN_003','중앙동','에스프레소','6500','','admin001','Y','2020-09-24 15:00:00','2024-03-13 15:00:00'),('rmm_34','admin001','rn_9','RSTRN_004','중앙동','카페라떼','7000','','admin001','Y','2020-09-24 15:00:00','2024-03-13 15:00:00'),('rmm_35','admin001','rn_9','RSTRN_005','중앙동','카푸치노','7500','','admin001','Y','2020-09-24 15:00:00','2024-03-13 15:00:00'),('rmm_36','admin001','rn_9','RSTRN_001','중앙동','바닐라라떼','7500','','admin001','Y','2020-09-24 15:00:00','2024-03-13 15:00:00'),('rmm_37','admin001','rn_9','RSTRN_002','중앙동','샌드위치','8500','','admin001','Y','2020-09-24 15:00:00','2024-03-13 15:00:00'),('rmm_38','admin001','rn_10','RSTRN_003','중앙동','이베리코오일파스타','17000','','admin001','Y','2020-09-25 15:00:00','2024-03-13 15:00:00'),('rmm_39','admin001','rn_10','RSTRN_004','중앙동','투움바파스타','17000','','admin001','Y','2020-09-25 15:00:00','2024-03-13 15:00:00'),('rmm_4','admin001','rn_2','RSTRN_004','덕진동','아시타 스탠다드 A초밥','22000','','admin001','Y','2020-09-16 15:00:00','2024-03-13 15:00:00'),('rmm_40','admin001','rn_10','RSTRN_005','중앙동','게살로제리조또','15000','','admin001','Y','2020-09-25 15:00:00','2024-03-13 15:00:00'),('rmm_41','admin001','rn_10','RSTRN_001','중앙동','감베로알리오올리오','14000','','admin001','Y','2020-09-25 15:00:00','2024-03-13 15:00:00'),('rmm_42','admin001','rn_11','RSTRN_002','중앙동','옛날흑임자팥빙수','9000','','admin001','Y','2020-09-26 15:00:00','2024-03-13 15:00:00'),('rmm_43','admin001','rn_11','RSTRN_003','중앙동','단팥죽','10000','','admin001','Y','2020-09-26 15:00:00','2024-03-13 15:00:00'),('rmm_44','admin001','rn_11','RSTRN_004','중앙동','망고빙수','15000','','admin001','Y','2020-09-26 15:00:00','2024-03-13 15:00:00'),('rmm_45','admin001','rn_11','RSTRN_005','중앙동','곡물미숫가루','7000','','admin001','Y','2020-09-26 15:00:00','2024-03-13 15:00:00'),('rmm_46','admin001','rn_11','RSTRN_001','중앙동','아메리카노','5000','','admin001','Y','2020-09-26 15:00:00','2024-03-13 15:00:00'),('rmm_47','admin001','rn_12','RSTRN_002','중앙동','된장쟁반해물짜장','22000','','admin001','Y','2020-09-27 15:00:00','2024-03-13 15:00:00'),('rmm_48','admin001','rn_12','RSTRN_003','중앙동','일반쟁반해물짜장','22000','','admin001','Y','2020-09-27 15:00:00','2024-03-13 15:00:00'),('rmm_49','admin001','rn_12','RSTRN_004','중앙동','짬뽕','8000','','admin001','Y','2020-09-27 15:00:00','2024-03-13 15:00:00'),('rmm_5','admin001','rn_2','RSTRN_005','덕진동','아시타 스탠다드 B초밥','22000','','admin001','Y','2020-09-16 15:00:00','2024-03-13 15:00:00'),('rmm_50','admin001','rn_12','RSTRN_005','중앙동','간짜장','8000','','admin001','Y','2020-09-27 15:00:00','2024-03-13 15:00:00'),('rmm_51','admin001','rn_12','RSTRN_001','중앙동','물짜장','7000','','admin001','Y','2020-09-27 15:00:00','2024-03-13 15:00:00'),('rmm_52','admin001','rn_12','RSTRN_002','중앙동','볶음밥','8000','','admin001','Y','2020-09-27 15:00:00','2024-03-13 15:00:00'),('rmm_53','admin001','rn_12','RSTRN_003','중앙동','잡채밥','9000','','admin001','Y','2020-09-27 15:00:00','2024-03-13 15:00:00'),('rmm_54','admin001','rn_12','RSTRN_004','중앙동','삼선울면','9000','','admin001','Y','2020-09-27 15:00:00','2024-03-13 15:00:00'),('rmm_55','admin001','rn_12','RSTRN_005','중앙동','짬뽕밥','8000','','admin001','Y','2020-09-27 15:00:00','2024-03-13 15:00:00'),('rmm_6','admin001','rn_2','RSTRN_001','덕진동','아시타 사시미(2인)','48000','','admin001','Y','2020-09-16 15:00:00','2024-03-13 15:00:00'),('rmm_7','admin001','rn_3','RSTRN_002','효자1동','자부심초밥세트','16900','','admin001','Y','2020-09-17 15:00:00','2024-03-13 15:00:00'),('rmm_8','admin001','rn_3','RSTRN_003','효자1동','진심초밥세트','12900','','admin001','Y','2020-09-17 15:00:00','2024-03-13 15:00:00'),('rmm_9','admin001','rn_3','RSTRN_004','효자1동','카이센동','19000','','admin001','Y','2020-09-17 15:00:00','2024-03-13 15:00:00');
/*!40000 ALTER TABLE `RESTAURANT_MENU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POST_COMMENT`
--

DROP TABLE IF EXISTS `POST_COMMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `POST_COMMENT` (
                                `PST_CMNT_NO` varchar(20) NOT NULL,
                                `REG_MBR_ID` varchar(20) NOT NULL,
                                `PST_NO` varchar(20) DEFAULT NULL,
                                `PST_CMNT_CN` varchar(500) NOT NULL,
                                `PST_CMNT_LIKE_TOTAL` int DEFAULT '0',
                                `PST_CMNT_DISLIKE_TOTAL` int DEFAULT '0',
                                `PST_CMNT_REPT_TOTAL` int DEFAULT '0',
                                `MDFCN_MBR_ID` varchar(20) DEFAULT NULL,
                                `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                `REG_YMD` timestamp NOT NULL,
                                `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                `DEL_YMD` timestamp NULL DEFAULT NULL,
                                PRIMARY KEY (`PST_CMNT_NO`),
                                KEY `FK_MM_PC2` (`REG_MBR_ID`),
                                CONSTRAINT `FK_MM_PC2` FOREIGN KEY (`REG_MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POST_COMMENT`
--

LOCK TABLES `POST_COMMENT` WRITE;
/*!40000 ALTER TABLE `POST_COMMENT` DISABLE KEYS */;
INSERT INTO `POST_COMMENT` VALUES ('PST_CMNT_1','member006','PST_7','저요 ~~',1,0,0,'member006','Y','2023-12-31 15:00:00',NULL,NULL),('PST_CMNT_10','id001','PST_18','나도',0,0,0,NULL,'Y','2024-05-27 06:17:03',NULL,NULL),('PST_CMNT_11','id003','PST_22','ㅎㅇ',0,0,0,NULL,'Y','2024-05-27 06:59:20',NULL,NULL),('PST_CMNT_12','id003','PST_22','ㅋㅋ',0,0,0,NULL,'Y','2024-05-27 06:59:46',NULL,NULL),('PST_CMNT_13','id003','PST_22','ㅎㅎ',0,0,0,NULL,'Y','2024-05-27 07:00:07',NULL,NULL),('PST_CMNT_14','id003','PST_22','ㅋ',0,0,0,NULL,'Y','2024-05-27 07:01:28',NULL,NULL),('PST_CMNT_15','id003','PST_22','ㅎㅎㅎ',0,0,0,NULL,'Y','2024-05-27 07:02:26',NULL,NULL),('PST_CMNT_16','id001','PST_24','gdgd',0,0,0,NULL,'Y','2024-05-29 01:04:03',NULL,NULL),('PST_CMNT_2','member006','PST_7','저 전주 사람임',0,0,0,'member006','Y','2023-12-31 15:00:00',NULL,NULL),('PST_CMNT_3','member006','PST_7','베테랑 추천드려여',1,1,0,'member006','Y','2023-12-31 15:00:00',NULL,NULL),('PST_CMNT_4','member005','PST_6','도배하지마세요',5,1,0,'member005','Y','2023-12-31 15:00:00',NULL,NULL),('PST_CMNT_5','member002','PST_1','어쩔 너나 가 18',1,10,10,'member002','Y','2023-12-31 15:00:00',NULL,NULL),('PST_CMNT_6','member001','PST_21','방갑읍니다 환영해여',1,0,0,'','Y','2024-05-22 03:10:25',NULL,NULL),('PST_CMNT_7','member001','PST_15','ㅎㅇㅎㅇ',1,0,0,'','Y','2024-05-22 03:12:18',NULL,NULL),('PST_CMNT_8','id001','PST_22','12345',0,0,0,NULL,'Y','2024-05-27 06:13:47',NULL,NULL),('PST_CMNT_9','id001','PST_22','1234',0,0,0,NULL,'Y','2024-05-27 06:14:21',NULL,NULL);
/*!40000 ALTER TABLE `POST_COMMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REPORT_HISTORY`
--

DROP TABLE IF EXISTS `REPORT_HISTORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REPORT_HISTORY` (
                                  `PST_CMNT_REPT_NO` varchar(20) NOT NULL,
                                  `REPORT_MBR_ID` varchar(20) NOT NULL,
                                  `REPT_CTGRY_NO` varchar(20) NOT NULL,
                                  `PST_PST_CMNT_NO` varchar(20) NOT NULL,
                                  `PST_CMNT_REPT_CN` varchar(200) NOT NULL,
                                  `PST_CMNT_REPT_YMD` timestamp NOT NULL,
                                  `REPORTED_MBR_ID` varchar(20) NOT NULL,
                                  `APRV_PF_ID` varchar(20) DEFAULT NULL,
                                  `PST_CMNT_REPT_APRV` char(1) NOT NULL DEFAULT 'N',
                                  `PST_CMNT_REPT_APRV_YMD` timestamp NULL DEFAULT NULL,
                                  PRIMARY KEY (`PST_CMNT_REPT_NO`),
                                  KEY `FK_MM_RH` (`REPORT_MBR_ID`),
                                  KEY `FK_RC_RH` (`REPT_CTGRY_NO`),
                                  CONSTRAINT `FK_MM_RH` FOREIGN KEY (`REPORT_MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REPORT_HISTORY`
--

LOCK TABLES `REPORT_HISTORY` WRITE;
/*!40000 ALTER TABLE `REPORT_HISTORY` DISABLE KEYS */;
INSERT INTO `REPORT_HISTORY` VALUES ('PST_CMNT_REPT_1','member001','REPT_CTGRY_1','PST_CMNT_5','욕설이 너무심해요','2023-12-31 15:00:00','member002','admin001','Y','2023-12-31 15:00:00'),('PST_CMNT_REPT_10','member005','REPT_CTGRY_6','PST_5','도배가 너무심함','2023-12-31 15:00:00','member003','admin001','Y','2023-12-31 15:00:00'),('PST_CMNT_REPT_11','member006','REPT_CTGRY_6','PST_6','도배가 너무심함','2023-12-31 15:00:00','member003','admin001','N','2023-12-31 15:00:00'),('PST_CMNT_REPT_2','member001','REPT_CTGRY_5','PST_2','폭력적인 콘텐츠','2023-12-31 15:00:00','member002','admin001','Y','2023-12-31 15:00:00'),('PST_CMNT_REPT_3','member001','REPT_CTGRY_6','PST_3','이사람글 광고같아요','2023-12-31 15:00:00','member003','admin001','N','2023-12-31 15:00:00'),('PST_CMNT_REPT_4','member004','REPT_CTGRY_6','PST_4','이사람글 광고같아요','2023-12-31 15:00:00','member003','admin001','N','2023-12-31 15:00:00'),('PST_CMNT_REPT_5','member005','REPT_CTGRY_6','PST_5','이사람글 광고같아요','2023-12-31 15:00:00','member003','admin001','N','2023-12-31 15:00:00'),('PST_CMNT_REPT_6','member006','REPT_CTGRY_6','PST_6','이사람글 광고같아요','2023-12-31 15:00:00','member003','admin001','N','2023-12-31 15:00:00'),('PST_CMNT_REPT_7','member004','REPT_CTGRY_1','PST_CMNT_5','욕하지마세요','2023-12-31 15:00:00','member002','admin001','Y','2023-12-31 15:00:00'),('PST_CMNT_REPT_8','member001','REPT_CTGRY_6','PST_3','도배가 너무심함','2023-12-31 15:00:00','member003','admin001','Y','2023-12-31 15:00:00'),('PST_CMNT_REPT_9','member004','REPT_CTGRY_6','PST_4','도배가 너무심함','2023-12-31 15:00:00','member003','admin001','Y','2023-12-31 15:00:00');
/*!40000 ALTER TABLE `REPORT_HISTORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RATING_CATEGORY`
--

DROP TABLE IF EXISTS `RATING_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RATING_CATEGORY` (
                                   `RAT_CTGRY_NO` varchar(200) NOT NULL,
                                   `REG_PF_ID` varchar(20) NOT NULL,
                                   `RAT_SCR` int NOT NULL,
                                   `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                   `REG_YMD` timestamp NOT NULL,
                                   `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                   PRIMARY KEY (`RAT_CTGRY_NO`),
                                   KEY `FK_MEMBER_MANAGE_TO_RATING_CATEGORY_1` (`REG_PF_ID`),
                                   CONSTRAINT `FK_MEMBER_MANAGE_TO_RATING_CATEGORY_1` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RATING_CATEGORY`
--

LOCK TABLES `RATING_CATEGORY` WRITE;
/*!40000 ALTER TABLE `RATING_CATEGORY` DISABLE KEYS */;
INSERT INTO `RATING_CATEGORY` VALUES ('RAT_CTGRY_01','admin001',1,'Y','2024-04-30 02:24:42',NULL),('RAT_CTGRY_02','admin001',2,'Y','2024-04-30 02:24:42',NULL),('RAT_CTGRY_03','admin001',3,'Y','2024-04-30 02:24:42',NULL),('RAT_CTGRY_04','admin001',4,'Y','2024-04-30 02:24:42',NULL),('RAT_CTGRY_05','admin001',5,'Y','2024-04-30 02:24:42',NULL);
/*!40000 ALTER TABLE `RATING_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MEMBER_RECOMMENDATION_RANKING`
--

DROP TABLE IF EXISTS `MEMBER_RECOMMENDATION_RANKING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MEMBER_RECOMMENDATION_RANKING` (
                                                 `MBR_ARTCL_RANK` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                                 `REG_PF_ID` varchar(20) NOT NULL,
                                                 `PRTNR_BPLC_CD` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                                 `TRIP_LCTGRY_CD` varchar(20) NOT NULL,
                                                 `destination_content_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                                 `destination_Type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                                 `TOP_RANK` tinyint NOT NULL,
                                                 `DN_DETAIL` varchar(255) DEFAULT '',
                                                 `REV_STATS_STRT_YMD` timestamp NOT NULL,
                                                 `REV_STATS_END_YMD` timestamp NOT NULL,
                                                 `PF_PSTG_STRT_YMD` timestamp NOT NULL,
                                                 `PF_PSTG_END_YMD` timestamp NOT NULL,
                                                 `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                                 `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                                 `REG_YMD` timestamp NOT NULL,
                                                 `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                                 PRIMARY KEY (`MBR_ARTCL_RANK`) USING BTREE,
                                                 KEY `FK_MEMBER_MANAGE_TO_MEMBER_RECOMMENDATION_RANKING_1` (`REG_PF_ID`),
                                                 KEY `FK_TRIP_LARGE_CATEGORY_TO_MEMBER_RECOMMENDATION_RANKING_1` (`TRIP_LCTGRY_CD`),
                                                 KEY `PRTNR_BPLC_CD` (`PRTNR_BPLC_CD`),
                                                 KEY `FK_MEMBER_RECOMMENDATION_RANKING_TOUR_INFO_FROM_API` (`destination_content_id`),
                                                 CONSTRAINT `FK_MEMBER_MANAGE_TO_MEMBER_RECOMMENDATION_RANKING_1` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                                 CONSTRAINT `FK_MEMBER_RECOMMENDATION_RANKING_TOUR_INFO_FROM_API` FOREIGN KEY (`destination_content_id`) REFERENCES `TOUR_INFO_FROM_API` (`destination_content_id`),
                                                 CONSTRAINT `FK_PARTNER_BUSINESS_PLACE_TO_MEMBER_RECOMMENDATION_RANKING_1` FOREIGN KEY (`PRTNR_BPLC_CD`) REFERENCES `PARTNER_BUSINESS_PLACE` (`PRTNR_BPLC_CD`),
                                                 CONSTRAINT `FK_TRIP_LARGE_CATEGORY_TO_MEMBER_RECOMMENDATION_RANKING_1` FOREIGN KEY (`TRIP_LCTGRY_CD`) REFERENCES `TRIP_LARGE_CATEGORY` (`TRIP_LCTGRY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEMBER_RECOMMENDATION_RANKING`
--

LOCK TABLES `MEMBER_RECOMMENDATION_RANKING` WRITE;
/*!40000 ALTER TABLE `MEMBER_RECOMMENDATION_RANKING` DISABLE KEYS */;
INSERT INTO `MEMBER_RECOMMENDATION_RANKING` VALUES ('mrr_01','admin001','tour_001','TRIP_LCTGRY_01','2996389','관광지',1,'관광명소입니다.','2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2024-05-28 01:25:55',NULL),('mrr_02','admin001','tour_002','TRIP_LCTGRY_01','2815080','관광지',2,'관광명소입니다.','2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2024-05-28 02:41:03',NULL),('mrr_03','admin001','tour_003','TRIP_LCTGRY_01','126684','관광지',3,'관광명소입니다.','2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2020-09-04 15:00:00',NULL),('mrr_04','admin001','RSTRN_001','TRIP_LCTGRY_02','2910807','음식점',1,'맛집입니다.','2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2020-09-07 15:00:00',NULL),('mrr_05','admin001','RSTRN_002','TRIP_LCTGRY_02','2609122','음식점',2,'맛집입니다.','2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2020-09-08 15:00:00',NULL),('mrr_06','admin001','RSTRN_003','TRIP_LCTGRY_02','2871024','음식점',3,'맛집입니다.','2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2020-09-09 15:00:00',NULL),('mrr_07','admin001','hotel_001','TRIP_LCTGRY_03','2950410','숙소',1,'시설이 깨끗합니다.','2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2020-09-09 15:00:00',NULL),('mrr_08','admin001','hotel_002','TRIP_LCTGRY_03','2040030','숙소',2,'최신식 입니다.','2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2020-09-09 15:00:00',NULL),('mrr_09','admin001','hotel_003','TRIP_LCTGRY_03','3080814','숙소',3,'친절합니다.','2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2020-09-09 15:00:00',NULL),('mrr_10','admin001','hotel_001','TRIP_LCTGRY_01','2774063','관광지',1,'관광명소입니다다다다','2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2024-05-29 08:22:09',NULL);
/*!40000 ALTER TABLE `MEMBER_RECOMMENDATION_RANKING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QNA_QUESTION`
--

DROP TABLE IF EXISTS `QNA_QUESTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QNA_QUESTION` (
                                `QNA_NO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                `REG_MBR_BIZ_ID` varchar(20) NOT NULL,
                                `FAQ_CTGRY_NO` varchar(20) NOT NULL,
                                `QNA_PRGRS_NO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'QNA_PRGRS_1',
                                `QNA_PRGRS_CTGRY` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '접수',
                                `QNA_TTL` varchar(50) NOT NULL,
                                `QNA_CN` varchar(500) NOT NULL,
                                `ANS_REG_PF_ID` varchar(20) DEFAULT NULL,
                                `MDFCN_MBR_BIZ_ID` varchar(20) DEFAULT NULL,
                                `REG_YMD` timestamp NOT NULL,
                                `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                `RTRCN_YMD` timestamp NULL DEFAULT NULL,
                                PRIMARY KEY (`QNA_NO`) USING BTREE,
                                KEY `REG_MBR_BIZ_ID` (`REG_MBR_BIZ_ID`),
                                KEY `FAQ_CTGRY_NO` (`FAQ_CTGRY_NO`),
                                KEY `QNA_PRGRS_NO` (`QNA_PRGRS_NO`),
                                CONSTRAINT `FK_QNA_QUESTION_FAQ_CATEGORY` FOREIGN KEY (`FAQ_CTGRY_NO`) REFERENCES `FAQ_CATEGORY` (`FAQ_CTGRY_NO`),
                                CONSTRAINT `FK_QNA_QUESTION_MEMBER_MANAGE` FOREIGN KEY (`REG_MBR_BIZ_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                CONSTRAINT `FK_QNA_QUESTION_QNA_PROGRESS` FOREIGN KEY (`QNA_PRGRS_NO`) REFERENCES `QNA_PROGRESS` (`QNA_PRGRS_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QNA_QUESTION`
--

LOCK TABLES `QNA_QUESTION` WRITE;
/*!40000 ALTER TABLE `QNA_QUESTION` DISABLE KEYS */;
INSERT INTO `QNA_QUESTION` VALUES ('QNA_1','member001','FAQ_CTGRY_1','QNA_PRGRS_1','답변완료','계정을 탈퇴하고싶어요','계정을 탈퇴하고싶…','admin001','member001','2024-05-01 05:06:47',NULL,NULL),('QNA_2','business001','FAQ_CTGRY_3','QNA_PRGRS_1','답변완료','교통편 추가 건의','항공편이 추가되었으면…','admin001','business001','2024-05-05 05:08:55',NULL,NULL),('QNA_3','member001','FAQ_CTGRY_4','QNA_PRGRS_3','답변완료','구매를 취소하고 싶어요','식당 예약을 취소하고…','admin001','member001','2024-05-15 05:10:51',NULL,NULL),('QNA_4','id003','FAQ_CTGRY_1','QNA_PRGRS_1','접수','환불 해주세요','환불\r<br>부탁드립니다',NULL,NULL,'2024-05-21 05:33:54','2024-05-29 06:29:53',NULL),('QNA_5','id003','FAQ_CTGRY_1','QNA_PRGRS_1','접수','탈퇴하는 법 ','탈퇴하는 법 모에요 ?',NULL,NULL,'2024-05-21 05:34:52',NULL,NULL),('QNA_6','id001','FAQ_CTGRY_1','QNA_PRGRS_1','접수','ㅇㅇ','ㅇㅇㅎㅎㅎㅎㅎㅎ\r<br>ㅇㅇ\r<br>ㅇ\r<br>\r<br>ㅇ\r<br>ㅇ',NULL,NULL,'2024-05-29 01:17:55','2024-05-29 01:18:04',NULL);
/*!40000 ALTER TABLE `QNA_QUESTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PAYMENT_AUTHENTICATION_FILE`
--

DROP TABLE IF EXISTS `PAYMENT_AUTHENTICATION_FILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PAYMENT_AUTHENTICATION_FILE` (
                                               `STLM_CERT_FILE_CD` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                               `REG_MBR_ID` varchar(20) NOT NULL,
                                               `BZENTY_RSVT_NO` varchar(200) DEFAULT NULL,
                                               `ATCH_FILE_FORM` varchar(200) NOT NULL,
                                               `ATCH_PATH_NM` varchar(200) NOT NULL,
                                               `REG_YMD` timestamp NOT NULL,
                                               `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                               PRIMARY KEY (`STLM_CERT_FILE_CD`) USING BTREE,
                                               KEY `FK_MEMBER_MANAGE_TO_PAYMENT_AUTHENTICATION_FILE_1` (`REG_MBR_ID`),
                                               CONSTRAINT `FK_MEMBER_MANAGE_TO_PAYMENT_AUTHENTICATION_FILE_1` FOREIGN KEY (`REG_MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAYMENT_AUTHENTICATION_FILE`
--

LOCK TABLES `PAYMENT_AUTHENTICATION_FILE` WRITE;
/*!40000 ALTER TABLE `PAYMENT_AUTHENTICATION_FILE` DISABLE KEYS */;
INSERT INTO `PAYMENT_AUTHENTICATION_FILE` VALUES ('STLM_CERT_FILE_001','member001','','음식점 영수증1.png','','2023-03-31 15:00:00',NULL),('STLM_CERT_FILE_002','member001','','호텔 영수증1.png','','2023-05-10 15:00:00',NULL),('STLM_CERT_FILE_003','member002','','호텔 영수증2.png','','2023-07-21 15:00:00',NULL),('STLM_CERT_FILE_004','member003','','관광지 영수증1.png','','2023-09-02 15:00:00',NULL),('STLM_CERT_FILE_005','member004','','음식점 영수증2.jpeg','','2023-12-31 15:00:00',NULL),('STLM_CERT_FILE_006','member005','','관광지 영수증2.png','','2024-02-10 15:00:00',NULL);
/*!40000 ALTER TABLE `PAYMENT_AUTHENTICATION_FILE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TOUR_DETAIL_FROM_API`
--

DROP TABLE IF EXISTS `TOUR_DETAIL_FROM_API`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TOUR_DETAIL_FROM_API` (
                                        `tour_detail_content_id` varchar(50) NOT NULL,
                                        `tour_detail_content_type_id` varchar(50) DEFAULT NULL,
                                        `tour_detail_title` varchar(255) DEFAULT NULL,
                                        `tour_detail_homepage` text,
                                        `tour_detail_first_image` varchar(255) DEFAULT NULL,
                                        `tour_detail_second_image` varchar(255) DEFAULT NULL,
                                        `area_code` varchar(10) DEFAULT NULL,
                                        `sigungu_code` varchar(10) DEFAULT NULL,
                                        `tour_detail_first_addr` varchar(255) DEFAULT NULL,
                                        `tour_detail_second_addr` varchar(255) DEFAULT NULL,
                                        `tour_detail_zipcode` varchar(20) DEFAULT NULL,
                                        `tour_detail_tel_num` varchar(50) DEFAULT NULL,
                                        `tour_detail_tel_name` varchar(255) DEFAULT NULL,
                                        `tour_detail_longitude` double DEFAULT NULL,
                                        `tour_detail_latitude` double DEFAULT NULL,
                                        `tour_detail_mlevel` varchar(10) DEFAULT NULL,
                                        `tour_detail_overview` text,
                                        `REG_YMD` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                        `MDFCN_YMD` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                        PRIMARY KEY (`tour_detail_content_id`),
                                        CONSTRAINT `TOUR_DETAIL_FROM_API_ibfk_1` FOREIGN KEY (`tour_detail_content_id`) REFERENCES `TOUR_INFO_FROM_API` (`destination_content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TOUR_DETAIL_FROM_API`
--

LOCK TABLES `TOUR_DETAIL_FROM_API` WRITE;
/*!40000 ALTER TABLE `TOUR_DETAIL_FROM_API` DISABLE KEYS */;
/*!40000 ALTER TABLE `TOUR_DETAIL_FROM_API` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POST_FILE`
--

DROP TABLE IF EXISTS `POST_FILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `POST_FILE` (
                             `PST_FILE_NO` varchar(20) NOT NULL,
                             `REG_MBR_ID` varchar(20) NOT NULL,
                             `PST_NO` varchar(20) NOT NULL,
                             `PST_FILE_NM` varchar(30) NOT NULL,
                             `PST_FILE_PATH_NM` varchar(50) NOT NULL,
                             `MDFCN_MBR_ID` varchar(20) DEFAULT NULL,
                             `REG_YMD` timestamp NOT NULL,
                             `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                             PRIMARY KEY (`PST_FILE_NO`),
                             KEY `FK_MM_PF` (`REG_MBR_ID`),
                             CONSTRAINT `FK_MM_PF` FOREIGN KEY (`REG_MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POST_FILE`
--

LOCK TABLES `POST_FILE` WRITE;
/*!40000 ALTER TABLE `POST_FILE` DISABLE KEYS */;
INSERT INTO `POST_FILE` VALUES ('PST_ FILE_1','member001','PST_1','a.pdf','null','member001','2023-12-31 15:00:00',NULL),('PST_ FILE_2','member002','PST_2','b.jpeg','null','member002','2023-12-31 15:00:00',NULL),('PST_ FILE_3','member003','PST_3','c.png','null','member003','2023-12-31 15:00:00',NULL),('PST_ FILE_4','member004','PST_7','d.png','null','member004','2023-12-31 15:00:00',NULL);
/*!40000 ALTER TABLE `POST_FILE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INDIVIDUAL_TRIP_PLAN_PARTICIPATION_CONFIRMATION`
--

DROP TABLE IF EXISTS `INDIVIDUAL_TRIP_PLAN_PARTICIPATION_CONFIRMATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INDIVIDUAL_TRIP_PLAN_PARTICIPATION_CONFIRMATION` (
                                                                   `PTY_NO` varchar(20) NOT NULL,
                                                                   `MBR_ID` varchar(20) NOT NULL,
                                                                   `TRIP_PLN_NO` varchar(20) NOT NULL,
                                                                   `DMND_NO` varchar(20) DEFAULT NULL,
                                                                   `PTY_NM` varchar(20) DEFAULT NULL,
                                                                   `PTY_TELNO` varchar(20) DEFAULT NULL,
                                                                   `REG_YMD` timestamp NOT NULL,
                                                                   PRIMARY KEY (`PTY_NO`),
                                                                   KEY `FK_MM_ITPPC` (`MBR_ID`),
                                                                   KEY `FK_ITP_ITPPC` (`TRIP_PLN_NO`),
                                                                   CONSTRAINT `FK_ITP_ITPPC` FOREIGN KEY (`TRIP_PLN_NO`) REFERENCES `INDIVIDUAL_TRIP_PLAN` (`TRIP_PLN_NO`),
                                                                   CONSTRAINT `FK_MM_ITPPC` FOREIGN KEY (`MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INDIVIDUAL_TRIP_PLAN_PARTICIPATION_CONFIRMATION`
--

LOCK TABLES `INDIVIDUAL_TRIP_PLAN_PARTICIPATION_CONFIRMATION` WRITE;
/*!40000 ALTER TABLE `INDIVIDUAL_TRIP_PLAN_PARTICIPATION_CONFIRMATION` DISABLE KEYS */;
/*!40000 ALTER TABLE `INDIVIDUAL_TRIP_PLAN_PARTICIPATION_CONFIRMATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PARTNER_BUSINESS_PLACE`
--

DROP TABLE IF EXISTS `PARTNER_BUSINESS_PLACE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PARTNER_BUSINESS_PLACE` (
                                          `PRTNR_BPLC_CD` varchar(200) NOT NULL,
                                          `BZ_ID` varchar(20) NOT NULL,
                                          `RGN_SCTGRY_CD` varchar(200) NOT NULL,
                                          `PRTNR_BRD_CTGRY_CD` varchar(200) NOT NULL,
                                          `RGN_SCTGRY_NM` varchar(200) NOT NULL,
                                          `PRTNR_BRD_NM` varchar(200) DEFAULT NULL,
                                          `BRNO` varchar(200) DEFAULT NULL,
                                          `BZMN_CONM` varchar(200) DEFAULT NULL,
                                          `BZMN_RBPRSN` varchar(200) DEFAULT NULL,
                                          `BPLC_TELNO` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                          `BPLC_ADDR` varchar(400) DEFAULT NULL,
                                          `BPLC_ADDR_DETAILS` varchar(400) DEFAULT NULL,
                                          `STLM_PSBLTY_YN` char(1) NOT NULL DEFAULT 'Y',
                                          `RSVT_PSBLTY_YN` char(1) NOT NULL DEFAULT 'Y',
                                          `REG_YMD` varchar(50) DEFAULT NULL,
                                          `MDFCN_YMD` varchar(50) DEFAULT NULL,
                                          PRIMARY KEY (`PRTNR_BPLC_CD`),
                                          KEY `FK_MEMBER_MANAGE_TO_PARTNER_BUSINESS_PLACE_1` (`BZ_ID`),
                                          KEY `FK_REGION_SMALL_CATEGORY_TO_PARTNER_BUSINESS_PLACE_1` (`RGN_SCTGRY_CD`),
                                          KEY `FK_PARTNER_BRAND_CATEGORY_TO_PARTNER_BUSINESS_PLACE_1` (`PRTNR_BRD_CTGRY_CD`),
                                          CONSTRAINT `FK_MEMBER_MANAGE_TO_PARTNER_BUSINESS_PLACE_1` FOREIGN KEY (`BZ_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                          CONSTRAINT `FK_PARTNER_BRAND_CATEGORY_TO_PARTNER_BUSINESS_PLACE_1` FOREIGN KEY (`PRTNR_BRD_CTGRY_CD`) REFERENCES `PARTNER_BRAND_CATEGORY` (`PRTNR_BRD_CTGRY_CD`),
                                          CONSTRAINT `FK_REGION_SMALL_CATEGORY_TO_PARTNER_BUSINESS_PLACE_1` FOREIGN KEY (`RGN_SCTGRY_CD`) REFERENCES `REGION_SMALL_CATEGORY` (`RGN_SCTGRY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PARTNER_BUSINESS_PLACE`
--

LOCK TABLES `PARTNER_BUSINESS_PLACE` WRITE;
/*!40000 ALTER TABLE `PARTNER_BUSINESS_PLACE` DISABLE KEYS */;
INSERT INTO `PARTNER_BUSINESS_PLACE` VALUES ('hotel_001','business005','RGN_SCTGRY_001','PRTNR_BRD_CTGRY_007','중앙동','사업자 직접 등록','111-11-11111','전주 그랜드힐스턴','박리봄','010-3333-1111','전라북도 전주시','매봉9길','Y','Y','2023-04-05',''),('hotel_002','business006','RGN_SCTGRY_002','PRTNR_BRD_CTGRY_008','중화산2동','부킹닷컴','222-22-22222','전주 더메이 호텔','이대표','010-3333-1112','전라북도 전주시','','Y','Y','2023-04-16',''),('hotel_003','business007','RGN_SCTGRY_003','PRTNR_BRD_CTGRY_009','서신동','사업자직접등록','222-22-22223','전주 그랜드힐스턴','이대표','010-3333-1113','전라북도 전주시','','Y','Y','2023-04-16',''),('RSTRN_001','business008','RGN_SCTGRY_003','PRTNR_BRD_CTGRY_004','서신동','사업자직접등록','333-33-33333','김해뒷고기','삼대표','010-3333-1113','전라북도 전주시','','N','Y','2023-10-10',''),('RSTRN_002','business009','RGN_SCTGRY_004','PRTNR_BRD_CTGRY_004','평화1동','배민','333-33-33334','오일내','삼대표','010-3333-1114','전라북도 전주시','','N','Y','2023-10-11',''),('RSTRN_003','business010','RGN_SCTGRY_005','PRTNR_BRD_CTGRY_005','효자1동','배민','333-33-33335','트럼펫','삼대표','010-3333-1115','전라북도 전주시','','N','Y','2023-10-12',''),('tour_001','business001','RGN_SCTGRY_004','PRTNR_BRD_CTGRY_007','평화1동','사업자직접등록','444-44-44444','경기전','사대표','010-3333-1114','전라북도 전주시','','N','N','2023-10-13',''),('tour_002','business002','RGN_SCTGRY_005','PRTNR_BRD_CTGRY_010','효자1동','한국관광공사','444-44-44445','수목원','사대표','010-3333-1115','전라북도 전주시','','N','N','2023-10-14',''),('tour_003','business003','RGN_SCTGRY_006','PRTNR_BRD_CTGRY_010','효자4동','한국관광공사','444-44-44446','덕진공원','사대표','010-3333-1116','전라북도 전주시','','N','N','2023-10-15','');
/*!40000 ALTER TABLE `PARTNER_BUSINESS_PLACE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LODGING_MENU`
--

DROP TABLE IF EXISTS `LODGING_MENU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LODGING_MENU` (
                                `LODGING_MENU_CD` varchar(200) NOT NULL,
                                `REG_PF_BIZ_ID` varchar(20) NOT NULL,
                                `LODGING_INFO_CD` varchar(200) NOT NULL,
                                `PRTNR_BPLC_CD` varchar(200) NOT NULL,
                                `RGN_SCTGRY_CD` varchar(200) NOT NULL,
                                `LODGING_MENU_TYPE` varchar(200) NOT NULL,
                                `LODGING_MENU_MINCPCT` varchar(200) NOT NULL,
                                `LODGING_MENU_MAXCPCT` varchar(200) NOT NULL,
                                `LODGING_MENU_PRC` varchar(200) NOT NULL,
                                `LODGING_MENU_ADDPRC` varchar(200) NOT NULL,
                                `LODGING_MENU_SMOKE_YN` varchar(10) NOT NULL,
                                `LODGING_MENU_BK_YN` varchar(10) NOT NULL,
                                `LODGING_MENU_RMRK` varchar(200) DEFAULT NULL,
                                `FAVS_TOTAL` varchar(200) DEFAULT NULL,
                                `MDFCN_PF_BIZ_ID` varchar(20) DEFAULT NULL,
                                `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                `REG_YMD` varchar(50) NOT NULL DEFAULT '',
                                `MDFCN_YMD` varchar(50) DEFAULT NULL,
                                PRIMARY KEY (`LODGING_MENU_CD`),
                                KEY `FK_MEMBER_MANAGE_TO_LODGING_MENU_1` (`REG_PF_BIZ_ID`),
                                KEY `FK_LODGING_INFO_TO_LODGING_MENU_1` (`LODGING_INFO_CD`),
                                CONSTRAINT `FK_LODGING_INFO_TO_LODGING_MENU_1` FOREIGN KEY (`LODGING_INFO_CD`) REFERENCES `LODGING_INFO` (`LODGING_INFO_CD`),
                                CONSTRAINT `FK_MEMBER_MANAGE_TO_LODGING_MENU_1` FOREIGN KEY (`REG_PF_BIZ_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LODGING_MENU`
--

LOCK TABLES `LODGING_MENU` WRITE;
/*!40000 ALTER TABLE `LODGING_MENU` DISABLE KEYS */;
INSERT INTO `LODGING_MENU` VALUES ('lmm_1','admin001','ln_1','hotel_001','RGN_SCTGRY_001','4호 파티스튜디오형','2','10','250000','20000','흡연 불가능','조식 미포함','','','','Y','2020-09-03 00:00:00','0000-00-00 00:00:00'),('lmm_10','admin001','ln_4','hotel_001','RGN_SCTGRY_001','스탠다드 더블','2','2','189000','20000','흡연 불가능','조식포함','','','','Y','2020-10-04 00:00:00','0000-00-00 00:00:00'),('lmm_102','admin001','ln_1','hotel_001','RGN_SCTGRY_001','4호 파티스튜디오형','2','10','250000','20000','흡연 불가능','조식 미포함','','','','Y','2020-09-03 00:00:00','0000-00-00 00:00:00'),('lmm_11','admin001','ln_4','hotel_002','RGN_SCTGRY_002','스탠다드 킹','1','2','225000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-04 00:00:00','0000-00-00 00:00:00'),('lmm_12','admin001','ln_4','hotel_003','RGN_SCTGRY_003','디럭스 패밀리 트윈','3','3','243000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-04 00:00:00','0000-00-00 00:00:00'),('lmm_13','admin001','ln_4','hotel_001','RGN_SCTGRY_001','비즈니스 트윈','1','3','279000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-04 00:00:00','0000-00-00 00:00:00'),('lmm_14','admin001','ln_5','hotel_002','RGN_SCTGRY_002','스탠다드','2','2','100000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-05 00:00:00','0000-00-00 00:00:00'),('lmm_15','admin001','ln_5','hotel_003','RGN_SCTGRY_003','슈페리얼','2','2','99000','30000','흡연 불가능','조식포함','','','','Y','2020-10-05 00:00:00','0000-00-00 00:00:00'),('lmm_16','admin001','ln_5','hotel_001','RGN_SCTGRY_001','프리미엄','2','2','109000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-05 00:00:00','0000-00-00 00:00:00'),('lmm_17','admin001','ln_5','hotel_002','RGN_SCTGRY_002','디럭스트윈','2','4','140000','10000','흡연 불가능','조식포함','','','','Y','2020-10-05 00:00:00','0000-00-00 00:00:00'),('lmm_18','admin001','ln_5','hotel_003','RGN_SCTGRY_003','디럭스 커플PC','2','2','120000','20000','흡연 불가능','조식 미포함','','','','Y','2020-10-05 00:00:00','0000-00-00 00:00:00'),('lmm_19','admin001','ln_6','hotel_001','RGN_SCTGRY_001','스탠다드','2','2','68000','20000','흡연 불가능','조식포함','','','','Y','2020-10-06 00:00:00','0000-00-00 00:00:00'),('lmm_2','admin001','ln_1','hotel_002','RGN_SCTGRY_002','3호 호텔라운지형','2','8','350000','30000','흡연 불가능','조식포함','','','','Y','2020-09-04 00:00:00','0000-00-00 00:00:00'),('lmm_20','admin001','ln_6','hotel_002','RGN_SCTGRY_002','디럭스','2','3','80000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-06 00:00:00','0000-00-00 00:00:00'),('lmm_21','admin001','ln_7','hotel_003','RGN_SCTGRY_003','스탠다드-노하드1PC','2','3','100000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-07 00:00:00','0000-00-00 00:00:00'),('lmm_22','admin001','ln_7','hotel_001','RGN_SCTGRY_001','커플PC디럭스-노하드2PC','2','3','99000','30000','흡연 불가능','조식포함','','','','Y','2020-10-07 00:00:00','0000-00-00 00:00:00'),('lmm_23','admin001','ln_7','hotel_002','RGN_SCTGRY_002','스타일디럭스-노하드1PC','2','3','99000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-07 00:00:00','0000-00-00 00:00:00'),('lmm_24','admin001','ln_7','hotel_003','RGN_SCTGRY_003','고사양커플PC','2','2','119000','30000','흡연 불가능','조식포함','','','','Y','2020-10-07 00:00:00','0000-00-00 00:00:00'),('lmm_25','admin001','ln_7','hotel_001','RGN_SCTGRY_001','커플PC트윈-노하드2PC','2','4','120000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-07 00:00:00','0000-00-00 00:00:00'),('lmm_26','admin001','ln_7','hotel_002','RGN_SCTGRY_002','스타일트윈-노하드1PC','2','4','120000','10000','흡연 불가능','조식포함','','','','Y','2020-10-07 00:00:00','0000-00-00 00:00:00'),('lmm_27','admin001','ln_7','hotel_003','RGN_SCTGRY_003','고사양커플PC VIP-게이밍2PC','2','2','130000','20000','흡연 불가능','조식 미포함','','','','Y','2020-10-07 00:00:00','0000-00-00 00:00:00'),('lmm_28','admin001','ln_7','hotel_001','RGN_SCTGRY_001','VIP무비룸-노하드2PC','2','3','130000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-07 00:00:00','0000-00-00 00:00:00'),('lmm_29','admin001','ln_7','hotel_002','RGN_SCTGRY_002','프리미어-노하드2PC','2','3','130000','20000','흡연 불가능','조식포함','','','','Y','2020-10-07 00:00:00','0000-00-00 00:00:00'),('lmm_3','admin001','ln_1','hotel_003','RGN_SCTGRY_003','2호 자쿠지가든형','2','4','350000','30000','흡연 불가능','조식포함','','','','Y','2020-10-01 00:00:00','0000-00-00 00:00:00'),('lmm_30','admin001','ln_7','hotel_003','RGN_SCTGRY_003','VIP사우나룸-노하드2PC','2','3','130000','20000','흡연 불가능','조식 미포함','','','','Y','2020-10-07 00:00:00','0000-00-00 00:00:00'),('lmm_31','admin001','ln_7','hotel_001','RGN_SCTGRY_001','패밀리룸-노하드1PC','2','6','150000','10000','흡연 불가능','조식포함','','','','Y','2020-10-07 00:00:00','0000-00-00 00:00:00'),('lmm_32','admin001','ln_7','hotel_002','RGN_SCTGRY_002','그랜드패밀리룸-노하드2PC','2','8','180000','20000','흡연 불가능','조식 미포함','','','','Y','2020-10-07 00:00:00','0000-00-00 00:00:00'),('lmm_33','admin001','ln_8','hotel_003','RGN_SCTGRY_003','슈페리어 더블','2','2','169000','30000','흡연 불가능','조식포함','','','','Y','2020-10-08 00:00:00','0000-00-00 00:00:00'),('lmm_34','admin001','ln_8','hotel_001','RGN_SCTGRY_001','슈페리어 트윈','3','3','239000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-08 00:00:00','0000-00-00 00:00:00'),('lmm_35','admin001','ln_8','hotel_002','RGN_SCTGRY_002','슈페리어 온돌','4','4','259000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-08 00:00:00','0000-00-00 00:00:00'),('lmm_36','admin001','ln_8','hotel_003','RGN_SCTGRY_003','스위트 스파','3','3','269000','30000','흡연 불가능','조식포함','','','','Y','2020-10-08 00:00:00','0000-00-00 00:00:00'),('lmm_37','admin001','ln_9','hotel_001','RGN_SCTGRY_001','디럭스 더블','2','2','121000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-09 00:00:00','0000-00-00 00:00:00'),('lmm_38','admin001','ln_9','hotel_002','RGN_SCTGRY_002','디럭스 패밀리 트윈','2','3','134000','30000','흡연 불가능','조식포함','','','','Y','2020-10-09 00:00:00','0000-00-00 00:00:00'),('lmm_39','admin001','ln_9','hotel_003','RGN_SCTGRY_003','디럭스 온돌','2','4','145000','10000','흡연 불가능','조식 미포함','','','','Y','2020-10-09 00:00:00','0000-00-00 00:00:00'),('lmm_4','admin001','ln_1','hotel_001','RGN_SCTGRY_001','1호 패밀리 리조트형','2','8','350000','30000','흡연 불가능','조식포함','','','','Y','2020-10-01 00:00:00','0000-00-00 00:00:00'),('lmm_40','admin001','ln_9','hotel_001','RGN_SCTGRY_001','디럭스 스위트 트윈','2','3','250000','20000','흡연 불가능','조식포함','','','','Y','2020-10-09 00:00:00','0000-00-00 00:00:00'),('lmm_41','admin001','ln_9','hotel_002','RGN_SCTGRY_002','디럭스 스위트 더블','2','2','250000','20000','흡연 불가능','조식 미포함','','','','Y','2020-10-09 00:00:00','0000-00-00 00:00:00'),('lmm_42','admin001','ln_10','hotel_003','RGN_SCTGRY_003','일반실A(금연)','2','2','85000','30000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_43','admin001','ln_10','hotel_001','RGN_SCTGRY_001','일반실B(금연)','2','2','90000','30000','흡연 불가능','조식포함','','','','Y','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_44','admin001','ln_10','hotel_002','RGN_SCTGRY_002','일반실A(흡연)','2','2','85000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_45','admin001','ln_10','hotel_003','RGN_SCTGRY_003','일반실B(흡연)','2','2','90000','30000','흡연 불가능','조식포함','','','','Y','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_46','admin001','ln_10','hotel_001','RGN_SCTGRY_001','한실(흡연)','2','2','90000','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_47','admin001','ln_10','hotel_002','RGN_SCTGRY_002','특실트윈(금연)','2','2','140000','30000','흡연 불가능','조식포함','','','','Y','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_48','admin001','ln_10','hotel_003','RGN_SCTGRY_003','스위트트윈(금연)','2','2','150000','10000','흡연 불가능','조식포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_49','admin001','ln_10','hotel_001','RGN_SCTGRY_001','패밀리트윈(금연)','3','3','170000','20000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_5','admin001','ln_2','hotel_002','RGN_SCTGRY_002','비즈니스트윈(룸온리)','2','2','115400','30000','흡연 불가능','조식포함','','','','Y','2020-10-02 00:00:00','0000-00-00 00:00:00'),('lmm_50','admin001','ln_11','hotel_002','RGN_SCTGRY_002','스탠다드 더블','2','2','180000','20000','흡연 불가능','조식포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_51','admin001','ln_11','hotel_003','RGN_SCTGRY_003','디럭스 트윈','2','3','200000','30000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_52','admin001','ln_11','hotel_001','RGN_SCTGRY_001','슈페리어','2','5','250000','20000','흡연 불가능','조식포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_53','admin001','ln_11','hotel_002','RGN_SCTGRY_002','스위트','2','6','300000','30000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_54','admin001','ln_11','hotel_003','RGN_SCTGRY_003','로얄 스위트','2','6','500000','30000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_55','admin001','ln_12','hotel_001','RGN_SCTGRY_001','디럭스 더블','2','3','149000','30000','흡연 불가능','조식포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_56','admin001','ln_12','hotel_002','RGN_SCTGRY_002','프리미엄 커플PC','2','2','169000','10000','흡연 불가능','조식포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_57','admin001','ln_12','hotel_003','RGN_SCTGRY_003','프리미엄 더블','2','3','169000','20000','흡연 불가능','조식포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_58','admin001','ln_12','hotel_001','RGN_SCTGRY_001','디럭스 트윈','3','4','200000','20000','흡연 불가능','조식포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_59','admin001','ln_12','hotel_002','RGN_SCTGRY_002','패밀리 트윈','4','5','240000','30000','흡연 불가능','조식포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_6','admin001','ln_2','hotel_003','RGN_SCTGRY_003','비즈니스트윈(1인조식패키지)','1','2','123000','30000','흡연 불가능','조식포함','','','','Y','2020-10-02 00:00:00','0000-00-00 00:00:00'),('lmm_60','admin001','ln_13','hotel_003','RGN_SCTGRY_003','Standard Double','2','2','148000','20000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_61','admin001','ln_13','hotel_001','RGN_SCTGRY_001','Standard Twin','2','2','170000','30000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_62','admin001','ln_13','hotel_002','RGN_SCTGRY_002','Deluxe Double','2','2','175000','30000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_63','admin001','ln_13','hotel_003','RGN_SCTGRY_003','Deluxe Twin','2','2','196000','30000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_64','admin001','ln_13','hotel_001','RGN_SCTGRY_001','Deluxe Family Twin','3','3','222000','10000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_65','admin001','ln_13','hotel_002','RGN_SCTGRY_002','Triple Room','3','3','264000','20000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_66','admin001','ln_13','hotel_003','RGN_SCTGRY_003','Suite Double','2','2','300000','20000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_67','admin001','ln_13','hotel_001','RGN_SCTGRY_001','Suite Twin','2','3','336000','30000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_68','admin001','ln_13','hotel_002','RGN_SCTGRY_002','Korean Suite Ondol','4','8','384000','20000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_69','admin001','ln_13','hotel_003','RGN_SCTGRY_003','Premier Suite','2','3','420000','30000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_7','admin001','ln_2','hotel_001','RGN_SCTGRY_001','비즈니스트윈(2인조식패키지)','2','2','140200','30000','흡연 불가능','조식 미포함','','','','Y','2020-10-02 00:00:00','0000-00-00 00:00:00'),('lmm_70','admin001','ln_13','hotel_001','RGN_SCTGRY_003','Royal Suite','2','3','750000','10000','흡연 불가능','조식 미포함','','','','N','2020-10-10 00:00:00','0000-00-00 00:00:00'),('lmm_8','admin001','ln_2','hotel_002','RGN_SCTGRY_002','디럭스 더블','2','2','130000','10000','흡연 불가능','조식포함','','','','Y','2020-10-02 00:00:00','0000-00-00 00:00:00'),('lmm_9','admin001','ln_3','hotel_003','RGN_SCTGRY_003','테오리아','2','3','450000','20000,20000','흡연 불가능','조식포함해줄까?','','','','Y','2020-10-03 00:00:00','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `LODGING_MENU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POST_COMMENT_LIKE_DISLIKE`
--

DROP TABLE IF EXISTS `POST_COMMENT_LIKE_DISLIKE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `POST_COMMENT_LIKE_DISLIKE` (
                                             `PST_CMNT_LIKE_DISLIKE_CD` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                             `REG_MBR_ID` varchar(20) NOT NULL,
                                             `PST_CMNT_NO` varchar(20) NOT NULL,
                                             `PST_CMNT_LIKE_DISLIKE_YN` char(1) NOT NULL DEFAULT '0',
                                             `RECORD_YMD` timestamp NOT NULL,
                                             PRIMARY KEY (`PST_CMNT_LIKE_DISLIKE_CD`),
                                             KEY `FK_MM_PCLD` (`REG_MBR_ID`),
                                             CONSTRAINT `FK_MM_PCLD` FOREIGN KEY (`REG_MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POST_COMMENT_LIKE_DISLIKE`
--

LOCK TABLES `POST_COMMENT_LIKE_DISLIKE` WRITE;
/*!40000 ALTER TABLE `POST_COMMENT_LIKE_DISLIKE` DISABLE KEYS */;
INSERT INTO `POST_COMMENT_LIKE_DISLIKE` VALUES ('PST_CMNT_LIKE_DISLIKE_1','member001','PST_1','Y','2023-12-31 15:00:00'),('PST_CMNT_LIKE_DISLIKE_10','member003','PST_CMNT_5','N','2023-12-31 15:00:00'),('PST_CMNT_LIKE_DISLIKE_2','member002','PST_CMNT_1','Y','2023-12-31 15:00:00'),('PST_CMNT_LIKE_DISLIKE_3','member003','PST_7','Y','2023-12-31 15:00:00'),('PST_CMNT_LIKE_DISLIKE_4','member004','PST_CMNT_3','Y','2023-12-31 15:00:00'),('PST_CMNT_LIKE_DISLIKE_5','member001','PST_8','Y','2023-12-31 15:00:00'),('PST_CMNT_LIKE_DISLIKE_6','member006','PST_CMNT_4','Y','2023-12-31 15:00:00'),('PST_CMNT_LIKE_DISLIKE_7','member006','PST_2','N','2023-12-31 15:00:00'),('PST_CMNT_LIKE_DISLIKE_8','member006','PST_CMNT_5','N','2023-12-31 15:00:00'),('PST_CMNT_LIKE_DISLIKE_9','member006','PST_3','N','2023-12-31 15:00:00');
/*!40000 ALTER TABLE `POST_COMMENT_LIKE_DISLIKE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NOTICE`
--

DROP TABLE IF EXISTS `NOTICE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NOTICE` (
                          `NTC_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
                          `REG_PF_ID` varchar(20) NOT NULL,
                          `NTC_CTGRY_NO` varchar(20) NOT NULL,
                          `NTC_TTL` varchar(20) NOT NULL,
                          `NTC_CN` mediumtext NOT NULL,
                          `NTC_INQ_CNT` int unsigned NOT NULL,
                          `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                          `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                          `REG_YMD` timestamp NOT NULL,
                          `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                          PRIMARY KEY (`NTC_NO`),
                          KEY `FK_NOTICE_MEMBER_MANAGE` (`REG_PF_ID`),
                          KEY `FK_NOTICE_NOTICE_CATEGORY` (`NTC_CTGRY_NO`),
                          CONSTRAINT `FK_NOTICE_MEMBER_MANAGE` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                          CONSTRAINT `FK_NOTICE_NOTICE_CATEGORY` FOREIGN KEY (`NTC_CTGRY_NO`) REFERENCES `NOTICE_CATEGORY` (`NTC_CTGRY_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NOTICE`
--

LOCK TABLES `NOTICE` WRITE;
/*!40000 ALTER TABLE `NOTICE` DISABLE KEYS */;
INSERT INTO `NOTICE` VALUES ('NTC_1','admin001','NTC_CTGRY_3','고객센터 휴무 공지','2024-01-18 일은 고객센터 휴무일입니다.',514,'id001','Y','2023-12-31 15:00:00','2024-05-29 02:53:14'),('NTC_2','admin001','NTC_CTGRY_3','웹사이트 서버 점검 공지','2024-01-29 04:00 부터 06:00 까지는 홈페이지 점검 시간입니다. ',402,'admin001','Y','2024-01-31 15:00:00',NULL),('NTC_3','admin001','NTC_CTGRY_3','시스템 업그레이드 예정 공지','\"시스템 업그레이드 작업을 진행할 예정입니다. \r<br><br>작업 일자 : 2024-01-14 05:00~07:00 (예상 소요 시간 : 2시간)\"',304,'id001','Y','2024-02-29 15:00:00','2024-05-28 01:19:17'),('NTC_4','admin001','NTC_CTGRY_5','시스템 장애 공지','시스템에 문제가 발생해 일부 서비스에 접근이 불가능한 상태입니다.',205,'admin001','Y','2024-03-31 15:00:00',NULL),('NTC_5','admin001','NTC_CTGRY_1','자연재해 경보 공지','현재 전주 지역에 자연재해 발생으로 인한 경보를 발령하고 있습니다. \r<br>안전 조치에 주의하시기를 바랍니다.',180,'id001','Y','2024-04-30 15:00:00','2024-05-28 02:08:06'),('NTC_6','id001','NTC_CTGRY_1','공지사항 테스트','테스트 !!!!!',4,NULL,'Y','2024-05-21 17:10:45',NULL),('NTC_7','id001','NTC_CTGRY_4','이벤트 공지','선착순 죽빵 이벤트 실시 ',2,NULL,'Y','2024-05-21 18:00:33',NULL),('NTC_8','id001','NTC_CTGRY_1','긴급 !!!!!!!!','ㅎㅇㅎㅇㅎㅇ',6,'id001','Y','2024-05-21 18:03:13','2024-05-22 04:53:47'),('NTC_9','id001','NTC_CTGRY_5','5/31 시스템 정기점검','안녕하세요, star travel 입니다.\r<br>\r<br>보다 안정적인 서비스를 위하여 아래 기간동안 서비스가 제한될 예정이오니,\r<br>이용에 참고 부탁드립니다.\r<br>\r<br>\r<br>정기점검 일시 : 5월 31일[금] 01:00 ~ 07:00  \r<br> - 해당 작업은 작업상태에 따라 단축되거나 연장될 수 있습니다. \r<br>\r<br>항상 고객님께 최선의 서비스를 제공하기 위해 노력하는 star travel 이 되겠습니다\r<br>감사합니다. ',0,NULL,'Y','2024-05-28 07:12:09',NULL);
/*!40000 ALTER TABLE `NOTICE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESERVATION_DETAIL`
--

DROP TABLE IF EXISTS `RESERVATION_DETAIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RESERVATION_DETAIL` (
                                      `RSVT_DETAIL_CD` varchar(50) NOT NULL,
                                      `BZENTY_RSVT_NO` varchar(50) NOT NULL,
                                      `CHECKIN_DT` char(10) NOT NULL,
                                      `CHECKOUT_DT` char(10) NOT NULL,
                                      `LODGE_DAYS` varchar(10) NOT NULL,
                                      PRIMARY KEY (`RSVT_DETAIL_CD`),
                                      KEY `FK_COMMON_RESERVATION_TO_RESERVATION_DETAIL_1` (`BZENTY_RSVT_NO`),
                                      CONSTRAINT `FK_COMMON_RESERVATION_TO_RESERVATION_DETAIL_1` FOREIGN KEY (`BZENTY_RSVT_NO`) REFERENCES `COMMON_RESERVATION` (`BZENTY_RSVT_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESERVATION_DETAIL`
--

LOCK TABLES `RESERVATION_DETAIL` WRITE;
/*!40000 ALTER TABLE `RESERVATION_DETAIL` DISABLE KEYS */;
/*!40000 ALTER TABLE `RESERVATION_DETAIL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REVIEW_COMMENT`
--

DROP TABLE IF EXISTS `REVIEW_COMMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REVIEW_COMMENT` (
                                  `REV_CMNT_CD` varchar(200) NOT NULL,
                                  `REG_BIZ_ID` varchar(20) NOT NULL,
                                  `PRCHS_REV_CD` varchar(200) NOT NULL,
                                  `RLS_YN_CD` varchar(50) NOT NULL,
                                  `PRTNR_BPLC_CD` varchar(200) NOT NULL,
                                  `TRIP_LCTGRY_CD` varchar(200) NOT NULL,
                                  `RGN_SCTGRY_NM` varchar(200) NOT NULL,
                                  `REV_CMNT_CN` mediumtext NOT NULL,
                                  `REG_YMD` timestamp NOT NULL,
                                  PRIMARY KEY (`REV_CMNT_CD`),
                                  KEY `FK_MEMBER_MANAGE_TO_REVIEW_COMMENT_1` (`REG_BIZ_ID`),
                                  KEY `FK_PURCHASE_REVIEW_TO_REVIEW_COMMENT_1` (`PRCHS_REV_CD`),
                                  KEY `FK_DISCLOSURE_STATUS_TO_REVIEW_COMMENT_1` (`RLS_YN_CD`),
                                  KEY `FK_PARTNER_BUSINESS_PLACE_TO_REVIEW_COMMENT_1` (`PRTNR_BPLC_CD`),
                                  KEY `FK_TRIP_LARGE_CATEGORY_TO_REVIEW_COMMENT_1` (`TRIP_LCTGRY_CD`),
                                  CONSTRAINT `FK_DISCLOSURE_STATUS_TO_REVIEW_COMMENT_1` FOREIGN KEY (`RLS_YN_CD`) REFERENCES `DISCLOSURE_STATUS` (`RLS_YN_CD`),
                                  CONSTRAINT `FK_MEMBER_MANAGE_TO_REVIEW_COMMENT_1` FOREIGN KEY (`REG_BIZ_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                  CONSTRAINT `FK_PARTNER_BUSINESS_PLACE_TO_REVIEW_COMMENT_1` FOREIGN KEY (`PRTNR_BPLC_CD`) REFERENCES `PARTNER_BUSINESS_PLACE` (`PRTNR_BPLC_CD`),
                                  CONSTRAINT `FK_PURCHASE_REVIEW_TO_REVIEW_COMMENT_1` FOREIGN KEY (`PRCHS_REV_CD`) REFERENCES `PURCHASE_REVIEW` (`PRCHS_REV_CD`),
                                  CONSTRAINT `FK_TRIP_LARGE_CATEGORY_TO_REVIEW_COMMENT_1` FOREIGN KEY (`TRIP_LCTGRY_CD`) REFERENCES `TRIP_LARGE_CATEGORY` (`TRIP_LCTGRY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVIEW_COMMENT`
--

LOCK TABLES `REVIEW_COMMENT` WRITE;
/*!40000 ALTER TABLE `REVIEW_COMMENT` DISABLE KEYS */;
INSERT INTO `REVIEW_COMMENT` VALUES ('RCC_1','business001','PRCHS_REV_001','DISCLOSURE_001','tour_001','TRIP_LCTGRY_01','평화1동','감사합니다','2023-03-31 15:00:00'),('RCC_2','business009','PRCHS_REV_002','DISCLOSURE_001','RSTRN_002','TRIP_LCTGRY_02','평화1동','감사합니다','2023-05-10 15:00:00'),('RCC_3','business001','PRCHS_REV_003','DISCLOSURE_001','tour_001','TRIP_LCTGRY_01','평화1동','열심히 하겠습니다','2023-07-21 15:00:00'),('RCC_4','business007','PRCHS_REV_004','DISCLOSURE_001','hotel_003','TRIP_LCTGRY_03','서신동','감사합니다','2023-09-02 15:00:00'),('RCC_5','business009','PRCHS_REV_005','DISCLOSURE_001','RSTRN_002','TRIP_LCTGRY_02','서신동','감사합니다','2023-12-31 15:00:00'),('RCC_6','business009','PRCHS_REV_006','DISCLOSURE_002','RSTRN_002','TRIP_LCTGRY_02','서신동','감사합니다','2024-02-10 15:00:00');
/*!40000 ALTER TABLE `REVIEW_COMMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MEMBER_GRADE`
--

DROP TABLE IF EXISTS `MEMBER_GRADE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MEMBER_GRADE` (
                                `MBR_GRD_NO` varchar(20) NOT NULL,
                                `REG_PF_ID` varchar(20) NOT NULL,
                                `MBR_GRD_NM` varchar(200) NOT NULL,
                                `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                `REG_YMD` timestamp NOT NULL,
                                `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                PRIMARY KEY (`MBR_GRD_NO`),
                                KEY `FK_MEMBER_MANAGE_TO_MEMBER_GRADE_1` (`REG_PF_ID`),
                                CONSTRAINT `FK_MEMBER_MANAGE_TO_MEMBER_GRADE_1` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEMBER_GRADE`
--

LOCK TABLES `MEMBER_GRADE` WRITE;
/*!40000 ALTER TABLE `MEMBER_GRADE` DISABLE KEYS */;
INSERT INTO `MEMBER_GRADE` VALUES ('uln_001','admin001','플랫폼 운영 담당자','null','Y','2022-12-31 15:00:00',NULL),('uln_101','admin001','개인사업자','null','Y','2022-12-31 15:00:00',NULL),('uln_301','admin001','회원','admin001','Y','2022-12-31 15:00:00',NULL);
/*!40000 ALTER TABLE `MEMBER_GRADE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QNA_FILE`
--

DROP TABLE IF EXISTS `QNA_FILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QNA_FILE` (
                            `QNA_FILE_NO` varchar(20) NOT NULL,
                            `REG_MBR_BIZ_ID` varchar(20) NOT NULL,
                            `QNA_NO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                            `QNA_FILE_NAME` varchar(30) NOT NULL,
                            `QNA_FILE_PATH_NM` varchar(50) NOT NULL,
                            `MDFCN_MBR_BIZ_ID` varchar(20) DEFAULT NULL,
                            `REG_YMD` timestamp NOT NULL,
                            `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                            PRIMARY KEY (`QNA_FILE_NO`),
                            KEY `REG_MBR_BIZ_ID` (`REG_MBR_BIZ_ID`),
                            KEY `QNA_NO` (`QNA_NO`) USING BTREE,
                            CONSTRAINT `FK_QNA_FILE_MEMBER_MANAGE` FOREIGN KEY (`REG_MBR_BIZ_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                            CONSTRAINT `FK_QNA_FILE_QNA_QUESTION` FOREIGN KEY (`QNA_NO`) REFERENCES `QNA_QUESTION` (`QNA_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QNA_FILE`
--

LOCK TABLES `QNA_FILE` WRITE;
/*!40000 ALTER TABLE `QNA_FILE` DISABLE KEYS */;
INSERT INTO `QNA_FILE` VALUES ('QNA_FILE_1','business001','QNA_1','a.jpeg','','business001','2023-12-31 15:00:00',NULL),('QNA_FILE_2','member001','QNA_2','b.jpeg','','member001','2023-12-31 15:00:00',NULL),('QNA_FILE_3','member002','QNA_3','c.jpeg','','member002','2023-12-31 15:00:00',NULL);
/*!40000 ALTER TABLE `QNA_FILE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FAVORITES_POST`
--

DROP TABLE IF EXISTS `FAVORITES_POST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FAVORITES_POST` (
                                  `FAVS_PST_NO` varchar(20) NOT NULL,
                                  `REG_ALL_ID` varchar(20) NOT NULL,
                                  `REG_YMD` timestamp NOT NULL,
                                  PRIMARY KEY (`FAVS_PST_NO`),
                                  KEY `FK_MEMBER_MANAGE_TO_FAVORITES_POST_1` (`REG_ALL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FAVORITES_POST`
--

LOCK TABLES `FAVORITES_POST` WRITE;
/*!40000 ALTER TABLE `FAVORITES_POST` DISABLE KEYS */;
/*!40000 ALTER TABLE `FAVORITES_POST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REPORT_CATEGORY`
--

DROP TABLE IF EXISTS `REPORT_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REPORT_CATEGORY` (
                                   `REPT_CTGRY_NO` varchar(20) NOT NULL,
                                   `REG_PF_ID` varchar(20) NOT NULL,
                                   `REPT_CTGRY_TYPE` varchar(20) NOT NULL,
                                   `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                   `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                   `REG_YMD` timestamp NOT NULL,
                                   `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                   PRIMARY KEY (`REPT_CTGRY_NO`),
                                   KEY `FK_MM_RC` (`REG_PF_ID`),
                                   CONSTRAINT `FK_MM_RC` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REPORT_CATEGORY`
--

LOCK TABLES `REPORT_CATEGORY` WRITE;
/*!40000 ALTER TABLE `REPORT_CATEGORY` DISABLE KEYS */;
INSERT INTO `REPORT_CATEGORY` VALUES ('REPT_CTGRY_1','admin001','욕설/인신공격','admin001','Y','2023-12-31 15:00:00',NULL),('REPT_CTGRY_2','admin001','홍보/상업성','admin001','Y','2023-12-31 15:00:00',NULL),('REPT_CTGRY_3','admin001','음란/선정성','admin001','Y','2023-12-31 15:00:00',NULL),('REPT_CTGRY_4','admin001','같은 내용 도배','admin001','Y','2023-12-31 15:00:00',NULL),('REPT_CTGRY_5','admin001','부적절한 콘텐츠','admin001','Y','2023-12-31 15:00:00',NULL),('REPT_CTGRY_6','admin001','스팸 또는 광고','admin001','Y','2023-12-31 15:00:00',NULL),('REPT_CTGRY_7','admin001','개인정보 노출','admin001','Y','2023-12-31 15:00:00',NULL),('REPT_CTGRY_8','admin001','권리침해 신고','admin001','Y','2023-12-31 15:00:00',NULL),('REPT_CTGRY_9','admin001','기타','admin001','Y','2023-12-31 15:00:00',NULL);
/*!40000 ALTER TABLE `REPORT_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REGION_MEDIUM_CATEGORY`
--

DROP TABLE IF EXISTS `REGION_MEDIUM_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REGION_MEDIUM_CATEGORY` (
                                          `RGN_MCTGRY_CD` varchar(200) NOT NULL,
                                          `REG_PF_ID` varchar(20) NOT NULL,
                                          `RGN_LCTGRY_CD` varchar(200) NOT NULL,
                                          `RGN_MCTGRY_NM` varchar(200) NOT NULL,
                                          `RGN_LCTGRY_NM` varchar(200) NOT NULL,
                                          `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                          `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                          `REG_YMD` timestamp NOT NULL,
                                          `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                          PRIMARY KEY (`RGN_MCTGRY_CD`),
                                          KEY `FK_MEMBER_MANAGE_TO_REGION_MEDIUM_CATEGORY_1` (`REG_PF_ID`),
                                          KEY `FK_REGION_LARGE_CATEGORY_TO_REGION_MEDIUM_CATEGORY_1` (`RGN_LCTGRY_CD`),
                                          CONSTRAINT `FK_MEMBER_MANAGE_TO_REGION_MEDIUM_CATEGORY_1` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                          CONSTRAINT `FK_REGION_LARGE_CATEGORY_TO_REGION_MEDIUM_CATEGORY_1` FOREIGN KEY (`RGN_LCTGRY_CD`) REFERENCES `REGION_LARGE_CATEGORY` (`RGN_LCTGRY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REGION_MEDIUM_CATEGORY`
--

LOCK TABLES `REGION_MEDIUM_CATEGORY` WRITE;
/*!40000 ALTER TABLE `REGION_MEDIUM_CATEGORY` DISABLE KEYS */;
INSERT INTO `REGION_MEDIUM_CATEGORY` VALUES ('RGN_MCTGRY_001','admin001','RGN_LCTGRY_012','전주시 완산구','전북특별자치도','','Y','2023-01-13 15:00:00','2023-01-13 15:00:00'),('RGN_MCTGRY_002','admin001','RGN_LCTGRY_012','전주시 덕진구','전북특별자치도','','Y','2023-01-14 15:00:00',NULL),('RGN_MCTGRY_003','admin001','RGN_LCTGRY_012','군산시','전북특별자치도','','Y','2023-01-15 15:00:00',NULL),('RGN_MCTGRY_004','admin001','RGN_LCTGRY_012','익산시','전북특별자치도','','Y','2023-01-16 15:00:00',NULL),('RGN_MCTGRY_005','admin001','RGN_LCTGRY_012','정읍시','전북특별자치도','','Y','2023-01-17 15:00:00',NULL),('RGN_MCTGRY_006','admin001','RGN_LCTGRY_012','남원시','전북특별자치도','','Y','2023-01-18 15:00:00',NULL),('RGN_MCTGRY_007','admin001','RGN_LCTGRY_012','김제시','전북특별자치도','','Y','2023-01-19 15:00:00',NULL),('RGN_MCTGRY_008','admin001','RGN_LCTGRY_012','완주군','전북특별자치도','','Y','2023-01-20 15:00:00',NULL),('RGN_MCTGRY_009','admin001','RGN_LCTGRY_012','진안군','전북특별자치도','','Y','2023-01-21 15:00:00',NULL),('RGN_MCTGRY_010','admin001','RGN_LCTGRY_012','무주군','전북특별자치도','','Y','2023-01-22 15:00:00',NULL),('RGN_MCTGRY_011','admin001','RGN_LCTGRY_012','장수군','전북특별자치도','','Y','2023-01-23 15:00:00',NULL),('RGN_MCTGRY_012','admin001','RGN_LCTGRY_012','임실군','전북특별자치도','','Y','2023-01-24 15:00:00',NULL),('RGN_MCTGRY_013','admin001','RGN_LCTGRY_012','순창군','전북특별자치도','','Y','2023-01-25 15:00:00',NULL),('RGN_MCTGRY_014','admin001','RGN_LCTGRY_012','고창군','전북특별자치도','','Y','2023-01-26 15:00:00',NULL),('RGN_MCTGRY_015','admin001','RGN_LCTGRY_012','부안군','전북특별자치도','','Y','2023-01-27 15:00:00',NULL);
/*!40000 ALTER TABLE `REGION_MEDIUM_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PARTNER_BRAND_CATEGORY`
--

DROP TABLE IF EXISTS `PARTNER_BRAND_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PARTNER_BRAND_CATEGORY` (
                                          `PRTNR_BRD_CTGRY_CD` varchar(200) NOT NULL,
                                          `REG_PF_ID` varchar(20) NOT NULL,
                                          `TRIP_LCTGRY_CD` varchar(200) NOT NULL,
                                          `TRIP_LCTGRY_NM` varchar(200) NOT NULL,
                                          `PRTNR_BRD_NM` varchar(200) NOT NULL,
                                          `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                          `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                          `REG_YMD` timestamp NOT NULL,
                                          `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                          PRIMARY KEY (`PRTNR_BRD_CTGRY_CD`),
                                          KEY `FK_MEMBER_MANAGE_TO_PARTNER_BRAND_CATEGORY_1` (`REG_PF_ID`),
                                          KEY `FK_TRIP_LARGE_CATEGORY_TO_PARTNER_BRAND_CATEGORY_1` (`TRIP_LCTGRY_CD`),
                                          CONSTRAINT `FK_MEMBER_MANAGE_TO_PARTNER_BRAND_CATEGORY_1` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                          CONSTRAINT `FK_TRIP_LARGE_CATEGORY_TO_PARTNER_BRAND_CATEGORY_1` FOREIGN KEY (`TRIP_LCTGRY_CD`) REFERENCES `TRIP_LARGE_CATEGORY` (`TRIP_LCTGRY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PARTNER_BRAND_CATEGORY`
--

LOCK TABLES `PARTNER_BRAND_CATEGORY` WRITE;
/*!40000 ALTER TABLE `PARTNER_BRAND_CATEGORY` DISABLE KEYS */;
INSERT INTO `PARTNER_BRAND_CATEGORY` VALUES ('PRTNR_BRD_CTGRY_001','admin001','TRIP_LCTGRY_01','관광지','사업자직접등록','','Y','2023-01-13 15:00:00',NULL),('PRTNR_BRD_CTGRY_002','admin001','TRIP_LCTGRY_01','관광지','한국관광공사','','Y','2023-01-13 15:00:00',NULL),('PRTNR_BRD_CTGRY_003','admin001','TRIP_LCTGRY_01','관광지','전주시청 관광담당자','','Y','2023-01-13 15:00:00',NULL),('PRTNR_BRD_CTGRY_004','admin001','TRIP_LCTGRY_02','음식점','사업자직접등록','','Y','2023-01-13 15:00:00',NULL),('PRTNR_BRD_CTGRY_005','admin001','TRIP_LCTGRY_02','음식점','배달의 민족','','Y','2023-01-13 15:00:00',NULL),('PRTNR_BRD_CTGRY_006','admin001','TRIP_LCTGRY_02','음식점','요기요','','Y','2023-01-13 15:00:00',NULL),('PRTNR_BRD_CTGRY_007','admin001','TRIP_LCTGRY_03','숙소','사업자직접등록','','Y','2023-01-13 15:00:00',NULL),('PRTNR_BRD_CTGRY_008','admin001','TRIP_LCTGRY_03','숙소','여기어때','','Y','2023-01-13 15:00:00',NULL),('PRTNR_BRD_CTGRY_009','admin001','TRIP_LCTGRY_03','숙소','부킹닷컴','','Y','2023-01-13 15:00:00',NULL),('PRTNR_BRD_CTGRY_010','admin001','TRIP_LCTGRY_03','숙소','야놀자','','Y','2023-01-13 15:00:00',NULL);
/*!40000 ALTER TABLE `PARTNER_BRAND_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QNA_ANSWER`
--

DROP TABLE IF EXISTS `QNA_ANSWER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QNA_ANSWER` (
                              `ANS_NO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                              `REG_PF_ID` varchar(20) NOT NULL,
                              `QNA_NO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                              `ANS_CN` varchar(500) NOT NULL,
                              `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                              `REG_YMD` timestamp NOT NULL,
                              `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                              PRIMARY KEY (`ANS_NO`) USING BTREE,
                              KEY `FK_MM_QA` (`REG_PF_ID`),
                              KEY `FK_QQ_QA` (`QNA_NO`) USING BTREE,
                              CONSTRAINT `FK_MM_QA` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                              CONSTRAINT `FK_QQ_QA` FOREIGN KEY (`QNA_NO`) REFERENCES `QNA_QUESTION` (`QNA_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QNA_ANSWER`
--

LOCK TABLES `QNA_ANSWER` WRITE;
/*!40000 ALTER TABLE `QNA_ANSWER` DISABLE KEYS */;
INSERT INTO `QNA_ANSWER` VALUES ('ANS_1','admin001','QNA_1','해당 내용은 …dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd','admin001','2023-12-31 15:00:00',NULL),('ANS_2','admin001','QNA_2','해당 내용은 …dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd','admin001','2023-12-31 15:00:00',NULL),('ANS_3','admin001','QNA_3','해당 내용은 …','admin001','2023-12-31 15:00:00',NULL);
/*!40000 ALTER TABLE `QNA_ANSWER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PLATFORM_PARTNERSHIP_INFO`
--

DROP TABLE IF EXISTS `PLATFORM_PARTNERSHIP_INFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PLATFORM_PARTNERSHIP_INFO` (
                                             `PF_MNGR_RANK_INFO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                             `REG_PF_ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                             `PF_MNGR_RANK` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                             `destination_content_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                             `DN_CON_TYPE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                             `DN_TITLE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                             `DN_FIRST_ADD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                             `DN_F_IMG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                             `RECOMMENDATION_DETAILS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                             PRIMARY KEY (`PF_MNGR_RANK_INFO`),
                                             KEY `REG_PF_ID` (`REG_PF_ID`),
                                             KEY `PF_MNGR_RANK` (`PF_MNGR_RANK`),
                                             KEY `FK_PLATFORM_PARTNERSHIP_INFO_TOUR_INFO_FROM_API` (`destination_content_id`) USING BTREE,
                                             CONSTRAINT `FK_PLATFORM_PARTNERSHIP_INFO_MEMBER_MANAGE` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                             CONSTRAINT `FK_PLATFORM_PARTNERSHIP_INFO_PLATFORM_PARTNERSHIP` FOREIGN KEY (`PF_MNGR_RANK`) REFERENCES `PLATFORM_PARTNERSHIP` (`PF_MNGR_RANK`),
                                             CONSTRAINT `FK_PLATFORM_PARTNERSHIP_INFO_TOUR_INFO_FROM_API` FOREIGN KEY (`destination_content_id`) REFERENCES `TOUR_INFO_FROM_API` (`destination_content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PLATFORM_PARTNERSHIP_INFO`
--

LOCK TABLES `PLATFORM_PARTNERSHIP_INFO` WRITE;
/*!40000 ALTER TABLE `PLATFORM_PARTNERSHIP_INFO` DISABLE KEYS */;
INSERT INTO `PLATFORM_PARTNERSHIP_INFO` VALUES ('pmr_1','admin001','pp_1','127269','관광지','청담근린공원','서울특별시 강남구 영동대로131길 26',NULL,'산책 명당'),('pmr_2','admin001','pp_2','2946231','관광지','에이바이봄','서울특별시 강남구 청담동 91-6 에이바이봄',NULL,'가나다라'),('pmr_3','admin001','pp_3','2592885','관광지','엘크레','서울특별시 강남구 도산대로15길 41',NULL,'가나다라'),('pmr_4','admin001','pp_4','3080809','숙소','호텔페이토 삼성','서울특별시 강남구 테헤란로87길 9 (삼성동)',NULL,'호텔입니다'),('pmr_5','admin001','pp_5','2783723','숙소','조선 팰리스 서울 강남, 럭셔리 컬렉션 호텔 (Josun Palace, a Luxury Collection Hotel, Seoul Gangnam)','서울특별시 강남구 테헤란로 231',NULL,'호텔입니다.'),('pmr_6','admin001','pp_6','2049307','숙소','녹운재[한국관광 품질인증/Korea Quality]','전북특별자치도 완주군 소양면 송광수만로 472-18',NULL,'숙소입니다.'),('pmr_7','admin001','pp_7','2685274','음식점','갓덴스시 강남','서울특별시 강남구 테헤란로 109',NULL,'맛집입니다.'),('pmr_8','admin001','pp_8','2851446','음식점','몽펠리에 카페 레스토랑','전북특별자치도 완주군 원대흥길 127-21',NULL,'음식점입니다.'),('pmr_9','admin001','pp_9','2929928','음식점','그 날의 온도','전북특별자치도 전주시 덕진구 원동로 45',NULL,'음식점입니다.');
/*!40000 ALTER TABLE `PLATFORM_PARTNERSHIP_INFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PURCHASE_REVIEW`
--

DROP TABLE IF EXISTS `PURCHASE_REVIEW`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PURCHASE_REVIEW` (
                                   `PRCHS_REV_CD` varchar(200) NOT NULL,
                                   `REG_MBR_ID` varchar(20) NOT NULL,
                                   `RLS_YN_CD` varchar(50) NOT NULL,
                                   `RAT_CTGRY_NO` varchar(200) NOT NULL,
                                   `PRTNR_BPLC_CD` varchar(200) NOT NULL,
                                   `RGN_SCTGRY_CD` varchar(200) NOT NULL,
                                   `TRIP_LCTGRY_CD` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                   `TRIP_SCTGRY_CD` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                   `STLM_CERT_FILE_CD` varchar(200) DEFAULT NULL,
                                   `BZENTY_RSVT_NO` varchar(200) DEFAULT NULL,
                                   `PRCHS_CD` varchar(200) DEFAULT NULL,
                                   `PRCHS_GDSOPT_CD` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                   `PRCHS_REV_CN` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
                                   `LIKE_TOTAL` int DEFAULT NULL,
                                   `DISLIKE_TOTAL` int DEFAULT NULL,
                                   `REPT_TOTAL` int DEFAULT NULL,
                                   `REG_YMD` timestamp NULL DEFAULT NULL,
                                   `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                   PRIMARY KEY (`PRCHS_REV_CD`),
                                   KEY `FK_MEMBER_MANAGE_TO_PURCHASE_REVIEW_1` (`REG_MBR_ID`),
                                   KEY `FK_DISCLOSURE_STATUS_TO_PURCHASE_REVIEW_1` (`RLS_YN_CD`),
                                   KEY `FK_RATING_CATEGORY_TO_PURCHASE_REVIEW_1` (`RAT_CTGRY_NO`),
                                   KEY `FK_PARTNER_BUSINESS_PLACE_TO_PURCHASE_REVIEW_1` (`PRTNR_BPLC_CD`),
                                   KEY `FK_REGION_SMALL_CATEGORY_TO_PURCHASE_REVIEW_1` (`RGN_SCTGRY_CD`),
                                   CONSTRAINT `FK_DISCLOSURE_STATUS_TO_PURCHASE_REVIEW_1` FOREIGN KEY (`RLS_YN_CD`) REFERENCES `DISCLOSURE_STATUS` (`RLS_YN_CD`),
                                   CONSTRAINT `FK_MEMBER_MANAGE_TO_PURCHASE_REVIEW_1` FOREIGN KEY (`REG_MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                   CONSTRAINT `FK_PARTNER_BUSINESS_PLACE_TO_PURCHASE_REVIEW_1` FOREIGN KEY (`PRTNR_BPLC_CD`) REFERENCES `PARTNER_BUSINESS_PLACE` (`PRTNR_BPLC_CD`),
                                   CONSTRAINT `FK_RATING_CATEGORY_TO_PURCHASE_REVIEW_1` FOREIGN KEY (`RAT_CTGRY_NO`) REFERENCES `RATING_CATEGORY` (`RAT_CTGRY_NO`),
                                   CONSTRAINT `FK_REGION_SMALL_CATEGORY_TO_PURCHASE_REVIEW_1` FOREIGN KEY (`RGN_SCTGRY_CD`) REFERENCES `REGION_SMALL_CATEGORY` (`RGN_SCTGRY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PURCHASE_REVIEW`
--

LOCK TABLES `PURCHASE_REVIEW` WRITE;
/*!40000 ALTER TABLE `PURCHASE_REVIEW` DISABLE KEYS */;
INSERT INTO `PURCHASE_REVIEW` VALUES ('PRCHS_REV_001','member001','DISCLOSURE_002','RAT_CTGRY_04','tour_001','RGN_SCTGRY_004','TRIP_LCTGRY_01','TRIP_SCTGRY_002','','','tmm_0001','TOUR_INFO_002','화려한 무대와 감동적인 공연. 추억 속으로 이끌어주는 멋진 시간이었다!',1,0,1,'2023-03-31 15:00:00','2023-04-10 15:00:00'),('PRCHS_REV_002','member001','DISCLOSURE_002','RAT_CTGRY_04','RSTRN_002','RGN_SCTGRY_004','TRIP_LCTGRY_02','TRIP_SCTGRY_005','','','tmm_0005','TOUR_INFO_005','시루방체험은 창의성을 자극하며 독특한 경험을 선사한다. 친절한 스탭과 함께하는 그 곳은 흥미로웠다.',10,3,2,'2023-07-21 15:00:14',NULL),('PRCHS_REV_003','member002','DISCLOSURE_002','RAT_CTGRY_01','tour_001','RGN_SCTGRY_004','TRIP_LCTGRY_01','TRIP_SCTGRY_003','','','tpon_0004','TOUR_INFO_003','혼잡하고 관리 미흡. 실망스러운 체험.',20,1,2,'2023-09-02 14:59:54',NULL),('PRCHS_REV_004','member002','DISCLOSURE_002','RAT_CTGRY_02','hotel_003','RGN_SCTGRY_003','TRIP_LCTGRY_03','TRIP_SCTGRY_022','','','lmm_21','ln_7','시설 낡고 청결상태가 좋지 않음. 편의시설 부족과 소음 문제. 편안한 휴식을 기대하기 어려웠다. 개선이 필요하다',30,3,0,'2013-07-11 15:00:00',NULL),('PRCHS_REV_005','member002','DISCLOSURE_002','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_004','TRIP_LCTGRY_02','TRIP_SCTGRY_015','','','','rn_5','신선한 재료와 훌륭한 맛. 친절한 서비스. 가격 대비 만족스러운 식사였다. 추천합니다',40,2,1,'2024-09-02 15:00:00',NULL),('PRCHS_REV_006','member004','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_004','TRIP_LCTGRY_02','TRIP_SCTGRY_012','','','','rn_2','신선한 재료와 정교한 손질. 일부 서빙이 느릴 수 있으나 전반적으로 맛과 품질은 좋았다. 가격 대비 만족스러운 경험.',50,4,0,'2019-03-11 15:00:00',NULL),('PRCHS_REV_007','member004','DISCLOSURE_002','RAT_CTGRY_05','hotel_003','RGN_SCTGRY_003','TRIP_LCTGRY_03','TRIP_SCTGRY_023','','','lmm_65','ln_13','넓고 편안한 객실. 청결하고 편리한 시설. 훌륭한 숙면을 제공하는 완벽한 선택. 최고!',60,5,0,'2022-02-09 15:00:00',NULL),('PRCHS_REV_008','member005','DISCLOSURE_001','RAT_CTGRY_04','tour_001','RGN_SCTGRY_004','TRIP_LCTGRY_01','TRIP_SCTGRY_001','','','','TOUR_INFO_001','풍부한 전통문화와 역사를 알차게 전시. 관람 후 자부심을 느끼게 해주는 인상적인 곳',70,40,0,'2023-12-21 15:00:00',NULL),('PRCHS_REV_009','member005','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_003','RGN_SCTGRY_005','TRIP_LCTGRY_02','TRIP_SCTGRY_012','','','','rn_9','분위기는 좋으나 음료의 맛은 평범. 가격은 다소 비싸지만 서비스는 친절함. 편안한 분위기에서 조용한 시간을 보낼 수 있지만 맛은 조금 아쉬움.',80,30,0,'2018-11-02 15:00:00',NULL),('PRCHS_REV_010','member001','DISCLOSURE_001','RAT_CTGRY_04','tour_001','RGN_SCTGRY_004','TRIP_LCTGRY_01','TRIP_SCTGRY_005','','','tmm_0011','TOUR_INFO_007','매력적인 전통 가옥과 다채로운 문화공연으로 분위기를 더해줍니다. 맛있는 음식과 함께하는 즐거운 시간을 선사합니다.',90,20,1,'2023-07-21 15:00:00',NULL),('PRCHS_REV_011','member006','DISCLOSURE_001','RAT_CTGRY_05','RSTRN_003','RGN_SCTGRY_005','TRIP_LCTGRY_02','TRIP_SCTGRY_011','','','','rn_8','신선한 재료와 풍부한 맛. 정말 맛있고 만족스럽다. 분위기도 좋고 서비스도 친절해요.',100,10,0,'2023-07-21 15:00:00',NULL),('PRCHS_REV_012','member005','DISCLOSURE_001','RAT_CTGRY_01','hotel_003','RGN_SCTGRY_003','TRIP_LCTGRY_03','TRIP_SCTGRY_022','','','lmm_9','ln_3','최악의 경험. 시설 미흡, 청결도 낮음, 서비스 부족. 실망스러운 숙박.',100,50,0,'2023-09-02 15:00:00',NULL),('PRCHS_REV_013','member001','DISCLOSURE_002','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'11111111',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_014','id003','DISCLOSURE_002','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'111111111111111',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_015','id003','DISCLOSURE_002','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'1111111111111111111111',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_016','id001','DISCLOSURE_002','RAT_CTGRY_04','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'이건될려나..?',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_017','id001','DISCLOSURE_002','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'wlsWk ehlsmsrjdla?',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_018','id001','DISCLOSURE_002','RAT_CTGRY_02','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥? 이거 되냥?',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_019','id001','DISCLOSURE_002','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'1111111111111111111111111111',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_020','id001','DISCLOSURE_002','RAT_CTGRY_04','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'11111111111111111111111111111111111111111',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_021','id001','DISCLOSURE_002','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'3333333333333333333333333333',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_022','id001','DISCLOSURE_002','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_023','id001','DISCLOSURE_002','RAT_CTGRY_04','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'11111111111111111111111111111',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_024','id003','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'11111111111111111111111111111111111112222222222222222222',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_025','id003','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'1111111111111111111',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_026','id003','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'진짜 나만공개의 값이 들어갔니?',NULL,NULL,NULL,'2024-05-16 15:00:00',NULL),('PRCHS_REV_027','id003','DISCLOSURE_001','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'111111111111111111111111111111111',NULL,NULL,NULL,'2024-05-17 05:28:29',NULL),('PRCHS_REV_028','id003','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'대체 사진은 어떻게 삽입을 해야하는겁니까',NULL,NULL,NULL,'2024-05-17 07:50:28',NULL),('PRCHS_REV_029','id003','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'11111111111111111111111111111111111111111111111111111122222222222222222',NULL,NULL,NULL,'2024-05-19 06:35:07',NULL),('PRCHS_REV_030','id003','DISCLOSURE_001','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'11111',NULL,NULL,NULL,'2024-05-20 04:34:10',NULL),('PRCHS_REV_031','id003','DISCLOSURE_001','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'아니 이미지 왜 안돼 왜 안돼 왜 안돼 진짜 왜 db에 저장이 왜 안돼?',NULL,NULL,NULL,'2024-05-20 04:44:03',NULL),('PRCHS_REV_32','id003','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'1111111111111111111111111111111111111111',NULL,NULL,NULL,'2024-05-20 05:55:23',NULL),('PRCHS_REV_33','id003','DISCLOSURE_001','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'111111111111122222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222233333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333332222222222222222222222222222222222222222222222222222222222222222222222222222222222222333333333243243432222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222',NULL,NULL,NULL,'2024-05-20 06:21:07',NULL),('PRCHS_REV_34','id001','DISCLOSURE_001','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'11111111111111111111111111111111111111111111111',NULL,NULL,NULL,'2024-05-21 02:18:27',NULL),('PRCHS_REV_35','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'최악아아가가ㅏㄱ아강가아가아가악아가강ㄱ',NULL,NULL,NULL,'2024-05-21 02:42:45',NULL),('PRCHS_REV_36','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'11111111111111111',NULL,NULL,NULL,'2024-05-21 02:43:05',NULL),('PRCHS_REV_37','id001','DISCLOSURE_001','RAT_CTGRY_04','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'되나되나되니다된되냗나ㅣ더니ㅑ더냐ㅣ더니ㅑ더냐ㅣㅓ디ㅑ머이ㅑㅓ냐ㅣ더니',NULL,NULL,NULL,'2024-05-21 02:48:42',NULL),('PRCHS_REV_38','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,'2024-05-21 03:35:14',NULL),('PRCHS_REV_39','id001','DISCLOSURE_001','RAT_CTGRY_04','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,'2024-05-21 03:37:39',NULL),('PRCHS_REV_40','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,'2024-05-21 03:38:37',NULL),('PRCHS_REV_41','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,'2024-05-21 03:39:18',NULL),('PRCHS_REV_42','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL,NULL,'2024-05-21 03:39:50',NULL),('PRCHS_REV_43','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,'2024-05-21 03:40:35',NULL),('PRCHS_REV_44','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL,NULL,'2024-05-21 03:40:42',NULL),('PRCHS_REV_45','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,'2024-05-21 03:41:44',NULL),('PRCHS_REV_46','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,'2024-05-21 03:41:45',NULL),('PRCHS_REV_47','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,'2024-05-21 03:44:55',NULL),('PRCHS_REV_48','id001','DISCLOSURE_001','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'111',NULL,NULL,NULL,'2024-05-21 03:45:01',NULL),('PRCHS_REV_49','id003','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,'2024-05-21 04:38:13',NULL),('PRCHS_REV_50','id003','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'1111111111',NULL,NULL,NULL,'2024-05-21 04:43:12',NULL),('PRCHS_REV_51','id003','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'111111111111',NULL,NULL,NULL,'2024-05-21 04:45:03',NULL),('PRCHS_REV_52','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'/??????????????????????????????????????????????????????????????????????????????????????????????????',NULL,NULL,NULL,'2024-05-21 06:27:46',NULL),('PRCHS_REV_53','id001','DISCLOSURE_001','RAT_CTGRY_03','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'솔직한 후기를 작성하기 어렵습니다.',NULL,NULL,NULL,'2024-05-21 07:12:06',NULL),('PRCHS_REV_54','id001','DISCLOSURE_001','RAT_CTGRY_02','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'솔직한 후기를 작성하기 어렵습니다.',NULL,NULL,NULL,'2024-05-21 07:14:48',NULL),('PRCHS_REV_55','id001','DISCLOSURE_001','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'솔직한 후기를 작성하기 어렵습니다.',NULL,NULL,NULL,'2024-05-21 07:15:41',NULL),('PRCHS_REV_56','id001','DISCLOSURE_001','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'솔직한 후기를 작성하기 어렵습니다.',NULL,NULL,NULL,'2024-05-21 07:15:53',NULL),('PRCHS_REV_57','id001','DISCLOSURE_001','RAT_CTGRY_04','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'오수다오수다오수다오수다오수다오수다오수다',NULL,NULL,NULL,'2024-05-21 07:42:37',NULL),('PRCHS_REV_58','id003','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'이제 이미지는 된다',NULL,NULL,NULL,'2024-05-22 00:44:49',NULL),('PRCHS_REV_59','id001','DISCLOSURE_001','RAT_CTGRY_01','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'최악이에요 최악이에요 최악이에요 최악이에요 최악이에요 ',NULL,NULL,NULL,'2024-05-22 02:12:32',NULL),('PRCHS_REV_60','yuhee','DISCLOSURE_001','RAT_CTGRY_04','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'짱구 귀여워 넘무 귀엽다 -\' \'-',NULL,NULL,NULL,'2024-05-22 03:16:59',NULL),('PRCHS_REV_61','yuhee','DISCLOSURE_001','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'좀 이미지가 이상한데?\r\n이미지를 여러개 올리고 취소해도 여러개 올라간 파일이 전부 올라간다..',NULL,NULL,NULL,'2024-05-22 03:18:08',NULL),('PRCHS_REV_62','id003','DISCLOSURE_001','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'나는 대체 무슨 븅신같은 짓을 한거지..?',NULL,NULL,NULL,'2024-05-23 06:23:08',NULL),('PRCHS_REV_63','id001','DISCLOSURE_001','RAT_CTGRY_05','RSTRN_002','RGN_SCTGRY_003',NULL,NULL,NULL,NULL,NULL,NULL,'ㅀㅇㅋㄶㄹㅋㄴㅇㅎㅍㅁㄴㅇㅎㅍㅋ',NULL,NULL,NULL,'2024-05-23 08:04:14',NULL);
/*!40000 ALTER TABLE `PURCHASE_REVIEW` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NOTICE_CATEGORY`
--

DROP TABLE IF EXISTS `NOTICE_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NOTICE_CATEGORY` (
                                   `NTC_CTGRY_NO` varchar(20) NOT NULL,
                                   `REG_PF_ID` varchar(20) NOT NULL,
                                   `NTC_CTGRY_TYPE` varchar(20) NOT NULL,
                                   `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                   `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                   `REG_YMD` timestamp NOT NULL,
                                   `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                   PRIMARY KEY (`NTC_CTGRY_NO`),
                                   KEY `REG_PF_ID` (`REG_PF_ID`),
                                   CONSTRAINT `FK_NOTICE_CATEGORY_MEMBER_MANAGE` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NOTICE_CATEGORY`
--

LOCK TABLES `NOTICE_CATEGORY` WRITE;
/*!40000 ALTER TABLE `NOTICE_CATEGORY` DISABLE KEYS */;
INSERT INTO `NOTICE_CATEGORY` VALUES ('NTC_CTGRY_1','admin001','일반 공지','admin001','Y','2023-12-31 15:00:00',NULL),('NTC_CTGRY_2','admin001','긴급 공지','admin001','Y','2023-12-31 15:00:00',NULL),('NTC_CTGRY_3','admin001','예정 공지','admin001','Y','2023-12-31 15:00:00',NULL),('NTC_CTGRY_4','admin001','이벤트 공지','admin001','Y','2023-12-31 15:00:00',NULL),('NTC_CTGRY_5','admin001','시스템 공지','admin001','Y','2023-12-31 15:00:00',NULL);
/*!40000 ALTER TABLE `NOTICE_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMMON_RESERVATION`
--

DROP TABLE IF EXISTS `COMMON_RESERVATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `COMMON_RESERVATION` (
                                      `BZENTY_RSVT_NO` varchar(50) NOT NULL,
                                      `MBR_ID` varchar(20) NOT NULL,
                                      `PRTNR_BPLC_CD` varchar(200) NOT NULL,
                                      `TRIP_LCTGRY_CD` varchar(20) NOT NULL,
                                      `TRIP_PLN_NO` varchar(20) NOT NULL,
                                      `MBR_NM` varchar(20) NOT NULL,
                                      `TRIP_LCTGRY_NM` varchar(50) NOT NULL,
                                      `BZMN_ GDS_NO` varchar(50) NOT NULL,
                                      `BZMN_ CONM` varchar(50) NOT NULL,
                                      `RSVT_YMD` date NOT NULL,
                                      `RSVT_STTS` varchar(20) NOT NULL,
                                      `RSVT_PERSON_TELNO` varchar(11) NOT NULL,
                                      `RSVT_PERSON_EML` varchar(50) NOT NULL,
                                      `RSVT_OPTION` varchar(50) NOT NULL,
                                      `RSVT_TNOPE` tinyint unsigned NOT NULL,
                                      `PLC_PRICE` int unsigned NOT NULL,
                                      `VISIT_TIME` datetime NOT NULL,
                                      `ATRZ_MTHD` varchar(10) NOT NULL,
                                      `TRIP_PLACE_OPTION_NUM` varchar(50) NOT NULL,
                                      `ATRZ_YN` char(1) DEFAULT NULL,
                                      `VISIT_YN` char(1) DEFAULT NULL,
                                      PRIMARY KEY (`BZENTY_RSVT_NO`),
                                      KEY `FK_MEMBER_MANAGE_TO_COMMON_RESERVATION_1` (`MBR_ID`),
                                      KEY `FK_PARTNER_BUSINESS_PLACE_TO_COMMON_RESERVATION_1` (`PRTNR_BPLC_CD`),
                                      KEY `FK_TRIP_LARGE_CATEGORY_TO_COMMON_RESERVATION_1` (`TRIP_LCTGRY_CD`),
                                      KEY `FK_INDIVIDUAL_TRIP_PLAN_TO_COMMON_RESERVATION_1` (`TRIP_PLN_NO`),
                                      CONSTRAINT `FK_INDIVIDUAL_TRIP_PLAN_TO_COMMON_RESERVATION_1` FOREIGN KEY (`TRIP_PLN_NO`) REFERENCES `INDIVIDUAL_TRIP_PLAN` (`TRIP_PLN_NO`),
                                      CONSTRAINT `FK_MEMBER_MANAGE_TO_COMMON_RESERVATION_1` FOREIGN KEY (`MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                      CONSTRAINT `FK_PARTNER_BUSINESS_PLACE_TO_COMMON_RESERVATION_1` FOREIGN KEY (`PRTNR_BPLC_CD`) REFERENCES `PARTNER_BUSINESS_PLACE` (`PRTNR_BPLC_CD`),
                                      CONSTRAINT `FK_TRIP_LARGE_CATEGORY_TO_COMMON_RESERVATION_1` FOREIGN KEY (`TRIP_LCTGRY_CD`) REFERENCES `TRIP_LARGE_CATEGORY` (`TRIP_LCTGRY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMMON_RESERVATION`
--

LOCK TABLES `COMMON_RESERVATION` WRITE;
/*!40000 ALTER TABLE `COMMON_RESERVATION` DISABLE KEYS */;
/*!40000 ALTER TABLE `COMMON_RESERVATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POST`
--

DROP TABLE IF EXISTS `POST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `POST` (
                        `PST_NO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                        `REG_MBR_ID` varchar(20) NOT NULL,
                        `PST_CTGRY_NO` varchar(20) NOT NULL,
                        `PST_TTL` varchar(50) NOT NULL,
                        `PST_CN` varchar(1000) NOT NULL,
                        `PST_INQ_CNT` int DEFAULT '0',
                        `PST_LIKE_TOTAL` int DEFAULT '0',
                        `PST_DISLIKE_TOTAL` int DEFAULT '0',
                        `PST_REPT_TOTAL` int DEFAULT '0',
                        `MDFCN_MBR_ID` varchar(20) DEFAULT NULL,
                        `DEL_MBR_ID` varchar(20) DEFAULT NULL,
                        `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                        `REG_YMD` timestamp NOT NULL,
                        `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                        `DEL_YMD` timestamp NULL DEFAULT NULL,
                        PRIMARY KEY (`PST_NO`),
                        KEY `FK_MM_P` (`REG_MBR_ID`),
                        KEY `FK_POST_POST_CATEGORY` (`PST_CTGRY_NO`),
                        CONSTRAINT `FK_MM_P` FOREIGN KEY (`REG_MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                        CONSTRAINT `FK_POST_POST_CATEGORY` FOREIGN KEY (`PST_CTGRY_NO`) REFERENCES `POST_CATEGORY` (`PST_CTGRY_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POST`
--

LOCK TABLES `POST` WRITE;
/*!40000 ALTER TABLE `POST` DISABLE KEYS */;
INSERT INTO `POST` VALUES ('PST_1','member001','PST_CTGRY_3','이번 주말 같이 남해 여행 가실분','1박 2일 동행 구합니다',62,10,10,0,'member001','member001','Y','2023-12-31 15:00:00',NULL,NULL),('PST_10','member001','PST_CTGRY_4','ㅎㅇ','방가',2,0,0,0,'','','Y','2024-05-17 08:20:26','2024-05-17 08:20:26','2024-05-17 08:20:26'),('PST_11','member002','PST_CTGRY_4','누르지마','누르는 사람 대머리',1,0,0,0,'','','Y','2024-05-17 08:21:10','2024-05-17 08:21:10','2024-05-17 08:21:10'),('PST_12','member002','PST_CTGRY_2','엥엥엥','?!',1,0,0,0,'','','Y','2024-05-17 08:21:43','2024-05-17 08:21:43','2024-05-17 08:21:43'),('PST_13','member002','PST_CTGRY_2','이거 눌러봐 !!!!!!!!!!!!!@@@@@@@@@@@@@','누르는 사람 대머리',26,0,0,0,'','','Y','2024-05-17 08:23:34','2024-05-17 08:23:34','2024-05-17 08:23:34'),('PST_14','id001','PST_CTGRY_3','게시글 제목','게시글 내용',8,0,0,0,NULL,NULL,'Y','2024-05-21 01:27:47','2024-05-23 22:01:27',NULL),('PST_15','id003','PST_CTGRY_3','ㅎㅇㅎㅇ','여행 갈 사람 구함 ~~ (1/10)',55,0,0,0,NULL,NULL,'Y','2024-05-21 02:27:19',NULL,NULL),('PST_16','id003','PST_CTGRY_5','안녕','ㅎㅇㄹ',1,0,0,0,NULL,NULL,'Y','2024-05-21 02:40:44',NULL,NULL),('PST_17','id003','PST_CTGRY_2','일본 여행 후기','궁금하면 500원',1,0,0,0,NULL,NULL,'Y','2024-05-21 02:42:29',NULL,NULL),('PST_18','id002','PST_CTGRY_4','배고파','점심 뭐먹지',11,0,0,0,NULL,NULL,'Y','2024-05-21 02:46:12',NULL,NULL),('PST_19','id002','PST_CTGRY_3','동포가자 여러분','안가는 사람 대머리',3,0,0,0,NULL,NULL,'Y','2024-05-21 03:09:23',NULL,NULL),('PST_2','member002','PST_CTGRY_4','(사진) 강남역에서 칼부림 일어남','조심하세요 다들',58,2,2,5,'member002','member002','Y','2024-02-13 15:00:00',NULL,NULL),('PST_21','yuhee','PST_CTGRY_4','나 가입함','안뇽하세요 오늘 가입했어요 잘부탁드려요',28,0,0,0,NULL,NULL,'Y','2024-05-22 03:03:53',NULL,NULL),('PST_22','id003','PST_CTGRY_4','ㅎ ㅏ 이','ㅃ2\r\n\r\n\r\nㅇㅇ\r\nㅇ\r\nㅇ\r\nㅇ\r\n\r\nㅇㅇㅇㅇ',72,0,0,0,NULL,NULL,'Y','2024-05-23 22:07:18','2024-05-28 01:08:27',NULL),('PST_23','id001','PST_CTGRY_1','ㅎㅎ','ㅎㅎ',1,0,0,0,NULL,NULL,'Y','2024-05-28 07:19:43',NULL,NULL),('PST_24','id001','PST_CTGRY_3','g','g\r<br>d\r<br>d\r<br>d\r<br>\r<br>d',6,0,0,0,NULL,NULL,'Y','2024-05-29 01:03:51',NULL,NULL),('PST_25','id001','PST_CTGRY_1','ㅇ','ㅇ',1,0,0,0,NULL,NULL,'Y','2024-05-29 01:35:50',NULL,NULL),('PST_3','member003','PST_CTGRY_1','양평 여행 가시는 분','구하우스 미술관 추천드려요',27,10,10,0,'member003','member003','Y','2024-03-08 15:00:00',NULL,NULL),('PST_4','member003','PST_CTGRY_1','양평 여행 가시는 분','구하우스 미술관 추천드려요',24,3,3,0,'member003','member003','Y','2024-03-14 15:00:00',NULL,NULL),('PST_5','member003','PST_CTGRY_1','양평 여행 가시는 분','구하우스 미술관 추천드려요',10,1,1,0,'member003','member003','Y','2024-04-01 15:00:00',NULL,NULL),('PST_6','member003','PST_CTGRY_1','양평 여행 가시는 분','구하우스 미술관 추천드려요',19,0,0,2,'member003','member003','Y','2024-04-30 15:00:00',NULL,NULL),('PST_7','member004','PST_CTGRY_5','전주 사시는분 있나요?','전주 현지인 맛집 추천 부탁드립니다',111,10,10,0,'member004','member004','Y','2024-05-01 15:00:00',NULL,NULL),('PST_8','member005','PST_CTGRY_4','내일부터 날씨 풀리네요','아싸',22,10,10,0,'member005','member005','Y','2024-05-02 15:00:00',NULL,NULL),('PST_9','member005','PST_CTGRY_4','아무말','아무말',2,0,0,0,'','','Y','2024-05-17 08:18:57','2024-05-17 08:18:57','2024-05-17 08:18:57');
/*!40000 ALTER TABLE `POST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POST_CATEGORY`
--

DROP TABLE IF EXISTS `POST_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `POST_CATEGORY` (
                                 `PST_CTGRY_NO` varchar(20) NOT NULL,
                                 `REG_PF_ID` varchar(20) NOT NULL,
                                 `PST_CTGRY_NM` varchar(20) DEFAULT NULL,
                                 `MDFCN_PF_ID` varchar(20) NOT NULL,
                                 `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                 `REG_YMD` timestamp NOT NULL,
                                 `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                 PRIMARY KEY (`PST_CTGRY_NO`),
                                 KEY `FK_MM_PC` (`REG_PF_ID`),
                                 CONSTRAINT `FK_MM_PC` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POST_CATEGORY`
--

LOCK TABLES `POST_CATEGORY` WRITE;
/*!40000 ALTER TABLE `POST_CATEGORY` DISABLE KEYS */;
INSERT INTO `POST_CATEGORY` VALUES ('PST_CTGRY_1','admin001','여행 정보','admin001','Y','2023-12-31 15:00:00',NULL),('PST_CTGRY_2','admin001','여행 후기','admin001','Y','2023-12-31 15:00:00',NULL),('PST_CTGRY_3','admin001','여행 친구 찾기','admin001','Y','2023-12-31 15:00:00',NULL),('PST_CTGRY_4','admin001','자유게시판','admin001','Y','2023-12-31 15:00:00',NULL),('PST_CTGRY_5','admin001','질문게시판','admin001','Y','2023-12-31 15:00:00',NULL);
/*!40000 ALTER TABLE `POST_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESTAURANT_INFO`
--

DROP TABLE IF EXISTS `RESTAURANT_INFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RESTAURANT_INFO` (
                                   `RSTRN_INFO_CD` varchar(200) NOT NULL,
                                   `REG_PF_BIZ_ID` varchar(20) NOT NULL,
                                   `TRIP_SCTGRY_CD` varchar(200) NOT NULL,
                                   `PRTNR_BPLC_CD` varchar(200) NOT NULL,
                                   `RGN_SCTGRY_NM` varchar(200) NOT NULL,
                                   `PRTNR_BRD_NM` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                   `RSTRN_NAME` varchar(200) NOT NULL,
                                   `RSTRN_ADDR` varchar(200) NOT NULL,
                                   `RSTRN_DADDR` varchar(200) NOT NULL,
                                   `RSTRN_CONTACT` varchar(11) NOT NULL,
                                   `RSTRN_SITE` varchar(200) DEFAULT NULL,
                                   `RSTRN_OTHERS` varchar(200) DEFAULT NULL,
                                   `FAVS_TOTAL` varchar(200) DEFAULT NULL,
                                   `MDFCN_PF_BIZ_ID` varchar(20) DEFAULT NULL,
                                   `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                   `REG_YMD` timestamp NOT NULL,
                                   `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                   PRIMARY KEY (`RSTRN_INFO_CD`),
                                   KEY `FK_MEMBER_MANAGE_TO_RESTAURANT_INFO_1` (`REG_PF_BIZ_ID`),
                                   KEY `FK_TRIP_SMALL_CATEGORY_TO_RESTAURANT_INFO_1` (`TRIP_SCTGRY_CD`),
                                   KEY `FK_PARTNER_BUSINESS_PLACE_TO_RESTAURANT_INFO_1` (`PRTNR_BPLC_CD`),
                                   CONSTRAINT `FK_MEMBER_MANAGE_TO_RESTAURANT_INFO_1` FOREIGN KEY (`REG_PF_BIZ_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                   CONSTRAINT `FK_PARTNER_BUSINESS_PLACE_TO_RESTAURANT_INFO_1` FOREIGN KEY (`PRTNR_BPLC_CD`) REFERENCES `PARTNER_BUSINESS_PLACE` (`PRTNR_BPLC_CD`),
                                   CONSTRAINT `FK_TRIP_SMALL_CATEGORY_TO_RESTAURANT_INFO_1` FOREIGN KEY (`TRIP_SCTGRY_CD`) REFERENCES `TRIP_SMALL_CATEGORY` (`TRIP_SCTGRY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESTAURANT_INFO`
--

LOCK TABLES `RESTAURANT_INFO` WRITE;
/*!40000 ALTER TABLE `RESTAURANT_INFO` DISABLE KEYS */;
INSERT INTO `RESTAURANT_INFO` VALUES ('rn_1','admin001','TRIP_SCTGRY_011','RSTRN_001','덕진동','','일품향','전북 전주시 완산구 전주객사3길 10','일품향','063-284-190','','','','','Y','2020-09-15 15:00:00','2024-03-13 15:00:00'),('rn_10','admin001','TRIP_SCTGRY_013','RSTRN_001','중앙동','','서양주택','전북 전주시 완산구 전주객사2길 40','서양주택','063-906-999','','','','','Y','2020-09-24 15:00:00','2024-03-13 15:00:00'),('rn_11','admin001','TRIP_SCTGRY_014','RSTRN_002','중앙동','','외할머니솜씨','전북 전주시 완산구 오목대길 81-8','외할머니솜씨','063-232-580','','','','','Y','2020-09-25 15:00:00','2024-03-13 15:00:00'),('rn_12','admin001','TRIP_SCTGRY_015','RSTRN_003','중앙동','','진미반점','전북 전주시완산구 전라감영3길 12-3','진미반점','063-284-421','','','','','Y','2020-09-26 15:00:00','2024-03-13 15:00:00'),('rn_2','admin001','TRIP_SCTGRY_012','RSTRN_002','덕진동','','스시아시타','전북 전주시 덕진구 송천중앙로','1층 스시아시타','0507-1354-5','','','','','Y','2020-09-16 15:00:00','2024-03-13 15:00:00'),('rn_3','admin001','TRIP_SCTGRY_013','RSTRN_003','효자1동','','상무초밥','전북 전주시 완산구 흥산로 276','1층 상무초밥','063-229-226','','','','','Y','2020-09-17 15:00:00','2024-03-13 15:00:00'),('rn_4','admin001','TRIP_SCTGRY_014','RSTRN_001','효자1동','','경성금돼지','전북 전주시 덕진구 동가재미 2길 1','경성금돼지','0507-1419-6','sitesite','마싯다','','','Y','2020-09-18 15:00:00','2024-05-29 02:12:25'),('rn_5','admin001','TRIP_SCTGRY_015','RSTRN_002','중앙동','','자매갈비전골','전북 전주시 완산구 기린대로 121','본관(자매갈비전골)','0507-1420-7','','','','','Y','2020-09-19 15:00:00','2024-03-13 15:00:00'),('rn_6','admin001','TRIP_SCTGRY_011','RSTRN_003','중앙동','','치히로','전북 전주시 완사나구 전주객사3길 73-56','치히로','070-4300-60','','','','','Y','2020-09-20 15:00:00','2024-03-13 15:00:00'),('rn_7','admin001','TRIP_SCTGRY_012','RSTRN_001','중앙동','','베테랑 칼국수','전북 전주시 완산구 경기전길 135','베테랑 칼국수','063-285-989','','','','','Y','2020-09-21 15:00:00','2024-03-13 15:00:00'),('rn_8','admin001','TRIP_SCTGRY_011','RSTRN_002','중앙동','','길거리야','전북 전주시 완산구 경기전길 124','1층(길거리야)','0507-1359-3','','','','','Y','2020-09-22 15:00:00','2024-03-13 15:00:00'),('rn_9','admin001','TRIP_SCTGRY_012','RSTRN_003','중앙동','','카페이르리','전북 전주시 완산구 은행로 69','교동(카페이르리)','0507-1363-1','','','','','Y','2020-09-23 15:00:00','2024-03-13 15:00:00');
/*!40000 ALTER TABLE `RESTAURANT_INFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sigungu`
--

DROP TABLE IF EXISTS `sigungu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sigungu` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `code` varchar(10) NOT NULL,
                           `name` varchar(50) NOT NULL,
                           `area_id` int NOT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `idx_sigungu_name_area_id` (`name`,`area_id`),
                           KEY `area_id` (`area_id`),
                           CONSTRAINT `sigungu_ibfk_1` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sigungu`
--

LOCK TABLES `sigungu` WRITE;
/*!40000 ALTER TABLE `sigungu` DISABLE KEYS */;
INSERT INTO `sigungu` VALUES (1,'1','남구',4),(2,'1','광산구',5),(3,'2','달서구',4),(4,'2','남구',5),(5,'3','달성군',4),(6,'1','대덕구',3),(7,'3','동구',5),(8,'4','동구',4),(9,'1','거제시',14),(10,'2','동구',3),(11,'4','북구',5),(12,'5','북구',4),(13,'2','거창군',14),(14,'3','서구',3),(15,'5','서구',5),(16,'6','서구',4),(17,'3','고성군',14),(18,'4','유성구',3),(19,'7','수성구',4),(20,'1','남제주군',17),(21,'4','김해시',14),(22,'5','중구',3),(23,'8','중구',4),(24,'2','북제주군',17),(25,'5','남해군',14),(26,'9','군위군',4),(27,'1','괴산군',11),(28,'6','마산시',14),(29,'3','서귀포시',17),(30,'1','공주시',12),(31,'7','밀양시',14),(32,'2','단양군',11),(33,'4','제주시',17),(34,'8','사천시',14),(35,'2','금산군',12),(36,'3','보은군',11),(37,'4','영동군',11),(38,'9','산청군',14),(39,'3','논산시',12),(40,'1','가평군',9),(41,'10','양산시',14),(42,'2','고양시',9),(43,'5','옥천군',11),(44,'4','당진시',12),(45,'12','의령군',14),(46,'3','과천시',9),(47,'6','음성군',11),(48,'13','진주시',14),(49,'5','보령시',12),(50,'4','광명시',9),(51,'7','제천시',11),(52,'14','진해시',14),(53,'6','부여군',12),(54,'8','진천군',11),(55,'5','광주시',9),(56,'15','창녕군',14),(57,'7','서산시',12),(58,'9','청원군',11),(59,'6','구리시',9),(60,'16','창원시',14),(61,'8','서천군',12),(62,'10','청주시',11),(63,'7','군포시',9),(64,'17','통영시',14),(65,'9','아산시',12),(66,'11','충주시',11),(67,'8','김포시',9),(68,'18','하동군',14),(69,'12','증평군',11),(70,'11','예산군',12),(71,'9','남양주시',9),(72,'19','함안군',14),(73,'12','천안시',12),(74,'10','동두천시',9),(75,'20','함양군',14),(76,'13','청양군',12),(77,'11','부천시',9),(78,'14','태안군',12),(79,'12','성남시',9),(80,'21','합천군',14),(81,'15','홍성군',12),(82,'13','수원시',9),(83,'1','강릉시',10),(84,'16','계룡시',12),(85,'14','시흥시',9),(86,'2','고성군',10),(87,'15','안산시',9),(88,'1','세종특별자치시',8),(89,'1','중구',7),(90,'3','동해시',10),(91,'16','안성시',9),(92,'1','강진군',16),(93,'2','남구',7),(94,'4','삼척시',10),(95,'17','안양시',9),(96,'2','고흥군',16),(97,'3','동구',7),(98,'5','속초시',10),(99,'18','양주시',9),(100,'3','곡성군',16),(101,'4','북구',7),(102,'6','양구군',10),(103,'19','양평군',9),(104,'4','광양시',16),(105,'5','울주군',7),(106,'7','양양군',10),(107,'20','여주시',9),(108,'5','구례군',16),(109,'8','영월군',10),(110,'21','연천군',9),(111,'6','나주시',16),(112,'1','고창군',15),(113,'9','원주시',10),(114,'22','오산시',9),(115,'7','담양군',16),(116,'2','군산시',15),(117,'23','용인시',9),(118,'10','인제군',10),(119,'8','목포시',16),(120,'3','김제시',15),(121,'24','의왕시',9),(122,'11','정선군',10),(123,'9','무안군',16),(124,'4','남원시',15),(125,'25','의정부시',9),(126,'12','철원군',10),(127,'10','보성군',16),(128,'5','무주군',15),(129,'26','이천시',9),(130,'13','춘천시',10),(131,'11','순천시',16),(132,'6','부안군',15),(133,'27','파주시',9),(134,'14','태백시',10),(135,'7','순창군',15),(136,'12','신안군',16),(137,'28','평택시',9),(138,'15','평창군',10),(139,'8','완주군',15),(140,'13','여수시',16),(141,'29','포천시',9),(142,'16','홍천군',10),(143,'9','익산시',15),(144,'16','영광군',16),(145,'30','하남시',9),(146,'17','화천군',10),(147,'10','임실군',15),(148,'17','영암군',16),(149,'31','화성시',9),(150,'18','횡성군',10),(151,'18','완도군',16),(152,'11','장수군',15),(153,'1','강남구',1),(154,'1','강서구',6),(155,'19','장성군',16),(156,'12','전주시',15),(157,'2','강동구',1),(158,'2','금정구',6),(159,'13','정읍시',15),(160,'20','장흥군',16),(161,'3','강북구',1),(162,'3','기장군',6),(163,'14','진안군',15),(164,'21','진도군',16),(165,'4','강서구',1),(166,'4','남구',6),(167,'22','함평군',16),(168,'5','관악구',1),(169,'23','해남군',16),(170,'5','동구',6),(171,'6','광진구',1),(172,'24','화순군',16),(173,'7','구로구',1),(174,'6','동래구',6),(175,'8','금천구',1),(176,'7','부산진구',6),(177,'9','노원구',1),(178,'8','북구',6),(179,'10','도봉구',1),(180,'9','사상구',6),(181,'11','동대문구',1),(182,'10','사하구',6),(183,'12','동작구',1),(184,'11','서구',6),(185,'13','마포구',1),(186,'12','수영구',6),(187,'14','서대문구',1),(188,'13','연제구',6),(189,'15','서초구',1),(190,'14','영도구',6),(191,'16','성동구',1),(192,'15','중구',6),(193,'17','성북구',1),(194,'16','해운대구',6),(195,'18','송파구',1),(196,'1','강화군',2),(197,'19','양천구',1),(198,'2','계양구',2),(199,'20','영등포구',1),(200,'3','미추홀구',2),(201,'21','용산구',1),(202,'4','남동구',2),(203,'22','은평구',1),(204,'5','동구',2),(205,'23','종로구',1),(206,'6','부평구',2),(207,'24','중구',1),(208,'7','서구',2),(209,'25','중랑구',1),(210,'8','연수구',2),(211,'1','경산시',13),(212,'9','옹진군',2),(213,'2','경주시',13),(214,'10','중구',2),(215,'3','고령군',13),(216,'4','구미시',13),(217,'6','김천시',13),(218,'7','문경시',13),(219,'8','봉화군',13),(220,'9','상주시',13),(221,'10','성주군',13),(222,'11','안동시',13),(223,'12','영덕군',13),(224,'13','영양군',13),(225,'14','영주시',13),(226,'15','영천시',13),(227,'16','예천군',13),(228,'17','울릉군',13),(229,'18','울진군',13),(230,'19','의성군',13),(231,'20','청도군',13),(232,'21','청송군',13),(233,'22','칠곡군',13),(234,'23','포항시',13);
/*!40000 ALTER TABLE `sigungu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EXPENSE_PER_DAY_PER_GROUP`
--

DROP TABLE IF EXISTS `EXPENSE_PER_DAY_PER_GROUP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EXPENSE_PER_DAY_PER_GROUP` (
                                             `PLN_EXP_NO` varchar(20) NOT NULL,
                                             `MBR_ID` varchar(20) NOT NULL,
                                             `PLN_PRM_NO` varchar(20) NOT NULL,
                                             `PTY_NO` varchar(20) NOT NULL,
                                             `INV_ID` varchar(20) DEFAULT NULL,
                                             `DLY_PLN_BGT_TOTAL` int NOT NULL,
                                             `PARTY_DLY_PLN_BGT` int NOT NULL,
                                             `REG_YMD` timestamp NOT NULL,
                                             PRIMARY KEY (`PLN_EXP_NO`),
                                             KEY `FK_MM_EPDPG` (`MBR_ID`),
                                             KEY `FK_TDP_EPDPG` (`PLN_PRM_NO`),
                                             KEY `FK_ITPPC_EPDPG` (`PTY_NO`),
                                             CONSTRAINT `FK_ITPPC_EPDPG` FOREIGN KEY (`PTY_NO`) REFERENCES `INDIVIDUAL_TRIP_PLAN_PARTICIPATION_CONFIRMATION` (`PTY_NO`),
                                             CONSTRAINT `FK_MM_EPDPG` FOREIGN KEY (`MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                             CONSTRAINT `FK_TDP_EPDPG` FOREIGN KEY (`PLN_PRM_NO`) REFERENCES `TRIP_DAILY_PLAN` (`PLN_PRM_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXPENSE_PER_DAY_PER_GROUP`
--

LOCK TABLES `EXPENSE_PER_DAY_PER_GROUP` WRITE;
/*!40000 ALTER TABLE `EXPENSE_PER_DAY_PER_GROUP` DISABLE KEYS */;
/*!40000 ALTER TABLE `EXPENSE_PER_DAY_PER_GROUP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AREA_CODE`
--

DROP TABLE IF EXISTS `AREA_CODE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AREA_CODE` (
                             `area_code` varchar(10) NOT NULL,
                             `area_name` varchar(50) NOT NULL,
                             `REG_YMD` timestamp NOT NULL,
                             `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                             PRIMARY KEY (`area_code`),
                             UNIQUE KEY `area_code` (`area_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AREA_CODE`
--

LOCK TABLES `AREA_CODE` WRITE;
/*!40000 ALTER TABLE `AREA_CODE` DISABLE KEYS */;
INSERT INTO `AREA_CODE` VALUES ('1','서울','2024-05-16 02:42:37','2024-05-26 14:34:17'),('2','인천','2024-05-16 02:42:37','2024-05-26 14:34:17'),('3','대전','2024-05-16 02:42:37','2024-05-26 14:34:17'),('31','경기도','2024-05-16 02:42:37','2024-05-26 14:34:17'),('32','강원특별자치도','2024-05-16 02:42:37','2024-05-26 14:34:17'),('33','충청북도','2024-05-16 02:42:37','2024-05-26 14:34:17'),('34','충청남도','2024-05-16 02:42:37','2024-05-26 14:34:17'),('35','경상북도','2024-05-16 02:42:37','2024-05-26 14:34:17'),('36','경상남도','2024-05-16 02:42:37','2024-05-26 14:34:17'),('37','전북특별자치도','2024-05-16 02:42:37','2024-05-26 14:34:17'),('38','전라남도','2024-05-16 02:42:37','2024-05-26 14:34:17'),('39','제주도','2024-05-16 02:42:37','2024-05-26 14:34:17'),('4','대구','2024-05-16 02:42:37','2024-05-26 14:34:17'),('5','광주','2024-05-16 02:42:37','2024-05-26 14:34:17'),('6','부산','2024-05-16 02:42:37','2024-05-26 14:34:17'),('7','울산','2024-05-16 02:42:37','2024-05-26 14:34:17'),('8','세종특별자치시','2024-05-16 02:42:37','2024-05-26 14:34:17');
/*!40000 ALTER TABLE `AREA_CODE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REVIEW_LIKE_DISLIKE`
--

DROP TABLE IF EXISTS `REVIEW_LIKE_DISLIKE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REVIEW_LIKE_DISLIKE` (
                                       `REV_LIKE_DISLIKE_CD` varchar(200) NOT NULL,
                                       `REG_MBR_ID` varchar(20) NOT NULL,
                                       `PRCHS_REV_CD` varchar(200) NOT NULL,
                                       `LIKE_DISLIKE_YN` enum('Y','N','C') NOT NULL,
                                       `REG_YMD` timestamp NOT NULL,
                                       `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                       PRIMARY KEY (`REV_LIKE_DISLIKE_CD`),
                                       KEY `FK_MEMBER_MANAGE_TO_REVIEW_LIKE_DISLIKE_1` (`REG_MBR_ID`),
                                       KEY `FK_PURCHASE_REVIEW_TO_REVIEW_LIKE_DISLIKE_1` (`PRCHS_REV_CD`),
                                       CONSTRAINT `FK_MEMBER_MANAGE_TO_REVIEW_LIKE_DISLIKE_1` FOREIGN KEY (`REG_MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                       CONSTRAINT `FK_PURCHASE_REVIEW_TO_REVIEW_LIKE_DISLIKE_1` FOREIGN KEY (`PRCHS_REV_CD`) REFERENCES `PURCHASE_REVIEW` (`PRCHS_REV_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVIEW_LIKE_DISLIKE`
--

LOCK TABLES `REVIEW_LIKE_DISLIKE` WRITE;
/*!40000 ALTER TABLE `REVIEW_LIKE_DISLIKE` DISABLE KEYS */;
INSERT INTO `REVIEW_LIKE_DISLIKE` VALUES ('RLD_1','member001','PRCHS_REV_001','C','2024-02-20 15:00:00','2023-04-10 15:00:00'),('RLD_10','member004','PRCHS_REV_010','N','2024-02-29 15:00:00',NULL),('RLD_2','member002','PRCHS_REV_002','Y','2024-02-21 15:00:00',NULL),('RLD_3','member003','PRCHS_REV_003','Y','2024-02-22 15:00:00',NULL),('RLD_4','member004','PRCHS_REV_004','Y','2024-02-23 15:00:00',NULL),('RLD_5','member001','PRCHS_REV_005','Y','2024-02-24 15:00:00',NULL),('RLD_6','member002','PRCHS_REV_006','Y','2024-02-25 15:00:00',NULL),('RLD_7','member003','PRCHS_REV_007','N','2024-02-26 15:00:00',NULL),('RLD_8','member004','PRCHS_REV_008','N','2024-02-27 15:00:00',NULL),('RLD_9','member003','PRCHS_REV_009','N','2024-02-28 15:00:00',NULL);
/*!40000 ALTER TABLE `REVIEW_LIKE_DISLIKE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_document_history`
--

DROP TABLE IF EXISTS `tb_document_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_document_history` (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `file_name` varchar(255) NOT NULL,
                                       `file_url` varchar(512) NOT NULL,
                                       `file_hash` varchar(128) NOT NULL,
                                       `status` varchar(50) NOT NULL,
                                       `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                       `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_document_history`
--

LOCK TABLES `tb_document_history` WRITE;
/*!40000 ALTER TABLE `tb_document_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_document_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REGION_SMALL_CATEGORY`
--

DROP TABLE IF EXISTS `REGION_SMALL_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REGION_SMALL_CATEGORY` (
                                         `RGN_SCTGRY_CD` varchar(200) NOT NULL,
                                         `REG_PF_ID` varchar(20) NOT NULL,
                                         `RGN_MCTGRY_CD` varchar(200) NOT NULL,
                                         `RGN_LCTGRY_CD` varchar(200) NOT NULL,
                                         `RGN_SCTGRY_NM` varchar(200) NOT NULL,
                                         `RGN_MCTGRY_NM` varchar(200) NOT NULL,
                                         `RGN_LCTGRY_NM` varchar(200) NOT NULL,
                                         `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                         `ACT_YN` char(1) NOT NULL DEFAULT 'y',
                                         `REG_YMD` timestamp NOT NULL,
                                         `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                         PRIMARY KEY (`RGN_SCTGRY_CD`),
                                         KEY `FK_MEMBER_MANAGE_TO_REGION_SMALL_CATEGORY_1` (`REG_PF_ID`),
                                         KEY `FK_REGION_MEDIUM_CATEGORY_TO_REGION_SMALL_CATEGORY_1` (`RGN_MCTGRY_CD`),
                                         KEY `FK_REGION_LARGE_CATEGORY_TO_REGION_SMALL_CATEGORY_1` (`RGN_LCTGRY_CD`),
                                         CONSTRAINT `FK_MEMBER_MANAGE_TO_REGION_SMALL_CATEGORY_1` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                         CONSTRAINT `FK_REGION_LARGE_CATEGORY_TO_REGION_SMALL_CATEGORY_1` FOREIGN KEY (`RGN_LCTGRY_CD`) REFERENCES `REGION_LARGE_CATEGORY` (`RGN_LCTGRY_CD`),
                                         CONSTRAINT `FK_REGION_MEDIUM_CATEGORY_TO_REGION_SMALL_CATEGORY_1` FOREIGN KEY (`RGN_MCTGRY_CD`) REFERENCES `REGION_MEDIUM_CATEGORY` (`RGN_MCTGRY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REGION_SMALL_CATEGORY`
--

LOCK TABLES `REGION_SMALL_CATEGORY` WRITE;
/*!40000 ALTER TABLE `REGION_SMALL_CATEGORY` DISABLE KEYS */;
INSERT INTO `REGION_SMALL_CATEGORY` VALUES ('RGN_SCTGRY_001','admin001','RGN_MCTGRY_001','RGN_LCTGRY_012','중앙동','전주시 완산구','전북특별자치도','','Y','2019-12-31 15:00:00',NULL),('RGN_SCTGRY_0010','admin001','RGN_MCTGRY_002','RGN_LCTGRY_012','팔복동','전주시 덕진구','전북특별자치도','','Y','2019-12-31 15:00:00',NULL),('RGN_SCTGRY_0011','admin001','RGN_MCTGRY_002','RGN_LCTGRY_012','호성동','전주시 덕진구','전북특별자치도','','Y','2019-12-31 15:00:00',NULL),('RGN_SCTGRY_0012','admin001','RGN_MCTGRY_002','RGN_LCTGRY_012','송천1동','전주시 덕진구','전북특별자치도','','Y','2019-12-31 15:00:00',NULL),('RGN_SCTGRY_0013','admin001','RGN_MCTGRY_002','RGN_LCTGRY_012','혁신동','전주시 덕진구','전북특별자치도','','Y','2019-12-31 15:00:00',NULL),('RGN_SCTGRY_0014','admin001','RGN_MCTGRY_004','RGN_LCTGRY_012','어양동','익산시','전북특별자치도','','Y','2019-12-31 15:00:00',NULL),('RGN_SCTGRY_002','admin001','RGN_MCTGRY_001','RGN_LCTGRY_012','중화산2동','전주시 완산구','전북특별자치도','','Y','2020-01-01 15:00:00',NULL),('RGN_SCTGRY_003','admin001','RGN_MCTGRY_001','RGN_LCTGRY_012','서신동','전주시 완산구','전북특별자치도','','Y','2019-12-31 15:00:00',NULL),('RGN_SCTGRY_004','admin001','RGN_MCTGRY_001','RGN_LCTGRY_012','평화1동','전주시 완산구','전북특별자치도','','Y','2019-12-31 15:00:00',NULL),('RGN_SCTGRY_005','admin001','RGN_MCTGRY_001','RGN_LCTGRY_012','효자1동','전주시 완산구','전북특별자치도','','Y','2019-12-31 15:00:00',NULL),('RGN_SCTGRY_006','admin001','RGN_MCTGRY_001','RGN_LCTGRY_012','효자4동','전주시 완산구','전북특별자치도','','Y','2019-12-31 15:00:00',NULL),('RGN_SCTGRY_007','admin001','RGN_MCTGRY_002','RGN_LCTGRY_012','진북동','전주시 덕진구','전북특별자치도','','Y','2019-12-31 15:00:00',NULL),('RGN_SCTGRY_008','admin001','RGN_MCTGRY_002','RGN_LCTGRY_012','덕진동','전주시 덕진구','전북특별자치도','','Y','2019-12-31 15:00:00',NULL),('RGN_SCTGRY_009','admin001','RGN_MCTGRY_002','RGN_LCTGRY_012','금암2동','전주시 덕진구','전북특별자치도','','Y','2019-12-31 15:00:00',NULL);
/*!40000 ALTER TABLE `REGION_SMALL_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QNA_LOG`
--

DROP TABLE IF EXISTS `QNA_LOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QNA_LOG` (
                           `QNA_LOG_NO` varchar(20) NOT NULL,
                           `QNA_PRGRS_NO` varchar(20) NOT NULL,
                           `QNA_ANS_NO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `QNA_PRGRS_CTGRY` varchar(20) NOT NULL,
                           `ALL_ID` varchar(20) NOT NULL,
                           `QNA_LOG_YMD` timestamp NOT NULL,
                           PRIMARY KEY (`QNA_LOG_NO`),
                           KEY `FK_QP_QL` (`QNA_PRGRS_NO`),
                           CONSTRAINT `FK_QP_QL` FOREIGN KEY (`QNA_PRGRS_NO`) REFERENCES `QNA_PROGRESS` (`QNA_PRGRS_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QNA_LOG`
--

LOCK TABLES `QNA_LOG` WRITE;
/*!40000 ALTER TABLE `QNA_LOG` DISABLE KEYS */;
/*!40000 ALTER TABLE `QNA_LOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NOTICE_FILE`
--

DROP TABLE IF EXISTS `NOTICE_FILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NOTICE_FILE` (
                               `NTC_FILE_NO` varchar(20) NOT NULL,
                               `REG_PF_ID` varchar(20) NOT NULL,
                               `NTC_NO` varchar(20) NOT NULL,
                               `NTC_FILE_NM` varchar(30) NOT NULL,
                               `NTC_FILE_PATH_NM` varchar(50) NOT NULL,
                               `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                               `REG_YMD` timestamp NOT NULL,
                               `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                               PRIMARY KEY (`NTC_FILE_NO`),
                               KEY `NTC_NO` (`NTC_NO`),
                               KEY `FK_NOTICE_FILE_MEMBER_MANAGE` (`REG_PF_ID`),
                               CONSTRAINT `FK_NOTICE_FILE_MEMBER_MANAGE` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                               CONSTRAINT `FK_NOTICE_FILE_NOTICE` FOREIGN KEY (`NTC_NO`) REFERENCES `NOTICE` (`NTC_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NOTICE_FILE`
--

LOCK TABLES `NOTICE_FILE` WRITE;
/*!40000 ALTER TABLE `NOTICE_FILE` DISABLE KEYS */;
INSERT INTO `NOTICE_FILE` VALUES ('NTC_ FILE_1','admin001','NTC_1','a.pdf','null','admin001','2023-12-31 15:00:00',NULL),('NTC_ FILE_2','admin001','NTC_2','a.jpeg','null','admin001','2023-12-31 15:00:00',NULL),('NTC_ FILE_3','admin001','NTC_3','a.png','null','admin001','2023-12-31 15:00:00',NULL),('NTC_ FILE_4','admin001','NTC_4','a.pdf','null','admin001','2023-12-31 15:00:00',NULL),('NTC_ FILE_5','admin001','NTC_5','a.png','null','admin001','2023-12-31 15:00:00',NULL);
/*!40000 ALTER TABLE `NOTICE_FILE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REVIEW_REPORT_HISTORY`
--

DROP TABLE IF EXISTS `REVIEW_REPORT_HISTORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REVIEW_REPORT_HISTORY` (
                                         `REV_REPT_NO` varchar(200) NOT NULL,
                                         `REG_MBR_ID` varchar(20) NOT NULL,
                                         `REPT_CTGRY_NO` varchar(20) NOT NULL,
                                         `PRCHS_REV_CD` varchar(200) NOT NULL,
                                         `APRV_PF_ID` varchar(20) DEFAULT NULL,
                                         `REPT_YMD` timestamp NOT NULL,
                                         `REV_REPT_APRV` char(1) NOT NULL DEFAULT 'N',
                                         `APRV_YMD` timestamp NULL DEFAULT NULL,
                                         `REV_REPT_CN` varchar(200) NOT NULL,
                                         PRIMARY KEY (`REV_REPT_NO`),
                                         KEY `FK_MEMBER_MANAGE_TO_REVIEW_REPORT_HISTORY_1` (`REG_MBR_ID`),
                                         KEY `FK_PURCHASE_REVIEW_TO_REVIEW_REPORT_HISTORY_1` (`PRCHS_REV_CD`),
                                         KEY `FK_REVIEW_REPORT_HISTORY_REPORT_CATEGORY` (`REPT_CTGRY_NO`),
                                         CONSTRAINT `FK_MEMBER_MANAGE_TO_REVIEW_REPORT_HISTORY_1` FOREIGN KEY (`REG_MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                         CONSTRAINT `FK_PURCHASE_REVIEW_TO_REVIEW_REPORT_HISTORY_1` FOREIGN KEY (`PRCHS_REV_CD`) REFERENCES `PURCHASE_REVIEW` (`PRCHS_REV_CD`),
                                         CONSTRAINT `FK_REVIEW_REPORT_HISTORY_REPORT_CATEGORY` FOREIGN KEY (`REPT_CTGRY_NO`) REFERENCES `REPORT_CATEGORY` (`REPT_CTGRY_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVIEW_REPORT_HISTORY`
--

LOCK TABLES `REVIEW_REPORT_HISTORY` WRITE;
/*!40000 ALTER TABLE `REVIEW_REPORT_HISTORY` DISABLE KEYS */;
INSERT INTO `REVIEW_REPORT_HISTORY` VALUES ('RTN_1','member001','REPT_CTGRY_2','PRCHS_REV_001','id001','2024-02-20 15:00:00','Y','2024-05-23 08:09:42','광고같아요'),('RTN_10','id003','REPT_CTGRY_1','PRCHS_REV_62','id001','2024-05-23 06:56:01','Y','2024-05-23 08:09:33','욕설이 있어요'),('RTN_11','id003','REPT_CTGRY_2','PRCHS_REV_006','id001','2024-05-23 07:13:08','Y','2024-05-23 08:09:49','ㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗ'),('RTN_12','id003','REPT_CTGRY_1','PRCHS_REV_62','id001','2024-05-23 07:19:55','Y','2024-05-23 08:09:56','욕설이 있어요'),('RTN_13','id003','REPT_CTGRY_5','PRCHS_REV_006',NULL,'2024-05-24 00:45:25','N',NULL,'ㅀㅁㄴㄹㅇㅇㅎㅁㅇㄻ'),('RTN_2','member002','REPT_CTGRY_2','PRCHS_REV_002','admin001','2024-02-21 15:00:00','Y','2024-02-21 15:00:00','광고같아요'),('RTN_3','member003','REPT_CTGRY_1','PRCHS_REV_002','admin001','2024-02-22 15:00:00','Y','2024-02-22 15:00:00','불쾌한 리뷰에요'),('RTN_4','member004','REPT_CTGRY_2','PRCHS_REV_004','id001','2024-02-23 15:00:00','Y','2024-05-23 08:10:09','광고같아요'),('RTN_5','member001','REPT_CTGRY_2','PRCHS_REV_005','admin001','2024-02-24 15:00:00','Y',NULL,'광고같아요'),('RTN_6','member002','REPT_CTGRY_1','PRCHS_REV_003','admin001','2024-02-25 15:00:00','Y','2024-02-25 15:00:00','불쾌한 리뷰에요'),('RTN_7','member003','REPT_CTGRY_2','PRCHS_REV_007','id001','2024-02-26 15:00:00','Y','2024-05-23 08:10:20','광고같아요'),('RTN_8','member004','REPT_CTGRY_1','PRCHS_REV_003','admin001','2024-02-27 15:00:00','Y','2024-02-27 15:00:00','불쾌한 리뷰에요'),('RTN_9','member003','REPT_CTGRY_2','PRCHS_REV_004','admin001','2024-02-28 15:00:00','N',NULL,'광고같아요');
/*!40000 ALTER TABLE `REVIEW_REPORT_HISTORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MEMBER_TRIP_PLAN_RANKING`
--

DROP TABLE IF EXISTS `MEMBER_TRIP_PLAN_RANKING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MEMBER_TRIP_PLAN_RANKING` (
                                            `MBR_TRIP_PLN` varchar(20) NOT NULL,
                                            `REG_PF_ID` varchar(20) NOT NULL,
                                            `TRIP_PLN_NO` varchar(20) NOT NULL,
                                            `RGN_SCTGRY_NM` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                            `TRIP_PLN_RANK` tinyint NOT NULL,
                                            `REV_STATS_STRT_YMD` timestamp NOT NULL,
                                            `REV_STATS_END_YMD` timestamp NOT NULL,
                                            `PF_PSTG_STRT_YMD` timestamp NOT NULL,
                                            `PF_PSTG_END_YMD` timestamp NOT NULL,
                                            `MDFCN_PF_ID` varchar(20) DEFAULT NULL,
                                            `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                            `REG_YMD` timestamp NOT NULL,
                                            `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                            PRIMARY KEY (`MBR_TRIP_PLN`),
                                            KEY `FK_MEMBER_MANAGE_TO_MEMBER_TRIP_PLAN_RANKING_1` (`REG_PF_ID`),
                                            KEY `FK_INDIVIDUAL_TRIP_PLAN_TO_MEMBER_TRIP_PLAN_RANKING_1` (`TRIP_PLN_NO`),
                                            CONSTRAINT `FK_INDIVIDUAL_TRIP_PLAN_TO_MEMBER_TRIP_PLAN_RANKING_1` FOREIGN KEY (`TRIP_PLN_NO`) REFERENCES `INDIVIDUAL_TRIP_PLAN` (`TRIP_PLN_NO`),
                                            CONSTRAINT `FK_MEMBER_MANAGE_TO_MEMBER_TRIP_PLAN_RANKING_1` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEMBER_TRIP_PLAN_RANKING`
--

LOCK TABLES `MEMBER_TRIP_PLAN_RANKING` WRITE;
/*!40000 ALTER TABLE `MEMBER_TRIP_PLAN_RANKING` DISABLE KEYS */;
INSERT INTO `MEMBER_TRIP_PLAN_RANKING` VALUES ('mtr_1','admin001','ind_trip_1','중앙동',1,'2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2020-09-02 15:00:00',NULL),('mtr_2','admin001','ind_trip_2','중화산동',2,'2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2020-09-03 15:00:00',NULL),('mtr_3','admin001','ind_trip_3','중앙동',3,'2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2024-05-17 07:02:30',NULL),('mtr_4','admin001','ind_trip_1','중앙동',4,'2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2024-05-29 01:36:10',NULL),('mtr_5','admin001','ind_trip_3','중앙동동',5,'2024-02-29 15:00:00','2024-03-30 15:00:00','2024-03-31 15:00:00','2024-04-30 15:00:00',NULL,'Y','2024-05-29 01:37:06',NULL);
/*!40000 ALTER TABLE `MEMBER_TRIP_PLAN_RANKING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DISCLOSURE_STATUS`
--

DROP TABLE IF EXISTS `DISCLOSURE_STATUS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DISCLOSURE_STATUS` (
                                     `RLS_YN_CD` varchar(50) NOT NULL,
                                     `REG_PF_ID` varchar(20) NOT NULL,
                                     `RLS_CN` varchar(200) NOT NULL,
                                     `ACT_YN` char(1) NOT NULL DEFAULT 'Y',
                                     `REG_YMD` timestamp NOT NULL,
                                     `MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                     PRIMARY KEY (`RLS_YN_CD`),
                                     KEY `FK_MEMBER_MANAGE_TO_DISCLOSURE_STATUS_1` (`REG_PF_ID`),
                                     CONSTRAINT `FK_MEMBER_MANAGE_TO_DISCLOSURE_STATUS_1` FOREIGN KEY (`REG_PF_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DISCLOSURE_STATUS`
--

LOCK TABLES `DISCLOSURE_STATUS` WRITE;
/*!40000 ALTER TABLE `DISCLOSURE_STATUS` DISABLE KEYS */;
INSERT INTO `DISCLOSURE_STATUS` VALUES ('DISCLOSURE_001','admin001','전체 공개','Y','2024-04-30 02:24:25',NULL),('DISCLOSURE_002','admin001','나만 공개','Y','2024-04-30 02:24:25',NULL);
/*!40000 ALTER TABLE `DISCLOSURE_STATUS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TOTAL_EXPENSES_PER_MEMBER`
--

DROP TABLE IF EXISTS `TOTAL_EXPENSES_PER_MEMBER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TOTAL_EXPENSES_PER_MEMBER` (
                                             `EXP_TOTAL_NO` varchar(20) NOT NULL,
                                             `MBR_ID` varchar(20) NOT NULL,
                                             `TRIP_PLN_NO` varchar(20) NOT NULL,
                                             `INV_ID` varchar(20) NOT NULL,
                                             `PARTY_PLN_BGT_TOTAL` int NOT NULL,
                                             `PARTY_DLY_PLN_BGT` int NOT NULL,
                                             `REG_YMD` timestamp NOT NULL,
                                             PRIMARY KEY (`EXP_TOTAL_NO`),
                                             KEY `FK_MM_TEPM` (`MBR_ID`),
                                             KEY `FK_ITP_TEPM` (`TRIP_PLN_NO`),
                                             CONSTRAINT `FK_ITP_TEPM` FOREIGN KEY (`TRIP_PLN_NO`) REFERENCES `INDIVIDUAL_TRIP_PLAN` (`TRIP_PLN_NO`),
                                             CONSTRAINT `FK_MM_TEPM` FOREIGN KEY (`MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TOTAL_EXPENSES_PER_MEMBER`
--

LOCK TABLES `TOTAL_EXPENSES_PER_MEMBER` WRITE;
/*!40000 ALTER TABLE `TOTAL_EXPENSES_PER_MEMBER` DISABLE KEYS */;
/*!40000 ALTER TABLE `TOTAL_EXPENSES_PER_MEMBER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MEMBER_MANAGE`
--

DROP TABLE IF EXISTS `MEMBER_MANAGE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MEMBER_MANAGE` (
                                 `MBR_ID` varchar(20) NOT NULL,
                                 `MBR_GRD_NO` varchar(20) NOT NULL,
                                 `MBR_PW` varchar(20) NOT NULL,
                                 `MBR_NM` varchar(20) NOT NULL,
                                 `MBR_NICKNAME` varchar(20) NOT NULL,
                                 `MBR_EML_ADDR` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                 `MBR_TELNO` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                 `WHDWL_CTGRY_NO` varchar(50) DEFAULT NULL,
                                 `MBR_WHDWL_RSN` varchar(2000) DEFAULT NULL,
                                 `MBR_REG_YMD` timestamp NOT NULL,
                                 `MBR_MDFCN_YMD` timestamp NULL DEFAULT NULL,
                                 `MBR_WHDWL_YMD` timestamp NULL DEFAULT NULL,
                                 PRIMARY KEY (`MBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEMBER_MANAGE`
--

LOCK TABLES `MEMBER_MANAGE` WRITE;
/*!40000 ALTER TABLE `MEMBER_MANAGE` DISABLE KEYS */;
INSERT INTO `MEMBER_MANAGE` VALUES ('admin001','uln_001','pw001','일기자','일닉네임','email01@gmail.com','010-1234-1234',NULL,NULL,'2022-12-31 15:00:00','2024-05-09 07:38:01',NULL),('business001','uln_101','pw008','팔구누','팔닉네임','email08@gmail.com','010-1111-11','WHDWL_CTGRY_004','개인정보 털릴것같음','2023-03-29 15:00:00',NULL,'2024-05-03 00:13:35'),('business002','uln_101','pw009','구구구','구닉네임','email09@gmail.com','010-1111-11','WHDWL_CTGRY_005','사용안함','2023-04-03 15:00:00',NULL,'2024-05-03 00:13:35'),('business003','uln_101','pw010','십십십','십닉네임','email10@gmail.com','010-1111-11','WHDWL_CTGRY_006','잘안쓰게되네요','2023-04-18 15:00:00',NULL,'2024-05-03 00:13:35'),('business004','uln_101','pw011','십일번','십일닉네임','email11@gmail.com','010-1111-11','WHDWL_CTGRY_006','별로인듯?','2023-04-29 15:00:00',NULL,'2024-05-03 00:13:35'),('business005','uln_101','pw012','십이번','호텔사장','email12@gmail.com','010-1111-11',NULL,NULL,'2023-04-30 15:00:00',NULL,NULL),('business006','uln_101','pw013','십삼번','모텔사장','email13@gmail.com','010-1111-11',NULL,NULL,'2023-05-19 15:00:00',NULL,NULL),('business007','uln_101','pw014','십사번','맛집사장','email14@gmail.com','010-1111-11',NULL,NULL,'2023-05-29 15:00:00',NULL,NULL),('business008','uln_101','pw015','십오번','안맛집사장','email15@gmail.com','010-1111-11',NULL,NULL,'2023-06-05 15:00:00',NULL,NULL),('business009','uln_101','pw016','십육번','전주시청','email16@gmail.com','010-1111-11',NULL,NULL,'2023-06-20 15:00:00',NULL,NULL),('business010','uln_101','pw016','십칠번','십육닉네임','email17@gmail.com','010-1111-11',NULL,NULL,'2023-06-20 15:00:00',NULL,NULL),('id001','uln_001','pw001','관리자','관리자','administrator@ksmart.com','010-1234-1234',NULL,NULL,'2024-05-02 04:54:04',NULL,NULL),('id002','uln_101','pw002','사업자','사업자','seller@ksmart.org','010-1234-1235',NULL,NULL,'2024-05-02 04:58:28',NULL,NULL),('id003','uln_301','pw003','사용자','사용자','user@ksmart.org','010-1234-1236',NULL,NULL,'2024-05-02 04:59:14','2024-05-17 01:48:22',NULL),('member001','uln_301','pw002','이아내','이닉네임','email02@gmail.com','010-1111-111',NULL,NULL,'2023-01-14 15:00:00','2024-05-23 02:10:45',NULL),('member002','uln_301','pw003','삼남편','삼닉네임','email03@gmail.com','010-1111-11',NULL,NULL,'2023-02-21 15:00:00',NULL,NULL),('member003','uln_301','pw004','사형수','사닉네임','email04@gmail.com','010-1111-11',NULL,NULL,'2023-02-15 15:00:00',NULL,NULL),('member004','uln_301','pw005','오남일','오닉네임','email05@gmail.com','010-1111-11','WHDWL_CTGRY_001','넘복잡함','2023-02-27 15:00:00',NULL,'2024-05-03 00:13:35'),('member005','uln_301','pw006','육기니','육닉네임','email06@gmail.com','010-1111-11','WHDWL_CTGRY_002','에러 계속뜸','2023-03-02 15:00:00',NULL,'2024-05-03 00:13:35'),('member006','uln_301','pw007','칠가나','칠닉네임','email07@gmail.com','010-1111-11','WHDWL_CTGRY_002','에러 계속뜸','2023-03-17 15:00:00',NULL,'2024-05-03 00:13:35'),('test','uln_001','test','test1','test1','khm3943@naver.com','test1','test','test','2024-05-07 06:57:41','2024-05-17 03:23:49','2024-05-07 06:57:41'),('test1','uln_001','test1','test1','test1','test1@naver.com','test1',NULL,NULL,'2024-05-08 04:57:09',NULL,NULL),('test2','uln_301','test2','test2','test2','test2@naver.com','test2',NULL,NULL,'2024-05-17 03:26:24','2024-05-17 03:26:59',NULL),('test6','uln_101','test6','test6','test6','test6@naver.com','test6',NULL,NULL,'2024-05-13 06:05:56',NULL,NULL),('yuhee','uln_301','yuhee','yuhee','yuhee','yuhee-@naver.com','010-0000-0000',NULL,NULL,'2024-05-22 02:59:07','2024-05-22 03:04:42',NULL);
/*!40000 ALTER TABLE `MEMBER_MANAGE` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `measurement_results`
--

DROP TABLE IF EXISTS `measurement_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `measurement_results` (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `area_nm` varchar(50) NOT NULL,
                                       `fact_manage_nm` varchar(100) NOT NULL,
                                       `stack_code` varchar(20) NOT NULL,
                                       `mesure_dt` datetime NOT NULL,
                                       `tsp_exhst_perm_stdr_value` decimal(10,2) DEFAULT NULL,
                                       `tsp_mesure_value` decimal(10,2) DEFAULT NULL,
                                       `sox_exhst_perm_stdr_value` decimal(10,2) DEFAULT NULL,
                                       `sox_mesure_value` decimal(10,2) DEFAULT NULL,
                                       `nox_exhst_perm_stdr_value` decimal(10,2) DEFAULT NULL,
                                       `nox_mesure_value` decimal(10,2) DEFAULT NULL,
                                       `hcl_exhst_perm_stdr_value` decimal(10,2) DEFAULT NULL,
                                       `hcl_mesure_value` decimal(10,2) DEFAULT NULL,
                                       `hf_exhst_perm_stdr_value` decimal(10,2) DEFAULT NULL,
                                       `hf_mesure_value` decimal(10,2) DEFAULT NULL,
                                       `nh3_exhst_perm_stdr_value` decimal(10,2) DEFAULT NULL,
                                       `nh3_mesure_value` decimal(10,2) DEFAULT NULL,
                                       `co_exhst_perm_stdr_value` decimal(10,2) DEFAULT NULL,
                                       `co_mesure_value` decimal(10,2) DEFAULT NULL,
                                       `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                       `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `unique_measurement` (`area_nm`,`fact_manage_nm`,`stack_code`,`mesure_dt`)
) ENGINE=InnoDB AUTO_INCREMENT=44180 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `REVIEW_IMAGE`
--

DROP TABLE IF EXISTS `REVIEW_IMAGE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REVIEW_IMAGE` (
                                `PRCH_REV_FILE_CD` varchar(200) NOT NULL,
                                `REG_MBR_ID` varchar(20) NOT NULL,
                                `PRCHS_REV_CD` varchar(200) NOT NULL,
                                `IMG_NM` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                `IMG_PATH_NM` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                `REG_YMD` timestamp NULL DEFAULT NULL,
                                `NEW_IMG_NM` varchar(200) DEFAULT NULL,
                                `FILE_SIZE` int DEFAULT NULL,
                                PRIMARY KEY (`PRCH_REV_FILE_CD`),
                                KEY `FK_MEMBER_MANAGE_TO_REVIEW_IMAGE_1` (`REG_MBR_ID`),
                                KEY `FK_PURCHASE_REVIEW_TO_REVIEW_IMAGE_1` (`PRCHS_REV_CD`),
                                CONSTRAINT `FK_MEMBER_MANAGE_TO_REVIEW_IMAGE_1` FOREIGN KEY (`REG_MBR_ID`) REFERENCES `MEMBER_MANAGE` (`MBR_ID`),
                                CONSTRAINT `FK_PURCHASE_REVIEW_TO_REVIEW_IMAGE_1` FOREIGN KEY (`PRCHS_REV_CD`) REFERENCES `PURCHASE_REVIEW` (`PRCHS_REV_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVIEW_IMAGE`
--

LOCK TABLES `REVIEW_IMAGE` WRITE;
/*!40000 ALTER TABLE `REVIEW_IMAGE` DISABLE KEYS */;
INSERT INTO `REVIEW_IMAGE` VALUES ('PRCH_REV_FILE_001','member001','PRCHS_REV_001','관광이미지.jpeg','null','2023-03-31 15:00:00',NULL,0),('PRCH_REV_FILE_002','member001','PRCHS_REV_002','음식이미지.png','null','2023-05-10 15:00:00',NULL,0),('PRCH_REV_FILE_003','member002','PRCHS_REV_003','관광이미지.png','null','2023-07-21 15:00:00',NULL,0),('PRCH_REV_FILE_004','member002','PRCHS_REV_004','숙소이미지.png','null','2023-09-02 15:00:00',NULL,0),('PRCH_REV_FILE_005','member002','PRCHS_REV_005','음식이미지.jpeg','null','2023-12-31 15:00:00',NULL,0),('PRCH_REV_FILE_006','member004','PRCHS_REV_006','음식이미지.png','null','2024-02-10 15:00:00',NULL,0),('PRCH_REV_FILE_202405211927211284896800','id001','PRCHS_REV_53','오수3.jpg','resources\\images\\20240521\\오수31927211275968400.jpg','2024-05-21 07:12:06','오수31927211275968400.jpg',107112),('PRCH_REV_FILE_202405211927438408697300','id001','PRCHS_REV_56','오수1.png','resources\\images\\20240521\\오수11927438400250600.png','2024-05-21 07:15:54','오수11927438400250600.png',347588),('PRCH_REV_FILE_202405211927438421456600','id001','PRCHS_REV_56','오수2.png','resources\\images\\20240521\\오수21927438411184000.png','2024-05-21 07:15:54','오수21927438411184000.png',406400),('PRCH_REV_FILE_202405211927438427425400','id001','PRCHS_REV_56','오수3.jpg','resources\\images\\20240521\\오수31927438421746800.jpg','2024-05-21 07:15:54','오수31927438421746800.jpg',107112),('PRCH_REV_FILE_202405211929041716280200','id001','PRCHS_REV_57','오수2.png','resources\\images\\20240521\\오수21929041708202700.png','2024-05-21 07:42:37','오수21929041708202700.png',406400),('PRCH_REV_FILE_20240522180781005523800','id001','PRCHS_REV_59','집 가고 싶다.jpg','resources\\images\\20240522\\집가고싶다180780999286200.jpg','2024-05-22 02:12:32','집가고싶다180780999286200.jpg',33444),('PRCH_REV_FILE_202405221990382646592700','id003','PRCHS_REV_58','KakaoTalk_20240520_090346272.jpg','resources\\images\\20240522\\KakaoTalk_20240520_0903462721990382632318300.jpg','2024-05-22 00:44:50','KakaoTalk_20240520_0903462721990382632318300.jpg',1374831),('PRCH_REV_FILE_202405221999512125747000','yuhee','PRCHS_REV_60','짱구1.jpg','resources\\images\\20240522\\짱구11999512118486000.jpg','2024-05-22 03:16:59','짱구11999512118486000.jpg',7499),('PRCH_REV_FILE_202405221999512133361600','yuhee','PRCHS_REV_60','짱구2.jpg','resources\\images\\20240522\\짱구21999512127550500.jpg','2024-05-22 03:16:59','짱구21999512127550500.jpg',9641),('PRCH_REV_FILE_202405221999512139128400','yuhee','PRCHS_REV_60','짱구3.jpg','resources\\images\\20240522\\짱구31999512133617900.jpg','2024-05-22 03:16:59','짱구31999512133617900.jpg',7655),('PRCH_REV_FILE_202405221999512145426800','yuhee','PRCHS_REV_60','짱구4.jpg','resources\\images\\20240522\\짱구41999512139354400.jpg','2024-05-22 03:16:59','짱구41999512139354400.jpg',7908),('PRCH_REV_FILE_202405221999512151057100','yuhee','PRCHS_REV_60','짱구5.jpg','resources\\images\\20240522\\짱구51999512145653800.jpg','2024-05-22 03:16:59','짱구51999512145653800.jpg',6665),('PRCH_REV_FILE_202405221999512157037500','yuhee','PRCHS_REV_60','짱구6.jpg','resources\\images\\20240522\\짱구61999512151276200.jpg','2024-05-22 03:16:59','짱구61999512151276200.jpg',31350),('PRCH_REV_FILE_202405221999580953103500','yuhee','PRCHS_REV_61','짱구5.jpg','resources\\images\\20240522\\짱구51999580951596600.jpg','2024-05-22 03:18:08','짱구51999580951596600.jpg',6665),('PRCH_REV_FILE_202405221999580959932700','yuhee','PRCHS_REV_61','짱구6.jpg','resources\\images\\20240522\\짱구61999580953381500.jpg','2024-05-22 03:18:08','짱구61999580953381500.jpg',31350),('PRCH_REV_FILE_202405221999580968385300','yuhee','PRCHS_REV_61','짱구7.jpg','resources\\images\\20240522\\짱구71999580960294100.jpg','2024-05-22 03:18:08','짱구71999580960294100.jpg',20287),('PRCH_REV_FILE_202405232097087697540800','id003','PRCHS_REV_62','짱구7.jpg','resources\\images\\20240523\\짱구72097087688026100.jpg','2024-05-23 06:23:08','짱구72097087688026100.jpg',20287),('PRCH_REV_FILE_202405232103153772335500','id001','PRCHS_REV_63','짱구4.jpg','resources\\images\\20240523\\짱구42103153761833500.jpg','2024-05-23 08:04:14','짱구42103153761833500.jpg',7908),('PRCH_REV_FILE_202405232103153841171700','id001','PRCHS_REV_63','짱구5.jpg','resources\\images\\20240523\\짱구52103153776075500.jpg','2024-05-23 08:04:14','짱구52103153776075500.jpg',6665),('PRCH_REV_FILE_202405232103153850003100','id001','PRCHS_REV_63','짱구6.jpg','resources\\images\\20240523\\짱구62103153841402200.jpg','2024-05-23 08:04:14','짱구62103153841402200.jpg',31350),('PRCH_REV_FILE_202405232103153862395099','id001','PRCHS_REV_63','짱구7.jpg','resources\\images\\20240523\\짱구72103153850277400.jpg','2024-05-23 08:04:14','짱구72103153850277400.jpg',20287);
/*!40000 ALTER TABLE `REVIEW_IMAGE` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-25  9:50:14
