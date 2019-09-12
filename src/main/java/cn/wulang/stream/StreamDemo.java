package cn.wulang.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author wulang
 * @create 2019/9/11/14:05
 */
public class StreamDemo {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> tr2011 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println("第一题：");
        tr2011.stream().forEach(System.out::println);


        //(2) 交易员都在哪些不同的城市工作过？
        List<String> collect = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        Set<String> collect1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println("第二题：（1）");
        collect.stream().forEach(System.out::println);
        System.out.println("---第二题：（2）");
        collect1.stream().forEach(System.out::println);


        //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("第三题：");
        traders.stream().forEach(System.out::println);


        //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
        String reduce = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println("第四题：");
        System.out.println(reduce);


        //(5) 有没有交易员是在米兰工作的？
        boolean milan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println("第五题：");
        System.out.println(milan);


        //(6) 打印生活在剑桥的交易员的所有交易额。
        System.out.println("第六题：");
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);


        //(7) 所有交易中，最高的交易额是多少？
        System.out.println("第七题：");
        Optional<Integer> integer = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(integer);


        //(8) 找到交易额最小的交易。
        System.out.println("第八题：");
        Optional<Transaction> reduce1 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println(reduce1);

        //勾股数
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                        .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                        );
        pythagoreanTriples.limit(100)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
        //优化后
        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                                .filter(t -> t[2] % 1 == 0));

        transactions.stream().collect(groupingBy(Transaction::getTrader, counting()));
        ArrayList<Transaction> collect2 = transactions.stream().collect(Collectors.toCollection(ArrayList::new));
    }
}
