import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 232. Implement Queue using Stacks (Easy)
 * https://leetcode.com/problems/implement-queue-using-stacks/
 *
 * @author Timothy Julian
 * @version $Id: ImplementQueueUsingStacks.java, v 0.1 2026‐11‐18 13.50 timothy.julian Exp $$
 */
public class ImplementQueueUsingStacks {

    public ImplementQueueUsingStacks() {
        Judge j = new Judge();
        MyQueue obj1 = new MyQueue();
        obj1.push(1);
        obj1.push(2);
        j.check("1", obj1.peek());
        j.check("1", obj1.pop());
        j.check("false", obj1.empty());
        j.done();
    }

    // ==== leetcode:begin ====
    static class MyQueue {
        Deque<Integer> stack1;
        Deque<Integer> stack2;

        public MyQueue() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
        }
    
        public void push(int x) {
            stack1.push(x);
        }
    
        public int pop() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            }
            fillStack2();
            return stack2.pop();
        }
    
        public int peek() {
            if (!stack2.isEmpty()) {
                return stack2.peek();
            }
            fillStack2();
            if (stack2.isEmpty()) return 0;
            return stack2.peek();
        }
    
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

        private void fillStack2() {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new ImplementQueueUsingStacks();
    }
}
