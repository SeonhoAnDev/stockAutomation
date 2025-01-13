CREATE TABLE `report_histories` (
                                    `id` INT AUTO_INCREMENT PRIMARY KEY,
                                    `stock_code` VARCHAR(45) NOT NULL,
                                    `open_price` VARCHAR(45) NOT NULL,
                                    `close_price` VARCHAR(45) NOT NULL,
                                    `high_price` VARCHAR(45) NOT NULL,
                                    `low_price` VARCHAR(45) NOT NULL,
                                    `reported_at` DATETIME

);
