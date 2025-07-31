create
database db_moviedb_service_cinema_system_microservices_v1;
CREATE TABLE "categories"
(
    "id"            BIGSERIAL PRIMARY KEY NOT NULL,
    "category_name" varchar(50) UNIQUE    NOT NULL,
    "is_active"     boolean               NOT NULL DEFAULT true,
    "version"       bigint                NOT NULL DEFAULT 0,
    "created_at"    bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"    bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "movies"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "title"       varchar               NOT NULL,
    "description" varchar               NOT NULL,
    "duration"    bigint                NOT NULL,
    "language"    varchar(50)           NOT NULL,
    "released_at" bigint                NOT NULL,
    "poster_url"  varchar(255)          NOT NULL,
    "trailer_url" varchar(255)          NOT NULL,
    "movie_url"   varchar(255)          NOT NULL,
    "theater_id"  bigint                NOT NULL,
    "category_id" bigint                NOT NULL,
    "is_active"   boolean               NOT NULL DEFAULT true,
    "version"     bigint                NOT NULL DEFAULT 0,
    "created_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "movie_rates"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "owner_id"    int                   NOT NULL,
    "star_number" int                   NOT NULL,
    "comment"     varchar,
    "movie_id"    bigint                NOT NULL,
    "is_active"   boolean               NOT NULL DEFAULT true,
    "version"     bigint                NOT NULL DEFAULT 0,
    "created_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "movie_rate_assets"
(
    "id"            BIGSERIAL PRIMARY KEY NOT NULL,
    "media_type"    varchar(50)           NOT NULL,
    "media_key"     varchar(255)          NOT NULL,
    "movie_rate_id" bigint                NOT NULL,
    "is_active"     boolean               NOT NULL DEFAULT true,
    "version"       bigint                NOT NULL DEFAULT 0,
    "created_at"    bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"    bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

ALTER TABLE "movies"
    ADD FOREIGN KEY ("category_id") REFERENCES "categories" ("id");

ALTER TABLE "movie_rates"
    ADD FOREIGN KEY ("movie_id") REFERENCES "movies" ("id");

ALTER TABLE "movice_rate_assets"
    ADD FOREIGN KEY ("movie_rate_id") REFERENCES "movie_rates" ("id");
