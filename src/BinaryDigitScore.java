
/**
 * 4092. Binary Digit Score (Easy)
 * https://leetcode.com/problems/binary-digit-score/
 *
 * @author Timothy Julian
 * @version $Id: BinaryDigitScore.java, v 0.1 2026‐11‐10 16.28 timothy.julian Exp $$
 */
public class BinaryDigitScore {

    public BinaryDigitScore() {
        Judge j = new Judge();
        j.check("6", calculateScore(14, 6));
        j.check("2", calculateScore(5, 15));
        j.check("4", calculateScore(10, 12));
        j.done();
    }

    // ==== leetcode:begin ====
    public int calculateScore(int a, int b) {
        int aCount = countOne(a);
        int bCount = countOne(b);
        if (aCount >= bCount) return aCount * bCount;
        else return bCount / aCount;
    }

    public int countOne(int a) {
        int count = 0;
        while (a > 0) {
            count += a % 2;
            a /= 2;
        }

        return count;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new BinaryDigitScore();
    }
}
