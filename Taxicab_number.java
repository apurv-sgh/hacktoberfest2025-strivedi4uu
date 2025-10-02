import java.util.HashMap;
import java.util.Map;

public class TaxicabNumbers {

    public static void findTaxicabNumbers(int N) {
        int count = 0;
        long num = 1;

        while (count < N) {
            Map<Long, Integer> cubeSums = new HashMap<>(); // Stores sum of two cubes and count of pairs

            // Iterate through possible 'a' and 'b' values for a^3 + b^3
            // We only need to check up to the cube root of 'num' approximately
            // to avoid unnecessary calculations, but we'll iterate up to a reasonable limit.
            // A more efficient approach might involve a priority queue.
            for (long a = 1; a * a * a < num; a++) {
                for (long b = a + 1; a * a * a + b * b * b <= num; b++) {
                    long sumOfCubes = a * a * a + b * b * b;
                    if (cubeSums.containsKey(sumOfCubes)) {
                        cubeSums.put(sumOfCubes, cubeSums.get(sumOfCubes) + 1);
                    } else {
                        cubeSums.put(sumOfCubes, 1);
                    }
                }
            }

            // Check if 'num' itself is a Taxicab number
            int distinctRepresentations = 0;
            for (long a = 1; a * a * a < num; a++) {
                long bCubed = num - (a * a * a);
                long b = (long) Math.cbrt(bCubed);

                if (b > a && b * b * b == bCubed) {
                    distinctRepresentations++;
                }
            }

            if (distinctRepresentations >= 2) {
                System.out.println("Taxicab Number " + (count + 1) + ": " + num);
                count++;
            }
            num++;
        }
    }

    public static void main(String[] args) {
        int numberOfTaxicabNumbersToFind = 5; // Find the first 5 Taxicab numbers
        findTaxicabNumbers(numberOfTaxicabNumbersToFind);
    }
}
