-- 初始化所有表
CREATE DATABASE bookstore CHARACTER SET UTF8;
USE bookstore;
DROP DATABASE bookstore;
-- 图书分类表
CREATE TABLE TYPES(
  id VARCHAR(32) PRIMARY KEY, -- 编号
  NAME VARCHAR(50),  -- 分类名称
  descr VARCHAR(200)  -- 备注
);

-- 图书信息表
CREATE TABLE books(
	id VARCHAR(32) PRIMARY KEY, -- 编号
	NAME VARCHAR(50),  -- 图书名称
	price NUMERIC(10,2), -- 价格
	auth VARCHAR(30), -- 作者
	img VARCHAR(100), -- 图片地址
	rebate NUMERIC(3,2), -- 折扣
	stock INT,   -- 库存
	publisher VARCHAR(100), -- 出版社
	publishdate VARCHAR(19), -- 出版日期
	pages INT,   -- 页码
	size INT,     -- 开本（如16开）
	printtimes INT, -- 印刷次数
	versions INT,  -- 版本
	brief TEXT,   -- 简介
	content TEXT,   -- 目录
	onlinetime VARCHAR(19) -- 上线时间
);

-- 图书 -分类 关系表
CREATE TABLE booktype(
   bookid VARCHAR(32) NOT NULL,  -- 图书编号（外键）
   typeid VARCHAR(32) NOT NULL,  -- 分类编号（外键）
   CONSTRAINT btpk PRIMARY KEY(bookid,typeid), -- 设置主键
   CONSTRAINT btfk1 FOREIGN KEY(bookid) REFERENCES books(id), -- 设置外键
   CONSTRAINT btfk2 FOREIGN KEY(typeid) REFERENCES TYPES(id)  -- 设置外键
);

-- 管理员信息表
CREATE TABLE admins(
  id VARCHAR(32) PRIMARY KEY, -- 编号
  NAME VARCHAR(30), -- 用户名
  PASSWORD VARCHAR(32), -- 密码
  sex CHAR(1),  -- 性别
  email VARCHAR(50),  -- 邮箱
  descr VARCHAR(200)  -- 备注
);

-- 前台用户表
CREATE TABLE users(
	id VARCHAR(32) PRIMARY KEY, -- 编号
	NAME VARCHAR(50), -- 用户名
	PASSWORD VARCHAR(32), -- 密码
	phone VARCHAR(50), -- 手机
	email VARCHAR(50) --  邮箱
);

-- 发货地址表
CREATE TABLE address(
	id VARCHAR(32) PRIMARY KEY, -- 编号
	NAME VARCHAR(50), -- 发货地址
	phone VARCHAR(50), -- 联系电话
	zip VARCHAR(50), -- 邮编
	dft CHAR(1) DEFAULT '0', -- 是否设置为默认
	userid VARCHAR(32),  -- 用户编号（外键）
	mktime VARCHAR(19), -- 创建时间
	CONSTRAINT addr_fk1 FOREIGN KEY(userid) REFERENCES users(id) -- 设置外键
);

-- 订单表
CREATE TABLE orders(
	id VARCHAR(32) PRIMARY 	KEY, -- 编号
	userid VARCHAR(32), -- 用户编号（外键）
	consignee VARCHAR(300), -- 收货人
	paytype CHAR(1), -- 付款方式
	amt NUMERIC(10,2), -- 总金额
	state CHAR(1), -- 订单状态（0：等待发货;1:商家已发货;2:交易已经完成;3:交易已取消;4:商户已退货）
	orderdate VARCHAR(19), -- 下订单时间
	CONSTRAINT orders_fk FOREIGN KEY(userid) REFERENCES users(id) -- 设置外键
);

-- 订单明细表
CREATE TABLE orderline(
	id VARCHAR(32) PRIMARY KEY, -- 编号
	orderid VARCHAR(32), -- 订单编号（外键）
	bookid VARCHAR(32), -- 图书编号（外键）
	Amt  INT, -- 合计
	price NUMERIC(10,2), -- 价格
	CONSTRAINT orderline_fk FOREIGN KEY(orderid) REFERENCES orders(id), -- 设置外键
	CONSTRAINT orderline_fk2 FOREIGN KEY(bookid) REFERENCES books(id) -- 设置外键
);

-- -------------------------------------------------------------

-- 初始化数据
USE bookstore;

-- 以下是图书分类
INSERT INTO TYPES VALUES('T001','计算机类','计算机类');
INSERT INTO TYPES VALUES('T002','管理类','管理类');
INSERT INTO TYPES VALUES('T003','医学类','医学类');

-- 以下是图书信息
INSERT INTO books VALUES('B001','计算机基础',34.12,'张三','a.jpg',1.0,100,'传智播客出版社','2011-01-09',344,16,1,1,'计算机基础教学','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B002','管理学基础',34.12,'张三','b.jpg',1.0,100,'传智播客出版社','2011-01-09',344,16,1,1,'一本关于政治的图书','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B003','大家风范',34.12,'李四','c.jpg',1.0,100,'传智播客出版社','2011-01-09',344,16,1,1,'小学语言学','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B004','小学语言学',34.12,'王五','d.jpg',0.87,100,'传智播客出版社','2011-01-09',344,16,1,1,'最基础的学习方法教学','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B005','Java基础',34.12,'马七','e.jpg',0.77,100,'传智播客出版社','2011-01-09',344,16,1,1,'人海战术','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B006','数据库入门',34.12,'那六','f.jpg',0.30,100,'传智播客出版社','2011-01-09',344,16,1,1,'网络的配置说明','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B007','SqlServer',34.12,'小胡','g.jpg',0.9,100,'传智播客出版社','2011-01-09',344,16,1,1,'大家都说说这是为什么','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B008','JavaEE',34.12,'老江','h.jpg',0.5,100,'传智播客出版社','2011-01-09',344,16,1,1,'海关总说明书','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B009','数学之美(第二版)',34.12,'张三','1.jpg',1.0,100,'传智播客出版社','2011-01-09',344,16,1,1,'计算机基础教学','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B010','计算机应用基础（第2版）',34.12,'张三','2.jpg',1.0,100,'传智播客出版社','2011-01-09',344,16,1,1,'一本关于政治的图书','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B011','编译原理（第2版）——计算机科学丛书',34.12,'李四','3.jpg',1.0,100,'传智播客出版社','2011-01-09',344,16,1,1,'小学语言学','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B012','云计算与分布式系统：从并行处理到物联网',34.12,'王五','4.jpg',0.87,100,'传智播客出版社','2011-01-09',344,16,1,1,'最基础的学习方法教学','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B013','深入理解计算机系统（英文版·第2版）',34.12,'马七','5.jpg',0.77,100,'传智播客出版社','2011-01-09',344,16,1,1,'人海战术','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B014','数据库入门',34.12,'那六','6.jpg',0.30,100,'传智播客出版社','2011-01-09',344,16,1,1,'网络的配置说明','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B015','算法谜题',34.12,'小胡','7.jpg',0.9,100,'传智播客出版社','2011-01-09',344,16,1,1,'大家都说说这是为什么','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B016','云计算（第二版）',34.12,'老江','8.jpg',0.5,100,'传智播客出版社','2011-01-09',344,16,1,1,'海关总说明书','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B017','爱上单片机(第3版)',34.12,'小胡','9.jpg',0.9,100,'传智播客出版社','2011-01-09',344,16,1,1,'大家都说说这是为什么','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B018','中文版Photoshop CS4实例与操作',34.12,'老江','10.jpg',0.5,100,'传智播客出版社','2011-01-09',344,16,1,1,'海关总说明书','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B019','数据结构与算法分析（C++语言描述）（第2版）',34.12,'小胡','11.jpg',0.9,100,'传智播客出版社','2011-01-09',344,16,1,1,'大家都说说这是为什么','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');
INSERT INTO books VALUES('B020','计算机建模与仿真技术',34.12,'老江','12.jpg',0.5,100,'传智播客出版社','2011-01-09',344,16,1,1,'海关总说明书','第一章：计算机入门<br/>第二章：计算机管理','2009-09-08');

-- 以下是分类与图书关系数据
INSERT INTO booktype VALUES('B001','T001');
INSERT INTO booktype VALUES('B002','T002');
INSERT INTO booktype VALUES('B003','T003');
INSERT INTO booktype VALUES('B004','T001');
INSERT INTO booktype VALUES('B005','T002');
INSERT INTO booktype VALUES('B006','T003');
INSERT INTO booktype VALUES('B007','T002');
INSERT INTO booktype VALUES('B008','T001');
INSERT INTO booktype VALUES('B009','T001');
INSERT INTO booktype VALUES('B010','T001');
INSERT INTO booktype VALUES('B011','T001');
INSERT INTO booktype VALUES('B012','T001');
INSERT INTO booktype VALUES('B013','T001');
INSERT INTO booktype VALUES('B014','T001');
INSERT INTO booktype VALUES('B015','T001');
INSERT INTO booktype VALUES('B016','T001');
INSERT INTO booktype VALUES('B017','T001');
INSERT INTO booktype VALUES('B018','T001');
INSERT INTO booktype VALUES('B019','T001');
INSERT INTO booktype VALUES('B020','T001');
-- 以下是管理员信息
INSERT INTO admins(id,NAME,PASSWORD) VALUES('A001','eric','6046aa19f0c57731b4afe5647694b2bb');
INSERT INTO admins(id,NAME,PASSWORD) VALUES('A002','jacky','78f8c66288137831f4f7fc799e5b8a4d');
INSERT INTO admins(id,NAME,PASSWORD) VALUES('A003','rose','ff4407b06bd3896aa293937a1603770d');
INSERT INTO admins(id,NAME,PASSWORD) VALUES('A004','lixian',);

SELECT * FROM admins;


