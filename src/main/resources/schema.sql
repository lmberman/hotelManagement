CREATE TABLE HOTEL_DETAILS (
    ID INTEGER NOT NULL,
    DAYS_OF_OPERATION VARCHAR(7),
    HOURS_OF_OPERATION VARCHAR(10),
    SINGLE_ROOM_PRICE DECIMAL(4,2),
    DOUBLE_ROOM_PRICE DECIMAL(4,2),
    SUITE_ROOM_PRICE DECIMAL(4,2),
    PRIMARY KEY (ID)
);
CREATE TABLE PERSON (
    ID INTEGER(8),
    FIRSTNAME VARCHAR(50),
    MIDDLE CHAR(1),
    LASTNAME VARCHAR(50),
    TELEPHONE VARCHAR(14),
    ADDRESS VARCHAR(50),
    CITY VARCHAR(50),
    STATE VARCHAR(50),
    ZIPCODE VARCHAR(50),
    PRIMARY KEY (ID)
);
CREATE TABLE EMPLOYEE (
    EMPLOYEE_ID INTEGER(8) NOT NULL,
    PASSWORD VARCHAR(60) NOT NULL,
    ROLE VARCHAR(12) NOT NULL,
    LAST_MODIFIED_DATE DATE,
    CREATED_DATE DATE,
    PRIMARY KEY (EMPLOYEE_ID),
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES PERSON(ID)
);

CREATE TABLE ROOM (
    ROOM_NUM INTEGER NOT NULL,
    TYPE VARCHAR(6) NOT NULL,
    STATUS VARCHAR(20) NOT NULL DEFAULT ('AVAILABLE'),
    PRIMARY KEY (ROOM_NUM)

);

CREATE TABLE HOUSE_KEEP_TASK (
    TASK_ID INTEGER NOT NULL,
    EMPLOYEE_ID INTEGER,
    ROOM_NUM INTEGER NOT NULL,
    TYPE VARCHAR(15) NOT NULL,
    STATUS VARCHAR(15) NOT NULL DEFAULT ('PENDING'),
    DEADLINE_DATE DATE NOT NULL,
    COMPLETION_DATE DATE,
    CREATED_DATE DATE NOT NULL,
    PRIMARY KEY (TASK_ID),
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE(EMPLOYEE_ID),
    FOREIGN KEY (ROOM_NUM) REFERENCES ROOM(ROOM_NUM)
);

CREATE TABLE CUSTOMER(
    CUST_ID INTEGER NOT NULL,
    LICENSE_PLATE_NUM VARCHAR(15),
    EMAIL_ADDRESS VARCHAR(50) NOT NULL,
    CHECKED_IN_BY INTEGER(8),
    CHECKED_OUT_BY INTEGER(8),
    PRIMARY KEY (CUST_ID),
    FOREIGN KEY (CHECKED_IN_BY) REFERENCES EMPLOYEE(EMPLOYEE_ID),
    FOREIGN KEY (CHECKED_OUT_BY) REFERENCES EMPLOYEE(EMPLOYEE_ID),
    FOREIGN KEY (CUST_ID) REFERENCES PERSON(ID)
);
CREATE TABLE RESERVATION(
    CONFIRM_NUM VARCHAR(10),
    CUSTOMER_ID INTEGER,
    ROOM_NUM INTEGER,
    START_DATE DATE,
    END_DATE DATE,
    PRICE_LOCKED CHAR DEFAULT ('Y'),
    DO_NOT_DISTURB CHAR DEFAULT ('N'),
    PRICE DECIMAL(4,2),
    STATUS VARCHAR(12),
    PRIMARY KEY (CONFIRM_NUM),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(CUST_ID),
    FOREIGN KEY (ROOM_NUM) REFERENCES ROOM(ROOM_NUM)
);


