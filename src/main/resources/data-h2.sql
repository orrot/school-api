INSERT INTO subject(id, name) VALUES('A1', 'Math');
INSERT INTO subject(id, name) VALUES('A2', 'Biology');
INSERT INTO subject(id, name) VALUES('B1', 'Arts');

INSERT INTO student(id, name, grade) VALUES('id-111', 'Orlando', 'ONE');
INSERT INTO student(id, name, grade) VALUES('id-222', 'Roy', 'FIVE');

INSERT INTO enrolled_subject(student_id, subject_id, note) VALUES('id-111', 'A1', 2.3);
INSERT INTO enrolled_subject(student_id, subject_id, note) VALUES('id-222', 'A1', 4.5);
INSERT INTO enrolled_subject(student_id, subject_id, note) VALUES('id-222', 'A2', 4.7);
