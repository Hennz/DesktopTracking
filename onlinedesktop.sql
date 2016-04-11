-- MySQL dump 10.10
--
-- Host: localhost    Database: onlinedesktop
-- ------------------------------------------------------
-- Server version	5.0.27-community-nt

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
-- Table structure for table `group_user`
--


create database onlinedesktop;

use onlinedesktop;


--
-- Table structure for table `nm_group`
--

DROP TABLE IF EXISTS `nm_group`;
CREATE TABLE `nm_group` (
  `group_id` int(5) NOT NULL auto_increment,
  `group_name` varchar(40) default NULL,
  `group_desc` varchar(90) default NULL,
  PRIMARY KEY  (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nm_group`
--

LOCK TABLES `nm_group` WRITE;
/*!40000 ALTER TABLE `nm_group` DISABLE KEYS */;
INSERT INTO `nm_group` VALUES (1,'DEVELOPER GROUP','DEVELOPER GROUP'),(2,'HR GROUP','HR GROUP');
/*!40000 ALTER TABLE `nm_group` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user` (
  `group_user_id` int(5) NOT NULL auto_increment,
  `grooup_user_ip` varchar(40) default NULL,
  `group_user_desc` varchar(90) default NULL,
  `group_id` int(5) default NULL,
  `host_name` varchar(50) default NULL,
  PRIMARY KEY  (`group_user_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `group_user_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `nm_group` (`group_id`),
  CONSTRAINT `group_user_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `nm_group` (`group_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_user`
--

LOCK TABLES `group_user` WRITE;
/*!40000 ALTER TABLE `group_user` DISABLE KEYS */;
INSERT INTO `group_user` VALUES (17,'192.168.1.10',NULL,1,'NM-9B247F3EAC58'),(18,'192.168.1.3',NULL,1,'NMINFOTE-074ADB'),(19,'192.168.1.6',NULL,1,'NM-EDEB7011402F'),(20,'192.168.1.8',NULL,1,'192.168.1.8'),(21,'192.168.1.2',NULL,1,'NM-MANJEET'),(22,'192.168.1.13',NULL,2,'192.168.1.13'),(23,'192.168.1.4',NULL,2,'NM-C9ACF510FABD'),(24,'192.168.1.9',NULL,1,'MINTI-786C30E1E'),(25,'192.168.1.8',NULL,1,'NM-5AA3159598D8');
/*!40000 ALTER TABLE `group_user` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-02-22  5:15:20
