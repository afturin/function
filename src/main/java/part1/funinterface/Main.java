package part1.funinterface;

import java.time.LocalDate;
import java.util.*;
import java.util.function.*;

interface MyPredicate<T> {
    boolean test(T t);
}

public class Main {
    static void pred() {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("abbb");
        strings.add("avvvv");
        MyPredicate<String> predicate = new MyPredicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 2;
            }
        };
        for (String s : strings) {
            if (predicate.test(s))
                System.out.println(s);
        }
    }

    static void sup() {
        Supplier<LocalDate> s1 = LocalDate::now;
        Supplier<LocalDate> s2 = () -> LocalDate.now();
        LocalDate d1 = s1.get();
        LocalDate d2 = s2.get();
        System.out.println(d1);
        System.out.println(d2);

        Supplier<StringBuilder> s11 = StringBuilder::new;
        Supplier<StringBuilder> s22 = () -> new StringBuilder();

        System.out.println(s11.get());
        System.out.println(s22.get());

        Supplier<ArrayList<String>> s3 = ArrayList<String>::new;
        ArrayList<String> a1 = s3.get();
        System.out.println(a1);
    }

    static void consumer() {
        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = x -> System.out.println(x);
        Consumer<String> c3 = c1.andThen(c2);
        c1.accept("1");
        c2.accept("2");
        c3.accept("3");
    }

    static void biCons() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        BiConsumer<String, Integer> b1 = map::put;
        BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);
        b1.accept("chicken", 7);
        b2.accept("chick", 1);
        System.out.println(map);
    }

    static void biPredicate() {
        BiPredicate<String, String> b1 = String::startsWith;
        BiPredicate<String, String> b2 = (string, prefix) -> string.startsWith(prefix);
        BiPredicate<String, String> b3 = b1.and(b2);
        System.out.println(b1.test("chicken", "chick"));
        System.out.println(b2.test("chicken", "chick"));
    }

    static void fun() {
        Function<String, Integer> f1 = String::length;
        Function<String, Integer> f2 = x -> x.length();

        System.out.println(f1.apply("cluck"));
        System.out.println(f2.apply("cluck"));
    }

    static void biFun() {
        BiFunction<String, String, String> b1 = String::concat;
        BiFunction<String, String, String> b2 =
                (string, toAdd) -> string.concat(toAdd);
        System.out.println(b1.apply("1 ", "2"));
        System.out.println(b2.apply("3 ", "4"));
    }

    @FunctionalInterface
    interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    static void trFun() {
        TriFunction<String, String, String, Integer> t = (s1, s2, s3) -> s1.length() + s2.length() + s3.length();
        System.out.println(t.apply("1", "2", "3"));
    }

    static void unary() {
        UnaryOperator<String> u1 = String::toUpperCase;
        UnaryOperator<String> u2 = x -> x.toUpperCase();
        System.out.println(u1.apply("string"));
        System.out.println(u2.apply("string"));
    }

    static void comparator() {
        Comparator<String> strings = (s1, s2) -> s1.compareTo(s2);
        System.out.println(strings.compare("1", "11"));

    }

    static void variables(int a) {
        int b = 1;
        Predicate<Integer> p1 = a1 -> {
            int b1 = 0;
            int c = 0;
            return b == c;
        };
    }

    static class Crow {
        private String color;
        static String field;

        public void caw(String name) {
            String volume = "loudly";
            Consumer<String> consumer = s -> System.out.println(name + " says "
                    + volume + " that she is " + color + "field" + field);
        }
    }
    //наиболее распространенные методы коллекций

    static void removeIf() {
        List<String> letters = new ArrayList<>();
        letters.add("aa");
        letters.add("pp");
        letters.add("az");
        System.out.println(letters); 
        letters.removeIf(s -> s.charAt(0) == 'a');
        System.out.println(letters); //[pp]
    }

    static void sort() {
        List<String> letters = new ArrayList<>();
        letters.add("aa");
        letters.add("pp");
        letters.add("az");
        System.out.println(letters); // [aa, pp, az]
        letters.sort((b1, b2) -> b1.compareTo(b2));
        System.out.println(letters); // [aa, az, pp]
    }

    static void forEach() {
        List<String> letters = new ArrayList<>();
        letters.add("aa");
        letters.add("pp");
        letters.add("az");
        letters.forEach(b -> System.out.println(b));
        System.out.println(letters);
    }

    static void forEachMap(){
        Map<String, Integer> bunnies = new HashMap<>();
        bunnies.put("a", 1);
        bunnies.put("aa", 2);
        bunnies.put("aaa", 3);
        bunnies.forEach((k, v) -> System.out.println(k + " " + v));
    }

    static void primitive(){
        BooleanSupplier b1 = () -> true;
        BooleanSupplier b2 = () -> Math.random()> .5;
        System.out.println(b1.getAsBoolean());
        System.out.println(b2.getAsBoolean());

        /*ToDoubleFunction<T>
        ToIntFunction<T>
        ToLongFunction<T>*/

        /*ToDoubleBiFunction<T, U>
        ToIntBiFunction<T, U>
        ToLongBiFunction<T, U>*/
        /*DoubleToIntFunction
                DoubleToLongFunction;
        IntToDoubleFunction
                IntToLongFunction;
        LongToDoubleFunction
                LongToIntFunction;*/
    }
}
