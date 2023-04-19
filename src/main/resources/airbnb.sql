-- Table: public.accommodations

-- DROP TABLE IF EXISTS public.accommodations;

CREATE TABLE IF NOT EXISTS public.accommodations
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    create_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    acc_name character varying(255) COLLATE pg_catalog."default",
    bathrooms integer,
    bedroom integer,
    cancellation_policy character varying(255) COLLATE pg_catalog."default",
    description character varying(255) COLLATE pg_catalog."default",
    has_air_conditioning boolean,
    has_heating boolean,
    has_internet boolean,
    has_kitchen boolean,
    has_tv boolean,
    is_active boolean,
    location_lat double precision,
    location_long double precision,
    maximum_nights integer,
    minimum_nights integer,
    price real,
    room_address character varying(255) COLLATE pg_catalog."default",
    room_country character varying(255) COLLATE pg_catalog."default",
    room_country_code character varying(255) COLLATE pg_catalog."default",
    room_state character varying(255) COLLATE pg_catalog."default",
    room_street character varying(255) COLLATE pg_catalog."default",
    room_type character varying(255) COLLATE pg_catalog."default",
    user_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT accommodations_pkey PRIMARY KEY (id),
    CONSTRAINT fkp0dg4bgd1dnioaadgcdb9ccvi FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.accommodations
    OWNER to postgres;


-- Table: public.reservations

-- DROP TABLE IF EXISTS public.reservations;

CREATE TABLE IF NOT EXISTS public.reservations
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    create_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    check_in date NOT NULL,
    check_out date NOT NULL,
    guest_ami integer NOT NULL,
    is_active boolean NOT NULL,
    payment_status character varying(30) COLLATE pg_catalog."default" NOT NULL,
    price real NOT NULL,
    total_price real NOT NULL,
    acc_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT reservations_pkey PRIMARY KEY (id),
    CONSTRAINT fk1nry9mwho4ch6do5kuw4uletn FOREIGN KEY (acc_id)
        REFERENCES public.accommodations (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkb5g9io5h54iwl2inkno50ppln FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.reservations
    OWNER to postgres;

-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    create_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    email character varying(40) COLLATE pg_catalog."default" NOT NULL,
    firstname character varying(50) COLLATE pg_catalog."default",
    is_deleted boolean NOT NULL DEFAULT false,
    lastname character varying(50) COLLATE pg_catalog."default",
    password character varying(100) COLLATE pg_catalog."default",
    phone character varying(20) COLLATE pg_catalog."default",
    provider character varying(10) COLLATE pg_catalog."default" DEFAULT 'local'::character varying,
    username character varying(40) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

-- Table: public.accommodation_images

-- DROP TABLE IF EXISTS public.accommodation_images;

CREATE TABLE IF NOT EXISTS public.accommodation_images
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    create_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    url character varying(255) COLLATE pg_catalog."default" NOT NULL,
    accommodation_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT accommodation_images_pkey PRIMARY KEY (id),
    CONSTRAINT fk6288e03blo2ltoh9kverp7j6i FOREIGN KEY (accommodation_id)
        REFERENCES public.accommodations (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.accommodation_images
    OWNER to postgres;