package ui;

import DataStruct.BookData;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static value.Values.BOOK_FIELD_HEIGHT;
import static value.Values.WINDOW_WIDTH;

/**
 * Created by Akihiro on 7/12/2017.
 */
public class BookField extends Layer {

    private BookData book_data;

    public BookField(BookData src_book_data)
    {
        super(WINDOW_WIDTH - 50, BOOK_FIELD_HEIGHT);
        book_data = src_book_data;
        draw();
    }

    void draw(){
        graphics_context.setFill(Color.WHITE);
        graphics_context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphics_context.setFont(new Font(15));

        graphics_context.setFill(Color.BLACK);
        graphics_context.fillText("タイトル : " + book_data.getTitle(), 10, 20);
        graphics_context.fillText("著者 : " + book_data.getAuthors().toString(), 10, 40);
        graphics_context.fillText("発売時期 : " + book_data.get_published_date(), 10, 60);
        graphics_context.fillText("概要 : " + fix_string(book_data.getDescription()), 10, 80);
    }

    private String fix_string(String str){
        if (str.length() > 60)
        {
            int offset = 60;
            StringBuilder builder = new StringBuilder();
            builder.append(str);
            builder.insert(offset, '\n');
            str = builder.toString();
            offset += 60;
            while (str.length() - str.lastIndexOf('\n') > 60){
                StringBuilder local_builder = new StringBuilder();
                local_builder.append(str);
                local_builder.insert(offset, '\n');
                str = local_builder.toString();
                offset += 60;
            }
        }

        return str;
    }

    void register(UIRoot root, double x, double y){
        root.register(canvas);
        root.set_place(canvas, x, y);
    }

}
