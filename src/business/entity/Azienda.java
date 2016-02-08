package business.entity;
import java.sql.SQLException;
import java.util.List;

import integration.AziendaDAO;
import javafx.beans.property.SimpleStringProperty;

public class Azienda {
	
	private SimpleStringProperty nome_azienda;
	private String nome;
	private String citta;
	private AziendaDAO aziendadao;
	
	
	public Azienda(String nome_azienda2){
		nome_azienda = new SimpleStringProperty(nome_azienda2);
		nome = nome_azienda2;
	}
	public Azienda(){
		
	}
	
	
	public String toString(){
		return nome;
	}


	public String getNome_azienda() {
		return nome_azienda.get();
	}


	public void setNome_azienda(String nome_azienda) {
		this.nome_azienda.set(nome_azienda);;
	}
	//TRANSFER OBJ

	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	
	public Azienda read(String ID) throws SQLException {
		return aziendadao.read(ID);
	}
	
	public List<Azienda> getAll() throws SQLException {
		return aziendadao.getAll();
	}
	
	
}
