//salam
public class ImageCulling {
    private int WIDTH;
    private int HEIGHT;
    private int CHUNK_SIZE;
    private int CHUNK;
    private int[][] new_array;

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
            }
        }
    }

    public void printArray(int[] arr) {
        int count = 0;
        System.out.print("This is the original array \n");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%-5s", arr[i]);
            count++;
            if (count % WIDTH == 0) System.out.print("\n");
        }
        System.out.print("\n\n\n");
    }

    public void printNewArray() {
        System.out.print("This is the compressed version of the array \n");
        for (int i = 0; i < HEIGHT / CHUNK_SIZE; i++) {
            for (int j = 0; j < WIDTH / CHUNK_SIZE; j++) {
                System.out.printf("%3s ", new_array[i][j] / CHUNK);
            }
            System.out.print("\n");
        }
    }
}

