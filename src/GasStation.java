public class GasStation {

    public GasStation() {
        canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2});
        canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3});
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        int start = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            int remain = gas[i] - cost[i];
            sum+= remain;
            tank += remain;
            if (tank < 0) {
                tank = 0;
                start = i + 1;
            }
        }

        if (sum < 0 ) {
            return -1;
        }
//        System.out.println(start);

        return start;
    }

    public static void main(String[] args) {
        new GasStation();
    }
}
