public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String treatmentDetails;
    private String completedOn;

    public TreatmentRecord(int patientId, String patientName, String treatmentDetails, String completedOn) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentDetails = treatmentDetails;
        this.completedOn = completedOn;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public String getCompletedOn() {
        return completedOn;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
               " | Name: " + patientName +
               " | Treatment: " + treatmentDetails +
               " | Completed: " + completedOn;
    }
}
