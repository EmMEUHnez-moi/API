CREATE SEQUENCE IF NOT EXISTS user_id_seq;

CREATE TABLE IF NOT EXISTS public.user (
    id INT PRIMARY KEY DEFAULT NEXTVAL('user_id_seq'),
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL,
    phone_number VARCHAR(10) NOT NULL
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
ALTER TABLE public.access_role OWNER TO postgres;
ALTER TABLE public.user_access_role OWNER TO postgres;

-- Insertions pour la table "user"
-- password123
-- securepass
-- mypassword
INSERT INTO public.user (name, surname, email, password, birthdate, phone_number) VALUES
    ('Alice', 'Dupont', 'alice.dupont@example.com', '$2y$10$B0wxSMV1QQVJ80zOme.n9.PpJNuIuxFIGXy9OxLzMgRGHB3hCZiZK',TO_DATE('1995-05-05', 'YYYY-MM-DD'), '0123456789'),
    ('Bob', 'Martin', 'bob.martin@example.com', '$2y$10$xJGnSg85tJnxe/nxH0WEl.XFq5FcFg.kKwX6luz3vu7bS2XyJfoce', TO_DATE('1995-05-05', 'YYYY-MM-DD'), '0987654321'),
    ('Charlie', 'Durand', 'charlie.durand@example.com', '$2y$10$B2OcrMtOqjUn2XEoVJpp0uRcErLCncsmKYDho0/lnsEcuHo1j/JWe', TO_DATE('1995-05-05', 'YYYY-MM-DD'), '0123456789');

-- Insertions pour la table "role"
INSERT INTO public.role (name) VALUES
    ('Driver'),
    ('Passenger');

-- Insertions pour la table "trip"
INSERT INTO public.trip (from_location, to_location, start_date, end_date, hour_of_departure, hour_of_arrival, price, number_of_seats) VALUES
    ('Paris', 'Lyon', '2025-01-20', '2025-01-20', '08:00:00', '12:00:00', 50.00, 3),
    ('Lille', 'Bruxelles', '2025-01-22', '2025-01-22', '10:00:00', '11:30:00', 20.00, 4),
    ('Marseille', 'Nice', '2025-01-25', '2025-01-25', '09:30:00', '11:00:00', 30.00, 2);

-- Insertions pour la table "user_trip"
INSERT INTO public.user_trip (user_id, trip_id, role_id) VALUES
    (1, 1, 1), -- Alice est le conducteur du premier trajet
    (2, 1, 2), -- Bob est un passager du premier trajet
    (3, 2, 1), -- Charlie est le conducteur du deuxième trajet
    (2, 2, 2), -- Bob est un passager du deuxième trajet
    (1, 3, 2); -- Alice est un passager du troisième trajet
