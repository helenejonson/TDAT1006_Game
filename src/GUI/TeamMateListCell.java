package GUI;

import main.*;
import game.Character;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.io.IOException;

/**
 * Handles the content in teamMateCells before they are displayed in
 * teamMatesList.
 *
 * Inspired by the blog post <i>Custom ListCell in a JavaFX ListView</i> by johannes
 * @see <a href="https://www.turais.de/how-to-custom-listview-cell-in-javafx/">Custom ListCell in a JavaFX ListView</a>
 *
 * @author magnubau
 */
public class TeamMateListCell extends ListCell<Character> {

    @FXML
    private Label nameLabel, hpLabel, acLabel;
    @FXML
    private ImageView characterIV;
    @FXML
    private HBox hBox;

    /**
     * Updates a spesific teamMateCell corresponding to a
     * specific Character Object.
     *
     * @param character         the Character to display.
     * @param empty             true if empty cell, false otherwise.
     */
    @Override
    protected void updateItem(Character character, boolean empty){
        super.updateItem(character, empty);

        if (empty || character == null) {

            setText(null);
            setGraphic(null);

            /* Loads the FXML-file for a TeamMateCell */
        } else {
            FXMLLoader mLLoader;
            mLLoader = new FXMLLoader(getClass().getResource("TeamMateCell.fxml"));
            mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /* Adds imageView, healthpoints and armour class to teamMateCell */
            Image image = new Image("GUI/images/" + character.getImageUrl());
            characterIV.setImage(image);
            nameLabel.setText(Main.db.fetchUsernameFromPlayerId(character.getPlayerId()));
            hpLabel.setText("HP: " + Math.max(0, character.getHp()) + " / " + character.getInitialHp());  //bytt ut med initialhp
            acLabel.setText("AC: " + character.getAc());
            setText(null);
            setGraphic(hBox);
        }
    }
}
