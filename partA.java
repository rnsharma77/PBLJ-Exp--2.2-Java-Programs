import java.util.*;

public class SumOfIntegers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter integers (type 'done' to finish):");
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("done"))
                break;

            try {
                // Autoboxing: int -> Integer
                Integer num = Integer.parseInt(input);
                numbers.add(num);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter an integer or 'done'.");
            }
        }

        int sum = 0;
        // Unboxing: Integer -> int
        for (Integer n : numbers) {
            sum += n;
        }

        System.out.println("Total Sum = " + sum);
        sc.close();
    }
}
