ALTER TABLE homestay
    ADD COLUMN is_delete BOOLEAN DEFAULT FALSE,
    ADD COLUMN deleted_at TIMESTAMP DEFAULT NULL;

ALTER TABLE user_airbnb
    ADD COLUMN is_delete BOOLEAN DEFAULT FALSE,
    ADD COLUMN deleted_at TIMESTAMP DEFAULT NULL;

ALTER TABLE profile
    ADD COLUMN is_delete BOOLEAN DEFAULT FALSE,
    ADD COLUMN deleted_at TIMESTAMP DEFAULT NULL;

ALTER TABLE booking
    ADD COLUMN is_delete BOOLEAN DEFAULT FALSE,
    ADD COLUMN deleted_at TIMESTAMP DEFAULT NULL;

ALTER TABLE homestay_availability
    ADD COLUMN is_delete BOOLEAN DEFAULT FALSE,
    ADD COLUMN deleted_at TIMESTAMP DEFAULT NULL;

ALTER TABLE amenity
    ADD COLUMN is_delete BOOLEAN DEFAULT FALSE,
    ADD COLUMN deleted_at TIMESTAMP DEFAULT NULL;

ALTER TABLE homestay_amenity
    ADD COLUMN is_delete BOOLEAN DEFAULT FALSE,
    ADD COLUMN deleted_at TIMESTAMP DEFAULT NULL;

ALTER TABLE ward
    ADD COLUMN is_delete BOOLEAN DEFAULT FALSE,
    ADD COLUMN deleted_at TIMESTAMP DEFAULT NULL;

ALTER TABLE district
    ADD COLUMN is_delete BOOLEAN DEFAULT FALSE,
    ADD COLUMN deleted_at TIMESTAMP DEFAULT NULL;

ALTER TABLE province
    ADD COLUMN is_delete BOOLEAN DEFAULT FALSE,
    ADD COLUMN deleted_at TIMESTAMP DEFAULT NULL;

ALTER TABLE user_token
    ADD COLUMN is_delete BOOLEAN DEFAULT FALSE,
    ADD COLUMN deleted_at TIMESTAMP DEFAULT NULL;

DROP INDEX idx_username;

CREATE UNIQUE INDEX idx_username_active
    ON user_airbnb (username)
    WHERE is_delete = FALSE AND deleted_at IS NULL;