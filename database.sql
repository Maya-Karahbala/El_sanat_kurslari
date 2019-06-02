--creating tables
create table ogretmen
(
id number not null,
primary key (id),
adi varchar2(20),
soyadi varchar2(20),
ev_adresi varchar2(60),
cep_telefonu number(11),
ev_telefonu number(11),
email varchar2(30),
durum varchar2(10)
)
-- ___________
create table ogretmen_detay
(
id number not null,
primary key (id),
ogretmen_id number not null,
foreign key(ogretmen_id) references ogretmen (id),
maas_bas_tarih varchar2(10),
maas_bitis_tarih varchar2(10),
h_sonu_ucreti number, --saat başı
h_ici_ucreti number --saat başı
)
-- ___________
create table kursiyer
(
id number not null,
primary key (id),
adi varchar2(20),
soyadi varchar2(20),
ev_adresi varchar2(60),
cep_telefonu number(11),
ev_telefonu number(11),
email varchar2(30)
)
-- ___________
create table personel1
(
id number not null,
primary key (id),
adi varchar2(20),
soyadi varchar2(20),
ev_adresi varchar2(60),
cep_telefonu number(11),
ev_telefonu number(11),
email varchar2(30)
)
-- ___________
create table kurs
(
id number not null,
primary key (id),
kurs_adi varchar2(30) ,
max_ogrenci integer,
min_ogrenci integer,
haftalik_saat number

)
-- ___________
create table ogretmen_kurslar -- ogretmenin verebileceği kurslar
(
ogretmen_id number not null,
kurs_id number not null,
PRIMARY KEY  ( ogretmen_id, kurs_id ),
foreign key(ogretmen_id) references ogretmen (id),
foreign key(kurs_id) references kurs (id)

)
-- ___________
create table acilan_kurs
(
id number not null,
primary key (id),
ogretmen_id number not null,
-- foreign key(ogretmen_id) references ogretmen_kurslar (ogretmen_id),
kurs_id number ,
foreign key(kurs_id) references kurs (id),
stand_fiyat number,
derslik varchar2(10),
zaman varchar2(10), -- hiçi/sonu
personel_id number,
foreign key(personel_id) references personel1 (id)
)
--____________
create table kurs_gunleri
(
acilan_kurs_id number not null, -- not primary or unique
foreign key(acilan_kurs_id) references acilan_kurs (id),
gun varchar2(10),
bas_saati varchar2(8),
bitis_saati varchar2(8)
)
---------------------------kurs_gunlerine değişti primary key eklendi
create table kurs_gunleri
(
id number not null,
primary key (id),
acilan_kurs_id number not null, -- not primary or unique
foreign key(acilan_kurs_id) references acilan_kurs (id),
gun varchar2(10),
bas_saati varchar2(8),
bitis_saati varchar2(8)
)
--------------------------
--____________
create table kursiyer_kurslar
(
acilan_kurs_id number not null, 
kursiyer_id number not null, 
primary key (acilan_kurs_id,kursiyer_id),
foreign key(acilan_kurs_id) references acilan_kurs (id),
foreign key(kursiyer_id) references kursiyer (id),
ozel_fiyat number,
kayit_tarih varchar2(8),
odeme_tipi varchar2(20) -- kredi/peşin
)
--__________
create table kupon
(
kod number not null, 
primary key (kod),
kursiyer_id number not null, 
foreign key(kursiyer_id) references kursiyer (id),
yuzdelik number
)
--sample
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (11, 'ebru başlangıç', 20, 10, 5,8,20);
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (12, 'ebru ileri', 20, 10, 5,15,30);
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (13, 'ahşap boyama', 10, 6, 4,10,35);
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (14, 'basit nakış', 15, 8, 4,10,20);
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (15, 'aksesuar yapımı', 15, 8, 6,8,25);
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (16, 'dantel anglez yapımı', 20, 8, 6,14,22);
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (17, 'elde kurdele işi', 20, 8, 6,15,35);
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (18, 'elde maraş işi', 20, 8, 6,15,35);
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (19, 'elde antep işi', 20, 8, 6,14,35);
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (20, 'halı dokuma', 20, 8, 6,17,40);
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (21, 'filografi tekniği', 20, 8, 6,15,40);
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (22, 'çocuklar için kanaviçe', 20, 8, 6,7,14);
INSERT INTO KURS (ID, KURS_ADI, MAX_OGRENCI, MIN_OGRENCI, HAFTALIK_SAAT, MIN_YAS, MAX_YAS) 	
VALUES 
            (23, 'resim', 10, 8, 2,7,24);

            -------------------------------------------
INSERT 
INTO KURSIYER (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL, YAS) 
	VALUES (10011, 'fatma', 'yaprak', 'beylikdüzü', 152122114, 152122114, 'fatma@gmail.com', 15);
insert
    INTO KURSIYER (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL, YAS) 
   values (10012, 'Ayşe', 'topal', 'beylikdüzü', NULL, NULL, NULL, 18);
insert
    INTO KURSIYER (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL, YAS) 
   values        
        (10013, 'ela', 'yılmaz', 'beylikdüzü', NULL, NULL, NULL, 19);
        insert
    INTO KURSIYER (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL, YAS) 
   values
        (10014, 'betül','kaya', 'beylikdüzü', NULL, NULL, NULL, 20);
    insert
    INTO KURSIYER (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL, YAS) 
   values
        (10015, 'mehmet', 'çetin', 'beylikdüzü', NULL, NULL, NULL, 25);
        insert
    INTO KURSIYER (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL, YAS) 
   values
        (10016, 'merve', 'aktürk', 'beylikdüzü', NULL, NULL, NULL, 30);
SELECT * FROM KURSIYER;
-----------------------------------------------------

INSERT INTO KUPON (KOD, KURSIYER_ID, YUZDELIK) 
	VALUES (122411, 10010, 10);
	INSERT INTO KUPON (KOD, KURSIYER_ID, YUZDELIK) 
	VALUES
        (122412, 10010, 5);
        INSERT INTO KUPON (KOD, KURSIYER_ID, YUZDELIK) 
	VALUES
        (122413, 10011, 12);
        INSERT INTO KUPON (KOD, KURSIYER_ID, YUZDELIK) 
	VALUES
        (122414, 10012, 5);
        INSERT INTO KUPON (KOD, KURSIYER_ID, YUZDELIK) 
	VALUES
        (122415, 10013, 10);
        ----------------------------------------------------

INSERT INTO OGRETMEN (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL, DURUM) 
	VALUES (20010, 'ahmet', 'kara', 'esenyurt,ist', 05342673, NULL, 'ak@gmail.com', 'hafta içi');
	INSERT INTO OGRETMEN (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL, DURUM) 
	VALUES(20011, 'mehmet', 'karataş', 'esenyurt,ist', 05342666, NULL, 'mk@gmail.com', 'hafta içi')
	;INSERT INTO OGRETMEN (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL, DURUM) 
	VALUES(20012, 'ibrahim', 'akkaş', 'esenyurt,ist', 05342673, NULL, 'iak@gmail.com', 'hafta sonu')
	;INSERT INTO OGRETMEN (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL, DURUM) 
	VALUES(20013, 'çiğdem', 'temel', 'esenyurt,ist', 05342673, NULL, 'ct@gmail.com', 'hafta sonu')
	;INSERT INTO OGRETMEN (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL, DURUM) 
	VALUES(20014, 'meryem', 'yıldırım', 'esenyurt,ist', 05967335, NULL, 'my@gmail.com', 'hafta sonu')
	;INSERT INTO OGRETMEN (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL, DURUM) 
	VALUES(20015, 'umut', 'yıldız', 'esenyurt,ist', 053420996, NULL, 'uy@gmail.com', 'hafta içi');
select * from ogretmen
--------------------------------

INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20010, 11)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20010, 12)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20010, 13)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20015, 14)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20011, 14)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20011, 15)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20012, 18)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20014, 20)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20014, 21)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20012, 21)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20014, 16)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20014, 17)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20013, 19)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20015, 18)
;INSERT INTO OGRETMEN_KURSLAR (OGRETMEN_ID, KURS_ID) 
	VALUES
             (20015, 22);
             ---------------------------


INSERT INTO PERSONEL1 (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL) 
	VALUES (100, 'melek','yıldız', NULL, NULL, NULL, NULL);
	INSERT INTO PERSONEL1 (ID, ADI, SOYADI, EV_ADRESI, CEP_TELEFONU, EV_TELEFONU, EMAIL) 
	VALUES
	 (101, 'ali','yılmaz', NULL, NULL, NULL, NULL);
---------------
INSERT INTO ACILAN_KURS (ID, OGRETMEN_ID, KURS_ID, STAND_FIYAT, DERSLIK, ZAMAN, PERSONEL_ID) 
	VALUES (40001, 20010, 11, 200, 'D202', 'haftaiçi', 100);
INSERT INTO ACILAN_KURS (ID, OGRETMEN_ID, KURS_ID, STAND_FIYAT, DERSLIK, ZAMAN, PERSONEL_ID) 
	VALUES
	 (40002, 20010, 12, 200, 'D202', 'haftasonu', 100);
	INSERT INTO ACILAN_KURS (ID, OGRETMEN_ID, KURS_ID, STAND_FIYAT, DERSLIK, ZAMAN, PERSONEL_ID) 
	VALUES
	 (40003, 20010, 13, 250, 'D202', 'haftaiçi', 100);
	INSERT INTO ACILAN_KURS (ID, OGRETMEN_ID, KURS_ID, STAND_FIYAT, DERSLIK, ZAMAN, PERSONEL_ID) 
	VALUES
	 (40004, 20011, 15, 170, 'D201', 'haftaiçi', 101);
	INSERT INTO ACILAN_KURS (ID, OGRETMEN_ID, KURS_ID, STAND_FIYAT, DERSLIK, ZAMAN, PERSONEL_ID) 
	VALUES
	 (40005, 20012, 21, 400, 'D204', 'haftasonu', 101);
	INSERT INTO ACILAN_KURS (ID, OGRETMEN_ID, KURS_ID, STAND_FIYAT, DERSLIK, ZAMAN, PERSONEL_ID) 
	VALUES
	 (40006, 20014, 17, 550, 'B103', 'haftasonu', 100);
	INSERT INTO ACILAN_KURS (ID, OGRETMEN_ID, KURS_ID, STAND_FIYAT, DERSLIK, ZAMAN, PERSONEL_ID) 
	VALUES
	 (40007, 20015, 18, 350, 'A207', 'haftaiçi', 101);
	INSERT INTO ACILAN_KURS (ID, OGRETMEN_ID, KURS_ID, STAND_FIYAT, DERSLIK, ZAMAN, PERSONEL_ID) 
	VALUES
	 (40008, 20015, 22, 275, 'D208', 'haftaiçi', 100);
	 -------------------

INSERT INTO OGRETMEN_DETAY (id,OGRETMEN_ID, MAAS_BAS_TARIH, MAAS_BITIS_TARIH, H_SONU_UCRETI, H_ICI_UCRETI) 
	VALUES (1,20011, '02.01.2015', '02.01.2016', 50,40);
INSERT INTO OGRETMEN_DETAY (id,OGRETMEN_ID, MAAS_BAS_TARIH, MAAS_BITIS_TARIH, H_SONU_UCRETI, H_ICI_UCRETI) 
	VALUES 
	 (2,20011, '02.01.2016', '02.01.2017', 55,45);
	INSERT INTO OGRETMEN_DETAY (id,OGRETMEN_ID, MAAS_BAS_TARIH, MAAS_BITIS_TARIH, H_SONU_UCRETI, H_ICI_UCRETI) 
	VALUES 
	 (3,20012, '02.01.2015', '02.01.2016', 40,35);
	INSERT INTO OGRETMEN_DETAY (id,OGRETMEN_ID, MAAS_BAS_TARIH, MAAS_BITIS_TARIH, H_SONU_UCRETI, H_ICI_UCRETI) 
	VALUES 
	 (4,20013, '01.06.2015', '02.09.2015', 50,35);
	INSERT INTO OGRETMEN_DETAY (id,OGRETMEN_ID, MAAS_BAS_TARIH, MAAS_BITIS_TARIH, H_SONU_UCRETI, H_ICI_UCRETI) 
	VALUES 
	 (5,20014, '10.01.2018', '02.01.2019', 40,40);
	INSERT INTO OGRETMEN_DETAY (id,OGRETMEN_ID, MAAS_BAS_TARIH, MAAS_BITIS_TARIH, H_SONU_UCRETI, H_ICI_UCRETI) 
	VALUES 
	 (6,20015, '02.01.2018', '02.01.2019', 50,40);
	--------------------------------

INSERT INTO KURS_GUNLERI (id,ACILAN_KURS_ID, GUN, BAS_SAATI, BITIS_SAATI) 
	VALUES (1,40001, 'pzt', '10:00', '12:50');
INSERT INTO KURS_GUNLERI (id,ACILAN_KURS_ID, GUN, BAS_SAATI, BITIS_SAATI) 
	VALUES
	 (2,40001, 'çrş', '10:00', '11:50');
	INSERT INTO KURS_GUNLERI (id,ACILAN_KURS_ID, GUN, BAS_SAATI, BITIS_SAATI) 
	VALUES
	 (3,40002, 'pzr', '10:00', '12:50');
	INSERT INTO KURS_GUNLERI (id,ACILAN_KURS_ID, GUN, BAS_SAATI, BITIS_SAATI) 
	VALUES
	 (4,40002, 'cts', '10:00', '11:50');
	INSERT INTO KURS_GUNLERI (id,ACILAN_KURS_ID, GUN, BAS_SAATI, BITIS_SAATI) 
	VALUES
	 (5,40003, 'salı', '10:00', '11:50');
	INSERT INTO KURS_GUNLERI (id,ACILAN_KURS_ID, GUN, BAS_SAATI, BITIS_SAATI) 
	VALUES
	 (6,40004, 'salı', '10:00', '11:50');
	INSERT INTO KURS_GUNLERI (id,ACILAN_KURS_ID, GUN, BAS_SAATI, BITIS_SAATI) 
	VALUES
	 (7,40004, 'cuma', '11:00', '13:50');
	 -----------------------------------------


INSERT INTO KURSIYER_KURSLAR (ACILAN_KURS_ID, KURSIYER_ID, OZEL_FIYAT, KAYIT_TARIH, ODEME_TIPI) 
	VALUES (40001, 10011, 190, '01.01.2019', 'kredi');
INSERT INTO KURSIYER_KURSLAR (ACILAN_KURS_ID, KURSIYER_ID, OZEL_FIYAT, KAYIT_TARIH, ODEME_TIPI) 
	VALUES
	 (40001, 10012, 190, '01.01.2019', 'kredi');
	INSERT INTO KURSIYER_KURSLAR (ACILAN_KURS_ID, KURSIYER_ID, OZEL_FIYAT, KAYIT_TARIH, ODEME_TIPI) 
	VALUES
	 (40001, 10013, 190, '01.01.2019', 'kredi');
	INSERT INTO KURSIYER_KURSLAR (ACILAN_KURS_ID, KURSIYER_ID, OZEL_FIYAT, KAYIT_TARIH, ODEME_TIPI) 
	VALUES
	 (40001, 10014, 180, '01.01.2019', 'kredi');
	INSERT INTO KURSIYER_KURSLAR (ACILAN_KURS_ID, KURSIYER_ID, OZEL_FIYAT, KAYIT_TARIH, ODEME_TIPI) 
	VALUES
	 (40001, 10015, 150, '11.01.2019', 'kredi');
	INSERT INTO KURSIYER_KURSLAR (ACILAN_KURS_ID, KURSIYER_ID, OZEL_FIYAT, KAYIT_TARIH, ODEME_TIPI) 
	VALUES
	 (40002, 10012, 190, '14.01.2019', 'peşin');
	INSERT INTO KURSIYER_KURSLAR (ACILAN_KURS_ID, KURSIYER_ID, OZEL_FIYAT, KAYIT_TARIH, ODEME_TIPI) 
	VALUES
	 (40002, 10013, 190, '12.01.2019', 'kredi');
	INSERT INTO KURSIYER_KURSLAR (ACILAN_KURS_ID, KURSIYER_ID, OZEL_FIYAT, KAYIT_TARIH, ODEME_TIPI) 
	VALUES
	 (40002, 10016, 190, '02.01.2019', 'peşin');
	INSERT INTO KURSIYER_KURSLAR (ACILAN_KURS_ID, KURSIYER_ID, OZEL_FIYAT, KAYIT_TARIH, ODEME_TIPI) 
	VALUES
	 (40002, 10017, 150, '04.01.2019', 'kredi');
	INSERT INTO KURSIYER_KURSLAR (ACILAN_KURS_ID, KURSIYER_ID, OZEL_FIYAT, KAYIT_TARIH, ODEME_TIPI) 
	VALUES
	 (40002, 10018, 140, '08.01.2019', 'peşin');
	 /*



	 */
	 --eklemeler ve örnekler
 alter session set nls_date_format = 'dd/MM/yyyy'

update acilan_kurs
set TO_DATE bas_tarih =('22/04/2011')
-------------- 
 update acilan_kurs
set  bas_tarih =date '2015-01-01'
where id=40008

--------------
update kurs_gunleri
set gun='Perşembe'
where acilan_kurs_id=40008
--------------
INSERT INTO KURS_GUNLERI (id,ACILAN_KURS_ID, GUN, BAS_SAATI, BITIS_SAATI) 
	VALUES
	 (12,40007, 'Perşembe', '8:00', '14:00');

	 delete from kursiyer_kurslar
where kursiyer_id=10016

--------------


 delete from kursiyer_kurslar
where kursiyer_id=10017 and acilan_kurs_id= 40013;
--------------


INSERT INTO KURSIYER_KURSLAR (ACILAN_KURS_ID, KURSIYER_ID, OZEL_FIYAT, KAYIT_TARIH, ODEME_TIPI) 
	VALUES (40003, 10017, 190, '01.01.2019', 'kredi');