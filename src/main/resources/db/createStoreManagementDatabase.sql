-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.3.0 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.1.0.6116
-- --------------------------------------------------------

-- Dumping database structure for storemanagement
CREATE DATABASE IF NOT EXISTS `storemanagement`  DEFAULT CHARACTER SET utf32 COLLATE utf32_bin  DEFAULT ENCRYPTION='N';
USE `storemanagement`;

-- Dumping structure for table storemanagement.currency
CREATE TABLE IF NOT EXISTS `currency` (
  `id` int NOT NULL,
  `currency` char(3) CHARACTER SET utf32 COLLATE utf32_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

-- Dumping data for table storemanagement.currency: ~2 rows (approximately)
INSERT INTO `currency` (`id`, `currency`) VALUES
	(0, 'RON'),
	(1, 'EUR');

-- Dumping structure for table storemanagement.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `orderDate` datetime DEFAULT NULL,
  `orderStatusId` int DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `createdBy` varchar(50) CHARACTER SET utf32 COLLATE utf32_bin DEFAULT NULL,
  `updatedBy` varchar(50) CHARACTER SET utf32 COLLATE utf32_bin DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

-- Dumping data for table storemanagement.orders: ~20 rows (approximately)
INSERT INTO `orders` (`id`, `orderDate`, `orderStatusId`, `createdAt`, `createdBy`, `updatedBy`, `updatedAt`) VALUES
	(1, '2024-05-06 09:00:00', 0, '2024-05-06 08:00:00', 'test6', NULL, NULL),
	(2, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test2', NULL, NULL),
	(3, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test3', NULL, NULL),
	(4, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test2', NULL, NULL),
	(5, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test2', NULL, NULL),
	(10, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test7', NULL, NULL),
	(11, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test7', NULL, NULL),
	(12, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test8', NULL, NULL),
	(13, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test8', NULL, NULL),
	(14, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test8', NULL, NULL),
	(15, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test8', NULL, NULL),
	(16, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test9', NULL, NULL),
	(17, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test9', NULL, NULL),
	(18, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test9', NULL, NULL),
	(19, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test9', 'test9', '2024-05-05 08:00:00'),
	(21, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test10', NULL, NULL),
	(22, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test10', NULL, NULL),
	(23, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test9', NULL, NULL),
	(26, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test10', NULL, NULL),
	(28, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test10', NULL, NULL),
	(29, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test10', 'test9', '2024-05-05 08:00:00');


-- Dumping structure for table storemanagement.products
CREATE TABLE IF NOT EXISTS `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(250) CHARACTER SET utf32 COLLATE utf32_bin NOT NULL,
  `supplier` varchar(250) CHARACTER SET utf32 COLLATE utf32_bin NOT NULL,
  `description` varchar(500) CHARACTER SET utf32 COLLATE utf32_bin DEFAULT '',
  `currencyId` int NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRODUCTS_CURRENCY_ID` (`currencyId`),
  CONSTRAINT `FK_PRODUCTS_CURRENCY_ID` FOREIGN KEY (`currencyId`) REFERENCES `currency` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

-- Dumping data for table storemanagement.products: ~3 rows (approximately)
INSERT INTO `products` (`id`, `name`, `supplier`, `description`, `currencyId`, `price`) VALUES
	(1, 'Dorna Izvorul Alb', 'Coca Cola Romania', 'Apa minerala naturala plata 2L', 0, 4.0),
	(2, 'Santal Top', 'La Capsuleria', 'Suc de portocale 1.5L', 0, 9.0),
	(3, 'Bere blonda Kozel Premium', 'Ursus Breweries', 'Sticla 15 x 0.66', 0, 149.0);



-- Dumping structure for table storemanagement.orders_products
CREATE TABLE IF NOT EXISTS `orders_products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `orderId` bigint NOT NULL,
  `productId` bigint NOT NULL,
  `quantity` double NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  `currencyId` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_ORDER_PRODUCT_ORDER_ID` (`orderId`) USING BTREE,
  KEY `FK_ORDER_PRODUCT_PRODUCT_ID` (`productId`) USING BTREE,
  KEY `FK_ORDERS_PRODUCTS_CURRENCY_ID` (`currencyId`),
  CONSTRAINT `FK_ORDERS_PRODUCTS_CURRENCY_ID` FOREIGN KEY (`currencyId`) REFERENCES `currency` (`id`),
  CONSTRAINT `FK_ORDERS_PRODUCTS_ORDER_ID` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_ORDERS_PRODUCTS_PRODUCT_ID` FOREIGN KEY (`productId`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

-- Dumping data for table storemanagement.orders_products: ~24 rows (approximately)
INSERT INTO `orders_products` (`id`, `orderId`, `productId`, `quantity`, `price`, `currencyId`) VALUES
	(1, 1, 1, 2, 1, 0),
	(2, 1, 2, 3, 1, 0),
	(3, 5, 1, 7, 1, 0),
	(4, 5, 2, 8, 1, 0),
	(17, 15, 1, 7, 1, 0),
	(18, 15, 2, 8, 1, 0),
	(19, 16, 1, 7, 1, 0),
	(20, 16, 2, 8, 1, 0),
	(21, 17, 1, 7, 1, 0),
	(22, 17, 2, 8, 1, 0),
	(29, 18, 1, 7, 1, 0),
	(30, 18, 2, 8, 1, 0),
	(37, 21, 1, 7, 1, 0),
	(38, 21, 2, 8, 1, 0),
	(39, 22, 1, 7, 1, 0),
	(40, 22, 2, 8, 1, 0),
	(43, 23, 1, 20, 1, 0),
	(44, 23, 2, 21, 1, 0),
	(59, 26, 1, 7, 1, 0),
	(60, 26, 2, 8, 1, 0),
	(65, 28, 1, 7, 1, 0),
	(66, 28, 2, 8, 1, 0),
	(67, 19, 1, 20, 1, 0),
	(68, 19, 2, 21, 1, 0),
	(71, 29, 1, 20, 4, 0),
	(72, 29, 2, 21, 9, 0);
