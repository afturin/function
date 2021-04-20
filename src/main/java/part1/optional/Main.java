package part1.optional;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<Double> opt = Optional.ofNullable(10d);
        opt.ifPresent(System.out::println);

        Optional<Double> opt2 = Optional.ofNullable(null);
        System.out.println(opt2.orElse(Double.NaN));
        System.out.println(opt2.orElseGet(() -> Math.random()));
        System.out.println(opt2.orElseGet(Math::random));


        Optional<Double> opt3 = Optional.ofNullable(null);
        System.out.println(opt3.orElseThrow(() -> new IllegalStateException()));
        System.out.println(opt3.orElseThrow(IllegalStateException::new));
    }
}
