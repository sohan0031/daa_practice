import java.util.*;

class Supply {
    String name;
    double weight;
    double utility;
    boolean divisible;

    Supply(String name, double weight, double utility, boolean divisible) {
        this.name = name;
        this.weight = weight;
        this.utility = utility;
        this.divisible = divisible;
    }

    double utilityPerKg() {
        return utility / weight;
    }
}

public class Assi3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Total Items: ");
        int n = sc.nextInt();

        List<Supply> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.println("\n--- Item " + i + " ---");
            System.out.print("Name: ");
            String name = sc.next();

            System.out.print("Weight (kg): ");
            double w = sc.nextDouble();

            System.out.print("Utility (importance): ");
            double u = sc.nextDouble();

            System.out.print("Divisible? (y/n): ");
            String flag = sc.next();
            boolean div = flag.equalsIgnoreCase("y");

            list.add(new Supply(name, w, u, div));
        }

        System.out.print("\nBoat capacity (kg): ");
        double capacity = sc.nextDouble();

        // Sort by utility per kg (descending)
        list.sort((a, b) -> Double.compare(b.utilityPerKg(), a.utilityPerKg()));

        double remaining = capacity;
        double totalUtility = 0;
        double used = 0;

        System.out.println("\nOutput:");
        for (Supply s : list) {
            if (remaining <= 0) break;

            if (s.divisible) {
                double take = Math.min(s.weight, remaining);
                double util = s.utilityPerKg() * take;
                totalUtility += util;
                remaining -= take;
                used += take;
                System.out.println(" - " + s.name + ": took " + round2(take) + " kg (utility +" + round2(util) + ")");
            } else {
                if (s.weight <= remaining) {
                    totalUtility += s.utility;
                    remaining -= s.weight;
                    used += s.weight;
                    System.out.println(" - " + s.name + ": took whole item (" + round2(s.weight) + " kg, utility +" + round2(s.utility) + ")");
                } else {
                    System.out.println(" - " + s.name + ": skipped (too heavy to fit)");
                }
            }
        }

        System.out.println("\nTotal weight loaded: " + round2(used) + " / " + round2(capacity) + " kg");
        System.out.println("Total utility on board: " + round2(totalUtility));
        System.out.println("Free space left: " + round2(remaining) + " kg");

        sc.close();
    }

    // round to 2 decimal places
    private static String round2(double x) {
        return String.format("%.2f", x);
    }
}
