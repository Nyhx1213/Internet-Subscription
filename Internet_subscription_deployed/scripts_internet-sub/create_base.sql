-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 12 mai 2026 à 13:53
-- Version du serveur : 11.8.6-MariaDB-0+deb13u1 from Debian
-- Version de PHP : 8.4.21

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


--
-- Déclencheurs `mcd_subscriptions`
--
DELIMITER $$
CREATE TRIGGER `deleteSubscription` BEFORE DELETE ON `mcd_subscriptions` FOR EACH ROW BEGIN
DELETE FROM mcd_buy WHERE id_subscription = OLD.id; 
DELETE FROM mcd_payment WHERE id_subscription = OLD.id;
DELETE FROM mcd_computers WHERE id_subscription = OLD.id;
END
$$
DELIMITER ;
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
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT pour la table `mcd_methods`
--
ALTER TABLE `mcd_methods`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `mcd_payment`
--
ALTER TABLE `mcd_payment`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

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
