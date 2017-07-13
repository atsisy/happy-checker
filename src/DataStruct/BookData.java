package DataStruct;

import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volume.SaleInfo;
import com.google.api.services.books.model.Volume.VolumeInfo;

import java.util.List;

/**
 * Created by Akihiro on 7/11/2017.
 */
public class BookData {

    private List<String> authors;
    private String title;
    private String description;
    private String published_date;
    private double rating;
    private double price;
    private String link_more_info;
    private String publisher;

    public BookData(Volume volume) {
        VolumeInfo volume_information = volume.getVolumeInfo();
        SaleInfo sale_info = volume.getSaleInfo();

        /*
        * タイトル
        */
        title = volume_information.getTitle();

        /*
        * 著者情報
        */
        authors = volume_information.getAuthors();

        /*
        * 発売日情報
        */
        if (volume_information.getPublishedDate() != null) {
            published_date = volume_information.getPublishedDate();
        } else {
            published_date = "Unknown";
        }

        /*
        * 出版社情報
         */
        if (volume_information.getPublisher() != null) {
            publisher = volume_information.getPublisher();
        } else {
            publisher = "Unknown";
        }

        /*
        * 概要情報
        */
        if (volume_information.getDescription() != null && volume_information.getDescription().length() > 0) {
            description = volume_information.getDescription();
        } else {
            description = "";
        }

        /*
        * 評価情報
        */
        if (volume_information.getRatingsCount() != null && volume_information.getRatingsCount() > 0) {
            rating = volume_information.getRatingsCount();
        }

        /*
        * 価格情報
        */
        if (sale_info != null && "FOR_SALE".equals(sale_info.getSaleability())) {
            if (sale_info.getListPrice().getAmount() - sale_info.getRetailPrice().getAmount() > 0.0) {
                price = sale_info.getRetailPrice().getAmount();
            } else {
                price = -1;
            }
        }

        link_more_info = volume_information.getInfoLink();

    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public String get_published_date() {
        return published_date;
    }

    public String getPublisher() {
        return publisher;
    }
}
