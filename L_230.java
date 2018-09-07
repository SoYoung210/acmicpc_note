import java.util.PriorityQueue;
class Solution {
    PriorityQueue<Integer> q = new PriorityQueue<>();
    public int kthSmallest(TreeNode root, int k) {
        preorder(root);
        int i = 0;
        while(i<k-1) {
            q.poll();
            i++;
        }
        return q.poll();
    }
    public void preorder(TreeNode root) {
        if(root!=null) {

            q.offer(root.val);
            preorder(root.left);
            preorder(root.right);
        }
    }
}