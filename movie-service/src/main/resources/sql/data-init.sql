
INSERT INTO public.categories (id, category_name, is_active, "version", created_at, updated_at)
VALUES
    (nextval('categories_id_seq'::regclass), 'Action', true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric)),
    (nextval('categories_id_seq'::regclass), 'Drama', true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric)),
    (nextval('categories_id_seq'::regclass), 'Romance', true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric)),
    (nextval('categories_id_seq'::regclass), 'Sci-Fi', true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric)),
    (nextval('categories_id_seq'::regclass), 'Crime', true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric)),
    (nextval('categories_id_seq'::regclass), 'Fantasy', true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric)),
    (nextval('categories_id_seq'::regclass), 'Thriller', true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric)),
    (nextval('categories_id_seq'::regclass), 'Animation', true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric)),
    (nextval('categories_id_seq'::regclass), 'Mystery', true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric)),
    (nextval('categories_id_seq'::regclass), 'Comedy', true, 0, (EXTRACT(epoch FROM now()) * 1000::numeric), (EXTRACT(epoch FROM now()) * 1000::numeric));



INSERT INTO public.movies (id, title, description, duration, "language", released_at, poster_url, trailer_url, movie_url, theater_id, category_id, is_active, "version", created_at, updated_at)
VALUES
    (nextval('movies_id_seq'::regclass), 'Inception', 'A mind-bending thriller by Christopher Nolan.', '148', 'English', 2010, '', '', '', 1, 4, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'The Dark Knight', 'Batman faces Joker in this iconic action film.', '152', 'English', 2008, '', '', '', 1, 1, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Titanic', 'Romance set against the tragic sinking of Titanic.', '195', 'English', 1997, '', '', '', 1, 3, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Parasite', 'A dark comedy thriller from South Korea.', '132', 'Korean', 2019, '', '', '', 1, 2, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Interstellar', 'Exploration through space and time.', '169', 'English', 2014, '', '', '', 1, 4, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'The Godfather', 'Crime and family saga.', '175', 'English', 1972, '', '', '', 1, 5, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Avengers: Endgame', 'Heroes unite to reverse the snap.', '181', 'English', 2019, '', '', '', 1, 1, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Your Name', 'Romantic fantasy anime.', '106', 'Japanese', 2016, '', '', '', 1, 8, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Joker', 'Origin story of the iconic villain.', '122', 'English', 2019, '', '', '', 1, 7, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Spirited Away', 'A magical journey in the spirit world.', '125', 'Japanese', 2001, '', '', '', 1, 6, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000));



INSERT INTO public.movies (id, title, description, duration, "language", released_at, poster_url, trailer_url, movie_url, theater_id, category_id, is_active, "version", created_at, updated_at)
VALUES
    (nextval('movies_id_seq'::regclass), 'Mad Max: Fury Road', 'High-octane chase in a post-apocalyptic wasteland.', '120', 'English', 2015, 'https://example.com/poster/madmax.jpg', 'https://example.com/trailer/madmax.mp4', 'https://example.com/movie/madmax.mp4', 1, 1, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'John Wick', 'A hitman seeks revenge for the death of his dog.', '101', 'English', 2014, 'https://example.com/poster/johnwick.jpg', 'https://example.com/trailer/johnwick.mp4', 'https://example.com/movie/johnwick.mp4', 1, 1, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'The Pursuit of Happyness', 'A father struggles to build a better future for his son.', '117', 'English', 2006, 'https://example.com/poster/happyness.jpg', 'https://example.com/trailer/happyness.mp4', 'https://example.com/movie/happyness.mp4', 1, 2, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'A Beautiful Mind', 'The life of mathematician John Nash.', '135', 'English', 2001, 'https://example.com/poster/beautifulmind.jpg', 'https://example.com/trailer/beautifulmind.mp4', 'https://example.com/movie/beautifulmind.mp4', 1, 2, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'La La Land', 'A jazz musician and aspiring actress fall in love.', '128', 'English', 2016, 'https://example.com/poster/lalaland.jpg', 'https://example.com/trailer/lalaland.mp4', 'https://example.com/movie/lalaland.mp4', 1, 3, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'The Notebook', 'A timeless love story unfolds.', '123', 'English', 2004, 'https://example.com/poster/notebook.jpg', 'https://example.com/trailer/notebook.mp4', 'https://example.com/movie/notebook.mp4', 1, 3, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'The Matrix', 'A hacker discovers reality is a simulation.', '136', 'English', 1999, 'https://example.com/poster/matrix.jpg', 'https://example.com/trailer/matrix.mp4', 'https://example.com/movie/matrix.mp4', 1, 4, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Blade Runner 2049', 'A new blade runner unearths a long-buried secret.', '164', 'English', 2017, 'https://example.com/poster/bladerunner.jpg', 'https://example.com/trailer/bladerunner.mp4', 'https://example.com/movie/bladerunner.mp4', 1, 4, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Pulp Fiction', 'Interwoven stories of crime in LA.', '154', 'English', 1994, 'https://example.com/poster/pulpfiction.jpg', 'https://example.com/trailer/pulpfiction.mp4', 'https://example.com/movie/pulpfiction.mp4', 1, 5, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Se7en', 'Detectives hunt a serial killer themed around the seven sins.', '127', 'English', 1995, 'https://example.com/poster/se7en.jpg', 'https://example.com/trailer/se7en.mp4', 'https://example.com/movie/se7en.mp4', 1, 5, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Harry Potter and the Sorcerer\s Stone', 'A boy discovers he is a wizard.', '152', 'English', 2001, 'https://example.com/poster/harrypotter1.jpg', 'https://example.com/trailer/harrypotter1.mp4', 'https://example.com/movie/harrypotter1.mp4', 1, 6, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Pan\s Labyrinth', 'A dark fairy tale set in post-Civil War Spain.', '118', 'Spanish', 2006, 'https://example.com/poster/panslabyrinth.jpg', 'https://example.com/trailer/panslabyrinth.mp4', 'https://example.com/movie/panslabyrinth.mp4', 1, 6, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Gone Girl', 'A woman disappears, and her husband is suspected.', '149', 'English', 2014, 'https://example.com/poster/gonegirl.jpg', 'https://example.com/trailer/gonegirl.mp4', 'https://example.com/movie/gonegirl.mp4', 1, 7, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Shutter Island', 'A U.S. Marshal investigates a psychiatric facility.', '138', 'English', 2010, 'https://example.com/poster/shutterisland.jpg', 'https://example.com/trailer/shutterisland.mp4', 'https://example.com/movie/shutterisland.mp4', 1, 7, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Toy Story', 'A cowboy doll feels replaced by a new space toy.', '81', 'English', 1995, 'https://example.com/poster/toystory.jpg', 'https://example.com/trailer/toystory.mp4', 'https://example.com/movie/toystory.mp4', 1, 8, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),
    (nextval('movies_id_seq'::regclass), 'Coco', 'A boy journeys to the land of the dead to discover his family’s musical past.', '105', 'English', 2017, 'https://example.com/poster/coco.jpg', 'https://example.com/trailer/coco.mp4', 'https://example.com/movie/coco.mp4', 1, 8, true, 0, (EXTRACT(epoch FROM now()) * 1000), (EXTRACT(epoch FROM now()) * 1000)),

