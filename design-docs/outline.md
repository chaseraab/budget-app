# Budget Application
## Purpose
I want to create a budget app to fufill my own personal needs, any the needs of any of those that wish to download it.

### What should the app do?
- Track purchases
- Track savings
- Track investments
- Track various money accounts (banks, schwab, etc..)
- Creation of budgets
    - A budget is a category of spending, i.e. groceries, rent, house hold items, etc..
- Allow for analytics on current spending
- Allow for planning on planned savings

### How will the app do it?
Backend: python server using django
Database: postgres
Frontend: Raw HTML, CSS, Javascript
    Eventually relay into React

First iteration is to run the webserver locally
Second iteration is to run the webserver on my linux laptop setup as a homeserver
Third iteration is to run the webserver on the linux laptop via kubernetes, fully containerized

## Design

Budgets will be based on monthly cycles. Planning and analytics will primarily be based on months. 

### Dashboard
The landing page for the application. This should show some metrics on the current month: last spending, budgets that are almost at capacity

### Planning Section:
- Budgets (Expected spending)
- Saving
- Income

### Monthly Budget Page
This will be the focal point of the app. There will be an entire section for budgets. A budget is a monthly tracking of spending against set limits in a category. It will also include income and savings for that period.

