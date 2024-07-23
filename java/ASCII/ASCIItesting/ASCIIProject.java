import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.util.Scanner;

//TODO
//ASK THE USER TO SPECIFY THEIR DIRECTORY IN WHICH THE FILE WILL BE SAVED, THE PRIORITY
// write into a .txt file instead of making another image
// remove grayscale file creating
// instead of making it cli based, remove any arguments required in cli
// make it interactable, let the user choose the chunk size, maybe even add a confirmation
// ask whether they want their result printed in the cli and saved or just saved.
// maybe put a decorative introduction to make the program look a bit welcoming

public class ASCIIProject{
    private static String filename;
    private static String fileDirectory;

    public static void main(String[] args) throws IOException{
        // try{
        //     filename = args[0];
        // }catch (NullPointerException e){
        //     System.exit(0);
        // }catch (ArrayIndexOutOfBoundsException e){
        //     System.out.println("Please enter a file name!");
        //     System.exit(0);
        // }

        Scanner input = new Scanner(System.in);


        System.out.print("⠄⢀⣀⣤⣴⣶⣶⣤⣄⡀⠄⠄⣀⣤⣤⣤⣤⡀⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n"
                +"⣴⣏⣹⣿⠿⠿⠿⠿⢿⣿⣄⢿⣿⣿⣿⣿⣿⣋⣷⡄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n"
                +"⣿⢟⣩⣶⣾⣿⣿⣿⣶⣮⣭⡂⢛⣭⣭⣭⣭⣭⣍⣛⣂⡀⠄⠄⠄⠄⠄⠄⠄⠄\n"
                +"⣿⣿⣿⣿⡿⢟⣫⣭⣷⣶⣾⣭⣼⡻⢛⣛⣭⣭⣶⣶⣬⣭⣅⡀⠄⠄⠄⠄⠄⠄\n"
                +"⣿⡿⢏⣵⣾⣿⣿⣿⡿⢉⡉⠙⢿⣇⢻⣿⣿⣿⣿⡟⠉⠉⢻⡷⠄⠄⠄⠄⠄⠄\n"
                +"⣿⣷⣾⣍⣛⢿⣿⣿⣿⣤⣁⣤⣿⢏⠸⣿⣿⣿⣿⣷⣬⣥⣾⠁⣿⣿⣷⠄⠄⠄\n"
                +"⣿⣿⣿⣿⣭⣕⣒⠿⠭⠭⠭⡷⢖⣫⣶⣶⣬⣭⣭⣭⣭⣥⡶⢣⣿⣿⣿⠄⠄⠄\n"
                +"⣿⣿⣿⣿⣿⣿⣿⡿⣟⣛⣭⣾⣿⣿⣿⣝⡛⣿⢟⣛⣛⣁⣀⣸⣿⣿⣿⣀⣀⣀\n"
                +"⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n"
                +"⣿⡿⢛⣛⣛⣛⣙⣛⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣬⣭⣭⠽⣛⢻⣿⣿⣿⠛⠛⠛\n"
                +"⣿⢰⣿⣿⣿⣿⣟⣛⣛⣶⠶⠶⠶⣦⣭⣭⣭⣭⣶⡶⠶⣾⠟⢸⣿⣿⣿⠄⠄⠄\n"
                +"⡻⢮⣭⣭⣭⣭⣉⣛⣛⡻⠿⠿⠷⠶⠶⠶⠶⣶⣶⣾⣿⠟⢣⣬⣛⡻⢱⣇⠄⠄\n"
                +"⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⠶⠒⠄⠄⠄⢸⣿⢟⣫⡥⡆⠄⠄\n"
                +"⢭⣭⣝⣛⣛⣛⣛⣛⣛⣛⣿⣿⡿⢛⣋⡉⠁⠄⠄⠄⠄⠄⢸⣿⢸⣿⣧⡅⠄⠄\n"
                +"⣶⣶⣶⣭⣭⣭⣭⣭⣭⣵⣶⣶⣶⣿⣿⣿⣦⡀⠄⠄⠄⠄⠈⠡⣿⣿⡯⠁⠄⠄\n\n");
        System.out.print("+------------------------------------+\n"
                        +"|Hello and bless thy soul, for coming|\n"
                        +"|to my program! Come, I will show you|\n"
                        +"|thing you NEED to know before       |\n"
                        +"|proceeding...                       |\n"
                        +"+------------------------------------+\n\n");
        System.out.print("1. This program is still in the works, so I recommend you \n"
                        +"to use photos that you are not afraid to lose, or ones \n"
                        +"you have a copy of.\n\n"
                        +"2. NEVER USE THE ORIGINAL PHOTO, SINCE IRREVERSIBLE \n"
                        +"CHANGES WILL BE MADE TO THEM\n\n\n");
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

        System.out.print("enter path to your image: ");
        filename = input.nextLine();

        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();
        ImageCulling imageCulling = new ImageCulling(width, height, 2 ); // Adjust width, height, and chunk size
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
            // p = (a << 24) | (avg << 16) | (avg << 8) | avg;
            p = (a << 24) | (avg << 16) | (avg << 8) | avg;
            pixels[i] = avg;  //was pixels[i] = p
            // System.out.println(count+ ": " + avg);
            count++;
            // System.out.print(denser(pixels[i])); //perhaps we need to put this function call somewhere else
        }

        image.setRGB(0, 0, width, height, pixels, 0, width);

        // try {
        //     System.out.print("Where do you want your file to be stored? (current dir if blank): ");
        //     fileDirectory = input.nextLine();
        //     if(fileDirectory.equals("")) {
        //         System.out.println("Your file will be saved in: " + System.getProperty("user.dir"));
        //     }
        //     System.out.print("Name your file (omit extensions): ");
        //     filename = input.nextLine();
        //     File fileDir = new File(fileDirectory, filename);

        //     file = new File( filename.replace(".jpg" , "_grayscale.jpg"));
        //     // file = new File(filename.replace(".jpeg" , "_grayscale.jpg"));
        //     // file = new File(filename.replace(".png" , "_grayscale.png"));
        //     ImageIO.write(image, "jpg", file);
        // } catch (IOException e) {
        //     System.out.println(e);
        // }








        try {
            System.out.print("Where do you want your file to be stored? (current dir if blank): ");
            String fileDirectory = input.nextLine().trim();
            if (fileDirectory.equals("")) {
                fileDirectory = System.getProperty("user.dir");
                System.out.println("Your file will be saved in: " + fileDirectory);
            } else {
                File dir = new File(fileDirectory);
                if (!dir.exists()) {
                    dir.mkdirs(); // Create the directory if it doesn't exist
                }
            }

            System.out.print("Name your file (omit extensions): ");
            String filename = input.nextLine().trim();
            if (filename.equals("")) {
                System.out.println("Invalid filename. Exiting.");
                return;
            }

            // Set the output file
            File f = new File(fileDirectory, filename + "_grayscale.jpg");

            // Assume `image` is your BufferedImage object containing the grayscale image
            ImageIO.write(image, "jpg", f);
            System.out.println("File saved successfully at " + f.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }











        imageCulling.processImage(pixels); //does actually process and place new average density into array
        // imageCulling.printNewArray();      //does actually print new array
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
