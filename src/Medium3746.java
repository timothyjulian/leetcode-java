public class Medium3746 {
    public Medium3746() {
        System.out.println(minLengthAfterRemovals("aabbab"));
        System.out.println(minLengthAfterRemovals("aaaa"));
        System.out.println(minLengthAfterRemovals("aaabb"));
    }

    public int minLengthAfterRemovals(String s) {
        int a = 0;
        int b = 0;
        char[] sArr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if(sArr[i] == 'a') a++;
            else b++;
        }

        return Math.abs(a - b);
    }

    public static void main(String[] args) {
        new Medium3746();
    }
}
