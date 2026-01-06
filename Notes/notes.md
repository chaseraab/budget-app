# Springboot

mvn spring-boot:run   
http://localhost:8080

mvm clean install
mvn clean compile

@RequestBody - https://www.baeldung.com/spring-request-response-body
- Used for interpretting the body of an HTTP request

@PathVariable - https://www.baeldung.com/spring-pathvariable
- Extracts value from the URL

# Application Design
What are the 'isActive' and 'isExpected' flags on the allocations class?
'isActive' is a reocurring monthly allocation
'isExpected' is an allocation that remains the same from month to month, i.e. a subscription. The price and time of payment are consistent.

# Postgres

password is admin

CREATE TABLE allocations (
id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
name TEXT NOT NULL,
type TEXT CHECK (type IN ('want', 'need', 'save', 'debt')),
amount DOUBLE PRECISION NOT NULL
);

CREATE TABLE transactions (
id INT GENERATED ALWAYS AS INDENTITY PRIMARY KEY,
transaction_date DATE NOT NULL,
item TEXT NOT NULL,
company TEXT NOT NULL,
FOREIGN KEY (account) REFERENCES accounts(id),
FOREIGN KEY (category) REFERENCES allocations(id),
amount DOUBLE PRECISION NOT NULL
);

ALTER TABLE allocation (
ADD COLUMN isTemp BOOLEAN DEFAULT False
);

CREATE TABLE income (
id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
name TEXT NOT NULL,
amount DOUBLE PRECISION NOT NULL,
account INT NOT NULL,
FOREIGN KEY (account) REFERENCES accounts(id)
);

begin;
alter table accounts drop constraint accounts_type_check;
alter table accounts add constraint accounts_type_check check (type in ('savings', 'checking', 'debt', 'credit card', 'debit card'));
commit;
rollback;

alter table public.account_balances
rename column balance_date to date;

insert into public.accounts (name, type) values ('USAA Checking', 'checking');

<!--                            <groupId>org.mapstruct</groupId>-->
<!--                            <artifactId>mapstruct-processor</artifactId>-->
<!--                            <version>1.5.5.Final</version>-->

    pg_dump -U admin -t public.account_balances --schema-only budget-app > table_creation_statement.sql