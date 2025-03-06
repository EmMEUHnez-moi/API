CREATE TABLE IF NOT EXISTS public.user (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
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
    user_id UUID NOT NULL,
    trip_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES public.user(id),
    FOREIGN KEY (trip_id) REFERENCES public.trip(id),
    FOREIGN KEY (role_id) REFERENCES public.role(id)
);

ALTER SEQUENCE role_id_seq OWNED BY public.role.id;
ALTER SEQUENCE trip_id_seq OWNED BY public.trip.id;
ALTER SEQUENCE user_trip_id_seq OWNED BY public.user_trip.id;

ALTER TABLE public.user OWNER TO postgres;
ALTER TABLE public.role OWNER TO postgres;
ALTER TABLE public.trip OWNER TO postgres;
ALTER TABLE public.user_trip OWNER TO postgres;

-- Insertions pour la table "user"
-- password123
-- securepass
-- mypassword
INSERT INTO public.user (id, name, surname, email, birthdate, phone_number) VALUES
    ('4ace6483-98d7-44d0-b6d8-89682fdaa014'::UUID,'Alice', 'Dupont', 'alice.dupont@example.com',TO_DATE('1995-05-05', 'YYYY-MM-DD'), '0123456789'),
    ('22ef6ab5-c1f3-4ca7-a4bb-14898a8dafc5'::UUID,'Bob', 'Martin', 'bob.martin@example.com', TO_DATE('1995-05-05', 'YYYY-MM-DD'), '0987654321'),
    ('99d99ed2-3c65-4407-940b-340f76955473'::UUID,'Charlie', 'Durand', 'charlie.durand@example.com', TO_DATE('1995-05-05', 'YYYY-MM-DD'), '0123456789');

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
    ('4ace6483-98d7-44d0-b6d8-89682fdaa014'::UUID, 1, 1), -- Alice est le conducteur du premier trajet
    ('22ef6ab5-c1f3-4ca7-a4bb-14898a8dafc5'::UUID, 1, 2), -- Bob est un passager du premier trajet
    ('99d99ed2-3c65-4407-940b-340f76955473'::UUID, 2, 1), -- Charlie est le conducteur du deuxième trajet
    ('22ef6ab5-c1f3-4ca7-a4bb-14898a8dafc5'::UUID, 2, 2), -- Bob est un passager du deuxième trajet
    ('4ace6483-98d7-44d0-b6d8-89682fdaa014'::UUID, 3, 2); -- Alice est un passager du troisième trajet

CREATE SCHEMA IF NOT EXISTS keycloak;