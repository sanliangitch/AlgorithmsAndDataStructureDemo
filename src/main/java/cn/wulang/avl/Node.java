package cn.wulang.avl;

/**
 * @author wulang
 * @create 2019/8/18/15:41
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

    //左旋转
    public void leftRotate(){
        //创建新的节点，以当前节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前节点的右子树的右子树
        right = right.right;
        //把当前节点的左子树(左子节点)设置成新的节点
        left = newNode;
    }
    //右旋转
    public void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }

    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }

    //返回以该节点为根节点的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height() , right == null ? 0 : right.height()) + 1 ;
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
        //当添加完一个节点后，如果（右边 - 左边） > 1 ，左旋转
        if (rightHeight() - leftHeight() > 1){
            //如果它的右子树的左子树高度大于它的右子树的右子树高度
            if (right != null && right.rightHeight() < right.leftHeight()){
                //先对右子树进行右旋
                right.rightRotate();
                //再对当前节点进行左旋
                leftRotate();
            }else {
                leftRotate();
            }
            //防止代码往后执行
            return;
        }
        if (leftHeight() - rightHeight() > 1){
            //如果它的左子树的右子树高度大于它的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()){
                //先对当前节点的左节点（左子树）左旋转
                left.leftRotate();
                //再对当前节点进行右旋
                rightRotate();
            }else {
                rightRotate();
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
