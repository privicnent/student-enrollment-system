DROP TABLE IF EXISTS STUDENT;
DROP TABLE IF EXISTS COURSE;

CREATE TABLE COURSE (
  COURSE_ID INTEGER  NOT NULL,
  COURSE_NAME  VARCHAR(50) NOT NULL
);
ALTER TABLE COURSE ADD PRIMARY KEY (COURSE_ID);

CREATE TABLE STUDENT (
    EMAIL_ID VARCHAR(250) NOT NULL,
    FIRST_NAME VARCHAR(250) NOT NULL,
    LAST_NAME VARCHAR(250) NOT NULL,
    DOB DATE NOT NULL,
    LOCATION VARCHAR(250) NOT NULL,
    COURSE_ID INTEGER NOT NULL
);

ALTER TABLE STUDENT ADD PRIMARY KEY (EMAIL_ID);
ALTER TABLE STUDENT ADD FOREIGN KEY (COURSE_ID)  REFERENCES COURSE(COURSE_ID);