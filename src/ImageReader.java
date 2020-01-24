import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The class represents as a reader to read the images from a file.
 *
 * @version 1.3
 */
public class ImageReader {
    /**
     * Reads the image file.
     *
     * @param filename The name of the file that is read.
     * @throws FileNotFoundException If the image file can't be found.
     */
    public static FileInputStream readImage(String filename) throws FileNotFoundException {
        Scanner in = null;
        File imageIn = new File(filename);

        try {
            in = new Scanner(imageIn);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find " + filename);
            System.exit(0);
        }
        FileInputStream input = new FileInputStream(filename);
        return input;

    }

}
