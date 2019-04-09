package GUI;

import Main.*;
import Database.*;
import audio.MusicPlayer;
import audio.SFXPlayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * MainMenuController.Java
 * The porgram handles the components in the MainMenu-scene.
 * @author williad saramoh
 */
public class MainMenuController {


    @FXML
    private Button startNewGameButton, joinLobbyButton, viewAccountButton, settingsButton, helpButton, signOutButton;


    private Database db = Main.db;

    /**
     * The user has no player_id, so, sets setHost() false
     */
    public void initialize(){
        Main.user.setPlayerId(-1);
        db.setHost(false);
    }

    /**
     * Creates a new lobby using the method createNewLobby() from the DB.
     * Setter the player as host using the method setHost() form the DB.
     * Switches the scene to the createcharacter-scene.
     * @throws Exception
     */
    public void startNewGameButtonPressed() throws Exception{
        db.createNewLobby();
        db.setHost(true);
        new SFXPlayer("knockSFX").run();
        MusicPlayer.getInstance().stopSong();
        MusicPlayer.getInstance().changeSong(3);
        Parent root = FXMLLoader.load(getClass().getResource("createcharacter.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)startNewGameButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches the scene to the FindLobby-scene.
     * @throws Exception
     */
    public void joinLobbyButtonPressed() throws Exception{
        SFXPlayer.getInstance().setSFX(0);
        Parent root = FXMLLoader.load(getClass().getResource("FindLobby.fxml"));
        Scene scene = new Scene(root, 800, 500);
        Stage stage = (Stage)joinLobbyButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     *  Switches the scene to the AccountDetails-scene.
     * @throws Exception
     */
    public void viewAccountButtonPressed() throws Exception{
        SFXPlayer.getInstance().setSFX(0);
        Parent root = FXMLLoader.load(getClass().getResource("AccountDetails.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)viewAccountButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches the scene to the settings-scene.
     * @throws Exception
     */
    public void settingsButtonPressed() throws Exception{
        SFXPlayer.getInstance().setSFX(0);
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)settingsButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Connects the User to User-manual page in WIKI in GitLab
     * @throws Exception
     */
    public void helpButtonPressed() throws Exception{
        new SFXPlayer("knockSFX").run();
        Parent root = FXMLLoader.load(getClass().getResource("Here will be set the url of the user-manual"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)helpButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches the scene to the start-scene.
     * @throws Exception
     */
    public void signOutButtonPressed() throws Exception{
        SFXPlayer.getInstance().setSFX(0);
        audio.MusicPlayer.getInstance().stopSong();
        MusicPlayer.getInstance().changeSong(10);
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)signOutButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
