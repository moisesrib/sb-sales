CREATE TABLE public.products (
     id SERIAL PRIMARY KEY, -- Torna o ID autoincrementável
     barcode VARCHAR(13) NULL, -- Para códigos de barras com limite de 13 caracteres
     name VARCHAR(255) NOT NULL, -- Nome do produto, obrigatório
     price NUMERIC(10, 2) NOT NULL, -- Preço com 2 casas decimais
     quantity INTEGER NOT NULL, -- Quantidade em estoque
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp automático na criação
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp automático na atualização
     deleted_at TIMESTAMP NULL -- Para soft delete
);
