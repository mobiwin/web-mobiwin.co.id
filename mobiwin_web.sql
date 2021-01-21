-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Waktu pembuatan: 21 Jan 2021 pada 10.28
-- Versi server: 10.4.10-MariaDB
-- Versi PHP: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mobiwin_web`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `about_us_tb`
--

DROP TABLE IF EXISTS `about_us_tb`;
CREATE TABLE IF NOT EXISTS `about_us_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  `summary` varchar(30) NOT NULL,
  `wording` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `about_us_tb`
--

INSERT INTO `about_us_tb` (`id`, `title`, `summary`, `wording`, `created_at`) VALUES
(1, 'About', 'About Us', '<p>Testing</p>\r\n', '2021-01-21 03:39:52');

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin_tb`
--

DROP TABLE IF EXISTS `admin_tb`;
CREATE TABLE IF NOT EXISTS `admin_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `admin_tb`
--

INSERT INTO `admin_tb` (`id`, `enabled`, `user_name`, `user_password`) VALUES
(1, b'1', 'admin', '08df912674b938bd8b163efce615d14a');

-- --------------------------------------------------------

--
-- Struktur dari tabel `candicate_tb`
--

DROP TABLE IF EXISTS `candicate_tb`;
CREATE TABLE IF NOT EXISTS `candicate_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip_user` varchar(50) NOT NULL,
  `candidate_name` varchar(200) NOT NULL,
  `candidate_skill` text NOT NULL,
  `candidate_desc` text NOT NULL,
  `candidate_cv_path` text NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `candidate_tb`
--

DROP TABLE IF EXISTS `candidate_tb`;
CREATE TABLE IF NOT EXISTS `candidate_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `candidate_cv_path` datetime DEFAULT NULL,
  `candidate_desc` varchar(255) DEFAULT NULL,
  `candidate_name` varchar(255) DEFAULT NULL,
  `candidate_skill` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `ip_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `career_tb`
--

DROP TABLE IF EXISTS `career_tb`;
CREATE TABLE IF NOT EXISTS `career_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_title` varchar(200) NOT NULL,
  `icon_of` text NOT NULL,
  `potition` varchar(200) NOT NULL,
  `requirement` text NOT NULL,
  `potition_desc` text NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `carousel_tb`
--

DROP TABLE IF EXISTS `carousel_tb`;
CREATE TABLE IF NOT EXISTS `carousel_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `carousel_image` text DEFAULT NULL,
  `caption` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `our_client_tb`
--

DROP TABLE IF EXISTS `our_client_tb`;
CREATE TABLE IF NOT EXISTS `our_client_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `preview_path` text NOT NULL,
  `client_name` varchar(200) NOT NULL,
  `year` varchar(10) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `our_project_tb`
--

DROP TABLE IF EXISTS `our_project_tb`;
CREATE TABLE IF NOT EXISTS `our_project_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `preview_path` text NOT NULL,
  `peoject_title` varchar(200) NOT NULL,
  `year` varchar(10) NOT NULL,
  `name_user` varchar(200) NOT NULL,
  `tecknology` text NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `our_service_tb`
--

DROP TABLE IF EXISTS `our_service_tb`;
CREATE TABLE IF NOT EXISTS `our_service_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon_path` text NOT NULL,
  `short_wording` varchar(200) NOT NULL,
  `full_wording` text NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `our_team_tb`
--

DROP TABLE IF EXISTS `our_team_tb`;
CREATE TABLE IF NOT EXISTS `our_team_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar_path` text NOT NULL,
  `employee_name` varchar(200) NOT NULL,
  `potition` varchar(200) NOT NULL,
  `bio` text NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `testimony_tb`
--

DROP TABLE IF EXISTS `testimony_tb`;
CREATE TABLE IF NOT EXISTS `testimony_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_ava_path` text NOT NULL,
  `name_user` varchar(200) NOT NULL,
  `company` varchar(200) NOT NULL,
  `testimony_text` text NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
