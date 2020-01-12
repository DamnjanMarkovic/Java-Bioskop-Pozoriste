-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 12, 2020 at 06:34 PM
-- Server version: 5.7.28-0ubuntu0.18.04.4
-- PHP Version: 7.2.24-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `teatar`
--

-- --------------------------------------------------------

--
-- Table structure for table `glumac`
--

CREATE TABLE `glumac` (
  `idGlumca` int(11) NOT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `glumac`
--

INSERT INTO `glumac` (`idGlumca`, `ime`, `prezime`) VALUES
(1, 'Andrija', 'Kuzmanovic'),
(2, 'Marko', 'Zivic'),
(3, 'Milena', 'Bozic'),
(8, 'Bred', 'Pit'),
(9, 'Robert', 'Redford'),
(10, 'Hari', 'Dankan'),
(11, 'Kristijan', 'Bejl'),
(12, 'Son', 'Bin'),
(13, 'Dominik', 'Parsel'),
(14, 'Dzon', 'Travolta'),
(15, 'Semjuel', 'Dzekson'),
(16, 'Uma', 'Turman'),
(17, 'Brus', 'Vilis'),
(18, 'Brus', 'Vejn'),
(19, 'Betmen', ''),
(20, 'Majkl', 'Kejn'),
(21, 'Kejti', 'Holms'),
(22, 'Kristofer', 'Riv'),
(23, 'Klark', 'Kent'),
(24, 'Supermen', ''),
(25, 'Luis', 'Lejn'),
(26, 'Valter', 'Vajt'),
(27, 'Dzesi', 'Pinkmen'),
(28, 'Skajler', 'Vajt'),
(29, 'Henk', 'Sreder'),
(30, 'Sol', 'Gudman'),
(31, 'Tajron', 'Lanister'),
(32, 'Sersei', 'Lanister'),
(33, 'Deneris', 'Targerijan'),
(34, 'Dzon', 'Snou'),
(35, 'Dzim', 'Halpert'),
(36, 'Majkl', 'Skot'),
(37, 'Dvajt', 'Srut'),
(38, 'Rajan', 'Temp'),
(39, 'Keli', 'Kapur'),
(40, 'Sten', 'Mars'),
(41, 'Kajl', 'Broflovski'),
(42, 'Kuvar', 'Sef'),
(43, 'Kartman', ''),
(44, 'Keni', ''),
(45, 'Jonas', 'Kanvald'),
(46, 'Hana', 'Kanvald'),
(47, 'Piter', 'Dopler'),
(48, 'Noa', ''),
(49, 'Ulrih', 'Nilsen'),
(50, 'Regina', 'Tidermen');

-- --------------------------------------------------------

--
-- Table structure for table `glumac_predstava`
--

CREATE TABLE `glumac_predstava` (
  `id_glumac_predstava` int(11) NOT NULL,
  `idPredstave` int(11) NOT NULL,
  `idGlumca` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `glumac_predstava`
--

INSERT INTO `glumac_predstava` (`id_glumac_predstava`, `idPredstave`, `idGlumca`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 3),
(4, 4, 8),
(5, 4, 9),
(6, 4, 10),
(7, 13, 11),
(8, 13, 12),
(9, 13, 13),
(10, 14, 14),
(11, 14, 15),
(12, 14, 16),
(13, 14, 17),
(14, 19, 18),
(15, 19, 19),
(16, 19, 20),
(17, 19, 21),
(18, 20, 22),
(19, 20, 23),
(20, 20, 24),
(21, 20, 25),
(22, 8, 26),
(23, 8, 27),
(24, 8, 28),
(25, 8, 29),
(26, 8, 30),
(27, 15, 31),
(28, 15, 32),
(29, 15, 33),
(30, 15, 34),
(31, 16, 35),
(32, 16, 36),
(33, 16, 37),
(34, 16, 38),
(35, 16, 39),
(36, 17, 40),
(37, 17, 41),
(38, 17, 42),
(39, 17, 43),
(40, 17, 44),
(41, 18, 45),
(42, 18, 46),
(43, 18, 47),
(44, 18, 48),
(45, 18, 49),
(46, 18, 50);

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `idKorisnika` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `telefon` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`idKorisnika`, `username`, `password`, `telefon`, `mail`, `status`) VALUES
(1, 'admin', 'admin', '111111', 'admin@gmail.com', 1),
(2, 'user', 'user', '222221', 'user@gmail.com', 2),
(3, 'bart', 'bart', '222222', 'bart@gmail.com', 2),
(4, 'homer', 'homer', '222223', 'homer@gmail.com', 2),
(5, 'lisa', 'lisa', '222222', 'lisa@gmail.com', 2),
(6, 'mardz', 'mardz', '222221', 'mardz@gmail.com', 2),
(7, 'megi', 'megi', '222222', 'megi@gmail.com', 2);

-- --------------------------------------------------------

--
-- Table structure for table `pozoriste`
--

CREATE TABLE `pozoriste` (
  `idPozorista` int(11) NOT NULL,
  `nazivPozorista` varchar(45) NOT NULL,
  `ulica` varchar(45) NOT NULL,
  `broj` int(11) NOT NULL,
  `grad` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pozoriste`
--

INSERT INTO `pozoriste` (`idPozorista`, `nazivPozorista`, `ulica`, `broj`, `grad`) VALUES
(1, 'Atelje 212', 'Svetogorska', 21, 'Beograd'),
(2, 'BDP', 'Milesevska', 64, 'Beograd'),
(17, 'Bioskop DeltaCity', 'Jurija Gagarina', 1000, 'Novi Beograd'),
(18, 'Bioskop Usce', 'Usce', 1, 'Novi Beograd');

-- --------------------------------------------------------

--
-- Table structure for table `predstava`
--

CREATE TABLE `predstava` (
  `idPredstave` int(11) NOT NULL,
  `nazivPredstave` varchar(45) NOT NULL,
  `idPozorista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `predstava`
--

INSERT INTO `predstava` (`idPredstave`, `nazivPredstave`, `idPozorista`) VALUES
(1, 'Nocna straza', 1),
(2, 'Liliom', 2),
(4, 'Spy Game', 17),
(8, 'Breaking Bad', 18),
(13, 'Equillibrium', 17),
(14, 'Petparacke price', 17),
(15, 'Game of Thrones', 18),
(16, 'The Office', 18),
(17, 'South Park', 18),
(18, 'Dark', 18),
(19, 'Batman', 17),
(20, 'Superman', 17);

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija`
--

CREATE TABLE `rezervacija` (
  `idRezervacije` int(11) NOT NULL,
  `brojUlaznica` int(11) NOT NULL,
  `idKorisnika` int(11) NOT NULL,
  `idTermina` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rezervacija`
--

INSERT INTO `rezervacija` (`idRezervacije`, `brojUlaznica`, `idKorisnika`, `idTermina`) VALUES
(1, 12, 4, 62),
(2, 12, 4, 39),
(3, 21, 4, 74),
(4, 12, 4, 71),
(5, 32, 4, 28),
(6, 32, 4, 41),
(7, 12, 4, 33),
(8, 32, 4, 48),
(9, 21, 4, 52),
(10, 122, 4, 90),
(11, 12, 4, 61),
(12, 23, 5, 27),
(13, 12, 5, 39),
(14, 33, 5, 44),
(15, 33, 5, 67),
(16, 11, 5, 73),
(17, 12, 5, 33),
(18, 3, 5, 48),
(19, 4, 5, 52),
(20, 12, 5, 61),
(21, 221, 3, 27),
(22, 11, 3, 39),
(23, 4, 3, 44),
(24, 3, 3, 67),
(25, 33, 3, 33),
(26, 23, 3, 90),
(27, 12, 7, 45),
(28, 12, 4, 42);

-- --------------------------------------------------------

--
-- Table structure for table `termin`
--

CREATE TABLE `termin` (
  `idTermina` int(11) NOT NULL,
  `vreme` time NOT NULL,
  `datum` date NOT NULL,
  `brojUlaznica` int(11) NOT NULL,
  `idPredstave` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `termin`
--

INSERT INTO `termin` (`idTermina`, `vreme`, `datum`, `brojUlaznica`, `idPredstave`) VALUES
(1, '12:00:00', '2020-01-06', 50, 1),
(2, '20:00:00', '2020-01-06', 50, 1),
(3, '12:00:00', '2020-01-08', 50, 1),
(4, '20:00:00', '2020-01-08', 50, 1),
(7, '12:00:00', '2020-01-09', 40, 2),
(21, '12:00:00', '2020-01-16', 50, 1),
(22, '20:00:00', '2020-01-18', 50, 1),
(23, '14:00:00', '2020-01-16', 50, 1),
(24, '22:00:00', '2020-01-18', 50, 1),
(25, '12:00:00', '2020-01-26', 50, 1),
(26, '22:00:00', '2020-01-28', 50, 1),
(27, '20:00:00', '2020-01-18', 250, 4),
(28, '22:00:00', '2020-01-18', 250, 4),
(29, '20:00:00', '2020-01-22', 250, 4),
(30, '22:00:00', '2020-01-22', 250, 4),
(31, '24:00:00', '2020-01-26', 250, 4),
(32, '20:00:00', '2020-01-26', 250, 4),
(33, '22:00:00', '2020-01-22', 99, 8),
(34, '18:00:00', '2020-02-12', 99, 8),
(35, '22:00:00', '2020-01-26', 99, 8),
(36, '22:00:00', '2020-01-28', 99, 8),
(37, '18:00:00', '2020-01-26', 99, 8),
(39, '18:00:00', '2020-01-26', 44, 13),
(40, '18:00:00', '2020-01-16', 44, 13),
(41, '12:00:00', '2020-01-26', 44, 13),
(42, '14:00:00', '2020-01-16', 44, 13),
(43, '22:00:00', '2020-01-26', 44, 13),
(44, '22:00:00', '2020-01-26', 44, 14),
(45, '22:00:00', '2020-01-28', 44, 14),
(46, '22:00:00', '2020-01-24', 44, 14),
(47, '20:00:00', '2020-01-26', 44, 14),
(48, '22:00:00', '2020-01-24', 44, 15),
(49, '22:00:00', '2020-01-26', 44, 15),
(50, '20:00:00', '2020-01-24', 44, 15),
(51, '20:00:00', '2020-01-26', 44, 15),
(52, '20:00:00', '2020-01-24', 44, 16),
(53, '20:00:00', '2020-01-22', 44, 16),
(54, '12:00:00', '2020-01-24', 44, 16),
(55, '12:00:00', '2020-01-22', 44, 16),
(61, '12:00:00', '2020-01-22', 44, 18),
(62, '12:00:00', '2020-01-24', 44, 18),
(63, '12:00:00', '2020-01-26', 44, 18),
(64, '22:00:00', '2020-01-22', 44, 18),
(65, '22:00:00', '2020-01-24', 44, 18),
(66, '22:00:00', '2020-01-26', 44, 18),
(67, '12:00:00', '2020-01-26', 44, 19),
(68, '12:00:00', '2020-01-22', 44, 19),
(69, '12:00:00', '2020-01-24', 44, 19),
(70, '16:00:00', '2020-01-26', 44, 19),
(71, '16:00:00', '2020-01-22', 44, 19),
(72, '18:00:00', '2020-01-26', 44, 19),
(73, '16:00:00', '2020-01-22', 44, 20),
(74, '16:00:00', '2020-01-24', 44, 20),
(75, '16:00:00', '2020-01-26', 44, 20),
(76, '22:00:00', '2020-01-24', 44, 20),
(77, '22:00:00', '2020-01-22', 44, 20),
(78, '22:00:00', '2020-01-26', 44, 20),
(79, '12:00:00', '2020-01-06', 50, 4),
(80, '22:00:00', '2020-01-06', 99, 8),
(81, '22:00:00', '2020-01-06', 99, 13),
(82, '22:00:00', '2020-01-06', 99, 14),
(83, '22:00:00', '2020-01-06', 99, 15),
(84, '22:00:00', '2020-01-06', 99, 16),
(86, '22:00:00', '2020-01-06', 99, 18),
(87, '22:00:00', '2020-01-06', 99, 19),
(88, '22:00:00', '2020-01-06', 99, 19),
(89, '22:00:00', '2020-01-06', 99, 20),
(90, '22:00:00', '2020-01-18', 250, 17),
(91, '22:00:00', '2020-01-28', 250, 17),
(92, '20:00:00', '2020-01-28', 25, 17),
(93, '20:00:00', '2020-01-18', 25, 17),
(94, '21:00:00', '2020-01-26', 250, 17),
(95, '12:00:00', '2020-01-09', 40, 16),
(96, '01:30:00', '2020-03-03', 33, 20),
(98, '06:30:00', '2020-04-03', 24, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `glumac`
--
ALTER TABLE `glumac`
  ADD PRIMARY KEY (`idGlumca`);

--
-- Indexes for table `glumac_predstava`
--
ALTER TABLE `glumac_predstava`
  ADD PRIMARY KEY (`id_glumac_predstava`),
  ADD KEY `idPredstave` (`idPredstave`),
  ADD KEY `idGlumca` (`idGlumca`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`idKorisnika`);

--
-- Indexes for table `pozoriste`
--
ALTER TABLE `pozoriste`
  ADD PRIMARY KEY (`idPozorista`);

--
-- Indexes for table `predstava`
--
ALTER TABLE `predstava`
  ADD PRIMARY KEY (`idPredstave`),
  ADD KEY `idPozorista` (`idPozorista`);

--
-- Indexes for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD PRIMARY KEY (`idRezervacije`),
  ADD KEY `idKorisnika` (`idKorisnika`),
  ADD KEY `idTermina` (`idTermina`);

--
-- Indexes for table `termin`
--
ALTER TABLE `termin`
  ADD PRIMARY KEY (`idTermina`),
  ADD KEY `idPredstave` (`idPredstave`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `glumac`
--
ALTER TABLE `glumac`
  MODIFY `idGlumca` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
--
-- AUTO_INCREMENT for table `glumac_predstava`
--
ALTER TABLE `glumac_predstava`
  MODIFY `id_glumac_predstava` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `idKorisnika` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `pozoriste`
--
ALTER TABLE `pozoriste`
  MODIFY `idPozorista` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `predstava`
--
ALTER TABLE `predstava`
  MODIFY `idPredstave` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `rezervacija`
--
ALTER TABLE `rezervacija`
  MODIFY `idRezervacije` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `termin`
--
ALTER TABLE `termin`
  MODIFY `idTermina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `glumac_predstava`
--
ALTER TABLE `glumac_predstava`
  ADD CONSTRAINT `glumac_predstava_ibfk_1` FOREIGN KEY (`idPredstave`) REFERENCES `predstava` (`idPredstave`),
  ADD CONSTRAINT `glumac_predstava_ibfk_2` FOREIGN KEY (`idGlumca`) REFERENCES `glumac` (`idGlumca`);

--
-- Constraints for table `predstava`
--
ALTER TABLE `predstava`
  ADD CONSTRAINT `predstava_ibfk_1` FOREIGN KEY (`idPozorista`) REFERENCES `pozoriste` (`idPozorista`);

--
-- Constraints for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD CONSTRAINT `rezervacija_ibfk_1` FOREIGN KEY (`idKorisnika`) REFERENCES `korisnik` (`idKorisnika`),
  ADD CONSTRAINT `rezervacija_ibfk_2` FOREIGN KEY (`idTermina`) REFERENCES `termin` (`idTermina`);

--
-- Constraints for table `termin`
--
ALTER TABLE `termin`
  ADD CONSTRAINT `termin_ibfk_1` FOREIGN KEY (`idPredstave`) REFERENCES `predstava` (`idPredstave`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
