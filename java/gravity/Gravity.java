//salam
import java.util.Random;

public class Gravity{
    static char[][] charray = new char[50][200];
    static char space = ' ';
    static char ch = '#';

    public static void main(String[] args){
        Random random = new Random();

        for(int i = 0; i < charray.length; i++){
            for(int j = 0; j < charray[i].length; j++){
                int randint = random.nextInt(3);
                if(randint == 1){
                    charray[i][j] = ch;
                }else{
                    charray[i][j] = space;
                }
            }
        }
        print(charray);
        System.out.println("end of the first array");

        try {
            for(;;){
                Thread.sleep(250);
                System.out.print("\033[2J\033[H");
                for(int i = 0; i < charray.length-1; i++){
                    for(int j = 0; j < charray[i].length; j++){
                        fall(j, i);
                    }
                }
                print( charray);
            }
        }catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void print(char[][] charray) {
        for (int i = 0; i < charray.length; i++) {
            System.out.println(new String(charray[i]));
        }
    }

    // public static void print(char[][] charray){
    //     for(int i = 0; i < charray.length; i++){
    //         for(int j = 0; j < charray[i].length; j++){
    //             System.out.print(charray[i][j]);
    //         }
    //         System.out.println();
    //     }
    // }

    public static void fall(int x, int y){
        if(charray[y][x] == space) return;
        if(charray[y+1][x] == space){
            charray[y][x] = space;
            charray[y+1][x] = ch;
        }else{
            return;
        }
    }
}
