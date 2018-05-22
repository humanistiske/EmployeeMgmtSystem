-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2017 at 01:21 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `synergydb`
--
CREATE DATABASE IF NOT EXISTS `synergydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `synergydb`;

-- --------------------------------------------------------

--
-- Table structure for table `empinfo`
--

DROP TABLE IF EXISTS `empinfo`;
CREATE TABLE `empinfo` (
  `empid` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `contact` bigint(15) DEFAULT NULL,
  `designation` varchar(45) NOT NULL,
  `managerid` varchar(10) NOT NULL,
  `emailid` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `empinfo`
--

INSERT INTO `empinfo` (`empid`, `name`, `address`, `contact`, `designation`, `managerid`, `emailid`) VALUES
('S001', 'Ganesh Chawkekar', '1E-Swadeshi Mills Complex, Swadeshi Mills Road,  Chunabhatti', 9923990602, 'Java Developer', 'M001', 'vfxlearners@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `emplogin`
--

DROP TABLE IF EXISTS `emplogin`;
CREATE TABLE `emplogin` (
  `empid` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emplogin`
--

INSERT INTO `emplogin` (`empid`, `password`) VALUES
('M001', '123'),
('S001', '123');

-- --------------------------------------------------------

--
-- Table structure for table `emplogs`
--

DROP TABLE IF EXISTS `emplogs`;
CREATE TABLE `emplogs` (
  `empid` varchar(10) NOT NULL,
  `intime` time DEFAULT NULL,
  `outtime` time DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emplogs`
--

INSERT INTO `emplogs` (`empid`, `intime`, `outtime`, `date`) VALUES
('', '16:04:05', '20:04:02', '2017-06-06'),
('S001', '13:26:15', '14:10:58', '2017-12-09'),
('S001', '13:29:46', '14:10:58', '2017-12-09'),
('S001', '13:29:48', '14:10:58', '2017-12-09'),
('S001', '13:30:12', '14:10:58', '2017-12-09'),
('S001', '13:30:15', '14:10:58', '2017-12-09'),
('S001', '13:32:30', '14:10:58', '2017-12-09'),
('S001', '14:10:55', '14:10:58', '2017-12-09'),
('S001', '14:33:32', '12:22:42', '2017-12-09'),
('S001', '11:16:27', '11:16:31', '2017-12-12'),
('S001', '11:17:38', '12:17:35', '2017-12-12'),
('S001', '12:17:46', '12:17:58', '2017-12-12'),
('S001', '12:23:40', '12:23:45', '2017-12-12'),
('S001', '12:35:54', '12:35:57', '2017-12-12'),
('S001', '11:21:10', '10:38:40', '2017-12-13'),
('S001', '10:38:47', '17:58:38', '2017-12-14'),
('S001', '10:40:24', NULL, '2017-12-16');

-- --------------------------------------------------------

--
-- Table structure for table `jobdist`
--

DROP TABLE IF EXISTS `jobdist`;
CREATE TABLE `jobdist` (
  `empid` varchar(20) NOT NULL,
  `job` varchar(200) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `starttime` time DEFAULT NULL,
  `endtime` time DEFAULT NULL,
  `date` date DEFAULT NULL,
  `jobid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobdist`
--

INSERT INTO `jobdist` (`empid`, `job`, `status`, `remarks`, `starttime`, `endtime`, `date`, `jobid`) VALUES
('S001', 'Create Job Distribution List tab for manager and employee', 'Completed', '', '00:00:00', '00:00:00', '2017-12-13', 1),
('S001', 'Some other job', 'In Progress', 'when sun set', '00:00:00', '01:00:00', '2017-12-13', 2),
('S001', 'Create something', 'In Progress', 'fdsfdas', '16:00:00', '19:00:00', '2017-12-16', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `empinfo`
--
ALTER TABLE `empinfo`
  ADD PRIMARY KEY (`empid`),
  ADD UNIQUE KEY `empid` (`empid`);

--
-- Indexes for table `emplogin`
--
ALTER TABLE `emplogin`
  ADD PRIMARY KEY (`empid`),
  ADD UNIQUE KEY `empid` (`empid`);

--
-- Indexes for table `jobdist`
--
ALTER TABLE `jobdist`
  ADD UNIQUE KEY `jobid` (`jobid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jobdist`
--
ALTER TABLE `jobdist`
  MODIFY `jobid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
