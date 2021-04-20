package part2;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main2 {
    static List<Log> logs = new ArrayList<>();

    static {
        logs.add(new Log("Сибирская сосна", 10));
        logs.add(new Log("Дуб монгольский", 30));
        logs.add(new Log("Берёза карликовая", 5));
    }

    static class Log {
        String type;
        int count;

        public Log(String type, int count) {
            this.type = type;
            this.count = count;
        }

        public String getType() {
            return type;
        }
    }

    public static void main(String[] args) {


    }

    static void filter() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.filter(x -> x.startsWith("m")).forEach(System.out::print); // monkey
    }

    static void distinct() {
        Stream<String> s = Stream.of("duck", "duck", "duck", "goose");
        s.distinct().forEach(System.out::print); // duckgoose
    }

    static void limit() {
        Stream<Integer> s = Stream.iterate(1, n -> n + 1);
        s.skip(5)
                .limit(2).forEach(System.out::print); // 67
    }

    static void map() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.map(String::length)
                .forEach(System.out::print); // 676

        logs.stream().map(x -> x.getType()).forEach(System.out::println);
    }

    static void flatMap() {
        logs.stream().map(Log::getType).map(x -> x.split(" "))
                .forEach(System.out::println);
        logs.stream().map(Log::getType).map(x -> x.split(" ")).flatMap(x ->
                Arrays.stream(x)).forEach(System.out::println);

        IntStream chars = logs.stream()
                .map(Log::getType)
                .map(x -> x.split(" "))
                .flatMap(Arrays::stream)
                .map(String::chars)
                .flatMapToInt(x -> x);
        chars.forEach(x1 -> System.out.println((char) x1));

        List<String> zero = new ArrayList<>();
        List<String> one = new ArrayList<>();
        one.add("Bonobo");
        List<String> two = new ArrayList<>();
        two.add("Mama Gorilla");
        two.add("Baby Gorilla");
        Stream<List<String>> animals = Stream.of(zero, one, two);
        animals.flatMap(m -> m.stream()).forEach(System.out::println);
    }

    static void sorted() {
        Stream<String> s = Stream.of("z", "a");
        s.sorted()
                .forEach(System.out::print);

        Stream<String> s1 = Stream.of("a", "z", "c");
        s1.sorted(Comparator.reverseOrder())
                .forEach(System.out::print);
    }

    static void peek() {
        Stream<String> stream = Stream.of("black bear", "brown bear", "grizzly");
        long count = stream.filter(s -> s.startsWith("g"))
                .peek(System.out::println).count();
        System.out.println(count);
    }

    // примитивные стримы
    static void prStreams() {
        //OptionalDouble average ()
        //Stream<T> boxed()

        //OptionalInt max()
        //OptionalLong max()
        //OptionalDouble max()

        //OptionalInt min()
        //OptionalLong min()
        //OptionalDouble min()

        //IntStream range ( int a, int b)
        //LongStream range ( long a, long b)
        //IntStream rangeClosed ( int a, int b)
        //LongStream rangeClosed ( long a, long b)

        //int sum()
        //long sum()
        //double sum()

        //IntSummaryStatistics        summaryStatistics()
        // LongSummaryStatistics
        // DoubleSummaryStatistics
        DoubleStream oneValue = DoubleStream.of(3.14);
        oneValue.forEach(System.out::println);

        DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
        varargs.forEach(System.out::println);

        IntStream count = IntStream.iterate(1, n -> n + 1).limit(5);
        count.forEach(System.out::println);

        IntStream range = IntStream.range(1, 6);
        range.forEach(System.out::println);

        IntStream rangeClosed = IntStream.rangeClosed(1, 5);
        rangeClosed.forEach(System.out::println);

        IntStream stream = IntStream.rangeClosed(1,10);
        OptionalDouble optional = stream.average();


    }
}
