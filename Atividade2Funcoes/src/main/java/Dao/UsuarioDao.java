package Dao;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import Model.Usuario;
import Model.Veiculo;


public class UsuarioDao extends Conexao{
	
	public Usuario Listar(int cod) throws Exception {
		open();
		stmt = con.prepareStatement("select * from Usuario where id = "+cod);
		rs = stmt.executeQuery();			
		Usuario p = null;
		if (rs.next()) {
			p = new Usuario();
			p.setCodigo(rs.getInt("id"));
			p.setNome(rs.getString("nome"));			
		}
		close();
		return p;
	}
	
	public List<Usuario> ListarTodos() {
		try {
			open();
			stmt = con.prepareStatement("SELECT U.id,U.nome,U.cpf,U.sexo,U.nascimento,V.nome nomeVeiculo,V.valor valorVeiculo "
					+ "FROM USUARIO U INNER JOIN VEICULO V ON V.id = U.id");
			rs = stmt.executeQuery();
			List<Usuario> lista = new ArrayList<Usuario>();
			while (rs.next()) {
				Usuario p = new Usuario();
				Veiculo v = new Veiculo();
				p.setCodigo(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setCpf(rs.getInt("cpf"));
				p.setSexo(rs.getString("sexo").charAt(0));
				p.setNascimento(rs.getDate("nascimento"));
				v.setNome(rs.getString("nomeVeiculo"));
				v.setValor(rs.getDouble("valorVeiculo"));
				p.setVeiculo(v);
				lista.add(p);
			}
			close();
			return lista;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}
	
	public void Salvar(Usuario u) throws Exception {
		VeiculoDao vd = new VeiculoDao();
		SeguroDao sd = new SeguroDao();
		u.setVeiculo(vd.Salvar(u.veiculo));
		
		String generatedColumns[] = { "ID" };
		open();
		stmt = con.prepareStatement("insert into Usuario values(?,?,?,?,?)", generatedColumns);
		stmt.setString(1, u.getNome());
		stmt.setLong(2, u.getCpf());
		stmt.setString(3, String.valueOf(u.getSexo()));
		stmt.setDate(4,(Date) u.getNascimento());
		stmt.setInt(5, u.getVeiculo().getCodigo());
		rs = stmt.executeQuery();
		if (rs.next()) {
		    u.setCodigo((int) rs.getLong(1));
		}
		stmt.close();
		close();
		
		sd.Salvar(u);
	}
}
