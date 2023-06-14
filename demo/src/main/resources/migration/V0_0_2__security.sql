CREATE TABLE public.system_user
(
    id       bigserial,
    username text NOT NULL UNIQUE,
    password text,
    roles    text
);

INSERT INTO public.system_user (id, username, password, roles) VALUES (DEFAULT, 'user'::text, '$2a$10$iP2EUC6LylQC/5s0rF719OU1CeNq3fdo68ckurqoLL2cjaFbY62K.'::text, 'USER'::text);

INSERT INTO public.system_user (id, username, password, roles) VALUES (DEFAULT, 'admin'::text, '$2a$10$iP2EUC6LylQC/5s0rF719OU1CeNq3fdo68ckurqoLL2cjaFbY62K.'::text, 'USER,ADMIN'::text);