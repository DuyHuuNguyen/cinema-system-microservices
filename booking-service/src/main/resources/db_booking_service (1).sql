create
database db_booking_service_cinema_system_microservices_v1;

CREATE TABLE "bookings"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "user_id"    bigint                NOT NULL,
    "payment_id" bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "version"    bigint                NOT NULL DEFAULT 0,
    "created_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "booking_tickets"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "booking_id" bigint                NOT NULL,
    "ticket_id"  bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "version"    bigint                NOT NULL DEFAULT 0,
    "created_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "tickets"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "price"       float                 NOT NULL,
    "seat_number" integer               NOT NULL,
    "seat_code"   varchar(100) UNIQUE   NOT NULL,
    "schedule_id" bigint                NOT NULL,
    "is_active"   boolean               NOT NULL DEFAULT true,
    "version"     bigint                NOT NULL DEFAULT 0,
    "created_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "booking_finger_foods"
(
    "id"             BIGSERIAL PRIMARY KEY NOT NULL,
    "booking_id"     bigint                NOT NULL,
    "finger_food_id" bigint                NOT NULL,
    "quantity"       int                   NOT NULL,
    "is_active"      boolean               NOT NULL DEFAULT true,
    "version"        bigint                NOT NULL DEFAULT 0,
    "created_at"     bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"     bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "booking_vouchers"
(
    "id"         BIGSERIAL PRIMARY KEY NOT NULL,
    "booking_id" bigint                NOT NULL,
    "voucher_id" bigint                NOT NULL,
    "is_active"  boolean               NOT NULL DEFAULT true,
    "version"    bigint                NOT NULL DEFAULT 0,
    "created_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at" bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

CREATE TABLE "vouchers"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "type"        varchar(255)          NOT NULL,
    "percent"     float                 NOT NULL,
    "max_price"   float                 NOT NULL,
    "expired_at"  bigint                NOT NULL,
    "code"        varchar(100) UNIQUE   NOT NULL,
    "description" varchar(255),
    "quality"     int                   NOT NULL,

    "theater_id"  bigint                NOT NULL,
    "is_active"   boolean               NOT NULL DEFAULT true,
    "version"     bigint                NOT NULL DEFAULT 0,
    "created_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"  bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);

ALTER TABLE "booking_tickets"
    ADD FOREIGN KEY ("booking_id") REFERENCES "bookings" ("id");

ALTER TABLE "booking_tickets"
    ADD FOREIGN KEY ("ticket_id") REFERENCES "tickets" ("id");

ALTER TABLE "booking_finger_foods"
    ADD FOREIGN KEY ("booking_id") REFERENCES "bookings" ("id");

ALTER TABLE "booking_vouchers"
    ADD FOREIGN KEY ("booking_id") REFERENCES "bookings" ("id");

ALTER TABLE "booking_vouchers"
    ADD FOREIGN KEY ("voucher_id") REFERENCES "vouchers" ("id");
