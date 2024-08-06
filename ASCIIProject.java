//salam
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class ASCIIProject{
    private static String filename;

    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(System.in);
        int size;
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
                        +"1. This program will turn your photos into ASCII art, BUT   \n"
                        +"it is still in the works, so I recommend you                \n"
                        +"to use photos that you are not afraid to lose, lest they    \n"
                        +"become corrupted.                                         \n\n"
                        +"2. NEVER USE THE ORIGINAL PHOTO, SINCE IRREVERSIBLE         \n"
                        +"CHANGES COULD BE MADE TO THEM                             \n\n"
                        +"3. Chunk size refers to the amount of pixels compressing,   \n"
                        +"for example 3 means that 3x3 pixels are read as one.        \n"
                        +"So the bigger the number, the smaller the \'photo\'.        \n"
                        +"Typing 1 as the chunk size is not recommended, since it's   \n"
                        +"going to print the original picture, which will result in   \n"
                        +"lag and a big text file.                                  \n\n"
                        +"4. Currently, the saved .txt file will be saved in the dir  \n"
                        +"this program was opened. Custom directory selection not     \n"
                        +"available yet (maybe some other time)                     \n\n"
                        +"5. When you open the created .txt file, make sure to        \n"
                        +"reduce font size (ctrl - or cmd -)                          \n"
                        +"6. It is highly recommended to use a square font, since it  \n"
                        +"will make the image look better. "
                        +"------------------------------------------------------------\n\n");
        System.out.print("Are you ready to proceed? [y/n]: ");

        String confirmation = input.nextLine();
        while(!confirmation.equalsIgnoreCase("Y")){
            if(confirmation.equalsIgnoreCase("Y")){
                System.out.println("proceeding...");
            }else if(confirmation.equalsIgnoreCase("N") || confirmation.equalsIgnoreCase("NO")){
                System.out.println("Leaving program...");
                System.exit(1);
            }else{
                System.out.print("Are you ready to proceed? [y/n]: ");
                confirmation = input.nextLine();
            }
        }

        System.out.print("PATH to your image: ");
        filename = input.nextLine();

        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);

        System.out.print("Select chunk size (bigger chunk means smaller, blurrier picture) (ex. 3 or 4):  ");
        size = input.nextInt();

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

            int a = ( p >> 24) & 0xFF; //isolate pixel density value
            int red = (p >> 16) & 0xFF; //isolate red pixel value
            int green = (p >> 8) & 0xFF; //isolate green pixel value
            int blue = p & 0xFF; //isolate blue pixel value

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

        // System.out.println("File saved successfully at " + file.getAbsolutePath());

        imageCulling.processImage(pixels);
        imageCulling.printNewArray(filename);
        // imageCulling.printArray(pixels);
    }

    public static String getAspectRatio(int width, int height) {
        int gcd = gcd(width, height);
        int aspectWidth = width / gcd;
        int aspectHeight = height / gcd;
        return aspectWidth + ":" + aspectHeight;
    }

    public static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
