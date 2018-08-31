package com.progressivecollabo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Base class for all persistable data
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String uuid; // 3PUB-YEAR-UUID

	String craetedBy;
	LocalDateTime createdDate;
	String modifiedBy;
	LocalDateTime modifiedDate;
	String archivedBy;
	LocalDateTime archivedDate;
	
	boolean archived;
	
	List<Pricing> pricinginformation;
	
	String language;
	String publisherID;
	String publisherName;
	String releaseYear;
	String publisherWebsite; 
	String copyright; 
	
	public static class Pricing {
		String priceType; // RENTAL, BUY
		double unitCost;
		String unitCostCurrency;
	}
	
	// 0 - true, 12.3 $
	// 1 - false, 24.4 $
	
}
