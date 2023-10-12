
--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS CUSTOMER;
CREATE TABLE CUSTOMER (
  id int AUTO_INCREMENT PRIMARY KEY,
  email varchar(255) DEFAULT NULL,
  FIRSTNAME varchar(255) DEFAULT NULL,
  LASTNAME varchar(255) DEFAULT NULL
);
--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS CAR;
CREATE TABLE CAR (
  is_booked boolean DEFAULT false,
  model_year int DEFAULT NULL,
  id int AUTO_INCREMENT PRIMARY KEY,
  price numeric(9,2) DEFAULT NOT NULL,
  color varchar(255) DEFAULT  NULL,
  manufacturer varchar(255) DEFAULT  NULL,
  model varchar(255) DEFAULT NULL,
  customer_id int NULL,
  FOREIGN KEY (customer_id) REFERENCES customer (id)
);

INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Carl','Bailey','carl.bailey@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Keith','Turner','keith.turner@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Lauren','Duncan','lauren.duncan@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Adrian','Baker','adrian.baker@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Eric','MacLeod','eric.macleod@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Irene','Nash','irene.nash@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Bella','Stewart','bella.stewart@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Sonia','Slater','sonia.slater@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Emma','Roberts','emma.roberts@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Connor','Sharp','connor.sharp@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Colin','Ogden','colin.ogden@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Theresa','Thomson','theresa.thomson@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Lisa','Edmunds','lisa.edmunds@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Anthony','Murray','anthony.murray@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Theresa','Walsh','theresa.walsh@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Carol','Davidson','carol.davidson@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Oliver','Morgan','oliver.morgan@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Caroline','Kelly','caroline.kelly@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Jacob','Powell','jacob.powell@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('John','Randall','john.randall@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Audrey','Hamilton','audrey.hamilton@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Audrey','Marshall','audrey.marshall@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Nicola','Black','nicola.black@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Simon','Turner','simon.turner@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Anthony','Churchill','anthony.churchill@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('David','Welch','david.welch@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Sonia','Bailey','sonia.bailey@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('William','Pullman','william.pullman@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Zoe','Dyer','zoe.dyer@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Grace','Mills','grace.mills@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Virginia','Nolan','virginia.nolan@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Alexander','Scott','alexander.scott@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Ryan','Bower','ryan.bower@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Mary','MacLeod','mary.macleod@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Emily','Clark','emily.clark@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Paul','Duncan','paul.duncan@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Simon','Ellison','simon.ellison@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Pippa','Oliver','pippa.oliver@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Rachel','Quinn','rachel.quinn@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Molly','Pullman','molly.pullman@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Natalie','Paterson','natalie.paterson@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Fiona','Morgan','fiona.morgan@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('David','Buckland','david.buckland@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Ryan','Henderson','ryan.henderson@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Jacob','White','jacob.white@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Nicholas','Quinn','nicholas.quinn@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Rachel','Parsons','rachel.parsons@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Eric','Young','eric.young@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Christian','Gibson','christian.gibson@gmail.com');
INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,email) VALUES ('Molly','Morgan','molly.morgan@gmail.com');


INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year,is_booked,customer_id) VALUES ('Audi','Q3',1000,'Black',2020,true,2);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year,is_booked,customer_id) VALUES ('Chevrolet','Malibu',1000,'Black',2020,true,2);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year,is_booked,customer_id) VALUES ('Cadillac','Escalade ESV',1000,'Black',2020,true,2);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Chevrolet','Corvette',1000,'Black',2020);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Acura','RLX',1000,'Black',2020);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Chevrolet','Silverado 2500 HD Crew Cab',1000,'Black',2020);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('BMW','3 Series',1000,'Black',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Chrysler','Pacifica',1000,'Black',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Chevrolet','Colorado Crew Cab',1000,'Black',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('BMW','X3',1000,'Black',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Acura','TLX',1000,'Black',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Chevrolet','Silverado 3500 HD Crew Cab',1000,'Black',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('BMW','7 Series',1000,'Black',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Ford','Fusion',1000,'Black',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Buick','Envision',1000,'White',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Audi','SQ5',1000,'White',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Audi','R8',1000,'White',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Chevrolet','Traverse',1000,'White',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Acura','MDX',1000,'White',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('INFINITI','QX80',1000,'White',2021);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Buick','Encore',1000,'White',2022);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('GMC','Sierra 2500 HD Crew Cab',1000,'White',2022);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Honda','Insight',1000,'White',2022);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Cadillac','XT6',1000,'White',2022);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Cadillac','XT5',1000,'White',2022);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Cadillac','XT4',1000,'White',2022);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Buick','Enclave',1000,'White',2022);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Audi','Q5',1000,'White',2022);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Hyundai','Santa Fe',1000,'White',2022);
INSERT INTO CAR(Manufacturer,Model,Price,Color,model_year) VALUES ('Ford','EcoSport',1000,'White',2022);


