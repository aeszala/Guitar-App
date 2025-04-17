package com.controller;

import com.model.User;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CreateAccountController {

    private static final String XML_FILE = "users.xml";

    public static void createUser(User user) {
        try {
            File xmlFile = new File(XML_FILE);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc;
            Element root;

            if (xmlFile.exists()) {
                doc = builder.parse(xmlFile);
                root = doc.getDocumentElement();
            } else {
                doc = builder.newDocument();
                root = doc.createElement("users");
                doc.appendChild(root);
            }

            Element userElement = doc.createElement("user");

            addChildElement(doc, userElement, "id", user.getId().toString());
            addChildElement(doc, userElement, "username", user.getUsername());
            addChildElement(doc, userElement, "password", user.getPassword());
            addChildElement(doc, userElement, "email", user.getEmail());
            addChildElement(doc, userElement, "name", user.getName());
            addChildElement(doc, userElement, "securityQuestion", user.getSecurityQuestion());
            addChildElement(doc, userElement, "securityAnswer", user.getSecurityAnswer());

            root.appendChild(userElement);

            // Write to XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(xmlFile));

            System.out.println("User created successfully and saved to XML.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addChildElement(Document doc, Element parent, String tag, String value) {
        Element element = doc.createElement(tag);
        element.appendChild(doc.createTextNode(value));
        parent.appendChild(element);
    }
}
