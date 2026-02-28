public class IndexOfTheFirstOccurance {

    public IndexOfTheFirstOccurance() {
        System.out.println(strStr("sadbutsad", "sad"));
        System.out.println(strStr("leetcode", "leeto"));
    }

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }


    public static void main(String[] args) {
        new IndexOfTheFirstOccurance();
    }
}
