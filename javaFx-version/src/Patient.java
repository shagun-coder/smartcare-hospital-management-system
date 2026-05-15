public class Patient {

    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String disease;
    private String doctorAssigned;
    private double billAmount;

    public Patient(int patientId, String name,
            int age, String gender,
            String disease,
            String doctorAssigned,
            double billAmount) {

        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.doctorAssigned = doctorAssigned;
        this.billAmount = billAmount;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDisease() {
        return disease;
    }

    public String getDoctorAssigned() {
        return doctorAssigned;
    }

    public double getBillAmount() {
        return billAmount;
    }
}