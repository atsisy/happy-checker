package ui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Akihiro on 7/12/2017.
 */
public class UIRoot {

    private AnchorPane root;

    public UIRoot(){
        root = new AnchorPane();
    }

    public void register(Node node){
        root.getChildren().add(node);
    }

    public void register(Node... node){
        for(Node elem : node) {
            root.getChildren().add(elem);
        }
    }

    public Scene create_scene(){
        return new Scene(root);
    }

    public void set_place(Node node, double x, double y){
        AnchorPane.setLeftAnchor(node, x);
        AnchorPane.setTopAnchor(node, y);
    }

}
