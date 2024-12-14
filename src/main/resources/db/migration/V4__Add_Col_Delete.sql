ALTER TABLE homestay
    ADD COLUMN is_delete TINYINT(1) DEFAULT 0;

ALTER TABLE `user`
    ADD COLUMN is_delete TINYINT(1) DEFAULT 0;

ALTER TABLE profile
    ADD COLUMN is_delete TINYINT(1) DEFAULT 0;

ALTER TABLE booking
    ADD COLUMN is_delete TINYINT(1) DEFAULT 0;

ALTER TABLE homestay_availability
    ADD COLUMN is_delete TINYINT(1) DEFAULT 0;

ALTER TABLE amenity
    ADD COLUMN is_delete TINYINT(1) DEFAULT 0;

ALTER TABLE homestay_amenity
    ADD COLUMN is_delete TINYINT(1) DEFAULT 0;

ALTER TABLE ward
    ADD COLUMN is_delete TINYINT(1) DEFAULT 0;

ALTER TABLE district
    ADD COLUMN is_delete TINYINT(1) DEFAULT 0;

ALTER TABLE province
    ADD COLUMN is_delete TINYINT(1) DEFAULT 0;

ALTER TABLE `user_token`
    ADD COLUMN is_delete TINYINT(1) DEFAULT 0;