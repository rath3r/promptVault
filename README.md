# Prompt Vault

This is the code for an assignment for the Secure Computing module in the Cybersecurity MSC in UCD.
The assignment spec asked to not consider security but I couldn't bring myself to store passwords
in plain text so have hashed the passwords. There are a couple of checks on the status of logged
in users also.

## Usage

The Spring Boot app was initialised using https://start.spring.io/

To run the app use `./mvnw spring-boot:run`

## Database

The database can be imported from an SQL dump or the application will build the tables on the fly.

Database credentials need to be added to `theapplication.properties`. Currenlty they read:

```
## Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url = jdbc:mysql://localhost:3306/promptVault
spring.datasource.username = website
spring.datasource.password = password
```

Change to your db username and password. Instructions for creating username and password are found
below.

### Import example DB

There is a small example database with an admin user, two normal users, some prompts, some flagged
keywords and categories in a MySQL dump

#### Database Setup

Create the database:

```bash
sudo mysql -e "CREATE DATABASE promptvault;"
```

Import the database dump which is located in `database/promptVault.sql`:

```bash
sudo mysql promptvault < promptvault.sql
```

Update `application.properties` with your MySQL configuration.

Run the application:

```bash
./mvn spring-boot:run
```

### Create DB from scratch

Create promptVault database;

```
CREATE DATABASE promptVault;
USE promptVault;
CREATE USER 'website'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON promptVault.* TO 'website'@'localhost';
```

The application will build the DB while you use it.

Make sure to change the spring.jpa.hibernate.ddl-auto value to `create` in
`src/main/resources/application.properties`

And change back to `update` afterwards to prevent readding all the data after every restart.

## Requirements

Use Spring Boot

Does not require the use of an AI API. AI responses can be mocked to read:

`This is a simulated AI response.`

## Login and logut functionality

Users should be able to login and logout.

### Users

#### Admins

Do not need to register. There is a predefined Admin user that can be used straight away.

Username: admin
password: admin

Using `admin` as a password is flagged by Chrome but this can be ignored.

When logged in as the Admin there is an Admin Dashboard link in the navbar which handles all admin
tasks.

- Can view the list of registered Users.
- Can enable and disable regular users.
- Can see all prompts
- Can see flagged prompts
- Can add category to flagged prompts
- Can create, edit and delete categories

#### Normal Users

User - needs to register. Has username, email, role, account status.

If using the DB dump there are two users ready to be used.

Username: example01
password: asdf

Username: example02
password: asdf

- Can see their prompts
- Can see shared prompts
- Can see their flagged prompts
- Can see their account status

#### Add Users

Normal users need to be registered.
Admin users are injected straight into the database.

### Categories

Use to categorise all prompts.

Admin can add, edit and delete prompt categories. Categories have name and description.

Example categories:

- Coding
- Research
- Cybersecurity
- Legal
- HR
- Personal productivity

### Policy Keywords

Prompts need to be scanned for keywords.

Examples of policy keywords include:

- password
- API key
- secret
- credit card
- private key
- confidential
- medical record
- student number

### Prompts

All prompts need to be recorded.

Admins need to be able to see a list of flagged Prompts
Users can see their own prompts

## Models

Users:

- ID
- username
- firstname
- surname
- email
- password
- status [enabled/disabled]
- role [admin/normal]
- createdAt
- updatedAt

Prompts:

- ID
- Title
- PromptText
- Owner - reference to User entity
- Shared [true/false]
- Category - reference to Category entity is added
- Flagged
- FlaggedKeyword
- CreatedAt
- UpdatedAt

PromptSubmission:

- ID
- Prompt
- User
- submittedAt
- responseText

FlaggedKeywords:

- ID
- Keyword

Categories:

- ID
- Name
