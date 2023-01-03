DROP TABLE books;
DROP TABLE authors;
CREATE TABLE IF NOT EXISTS authors (
    id INTEGER NOT NULL ,
    full_name TEXT NOT NULL,
    PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE IF NOT EXISTS books (
    id INTEGER NOT NULL ,
    name TEXT NOT NULL,
    isbn TEXT UNIQUE NOT NULL,
    author_id INTEGER NOT NULL,
    release_date TEXT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES books(id),
    PRIMARY KEY("id" AUTOINCREMENT)
);

