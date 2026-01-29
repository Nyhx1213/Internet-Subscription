-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : jeu. 29 jan. 2026 à 13:16
-- Version du serveur : 11.8.3-MariaDB-0+deb13u1 from Debian
-- Version de PHP : 8.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `adjedjm_internet_subscriptions`
--

-- --------------------------------------------------------

--
-- Structure de la table `mcd_antiviruses`
--

CREATE TABLE `mcd_antiviruses` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_antiviruses`
--

INSERT INTO `mcd_antiviruses` (`id`, `name`, `comment`, `created_at`, `updated_at`) VALUES
(1, 'BitDefender', 'Please Switch my comment!', '2025-12-03 13:32:08', '2025-12-03 13:52:10'),
(2, 'Test', 'Un super antivirus !', '2025-12-03 13:33:14', '2025-12-03 13:33:14'),
(3, 'Test', 'Un super antivirus !', '2025-12-03 13:38:00', '2025-12-03 13:38:00');

--
-- Déclencheurs `mcd_antiviruses`
--
DELIMITER $$
CREATE TRIGGER `mcd_antiviruses_set_update_date` BEFORE UPDATE ON `mcd_antiviruses` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_authorization`
--

CREATE TABLE `mcd_authorization` (
  `id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_authorization`
--

INSERT INTO `mcd_authorization` (`id`, `code`, `description`, `comment`, `created_at`, `updated_at`) VALUES
(1, 'mn_parameters', 'Parameter menu, designed for a super administrator. Allows the manipulation of critical data, such as user data and rooms.', NULL, '2026-01-25 20:15:34', '2026-01-25 20:15:34'),
(2, 'mn_management', 'Management menu designed for a normal administrator. Allows the manipulation of subscriptions, accommodations, products etc...', NULL, '2026-01-25 20:15:34', '2026-01-25 20:15:34'),
(3, 'mi_rooms', 'Room manipulation menu.', NULL, '2026-01-25 20:15:34', '2026-01-25 20:15:34'),
(4, 'mi_users', 'User manipulation menu.', NULL, '2026-01-25 20:15:34', '2026-01-25 20:15:34'),
(5, 'mi_computer', 'Computer / System / AntiVirus manipulation menu.', NULL, '2026-01-25 20:15:34', '2026-01-25 20:15:34'),
(6, 'mi_paymentMeth', 'Payment method manipulation menu.', NULL, '2026-01-25 20:15:34', '2026-01-25 20:15:34'),
(7, 'mi_subscriptions', 'Internet subscription manipulation menu.', NULL, '2026-01-25 20:15:34', '2026-01-25 20:15:34'),
(8, 'mi_accommodations', 'Accommodation manipulation menu.', NULL, '2026-01-25 20:15:34', '2026-01-25 20:15:34');

--
-- Déclencheurs `mcd_authorization`
--
DELIMITER $$
CREATE TRIGGER `Authorization__set_update_date` BEFORE UPDATE ON `mcd_authorization` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_authorized`
--

CREATE TABLE `mcd_authorized` (
  `id_role` bigint(20) UNSIGNED NOT NULL,
  `id_auth` int(11) NOT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_authorized`
--

INSERT INTO `mcd_authorized` (`id_role`, `id_auth`, `comment`, `created_at`, `updated_at`) VALUES
(1, 1, NULL, '2026-01-25 20:16:58', '2026-01-25 20:16:58'),
(1, 2, NULL, '2026-01-25 20:16:58', '2026-01-25 20:16:58'),
(1, 3, NULL, '2026-01-25 20:16:58', '2026-01-25 20:16:58'),
(1, 4, NULL, '2026-01-25 20:16:58', '2026-01-25 20:16:58'),
(1, 5, NULL, '2026-01-25 20:16:58', '2026-01-25 20:16:58'),
(1, 6, NULL, '2026-01-25 20:16:58', '2026-01-25 20:16:58'),
(1, 7, NULL, '2026-01-25 20:16:58', '2026-01-25 20:16:58'),
(1, 8, NULL, '2026-01-25 20:16:58', '2026-01-25 20:16:58'),
(3, 2, NULL, '2026-01-25 20:16:58', '2026-01-25 20:16:58'),
(3, 7, NULL, '2026-01-25 20:16:58', '2026-01-25 20:16:58'),
(3, 8, NULL, '2026-01-25 20:16:58', '2026-01-25 20:16:58');

--
-- Déclencheurs `mcd_authorized`
--
DELIMITER $$
CREATE TRIGGER `Authorized_set_update_date` BEFORE UPDATE ON `mcd_authorized` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_buy`
--

CREATE TABLE `mcd_buy` (
  `id_subscription` bigint(20) UNSIGNED NOT NULL,
  `id_product` bigint(20) UNSIGNED NOT NULL,
  `quantity` float DEFAULT NULL,
  `price` decimal(19,4) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_buy`
--

INSERT INTO `mcd_buy` (`id_subscription`, `id_product`, `quantity`, `price`, `comment`, `created_at`, `updated_at`) VALUES
(21, 5, 1, 100.0000, 'test', '2026-01-21 11:00:01', '2026-01-21 11:00:01'),
(21, 6, 1, 100.0000, 'test', '2026-01-21 11:00:08', '2026-01-21 11:00:08');

-- --------------------------------------------------------

--
-- Structure de la table `mcd_computers`
--

CREATE TABLE `mcd_computers` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp(),
  `id_antivirus` bigint(20) UNSIGNED DEFAULT NULL,
  `id_subscription` bigint(20) UNSIGNED NOT NULL,
  `id_system` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_computers`
--

INSERT INTO `mcd_computers` (`id`, `name`, `comment`, `created_at`, `updated_at`, `id_antivirus`, `id_subscription`, `id_system`) VALUES
(1, NULL, NULL, '2026-01-07 15:26:38', '2026-01-07 15:26:38', 1, 21, 1),
(2, NULL, NULL, '2026-01-07 15:26:38', '2026-01-07 15:26:38', 1, 22, 1),
(3, NULL, NULL, '2026-01-07 15:26:38', '2026-01-07 15:26:38', 1, 23, 1),
(4, NULL, NULL, '2026-01-07 15:26:38', '2026-01-07 15:26:38', 1, 24, 1),
(5, NULL, NULL, '2026-01-07 15:26:38', '2026-01-07 15:26:38', 1, 25, 1),
(6, NULL, NULL, '2026-01-07 15:26:38', '2026-01-07 15:26:38', 1, 26, 1),
(7, NULL, NULL, '2026-01-07 15:26:38', '2026-01-07 15:26:38', 1, 27, 1),
(8, NULL, NULL, '2026-01-07 15:26:38', '2026-01-07 15:26:38', 1, 28, 1),
(9, NULL, NULL, '2026-01-07 15:26:38', '2026-01-07 15:26:38', 1, 29, 1),
(10, NULL, NULL, '2026-01-07 15:26:38', '2026-01-07 15:26:38', 1, 30, 1),
(31, NULL, NULL, '2026-01-07 15:27:42', '2026-01-07 15:27:42', 1, 31, 3),
(32, NULL, NULL, '2026-01-07 15:27:42', '2026-01-07 15:27:42', 1, 32, 3),
(33, NULL, NULL, '2026-01-07 15:27:42', '2026-01-07 15:27:42', 1, 33, 3),
(34, NULL, NULL, '2026-01-07 15:27:42', '2026-01-07 15:27:42', 1, 34, 3),
(35, NULL, NULL, '2026-01-07 15:27:42', '2026-01-07 15:27:42', 1, 35, 3),
(36, NULL, NULL, '2026-01-07 15:27:42', '2026-01-07 15:27:42', 1, 36, 3),
(37, NULL, NULL, '2026-01-07 15:27:42', '2026-01-07 15:27:42', 1, 37, 3),
(38, NULL, NULL, '2026-01-07 15:27:42', '2026-01-07 15:27:42', 1, 38, 3),
(39, NULL, NULL, '2026-01-07 15:27:42', '2026-01-07 15:27:42', 1, 39, 3),
(40, 'null', 'Please Switch my comment!', '2026-01-07 15:27:42', '2026-01-14 14:00:11', 1, 40, 3);

--
-- Déclencheurs `mcd_computers`
--
DELIMITER $$
CREATE TRIGGER `mcd_computers_set_update_date` BEFORE UPDATE ON `mcd_computers` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_exist`
--

CREATE TABLE `mcd_exist` (
  `id_antivirus` bigint(20) UNSIGNED NOT NULL,
  `id_system` bigint(20) UNSIGNED NOT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_exist`
--

INSERT INTO `mcd_exist` (`id_antivirus`, `id_system`, `comment`, `created_at`, `updated_at`) VALUES
(1, 1, 'test', '2026-01-21 10:37:50', '2026-01-21 10:37:50'),
(1, 3, 'Please Switch my comment!', '2026-01-21 10:37:34', '2026-01-21 10:38:35');

--
-- Déclencheurs `mcd_exist`
--
DELIMITER $$
CREATE TRIGGER `mcd_exist_set_update_date` BEFORE UPDATE ON `mcd_exist` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_methods`
--

CREATE TABLE `mcd_methods` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_methods`
--

INSERT INTO `mcd_methods` (`id`, `code`, `name`, `comment`, `created_at`, `updated_at`) VALUES
(1, 'MSC', 'MasterCard', NULL, '2026-01-07 15:06:19', '2026-01-07 15:06:19'),
(2, 'LIQ', 'Liquid', NULL, '2026-01-07 15:06:19', '2026-01-07 15:06:19'),
(3, 'PPL', 'Paypal', NULL, '2026-01-07 15:06:19', '2026-01-07 15:06:19'),
(4, 'VRM', 'Virement', 'Please Switch my comment!', '2026-01-07 15:06:19', '2026-01-14 13:47:34');

--
-- Déclencheurs `mcd_methods`
--
DELIMITER $$
CREATE TRIGGER `mcd_methods_set_update_date` BEFORE UPDATE ON `mcd_methods` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_outlets`
--

CREATE TABLE `mcd_outlets` (
  `code` varchar(10) NOT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp(),
  `code_room` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_outlets`
--

INSERT INTO `mcd_outlets` (`code`, `comment`, `created_at`, `updated_at`, `code_room`) VALUES
('C1', NULL, '2026-01-07 14:37:04', '2026-01-07 14:37:04', 'fu_01'),
('C10', NULL, '2026-01-07 14:37:31', '2026-01-07 14:37:31', 'fu_10'),
('C11', NULL, '2026-01-07 14:37:31', '2026-01-07 14:37:31', 'fu_11'),
('C12', NULL, '2026-01-07 14:37:31', '2026-01-07 14:37:31', 'fu_12'),
('C13', NULL, '2026-01-07 14:37:31', '2026-01-07 14:37:31', 'fu_13'),
('C14', NULL, '2026-01-07 14:37:31', '2026-01-07 14:37:31', 'fu_14'),
('C15', NULL, '2026-01-07 14:37:31', '2026-01-07 14:37:31', 'fu_15'),
('C16', NULL, '2026-01-07 14:37:31', '2026-01-07 14:37:31', 'fu_16'),
('C17', NULL, '2026-01-07 14:37:31', '2026-01-07 14:37:31', 'fu_17'),
('C18', NULL, '2026-01-07 14:37:31', '2026-01-07 14:37:31', 'fu_18'),
('C19', NULL, '2026-01-07 14:37:31', '2026-01-07 14:37:31', 'fu_19'),
('C2', NULL, '2026-01-07 14:37:04', '2026-01-07 14:37:04', 'fu_02'),
('C21', '', '2026-01-14 17:55:50', '2026-01-14 17:55:50', 'fu_01'),
('C3', NULL, '2026-01-07 14:37:04', '2026-01-07 14:37:04', 'fu_03'),
('C4', NULL, '2026-01-07 14:37:04', '2026-01-07 14:37:04', 'fu_04'),
('C5', NULL, '2026-01-07 14:37:04', '2026-01-07 14:37:04', 'fu_05'),
('C6', NULL, '2026-01-07 14:37:04', '2026-01-07 14:37:04', 'fu_06'),
('C7', NULL, '2026-01-07 14:37:04', '2026-01-07 14:37:04', 'fu_07'),
('C8', NULL, '2026-01-07 14:37:04', '2026-01-07 14:37:04', 'fu_08'),
('C9', NULL, '2026-01-07 14:37:04', '2026-01-07 14:37:04', 'fu_09'),
('D21', '', '2026-01-14 17:54:38', '2026-01-14 17:54:38', 'fu_01');

--
-- Déclencheurs `mcd_outlets`
--
DELIMITER $$
CREATE TRIGGER `mcd_outlets_set_update_date` BEFORE UPDATE ON `mcd_outlets` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_payment`
--

CREATE TABLE `mcd_payment` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `date_payment` datetime DEFAULT NULL,
  `amount` decimal(19,4) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp(),
  `id_method` bigint(20) UNSIGNED NOT NULL,
  `id_subscription` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_payment`
--

INSERT INTO `mcd_payment` (`id`, `date_payment`, `amount`, `comment`, `created_at`, `updated_at`, `id_method`, `id_subscription`) VALUES
(21, '2026-01-01 15:58:15', 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-28 15:58:19', 1, 21),
(22, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 22),
(23, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 23),
(24, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 24),
(25, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 25),
(26, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 26),
(27, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 27),
(28, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 28),
(29, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 29),
(30, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 30),
(31, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 31),
(32, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 32),
(33, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 33),
(34, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 34),
(35, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 35),
(36, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 36),
(37, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 37),
(38, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 38),
(39, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 39),
(40, NULL, 27.0000, NULL, '2026-01-07 15:32:51', '2026-01-07 15:32:51', 1, 40);

--
-- Déclencheurs `mcd_payment`
--
DELIMITER $$
CREATE TRIGGER `mcd_payment_set_update_date` BEFORE UPDATE ON `mcd_payment` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_products`
--

CREATE TABLE `mcd_products` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `code` varchar(10) NOT NULL,
  `designation` varchar(150) DEFAULT NULL,
  `price` decimal(19,4) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_products`
--

INSERT INTO `mcd_products` (`id`, `code`, `designation`, `price`, `comment`, `created_at`, `updated_at`) VALUES
(5, 'B', 'Brosse', 100.0000, 'check', '2025-12-11 17:50:29', '2026-01-21 08:54:54'),
(6, 'as', 'Brosse', 5.0000, 'Une borsse quoi', '2025-12-11 17:53:22', '2025-12-11 17:53:22');

--
-- Déclencheurs `mcd_products`
--
DELIMITER $$
CREATE TRIGGER `mcd_products_set_update_date` BEFORE UPDATE ON `mcd_products` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_rentings`
--

CREATE TABLE `mcd_rentings` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `date_begin` date NOT NULL,
  `date_end` date DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp(),
  `id_user` bigint(20) UNSIGNED NOT NULL,
  `code_room` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_rentings`
--

INSERT INTO `mcd_rentings` (`id`, `date_begin`, `date_end`, `comment`, `created_at`, `updated_at`, `id_user`, `code_room`) VALUES
(101, '2028-01-06', '2028-01-06', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 1, 'fu_01'),
(102, '2027-05-15', '2027-05-15', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 2, 'fu_02'),
(103, '2026-10-29', '2026-10-29', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 3, 'fu_03'),
(104, '2026-01-22', '2026-01-22', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 4, 'fu_04'),
(105, '2027-09-23', '2027-09-23', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 55, 'fu_05'),
(106, '2026-01-21', '2026-01-21', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 56, 'fu_06'),
(107, '2027-11-16', '2027-11-16', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 57, 'fu_07'),
(108, '2026-06-17', '2026-06-17', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 58, 'fu_08'),
(109, '2026-12-13', '2026-12-13', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 59, 'fu_09'),
(110, '2026-11-28', '2026-11-28', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 60, 'fu_10'),
(111, '2026-12-19', '2026-12-19', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 61, 'fu_11'),
(112, '2027-03-24', '2027-03-24', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 62, 'fu_12'),
(113, '2027-04-11', '2027-04-11', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 63, 'fu_13'),
(114, '2027-03-15', '2027-03-15', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 64, 'fu_14'),
(115, '2027-04-20', '2027-04-20', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 65, 'fu_15'),
(116, '2027-02-21', '2027-02-21', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 66, 'fu_16'),
(117, '2026-04-11', '2026-04-11', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 67, 'fu_17'),
(118, '2027-09-26', '2027-09-26', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 115, 'fu_18'),
(119, '2027-10-27', '2027-10-27', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 116, 'fu_19'),
(120, '2026-09-10', '2026-09-10', NULL, '2026-01-07 14:55:12', '2026-01-07 14:55:12', 117, 'fu_20');

--
-- Déclencheurs `mcd_rentings`
--
DELIMITER $$
CREATE TRIGGER `mcd_rentings_set_update_date` BEFORE UPDATE ON `mcd_rentings` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_roles`
--

CREATE TABLE `mcd_roles` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `comment` varchar(250) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_roles`
--

INSERT INTO `mcd_roles` (`id`, `code`, `name`, `comment`, `created_at`, `updated_at`) VALUES
(1, 'SUP', 'Super-Admin', '', '2025-12-03 15:36:52', '2026-01-07 08:47:04'),
(3, 'ADM', 'Administrator', '', '2025-12-03 15:37:36', '2026-01-07 08:47:06'),
(7, 'RES', 'Resident', '', '2026-01-07 08:45:58', '2026-01-07 08:45:58');

--
-- Déclencheurs `mcd_roles`
--
DELIMITER $$
CREATE TRIGGER `mcd_roles_set_update_date` BEFORE UPDATE ON `mcd_roles` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_rooms`
--

CREATE TABLE `mcd_rooms` (
  `code` varchar(10) NOT NULL,
  `capacity` tinyint(4) NOT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_rooms`
--

INSERT INTO `mcd_rooms` (`code`, `capacity`, `comment`, `created_at`, `updated_at`) VALUES
('fu_01', 1, '', '2025-12-03 15:59:37', '2025-12-03 15:59:37'),
('fu_02', 1, 'Please Switch my comment!', '2025-12-03 16:02:01', '2025-12-03 16:02:30'),
('fu_03', 1, '', '2025-12-03 16:02:06', '2025-12-03 16:02:06'),
('fu_04', 1, NULL, '2026-01-07 14:30:28', '2026-01-07 14:30:28'),
('fu_05', 1, NULL, '2026-01-07 14:30:28', '2026-01-07 14:30:28'),
('fu_06', 1, NULL, '2026-01-07 14:30:28', '2026-01-07 14:30:28'),
('fu_07', 1, NULL, '2026-01-07 14:30:28', '2026-01-07 14:30:28'),
('fu_08', 1, NULL, '2026-01-07 14:30:28', '2026-01-07 14:30:28'),
('fu_09', 1, NULL, '2026-01-07 14:30:28', '2026-01-07 14:30:28'),
('fu_10', 1, NULL, '2026-01-07 14:31:10', '2026-01-07 14:31:10'),
('fu_11', 1, NULL, '2026-01-07 14:31:10', '2026-01-07 14:31:10'),
('fu_12', 1, NULL, '2026-01-07 14:31:10', '2026-01-07 14:31:10'),
('fu_13', 1, NULL, '2026-01-07 14:31:10', '2026-01-07 14:31:10'),
('fu_14', 1, NULL, '2026-01-07 14:31:10', '2026-01-07 14:31:10'),
('fu_15', 1, NULL, '2026-01-07 14:31:10', '2026-01-07 14:31:10'),
('fu_16', 1, NULL, '2026-01-07 14:31:10', '2026-01-07 14:31:10'),
('fu_17', 1, NULL, '2026-01-07 14:31:10', '2026-01-07 14:31:10'),
('fu_18', 1, NULL, '2026-01-07 14:31:10', '2026-01-07 14:31:10'),
('fu_19', 1, NULL, '2026-01-07 14:31:10', '2026-01-07 14:31:10'),
('fu_20', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_21', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_22', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_23', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_24', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_25', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_26', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_27', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_28', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_29', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_30', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_31', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_32', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_33', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_34', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_35', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_36', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_37', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_38', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21'),
('fu_39', 1, NULL, '2026-01-07 14:31:21', '2026-01-07 14:31:21');

--
-- Déclencheurs `mcd_rooms`
--
DELIMITER $$
CREATE TRIGGER `mcd_rooms_set_update_date` BEFORE UPDATE ON `mcd_rooms` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_subscriptions`
--

CREATE TABLE `mcd_subscriptions` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `date_begin` date NOT NULL DEFAULT current_timestamp(),
  `date_end` date DEFAULT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(250) NOT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp(),
  `code_outlet` varchar(10) DEFAULT NULL,
  `id_user` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_subscriptions`
--

INSERT INTO `mcd_subscriptions` (`id`, `date_begin`, `date_end`, `login`, `password`, `comment`, `created_at`, `updated_at`, `code_outlet`, `id_user`) VALUES
(21, '2027-11-29', '2027-11-29', 'Pé.Chauvin', '$2y$12$hn8lPVP0NDNZsa6S6Sk.B.3dP3TLrdAf4wHBpZiSHEbDqC76xofPu', 'bJYFp56zBhtQ', '2026-01-07 15:01:19', '2026-01-28 11:27:37', 'C10', 1),
(22, '2026-10-09', '2026-10-09', 'Ren.Muller', '$2y$12$K/4P8/qDLjgQZUJzanWOeedZl6AaIA6t1E.JUfbYPfsYpds6TopDW', 'MusElY2o71Wa', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 2),
(23, '2027-01-25', '2027-01-25', 'Ant.Potier', '$2y$12$EZsB7oO/9houBLDc1wkBSeYe6EVm6Y/QW.4Y/nmT4mcH6tuyhFnye', 'QBX4yDAcgdTN', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 3),
(24, '2026-12-29', '2026-12-29', 'Lor.Bernier', '$2y$12$fcCHswc3wtfO5vIb51GJiOsoi3KOtOCQWGMpC5IUL1WVlIkAtMTOa', 'RJObWBFQeXlV', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 4),
(25, '2027-07-08', '2027-07-08', 'Ast.Costa', '$2y$12$Kd8GT8ehwSNZ.4WhBS1f2eLVCJ7b0wT.WLP4s25UgX2HiA/C9L/JC', 'L98SfX7h6aIy', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 55),
(26, '2027-11-23', '2027-11-23', 'Col.Poirier', '$2y$12$YlF9ZuQhmoeZ33RNf1bgn.Aal9wB0oMIEUhmiUQ8zj/g6.R2DBWuy', '8IOFf9D7WmUY', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 56),
(27, '2027-01-30', '2027-01-30', 'Ber.Toussaint', '$2y$12$viST.AhPWKx9Di6UcXD1u.EWXmdMTAbfvu.73Xm6JUrE6E1ZZ23v.', 'bg1FAt7qLEP9', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 57),
(28, '2026-04-25', '2026-04-25', 'Mon.Leblanc', '$2y$12$DoNNpLB6zqiufgm891.MjOhIJUkuZJdyBp/lURuOzAjTTHbjK6rlO', 'bLTo2z3RcmYu', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 58),
(29, '2026-05-10', '2026-05-10', 'Jos.Poulain', '$2y$12$UfJ6G6pl9N/Axvy4AqIV7eh2y7DQVNlLqn75QwY9og4cg6TCXPgQi', 'lt8FNJ9HsPMA', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 59),
(30, '2026-05-27', '2026-05-27', 'Jea.Leger', '$2y$12$3nq1OfDPbhrKVC2y4Dt/KezBVMxmYTSNUq.mBn/EhOSE5Zo.Yppjq', 'cxHaIX2AB80y', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 60),
(31, '2026-01-21', '2026-01-21', 'Rob.Roche', '$2y$12$mQnpGHF5T4xD6NhOeF/ACOcqGY.l92EnPnVub1aai/iM9mRu2FnAi', 'MTkuUgS4YoiG', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 61),
(32, '2026-06-12', '2026-06-12', 'Den.Blondel', '$2y$12$yUmaQRaj5qS6wFxAfIG8KuA7UcKCmAa4ifosKkd8t9HREXIVc.BpC', 'odkBHTcV5vaz', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 62),
(33, '2026-04-22', '2026-04-22', 'Ren.Allard', '$2y$12$HxqtDVj7foTivzElBimRmeBQY0mW9BKybm.Ic3FB.hRfi7aSBv60.', 'G8KOCqnmB9L5', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 63),
(34, '2027-06-04', '2027-06-04', 'Pau.Barbier', '$2y$12$gexe.yWfdanT05zoCAQAcuAf6u7m.Jn5qKL5MQmaxN.O/SprU0/yS', 'GHscUzJ6R1We', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 64),
(35, '2026-05-18', '2026-05-18', 'Jul.Legros', '$2y$12$ta40T.3W1wT7q78ZAYY97uBe2icJsjIAm6oV.iNWfj2pb2cUh2kt.', 'JELsrZftuTzN', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 65),
(36, '2027-08-05', '2027-08-05', 'Mar.Perrot', '$2y$12$hhgf2muyzSxUkAYIpP7zqOPpQiNTAchymLYdS3JUmh0htoM6MKZwe', '0Qp2Hcw1bC7I', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 66),
(37, '2026-09-12', '2026-09-12', 'Aur.Delattre', '$2y$12$B3R/Zj.emImaXalV2PsUBOj/zzybn1fpB.kIXvgu2Md0Uz1nvd0I6', 'Kelm9YkijURy', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 67),
(38, '2026-05-02', '2026-05-02', 'Eug.Legrand', '$2y$12$65cyqM5HOuhRiOa2.mttDOXf6iOTbwTTdY0ow2PyJj9tUsjW2Rvo6', 'miQkhWyeOn5L', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 115),
(39, '2027-12-14', '2027-12-14', 'Mau.Marchand', '$2y$12$f3Ta/NjnEl8sMNN58x8LNutbtc5XrgnoQi5gUMJ8QCvdMqZeQPvze', 'lbIPwd5oht0Q', '2026-01-07 15:01:19', '2026-01-07 15:01:19', 'C1', 116),
(40, '2026-09-17', '2026-09-17', 'Car.De Oliveira', '$2y$12$0kICoHI7Be92iRous7KReu5MqG5/RURMH.aOTtgZWqQLueE1ZcILm', 'Please Switch my comment!', '2026-01-07 15:01:19', '2026-01-14 16:47:56', 'C1', 117);

--
-- Déclencheurs `mcd_subscriptions`
--
DELIMITER $$
CREATE TRIGGER `mcd_subscriptions_set_update_date` BEFORE UPDATE ON `mcd_subscriptions` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_systems`
--

CREATE TABLE `mcd_systems` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `version` varchar(50) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_systems`
--

INSERT INTO `mcd_systems` (`id`, `name`, `version`, `comment`, `created_at`, `updated_at`) VALUES
(1, 'Linux', '01', 'Please Switch my comment!', '2025-12-03 17:32:28', '2025-12-03 17:34:09'),
(3, 'Windows', '01', 'Un super System', '2025-12-03 17:35:53', '2025-12-03 17:35:53'),
(4, 'MAC', '01', NULL, '2026-01-07 10:24:16', '2026-01-07 10:24:16');

--
-- Déclencheurs `mcd_systems`
--
DELIMITER $$
CREATE TRIGGER `mcd_systems_set_update_date` BEFORE UPDATE ON `mcd_systems` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `mcd_users`
--

CREATE TABLE `mcd_users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `email_verified_at` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `remember_token` varchar(100) DEFAULT NULL,
  `comment` varchar(250) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp(),
  `id_role` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mcd_users`
--

INSERT INTO `mcd_users` (`id`, `first_name`, `last_name`, `name`, `email`, `email_verified_at`, `password`, `remember_token`, `comment`, `created_at`, `updated_at`, `id_role`) VALUES
(1, 'Pénélope', 'Chauvin', 'Pé.Chauvin', 'Pé.Chauvin@example.org', NULL, '$2y$12$xDTOmX39zLKJTPUBpNuHFOaYqydUMJDiY55gPw1EFW5It5h.7kMlK', NULL, 'DIyu9Y>Yy', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(2, 'René', 'Muller', 'Ren.Muller', 'Ren.Muller@example.org', NULL, '$2y$12$5Enwm/5oOHeU30wUJeBPhOddp2d9Bax7knEAmI4XFeZ1IyjJ43ljS', NULL, '|(MOBe', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(3, 'Antoinette', 'Potier', 'Ant.Potier', 'Ant.Potier@example.org', NULL, '$2y$12$1GkvYIAoJpmbSwKk.bhbtOlKOxMnkdwFZYyljTGCoWcfRPKX7K8O2', NULL, 'Cw73fyGvp94W', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(4, 'Lorraine', 'Bernier', 'Lor.Bernier', 'Lor.Bernier@example.org', NULL, '$2y$12$GL2/Cw2/rj.mIHj5lsMkqu7MocCKPPt9QBSWjDwfegDeQWwUgB06m', NULL, 'OgX1iU2k65pL', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(55, 'Astrid', 'Costa', 'Ast.Costa', 'Ast.Costa@example.org', NULL, '$2y$12$uSQp8WSlRldgk.KwyG4nr.RiHK8wAfqczPuji6/klz/a.MIQpt386', NULL, 'uIQnrTCkOf8s', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(56, 'Colette', 'Poirier', 'Col.Poirier', 'Col.Poirier@example.org', NULL, '$2y$12$GyxuKsYHBzM4dQStrSPCMO6H5MzRv1PuXThoDymtGXS34w7ate1Ya', NULL, 'UTaVJLE1z27M', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(57, 'Bertrand', 'Toussaint', 'Ber.Toussaint', 'Ber.Toussaint@example.org', NULL, '$2y$12$Ihvu7P9/mFDGoE0pdR/kmujSPWF.LYhHoG5GSB8t3YO0CJN3S512e', NULL, 'qPX0Gfwc4ngl', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(58, 'Monique', 'Leblanc', 'Mon.Leblanc', 'Mon.Leblanc@example.org', NULL, '$2y$12$2wDj8iiQ/zORu.HxZO5ATeWmlPq3pKECm3u2kllUfq4rqeVfpm2BG', NULL, 'In24CTopBEvu', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(59, 'Joseph', 'Poulain', 'Jos.Poulain', 'Jos.Poulain@example.org', NULL, '$2y$12$NPwlZpOGoN.TuGZZIrc/JOq3ipngA20b5oLNaF/kLA6n222bXZoE2', NULL, 'wHdFDthQuzTv', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(60, 'Jean', 'Leger', 'Jea.Leger', 'Jea.Leger@example.org', NULL, '$2y$12$iR3CPqMe45xzYVtzip3nke/.OH40RmNeIGHSKHJ6A9jQ9bLfcQHwm', NULL, 'ZMThzDLGJlr9', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(61, 'Robert', 'Roche', 'Rob.Roche', 'Rob.Roche@example.org', NULL, '$2y$12$wtQWpA3anAv7moaTBC/WFObMnIqK0jQ05M8fQt9hrtxwyWiwdrRxC', NULL, 'Ri8rG2QyDh4L', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(62, 'Denis', 'Blondel', 'Den.Blondel', 'Den.Blondel@example.org', NULL, '$2y$12$g06qhiuif.dcHZAVErdWV.MUsdYCHm18ZHahTyjh9oMgYI05BxrLW', NULL, '4hzVPD9NlL2i', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(63, 'Renée', 'Allard', 'Ren.Allard', 'Ren.Allard@example.org', NULL, '$2y$12$nNrHhcCyPb89R5kkM2E9zuKMlhxXIemhsDwjdBTSTESlj4EC6S8aq', NULL, 'zwSXgADNUoPb', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(64, 'Pauline', 'Barbier', 'Pau.Barbier', 'Pau.Barbier@example.org', NULL, '$2y$12$BHdzcAoYz/LEwil4N53jxOk55fL149IGRPVWbrXOQpQ0n42068uzu', NULL, 'lchCsSm2bikr', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(65, 'Julien', 'Legros', 'Jul.Legros', 'Jul.Legros@example.org', NULL, '$2y$12$tkIfBIMJqC10fKoyiIKsfeHlLpDpD/U6nNvalhC9/y3cMfk7WPSK6', NULL, 'QdcBSEYxnqyv', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(66, 'Marine', 'Perrot', 'Mar.Perrot', 'Mar.Perrot@example.org', NULL, '$2y$12$Sdfz9eVz4LWEUNh1HEPHy.Jsx7jsazUqDWrB3f0CeTOAakf7UNZn2', NULL, 'QnA4e0ND1Rld', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(67, 'Aurore', 'Delattre', 'Aur.Delattre', 'Aur.Delattre@example.org', NULL, '$2y$12$SEezszFvzQBU6bUZiV30mejht8skyW93f7BNANyI7X78vXudL419y', NULL, '06CeFI82zVpj', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(115, 'Eugène', 'Legrand', 'Eug.Legrand', 'Eug.Legrand@example.org', NULL, '$2y$12$8nCeUm1orHt7JSoF.irnR.bAYJKNloOM2EjCoskAffM3K.adgWuza', NULL, 'QHB7ysUwiaFb', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(116, 'Maurice', 'Marchand', 'Mau.Marchand', 'Mau.Marchand@example.org', NULL, '$2y$12$wJiszpnQcydMYHRL1hnqw.KvFp5snd.vnJM5uUfTY9B5a6bh5A/2S', NULL, 'iwDrl0RtvbUm', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(117, 'Caroline', 'De Oliveira', 'Car.De Oliveira', 'Car.De Oliveira@example.org', NULL, '$2y$12$3kLXDG..o17M8swlFKS3NuDDch721gapb06qaDFs7cglngYzs/qUS', NULL, 'VTjbBELi3hAg', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(118, 'Benoît', 'Boyer', 'Ben.Boyer', 'Ben.Boyer@example.org', NULL, '$2y$12$VpQcG0vgp0ial3wgyMl6CeYxJN44cqfnGbeoPhKT8OkuB3roAbv3K', NULL, 'i85rgPkZcqJ0', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(119, 'Martin', 'Dupuy', 'Mar.Dupuy', 'Mar.Dupuy@example.org', NULL, '$2y$12$LWHB0r/s6xrEjokReI2Joeb9MEIujM3qzn1N.7PSEKSkdz8Qg7hdC', NULL, 'IwB6L4FRilK7', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(120, 'Constance', 'Leveque', 'Con.Leveque', 'Con.Leveque@example.org', NULL, '$2y$12$UR6nLXJdljBlvtJ25Mx39edxh5pwfMktZ2ctR9KFk662YdK0a./T.', NULL, '9khWTcYJLDxN', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(121, 'Pierre', 'Fontaine', 'Pie.Fontaine', 'Pie.Fontaine@example.org', NULL, '$2y$12$ZU0d6MXDbgDpg9wqd/96W.GCy4rkaB8fmDZURkUC4SXUroOC/95yK', NULL, 'w1NRnZ0ahJXu', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(122, 'Jérôme', 'Delannoy', 'Jé.Delannoy', 'Jé.Delannoy@example.org', NULL, '$2y$12$u2VaA3YXeYapx1GSTb.umuNoocpQJj6Af0weOdYSV04V5Ilh43IXK', NULL, 'Z7z8Sd3GtvIk', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(123, 'Margaret', 'Marin', 'Mar.Marin', 'Mar.Marin@example.org', NULL, '$2y$12$zffxqv03hzmmXPM.wkYh3.JsL3DtGieMQ8tAmwk6Rm2/Ubn4U1QUG', NULL, 'IE4SHnYNgOja', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(124, 'Julien', 'Hamon', 'Jul.Hamon', 'Jul.Hamon@example.org', NULL, '$2y$12$SwvE/azPfczu09zr41U1/uNFWc07XXc0S1ureJjxeonCefBRIE.i2', NULL, 'r9phN2eV8MUc', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(125, 'Richard', 'Guyon', 'Ric.Guyon', 'Ric.Guyon@example.org', NULL, '$2y$12$fCL3lLSLLOHpzLO7X8FGkO6YkfThL1a595JwlKZSpi./pcIOLGZvq', NULL, 'VEz1oUHFycx8', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(147, 'Michele', 'Turpin', 'Mic.Turpin', 'Mic.Turpin@example.org', NULL, '$2y$12$AZpyFe72rk2oYD5R2TJFZuKdS7UqQwWY.m/2tXDwSdwgMBlmYQcj2', NULL, 'uPekilXvMQEh', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(148, 'Lorraine', 'Brun', 'Lor.Brun', 'Lor.Brun@example.org', NULL, '$2y$12$6IXH8F2jZq9Hxfwiaglk2eBonC.jbxAjt2KDjj61aCbYa2YtErD7G', NULL, 'HQwio9014knh', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(149, 'Eleonore', 'Da Silva', 'Ele.Da Silva', 'Ele.Da Silva@example.org', NULL, '$2y$12$B3Sb20pn0cTa2ESZPZ5pkO8PxNGUqVZGcOO7gYfhxfgyQYeP.b9mG', NULL, 'gnE6qZptKlSo', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(150, 'Nicolas', 'Barre', 'Nic.Barre', 'Nic.Barre@example.org', NULL, '$2y$12$tXQZIBGM.mH0LApo.wRStOTzACGdOygl3wMiACn96lzI3.uPMfubS', NULL, 'AHpV9xi1oghD', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(151, 'Renee', 'Richard', 'Ren.Richard', 'Ren.Richard@example.org', NULL, '$2y$12$OhbMC85La2i5lmZeYMNYCuEyOWluM8Io1Zo86T6I6wf739Mlm1me6', NULL, 'm2sWSv4ixjp7', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(152, 'Henri', 'Bruneau', 'Hen.Bruneau', 'Hen.Bruneau@example.org', NULL, '$2y$12$ON6SZy3Usq6azNPPNgg6..gystv4j/evjFI5cif1hjOKgze0hmNwW', NULL, 'ZrNSOP7ws65n', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(153, 'Honore', 'Payet', 'Hon.Payet', 'Hon.Payet@example.org', NULL, '$2y$12$wCtkaq9kINXoYMOQ1PNDfuT.XgaQ1.EQ3Ravz8rFF9iSq59xOf/yW', NULL, '3qx0jEkiXmDz', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(154, 'Bernard', 'Joly', 'Ber.Joly', 'Ber.Joly@example.org', NULL, '$2y$12$0zA.i5q6IMtCqsMAuSVSQOb/vdF.CPUWdViamcw9mn.4eetPan5Qa', NULL, 'qWoglardZi9U', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(155, 'Philippine', 'Perrier', 'Phi.Perrier', 'Phi.Perrier@example.org', NULL, '$2y$12$2KnrrMH819Rxsem1iXUsGORGSccY5WqNLBglrDOGNNHHXYEdd1Gnu', NULL, 'f9ajXErSQHUs', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(156, 'Thibaut', 'Lopez', 'Thi.Lopez', 'Thi.Lopez@example.org', NULL, '$2y$12$SW2dpScLZOdHnRRJsu1equRlvk0jtm6HngPrs4USF4PNavGEuQZYy', NULL, '3pVGyXkS1Uiv', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(157, 'Marthe', 'Fernandes', 'Mar.Fernandes', 'Mar.Fernandes@example.org', NULL, '$2y$12$84zW.KoMEObGY.wApUjnNeIE99it2ZR5kNKTdfU7ORlTrmyQfd7Gm', NULL, 'xmwAQ089TuSD', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(158, 'Anais', 'Guerin', 'Ana.Guerin', 'Ana.Guerin@example.org', NULL, '$2y$12$rk2U8WO2OBceHWzzSlA/QOF8G1XTkW9hCCZPdNFbB/yaM50pqQ/Ua', NULL, 'MOiQTfe7LCkw', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(159, 'Hortense', 'Dos Santos', 'Hor.Dos Santos', 'Hor.Dos Santos@example.org', NULL, '$2y$12$DlMnFZ7rKUrAJSz29AQewOtdUV/B.dA32JnUU.0yvlSVxydgdLOje', NULL, 'Jp8ThGv6mPce', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(160, 'Helene', 'Pelletier', 'Hel.Pelletier', 'Hel.Pelletier@example.org', NULL, '$2y$12$tYvaH/BLVi22LDcFLjWniuXdXun.NP/kG5RA0Ta6nitxb5hxm2rr.', NULL, 'Qwip7Jkz9aXr', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(161, 'Dorothee', 'Loiseau', 'Dor.Loiseau', 'Dor.Loiseau@example.org', NULL, '$2y$12$xgNXEQlvGWXGYkCH2VjJE.xLivZNN1cfiErXIA/xYgngVlOIfVrFO', NULL, 'hUfEVL3ep6j2', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(162, 'Amelie', 'Torres', 'Ame.Torres', 'Ame.Torres@example.org', NULL, '$2y$12$LfaOG5TT0Wy4Nijoqp9i3.aEhHZXrcKUTrC/RKI2OdRTqanClCiJK', NULL, 'kaxvftIgcArT', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(163, 'Thibaut', 'Coulon', 'Thi.Coulon', 'Thi.Coulon@example.org', NULL, '$2y$12$OJ8Hirq5n8IE8TnNrfLwm.2IuzGufXq/rq7pRw.dceZiXckimuNjW', NULL, 'VeREmQo72kWZ', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(164, 'Joseph', 'Perret', 'Jos.Perret', 'Jos.Perret@example.org', NULL, '$2y$12$sJN.qV2.5/IZBXFwqa1ZGOBkkuJCM9izY6r6/18uBczl.IcD9B26K', NULL, 'fAdTFLwh5SbD', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(165, 'Emile', 'Boulay', 'Emi.Boulay', 'Emi.Boulay@example.org', NULL, '$2y$12$gYt/SoU6eA09tOQPXOF7UuuRk/IOL2mfsaEdj4yZ4FbVznJjKIq9O', NULL, 'mU3fHit2MjpE', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(166, 'Hortense', 'Vincent', 'Hor.Vincent', 'Hor.Vincent@example.org', NULL, '$2y$12$L2S8Gw9MUU3b40vMqDIQKO1h7CJvF3yMYpENJcA7Rb.3UNpj7K0uu', NULL, 'eOmDHFTIWbuw', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(167, 'Jacqueline', 'Lemoine', 'Jac.Lemoine', 'Jac.Lemoine@example.org', NULL, '$2y$12$1dfl.OYz0gSwPU5oYcPPD.UzfA.T7FMwTRDLa6UkD.sebi7E0qEXW', NULL, 'Gx2OsP965goz', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(168, 'Celine', 'Pierre', 'Cel.Pierre', 'Cel.Pierre@example.org', NULL, '$2y$12$petvcRAKgOytl4WAenhgHe9Yfbe5Q7y775V9/1gbNOEOE1Y0vdEhW', NULL, 'zsm15QxZc92h', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(169, 'Genevieve', 'Carlier', 'Gen.Carlier', 'Gen.Carlier@example.org', NULL, '$2y$12$l.DVSRH8cmJ0MjkMtjbIVeQL3.uPRTFuVuab5y/KrKYT1f9.5ZDCG', NULL, 'OudFsTURKjtZ', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(170, 'Dominique', 'Gros', 'Dom.Gros', 'Dom.Gros@example.org', NULL, '$2y$12$tdcMCVYRqbsj950Gpq.XO.ikYehM5PT.ibIo963fk6nGecPa7B8B2', NULL, 'gv0r9a2APIRj', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(171, 'Bernadette', 'Lemaire', 'Ber.Lemaire', 'Ber.Lemaire@example.org', NULL, '$2y$12$fymFlHX7xkLS4F0eQeetxeBm3NjX16IsWe3rr1J2CQB06li9oYgoC', NULL, 'Ocy2EqiJmkFW', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(172, 'Bernard', 'Ribeiro', 'Ber.Ribeiro', 'Ber.Ribeiro@example.org', NULL, '$2y$12$V0uBs4WJL3CigukF5FKsxu5iEUPbZWAQhcOtdiSMAGwy6QMccSyXO', NULL, 'gkoEHI7mhz13', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(173, 'Charles', 'Coste', 'Cha.Coste', 'Cha.Coste@example.org', NULL, '$2y$12$aVyErnanLZhYR7vsrynn5O52gY5Qop0rPFFdogAA/oAaSKIx.vL.q', NULL, 'WNT4GL7yqnHR', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(174, 'Guillaume', 'Charles', 'Gui.Charles', 'Gui.Charles@example.org', NULL, '$2y$12$C9k22HLmtXWDzLU8thBsku9XW1DpEltEfwbqpVfxnQwSt0E9VP8NK', NULL, 'k6xePZlzn4j2', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(175, 'Thierry', 'Mallet', 'Thi.Mallet', 'Thi.Mallet@example.org', NULL, '$2y$12$rsoRoPDLeFuBYlS1NfLrauMVa2Mrs5w772NESeXvB7UIbfUCJdQe6', NULL, 'cGruT6M4B2Cy', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(176, 'Agnes', 'Marion', 'Agn.Marion', 'Agn.Marion@example.org', NULL, '$2y$12$3GAIomW2l/NuYCPGtkXO2.42FmqnNP4SDpWaGIxCek7OBAcnA.Ly.', NULL, 'uxk31JZKnY9T', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(177, 'Antoinette', 'Leveque', 'Ant.Leveque', 'Ant.Leveque@example.org', NULL, '$2y$12$ApWksmCNn70Zt.yvFEOoze1xCFpN66BJVyzluerxzec2472r.zzYq', NULL, 'C9pUczsdlquP', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(178, 'David', 'Coste', 'Dav.Coste', 'Dav.Coste@example.org', NULL, '$2y$12$lIYJ04vEq1wMmAryzsYmGO6L0kpBCwctmc5ZsLwpi023gTQu8AJIW', NULL, 'uMUCLGfS2irT', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(179, 'Martine', 'Laine', 'Mar.Laine', 'Mar.Laine@example.org', NULL, '$2y$12$tJqyMSWpbZxhK5GaTSsePurENoHHno6CXrTuNVR9AYT0sAZvwO6Zy', NULL, 'x4te67mcnSVE', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(180, 'Joseph', 'Ollivier', 'Jos.Ollivier', 'Jos.Ollivier@example.org', NULL, '$2y$12$TCry5cOm9MAhlnAws8xYm.eduhARNXPVPMlhyouoIL.LcIk8WMVRe', NULL, 'nUolxjSfX9FB', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(181, 'Aurore', 'Schneider', 'Aur.Schneider', 'Aur.Schneider@example.org', NULL, '$2y$12$sfhOP6Jewup2d9ov/4AHTetiNQvmlu6dult227YubMmWB2LT15VNW', NULL, 'AigvXJ9TKdcw', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(182, 'Alfred', 'Lemonnier', 'Alf.Lemonnier', 'Alf.Lemonnier@example.org', NULL, '$2y$12$xlfLZr5PR1P6oESq6TAjnOVCCc6JDEU7yJVLLRWfRq5.3q3plNEg2', NULL, 'SLhNncsuYBQo', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(183, 'Adrien', 'Charrier', 'Adr.Charrier', 'Adr.Charrier@example.org', NULL, '$2y$12$j5NxTPTLu8ODJAKlfhGtUO1UEfYxt.gjtrJt7Emi4gQPcms1kDErW', NULL, 'wBq0bpyISK6N', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(184, 'Christiane', 'Aubert', 'Chr.Aubert', 'Chr.Aubert@example.org', NULL, '$2y$12$modJeU6GUABuwO2MNPcaweJUHl1lbisoE75cld6MmcKuA90VZWBtO', NULL, 'gNnMvu9Ta8c4', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(185, 'Cecile', 'Verdier', 'Cec.Verdier', 'Cec.Verdier@example.org', NULL, '$2y$12$SN.2XPnfGVpSsbh0qLK70.gdAr1GWHRetdsdATJO5SSSrxSw/tfiu', NULL, 'gQEDUdPBGwzb', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(186, 'Agathe', 'Blot', 'Aga.Blot', 'Aga.Blot@example.org', NULL, '$2y$12$xAN/Rq/OSBhcweYwYvskre/kxtYFgvkxNscCri.MMfsSPQud13xvO', NULL, 'oJDihfY3EbTQ', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(187, 'Benoit', 'Delannoy', 'Ben.Delannoy', 'Ben.Delannoy@example.org', NULL, '$2y$12$/6M.3vPIjk5.FZY.TowaC.OezGRlcOpc./BDiU37wbqc6yR.xrxvW', NULL, 'uqLjVIW0NCP5', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(188, 'Nicole', 'Delaunay', 'Nic.Delaunay', 'Nic.Delaunay@example.org', NULL, '$2y$12$nqfITjzWzW0o91PYQ9o8Q.ND0aa7jN8OSh5KKXQz..i1A2p1ftiZm', NULL, '2qY8pnljJEyK', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(189, 'Francoise', 'Jacques', 'Fra.Jacques', 'Fra.Jacques@example.org', NULL, '$2y$12$UgKgNSsjOLPfhCB1wvhko.qMGhNdPGKgB0bhoIoa9ZbG.TCjUpsam', NULL, '0XbNloUrSVaq', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(190, 'Maggie', 'Verdier', 'Mag.Verdier', 'Mag.Verdier@example.org', NULL, '$2y$12$7AfghMZelznLlaJdLpoBMuZ99tLg3FldfeoZzu70he/Gpwzns0rBW', NULL, '65sBjlpkWUTd', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(191, 'Adele', 'Alves', 'Ade.Alves', 'Ade.Alves@example.org', NULL, '$2y$12$aYu/yQFER5JxPf2A.yIkBOHeRphyVvHdKBZm1Ps.vMajLmMMYUZki', NULL, 'r9KRWQvdz0ac', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(192, 'Sebastien', 'Auger', 'Seb.Auger', 'Seb.Auger@example.org', NULL, '$2y$12$RHx.1.TBrkJyqhaHonsh/uB8HhHTcTJcLY4N0LRQ7ya95HiZuwrri', NULL, 'fwkNhDLiEosr', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(193, 'Helene', 'Riviere', 'Hel.Riviere', 'Hel.Riviere@example.org', NULL, '$2y$12$/xaXw0iDNzp2bl5Z.BTIyO7dJs/eXMSrjwQ.4/4Jmjrv37slaAzTG', NULL, '6YDaXmewtA8F', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(194, 'Isabelle', 'Blanchard', 'Isa.Blanchard', 'Isa.Blanchard@example.org', NULL, '$2y$12$QCsfbWY8KjlmjZxs6lDMVOXJ/xMsJA23fNOrSZB5J35Lx6Lvr0cRq', NULL, 'cu3QMrmLWhTv', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(195, 'Roger', 'Vasseur', 'Rog.Vasseur', 'Rog.Vasseur@example.org', NULL, '$2y$12$OGkqQFR5vguM8.PCNM1sD.qXH7WxVYvy07z8f0ihpZf82JQ8U19zS', NULL, 'fMji7BgRpxGy', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(196, 'Claude', 'Delaunay', 'Cla.Delaunay', 'Cla.Delaunay@example.org', NULL, '$2y$12$46B6dM4swUvkgA1xc21lG.ma25HbzA6IeOac.EM5Hf4lxw6P/GGQC', NULL, 'BfqD8Y20CFxp', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(197, 'Stephanie', 'Roux', 'Ste.Roux', 'Ste.Roux@example.org', NULL, '$2y$12$CnJGvaKZ14sIxHcqdil1qOogWb6T0BIKKkyNCGNMVf.xnRstp5lCS', NULL, 'NxdOynjEUHki', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(198, 'Helene', 'Cordier', 'Hel.Cordier', 'Hel.Cordier@example.org', NULL, '$2y$12$UUue0D2TDdUCEBIaRtlFjesMvH8rGL6zNpEvGcYCKheGh34wRgK1a', NULL, 'Uu4mSRXpV9QH', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(199, 'Leon', 'Gauthier', 'Leo.Gauthier', 'Leo.Gauthier@example.org', NULL, '$2y$12$HPDo7ZIZyhjfiau9J7dwoet5.ypVF41Ew4UIsz7pa9mWZbybiNc1u', NULL, 'lEqDR90zAFrc', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(200, 'Alice', 'Perez', 'Ali.Perez', 'Ali.Perez@example.org', NULL, '$2y$12$YVK1iuCjy8NsWmLtR5MGauy.8ZGLR202seiykxpaH0bMHfQreZPS2', NULL, 'hXqx9Jv5rPkV', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(201, 'Philippe', 'Weber', 'Phi.Weber', 'Phi.Weber@example.org', NULL, '$2y$12$paJYWxY5zs9WwcOOvw.2dO2cLh.y7U4gu8qWMcCJHAQv2wSNtxvBy', NULL, 'Yvi0OlfgIqj8', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(202, 'Alix', 'Perrier', 'Ali.Perrier', 'Ali.Perrier@example.org', NULL, '$2y$12$Pkdp8jUJwNYgcoyEQpOlNO/p0npD40eTln3OHEsSx9QpVHQ0xNOCC', NULL, 'R1YJNkr5czyH', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(203, 'Nicolas', 'Maillot', 'Nic.Maillot', 'Nic.Maillot@example.org', NULL, '$2y$12$hziWXQrKjmYP72ymdytfVeG9WTXKsctK/c2e/IAb/xjAdILHnUXVy', NULL, 'sqtTzUCbHQpP', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(204, 'Andre', 'Dijoux', 'And.Dijoux', 'And.Dijoux@example.org', NULL, '$2y$12$fBMlRme8.imP5qoJR72BsOzd.o62dQBwhcGgzxGOFo99XJS6KDBpC', NULL, 'RerFwQhcq8VP', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(205, 'Caroline', 'Rousseau', 'Car.Rousseau', 'Car.Rousseau@example.org', NULL, '$2y$12$j9VxqDJWsRTFCGrxSh.xYO/o6e1b7ozpLYGI8taC0Z80x.Gjuaw4K', NULL, 'iMTsIzqG2Zvt', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(206, 'Chantal', 'Collet', 'Cha.Collet', 'Cha.Collet@example.org', NULL, '$2y$12$k1BiZ18vYPfG89ljwGcOZOZzblrgS8MsJvNilZ2bYniVXWj6VE6Ly', NULL, 'uL9tdwRozIsX', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(207, 'Andree', 'Jacob', 'And.Jacob', 'And.Jacob@example.org', NULL, '$2y$12$Nj0ayCeT5axfXj43Gb8eve5253JUgcdWCq1KRztEri2z9wYnjKFZS', NULL, 'uKHWYjUSRABc', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(208, 'Alexandrie', 'Carre', 'Ale.Carre', 'Ale.Carre@example.org', NULL, '$2y$12$XChDdAmX2iR0NLWnsuAEG.X1E/oRNrLio7Mt8eXWngtqcFQqPurnW', NULL, 'yjK14Sta9B5U', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(209, 'Audrey', 'Brun', 'Aud.Brun', 'Aud.Brun@example.org', NULL, '$2y$12$35OAQbwovCyML4bVh7NAv.EBfa.FoYAsiAASESXp3UMrbaEH60/c2', NULL, '59bpguINomBU', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(210, 'Auguste', 'Jacob', 'Aug.Jacob', 'Aug.Jacob@example.org', NULL, '$2y$12$2SAkJf6Nyvst0TBDG5w0CO40lDoHmw/BTUL8.rhyOfx9zP3EciHw6', NULL, 'T6Kwyih72GdL', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(211, 'Laure', 'Charles', 'Lau.Charles', 'Lau.Charles@example.org', NULL, '$2y$12$jMuy6Z78HjNp3vyJnvGcFeKlH5x8FV2C9fS8nyG5faCzevFEDsA6i', NULL, 'TunatGVRQ8W6', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(212, 'Jerome', 'Gimenez', 'Jer.Gimenez', 'Jer.Gimenez@example.org', NULL, '$2y$12$Lx13YDFx/g3lNzgNNan7Nek7NCkOzJJFLcrSqR5ynU1sOw0T2q/E2', NULL, 'He4SPgtdOjlJ', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(213, 'Michele', 'Olivier', 'Mic.Olivier', 'Mic.Olivier@example.org', NULL, '$2y$12$w1yoiE/c9vvlxE/j0n59zOVjghzEw/zfLIycyxuxA4hIFD7KBEu5q', NULL, 'M9F03LbEjhS6', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(214, 'Frederic', 'Garnier', 'Fre.Garnier', 'Fre.Garnier@example.org', NULL, '$2y$12$2BUUDfp4hzd5.5t53YJbOuuOaTeiCUgegl9q8xo5xKViG77amTR/2', NULL, 'aQDuWtYKJkL2', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(215, 'Patricia', 'Martin', 'Pat.Martin', 'Pat.Martin@example.org', NULL, '$2y$12$dJfRLRPobD4GGp9FIvWPn.ns5VKgKHCkgyvaxxdB8D7jkHHahhnAi', NULL, 'VbFIqNsjJgwu', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(216, 'Matthieu', 'Navarro', 'Mat.Navarro', 'Mat.Navarro@example.org', NULL, '$2y$12$AlRjzFt/wLNSvxSEOigEx.6Xseqt2o1a8xX9Dz8eQNzukZ/hbflOO', NULL, 'VzoJC4fXjQ56', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(217, 'Timothee', 'Merle', 'Tim.Merle', 'Tim.Merle@example.org', NULL, '$2y$12$aI0kUgxTCHzb3zdGZtNrpuVfGk/VKq8tX45ZTczqQIFJsffdd9eq6', NULL, 'CsgNakHYWwMI', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(218, 'Rene', 'Delmas', 'Ren.Delmas', 'Ren.Delmas@example.org', NULL, '$2y$12$M88dcbBK4YmQr1d.QvbDHOIK5mVwiwhUPxkjCyEMV1tswu5LjYLd.', NULL, '302mCXMGpPoN', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(219, 'Anais', 'Benoit', 'Ana.Benoit', 'Ana.Benoit@example.org', NULL, '$2y$12$oCG/S2ear29cRu3tVsc7uuf9Pa2QWLBTgXj96Zl4fzHLitnWozgdO', NULL, 'JFzHIuKMCRb2', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(220, 'Gabriel', 'Cousin', 'Gab.Cousin', 'Gab.Cousin@example.org', NULL, '$2y$12$aIbUHUJbLX2PpUSv4fn7GuexHxuTNtzcIm8RvrscGnKlVOuk69PvW', NULL, 'Hx7oUpCsK8z4', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(221, 'Marcelle', 'Gautier', 'Mar.Gautier', 'Mar.Gautier@example.org', NULL, '$2y$12$E3qwJG.Yn9i/GKXInMwejuiFyeGllnyMByMP2mxeJjWtWlxpBBMXG', NULL, 'V5f6XNE1H8wj', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(222, 'Bernard', 'De Sousa', 'Ber.De Sousa', 'Ber.De Sousa@example.org', NULL, '$2y$12$sVnRQWCWIebRtCgwNXrg4uEE6G0Mwu3uLiShGtd2YfSMI9iySXCaS', NULL, 'zN4guLhrDAH0', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(223, 'Manon', 'Guilbert', 'Man.Guilbert', 'Man.Guilbert@example.org', NULL, '$2y$12$nJRy7JN0QqgWFrkJKj7CiuhMspyuv/yk6mClUYBJ5EwAtjTroju3W', NULL, '9rNMyPHbsIKa', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(224, 'Guillaume', 'Gaudin', 'Gui.Gaudin', 'Gui.Gaudin@example.org', NULL, '$2y$12$I0boESfdm7HNdfoasY50MOgA2hvtOaMXnur6JpjMd7Rm.y0hgTM/i', NULL, 'nXKI5hQdbvCR', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(225, 'Alfred', 'Rocher', 'Alf.Rocher', 'Alf.Rocher@example.org', NULL, '$2y$12$kGV8JHLzuRd2/5gA1fpmTeE1A3G.SACOsJgdhAp2BFwrKkApHIjIe', NULL, 'MGWQLVZyRnPb', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(226, 'Madeleine', 'Guichard', 'Mad.Guichard', 'Mad.Guichard@example.org', NULL, '$2y$12$gMVf.4AZ7mGHUc9rwiVhYuAafKbENysSifth8Ch1PfGf1xS5STYuq', NULL, 'imFoWt9RJY6A', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(227, 'Lucas', 'Durand', 'Luc.Durand', 'Luc.Durand@example.org', NULL, '$2y$12$lgmlJGQTSi8gIrhzVQNT.e4GgUFeJgIr8rVhCqbwug4xelLSCADbK', NULL, '238AyYOuFNk5', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(228, 'Capucine', 'Mendes', 'Cap.Mendes', 'Cap.Mendes@example.org', NULL, '$2y$12$Dbhkw8H8FAvE3xeXV8uV7eMgssJlgekl7KlMJ.ksT4809YNHlEqbm', NULL, 'iJUIvSl0groM', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(229, 'Georges', 'Bernier', 'Geo.Bernier', 'Geo.Bernier@example.org', NULL, '$2y$12$zxvypSQzhNTuX0ZrjKUeHeCRu7svUtX5tVnTe8i9kcel74H84kWQK', NULL, 'YfJwcDFpuLTd', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(230, 'Pierre', 'Gallet', 'Pie.Gallet', 'Pie.Gallet@example.org', NULL, '$2y$12$wNipV8evpa.lsAPP8eZYMuo0.4YrbzNhb7ht3uUlck9yrkYJmVtTi', NULL, 'u4ZWvnQh1FV8', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(231, 'Maryse', 'Leveque', 'Mar.Leveque', 'Mar.Leveque@example.org', NULL, '$2y$12$C1U4djL68xOFJ8wRk8iD5ewBjqFUvmJNRGQnKhH0PDB/XSpNdXVea', NULL, '4CdDaP6g1AEW', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(232, 'Laurence', 'Martins', 'Lau.Martins', 'Lau.Martins@example.org', NULL, '$2y$12$tHKqW5HBsQmPio9AQhxrzeH7/W37/Aj1RVW0gKr4oGazO5ZKJanf6', NULL, 'P61BHrKxEYAQ', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(233, 'Louis', 'Perrin', 'Lou.Perrin', 'Lou.Perrin@example.org', NULL, '$2y$12$4tRFn2BE2UxSmw4tUwjLd.jBLrlvDaRsTxfBau6o1DtvFkOdYJkzG', NULL, 'UcMfP15XmYdw', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(234, 'Raymond', 'Humbert', 'Ray.Humbert', 'Ray.Humbert@example.org', NULL, '$2y$12$7SQJKEBiAfxtCJwWHqvFueu2wZfpG3wnT9KeytuPdXYdhUAiEr90q', NULL, 'l1p3qms0ESr2', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(235, 'Martine', 'Gilles', 'Mar.Gilles', 'Mar.Gilles@example.org', NULL, '$2y$12$apr0vwhCO5aADwtXzT5dLOKdFh12MrbiN5YKDfzY4JixckIM1koCC', NULL, 'k57HwsrGfd64', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(236, 'Simone', 'Gaudin', 'Sim.Gaudin', 'Sim.Gaudin@example.org', NULL, '$2y$12$iOj1zkhW/NZQT48CuzKXOuly/hm0mrt5smQ6Usx4oOAj3zidPN.9u', NULL, 'S8cUezOXJK1G', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(237, 'Alice', 'Carlier', 'Ali.Carlier', 'Ali.Carlier@example.org', NULL, '$2y$12$Ee8BTWvyJMCF4lUGQ/p3gu8XadG5hxAdUycE63oitQLmxvgzdVDQG', NULL, 'sIgyWlNFTpXm', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(238, 'Roland', 'Blondel', 'Rol.Blondel', 'Rol.Blondel@example.org', NULL, '$2y$12$1P8cBLe44kjaPGa/TcGYJ.eq.TtVG.vqQa9vXiICk5XFURgCTBBVO', NULL, 'bGwYaUQK0e3M', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(239, 'Elisabeth', 'Guillon', 'Eli.Guillon', 'Eli.Guillon@example.org', NULL, '$2y$12$/xQn4Dcu6FFI92Xgw8NQmujZ6qceBvBxwSAd4PXtHmEDwB3vJhW8a', NULL, 'Xnq7lmAhBS6d', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(240, 'Elodie', 'Briand', 'Elo.Briand', 'Elo.Briand@example.org', NULL, '$2y$12$k0d71ix7E78f53bc.chSOuzScgShjf6yzWhJDU7BYo8FU4qiZmwe.', NULL, 'E6pY8Ikg1tR5', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(241, 'Margaud', 'Lesage', 'Mar.Lesage', 'Mar.Lesage@example.org', NULL, '$2y$12$r7gPM52Zrh5wTLm2jkNbP.iBBpMmIxFJ1P5fYPWt.Y.Lg0/Kuz5Jy', NULL, 'qYv0JGbogCZn', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(242, 'Jacques', 'Paul', 'Jac.Paul', 'Jac.Paul@example.org', NULL, '$2y$12$wh1xdElNxcw0G1cEQdzLOOr9Oxi4xbfUSQk4Ui1LwZsBRKDXD/eKG', NULL, 'fZHwkOtL2v4N', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(243, 'Amelie', 'Leduc', 'Ame.Leduc', 'Ame.Leduc@example.org', NULL, '$2y$12$D2Jl5uViCI90PFWy7OjrMusCgSE5IqeNOh52FGb1atxnud8jvsKhC', NULL, 'xjPZw26dHq94', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(244, 'Andre', 'Roux', 'And.Roux', 'And.Roux@example.org', NULL, '$2y$12$EAgUjAh8PSKh58qnw6ZqAOsq1hTA1DiS/Sa/Wb0qgirhzlRH8/tSG', NULL, 'pqLJZXr0mYEg', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(245, 'Francoise', 'Mallet', 'Fra.Mallet', 'Fra.Mallet@example.org', NULL, '$2y$12$5dyXesGnE1jSwxnComdqV.nBvTSw3gjuFMKygzWRkm.1YnQUmRLfa', NULL, '69M53c7gEd0G', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(246, 'Helene', 'Boucher', 'Hel.Boucher', 'Hel.Boucher@example.org', NULL, '$2y$12$dUPXHVeYY0lIoyPucL4ohuMVgSuIOUoN/iIXMSbkehJ5MbD2YZdxy', NULL, 'ykfdvbJ4WOsn', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(247, 'Celine', 'Regnier', 'Cel.Regnier', 'Cel.Regnier@example.org', NULL, '$2y$12$k.Ae5zAYWESarobH87fi7uNGfb1A5IbhcpALf6AAGVJ65pBLq1kMW', NULL, 'zb3ArV8Q4fmk', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(248, 'Maggie', 'Charpentier', 'Mag.Charpentier', 'Mag.Charpentier@example.org', NULL, '$2y$12$AwPsBcfG2/.v5LbzNAAoKeaVQuYRkksXpp7ta0N9FH49wKZb.K6PS', NULL, 'bKpkLZa2qnS9', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(249, 'Dominique', 'De Sousa', 'Dom.De Sousa', 'Dom.De Sousa@example.org', NULL, '$2y$12$I2mLzPBG2Xo5lRjcYq5FVuWR1L8QS8HKg6PZSV9b7TaawRQUWk9FS', NULL, 'UvuB9QlkFr7p', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(250, 'Thierry', 'Aubry', 'Thi.Aubry', 'Thi.Aubry@example.org', NULL, '$2y$12$vIdMiMAXLjv93atOTyYrcOPUEdYLTKGGhRepLU71lgXVTwohBXllK', NULL, 'KfDG8YBmdqoH', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(251, 'Noel', 'Ferreira', 'Noe.Ferreira', 'Noe.Ferreira@example.org', NULL, '$2y$12$tmoiR4caY66lK/0PKgCQvOi1oHw91mHvoNJiSRQeERXqbSLaF2UE.', NULL, 'LI83Xp2QVJ9g', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(252, 'Pauline', 'Rocher', 'Pau.Rocher', 'Pau.Rocher@example.org', NULL, '$2y$12$EDDtjRH2XdF1pIOKfOyZlulsMeHuAdC.bQgj5QnQRF0yo2Kunjkme', NULL, 'b4ycZspDvo5w', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(253, 'Zoe', 'Marion', 'Zoe.Marion', 'Zoe.Marion@example.org', NULL, '$2y$12$JYh70vOR5yk5xbgTPxqbOu/WdKb66u1FfbVkMzz/cMRUa/kqOHB86', NULL, 'q5WNPljhgTE9', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(254, 'Denis', 'Perret', 'Den.Perret', 'Den.Perret@example.org', NULL, '$2y$12$xkK19a19zeKTITlVJnks2e7E4Q3HlUJPAGx7j3PO09p9KN6WC7bPu', NULL, 'cq71oj2uPY4T', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(255, 'Clemence', 'Fabre', 'Cle.Fabre', 'Cle.Fabre@example.org', NULL, '$2y$12$08Pkr4.kJ/SMNjd7d5PIDeOweExeTXMySYhyskl3QgyjtORYS0XG6', NULL, '7NMcQ4h9PFtU', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(256, 'Veronique', 'Guillou', 'Ver.Guillou', 'Ver.Guillou@example.org', NULL, '$2y$12$n4EG9lfqc2rUoO4bEy3KBO4So9mcTatv4yoRvelhtJ1pe4Mkykslq', NULL, '0M5CcPHmxYG4', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(257, 'Chantal', 'Guyot', 'Cha.Guyot', 'Cha.Guyot@example.org', NULL, '$2y$12$99paVut/hPq.IaiXXzF..OZicIIKmMfcQE6MMuxGTaKCVbumRd0w2', NULL, 'PC4eWDvOyZIw', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(258, 'Zoe', 'Roussel', 'Zoe.Roussel', 'Zoe.Roussel@example.org', NULL, '$2y$12$Mm1VfiJlDWAeS6Gb546V9e7KT4WcantNxm6Z3UKeM80JrqNVlgmpK', NULL, 'YBgqC43kP0jW', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(259, 'Christiane', 'Perrot', 'Chr.Perrot', 'Chr.Perrot@example.org', NULL, '$2y$12$qGWuKYzhP70buE7L.PAio.cNVTP9i4PNfEaBWRXHmnGg3Mfas1An6', NULL, 'DxgQkYm0NOlL', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(260, 'Honore', 'Lebon', 'Hon.Lebon', 'Hon.Lebon@example.org', NULL, '$2y$12$gcU9lOvJumxH9egvErGrdONZjz4mjJCpvdm8Z1chzs1lCZOFYaiRC', NULL, 'RcNoh2IfuyBM', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(261, 'Michel', 'Hubert', 'Mic.Hubert', 'Mic.Hubert@example.org', NULL, '$2y$12$n/13H6SXh54X1RSkOyN9C.Tza4xsJyKvC5debkfCSs9B0bsDRMktS', NULL, 'lUQe6gatN1qm', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(262, 'Jeanne', 'Jean', 'Jea.Jean', 'Jea.Jean@example.org', NULL, '$2y$12$Fh9jdEwa8Lf3Q2KsnpiWAOdmafj.zkPotOEgQtIzDebY0TJLpSglW', NULL, '4sLo8de7Bu6C', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(263, 'Frederic', 'Alves', 'Fre.Alves', 'Fre.Alves@example.org', NULL, '$2y$12$7lfT5CryB/8b.AD6Ahe8FOVhMqVn5UFKHP0NWoXo9jHMkwSJvGz2m', NULL, 'S2mrMylU0TYg', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(264, 'Michele', 'Raynaud', 'Mic.Raynaud', 'Mic.Raynaud@example.org', NULL, '$2y$12$CVh.T9CVoLoI4XLTK6QLZ.v1NmW5aMfG3ATxBh8TVy/rzfunDuSCa', NULL, '48YRHKky5zb1', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(265, 'Theodore', 'Parent', 'The.Parent', 'The.Parent@example.org', NULL, '$2y$12$gXsKF.6Yzjlq436WUUReqeuLQCOUga61ZGfsRC/z5CreLUIWCStCq', NULL, 'ltWzN1MZKVcI', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(266, 'Sebastien', 'Brun', 'Seb.Brun', 'Seb.Brun@example.org', NULL, '$2y$12$Ur9xc4uvTu5Yr.1GytPLGOXANhhDyErgQ4Gt5xGlhLO3yYgpmMLye', NULL, 'utJ9WlYNBj8p', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(267, 'Antoinette', 'Samson', 'Ant.Samson', 'Ant.Samson@example.org', NULL, '$2y$12$QWoYiU7xFGp.BejJ59u28.m4s2SfJZ7TQWfX/0uX0nzjHkkUDwSnu', NULL, 'P9BLGIj0nZWK', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(268, 'Valerie', 'Normand', 'Val.Normand', 'Val.Normand@example.org', NULL, '$2y$12$2luI.RMsD/qFeYK5L2SGZOTdIgtBEaN5KIaJd2ee.vTkFx..7ixQ.', NULL, '1DKE6UWp4SVo', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(269, 'Antoinette', 'Charpentier', 'Ant.Charpentier', 'Ant.Charpentier@example.org', NULL, '$2y$12$hCFC6qtu2U2rfYsFs7Lpnei7Nvv81U4rW28wRVlAWVy0D/XnNOsfS', NULL, 'HQNytGsucjpb', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(270, 'Manon', 'Gomez', 'Man.Gomez', 'Man.Gomez@example.org', NULL, '$2y$12$ZyxZ8q1YxLLgzFVWvp80PeDystOVbiriThIEICJtYrEIN5jbLTsO2', NULL, 'NyxMiJBH0zvf', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(271, 'Laurent', 'Goncalves', 'Lau.Goncalves', 'Lau.Goncalves@example.org', NULL, '$2y$12$NAuoLMQlJRnT.sl9v7klTukktjwB.C9eNJ/thaW3.VJEjMXTwSNIC', NULL, '6urkKyJ8ItRH', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(272, 'Nath', 'Garnier', 'Nat.Garnier', 'Nat.Garnier@example.org', NULL, '$2y$12$adlWp0EPsMgWAG98pPDRK.853hKUjSlH4qur593hHR/fApq3rYn/a', NULL, 'N76WuQtXwIaH', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(273, 'Zoe', 'Reynaud', 'Zoe.Reynaud', 'Zoe.Reynaud@example.org', NULL, '$2y$12$fTgqf7TB8ZY1yxPJ.iXDDuyHYZiEGlHEcEmkgMVYPYCOrZExmbcjC', NULL, '5HUqPdbyfimj', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(274, 'Zacharie', 'Brunel', 'Zac.Brunel', 'Zac.Brunel@example.org', NULL, '$2y$12$d/yWTgMQUzYxE3bp3uRji.N8kMSEUIsEbCkPvramcsUgH9DtiViGG', NULL, 'rLMV0KxeFGiq', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(275, 'Clemence', 'Tanguy', 'Cle.Tanguy', 'Cle.Tanguy@example.org', NULL, '$2y$12$rkucOKHg.q10pq0.MvfYeer6ZJqFNZe/m85iTCaWGTBZCdnAIo9qu', NULL, '9Vue6N821qhn', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(276, 'Andre', 'Pineau', 'And.Pineau', 'And.Pineau@example.org', NULL, '$2y$12$HvcPJslHBnF2kodn.PYHuOUsQY0h0GAcKqKomzmNKZX3V3e/Ax6sm', NULL, 'Se0j9BcHR7vU', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(277, 'Yves', 'Masson', 'Yve.Masson', 'Yve.Masson@example.org', NULL, '$2y$12$rZU4QZYIPyHXJr2OZIpN7uyaggvkWS/bD6y8nvIs2P1NCe4xvkH16', NULL, '2c6OrAxbIMD5', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(278, 'Oceane', 'Robert', 'Oce.Robert', 'Oce.Robert@example.org', NULL, '$2y$12$jtu8DH8znSBheax2.KONO.38ng22DUx/XBCy.4sG4oVNcrXhqrXCO', NULL, 'R5fyqew7LIWA', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(279, 'Michele', 'Lambert', 'Mic.Lambert', 'Mic.Lambert@example.org', NULL, '$2y$12$GJyFruqVLd7P4h8okDV7ju/A4G.37k7i2k403RKphFRCJruMW0nCy', NULL, 'NKiSIu9fzZL3', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(280, 'Anouk', 'Carlier', 'Ano.Carlier', 'Ano.Carlier@example.org', NULL, '$2y$12$CYsAN9.9rFvxNd2FLQheluz3m3dapXwH1KCzVEhHEXPhH7rlAVyc.', NULL, '2IBhRG8VaYJn', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(281, 'Adrienne', 'Valentin', 'Adr.Valentin', 'Adr.Valentin@example.org', NULL, '$2y$12$iQSIBNeTM7k11GuEah.RH.6StBMkiPuT.KYUvKLopjJKHKVHnnPrq', NULL, 'Bi8OcsE9CLX6', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(282, 'Marc', 'Traore', 'Mar.Traore', 'Mar.Traore@example.org', NULL, '$2y$12$31qHIC8f2T.CutWg2C9BLumaClH80PEPJANQIwIfThbVHSjsNreQ2', NULL, 'b0LMtZv7iKTN', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(283, 'Gabrielle', 'Fournier', 'Gab.Fournier', 'Gab.Fournier@example.org', NULL, '$2y$12$3hweQqhx6O4Kv9NXWSmGfuMuTBGaNThsvog.jAOtCttNbgQPBZL2G', NULL, '6UukcnC4lDaV', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(284, 'Bertrand', 'Rousset', 'Ber.Rousset', 'Ber.Rousset@example.org', NULL, '$2y$12$8dMmzLqun8dDaZ8G8hGusuEy1tYYYTkpIgLL8Wf4F12SiOGOfAZl2', NULL, '9qOFgjbmpBlh', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(285, 'Mathilde', 'Noel', 'Mat.Noel', 'Mat.Noel@example.org', NULL, '$2y$12$lBXRdok7Yw7DU8RngYZcQ.D5KY/996wXF/Pr8KVQG5Sbcf9Ntm7V.', NULL, 'BRDFfItN4hqQ', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(286, 'Jerome', 'Gautier', 'Jer.Gautier', 'Jer.Gautier@example.org', NULL, '$2y$12$nybJBI7V8o.pzDLaovUKG.ePmEwFD.tMtrrOqpnQuVvdyto2tvR1q', NULL, 'aX6Y4bDSzPGj', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(287, 'Aurore', 'Blot', 'Aur.Blot', 'Aur.Blot@example.org', NULL, '$2y$12$zmo5trkvdl5aogUjQoeZc..OzDrR7DmJ9ixQ5gIPmtgCDAD.i91vu', NULL, 'qR2LB6gWlYpe', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(288, 'Genevieve', 'Noel', 'Gen.Noel', 'Gen.Noel@example.org', NULL, '$2y$12$waAuIyULc09OrXUGmA9GjuHWrywu/bruDgUlTUkpARwU9LspusTV.', NULL, 'EUq3iuxHFAdl', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 7),
(289, 'Emmanuelle', 'Foucher', 'Emm.Foucher', 'Emm.Foucher@example.org', NULL, '$2y$12$c2tJ5l.B1HeHYFGCWBCcDuwar03eiycsa/ixIUob5z/AG0JSZfwrG', NULL, 'zQFAWgvVowMU', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 1),
(290, 'Veronique', 'Poirier', 'Ver.Poirier', 'Ver.Poirier@example.org', NULL, '$2y$12$/bU4YcIlNxbWF0joZaIWLOVj3R/ivwctECcJuvRsSKCuCyuna3F.2', NULL, 'JF5qtnLZwkfM', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 3),
(291, 'Gregoire', 'Benoit', 'Gre.Benoit', 'Gre.Benoit@example.org', NULL, '$2y$12$kLhRlnU2BryXeSFKsVc2IO42qnfd/trweShjVSIwPRn5Q67DSjO6y', NULL, 'Zynk5Puwc6Jv', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 3),
(292, 'Xavier', 'Clerc', 'Xav.Clerc', 'Xav.Clerc@example.org', NULL, '$2y$12$mebhs3GadGX.02X2xGUtF.LVMWOWRvP6xCbRSC2ZfPtRcd7ebcNu.', NULL, 'zmb8LkN0AecD', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 3),
(293, 'Michel', 'Besson', 'Mic.Besson', 'Mic.Besson@example.org', NULL, '$2y$12$qGcUfjtmtHETs5QcsQ3JnuFiI/LUU9kIP3m3tFNPhZUy81ufqHGE.', NULL, '6hlWV8aoDkHO', '2026-01-21 08:52:52', '2026-01-21 08:53:00', 3),
(297, 'test', 'tester', 'tes.tester', 'tes.tester@example.com', NULL, '$2a$12$uIO6W2emTyu4yabFz6M/EeJpUYiH.HjS6VIdT5eh5P7llEIaUTAu6', NULL, 'Please Switch my comment!', '2026-01-21 08:58:53', '2026-01-21 09:02:20', 7),
(298, 'a', 'a', 'a', 'a', NULL, '$2a$12$QAJYPoROaG/ILYLeRueeHuQN7bxHL422QowZyRBxB8uAS5C8sJGcW', NULL, NULL, '2026-01-28 09:29:11', '2026-01-28 09:29:11', 1),
(299, NULL, NULL, '', '', NULL, '', NULL, NULL, '2026-01-28 09:29:11', '2026-01-28 09:29:11', 1);

--
-- Déclencheurs `mcd_users`
--
DELIMITER $$
CREATE TRIGGER `mcd_users_set_update_date` BEFORE UPDATE ON `mcd_users` FOR EACH ROW SET NEW.updated_at=CURRENT_TIMESTAMP()
$$
DELIMITER ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `mcd_antiviruses`
--
ALTER TABLE `mcd_antiviruses`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Index pour la table `mcd_authorization`
--
ALTER TABLE `mcd_authorization`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `mcd_authorized`
--
ALTER TABLE `mcd_authorized`
  ADD PRIMARY KEY (`id_role`,`id_auth`),
  ADD KEY `id_1` (`id_auth`);

--
-- Index pour la table `mcd_buy`
--
ALTER TABLE `mcd_buy`
  ADD PRIMARY KEY (`id_subscription`,`id_product`),
  ADD KEY `id_1` (`id_product`);

--
-- Index pour la table `mcd_computers`
--
ALTER TABLE `mcd_computers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `id_antivirus` (`id_antivirus`),
  ADD KEY `id_subscription` (`id_subscription`),
  ADD KEY `id_system` (`id_system`);

--
-- Index pour la table `mcd_exist`
--
ALTER TABLE `mcd_exist`
  ADD PRIMARY KEY (`id_antivirus`,`id_system`),
  ADD KEY `id_system` (`id_system`);

--
-- Index pour la table `mcd_methods`
--
ALTER TABLE `mcd_methods`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Index pour la table `mcd_outlets`
--
ALTER TABLE `mcd_outlets`
  ADD PRIMARY KEY (`code`),
  ADD KEY `code_room` (`code_room`);

--
-- Index pour la table `mcd_payment`
--
ALTER TABLE `mcd_payment`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `id_method` (`id_method`),
  ADD KEY `id_subscription` (`id_subscription`);

--
-- Index pour la table `mcd_products`
--
ALTER TABLE `mcd_products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Index pour la table `mcd_rentings`
--
ALTER TABLE `mcd_rentings`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `code_room` (`code_room`);

--
-- Index pour la table `mcd_roles`
--
ALTER TABLE `mcd_roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Index pour la table `mcd_rooms`
--
ALTER TABLE `mcd_rooms`
  ADD PRIMARY KEY (`code`);

--
-- Index pour la table `mcd_subscriptions`
--
ALTER TABLE `mcd_subscriptions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD KEY `code_outlet` (`code_outlet`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `mcd_systems`
--
ALTER TABLE `mcd_systems`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Index pour la table `mcd_users`
--
ALTER TABLE `mcd_users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_role` (`id_role`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `mcd_antiviruses`
--
ALTER TABLE `mcd_antiviruses`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `mcd_authorization`
--
ALTER TABLE `mcd_authorization`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `mcd_computers`
--
ALTER TABLE `mcd_computers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT pour la table `mcd_methods`
--
ALTER TABLE `mcd_methods`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `mcd_payment`
--
ALTER TABLE `mcd_payment`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT pour la table `mcd_products`
--
ALTER TABLE `mcd_products`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `mcd_rentings`
--
ALTER TABLE `mcd_rentings`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT pour la table `mcd_roles`
--
ALTER TABLE `mcd_roles`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `mcd_subscriptions`
--
ALTER TABLE `mcd_subscriptions`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT pour la table `mcd_systems`
--
ALTER TABLE `mcd_systems`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `mcd_users`
--
ALTER TABLE `mcd_users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=300;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `mcd_authorized`
--
ALTER TABLE `mcd_authorized`
  ADD CONSTRAINT `mcd_authorized_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `mcd_roles` (`id`),
  ADD CONSTRAINT `mcd_authorized_ibfk_2` FOREIGN KEY (`id_auth`) REFERENCES `mcd_authorization` (`id`);

--
-- Contraintes pour la table `mcd_buy`
--
ALTER TABLE `mcd_buy`
  ADD CONSTRAINT `mcd_buy_ibfk_1` FOREIGN KEY (`id_subscription`) REFERENCES `mcd_subscriptions` (`id`),
  ADD CONSTRAINT `mcd_buy_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `mcd_products` (`id`);

--
-- Contraintes pour la table `mcd_computers`
--
ALTER TABLE `mcd_computers`
  ADD CONSTRAINT `mcd_computers_ibfk_1` FOREIGN KEY (`id_antivirus`) REFERENCES `mcd_antiviruses` (`id`),
  ADD CONSTRAINT `mcd_computers_ibfk_2` FOREIGN KEY (`id_subscription`) REFERENCES `mcd_subscriptions` (`id`),
  ADD CONSTRAINT `mcd_computers_ibfk_3` FOREIGN KEY (`id_system`) REFERENCES `mcd_systems` (`id`);

--
-- Contraintes pour la table `mcd_exist`
--
ALTER TABLE `mcd_exist`
  ADD CONSTRAINT `mcd_exist_ibfk_1` FOREIGN KEY (`id_antivirus`) REFERENCES `mcd_antiviruses` (`id`),
  ADD CONSTRAINT `mcd_exist_ibfk_2` FOREIGN KEY (`id_system`) REFERENCES `mcd_systems` (`id`);

--
-- Contraintes pour la table `mcd_outlets`
--
ALTER TABLE `mcd_outlets`
  ADD CONSTRAINT `mcd_outlets_ibfk_1` FOREIGN KEY (`code_room`) REFERENCES `mcd_rooms` (`code`);

--
-- Contraintes pour la table `mcd_payment`
--
ALTER TABLE `mcd_payment`
  ADD CONSTRAINT `mcd_payment_ibfk_1` FOREIGN KEY (`id_method`) REFERENCES `mcd_methods` (`id`),
  ADD CONSTRAINT `mcd_payment_ibfk_2` FOREIGN KEY (`id_subscription`) REFERENCES `mcd_subscriptions` (`id`);

--
-- Contraintes pour la table `mcd_rentings`
--
ALTER TABLE `mcd_rentings`
  ADD CONSTRAINT `mcd_rentings_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `mcd_users` (`id`),
  ADD CONSTRAINT `mcd_rentings_ibfk_2` FOREIGN KEY (`code_room`) REFERENCES `mcd_rooms` (`code`);

--
-- Contraintes pour la table `mcd_subscriptions`
--
ALTER TABLE `mcd_subscriptions`
  ADD CONSTRAINT `mcd_subscriptions_ibfk_1` FOREIGN KEY (`code_outlet`) REFERENCES `mcd_outlets` (`code`),
  ADD CONSTRAINT `mcd_subscriptions_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `mcd_users` (`id`);

--
-- Contraintes pour la table `mcd_users`
--
ALTER TABLE `mcd_users`
  ADD CONSTRAINT `mcd_users_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `mcd_roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
