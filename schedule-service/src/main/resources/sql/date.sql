--location
INSERT INTO public.locations
(id, longitude, latitude, is_active, created_at, updated_at)
VALUES(nextval('locations_id_seq'::regclass), 10.762622, 106.660172, true, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.locations
(id, longitude, latitude, is_active, created_at, updated_at)
VALUES(nextval('locations_id_seq'::regclass), 21.028511, 105.804817, true, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.locations
(id, longitude, latitude, is_active, created_at, updated_at)
VALUES(nextval('locations_id_seq'::regclass), 16.047079, 108.206230, true, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.locations
(id, longitude, latitude, is_active, created_at, updated_at)
VALUES(nextval('locations_id_seq'::regclass), 11.940419, 108.458313, true, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.locations
(id, longitude, latitude, is_active, created_at, updated_at)
VALUES(nextval('locations_id_seq'::regclass), 10.823099, 106.629664, true, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.locations
(id, longitude, latitude, is_active, created_at, updated_at)
VALUES(nextval('locations_id_seq'::regclass), 9.934950, 105.601998, true, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.locations
(id, longitude, latitude, is_active, created_at, updated_at)
VALUES(nextval('locations_id_seq'::regclass), 22.336255, 103.972083, true, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.locations
(id, longitude, latitude, is_active, created_at, updated_at)
VALUES(nextval('locations_id_seq'::regclass), 20.844911, 106.688087, true, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.locations
(id, longitude, latitude, is_active, created_at, updated_at)
VALUES(nextval('locations_id_seq'::regclass), 13.756331, 100.501762, true, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.locations
(id, longitude, latitude, is_active, created_at, updated_at)
VALUES(nextval('locations_id_seq'::regclass), 18.666624, 105.690449, true, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));


-- theater

INSERT INTO public.theaters
(id, theater_name, description, director_id, location_id, is_active, "version", created_at, updated_at)
VALUES(nextval('theaters_id_seq'::regclass), 'Grand Cinema', 'A modern cinema with luxury seating.', 2, 1, true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.theaters
(id, theater_name, description, director_id, location_id, is_active, "version", created_at, updated_at)
VALUES(nextval('theaters_id_seq'::regclass), 'Starlight Theater', 'Features the latest blockbusters in 3D.', 2, 2, true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.theaters
(id, theater_name, description, director_id, location_id, is_active, "version", created_at, updated_at)
VALUES(nextval('theaters_id_seq'::regclass), 'Sunset Multiplex', 'Family-friendly cinema with 8 screens.', 2, 3, true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.theaters
(id, theater_name, description, director_id, location_id, is_active, "version", created_at, updated_at)
VALUES(nextval('theaters_id_seq'::regclass), 'Downtown Movies', 'Classic films and indie favorites.', 2, 4, true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.theaters
(id, theater_name, description, director_id, location_id, is_active, "version", created_at, updated_at)
VALUES(nextval('theaters_id_seq'::regclass), 'Cineplex 21', 'State-of-the-art sound and projection.', 2, 10, true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.theaters
(id, theater_name, description, director_id, location_id, is_active, "version", created_at, updated_at)
VALUES(nextval('theaters_id_seq'::regclass), 'Galaxy Cinema', 'Best place for sci-fi movie lovers.', 2, 5, true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.theaters
(id, theater_name, description, director_id, location_id, is_active, "version", created_at, updated_at)
VALUES(nextval('theaters_id_seq'::regclass), 'The Silver Screen', 'Retro-themed cinema with vintage decor.', 2, 6, true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.theaters
(id, theater_name, description, director_id, location_id, is_active, "version", created_at, updated_at)
VALUES(nextval('theaters_id_seq'::regclass), 'Majestic Theater', 'Large auditorium for premieres and events.', 2, 7, true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.theaters
(id, theater_name, description, director_id, location_id, is_active, "version", created_at, updated_at)
VALUES(nextval('theaters_id_seq'::regclass), 'Riverfront Cinema', 'Riverside location with stunning views.', 2, 8, true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));

INSERT INTO public.theaters
(id, theater_name, description, director_id, location_id, is_active, "version", created_at, updated_at)
VALUES(nextval('theaters_id_seq'::regclass), 'Palace Cinemas', 'Historic cinema with modern amenities.', 2, 9, true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));












