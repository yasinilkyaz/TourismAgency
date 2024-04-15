-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 08 Kas 2023, 11:28:28
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
-- Veritabanı: `tourismagency`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `clients`
--

CREATE TABLE `clients` (
  `id` int NOT NULL,
  `list_id` int DEFAULT NULL,
  `name_surname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `client_type` enum('adult','child') COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `clients`
--

INSERT INTO `clients` (`id`, `list_id`, `name_surname`, `phone`, `email`, `client_type`) VALUES
(1, 1, 'Yasin İlkyaz', '5555555555', 'asdasdasd@asd.com', 'adult'),
(2, 1, 'Ali Şimşek', '555555555', 'asdasda@gma.co', 'adult');



-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hostel_type`
--

CREATE TABLE `hostel_type` (
  `id` int NOT NULL,
  `hotel_id` int DEFAULT NULL,
  `hostel_type_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hostel_type`
--

INSERT INTO `hostel_type` (`id`, `hotel_id`, `hostel_type_name`) VALUES
(15, 1, 'Ultra Herşey Dahil'),
(16, 2, 'Herşey Dahil'),
(17, 3, 'Oda Kahvaltı'),
(22, 1, 'Tam Pansiyon'),
(23, 2, 'Yarım Pansiyon'),
(24, 3, 'Sadece Yatak'),
(25, 2, 'Alkol Hariç Full credit'),
(29, 5, 'Alkol Hariç Full credit'),
(30, 6, 'Herşey Dahil');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hotels`
--

CREATE TABLE `hotels` (
  `id` int NOT NULL,
  `hotel_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `star_rating` int DEFAULT NULL,
  `facility_features` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `region` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hotels`
--

INSERT INTO `hotels` (`id`, `hotel_name`, `address`, `email`, `phone`, `star_rating`, `facility_features`, `region`) VALUES
(1, 'Hotel A', '123 Main St, İzmir', 'hotelA@example.com', '1234567890', 4, 'Ücretsiz WiFi, Yüzme Havuzu', 'İzmir'),
(2, 'Hotel B', '456 Elm St, Samsun', 'hotelB@example.com', '9876543210', 3, 'Fitness Center', 'Samsun'),
(3, 'Hotel C', '789 Oak St, Antalya', 'hotelC@example.com', '1112223333', 5, 'Ücretsiz Otopark, Spa', 'Antalya'),
(5, 'Hotel D', '8754 Sk. İstanbul', 'hoteld@example.com', '23156421', 5, 'Ücretsiz WiFi, Yüzme Havuzu, Ücretsiz Otopark', 'İstanbul'),
(6, 'Hotel F', '8754 Sk. Ayvalık Balıkesir', 'hotelf@example.com', '23156412', 4, 'Yüzme Havuzu, Ücretsiz Otopark', 'Balıkesir');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `period_management`
--

CREATE TABLE `period_management` (
  `id` int NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `period_management`
--

INSERT INTO `period_management` (`id`, `start_date`, `end_date`) VALUES
(1, '2021-01-01', '2021-05-31'),
(2, '2021-06-01', '2021-12-01');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `reservations`
--

CREATE TABLE `reservations` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  `check_in_date` date DEFAULT NULL,
  `check_out_date` date DEFAULT NULL,
  `adult_count` int DEFAULT NULL,
  `child_count` int DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `clients_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `reservations`
--

INSERT INTO `reservations` (`id`, `user_id`, `room_id`, `check_in_date`, `check_out_date`, `adult_count`, `child_count`, `total_price`, `clients_id`) VALUES
(1, 4, 16, '2021-08-08', '2021-08-09', 2, 0, 400.00, 1),
(3, 4, 19, '2021-03-03', '2021-03-08', 1, 3, 340.00, 2),
(4, 4, 15, '2021-03-03', '2021-03-05', 1, 1, 270.00, 3);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room_management`
--

CREATE TABLE `room_management` (
  `id` int NOT NULL,
  `hotel_id` int DEFAULT NULL,
  `room_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `stock_quantity` int DEFAULT NULL,
  `bed_count` int DEFAULT NULL,
  `television` char(3) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `minibar` char(3) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `hostel_type_id` int DEFAULT NULL,
  `period_management_id` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `price_child` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room_management`
--

INSERT INTO `room_management` (`id`, `hotel_id`, `room_name`, `stock_quantity`, `bed_count`, `television`, `minibar`, `hostel_type_id`, `period_management_id`, `price`, `price_child`) VALUES
(13, 1, 'Single Room', 10, 1, 'Var', 'Var', 15, 1, 150.00, 100.00),
(14, 1, 'Çift Oda', 5, 4, 'Var', 'Var', 22, 1, 150.00, 110.00),
(15, 2, 'Suit', 11, 2, 'Var', 'Yok', 17, 1, 120.00, 80.00),
(16, 1, 'Deniz Manzaralı', 3, 2, 'Var', 'Var', 22, 2, 200.00, 175.00),
(18, 5, 'Kral Odası Deniz Manzaralı', 3, 5, 'Var', 'Var', 29, 2, 700.00, 500.00),
(19, 3, 'Tek Oda', 4, 4, 'Var', 'Var', 24, 1, 100.00, 80.00);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room_price`
--

CREATE TABLE `room_price` (
  `id` int NOT NULL,
  `period_id` int DEFAULT NULL,
  `hostel_type_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `price_child` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room_price`
--

INSERT INTO `room_price` (`id`, `period_id`, `hostel_type_name`, `price`, `price_child`) VALUES
(1, 1, 'Ultra Herşey Dahil', 200.00, 150.00),
(2, 1, 'Herşey Dahil', 175.00, 125.00),
(3, 1, 'Oda Kahvaltı', 50.00, 20.00),
(4, 1, 'Tam Pansiyon', 100.00, 75.00),
(5, 1, 'Yarım Pansiyon', 50.00, 40.00),
(6, 1, 'Sadece Yatak', 0.00, 0.00),
(7, 1, 'Alkol Hariç Full credit', 500.00, 350.00),
(8, 2, 'Ultra Herşey Dahil', 400.00, 300.00),
(9, 2, 'Herşey Dahil', 300.00, 175.00),
(10, 2, 'Oda Kahvaltı', 150.00, 90.00),
(11, 2, 'Tam Pansiyon', 200.00, 150.00),
(12, 2, 'Yarım Pansiyon', 100.00, 80.00),
(13, 2, 'Sadece Yatak', 0.00, 0.00),
(14, 2, 'Alkol Hariç Full credit', 1000.00, 700.00);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `user_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `role` enum('admin','employee') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `users`
--

INSERT INTO `users` (`id`, `user_name`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'employee', 'employee', 'employee'),
(3, '1', '1', 'admin'),
(4, '2', '2', 'employee');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `hostel_type`
--
ALTER TABLE `hostel_type`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hotel_id` (`hotel_id`);

--
-- Tablo için indeksler `hotels`
--
ALTER TABLE `hotels`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `period_management`
--
ALTER TABLE `period_management`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `room_id` (`room_id`);

--
-- Tablo için indeksler `room_management`
--
ALTER TABLE `room_management`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hotel_id` (`hotel_id`),
  ADD KEY `hostel_type_id` (`hostel_type_id`),
  ADD KEY `period_management_id` (`period_management_id`);

--
-- Tablo için indeksler `room_price`
--
ALTER TABLE `room_price`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Tablo için indeksler `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `clients`
--
ALTER TABLE `clients`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `hostel_type`
--
ALTER TABLE `hostel_type`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Tablo için AUTO_INCREMENT değeri `hotels`
--
ALTER TABLE `hotels`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `period_management`
--
ALTER TABLE `period_management`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `room_management`
--
ALTER TABLE `room_management`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Tablo için AUTO_INCREMENT değeri `room_price`
--
ALTER TABLE `room_price`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Tablo için AUTO_INCREMENT değeri `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `hostel_type`
--
ALTER TABLE `hostel_type`
  ADD CONSTRAINT `hostel_type_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`id`);

--
-- Tablo kısıtlamaları `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `room_management` (`id`);

--
-- Tablo kısıtlamaları `room_management`
--
ALTER TABLE `room_management`
  ADD CONSTRAINT `room_management_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`id`),
  ADD CONSTRAINT `room_management_ibfk_2` FOREIGN KEY (`hostel_type_id`) REFERENCES `hostel_type` (`id`),
  ADD CONSTRAINT `room_management_ibfk_3` FOREIGN KEY (`period_management_id`) REFERENCES `period_management` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
