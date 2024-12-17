INSERT INTO news_schema.users (birthday, first_name, last_name) VALUES
('2000-01-01', 'Ivan', 'Ivanov'),
('2001-02-02', 'Sergey', 'Sidorov'),
('2002-03-03', 'Petr', 'Petrov'),
('2003-03-03', 'Nikolay', 'Nikolaev'),
('2004-04-04', 'Alexander', 'Alexandrov'),
('2005-05-05', 'Igor', 'Igorev'),
('2006-06-06', 'Victor', 'Victorov'),
('2007-07-07', 'Ilya', 'Ilyushin'),
('2008-08-08', 'Stepan', 'Stepanov'),
('2009-09-09', 'Konstantin', 'Konstantinov');

INSERT INTO news_schema.news_categories (name) VALUES
('Sport'),
('Politic'),
('Economy');

INSERT INTO news_schema.news (category_id, created_at, updated_at, user_id, body, title) VALUES
(1, '2024-07-02 14:07:48.240791+10', '2024-07-02 14:07:48.242813+10', 1, 'Updated text...', 'News from user: Ivan Ivanov'),
(1, '2024-07-02 14:07:48.251319+10', '2024-07-02 14:07:48.252240+10', 3, 'Updated text...', 'News from user: Petr Petrov'),
(3, '2024-07-02 14:07:48.259557+10', '2024-07-02 14:07:48.260487+10', 3, 'Updated text...', 'News from user: Petr Petrov'),
(1, '2024-07-02 14:07:48.267681+10', '2024-07-02 14:07:48.268569+10', 4, 'Updated text...', 'News from user: Nikolay Nikolaev'),
(1, '2024-07-02 14:07:48.275523+10', '2024-07-02 14:07:48.276379+10', 4, 'Updated text...', 'News from user: Nikolay Nikolaev'),
(1, '2024-07-02 14:07:48.283424+10', '2024-07-02 14:07:48.284197+10', 5, 'Updated text...', 'News from user: Alexander Alexandrov'),
(2, '2024-07-02 14:07:48.290837+10', '2024-07-02 14:07:48.291639+10', 5, 'Updated text...', 'News from user: Alexander Alexandrov'),
(3, '2024-07-02 14:07:48.299681+10', '2024-07-02 14:07:48.300670+10', 6, 'Updated text...', 'News from user: Igor Igorev'),
(2, '2024-07-02 14:07:48.308226+10', '2024-07-02 14:07:48.309184+10', 6, 'Updated text...', 'News from user: Igor Igorev'),
(2, '2024-07-02 14:07:48.316430+10', '2024-07-02 14:07:48.317778+10', 6, 'Updated text...', 'News from user: Igor Igorev'),
(2, '2024-07-02 14:07:48.325016+10', '2024-07-02 14:07:48.325892+10', 6, 'Updated text...', 'News from user: Igor Igorev'),
(3, '2024-07-02 14:07:48.331836+10', '2024-07-02 14:07:48.332647+10', 7, 'Updated text...', 'News from user: Victor Victorov'),
(2, '2024-07-02 14:07:48.339252+10', '2024-07-02 14:07:48.340136+10', 7, 'Updated text...', 'News from user: Victor Victorov'),
(3, '2024-07-02 14:07:48.346468+10', '2024-07-02 14:07:48.347559+10', 7, 'Updated text...', 'News from user: Victor Victorov'),
(2, '2024-07-02 14:07:48.354052+10', '2024-07-02 14:07:48.354752+10', 8, 'Updated text...', 'News from user: Ilya Ilyushin'),
(1, '2024-07-02 14:07:48.360728+10', '2024-07-02 14:07:48.361693+10', 8, 'Updated text...', 'News from user: Ilya Ilyushin'),
(2, '2024-07-02 14:07:48.367784+10', '2024-07-02 14:07:48.368620+10', 8, 'Updated text...', 'News from user: Ilya Ilyushin'),
(1, '2024-07-02 14:07:48.374281+10', '2024-07-02 14:07:48.375085+10', 8, 'Updated text...', 'News from user: Ilya Ilyushin'),
(2, '2024-07-02 14:07:48.381786+10', '2024-07-02 14:07:48.382453+10', 9, 'Updated text...', 'News from user: Stepan Stepanov'),
(2, '2024-07-02 14:07:48.398434+10', '2024-07-02 14:07:48.401200+10', 9, 'Updated text...', 'News from user: Stepan Stepanov'),
(2, '2024-07-02 14:07:48.416981+10', '2024-07-02 14:07:48.419194+10', 9, 'Updated text...', 'News from user: Stepan Stepanov'),
(1, '2024-07-02 14:07:48.429321+10', '2024-07-02 14:07:48.430423+10', 10, 'Updated text...', 'News from user: Konstantin Konstantinov'),
(3, '2024-07-02 14:07:48.437929+10', '2024-07-02 14:07:48.438980+10', 10, 'Updated text...', 'News from user: Konstantin Konstantinov'),
(3, '2024-07-02 14:07:48.445772+10', '2024-07-02 14:07:48.446628+10', 10, 'Updated text...', 'News from user: Konstantin Konstantinov'),
(3, '2024-07-02 14:07:48.461988+10', '2024-07-02 14:07:48.464698+10', 10, 'Updated text...', 'News from user: Konstantin Konstantinov');

INSERT INTO news_schema."comments" (created_at, news_id, updated_at, user_id, body) VALUES
('2024-07-02 14:07:48.487348+10', 7, '2024-07-02 14:07:48.487360+10', 4, 'Updated comment from user 4 to news 7'),
('2024-07-02 14:07:48.498246+10', 1, '2024-07-02 14:07:48.498255+10', 6, 'Updated comment from user 6 to news 1'),
('2024-07-02 14:07:48.507277+10', 20, '2024-07-02 14:07:48.507286+10', 9, 'Updated comment from user 9 to news 20');