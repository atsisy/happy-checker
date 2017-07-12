package collection;

import java.io.*;
import DataStruct.BookData;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.model.Volumes;
import value.Values;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Akihiro on 7/11/2017.
 */
public class GoogleBooksClient {

    private String API_KEY;

    public GoogleBooksClient(String api_key_path)
    {
        FileReader file_reader = null;
        BufferedReader buffered_reader = null;

        try {
            file_reader = new FileReader(api_key_path);
            buffered_reader = new BufferedReader(file_reader);

            API_KEY = buffered_reader.readLine();

            if(API_KEY == null){
                System.err.println("ERROR API KEY FILE IS WRONG?");
                System.exit(-1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffered_reader.close();
                file_reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private ArrayList<BookData> query_google_books(String query) throws Exception {

        ArrayList<BookData> books_data = new ArrayList<>();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        /*
        * Booksオブジェクトを生成
         */
        final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
                .setApplicationName(Values.APPLICATION_NAME)
                .setGoogleClientRequestInitializer(new BooksRequestInitializer(this.API_KEY))
                .build();

        /*
        * クエリを登録
         */
        List volumes_list = books.volumes().list(query);

        Volumes volumes = volumes_list.execute();
        if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
            return null;
        }

        volumes.getItems().forEach(volume -> books_data.add(new BookData(volume)) );

        return books_data;
    }

    public ArrayList<BookData> get_books_data(String query) {

        ArrayList<BookData> data = null;

        try {
            data = query_google_books(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

}
