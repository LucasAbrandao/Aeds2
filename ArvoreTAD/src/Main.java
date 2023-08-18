import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Experiment: Ordered Insertions");
        runExperiments(true);

        System.out.println("\nExperiment: Random Insertions");
        runExperiments(false);
    }

    private static void runExperiments(boolean ordered) {
        for (int n = 1000; n <= 9000; n += 1000) {
            ArvoreBinaria arvore = new ArvoreBinaria();

            if (ordered) {
                for (int i = 1; i <= n; i++) {
                    arvore.insere(new Item(i));
                }
            } else {
                Random random = new Random();
                for (int i = 1; i <= n; i++) {
                    arvore.insere(new Item(random.nextInt(n * 10) + 1));
                }
            }

            int nonExistentElement = n + 1; // Element not present in the tree

            long startTime = System.nanoTime();
            arvore.pesquisa(new Item(nonExistentElement));
            long endTime = System.nanoTime();

            int comparisons = arvore.getComparisons();
            long timeTaken = endTime - startTime;

            System.out.println("Tree size: " + n);
            System.out.println("Comparisons: " + comparisons);
            System.out.println("Time taken: " + timeTaken + " nanoseconds\n");
        }
    }
}
