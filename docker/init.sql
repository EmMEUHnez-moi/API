CREATE SEQUENCE IF NOT EXISTS user_id_seq;

CREATE TABLE IF NOT EXISTS public.user (
    id INT PRIMARY KEY DEFAULT NEXTVAL('user_id_seq'),
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS role_id_seq;

CREATE TABLE IF NOT EXISTS public.role (
    id INT PRIMARY KEY DEFAULT NEXTVAL('role_id_seq'),
    name VARCHAR(255) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS trip_id_seq;

CREATE TABLE IF NOT EXISTS public.trip (
    id INT PRIMARY KEY DEFAULT NEXTVAL('trip_id_seq'),
    from_location VARCHAR(255) NOT NULL,
    to_location VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    hour_of_departure TIME NOT NULL,
    hour_of_arrival TIME NOT NULL,
    price DECIMAL NOT NULL,
    number_of_seats INT NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS user_trip_id_seq;

CREATE TABLE IF NOT EXISTS public.user_trip (
    id INT PRIMARY KEY DEFAULT NEXTVAL('user_trip_id_seq'),
    user_id INT NOT NULL,
    trip_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES public.user(id),
    FOREIGN KEY (trip_id) REFERENCES public.trip(id),
    FOREIGN KEY (role_id) REFERENCES public.role(id)
);

ALTER SEQUENCE user_id_seq OWNED BY public.user.id;
ALTER SEQUENCE role_id_seq OWNED BY public.role.id;
ALTER SEQUENCE trip_id_seq OWNED BY public.trip.id;
ALTER SEQUENCE user_trip_id_seq OWNED BY public.user_trip.id;

ALTER TABLE public.user OWNER TO postgres;
ALTER TABLE public.role OWNER TO postgres;
ALTER TABLE public.trip OWNER TO postgres;
ALTER TABLE public.user_trip OWNER TO postgres;


