INSERT INTO `flight_booking`.`user` (`id`,`firstname`, `middlename`, `lastname`, `email`, `password`, `phone`, `address`, `role`)
VALUES
  ('0', 'John', 'John', 'Doe', 'string1', '$2a$10$vEzHnR.FkZgOozwhzYuslOclsQww3.ayWznZjawerpvoaGie1X3XG', '1234567890', '123 Main St', '1'),
  ('1', 'John', 'Jane', 'Doe', 'john.doe@example.com', '$2a$10$vEzHnR.FkZgOozwhzYuslOclsQww3.ayWznZjawerpvoaGie1X3XG', '1234567890', '123 Main St', '2'),
  ('2', 'Jane', 'David', 'Smith', 'jane.smith@example.com', '$2a$10$vEzHnR.FkZgOozwhzYuslOclsQww3.ayWznZjawerpvoaGie1X3XG', '0987654321', '456 Elm St', '2'),
  ('3', 'David', 'Sarah', 'Johnson', 'david.johnson@example.com', '$2a$10$vEzHnR.FkZgOozwhzYuslOclsQww3.ayWznZjawerpvoaGie1X3XG', '9876543210', '789 Oak St', '2'),
  ('4', 'Sarah', 'Michael', 'Williams', 'sarah.williams@example.com', '$2a$10$vEzHnR.FkZgOozwhzYuslOclsQww3.ayWznZjawerpvoaGie1X3XG', '0123456789', '567 Pine St', '2'),
  ('5', 'Michael', 'Emily', 'Brown', 'michael.brown@example.com', '$2a$10$vEzHnR.FkZgOozwhzYuslOclsQww3.ayWznZjawerpvoaGie1X3XG', '5432109876', '321 Cedar St', '2'),
  ('6', 'Emily', 'Daniel', 'Davis', 'emily.davis@example.com', '$2a$10$vEzHnR.FkZgOozwhzYuslOclsQww3.ayWznZjawerpvoaGie1X3XG', '6543210987', '654 Birch St', '2'),
  ('7', 'Daniel', 'Olivia', 'Miller', 'daniel.miller@example.com', '$2a$10$vEzHnR.FkZgOozwhzYuslOclsQww3.ayWznZjawerpvoaGie1X3XG', '8765432109', '987 Maple St', '1'),
  ('8', 'Olivia', 'James', 'Wilson', 'olivia.wilson@example.com', '$2a$10$vEzHnR.FkZgOozwhzYuslOclsQww3.ayWznZjawerpvoaGie1X3XG', '1098765432', '890 Walnut St', '2'),
  ('9', 'James', 'Sophia', 'Anderson', 'james.anderson@example.com', '$2a$10$vEzHnR.FkZgOozwhzYuslOclsQww3.ayWznZjawerpvoaGie1X3XG', '3210987654', '345 Pine St', '1'),
  ('10', 'Sophia', 'John', 'Thomas', 'sophia.thomas@example.com', '$2a$10$vEzHnR.FkZgOozwhzYuslOclsQww3.ayWznZjawerpvoaGie1X3XG', '9876543210', '678 Elm St', '1');
  
INSERT INTO `flight_booking`.`booking` (`id`,`booking_status`, `cancellation_answer`, `created_at`, `user_id`)
VALUES
  ('1', 'UP', null, CURRENT_TIMESTAMP, 1),
  ('2', 'UP', null, CURRENT_TIMESTAMP, 2),
  ('3', 'UP', null, CURRENT_TIMESTAMP, 3),
  ('4', 'UP', null, CURRENT_TIMESTAMP, 4),
  ('5', 'UP', null, CURRENT_TIMESTAMP, 5),
  ('6', 'UP', null, CURRENT_TIMESTAMP, 6),
  ('7', 'UP', null, CURRENT_TIMESTAMP, 7),
  ('8', 'UP', null, CURRENT_TIMESTAMP, 8),
  ('9', 'UP', null, CURRENT_TIMESTAMP, 9),
  ('10', 'UP', null, CURRENT_TIMESTAMP, 10);
  
INSERT INTO `flight_booking`.`flight` (`id`,`airlinecode`, `flightnumber`, `origin`, `destination`, `date`, `time`, `aircrafttype`)
VALUES
  ('1', 'LH', 'AA123', 'LAX', 'JFK', '2023-06-01', '12:00', 'Boeing 737'),
  ('2', 'OS', 'DL456', 'JFK', 'LAX', '2023-06-02', '14:30', 'Airbus A320'),
  ('3', 'LX', 'UA789', 'ORD', 'DFW', '2023-06-03', '10:45', 'Boeing 747'),
  ('4', 'EW', 'AA246', 'DFW', 'ORD', '2023-06-04', '08:15', 'Airbus A380'),
  ('5', 'LH', 'DL135', 'LAX', 'ORD', '2023-06-05', '15:20', 'Boeing 777'),
  ('6', 'LH', 'UA680', 'ORD', 'JFK', '2023-06-06', '17:30', 'Airbus A330');
  
INSERT INTO `flight_booking`.`flight_type` (`id`, `total_seats`, `flight_class`, `flight_id`)
VALUES
  ('1', 150, 'ECONOMY', 1),  ('2', 50, 'PREMIUM_ECONOMY', 1),  ('3', 30, 'BUSINESS', 1),  ('4', 20, 'FIRST', 1),
  ('5', 200, 'ECONOMY', 2),  ('6', 100, 'PREMIUM_ECONOMY', 2),  ('7', 40, 'BUSINESS', 2),  ('8', 10, 'FIRST', 2),
  ('9', 180, 'ECONOMY', 3),  ('10', 70, 'PREMIUM_ECONOMY', 3),  ('11', 50, 'BUSINESS', 3),  ('12', 15, 'FIRST', 3),
  ('13', 170, 'ECONOMY', 4),  ('14', 80, 'PREMIUM_ECONOMY', 4),  ('15', 60, 'BUSINESS', 4),  ('16', 25, 'FIRST', 4),
  ('17', 190, 'ECONOMY', 5),  ('18', 90, 'PREMIUM_ECONOMY', 5),  ('19', 70, 'BUSINESS', 5),  ('20', 30, 'FIRST', 5),
  ('21', 160, 'ECONOMY', 6),  ('22', 60, 'PREMIUM_ECONOMY', 6),  ('23', 40, 'BUSINESS', 6),  ('24', 10, 'FIRST', 6);
  
INSERT INTO `flight_booking`.`flight_booking` (`id`, `flight_id`, `booking_id`, `flight_class`)
VALUES
  ('1', 1, 1, 'ECONOMY'),
  ('2', 1, 2, 'PREMIUM_ECONOMY'),
  ('3', 2, 3, 'BUSINESS'),
  ('4', 3, 4, 'ECONOMY'),
  ('5', 4, 5, 'PREMIUM_ECONOMY'),
  ('6', 5, 6, 'BUSINESS'),
  ('7', 6, 7, 'ECONOMY'),
  ('8', 6, 8, 'BUSINESS'),
  ('9', 6, 9, 'FIRST'),
  ('10', 2, 10, 'ECONOMY');
  