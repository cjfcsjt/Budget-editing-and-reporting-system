-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: ysxt
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acquisition_table`
--

DROP TABLE IF EXISTS `acquisition_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `acquisition_table` (
  `idacquisitionTable` int(11) NOT NULL AUTO_INCREMENT,
  `college_name` varchar(45) NOT NULL COMMENT '学院名称',
  `department_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL COMMENT '项目名称',
  `budget_proposal` float NOT NULL,
  `notes` varchar(45) DEFAULT NULL,
  `audit_time` varchar(45) DEFAULT NULL,
  `audit_result` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核结果',
  PRIMARY KEY (`idacquisitionTable`),
  KEY `collegename_idx` (`college_name`),
  KEY `dept_id_idx` (`department_id`),
  KEY `pro_id_idx` (`project_id`),
  CONSTRAINT `coll_name` FOREIGN KEY (`college_name`) REFERENCES `college` (`name`),
  CONSTRAINT `departmentid` FOREIGN KEY (`department_id`) REFERENCES `department` (`iddepartment`),
  CONSTRAINT `proid` FOREIGN KEY (`project_id`) REFERENCES `project` (`idproject`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acquisition_table`
--

LOCK TABLES `acquisition_table` WRITE;
/*!40000 ALTER TABLE `acquisition_table` DISABLE KEYS */;
INSERT INTO `acquisition_table` VALUES (67,'软件学院',3,1,123,'差旅','111',1),(68,'软件学院',3,2,456,'购置','111',1),(69,'软件学院',3,3,789,'修缮','111',1);
/*!40000 ALTER TABLE `acquisition_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budget`
--

DROP TABLE IF EXISTS `budget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `budget` (
  `budgetid` int(11) NOT NULL AUTO_INCREMENT,
  `college_name` varchar(45) NOT NULL COMMENT '学院名称',
  `department_id` int(11) NOT NULL COMMENT '科室名称',
  `project_id` int(11) NOT NULL COMMENT '项目名称',
  `budget_amount` float NOT NULL COMMENT '预算编制',
  `budget_notes` varchar(45) DEFAULT NULL COMMENT '预算编制说明',
  `audit_time` varchar(45) DEFAULT NULL COMMENT '审核时间',
  `audit_result` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核结果',
  PRIMARY KEY (`budgetid`),
  KEY `cn_idx` (`college_name`),
  KEY `di_idx` (`department_id`),
  KEY `pi_idx` (`project_id`),
  CONSTRAINT `cn` FOREIGN KEY (`college_name`) REFERENCES `college` (`name`),
  CONSTRAINT `di` FOREIGN KEY (`department_id`) REFERENCES `department` (`iddepartment`),
  CONSTRAINT `pi` FOREIGN KEY (`project_id`) REFERENCES `project` (`idproject`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budget`
--

LOCK TABLES `budget` WRITE;
/*!40000 ALTER TABLE `budget` DISABLE KEYS */;
INSERT INTO `budget` VALUES (41,'软件学院',3,3,700,'说明1','2018/7/24 下午11:37:41',1),(42,'软件学院',3,2,400,'说明2','2018/7/24 下午11:37:42',1),(43,'软件学院',3,1,100,'说明3','2018/7/24 下午11:37:42',1);
/*!40000 ALTER TABLE `budget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `college` (
  `idcollege` int(11) NOT NULL COMMENT '学院编号',
  `name` varchar(45) NOT NULL COMMENT '学院名称',
  `address` varchar(45) NOT NULL COMMENT '学院地址',
  `head` varchar(45) NOT NULL COMMENT '负责人',
  `college_state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES (4,'微电子学院','高新区舜华路1500号','阿斯弗',0),(2,'数学学院','济南市历城区山大南路27号','宋晓强',0),(3,'计算机学院','山东省青岛市即墨区滨海路72号','李庆阳',0),(1,'软件学院','高新区舜华路1500号','何思源',0);
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college_controlnum`
--

DROP TABLE IF EXISTS `college_controlnum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `college_controlnum` (
  `college_name` varchar(45) NOT NULL COMMENT '学院名称',
  `budget_control_num` float NOT NULL COMMENT '预算控制数',
  `notes` varchar(45) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`college_name`),
  CONSTRAINT `collename` FOREIGN KEY (`college_name`) REFERENCES `college` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college_controlnum`
--

LOCK TABLES `college_controlnum` WRITE;
/*!40000 ALTER TABLE `college_controlnum` DISABLE KEYS */;
INSERT INTO `college_controlnum` VALUES ('软件学院',1300,'nothing');
/*!40000 ALTER TABLE `college_controlnum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `department` (
  `iddepartment` int(11) NOT NULL AUTO_INCREMENT COMMENT '科室编号',
  `name` varchar(45) NOT NULL COMMENT '科室名称',
  `head` varchar(45) NOT NULL COMMENT '负责人',
  `tel` varchar(45) NOT NULL COMMENT '电话',
  `college_name` varchar(45) NOT NULL COMMENT '学院名称',
  `depart_state` tinyint(4) NOT NULL,
  PRIMARY KEY (`iddepartment`),
  KEY `idcollege_idx` (`college_name`),
  CONSTRAINT `college_name` FOREIGN KEY (`college_name`) REFERENCES `college` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'行政部门','何思源','17864154914','软件学院',0),(2,'党委部门','何老板','17812345677','软件学院',0),(3,'学院工会','何炅','17811111111','软件学院',0),(9,'行政部门','宋晓强','17877777777','数学学院',0),(10,'党委部门','宋慈','17888888888','数学学院',0),(11,'学院工会','宋庆龄','17899999999','数学学院',0),(17,'行政部门','李庆阳','17966666666','计算机学院',0),(18,'党委部门','李大钊','17977777777','计算机学院',0),(19,'学院工会','李国章','17988888888','计算机学院',0);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_controlnum`
--

DROP TABLE IF EXISTS `department_controlnum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `department_controlnum` (
  `department_id` int(11) NOT NULL COMMENT '科室名称',
  `college_name` varchar(45) NOT NULL COMMENT '学院名称',
  `budget_control_num` float NOT NULL COMMENT '科室预算控制数',
  `notes` varchar(45) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`department_id`),
  KEY `coname_idx` (`college_name`),
  CONSTRAINT `coname` FOREIGN KEY (`college_name`) REFERENCES `college` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_controlnum`
--

LOCK TABLES `department_controlnum` WRITE;
/*!40000 ALTER TABLE `department_controlnum` DISABLE KEYS */;
INSERT INTO `department_controlnum` VALUES (3,'软件学院',1200,'good');
/*!40000 ALTER TABLE `department_controlnum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `first_summary_table`
--

DROP TABLE IF EXISTS `first_summary_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `first_summary_table` (
  `department_id` int(11) NOT NULL COMMENT '科室名称',
  `college_name` varchar(45) NOT NULL COMMENT '学院名称',
  `summary_budget` float NOT NULL COMMENT '科室预算总建议数',
  `notes` varchar(45) DEFAULT NULL COMMENT '备注',
  `audit_time` varchar(45) DEFAULT NULL COMMENT '审核时间',
  `audit_result` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核结果',
  PRIMARY KEY (`department_id`),
  KEY `collname_idx` (`college_name`),
  CONSTRAINT `collname` FOREIGN KEY (`college_name`) REFERENCES `college` (`name`),
  CONSTRAINT `deptid` FOREIGN KEY (`department_id`) REFERENCES `department` (`iddepartment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `first_summary_table`
--

LOCK TABLES `first_summary_table` WRITE;
/*!40000 ALTER TABLE `first_summary_table` DISABLE KEYS */;
INSERT INTO `first_summary_table` VALUES (3,'软件学院',1368,'说明','2018-07-21',1);
/*!40000 ALTER TABLE `first_summary_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `project` (
  `idproject` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目编号',
  `name` varchar(45) NOT NULL COMMENT '项目名称',
  PRIMARY KEY (`idproject`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'差旅费'),(2,'购置费'),(3,'修缮费');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `second_summary_table`
--

DROP TABLE IF EXISTS `second_summary_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `second_summary_table` (
  `college_name` varchar(45) NOT NULL COMMENT '学院名称',
  `department_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '科室名称',
  `summary_budget` varchar(45) NOT NULL COMMENT '科室预算总额',
  `budget_notes` varchar(45) DEFAULT NULL,
  `audit_time` varchar(45) DEFAULT NULL COMMENT '审核时间',
  `audit_result` tinyint(4) NOT NULL COMMENT '审核结果',
  PRIMARY KEY (`department_id`),
  KEY `namecollege_idx` (`college_name`),
  CONSTRAINT `iddept` FOREIGN KEY (`department_id`) REFERENCES `department` (`iddepartment`),
  CONSTRAINT `namecollege` FOREIGN KEY (`college_name`) REFERENCES `college` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `second_summary_table`
--

LOCK TABLES `second_summary_table` WRITE;
/*!40000 ALTER TABLE `second_summary_table` DISABLE KEYS */;
INSERT INTO `second_summary_table` VALUES ('软件学院',3,'1200.0','说明','2018-07-21',1);
/*!40000 ALTER TABLE `second_summary_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `worker` (
  `idworker` varchar(10) NOT NULL COMMENT '员工编号',
  `name` varchar(45) NOT NULL COMMENT '员工姓名',
  `sex` varchar(45) NOT NULL COMMENT '性别',
  `tel` varchar(45) NOT NULL COMMENT '电话',
  `title` varchar(45) NOT NULL COMMENT '职务',
  `address` varchar(45) NOT NULL COMMENT '地址',
  `birth` varchar(45) NOT NULL COMMENT '出生年月',
  `password` varchar(45) NOT NULL COMMENT '系统密码',
  `college_name` varchar(45) NOT NULL COMMENT '学院名称',
  `department_id` int(11) NOT NULL COMMENT '科室名称',
  PRIMARY KEY (`idworker`),
  KEY `college_name_idx` (`college_name`),
  KEY `dept_id_idx` (`department_id`),
  CONSTRAINT `collegename` FOREIGN KEY (`college_name`) REFERENCES `college` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dpid` FOREIGN KEY (`department_id`) REFERENCES `department` (`iddepartment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES ('2018071500','学校用户1','男','17852131111','财务部门管理员','温州','19970118','123','软件学院',1),('2018071501','学院用户1','男','1921212121','财政部门管理员','苏州','19971228','123','软件学院',2),('2018071502','部门用户1','男','123456777','业务部门管理员','杭州','20001222','123','软件学院',3),('2018071503','学校用户2','男','17853143933','财务部门管理员','温州','19971111','123','计算机学院',9),('2018071504','学院用户2','男','17853143934','财政部门管理员','杭州','19981111','123','计算机学院',10),('2018071505','部门用户2','男','17853143935','业务部门管理员','杭州','19991111','123','计算机学院',11),('2018071506','学校用户3','男','17853143131','财务部门管理员','温州','20001111','123','数学学院',17),('2018071507','学院用户3','男','17856454412','财政部门管理员','温州','20001112','123','数学学院',18),('2018071508','部门用户3','男','17852152222','业务部门管理员','温州','20000111','123','数学学院',19);
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-24 23:49:22
