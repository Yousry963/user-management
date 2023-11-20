-- changeset ahmed_yousry:1691405550379-1
CREATE TABLE users
(
    id                 bigserial NOT NULL,
    created_date       timestamp NULL,
    last_modified_date timestamp NULL,
    email              varchar(255) NULL,
    "first_name"       varchar(255) NULL,
    "last_name"        varchar(255) NULL,
    enabled            bool NULL,
    marketing_consent  bool NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

-- changeset ahmed_yousry:1691405550379-3
ALTER TABLE users
    ADD CONSTRAINT uc_fa186aaf68519427debd77e9b UNIQUE (email);