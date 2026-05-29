CREATE TABLE prodejna (
    id_prodejna NUMBER PRIMARY KEY,
    nazev VARCHAR2(100) NOT NULL,
    mesto VARCHAR2(100) NOT NULL,
    adresa VARCHAR2(150) NOT NULL,
    oteviraci_doba VARCHAR2(100),
    velikost_skladu NUMBER
);

CREATE TABLE vyrobce (
    id_vyrobce NUMBER PRIMARY KEY,
    nazev VARCHAR2(100) NOT NULL,
    zeme_puvodu VARCHAR2(100),
    rok_zalozeni NUMBER,
    webova_stranka VARCHAR2(150)
);

CREATE TABLE kategorie (
    id_kategorie NUMBER PRIMARY KEY,
    nazev VARCHAR2(100) NOT NULL,
    popis VARCHAR2(255)
);

CREATE TABLE produkt (
    id_produkt NUMBER PRIMARY KEY,
    kod_produktu VARCHAR2(50) NOT NULL,
    nazev VARCHAR2(100) NOT NULL,
    popis VARCHAR2(500),
    cena NUMBER(10,2) NOT NULL,
    skladem NUMBER,
    zaruka_mesice NUMBER,
    datum_uvedeni DATE,
    id_kategorie NUMBER,
    id_vyrobce NUMBER,

    FOREIGN KEY (id_kategorie)
        REFERENCES kategorie(id_kategorie),

    FOREIGN KEY (id_vyrobce)
        REFERENCES vyrobce(id_vyrobce)
);

CREATE TABLE objednavka (
    id_objednavka NUMBER PRIMARY KEY,
    datum_vytvoreni DATE NOT NULL,
    stav VARCHAR2(20) NOT NULL,
    celkova_cena NUMBER(10,2),
    jmeno_zadavatele VARCHAR2(100) NOT NULL,
    zpusob_platby VARCHAR2(50),
    zpusob_dopravy VARCHAR2(50),
    id_prodejna NUMBER,

    FOREIGN KEY (id_prodejna)
        REFERENCES prodejna(id_prodejna)
);

CREATE TABLE polozka_objednavky (
    id_polozka NUMBER PRIMARY KEY,
    id_objednavka NUMBER,
    id_produkt NUMBER,
    mnozstvi NUMBER,
    cena_za_kus NUMBER(10,2),
    sleva_procenta NUMBER(5,2),

    FOREIGN KEY (id_objednavka)
        REFERENCES objednavka(id_objednavka),

    FOREIGN KEY (id_produkt)
        REFERENCES produkt(id_produkt)
);

CREATE TABLE zamestnanec (
    id_zamestnanec NUMBER PRIMARY KEY,
    jmeno VARCHAR2(50) NOT NULL,
    prijmeni VARCHAR2(50) NOT NULL,
    pozice VARCHAR2(50),
    plat NUMBER(10,2),
    datum_nastupu DATE,
    smena VARCHAR2(20),
    id_prodejna NUMBER,

    FOREIGN KEY (id_prodejna)
        REFERENCES prodejna(id_prodejna)
);

CREATE TABLE prodejna_produkt (
    id_prodejna NUMBER,
    id_produkt NUMBER,
    mnozstvi NUMBER,
    umisteni_regal VARCHAR2(20),

    PRIMARY KEY (id_prodejna, id_produkt),

    FOREIGN KEY (id_prodejna)
        REFERENCES prodejna(id_prodejna),

    FOREIGN KEY (id_produkt)
        REFERENCES produkt(id_produkt)
);