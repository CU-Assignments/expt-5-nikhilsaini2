import java.util.ArrayList;
import java.util.List;

public class Easy_Level {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10); // Autoboxing
        numbers.add(20);
        numbers.add(30);
        
        int sum = calculateSum(numbers);
        System.out.println("The sum of the list is: " + sum);
    }

    public static int calculateSum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) { // Unboxing
            sum += number;
        }
        return sum;
    }
}