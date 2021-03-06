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
        if(book_data.getTitle() != null) {
            stringBuilder.append("タイトル : " + book_data.getTitle() + "\n");
        }
        if(book_data.getAuthors() != null){
            stringBuilder.append("著者 : " + book_data.getAuthors().toString() + "\n");
        }
        if(book_data.get_published_date() != null){
            stringBuilder.append("発売時期 : " + book_data.get_published_date() + "\n");
        }
        if(book_data.getDescription() != null){
            stringBuilder.append("概要 : " + format_string(book_data.getDescription()));
        }
        return stringBuilder.toString();
    }
    private String format_string(String str){
        if (str.length() > 60)
        {
            if (str.length() > 140){
                str = shorten(str);
            }
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

    private String shorten(String str)
    {
        StringBuilder local_sb = new StringBuilder();
        local_sb.append(str.substring(0, 140));
        local_sb.append("・・・");
        return local_sb.toString();
    }

    private String format_string_reference_style()
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (book_data.getAuthors() != null) {
            book_data.getAuthors().forEach(s -> stringBuilder.append(s + " "));
        }
        if (book_data.get_published_date() != null && book_data.get_published_date().indexOf('-') != -1) {
            stringBuilder.append("(" + book_data.get_published_date().substring(0, book_data.get_published_date().indexOf('-')) + ") ");
        }else{
            stringBuilder.append("(Unknown) ");
        }

        if(book_data.getTitle() != null){
            stringBuilder.append(" 『" + book_data.getTitle() + "』 ");
        }

        if(book_data.getPublisher() != null){
            stringBuilder.append(book_data.getPublisher());
        }

        return stringBuilder.toString();
    }

    void register(UIRoot root, double x, double y){
        root.register(canvas);
        root.set_place(canvas, x, y);
    }

}
