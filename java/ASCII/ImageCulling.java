//salam
public class ImageCulling{
    private static int WIDTH = 17;
    private static int HEIGHT = 23;
    private static int avg = 0;
    private static int CHUNK_SIZE = 3;
    private static int CHUNK = CHUNK_SIZE*CHUNK_SIZE;
    private static int new_x = 0;
    private static int new_y = 0;
    private static int[][] new_array = new int[HEIGHT/CHUNK_SIZE+1][WIDTH/CHUNK_SIZE+1];

    //establishing the original array
    int[] arr = new int[HEIGHT*WIDTH];
    for(int i = 0; i < arr.length; i++){
        arr[i] = i;
    }

    //printing the original array
    int count = 0;
    System.out.print("This is the original array \n");
    for(int i = 0; i < arr.length; i++){
        System.out.printf("%-5s",arr[i]);
        count++;
        if(count % WIDTH == 0) System.out.print("\n");
    }
    System.out.print("\n\n\n");

    for(int y = 0; y < HEIGHT; y++){
        new_x = 0;
        if(y != 0 && y % CHUNK_SIZE == 0) {
            new_y++;
        }
        for(int x = 0; x < WIDTH; x++){
            if(x != 0 && x % CHUNK_SIZE == 0){
                new_x++;
            }
            new_array[new_y][new_x] += arr[WIDTH * y + x];
        }
    }

    //printing the new array
    System.out.print("This is the compressed version of the array \n");
    for(int i = 0; i < HEIGHT/CHUNK_SIZE; i++){
        for(int j = 0; j < WIDTH/CHUNK_SIZE; j++){
            System.out.printf("%3s ", new_array[i][j]/CHUNK);
        }
        System.out.print("\n");
    }
}
