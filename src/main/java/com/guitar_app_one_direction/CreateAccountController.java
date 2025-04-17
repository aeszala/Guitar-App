package com.guitar_app_one_direction;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

import com.model.User;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateAccountController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtEmail;
    @FXML private TextField txtName;
    @FXML private TextField txtSecurityQuestion;
    @FXML private TextField txtSecurityAnswer;
    @FXML private Label lblStatus;

    @FXML
    private void handleCreateAccount() {
        try {
            User user = new User(
                txtUsername.getText(),
                txtPassword.getText(),
                txtEmail.getText(),
                txtName.getText(),
                txtSecurityQuestion.getText(),
                txtSecurityAnswer.getText()
            );

            saveUserToXML(user);
            lblStatus.setText("Account created and saved!");
        } catch (Exception e) {
            lblStatus.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveUserToXML(User user) throws Exception {
        String filePath = "user_" + user.getUsername() + ".xml";

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(fos, "UTF-8");

            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("user");

            writeElement(writer, "id", user.getId().toString());
            writeElement(writer, "username", user.getUsername());
            writeElement(writer, "password", user.getPassword());
            writeElement(writer, "email", user.getEmail());
            writeElement(writer, "name", user.getName());
            writeElement(writer, "securityQuestion", user.getSecurityQuestion());
            writeElement(writer, "securityAnswer", user.getSecurityAnswer());

            writer.writeEndElement(); // </user>
            writer.writeEndDocument();

            writer.flush();
            writer.close();
        }
    }

    private void writeElement(XMLStreamWriter writer, String name, String value) throws Exception {
        writer.writeStartElement(name);
        writer.writeCharacters(value != null ? value : "");
        writer.writeEndElement();
    }
}
