import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ASCIIProject{
   private static String filename;
   private static String density = "Ñ@#W$9876543210?!abc;:+=-,._ ";

   public static void main(String[] args) throws IOException{
     try{
      filename = args[0];
     }catch (ArrayIndexOutOfBoundsException e){
        System.out.println("Please enter a file name!");
     }catch (NullPointerException e){
        System.err.println("you moron");
     }

      File file = new File(filename);
      BufferedImage image = ImageIO.read(file);
      int width = image.getWidth();
      int height = image.getHeight();
      int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

      // System.out.println("Image width: " + width);
      // System.out.println("Image height: "+ height);
      // System.out.println("Aspect ratio: " + getAspectRatio(width, height));

      for(int i = 0; i < pixels.length; i++){

         int p = pixels[i];

         int a = ( p >> 24) & 0xFF;    
         int red = (p >> 16) & 0xFF;
         int green = (p >> 8) & 0xFF;
         int blue = p & 0xFF;

         int avg = (red + green + blue) / 3;
         p = (a << 24) | (avg << 16) | (avg << 8) | avg;
         pixels[i] = p;
      }
      image.setRGB(0, 0, width, height, pixels, 0, width);
      try {
         file = new File(filename.replace(".jpg", "_grayscale.jpg"));
         ImageIO.write(image, "jpg", file);
      } catch (IOException e) {
         System.out.println(e);
      }
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
