package com.phoneBook.dbOperation;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Persistence;

import com.phoneBook.controller.PersonEntity;
import com.phoneBook.controller.PersonController;
public class dbOperation {

	private static final String prUnitName = "phoneBook";   
         
	    
	    private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(prUnitName).createEntityManager();
	    private static EntityTransaction transactionObj = entityMgrObj.getTransaction();

	             // Veri tabanýndan kiþiler listeleniyor arama iþlemi gerçekleþmiþ ise aranan kiþiyi arýyor ve onu geri döndürüyor. 
	    	  public static List getAllPerson(String pAdsoyad) {
	    		  Query queryObj;
	    		  if(pAdsoyad==null || pAdsoyad==""){
	            queryObj = entityMgrObj.createQuery("SELECT p FROM PersonEntity p ");
	    		  }
	    		  else{
	            queryObj = entityMgrObj.createQuery("SELECT p FROM PersonEntity p WHERE p.adSoyad= :adsoyad");
	            queryObj.setParameter("adsoyad",pAdsoyad );
	    		  }
	            List PersonList = queryObj.getResultList();
	    		
	            if (PersonList != null && PersonList.size() > 0) {           
	                return PersonList;
	            } else {
	                return null;
	            }
	        }
	    	  // Ýd si gönderilen kiþinin veri tabanýndan diðer bilgileri çekiliyor ve geri döndürülüyor.
	    	  public static PersonEntity getPersonDetail(int Pid){
	    		   PersonEntity Pr =new PersonEntity();
	    		   try{
	    			   Query queryObj = entityMgrObj.createQuery("SELECT p FROM PersonEntity p WHERE p.id= :id");
		    		   queryObj.setParameter("id", Pid);
	    		       Pr= (PersonEntity) queryObj.getSingleResult();
		                return Pr;
	    		   }catch (NoResultException e){
	    			   return null;
	    		   }
	    		   
	    	  }
	    	  // Kiþi veri tabanýnda girilen bilgiler doðrultusunda güncelleniyor
	    	  public static String uptdatePersonDb(int Pid, String Padsoyad, String Peposta, long Pcep, long Pis,long Pev) {
	    		          if (!transactionObj.isActive()) {
	    		        	  transactionObj.begin();

	    		          }   try{      
	    		              Query queryObj = entityMgrObj.createQuery("UPDATE PersonEntity p SET p.adSoyad=:adsoyad, p.cepTel=:ceptel, p.eposta=:eposta, p.evTel=:evtel, p.isTel=:istel WHERE p.id= :id");         
	    		              queryObj.setParameter("id", Pid);
	    		              queryObj.setParameter("adsoyad",Padsoyad );
	    		              queryObj.setParameter("ceptel",Pcep );
	    		              queryObj.setParameter("eposta",Peposta );
	    		              queryObj.setParameter("evtel",Pev );
	    		              queryObj.setParameter("istel",Pis );
	    		               queryObj.executeUpdate();
	    		               transactionObj.commit();	    		              
	    		               return "personList.xhtml?faces-redirect=true";
	    		               } catch (NoResultException e){
	    		          return null;
	    		          }
	    		   }
	    	  // Ýd si gönderilen kiþi silinme iþlemi yapýlýyor 
	    	  public static String deletePersonDb(int personId){
	    		  if (!transactionObj.isActive()) {
		        	  transactionObj.begin();
		             }
	    		    PersonEntity Person =new PersonEntity();
	    		    try{ 
	    		    	Person.setId(personId);
	    			  entityMgrObj.remove(entityMgrObj.merge(Person));
	    			  transactionObj.commit();
	    			  return "personList.xhtml?faces-redirect=true";
	    		      }catch(NoResultException e){
	    			     return null;
	    		      }

	    	   }
	    	  // Yeni kiþi veri tabanýna kaydediliyor.
			public static String createPersonDb(String pAdsoyad, String pEposta, long pCep, long pIs,long pEv){
				if (!transactionObj.isActive()) {
		        	  transactionObj.begin();
		             }
				PersonEntity personE =new PersonEntity();
				try{
					personE.setAdSoyad(pAdsoyad);
					personE.setCepTel(pCep);
					personE.setIsTel(pIs);
					personE.setEvTel(pEv);
					personE.setEposta(pEposta);
					entityMgrObj.persist(personE);
					transactionObj.commit();
					return "personList.xhtml?faces-redirect=true";
				}catch(NoResultException e){
					return null;
				}
			}
         
}
