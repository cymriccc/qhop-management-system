# Q-Hop Management System

The Q-Hop (QueHop) Queue Handling for Office Procedures is a full-stack, cloud-connected queue management system designed to organize student and visitor queues in administrative offices, specifically tailored for environments like National University Mall of Asia. The system automates queue number generation through a sleek self-service kiosk, secures administrative access with encrypted authentication, and synchronizes office workflows in real-time across an Admin Dashboard and a public TV Display.

---

## 🎯 General Objective
To develop a comprehensive Queueing Management System that organizes student queues, minimizes waiting time within school administrative offices, eliminates confusion, and provides a fast, secure, and highly efficient queuing experience.

---

## 💻 Tech Stack
* **Language:** Java (JDK 17+)
* **GUI Framework:** Java Swing & AWT (Custom Glassmorphism UI components)
* **IDE / Build Tool:** Apache NetBeans & Apache Maven (`maven-shade-plugin` for standalone Uber/Fat JARs)
* **Architecture:** Object-Oriented Component-Based UI (`CardLayout` transitions & Model-View-Controller pattern)
* **Database:** MongoDB Atlas (Cloud) with strict `$jsonSchema` validation and Compound Indexing
* **Security:** jBCrypt (Password Hashing), `javax.crypto` (AES-128 Encryption), & Environment Variable Secrets Management

---

## ✨ Features
* **Master Admin Global Monitoring:** Bypasses department routing for a read-only global dashboard tracking live tickets across all offices.
* **Action Audit Logging:** Automated local file I/O appending timestamped admin actions (`QHop_Audit_Log.txt`) for strict accountability tracking.
* **Real-Time Table Filtering:** Injected a `TableRowSorter` for live, case-insensitive searching by ticket number, student ID, or status in the transaction history.
* **Database Polling Optimization:** Throttled `DisplayFrame` active queue fetching to 3000ms, heavily reducing MongoDB Atlas read operations to prevent rate-limiting and maximize efficiency.
* **Cloud-Synced Multi-Device Ecosystem:** The Self-Service Kiosk, Admin Dashboard, and public TV Queue Display are decoupled and synchronize in real-time across multiple computers via MongoDB Atlas.
* **Enterprise-Grade Security:**
  * **Environment-Based Secrets:** Database URIs and application keys are injected via system environment variables, completely preventing hardcoded credential leaks.
  * **AES Encryption:** Student and Staff ID numbers are encrypted (AES-128 Base64) before being stored in the cloud.
  * **Brute-Force Protection:** Admin login auto-locks for 60 seconds after 5 failed attempts.
  * **Session Timeouts:** System automatically logs out inactive admin sessions after 30 seconds.
* **Automated Queue Generation:** Users generate a queue number slip directly from a responsive desktop-based kiosk with an interactive on-screen keypad.
* **Smart Anti-Spam Check & UI Debouncing:** UI state locks and real-time MongoDB compound index queries prevent users from generating duplicate tickets if they are already in the active queue.
* **Timestamp Integration:** Parsed `LocalDateTime` from MongoDB to display exact ticket generation times (e.g., `hh:mm a`).
* **Sleek Slate & Ice Aesthetic:** Hardware-accelerated UI utilizing deep slate-blue backgrounds, high-contrast ice-blue text, and custom rounded components.
* **Queue Flow Management:** Administrative staff can call the next oldest ticket, skip absent users, or complete transactions.
* **Q-Hop (Transfer Feature):** Staff can transfer a user's ticket to another office seamlessly, resetting their timestamp and safely moving them into the new queue line.

---

## 🏢 Supported Offices & Dynamic Services
The system provides dynamic service options based on the user's category:

### 1. Registrar
* **Students:** Transcript of Records (TOR), Cert. of Enrollment (COE) / Good Moral, Diploma Application, Cross-Enrollment.
* **Staff:** Grade sheet submission, Service records, Student clearance approvals.

### 2. Admission
* **Students:** New Admission Inquiry, Entrance Exam Registration, Credentials Submission, Transferee Evaluation.
* **Staff:** Applicant evaluation, Entrance requirements routing, Employee-dependent discounts.

### 3. Treasury
* **Students:** Tuition Assessment/Payment, Statement of Account (SOA), Refund Processing, Scholarship Validation.
* **Staff:** Expense reimbursement, Budget/Cash advances, Payroll inquiries.

### 4. General Inquiry
* General campus routing, information, and administrative support.

---

## 👥 System Users
* **Student / Parent:** Requires secure 10-digit ID verification (`YYYY-######`).
* **Staff / Employee:** Requires 10-digit ID verification.
* **Guest:** Expedited queue entry (Bypasses keypad entry for rapid ticketing).

---

## 📦 Deployment & Execution Guide

The application is packaged into a self-contained **Fat/Uber JAR** containing all runtime dependencies. No local database installation or external dependency folders are required on target machines. 

### Prerequisites
* **Java Runtime:** JRE/JDK 17 or higher installed on each deployment machine.
* **Network:** Active internet connection to communicate with MongoDB Atlas.

---

### Deployment Package Structure
```text
QHop_Release/
├── QHopSystem-1.0-SNAPSHOT.jar
├── launch.bat                  # One-click launcher for Windows
├── launch.sh                   # One-click launcher for Linux
├── launch.command              # One-click launcher for MacOS
└── README.md
```
### 1. Windows Deployment

1. Place `QHopSystem-1.0-SNAPSHOT.jar` and `launch.bat` in the same directory.
2. Ensure `launch.bat` contains the following execution command, injecting your specific database credentials:
   ```bat
   @echo off
   set MONGO_URI=mongodb+srv://<username>:<password>@<cluster-url>
   start javaw -DAPP_KEY=1234567890ABCDEF -jar QHopSystem-1.0-SNAPSHOT.jar
   exit
   ```
3. Double-click `launch.bat` to run.

---

### 2. Linux Deployment

1. Place `QHopSystem-1.0-SNAPSHOT.jar` and `launch.sh` in the same directory.
2. Ensure `launch.sh` contains:

    ```bash
    #!/bin/bash
    export MONGO_URI="mongodb+srv://<username>:<password>@<cluster-url>"
    java -DAPP_KEY=1234567890ABCDEF -jar QHopSystem-1.0-SNAPSHOT.jar
    ```

3. Grant execute permissions via terminal (run once):

    ```bash
    chmod +x launch.sh
    ```

4. Run the script by double-clicking `launch.sh` or executing `./launch.sh` in the terminal.

---

### 3. macOS Deployment

1. Place `QHopSystem-1.0-SNAPSHOT.jar` and `launch.command` in the same directory.
2. Ensure `launch.command` contains the following:

    ```bash
    #!/bin/bash
    cd "$(dirname "$0")"
    export MONGO_URI="mongodb+srv://<username>:<password>@<cluster-url>"
    java -DAPP_KEY=1234567890ABCDEF -jar QHopSystem-1.0-SNAPSHOT.jar
    ```
    *(Note: The `cd "$(dirname "$0")"` line ensures the script runs in the correct folder when double-clicked).*

3. Grant execute permissions via terminal (run once):
   * Open the **Terminal** app.
   * Type `chmod +x ` (leave a space at the end).
   * Drag and drop the `launch.command` file into the terminal window and hit **Enter**.

4. Double-click `launch.command` to run the application. (If macOS warns about an unidentified developer, right-click the file and select **Open**).

---

### 4. Building from Source (Apache NetBeans)

1. Clone or open the project directory in **Apache NetBeans**.
2. Ensure the main class is set to `com.mycompany.qhopsystem.QHopSystem`.
3. Configure VM Options for IDE execution:
   * Right-click Project -> **Properties** -> **Run**.
   * Set **VM Options**: `-DAPP_KEY=1234567890ABCDEF`
   * Ensure your local system environment variables contain `MONGO_URI`.
4. Build the standalone executable:
   * Press **Shift + F11** (**Clean and Build Project**).
   * Retrieve the generated `QHopSystem-1.0-SNAPSHOT.jar` from the `target/` folder.

---

## 🎓 Development Team
Developed as a Capstone IT Project by cymric :)
