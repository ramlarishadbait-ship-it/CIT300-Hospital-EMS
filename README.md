# CIT300-Hospital-EMS
# Mini Hospital Emergency Management System

**Course:** CIT300 - Data Structures and Algorithms
**Assignment:** Individual Mid Assignment

## Overview
A console-based Java application that simulates a hospital's emergency
management workflow: patient registration, emergency queueing, treatment
completion, and visit history tracking. Built using four core data
structures, each implemented from scratch (no built-in `java.util`
collection classes were used for the core logic).

## Data Structures Used

| Requirement | Data Structure | File | Purpose |
|---|---|---|---|
| Patient Records | Binary Search Tree | `PatientBST.java` | Store & look up patients by ID in O(log n) average time |
| Emergency Queue | Queue (linked-node, FIFO) | `EmergencyQueue.java` | Manage patients waiting for treatment |
| Treatment History | Stack (linked-node, LIFO) | `TreatmentStack.java` | Track completed treatments, most recent first |
| Patient Visit History | Singly Linked List | `VisitLinkedList.java` | Store each patient's past visits |

## Project Structure
```
HospitalEMS/
├── src/
│   ├── Patient.java              # Patient data model
│   ├── Visit.java                # Visit data model
│   ├── VisitLinkedList.java      # Singly linked list (per-patient visit history)
│   ├── PatientBST.java           # Binary Search Tree of patients
│   ├── EmergencyQueue.java       # FIFO queue for the emergency unit
│   ├── TreatmentRecord.java      # Completed treatment data model
│   ├── TreatmentStack.java       # LIFO stack of treatment records
│   └── HospitalManagementSystem.java  # Main class / console menu
└── README.md
```

## How to Compile & Run
```bash
cd src
javac *.java
java HospitalManagementSystem
```

## Features / Menu
1. Register New Patient (BST insert)
2. Search Patient by ID (BST search)
3. Delete Patient (BST delete)
4. Display All Patients — ascending Patient ID (BST in-order traversal)
5. Add Patient to Emergency Queue (enqueue)
6. Treat Next Patient (dequeue + pushes a treatment record + logs a visit)
7. Display Waiting Queue
8. Display Treatment History (stack, most recent first)
9. Undo Last Treatment Record (pop)
10. Add Visit Record to a Patient
11. Remove Visit Record from a Patient
12. Search a Visit Record
13. Display a Patient's Visit History
0. Exit

## Design Notes
- Each `Patient` object owns its own `VisitLinkedList`, so visit history is
  naturally scoped per patient rather than kept in one global list.
- `EmergencyQueue` and `TreatmentStack` store references to the same
  `Patient`/`TreatmentRecord` objects used elsewhere, avoiding duplication.
- All four structures are implemented with custom node classes (no
  `java.util.LinkedList`, `Stack`, `Queue`, or `TreeMap`) to demonstrate the
  underlying mechanics required by the assignment.
- Empty-structure edge cases (empty queue dequeue, empty stack pop, empty
  BST/list search) are all handled with user-friendly messages instead of
  exceptions or crashes.

## Author
M.R.F Ramla - 23DA2-0925
