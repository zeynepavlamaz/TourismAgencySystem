-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 23 Kas 2023, 01:21:17
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `tourism_agency`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `customer`
--

CREATE TABLE `customer` (
  `id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `room_id` int NOT NULL,
  `name_surname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `customer_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `adult_number` int NOT NULL,
  `child_number` int NOT NULL,
  `total_price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `customer`
--

INSERT INTO `customer` (`id`, `hotel_id`, `room_id`, `name_surname`, `email`, `phone_number`, `customer_ID`, `adult_number`, `child_number`, `total_price`) VALUES
(27, 31, 86, 'Mustafa Kemal ATATÜRK', 'atatürk@gmail.com', '193∞', '193∞', 1, 0, 1917),
(28, 26, 71, 'arzu avlamaz', 'avlamazarzu@gmail.com', '0536 678 66 77', '123456789101', 1, 0, 5000),
(30, 27, 77, 'erol avlamaz', 'avlamazerol@gmail.com', '0589 356 23 34', '123456789011', 1, 0, 70350),
(31, 30, 84, 'hakan çatıkkaya', 'hhçatıkkaya@gmail.com', '0523 435 18 98', '56473829101', 1, 0, 35000),
(33, 30, 85, 'avlamaz', 'avlamaz@gmail.com', '0560 606 60 06', '60606060606', 4, 0, 140000),
(34, 27, 79, 'osman avlamaz', 'oavlamaz@gmail.com', '9172361471', '3186402384', 4, 0, 140000),
(37, 30, 84, 'eliza yağmur polat ', 'deniz@gmail.com', '128361826', '213892873', 1, 0, 20000),
(38, 29, 82, 'ali şimşek', 'ali@gmail.com', '29837492346', '23672084327', 1, 0, 9400),
(39, 29, 83, 'Özge/Kübra', 'özgekübra@gmail.com', '9238245629124', '238624914692', 2, 0, 201000);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `facility_features`
--

CREATE TABLE `facility_features` (
  `id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `features` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `facility_features`
--

INSERT INTO `facility_features` (`id`, `hotel_id`, `features`) VALUES
(74, 26, 'Wifi'),
(75, 26, '7/24 Service'),
(76, 26, 'Concierge'),
(77, 26, 'Fitness'),
(78, 26, 'Park'),
(79, 26, 'Pool'),
(80, 26, 'SPA'),
(81, 27, 'Wifi'),
(82, 27, '7/24 Service'),
(83, 27, 'Concierge'),
(84, 27, 'Fitness'),
(85, 27, 'Park'),
(86, 27, 'Pool'),
(87, 27, 'SPA'),
(88, 28, 'Concierge'),
(89, 28, 'Park'),
(90, 29, 'Wifi'),
(91, 29, '7/24 Service'),
(92, 29, 'Concierge'),
(93, 29, 'Fitness'),
(94, 29, 'Park'),
(95, 29, 'SPA'),
(96, 30, 'Concierge'),
(97, 30, 'Park'),
(98, 31, 'Wifi'),
(99, 31, '7/24 Service'),
(100, 31, 'Concierge'),
(101, 31, 'Fitness'),
(102, 31, 'Park'),
(103, 31, 'Pool'),
(104, 31, 'SPA');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hotel`
--

CREATE TABLE `hotel` (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `adress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `star` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `city`, `region`, `adress`, `email`, `phone_number`, `star`) VALUES
(26, 'Anibal', 'Kocaeli', 'Gebze', 'Sultan Orhan mah. Hükümet cad. no: 127', 'hotelanibal@gmail.com', '0541 414 41 41', 3),
(27, 'ramada', 'sakarya', 'serdivan', 'kemalpaşa mah. üniversite cad. no/13 ', 'ramada@gmail.com', '0213 444 54 54', 5),
(28, 'luxary', 'istanbul', 'ümraniye', 'çakmak mah. çakmak sok. no/12', 'luxaryhotel@gmail.com', '0534 343 34 34', 4),
(29, 'patika', 'patika', 'patika', 'patika', 'patika@gmail.com', '0222 222 22 22', 5),
(30, 'New Baltürk', 'kocaeli', 'izmit', 'Kadikoy Mahallesi., Yahya Kaptan Sokak No:38 İzmit, 41040, İzmit, Türkiye', 'baltürk@gmail.com', '02623112311', 4),
(31, 'Pera Palace Hotel', 'istanbul', 'beyoğlu', 'Mesrutiyet Caddesi 52 Tepebasi Beyoglu, 34430, İstanbul, Türkiye', 'perapalace@gmail.com', '02123774000 ', 5);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `pension`
--

CREATE TABLE `pension` (
  `id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `pension` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `pension`
--

INSERT INTO `pension` (`id`, `hotel_id`, `pension`) VALUES
(64, 26, 'Only Bed'),
(65, 26, 'Full Pension'),
(66, 26, 'Room Service'),
(67, 26, 'Half Pension'),
(68, 26, 'Ultra Pension'),
(69, 26, 'No Alcohol Full Credit'),
(70, 27, 'Only Bed'),
(71, 27, 'Full Pension'),
(72, 27, 'Room Service'),
(73, 27, 'Half Pension'),
(74, 27, 'Ultra Pension'),
(75, 27, 'No Alcohol Full Credit'),
(76, 28, 'Full Pension'),
(77, 28, 'Room Service'),
(78, 28, 'No Alcohol Full Credit'),
(79, 29, 'Full Pension'),
(80, 29, 'Ultra Pension'),
(81, 30, 'No Alcohol Full Credit'),
(82, 31, 'Full Pension');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room`
--

CREATE TABLE `room` (
  `id` int NOT NULL,
  `room` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL,
  `season_id` int NOT NULL,
  `pension_id` int NOT NULL,
  `stock` int NOT NULL,
  `adult_price` int NOT NULL,
  `child_price` int NOT NULL,
  `bed` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room`
--

INSERT INTO `room` (`id`, `room`, `hotel_id`, `season_id`, `pension_id`, `stock`, `adult_price`, `child_price`, `bed`) VALUES
(71, 'Single', 26, 51, 64, 9, 1000, 500, 1),
(72, 'Double', 26, 51, 64, 10, 1500, 750, 2),
(73, 'King', 26, 51, 64, 10, 2000, 100, 2),
(74, 'Single', 26, 52, 64, 10, 1250, 750, 2),
(75, 'Double', 26, 52, 64, 10, 2150, 880, 2),
(76, 'King', 26, 52, 64, 10, 3000, 1500, 5),
(77, 'Double', 27, 53, 75, 9, 2345, 1234, 2),
(78, 'Double', 27, 54, 72, 10, 3345, 2234, 2),
(79, 'King', 27, 54, 74, 9, 15000, 10000, 5),
(80, 'Double', 28, 55, 77, 10, 3450, 2000, 3),
(81, 'Double', 28, 56, 78, 10, 3600, 2500, 3),
(82, 'King', 29, 58, 79, 9, 2350, 1150, 3),
(83, 'King', 29, 57, 80, 9, 3350, 2500, 5),
(84, 'Double', 30, 60, 81, 8, 5000, 2500, 1),
(85, 'King', 30, 60, 81, 9, 7000, 3500, 5),
(86, 'King', 31, 61, 82, 0, 1917, 1917, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room_features`
--

CREATE TABLE `room_features` (
  `id` int NOT NULL,
  `room_id` int NOT NULL,
  `features` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room_features`
--

INSERT INTO `room_features` (`id`, `room_id`, `features`) VALUES
(81, 83, 'Air Conditioner'),
(82, 83, 'Safe Case'),
(83, 84, 'Air Conditioner'),
(84, 84, 'Safe Case'),
(85, 84, 'Gaming Console');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `season`
--

CREATE TABLE `season` (
  `id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `season_start` date NOT NULL,
  `season_end` date NOT NULL,
  `season_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `season`
--

INSERT INTO `season` (`id`, `hotel_id`, `season_start`, `season_end`, `season_type`) VALUES
(51, 26, '2023-01-01', '2023-05-31', 'first half '),
(52, 26, '2023-06-01', '2023-12-31', 'second half'),
(53, 27, '2023-01-01', '2023-05-31', 'first half'),
(54, 27, '2023-01-06', '2023-12-31', 'second half'),
(55, 28, '2023-01-01', '2023-05-31', 'first half'),
(56, 28, '2023-01-06', '2023-12-31', 'second half'),
(57, 29, '2023-01-01', '2023-05-31', 'first half'),
(58, 29, '2023-06-01', '2023-12-31', 'second half'),
(59, 30, '2023-01-01', '2023-05-31', 'first half'),
(60, 30, '2023-06-01', '2023-12-31', 'second half'),
(61, 31, '2023-01-01', '2023-05-31', 'first half'),
(62, 31, '2023-06-01', '2023-12-31', 'second half');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` enum('admin','user') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `name`, `username`, `password`, `type`) VALUES
(1, 'admin', 'admin', 'admin', 'admin'),
(3, 'zeynep', 'zavlamaz', '12345', 'user'),
(6, 'eliza yağmur deniz polat', 'epolat', '12345', 'user'),
(7, 'hilal bozkaya', 'hbozkaya', '12345', 'user'),
(8, 'arzu avlamaz', 'aavlamaz', '12345', 'user'),
(9, 'erol avlamaz', 'eavlamaz', '12345', 'user'),
(10, 'osman avlamaz', 'oavlamaz', '12345', 'user'),
(14, 'örnek', 'örnek', '12345', 'user');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `facility_features`
--
ALTER TABLE `facility_features`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `pension`
--
ALTER TABLE `pension`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room_features`
--
ALTER TABLE `room_features`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `season`
--
ALTER TABLE `season`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Tablo için AUTO_INCREMENT değeri `facility_features`
--
ALTER TABLE `facility_features`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;

--
-- Tablo için AUTO_INCREMENT değeri `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Tablo için AUTO_INCREMENT değeri `pension`
--
ALTER TABLE `pension`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- Tablo için AUTO_INCREMENT değeri `room`
--
ALTER TABLE `room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

--
-- Tablo için AUTO_INCREMENT değeri `room_features`
--
ALTER TABLE `room_features`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- Tablo için AUTO_INCREMENT değeri `season`
--
ALTER TABLE `season`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- Tablo için AUTO_INCREMENT değeri `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
