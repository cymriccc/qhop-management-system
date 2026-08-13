# Q-Hop Management System

The Q-Hop (QueHop) Queue Handling for Office Procedures is a full-stack, desktop-based queue management system designed to organize student and visitor queues in school administrative offices. The system automates queue number generation through a sleek kiosk, secures administrative access with encrypted authentication, and provides staff with a real-time dashboard to manage office workflows.

---

## 🎯 General Objective
To develop a comprehensive Queueing Management System that organizes student queues, minimizes waiting time within school administrative offices, eliminates confusion, and provides a fast, secure, and highly efficient queuing experience.

---

## 💻 Tech Stack
* **Language:** Java (JDK 17+)
* **GUI Framework:** Java Swing & AWT (Custom Glassmorphism UI components)
* **IDE:** Apache NetBeans
* **Architecture:** Object-Oriented Component-Based UI (`CardLayout` transitions & Model-View-Controller pattern)
* **Database:** MongoDB (Local instance with Compass visual support)
* **Security:** jBCrypt for secure password hashing

---

## ✨ Features
* **Custom Application Launcher:** Modern startup window allowing users to choose between launching the Admin Dashboard or Self-Service Kiosk.
* **Secure Admin Authentication:** Role-based access control backed by MongoDB and BCrypt password encryption.
* **Automated Queue Generation:** Users can generate a queue number slip directly from a responsive desktop-based kiosk.
* **Sleek Slate & Ice Aesthetic:** Features a modern hardware-accelerated UI utilizing deep slate-blue backgrounds, high-contrast ice-blue text, and custom rounded components.
* **Dynamic Workflows:** Smart navigation logic (e.g., Guests automatically bypass ID keypad entry to speed up queue times).
* **Queue Flow Management:** Administrative staff can manage the queue flow efficiently, calling the next oldest ticket in the waiting list, skipping, or completing transactions.
* **Q-Hop (Transfer Feature):** Staff can transfer a user's ticket to another office seamlessly, resetting their timestamp and placing them at the back of the new queue.
* **Real-time Tracking & History:** Automatically polls MongoDB every two seconds to update live dashboard metrics, active queue tables, and archived transaction logs.

---

## 🏢 Supported Offices & Services
The system manages queues for the following administrative categories:

### 1. Registrar
* Requesting documents and academic records.

### 2. Admission
* Student admission and enrollment processing.
* Submission of admission requirements.

### 3. Treasury
* Tuition fee payment and processing.
* Other school-related transactions.

### 4. General Inquiry
* General campus questions, payment inquiries, and routing.

---

## 👥 System Users
The system tailors the queue generation process to three specific user categories:
* **Student / Parent:** Requires secure 10-digit ID verification (`YYYY-######`).
* **Staff / Employee:** Requires 10-digit ID verification.
* **Guest:** Expedited queue entry (No ID required).

---

## 🚀 Current Development Status
- [x] Backend Object-Oriented Architecture (`QueueManager`, `Ticket` entities)
- [x] MongoDB Integration for full data persistence
- [x] Secure BCrypt Authentication & Default Admin Seed
- [x] Custom Application Launcher (`QHopSystem`)
- [x] Kiosk UI Shell & `CardLayout` Configuration
- [x] Service & User Type Selection Screens
- [x] Hardware-accelerated transitions & Custom Alert Popups
- [x] Keypad logic and Guest smart-skip feature
- [x] Confirmation & Ticket Generation Screen
- [x] Admin Dashboard UI (Live Stats, Active Queue Table, & Transaction History Archive)

---

## 🛠️ Running the Application

1. **Start MongoDB:**
   Ensure your local MongoDB service is running:
   ```bash
   sudo systemctl start mongod
   ```
2. **Open in Apache NetBeans:**
   Open the project folder and make sure your Main Class is set to `com.mycompany.qhopsystem.QHopSystem`.

3. **Build & Run:**
   Clean and build the project (Shift + F11), then run (F6) to open the custom system launcher.
