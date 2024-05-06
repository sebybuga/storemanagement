-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.3.0 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.1.0.6116
-- --------------------------------------------------------


-- Dumping database structure for storemanagement
CREATE DATABASE IF NOT EXISTS `storemanagement` DEFAULT CHARACTER SET utf32 COLLATE utf32_bin DEFAULT ENCRYPTION='N';
USE `storemanagement`;

-- Dumping structure for table storemanagement.currency
CREATE TABLE IF NOT EXISTS `currency` (
  `id` int NOT NULL AUTO_INCREMENT,
  `currency` char(3) COLLATE utf32_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

-- Dumping data for table storemanagement.currency: ~1 rows (approximately)
/* ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` (`id`, `currency`) VALUES
	(1, 'RON'),
	(2, 'EUR');
/* ALTER TABLE `currency` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

-- Dumping data for table storemanagement.orders: ~0 rows (approximately)
/* ALTER TABLE `orders` DISABLE KEYS */;
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
	(19, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test9', NULL, NULL),
	(21, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test10', NULL, NULL),
	(22, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test10', NULL, NULL),
	(23, '2024-05-05 09:00:00', 0, '2024-05-05 08:00:00', 'test9', NULL, NULL);
/* ALTER TABLE `orders` ENABLE KEYS */;

-- Dumping structure for table storemanagement.orders_products
CREATE TABLE IF NOT EXISTS `orders_products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `orderId` bigint NOT NULL,
  `productId` bigint NOT NULL,
  `quantity` double NOT NULL,
  `version` varchar(10) COLLATE utf32_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_ORDER_PRODUCT_ORDER_ID` (`orderId`) USING BTREE,
  KEY `FK_ORDER_PRODUCT_PRODUCT_ID` (`productId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

-- Dumping data for table storemanagement.orders_products: ~2 rows (approximately)
/* ALTER TABLE `orders_products` DISABLE KEYS */;
INSERT INTO `orders_products` (`id`, `orderId`, `productId`, `quantity`, `version`) VALUES
	(1, 1, 1, 2, ''),
	(2, 1, 2, 3, ''),
	(3, 5, 1, 7, ''),
	(4, 5, 2, 8, ''),
	(17, 15, 1, 7, ''),
	(18, 15, 2, 8, ''),
	(19, 16, 1, 7, ''),
	(20, 16, 2, 8, ''),
	(21, 17, 1, 7, ''),
	(22, 17, 2, 8, ''),
	(29, 18, 1, 7, ''),
	(30, 18, 2, 8, ''),
	(37, 21, 1, 7, '0'),
	(38, 21, 2, 8, '0'),
	(39, 22, 1, 7, '0'),
	(40, 22, 2, 8, '0'),
	(43, 23, 1, 20, '1'),
	(44, 23, 2, 21, '1');
/* ALTER TABLE `orders_products` ENABLE KEYS */;

-- Dumping structure for table storemanagement.products
CREATE TABLE IF NOT EXISTS `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf32_bin NOT NULL,
  `supplier` varchar(250) COLLATE utf32_bin NOT NULL,
  `description` varchar(500) COLLATE utf32_bin DEFAULT '',
  `currencyId` int NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

-- Dumping data for table storemanagement.products: ~2 rows (approximately)
/* ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id`, `name`, `supplier`, `description`, `currencyId`, `price`) VALUES
	(1, 'Dorna Izvorul Alb', 'Coca Cola Romania', 'Apa minerala naturala plata 2L', 0, 4),
	(2, 'Santal Top', 'La Capsuleria', 'Suc de portocale 1.5L', 0, 9),
	(3, 'Bere blonda Kozel Premium', 'Ursus Breweries', 'Sticla 15 x 0.66', 0, 149);
/* ALTER TABLE `products` ENABLE KEYS */;

/* SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/* SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/* SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/* SET SQL_NOTES=@OLD_SQL_NOTES */;
