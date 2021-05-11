package tasks;

import jdk.dynalink.Operation;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tasks {

    public void filter(List<String> strings, Predicate<String> predicate) {
        strings.stream().filter(predicate);
    }

    public void stream(){
        int[] result =
                IntStream.iterate(0, i -> i+1)
                        .filter(i -> i % 27 == 0)
                        .limit(10)
                        .toArray();
        System.out.println(Arrays.toString(result));
    }


    public void sortArray(String... array) {
        Collection<String> collection = Arrays.asList(array);
        List<String> sorted =
                collection.stream()
                        .sorted((o1,o2) -> o1.length() - o2.length())
                        .collect(Collectors.toList());
        System.out.println(sorted);
    }

    public void map(String... array) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        Collection <String> collection = Arrays.asList(array);
        for (String string : collection) {
            int key = string.length();
            Operation operation = null // TODO вставить лямбду

            int value = collection.stream().filter(operation);
            hashMap.put(key,value);
        }
        System.out.println(hashMap);


    }

}
