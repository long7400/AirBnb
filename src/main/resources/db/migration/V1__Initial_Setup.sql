CREATE TABLE homestay
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         NVARCHAR(50) NOT NULL,
    description  TEXT         NOT NULL,
    type         VARCHAR(20)  NOT NULL,
    status       VARCHAR(20)  NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,

    address      TEXT         NOT NULL,
    ward_id      INT,
    district_id  INT,
    province_id  INT,

    images       JSON,

    guests       SMALLINT,
    bedrooms     SMALLINT,
    bathrooms    SMALLINT,

    extra_data   JSON,
    version      BIGINT,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by   BIGINT,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by   BIGINT
) ENGINE = InnoDB;

CREATE TABLE `user`
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   NVARCHAR(255) NOT NULL,
    password   TEXT          NOT NULL,
    email      NVARCHAR(255) NOT NULL,
    full_name  NVARCHAR(255) NOT NULL,
    type       VARCHAR(20)   NOT NULL,
    status     VARCHAR(20)   NOT NULL,

    extra_data JSON,
    version    BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by BIGINT
) ENGINE = InnoDB;

CREATE TABLE profile
(
    user_id    BIGINT      NOT NULL,
    avatar     NVARCHAR(255),
    work       NVARCHAR(255),
    about      TEXT,
    interests  JSON,
    status     VARCHAR(20) NOT NULL,

    extra_data JSON,
    version    BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by BIGINT,

    PRIMARY KEY (user_id)
) ENGINE = InnoDB;

CREATE TABLE booking
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id       BIGINT         NOT NULL,
    homestay_id   BIGINT         NOT NULL,
    checkin_date  DATE           NOT NULL,
    checkout_date DATE           NOT NULL,
    guests        SMALLINT       NOT NULL,
    status        VARCHAR(50)    NOT NULL,

    currency      NVARCHAR(10)   NOT NULL,
    subtotal      DECIMAL(12, 6),
    discount      DECIMAL(8, 6),
    total_amount  DECIMAL(12, 2) NOT NULL,
    price_detail  JSON,

    note          TEXT,
    request_id    NVARCHAR(255)  NOT NULL,

    version       SMALLINT,
    extra_data    JSON,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by    BIGINT,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by    BIGINT
) ENGINE = InnoDB;

CREATE TABLE homestay_availability
(
    homestay_id BIGINT NOT NULL,
    date        DATE   NOT NULL,
    price       DECIMAL(12, 2),
    status      SMALLINT,
    PRIMARY KEY (homestay_id, date)
) ENGINE = InnoDB;

CREATE TABLE amenity
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    icon NVARCHAR(255) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE homestay_amenity
(
    homestay_id BIGINT NOT NULL,
    amenity_id  INT    NOT NULL,
    PRIMARY KEY (homestay_id, amenity_id),
    CONSTRAINT homestay_amenity_homestay_id_fk FOREIGN KEY (homestay_id) REFERENCES homestay (id),
    CONSTRAINT homestay_amenity_amenity_id_fk FOREIGN KEY (amenity_id) REFERENCES amenity (id)
) ENGINE = InnoDB;

CREATE TABLE ward
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    ward_name   NVARCHAR(255) NOT NULL,
    district_id INT
) ENGINE = InnoDB;

CREATE TABLE district
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    district_name NVARCHAR(255) NOT NULL,
    province_id   INT
) ENGINE = InnoDB;

CREATE TABLE province
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    province_name NVARCHAR(255) NOT NULL,
    country_id    INT
) ENGINE = InnoDB;