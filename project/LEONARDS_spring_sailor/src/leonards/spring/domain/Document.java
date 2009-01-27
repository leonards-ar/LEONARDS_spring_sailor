/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: Document.java
 *
 * Property of Leonards / Mindpool
 * Created on 17/06/2004
 */
package leonards.spring.domain;

import java.io.Serializable;

import leonards.common.base.*;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class Document extends PersistentObject implements Serializable {
	private String number = null;
	private Integer documentTypeId = null;
	private ReferenceData documentType = null;
	
	/**
	 * @param id
	 */
	public Document(Object id) {
		super(id);
	}

	/**
	 * 
	 */
	public Document() {
		super();
	}

	/** 
	 * @throws NestedException
	 * @see leonards.common.base.PersistentObject#update()
	 */
	protected void update() throws NestedException {

	}

	/** 
	 * @throws NestedException
	 * @see leonards.common.base.PersistentObject#insert()
	 */
	protected void insert() throws NestedException {

	}

	/** 
	 * @throws NestedException
	 * @see leonards.common.base.PersistentObject#delete()
	 */
	public void delete() throws NestedException {

	}

	/**
	 * @return
	 */
	public Integer getDocumentTypeId() {
		return documentTypeId;
	}

	/**
	 * @return
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param integer
	 */
	public void setDocumentTypeId(Integer integer) {
		documentTypeId = integer;
	}

	/**
	 * @param string
	 */
	public void setNumber(String string) {
		number = string;
	}

	/**
	 * @return
	 */
	public ReferenceData getDocumentType() {
		if( documentType == null ) {
			try {
				setDocumentType(StaticDataManager.getDocumentType(getDocumentTypeId()));
			} catch(Throwable ex ) {
				setDocumentType(null);
			}
			
		}
		return documentType;
	}

	/**
	 * @param data
	 */
	public void setDocumentType(ReferenceData data) {
		documentType = data;
	}

}
