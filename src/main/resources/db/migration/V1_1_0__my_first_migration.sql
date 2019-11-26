CREATE TABLE users(
    uid bigint NOT NULL,
    optlock bigint,
    email character varying(255)
);


CREATE SEQUENCE users_uid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE users_uid_seq OWNED BY users.uid;

ALTER TABLE ONLY users ALTER COLUMN uid SET DEFAULT nextval('users_uid_seq'::regclass);

ALTER TABLE ONLY users ADD CONSTRAINT users_pkey PRIMARY KEY (uid);
