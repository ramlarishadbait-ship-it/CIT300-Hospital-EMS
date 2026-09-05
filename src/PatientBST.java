public class PatientBST {

    private class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node root;

    public PatientBST() {
        root = null;
    }

    // ---------------- INSERT ----------------

    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) {
            return new Node(patient);
        }
        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        } else {
            System.out.println("A patient with ID " + patient.getPatientId() + " already exists. Insert skipped.");
        }
        return node;
    }

    // ---------------- SEARCH ----------------

    public Patient search(int patientId) {
        return searchRec(root, patientId);
    }

    private Patient searchRec(Node node, int patientId) {
        if (node == null) {
            return null;
        }
        if (patientId == node.patient.getPatientId()) {
            return node.patient;
        }
        return patientId < node.patient.getPatientId()
                ? searchRec(node.left, patientId)
                : searchRec(node.right, patientId);
    }

    // ---------------- DELETE ----------------

    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false;
        }
        root = deleteRec(root, patientId);
        return true;
    }

    private Node deleteRec(Node node, int patientId) {
        if (node == null) {
            return null;
        }

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ---------------- IN-ORDER TRAVERSAL ----------------

    public void displayInorder() {
        if (root == null) {
            System.out.println("   No patients registered yet.");
            return;
        }
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println("   " + node.patient);
            inorderRec(node.right);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}
