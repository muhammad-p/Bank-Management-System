# Description
This project is a lightweight, object-oriented banking application designed to handle secure user authentication, account management, and core financial transactions (deposits and withdrawals). Built with a strong focus on software architecture, it uses a flat package structure (model, service, view, exception) to achieve high cohesion and low coupling. User data is loaded into an in-memory HashMap for lightning-fast O(1) lookups and synchronized permanently with a custom CSV persistence layer, all wrapped inside a responsive Java Swing user interface.

## Architectural & Security Highlights

* **Strict Field Encapsulation:** Core domain attributes such as user passwords and account balances are declared as `private` within the `UserAccount` model, preventing direct external tampering or unauthorized state modification.
* **Controlled Access Patterns:** State modifications and credential verifications are exclusively managed through explicit behavior methods (such as `verifyPassword()` and `addToBalance()`) instead of open public setters, enforcing safe data boundaries.
* **Layered Separation of Concerns:** Business logic and data validation are completely decoupled from the Java Swing user interface through dedicated service classes (`AuthService.java` and `DashboardService.java`), ensuring security rules cannot be bypassed at the presentation tier.
* **Secure In-Memory Caching:** User records are loaded securely on startup into a `HashMap` managed by the service layer to optimize validation checks and transaction processing.
* **Defensive Exception Handling:** Operational edge cases—including invalid credentials, insufficient funds, duplicate user registrations, and self-transfers—are managed via dedicated custom exceptions to maintain transaction integrity.
<br><br>
<br><br>
# Project Reflections & Engineering Takeaways

Building this banking management system from scratch offered invaluable practical insights into software design, system architecture, and UI engineering. Beyond just getting the code to compile, this project served as a deep dive into how real-world systems are structured and where my current design choices diverge from production-grade standards.

### 1. Architectural & Real-World System Insights

*   **Persistence & Storage:** While a flat CSV file acts as a simple and effective persistence layer for a localized learning project, production systems demand robust relational databases (such as PostgreSQL) with ACID compliance, connection pooling, and proper indexing for security and scalability.
*   **Financial Ledger Design:** One of the most fascinating realizations during this project was that real financial systems rarely mutate an account balance directly. Instead of updating a single balance value in place, production systems rely on an append-only transaction log (or double-entry bookkeeping ledger), ensuring every debit and credit leaves an immutable audit trail.
*   **Concurrency & I/O:** Currently, writing to the CSV file happens synchronously on the main thread. In a multi-user production environment, heavy file or database write operations would need to be offloaded to background threads or connection pools to prevent I/O blocking and maintain UI responsiveness.

### 2. Core Software Principles

*   **Separation of Concerns:** Strictly decoupling the application into distinct layers (model, service, view, and exception) proved essential for maintaining high cohesion and low coupling. Keeping business logic out of the presentation layer made debugging and testing significantly cleaner.
*   **Security Encapsulation:** Enforcing private data fields within the `UserAccount` model and restricting state changes to explicit behavior methods highlighted the importance of data integrity and controlled access patterns.

### 3. UI/UX & Layout Lessons

*   **Layout Manager Friction:** Implementing the `DashboardWindow` exposed the learning curve associated with Java Swing layout managers. Struggling with `GridBagLayout` led to heavy reliance on filler components (such as empty labels) to achieve visual symmetry and spacing. This highlighted the need for better layout strategies or exploring dedicated UI frameworks in future desktop applications.

### Looking Forward

This project provided a rock-solid foundation in object-oriented programming, file I/O, and event-driven GUI development. Moving into future projects, I look forward to implementing proper password hashing (e.g., BCrypt), transitioning to relational databases, exploring asynchronous programming for I/O operations, and adopting cleaner, more maintainable UI design patterns.

<br><br>
<br>
# How to Compile and Run

### 1. Clone the repository
Run the following command in your terminal to locally clone the repository onto your machine:

```bash
git clone https://github.com/muhammad-p/Bank-Management-System.git
```

### 2. Compile the Java Files
>[!Important]
>Make sure you are inside the root directory of the project before running the commands below.

Run the following command to compile all `.java` files from the `src` directory and output the compiled `.class` files into a `bin` folder:

```bash
javac -d bin -sourcepath src src/**/*.java
```

*Note: If your terminal shell does not support the recursive `**` wildcard, use the manual path format instead:*
```bash
javac -d bin -sourcepath src src/service/*.java src/view/*.java src/model/*.java
```

### 3. Run the Application
The `Main` entry point belongs to the `service` package. To launch the application, specify the classpath (`bin`) and the fully qualified main class name:

```bash
java -cp bin service.Main
```
