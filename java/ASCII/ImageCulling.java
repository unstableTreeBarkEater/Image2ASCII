//salam
public class ImageCulling {
    private int WIDTH;
    private int HEIGHT;
    private int CHUNK_SIZE;
    private int CHUNK;
    private int[][] new_array;
    private static String density = "Ñ@#W$9876543210?!abc;:+=-,._ "; //28 characters

    public ImageCulling(int width, int height, int chunkSize) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CHUNK_SIZE = chunkSize;
        this.CHUNK = CHUNK_SIZE * CHUNK_SIZE;
        this.new_array = new int[HEIGHT / CHUNK_SIZE + 1][WIDTH / CHUNK_SIZE + 1];
    }

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
                // print(new_array[new_y][new_x]);
                // printCharArray();
            }
        }
    }

    // public void printCharArray()
    public void printArray(int[] arr) {
        int count = 0;
        System.out.print("This is the original array \n");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("count: " + count + " array density: " + arr[i]);
            // count++;
            if (count % WIDTH == 0) System.out.print("\n");
        }
        System.out.print("\n\n\n");
    }

    public void printNewArray() {
        // int count = 0;
        System.out.print("This is the compressed version of the array \n");
        for (int i = 0; i < HEIGHT / CHUNK_SIZE; i++) {
            for (int j = 0; j < WIDTH / CHUNK_SIZE; j++) {
                // System.out.printf("%3s ", new_array[i][j] / CHUNK);
                // System.out.println("count: " + count);
                // count++;
                System.out.print(denser(new_array[i][j]/CHUNK));
            }
            System.out.print("\n");
        }
    }

    public static char denser(int avg){
        int count = 28;
        for(int bit = 0; bit < 255; bit++){
            if(bit != 0 && bit % 9 == 0) density.charAt(count--);
            if(bit == avg) return density.charAt(count);
        }
        return ' ';
    }

    public void print(int value) { //compressed version of the array
        System.out.println("Value: " + value);
    }
}

