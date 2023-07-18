create table person (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    full_name varchar(100) UNIQUE NOT NULL,
    year_birth int NOT NULL
);

create table book (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(100) NOT NULL,
    author varchar(100) NOT NULL,
    year int NOT NULL,
    person_id int REFERENCES person(id) on delete set null
)