public class Patient {

    int patientId;
    String name;
    int age;
    String gender;
    String disease;
    String doctorAssigned;
    double billAmount;

    public Patient(int patientId, String name, int age,
            String gender, String disease,
            String doctorAssigned, double billAmount) {

        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.doctorAssigned = doctorAssigned;
        this.billAmount = billAmount;
    }

    @Override
    public String toString() {
        return patientId + " | " + name + " | " + age + " | "
                + gender + " | " + disease + " | "
                + doctorAssigned + " | ₹" + billAmount;
    }
}