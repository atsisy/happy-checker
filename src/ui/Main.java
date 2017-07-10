package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Akihiro on 7/10/2017.
 */
public class Main extends Application {

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    public void start(Stage stage)
    {
        stage.setTitle("Happy Checker");
        stage.setWidth(768);
        stage.setHeight(768);

        AnchorPane root = new AnchorPane();

        stage.setScene(new Scene(root));
        stage.show();

    }

}
