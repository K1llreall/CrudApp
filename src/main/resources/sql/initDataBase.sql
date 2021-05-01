CREATE TABLE IF NOT EXISTS feedback
(   id SERIAL PRIMARY KEY,
    description TEXT,
    feedback_date DATE
);
CREATE TABLE IF NOT EXISTS team
(   id SERIAL PRIMARY KEY,
    name VARCHAR (128)
);
CREATE TABLE IF NOT EXISTS project
(   id SERIAL PRIMARY KEY,
    project_name VARCHAR (128),
    customer VARCHAR (128),
    duration VARCHAR (128),
    methodology VARCHAR (128),
    project_manager VARCHAR (128),
    team_id INTEGER REFERENCES team (id)
);

create TABLE if not exists employee
(id SERIAL primary key,
    name VARCHAR (128),
    surname VARCHAR (128),
    patronymic VARCHAR (128),
    email VARCHAR (128),
    phone_number VARCHAR (14),
    birthday DATE,
    experience REAL ,
    hiringday DATE,
    developer_level VARCHAR (2),
    english_level VARCHAR (30),
    skype VARCHAR (128),
    project_id INTEGER REFERENCES project (id),
    feedback_id INTEGER REFERENCES feedback(id),
    team_id INTEGER REFERENCES team (id)
    );
