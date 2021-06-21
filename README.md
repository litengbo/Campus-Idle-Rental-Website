# Campus-Idle-Rental-Website
##校园闲置租赁网站
## mysqls数据库版本：5.7.24
## Tomcat版本：8.0
### 数据库文件（直接导入到自己的数据库软件中）
/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : secondhandvue_db

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2021-05-23 14:02:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `about`
-- ----------------------------
DROP TABLE IF EXISTS `about`;
CREATE TABLE `about` (
  `id` int(11) NOT NULL auto_increment,
  `content` text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of about
-- ----------------------------
INSERT INTO `about` VALUES ('1', '<span style=\"color:#3E4D5C;font-family:&quot;font-size:14px;background-color:#FFFFFF;\"> \n<p style=\"color:#3E4D5C;font-family:&quot;font-size:13px;\">\n	Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.\n</p>\n<p style=\"color:#3E4D5C;font-family:&quot;font-size:13px;\">\n	Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.11111111\n</p>\n<h2 style=\"font-family:&quot;font-weight:500;color:#3E4D5C;font-size:20px;\">\n	LOREM IPSUM DOLOR SIT AMET2222\n</h2>\n<p style=\"color:#3E4D5C;font-family:&quot;font-size:13px;\">\n	Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius.\n</p>\n</span>');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `fatherid` int(11) default NULL,
  `delstatus` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fatherid` (`fatherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('4', '电子产品', '0', '0');
INSERT INTO `category` VALUES ('14', '图书', '0', '0');
INSERT INTO `category` VALUES ('15', '小说', '14', '0');
INSERT INTO `category` VALUES ('16', '教学资料', '14', '0');
INSERT INTO `category` VALUES ('17', '手机', '4', '0');
INSERT INTO `category` VALUES ('18', '相机', '4', '0');
INSERT INTO `category` VALUES ('19', '笔记本', '4', '0');
INSERT INTO `category` VALUES ('20', '运动', '0', '0');
INSERT INTO `category` VALUES ('21', '运动器材', '20', '0');
INSERT INTO `category` VALUES ('22', '运动护具', '20', '0');
INSERT INTO `category` VALUES ('26', '教资', '25', '0');
INSERT INTO `category` VALUES ('27', '111', '25', '0');

-- ----------------------------
-- Table structure for `chat`
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL,
  `content` text,
  `savetime` varchar(255) default NULL,
  `hfcontent` text,
  PRIMARY KEY  (`id`),
  KEY `fk_chat_member` (`memberid`),
  CONSTRAINT `fk_chat_member` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES ('29', '40', '你好', '2021-04-29 16:31:04', '你好');

-- ----------------------------
-- Table structure for `fav`
-- ----------------------------
DROP TABLE IF EXISTS `fav`;
CREATE TABLE `fav` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL,
  `productid` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_fav_member` (`memberid`),
  KEY `fk_fav_product` (`productid`),
  CONSTRAINT `fk_fav_member` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_fav_product` FOREIGN KEY (`productid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fav
-- ----------------------------
INSERT INTO `fav` VALUES ('39', '45', '41');

-- ----------------------------
-- Table structure for `imgadv`
-- ----------------------------
DROP TABLE IF EXISTS `imgadv`;
CREATE TABLE `imgadv` (
  `id` int(11) NOT NULL auto_increment,
  `filename` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of imgadv
-- ----------------------------
INSERT INTO `imgadv` VALUES ('1', 'd96fca3d-7385-46e5-b914-e0981a15f041.jpg', 'https://www.lenovo.com.cn/');
INSERT INTO `imgadv` VALUES ('2', 'e433052f-16af-4f22-9fa4-6d7fae1ab7bb.jpg', 'https://shop.vivo.com.cn/product/10000467');
INSERT INTO `imgadv` VALUES ('3', 'ed49dab4-f476-4801-840f-fe5bb0eb51a5.jpg', 'http://www.mi.com/redmik30');

-- ----------------------------
-- Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL auto_increment,
  `uname` varchar(12) default NULL,
  `upass` varchar(255) default NULL,
  `tname` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  `birthtime` varchar(255) default NULL,
  `idcard` varchar(255) default NULL,
  `tel` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `delstatus` varchar(255) default NULL,
  `filename` varchar(255) default NULL,
  `status` varchar(255) default NULL,
  `wallet` decimal(10,2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('40', '201740405115', '123', '腾博', '男', '1998-08-19', '13013219980319255X', '15610800750', '2382254684@qq.com', '0', 'ba650824-383c-45d7-98f6-09a3d8e546e8.jpg', '正常', '1113.00');
INSERT INTO `member` VALUES ('42', '123', '123', 'admin', '男', '2021-05-08', '13013219980319255X', '15610800750', '15610800750@163.com', '0', 'noimg.jpg', '正常', '320.00');
INSERT INTO `member` VALUES ('45', '201740405120', '11111111', '张三', '男', '1998-03-19', '13013219980319255X', '13888888888', 'zs@163.com', '0', 'c9c1ba6f-8416-4e29-a0a4-b723fa3628a7.jpg', '正常', '0.00');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL,
  `sellerid` int(11) default NULL,
  `productid` int(11) default NULL,
  `content` text,
  `savetime` varchar(255) default NULL,
  `hfcontent` text,
  `score` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_message_member-memberid` (`memberid`),
  KEY `fk_message_member_sellerid` (`sellerid`),
  KEY `fk_message_product` (`productid`),
  CONSTRAINT `fk_message_member-memberid` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_message_member_sellerid` FOREIGN KEY (`sellerid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_message_product` FOREIGN KEY (`productid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(255) default NULL,
  `filename` varchar(255) default NULL,
  `content` text,
  `savetime` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('7', '二手物品置换、出售、拍卖，一站式闲置物品处理平台闲转获1000万元天使投资', 'd46f1abc-3e19-4bad-9ee4-8588c573a910.jpg', '近日，猎云网（微信：ilieyun）获悉，闲置物品一站式处理服务公司武汉市闲转信息技术有限公司于去年12月获1000万元天使投资，资金由赛富基金和盈动资本提供，主要用于产品研发和升级、用户获取和培养等方面。资方投资闲转的原因是其在二手市场布局较广且二手市场仍有很大潜力。<br />\n<br />\n在传统观念中，二手物品往往给人“低一等”的感觉。但近几年，随着“物尽其用”、“循环利用”等绿色消费理念渐渐流行，越来越多的消费者尤其是年轻一代成为二手商品的常客，选择购买二手商品甚至成为年轻人“时髦”的生活方式。年轻人就是其中主要的群体之一，他们大部分收入不是很高，但对新鲜事物充满好奇，且具有前卫的消费观，使得年轻人对二手交易十分青睐。<br />', '2021-02-24 14:54:10');
INSERT INTO `notice` VALUES ('8', '获数百万元天使轮投资，Have要打造有调性的闲置物品交易平台', '3fdfa09c-5b80-4f19-8ddc-715ab6cff466.jpg', '近日，猎云网（微信：ilieyun）获悉，闲置物品一站式处理服务公司武汉市闲转信息技术有限公司于去年12月获1000万元天使投资，资金由赛富基金和盈动资本提供，主要用于产品研发和升级、用户获取和培养等方面。资方投资闲转的原因是其在二手市场布局较广且二手市场仍有很大潜力。<br />\n<br />\n在传统观念中，二手物品往往给人“低一等”的感觉。但近几年，随着“物尽其用”、“循环利用”等绿色消费理念渐渐流行，越来越多的消费者尤其是年轻一代成为二手商品的常客，选择购买二手商品甚至成为年轻人“时髦”的生活方式。年轻人就是其中主要的群体之一，他们大部分收入不是很高，但对新鲜事物充满好奇，且具有前卫的消费观，使得年轻人对二手交易十分青睐。<br />', '2021-02-24 14:55:18');
INSERT INTO `notice` VALUES ('9', '中国青年报社社会调查中心：75.9％受访者使用过网络闲置物品交易平台', 'cf47140e-a8ac-4575-8016-85c387f6bcf1.jpg', '日前，中国青年报社社会调查中心联合问卷网，对2001人进行的一项调查显示，75.9％的受访者使用过网络闲置物品交易平台。在这些平台，受访者更愿意买卖电子产品（61.0％）和书籍（45.2％）。受访者最担心的问题是质量难以保证（56.0％）、欺诈骗钱（45.6％）和物品掉包（43.3％）。64.2％的受访者认为要加强使用第三方担保支付。<br />\n<br />\n<p>\n	75.9％受访者使用过网络闲置物品交易平台\n</p>\n<p>\n	<br />\n</p>\n<p>\n	<br />\n</p>', '2021-02-24 14:56:17');

-- ----------------------------
-- Table structure for `ordermsg`
-- ----------------------------
DROP TABLE IF EXISTS `ordermsg`;
CREATE TABLE `ordermsg` (
  `id` int(11) NOT NULL auto_increment,
  `ddno` varchar(255) default NULL,
  `memberid` int(11) default NULL,
  `productid` int(11) default NULL,
  `stime` varchar(255) default NULL,
  `etime` varchar(255) default NULL,
  `total` decimal(10,2) default NULL,
  `yjprice` decimal(10,2) default NULL,
  `content` text,
  `status` varchar(255) default NULL,
  `sellerid` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_ordermsg_member_memberid` (`memberid`),
  KEY `fk_ordermsg_member_sellerid` (`sellerid`),
  KEY `ddno` (`ddno`),
  KEY `fk_ordermsg_product` (`productid`),
  CONSTRAINT `fk_ordermsg_member_memberid` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ordermsg_member_sellerid` FOREIGN KEY (`sellerid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ordermsg_product` FOREIGN KEY (`productid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ordermsg
-- ----------------------------
INSERT INTO `ordermsg` VALUES ('31', '999005', '45', '41', '2021-05-08', '2021-06-08', '320.00', '100.00', '', '交易完成', '42');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `filename` varchar(255) default NULL,
  `categoryid` int(11) default NULL,
  `childid` int(11) default NULL,
  `price` decimal(10,2) default NULL,
  `content` text,
  `sellerid` int(11) default NULL,
  `status` varchar(255) default NULL,
  `issj` varchar(255) default NULL,
  `shstatus` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_product_category` (`categoryid`),
  KEY `fk_product_categorychild` (`childid`),
  KEY `fk_product_member` (`sellerid`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_product_categorychild` FOREIGN KEY (`childid`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_product_member` FOREIGN KEY (`sellerid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('30', '华为Mate 30pro', 'd6087cc5-f878-4bf2-8610-c57033c6d5b0.jpg', '4', '17', '10.00', '华为Mate30Pro，仅一台<br />租用请先联系我', '42', '闲置', 'yes', '通过审核');
INSERT INTO `product` VALUES ('31', '单反相机', '355a7b5c-fb7b-44cc-a6ab-ea9ab4111d82.jpg', '4', '18', '20.00', '尼康相机<br />租用请先联系我', '42', '闲置', 'yes', '通过审核');
INSERT INTO `product` VALUES ('32', '狂人日记（鲁迅）', '87760bc3-514d-46b1-98a9-436acb024513.jpg', '14', '15', '1.00', '鲁迅著作，租用请先联系我', '42', '闲置', 'yes', '通过审核');
INSERT INTO `product` VALUES ('33', '苹果12', '5e4ce6de-f305-4658-abc0-15d97f162a15.jpg', '4', '17', '6.00', '苹果12（512GB）租用请先联系我', '42', '闲置', 'yes', '通过审核');
INSERT INTO `product` VALUES ('34', '大一计算机基础', '9a2049c2-8334-46e0-91af-6c8271b3574d.jpg', '14', '16', '1.00', '计算机基础（先到先得，仅一本）租用请先联系我', '42', '闲置', 'yes', '通过审核');
INSERT INTO `product` VALUES ('36', '教师图书', 'f816500e-dc3f-4691-b338-53e47107ce0f.jpg', '14', '16', '3.00', '教资必备，租用请先联系我', '42', '闲置', 'yes', '通过审核');
INSERT INTO `product` VALUES ('37', '活着（余华）', '5cf8776e-b096-43b5-8b9c-d6ee3fc542a7.jpg', '14', '15', '1.00', '余华经典著作，教材选段，租用请先联系我', '42', '闲置', 'yes', '通过审核');
INSERT INTO `product` VALUES ('38', '滑板', '5c58d9d1-e785-425f-a08f-bb7b6a46cdca.jpg', '20', '21', '5.00', '滑板（法拉利）<br />，租用请先联系我', '42', '闲置', 'yes', '通过审核');
INSERT INTO `product` VALUES ('39', '狼王梦', '7bafac52-f0c3-4498-9cf8-2d578dca0bb3.jpg', '14', '15', '1.00', '狼王梦，租用请先联系我', '42', '闲置', 'yes', '通过审核');
INSERT INTO `product` VALUES ('40', '小米9手机', 'e2ec39b6-bdcd-45d1-8071-902374aa9ca5.jpg', '4', '17', '5.00', '5元一天（8GB+256GB），租用请先联系我', '40', '闲置', 'yes', '通过审核');
INSERT INTO `product` VALUES ('41', 'ThinkPad', 'b0934c4d-ab8d-467d-965c-c3767a9d18a3.jpg', '4', '19', '10.00', 'ThinkPad，租用请先联系我', '42', '闲置', 'yes', '通过审核');
INSERT INTO `product` VALUES ('46', '毛泽东选集', '1d80fc99-9cc6-4ef7-bdfa-c83bdb52b2c5.jpg', '14', '15', '1.00', '毛泽东选集', '45', '闲置', 'yes', '通过审核');

-- ----------------------------
-- Table structure for `sysuser`
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `id` int(20) NOT NULL auto_increment,
  `username` varchar(255) default NULL,
  `userpwd` varchar(255) default NULL,
  `usertype` varchar(255) default NULL,
  `realname` varchar(255) default NULL,
  `filename` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  `tel` varchar(255) default NULL,
  `idcard` varchar(255) default NULL,
  `delstatus` varchar(255) default NULL,
  `status` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysuser
-- ----------------------------
INSERT INTO `sysuser` VALUES ('16', 'admin', '123', '管理员', 'admin', '2c7c5999-65f8-4ab5-a7c2-3d158ecd09a8.jpg', '男', '15610800750', '422429198502013322', '0', '在岗');
### 修改Mysql数据库的密码（src文件夹下的db.properties）
![image](https://user-images.githubusercontent.com/53282131/122696908-ccdde480-d276-11eb-81cd-75fbb184ccd7.png)
