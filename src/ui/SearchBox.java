package ui;

import DataStruct.BookData;
import collection.GoogleBooksClient;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
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
    private ChoiceBox<String> search_word_type;
    private HBox root_box;

    public SearchBox() {
        search_box = new TextField();
        search_word_type = new ChoiceBox<>();
        search_word_type.getItems().addAll("タイトル", "著者", "ISBN");
        search_word_type.getSelectionModel().select("著者");
        root_box = new HBox();
        root_box.getChildren().addAll(search_label, search_box, search_word_type);
        root_box.setSpacing(10);

        search_box.setOnAction(event -> {
            GoogleBooksClient client = new GoogleBooksClient(API_KEY_PATH);
            ArrayList<BookData> data = client.get_books_data(choice_to_query(search_word_type.getValue()) + search_box.getText());
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

    String choice_to_query(String choice){
        switch (choice){
            case "タイトル":
                return "intitle:";
            case "著者":
                return "inauthor:";
            case "ISBN":
                return "isbn:";
        }
        return "null";
    }

}
