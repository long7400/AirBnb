CREATE TABLE user_token
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username   VARCHAR(255) NOT NULL,
    token      VARCHAR(255)         NOT NULL,
    expires_at TIMESTAMP    NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by BIGINT,
    UNIQUE (token)
);