/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.pojo.Document;
import com.model.pojo.Documentitem;
import com.util.HibernateUtil;
import java.util.Comparator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mylosh
 */
public class DocumentDAO {
    
    private List<Document> documents;
    
    public List <Document> AllDocuments() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            documents = session.createCriteria(Document.class).list();
            documents.sort(Comparator.comparingInt(Document::getId));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return documents;
    }
    
    public Document getDocumentWithItems(Document document) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Document doc = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Document where id = '"+document.getId()+"'");
            doc = (Document)q.list().get(0);
            Hibernate.initialize(doc.getDocumentitems());
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return doc;
    }
    
    public Integer getId() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select max(D.id) from Document D";
        Query query = session.createQuery(hql);
        List<Integer> results = query.list();
        Integer docId = 1;
        if (results.get(0) != null)
            docId = results.get(0) + 1;
        session.flush();
        session.close();
        return docId;
    }
    
    public Integer getItemId() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select max(DI.id) from Documentitem DI";
        Query q = session.createQuery(hql);
        List<Integer> results = q.list();
        Integer docItemId = 1;
        if (results.get(0) != null)
            docItemId = results.get(0) + 1;
        session.flush();
        session.close();
        return  docItemId;
    }
    
    public void add(Document newDocument) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String id = Integer.toString(newDocument.getId());
            session.beginTransaction();
            session.merge(newDocument);
            session.flush();
            System.out.println("New document saved, id: " + id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    
    public void delete(Document document) {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {    
            session.beginTransaction();
            session.delete(document);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    } 
    
    public void update(Document document) {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();
            session.update(document);  
            session.flush();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }
    
    public void addItem(Documentitem documentitem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String id = Integer.toString(documentitem.getId());
            session.beginTransaction();
            session.merge(documentitem);
            session.flush();
            System.out.println("New item saved, id: " + id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    
    public void deleteItem(Documentitem documentitem) {
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();
            session.delete(documentitem);
            session.flush();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();
    }
    
    public void updateItem(Documentitem documentitem) {
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();
            session.update(documentitem);  
            session.flush();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();
    }
    
}
