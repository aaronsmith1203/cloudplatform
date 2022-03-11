BEGIN;
CREATE SCHEMA IF NOT EXISTS `cloudplatform`;
USE `cloudplatform`;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`(
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
INSERT INTO `account` (
	`title`,
	`first_name`,
	`last_name`,
	`tenant_name`,
	`address_line1`,
	`address_line2`,
	`city`,
	`county`,
	`post_code`,
	`telephone_number`,
	`email_address`
)	
VALUES
(
	'Mr',
	'Donald',
	'Gibson',
	'asure',
	'Flat 4',
	'22 Roadington Road',
	'Mockleton',
	'Java',
	'JA1 2JA',
	'01234 567890',
	'donald.gibson@asure.com'
),
(
	'Miss',
	'Jennifer',
	'Green',
	'qa-training',
	'22a Road Avenue',
	'',
	'Mockiton',
	'Javashire',
	'MO22 8JA',
	'0123 4567 890',
	'jengreen@qa.com'
),
(
	'Mr',
	'Aaron',
	'Smith',
	'smith-development',
	'83 Apple Turnover',
	'Bakery Way',
	'Mockassin',
	'Javerley',
	'AP12 1TO',
	'0123 456 7890',
	'asmith@mydomain.com'
);

DROP TABLE IF EXISTS `cloud_service`;
CREATE TABLE `cloud_service`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(2040),
	`cost_in_pence` INT NOT NULL
);
INSERT INTO `cloud_service` (
	`name`,
    `description`,
    `cost_in_pence`
)
VALUES
(
	'ASure Web Site (Basic)',
	'Host a web-site with static content. 100MB storage. Single instance.',
	295
),
(
	'ASure Web Site (Standard)',
	'Host a web-site with static content. 500MB storage. Two instances, providing high-availability.',
	495
),
(
	'ASure Web Application (Basic)',
	'Host a back-end web-application using PHP, ASP.NET, or Java. 250MB storage. Single instance.',
	475
),
(
	'ASure Web Application (Standard)',
	'Host a back-end web-application using PHP, ASP.NET, or Java. 1GB storage. Single instance.',
	749
),
(
	'ASure Web Application (Premium)',
	'Host a back-end web-application using PHP, ASP.NET, or Java. 1GB storage. Two instances, providing high-availability.',
	749
),
(
	'ASure Virtual Machine (A1)',
	'A virtual-machine with a number of available operating-systems. 1 x vCPU @ 1.2GHz, 1GB RAM, 20GB storage.',
	462
),
(
	'ASure Virtual Machine (B1)',
	'A virtual-machine with a number of available operating-systems. 1 x vCPU @ 2.4GHz, 2GB RAM, 40GB storage.',
	681
),
(
	'ASure Virtual Machine (B2)',
	'A virtual-machine with a number of available operating-systems. 2 x vCPU @ 2.4GHz, 4GB RAM, 80GB storage.',
	879
);