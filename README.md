# SRP Exercise - SOLID Principles in Practice

A Java application demonstrating SOLID principles through a practical salary calculation system, with special emphasis on the **Single Responsibility Principle (SRP)** and the **Strategy Pattern**.

## 📋 Table of Contents

- [Overview](#overview)
- [Code Structure](#code-structure)
- [Design Patterns](#design-patterns)
- [SOLID Principles](#solid-principles)
- [How to Build and Run](#how-to-build-and-run)
- [Example Usage](#example-usage)
- [Author](#author)

## 🎯 Overview

This project implements a salary calculation system that manages various types of discounts (taxes and health insurance) for employees. The application showcases how to apply SOLID principles to create maintainable, extensible, and testable code.

### Key Features

- **Multiple discount types**: Tax discounts based on salary ranges and union membership, plus health insurance deductions
- **Flexible tax rules**: Different tax rates based on income brackets and union affiliation
- **Extensible architecture**: Easy to add new discount types or tax rules without modifying existing code
- **Clean separation of concerns**: Each class has a single, well-defined responsibility

## 📁 Code Structure

The project follows a layered architecture organized into clear packages:

```
src/main/java/pablo/tzeliks/
│
├── model/                          # Domain models
│   ├── Funcionario.java           # Employee entity with attributes
│   └── Sindicato.java             # Union enumeration (METALURGICO, TEXTIL)
│
├── service/                        # Business logic layer
│   ├── SalarioService.java        # Main service orchestrating salary calculations
│   │
│   ├── interfaces/                # Core abstractions
│   │   ├── Desconto.java          # Interface for discount types
│   │   └── RegraStrategy.java     # Interface for tax calculation rules
│   │
│   ├── impl/                      # Concrete discount implementations
│   │   ├── DescontoImposto.java   # Tax discount calculator
│   │   └── DescontoPlanoSaude.java # Health insurance discount
│   │
│   └── strategy/                  # Tax calculation strategies
│       ├── RegraIsento.java       # Exempt rule (salary < 1000)
│       ├── RegraFaixaBaixa.java   # Low bracket (salary < 2000)
│       ├── RegraFaixaMedia.java   # Medium bracket (salary < 5000)
│       ├── RegraFaixaAlta.java    # High bracket (salary >= 5000)
│       ├── RegraMetalurgico.java  # Metalworkers union rule (2.5%)
│       └── RegraTextil.java       # Textile union rule (4%)
│
└── view/                          # Presentation layer
    ├── Main.java                  # Application entry point
    └── helper/
        └── PrintHelper.java       # Utility for formatted output
```

### Package Responsibilities

- **model**: Contains domain entities that represent the business concepts (Employee, Union)
- **service**: Implements all business logic for salary calculations and discount management
- **service/interfaces**: Defines contracts that ensure loose coupling and high cohesion
- **service/impl**: Provides concrete implementations for different types of discounts
- **service/strategy**: Contains specific tax calculation rules following the Strategy pattern
- **view**: Handles user interaction and output formatting

## 🎨 Design Patterns

### Strategy Pattern

The application extensively uses the **Strategy Pattern** to encapsulate different tax calculation algorithms. This pattern allows the system to:

- Define a family of algorithms (tax rules) that are interchangeable
- Encapsulate each algorithm in separate classes
- Select the appropriate algorithm at runtime based on employee attributes

#### Implementation Details

1. **Strategy Interface**: `RegraStrategy` defines the contract for all tax rules
   ```java
   public interface RegraStrategy {
       boolean aplicavel(Funcionario funcionario);  // Check if rule applies
       double calcularDesconto(Funcionario funcionario);  // Calculate discount
       String getName();  // Get rule name
       double getPorcentagem();  // Get percentage
   }
   ```

2. **Concrete Strategies**: Each tax rule implements the interface
   - `RegraIsento`: For salaries under 1,000 (0% tax)
   - `RegraFaixaBaixa`: For salaries under 2,000 (15% tax)
   - `RegraFaixaMedia`: For salaries under 5,000 (20% tax)
   - `RegraFaixaAlta`: For salaries 5,000+ (25% tax)
   - `RegraMetalurgico`: Additional 2.5% for metalworkers union
   - `RegraTextil`: Additional 4% for textile union

3. **Context**: `DescontoImposto` uses strategies to calculate tax discounts

### Additional Patterns

- **Template Method**: `Desconto` interface defines a template for all discount calculations
- **Dependency Injection**: Strategies are injected into `DescontoImposto` constructor

## 🏗️ SOLID Principles

### Single Responsibility Principle (SRP) ⭐

**SRP states that a class should have only one reason to change.** This is the primary focus of this exercise.

#### Examples in the Code

1. **Model Classes**
   - `Funcionario`: Only responsible for holding employee data
   - `Sindicato`: Only represents union types as an enumeration

2. **Service Layer Separation**
   - `SalarioService`: Orchestrates salary calculations (coordinator role)
   - `DescontoImposto`: Calculates tax discounts only
   - `DescontoPlanoSaude`: Calculates health insurance discounts only

3. **Strategy Classes** (Best SRP Examples!)
   - Each `Regra*` class has ONE responsibility: determine if it applies and calculate its specific discount
   - `RegraFaixaBaixa`: Only handles low income bracket logic
   - `RegraMetalurgico`: Only handles metalworkers union logic
   - If tax rules change, only the affected strategy class needs modification

4. **View Layer**
   - `PrintHelper`: Only responsible for formatting and displaying output
   - `Main`: Only responsible for application initialization and execution flow

#### Benefits Demonstrated

- **Maintainability**: To change a tax rule, modify only one strategy class
- **Testability**: Each class can be tested independently
- **Clarity**: Code is easier to understand as each class does one thing well

### Open/Closed Principle (OCP)

The system is **open for extension but closed for modification**:

- **Adding new discount types**: Create a new class implementing `Desconto` interface
- **Adding new tax rules**: Create a new class implementing `RegraStrategy` interface
- No need to modify existing code to add functionality

Example: Adding a new "RegraIsencaoParcial" would only require:
```java
public class RegraIsencaoParcial implements RegraStrategy {
    // Implementation
}
```
Then add it to the list in `SalarioService` - no other changes needed!

### Liskov Substitution Principle (LSP)

All strategy implementations can be substituted for their interface without breaking functionality:
- Any `RegraStrategy` can replace another in the list
- Any `Desconto` can replace another in the discount calculations

### Interface Segregation Principle (ISP)

Interfaces are focused and clients only depend on methods they use:
- `Desconto`: Minimal interface with only essential methods
- `RegraStrategy`: Contains only methods needed for strategy implementation

### Dependency Inversion Principle (DIP)

High-level modules depend on abstractions, not concrete implementations:
- `SalarioService` depends on `Desconto` and `RegraStrategy` interfaces
- `DescontoImposto` depends on `RegraStrategy` interface, not concrete strategies
- Concrete implementations are injected through constructors

## 🚀 How to Build and Run

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Building the Project

```bash
# Clone the repository
git clone https://github.com/PabloTzeliks/srp-exercise.git
cd srp-exercise

# Compile the project
mvn clean compile

# Package the application
mvn package
```

### Running the Application

```bash
# Run with Maven
mvn exec:java -Dexec.mainClass="pablo.tzeliks.view.Main"

# Or run the compiled class directly
java -cp target/classes pablo.tzeliks.view.Main
```

## 💡 Example Usage

The `Main` class demonstrates the system with three employees:

```java
// Create employees with different salaries and unions
Funcionario f1 = new Funcionario("Anacleto", 1800, "Bobinagem", Sindicato.METALURGICO);
Funcionario f2 = new Funcionario("Félix", 6000, "Torneiro Senior", Sindicato.METALURGICO);
Funcionario f3 = new Funcionario("Foba", 3000, "Provador de Roupa", Sindicato.TEXTIL);

// Calculate discounts
SalarioService salarioService = new SalarioService();
double salarioLiquido = salarioService.calcularSalarioLiquido(f1);
```

### Sample Output

```
1- Desconto: Desconto Imposto
2- Desconto: Desconto Plano de Saúde

------------------------------------------

Nome: Anacleto
Salario Base: R$1800.0
Cargo: Bobinagem
Sindicato: METALURGICO

1- | Regra: Faixa Baixa | Porcentagem: 15.00 por cento
2- | Regra: Sindicato Metalúrgico | Porcentagem: 2.50 por cento

------------------------------------------
```

### How the Calculation Works

For an employee with salary R$1,800 (Metalworkers Union):
1. **Tax Discount** (RegraFaixaBaixa applies): 15% = R$270
2. **Union Discount** (RegraMetalurgico applies): 2.5% = R$45
3. **Health Insurance**: R$200 fixed
4. **Net Salary**: R$1,800 - R$270 - R$45 - R$200 = R$1,285

## 👤 Author

**Pablo Tzeliks**

This project was created as an educational exercise to demonstrate practical application of SOLID principles in object-oriented design, with special emphasis on the Single Responsibility Principle and the Strategy Pattern.

### Learning Objectives

- Understanding how to break down complex business logic into focused, maintainable classes
- Applying the Strategy Pattern to handle varying algorithms
- Creating extensible code that's easy to modify and test
- Practicing clean code principles and separation of concerns

---

*This exercise demonstrates that well-designed code following SOLID principles leads to systems that are easier to understand, maintain, and extend.*
