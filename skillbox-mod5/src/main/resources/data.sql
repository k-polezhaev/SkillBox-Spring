INSERT INTO books_schema.categories (name)
VALUES ('cat1'),
       ('cat2'),
       ('cat3');

INSERT INTO books_schema.books (name, author, category_id)
VALUES ('book1', 'author1', 1),
       ('book2', 'author1', 2),
       ('book3', 'author1', 3),
       ('book4', 'author2', 3),
       ('book5', 'author2', 1),
       ('book6', 'author3', 2),
       ('book7', 'author3', 3);
