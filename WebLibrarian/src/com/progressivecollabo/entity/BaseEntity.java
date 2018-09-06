package com.progressivecollabo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;

/**
 * Base class for all persistable data
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String uuid;
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDateTime modifiedDate;
	private String archivedBy;
	private LocalDateTime archivedDate;
	private boolean archived;
	String language;

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

	}

	public void delete() {
	}

	public void get() {
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

	public boolean isArchived() {
		return archived;
	}

	public String getLanguage() {
		return language;
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

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * can be used by subclasses to set an _id prefix
	 */
	public String IdPrefix() {
		return "";
	}
}
