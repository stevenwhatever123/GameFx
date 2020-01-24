import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * This class controls the animation of the character by using a sprite sheet.
 * Codes are mostly reference to JavaFX GameDevelopment from Jeffrey Seaman on youtube.
 * @version 1.0
 */
public class SpriteAnimation extends Transition {

    // The image view of the character
    private final ImageView imageview;

    private final int count;

    // number of columns on a sprite sheet
    private final int columns;

    // position of the x axis
    private int offsetX;

    // position of the y axis
    private int offsetY;

    // Width of the image
    private final int width;

    // Height of the image
    private final int height;

    public SpriteAnimation(
            ImageView imageview,
            Duration duration, int count,
            int columns, int offsetX,
            int offsetY, int width,
            int height){
        this.imageview = imageview;
        this.count = count;
        this.columns = count;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;

        //method
        setCycleDuration(duration);
        setCycleCount(Animation.INDEFINITE);
        setInterpolator(Interpolator.LINEAR);
        this.imageview.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }

    public void setOffsetX(int offsetX){
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY){
        this.offsetY = offsetY;
    }

    protected void interpolate(double frac){
        final int index = Math.min((int)Math.floor(count*frac), count -1);
        final int x = (index % columns) * width + offsetX;
        final int y = (index / columns) * height + offsetY;
        imageview.setViewport(new Rectangle2D(x, y, width, height));
    }

}
