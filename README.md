# Sushi Menu Database App (SQLite)

A small Java application that connects to a local SQLite database and performs basic operations on a “menu” of dishes. It demonstrates a simple JDBC setup, parameterized queries, and a minimal data model suitable for a restaurant menu or inventory of items.

## Features
- Java 21 application using JDBC to connect to SQLite
- Basic CRUD operations on a single table (menu)
- Simple console execution
- Easy to run locally with a file-based database

## Requirements
- Java 21 or later
- SQLite JDBC driver (Suggested: org.xerial:sqlite-jdbc)
- A local SQLite database file

## Quick Start

1) Prepare the database file
- Create or choose a location for your SQLite database file, for example: path/to/database/app.db

2) Create the schema
- Initialize the database by creating the menu table:

3) Build and run
- Make sure the SQLite JDBC driver is on the classpath when compiling and running.

If you use an IDE, add the SQLite JDBC dependency to the project configuration and set the run configuration’s classpath accordingly.

## Configuration
- JDBC URL format for SQLite: jdbc:sqlite:/absolute/or/relative/path/to/your.db
- Example: jdbc:sqlite:database/sushi.db
- Ensure the directory exists and that the running user has read/write permissions.

## Database Structure

The project uses a single table named menu within the main schema:

```sql
create table main.menu
(
    id       integer          not null
        constraint menu_pk
            primary key autoincrement,
    piatto   varchar(255)     not null,
    prezzo   double unsigned  not null,
    quantita integer unsigned not null
);
```


Column details:
- id: INTEGER PRIMARY KEY AUTOINCREMENT. Unique identifier for each menu item.
- piatto: VARCHAR(255) NOT NULL. The dish/item name.
- prezzo: DOUBLE UNSIGNED NOT NULL. The item price. In SQLite, “DOUBLE” maps to REAL affinity; “UNSIGNED” is not enforced and is used here for documentation purposes.
- quantita: INTEGER UNSIGNED NOT NULL. Available quantity. “UNSIGNED” is not enforced by SQLite; use application-side validation to prevent negative values.

Indexes:
- The primary key on id ensures fast lookups by identifier. Additional indexes can be added if you frequently filter by piatto or other columns.

## Development Tips
- Use parameterized queries (PreparedStatement) for all write/read operations to avoid SQL injection and handle typing safely.
- Validate user inputs (e.g., prezzo >= 0, quantita >= 0) at the application layer.
- Consider wrapping database operations in transactions for batch inserts/updates.

## Troubleshooting
- Class not found: Ensure the SQLite JDBC JAR is on the runtime classpath.
- Database locked errors: Close statements and result sets promptly; avoid long-running transactions.
- Invalid path: Verify the JDBC URL points to an existing directory and a valid .db file path.

## License
Add your license here (e.g., MIT, Apache 2.0), or specify that it is proprietary.