import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Stack;

public class TemplateController {
    @FXML
    private StackPane contentPane;
    
    private Stack<Node> pageHistory = new Stack<>();
    
    // Method to set the content of the template
    public void setContent(String fxmlPath) {
        try {
            // Load the new content
            Node content = FXMLLoader.load(getClass().getResource(fxmlPath));
            
            // Add current page to history (if there is one)
            if (!contentPane.getChildren().isEmpty()) {
                pageHistory.push(contentPane.getChildren().get(0));
            }
            
            // Clear and set new content
            contentPane.getChildren().clear();
            contentPane.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleBackButton() {
        if (!pageHistory.isEmpty()) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(pageHistory.pop());
        }
    }
    
    @FXML
    private void handleHomeButton() {
        // Load your home page
        setContent("home.fxml");
        
        // Clear history since we're going to home
        pageHistory.clear();
    }
}