CREATE
SEQUENCE IF NOT EXISTS diario_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE IF NOT EXISTS null_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE IF NOT EXISTS parceiro_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE IF NOT EXISTS post_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE IF NOT EXISTS usuario_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE IF NOT EXISTS voluntario_seq START
WITH 1 INCREMENT BY 50;

CREATE TABLE diario
(
    id         BIGINT NOT NULL,
    texto      VARCHAR(255),
    data       VARCHAR(255),
    id_usuario BIGINT,
    CONSTRAINT pk_diario PRIMARY KEY (id)
);

CREATE TABLE parceiro
(
    id       BIGINT NOT NULL,
    nome     VARCHAR(255),
    email    VARCHAR(255),
    telefone VARCHAR(255),
    senha    VARCHAR(255),
    cnpj     VARCHAR(255),
    CONSTRAINT pk_parceiro PRIMARY KEY (id)
);

CREATE TABLE post
(
    id            BIGINT NOT NULL,
    texto         VARCHAR(255),
    titulo        VARCHAR(255),
    anexo         VARCHAR(255),
    id_voluntario BIGINT,
    CONSTRAINT pk_post PRIMARY KEY (id)
);

CREATE TABLE propagandas
(
    id          BIGINT NOT NULL,
    nome        VARCHAR(255),
    descricao   VARCHAR(255),
    anexo       VARCHAR(255),
    link        VARCHAR(255),
    id_parceiro BIGINT,
    CONSTRAINT pk_propagandas PRIMARY KEY (id)
);

CREATE TABLE usuario
(
    id       BIGINT NOT NULL,
    nome     VARCHAR(255),
    email    VARCHAR(255),
    telefone VARCHAR(255),
    senha    VARCHAR(255),
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

CREATE TABLE voluntario
(
    id       BIGINT NOT NULL,
    nome     VARCHAR(255),
    email    VARCHAR(255),
    telefone VARCHAR(255),
    senha    VARCHAR(255),
    CONSTRAINT pk_voluntario PRIMARY KEY (id)
);

ALTER TABLE diario
    ADD CONSTRAINT FK_DIARIO_ON_ID_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id);

ALTER TABLE post
    ADD CONSTRAINT FK_POST_ON_ID_VOLUNTARIO FOREIGN KEY (id_voluntario) REFERENCES voluntario (id);

ALTER TABLE propagandas
    ADD CONSTRAINT FK_PROPAGANDAS_ON_ID_PARCEIRO FOREIGN KEY (id_parceiro) REFERENCES parceiro (id);