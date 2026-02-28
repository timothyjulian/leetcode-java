import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HIndexNew {

    public HIndexNew() {
        hIndex(new int[]{3,0,6,1,5});
        hIndex(new int[]{1,3,1});
        hIndex(new int[]{100});
        hIndex(new int[]{0, 0, 2});
        hIndex(new int[]{0});
    }

    public int hIndex(int[] citations) {
        List<Integer> test = new ArrayList<>();
        Arrays.sort(citations);
        int l = 0;
        int r = citations.length - 1;
        while (l < r) {
            int temp = citations[r];
            citations[r] = citations[l];
            citations[l] = temp;
            l++;
            r--;
        }

        int hIndex = 0;
        for (int i = 0; i < citations.length; i++) {
            if (hIndex + 1 > citations[i]) {
                break;
            }

            hIndex++;
        }

//        System.out.println(Arrays.toString(citations));
//        System.out.println(hIndex);

        return hIndex;
    }


    public static void main(String[] args) {
        new HIndexNew();
    }
}
