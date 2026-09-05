import java.util.Scanner;

public class HospitalManagementSystem {

    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();
    private static Scanner sc = new Scanner(System.in);

    private static int visitCounter = 1000;

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1: registerPatient(); break;
                case 2: searchPatient(); break;
                case 3: deletePatient(); break;
                case 4: displayAllPatients(); break;
                case 5: addToEmergencyQueue(); break;
                case 6: treatNextPatient(); break;
                case 7: emergencyQueue.displayQueue(); break;
                case 8: treatmentStack.displayStack(); break;
                case 9: undoLastTreatment(); break;
                case 10: addVisitHistory(); break;
                case 11: removeVisitHistory(); break;
                case 12: searchVisitHistory(); break;
                case 13: displayVisitHistory(); break;
                case 0: System.out.println("Exiting system. Goodbye!"); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n===================================================");
        System.out.println(" MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("===================================================");
        System.out.println(" --- Patient Records (Binary Search Tree) ---");
        System.out.println(" 1. Register New Patient");
        System.out.println(" 2. Search Patient by ID");
        System.out.println(" 3. Delete Patient");
        System.out.println(" 4. Display All Patients (In-order)");
        System.out.println(" --- Emergency Queue (Queue) ---");
        System.out.println(" 5. Add Patient to Emergency Queue");
        System.out.println(" 6. Treat Next Patient (Dequeue)");
        System.out.println(" 7. Display Waiting Queue");
        System.out.println(" --- Treatment History (Stack) ---");
        System.out.println(" 8. Display Treatment History");
        System.out.println(" 9. Undo Last Treatment Record (Pop)");
        System.out.println(" --- Patient Visit History (Linked List) ---");
        System.out.println(" 10. Add Visit Record to Patient");
        System.out.println(" 11. Remove Visit Record from Patient");
        System.out.println(" 12. Search Visit Record");
        System.out.println(" 13. Display Patient's Visit History");
        System.out.println(" 0. Exit");
        System.out.println("===================================================");
    }

    // ---------------- BST OPERATIONS ----------------

    private static void registerPatient() {
        int id = getIntInput("Enter Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }
        String name = getStringInput("Enter Patient Name: ");
        int age = getIntInput("Enter Age: ");
        String contact = getStringInput("Enter Contact Number: ");
        String condition = getStringInput("Enter Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        int id = getIntInput("Enter Patient ID to search: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("Found: " + patient);
        }
    }

    private static void deletePatient() {
        int id = getIntInput("Enter Patient ID to delete: ");
        boolean deleted = patientBST.delete(id);
        System.out.println(deleted ? "Patient deleted successfully." : "No patient found with ID " + id);
    }

    private static void displayAllPatients() {
        System.out.println("Patients (ascending order of Patient ID):");
        patientBST.displayInorder();
    }

    // ---------------- QUEUE OPERATIONS ----------------

    private static void addToEmergencyQueue() {
        int id = getIntInput("Enter Patient ID to add to emergency queue: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No such patient. Please register the patient first (Option 1).");
            return;
        }
        emergencyQueue.enqueue(patient);
        System.out.println("Patient added to emergency queue.");
    }

    private static void treatNextPatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) {
            return; 
        }
        System.out.println("Now treating: " + patient);

        String treatmentDetails = getStringInput("Enter treatment details: ");
        String completedOn = getStringInput("Enter completion date/time (e.g. 2026-09-04 14:30): ");

        TreatmentRecord record = new TreatmentRecord(
                patient.getPatientId(), patient.getName(), treatmentDetails, completedOn);
        treatmentStack.push(record);

        // Automatically log this as a visit in the patient's history too
        visitCounter++;
        Visit visit = new Visit(visitCounter, completedOn, "Attending Doctor",
                patient.getMedicalCondition(), treatmentDetails);
        patient.getVisitHistory().addVisit(visit);

        System.out.println("Treatment completed and recorded.");
    }

    // ---------------- STACK OPERATIONS ----------------

    private static void undoLastTreatment() {
        TreatmentRecord record = treatmentStack.pop();
        if (record != null) {
            System.out.println("Removed most recent treatment record: " + record);
        }
    }

    // ---------------- LINKED LIST OPERATIONS ----------------

    private static void addVisitHistory() {
        int id = getIntInput("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No such patient found.");
            return;
        }
        visitCounter++;
        String date = getStringInput("Enter Visit Date: ");
        String doctor = getStringInput("Enter Doctor Name: ");
        String diagnosis = getStringInput("Enter Diagnosis: ");
        String treatment = getStringInput("Enter Treatment: ");

        Visit visit = new Visit(visitCounter, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit record added with Visit ID " + visitCounter);
    }

    private static void removeVisitHistory() {
        int id = getIntInput("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No such patient found.");
            return;
        }
        int visitId = getIntInput("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit record removed." : "Visit ID not found.");
    }

    private static void searchVisitHistory() {
        int id = getIntInput("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No such patient found.");
            return;
        }
        int visitId = getIntInput("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(visit != null ? "Found: " + visit : "Visit ID not found.");
    }

    private static void displayVisitHistory() {
        int id = getIntInput("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No such patient found.");
            return;
        }
        System.out.println("Visit history for " + patient.getName() + ":");
        patient.getVisitHistory().displayVisits();
    }

    // ---------------- INPUT HELPERS ----------------

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine(); 
        return value;
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
