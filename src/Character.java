import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The class represents as a reader to read the images from a file.
 *
 * @version 1.1
 */
public class Character extends Pane {

    // Character Configuration
    ImageView imageview;

    // Number of photos in a row
    protected int count = 3;
    // Number of columns
    protected int columns = 3;

    // The position of the image
    protected int offsetX = 0;
    protected int offsetY = 0;

    // Size of the image of character
    protected int width = 32;
    protected int height = 32;

    // Size of the character
    protected int viewWidth = 64;
    protected int viewHeight = 64;

    // Image of the character
    Image image;

    /**
     * This constructor creates the object of the character.
     * @param imageview the image view for the character
     * @throws FileNotFoundException if the image file is not found
     */
    public Character(ImageView imageview) throws FileNotFoundException {
        this.setPrefSize(width, height);
        this.imageview = imageview;
        this.imageview.setImage(image);
        this.imageview.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        imageview.setFitWidth(viewWidth);
        imageview.setFitHeight(viewHeight);
        getChildren().addAll(imageview);
    }

    /**
     * This method moves the player horizontally.
     * @param x distance to move
     */
    public void moveX(int x){
        boolean right = x > 0;
        for(int i = 0; i < Math.abs(x); i++){
            if(right){
                this.setTranslateX(this.getTranslateX() + 1);
            } else {
                this.setTranslateX(this.getTranslateX() - 1);
            }
        }
    }

    /**
     * This method moves the player vertically.
     * @param y distance to move
     */
    public void moveY(int y) {
        boolean right = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            if (right) {
                this.setTranslateY(this.getTranslateY() + 1);
            } else {
                this.setTranslateY(this.getTranslateY() - 1);
            }
        }
    }

    /**
     * This method checks if the player is collided with any tile objects that is not walkable.
     * @param tile list of not walkable tiles
     * @return true if it is not walkable, otherwise false
     */
    public boolean checkCollision(ArrayList<Tile> tile) {
        boolean collisionDetected = false;
        for(int i = 0; i < tile.size(); i++){
            if(this.getBoundsInParent().intersects(tile.get(i).getBoundsInParent())){
                collisionDetected = true;
            }
        }
        return collisionDetected;
    }

    /**
     * This method checks if the player has walked out of the game boundaries.
     * @param root the pane of the game
     * @return true if the player is walk out of the pane, otherwise false
     */
    public boolean checkBoundary(Pane root){
        boolean boundaryDetected = false;
        if(this.getTranslateY() + viewWidth> root.getPrefHeight() ||
                this.getTranslateY() < 0 ||
                this.getTranslateX() + viewWidth > root.getPrefWidth() ||
                this.getTranslateX() < 0){
            boundaryDetected = true;
        }
        return boundaryDetected;
    }


}
