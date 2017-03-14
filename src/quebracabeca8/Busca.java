package quebracabeca8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Busca {

    private Problema problema;
    private List<int[]> visitados;
    private int limiteProfundidade = 22;
    private final int LARG = 0;
    private final int PROF = 1;
    private final int limitePROF = 25;
    private final int limiteLARG = 18;

    public Busca(Problema problema) {
        this.problema = problema;
    }

    public Stack<No> A_Asterisco() {
        this.visitados = new ArrayList<>();
        List<No> borda = new ArrayList<>();

        No no = new No();
        no.setEstado(problema.getEstadoInicial());
        borda.add(no);

        while (!borda.isEmpty()) {

            no = borda.remove(0);

            if (problema.isObjetivo(no.getEstado())) {
                return solucao(no);
            }

            borda.addAll(expandirAsterico(no));
            Collections.sort(borda);
        }

        return null;
    }

    private List<No> expandirAsterico(No no) {
        List<No> sucessores = new ArrayList<>();

        // imprimirArvore(no);
        this.visitados.add(no.getEstado());

        for (int[] sucessor : problema.getSucerrores(no.getEstado()).get(no.getEstado())) {

            if (!foiVisitado(sucessor)) {

                No novo = new No();
                novo.setEstado(sucessor);
                novo.setPai(no);
                novo.setProfundidade((no.getProfundidade() + 1));
                novo.setCusto(no.getCusto() + 1);

                //f(n) = h(n) + g(n)
                novo.setComparador( numblocosPosicaoErrada(sucessor) + ( no.getCusto() + 1 ) );//criterio para ordenar

                sucessores.add(novo);
            }
        }
        return sucessores;
    }

    private List<No> expandir(No no, int tipo) {
        List<No> sucessores = new ArrayList<>();

        // imprimirArvore(no);
        this.visitados.add(no.getEstado());
        System.out.println(no.getProfundidade() + " PROF.");

        if ( (no.getProfundidade() <= limitePROF && tipo == PROF ) || 
                (no.getProfundidade() <= limiteLARG && tipo == LARG ) ) {

            for (int[] sucessor : problema.getSucerrores(no.getEstado()).get(no.getEstado())) {

                if (!foiVisitado(sucessor)) {

                    No novo = new No();
                    novo.setEstado(sucessor);
                    novo.setPai(no);
                    novo.setProfundidade((no.getProfundidade() + 1));

                    sucessores.add(novo);
                }
            }
            Collections.shuffle(sucessores);
        }
        return sucessores;
    }

    private Stack<No> solucao(No no) {
        Stack<No> caminho = new Stack<>();
        caminho.add(no);

        No pai = no.getPai();
        while (pai != null) {
            caminho.push(pai);
            pai = pai.getPai();
        }

        return caminho;
    }    
    
    //heuristica
    private int numblocosPosicaoErrada(int[] estado) {
        int soma = 0;
        int[] objetivo = this.problema.getEstadoFinal();

        for (int pos = 0; pos < estado.length; pos++) {
            if (objetivo[pos] != estado[pos]) {
                soma++;
            }
        }
        return soma;
    }

    private boolean foiVisitado(int[] estado) {
        if (this.visitados.isEmpty()) {
            return false;
        }

        for (int[] visitado : this.visitados) {

            if (igual(visitado, estado)) {
                return true;
            }
        }

        return false;
    }

    private boolean igual(int[] ob1, int[] ob2) {

        for (int pos = 0; pos < ob1.length; pos++) {

            if (ob1[pos] != ob2[pos]) {
                return false;
            }
        }

        return true;
    }
}
