
--insert into tbl_users values (null, 'admin', '81dc9bdb52d04dc20036dbd8313ed055', 'Administrador', 'Administrador', 'marianocapurro@fibertel.com.ar', 'Dr. Eduardo Madero 1324', '4797-6996', '', 1, '25.967.547', 1, '', '', '', 1, 1, 'AGITBA', 0.0);
--insert into tbl_reservations values (null, now(), 20, 1, 1, 1, 1);
--insert into tbl_reservations_shifts values ('2004-07-24 00:00:00', 1, 1);

insert into tbl_reservation_states values (1, 'Activa');
insert into tbl_reservation_states values (2, 'Cancelada por Usuario');
insert into tbl_reservation_states values (3, 'Cancelada por Administrador');
insert into tbl_reservation_states values (4, 'Salida Realizada');

insert into tbl_reservation_types values (1, 'Reserva Turno', 1, 1, 0.0);
insert into tbl_reservation_types values (2, 'Reserva Crucero Colonia', 4, 1, 0.0);
insert into tbl_reservation_types values (3, 'Reserva Dia', 2, 1, 0.0);


insert into tbl_document_types values (1, 'DNI');
insert into tbl_document_types values (2, 'CI');

insert into tbl_permit_types values (1, 'Timonel Yate Vela Motor');
insert into tbl_permit_types values (2, 'Patron Yate Vela Motor');

insert into tbl_parameters values ('MAIN_ADMIN_ID', '1', 'Administrador por defecto', 'select user_id as id, concat(surname, \', \', name) as description from tbl_users order by surname, name');
insert into tbl_parameters values ('DEFAULT_BOAT_ID', '1', 'Barco por defecto', 'select boat_id as id, name as description from tbl_boats order by name');
insert into tbl_parameters values ('RESERVATION_MOVEMENT', '1', 'Movimiento de Ingreso para Reservas', 'select movement_type_id as id, description from tbl_movement_types order by description');
insert into tbl_parameters values ('ACTIVE_RESERVATION_STATE_ID', '1', 'Estado para reserva activa', 'select reservation_state_id as id, description from tbl_reservation_states order by description');
insert into tbl_parameters values ('MAX_CREDIT_LIMIT', '20', 'Deuda Maxima', null);
insert into tbl_parameters values ('BOAT_TIME_TABLE_DAYS', '15', 'Cantidad de dias del horario', null);
insert into tbl_parameters values ('MAX_ACTIVE_RESERVATIONS', '1', 'Cantidad maxima de reservas activas', null);
insert into tbl_parameters values ('MAX_INVITED_CREW', '6', 'Cantidad maxima de invitados', null);
insert into tbl_parameters values ('ADMIN_CANCEL_RESERVATION_STATE_ID', '3', 'Estado para reserva cancelada por administrador', 'select reservation_state_id as id, description from tbl_reservation_states order by description');
insert into tbl_parameters values ('USER_CANCEL_RESERVATION_STATE_ID', '2', 'Estado para reserva cancelada por usuario', 'select reservation_state_id as id, description from tbl_reservation_states order by description');


insert into tbl_movement_types values (1, 'Ingreso para Reservas', 1);
insert into tbl_movement_types values (2, 'Egreso por Reparaciones', -1);
insert into tbl_movement_types values (3, 'Otros Ingresos', 1);
insert into tbl_movement_types values (4, 'Otros Egresos', -1);
insert into tbl_movement_types values (5, 'Combustible', -1);
insert into tbl_movement_types values (6, 'Honorarios Marineria', -1);



insert into tbl_boat_states values (1, 'Habilitado', 1);
insert into tbl_boat_states values (2, 'En Reparaciones', 0);
insert into tbl_boat_states values (3, 'En Regata', 0);
insert into tbl_boat_states values (4, 'En Entrenamiento Regata', 0);

insert into tbl_user_states values (1, 'Habilitado', 1);
insert into tbl_user_states values (2, 'Inhibido', 0);

insert into tbl_boats values (1, 'Primavera', 1, null, null, null);

insert into tbl_menu_group values (1, 'Administracion');
insert into tbl_menu_group values (2, 'Cuenta Corriente');
insert into tbl_menu_group values (3, 'Reservas');
insert into tbl_menu_group values (4, 'Reportes');

insert into tbl_menu_item values (1, 'Usuarios', 'USERS_MAIN', 'bodyFrame', 1, 1);
insert into tbl_menu_item values (2, 'Barcos', 'BOATS_MAIN', 'bodyFrame', 1, 2);
insert into tbl_menu_item values (3, 'Reservas', 'ADMIN_RESERVATION_MAIN', 'bodyFrame', 1, 3);
insert into tbl_menu_item values (4, 'Parametros', 'PARAMETERS_MAIN', 'bodyFrame', 1, 5);
insert into tbl_menu_item values (14, 'Mensajes', 'MESSAGES_ADMIN_MAIN', 'bodyFrame', 1, 4);


insert into tbl_menu_item values (5, 'Ingresos Reservas', 'RESERVATION_INCOME_MAIN', 'bodyFrame', 2, 1);
insert into tbl_menu_item values (6, 'Otros Ingresos', 'OTHER_INCOME_MAIN', 'bodyFrame', 2, 2);
insert into tbl_menu_item values (7, 'Egresos', 'EXPENSE_MAIN', 'bodyFrame', 2, 3);
insert into tbl_menu_item values (8, 'Cuenta Corriente', 'CURRENT_ACCOUNT_MAIN', 'bodyFrame', 2, 4);

insert into tbl_menu_item values (9, 'Reservas', 'USER_RESERVATION_MAIN', 'bodyFrame', 3, 2);
insert into tbl_menu_item values (10, 'Mensajes', 'MESSAGES_MAIN', 'bodyFrame', 3, 3);
insert into tbl_menu_item values (11, 'Mis Datos', 'MY_PROFILE_MAIN', 'bodyFrame', 3, 4);
insert into tbl_menu_item values (12, 'Pagina Principal', 'HOME_MAIN', 'bodyFrame', 3, 1);

insert into tbl_menu_item values (13, 'Reportes', 'REPORTS_MAIN', 'bodyFrame', 4, 1);


insert into tbl_profiles values (1, 'Administrador', 0, 12);
insert into tbl_profiles values (2, 'Usuario Navegante', 0, 12);
insert into tbl_profiles values (3, 'Instructor Curso YCA', 1, 12);
insert into tbl_profiles values (4, 'Administrador Ingresos Reservas', 0, 5);
insert into tbl_profiles values (5, 'Administrador No Navegante', 0, 1);




insert into tbl_profiles_menu_items values (1, 1);
insert into tbl_profiles_menu_items values (2, 1);
insert into tbl_profiles_menu_items values (3, 1);
insert into tbl_profiles_menu_items values (4, 1);
insert into tbl_profiles_menu_items values (5, 1);
insert into tbl_profiles_menu_items values (6, 1);
insert into tbl_profiles_menu_items values (7, 1);
insert into tbl_profiles_menu_items values (8, 1);
insert into tbl_profiles_menu_items values (9, 1);
insert into tbl_profiles_menu_items values (10, 1);
insert into tbl_profiles_menu_items values (11, 1);
insert into tbl_profiles_menu_items values (12, 1);
insert into tbl_profiles_menu_items values (13, 1);
insert into tbl_profiles_menu_items values (14, 1);

insert into tbl_profiles_menu_items values (8, 2);
insert into tbl_profiles_menu_items values (9, 2);
insert into tbl_profiles_menu_items values (10, 2);
insert into tbl_profiles_menu_items values (11, 2);
insert into tbl_profiles_menu_items values (12, 2);

insert into tbl_profiles_menu_items values (13, 3);
insert into tbl_profiles_menu_items values (9, 3);
insert into tbl_profiles_menu_items values (10, 3);
insert into tbl_profiles_menu_items values (11, 3);
insert into tbl_profiles_menu_items values (12, 3);

insert into tbl_profiles_menu_items values (5, 4);
insert into tbl_profiles_menu_items values (8, 4);
insert into tbl_profiles_menu_items values (10, 4);
insert into tbl_profiles_menu_items values (11, 4);

insert into tbl_profiles_menu_items values (1, 5);
insert into tbl_profiles_menu_items values (2, 5);
insert into tbl_profiles_menu_items values (3, 5);
insert into tbl_profiles_menu_items values (4, 5);
insert into tbl_profiles_menu_items values (5, 5);
insert into tbl_profiles_menu_items values (6, 5);
insert into tbl_profiles_menu_items values (7, 5);
insert into tbl_profiles_menu_items values (8, 5);
insert into tbl_profiles_menu_items values (10, 5);
insert into tbl_profiles_menu_items values (11, 5);
insert into tbl_profiles_menu_items values (12, 5);
insert into tbl_profiles_menu_items values (13, 5);

insert into tbl_shifts values (1, 'Turno Mañana',50400, 28800, 1);
insert into tbl_shifts values (2, 'Turno Tarde', 64800, 50400, 1);

insert into tbl_shift_values values(1, 20, 1);
insert into tbl_shift_values values(2, 15, 1);
insert into tbl_shift_values values(3, 15, 1);
insert into tbl_shift_values values(4, 15, 1);
insert into tbl_shift_values values(5, 15, 1);
insert into tbl_shift_values values(6, 15, 1);
insert into tbl_shift_values values(7, 20, 1);
insert into tbl_shift_values values(1, 20, 2);
insert into tbl_shift_values values(2, 15, 2);
insert into tbl_shift_values values(3, 15, 2);
insert into tbl_shift_values values(4, 15, 2);
insert into tbl_shift_values values(5, 15, 2);
insert into tbl_shift_values values(6, 15, 2);
insert into tbl_shift_values values(7, 20, 2);

insert into tbl_users values (null, 'root', '81dc9bdb52d04dc20036dbd8313ed055', 'Mariano', 'Capurro', 'marianocapurro@fibertel.com.ar', 'Dr. Eduardo Madero 1324', '4797-6996', '15-4494-4484', 1, '25.967.547', 1, 'N5-25967547', '2009/01/28', '38043', 1, 1, 'AGITBA', 0.0, 0);



