CREATE DATABASE curious_fox;

CREATE TABLE accounts(
  user_id uuid DEFAULT uuid_generate_v4 (),
  name VARCHAR(50) NOT NULL,
  username VARCHAR(15) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL,
  bio VARCHAR(160), 
  email VARCHAR(255) UNIQUE,
  picture_url VARCHAR(255),
  PRIMARY KEY (user_id)
);

CREATE TABLE comments(
  comment_id uuid DEFAULT uuid_generate_v4 (),
  comment_text VARCHAR(280) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  parent_id uuid,
  sender_id uuid REFERENCES accounts (user_id),
  receiver_id uuid REFERENCES accounts (user_id)
);