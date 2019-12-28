import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class Controller {

    private Model _model = new Model();

    @FXML
    public TextField word;
    public Label     translation;
    public Button    translate_button;

    public void onClickMethod() {
        if ( word.getText().isEmpty() )
            return;

        var tmp_translation = _model.getTranslation( word.getText() );

        if ( !tmp_translation.isEmpty() ) {
            translation.setText( _model.getTranslation(word.getText()) );
            return;
        }

        Alert wordNotSearchedAlert = new Alert( Alert.AlertType.CONFIRMATION );

        wordNotSearchedAlert.setTitle("Word doesnt exist");
        wordNotSearchedAlert.setHeaderText("The requested word does not exist");
        wordNotSearchedAlert.setContentText("Are you want create this translation?");

        Optional<ButtonType> answerButton = wordNotSearchedAlert.showAndWait();

        if ( answerButton.isPresent() && answerButton.get() == ButtonType.OK ) {
            TextInputDialog translationInput = new TextInputDialog();

            translationInput.setTitle("New translation");
            translationInput.setHeaderText("Input translation");
            translationInput.setContentText("Translation");

            Optional<String> newTranslation = translationInput.showAndWait();

            if ( newTranslation.isPresent() && !newTranslation.get().isEmpty() ) {
                _model.addDictPair(word.getText(), newTranslation.get());
            }
        }
    }
}
