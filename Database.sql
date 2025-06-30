USE tourbooking;

-- Thêm role 
INSERT INTO role (name) VALUES 
('CUSTOMER'), ('ADMIN'), ('STAFF');

-- Thêm account
INSERT INTO account (username, password, is_lock) VALUES
('admin', '123456', false),
('customer', '123456', false),
('staff', '123456', false);

-- Thêm user 
INSERT INTO user (
    fullname, email, phone_number, birthday, address, sex, avatar, created_at, updated_at,
    role_id, account_id
) VALUES
('Quản trị viên', 'admin@gmail.com', '0123456789', '1990-01-01', 'Hà Nội', 1, 'https://i.pinimg.com/736x/6e/af/1a/6eaf1a844ae4b6fa6eeb6ff17f468cc0.jpg', NOW(), NOW(), 2, 1),
('Mai Ánh', 'vubinh.2004.17.7@gmail.com', '0393712562', '1990-01-01', 'Hà Nội', 1, 'https://i.pinimg.com/736x/6e/af/1a/6eaf1a844ae4b6fa6eeb6ff17f468cc0.jpg', NOW(), NOW(), 1, 2),
('Nhân viên', 'staff@gmail.com', '0123456789', '1990-01-01', 'Hà Nội', 1, 'https://i.pinimg.com/736x/6e/af/1a/6eaf1a844ae4b6fa6eeb6ff17f468cc0.jpg', NOW(), NOW(), 3, 3);

-- Thêm cart
insert into cart (created_at, updated_at, user_id) values ('2025-01-01','2025-01-01',2);
-- Thêm loại địa điểm
INSERT INTO category (name) VALUES
('Danh lam thắng cảnh'),
('Di tích lịch sử'),
('Khu du lịch sinh thái');

-- Thêm địa điểm
INSERT INTO tourist_attraction (name, description, location, created_at, updated_at, category_id) VALUES
(
  'Vịnh Hạ Long',
  'Vịnh Hạ Long là một trong những kỳ quan thiên nhiên thế giới với hàng ngàn đảo đá vôi tuyệt đẹp.',
  'Quảng Ninh',
  NOW(), NOW(), 1
),
(
  'Cố đô Huế',
  'Cố đô Huế mang nét cổ kính với các di tích triều Nguyễn như Hoàng thành, lăng tẩm và đền đài.',
  'Thừa Thiên Huế',
  NOW(), NOW(), 2
),
(
  'Vườn quốc gia Cúc Phương',
  'Vườn quốc gia Cúc Phương là khu rừng nguyên sinh đầu tiên của Việt Nam, nơi có hệ động thực vật phong phú.',
  'Ninh Bình',
  NOW(), NOW(), 3
),
(
  'Bà Nà Hills',
  'Khu du lịch nổi tiếng với Cầu Vàng, khí hậu mát mẻ và cảnh quan châu Âu thu nhỏ.',
  'Đà Nẵng',
  NOW(), NOW(), 1
),
(
  'Địa đạo Củ Chi',
  'Hệ thống đường hầm lịch sử trong kháng chiến chống Mỹ, thể hiện tinh thần chiến đấu kiên cường.',
  'TP. Hồ Chí Minh',
  NOW(), NOW(), 2
),
(
  'Thác Bản Giốc',
  'Một trong những thác nước đẹp nhất Việt Nam, nằm giáp biên giới Trung Quốc.',
  'Cao Bằng',
  NOW(), NOW(), 1
);

-- Thêm ảnh địa điểm
INSERT INTO galley (thumb_nail, tourist_attraction_id) VALUES
-- Vịnh Hạ Long (id = 1)
('https://vnproperty.com.vn/wp-content/uploads/2022/09/ha-long-thuoc-tinh-nao-2.jpg', 1),
('https://vnproperty.com.vn/wp-content/uploads/2022/09/ha-long-thuoc-tinh-nao-1.jpg', 1),
('https://vnproperty.com.vn/wp-content/uploads/2022/09/ha-long-thuoc-tinh-nao-1.jpg', 1),

-- Cố đô Huế (id = 2)
('https://apibeta.baoninhbinh.org.vn/user-blob/15088545-560b-d200-1fa2-cc31adda5a44/2024/11/20/hue.jpg', 2),
('https://danangopentour.vn/uploads/images/images/ngo-mon-hue.jpeg', 2),

-- Vườn quốc gia Cúc Phương (id = 3)
('https://baothainguyen.vn/file/oldimage/baothainguyen/UserFiles/image/20201207155042_cuc2.jpg', 3),
('https://mia.vn/media/uploads/blog-du-lich/vuon-quoc-gia-cuc-phuong-9-1733382755.jpg', 3),

-- Bà Nà Hills (id = 4)
('https://danangsensetravel.com/view-800/at_gioi-thieu-ve-ba-na-hills_e7801426a1ba773d4ab032e36b110795.jpg', 4),
('https://dulichyenviet.com/wp-content/uploads/2022/06/dia-diem-du-lich-ba-na-hills-1.jpg', 4),
('https://images2.thanhnien.vn/528068263637045248/2024/3/27/ban-sao-cua-sun-world-ba-na-hills-4-17115055398311570071595.jpg', 4),

-- Địa đạo Củ Chi (id = 5)
('https://sakos.vn/wp-content/uploads/2024/06/gt-1-2-2.jpg', 5),
('https://datviettour.com.vn/uploads/images/mien-nam/cu-chi/hinh-danh-thang/cu-chi-1.JPG', 5),

-- Thác Bản Giốc (id = 6)
('https://vj-prod-website-cms.s3.ap-southeast-1.amazonaws.com/w2-1715749810964.jpg', 6),
('https://hanotour.com.vn/upload_images/images/thac_B%E1%BA%A3n_Gi%E1%BB%91c.jpg', 6);

-- Thêm tour route 
INSERT INTO tour_route (route_name, start_location, end_location, start_date, end_date, image, created_at, updated_at) VALUES
('Hành trình khám phá miền Bắc', 'Hà Nội', 'Cao Bằng', '2025-07-06', '2025-07-08', 'https://www.vietnambooking.com/wp-content/uploads/2019/10/tour-ha-noi-ha-long-1-ngay-6.jpg', NOW(), NOW()),
('Khám phá miền Trung', 'Huế', 'Đà Nẵng', '2025-07-10', '2025-07-14', 'https://truongsatourist.net/uploads/image/images/hoi-an-%20truong%20sa%20tourist-min.jpg', NOW(), NOW()),
('Hành trình về nguồn', 'TP. Hồ Chí Minh', 'Củ Chi', '2025-08-01', '2025-08-02', 'https://sontungtravel.vn/wp-content/uploads/2019/12/image007-2.jpg', NOW(), NOW());

-- Thêm tour route  attraction
INSERT INTO tour_route_attraction (order_action, action_description, day, tour_route_id, tourist_attraction_id) VALUES
(1, 'Tham quan Vịnh Hạ Long bằng du thuyền', 1, 1, 1),
(1, 'Khám phá hang Sửng Sốt và đảo Titop', 2, 1, 1),

(2, 'Khám phá hang Sửng Sốt và đảo Titop', 2, 1, 1),
(3, 'Chiêm ngưỡng Thác Bản Giốc', 3, 1, 6),

(1, 'Thăm Đại Nội Huế và lăng Tự Đức', 1, 2, 2),
(2, 'Check-in tại Bà Nà Hills', 2, 2, 4),

(1, 'Tham quan Địa đạo Củ Chi', 1, 3, 5);
-- Thêm tour-- 
INSERT INTO tour (
  depature_date, return_date, status, price, total_seats, booked_seats,
  pick_up_time, pick_up_location, created_at, updated_at, tour_route_id
) VALUES
('2025-07-08 08:00:00', '2025-07-10 18:00:00', 0, 5500000, 30, 10, '2025-07-01 06:00:00', 'Hà Nội - Bến xe Mỹ Đình', NOW(), NOW(), 1),
('2025-07-08 08:00:00', '2025-07-12 18:00:00', 0, 5500000, 30, 12, '2025-07-08 06:00:00', 'Hà Nội - Bến xe Mỹ Đình', NOW(), NOW(), 1),
('2025-07-15 08:00:00', '2025-07-19 18:00:00', 0, 5500000, 30, 8,  '2025-07-15 06:00:00', 'Hà Nội - Bến xe Mỹ Đình', NOW(), NOW(), 1);
INSERT INTO tour (
  depature_date, return_date, status, price, total_seats, booked_seats,
  pick_up_time, pick_up_location, created_at, updated_at, tour_route_id
) VALUES
('2025-07-10 07:30:00', '2025-07-14 17:00:00', 0, 4700000, 40, 25, '2025-07-10 05:30:00', 'Huế - Ga Huế', NOW(), NOW(), 2),
('2025-07-17 07:30:00', '2025-07-21 17:00:00', 0, 4700000, 40, 20, '2025-07-17 05:30:00', 'Huế - Ga Huế', NOW(), NOW(), 2),
('2025-07-24 07:30:00', '2025-07-28 17:00:00', 0, 4700000, 40, 18, '2025-07-24 05:30:00', 'Huế - Ga Huế', NOW(), NOW(), 2);
INSERT INTO tour (
  depature_date, return_date, status, price, total_seats, booked_seats,
  pick_up_time, pick_up_location, created_at, updated_at, tour_route_id
) VALUES
('2025-08-01 09:00:00', '2025-08-02 16:00:00', 0, 1800000, 50, 12, '2025-08-01 07:00:00', 'TP.HCM - Công viên 23/9', NOW(), NOW(), 3),
('2025-08-08 09:00:00', '2025-08-09 16:00:00', 0, 1800000, 50, 10, '2025-08-08 07:00:00', 'TP.HCM - Công viên 23/9', NOW(), NOW(), 3),
('2025-08-15 09:00:00', '2025-08-16 16:00:00', 0, 1800000, 50, 8,  '2025-08-15 07:00:00', 'TP.HCM - Công viên 23/9', NOW(), NOW(), 3);

-- Thêm usermember
INSERT INTO user_member (
    fullname,
    email,
    phone_number,
    birthday,
    sex,
    created_at,
    updated_at,
    user_id
) VALUES
(
    'Mai Ánh',
    'vubinh.2004.17.7@gmail.com',
    '0393712562',
    '1990-01-01',
    false,
    NOW(),
    NOW(),
    2
),
(
    'Nguyễn Văn An',
    'an.nguyen@example.com',
    '0912345678',
    '1995-06-15',
    true,
    NOW(),
    NOW(),
    2
),
(
    'Trần Thị Bích',
    'bich.tran@example.com',
    '0987654321',
    '1998-09-25',
    false,
    NOW(),
    NOW(),
    2
),
(
    'Lê Hữu Tài',
    'tai.le@example.com',
    '0901234567',
    '1992-03-10',
    true,
    NOW(),
    NOW(),
    2
);

-- Thêm hóa đơn
INSERT INTO invoice (
    total_amount,
    payment_status,
    payment_method,
    created_at,
    updated_at
) VALUES
(
    24567000,         -- Tổng tiền cho invoice 1
    true,             -- Đã thanh toán
    'Chuyển khoản',
    NOW(),
    NOW()
),
(
    16378000,         -- Tổng tiền cho invoice 2
    false,            -- Chưa thanh toán
    'Tiền mặt',
    NOW(),
    NOW()
),
(
    32756000,         -- Tổng tiền cho invoice 3
    true,
    'Chuyển khoản',
    NOW(),
    NOW()
);

-- Thêm tourbooking
-- Invoice 1: 1 tour booking
INSERT INTO tour_booking (seats_booked, total_price, created_at, updated_at, tour_id, user_id, invoice_id)
VALUES
(2, 16378000, NOW(), NOW(), 1, 2, 1); -- 2 ghế, tour 1

-- Invoice 2: 2 tour bookings
INSERT INTO tour_booking (seats_booked, total_price, created_at, updated_at, tour_id, user_id, invoice_id)
VALUES
(1, 8189000, NOW(), NOW(), 2, 2, 2), -- tour 2
(2, 16378000, NOW(), NOW(), 3, 2, 2); -- tour 3

-- Invoice 3: 3 tour bookings
INSERT INTO tour_booking (seats_booked, total_price, created_at, updated_at, tour_id, user_id, invoice_id)
VALUES
(1, 8189000, NOW(), NOW(), 4, 2, 3), -- tour 4
(2, 16378000, NOW(), NOW(), 5, 2, 3), -- tour 5
(1, 8189000, NOW(), NOW(), 6, 2, 3); -- tour 6

-- Thêm tourbookingdetail
-- Booking 1: 1 người (user_member_id = 1), là người liên hệ
INSERT INTO tour_booking_detail (is_contact, created_at, updated_at, user_member_id, tour_booking_id)
VALUES (true, NOW(), NOW(), 1, 1);

-- Booking 2: 2 người (user_member_id = 1,2), 1 người liên hệ
INSERT INTO tour_booking_detail (is_contact, created_at, updated_at, user_member_id, tour_booking_id)
VALUES 
(true, NOW(), NOW(), 1, 2),
(false, NOW(), NOW(), 2, 2);

-- Booking 3: 1 người (user_member_id = 3)
INSERT INTO tour_booking_detail (is_contact, created_at, updated_at, user_member_id, tour_booking_id)
VALUES (true, NOW(), NOW(), 3, 3);

-- Booking 4: 2 người (user_member_id = 2,3)
INSERT INTO tour_booking_detail (is_contact, created_at, updated_at, user_member_id, tour_booking_id)
VALUES 
(false, NOW(), NOW(), 2, 4),
(true, NOW(), NOW(), 3, 4);

-- Booking 5: 1 người (user_member_id = 1)
INSERT INTO tour_booking_detail (is_contact, created_at, updated_at, user_member_id, tour_booking_id)
VALUES (true, NOW(), NOW(), 1, 5);

-- Booking 6: 2 người (user_member_id = 2,3)
INSERT INTO tour_booking_detail (is_contact, created_at, updated_at, user_member_id, tour_booking_id)
VALUES 
(true, NOW(), NOW(), 2, 6),
(false, NOW(), NOW(), 3, 6);
 
-- Thêm cart-item
INSERT INTO cart_item (quantity, price, tour_id, cart_id, departure_day) VALUES
(1, 5500000, 1, 1, now()),  -- 2 vé
(2, 11000000, 4, 1, now()),   -- 1 vé
(3, 16500000, 8, 1, now());  -- 3 vé

-- Tour yêu thích
INSERT INTO favorite_tour (created_at, user_id, tour_route_id) VALUES
(NOW(), 2, 1),
(NOW(), 2, 3);

 
 
 
 