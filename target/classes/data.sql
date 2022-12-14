INSERT INTO `roles` (`id`,`name`) VALUES (75,'administrator');
INSERT INTO `roles` (`id`,`name`) VALUES (76,'teacher');

INSERT INTO `teachers` (`id`,`name`) VALUES (4,'Julian Borja');
INSERT INTO `teachers` (`id`,`name`) VALUES (6,'Gonzalo G. Verón');
INSERT INTO `teachers` (`id`,`name`) VALUES (8,'Test');
INSERT INTO `teachers` (`id`, `name`) VALUES (0, 'Administrador');

INSERT INTO `lessons` (`id`,`content`,`date`,`homework`,`lesson_number`,`title`) VALUES (17,'Estudiamos Katakanas.','2022-11-04','Le deje tres ejercicios para practicar sobre Katakanas.',0,'titulo4');
INSERT INTO `lessons` (`id`,`content`,`date`,`homework`,`lesson_number`,`title`) VALUES (19,'test','2022-11-04','test',0,'titulo3');
INSERT INTO `lessons` (`id`,`content`,`date`,`homework`,`lesson_number`,`title`) VALUES (31,'Hablamos sobre sus intereses y aspiraciones con respecto al estudio de japonés y empezamos a ver sobre los Kanji.','2022-11-04','Dejé tres Kanji para que traduzca.',0,'titulo2');
INSERT INTO `lessons` (`id`,`content`,`date`,`homework`,`lesson_number`,`title`) VALUES (36,'Estuvimos estudiando los Kanjis.','2022-11-04','Deje para que traduzca varios Kanjis',0,'titulo1');

INSERT INTO `links` (`id`,`title`,`url`) VALUES (18,'Google','www.google.com.ar');
INSERT INTO `links` (`id`,`title`,`url`) VALUES (20,'Prime','https://www.primefaces.org/primeflex/formlayout');
INSERT INTO `links` (`id`,`title`,`url`) VALUES (32,'Generador','https://generadordenombres.online/argentina/');
INSERT INTO `links` (`id`,`title`,`url`) VALUES (33,'PrimeNG','https://www.primefaces.org/primeng/icons');
INSERT INTO `links` (`id`,`title`,`url`) VALUES (37,'Trello','https://trello.com/b/vFzGNce5/murasaki');

INSERT INTO `interests` (`id`,`icon`,`name`) VALUES (11,'pi pi-users','Cultura');
INSERT INTO `interests` (`id`,`icon`,`name`) VALUES (13,'pi pi-building','Estudios');
INSERT INTO `interests` (`id`,`icon`,`name`) VALUES (14,'pi pi-briefcase','Trabajo');
INSERT INTO `interests` (`id`,`icon`,`name`) VALUES (15,'pi pi-box','Hobby');
INSERT INTO `interests` (`id`,`icon`,`name`) VALUES (22,'pi pi-book','Historia');
INSERT INTO `interests` (`id`,`icon`,`name`) VALUES (23,'pi pi-book','Test');

INSERT INTO `users` (`id`,`email`,`password`,`teacher_id`,`role_id`,`is_first_time`) VALUES (9,'borja@gmail.com','$2a$10$F1k1JmPFBTyJhn8ibaseFuthOKNTr0iomCKkDNJEtEflI7o46RlTu',4,76,0);
INSERT INTO `users` (`id`,`email`,`password`,`teacher_id`,`role_id`,`is_first_time`) VALUES (7,'gonveron96@gmail.com','$2a$10$6JUjLc2S45LOCypJccxmb.M2UtEoJpPLnh.SxcmMHUar5lCi8Ueq2',6,76,0);
INSERT INTO `users` (`id`,`email`,`password`,`teacher_id`,`role_id`,`is_first_time`) VALUES (77,'admin@murasaki.com','$2a$10$qNOXK67nz7.vYQD83ovD1uUQlS9zsvZyfbCj.wZhDj9QXetlGiOI.',0,75,0);

INSERT INTO `students` (`id`,`age`,`email`,`email_tutor`,`jlpt_level`,`name`,`prior_knowledge`,`tel`,`teacher_id`) VALUES (5,18,'angeles@gmail.com','tutor1@gmail.com',4,'Angeles Barroso','Estudio japonés con anterioridad.',1162641228,4);
INSERT INTO `students` (`id`,`age`,`email`,`email_tutor`,`jlpt_level`,`name`,`prior_knowledge`,`tel`,`teacher_id`) VALUES (8,25,'vic@gmail.com','tutor2@gmail.com',5,'Victor Seoane','No tiene.',1162641228,6);
INSERT INTO `students` (`id`,`age`,`email`,`email_tutor`,`jlpt_level`,`name`,`prior_knowledge`,`tel`,`teacher_id`) VALUES (16,21,'nahuel@gmail.com','tutor3@gmail.com',5,'Nahuel Fazio','No sabe nada.',1162641228,6);
INSERT INTO `students` (`id`,`age`,`email`,`email_tutor`,`jlpt_level`,`name`,`prior_knowledge`,`tel`,`teacher_id`) VALUES (24,21,'guille@gmail.com','tutor4@gmail.com',3,'Guillermo Gaspar','Viene de otro instituto de japonés.',1162641228,4);
INSERT INTO `students` (`id`,`age`,`email`,`email_tutor`,`jlpt_level`,`name`,`prior_knowledge`,`tel`,`teacher_id`) VALUES (25,33,'valen@gmail.com','tutor5@gmail.com',5,'Valeria Olmos','No tiene.',1162641228,4);
INSERT INTO `students` (`id`,`age`,`email`,`email_tutor`,`jlpt_level`,`name`,`prior_knowledge`,`tel`,`teacher_id`) VALUES (26,22,'emilio@gmail.com','tutor6@gmail.com',1,'Emilio Sanmartin','No tiene.',1162641228,6);
INSERT INTO `students` (`id`,`age`,`email`,`email_tutor`,`jlpt_level`,`name`,`prior_knowledge`,`tel`,`teacher_id`) VALUES (27,15,'silvia@gmail.com','tutor7@gmail.com',5,'Sílvia Belda','No tiene.',1162641228,6);
INSERT INTO `students` (`id`,`age`,`email`,`email_tutor`,`jlpt_level`,`name`,`prior_knowledge`,`tel`,`teacher_id`) VALUES (28,16,'elias@gmail.com','tutor8@gmail.com',5,'Elias Hurtado','No tiene.',1162641228,6);
INSERT INTO `students` (`id`,`age`,`email`,`email_tutor`,`jlpt_level`,`name`,`prior_knowledge`,`tel`,`teacher_id`) VALUES (29,27,'julio@gmail.com','tutor9@gmail.com',3,'Julio Palomares','Aprendió lo básico desde internet.',1162641228,6);
INSERT INTO `students` (`id`,`age`,`email`,`email_tutor`,`jlpt_level`,`name`,`prior_knowledge`,`tel`,`teacher_id`) VALUES (30,51,'norma@gmail.com','tutor10@gmail.com',4,'Norma Melendez','No tiene.',1162641228,6);
INSERT INTO `students` (`id`,`age`,`email`,`email_tutor`,`jlpt_level`,`name`,`prior_knowledge`,`tel`,`teacher_id`) VALUES (38,15,'asd@asd.com','tutor11@gmail.com',5,'asdasd','algo',1162641228,6);

INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (5,11);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (5,22);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (8,11);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (8,13);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (16,11);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (16,13);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (16,14);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (16,15);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (24,13);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (24,14);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (25,15);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (26,11);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (26,13);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (26,14);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (26,15);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (26,22);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (27,11);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (27,15);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (28,15);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (28,22);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (29,14);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (30,15);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (38,11);
INSERT INTO `student_interest` (`student_id`,`interest_id`) VALUES (38,14);

INSERT INTO `lessons_links` (`lesson_id`,`links_id`) VALUES (17,18);
INSERT INTO `lessons_links` (`lesson_id`,`links_id`) VALUES (19,20);
INSERT INTO `lessons_links` (`lesson_id`,`links_id`) VALUES (31,32);
INSERT INTO `lessons_links` (`lesson_id`,`links_id`) VALUES (31,33);
INSERT INTO `lessons_links` (`lesson_id`,`links_id`) VALUES (36,37);

INSERT INTO `students_lessons` (`student_id`,`lessons_id`) VALUES (8,19);
INSERT INTO `students_lessons` (`student_id`,`lessons_id`) VALUES (8,17);
INSERT INTO `students_lessons` (`student_id`,`lessons_id`) VALUES (8,31);
INSERT INTO `students_lessons` (`student_id`,`lessons_id`) VALUES (28,36);



