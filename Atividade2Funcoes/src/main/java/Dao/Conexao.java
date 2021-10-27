package Dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexao {
	Connection con;  // Conexao ao Banco de Dados
	PreparedStatement stmt; // Acessa a Tabela ( insert, update, delete , select)
	ResultSet rs; // Consulta a Tabela( select )
	CallableStatement call; // Procedures e Function
	
	public void open() throws Exception{
		String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=TESTE_SPEED;user=sa;password=senhasa";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection(connectionUrl);		
	}
	
	public void close() throws Exception{		
		con.close();
	}
}
