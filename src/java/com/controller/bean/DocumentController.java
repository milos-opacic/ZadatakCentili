/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.dao.DocumentDAO;
import com.model.pojo.Document;
import com.model.pojo.Documentitem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author mylosh
 */
@Named(value = "documentController")
@SessionScoped
public class DocumentController implements Serializable {

    /**
     * Creates a new instance of DocumentController
     */
    
    private List<Document> documentList = null;
    private DocumentDAO documentDao = new DocumentDAO();
    private Document document = new Document();
    private Document newDocument = new Document();
    private Documentitem item = new Documentitem();
    private Documentitem newItem = new Documentitem();
    private Integer itemId = 0;
    private Integer docId = 0;
    
    public List<Document> getDocuments() {
        setDocumentList(getDocumentDao().AllDocuments());
        return getDocumentList();
    }
    
    public void addDocument() {
        setDocId(documentDao.getId());
        newDocument.setId(getDocId());
        documentDao.add(newDocument);
        System.out.println("Document successfully saved.");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Document successfully saved!");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        newDocument = new Document();
    }
    
    public void updateDocument() {  
        String Name = this.document.getName();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Name", Name);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        documentDao.update(this.document);  
        System.out.println("Document Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Document updated successfully!");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        this.document = new Document();  
    }  
    
    public void deleteDocument(Document document) {    
        documentDao.delete(document);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully!");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void setDocumentWithItems(Document document) {
        this.document = documentDao.getDocumentWithItems(document);
    }
    
    public void addItem() {
        setItemId(documentDao.getItemId());
        newItem.setDocument(item.getDocument());
        newItem.setId(getItemId());
        documentDao.addItem(newItem);
        System.out.println("Item successfully saved.");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Item successfully saved!");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        newItem = new Documentitem();
    }
    
    public void deleteItem(Documentitem documentitem) {
        documentDao.deleteItem(documentitem);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Item "+documentitem.getName()+"deleted successfully!");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void updateItem() {
        documentDao.updateItem(this.item);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Item "+this.item.getName()+"updated successfully!");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        this.item = new Documentitem();
    }
    
    public List<Documentitem> getItemsInOrderById() {
        List<Documentitem> items = new ArrayList<>(this.document.getDocumentitems());
        items.sort(Comparator.comparingInt(Documentitem::getId));
        return items;
    }
    
    
    public DocumentController() {}
       
    /**
     * @return the documentList
     */
    public List<Document> getDocumentList() {
        return documentList;
    }

    /**
     * @param documentList the documentList to set
     */
    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    /**
     * @return the documentDao
     */
    public DocumentDAO getDocumentDao() {
        return documentDao;
    }

    /**
     * @param documentDao the documentDao to set
     */
    public void setDocumentDao(DocumentDAO documentDao) {
        this.documentDao = documentDao;
    }

    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(Document document) {
        this.document = document;
    }

    /**
     * @return the newDocument
     */
    public Document getNewDocument() {
        return newDocument;
    }

    /**
     * @param newDocument the newDocument to set
     */
    public void setNewDocument(Document newDocument) {
        this.newDocument = newDocument;
    }

    /**
     * @return the docId
     */
    public Integer getDocId() {
        return docId;
    }

    /**
     * @param docId the docId to set
     */
    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    /**
     * @return the newItem
     */
    public Documentitem getNewItem() {
        return newItem;
    }

    /**
     * @param newItem the newItem to set
     */
    public void setNewItem(Documentitem newItem) {
        this.newItem = newItem;
    }

    /**
     * @return the itemId
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the item
     */
    public Documentitem getItem() {
        return item;
    }
    
    /**
     * @param item the item to set
     */
    public void setItem(Documentitem item) {
        this.item = item;
    }
}
