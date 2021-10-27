package Model;

import java.util.Calendar;

public class Seguro {
	public int id;
	public double valorBase;
	public Usuario usuario;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValorBase() {
		return valorBase;
	}
	public void setValorBase(double valorBase) {
		this.valorBase = valorBase;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Seguro() {
		
	}
	
	public Seguro(int id, double valorBase, Usuario usuario) {
		this.id = id;
		this.valorBase = valorBase;
		this.usuario = usuario;
	}
	
	public double calcularValorTotal(Usuario usuario) {
		double valorBase = calcularValorBase(usuario.veiculo.valor).valorBase;
		double acrescimo = 0;
		double valorTotal = 0;
		
    	//Idade
		Calendar cal = Calendar.getInstance();
    	cal.setTime(usuario.nascimento);
		int idade = Calendar.getInstance().get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if(cal.get(Calendar.DAY_OF_MONTH) >= cal.get(Calendar.DAY_OF_MONTH) && cal.get(Calendar.MONTH) >= cal.get(Calendar.MONTH)){
            idade--;
        }       			
		//Acréscimos idade
        if(idade >= 18 && idade <= 25)
        	acrescimo = valorBase * 0.1; 
        else if(idade >= 26 && idade <= 30)
        	acrescimo = valorBase * 0.05; 
        else if(idade >= 31 && idade <= 35)
        	acrescimo = valorBase * 0.02; 
        	
		//Acréscimos sexo
        if(Character.toUpperCase(usuario.sexo) == 'M')
        	acrescimo += valorBase * 0.1;
        	
        valorTotal = valorBase + acrescimo;
        
        return valorTotal;
	}
	
	public Seguro calcularValorBase(double valorVeiculo){
		Seguro seguro = new Seguro();
		seguro.valorBase = valorVeiculo * 0.03;
		
        return seguro;
	}
	
}
