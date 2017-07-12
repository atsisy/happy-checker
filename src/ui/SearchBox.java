package ui;

import DataStruct.BookData;
import collection.GoogleBooksClient;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

import static value.Values.*;

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
            GoogleBooksClient client = new GoogleBooksClient(API_KEY_PATH);
            ArrayList<BookData> data = client.get_books_data(search_box.getText());
            if (data != null) {
                for (int i = 0; i < data.size(); ++i) {
                    BookField field = new BookField(data.get(i));
                    field.register(Main.root, 10, 60 + (BOOK_FIELD_HEIGHT * i));
                }
            }
        });
    }

    void register_to_root(UIRoot ui_root){
        ui_root.register(root_box);
        ui_root.set_place(root_box, SEARCH_BOX_X, SEARCH_BOX_Y);
    }


}
