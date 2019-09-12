package cn.wulang.HuffmanCode;

/**
 * @author wulang
 * @create 2019/8/15/21:28
 */
public class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public String toString() {
        return "Node [data = " + this.data + " weight=" + this.weight + "]";
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }

    }
}
