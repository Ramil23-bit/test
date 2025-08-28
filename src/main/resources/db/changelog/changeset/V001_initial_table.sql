CREATE TABLE role (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    role_name varchar(64)  NOT NULL
);

CREATE TABLE users (
    uuid uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    name varchar(64) NOT NULL,
    phone varchar(32) UNIQUE,
    avatar varchar(4096),
    role_id bigint NOT NULL,

    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role (id)
);
