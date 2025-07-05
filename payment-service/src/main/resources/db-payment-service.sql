create
database db_payment_service_cinema_system_microservices_v1;
CREATE TABLE "payments"
(
    "id"             BIGSERIAL PRIMARY KEY NOT NULL,
    "payment_status" varchar(100)          NOT NULL,
    "payment_type"   varchar(100)          NOT NULL,
    "booking_id"     bigint                NOT NULL,
    "is_active"      boolean               NOT NULL DEFAULT true,
    "version"        bigint                NOT NULL DEFAULT 0,
    "created_at"     bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric,
    "updated_at"     bigint                NOT NULL DEFAULT EXTRACT(epoch FROM now()) * 1000::numeric
);
