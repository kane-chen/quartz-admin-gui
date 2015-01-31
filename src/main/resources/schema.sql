-- create schema IF NOT EXISTS demo;

-- drop table demo.code if exists;

CREATE TABLE `quartz_admin_scheduler` (
	   `schedulerId` bigint auto_increment,
	   `name` varchar(64) NOT NULL,
	   `host` varchar(128) NOT NULL,
	   `port` int(11) NOT NULL,
	   `userName` varchar(50) DEFAULT NULL,
	   `password` varchar(50) DEFAULT NULL,
	   PRIMARY KEY (`schedulerId`)
	 )  ;
	
	CREATE TABLE `quartz_admin_job` (
	   `jobId` bigint auto_increment,
	   `schedulerId` bigint not null,
	   `jobName` varchar(32) NOT NULL,
	   `group` varchar(32) NOT NULL,
	   `jobClass` varchar(256) NOT NULL,
	   `jobDataMap` varchar(1024) ,
	   `description` varchar(50) ,
	   `durability` int(3) NOT NULL DEFAULT '1',
	   `shouldRecover` int(3) NOT NULL DEFAULT '0',
	   `triggerCount` int(5) NOT NULL DEFAULT '0',
	   `jobStatus` int(3) NOT NULL DEFAULT '0',
	   PRIMARY KEY (`jobId`)
	 )  ;
	
	CREATE TABLE `quartz_admin_trigger` (
	   `triggerId`bigint auto_increment,
	   `jobId` bigint NOT NULL,
	   `name` varchar(50) NOT NULL,
	   `description` varchar(50) ,
	   `group` varchar(50) NOT NULL,
	   `cronexpr` varchar(50) NOT NULL,
	   PRIMARY KEY (`triggerId`)
	 )  ;
