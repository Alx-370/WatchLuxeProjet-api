USE watchlux;

-- Insertion de clients
INSERT INTO customer (email, name, password, role) VALUES
('alice@example.com', 'Alice Dupont', '$2a$10$TrfL8F7/1fR6eUeT8lt2he1bUQ/2fy4O7nXxG6.jM.m1Z.Kny1G3a', 'ROLE_USER'),
('bob@example.com', 'Bob Martin', '$2a$10$jdQ4oM48XANv6nt.P8WDLu9f4uy3PxhxNdyJh0kUpO3K77IqoHyMi', 'ROLE_ADMIN');

-- Insertion de produits
INSERT INTO product (name, description, price, stock, image) VALUES
('Montre Classic', 'Montre classique élégante', 199.99, 10, 'https://exemple.com/images/classic.jpg'),
('Montre Sport', 'Montre sport avec chronomètre', 249.50, 5, 'https://exemple.com/images/sport.jpg'),
('Montre Luxe', 'Montre de luxe en or', 9999.99, 2, 'https://exemple.com/images/luxe.jpg');

-- Insertion de commandes
INSERT INTO orders (order_date, customer_id) VALUES
('2025-08-01 10:30:00', 1),
('2025-08-02 15:45:00', 1),
('2025-08-03 12:00:00', 2);

-- Insertion des items de commande
INSERT INTO order_item (order_id, product_id, quantity) VALUES
(1, 1, 2),
(1, 2, 1),
(2, 3, 1),
(3, 2, 2);
