# Q-Hop Management System

[![Java Version](https://img.shields.io/badge/Java-JDK_17%2B-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Database](https://img.shields.io/badge/Database-MongoDB_Atlas-47A248?style=flat-square&logo=mongodb&logoColor=white)](https://www.mongodb.com/cloud/atlas)
[![Build Tool](https://img.shields.io/badge/Build-Apache_Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![UI Style](https://img.shields.io/badge/UI_Aesthetic-Glassmorphism_Slate_%26_Ice-38BDF8?style=flat-square)](https://github.com/cymriccc/qhop-management-system)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat-square)](LICENSE)

**Q-Hop (QueHop) Queue Handling for Office Procedures** is a enterprise-grade, cloud-connected queue management system designed to organize student and visitor traffic in academic administrative offices (specifically tailored for high-volume environments like National University Mall of Asia). 

The system automates queue slip generation through a self-service kiosk, secures administrative workflows via jBCrypt and AES-128 encryption, and synchronizes queue state in real time across decoupled Kiosk, Admin Dashboard, and Public TV Display clients.

---

## 🎯 General Objective

To provide a comprehensive, fault-tolerant Queuing Management System that organizes student traffic, minimizes waiting times across administrative offices, eliminates queue confusion, and delivers a fast, secure, and data-driven queuing experience.

---

## 💻 Tech Stack & Architecture

- **Language:** Java (JDK 17+)
- **GUI Framework:** Java Swing & AWT (Hardware-accelerated Glassmorphism UI components & `CardLayout` transitions)
- **Database Layer:** MongoDB Atlas (Cloud) with strict `$jsonSchema` document validation and compound indexes
- **Security & Cryptography:** `jBCrypt` password hashing, `javax.crypto` AES-128 Base64 payload encryption, & environment variable secrets management
- **Build System:** Apache Maven (`maven-shade-plugin` for standalone Fat/Uber JAR compilation)
- **Architecture Pattern:** Event-Driven Component Architecture with Model-View-Controller (MVC) decoupling

---

## ✨ Enterprise Features

- **Cloud-Synced Multi-Device Ecosystem:** Decoupled instances of the Self-Service Kiosk, Admin Dashboard, and Public TV Queue Display communicate asynchronously over MongoDB Atlas.
- **Master Admin Global Monitoring:** High-level administrative view bypassing department routing to provide real-time cross-office metrics and ticket lifecycle tracking.
- **Action Audit Logging:** Automated, non-volatile file I/O logging (`QHop_Audit_Log.txt`) recording timestamped administrative events for compliance and security auditing.
- **Real-Time Table Filtering:** Injected `TableRowSorter` enabling live, case-insensitive searching by ticket number, student ID, or status.
- **Database Polling Optimization:** Throttled active queue state fetching on the `DisplayFrame` (3000ms intervals) to drastically reduce database I/O overhead and prevent Atlas rate limits.
- **Enterprise Security Model:**
  * **Environment Variable Secret Injection:** Completely eliminates hardcoded connection strings (`MONGO_URI`) and cryptographic keys (`APP_KEY`).
  * **AES Payload Encryption:** Encrypts sensitive student and staff ID records (AES-128 Base64) prior to cloud persistence.
  * **Brute-Force Lockout:** Automatically locks login forms for 60 seconds after 5 consecutive failed authentication attempts.
  * **Session Timeout Handling:** Terminates inactive administrative sessions after 30 seconds of non-interaction.
- **Smart Anti-Spam Check & Debouncing:** Compound indexing and UI state locks prevent users from generating duplicate active queue tickets.
- **Q-Hop Transfer Protocol:** Enables staff to seamlessly transfer active tickets between departments while resetting timestamps and preserving audit trails.

---

## 🏢 Supported Offices & Dynamic Services

The system dynamically adapts service options based on user classification:

### 1. Registrar
- **Students:** Transcript of Records (TOR), Cert. of Enrollment (COE) / Good Moral, Diploma Application, Cross-Enrollment.
- **Staff:** Grade sheet submission, Service records, Student clearance approvals.

### 2. Admission
- **Students:** New Admission Inquiry, Entrance Exam Registration, Credentials Submission, Transferee Evaluation.
- **Staff:** Applicant evaluation, Entrance requirements routing, Employee-dependent discounts.

### 3. Treasury
- **Students:** Tuition Assessment/Payment, Statement of Account (SOA), Refund Processing, Scholarship Validation.
- **Staff:** Expense reimbursement, Budget/Cash advances, Payroll inquiries.

### 4. General Inquiry
- General campus routing, information desk assistance, and administrative support.

---

## 👥 User Categories

- **Student / Parent:** Validated via secure 10-digit ID pattern (`YYYY-######`).
- **Staff / Employee:** Validated via 10-digit employee ID credentials.
- **Guest:** Fast-track expedited queue slip generation (bypasses keypad entry for rapid ticketing).

---

## 📦 Deployment & Execution Guide

The application compiles into a single, self-contained **Fat/Uber JAR** containing all runtime dependencies. Target deployment machines require no local database software or external library installations.

### Prerequisites

- **Java Runtime Environment:** JRE/JDK 17 or higher installed on each node.
- **Network Access:** Unrestricted outbound connection to MongoDB Atlas.

---

### Package Structure

```text
QHop_Release/
├── QHopSystem-1.0-SNAPSHOT.jar    # Compiled standalone Fat JAR
├── launch.bat                      # One-click launch script for Windows
├── launch.sh                       # One-click launch script for Linux
├── launch.command                  # One-click launch script for macOS
└── README.md                       # System documentation
```

### 1. Windows Launch Setup

Ensure `launch.bat` resides alongside `QHopSystem-1.0-SNAPSHOT.jar` with the following configuration:

```bat
@echo off
set MONGO_URI=mongodb+srv://<username>:<password>@<cluster-url>
start javaw -DAPP_KEY=1234567890ABCDEF -jar QHopSystem-1.0-SNAPSHOT.jar
exit
```

### 2. Linux Launch Setup

Ensure `launch.sh` contains:

```bash
#!/bin/bash
export MONGO_URI="mongodb+srv://<username>:<password>@<cluster-url>"
java -DAPP_KEY=1234567890ABCDEF -jar QHopSystem-1.0-SNAPSHOT.jar
```

Grant executable permissions once via terminal:
```bash
chmod +x launch.sh
./launch.sh
```

### 3. macOS Launch Setup

Ensure `launch.command` contains:

```bash
#!/bin/bash
cd "$(dirname "$0")"
export MONGO_URI="mongodb+srv://<username>:<password>@<cluster-url>"
java -DAPP_KEY=1234567890ABCDEF -jar QHopSystem-1.0-SNAPSHOT.jar
```

Grant executable permissions via terminal:
```bash
chmod +x launch.command
```

---

### 4. Compiling from Source (Apache NetBeans / Maven)

1. Clone and open the project directory in **Apache NetBeans**.
2. Verify primary main class entry point: `com.mycompany.qhopsystem.QHopSystem`.
3. Configure VM Options under **Project Properties -> Run**:
   ```text
   -DAPP_KEY=1234567890ABCDEF
   ```
4. Ensure your local OS environment variables contain `MONGO_URI`.
5. Run **Clean and Build** (`Shift + F11`) to generate the Fat JAR inside the `target/` directory.

---

## 📄 License

Distributed under the **MIT License**. See `LICENSE` for details.

---

**Author**: [@cymriccc](https://github.com/cymriccc)
