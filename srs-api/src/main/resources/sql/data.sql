insert into students values ('B00000001', 'Anne', 'Broder', 'master', 3.7, 'broder@bu.edu', '17-JAN-94')^
insert into students values ('B00000002', 'Terry', 'Buttler', 'master', 3.0, 'buttler@bu.edu', '28-MAY-93')^
insert into students values ('B00000003', 'Tracy', 'Wang', 'master', 4.0, 'wang@bu.edu','06-AUG-97')^
insert into students values ('B00000004', 'Barbara', 'Callan', 'master', 2.5, 'callan@bu.edu', '18-OCT-95')^
insert into students values ('B00000005', 'Jack', 'Smith', 'master', 3.2, 'smith@bu.edu', '18-OCT-95')^
insert into students values ('B00000006', 'Terry', 'Zillman', 'PhD', 4.0, 'zillman@bu.edu', '15-JUN-92')^
insert into students values ('B00000007', 'Becky', 'Lee', 'master', 4.0, 'lee@bu.edu', '12-NOV-96')^
insert into students values ('B00000008', 'Tom', 'Baker', 'master', null, 'baker@bu.edu', '23-DEC-97')^
insert into students values ('B00000009', 'Ben', 'Liu', 'master', 3.8, 'liu@bu.edu', '18-MAR-96')^
insert into students values ('B00000010', 'Sata', 'Patel', 'master', 3.9, 'patel@bu.edu', '12-OCT-94')^
insert into students values ('B00000011', 'Art', 'Chang', 'PhD', 4.0, 'chang@bu.edu', '08-JUN-93')^
insert into students values ('B00000012', 'Tara', 'Ramesh', 'senior', 3.98, 'ramesh@bu.edu', '29-JUL-98')^

insert into courses values ('CS', 432, 'database systems')^
insert into courses values ('Math', 314, 'discrete math')^
insert into courses values ('CS', 240, 'data structure')^
insert into courses values ('CS', 575, 'algorithms')^
insert into courses values ('CS', 532, 'database systems')^
insert into courses values ('CS', 550, 'operating systems')^
insert into courses values ('CS', 536, 'machine learning')^

insert into prerequisites values ('CS', '432', 'Math', '314')^
insert into prerequisites values ('CS', '432', 'CS', '240')^
insert into prerequisites values ('CS', '532', 'CS', '432')^
insert into prerequisites values ('CS' , '536', 'CS', '532')^
insert into prerequisites values ('CS', '575', 'CS', '240')^

-- insert into course_credit values (432, 4)^
-- insert into course_credit values (314, 4)^
-- insert into course_credit values (240, 4)^
-- insert into course_credit values (536, 3)^
-- insert into course_credit values (532, 3)^
-- insert into course_credit values (550, 3)^
-- insert into course_credit values (575, 3)^

insert into classes values ('c0001', 'CS', 432, 1, 2021, 'Spring', 13, 12, 'LH 005')^
insert into classes values ('c0002', 'Math', 314, 1, 2020, 'Fall', 13, 12, 'LH 009')^
insert into classes values ('c0003', 'Math', 314, 2, 2020, 'Fall', 14, 11, 'LH 009')^
insert into classes values ('c0004', 'CS', 432, 1, 2020, 'Spring', 13, 13, 'SW 222')^
insert into classes values ('c0005', 'CS', 536, 1, 2021, 'Spring', 14, 13, 'LH 003')^
insert into classes values ('c0006', 'CS', 532, 1, 2021, 'Spring', 10, 9, 'LH 005')^
insert into classes values ('c0007', 'CS', 550, 1, 2021, 'Spring', 11, 11, 'WH 155')^

insert into score_grade values (92, 'A')^
insert into score_grade values (79.5, 'B')^
insert into score_grade values (84, 'B+')^
insert into score_grade values (72.8, 'B-')^
insert into score_grade values (76, 'B')^
insert into score_grade values (65.35, 'C')^
insert into score_grade values (94, 'A')^
insert into score_grade values (82, 'B+')^
insert into score_grade values (88, 'A-')^
insert into score_grade values (68, 'C+')^

insert into g_enrollments values ('B00000001', 'c0001', 92)^
insert into g_enrollments values ('B00000002', 'c0002', 76)^
insert into g_enrollments values ('B00000003', 'c0004', 94)^
insert into g_enrollments values ('B00000004', 'c0004', 65.35)^
insert into g_enrollments values ('B00000004', 'c0005', 82)^
insert into g_enrollments values ('B00000005', 'c0006', 79.5)^
insert into g_enrollments values ('B00000006', 'c0006', 92)^
insert into g_enrollments values ('B00000001', 'c0002', 68)^
insert into g_enrollments values ('B00000003', 'c0005', null)^
insert into g_enrollments values ('B00000007', 'c0007', 92)^
insert into g_enrollments values ('B00000001', 'c0003', 76)^
insert into g_enrollments values ('B00000001', 'c0006', 72.8)^
insert into g_enrollments values ('B00000001', 'c0004', 94)^
insert into g_enrollments values ('B00000001', 'c0005', 76)^
insert into g_enrollments values ('B00000003', 'c0001', 84)^
insert into g_enrollments values ('B00000005', 'c0001', 76)^
