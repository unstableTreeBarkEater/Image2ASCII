import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ASCIIProject{
    private static String filename;
    // private static String density = "Ñ@#W$9876543210?!abc;:+=-,._ "; //28 characters

    public static void main(String[] args) throws IOException{
        try{
            filename = args[0];
        }catch (NullPointerException e){
            System.exit(0);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please enter a file name!");
            System.exit(0);
        }

        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);


        int width = image.getWidth();
        int height = image.getHeight();
        ImageCulling imageCulling = new ImageCulling(width, height, 3 ); // Adjust width, height, and chunk size
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

        try {
            file = new File(filename.replace(".jpg", "_grayscale.jpg"));
            ImageIO.write(image, "jpg", file);
        } catch (IOException e) {
            System.out.println(e);
        }
        imageCulling.processImage(pixels); //does actually process and place new average density into array
        imageCulling.printNewArray();      //does actually print new array
        // imageCulling.printArray(pixels);

    }

    // public static char denser(int avg){
    //     int count = 0;
    //     for(int bit = 0; bit < 255; bit++){
    //         if(bit != 0 && bit % 9 == 0) density.charAt(count++);
    //         if(bit == avg) return density.charAt(count);
    //     }
    //     return ' ';
    // }

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
