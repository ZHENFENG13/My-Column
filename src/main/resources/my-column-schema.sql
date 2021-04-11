CREATE DATABASE /*!32312 IF NOT EXISTS*/`my_column_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `my_column_db`;

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `login_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '登陆名称(默认为手机号)',
  `password_md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'MD5加密后的密码',
  `head_img_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `introduce` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '个人简介',
  `profession` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '行业',
  `address` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '居住地',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO my_column_db.tb_user (user_id, login_name, password_md5, head_img_url, introduce, profession, address, create_time)
VALUES
(1, '13711110001', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '谢谢你的关注', '金融', '北京', now()),
(2, '13711110002', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '随心写作，自由表达', '医药', '广州', now()),
(3, '13711110003', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '我不怕千万人阻挡，只怕自己投降', '教育', '上海', now()),
(4, '13711110004', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '随心写作，自由表达', '基金', '杭州', now()),
(5, '13711110005', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', 'fighting', '教育', '杭州', now()),
(6, '13711110006', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '随心写作，自由表达', '未知', '北京', now()),
(7, '13711110007', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', 'fighting', '金融', '上海', now()),
(8, '13711110008', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '随心写作，自由表达', '工厂', '北京', now()),
(9, '13711110009', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '可爱', '互联网', '杭州', now()),
(10, '13711110010', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '探险家', '自由职业', '呼和浩特', now()),
(11, '13711110011', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '谢谢你的关注', '金融', '哈尔滨', now()),
(12, '13711110012', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '随心写作，自由表达', '医药', '青岛', now()),
(13, '13711110013', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', 'fighting', '教育', '上海', now()),
(14, '13711110014', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '随心写作，自由表达', '基金', '广州', now()),
(15, '13711110015', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', 'fighting', '教育', '重庆', now()),
(16, '13711110016', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '随心写作，自由表达', '未知', '长沙', now()),
(17, '13711110017', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '雄起！', '金融', '成都', now()),
(18, '13711110018', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '山河无恙，人间皆安', '医生', '武汉', now()),
(19, '13711110019', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '可爱', '互联网', '杭州', now()),
(20, '13711110020', 'e10adc3949ba59abbe56e057f20f883e', '/images/default-user-img.png', '探险家', '自由职业', '天津', now());

DROP TABLE IF EXISTS `tb_user_column`;
CREATE TABLE `tb_user_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '专栏id',
  `user_id` bigint(20) NOT NULL default 0 COMMENT '用户主键id',
  `column_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '专栏名称',
  `column_cover` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '专栏封面',
  `column_introduce` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '专栏介绍',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO my_column_db.tb_user_column (column_id, user_id, column_name, column_cover, column_introduce, create_time, update_time)
VALUES
(1, 1, '13711110001的专栏', '/images/default-column-img.jpg', '这是13711110001的专栏，赶快来阅读吧', now(), now()),
(2, 2, '13711110002的专栏', '/images/default-column-img.jpg', '这是13711110002的专栏，赶快来阅读吧', now(), now()),
(3, 3, '13711110003的专栏', '/images/default-column-img.jpg', '这是13711110003的专栏，赶快来阅读吧', now(), now()),
(4, 4, '13711110004的专栏', '/images/default-column-img.jpg', '这是13711110004的专栏，赶快来阅读吧', now(), now()),
(5, 5, '13711110005的专栏', '/images/default-column-img.jpg', '这是13711110005的专栏，赶快来阅读吧', now(), now()),
(6, 6, '13711110006的专栏', '/images/default-column-img.jpg', '这是13711110006的专栏，赶快来阅读吧', now(), now()),
(7, 7, '13711110007的专栏', '/images/default-column-img.jpg', '这是13711110007的专栏，赶快来阅读吧', now(), now()),
(8, 8, '13711110008的专栏', '/images/default-column-img.jpg', '这是13711110008的专栏，赶快来阅读吧', now(), now()),
(9, 9, '13711110009的专栏', '/images/default-column-img.jpg', '这是13711110009的专栏，赶快来阅读吧', now(), now()),
(10, 10, '13711110010的专栏', '/images/default-column-img.jpg', '这是13711110010的专栏，赶快来阅读吧', now(), now()),
(11, 11, '13711110011的专栏', '/images/default-column-img.jpg', '这是13711110011的专栏，赶快来阅读吧', now(), now()),
(12, 12, '13711110012的专栏', '/images/default-column-img.jpg', '这是13711110012的专栏，赶快来阅读吧', now(), now()),
(13, 13, '13711110013的专栏', '/images/default-column-img.jpg', '这是13711110013的专栏，赶快来阅读吧', now(), now()),
(14, 14, '13711110014的专栏', '/images/default-column-img.jpg', '这是13711110014的专栏，赶快来阅读吧', now(), now()),
(15, 15, '13711110015的专栏', '/images/default-column-img.jpg', '这是13711110015的专栏，赶快来阅读吧', now(), now()),
(16, 16, '13711110016的专栏', '/images/default-column-img.jpg', '这是13711110016的专栏，赶快来阅读吧', now(), now()),
(17, 17, '13711110017的专栏', '/images/default-column-img.jpg', '这是13711110017的专栏，赶快来阅读吧', now(), now()),
(18, 18, '13711110018的专栏', '/images/default-column-img.jpg', '这是13711110018的专栏，赶快来阅读吧', now(), now()),
(19, 19, '13711110019的专栏', '/images/default-column-img.jpg', '这是13711110019的专栏，赶快来阅读吧', now(), now()),
(20, 20, '13711110020的专栏', '/images/default-column-img.jpg', '这是13711110020的专栏，赶快来阅读吧', now(), now());

DROP TABLE IF EXISTS `tb_column_article`;
CREATE TABLE `tb_column_article` (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `column_id` bigint(20) NOT NULL default 0 COMMENT '专栏id',
  `user_id` bigint(20) NOT NULL default 0 COMMENT '用户主键id',
  `article_title` varchar(200) NOT NULL COMMENT '文章标题',
  `article_cover_image` varchar(200) NOT NULL COMMENT '文章封面图',
  `article_content` mediumtext NOT NULL COMMENT '文章内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`article_id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;