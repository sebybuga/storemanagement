-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.3.0 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for storemanagement
DROP DATABASE IF EXISTS `storemanagement`;
CREATE DATABASE IF NOT EXISTS `storemanagement` /*!40100 DEFAULT CHARACTER SET utf32 COLLATE utf32_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `storemanagement`;

-- Dumping structure for table storemanagement.currency
DROP TABLE IF EXISTS `currency`;
CREATE TABLE IF NOT EXISTS `currency` (
  `id` int NOT NULL AUTO_INCREMENT,
  `currency` char(3) COLLATE utf32_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

-- Dumping data for table storemanagement.currency: ~0 rows (approximately)
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` (`id`, `currency`) VALUES
	(1, 'RON');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;

-- Dumping structure for table storemanagement.orders
DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `productId` bigint NOT NULL,
  `priceId` bigint DEFAULT NULL,
  `quantity` int NOT NULL,
  `orderDate` date NOT NULL,
  `createdBy` varchar(50) COLLATE utf32_bin DEFAULT NULL,
  `createdAt` date DEFAULT NULL,
  `updatedBy` varchar(50) CHARACTER SET utf32 COLLATE utf32_bin DEFAULT NULL,
  `updatedAt` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ORDER_PRODUCT_ID` (`productId`),
  KEY `FK_ORDER_PRICE_ID` (`priceId`),
  CONSTRAINT `FK_ORDER_PRICE_ID` FOREIGN KEY (`priceId`) REFERENCES `prices` (`id`),
  CONSTRAINT `FK_ORDER_PRODUCT_ID` FOREIGN KEY (`productId`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

-- Dumping data for table storemanagement.orders: ~0 rows (approximately)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dumping structure for table storemanagement.prices
DROP TABLE IF EXISTS `prices`;
CREATE TABLE IF NOT EXISTS `prices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `productId` bigint NOT NULL,
  `currencyId` int NOT NULL,
  `priceValue` decimal(10,4) NOT NULL,
  `priceDate` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRICE_PRODUCT_ID` (`productId`),
  KEY `FK_PRICE_CURRENCY_ID` (`currencyId`),
  CONSTRAINT `FK_PRICE_CURRENCY_ID` FOREIGN KEY (`currencyId`) REFERENCES `currency` (`id`),
  CONSTRAINT `FK_PRICE_PRODUCT_ID` FOREIGN KEY (`productId`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

-- Dumping data for table storemanagement.prices: ~0 rows (approximately)
/*!40000 ALTER TABLE `prices` DISABLE KEYS */;
INSERT INTO `prices` (`id`, `productId`, `currencyId`, `priceValue`, `priceDate`) VALUES
	(1, 1, 1, 4.0000, '2024-04-29 00:00:00'),
	(2, 2, 1, 13.3300, '2024-04-29 00:00:00');
/*!40000 ALTER TABLE `prices` ENABLE KEYS */;

-- Dumping structure for table storemanagement.products
DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf32_bin NOT NULL,
  `supplier` varchar(250) COLLATE utf32_bin NOT NULL,
  `description` varchar(500) COLLATE utf32_bin DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

-- Dumping data for table storemanagement.products: ~0 rows (approximately)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id`, `name`, `supplier`, `description`) VALUES
	(1, 'Dorna Izvorul Alb', 'Coca Cola Romania', 'Apa minerala naturala plata 2L'),
	(2, 'Santal Top', 'La Capsuleria', 'Suc de portocale 1.5L');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
