package cn.wulang.avl;

/**
 * @author wulang
 * @create 2019/8/18/15:53
 */
public class AvlTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {10,12,8,9,7,6};
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        AvlTree avlTree = new AvlTree();
        for (int i = 0 ; i < arr.length; i++){
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();
        //4
        System.out.println("没有平衡处理前：" + avlTree.getRoot().height());
        //1
        System.out.println("没有平衡处理前 左子树：" + avlTree.getRoot().leftHeight());
        //3
        System.out.println("没有平衡处理前 右子树：" + avlTree.getRoot().rightHeight());
    }
}
