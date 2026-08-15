# Q-Hop Management System

The Q-Hop (QueHop) Queue Handling for Office Procedures is a full-stack, cloud-connected queue management system designed to organize student and visitor queues in school administrative offices. The system automates queue number generation through a sleek self-service kiosk, secures administrative access with encrypted authentication, and synchronizes office workflows in real-time across an Admin Dashboard and a public TV Display.

---

## 🎯 General Objective
To develop a comprehensive Queueing Management System that organizes student queues, minimizes waiting time within school administrative offices, eliminates confusion, and provides a fast, secure, and highly efficient queuing experience.

---

## 💻 Tech Stack
* **Language:** Java (JDK 17+)
* **GUI Framework:** Java Swing & AWT (Custom Glassmorphism UI components)
* **IDE:** Apache NetBeans
* **Architecture:** Object-Oriented Component-Based UI (`CardLayout` transitions & Model-View-Controller pattern)
* **Database:** MongoDB Atlas (Cloud) with strict `$jsonSchema` validation and Compound Indexing
* **Security:** jBCrypt (Password Hashing) & `javax.crypto` (AES-128 Encryption for IDs at rest)

---

## ✨ Features
* **Cloud-Synced Multi-Device Ecosystem:** The Self-Service Kiosk, Admin Dashboard, and public TV Queue Display are all decoupled and sync in real-time across multiple computers via MongoDB Atlas.
* **Custom Application Launcher:** Modern startup window allowing users to choose their deployment mode (Admin, Kiosk, or TV Display).
* **Enterprise-Grade Security:**
  * **AES Encryption:** Student and Staff ID numbers are completely scrambled (AES-128 Base64) before being stored in the cloud.
  * **Brute-Force Protection:** Admin login auto-locks for 60 seconds after 5 failed attempts.
  * **Session Timeouts:** System automatically logs out inactive admin sessions after 30 seconds.
* **Automated Queue Generation:** Users generate a queue number slip directly from a responsive desktop-based kiosk with an interactive on-screen keypad.
* **Smart Anti-Spam Check:** Real-time database queries prevent users from generating duplicate tickets if they are already in the active queue.
* **Sleek Slate & Ice Aesthetic:** Features a modern hardware-accelerated UI utilizing deep slate-blue backgrounds, high-contrast ice-blue text, and custom rounded components.
* **Queue Flow Management:** Administrative staff can efficiently call the next oldest ticket, skip absent users, or complete transactions.
* **Q-Hop (Transfer Feature):** Staff can transfer a user's ticket to another office seamlessly, resetting their timestamp and safely pushing them into the new queue line.

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
The system tailors the queue generation process to three specific user categories:
* **Student / Parent:** Requires secure 10-digit ID verification (`YYYY-######`).
* **Staff / Employee:** Requires 10-digit ID verification.
* **Guest:** Expedited queue entry (Bypasses the keypad entirely for speed).

---

## 🚀 Current Development Status
- [x] Backend Object-Oriented Architecture (`QueueManager`, `Ticket` entities)
- [x] MongoDB Atlas Cloud Integration for full remote data persistence
- [x] Secure BCrypt Authentication & Initial Admin Cloud Setup
- [x] AES-128 Encryption for PII Data & Strict Schema Validation
- [x] Custom Application Launcher (`QHopSystem`)
- [x] Kiosk UI Shell, `CardLayout` Configuration, & Keypad Logic
- [x] Real-time TV Queue Display Component (`DisplayFrame`)
- [x] Hardware-accelerated transitions & Custom Alert Popups
- [x] Admin Dashboard UI (Live Stats, Active Queue Table, & Transaction Archive)
- [x] Compound Database Indexing for fast queue polling and anti-spam checks

---

## 🛠️ Running the Application

Because this system is cloud-connected and secure, it does not require a local database installation. However, it requires a secure encryption key to boot.

1. **Clone & Open in Apache NetBeans:**
   Open the project folder and make sure your Main Class is set to `com.mycompany.qhopsystem.QHopSystem`.

2. **Configure Security Environment Variables:**
   To pass the security check, right-click the project -> **Properties** -> **Run**. In the **VM Options** field, insert your secure 16-character encryption key and cloud URI:
   ```text
   -DAPP_KEY=Your16CharKey123 -DMONGO_URI="mongodb+srv://<user>:<pass>@your-cluster.mongodb.net/"
   ```
3. Build & Run:
   Clean and build the project (`Shift + F11`), then run (`F6`) to open the custom system launcher. The app will automatically sync with the cloud database.
