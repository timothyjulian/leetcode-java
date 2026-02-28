import java.util.Arrays;

public class LexReverse {

    public LexReverse() {
        System.out.println(lexSmallest("dcab"));
        System.out.println(lexSmallest("abba"));
        System.out.println(lexSmallest("caa"));
        System.out.println(lexSmallest("zxy"));
        System.out.println(lexSmallest("baca"));
        System.out.println(lexSmallest("loonlmonm"));
    }

    public String lexSmallest(String s) {
        char[] sArr = s.toCharArray();
        String smallestLex = s;
        for (int i = 1; i < sArr.length; i++) {
            StringBuilder temp = new StringBuilder();
            for (int j = i; j >= 0; j--) {
                temp.append(sArr[j]);
            }

            for (int j = i + 1; j < sArr.length; j++) {
                temp.append(sArr[j]);
            }

//            System.out.printf("%s %s\n", temp, smallestLex);
//            System.out.printf("%d\n", temp.toString().compareTo(smallestLex));

            if (temp.toString().compareTo(smallestLex) < 0) {
                smallestLex = temp.toString();
            }

        }

//        if (!smallestLex.equals(s)) {
//            return smallestLex;
//        }

        for (int i = sArr.length - 2; i >= 0; i--) {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < i; j++) {
                temp.append(sArr[j]);
            }

            for (int j = sArr.length - 1; j >= i; j--) {
                temp.append(sArr[j]);
            }

//            System.out.printf("%s %s\n", temp, smallestLex);
//            System.out.printf("%d\n", temp.toString().compareTo(smallestLex));

            if (temp.toString().compareTo(smallestLex) < 0) {
                smallestLex = temp.toString();
            }
        }
//        System.out.printf("result : %s %s", smallestLex, s);

        return smallestLex;
    }

    public static void main(String[] args) {
        new LexReverse();
//        System.out.println("acdb".compareTo("dcab"));
    }
}
