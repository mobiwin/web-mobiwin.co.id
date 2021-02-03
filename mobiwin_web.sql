-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Waktu pembuatan: 03 Feb 2021 pada 02.33
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
  `title` varchar(40) NOT NULL,
  `summary` varchar(250) NOT NULL,
  `wording` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `about_us_tb`
--

INSERT INTO `about_us_tb` (`id`, `title`, `summary`, `wording`, `created_at`) VALUES
(1, 'Services', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc urna orci, feugiat vel porttitor non, iaculis nec tortor.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc urna orci, feugiat vel porttitor non, iaculis nec tortor.', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc urna orci, feugiat vel porttitor non, iaculis nec tortor. Suspendisse lobortis aliquet egestas. Maecenas at ligula eu ex pretium egestas eu quis nibh. Aliquam a pellentesque dolor. Nullam elementum urna eget tortor cursus lobortis. Proin aliquam dapibus massa, pellentesque vulputate tellus viverra sed. Integer aliquam dui et massa consectetur tristique. Phasellus finibus nulla sit amet lacus eleifend, quis convallis tellus viverra. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus mauris nisl, ultricies eu venenatis eu, semper ac nisl. Suspendisse dui orci, luctus nec facilisis ut, egestas at diam.', '2021-01-26 04:34:27');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `career_tb`
--

INSERT INTO `career_tb` (`id`, `job_title`, `icon_of`, `potition`, `requirement`, `potition_desc`, `created_at`) VALUES
(1, 'Front End', '/career/Front_End_20210126145526.jpg', 'Front End Developer', '<div>JOB SPESIFICATION</div><div><ul><li>Candidate must possess at least a Diploma, Bachelor\'s Degree, Computer Science/Information Technology, Engineering (Computer/Telecommunication) or equivalent</li><li>Fluent in Javascript and NodeJS</li><li>Experience in building page application, server side rendering using ReactJS/VueJS/Angular/Svelte</li><li>Required language(s): English, Bahasa Indonesia</li><li>At least 3 year(s) of working experience in the related field is required for this position.</li><li>Preferably Staff (non-management &amp; non-supervisor)s specializing in IT/Computer - Software or equivalent.</li><li>1 Full-Time position(s) available.</li></ul></div>', '<div>JOB DESCRIPTION</div><div><ul><li>Develop new user-facing features</li><li>Build reusable code and libraries for future use</li><li>Design and ensure the technical feasibility of UI/UX Designs</li><li>Optimize application for maximum speed and scalability</li><li>Documentating all function, method that can be develop in the futhure</li></ul></div>', '2021-01-26 07:55:26');

-- --------------------------------------------------------

--
-- Struktur dari tabel `carousel_tb`
--

DROP TABLE IF EXISTS `carousel_tb`;
CREATE TABLE IF NOT EXISTS `carousel_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orders` int(11) NOT NULL,
  `carousel_image` text DEFAULT NULL,
  `caption` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `order` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `carousel_tb`
--

INSERT INTO `carousel_tb` (`id`, `orders`, `carousel_image`, `caption`, `created_at`, `order`) VALUES
(21, 1, '/slider/20210126102507.jpg', 'asdsadsadsad', '2021-01-26 03:25:07', NULL),
(22, 2, '/slider/20210126144855.jpg', '<div style=\"text-align:justify\">Today MMI does not only provides Software and Hardware Infrastructure to the clients, MMI also delivers total IT Solutions, including Supply Chain Management (SCM), E-Government (ERP), Consulting Services, and Managed-services.</div>', '2021-01-26 07:48:55', NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `contact_tb`
--

DROP TABLE IF EXISTS `contact_tb`;
CREATE TABLE IF NOT EXISTS `contact_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `departement` varchar(30) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `pesan` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `contact_tb`
--

INSERT INTO `contact_tb` (`id`, `name`, `email`, `departement`, `subject`, `pesan`) VALUES
(9, 'programmer', 'admin@admin.com', 'business inquery', 'sadasd', 'adasd');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `our_client_tb`
--

INSERT INTO `our_client_tb` (`id`, `preview_path`, `client_name`, `year`, `created_at`) VALUES
(1, '/client/PT_Makmur_Sejahtera_20210126145441.jpg', 'PT Makmur Sejahtera', '2021', '2021-01-26 07:54:41');

-- --------------------------------------------------------

--
-- Struktur dari tabel `our_project_tb`
--

DROP TABLE IF EXISTS `our_project_tb`;
CREATE TABLE IF NOT EXISTS `our_project_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `preview_path` text NOT NULL,
  `project_title` varchar(200) NOT NULL,
  `kind` varchar(10) NOT NULL,
  `client` varchar(200) NOT NULL,
  `technology` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `our_project_tb`
--

INSERT INTO `our_project_tb` (`id`, `preview_path`, `project_title`, `kind`, `client`, `technology`, `created_at`) VALUES
(3, '/project/ini_background_20210125164003.jpg', 'ini_background', 'web', 'PT MAJU MAKMUR SEJAHTERA', 'dsadsad', '2021-01-25 09:14:23'),
(4, '/project/ini_barcode_20210126144943.jpg', 'ini_barcode', 'app', 'PT MAJU MAKMUR SEJAHTERA', 'HTML', '2021-01-26 07:49:43');

-- --------------------------------------------------------

--
-- Struktur dari tabel `our_service_tb`
--

DROP TABLE IF EXISTS `our_service_tb`;
CREATE TABLE IF NOT EXISTS `our_service_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(40) NOT NULL,
  `icon_path` text NOT NULL,
  `short_wording` varchar(200) NOT NULL,
  `full_wording` text NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `our_service_tb`
--

INSERT INTO `our_service_tb` (`id`, `title`, `icon_path`, `short_wording`, `full_wording`, `created_at`) VALUES
(2, 'Services', 'bx bx-file', 'Voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi', 'testing edited aja', '2021-01-25 06:54:08'),
(3, 'penambahan', 'fa fa-file', 'ini adalah text singkat penuh makna yang tidak bisa ku sampaikan padamu', 'ini text yang sangat panjang ', '2021-01-28 07:47:44');

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
  `email` varchar(20) NOT NULL,
  `address` text NOT NULL,
  `website` varchar(50) NOT NULL,
  `twitter` varchar(30) NOT NULL,
  `instagram` varchar(30) NOT NULL,
  `facebook` varchar(30) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `our_team_tb`
--

INSERT INTO `our_team_tb` (`id`, `avatar_path`, `employee_name`, `potition`, `bio`, `email`, `address`, `website`, `twitter`, `instagram`, `facebook`, `created_at`) VALUES
(1, '/team/dasdsa_20210125105548.jpg', 'dasdsa', 'asdas', 'asdsad', '', '', '', '', '', '', '2021-01-25 03:55:48');

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
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `testimony_tb`
--

INSERT INTO `testimony_tb` (`id`, `user_ava_path`, `name_user`, `company`, `testimony_text`, `created_at`) VALUES
(3, '/testimony/ini_bola_20210126145001.jpg', 'ini_bola', 'fdsgsgs', 'fdsfedw', '2021-01-26 07:50:01');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
