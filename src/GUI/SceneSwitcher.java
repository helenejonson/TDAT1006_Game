/*
Give all your menuController classes the following object variable:
private SceneSwitcher sceneSwitcher

Also give your menuController classes this constructor:
    public *menuController name*(){
        sceneSwitcher = new SceneSwitcher();
    }

SceneSwitcher need Button and the name of a fxml-file in a String as input
*/
package GUI; // switch to the name of your package

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SceneSwitcher {

    public void switchScene(Button button, String page) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(page));
        Scene scene = new Scene(root);
        Stage stage = (Stage)button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
