public class ZigZagConversionNEW2 {

    public ZigZagConversionNEW2() {
        System.out.println(convert("PAYPALISHIRING", 3));
        System.out.println(convert("PAYPALISHIRING", 4));
        System.out.println(convert("A", 1));
        System.out.println(convert("AB", 1));
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        var sb = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sb[i] = new StringBuilder();
        }

        int i = 0;
        int n = s.length();
        int h = 0;
        while (i < n) {
            while (h < numRows - 1 && i < n) {
                sb[h].append(s.charAt(i));
                h++;
                i++;
            }
            while (h > 0 && i < n) {
                sb[h].append(s.charAt(i));
                h--;
                i++;
            }
        }

        var last = new StringBuilder();
        for (var sbTemp: sb) {
            last.append(sbTemp);
        }

        return last.toString();

    }


    public static void main(String[] args) {
        new ZigZagConversionNEW2();
    }
}
