import java.util.*;

class Movie {
    String title;
    float rating;
    int releaseYear;
    int popularity;

    Movie(String title, float rating, int releaseYear, int popularity) {
        this.title = title;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.popularity = popularity;
    }

    void display() {
        System.out.println(title + " | Rating: " + rating + 
                           " | Year: " + releaseYear + 
                           " | Popularity: " + popularity);
    }
}

public class Assi2 {
    

    public static void quickSort(List<Movie> movies, int low, int high, Comparator<Movie> comparator) {
        if (low < high) {
            int pivotIndex = partition(movies, low, high, comparator);
            quickSort(movies, low, pivotIndex - 1, comparator);
            quickSort(movies, pivotIndex + 1, high, comparator);
        }
    }

    private static int partition(List<Movie> movies, int low, int high, Comparator<Movie> comparator) {
        Movie pivot = movies.get(high);
        int i = low;
        for (int j = low; j < high; j++) {
            if (comparator.compare(movies.get(j), pivot) < 0) {
                Collections.swap(movies, i, j);
                i++;
            }
        }
        Collections.swap(movies, i, high);
        return i;
    }

    // Comparators for sorting
    private static Comparator<Movie> compareByRating = Comparator.comparingDouble(m -> m.rating);
    private static Comparator<Movie> compareByYear = Comparator.comparingInt(m -> m.releaseYear);
    private static Comparator<Movie> compareByPopularity = Comparator.comparingInt(m -> m.popularity);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of movies: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        List<Movie> movies = new ArrayList<>();

        // Take user input for each movie
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Movie " + (i + 1) + ":");
            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Rating (1.0 - 10.0): ");
            float rating = sc.nextFloat();
            System.out.print("Release Year: ");
            int year = sc.nextInt();
            System.out.print("Popularity (number of viewers/fans): ");
            int popularity = sc.nextInt();
            sc.nextLine(); // consume newline

            movies.add(new Movie(title, rating, year, popularity));
        }

        // Choose sorting criteria
        System.out.print("\nSort movies by (rating/year/popularity): ");
        String sortBy = sc.nextLine().trim().toLowerCase();

        Comparator<Movie> comparator;
        switch (sortBy) {
            case "year":
                comparator = compareByYear;
                break;
            case "popularity":
                comparator = compareByPopularity;
                break;
            case "rating":
            default:
                comparator = compareByRating;
                break;
        }

        System.out.println("\nSorting movies by " + sortBy + "...");

        // --- Time measurement using System.nanoTime() ---
        long startTime = System.nanoTime();
        quickSort(movies, 0, movies.size() - 1, comparator);
        long endTime = System.nanoTime();

        double timeTakenMs = (endTime - startTime) / 1_000_000.0;

        // Display sorted movies
        System.out.println("\nMovies sorted by " + sortBy + ":");
        for (Movie m : movies) {
            m.display();
        }

        System.out.printf("\nSorting completed in %.3f milliseconds.%n", timeTakenMs);
        sc.close();
    }
}
