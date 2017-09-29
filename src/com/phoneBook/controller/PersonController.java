package com.phoneBook.controller;

import javax.faces.bean.ManagedBean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;

import com.phoneBook.dbOperation.*;
import com.phoneBook.controller.PersonEntity;
@ManagedBean
@ViewScoped

public class PersonController {

	private int id;
	private String adSoyad;
	private long cepTel;
	private long isTel;
	private long evTel;
	private String eposta;
	private static int sid;
	

	public String getAdSoyad() {
		return adSoyad;
	}
	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	//Kiþilerin listeleme iþlemi ve ara iþlemi yapýlýyor
	public List personListMet(PersonController person) {
		
        return dbOperation.getAllPerson(person.getAdSoyad());      
    }
	/* Kiþi listesinde bir herhangib bir kiþiye basýldýðunda çalýþan method
	 * Kiþinin Id sini uid deðiþkeni ile setliyor ve personDetail sayfasýnda ki action içersinde kullanýlýyor */
	public String selectid(int selecetId){
		    sid=selecetId;
		   return "personDetail.xhtml?faces-redirect=true";
	}
	// Seçilen kiþinin bilgilerini çekmek için id gönderiliyor ve dbOperation sýnýfýndan gelen bilgiler göndürülüyor. 
	public PersonEntity personDetailById(int Uid){
		 
		return dbOperation.getPersonDetail(Uid);
		
	}
	//Düzenle Butonuna Týklandýðýnda Çalýþan method
   public String editPerson(int Sid){
	   sid=Sid;
	   return "personEdit.xhtml?faces-redirect=true";
   }
   // Kiþi update edildikten sonra dbOperation sýnýfýna post edilme iþlemi yapýlýyor.
   public String updatePersonDetail(PersonController personC){
	   
	   return dbOperation.uptdatePersonDb(personC.getSid(),personC.getAdSoyad(),personC.getEposta(),personC.getEvTel(),personC.getIsTel(),personC.getCepTel());
   }
   // Kiþi silinmesi için id'sini dbOperation sýnýfýna gönderiyor
   public String personDelete(int id){
	   return dbOperation.deletePersonDb(id);
   }
   // Yeni kiþi bilgilerini dbOperation sýnýfýna post ediyor. 
   public String personCreate(PersonController createP){
	   
	   return dbOperation.createPersonDb(createP.getAdSoyad(), createP.getEposta(), createP.getIsTel(),createP.getCepTel(),createP.getEvTel());
   }
  


	
}
