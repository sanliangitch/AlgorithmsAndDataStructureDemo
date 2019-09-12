package cn.wulang.BinarySort;

/**
 * @author wulang
 * @create 2019/8/18/10:14
 */
public class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加节点的方法
    //递归的方法添加
    public void add(Node node){
        if (node == null){
            return;
        }
        //左边
        if (this.value > node.value){
            if (this.left == null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            //右边
            if (this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
    }

    public void list(){
        if (this.left != null){
            this.left.list();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.list();
        }
    }


    //查找要删除的节点
    /**
     *
     * @param value 希望删除的值
     * @return
     */
    public Node search(int value){
        if (this.value == value){
            return this;
        }else if (value < this.value){
            if (this.left == null){
                return null;
            }
            //向左
            return this.left.search(value);
        } else{
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    /**
     *
     * @param value 希望删除的值
     * @return 要删除节点的父节点
     */
    public Node searchParent(int value){
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)){
            return this;
        }else {
            /**
             * 要注意是比较的当前节点，不是左（右）节点的值
             */
            if (this.left != null && this.value > value){
                return this.left.searchParent(value);
            }else if (this.right != null && this.value <= value){
                return this.right.searchParent(value);
            }else {
                //没有找到父节点
                return null;
            }
        }
    }
}
