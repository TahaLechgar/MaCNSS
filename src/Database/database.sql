use MaCNSS;

create table if not exists Patient (
                         immatricule bigint primary key auto_increment,
                         fullName varchar(100) not null,
                         birthDate date not null,
                         email varchar(100),
                         cin varchar(10) unique not null
);

create table if not exists Conjoint (
                          id bigint primary key auto_increment,
                          patientImm bigint,
                          fullName varchar(100) not null,
                          relationship enum('spouse', 'parent') not null,
                          birthDate date not null,
                          foreign key (patientImm) references Patient(immatricule)
);

create table if not exists Dossier (
                         id bigint primary key auto_increment,
                         state enum('pending', 'validated', 'refused') default 'pending',
                         patientImm bigint not null,
                         consultationType varchar(30) not null,
                         depositDate date not null,
                         repaymentAmount float default 0,
                         foreign key (patientImm) references Patient(immatricule)
);

create table if not exists Medications (
                             codeBarre bigint primary key auto_increment,
                             name varchar(100) not null,
                             price float not null,
                             repaymentPrice float not null
);

create table if not exists Prescription (
                                            id bigint primary key auto_increment,
                                            medicationId bigint not null,
                                            dossierId bigint not null,
                                            consultationDate date not null,
                                            quantity int default 1,
                                            foreign key (dossierId) references Dossier(id),
                                            foreign key (medicationId) references Medications(codeBarre),
                                            unique (medicationId, dossierId)
);

create table if not exists Attachement (
                             id bigint primary key auto_increment,
                             type enum('analyzes', 'scanner', 'radio') not null,
                             dossierId bigint not null,
                             price float not null,
                             foreign key (dossierId) references Dossier(id)
);

create table if not exists User (
                      id bigint primary key auto_increment,
                      username varchar(20) unique not null,
                      password varchar(40) not null,
                      fullName varchar(100) not null,
                      birthDate date not null
);

insert ignore into Medications (codeBarre, name, price, repaymentPrice) values (6118000010050, 'ABBOTICINE', 26.80, 26.80);
insert ignore into Medications (codeBarre, name, price, repaymentPrice) values (6118001170371, 'ACCUPRIL', 41.70, 41.70);
insert ignore into Medications (codeBarre, name, price, repaymentPrice) values (6118000260202, 'ACETATE', 172.80, 161.40);
insert ignore into Medications (codeBarre, name, price, repaymentPrice) values (6118000040361, 'DOLIPRANE', 15.80, 15.00);
insert ignore into Medications (codeBarre, name, price, repaymentPrice) values (6118001100934, 'DOCETAXEL', 2575.0, 2575.0);
insert ignore into Medications (codeBarre, name, price, repaymentPrice) values (6118000060062, 'FLAGYL', 49.80, 39.90);
insert ignore into Medications (codeBarre, name, price, repaymentPrice) values (6118001030125, 'COTAREG', 153.0, 148.0);
insert ignore into Medications (codeBarre, name, price, repaymentPrice) values (6118001101580, 'SYNNAX', 370.0, 370.0);
insert ignore into Medications (codeBarre, name, price, repaymentPrice) values (6118000032632, 'OXIFLOR', 112.0, 112.80);
insert ignore into Medications (codeBarre, name, price, repaymentPrice) values (6118000091011, 'ULCESTOP', 59.50, 37.10);


insert ignore into Patient (fullName, birthDate, email, cin) values ( 'Taha Lechgar', '2001-04-10', 'tahamr08@gmail.com', 'HH75402');
insert ignore into Patient (fullName, birthDate, email, cin) values ( 'Abderrahmane Dachoucha', '2000-08-27', 'im.dachoucha@gmail.com', 'HH12345');
insert ignore into Patient (fullName, birthDate, email, cin) values ( 'Mohammed Ait-gaba', '2001-02-14', 'medgaba237@gmail.com', 'HH54321');

insert ignore into Conjoint ( patientImm, fullName, relationship, birthDate) values (1, 'Nissrine Bennani', 'spouse', '2002-03-22');
insert ignore into Conjoint ( patientImm, fullName, relationship, birthDate) values (1, 'Reda Lechgar', 'parent', '2020-01-14');
insert ignore into Conjoint ( patientImm, fullName, relationship, birthDate) values (1, 'Youness Lechgar', 'parent', '2021-03-21');
insert ignore into Conjoint ( patientImm, fullName, relationship, birthDate) values (2, 'Adara Berrada', 'spouse', '2000-07-13');
insert ignore into Conjoint ( patientImm, fullName, relationship, birthDate) values (2, 'Mohammed Dachoucha', 'parent', '2019-02-01');
insert ignore into Conjoint ( patientImm, fullName, relationship, birthDate) values (2, 'Yasmine Dachoucha', 'parent', '2020-04-29');
insert ignore into Conjoint ( patientImm, fullName, relationship, birthDate) values (3, 'Hajiba Belhmad', 'spouse', '2002-09-18');
insert ignore into Conjoint ( patientImm, fullName, relationship, birthDate) values (3, 'Hassan Ait-gaba', 'parent', '2019-05-27');
insert ignore into Conjoint ( patientImm, fullName, relationship, birthDate) values (3, 'Abdelali Ait-gaba', 'parent', '2020-08-02');


