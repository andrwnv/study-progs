import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class Controller {

    private Model model = new Model();

    @FXML
    public Button translate_button;
    public Label translation;
    public TextField word;

    public void onClickMethod() {
        if (word.getText().isEmpty())
            return;

        var tmp_translation = model.getTranslation(word.getText());

        if (!tmp_translation.isEmpty()) {
            translation.setText(model.getTranslation(word.getText()));
            return;
        }

        Alert word_not_searched_alert = new Alert(Alert.AlertType.CONFIRMATION);

        word_not_searched_alert.setTitle("Word doesnt exist");
        word_not_searched_alert.setHeaderText("The requested word does not exist");
        word_not_searched_alert.setContentText("Are you want create this translation?");

        Optional<ButtonType> button_answer = word_not_searched_alert.showAndWait();

        if (button_answer.isPresent() && button_answer.get() == ButtonType.OK) {
            TextInputDialog input_translation = new TextInputDialog();

            input_translation.setTitle("New translation");
            input_translation.setHeaderText("Input translation");
            input_translation.setContentText("Translation");

            Optional<String> new_translation = input_translation.showAndWait();

            if ( new_translation.isPresent() && !new_translation.get().isEmpty()) {
                model.addDictPair(word.getText(), new_translation.get());
            }
        }
    }
}
