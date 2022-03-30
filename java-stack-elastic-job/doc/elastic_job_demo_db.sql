DROP DATABASE IF EXISTS `elastic_job_demo`;
CREATE DATABASE `elastic_job_demo` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

USE `elastic_job_demo`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
    `id`    bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
    `age`   int(11) NULL DEFAULT NULL COMMENT '年龄',
    `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `user` VALUES (1, 'Jone', 18, 'test1@moon.com');
INSERT INTO `user` VALUES (2, 'Jack', 20, 'test2@moon.com');
INSERT INTO `user` VALUES (3, 'Tom', 28, 'test3@moon.com');
INSERT INTO `user` VALUES (4, 'Sandy', 21, 'test4@moon.com');
INSERT INTO `user` VALUES (5, 'Billie', 24, 'test5@moon.com');

SET FOREIGN_KEY_CHECKS = 1;