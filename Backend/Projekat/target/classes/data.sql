-- Drop tables if they exist
DROP TABLE IF EXISTS usluga CASCADE;
DROP TABLE IF EXISTS filijala CASCADE;
DROP TABLE IF EXISTS korisnik_usluge CASCADE;
DROP TABLE IF EXISTS banka CASCADE;

-- Create tables again
CREATE TABLE banka (
    bankaid SERIAL PRIMARY KEY,
    naziv VARCHAR(50) NOT NULL,
    kontakt VARCHAR(50) NOT NULL,
    pib INT NOT NULL
);

CREATE TABLE usluga (
    uslugaid SERIAL PRIMARY KEY,
    naziv VARCHAR(50) NOT NULL,
    opis_usluge VARCHAR(50) NOT NULL,
    datum_ugovora DATE NOT NULL,
    provizija NUMERIC(5, 2) NOT NULL
);

CREATE TABLE filijala (
    filijalaid SERIAL PRIMARY KEY,
    adresa VARCHAR(50) NOT NULL,
    broj_pultova INTEGER NOT NULL,
    poseduje_sef BOOLEAN NOT NULL,
    bankaid INT,
    FOREIGN KEY (bankaid) REFERENCES banka (bankaid)
);

CREATE TABLE korisnik_usluge (
    korisnik_uslugeid SERIAL PRIMARY KEY,
    ime VARCHAR(50) NOT NULL,
    prezime VARCHAR(50) NOT NULL,
    maticni_broj VARCHAR(50) NOT NULL,
    filijalaid INT,
    uslugaid INT,
    FOREIGN KEY (filijalaid) REFERENCES filijala (filijalaid),
    FOREIGN KEY (uslugaid) REFERENCES usluga (uslugaid)
);



-- Insert data into Banka
INSERT INTO banka (naziv, kontakt, pib) VALUES 
    ('Centralna Banka', 'central@banka.com', 123456789),
    ('Finansijska Banka', 'finansijska@banka.com', 987654321),
    ('Ekonomija Banka', 'ekonomija@banka.com', 234567890);
    
    -- Insert data into Usluga
INSERT INTO usluga (naziv, opis_usluge, datum_ugovora, provizija)
VALUES ('Usluga 1', 'Opis usluge 1', '2024-01-01', 5.00);

INSERT INTO usluga (naziv, opis_usluge, datum_ugovora, provizija)
VALUES ('Usluga 2', 'Opis usluge 2', '2024-02-15', 3.50);

INSERT INTO usluga (naziv, opis_usluge, datum_ugovora, provizija)
VALUES ('Usluga 3', 'Opis usluge 3', '2024-03-20', 7.25);

-- Insert data into Filijala
INSERT INTO filijala (adresa, broj_pultova, poseduje_sef, bankaid) VALUES 
    ('Ul. Glavna 1, Beograd', 5, TRUE, 1),
    ('Ul. Branka 2, Novi Sad', 3, FALSE, 1),
    ('Ul. Ekspresna 3, Nis', 4, TRUE, 2),
    ('Ul. Obala 4, Kragujevac', 2, FALSE, 3),
    ('Ul. Narodnog Fronta 5, Subotica', 3, TRUE, 2);

-- Insert data into Korisnik_usluge
INSERT INTO korisnik_usluge (ime, prezime, maticni_broj, uslugaid, filijalaid) VALUES 
    ('Marko', 'Markovic', '0101996775012',1,1),
    ('Ana', 'Anic', '1203996773011',1,2),
    ('Petar', 'Petrovic', '1505996768013',2,2),
    ('Milena', 'Milenic', '2203996752010',1,3),
    ('Stefan', 'Stefanovic', '3107996789014',3,4);



ALTER TABLE filijala
DROP CONSTRAINT filijala_bankaid_fkey;

ALTER TABLE filijala
ADD CONSTRAINT filijala_bankaid_fkey
FOREIGN KEY (bankaid)
REFERENCES banka (bankaid)
ON DELETE CASCADE;


