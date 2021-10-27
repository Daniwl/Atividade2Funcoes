package Model;

public class Veiculo {
	public int codigo;
	public String nome;
	public double valor;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int id) {
		this.codigo = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public Veiculo() {
		
	}
	
	public Veiculo(int id, String nome, double valor) {
		this.codigo = id;
		this.nome = nome;
		this.valor = valor;
	}
	
}
