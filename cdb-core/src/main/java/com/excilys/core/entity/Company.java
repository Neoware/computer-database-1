package com.excilys.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Company entity DB representation
 * 
 * @author pqwarlot
 *
 */
@Entity
@Table(name="company")
public class Company {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="name")
	private String name;

	/**
	 * Construct an empty company object
	 */
	public Company() {
		this(null, null);
	}

	/**
	 * Construct a company object
	 * 
	 * @param id
	 *            company id
	 * @param name
	 *            company name
	 */
	public Company(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Construct a company object without id
	 * 
	 * @param name
	 *            company name
	 */
	public Company(String name) {
		this(null, name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "id: " + getId() + " name: " + getName();
	}
}
