import java.util.Arrays;

public class ZigZagConversionNEW {

    public ZigZagConversionNEW() {
        System.out.println(convert("PAYPALISHIRING", 3));
        System.out.println(convert("PAYPALISHIRING", 4));
        System.out.println(convert("A", 1));
        System.out.println(convert("AB", 1));
    }

    public String convert(String s, int numRows) {
        char[][] field = new char[numRows][s.length()];
        int h = 0;
        int w = 0;
        int i = 0;
        boolean upward = false;
        if (numRows == 1) {
            return s;
        }
        while (i < s.length()) {
//            printArray(field);
            field[h][w] = s.charAt(i);
            if (h < numRows - 1 && !upward) {
                h++;
            } else {
                upward = true;
                h--;
                w++;
                if (h == 0) {
                    upward = false;
                }
            }
            i++;
        }

//        printArray(field);

        var sb = new StringBuilder();
        for (char[] chars : field) {
//            System.out.println(Arrays.toString(chars));
            for (char c: chars) {
                if (c != '\0') {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }

    private void printArray(char[][] num) {
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i].length; j++) {
                System.out.printf("%c ", num[i][j]);
//                System.out.println(num[i][j] == '\0');
            }
            System.out.println();
        }
        System.out.println("====");
    }

    public static void main(String[] args) {
        new ZigZagConversionNEW();
    }
}
