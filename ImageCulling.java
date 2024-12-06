//salam
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The ImageCulling class processes an image array by compressing it into chunks
 * and converting it into ASCII art based on pixel intensity.
 */
public class ImageCulling {
    private int WIDTH;
    private int HEIGHT;
    private int CHUNK_SIZE;
    private int CHUNK;
    private int[][] new_array;
    private static String density = "Ñ@#W$9876543210?!abc;:+=-,._ "; // 28 characters

    /**
     * Constructs an ImageCulling object with the specified dimensions and chunk size.
     *
     * @param width     The width of the image.
     * @param height    The height of the image.
     * @param chunkSize The size of the chunk to divide the image into.
     */
    public ImageCulling(int width, int height, int chunkSize) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CHUNK_SIZE = chunkSize;
        this.CHUNK = CHUNK_SIZE * CHUNK_SIZE;
        this.new_array = new int[HEIGHT / CHUNK_SIZE + 1][WIDTH / CHUNK_SIZE + 1];
    }

    /**
     * Processes the pixel array and maps it to a smaller array by summing up pixel values
     * within each chunk.
     *
     * @param arr The original pixel array.
     */
    public void processImage(int[] arr) {
        int new_x = 0;
        int new_y = 0;

        for (int y = 0; y < HEIGHT; y++) {
            new_x = 0;
            if (y != 0 && y % CHUNK_SIZE == 0) {
                new_y++;
            }
            for (int x = 0; x < WIDTH; x++) {
                if (x != 0 && x % CHUNK_SIZE == 0) {
                    new_x++;
                }
                new_array[new_y][new_x] += arr[WIDTH * y + x];
            }
        }
    }

    /**
     * Prints the original array with density values to the terminal.
     * Used for debugging purposes.
     *
     * @param arr The original pixel array.
     */
    public void printArray(int[] arr) {
        int count = 0;
        System.out.print("This is the original array \n");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("count: " + count + " array density: " + arr[i]);
            if (count % WIDTH == 0) System.out.print("\n");
        }
        System.out.print("\n\n\n");
    }

    /**
     * Prints the compressed ASCII art representation of the image to a file
     * and the console.
     *
     * @param filename The name of the output file (without extension).
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void printNewArray(String filename) throws IOException{
        PrintWriter writer = new PrintWriter(filename+".txt", "UTF-8");
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        System.out.print("This is the compressed version of the art \n");
        for (int i = 0; i < HEIGHT / CHUNK_SIZE; i++) {
            for (int j = 0; j < WIDTH / CHUNK_SIZE; j++) {
                System.out.print(denser(new_array[i][j]/CHUNK));
                writer.print(denser(new_array[i][j]/CHUNK));
            }
            System.out.print("\n");
            writer.print("\n");
        }
        writer.close();
    }

    /**
     * Maps an average pixel brightness to a character from the density string.
     *
     * @param avg The average pixel intensity for a chunk.
     * @return A character representing the intensity.
     */
    public static char denser(int avg){
        int count = density.length() - 1;
        for(int bit = 0; bit < 255; bit++){
            if(bit != 0 && bit % 9 == 0) density.charAt(count--);
            if(bit == avg) return density.charAt(count);
        }
        return ' ';
    }
}

