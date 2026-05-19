-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 19. Mai 2026 um 11:12
-- Server-Version: 10.4.32-MariaDB
-- PHP-Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `mydatabaseschema`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `devices`
--

CREATE TABLE `devices` (
  `designation` varchar(255) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `devices`
--

INSERT INTO `devices` (`designation`, `location`, `type`) VALUES
('CT-1', 'CT', 'Raum 102'),
('CT-2', 'CT', 'Raum 102'),
('MR-1', 'MR', 'Raum 101'),
('RX-3', 'Röntgen', 'Raum 103');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `patient`
--

CREATE TABLE `patient` (
  `svn` varchar(255) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `patient`
--

INSERT INTO `patient` (`svn`, `birth_date`, `first_name`, `gender`, `last_name`) VALUES
('', NULL, '', 'male', ''),
('1223456', '2026-04-22', 'Samra', 'female', 'Müllerin'),
('1234567891', '2026-04-01', 'Asma', 'female', 'Kha'),
('1234567898', '2026-03-31', 'Rebecca', 'female', 'Müller'),
('1234567899', '2026-05-16', 'tom', 'male', 'Hofer');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `reservations`
--

CREATE TABLE `reservations` (
  `id` bigint(20) NOT NULL,
  `body_region` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `reservation_time` datetime(6) DEFAULT NULL,
  `device_designation` varchar(255) DEFAULT NULL,
  `patient_svn` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `reservations`
--

INSERT INTO `reservations` (`id`, `body_region`, `comment`, `reservation_time`, `device_designation`, `patient_svn`) VALUES
(2, 'Kopf', '', NULL, 'RX-3', '1223456'),
(3, 'Bauch', 'hab shcmerzen am bauch', '2026-04-07 11:05:00.000000', 'MR-1', '1223456'),
(4, 'Kopf', '', NULL, 'CT-1', ''),
(17, 'Wirbelsäule', '', '2026-04-06 09:19:00.000000', 'CT-2', '1223456'),
(25, 'Kopf', '', NULL, 'CT-1', ''),
(26, 'Kopf', '', '2027-11-27 23:14:00.000000', 'MR-1', '1234567899'),
(27, 'Bauch', 'sedrzghujik', '2026-05-24 10:38:00.000000', 'CT-1', '1234567899'),
(28, 'Kopf', 'gghjk', '2026-05-27 10:40:00.000000', 'CT-2', '1223456'),
(29, 'Kopf', '', '2026-05-27 10:40:00.000000', 'CT-1', '1234567899');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `devices`
--
ALTER TABLE `devices`
  ADD PRIMARY KEY (`designation`);

--
-- Indizes für die Tabelle `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`svn`);

--
-- Indizes für die Tabelle `reservations`
--
c
--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `FKhism7st6ixm9t5ougo4d7m9cl` FOREIGN KEY (`device_designation`) REFERENCES `devices` (`designation`),
  ADD CONSTRAINT `FKnbmx0256q980doan8a97e3tuj` FOREIGN KEY (`patient_svn`) REFERENCES `patient` (`svn`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
