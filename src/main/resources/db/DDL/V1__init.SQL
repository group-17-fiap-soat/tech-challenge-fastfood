CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

-- Tabela Customer
CREATE TABLE Customer
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    cpf        VARCHAR(11) UNIQUE NOT NULL,
    name       TEXT               NOT NULL,
    email      TEXT UNIQUE        NOT NULL,
    created_at TIMESTAMPTZ      DEFAULT now(),
    updated_at TIMESTAMPTZ      DEFAULT now()
);

-- Tabela Product
CREATE TABLE Product
(
    id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        TEXT    NOT NULL,
    description TEXT,
    price       NUMERIC NOT NULL,
    category    TEXT    NOT NULL,
    image_url   TEXT,
    created_at  TIMESTAMPTZ      DEFAULT now(),
    updated_at  TIMESTAMPTZ      DEFAULT now()
);

-- Sequence para o campo number da tabela Order
CREATE SEQUENCE sq_order_number;

-- Tabela Order
CREATE TABLE "order"
(
    id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    number        INT4 NOT NULL    DEFAULT nextval('sq_order_number'),
    customer_id   UUID REFERENCES Customer (id),
    status        TEXT NOT NULL,
    order_date    TIMESTAMPTZ      DEFAULT now(),
    finished_date TIMESTAMPTZ,
    created_at    TIMESTAMPTZ      DEFAULT now(),
    updated_at    TIMESTAMPTZ      DEFAULT now()
);

-- Tabela OrderItem
CREATE TABLE Order_Item
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    order_id   UUID REFERENCES "order" (id) ON DELETE CASCADE,
    product_id UUID REFERENCES Product (id),
    quantity   INT NOT NULL,
    created_at TIMESTAMPTZ      DEFAULT now(),
    updated_at TIMESTAMPTZ      DEFAULT now()
);

-- Tabela Payment
CREATE TABLE Payment
(
    id             UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    order_id       UUID REFERENCES "order" (id),
    total_amount   NUMERIC NOT NULL,
    payment_method TEXT    NOT NULL,
    payment_date   TIMESTAMPTZ      DEFAULT now(),
    created_at     TIMESTAMPTZ      DEFAULT now(),
    updated_at     TIMESTAMPTZ      DEFAULT now()
);

-- Tabela Example TODO apagar, apenas para fins de demonstração
CREATE TABLE Example
(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4()
);