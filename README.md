# Prompt Vault

This is the code for an assignment for the Secure Computing module in the Cybersecurity MSC in UCD.

## Usage

The Spring Boot app was initialised using https://start.spring.io/

To run the app use `./mvnw spring-boot:run`

## Database

Create promptVault database;

```
CREATE DATABASE promptVault;
USE promptVault;
CREATE USER 'website'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON promptVault.* TO 'website'@'localhost';
```

## Requirements

Use Spring Boot

Does not require the use of an AI API. AI responses can be mocked to read:

`This is a simulated AI response.`

## Login and logut functionality

Users should be able to login and logout.

### Users

#### Admins

Do not need to register.

- Can view the list of registered Users.
- Can enable and disable regular users.
- Can see all prompts
- Can see flagged prompts
- Can add category to flagged prompts
- Can create, edit and delete categories

#### Normal Users

User - needs to register. Has username, email, role, account status.

- Can see their prompts
- Can see shared prompts
- Can see their flagged prompts
- Can see their account status

### Categories

Use to categorise flagged prompts.

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
- email
- status [enabled/disabled]
- role [admin/normal]

Prompts:

- ID
- Title
- PromptText
- OwnerID
- Shared [true/false]
- Date

FlaggedPrompts:

- ID
- PromptID
- FlaggedKeywordID
- CategoryID

FlaggedKeywords:

- ID
- Title
- Keyword

Categories:

- ID
- Category
