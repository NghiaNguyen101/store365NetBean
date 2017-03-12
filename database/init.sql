DROP DATABASE IF EXISTS CPE365_Store;
CREATE DATABASE CPE365_Store;
USE CPE365_Store;

DROP TABLE IF EXISTS itemTable; 
DROP TABLE IF EXISTS customerTable;
DROP TABLE IF EXISTS purchaseTable;
DROP TABLE IF EXISTS transactionTable;

CREATE TABLE itemTable (
   id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(128) NOT NULL,
   price DECIMAL(7,2) NOT NULL,
   description VARCHAR(128) NULL,
   manufacturer VARCHAR(64) NOT NULL,
   stock INT UNSIGNED NOT NULL DEFAULT 0,
   discontinued TINYINT(1) NOT NULL DEFAULT 0
);

CREATE TABLE customerTable (
   id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(50) NOT NULL,
   address VARCHAR(128) NOT NULL
);

CREATE TABLE transactionTable (
   id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
   customer_id INT UNSIGNED NOT NULL,
   credit_card VARCHAR(16) NOT NULL,
   amount DECIMAL(7,2) NOT NULL,
   purchase_date TIMESTAMP NOT NULL,
   CONSTRAINT fk_transaction_customer_id FOREIGN KEY (customer_id)
   REFERENCES customerTable (id)
);

CREATE TABLE purchaseTable (
   id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   transaction_id INT UNSIGNED NOT NULL,
   item_id INT UNSIGNED NOT NULL,
   qty INT UNSIGNED NOT NULL,
   price DECIMAL(7,2) NOT NULL,
   CONSTRAINT fk_purchase_transaction_id FOREIGN KEY (transaction_id)
   REFERENCES transactionTable (id),
   CONSTRAINT fk_purchase_item_id FOREIGN KEY (item_id)
   REFERENCES itemTable (id)
);

DROP TRIGGER IF EXISTS after_insert_purchase;
 
DELIMITER $$
-- Trigger for adding balance for each transaction
CREATE TRIGGER after_insert_purchase
   AFTER INSERT ON purchaseTable
   FOR EACH ROW
BEGIN
   IF NEW.qty > (SELECT stock FROM itemTable WHERE id=NEW.item_id) THEN
      SIGNAL SQLSTATE '12345'
      SET MESSAGE_TEXT = 'Can\'t insert item, out of stock';
   ELSE 
      UPDATE itemTable SET stock = stock - NEW.qty WHERE id=NEW.item_id;
   END IF;
END$$
DELIMITER ;


