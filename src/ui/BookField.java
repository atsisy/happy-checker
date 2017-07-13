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
    private boolean mode;

    public BookField(BookData src_book_data, boolean mode)
    {
        super(WINDOW_WIDTH - 50, BOOK_FIELD_HEIGHT);
        book_data = src_book_data;
        this.mode = mode;
        draw();
    }

    private void draw(){

        graphics_context.setFill(Color.WHITE);
        graphics_context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        draw_text();
        draw_frame();
    }

    private void draw_frame(){
        graphics_context.setLineWidth(5);
        graphics_context.setStroke(Color.CADETBLUE);
        graphics_context.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void draw_text(){
        graphics_context.setFont(new Font(15));

        graphics_context.setFill(Color.BLACK);

        if (!mode) {
            /*
            * 参考文献モードが無効
             */
            graphics_context.fillText(format_string_normal_mode(), 10, 20);
        }else{
            /*
            * 有効
             */
            graphics_context.fillText(format_string_reference_style(), 10, 20);
        }
    }

    private String format_string_normal_mode(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("タイトル : " + book_data.getTitle() + "\n");
        stringBuilder.append("著者 : " + book_data.getAuthors().toString() + "\n");
        stringBuilder.append("発売時期 : " + book_data.get_published_date() + "\n");
        stringBuilder.append("概要 : " + format_string(book_data.getDescription()));
        return stringBuilder.toString();
    }
    private String format_string(String str){
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

    private String format_string_reference_style()
    {
        StringBuilder stringBuilder = new StringBuilder();

        book_data.getAuthors().forEach(s -> stringBuilder.append(s + " "));
        if (book_data.get_published_date().indexOf('-') != -1) {
            stringBuilder.append("(" + book_data.get_published_date().substring(0, book_data.get_published_date().indexOf('-')) + ") ");
        }else{
            stringBuilder.append("(Unknown) ");
        }

        stringBuilder.append(" 『" + book_data.getTitle() + "』 ");

        stringBuilder.append(book_data.getPublisher());

        return stringBuilder.toString();
    }

    void register(UIRoot root, double x, double y){
        root.register(canvas);
        root.set_place(canvas, x, y);
    }

}
