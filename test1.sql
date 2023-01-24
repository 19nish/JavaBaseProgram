/*
MySQL Data Transfer
Source Host: localhost
Source Database: test
Target Host: localhost
Target Database: test
Date: 17-05-2022 15:44:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `Name` varchar(30) default NULL,
  `Email` varchar(30) default NULL,
  `Password` varchar(30) default NULL,
  `Course_Id` int(11) default NULL,
  `Student_Id` int(11) default NULL,
  `Professor_Id` int(11) default NULL,
  KEY `crs_id` (`Course_Id`),
  KEY `std_id` (`Student_Id`),
  KEY `prof_id` (`Professor_Id`),
  CONSTRAINT `prof_id` FOREIGN KEY (`Professor_Id`) REFERENCES `professor` (`Id`),
  CONSTRAINT `crs_id` FOREIGN KEY (`Course_Id`) REFERENCES `course` (`Id`),
  CONSTRAINT `std_id` FOREIGN KEY (`Student_Id`) REFERENCES `student` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for catalog
-- ----------------------------
DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `Course_Name` varchar(30) default NULL,
  `Course_Detail` varchar(100) default NULL,
  `Course_Professor_name` varchar(30) default NULL,
  `Course_Category` varchar(30) default NULL,
  `Course_fees` decimal(10,0) default NULL,
  `Course_Duration` varchar(30) default NULL,
  `Course_Id` int(11) default NULL,
  KEY `course_id` (`Course_Id`),
  CONSTRAINT `course_id` FOREIGN KEY (`Course_Id`) REFERENCES `course` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `Id` int(11) NOT NULL,
  ` Course_Name` varchar(40) NOT NULL,
  `Current_Student` int(11) default NULL,
  `Course_Section` varchar(30) default NULL,
  `Course_Type` varchar(30) default NULL,
  `Course_Status` int(11) default NULL,
  `Max_Student` int(11) default NULL,
  `Course_Price` decimal(10,0) NOT NULL,
  `Course_duration` datetime default NULL,
  `Student_Id` int(11) NOT NULL,
  `Professor_Id` int(11) NOT NULL,
  PRIMARY KEY  (`Id`),
  KEY `Student_Id` (`Student_Id`),
  CONSTRAINT `Student_Id` FOREIGN KEY (`Student_Id`) REFERENCES `student` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for course_registration
-- ----------------------------
DROP TABLE IF EXISTS `course_registration`;
CREATE TABLE `course_registration` (
  `Id` int(11) NOT NULL,
  `Student_Id` int(11) NOT NULL,
  `Course_Id` int(11) default NULL,
  PRIMARY KEY  (`Id`),
  KEY `studentid` (`Student_Id`),
  KEY `courseid` (`Course_Id`),
  CONSTRAINT `courseid` FOREIGN KEY (`Course_Id`) REFERENCES `course` (`Id`),
  CONSTRAINT `studentid` FOREIGN KEY (`Student_Id`) REFERENCES `student` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `registration_msg` varchar(60) default NULL,
  `CourseAdd_msg` varchar(60) default NULL,
  `CourseDrop_msg` varchar(60) default NULL,
  `PasswordChange_msg` varchar(60) default NULL,
  `PaymentAlert_msg` varchar(60) default NULL,
  `PaymentSuccessfull_msg` varchar(60) default NULL,
  `PaymentFailed_msg` varchar(60) default NULL,
  `CourseDuration_msg` varchar(60) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for payment_fees
-- ----------------------------
DROP TABLE IF EXISTS `payment_fees`;
CREATE TABLE `payment_fees` (
  `Id` varchar(30) NOT NULL,
  `Payment_Date` datetime NOT NULL,
  `Payment_Method` varchar(30) default NULL,
  `Payment_Status` int(11) default NULL,
  `RegCourse_Id` int(11) default NULL,
  PRIMARY KEY  (`Id`),
  KEY `regcourse_id` (`RegCourse_Id`),
  CONSTRAINT `regcourse_id` FOREIGN KEY (`RegCourse_Id`) REFERENCES `course_registration` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for professor
-- ----------------------------
DROP TABLE IF EXISTS `professor`;
CREATE TABLE `professor` (
  `Id` int(11) NOT NULL,
  `Professor_Name` varchar(30) default NULL,
  `Professor_Department` varchar(30) default NULL,
  `Professor_Qualification` varchar(30) default NULL,
  `Professor_Address` varchar(60) default NULL,
  `Professor_Contact` bigint(10) default NULL,
  PRIMARY KEY  (`Id`),
  KEY `Id` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `Id` int(11) NOT NULL,
  `Type` varchar(30) NOT NULL,
  `User_Id` varchar(30) NOT NULL,
  PRIMARY KEY  (`Id`),
  KEY `User_Id` (`User_Id`),
  CONSTRAINT `User_Id` FOREIGN KEY (`User_ID`) REFERENCES `user` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Id` int(255) NOT NULL default '0',
  `name` varchar(40) NOT NULL,
  `qualification` varchar(40) NOT NULL,
  `branch` varchar(40) default NULL,
  `contactno` bigint(10) default NULL,
  `address` varchar(60) default NULL,
  `grade` varchar(10) default NULL,
  `Date_of_Join` datetime default NULL,
  PRIMARY KEY  (`Id`),
  KEY `Id` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Email` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Name` varchar(30) default NULL,
  PRIMARY KEY  (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
