package com.progressivecollabo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base class for all persistable data
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	LocalDateTime createdDate;
	LocalDateTime modifiedDate;

}
