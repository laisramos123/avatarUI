CREATE TABLE IF NOT EXISTS profile_photos(
                                             custumer_id VARCHAR(36) NOT NULL,
    id VARCHAR(36) NOT NULL,
    original_photo VARCHAR(200),
    generated_photo VARCHAR(200)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON
    PRIMARY KEY (customer_id, id)
    )

    INSERT INTO profile_photos(custumer_id,id,original_photo,generated_photo) VALUES
("custumer_1","","custumer-1-original-photo-1-path",""),
("custumer_2","","custumer-1-original-photo-2-path",""),
("custumer_3","","custumer-1-original-photo-3-path",);