import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileNotFoundException;

/**
 * The class represents as a reader to read the images from a file.
 *
 * @version 1.2
 */
public class Player extends Character {

    // Text box for the dialogue
    Text text = new Text();

    // Image of the player
    Image image = new Image(ImageReader.readImage("Male 01-1.png"));

    // Player's animation
    SpriteAnimation animation;

    // The parent root
    Pane root;

    /**
     * This constructor creates the player object.
     * @param imageview the image view for the player
     * @param root the parent root of the game
     * @throws FileNotFoundException if the image of the player is not found
     */
    public Player(ImageView imageview, Pane root) throws FileNotFoundException {
        super(imageview);
        this.root = root;
        this.imageview.setImage(image);
        this.imageview.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageview, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
    }

    /**
     * This method starts a dialogue and add it to the parent root.
     */
    public void startDialogue(){
        text.setText("WOW, I can interact with this thing!");
        text.setTranslateX(400);
        text.setTranslateY(50);
        text.setFont(new Font(30));
        text.setWrappingWidth(500);
        text.setFill(Color.YELLOW);
        root.getChildren().add(text);
    }

    /**
     * This method removes the dialogue from the parent root.
     */
    public void clearDialogue(){
        root.getChildren().remove(text);
    }
}
