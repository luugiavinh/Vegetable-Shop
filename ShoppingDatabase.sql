USE [master]

DROP DATABASE IF EXISTS Shopping
GO

CREATE DATABASE Shopping
GO

use [Shopping]

CREATE TABLE tblRoles
(
	roleID varchar(50) NOT NULL primary key,
	roleName varchar(50) NOT NULL

)
GO

CREATE TABLE tblUsers
(
	userID varchar(50) NOT NULL primary key,
	fullName varchar(50) NOT NULL,
	password varchar(30) NOT NULL,
	roleID varchar(50) NOT NULL,
	address varchar(50) NOT NULL,
	birthday date NOT NULL,
	phone varchar(12) NOT NULL,
	email varchar(50) NOT NULL,
	status bit NOT NULL,
	CONSTRAINT fk_ROLE_ID FOREIGN KEY (roleID) REFERENCES tblRoles(roleID)

)
GO

CREATE TABLE tblOrder
(
	orderID varchar(50) NOT NULL primary key,
	orderDate date NOT NULL,
	total float NOT NULL,
	userID varchar(50) NOT NULL,
	status bit NOT NULL,
	CONSTRAINT fk_USER_ID FOREIGN KEY (userID) REFERENCES tblUsers(userID)
)
GO


CREATE TABLE tblCategory
(
	categoryID varchar(50) NOT NULL primary key,
	categoryName varchar(100) NOT NULL
)

GO

CREATE TABLE tblProduct
(
	productID varchar(50) NOT NULL primary key,
	productName varchar(50) NOT NULL,
	image varchar(100) NOT NULL,
	price float NOT NULL,
	quantity float NOT NULL,
	categoryID varchar(50) NOT NULL,
	importDate date NOT NULL,
	usingDate date NOT NULL,
	status bit NOT NULL,
	CONSTRAINT fk_CATEGORY_ID FOREIGN KEY (categoryID) REFERENCES tblCategory(categoryID)


)
GO


CREATE TABLE tblOrderDetail
(
	detailID varchar(50) NOT NULL primary key,
	price float NOT NULL,
	quantity int NOT NULL,
	orderID varchar(50) NOT NULL,
	productID varchar(50) NOT NULL,
	CONSTRAINT fk_ORDER_ID FOREIGN KEY (orderID) REFERENCES tblOrder(orderID),
	CONSTRAINT fk_PRODUCT_ID FOREIGN KEY (productID) REFERENCES tblProduct(productID)

)
GO

insert into tblRoles
values	('AD','ADMIN'),
		('US','USER')
GO

insert into tblUsers
values	('ID1','Nguyen Van Teo','1','AD','Hong Bang street','2002-10-20', '0901234567','asdd@gmail.com',1),
		('ID2','Pham Van An','123','US','Nguyen Khuyen street','2002-09-18', '0901334547','bcdd@gmail.com',1),
		('ID3','Nguyen Pham Tuan','123','US','Tran Hung Dao street','2002-05-08', '0901874556','vjfod@gmail.com',1)
GO

insert into tblCategory
values	('C1','Rau'),
		('C2','Cu Qua')
GO		

insert into tblProduct
values	('P01', 'Rau xa lach', 'images/rauxalach1.jpg', 30000, 5.5, 'C1', '2022-08-10','2022-09-10',1),
		('P02', 'Rau muong', 'images/raumuong1.jpg', 40000, 2.5, 'C1', '2022-08-9','2022-09-17',1),
		('P03', 'Rau chan vit', 'images/rauchanvit1.jpg', 50000, 6, 'C1', '2022-08-8','2022-09-16',1),
		('P04', 'Ca chua', 'images/cachua1.jpg', 25000, 7.5, 'C2', '2022-08-9','2022-09-11',1),
		('P05', 'Cu cai duong', 'images/cucai1.jpg', 35000, 5, 'C2', '2022-08-10','2022-09-15',1),
		('P06', 'Dua leo', 'images/dualeo1.jpg', 30000, 8, 'C2', '2022-08-11','2022-09-20',1)



GO


