    CREATE TABLE IF NOT EXISTS ANSWER (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    rangeMax int,
    rangeMin int,
    text varchar(250),
    types varchar(20),
    survey_id int
    );