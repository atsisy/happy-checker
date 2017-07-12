package ui;

import javafx.application.Application;
import javafx.stage.Stage;

import static value.Values.WINDOW_HEIGHT;
import static value.Values.WINDOW_WIDTH;

/**
 * Created by Akihiro on 7/10/2017.
 */
public class Main extends Application {

    static UIRoot root = new UIRoot();

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    public void start(Stage stage)
    {
        stage.setTitle("Happy Checker");
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);

        SearchBox search_box = new SearchBox();
        search_box.register_to_root(root);

        stage.setScene(root.create_scene());
        stage.show();

    }

}
