import java.util.HashMap;
import java.util.Map;

public class ValidSudoku {

    public ValidSudoku() {
        System.out.println(isValidSudoku(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                ,{'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                ,{'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                ,{'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                ,{'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                ,{'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                ,{'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                ,{'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                ,{'.', '.', '.', '.', '8', '.', '.', '7', '9'}}));
    }

    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowMap = new boolean[9][9];
        boolean[][] colMap = new boolean[9][9];
        boolean[][] boxMap = new boolean[9][9];
        for (int i = 0; i< board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
//                System.out.println(box);
                if (board[i][j] == '.') continue;
                int num = board[i][j] - '1';
                if (!rowMap[i][num]) {
                    rowMap[i][num] = true;
                } else {
//                    System.out.println("row");
                    return false;
                }

                if (!colMap[j][num]) {
                    colMap[j][num] = true;
                } else {
//                    System.out.println("col");
                    return false;
                }

                int box = i / 3 * 3 + j / 3;
                if (!boxMap[box][num]) {
                    boxMap[box][num] = true;
                } else {
//                    System.out.println("box");
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        new ValidSudoku();
    }
}
