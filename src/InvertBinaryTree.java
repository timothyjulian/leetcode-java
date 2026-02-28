public class InvertBinaryTree {

    public InvertBinaryTree() {

    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode newRoot = new TreeNode(root.val);
        if (root.left != null) {
            newRoot.right = new TreeNode(root.left.val);
        }

        if (root.right != null) {
            newRoot.left = new TreeNode(root.right.val);
        }

        dfs(newRoot.left, root.right);
        dfs(newRoot.right, root.left);

        return newRoot;
    }

    public void dfs(TreeNode a, TreeNode b) {
        if (b == null) return;
        if (b.left != null) {
            a.right = new TreeNode(b.left.val);
        }

        if (b.right != null) {
            a.left = new TreeNode(b.right.val);
        }

        dfs(a.left, b.right);
        dfs(a.right, b.left);
    }

    public static void main(String[] args) {

    }
}
