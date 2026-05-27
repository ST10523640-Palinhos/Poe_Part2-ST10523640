# QuickChat - POE Part 2 (Console Messaging System)

QuickChat is a Java-based console application designed to simulate a secure mobile messaging platform. It allows users to capture recipient cell numbers with strict regional formatting, validate text inputs, generate unique message identifiers, and track session messaging metrics. The application also supports local data persistence by exporting stored messages into structured JSON files.

##  Features
* Interactive Menu System:** Easily navigate through sending messages, viewing session history, or quitting the application.
* Cell Number Validation:** Automatically checks that recipient numbers comply with international formats (must start with `+27` and be exactly 12 characters long).
* Automated Message Identification:** Generates a randomized, secure 10-digit unique ID for every fresh message object.
* Custom Security Hashing:** Computes a custom tracking token framework for sent messages combining the ID prefix, sequential sequence numbers, and specialized string compression (extracting and capitalizing the first and last words of the text body).
* JSON Exporting (Data Persistence):** Safely logs drafted messages to a local `messages.json` append-mode tracking ledger for future transmissions.

---

##  Project Architecture

The application contains two core components:

1. **`POEp2.java` (Main Class):** Handles user interactions, manages the console scanner streams, directs menu execution loops, and maintains the active session counters.
2. **`Message.java` (Business Logic Class):** Encapsulates individual message data fields, provides utility validation workflows (`checkRecipientCell`, `checkMessageLength`), runs string mutation hashing algorithms, and manages file output streams (`FileWriter`).

---

##  Test Data Framework

The system has been heavily validated using the following mandated institutional mock datasets:

| Dataset Instance | Recipient / Target | Message Text Content | Expected Processing Status |
| :--- | :--- | :--- | :--- |
| **Message 1** | `+27834557896` | "Did you get the cake?" | **Sent** (Passed validation) |
| **Message 2** | `+27838884567` | "Where are you? You are late! I have asked you to be on time." | **Stored** (Saved to JSON ledger) |
| **Message 3** | `+27834484567` | "Yohoooo, I am at your gate." | **Disregard** (Dropped safely) |
| **Message 4** | `0838884567` | "It is dinner time !" | **Invalid Format** (Flagged by security filter) |
| **Message 5** | `+27838884567` | "Ok, I am leaving without you." | **Stored** (Saved to JSON ledger) |

---

##  How To Run the Application

### Prerequisites
* Java Development Kit (JDK) 8 or higher installed on your local machine.
* An IDE such as **NetBeans**, Eclipse, or IntelliJ IDEA (Optional, but recommended).

### Running via Terminal
1. Clone this repository to your computer:
   ```bash
   git clone [https://github.com/YOUR_USERNAME/YOUR_REPOSITORY_NAME.git](https://github.com/YOUR_USERNAME/YOUR_REPOSITORY_NAME.git)
