insert into user(cdsid,e_name,c_name) values('test1','etest1','ctest1'),
                                            ('test2','test2','test2'),
                                            ('test3','test3','test3'),
                                            ('test4','test4','test4'),
                                            ('test5','test5','test5'),
                                            ('test6','test6','test6'),
                                            ('test7','test7','test7'),
                                            ('test8','test8','test8');

insert into quiz(index, context)
values (1, 'test1 question'),
       (2, 'test2 question'),
       (3, 'test3 question'),
       (4, 'test4 question'),
       (5, 'test5 question');

insert into quiz_option(question_index, index, context, is_answer)
values (1, 1, 'test1 option1', false),
       (1, 2, 'test1 option2', false),
       (1, 3, 'test1 option3', true),
       (1, 4, 'test1 option4', false),
       (2, 1, 'test2 option1', false),
       (2, 2, 'test2 option2', false),
       (2, 3, 'test2 option3', true),
       (2, 4, 'test2 option4', false),
       (3, 1, 'test3 option1', false),
       (3, 2, 'test3 option2', false),
       (3, 3, 'test3 option3', true),
       (3, 4, 'test3 option4', false),
       (4, 1, 'test4 option1', false),
       (4, 2, 'test4 option2', false),
       (4, 3, 'test4 option3', true),
       (4, 4, 'test4 option4', false),
       (5, 1, 'test5 option1', false),
       (5, 2, 'test5 option2', false),
       (5, 3, 'test5 option3', true),
       (5, 4, 'test5 option4', false)
;