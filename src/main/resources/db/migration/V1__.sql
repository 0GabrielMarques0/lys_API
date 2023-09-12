CREATE TABLE diario
(
    id         BIGINT       NOT NULL,
    texto      VARCHAR(255) NULL,
    data       VARCHAR(255) NULL,
    id_usuario BIGINT       NULL,
    CONSTRAINT pk_diario PRIMARY KEY (id)
);

CREATE TABLE parceiro
(
    id       BIGINT       NOT NULL,
    nome     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    telefone VARCHAR(255) NULL,
    senha    VARCHAR(255) NULL,
    cnpj     VARCHAR(255) NULL,
    CONSTRAINT pk_parceiro PRIMARY KEY (id)
);

CREATE TABLE post
(
    id            BIGINT       NOT NULL,
    texto         VARCHAR(255) NULL,
    titulo        VARCHAR(255) NULL,
    anexo         VARCHAR(255) NULL,
    id_voluntario BIGINT       NULL,
    CONSTRAINT pk_post PRIMARY KEY (id)
);

CREATE TABLE propagandas
(
    id          BIGINT       NOT NULL,
    nome        VARCHAR(255) NULL,
    descricao   VARCHAR(255) NULL,
    anexo       VARCHAR(255) NULL,
    link        VARCHAR(255) NULL,
    id_parceiro BIGINT       NULL,
    CONSTRAINT pk_propagandas PRIMARY KEY (id)
);

CREATE TABLE usuario
(
    id       BIGINT       NOT NULL,
    nome     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    telefone VARCHAR(255) NULL,
    senha    VARCHAR(255) NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

CREATE TABLE voluntario
(
    id       BIGINT       NOT NULL,
    nome     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    telefone VARCHAR(255) NULL,
    senha    VARCHAR(255) NULL,
    CONSTRAINT pk_voluntario PRIMARY KEY (id)
);

ALTER TABLE diario
    ADD CONSTRAINT FK_DIARIO_ON_ID_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id);

ALTER TABLE post
    ADD CONSTRAINT FK_POST_ON_ID_VOLUNTARIO FOREIGN KEY (id_voluntario) REFERENCES voluntario (id);

ALTER TABLE propagandas
    ADD CONSTRAINT FK_PROPAGANDAS_ON_ID_PARCEIRO FOREIGN KEY (id_parceiro) REFERENCES parceiro (id);