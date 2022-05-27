DROP TABLE IF EXISTS comment CASCADE;
DROP TABLE IF EXISTS favorite CASCADE;
DROP TABLE IF EXISTS topic CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS city_data CASCADE;

CREATE TABLE IF NOT EXISTS users (
  user_id SERIAL NOT NULL,
  authority VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  live VARCHAR(10) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS topic (
  id SERIAL NOT NULL,
  user_id INT NOT NULL,
  path VARCHAR(255) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  latitude VARCHAR(20),
  longitude VARCHAR(20),
  date DATE NOT NULL,
  weather VARCHAR(10) NOT NULL,
  tide_level VARCHAR(10) NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE topic ADD CONSTRAINT FK_users_topic FOREIGN KEY (user_id) REFERENCES users;

CREATE TABLE IF NOT EXISTS favorite (
  id SERIAL NOT NULL,
  user_id INT NOT NULL,
  topic_id INT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE favorite ADD CONSTRAINT FK_favorite_users FOREIGN KEY (user_id) REFERENCES users;
ALTER TABLE favorite ADD CONSTRAINT FK_favorite_topic FOREIGN KEY (topic_id) REFERENCES topic;

CREATE TABLE IF NOT EXISTS comment (
  id SERIAL NOT NULL,
  topic_id INT NOT NULL,
  description VARCHAR(1000) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE comment ADD CONSTRAINT FK_comment_topic FOREIGN KEY (topic_id) REFERENCES topic;

CREATE TABLE city_data(
 id SERIAL NOT NULL,
 city_code VARCHAR(2),
 city_name VARCHAR(30),
 weather_city VARCHAR(30),
 weather_lat VARCHAR(20),
 weather_lon VARCHAR(20),
 PRIMARY KEY (city_name)
);
 
 INSERT INTO city_data
 (city_code, city_name, weather_city, weather_lat, weather_lon)
 VALUES
 ('8','茨城県','茨城県水戸市','36.365852','140.471588'),
 ('12','千葉県','千葉県千葉市中央区','35.607414','140.122833'),
 ('13','東京都','東京都渋谷区','35.66367','139.697723'),
 ('14','神奈川県','神奈川県横浜市中区','35.444721','139.642227');

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO fishing;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO fishing;