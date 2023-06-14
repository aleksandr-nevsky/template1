------------------------------
CREATE TABLE public.employees
(
    id              bigserial NOT NULL
        CONSTRAINT employees_pk
            PRIMARY KEY,
    external_id     text      NOT NULL
        CONSTRAINT employees_pk2
            UNIQUE,
    first_name      text      NOT NULL,
    last_name       text      NOT NULL,
    email           text      NOT NULL,
    enabled         boolean
);

COMMENT ON TABLE public.employees IS 'Сотрудники';

COMMENT ON COLUMN public.employees.id IS 'идентификатор сотрудника';

COMMENT ON COLUMN public.employees.external_id IS 'внешний идентификатор сотрудника';

COMMENT ON COLUMN public.employees.last_name IS 'Фамилия';

COMMENT ON COLUMN public.employees.first_name IS 'Имя';

COMMENT ON COLUMN public.employees.email IS 'Почта';

COMMENT ON COLUMN public.employees.enabled IS 'актуальность';

