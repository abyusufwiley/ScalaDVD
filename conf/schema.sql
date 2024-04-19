
DROP TABLE IF EXISTS DVDs
CREATE TABLE DVDs (
    DvdId INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    releaseDate DATE,
    mpaaRating VARCHAR(10),
    directorName VARCHAR(255),
    studio VARCHAR(255),
    userRating VARCHAR(255)
);