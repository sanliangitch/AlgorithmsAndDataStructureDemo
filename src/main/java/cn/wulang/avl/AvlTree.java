package cn.wulang.avl;

/**
 * @author wulang
 * @create 2019/8/18/15:53
 */
public class AvlTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    //查找要删除的节点
    public Node search(int value){
        if (root == null){
            return null;
        }else {
            return root.search(value);
        }
    }

    public Node searchParent(int value){
        if (root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    public void delNode(int value){
        if (root == null){
            return;
        }else {
            Node targetNode = search(value);
            //没找到
            if (targetNode == null){
                return;
            }
            //找到了，root，只有一个
            if (root.left == null && root.right == null){
                root = null;
                return;
            }
            Node parent = searchParent(value);
            //要删除的是叶子节点
            if (targetNode.left == null && targetNode.right == null){
                //判断删除的是 左子节点还是右子节点
                if (parent.left != null && parent.left.value == value){
                    parent.left = null;
                }else if(parent.right != null && parent.right.value == value){
                    parent.right = null;
                }
            }else if (targetNode.left != null && targetNode.right != null){
                //删除有两个子树的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;

                //也可以左边找最大的
            }else {
                //删除只有一个子树的节点
                //如果要删除的节点有左子节点
                if (targetNode.left != null){
                    if (parent != null){
                        //如果 targetNode 是 parent 的左子结点
                        if (parent.left.value == value){
                            parent.left = targetNode.left;
                        }else {
                            parent.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }
                }else {
                    if (parent != null){
                        //要删除的节点有右子节点
                        if (parent.left.value == value){
                            parent.left = targetNode.right;
                        }else {
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     *1、返回以 node 为根节点的二叉排序树的最小节点的值
     * 2、删除以 node 为根节点的二叉排序树的最小节点
     * @param node  传入的节点（当做二叉排序树的根节点）
     * @return   返回以 node 为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环找左节点，就能找到最小值
        while (target.left != null){
            target= target.left;
        }
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    public void add(Node node){
        if (root == null){
            root = node;
            return;
        }else {
            root.add(node);
        }
    }

    public void infixOrder(){
        if (root != null){
            root.list();
        }else {
            System.out.println("树为空 不能便利");
        }
    }

}
