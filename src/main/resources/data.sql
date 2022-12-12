insert into user(csid,e_name,c_name) values('test','test1','test2');

insert into quiz(index, context)
values (1, 'test question'),
       (2, 'test question2');

insert into quiz_option(question_index, index, context, is_answer)
values (1, 1, 'test option1', false),
       (1, 2, 'test option2', false),
       (1, 3, 'test option3', true),
       (1, 4, 'test option4', false),
       (2, 1, 'test2 option1', false),
       (2, 2, 'test2 option2', false),
       (2, 3, 'test2 option3', true),
       (2, 4, 'test2 option4', false)
;