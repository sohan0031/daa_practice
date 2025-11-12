import java.util.*;

public class Assi3 {

    static class Supply {
        String itemName;
        double itemWeight;
        double itemValue;
        boolean isDivisible;

        Supply(String itemName, double itemWeight, double itemValue, boolean isDivisible) {
            this.itemName = itemName;
            this.itemWeight = itemWeight;
            this.itemValue = itemValue;
            this.isDivisible = isDivisible;
        }

        double valuePerKg() {
            return itemValue / itemWeight;
        }
    }

    public static double maximizeUtility(List<Supply> supplies, double maxCapacity) {
        supplies.sort((a, b) -> Double.compare(b.valuePerKg(), a.valuePerKg()));
        double totalValue = 0.0;
        double remainingCapacity = maxCapacity;

        System.out.println("\n--- Relief Supply Loading Summary ---\n");

        for (Supply s : supplies) {
            if (remainingCapacity <= 0)
                break;

            if (s.isDivisible) {
                double loadWeight = Math.min(s.itemWeight, remainingCapacity);
                double loadValue = s.valuePerKg() * loadWeight;
                totalValue += loadValue;
                remainingCapacity -= loadWeight;
                System.out.printf("Loaded %.2f kg of %-15s | Utility Gained: ₹%.2f%n",
                        loadWeight, s.itemName, loadValue);
            } else {
                if (s.itemWeight <= remainingCapacity) {
                    totalValue += s.itemValue;
                    remainingCapacity -= s.itemWeight;
                    System.out.printf("Loaded Full Item: %-15s (%.2f kg) | Utility Gained: ₹%.2f%n",
                            s.itemName, s.itemWeight, s.itemValue);
                } else {
                    System.out.printf("Skipped %-15s (too heavy for remaining space)%n", s.itemName);
                }
            }
        }

        System.out.println("\nRemaining Boat Capacity: " + remainingCapacity + " kg");
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Emergency Relief Supply Distribution System");
        System.out.println("----------------------------------------------");

        System.out.print("Enter number of supply items: ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n <= 0) {
            System.out.println("Invalid input. Exiting...");
            sc.close();
            return;
        }

        List<Supply> supplies = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for item " + (i + 1) + ":");
            System.out.print("Item Name: ");
            String name = sc.nextLine();
            System.out.print("Weight (kg): ");
            double weight = sc.nextDouble();
            System.out.print("Utility Value (₹): ");
            double value = sc.nextDouble();
            System.out.print("Is Divisible? (1 = Yes, 0 = No): ");
            boolean divisible = sc.nextInt() == 1;
            sc.nextLine();
            supplies.add(new Supply(name, weight, value, divisible));
        }

        System.out.print("\nEnter boat capacity (kg): ");
        double capacity = sc.nextDouble();

        long start = System.nanoTime();
        double totalValue = maximizeUtility(supplies, capacity);
        long end = System.nanoTime();

        double execTime = (end - start) / 1_000_000.0;

        System.out.println("\n✅ Maximum Total Utility Value Carried: ₹" + totalValue);
        System.out.println("⏱ Execution Time: " + execTime + " milliseconds");
        System.out.println("\nRelief supplies are ready for delivery. ✅");
        sc.close();
    }
}
