import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;

/**
 * This class creates the Tile of the game
 * @version 1.2
 */
public class Tile extends Pane {

    // Name of the tile
    private String name;

    // Width and height of the tile itself
    private int width = 64;
    private int height = 64;

    // An image object for storing the tile's image
    Image image;

    // Images of different type of tiles
    Image wallImage = new Image(ImageReader.readImage("Wall1.PNG"));
    Image groundImage = new Image(ImageReader.readImage("Ground.jpg"));

    public ImageView imageview;

    public Tile(ImageView imageview, String name, int x, int y) throws FileNotFoundException {
        setName(name);
        this.imageview = imageview;
        setType(name);
        this.imageview.setImage(image);
        this.setPrefSize(width, height);
        imageview.setPreserveRatio(true);
        imageview.setFitWidth(width);
        imageview.setFitHeight(height);
        this.setTranslateX(x);
        this.setTranslateY(y);
        getChildren().addAll(imageview);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setType(String name){
        switch(name){
            case "Ground":
                image = groundImage;
                break;
            case "Wall":
                image = wallImage;
                break;
        }
    }

}
