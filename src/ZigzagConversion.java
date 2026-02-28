public class ZigzagConversion {

    public ZigzagConversion() {
        System.out.println(convert("PAYPALISHIRING", 3));
        System.out.println(convert("PAYPALISHIRING", 4));
        System.out.println(convert("A", 1));
    }

    public String convert(String s, int zig) {
        int zag = Math.max(zig - 2, 0);
        var sb = new StringBuilder();
        for (int j = 0; j < zig; j++) {
            for (int i = 0; i < s.length(); i++) {
                if (i % (zig + zag) == j) {
                    sb.append(s.charAt(i));
                } else if ((zig + zag) - j == i % (zig + zag) && j != 0 && j != zig - 1) {
                    sb.append(s.charAt(i));
                }
            }
        }

//        for (int i = 0; i < s.length(); i++) {
//            if (i % (zig + zag) == numRows - 1) {
//                sb.append(s.charAt(i));
//            }
//        }

        return sb.toString();

    }

    public static void main(String[] args) {
        new ZigzagConversion();
    }
}
