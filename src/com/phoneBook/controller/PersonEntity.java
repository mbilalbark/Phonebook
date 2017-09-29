package com.phoneBook.controller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="persons")


public class PersonEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id; 
	    private String adSoyad;
		private long cepTel;
		private long isTel;
		private long evTel;
		private String eposta;
	    
	    
	    public PersonEntity() { }
	 

	 public int getId(){
		 return id;
	 }

     public void setId(int id){
	   this.id=id;
     }
		public String getAdSoyad() {
			return adSoyad;
		}


		public void setAdSoyad(String adSoyad) {
			this.adSoyad = adSoyad;
		}


		public long getCepTel() {
			return cepTel;
		}


		public void setCepTel(long cepTel) {
			this.cepTel = cepTel;
		}


		public long getIsTel() {
			return isTel;
		}


		public void setIsTel(long isTel) {
			this.isTel = isTel;
		}


		public long getEvTel() {
			return evTel;
		}


		public void setEvTel(long evTel) {
			this.evTel = evTel;
		}


		public String getEposta() {
			return eposta;
		}


		public void setEposta(String eposta) {
			this.eposta = eposta;
		}
	 

}
