import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {
    private Map<Integer, Integer> map;
    private List<Integer> list;
    private Random random;
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
//        this.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
//        System.out.println(list.toString());
//        this.remove(2); // Returns false as 2 does not exist in the set.
//        System.out.println(list.toString());
//        this.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
//        System.out.println(list.toString());
//        this.getRandom(); // getRandom() should return either 1 or 2 randomly.
//        System.out.println(list.toString());
//        this.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
//        System.out.println(list.toString());
//        System.out.println(map.toString());
//        this.insert(2); // 2 was already in the set, so return false.
//        System.out.println(list.toString());
//        this.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
//        System.out.println(list.toString());
        this.insert(0);
        System.out.println(list.toString());
        this.remove(0);
        System.out.println(list.toString());
        System.out.println(map.toString());
        this.insert(0);
        System.out.println(list.toString());
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return  false;
        list.add(val);
        map.put(val, list.size() - 1);

        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int indexReplaced = map.get(val);
        int valueReplacement = list.getLast();
//        System.out.printf("%d, %d", indexReplaced, valueReplacement);
        map.put(valueReplacement, indexReplaced);
        map.remove(val);
        list.set(indexReplaced, valueReplacement);
        list.removeLast();

        return true;
    }

    public int getRandom() {
        return list.get((int) (Math.random() * list.size()));
//        return list.get(random.nextInt(list.size()));
    }


    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
    }
}
