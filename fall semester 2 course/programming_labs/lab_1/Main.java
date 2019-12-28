import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double PREF_MIN_WIDTH  = 600;
    private static final double PREF_MIN_HEIGHT = 220;

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            Group group = new Group();

            Parent content = FXMLLoader.load(getClass().getResource("GUI.fxml"));
            BorderPane root = new BorderPane();
            root.setCenter(content);

            group.getChildren().add(root);
            primaryStage.setScene(new Scene(group, PREF_MIN_WIDTH, PREF_MIN_HEIGHT));

            primaryStage.showingProperty().addListener((observable, oldValue, showing) -> {

                if (!showing)
                    return;

                // set fixed app size.
                primaryStage.setMinHeight(primaryStage.getHeight());
                primaryStage.setMaxHeight(primaryStage.getHeight());
                primaryStage.setMinWidth(primaryStage.getWidth());
                primaryStage.setMaxWidth(primaryStage.getWidth());

                primaryStage.setTitle("Simple dictionary");

            });

            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); // main like view.
    }

}