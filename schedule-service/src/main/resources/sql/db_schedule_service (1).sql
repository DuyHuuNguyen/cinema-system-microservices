CREATE TABLE "movie_schedules"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "started_at"  bigint                NOT NULL,
    "finished_at" bigint                NOT NULL,
    "movie_id"    bigint                NOT NULL,
    "room_id"     bigint                NOT NULL,
    "theater_id"  bigint                NOT NULL,
    "is_active"   boolean               NOT NULL DEFAULT true,
    "version"     bigint                NOT NULL DEFAULT 0,
    "created_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "theaters"
(
    "id"           BIGSERIAL PRIMARY KEY NOT NULL,
    "theater_name" varchar               NOT NULL,
    "description"  text,
    "director_id"  bigint                NOT NULL,
    "location_id"  bigint                NOT NULL,
    "is_active"    boolean               NOT NULL DEFAULT true,
    "version"      bigint                NOT NULL DEFAULT 0,
    "created_at"   bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"   bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "theater_assets"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "media_key"  varchar               NOT NULL,
    "media_type" varchar               NOT NULL,
    "theater_id" bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "version"    bigint                NOT NULL DEFAULT 0,
    "created_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "locations"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "longitude" bigint                NOT NULL,
    "latitude"    bigint                NOT NULL,
    "version"    bigint                NOT NULL DEFAULT 0,
    "is_active"   boolean               NOT NULL DEFAULT true,
    "created_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "rooms"
(
    "id"             BIGSERIAL PRIMARY KEY NOT NULL,
    "monitor_width"  int                   NOT NULL,
    "monitor_height" int                   NOT NULL,
    "room_name"      varchar               NOT NULL,
    "seat_number"    integer               NOT NULL,
    "theater_id"     bigint                NOT NULL,
    "is_active"      boolean               NOT NULL DEFAULT true,
    "version"        bigint                NOT NULL DEFAULT 0,
    "created_at"     bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"     bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "theater_rates"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "owner_id"    bigint                NOT NULL,
    "star_number" integer               NOT NULL,
    "comment"     varchar(255)          NOT NULL,
    "theater_id"  bigint                NOT NULL,
    "is_active"   boolean               NOT NULL DEFAULT true,
    "version"     bigint                NOT NULL DEFAULT 0,
    "created_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "theater_rate_assets"
(
    "id"              BIGSERIAL PRIMARY KEY NOT NULL,
    "media_key"       varchar(255)          NOT NULL,
    "media_type"      varchar(50)           NOT NULL,
    "theater_rate_id" bigint                NOT NULL,
    "is_active"       boolean               NOT NULL DEFAULT true,
    "version"         bigint                NOT NULL DEFAULT 0,
    "created_at"      bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"      bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "finger_foods"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "food_name"  varchar(255)          NOT NULL,
    "food_type"  varchar(50)           NOT NULL,
    "price"      float                 NOT NULL,
    "theater_id" bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "version"    bigint                NOT NULL DEFAULT 0,
    "created_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "finger_food_assets"
(
    "id"             BIGSERIAL PRIMARY KEY NOT NULL,
    "media_key"      varchar(255)          NOT NULL,
    "media_type"     varchar(50)           NOT NULL,
    "finger_food_id" bigint                NOT NULL,
    "is_active"      boolean               NOT NULL DEFAULT true,
    "version"        bigint                NOT NULL DEFAULT 0,
    "created_at"     bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"     bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

ALTER TABLE "movie_schedules"
    ADD FOREIGN KEY ("room_id") REFERENCES "rooms" ("id");

ALTER TABLE "movie_schedules"
    ADD FOREIGN KEY ("theater_id") REFERENCES "theaters" ("id");

ALTER TABLE "theaters"
    ADD FOREIGN KEY ("location_id") REFERENCES "locations" ("id");

ALTER TABLE "theater_assets"
    ADD FOREIGN KEY ("theater_id") REFERENCES "theaters" ("id");

ALTER TABLE "rooms"
    ADD FOREIGN KEY ("theater_id") REFERENCES "theaters" ("id");

ALTER TABLE "theater_rates"
    ADD FOREIGN KEY ("theater_id") REFERENCES "theaters" ("id");

ALTER TABLE "theater_rate_assets"
    ADD FOREIGN KEY ("theater_rate_id") REFERENCES "theater_rates" ("id");

ALTER TABLE "finger_foods"
    ADD FOREIGN KEY ("theater_id") REFERENCES "theaters" ("id");

ALTER TABLE "finger_food_assets"
    ADD FOREIGN KEY ("finger_food_id") REFERENCES "finger_foods" ("id");
