package Dao;
import java.sql.Date;
import java.sql.Statement;

import Model.Veiculo;

public class VeiculoDao extends Conexao{
	
	public Veiculo Salvar(Veiculo v) throws Exception {		
		String generatedColumns[] = { "ID" };
		
		open();
		stmt = con.prepareStatement("insert into VEICULO values(?,?)", generatedColumns);
		stmt.setString(1, v.getNome());
		stmt.setDouble(2, v.getValor());
		rs = stmt.executeQuery();
		if (rs.next()) {
		    v.setCodigo((int) rs.getLong(1));
		}
		stmt.close();
		close();
		return v;
	}
}
