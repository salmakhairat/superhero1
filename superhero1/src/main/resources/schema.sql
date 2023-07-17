DROP DATABASE IF EXISTS superherodb;
CREATE DATABASE superherodb;
USE superherodb;

CREATE TABLE superpower (
	superpower_id INT AUTO_INCREMENT PRIMARY KEY,
    superpower_name VARCHAR(50) NOT NULL
);

CREATE TABLE superherovillain(
	superherovillain_id  INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TINYTEXT,
    superpower INT NOT NULL,
    FOREIGN KEY (superpower) REFERENCES superpower(superpower_id)
);

CREATE TABLE organization(
	organization_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TINYTEXT,
    org_address MEDIUMTEXT NOT NULL,
    contact TINYTEXT
);

CREATE TABLE superhero_organization (
	superhero_id INT NOT NULL,
    organization_id INT NOT NULL,
    PRIMARY KEY(superhero_id, organization_id),
	FOREIGN KEY (superhero_id) REFERENCES superherovillain(superherovillain_id),
	FOREIGN KEY (organization_id) REFERENCES organization(organization_id)
);

CREATE TABLE location (
	location_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TINYTEXT,
    address MEDIUMTEXT NOT NULL,
    latitude DECIMAL(9,6) NOT NULL,
    longitute DECIMAL (9,6) NOT NULL
);

CREATE TABLE sightings (
	sighting_id INT AUTO_INCREMENT PRIMARY KEY,
    location_id INT NOT NULL,
    superhero_id INT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (location_id) REFERENCES location(location_id),
    FOREIGN KEY (superhero_id) REFERENCES superherovillain(superherovillain_id)
);