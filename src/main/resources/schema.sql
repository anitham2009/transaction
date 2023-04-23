drop table if exists transaction_detail;
CREATE TABLE transaction_detail (
  transaction_id INT AUTO_INCREMENT  PRIMARY KEY,
  transaction_number VARCHAR(50) not null,
  account_id INT NOT NULL,
  transferred_amount numeric not null,
  balance numeric not null,
  description VARCHAR(30) not null,
  transferred_date date default CURRENT_DATE
  );