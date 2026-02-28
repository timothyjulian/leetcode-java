import java.lang.reflect.Array;
import java.util.Arrays;

public class RotateImage {

    public RotateImage() {
        rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }

    public void rotate(int[][] matrix) {
//        printArray(matrix);

        transpose(matrix);

//        printArray(matrix);

        reverse(matrix);

//        printArray(matrix);
    }

    public void transpose(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
//                System.out.printf("%d %d\n", matrix[i][j], matrix[j][i]);
            }
        }
    }

    public void reverse(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++){
            int l = 0;
            int r = matrix[i].length - 1;
            while (l < r) {
                int temp = matrix[i][l];
                matrix[i][l] = matrix[i][r];
                matrix[i][r] = temp;
                l++;
                r--;
            }
        }
    }

    public void printArray(int[][] matrix) {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void main(String[] args) {
        new RotateImage();

//        String test = "[[1,2,3],[4,5,6],[7,8,9]]";
//        System.out.println(test.replace("[", "{").replace("]", "}"));
    }
}
