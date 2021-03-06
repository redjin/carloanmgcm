package integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import business.entity.Automobile;

public class AutomobileDAO extends DAOAB<Automobile>{
	private static final String INSERT_QUERY="INSERT INTO Auto VALUES(?,?,?,?,?,1)";
	private static final String GET_AUTO_QUERY="SELECT * FROM Auto WHERE CodAgenzia=?";
	private final static String GET_DISP_AUTO_QUERY="SELECT * "
			+ "FROM Auto "
			+ "WHERE CodAgenzia=? AND CodFascia=? AND Disponibile=1";
	private final static String AUTO_FUORI_QUERY="UPDATE Auto SET Disponibile=0 WHERE targa=?";
	private static final String READ_QUERY="SELECT * FROM Auto WHERE targa=?";
	private static final String DELETE_QUERY="DELETE FROM Auto WHERE targa=?";
	
	@Override
	public boolean create(Automobile entity) throws SQLException {
		Connection connessione=MySqlDaoFactory.connetti();
		PreparedStatement prepStat=connessione.prepareStatement(INSERT_QUERY);
		String targa=entity.getTarga();
		prepStat.setString(1, targa);
		String marcaAuto=entity.getModello_auto();
		prepStat.setString(2, marcaAuto);
		int cilindrata=entity.getCilindrata();
		prepStat.setInt(3, cilindrata);
		String codAgenzia=entity.getAgenzia();
		prepStat.setString(4, codAgenzia);
		String codFascia=entity.getCategoria();
		prepStat.setString(5, codFascia);
		prepStat.executeUpdate();
		return false;
	}

	@Override
	public boolean update(Automobile entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String ID) throws SQLException {
		boolean b;
		Connection connessione=MySqlDaoFactory.connetti();
		PreparedStatement prepStat=connessione.prepareStatement(DELETE_QUERY);
		prepStat.setString(1,ID);
		b=prepStat.executeUpdate()>=1;
		return b;
	}

	@Override
	public Automobile read(String targa) throws SQLException {
		Connection connessione=MySqlDaoFactory.connetti();
		PreparedStatement prepStat=connessione.prepareStatement(READ_QUERY);
		prepStat.setString(1,targa);
		ResultSet risultato =prepStat.executeQuery();
		List<Automobile> lista =getLista(risultato);
		if(lista.size()>0)
			return lista.get(FIRST);
		else
			return null;
	}

	@Override
	public List<Automobile> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Automobile> getAuto(String sede) throws SQLException{
		Connection connessione=MySqlDaoFactory.connetti();
		PreparedStatement prepStat=connessione.prepareStatement(GET_AUTO_QUERY);
		prepStat.setString(1, sede);
		ResultSet risultato=prepStat.executeQuery();
		List<Automobile> lista= getLista(risultato);
		return lista;
	}
	
	public List<Automobile> getAutoDisponibili(String nomeFascia,String sede) throws SQLException{
		Connection connessione=MySqlDaoFactory.connetti();
		PreparedStatement prepStat=connessione.prepareStatement(GET_DISP_AUTO_QUERY);
		prepStat.setString(1, sede);
		prepStat.setString(2, nomeFascia);
		ResultSet risultato=prepStat.executeQuery();
		List<Automobile> lista= getLista(risultato);
		return lista;
	}
	public static void setAutoFuori(String targa) throws SQLException{
		Connection connessione=MySqlDaoFactory.connetti();
		PreparedStatement prepStat=connessione.prepareStatement(AUTO_FUORI_QUERY);
		prepStat.setString(1, targa);
		prepStat.executeUpdate();
	}
	
	
	private List<Automobile> getLista(ResultSet resultSet) throws SQLException{
		ArrayList<Automobile> automobili=new ArrayList<Automobile>();
		
		while(resultSet.next()){
			Automobile elemento =new Automobile();
			String targa=resultSet.getString("Targa");
			elemento.setTarga(targa);
			String nomeAuto=resultSet.getString("NomeAuto");
			elemento.setModello_auto(nomeAuto);
			int cilindrata=resultSet.getInt("Cilindrata");
			elemento.setCilindrata(cilindrata);
			String CodAgenzia=resultSet.getString("CodAgenzia");
			elemento.setAgenzia(CodAgenzia);
			String CodFascia=resultSet.getString("CodFascia");
			elemento.setCategoria(CodFascia);
			int disponibile=resultSet.getInt("Disponibile");
			elemento.setDisponibile(disponibile);
			automobili.add(elemento);
		}
		return automobili;
	}

}
