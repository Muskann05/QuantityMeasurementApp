CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS quantity_measurements(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    this_value DOUBLE,
    this_unit VARCHAR(50),
    this_measurement_type VARCHAR(50),

    that_value DOUBLE,
    that_unit VARCHAR(50),
    that_measurement_type VARCHAR(50),

    operation VARCHAR(20),

    result_value DOUBLE,
    result_unit VARCHAR(20),

    result_string VARCHAR(255),
    error BOOLEAN DEFAULT FALSE,
    error_message VARCHAR(255),

    created_at DATETIME
);