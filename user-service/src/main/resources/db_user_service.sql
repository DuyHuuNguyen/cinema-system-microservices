create
database db_user_service_cinema_system_microservices_v1;
CREATE TABLE "users"
(
    "id"                BIGSERIAL PRIMARY KEY NOT NULL,
    "first_name"        varchar(255)          NOT NULL,
    "last_name"         varchar(255)          NOT NULL,
    "password"          varchar(255)          NOT NULL,
    "email"             varchar(255) UNIQUE   NOT NULL,
    "avatar_key"        varchar(100),
    "date_of_birth"     bigint                NOT NULL,
    "is_loyal_customer" boolean               NOT NULL DEFAULT false,
    "location_id"       bigint                NOT NULL,
    "is_active"         boolean               NOT NULL DEFAULT true,
    "version"           bigint                NOT NULL DEFAULT 0,
    "created_at"        bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint,
    "updated_at"        bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint
);

CREATE TABLE "roles"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "role_name"  varchar               NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "version"    bigint                NOT NULL DEFAULT 0,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint,
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint
);

CREATE TABLE "user_roles"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "user_id"    bigint                NOT NULL,
    "role_id"    bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "version"    bigint                NOT NULL DEFAULT 0,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint,
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint
);

CREATE TABLE "locations"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "longitude"  bigint                NOT NULL,
    "latitude"   bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "version"    bigint                NOT NULL DEFAULT 0,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint,
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint
);

CREATE TABLE "user_hobbies"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "user_id"    bigint                NOT NULL,
    "hobby_id" bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "version"    bigint                NOT NULL DEFAULT 0,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint,
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint
);

CREATE TABLE "hobbies"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "hobby_name" varchar(100) UNIQUE   NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "version"    bigint                NOT NULL DEFAULT 0,
    "created_at" bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint,
    "updated_at" bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint
);

CREATE TABLE "work_places"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "employee_id" bigint                NOT NULL,
    "theater_id"  bigint                NOT NULL,
    "is_active"   boolean               NOT NULL DEFAULT true,
    "version"     bigint                NOT NULL DEFAULT 0,
    "created_at"  bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint,
    "updated_at"  bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint
);

CREATE TABLE "work_shifts"
(
    "id"                  BIGSERIAL PRIMARY KEY NOT NULL,
    "leave_for_worked_at" bigint                NOT NULL,
    "leave_worked_at"     bigint                NOT NULL,
    "checked_in_at"       bigint                NOT NULL DEFAULT 0,
    "checked_out_at"      bigint                NOT NULL DEFAULT 0,
    "work_place_id"        bigint                NOT NULL,
    "is_active"           boolean               NOT NULL DEFAULT true,
    "version"             bigint                NOT NULL DEFAULT 0,
    "created_at"          bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint,
    "updated_at"          bigint                NOT NULL DEFAULT (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint
);

ALTER TABLE "users"
    ADD FOREIGN KEY ("location_id") REFERENCES "locations" ("id");

ALTER TABLE "user_roles"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "user_roles"
    ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("id");

ALTER TABLE "user_hobbies"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "user_hobbies"
    ADD FOREIGN KEY ("hobbies_id") REFERENCES "hobbies" ("id");

ALTER TABLE "workplaces"
    ADD FOREIGN KEY ("employee_id") REFERENCES "users" ("id");

ALTER TABLE "work_shifts"
    ADD FOREIGN KEY ("workplace_id") REFERENCES "workplaces" ("id");
