package cn.wulang.HuffmanCode;

import java.io.*;
import java.util.*;

/**
 * @author wulang
 * @create 2019/8/15/21:11
 */
public class HuffmanCode {
    static Map<Byte, String> huffmanCodes = new HashMap();
    static StringBuilder stringBuilder = new StringBuilder();
    public static void main(String[] args) {
        String srcFile = "e://src.dmp";
        String dstFile = "D://dst.zip";
        zipFile(srcFile,dstFile);

        System.out.println("压缩文件 OK！");
    }

    //解压
    public static void unZipFile(String zipFile,String dstFile){
        //定义文件的输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            //读取 byte[]
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取 哈夫曼编码 表
            Map<Byte,String> codes = (Map<Byte, String>) ois.readObject();
            byte[] bytes = decode(codes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < huffmanBytes.length; ++i) {
            byte b = huffmanBytes[i];
            boolean flag = i == huffmanBytes.length - 1;
            stringBuilder.append(byteToBitString(!flag, b));
        }

        Map<String, Byte> map = new HashMap();
        Iterator var13 = huffmanCodes.entrySet().iterator();

        while(var13.hasNext()) {
            Map.Entry<Byte, String> entry = (Map.Entry)var13.next();
            map.put((String)entry.getValue(), (Byte)entry.getKey());
        }

        List<Byte> list = new ArrayList();

        int i;
        for(i = 0; i < stringBuilder.length(); i += i) {
            i = 1;
            boolean flag = true;
            Byte b = null;

            while(flag) {
                String key = stringBuilder.substring(i, i + i);
                b = (Byte)map.get(key);
                if (b == null) {
                    ++i;
                } else {
                    flag = false;
                }
            }

            list.add(b);
        }

        byte[] b = new byte[list.size()];

        for(i = 0; i < b.length; ++i) {
            b[i] = (Byte)list.get(i);
        }

        return b;
    }

    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp = b | 256;
        }

        String str = Integer.toBinaryString(temp);
        return flag ? str.substring(str.length() - 8) : str;
    }

    public static void zipFile(String srcFile,String dstFile){
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            //创建输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件一样大小的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //使用霍夫曼编码，对源文件进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutPutStream
            oos = new ObjectOutputStream(os);
            //以对象流的方式写入 霍夫曼编码 ，是为了我们以后恢复源文件时使用
            //把赫夫曼編碼后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //注意：一定要把 霍夫曼编码 写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList();
        Map<Byte, Integer> counts = new HashMap();
        byte[] var6 = bytes;
        int var5 = bytes.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            byte b = var6[var4];
            Integer count = (Integer)counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        Iterator var9 = counts.entrySet().iterator();

        while(var9.hasNext()) {
            Map.Entry<Byte, Integer> entry = (Map.Entry)var9.next();
            nodes.add(new Node((Byte)entry.getKey(), (Integer)entry.getValue()));
        }

        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while(nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = (Node)nodes.get(0);
            Node rightNode = (Node)nodes.get(1);
            Node parent = new Node((Byte)null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }

        return (Node)nodes.get(0);
    }

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        } else {
            getCodes(root.left, "0", stringBuilder);
            getCodes(root.right, "1", stringBuilder);
            return huffmanCodes;
        }
    }

    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }

    }

    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] var6 = bytes;
        int index = bytes.length;

        for(int var4 = 0; var4 < index; ++var4) {
            byte b = var6[var4];
            stringBuilder.append((String)huffmanCodes.get(b));
        }

        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        byte[] huffmanCodeBytes = new byte[len];
        index = 0;

        for(int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
            ++index;
        }

        return huffmanCodeBytes;
    }
}
