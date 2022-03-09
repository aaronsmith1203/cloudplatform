DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`title` VARCHAR(12),
	`first_name` VARCHAR(255) NOT NULL,
	`last_name` VARCHAR(255) NOT NULL,
	`tenant_name` VARCHAR(255) UNIQUE NOT NULL,
	`address_line1` VARCHAR(255) NOT NULL,
	`address_line2` VARCHAR(255),
	`city` VARCHAR(255) NOT NULL,
	`county` VARCHAR(255),
	`post_code` VARCHAR(9) NOT NULL,
	`telephone_number` VARCHAR(20),
	`email_address` VARCHAR(255) UNIQUE NOT NULL
);