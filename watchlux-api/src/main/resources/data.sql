USE watchlux;

-- Insertion de clients
INSERT INTO customer (email, name, password, role) VALUES
('alice@example.com', 'Alice Dupont', 'password_hash_1', 'ROLE_USER'),
('bob@example.com', 'Bob Martin', 'password_hash_2', 'ROLE_ADMIN');

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
