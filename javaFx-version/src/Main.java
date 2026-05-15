import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    TableView<Patient> table = new TableView<>();

    ObservableList<Patient> patientList =
            FXCollections.observableArrayList();

    TextField idField = new TextField();
    TextField nameField = new TextField();
    TextField ageField = new TextField();
    TextField genderField = new TextField();
    TextField diseaseField = new TextField();
    TextField doctorField = new TextField();
    TextField billField = new TextField();

    @Override
    public void start(Stage stage) {

        // ================= TITLE =================

        Label title =
                new Label("SmartCare Hospital Management System");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1e293b;"
        );

        // ================= CARDS =================

        VBox totalPatientsCard = createCard(
                "Total Patients",
                "0"
        );

        VBox revenueCard = createCard(
                "Revenue",
                "₹0"
        );

        VBox doctorsCard = createCard(
                "Doctors",
                "12"
        );

        HBox cardsBox =
                new HBox(20,
                        totalPatientsCard,
                        revenueCard,
                        doctorsCard);

        // ================= FORM =================

        GridPane form = new GridPane();

        form.setHgap(15);
        form.setVgap(15);
        form.setPadding(new Insets(20));

        idField.setPromptText("Patient ID");
        nameField.setPromptText("Patient Name");
        ageField.setPromptText("Age");
        genderField.setPromptText("Gender");
        diseaseField.setPromptText("Disease");
        doctorField.setPromptText("Doctor");
        billField.setPromptText("Bill Amount");

        form.add(new Label("Patient ID"), 0, 0);
        form.add(idField, 1, 0);

        form.add(new Label("Name"), 2, 0);
        form.add(nameField, 3, 0);

        form.add(new Label("Age"), 0, 1);
        form.add(ageField, 1, 1);

        form.add(new Label("Gender"), 2, 1);
        form.add(genderField, 3, 1);

        form.add(new Label("Disease"), 0, 2);
        form.add(diseaseField, 1, 2);

        form.add(new Label("Doctor"), 2, 2);
        form.add(doctorField, 3, 2);

        form.add(new Label("Bill"), 0, 3);
        form.add(billField, 1, 3);

        // ================= BUTTONS =================

        Button addBtn = new Button("Add Patient");
        Button deleteBtn = new Button("Delete");
        Button clearBtn = new Button("Clear");

        addBtn.setPrefWidth(140);
        deleteBtn.setPrefWidth(140);
        clearBtn.setPrefWidth(140);

        HBox buttonBox =
                new HBox(15, addBtn, deleteBtn, clearBtn);

        buttonBox.setAlignment(Pos.CENTER_LEFT);

        form.add(buttonBox, 1, 4, 3, 1);

        // ================= TABLE =================

        TableColumn<Patient, Integer> idCol =
                new TableColumn<>("ID");

        idCol.setCellValueFactory(
                new PropertyValueFactory<>("patientId")
        );

        TableColumn<Patient, String> nameCol =
                new TableColumn<>("Name");

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        TableColumn<Patient, Integer> ageCol =
                new TableColumn<>("Age");

        ageCol.setCellValueFactory(
                new PropertyValueFactory<>("age")
        );

        TableColumn<Patient, String> diseaseCol =
                new TableColumn<>("Disease");

        diseaseCol.setCellValueFactory(
                new PropertyValueFactory<>("disease")
        );

        TableColumn<Patient, String> doctorCol =
                new TableColumn<>("Doctor");

        doctorCol.setCellValueFactory(
                new PropertyValueFactory<>("doctorAssigned")
        );

        table.getColumns().addAll(
                idCol,
                nameCol,
                ageCol,
                diseaseCol,
                doctorCol
        );

        table.setItems(patientList);

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setPrefHeight(400);

        // ================= ACTIONS =================

        addBtn.setOnAction(e -> addPatient());

        deleteBtn.setOnAction(e -> deletePatient());

        clearBtn.setOnAction(e -> clearFields());

        // ================= ROOT =================

        VBox root = new VBox(
                25,
                title,
                cardsBox,
                form,
                table
        );

        root.setPadding(new Insets(30));

        root.setStyle(
                "-fx-background-color: #f1f5f9;"
        );

        // LOAD FILE
        loadFromFile();

        Scene scene = new Scene(root, 1200, 800);

        scene.getStylesheets().add("style.css");

        stage.setTitle("SmartCare HMS");

        stage.setScene(scene);

        stage.show();
    }

    // ================= CARD =================

    private VBox createCard(String title, String value) {

        Label titleLabel = new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #64748b;"
        );

        Label valueLabel = new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;"
        );

        VBox card = new VBox(10,
                titleLabel,
                valueLabel);

        card.setPadding(new Insets(20));

        card.setPrefWidth(220);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08),10,0,0,4);"
        );

        return card;
    }

    // ================= ADD PATIENT =================

    private void addPatient() {

        try {

            Patient patient = new Patient(

                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    Integer.parseInt(ageField.getText()),
                    genderField.getText(),
                    diseaseField.getText(),
                    doctorField.getText(),
                    Double.parseDouble(billField.getText())
            );

            patientList.add(patient);

            saveToFile();

            clearFields();

        } catch (Exception e) {

            showAlert("Please enter valid data!");
        }
    }

    // ================= DELETE =================

    private void deletePatient() {

        Patient selected =
                table.getSelectionModel().getSelectedItem();

        if (selected != null) {

            patientList.remove(selected);

            saveToFile();

        } else {

            showAlert("Select patient first!");
        }
    }

    // ================= CLEAR =================

    private void clearFields() {

        idField.clear();
        nameField.clear();
        ageField.clear();
        genderField.clear();
        diseaseField.clear();
        doctorField.clear();
        billField.clear();
    }

    // ================= ALERT =================

    private void showAlert(String message) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setContentText(message);

        alert.show();
    }

    // ================= SAVE FILE =================

    private void saveToFile() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter("patients.txt")
                    );

            for (Patient p : patientList) {

                writer.write(
                        p.getPatientId() + "," +
                        p.getName() + "," +
                        p.getAge() + "," +
                        p.getGender() + "," +
                        p.getDisease() + "," +
                        p.getDoctorAssigned() + "," +
                        p.getBillAmount()
                );

                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {

            showAlert("Error Saving File!");
        }
    }

    // ================= LOAD FILE =================

    private void loadFromFile() {

        try {

            File file = new File("patients.txt");

            if (!file.exists()) {
                return;
            }

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Patient patient = new Patient(

                        Integer.parseInt(data[0]),
                        data[1],
                        Integer.parseInt(data[2]),
                        data[3],
                        data[4],
                        data[5],
                        Double.parseDouble(data[6])
                );

                patientList.add(patient);
            }

            reader.close();

        } catch (IOException e) {

            showAlert("Error Loading File!");
        }
    }

    public static void main(String[] args) {

        launch();
    }
}