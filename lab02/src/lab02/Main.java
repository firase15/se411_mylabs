package lab02;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[] names = {"Ali", "Ahmed", "Sara"};
        PrintableList<String> list = new PrintableList<>(names);

        list.printItems();


        NumberBox<Integer> intBox = new NumberBox<>(10);
        NumberBox<Double> doubleBox = new NumberBox<>(5.5);

        System.out.println(intBox.getItem());
        System.out.println(doubleBox.getItem()) ;

        System.out.println(intBox.add(5));
        System.out.println(doubleBox.add(2.5)) ;


        System.out.println(NumberBox.sumNumbers(Arrays.asList(1, 2, 3, 4)));

        Pipeline<String, String> pipeline = new Pipeline<>("hello");

        Pipeline<String, Integer> result =
                pipeline.addTransformer(s -> s.length()) ;

        System.out.println(result.execute());
    }

}