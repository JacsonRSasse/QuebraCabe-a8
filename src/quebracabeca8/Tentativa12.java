/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quebracabeca8;

import java.util.Stack;
import javax.swing.DefaultListModel;

/**
 *
 * @author User
 */
public class Tentativa12 {

    public int[] estadoAtual;
    public Problema problema;
    public Busca busca;
    public Stack<No> solucao;

    public void resolve(String busca) {
        estadoAtual = new int[]{4, 6, 5, 7, 8, 0, 3, 1, 2};
        problema = new Problema();
        this.busca = new Busca(problema);
        problema.setEstadoInicial(estadoAtual);

        long ti = 0, tf = 0;
        solucao = null;

        System.out.println("== Busca A*==");
        ti = System.currentTimeMillis();
        solucao = this.busca.A_Asterisco();
        tf = System.currentTimeMillis();

        if (solucao != null) {
            //solucao.pop();
            System.out.println("Numero de passos: " + solucao.size());
            System.out.println("Tempo que levou: " + (tf - ti) + "");
            imprimeCaminho((Stack<No>) solucao.clone());

            if (this.problema.isObjetivo(solucao.pop().getEstado())) {
                System.out.println("Sucesso!");
            }

        } else {
            System.out.println("Erro!");
        }
    }

    public void imprimeCaminho(Stack<No> caminho) {
        if (caminho == null) {
            System.out.println("Caminho não encontrado");
            return;
        }

        DefaultListModel lista = new DefaultListModel();
        No no;
        int quantidade = caminho.size();
        do {
            no = caminho.pop();
            System.out.println(no);
            //lista.addElement(no.toString());

        } while (!caminho.isEmpty());
        System.out.println(lista);
        System.out.println("Número de passos: " + quantidade);
    }

    public static void main(String[] args) {
        Tentativa12 tentoDeNv = new Tentativa12();
        tentoDeNv.resolve("A*");
    }
}
