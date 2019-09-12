package cn.wulang.ActiveMQ;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * 嵌入式的
 *
 * @author wulang
 * @create 2019/6/23/11:24
 */
public class EmsBroker {
    public static void main(String[] args) {
        String substring = UUID.randomUUID().toString().substring(0, 3);
        System.out.println(substring);

        Collection collection = new ArrayList();
    }
}
