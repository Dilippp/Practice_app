CREATE TABLE IF NOT EXISTS `company` (
    `company_id` int NOT NULL,
    `created_timestamp` datetime(6) NOT NULL,
    `updated_timestamp` datetime(6) NOT NULL,
    `company_name` varchar(255) NOT NULL,
    PRIMARY KEY (`company_id`)
)