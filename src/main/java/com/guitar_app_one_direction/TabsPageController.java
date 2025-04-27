package com.guitar_app_one_direction;

import java.io.IOException;

import javafx.fxml.FXML;

public class TabsPageController {

  @FXML
  private void switchToPrimary() throws IOException {
    App.setRoot("primary");
  }

}
