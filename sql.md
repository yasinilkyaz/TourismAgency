# Turizm Ajansı Veritabanı

Bu belge, bir turizm ajansı için kullanılan veritabanının yapısını ve örnek verilerini içermektedir. Veritabanı, müşteriler, otel türleri, oteller, dönem yönetimi, rezervasyonlar, oda yönetimi, oda fiyatları ve kullanıcılar gibi önemli bilgileri saklamak için tasarlanmıştır.

## Veritabanı Yapısı

Veritabanı aşağıdaki tabloları içermektedir:

- `clients`: Müşteri bilgilerini tutar.
- `hostel_type`: Otel türleri ve özelliklerini saklar.
- `hotels`: Otellerin detaylı bilgilerini içerir.
- `period_management`: Dönem yönetimi için kullanılır.
- `reservations`: Rezervasyon bilgilerini saklar.
- `room_management`: Otellerin oda bilgilerini içerir.
- `room_price`: Oda fiyatlarını tutar.
- `users`: Kullanıcı bilgilerini saklar.

Aşağıda veritabanının SQL kodları bulunmaktadır:

## Tablo Oluşturma SQL Kodları

### `clients` Tablosu

```sql
CREATE TABLE `clients` (
  `id` int NOT NULL,
  `list_id` int DEFAULT NULL,
  `name_surname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `client_type` enum('adult','child') COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```
### `clients` Veri Örneği
```sql
INSERT INTO `clients` (`id`, `list_id`, `name_surname`, `phone`, `email`, `client_type`) VALUES
(1, 1, 'Yasin İlkyaz', '5555555555', 'asdasdasd@asd.com', 'adult'),
(2, 1, 'Ali Şimşek', '555555555', 'asdasda@gma.co', 'adult');
```

### `hostel_type` Tablosu

```sql
CREATE TABLE `hostel_type` (
  `id` int NOT NULL,
  `hotel_id` int DEFAULT NULL,
  `hostel_type_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```
### `hostel_type` Veri Örneği
```sql
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
```
### `hotels` Tablosu
```sql
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
```
### `hotels` Veri Örneği
```sql
INSERT INTO `hotels` (`id`, `hotel_name`, `address`, `email`, `phone`, `star_rating`, `facility_features`, `region`) VALUES
(1, 'Hotel A', '123 Main St, İzmir', 'hotelA@example.com', '1234567890', 4, 'Ücretsiz WiFi, Yüzme Havuzu', 'İzmir'),
(2, 'Hotel B', '456 Elm St, Samsun', 'hotelB@example.com', '9876543210', 3, 'Fitness Center', 'Samsun'),
(3, 'Hotel C', '789 Oak St, Antalya', 'hotelC@example.com', '1112223333', 5, 'Ücretsiz Otopark, Spa', 'Antalya'),
(5, 'Hotel D', '8754 Sk. İstanbul', 'hoteld@example.com', '23156421', 5, 'Ücretsiz WiFi, Yüzme Havuzu, Ücretsiz Otopark', 'İstanbul'),
(6, 'Hotel F', '8754 Sk. Ayvalık Balıkesir', 'hotelf@example.com', '23156412', 4, 'Yüzme Havuzu, Ücretsiz Otopark', 'Balıkesir');
```
### `period_management` Tablosu
```sql
CREATE TABLE `period_management` (
`id` int NOT NULL,
`start_date` date DEFAULT NULL,
`end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```
### `period_management` Veri Örneği
```sql
INSERT INTO `period_management` (`id`, `start_date`, `end_date`) VALUES
(1, '2021-01-01', '2021-05-31'),
(2, '2021-06-01', '2021-12-01');
```
### `reservations` Tablosu
```sql
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
```
### `reservations` Veri Örneği
```sql
INSERT INTO `reservations` (`id`, `user_id`, `room_id`, `check_in_date`, `check_out_date`, `adult_count`, `child_count`, `total_price`, `clients_id`) VALUES
(1, 4, 16, '2021-08-08', '2021-08-09', 2, 0, 400.00, 1),
(3, 4, 19, '2021-03-03', '2021-03-08', 1, 3, 340.00, 2),
(4, 4, 15, '2021-03-03', '2021-03-05', 1, 1, 270.00, 3);

```
### `room_management` Tablosu
```sql
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
```
### `room_management` Veri Örneği
```sql
INSERT INTO `room_management` (`id`, `hotel_id`, `room_name`, `stock_quantity`, `bed_count`, `television`, `minibar`, `hostel_type_id`, `period_management_id`, `price`, `price_child`) VALUES
(13, 1, 'Single Room', 10, 1, 'Var', 'Var', 15, 1, 150.00, 100.00),
(14, 1, 'Çift Oda', 5, 4, 'Var', 'Var', 22, 1, 150.00, 110.00),
(15, 2, 'Suit', 11, 2, 'Var', 'Yok', 17, 1, 120.00, 80.00),
(16, 1, 'Deniz Manzaralı', 3, 2, 'Var', 'Var', 22, 2, 200.00, 175.00),
(18, 5, 'Kral Odası Deniz Manzaralı', 3, 5, 'Var', 'Var', 29, 2, 700.00, 500.00),
(19, 3, 'Tek Oda', 4, 4, 'Var', 'Var', 24, 1, 100.00, 80.00);

```
### `room_price` Tablosu
```sql
CREATE TABLE `room_price` (
  `id` int NOT NULL,
  `period_id` int DEFAULT NULL,
  `hostel_type_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `price_child` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```
### `room_price` Veri Örneği
```sql
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
```
### `users` Tablosu
```sql
CREATE TABLE `users` (
  `id` int NOT NULL,
  `user_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `role` enum('admin','employee') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```
### `users` Veri Örneği
```sql
INSERT INTO `users` (`id`, `user_name`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'employee', 'employee', 'employee'),
(3, '1', '1', 'admin'),
(4, '2', '2', 'employee');
```

### Tablolar için Indeksler

```sql
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `hostel_type`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hotel_id` (`hotel_id`);

ALTER TABLE `hotels`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `period_management`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `room_id` (`room_id`);

ALTER TABLE `room_management`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hotel_id` (`hotel_id`),
  ADD KEY `hostel_type_id` (`hostel_type_id`),
  ADD KEY `period_management_id` (`period_management_id`);

ALTER TABLE `room_price`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);
```
### Tablolar için AUTO_INCREMENT değerleri
```sql
ALTER TABLE `clients`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

ALTER TABLE `hostel_type`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

ALTER TABLE `hotels`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

ALTER TABLE `period_management`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `reservations`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

ALTER TABLE `room_management`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

ALTER TABLE `room_price`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
```
### Tablolar için Kısıtlamalar
```sql
ALTER TABLE `hostel_type`
  ADD CONSTRAINT `hostel_type_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`id`);

ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `room_management` (`id`);

ALTER TABLE `room_management`
  ADD CONSTRAINT `room_management_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`id`),
  ADD CONSTRAINT `room_management_ibfk_2` FOREIGN KEY (`hostel_type_id`) REFERENCES `hostel_type` (`id`),
  ADD CONSTRAINT `room_management_ibfk_3` FOREIGN KEY (`period_management_id`) REFERENCES `period_management` (`id`);