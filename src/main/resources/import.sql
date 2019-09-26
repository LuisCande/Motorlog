insert into user_account values (700, 0, false, '$2a$11$/y4s4K.sa2l2hVrtYxP4TeZSpYanBm7O8yOPeXcwKUjX44xgd/TLW', 'adminmotorlog1');

insert into user_account values (701, 0, false, '$2a$11$Znq2ai4OYeZJOeiNe.EQ6.Hm6Z9PZSlocSP/qXNZotZC6EkfTH4bu', 'sevillamotor');
insert into user_account values (702, 0, false, '$2a$11$B0tXEkkKmewce.hKMfRGFOb8Sl5xtHuCGQVln.BCtEFeJjDBtwwUW', 'canocasion');
insert into user_account values (703, 0, false, '$2a$11$9uUC7KX99/3In3T8QDEfWe0z4YDUggTjqLwPrSRxh7w6AAGih7ZRy', 'tallermotorlog3');
insert into user_account values (704, 0, false, '$2a$11$02AsHGTVihT7PJxMxPehEuIYfh5V0rcwRDuiFVYbemMdgroTw21/W', 'tallermotorlog4');

insert into user_account values (705, 0, false, '$2a$11$VrHPgpV3zPZVz5wZ2sIxR.f1q8PFctcLYZeXvr8ri/DvIlXNZIS8G', 'cmmotorlog1');
insert into user_account values (706, 0, false, '$2a$11$ymNUZUXrz6/AnhnVGJjMu.s/zwN9T4Wgx0JkDyJh/C0T/wsA6dbG6', 'cmmotorlog2');

insert into user_account values (707 , 0, false, '$2a$11$oQH0GJUnOM4woCYU8BN8Huvk/PBbpm0Ds5EpInKdY.Q5hKPi58rxG', 'motoscande');
insert into user_account values (708 , 0, false, '$2a$11$vQIB8c/twL8iAS2NM7sgDuwAw5Rrz8L08E.Bq/rOJ/cFhG184eKra', 'motomas');
insert into user_account values (709, 0, false, '$2a$11$oZOKjZGM3hIKPh54dPf8memX5hOHSDi./keE6cMu9qsfWGKYecd7O', 'automocionloscanos');
insert into user_account values (710, 0, false, '$2a$11$nwUtM0v3N4V8N41GCZ6DBejWq/KMXpKs34yfWpgZdG9xEhwIA3DtK', 'marianolopez');
insert into user_account values (711, 0, false, '$2a$11$0Il6Y9PStRJ5sOn/5R3rX.7a7OtGFP2nQA/hKvACMki2UlOcW0NFS', 'Fermar');
insert into user_account values (712, 0, false, '$2a$11$n97MAXI6eVGkqPF3.EmzteUN.j1j.k/jLnRQUlbl6hcnBCnSCH4Vm', 'Midas');
insert into user_account values (713, 0, false, '$2a$11$zfPHQnUndB2JBJWP91vIPOIikC1y3SeyN3PyyvFgtUTEfTfJLJi.C', 'estegorcha');
insert into user_account values (714, 0, false, '$2a$11$8w3NSvgxnDS3Okxx.OGAOuCRjsrWY82J2dbtCKtdeDhIoNWM1wSSu', 'muniz');
insert into user_account values (715, 0, false, '$2a$11$DQKD1hD6xeLzpVqAGoxN/u2O58pWaHMHaLsGnK1oWOR577r2zK7F.', 'zaframotor');

-- Authorities
insert into user_account_authorities values (700, 'ADMIN');
insert into user_account_authorities values (701, 'GARAGE');
insert into user_account_authorities values (702, 'GARAGE');
insert into user_account_authorities values (703, 'GARAGE');
insert into user_account_authorities values (704, 'GARAGE');
insert into user_account_authorities values (705, 'CONTENTMANAGER');
insert into user_account_authorities values (706, 'CONTENTMANAGER');
insert into user_account_authorities values (707 , 'GARAGE');
insert into user_account_authorities values (708 , 'GARAGE');
insert into user_account_authorities values (709, 'GARAGE');
insert into user_account_authorities values (710, 'GARAGE');
insert into user_account_authorities values (711, 'GARAGE');
insert into user_account_authorities values (712, 'GARAGE');
insert into user_account_authorities values (713, 'GARAGE');
insert into user_account_authorities values (714, 'GARAGE');
insert into user_account_authorities values (715, 'GARAGE');

--Manual
insert into manual VALUES(400, 0, 'http://www.google.es');

--ManualRevisionDates
insert into manual_revision_dates VALUES(400, '12/12/2022 12:10');

--Actor
insert into administrator values (51, 0, 'Calle falsa', 'Sevilla', 'España', 'luisito@gmail.com', '74125478T', 'Luis', '659832147', '41012', (select a.id from user_account a where a.username='adminmotorlog1'));
insert into garage VALUES (52, 0, 'Calle verdadera', 'Sevilla', 'España', 'sergito@gmail.com', 'U4061600E', 'SevillaMotor', '632145987', '06230', (select a.id from user_account a where a.username='sevillamotor'), null);
insert into garage VALUES (53, 0, 'Calle esmeralda', 'Sevilla', 'España', 'juanmita@gmail.com', 'S3735988B', 'Canocasion', '632963852', '06230', (select a.id from user_account a where a.username='canocasion'), null);
insert into garage VALUES (54, 0, 'Calle trillo', 'Zafra', 'España', 'motos@gmail.com', 'A8945988B', 'Autosmocion', '641125874', '06230', (select a.id from user_account a where a.username='tallermotorlog3'), null);
insert into garage VALUES (56, 0, 'Calle naranjo', 'Zafra', 'España', 'cochesSeminuevos@gmail.com', 'A2335124B', 'Cancoches', '712127792', '06230', (select a.id from user_account a where a.username='tallermotorlog4'), null);
insert into content_manager VALUES (57, 0, 'Calle rubi', 'Sevilla', 'España', 'luiscande@gmail.com', '15935748M', 'Luis', '686142130', '41012', (select a.id from user_account a where a.username='cmmotorlog1'));
insert into content_manager VALUES (58, 0, 'Calle zafiro', 'Sevilla', 'España', 'antcarmar@gmail.com', '74125478T', 'Antonio', '612321123', '41012', (select a.id from user_account a where a.username='cmmotorlog2'));
insert into garage VALUES (59, 0, 'Polg.Los Caños C/Gevora 209 b', 'Zafra', 'España', 'motoscande@gmail.com', 'A2335112B', 'Jose Antonio Candelario Montaño', '924553536', '06200', (select a.id from user_account a where a.username='motoscande'), null);
insert into garage VALUES (60, 0, 'Calle Merida', 'Los Santos de Maimona', 'España', 'motomas@gmail.com', 'A1115112B', 'Tomás Candelario Montaño', '622127792', '06230', (select a.id from user_account a where a.username='motomas'), null);
insert into garage VALUES (61, 0, 'Polg.Los Caños C/Gevora 208g', 'Zafra', 'España', 'automocionloscaños@gmail.com', 'A1125112B', 'Emilio Lavado Hernandez', '664874990', '06300', (select a.id from user_account a where a.username='automocionloscanos'), null);
insert into garage VALUES (62, 0, 'Polg.Los Caños C/Albarregas 202 b', 'Zafra', 'España', 'automocionmarianolopez@gmail.com', 'A1135112B', 'Mariano López Morenas', '617227957', '06300', (select a.id from user_account a where a.username='marianolopez'), null);
insert into garage VALUES (63, 0, 'C/ Ulia,9', 'Zafra', 'España', 'afernandezreina@gmail.com', 'A1145112B', 'Antonio Fernández Reina', '954631987', '06300', (select a.id from user_account a where a.username='Fermar'), null);
insert into garage VALUES (64, 0, 'Calle Tabladilla', 'Sevilla', 'España', '3688@redmidas.com', 'A1155112B', 'Esperanza Gil', '954622023', '41013', (select a.id from user_account a where a.username='Midas'), null);
insert into garage VALUES (65, 0, 'Polg.Los Caños C/Guadiana 35', 'Zafra', 'España', 'info@gedauto.com', 'A1165112B', 'Esteban Gordillo Chano', '608701466', '06300', (select a.id from user_account a where a.username='estegorcha'), null);
insert into garage VALUES (66, 0, 'Polg.Los Caños C/Calla 10', 'Zafra', 'España', 'santitaller@hotmail.com', 'A1175112B', 'Santiago Pujol Muñiz', '687610496', '06300', (select a.id from user_account a where a.username='muniz'), null);
insert into garage VALUES (68, 0, 'Ctra. Badajoz Granada km 73600', 'Zafra', 'España', 'zafra@soc.redcitroen.com', 'A1185112B', 'Pedro Suarez Carmona', '924563044', '06300', (select a.id from user_account a where a.username='zaframotor'), null);

--Configuration
insert into configuration values (15,0, 'https://i.ibb.co/kMKrVnh/imageedit-1-8679541703.png', 'https://i.ibb.co/kMKrVnh/imageedit-1-8679541703.png', 'Motorlog', 'Terms and conditions', '0.21', 'Welcome to Motorlog!', '¡Bienvenidos a Motorlog!');

insert into configuration_price_per_month values (15, '20.0', '1');
insert into configuration_price_per_month values (15, '18.0', '6');
insert into configuration_price_per_month values (15, '16.0', '12');

insert into configuration_items_for_car values (15, 'Aceite');
insert into configuration_items_for_car values (15, 'Filtro Aceite');
insert into configuration_items_for_car values (15, 'Luces');
insert into configuration_items_for_car values (15, 'Neumáticos');
insert into configuration_items_for_car values (15, 'Pastillas Freno');
insert into configuration_items_for_car values (15, 'Bujías');
insert into configuration_items_for_car values (15, 'Cadena Transmisión');
insert into configuration_items_for_car values (15, 'Cadena Distribución');
insert into configuration_items_for_car values (15, 'Filtro Aire');
insert into configuration_items_for_car values (15, 'Líquidos Frenos D y T');
insert into configuration_items_for_car values (15, 'Líquido Refrigerante');
insert into configuration_items_for_car values (15, 'Limpieza Carburador');
insert into configuration_items_for_car values (15, 'Reglaje Válvulas');
insert into configuration_items_for_car values (15, 'Aprietes Importantes');
insert into configuration_items_for_car values (15, 'Batería');
insert into configuration_items_for_car values (15, 'Engrase General');
insert into configuration_items_for_car values (15, 'Ajuste Embrague');
insert into configuration_items_for_car values (15, 'Ajuste Acelerador');
insert into configuration_items_for_car values (15, 'Dirección');
insert into configuration_items_for_car values (15, 'Amortiguadores');
insert into configuration_items_for_car values (15, 'Mano de Obra General');

insert into configuration_items_for_moto values (15, 'Aceite');
insert into configuration_items_for_moto values (15, 'Filtro Aceite');
insert into configuration_items_for_moto values (15, 'Luces');
insert into configuration_items_for_moto values (15, 'Neumáticos');
insert into configuration_items_for_moto values (15, 'Pastillas Freno');
insert into configuration_items_for_moto values (15, 'Bujías');
insert into configuration_items_for_moto values (15, 'Cadena Transmisión');
insert into configuration_items_for_moto values (15, 'Cadena Distribución');
insert into configuration_items_for_moto values (15, 'Filtro Aire');
insert into configuration_items_for_moto values (15, 'Líquidos Frenos D y T');
insert into configuration_items_for_moto values (15, 'Líquido Refrigerante');
insert into configuration_items_for_moto values (15, 'Limpieza Carburador');
insert into configuration_items_for_moto values (15, 'Reglaje Válvulas');
insert into configuration_items_for_moto values (15, 'Aprietes Importantes');
insert into configuration_items_for_moto values (15, 'Batería');
insert into configuration_items_for_moto values (15, 'Engrase General');
insert into configuration_items_for_moto values (15, 'Ajuste Embrague');
insert into configuration_items_for_moto values (15, 'Ajuste Acelerador');
insert into configuration_items_for_moto values (15, 'Dirección');
insert into configuration_items_for_moto values (15, 'Amortiguadores');
insert into configuration_items_for_moto values (15, 'Mano de Obra General');

--Motocicletas 1 y 2 (sevillamotor)
insert into vehicle VALUES(100, 0, 'Kawasaki', '25000.00', '256958959TR', 'Gris', '1231', '2509FBT', 'Z900', '12/12/2018', 'https://storage.kawasaki.eu/public/kawasaki.eu/en-EU/model/19ZR900B_40SGY1DRF1CG_A_001.png', '2008', '1', 400);
insert into vehicle VALUES(101, 0, 'Yamaha', '35000.00', '859674526PR', 'Blanco', '1231', '2598JTL', 'FZ25', '12/12/2018', 'https://www.incolmotos-yamaha.com.co/wp-content/uploads/2019/02/z25.png', '2018', '1', 400);

--Motocicleta 3 (canocasion)
insert into vehicle VALUES(102, 0, 'Suzuki', '10000.00', '859674258LT', 'Azul', '1231', '5869CFO', 'GSX150', '12/12/2018', 'https://comandato.vteximg.com.br/arquivos/ids/193660-1000-1000/gixxer_img.jpg?v=636670214270970000', '2006', '1', 400);

--Motocicleta 4 y 5
insert into vehicle VALUES(103, 0, 'Honda', '10000.00', '154724258RF', 'Negra', '1231', '8643DFZ', 'CBR', '12/12/2018', 'https://www.motociclismo.es/media/cache/big/upload/images/article/27713/article-analizamos-honda-cbr250rr-2017-heredera-nsr125-5869478c9a380.jpg', '2018', '1', 400);
insert into vehicle VALUES(104, 0, 'Kawasaki', '10000.00', '524985258DG', 'Verde', '1231', '9773FAX', 'Ninja', '12/12/2018', 'https://storage.kawasaki.eu/public/kawasaki.eu/en-EU/model/18EX650K_44SGN2DRF1CG_A.png', '2010', '1', 400);

--Coche 1 (sevillamotor)
insert into vehicle VALUES(105, 0, 'Citroen', '35000.00', '789456123PR', 'Rojo', '1231', '5623GHL', 'Picasso', '12/12/2018', 'http://www.citroenorigins.es/sites/default/files/styles/1600/public/xsara_picasso_45_1620x1000.png?itok=y8QNA1Y_', '2011', '0', 400);


--Coche 2 (canocasion)
insert into vehicle VALUES(106, 0, 'Jaguar', '89000.00', '758426352TG', 'Azul', '1231', '5879JRR', 'XFRS', '12/12/2018', 'https://parkers-images.bauersecure.com/pagefiles/189338/cut-out/600x400/jaguar_xf_rs.jpg', '2001', '0', 400);

--Coche 3 y 4
insert into vehicle VALUES(107, 0, 'Renault', '10000.00', '478114258FV', 'Amarillo', '1231', '4125DFZ', 'Megane', '12/12/2018', 'https://www.autobild.es/sites/autobild.es/public/styles/main_element/public/dc/fotos/Renault-Megane_RS-2018-C01.jpg?itok=SSsWfy49', '2015', '0', 400);
insert into vehicle VALUES(108, 0, 'Seat', '20000.00', '364158585DG', 'Gris', '1231', '1414BAX', 'Cordoba', '12/12/2018', 'https://cdn.wallapop.com/images/10420/3h/li/__/c10420p210955886/i474724273.jpg?pictureSize=W640', '1999', '0', 400);

--Reparacion1 coche 1
insert into repair VALUES(201, 0, 'Se pone un tubo de escape nuevo', 'Tubo de escape picado', '01/01/2019 12:12', '12/12/2018 16:31', '250.00', '200.00', '50.00', 'Pedro Gonzalez;;647549355;;Calle Zafiro, 34', (select g.id from garage g where g.name='SevillaMotor'), (select v.id from vehicle v where v.license_plate='5623GHL'));


--Reparacion motocicleta 2
insert into repair VALUES(202, 0, 'Cambio de bujías', 'La motocicleta no arranca', '02/01/2019 14:12', '12/12/2018 17:31', '120.00', '80.00', '50.00', 'Gonzalo Martinez;;714257458;;Calle Tarfia, 12', (select g.id from garage g where g.name='SevillaMotor'), (select v.id from vehicle v where v.license_plate='2598JTL'));
insert into repair VALUES(203, 0, 'Cambio de mangito de gasolina', 'Pierde gasolina', '03/01/2019 16:12', '12/12/2018 18:31', '30.00', '15.00', '15.00', 'Gonzalo Martinez;;714257458;;Calle Tarfia, 12', (select g.id from garage g where g.name='SevillaMotor'), (select v.id from vehicle v where v.license_plate='2598JTL'));
insert into repair VALUES(206, 0, 'Cambiar junta de culata', 'Ruido en el motor', '10/01/2019 16:12', '08/02/2019 18:31', '500.00', '250.00', '250.00', 'Gonzalo Martinez;;714257458;;Calle Tarfia, 12', (select g.id from garage g where g.name='SevillaMotor'), (select v.id from vehicle v where v.license_plate='2598JTL'));

--Reparacion motocicleta 1
insert into repair VALUES(204, 0, 'Cambio de filtros', 'Poca potencia', '04/01/2019 16:12', '12/08/2018 18:31', '180.00', '120.00', '60.00', 'Julián Ramirez;;639128744;;Calle Esmeralda, 29', (select g.id from garage g where g.name='SevillaMotor'), (select v.id from vehicle v where v.license_Plate='2509FBT'));

--Reparacion motocicleta 3
insert into repair VALUES(205, 0, 'Se reemplaza por una centralita de segunda mano', 'Arranque electrico no funciona', null, '12/08/2018 18:31', null, null, null, 'Miguel Escudero;;641117787;;Calle Puerto, 15', (select g.id from garage g where g.name='SevillaMotor'), (select v.id from vehicle v where v.license_Plate='5869CFO'));

--Lo mismo que repair
insert into revision VALUES(250, 0, 'El dueño dice de revisar especialmente los frenos', '01/01/2019 16:12', '01/01/2018 16:12', '300.00', '100.0', '200.00', '12/12/2021 12:30', 'Julián Ramirez;;639128744;;Calle Esmeralda, 29', (select g.id from garage g where g.name='SevillaMotor'), (select v.id from vehicle v where v.license_plate='2509FBT'));
insert into revision VALUES(251, 0, 'El cable del embrague lo tiene roto', '02/01/2019 16:12', '02/01/2018 16:12', '300.00', '100.0', '200.00', '12/12/2022 12:30', 'Gonzalo Martinez;;714257458;;Calle Tarfia, 12', (select g.id from garage g where g.name='SevillaMotor'), (select v.id from vehicle v where v.license_plate='2598JTL'));
insert into revision VALUES(252, 0, 'Cubiertas demasiado gastadas', '03/01/2019 16:12', '03/01/2018 16:12', '300.00', '100.0', '200.00', '10/10/2021 12:30', 'Gonzalo Martinez;;714257458;;Calle Tarfia, 12', (select g.id from garage g where g.name='SevillaMotor'), (select v.id from vehicle v where v.license_plate='2598JTL'));
insert into revision VALUES(253, 0, 'Cambiarle el tubo de escape hasta que pase la revisión', '04/01/2019 16:12', '04/01/2018 16:12', '300.00', '100.0', '200.00', '12/12/2021 12:30', 'Pedro Gonzalez;;647549355;;Calle Zafiro, 34', (select g.id from garage g where g.name='SevillaMotor'), (select v.id from vehicle v where v.license_plate='5623GHL'));
insert into revision VALUES(254, 0, 'Le faltan los espejos', null, '05/01//2018 16:12', null, null, null,'12/12/2021 12:30', 'Juan Manuel Moreno;;663748798;;Calle Pollo, 3', (select g.id from garage g where g.name='SevillaMotor'), (select v.id from vehicle v where v.license_plate='5623GHL'));
insert into revision VALUES(255, 0, 'Aunque se haya pasado la revisión, es necesario cambiarle el liquido de freno', '03/01/2019 16:12', '05/01/2018 16:12', '20.00', '15.0', '35.00', '12/05/2021 12:30', 'Gonzalo Martinez;;714257458;;Calle Tarfia, 12', (select g.id from garage g where g.name='SevillaMotor'), (select v.id from vehicle v where v.license_plate='2598JTL'));

--RevisionItem
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, false);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, false);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, false);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, false);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, false);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, false);
insert into revision_is_substituted VALUES(250, true);
insert into revision_is_substituted VALUES(250, false);

insert into revision_is_substituted VALUES(251, true);
insert into revision_is_substituted VALUES(251, false);
insert into revision_is_substituted VALUES(251, false);
insert into revision_is_substituted VALUES(251, true);
insert into revision_is_substituted VALUES(251, true);
insert into revision_is_substituted VALUES(251, false);
insert into revision_is_substituted VALUES(251, true);
insert into revision_is_substituted VALUES(251, false);
insert into revision_is_substituted VALUES(251, true);
insert into revision_is_substituted VALUES(251, false);
insert into revision_is_substituted VALUES(251, true);
insert into revision_is_substituted VALUES(251, false);
insert into revision_is_substituted VALUES(251, false);
insert into revision_is_substituted VALUES(251, true);
insert into revision_is_substituted VALUES(251, false);
insert into revision_is_substituted VALUES(251, true);
insert into revision_is_substituted VALUES(251, false);
insert into revision_is_substituted VALUES(251, true);
insert into revision_is_substituted VALUES(251, false);
insert into revision_is_substituted VALUES(251, true);
insert into revision_is_substituted VALUES(251, false);

insert into revision_is_substituted VALUES(252, true);
insert into revision_is_substituted VALUES(252, false);
insert into revision_is_substituted VALUES(252, false);
insert into revision_is_substituted VALUES(252, true);
insert into revision_is_substituted VALUES(252, true);
insert into revision_is_substituted VALUES(252, false);
insert into revision_is_substituted VALUES(252, true);
insert into revision_is_substituted VALUES(252, false);
insert into revision_is_substituted VALUES(252, true);
insert into revision_is_substituted VALUES(252, false);
insert into revision_is_substituted VALUES(252, true);
insert into revision_is_substituted VALUES(252, true);
insert into revision_is_substituted VALUES(252, true);
insert into revision_is_substituted VALUES(252, true);
insert into revision_is_substituted VALUES(252, false);
insert into revision_is_substituted VALUES(252, true);
insert into revision_is_substituted VALUES(252, false);
insert into revision_is_substituted VALUES(252, true);
insert into revision_is_substituted VALUES(252, false);
insert into revision_is_substituted VALUES(252, true);
insert into revision_is_substituted VALUES(252, false);

insert into revision_is_substituted VALUES(253, false);
insert into revision_is_substituted VALUES(253, false);
insert into revision_is_substituted VALUES(253, false);
insert into revision_is_substituted VALUES(253, true);
insert into revision_is_substituted VALUES(253, true);
insert into revision_is_substituted VALUES(253, false);
insert into revision_is_substituted VALUES(253, true);
insert into revision_is_substituted VALUES(253, false);
insert into revision_is_substituted VALUES(253, true);
insert into revision_is_substituted VALUES(253, true);
insert into revision_is_substituted VALUES(253, true);
insert into revision_is_substituted VALUES(253, true);
insert into revision_is_substituted VALUES(253, true);
insert into revision_is_substituted VALUES(253, true);
insert into revision_is_substituted VALUES(253, false);
insert into revision_is_substituted VALUES(253, true);
insert into revision_is_substituted VALUES(253, false);
insert into revision_is_substituted VALUES(253, true);
insert into revision_is_substituted VALUES(253, false);
insert into revision_is_substituted VALUES(253, true);
insert into revision_is_substituted VALUES(253, false);

insert into revision_is_substituted VALUES(255, false);
insert into revision_is_substituted VALUES(255, false);
insert into revision_is_substituted VALUES(255, false);
insert into revision_is_substituted VALUES(255, true);
insert into revision_is_substituted VALUES(255, true);
insert into revision_is_substituted VALUES(255, false);
insert into revision_is_substituted VALUES(255, true);
insert into revision_is_substituted VALUES(255, false);
insert into revision_is_substituted VALUES(255, true);
insert into revision_is_substituted VALUES(255, true);
insert into revision_is_substituted VALUES(255, true);
insert into revision_is_substituted VALUES(255, true);
insert into revision_is_substituted VALUES(255, true);
insert into revision_is_substituted VALUES(255, true);
insert into revision_is_substituted VALUES(255, false);
insert into revision_is_substituted VALUES(255, true);
insert into revision_is_substituted VALUES(255, false);
insert into revision_is_substituted VALUES(255, true);
insert into revision_is_substituted VALUES(255, false);
insert into revision_is_substituted VALUES(255, true);
insert into revision_is_substituted VALUES(255, false);

insert into revision_is_substituted VALUES(256, false);
insert into revision_is_substituted VALUES(256, false);
insert into revision_is_substituted VALUES(256, false);
insert into revision_is_substituted VALUES(256, true);
insert into revision_is_substituted VALUES(256, true);
insert into revision_is_substituted VALUES(256, false);
insert into revision_is_substituted VALUES(256, true);
insert into revision_is_substituted VALUES(256, false);
insert into revision_is_substituted VALUES(256, true);
insert into revision_is_substituted VALUES(256, true);
insert into revision_is_substituted VALUES(256, true);
insert into revision_is_substituted VALUES(256, true);
insert into revision_is_substituted VALUES(256, true);
insert into revision_is_substituted VALUES(256, true);
insert into revision_is_substituted VALUES(256, false);
insert into revision_is_substituted VALUES(256, true);
insert into revision_is_substituted VALUES(256, false);
insert into revision_is_substituted VALUES(256, true);
insert into revision_is_substituted VALUES(256, false);
insert into revision_is_substituted VALUES(256, true);
insert into revision_is_substituted VALUES(256, false);

--Report
insert into report VALUES(500, 0, 'true', 'Fallo al cargar las imágenes de los vehiculos', '10/11/2018 10:00','https://www.fabricadefotolibros.com/img/upload/error.png', '0', 'Imagenes', (select g.id from garage g where g.name='SevillaMotor'));
insert into report VALUES(501, 0, 'false', 'Agradecería que el tamaño de letra en las listas de reparaciones y revisiones fuera un poco mas grande', '04/08/2018 10:00','https://d1whtlypfis84e.cloudfront.net/guides/wp-content/uploads/2018/10/29232033/report.jpg', '1', 'Letra poco visible', (select g.id from garage g where g.name='Canocasion'));
insert into report VALUES(502, 0, 'false', 'Seria de gran ayuda ordenar los vehiculos por colores', '22/10/2018 10:00','https://previews.123rf.com/images/dazdraperma/dazdraperma1506/dazdraperma150600012/41175052-ilustraci%C3%B3n-de-arco-iris-en-colores-pastel.jpg', '2', 'Listar por colores', (select g.id from garage g where g.name='Canocasion'));
insert into report VALUES(503, 0, 'true', 'Es sistema funciona lento al mostrar los detalles de un vehiculo', '18/12/2018 10:00','https://aldeahost.info/wp-content/uploads/2017/11/2-1.jpg', '3', 'Sistema lento', (select g.id from garage g where g.name='Canocasion'));
insert into report VALUES(504, 0, 'true', 'Tras crear una reparacion nueva, se me quito la aplicacion', '03/01/2019 10:00','https://www.redeszone.net/app/uploads/2017/05/Firefox-Conexi%C3%B3n-segura-fallida.png', '4', 'Acceso al sistema', (select g.id from garage g where g.name='SevillaMotor'));

--ReportUpdate
insert into report_update VALUES(551, 0, 'Arreglado el bug al cargar las imágenes', '12/11/2018 10:00', (select cm.id from content_manager cm where cm.identifier='15935748M'), 500);
insert into report_update VALUES(552, 0, 'Se hara lo posible para hacer la letra mas visible', '15/08/2018 10:00', (select cm.id from content_manager cm where cm.identifier='74125478T'), 501);
insert into report_update VALUES(553, 0, 'Debido a la falta de utilidad no se analizara esta sugerencia', '12/11/2018 10:00', (select cm.id from content_manager cm where cm.identifier='74125478T'), 502);
insert into report_update VALUES(554, 0, 'Mejora de rendimiento al mostrar los detalles de un vehiculo', '30/12/2018 10:00', (select cm.id from content_manager cm where cm.identifier='74125478T'), 503);
insert into report_update VALUES(555, 0, 'Disculpe de las molestias pero el servidor se encontraba en mantenimiento', null, (select cm.id from content_manager cm where cm.identifier='15935748M'), 504);
