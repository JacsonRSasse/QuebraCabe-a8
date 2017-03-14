package quebracabeca8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Problema {
	private int[] estadoInicial;
	private int[] estadoFinal;

	public Problema() {
		estadoFinal = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 0};
	}

	public HashMap<int[], List<int[]>> getSucerrores( int[] no) {
		HashMap<int[], List<int[]>> map = new HashMap<>();		
		List<int[]> sucessores = new ArrayList<>();
		int[] novo;
		
		
		for (int pos = 0; pos < no.length; pos++) {
			if ((pos != 2) && (pos != 5) && (pos != 8)) {
				if ((no[pos + 1] == 0)) {
					novo = cloneList(no);
										
					novo[pos+1] = novo[pos];
					novo[pos] = 0;
										
					sucessores.add(novo);
				}				
			}
			
			if ((pos != 0) && (pos != 3) && (pos != 6)) {
				if ((no[pos - 1] == 0)) {
					novo = cloneList(no);
					
					novo[pos - 1] = novo[pos];
					novo[pos] = 0;
										
					sucessores.add(novo);
				}				
			}
			
			if ((pos != 6) && (pos != 7) && (pos != 8)) {
				if ((no[pos + 3] == 0)) {
					novo = cloneList(no);
					
					novo[pos+3] = novo[pos];
					novo[pos] = 0;
					
					sucessores.add(novo);
				}				
			}
			
			if ((pos != 0) && (pos != 1) && (pos != 2)) {
				if ((no[pos - 3] == 0)) {					
					novo = cloneList(no);
					
					novo[pos-3] = novo[pos];
					novo[pos] = 0;
					
					sucessores.add(novo);					
				}
			}
		}
		
		map.put(no, sucessores);

		return map;
	}

	public int[] getEstadoFinal() {
		return estadoFinal;
	}

	public int[] getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(int[] estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public void setEstadoInicial() {
		estadoInicial = new int[9];
		for (int i = 0; i < estadoInicial.length; i++) {
			estadoInicial[i] = i;
		}
		aleatorioQC(estadoInicial);
	}
	
	public static int[] cloneList(int[] list) {
	    int[] clone = new int[9];
	    
	    for(int i = 0; i < list.length; i++){
	    	clone[i] = list[i];
	    }
	    
	    return clone;
	}

	public boolean isObjetivo(int[] estado){
		int[] objetivo = this.getEstadoFinal();
		
		for ( int pos = 0; pos < estado.length; pos++){
			
			if (objetivo[pos] != estado[pos])
				return false;
		}
		
		return true; 
	}
	
	public void aleatorioQC(int[] qc){
		List<Integer> posicoes = new ArrayList<>(9);
		for (int i = 0; i < 9; i++) {
			posicoes.add(i);
		}
		//Torna a escolha das posicoes aleatorias
		Collections.shuffle(posicoes);
		System.out.println(posicoes);
		
		//Sorteia o n�mero de tentativa de mudanca
		int num = 1 + (int) (Math.random() * 100);
		int i = 0;
		while (i<num){
			
			for (Integer pos : posicoes) {
				if ((pos != 2) && (pos != 5) && (pos != 8)) {
					if ((qc[pos + 1] == 0)) {
						
						qc[pos+1] = qc[pos];
						qc[pos] = 0;
						
					}				
				}
				
				if ((pos != 0) && (pos != 3) && (pos != 6)) {
					if ((qc[pos - 1] == 0)) {
						
						qc[pos-1] = qc[pos];
						qc[pos] = 0;

					}				
				}
				
				if ((pos != 6) && (pos != 7) && (pos != 8)) {
					if ((qc[pos + 3] == 0)) {
						
						qc[pos+3] = qc[pos];
						qc[pos] = 0;

					}				
				}
				
				if ((pos != 0) && (pos != 1) && (pos != 2)) {
					if ((qc[pos - 3] == 0)) {
						
						qc[pos-3] = qc[pos];
						qc[pos] = 0;
						
					}
				}
			}
			
			i++;
		}
	}
}
