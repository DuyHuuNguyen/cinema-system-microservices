CREATE
DATABASE db_notification_service_cinema_system_microservices_v1;
CREATE TABLE "messages"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "subject"    varchar(255)          NOT NULL,
    "content"    varchar               NOT NULL,
    "status"     varchar(255)          NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "version"    bigint                NOT NULL DEFAULT 0,
    "created_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "message_assets"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "media_key"  varchar(255)          NOT NULL,
    "media_type" varchar(100)          NOT NULL,
    "message_id" bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "version"    bigint                NOT NULL DEFAULT 0,
    "created_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "receivers"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "receiver_id" bigint                NOT NULL,
    "message_id"  bigint                NOT NULL,
    "is_active"   boolean               NOT NULL DEFAULT true,
    "version"     bigint                NOT NULL DEFAULT 0,
    "created_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

ALTER TABLE "message_assets"
    ADD FOREIGN KEY ("message_id") REFERENCES "messages" ("id");

ALTER TABLE "receivers"
    ADD FOREIGN KEY ("message_id") REFERENCES "messages" ("id");
