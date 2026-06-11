BEGIN

INSERT INTO kategorie VALUES (1,'Notebooky','Prenosne pocitace');
INSERT INTO kategorie VALUES (2,'Mobily','Chytre telefony');
INSERT INTO kategorie VALUES (3,'Televize','Smart televize');
INSERT INTO kategorie VALUES (4,'Tablety','Tablety a iPady');
INSERT INTO kategorie VALUES (5,'Sluchatka','Audio technika');

INSERT INTO vyrobce VALUES (1,'Apple','USA',1976,'www.apple.com');
INSERT INTO vyrobce VALUES (2,'Samsung','Jizni Korea',1938,'www.samsung.com');
INSERT INTO vyrobce VALUES (3,'Lenovo','Cina',1984,'www.lenovo.com');
INSERT INTO vyrobce VALUES (4,'Sony','Japonsko',1946,'www.sony.com');bw
INSERT INTO vyrobce VALUES (5,'LG','Jizni Korea',1958,'www.lg.com');
INSERT INTO vyrobce VALUES (6,'Asus','Tchaj-wan',1989,'www.asus.com');
INSERT INTO vyrobce VALUES (7,'Acer','Tchaj-wan',1976,'www.acer.com');
INSERT INTO vyrobce VALUES (8,'HP','USA',1939,'www.hp.com');
INSERT INTO vyrobce VALUES (9,'Xiaomi','Cina',2010,'www.mi.com');
INSERT INTO vyrobce VALUES (10,'Dell','USA',1984,'www.dell.com');

INSERT INTO prodejna VALUES (1,'Electro Praha','Praha','Vaclavske namesti 1','8-20',500);
INSERT INTO prodejna VALUES (2,'Electro Brno','Brno','Masarykova 15','8-20',450);
INSERT INTO prodejna VALUES (3,'Electro Ostrava','Ostrava','Nova 10','9-21',400);
INSERT INTO prodejna VALUES (4,'Electro Plzen','Plzen','Americka 25','8-19',350);
INSERT INTO prodejna VALUES (5,'Electro Liberec','Liberec','Sokolovska 8','9-20',300);
INSERT INTO prodejna VALUES (6,'Electro Olomouc','Olomouc','Hlavni 12','8-20',280);
INSERT INTO prodejna VALUES (7,'Electro Zlin','Zlin','Dlouha 4','9-19',260);
INSERT INTO prodejna VALUES (8,'Electro Pardubice','Pardubice','Trida Miru 7','8-20',320);
INSERT INTO prodejna VALUES (9,'Electro Hradec','Hradec Kralove','Goctarova 3','9-20',310);
INSERT INTO prodejna VALUES (10,'Electro CB','Ceske Budejovice','Lannova 14','8-20',290);

INSERT INTO produkt VALUES (1,'NB001','MacBook Air M3','Apple notebook',32990,15,24,DATE '2024-03-01',1,1);
INSERT INTO produkt VALUES (2,'NB002','Lenovo ThinkPad E14','Pracovni notebook',21990,12,24,DATE '2023-05-10',1,3);
INSERT INTO produkt VALUES (3,'MB001','iPhone 15','Apple telefon',23990,20,24,DATE '2023-09-20',2,1);
INSERT INTO produkt VALUES (4,'MB002','Samsung Galaxy S24','Samsung telefon',21990,18,24,DATE '2024-01-15',2,2);
INSERT INTO produkt VALUES (5,'TV001','LG OLED55','OLED televize',28990,8,24,DATE '2023-08-01',3,5);
INSERT INTO produkt VALUES (6,'TV002','Sony Bravia 55','Smart televize',24990,7,24,DATE '2023-04-12',3,4);
INSERT INTO produkt VALUES (7,'TB001','iPad Air','Apple tablet',17990,10,24,DATE '2024-02-01',4,1);
INSERT INTO produkt VALUES (8,'TB002','Samsung Tab S9','Samsung tablet',15990,9,24,DATE '2023-11-01',4,2);
INSERT INTO produkt VALUES (9,'SL001','Sony WH1000XM5','Bezdratova sluchatka',8990,25,24,DATE '2022-09-01',5,4);
INSERT INTO produkt VALUES (10,'SL002','AirPods Pro 2','Apple sluchatka',6990,30,24,DATE '2023-01-01',5,1);

INSERT INTO zamestnanec VALUES (1,'Jan','Novak','Vedouci',45000,DATE '2021-01-10','Ranni',1);
INSERT INTO zamestnanec VALUES (2,'Petr','Svoboda','Prodejce',32000,DATE '2022-03-15','Odpoledni',2);
INSERT INTO zamestnanec VALUES (3,'Martin','Dvorak','Prodejce',31000,DATE '2023-05-01','Ranni',3);
INSERT INTO zamestnanec VALUES (4,'Tomas','Kral','Skladnik',29000,DATE '2022-06-20','Odpoledni',4);
INSERT INTO zamestnanec VALUES (5,'Lukas','Prochazka','Prodejce',31500,DATE '2023-02-14','Ranni',5);
INSERT INTO zamestnanec VALUES (6,'Jiri','Vesely','Vedouci',43000,DATE '2020-09-01','Ranni',6);
INSERT INTO zamestnanec VALUES (7,'David','Horak','Skladnik',28000,DATE '2024-01-10','Odpoledni',7);
INSERT INTO zamestnanec VALUES (8,'Adam','Benes','Prodejce',30000,DATE '2022-11-05','Ranni',8);
INSERT INTO zamestnanec VALUES (9,'Michal','Marek','Prodejce',30500,DATE '2021-08-16','Odpoledni',9);
INSERT INTO zamestnanec VALUES (10,'Filip','Pokorny','Vedouci',44000,DATE '2019-04-01','Ranni',10);

INSERT INTO objednavka VALUES (1,DATE '2025-01-10','zaplacena',23990,'Karel Novak','Kartou','Kuryr',1);
INSERT INTO objednavka VALUES (2,DATE '2025-01-12','odeslana',21990,'Eva Mala','Prevod','Kuryr',2);
INSERT INTO objednavka VALUES (3,DATE '2025-01-14','nova',8990,'Jan Cerny','Kartou','Osobni odber',3);
INSERT INTO objednavka VALUES (4,DATE '2025-01-15','zaplacena',6990,'Pavel Dolezal','Kartou','Kuryr',4);
INSERT INTO objednavka VALUES (5,DATE '2025-01-17','stornovana',15990,'Petra Novakova','Dobirka','Kuryr',5);
INSERT INTO objednavka VALUES (6,DATE '2025-01-18','odeslana',32990,'Tomas Urban','Prevod','Kuryr',6);
INSERT INTO objednavka VALUES (7,DATE '2025-01-20','zaplacena',17990,'Lucie Novakova','Kartou','Osobni odber',7);
INSERT INTO objednavka VALUES (8,DATE '2025-01-22','nova',24990,'Milan Sedlak','Kartou','Kuryr',8);
INSERT INTO objednavka VALUES (9,DATE '2025-01-24','odeslana',28990,'Roman Havel','Prevod','Kuryr',9);
INSERT INTO objednavka VALUES (10,DATE '2025-01-25','zaplacena',21990,'Veronika Kralova','Kartou','Kuryr',10);

INSERT INTO polozka_objednavky VALUES (1,1,3,1,23990,0);
INSERT INTO polozka_objednavky VALUES (2,2,4,1,21990,0);
INSERT INTO polozka_objednavky VALUES (3,3,9,1,8990,5);
INSERT INTO polozka_objednavky VALUES (4,4,10,1,6990,0);
INSERT INTO polozka_objednavky VALUES (5,5,8,1,15990,10);
INSERT INTO polozka_objednavky VALUES (6,6,1,1,32990,0);
INSERT INTO polozka_objednavky VALUES (7,7,7,1,17990,0);
INSERT INTO polozka_objednavky VALUES (8,8,6,1,24990,0);
INSERT INTO polozka_objednavky VALUES (9,9,5,1,28990,0);
INSERT INTO polozka_objednavky VALUES (10,10,2,1,21990,5);

INSERT INTO prodejna_produkt VALUES (1,1,5,'A01');
INSERT INTO prodejna_produkt VALUES (1,3,8,'A02');
INSERT INTO prodejna_produkt VALUES (2,2,6,'B01');
INSERT INTO prodejna_produkt VALUES (2,4,7,'B02');
INSERT INTO prodejna_produkt VALUES (3,5,3,'C01');
INSERT INTO prodejna_produkt VALUES (4,6,4,'D01');
INSERT INTO prodejna_produkt VALUES (5,7,5,'E01');
INSERT INTO prodejna_produkt VALUES (6,8,5,'F01');
INSERT INTO prodejna_produkt VALUES (7,9,10,'G01');
INSERT INTO prodejna_produkt VALUES (8,10,12,'H01');
INSERT INTO prodejna_produkt VALUES (9,1,4,'I01');
INSERT INTO prodejna_produkt VALUES (10,4,6,'J01');

COMMIT;

END;
/