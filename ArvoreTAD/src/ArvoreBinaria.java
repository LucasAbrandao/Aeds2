public class ArvoreBinaria {

    private No raiz;
    private int comparisons; // To track the number of comparisons

    private No antecessor(No q, No r) {
        if (r.dir != null) {
            r.dir = antecessor(q, r.dir);
        } else {
            q.reg = r.reg;
            r = r.esq;
        }
        return r;
    }

    public ArvoreBinaria() {
        this.raiz = null;
        this.comparisons = 0; // Initialize comparisons count
    }

    public void insere(Item reg) {
        this.raiz = this.insere(reg, this.raiz);
    }

    public void retira(Item reg) {
        this.raiz = this.retira(reg, this.raiz);
    }

    public Item pesquisa(Item reg) {
        this.comparisons = 0; // Reset comparisons count before each search
        return this.pesquisa(reg, this.raiz);
    }

    public int getComparisons() {
        return this.comparisons;
    }

    public void imprimirArvore() {
        imprimirArvore(this.raiz, 0);
    }

    private void imprimirArvore(No p, int nivel) {
        if (p != null) {
            imprimirArvore(p.dir, nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("   ");
            }
            System.out.println(p.reg.getChave());
            imprimirArvore(p.esq, nivel + 1);
        }
    }

    private Item pesquisa(Item reg, No p) {
        if (p == null) {
            return null;
        } else {
            this.comparisons++; // Increment comparisons count
            int comparisonResult = reg.compara(p.reg);
            if (comparisonResult < 0) {
                return pesquisa(reg, p.esq);
            } else if (comparisonResult > 0) {
                return pesquisa(reg, p.dir);
            } else {
                return p.reg;
            }
        }
    }

    private No insere(Item reg, No p) {
        if (p == null) {
            p = new No();
            p.reg = reg;
            p.esq = null;
            p.dir = null;
        } else if (reg.compara(p.reg) < 0) {
            p.esq = insere(reg, p.esq);
        } else if (reg.compara(p.reg) > 0) {
            p.dir = insere(reg, p.dir);
        } else {
            //System.out.println("Erro: registro já existe");
        }
        return p;
    }

    private No retira(Item reg, No p) {
        if (p == null) {
            //System.out.println("Erro: Registro não encontrado");
        } else if (reg.compara(p.reg) < 0) {
            p.esq = retira(reg, p.esq);
        } else if (reg.compara(p.reg) > 0) {
            p.dir = retira(reg, p.dir);
        } else {
            if (p.dir == null) {
                p = p.esq;
            } else if (p.esq == null) {
                p = p.dir;
            } else {
                p.esq = antecessor(p, p.esq);
            }
        }
        return p;
    }
}
