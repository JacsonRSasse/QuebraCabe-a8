package quebracabeca8;


public class No implements Comparable<No>{
	private int[] estado;
	private No pai = null;
	private int profundidade = 0;
	private int custo = 0;
	private int comparador = 0;
	
	public int[] getEstado() {
		return estado;
	}
	public void setEstado(int[] estado) {
		this.estado = estado;
	}
	public No getPai() {
		return pai;
	}
	public void setPai(No pai) {
		this.pai = pai;
	}
	public int getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(int profundidade) {
		this.profundidade = profundidade;
	}
	public int getCusto() {
		return custo;
	}
	public void setCusto(int custo) {
		this.custo = custo;
	}	
	
	public int getComparador() {
		return comparador;
	}
	public void setComparador(int g) {
		this.comparador = g;
	}
        
        @Override
	public String toString(){
		String valor = "";
		for(int i = 0 ; i < estado.length; i++){
			if (i == (estado.length - 1))
				valor += estado[i];
			else
				valor += estado[i] + ", ";
		}		
		return "[ " + valor + " ]";
	}

	@Override
	public int compareTo(No o) {
		if (this.comparador < o.getComparador()){
			return -1;
		}else{
			return 1;
		}		
	}

}
