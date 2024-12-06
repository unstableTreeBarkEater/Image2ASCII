//salam
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import org.jline.reader.*;
import org.jline.reader.impl.*;
import org.jline.reader.impl.completer.*;
import org.jline.terminal.*;
import org.jline.terminal.impl.*;

/**
 * ASCIIProject is a program that converts an image to an ASCII representation
 * and saves the result as a text file. Users can specify a chunk size, which determines
 * the level of compression applied to the image.
 *
 * <p>Features:
 * - Tab-completion for file path selection.
 * - Customizable chunk size for ASCII conversion.
 * - Simple aspect ratio calculation.
 *
 * Note: Saved files are stored in the same directory as the program execution.
 */
public class ASCIIProject{
    private static String filename;

    /**
     * The main method initializes the terminal, gets user input for image path and chunk size,
     * processes the image into an ASCII representation, and saves the output to a file.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If there are issues with file reading or writing.
     */
    public static void main(String[] args) throws IOException{

        Terminal terminal = TerminalBuilder.terminal();
        LineReader reader = LineReaderBuilder.builder()
            .terminal(terminal)
            .completer(new FileNameCompleter()) // Add file path completer
            .build();

        Scanner input = new Scanner(System.in);

        // Introduction{{{
        System.out.print("⠄⢀⣀⣤⣴⣶⣶⣤⣄⡀⠄⠄⣀⣤⣤⣤⣤⡀⠄⠄⠄⠄⠀⠀⠀⠀⠀⠀                        \n"
                +"⣴⣏⣹⣿⠿⠿⠿⠿⢿⣿⣄⢿⣿⣿⣿⣿⣿⣋⣷⡄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⢠⣠⣾⣷⣿⣿⣿⣷⣄⠄⠀         \n"
                +"⣿⢟⣩⣶⣾⣿⣿⣿⣶⣮⣭⡂⢛⣭⣭⣭⣭⣭⣍⣛⣂⡀⠄⠄⠄⠄⠄⠄⠄⠄        ⣀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⢅⠀        \n"
                +"⣿⣿⣿⣿⡿⢟⣫⣭⣷⣶⣾⣭⣼⡻⢛⣛⣭⣭⣶⣶⣬⣭⣅⡀⠄⠄⠄⠄⠄⠄⠀⠀⠀⠀⠀⠀ ⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⡀⠀      \n"
                +"⣿⡿⢏⣵⣾⣿⣿⣿⡿⢉⡉⠙⢿⣇⢻⣿⣿⣿⣿⡟⠉⠉⢻⡷⠄⠄⠄⠄⠄⠄ ⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡗       \n"
                +"⣿⣷⣾⣍⣛⢿⣿⣿⣿⣤⣁⣤⣿⢏⠸⣿⣿⣿⣿⣷⣬⣥⣾⠁⣿⣿⣷⠄⠄⠄⠀⠀   ⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀   \n"
                +"⣿⣿⣿⣿⣭⣕⣒⠿⠭⠭⠭⡷⢖⣫⣶⣶⣬⣭⣭⣭⣭⣥⡶⢣⣿⣿⣿⠄⠄⠄⠀⠀⠀⠀⠀⠘⢿⣿⠁⣩⣿⣿⣿⠿⣿⡿⢿⣿⣿⣿⠛⣿⡟⠀⠀⠀⠀⠀  \n"
                +"⣿⣿⣿⣿⣿⣿⣿⡿⣟⣛⣭⣾⣿⣿⣿⣝⡛⣿⢟⣛⣛⣁⣀⣸⣿⣿⣿⣀⣀⣀⠀⠀⠀⠀   ⢷⣾⣿⣋⡡⠤⣀⣷⣄⠠⠤⣉⣿⣷⣽⠀⠀⠀⠀⠀⠀  \n"
                +"⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀   ⠈⣿⣿⣿⣿⣿⣿⣿⣿⡻⣾⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀  \n"
                +"⣿⡿⢛⣛⣛⣛⣙⣛⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣬⣭⣭⠽⣛⢻⣿⣿⣿⠛⠛⠛⠀⠀⠀⠀⠀⠀⠀ ⠙⣿⣟⢋⣰⣯⠉⠉⣿⢄⠉⢻⡟⠀⠀⠀⠀⠀⠀⠀  \n"
                +"⣿⢰⣿⣿⣿⣿⣟⣛⣛⣶⠶⠶⠶⣦⣭⣭⣭⣭⣶⡶⠶⣾⠟⢸⣿⣿⣿⠄⠄⠄⠀⠀⠀⠀⠀⠀   ⠹⣿⢿⣷⣶⠤⠔⣶⣶⠿⢾⣧⠀⠀⠀⠀⠀⠀⠀  \n"
                +"⡻⢮⣭⣭⣭⣭⣉⣛⣛⡻⠿⠿⠷⠶⠶⠶⠶⣶⣶⣾⣿⠟⢣⣬⣛⡻⢱⣇⠄⠄   ⢀⡀⠠⠀⠂⠀⠀⣧⡚⢿⣿⡶⢶⡿⠟⣠⣿⣿⠀⠀⠀⠀⠄⣀⡀  \n"
                +"⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⠶⠒⠄⠄⠄⢸⣿⢟⣫⡥⡆⠄⠄⠒⠒⠋⠁⠀⠀⠀⠀⠀⠀⢿⣷⣄⡀⠀⠀⠀⣈⣴⣿⣿⠀⠀⠀⠀⠀⠀⠀⠉⠒\n"
                +"⢭⣭⣝⣛⣛⣛⣛⣛⣛⣛⣿⣿⡿⢛⣋⡉⠁⠄⠄⠄⠄⠄⢸⣿⢸⣿⣧⡅⠄⠄⠀⠀⠀⠀⠀⠀⠀   ⢸⣿⣿⡿⠒⠐⠺⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                +"⣶⣶⣶⣭⣭⣭⣭⣭⣭⣵⣶⣶⣶⣿⣿⣿⣦⡀⠄⠄⠄⠄⠈⠡⣿⣿⡯⠁⠄⠄          ⢸⢿⣋⣀⡄⠠⣢⣀⣩⣛⠇       \n\n");
        System.out.print("+----------------------------------------------------------+\n"
                        +"| Hail and blessings upon thy soul, for gracing my program |\n"
                        +"| with thy presence! Come hither, and I shall show thee    |\n"
                        +"| that which thou must needs know ere thou dost proceed... |\n"
                        +"+----------------------------------------------------------+\n\n");
        System.out.print("------------------------------------------------------------\n"
                        +"1. Chunk size refers to the amount of pixels compressing,   \n"
                        +"for example 3 means that 3x3 pixels are read as one.        \n"
                        +"So the bigger the number, the smaller the \'photo\'.        \n"
                        +"Typing 1 as the chunk size is not recommended, since it's   \n"
                        +"going to print the original picture, which will result in   \n"
                        +"lag and a big text file.                                  \n\n"
                        +"2. Currently, the saved .txt file will be saved in the dir  \n"
                        +"this program was opened. Custom directory selection not     \n"
                        +"available yet (maybe some other time)                     \n\n"
                        +"3. When you open the created .txt file, make sure to        \n"
                        +"reduce font size (ctrl - or cmd -)                          \n"
                        +"4. It is highly recommended to use a square font, since it  \n"
                        +"will make the image look better. "
                        +"------------------------------------------------------------\n\n");//}}}

        System.out.println("PATH to your image (press TAB for suggestions): ");
        filename = reader.readLine("> ").trim(); // tab completion

        System.out.print("Select chunk size (bigger chunk means smaller, blurrier picture) (ex. 3 or 4):  ");
        int size = Integer.parseInt(reader.readLine("> "));

        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();
        ImageCulling imageCulling = new ImageCulling(width, height, size ); // Adjust width, height, and chunk size
        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

        System.out.println("Image width: " + width);
        System.out.println("Image height: "+ height);
        System.out.println("Aspect ratio: " + getAspectRatio(width, height));

        int count = 0;
        for(int i = 0; i < pixels.length; i++){
            int p = pixels[i];

            int a = ( p >> 24) & 0xFF;
            int red = (p >> 16) & 0xFF;
            int green = (p >> 8) & 0xFF;
            int blue = p & 0xFF;

            int avg = (red + green + blue) / 3;
            p = (a << 24) | (avg << 16) | (avg << 8) | avg;
            pixels[i] = avg;  //was pixels[i] = p
        }

        image.setRGB(0, 0, width, height, pixels, 0, width);
        Scanner in = new Scanner(System.in);

        System.out.print("\nName your file (omit extensions): ");
        filename = in.nextLine();

        if (filename.isEmpty()) {
            System.out.println("Invalid filename. Exiting.");
            return;
        }

        File outputFile = new File( filename + ".txt");
        if (outputFile.exists()) {
            System.out.println("File already exists. Exiting.");
            return;
        }

        imageCulling.processImage(pixels);
        imageCulling.printNewArray(filename);
    }

    /**
     * Calculates the aspect ratio of the image.
     *
     * @param width  The width of the image.
     * @param height The height of the image.
     * @return A string representing the aspect ratio in the format "width:height".
     */
    public static String getAspectRatio(int width, int height) {
        int gcd = gcd(width, height);
        int aspectWidth = width / gcd;
        int aspectHeight = height / gcd;
        return aspectWidth + ":" + aspectHeight;
    }

    /**
     * Calculates the greatest common divisor (GCD) of two integers.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @return The GCD of the two integers.
     */
    public static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
