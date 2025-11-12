import java.text.SimpleDateFormat;
import java.util.*;

class OrderRecord {
    String code;   
    long time;     

    public OrderRecord(String code, long time) {
        this.code = code;
        this.time = time;
    }
}

public class Assi1 {


    private static void merge(List<OrderRecord> list, int left, int mid, int right) {
        OrderRecord[] temp = new OrderRecord[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (list.get(i).time <= list.get(j).time) {
                temp[k++] = list.get(i++);
            } else {
                temp[k++] = list.get(j++);
            }
        }

        while (i <= mid) temp[k++] = list.get(i++);
        while (j <= right) temp[k++] = list.get(j++);

        for (i = left, k = 0; i <= right; i++, k++) list.set(i, temp[k]);
    }

   
    private static void mergeSort(List<OrderRecord> list, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }


    private static void generateOrders(List<OrderRecord> list, int total) {
        Random rng = new Random();
        long now = System.currentTimeMillis(); 


        for (int i = 1; i <= total; i++) {
            long randomOffset = rng.nextInt(300) * 60L * 1000L;
            long orderTime = now + randomOffset;

            list.add(new OrderRecord("ORD" + i, orderTime));
        }
    }


    private static void printOrders(List<OrderRecord> list, int count) {
     
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < Math.min(count, list.size()); i++) {
            String fTime = fmt.format(new Date(list.get(i).time));
            System.out.println(list.get(i).code + " | " + fTime);
        }
    }

    // 🚀 Main
    public static void main(String[] args) {
        int totalOrders = 20;  
        List<OrderRecord> orders = new ArrayList<>();

        // Generate random orders
        generateOrders(orders, totalOrders);

        System.out.println("Before Sorting:\n");
        printOrders(orders, totalOrders);

        // Sort using merge sort
        mergeSort(orders, 0, orders.size() - 1);

        System.out.println("\nAfter Sorting (by time):\n");
        printOrders(orders, totalOrders);
    }
}
