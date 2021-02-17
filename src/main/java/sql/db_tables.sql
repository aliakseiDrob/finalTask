CREATE SCHEMA `dbhotel` DEFAULT CHARACTER SET utf8;



CREATE TABLE IF NOT EXISTS `dbhotel`.`room`
(
    `id`       BIGINT PRIMARY KEY                               NOT NULL AUTO_INCREMENT,
    `type`     ENUM ('STANDARD', 'COMFORT', 'LUX','PRESIDENT') NOT NULL,
    `price`    DECIMAL(6, 2)                                    NOT NULL,
    `status`   ENUM ('AVAILABLE', 'UNAVAILABLE')              NOT NULL,
    `capacity` TINYINT                                          NOT NULL,
    `number`   INT                                              NOT NULL
);

CREATE TABLE IF NOT EXISTS `dbhotel`.`application`
(
    id         BIGINT PRIMARY KEY                               NOT NULL AUTO_INCREMENT,
    check_in   DATE                                             NOT NULL,
    check_out  DATE                                             NOT NULL,
    room_type  ENUM ('STANDARD', 'COMFORT', 'LUX','PRESIDENT') NOT NULL,
    capacity   TINYINT                                          NOT NULL,
    status     enum ('IN_PROGRESS', 'CONFIRMED','REJECTED')                NOT NULL,
    user_id    BIGINT                                           NOT NULL,
    room_id     BIGINT                                      default null,
    invoice       DECIMAL(6, 2)                               default null
);

CREATE TABLE IF NOT EXISTS user
(
    id         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    login      VARCHAR(25) UNIQUE NOT NULL,
    password   VARCHAR(50)        NOT NULL,
    role       enum ('GUEST', 'ADMINISTRATOR'),
    status     enum ('ACTIVE','BLOCKED','DELETED')
);
