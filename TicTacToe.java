import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

public class TicTacToe {
    static ArrayList playerpositions = new ArrayList();
    static ArrayList cpupositions = new ArrayList();
    public static void main(String[] args) {
        char[][] gameboard = new char[][]{{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        printgameboard(gameboard);


        while (true){
            Scanner scan = new Scanner(System.in);
            System.out.println("enter ypur placement(1-9):");
            int playerpos =scan.nextInt();
            while (playerpositions.contains(playerpos) || cpupositions.contains(playerpos)){
                System.out.println("position taken! Enter correct position");
                playerpos= scan.nextInt();

            }

            placepeice(gameboard,playerpos,"player");
            String result = checkwinner();
            if (result.length()>0){
                System.out.println(result);
                break;
            }


            Random rand = new Random();
            int cpupos = rand.nextInt(9)+1;
            while (playerpositions.contains(cpupos) || cpupositions.contains(cpupos)){
                cpupos = rand.nextInt(9)+1;

            }

            placepeice(gameboard,cpupos,"cpu");

            printgameboard(gameboard);

            result = checkwinner();

            if (result.length()>0){
                 System.out.println(result);
                 break;
             }
        }

    }




    public static void printgameboard(char[][] gameboard) {
        char[][] var1 = gameboard;
        int var2 = gameboard.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            char[] row = var1[var3];
            char[] var5 = row;
            int var6 = row.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                char c = var5[var7];
                System.out.print(c);
            }

            System.out.println();
        }

    }

    public static void placepeice(char[][] gameboard, int pos, String user ) {
        char symbol =' ';

        if(user.equals("player")){
            symbol = 'x';
            playerpositions.add(pos);
        }else if (user.equals("cpu")){
            symbol = 'O';
            cpupositions.add(pos);
        }

        switch (pos){
            case 1:
                gameboard[0][0] = symbol;
                break;
            case 2:
                gameboard[0][2] = symbol;
                break;
            case 3:
                gameboard[0][4] = symbol;
                break;
            case 4:
                gameboard[2][0] = symbol;
                break;
            case 5:
                gameboard[2][2] = symbol;
                break;
            case 6:
                gameboard[2][4] = symbol;
                break;
            case 7:
                gameboard[4][0] = symbol;
                break;
            case 8:
                gameboard[4][2] = symbol;
                break;
            case 9:
                gameboard[4][4] = symbol;
                break;
            default:
                break;
        }

    }

    public static String checkwinner() {

        List<Integer> toprow = Arrays.asList(1,2,3);
        List<Integer> midrow = Arrays.asList(4,5,6);
        List<Integer> botrow = Arrays.asList(7,8,9);
        List<Integer> topcol = Arrays.asList(1,4,7);
        List<Integer> midcol = Arrays.asList(2,5,8);
        List<Integer> botcol = Arrays.asList(3,6,9);
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(7,5,3);

        List<List> winning = new ArrayList<>();
        winning.add(toprow);
        winning.add(midrow);
        winning.add(botrow);
        winning.add(topcol);
        winning.add(midcol);
        winning.add(botcol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning){
            if (playerpositions.containsAll(l)){
                return "congragulations you won!";
            }else if (cpupositions.containsAll(l)){
                return "cpu wins!";
            }else if (playerpositions.size() + cpupositions.size()==9){
                return "draw";
            }
        }

        return "";

    }
}
