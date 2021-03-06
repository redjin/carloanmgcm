package business.entity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integration.AziendaDAO;
import javafx.beans.property.SimpleStringProperty;

public class Azienda {
	
	private SimpleStringProperty nome_azienda;
	private AziendaDAO aziendadao;
	
	
	public Azienda(String nome_azienda2){
		aziendadao = new AziendaDAO();
		nome_azienda = new SimpleStringProperty(nome_azienda2);
	}
	public Azienda(){
		aziendadao = new AziendaDAO();
		nome_azienda=new SimpleStringProperty();
		
	}
	
	
	public String toString(){
		return nome_azienda.get();
	}


	public String getNome_azienda() {
		return nome_azienda.get();
	}


	
	public void setNome_azienda(String nome_azienda) {
		this.nome_azienda.set(nome_azienda);;
	}

	public void create(Azienda entity) throws SQLException {
		aziendadao.create(entity);
	}
	public void delete(String nomeAgenzia) throws SQLException {
		aziendadao.delete(nomeAgenzia);
	}
	
	public void update(Azienda entity) {
		aziendadao.update(entity);
		}
	
	public Azienda read(String ID) throws SQLException {
		return aziendadao.read(ID);
	}
	
	public List<Azienda> getAll() throws SQLException {
		return aziendadao.getAll();
	}
	
	
}
