import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ASCIIProject{
    private static String filename;
    private static String density = "Ñ@#W$9876543210?!abc;:+=-,._ ";

    public static void main(String[] args) throws IOException{
        
        filename = "/Users/javidanaghayev/Downloads/lincoln.jpg";
        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);
;
        int x = 50; //finds the RGB value at x axis
        int y = 50; //finds the RGB value at y axis

        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);

        System.out.println("Image width: " + width);
        System.out.println("Image height: "+ height);
        System.out.println("Aspect ratio: " + getAspectRatio(width, height));
        RGBValues(x, y);

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

    public static void RGBValues(int x, int y) throws IOException{
        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);

        int rgb = image.getRGB(x, y);
        int a = (rgb >> 24) & 0xFF;    
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        System.out.println("RGB values at (" + x + ", " + y + "):");
        System.out.println("Red: " + red);
        System.out.println("Green: " + green);
        System.out.println("Blue: " + blue);
    }
}
