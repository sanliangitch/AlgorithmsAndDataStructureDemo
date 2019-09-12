package cn.wulang.ShiDaSuanFa;

import java.util.Arrays;

/**
 * KMP 字符串匹配
 *
 * @author wulang
 * @create 2019/8/24/10:39
 */
public class KMP {
    public static void main(String[] args) {
        String s1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String s2 = "尚硅谷你尚硅你";
        System.out.println(violenceMatch(s1,s2));
        System.out.println("----------------------------------------");
        String str1= "BBC ABCDAB ABCDABCDABDE";
        String str2="ABCDABD";
        int[] ints = kmpNext(str2);
        System.out.println(Arrays.toString(ints));
        int i = kmpSearch(str1, str2, ints);
        System.out.println(i);
    }

    //搜索算法
    public static int kmpSearch(String str1,String str2,int[] index){
        for (int i = 0,j=0; i < str1.length() ; i++){
            /**
             * 核心点
             */
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = index[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j == str2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串的 部分匹配值
    public static int[] kmpNext(String dest){
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1,j = 0;i < dest.length(); i++){
            //当 dest.charAt(i) != dest.charAt(j) ，我们需要从next[j - 1]获取新的j
            //直到 dest.charAt(i) == dest.charAt(j)，成立才退出
            /**
             * 这是核心
             */
            while (j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    //暴力匹配
    public static int violenceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;
        int j = 0;
        while (i < s1Len && j < s2Len){
            if (s1[i] == s2[j]){
                i++;
                j++;
            }else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == s2Len){
            return i - j;
        }
        return -1;
    }
}
