-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- 主機: localhost
-- 建立日期: Jan 19, 2015, 07:31 AM
-- 伺服器版本: 5.0.51
-- PHP 版本: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- 資料庫: `test`
-- 

-- --------------------------------------------------------

-- 
-- 資料表格式： `users`
-- 

CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(50) NOT NULL,
  `enabled` tinyint(4) NOT NULL default '1',
  PRIMARY KEY  (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- 列出以下資料庫的數據： `users`
-- 

INSERT INTO `users` VALUES ('root', 'e10adc3949ba59abbe56e057f20f883e', 'mkyong@test.org.tw', 1);
INSERT INTO `users` VALUES ('alex', 'e10adc3949ba59abbe56e057f20f883e', 'alex@test.org.tw', 1);
INSERT INTO `users` VALUES ('maokao', 'e10adc3949ba59abbe56e057f20f883e', 'asdfs@123.123.213', 1);
INSERT INTO `users` VALUES ('amelia', 'e10adc3949ba59abbe56e057f20f883e', 'adf@123.123.123', 1);
INSERT INTO `users` VALUES ('ranma', 'e10adc3949ba59abbe56e057f20f883e', 'maokao25@gmail.com', 1);

-- --------------------------------------------------------

-- 
-- 資料表格式： `user_roles`
-- 

CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL auto_increment,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY  (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`,`username`),
  KEY `fk_username_idx` (`username`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

-- 
-- 列出以下資料庫的數據： `user_roles`
-- 

INSERT INTO `user_roles` VALUES (1, 'root', 'ROLE_USER');
INSERT INTO `user_roles` VALUES (2, 'root', 'ROLE_ADMIN');
INSERT INTO `user_roles` VALUES (3, 'alex', 'ROLE_USER');
INSERT INTO `user_roles` VALUES (4, 'maokao', 'ROLE_ADMIN');
INSERT INTO `user_roles` VALUES (5, 'amelia', 'ROLE_ADMIN');
