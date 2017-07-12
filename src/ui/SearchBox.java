package ui;

import DataStruct.BookData;
import collection.GoogleBooksClient;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

import static value.Values.SEARCH_BOX_X;
import static value.Values.SEARCH_BOX_Y;

/**
 * Created by Akihiro on 7/12/2017.
 */
public class SearchBox {

    private final Label search_label = new Label("Search :");
    private TextField search_box;
    private HBox root_box;

    public SearchBox() {
        search_box = new TextField();
        root_box = new HBox();
        root_box.getChildren().addAll(search_label, search_box);
        root_box.setSpacing(10);

        search_box.setOnAction(event -> {
            GoogleBooksClient client = new GoogleBooksClient("C:\\Users\\Akihiro\\Desktop\\api_key.txt");
            ArrayList<BookData> data = null;

            try {
                data = client.query_google_books("inauthor:" + search_box.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (data != null) {
                data.forEach(bookData -> {
                    System.out.println(bookData.getTitle());
                });
            }
        });
    }

    void register_to_root(UIRoot ui_root){
        ui_root.register(root_box);
        ui_root.set_place(root_box, SEARCH_BOX_X, SEARCH_BOX_Y);
    }


}
