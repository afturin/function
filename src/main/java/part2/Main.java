package part2;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

    }

    static void baseStream() {
        Stream<String> empty = Stream.empty();
        Stream<Integer> singleElement = Stream.of(1);
        Stream<Integer> fromArray = Stream.of(1, 2, 3);
        Stream<String> srt = Stream.concat(Stream.of("aaa", "bbb", "ccc"), Stream.of("111", "222", "333"));

        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);

        new Random().ints();
        new Random().doubles();
        new Random().longs();

        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        System.out.println(s.count());
    }

    static void min() {
        Stream<String> ss = Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = ss.min((s1, s2) -> s1.length() - s2.length());
        min.ifPresent(System.out::println); // ape
    }

    static void find() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        s.findAny().ifPresent(System.out::println); // monkey (usually)
        infinite.findAny().ifPresent(System.out::println); // chimp
    }

    static void match() {
        List<String> list = new ArrayList<>();
        list.add("monkey");
        list.add("2");
        list.add("chi,p");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
        System.out.println(list.stream().anyMatch(pred)); // true
        System.out.println(list.stream().allMatch(pred)); // false
        System.out.println(list.stream().noneMatch(pred)); // false
        System.out.println(infinite.anyMatch(pred)); // true
    }

    static void forEach() {
        Stream<String> s = Stream.of("Monkey", "Gorilla", "Bonobo");
        s.forEach(System.out::print);
    }

    static void reduce() {
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        String word = stream.reduce("", (s, c) -> s + c);
        System.out.println(word);


        Stream<Integer> stream1 = Stream.of(3, 5, 6);
        System.out.println(stream1.reduce(1, (a, b) -> a * b));

        BinaryOperator<Integer> op = (a, b) -> a * b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(3);
        Stream<Integer> threeElements = Stream.of(3, 5, 6);

        empty.reduce(op).ifPresent(System.out::println); // no output
        oneElement.reduce(op).ifPresent(System.out::println); // 3
        threeElements.reduce(op).ifPresent(System.out::println); // 90

        //immutable
    }

    static void badReduce() {
        ArrayList<Integer> ints = new Random().ints(100)
                .boxed() // оборачиваем, так как коллекции не работают с примитивами
                .reduce(new ArrayList<>(),
                        (x, y) -> {
                            x.add(y);
                            return x;
                        },
                        (a, b) -> {
                            a.addAll(b);
                            return a;
                        });
        System.out.println(ints);
    }

    static void collect() {
        //collect() - a mutable reduction
        /*<R> R collect(
                        Supplier<R> supplier,
                        BiConsumer<R, ? super T> accumulator,
                        BiConsumer<R, R> combiner)
        <R,A> R collect(Collector<? super T, A,R> collector)*/
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        StringBuilder word = stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        System.out.println(word); // wolf

        //Первый параметр - supplier, который создает объект, который будет хранить результаты по мере сбора данных.
        // В этом случае он строит новый StringBuilder.
        //Вторым параметром является аккумулятор, который является BiConsumer, который принимает два параметра и ничего не возвращает.
        // Он отвечает за добавление еще одного элемента к данным. В данном примере он добавляет следующую строку в StringBuilder.
        //Конечным параметром является комбинатор, который является еще одним BiConsumer.
        // Он отвечает за получение двух наборов данных и их объединение.

        Stream<String> stream2 = Stream.of("w", "o", "l", "f");
        Set<String> set = stream2.collect(Collectors.toSet());
        List<String> list = stream2.collect(Collectors.toList());

    }

    static void toMap() {
        System.out.println(Stream.of('a', 'b', 'c').collect(Collectors.toMap(x -> x, x -> 1, Integer::sum)));
    }
    //Main2 - intermediate

    static void collect2() {
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        String result = ohMy.collect(Collectors.joining(", "));
        System.out.println(result); // lions, tigers, bears

        ohMy = Stream.of("lions", "tigers", "bears");
        Double result2 = ohMy.collect(Collectors.averagingInt(String::length));
        System.out.println(result2); // 5.333333333333333

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map = ohMy.collect(Collectors.toMap(
                String::length,
                k -> k,
                (s1, s2) -> s1 + "," + s2));
        System.out.println(map);            // {5=lions,bears, 6=tigers}

        ohMy = Stream.of("lions", "tigers", "bears");
        map = ohMy.collect(Collectors.toMap(
                String::length,
                k -> k,
                (s1, s2) -> s1 + "," + s2, TreeMap::new));
        System.out.println(map); //         // {5=lions,bears, 6=tigers}
        System.out.println(map.getClass()); // class java.util.TreeMap

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, List<String>> map2 = ohMy.collect(
                Collectors.groupingBy(String::length));
        System.out.println(map2); // {5=[lions, bears], 6=[tigers]}

        TreeMap<Integer, Set<String>> map3 = ohMy.collect(
                Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));
        System.out.println(map3); // {5=[lions, bears], 6=[tigers]}

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map4 = ohMy.collect(
                Collectors.partitioningBy(s -> s.length() <= 5));
        System.out.println(map4); // {false=[tigers], true=[lions, bears]}

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Long> map5 = ohMy.collect(
                Collectors.groupingBy( String::length, Collectors.counting()));
        System.out.println(map5);    // {5=2, 6=1}
    }
}
