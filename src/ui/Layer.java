package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Akihiro on 7/12/2017.
 */
public class Layer {
    protected Canvas canvas;
    protected GraphicsContext graphics_context;

    public Layer(double width, double height){
        canvas = new Canvas(width, height);
        graphics_context = canvas.getGraphicsContext2D();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGraphicsContext() {
        return graphics_context;
    }

    /*
    * 指定したグラフィックレイヤーをすべて消す関数
     */
    public void eraseLayer(){
        this.graphics_context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /*
    * アクティブレイヤーの変更を行うメソッド
     */
    public void to_front(){
        canvas.toFront();
    }

    /*
    * アクティブレイヤーの変更を行うメソッド
     */
    public void be_back(){
        canvas.toBack();
    }
}