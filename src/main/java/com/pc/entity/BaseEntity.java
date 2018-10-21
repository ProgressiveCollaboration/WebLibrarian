package com.pc.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;

import com.pc.db.MDB;

/**
 * Base class for all persistable data
 */
public class BaseEntity implements Serializable {

	public static String _createdBy = "createdBy";
	public static String _createdDate = "createdDate";
	public static String _modifiedBy = "modifiedBy";
	public static String _modifiedDate = "modifiedDate";
	public static String _archivedBy = "archivedBy";
	public static String _archivedDate = "archivedDate";
	public static String _archived = "archived";

	private static final long serialVersionUID = 1L;
	@Id
	private String uuid;
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDateTime modifiedDate;
	private String archivedBy;
	private LocalDateTime archivedDate;

	public String getId() {
		return uuid;
	}

	@PrePersist
	private void prepersist() {
		if (uuid == null)
			setUuid(IdPrefix() + UUID.randomUUID().toString());

		if (createdDate == null) {
			// setCreatedBy(getSessionUser());
			setCreatedDate(LocalDateTime.now());
		}

		// setModifiedBy(getSessionUser());
		setModifiedDate(LocalDateTime.now());
	}

	public void save() {
		MDB.getDS().save(this);
	}

	public void delete() {
		MDB.getDS().delete(this);
	}

	public String getUuid() {
		return uuid;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public String getArchivedBy() {
		return archivedBy;
	}

	public LocalDateTime getArchivedDate() {
		return archivedDate;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setArchivedBy(String archivedBy) {
		this.archivedBy = archivedBy;
	}

	public void setArchivedDate(LocalDateTime archivedDate) {
		this.archivedDate = archivedDate;
	}

	/**
	 * can be used by subclasses to set an _id prefix
	 */
	public String IdPrefix() {
		return "";
	}
	
	public <E extends BaseEntity> String persist(E entity) {
		MDB.getDS().save(entity);
		return entity.getId();
	}
	
}
