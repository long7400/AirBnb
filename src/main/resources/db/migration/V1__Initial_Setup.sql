CREATE EXTENSION postgis;

CREATE TABLE homestay
(
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name         VARCHAR(50) NOT NULL,
    description  TEXT        NOT NULL,
    type         VARCHAR(20) NOT NULL,
    status       VARCHAR(20) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,

    address      TEXT        NOT NULL,
    longitude    DOUBLE PRECISION,
    latitude     DOUBLE PRECISION,
    geom         geometry(Point, 3857),

    images       TEXT[],

    guests       SMALLINT,
    bedrooms     SMALLINT,
    bathrooms    SMALLINT,

    extra_data   TEXT[],
    version      BIGINT,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by   BIGINT,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by   BIGINT
);

CREATE TABLE user_airbnb
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username   VARCHAR(255) NOT NULL,
    password   TEXT         NOT NULL,
    email      VARCHAR(255) NOT NULL,
    full_name  VARCHAR(255) NOT NULL,
    type       VARCHAR(20)  NOT NULL,
    status     VARCHAR(20)  NOT NULL,

    extra_data TEXT[],
    version    BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by BIGINT
);

CREATE TABLE profile
(
    user_id    BIGINT      NOT NULL,
    avatar     VARCHAR(255),
    work       VARCHAR(255),
    about      TEXT,
    interests  TEXT[],
    status     VARCHAR(20) NOT NULL,

    extra_data TEXT[],
    version    BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by BIGINT
);

CREATE TABLE booking
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id       BIGINT         NOT NULL,
    homestay_id   BIGINT         NOT NULL,
    checkin_date  DATE           NOT NULL,
    checkout_date DATE           NOT NULL,
    guests        SMALLINT       NOT NULL,
    status        VARCHAR(50)    NOT NULL,

    currency      VARCHAR(10)    NOT NULL,
    subtotal      NUMERIC(12, 6),
    discount      NUMERIC(8, 6),
    total_amount  NUMERIC(12, 2) NOT NULL,
    price_detail  TEXT[],

    note          TEXT,
    request_id    VARCHAR(255)   NOT NULL,

    extra_data    TEXT[],
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by    BIGINT,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by    BIGINT
);

CREATE TABLE homestay_availability
(
    homestay_id BIGINT NOT NULL,
    date        DATE   NOT NULL,
    price       NUMERIC(12, 2),
    status      SMALLINT,
    PRIMARY KEY (homestay_id, date)
);

CREATE TABLE amenity
(
    id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    icon VARCHAR(255) NOT NULL
);

CREATE TABLE homestay_amenity
(
    homestay_id BIGINT  NOT NULL,
    amenity_id  INTEGER NOT NULL,
    PRIMARY KEY (homestay_id, amenity_id),
    CONSTRAINT homestay_amenity_homestay_id_fk FOREIGN KEY (homestay_id) REFERENCES homestay (id),
    CONSTRAINT homestay_amenity_amenity_id_fk FOREIGN KEY (amenity_id) REFERENCES amenity (id)
);

CREATE TABLE ward
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ward_name VARCHAR(255) NOT NULL
);



CREATE INDEX idx_homestay_geom ON homestay USING gist (geom);