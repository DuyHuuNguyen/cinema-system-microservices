

CREATE TABLE "wallets"
(
    "id"          BIGSERIAL PRIMARY KEY NOT NULL,
    "user_id"     BIGINT               NOT NULL,
    "balance"     DECIMAL(18, 2)       NOT NULL DEFAULT 0.00,
    "currency"    VARCHAR(10)          NOT NULL,
    "status"      VARCHAR(10)          NOT NULL DEFAULT 'ACTIVE',

    "is_active"   BOOLEAN              NOT NULL DEFAULT true,
    "version"     BIGINT               NOT NULL DEFAULT 0,
    "created_at"  BIGINT               NOT NULL DEFAULT (EXTRACT(EPOCH FROM now()) * 1000)::BIGINT,
    "updated_at"  BIGINT               NOT NULL DEFAULT (EXTRACT(EPOCH FROM now()) * 1000)::BIGINT,

    CONSTRAINT fk_wallets_user FOREIGN KEY ("user_id") REFERENCES "users" ("id"),
    CONSTRAINT chk_wallets_status CHECK ("status" IN ('ACTIVE', 'LOCKED'))
);
alter table wallets add column wallet_name varchar(100);

CREATE TABLE "transactions"
(
    "id"            BIGSERIAL PRIMARY KEY NOT NULL,
    "wallet_id"     BIGINT               NOT NULL,
    "type"          VARCHAR(20)          NOT NULL,
    "amount"        DECIMAL(18, 2)       NOT NULL,
    "status"        VARCHAR(20)          NOT NULL DEFAULT 'PENDING',
    "reference_id"  VARCHAR(255),

    -- Metadata columns
    "is_active"     BOOLEAN              NOT NULL DEFAULT true,
    "version"       BIGINT               NOT NULL DEFAULT 0,
    "created_at"    BIGINT               NOT NULL DEFAULT (EXTRACT(EPOCH FROM now()) * 1000)::BIGINT,
    "updated_at"    BIGINT               NOT NULL DEFAULT (EXTRACT(EPOCH FROM now()) * 1000)::BIGINT,

    -- FK constraint
    CONSTRAINT fk_transactions_wallet FOREIGN KEY ("wallet_id") REFERENCES "wallets" ("id"),

    -- Enum-like constraints
    CONSTRAINT chk_transaction_type CHECK ("type" IN ('DEPOSIT', 'WITHDRAW', 'TRANSFER')),
    CONSTRAINT chk_transaction_status CHECK ("status" IN ('PENDING', 'SUCCESS', 'FAILED'))
);

alter table transactions add column is_payment_for_booking boolean ;

