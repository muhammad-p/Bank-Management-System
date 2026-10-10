This project is a lightweight, object-oriented banking application designed to handle secure user authentication, account management, and core financial transactions (deposits and withdrawals). Built with a strong focus on software architecture, it uses a flat package structure (model, service, view, exception) to achieve high cohesion and low coupling. User data is loaded into an in-memory HashMap for lightning-fast O(1) lookups and synchronized permanently with a custom CSV persistence layer, all wrapped inside a responsive Java Swing user interface.

## How to Compile and Run

>[!Important]
>Make sure you are inside the root directory of the project before running any of these commands in your terminal.
### 1. Clone the repository
Run the following command in your terminal to locally clone the repository onto your machine:

```bash
git clone https://github.com/muhammad-p/Bank-Management-System.git
```

### 2. Compile the Java Files

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