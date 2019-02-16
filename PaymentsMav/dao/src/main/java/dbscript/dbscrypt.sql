-- MySQL dump 10.13  Distrib 8.0.11, for macos10.13 (x86_64)
--
-- Host: 127.0.0.1    Database: Payments
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `T_Bank`
--

DROP TABLE IF EXISTS `T_Bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `T_Bank` (
  `F_bankAccount_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `F_ACCOUNT_BALANCE` double NOT NULL,
  `F_BANK_ACCOUNT` varchar(255) DEFAULT NULL,
  `F_CLIENT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`F_bankAccount_ID`),
  UNIQUE KEY `UK_hipfxrxb6tgldnscrj6t1y23w` (`F_BANK_ACCOUNT`),
  KEY `FK3rmbovrcp3xnfoq6qqgeki24u` (`F_CLIENT_ID`),
  CONSTRAINT `FK3rmbovrcp3xnfoq6qqgeki24u` FOREIGN KEY (`F_CLIENT_ID`) REFERENCES `t_client` (`f_client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Bank`
--

LOCK TABLES `T_Bank` WRITE;
/*!40000 ALTER TABLE `T_Bank` DISABLE KEYS */;
INSERT INTO `T_Bank` VALUES (1,112000,'BFGNM123124FSDFAGADSASGAGAS23',1),(2,41699,'BDSOA123821NFSFA1239M',2),(3,5413,'BQWE9823POLBVCXD37J',3),(4,35700422,'BCZER9214150MCXVCXDDFS',4),(5,666,'NEW_BANK_ACCOUNT0.8948010297415804',5);
/*!40000 ALTER TABLE `T_Bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Client`
--

DROP TABLE IF EXISTS `T_Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `T_Client` (
  `F_CLIENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `F_APARTMENT` varchar(255) DEFAULT NULL,
  `F_CITY` varchar(255) DEFAULT NULL,
  `F_HOUSE` varchar(255) DEFAULT NULL,
  `F_STREET` varchar(255) DEFAULT NULL,
  `F_DATE_OF_BIRTH` date NOT NULL,
  `F_FIRST_NAME` varchar(255) NOT NULL,
  `F_LAST_NAME` varchar(255) NOT NULL,
  `F_LOGIN` varchar(255) NOT NULL,
  `F_PASSWORD` varchar(255) NOT NULL,
  `F_PHONE_NUMBER` varchar(255) NOT NULL,
  `F_STATE` varchar(255) NOT NULL,
  PRIMARY KEY (`F_CLIENT_ID`),
  UNIQUE KEY `UK_3qmjsghn9gc59i4oxxl2m4r30` (`F_LOGIN`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Client`
--

LOCK TABLES `T_Client` WRITE;
/*!40000 ALTER TABLE `T_Client` DISABLE KEYS */;
INSERT INTO `T_Client` VALUES (1,'121','Minsk','52B','Main','2001-04-25','Bobby','Lee','moonLoght','$2a$10$rAikT4NZAJLl6hhTy9gS1e.wSHQ0thjHZAfBVJqDcDLqs4mgTR/i.','+375295674321','Active'),(2,'52','Los Soligorsk','1','Shaxterov','1976-01-21','Alan','Walker','login7','$2a$10$ey8sskVy.IAWVrX8MmuTOOc6JOOgbgGEBTsd3W1fnWSFLQJnPFWO6','+375447654321','Active'),(3,'43B','Polock','54','Efrasini','1985-08-05','Jack','Morris','mbvfsa','$2a$10$VQ7IofEN8uV.oFUjfq9N9.lfakv45HMRNngSxbb/yHr0xu88sj.zq','+375445533215','Active'),(4,'71','Borisov','17','Football','1972-06-23','Zinedin','Zidan','zinadin','$2a$10$8g4mrBqafhRIc.lTUfJzl.0cJ4E9dl0EMquGKmcMpBKlP.wSzK3Am','+375255555555','Active'),(5,'666','Moscow','666','Patriki','1918-12-12','Michael','Bulgakov','master@me.com','$2a$10$2.H1ttt7BpymsnAfYfJQWeb/70zDADwZMdHr9XrCy/B11OFo/6Dvu','+375446666666','Active');
/*!40000 ALTER TABLE `T_Client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Client_Client_Profile`
--

DROP TABLE IF EXISTS `T_Client_Client_Profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `T_Client_Client_Profile` (
  `F_CLIENT_ID` bigint(20) NOT NULL,
  `F_CLIENT_PROFILE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`F_CLIENT_ID`,`F_CLIENT_PROFILE_ID`),
  KEY `FKibn9l33126ddiyxejo65n20yq` (`F_CLIENT_PROFILE_ID`),
  CONSTRAINT `FK6btlso4pcpeenictyn5oiuoud` FOREIGN KEY (`F_CLIENT_ID`) REFERENCES `t_client` (`f_client_id`),
  CONSTRAINT `FKibn9l33126ddiyxejo65n20yq` FOREIGN KEY (`F_CLIENT_PROFILE_ID`) REFERENCES `t_client_profile` (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Client_Client_Profile`
--

LOCK TABLES `T_Client_Client_Profile` WRITE;
/*!40000 ALTER TABLE `T_Client_Client_Profile` DISABLE KEYS */;
INSERT INTO `T_Client_Client_Profile` VALUES (1,1),(2,1),(5,1),(3,2),(4,2);
/*!40000 ALTER TABLE `T_Client_Client_Profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Client_Profile`
--

DROP TABLE IF EXISTS `T_Client_Profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `T_Client_Profile` (
  `F_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `F_TYPE` varchar(15) NOT NULL,
  PRIMARY KEY (`F_id`),
  UNIQUE KEY `UK_sj1mnqpvgarias3ohw8l93r88` (`F_TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Client_Profile`
--

LOCK TABLES `T_Client_Profile` WRITE;
/*!40000 ALTER TABLE `T_Client_Profile` DISABLE KEYS */;
INSERT INTO `T_Client_Profile` VALUES (2,'ADMIN'),(1,'USER');
/*!40000 ALTER TABLE `T_Client_Profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_CreditCard`
--

DROP TABLE IF EXISTS `T_CreditCard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `T_CreditCard` (
  `F_CARD_NUMBER` bigint(20) NOT NULL,
  `F_CARD_COMPANY` varchar(255) NOT NULL,
  `F_CASHBACK` int(11) DEFAULT NULL,
  `F_CVV` int(11) NOT NULL,
  `F_EXP_DATE` date NOT NULL,
  `F_FIRST_NAME` varchar(255) NOT NULL,
  `F_LAST_NAME` varchar(255) NOT NULL,
  `F_PIN` int(11) NOT NULL,
  `F_CARD_STATUS` varchar(255) NOT NULL,
  `F_BANK_ACCOUNT` varchar(255) DEFAULT NULL,
  `F_CLIENT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`F_CARD_NUMBER`),
  KEY `FK714ywdtf8uo1rpdu6d62bih7h` (`F_BANK_ACCOUNT`),
  KEY `FKsa6sh7oruvkg0rbwtjn8yvrdu` (`F_CLIENT_ID`),
  CONSTRAINT `FK714ywdtf8uo1rpdu6d62bih7h` FOREIGN KEY (`F_BANK_ACCOUNT`) REFERENCES `t_bank` (`f_bank_account`),
  CONSTRAINT `FKsa6sh7oruvkg0rbwtjn8yvrdu` FOREIGN KEY (`F_CLIENT_ID`) REFERENCES `t_client` (`f_client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_CreditCard`
--

LOCK TABLES `T_CreditCard` WRITE;
/*!40000 ALTER TABLE `T_CreditCard` DISABLE KEYS */;
INSERT INTO `T_CreditCard` VALUES (4322558700884302,'VISA',0,652,'2025-11-15','Alan','Walker',8218,'ACTIVE','BDSOA123821NFSFA1239M',2),(4444555566669999,'VISA',99,333,'2056-12-11','Michael','Bulgakov',4444,'ACTIVE','NEW_BANK_ACCOUNT0.8948010297415804',5),(4531775344009622,'VISA',8,321,'2042-03-11','Andrew','Makarevich',5421,'ACTIVE','BQWE9823POLBVCXD37J',3),(5432123467890123,'MASTERCARD',25,852,'2031-11-17','Zinedin','Zidan',3333,'ACTIVE','BCZER9214150MCXVCXDDFS',4),(5643890743210091,'MASTERCARD',7,333,'2022-07-23','Jojo','Pojo',5555,'ACTIVE','BFGNM123124FSDFAGADSASGAGAS23',2);
/*!40000 ALTER TABLE `T_CreditCard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_Transaction`
--

DROP TABLE IF EXISTS `T_Transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `T_Transaction` (
  `F_TRANSACTION_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `F_AMOUNT_OF_MONEY` double NOT NULL,
  `F_CARD_NUMMBER` bigint(20) NOT NULL,
  `F_TRANSACTION_TIME` datetime NOT NULL,
  `F_TRANSACTION_TYPE` varchar(255) NOT NULL,
  `F_creditCard_CARD_NUMBER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`F_TRANSACTION_ID`),
  KEY `FKssv186r3hb1u6tbj1csuwen1r` (`F_creditCard_CARD_NUMBER`),
  CONSTRAINT `FKssv186r3hb1u6tbj1csuwen1r` FOREIGN KEY (`F_creditCard_CARD_NUMBER`) REFERENCES `t_creditcard` (`f_card_number`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_Transaction`
--

LOCK TABLES `T_Transaction` WRITE;
/*!40000 ALTER TABLE `T_Transaction` DISABLE KEYS */;
INSERT INTO `T_Transaction` VALUES (1,2330,4531775344009622,'2019-02-11 01:24:06','DEPOSIT',4531775344009622),(2,430,4531775344009622,'2019-02-11 01:24:06','DEPOSIT',4531775344009622),(3,870,5643890743210091,'2019-02-11 01:24:06','WITHDRAW',4322558700884302),(10,101,4322558700884302,'2019-02-11 01:42:17','WITHDRAW',4322558700884302),(11,101,4531775344009622,'2019-02-11 01:42:17','DEPOSIT',4531775344009622),(12,666,4322558700884302,'2019-02-14 01:34:48','WITHDRAW',4322558700884302),(13,666,4444555566669999,'2019-02-14 01:34:48','DEPOSIT',4444555566669999);
/*!40000 ALTER TABLE `T_Transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-15  0:30:57
