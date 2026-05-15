import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class HospitalManagementSystem {

    static ArrayList<Patient> patients = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadFromFile();

        int choice;

        do {

            System.out.println("\n======================================");
            System.out.println(" SMARTCARE HOSPITAL MANAGEMENT SYSTEM ");
            System.out.println("======================================");

            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Search Patient");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addPatient();
                    break;

                case 2:
                    viewPatients();
                    break;

                case 3:
                    searchPatient();
                    break;

                case 4:
                    updatePatient();
                    break;

                case 5:
                    deletePatient();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);
    }

    // ADD PATIENT
    static void addPatient() {

        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        // Duplicate ID Check
        for (Patient p : patients) {

            if (p.patientId == id) {

                System.out.println("Patient ID already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();

        System.out.print("Enter Disease: ");
        String disease = sc.nextLine();

        System.out.print("Enter Doctor Assigned: ");
        String doctor = sc.nextLine();

        System.out.print("Enter Bill Amount: ");
        double bill = sc.nextDouble();

        Patient patient = new Patient(
                id,
                name,
                age,
                gender,
                disease,
                doctor,
                bill);

        patients.add(patient);

        saveToFile();

        System.out.println("Patient Added Successfully!");
    }

    // VIEW PATIENTS
    static void viewPatients() {

        if (patients.isEmpty()) {

            System.out.println("No Patient Records Found!");
            return;
        }

        System.out.println("\n========== PATIENT RECORDS ==========");

        for (Patient p : patients) {

            System.out.println(p);
        }
    }

    // SEARCH PATIENT
    static void searchPatient() {

        System.out.print("Enter Patient ID to Search: ");
        int id = sc.nextInt();

        boolean found = false;

        for (Patient p : patients) {

            if (p.patientId == id) {

                System.out.println("\nPatient Found:");
                System.out.println(p);

                found = true;
                break;
            }
        }

        if (!found) {

            System.out.println("Patient Not Found!");
        }
    }

    // UPDATE PATIENT
    static void updatePatient() {

        System.out.print("Enter Patient ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean found = false;

        for (Patient p : patients) {

            if (p.patientId == id) {

                System.out.print("Enter New Name: ");
                p.name = sc.nextLine();

                System.out.print("Enter New Age: ");
                p.age = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter New Gender: ");
                p.gender = sc.nextLine();

                System.out.print("Enter New Disease: ");
                p.disease = sc.nextLine();

                System.out.print("Enter New Doctor Assigned: ");
                p.doctorAssigned = sc.nextLine();

                System.out.print("Enter New Bill Amount: ");
                p.billAmount = sc.nextDouble();

                saveToFile();

                System.out.println("Patient Updated Successfully!");

                found = true;
                break;
            }
        }

        if (!found) {

            System.out.println("Patient Not Found!");
        }
    }

    // DELETE PATIENT
    static void deletePatient() {

        System.out.print("Enter Patient ID to Delete: ");
        int id = sc.nextInt();

        boolean found = false;

        for (Patient p : patients) {

            if (p.patientId == id) {

                patients.remove(p);

                saveToFile();

                System.out.println("Patient Deleted Successfully!");

                found = true;
                break;
            }
        }

        if (!found) {

            System.out.println("Patient Not Found!");
        }
    }

    // SAVE DATA TO FILE
    static void saveToFile() {

        try {

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("patients.txt"));

            for (Patient p : patients) {

                writer.write(
                        p.patientId + "," +
                                p.name + "," +
                                p.age + "," +
                                p.gender + "," +
                                p.disease + "," +
                                p.doctorAssigned + "," +
                                p.billAmount);

                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {

            System.out.println("Error Saving File!");
        }
    }

    // LOAD DATA FROM FILE
    static void loadFromFile() {

        try {

            File file = new File("patients.txt");

            if (!file.exists()) {

                return;
            }

            BufferedReader reader = new BufferedReader(
                    new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                patients.add(
                        new Patient(
                                Integer.parseInt(data[0]),
                                data[1],
                                Integer.parseInt(data[2]),
                                data[3],
                                data[4],
                                data[5],
                                Double.parseDouble(data[6])));
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error Loading File!");
        }
    }
}