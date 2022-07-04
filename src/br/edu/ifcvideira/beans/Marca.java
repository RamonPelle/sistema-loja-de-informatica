package br.edu.ifcvideira.beans;

import java.sql.Timestamp;

public class Marca {

		private int idMarca;
		private String descricaoMarca;
		
		public Marca(){
			
		} 
		public int getIdMarca() {
			return idMarca;
		}
		public void setIdMarca(int idMarca) {
			this.idMarca = idMarca;
		}
		public String getDescricaoMarca() {
			return descricaoMarca;
		}
		public void setDescricaoMarca(String descricaoMarca) {
			this.descricaoMarca = descricaoMarca;
		}
		public void setDataMarca(Timestamp timestamp) {
			
		}
}