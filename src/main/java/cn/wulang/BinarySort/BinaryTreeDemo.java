package cn.wulang.BinarySort;

/**
 * @author wulang
 * @create 2019/8/18/10:32
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++){
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序便利");
        binarySortTree.infixOrder();
        System.out.println("删除叶子节点");
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
//        binarySortTree.delNode(1);
        System.out.println(binarySortTree.getRoot());
        binarySortTree.infixOrder();
    }
}
