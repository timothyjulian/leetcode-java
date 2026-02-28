import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HIndex {
    public HIndex() {
        hIndex(new int[]{3,0,6,1,5});
        hIndex(new int[]{1,3,1});
        hIndex(new int[]{100});
        hIndex(new int[]{0, 0, 2});
        hIndex(new int[]{0});
    }

    public int hIndex(int[] citations) {
//        Arrays.sort(citations);
//        System.out.println(Arrays.toString(citations));
        int hIndex = 0;
        while (true) {
            int count = 0;
            if (hIndex > citations.length) {
                break;
            }
            for (int citation : citations) {
//                System.out.println(count);
                if (citation >= hIndex) {
                    count++;
                }
            }
            if (count < hIndex) {
                break;
            } else {
                hIndex++;
            }
        }
        hIndex--;
//        System.out.println(hIndex);

        return hIndex;
    }


    public static void main(String[] args) {
        new HIndex();
    }
}
