package Model;

import java.sql.Date;

public class Usuario {
	public int codigo;
	public String nome;
	public long cpf;	
	public char sexo;
	public Date nascimento;
	public Veiculo veiculo;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public Usuario() {
		
	}
	
	public Usuario(int codigo, String nome, char sexo, Date nascimento, Veiculo veiculo) {
		this.codigo = codigo;
		this.nome = nome;
		this.sexo = sexo;
		this.nascimento = nascimento;
		this.veiculo = veiculo;
	}
	
	private static int calcularDigito(String str, int[] peso) {
	      int soma = 0;
	      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
	         digito = Integer.parseInt(str.substring(indice,indice+1));
	         soma += digito*peso[peso.length-str.length()+indice];
	      }
	      soma = 11 - soma % 11;
	      return soma > 9 ? 0 : soma;
	}
	
	public boolean ValidaCPF(String cpf) {
	      if ((cpf==null) || (cpf.length()!=11)) return false;
	      int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	      Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
	      Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
	      return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	   }
}