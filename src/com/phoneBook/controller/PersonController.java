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
	//Ki�ilerin listeleme i�lemi ve ara i�lemi yap�l�yor
	public List personListMet(PersonController person) {
		
        return dbOperation.getAllPerson(person.getAdSoyad());      
    }
	/* Ki�i listesinde bir herhangib bir ki�iye bas�ld��unda �al��an method
	 * Ki�inin Id sini uid de�i�keni ile setliyor ve personDetail sayfas�nda ki action i�ersinde kullan�l�yor */
	public String selectid(int selecetId){
		    sid=selecetId;
		   return "personDetail.xhtml?faces-redirect=true";
	}
	// Se�ilen ki�inin bilgilerini �ekmek i�in id g�nderiliyor ve dbOperation s�n�f�ndan gelen bilgiler g�nd�r�l�yor. 
	public PersonEntity personDetailById(int Uid){
		 
		return dbOperation.getPersonDetail(Uid);
		
	}
	//D�zenle Butonuna T�kland���nda �al��an method
   public String editPerson(int Sid){
	   sid=Sid;
	   return "personEdit.xhtml?faces-redirect=true";
   }
   // Ki�i update edildikten sonra dbOperation s�n�f�na post edilme i�lemi yap�l�yor.
   public String updatePersonDetail(PersonController personC){
	   
	   return dbOperation.uptdatePersonDb(personC.getSid(),personC.getAdSoyad(),personC.getEposta(),personC.getEvTel(),personC.getIsTel(),personC.getCepTel());
   }
   // Ki�i silinmesi i�in id'sini dbOperation s�n�f�na g�nderiyor
   public String personDelete(int id){
	   return dbOperation.deletePersonDb(id);
   }
   // Yeni ki�i bilgilerini dbOperation s�n�f�na post ediyor. 
   public String personCreate(PersonController createP){
	   
	   return dbOperation.createPersonDb(createP.getAdSoyad(), createP.getEposta(), createP.getIsTel(),createP.getCepTel(),createP.getEvTel());
   }
  


	
}
