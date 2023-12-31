CREATE SEQUENCE IF NOT EXISTS ORDERS_ID_SEQ;

CREATE TABLE  IF NOT EXISTS ORDERS(
ID INTEGER NOT NULL PRIMARY KEY DEFAULT nextval('ORDERS_ID_SEQ') ,
CUST_ID BIGINT NOT NULL,
AMOUNT REAL NOT NULL
);

ALTER SEQUENCE ORDERS_ID_SEQ OWNED BY ORDERS.ID;