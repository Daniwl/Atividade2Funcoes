package Dao;

import java.sql.Date;

import Model.Seguro;
import Model.Usuario;

public class SeguroDao extends Conexao{
	
	public void Salvar(Usuario u) throws Exception {
		Seguro seguro = new Seguro();
		seguro = seguro.calcularValorBase(u.veiculo.valor);
		
		open();
		stmt = con.prepareStatement("insert into Seguro values(?,?)");
		stmt.setDouble(1, u.getVeiculo().getValor());
		stmt.setInt(2, u.getCodigo());
		stmt.executeUpdate();
		stmt.close();
		close();
	}
}
