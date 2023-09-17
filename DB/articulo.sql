-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 17, 2023 at 02:13 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bd_prueba_conexion`
--

-- --------------------------------------------------------

--
-- Table structure for table `articulo`
--

CREATE TABLE `articulo` (
  `id` int(11) NOT NULL,
  `Nombre` varchar(70) NOT NULL,
  `Precio` decimal(8,2) NOT NULL,
  `AplicaCredito` bit(1) NOT NULL,
  `Estado` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `articulo`
--

INSERT INTO `articulo` (`id`, `Nombre`, `Precio`, `AplicaCredito`, `Estado`) VALUES
(1, 'salvo', '55.00', b'1', 1),
(3, 'Sabritas', '20.00', b'0', 1),
(4, 'galletas', '20.00', b'0', 0),
(5, 'Galletas Principe', '25.00', b'0', 1),
(6, 'CEREAL', '47.00', b'1', 1),
(7, 'NEW', '11.00', b'1', 0),
(8, 'NEW', '12.00', b'1', 0),
(9, 'NEW', '12.00', b'1', 1),
(10, 'PROD', '44.00', b'1', 0),
(11, 'x1', '22.00', b'0', 0),
(12, 'Agua Ciel upd', '20.00', b'1', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articulo`
--
ALTER TABLE `articulo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `articulo`
--
ALTER TABLE `articulo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
