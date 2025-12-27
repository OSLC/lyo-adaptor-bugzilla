-- Initialize database for Bugzilla
CREATE DATABASE IF NOT EXISTS bugzilla CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
GRANT ALL PRIVILEGES ON bugzilla.* TO 'bugzilla'@'%';
FLUSH PRIVILEGES;
