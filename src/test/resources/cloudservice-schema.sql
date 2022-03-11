DROP TABLE IF EXISTS `cloud_service`;
CREATE TABLE IF NOT EXISTS `cloud_service`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(2040),
	`cost_in_pence` INT NOT NULL
);