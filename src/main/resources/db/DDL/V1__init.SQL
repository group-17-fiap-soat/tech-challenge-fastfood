CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";


-- Sequence para o campo number da tabela Order
CREATE SEQUENCE sq_order_number START WITH 1 INCREMENT BY 1;


-- Tabela Customer
CREATE TABLE tb_customer
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    cpf        VARCHAR(11) UNIQUE NOT NULL,
    name       TEXT               NOT NULL,
    email      TEXT UNIQUE        NOT NULL,
    created_at TIMESTAMPTZ      DEFAULT now(),
    updated_at TIMESTAMPTZ      DEFAULT now()
);

-- Tabela Product
CREATE TABLE tb_product
(
    id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        TEXT   NOT NULL,
    description TEXT,
    price       NUMERIC NOT NULL,
    category    TEXT    NOT NULL,
    image_url   TEXT,
    created_at  TIMESTAMPTZ      DEFAULT now(),
    updated_at  TIMESTAMPTZ      DEFAULT now()
);

-- Tabela Order
CREATE TABLE tb_order
(
    id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    number        BIGINT NOT NULL,
    customer_id   UUID REFERENCES tb_customer (id),
    status        TEXT   NOT NULL,
    order_date    TIMESTAMPTZ      DEFAULT now(),
    finished_date TIMESTAMPTZ,
    created_at    TIMESTAMPTZ      DEFAULT now(),
    updated_at    TIMESTAMPTZ      DEFAULT now()
);

-- Tabela OrderItem
CREATE TABLE tb_order_item
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    order_id   UUID REFERENCES tb_order (id) ON DELETE CASCADE,
    product_id UUID REFERENCES tb_product (id),
    quantity   INT NOT NULL,
    created_at TIMESTAMPTZ      DEFAULT now(),
    updated_at TIMESTAMPTZ      DEFAULT now()
);

-- Tabela Payment
CREATE TABLE tb_payment
(
    id             UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    order_id       UUID REFERENCES tb_order (id),
    total_amount   NUMERIC NOT NULL,
    payment_method TEXT    NOT NULL,
    payment_date   TIMESTAMPTZ      DEFAULT now(),
    created_at     TIMESTAMPTZ      DEFAULT now(),
    updated_at     TIMESTAMPTZ      DEFAULT now()
);





CREATE OR REPLACE FUNCTION set_order_number()
RETURNS TRIGGER AS $$
BEGIN
    NEW.number
:= nextval('sq_order_number');
RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- Add the trigger to the table
CREATE TRIGGER order_number_trigger
    BEFORE INSERT
    ON tb_order
    FOR EACH ROW
    EXECUTE FUNCTION set_order_number();