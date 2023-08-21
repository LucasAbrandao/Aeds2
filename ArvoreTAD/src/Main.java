

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Experimento: Inserções Ordenadas");
        executarExperimentos(true);

        System.out.println("\nExperimento: Inserções Aleatórias");
        executarExperimentos(false);
    }

    private static void executarExperimentos(boolean ordenado) {
        for (int n = 1000; n <= 9000; n += 1000) {
            long totalComparacoes = 0;
            long tempoTotal = 0;

            for (int i = 0; i < 10000; i++) {
                ArvoreBinaria arvore = new ArvoreBinaria();

                if (ordenado) {
                    for (int j = 1; j <= n; j++) {
                        arvore.insere(new Item(j));
                    }
                } else {
                    Random random = new Random();
                    for (int j = 1; j <= n; j++) {
                        arvore.insere(new Item(random.nextInt(n * 10) + 1));
                    }
                }

                int elementoNaoExistente = n + 1; // Elemento não presente na árvore

                long tempoInicio = System.nanoTime();
                arvore.pesquisa(new Item(elementoNaoExistente));
                long tempoFim = System.nanoTime();

                int comparacoes = arvore.getComparisons();
                long tempoGasto = tempoFim - tempoInicio;

                totalComparacoes += comparacoes;
                tempoTotal += tempoGasto;
            }

            double mediaComparacoes = (double) totalComparacoes / 10000;
            double mediaTempo = (double) tempoTotal / 10000;

            System.out.println("Tamanho da árvore: " + n);
            System.out.println("Média de Comparacoes: " + mediaComparacoes);
            System.out.println("Média de Tempo gasto: " + mediaTempo + " nanossegundos\n");
        }
    }
}


