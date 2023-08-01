-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 31, 2023 at 11:53 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `warmed-sakkarepmu`
--

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `id_transaksi` varchar(255) NOT NULL,
  `total` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`id_transaksi`, `total`) VALUES
('TR0001', 0),
('TR0002', 35000),
('TR0003', 162500),
('TR0004', 30000),
('TR0005', 30000),
('TR0006', 23000),
('TR0007', 35000),
('TR0008', 30000),
('TR0009', 90000),
('TR0010', 30000),
('TR0011', 113000),
('TR0012', 99000),
('TR0013', 30000),
('TR0014', 17500);

-- --------------------------------------------------------

--
-- Table structure for table `penjualan_rinci`
--

CREATE TABLE `penjualan_rinci` (
  `id_barang` varchar(255) NOT NULL,
  `nama_barang` varchar(255) NOT NULL,
  `harga` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penjualan_rinci`
--

INSERT INTO `penjualan_rinci` (`id_barang`, `nama_barang`, `harga`, `jumlah`, `total`) VALUES
('BR002', 'gajah baru', 15000, 2, 30000),
('BR001', 'camel ungu', 17500, 5, 30000),
('BR001', 'camel ungu', 17500, 1, 17500);

--
-- Triggers `penjualan_rinci`
--
DELIMITER $$
CREATE TRIGGER `kurangstok` AFTER INSERT ON `penjualan_rinci` FOR EACH ROW BEGIN
	UPDATE perbarangan set stock = stock - NEW.Jumlah
    WHERE id_barang = NEW.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `perbarangan`
--

CREATE TABLE `perbarangan` (
  `id_barang` varchar(255) NOT NULL,
  `nama_barang` varchar(255) NOT NULL,
  `harga_barang` int(255) NOT NULL,
  `satuan` varchar(255) NOT NULL,
  `stock` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `perbarangan`
--

INSERT INTO `perbarangan` (`id_barang`, `nama_barang`, `harga_barang`, `satuan`, `stock`) VALUES
('BR001', 'camel ungu', 17500, 'Pcs', 25),
('BR002', 'gajah baru', 15000, 'Pcs', 115),
('BR003', 'sari roti', 15000, 'Pcs', 32),
('BR004', 'beng beng', 26500, 'Box', 5),
('BR005', 'kopiko', 11500, 'Box', 25);

-- --------------------------------------------------------

--
-- Table structure for table `perloginan`
--

CREATE TABLE `perloginan` (
  `id` int(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `perloginan`
--

INSERT INTO `perloginan` (`id`, `firstname`, `lastname`, `username`, `password`, `role`) VALUES
(2, 'muan', 'paharani', 'admin', 'admin', 'admin'),
(3, 'jichael', 'mordan', 'kasir', 'kasir', 'kasir'),
(12, 'sihub', 'los', 'sihub', 'sihub', 'admin'),
(13, 'deru', 'anggoro', 'deru chan', 'deru', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indexes for table `perbarangan`
--
ALTER TABLE `perbarangan`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `perloginan`
--
ALTER TABLE `perloginan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `perloginan`
--
ALTER TABLE `perloginan`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
