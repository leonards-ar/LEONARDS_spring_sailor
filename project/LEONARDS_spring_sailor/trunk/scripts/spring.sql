--GRANT ALL PRIVILEGES ON spring.* TO 'spring'@'localhost' IDENTIFIED BY 'spring1234' WITH GRANT OPTION;
--GRANT ALL PRIVILEGES ON spring.* TO 'spring'@'%' IDENTIFIED BY 'spring1234' WITH GRANT OPTION;

DROP TABLE tbl_messages;
DROP TABLE tbl_reservation_contacts;
DROP TABLE tbl_reservations_shifts;
DROP TABLE tbl_movements;
DROP TABLE tbl_shift_values;
DROP TABLE tbl_profiles_menu_items;
DROP TABLE tbl_reservations;
DROP TABLE tbl_user_contacts;
DROP TABLE tbl_menu_item;
DROP TABLE tbl_boats;
DROP TABLE tbl_users;
DROP TABLE tbl_user_states;
DROP TABLE tbl_reservation_states;
DROP TABLE tbl_reservation_types;
DROP TABLE tbl_boat_states;
DROP TABLE tbl_movement_types;
DROP TABLE tbl_parameters;
DROP TABLE tbl_shifts;
DROP TABLE tbl_menu_group;
DROP TABLE tbl_profiles;
DROP TABLE tbl_permit_types;
DROP TABLE tbl_document_types;

CREATE TABLE tbl_document_types (
       document_type_id INTEGER(3) NOT NULL
     , description VARCHAR(50)
     , PRIMARY KEY (document_type_id)
)TYPE=InnoDB;

CREATE TABLE tbl_permit_types (
       permit_type_id INTEGER(3) NOT NULL
     , description VARCHAR(50)
     , PRIMARY KEY (permit_type_id)
)TYPE=InnoDB;

CREATE TABLE tbl_profiles (
       profile_id INTEGER(10) NOT NULL
     , description VARCHAR(50)
     , special_reserver INTEGER(1)
     , home_menu_item_id INTEGER(3)
     , PRIMARY KEY (profile_id)
)TYPE=InnoDB;

CREATE TABLE tbl_menu_group (
       menu_group_id INTEGER(3) NOT NULL
     , description VARCHAR(50)
     , PRIMARY KEY (menu_group_id)
)TYPE=InnoDB;

CREATE TABLE tbl_shifts (
       shift_id INTEGER(10) NOT NULL
     , description VARCHAR(100)
     , time_to INTEGER(10)
     , time_from INTEGER(10)
     , enabled INTEGER(1) DEFAULT 1
     , PRIMARY KEY (shift_id)
)TYPE=InnoDB;

CREATE TABLE tbl_parameters (
       name VARCHAR(100) NOT NULL
     , value VARCHAR(255)
     , label VARCHAR(255)
     , reference_data_statement VARCHAR(255)
     , PRIMARY KEY (name)
);

CREATE TABLE tbl_movement_types (
       movement_type_id INTEGER(5) NOT NULL
     , description VARCHAR(50)
     , sign INTEGER(1)
     , PRIMARY KEY (movement_type_id)
)TYPE=InnoDB;

CREATE TABLE tbl_boat_states (
       state_id INTEGER(3) NOT NULL
     , description VARCHAR(50)
     , can_reserve INTEGER(1)
     , PRIMARY KEY (state_id)
)TYPE=InnoDB;

CREATE TABLE tbl_reservation_types (
       reservation_type_id INTEGER(3) NOT NULL
     , description VARCHAR(50)
     , shifts_qty INTEGER(3)
     , enabled INTEGER(1) DEFAULT 1
     , extra_value DECIMAL(10, 2) DEFAULT 0.0
     , PRIMARY KEY (reservation_type_id)
)TYPE=InnoDB;

CREATE TABLE tbl_reservation_states (
       reservation_state_id INTEGER(3) NOT NULL
     , description VARCHAR(50)
     , PRIMARY KEY (reservation_state_id)
)TYPE=InnoDB;

CREATE TABLE tbl_user_states (
       state_id INTEGER(3) NOT NULL
     , description VARCHAR(50)
     , can_reserve INTEGER(1)
     , PRIMARY KEY (state_id)
)TYPE=InnoDB;

CREATE TABLE tbl_users (
       user_id INTEGER(10) NOT NULL AUTO_INCREMENT
     , username VARCHAR(50) NOT NULL
     , password VARCHAR(255)
     , name VARCHAR(255)
     , surname VARCHAR(255)
     , email VARCHAR(255)
     , address VARCHAR(255)
     , telephone VARCHAR(50)
     , cel_phone VARCHAR(50)
     , document_type_id INTEGER(3)
     , document VARCHAR(50)
     , permit_type_id INTEGER(3)
     , permit VARCHAR(50)
     , permit_due_date DATETIME
     , dossier VARCHAR(10)
     , state_id INTEGER(3)
     , profile_id INTEGER(10)
     , department VARCHAR(50)
     , balance DECIMAL(10, 2)
     , deleted INTEGER(1)
     , PRIMARY KEY (user_id)
     , INDEX (state_id)
     , CONSTRAINT FK_tbl_users_1 FOREIGN KEY (state_id)
                  REFERENCES tbl_user_states (state_id) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (document_type_id)
     , CONSTRAINT FK_tbl_users_2 FOREIGN KEY (document_type_id)
                  REFERENCES tbl_document_types (document_type_id)
     , INDEX (permit_type_id)
     , CONSTRAINT FK_tbl_users_3 FOREIGN KEY (permit_type_id)
                  REFERENCES tbl_permit_types (permit_type_id)
     , INDEX (profile_id)
     , CONSTRAINT FK_tbl_users_4 FOREIGN KEY (profile_id)
                  REFERENCES tbl_profiles (profile_id)
)TYPE=InnoDB;

CREATE TABLE tbl_boats (
       boat_id INTEGER(3) NOT NULL
     , name VARCHAR(100)
     , state_id INTEGER(3)
     , state_date_from DATETIME
     , state_date_to DATETIME
     , state_comments VARCHAR(255)
     , PRIMARY KEY (boat_id)
     , INDEX (state_id)
     , CONSTRAINT FK_tbl_boats_1 FOREIGN KEY (state_id)
                  REFERENCES tbl_boat_states (state_id)
)TYPE=InnoDB;

CREATE TABLE tbl_menu_item (
       menu_item_id INTEGER(3) NOT NULL
     , description VARCHAR(50)
     , target_url VARCHAR(255)
     , target VARCHAR(50)
     , menu_group_id INTEGER(3)
     , menu_order INTEGER(2)
     , PRIMARY KEY (menu_item_id)
     , INDEX (menu_group_id)
     , CONSTRAINT FK_tbl_menu_item_1 FOREIGN KEY (menu_group_id)
                  REFERENCES tbl_menu_group (menu_group_id)
)TYPE=InnoDB;

CREATE TABLE tbl_user_contacts (
       contact_id INTEGER(10) NOT NULL AUTO_INCREMENT
     , user_id INTEGER(10)
     , full_name VARCHAR(100)
     , document_type_id INTEGER(3)
     , document VARCHAR(50)
     , telephone VARCHAR(50)
     , deleted INTEGER(1) DEFAULT 0
     , PRIMARY KEY (contact_id)
     , INDEX (user_id)
     , CONSTRAINT FK_tbl_user_contactas_1 FOREIGN KEY (user_id)
                  REFERENCES tbl_users (user_id)
     , INDEX (document_type_id)
     , CONSTRAINT FK_tbl_user_contactas_3 FOREIGN KEY (document_type_id)
                  REFERENCES tbl_document_types (document_type_id)
)TYPE=InnoDB;

CREATE TABLE tbl_reservations (
       reservation_id INTEGER(10) NOT NULL AUTO_INCREMENT
     , state_date DATETIME DEFAULT null
     , value DECIMAL(10, 2)
     , user_id INTEGER(10)
     , reservation_type_id INTEGER(3)
     , reservation_state_id INTEGER(3)
     , boat_id INTEGER(3)
     , PRIMARY KEY (reservation_id)
     , INDEX (user_id)
     , CONSTRAINT FK_tbl_reservations_1 FOREIGN KEY (user_id)
                  REFERENCES tbl_users (user_id)
     , INDEX (reservation_type_id)
     , CONSTRAINT FK_tbl_reservations_2 FOREIGN KEY (reservation_type_id)
                  REFERENCES tbl_reservation_types (reservation_type_id)
     , INDEX (reservation_state_id)
     , CONSTRAINT FK_tbl_reservations_3 FOREIGN KEY (reservation_state_id)
                  REFERENCES tbl_reservation_states (reservation_state_id)
     , INDEX (boat_id)
     , CONSTRAINT FK_tbl_reservations_7 FOREIGN KEY (boat_id)
                  REFERENCES tbl_boats (boat_id)
)TYPE=InnoDB;

CREATE TABLE tbl_profiles_menu_items (
       menu_item_id INTEGER(3) NOT NULL
     , profile_id INTEGER(10) NOT NULL
     , PRIMARY KEY (menu_item_id, profile_id)
     , INDEX (profile_id)
     , CONSTRAINT FK_tbl_profiles_menu_items_1 FOREIGN KEY (profile_id)
                  REFERENCES tbl_profiles (profile_id)
     , INDEX (menu_item_id)
     , CONSTRAINT FK_tbl_profiles_menu_items_2 FOREIGN KEY (menu_item_id)
                  REFERENCES tbl_menu_item (menu_item_id)
)TYPE=InnoDB;

CREATE TABLE tbl_shift_values (
       day INTEGER(1) NOT NULL
     , value DECIMAL(10, 2)
     , shift_id INTEGER(10) NOT NULL
     , PRIMARY KEY (day, shift_id)
     , INDEX (shift_id)
     , CONSTRAINT FK_tbl_shift_details_1 FOREIGN KEY (shift_id)
                  REFERENCES tbl_shifts (shift_id)
)TYPE=InnoDB;

CREATE TABLE tbl_movements (
       movement_id INTEGER(10) NOT NULL AUTO_INCREMENT
     , movement_type_id INTEGER(5)
     , amount DECIMAL(10, 2)
     , balance DECIMAL(10, 2)
     , document_data VARCHAR(255)
     , comments VARCHAR(255)
     , date DATETIME
     , user_id INTEGER(10)
     , PRIMARY KEY (movement_id)
     , INDEX (movement_type_id)
     , CONSTRAINT FK_tbl_movements_1 FOREIGN KEY (movement_type_id)
                  REFERENCES tbl_movement_types (movement_type_id)
)TYPE=InnoDB;

CREATE TABLE tbl_reservations_shifts (
       date DATE NOT NULL
     , reservation_id INTEGER(10) NOT NULL
     , shift_id INTEGER(10) NOT NULL
     , PRIMARY KEY (date, reservation_id, shift_id)
     , INDEX (shift_id)
     , CONSTRAINT FK_tbl_reservations_shifts_1 FOREIGN KEY (shift_id)
                  REFERENCES tbl_shifts (shift_id)
     , INDEX (reservation_id)
     , CONSTRAINT FK_tbl_reservations_shifts_2 FOREIGN KEY (reservation_id)
                  REFERENCES tbl_reservations (reservation_id)
)TYPE=InnoDB;

CREATE TABLE tbl_reservation_contacts (
       reservation_id INTEGER(10) NOT NULL
     , contact_id INTEGER(10) NOT NULL
     , PRIMARY KEY (reservation_id, contact_id)
     , INDEX (reservation_id)
     , CONSTRAINT FK_tbl_reservation_contacts_1 FOREIGN KEY (reservation_id)
                  REFERENCES tbl_reservations (reservation_id)
     , INDEX (contact_id)
     , CONSTRAINT FK_tbl_reservation_contacts_2 FOREIGN KEY (contact_id)
                  REFERENCES tbl_user_contacts (contact_id)
)TYPE=InnoDB;

CREATE TABLE tbl_messages (
       message_id INTEGER(10) NOT NULL AUTO_INCREMENT
     , user_id INTEGER(10)
     , date DATETIME
     , message TEXT
     , PRIMARY KEY (message_id)
     , INDEX (user_id)
     , CONSTRAINT FK_messages_1 FOREIGN KEY (user_id)
                  REFERENCES tbl_users (user_id)
)TYPE=InnoDB;

