package com.avob.openadr.server.common.vtn.models.venmarketcontext;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.avob.openadr.server.common.vtn.models.ven.Ven;

/**
 * VenGroup persistent object - database representation
 * 
 * @author bertrand
 *
 */
@Entity
@Table(name = "venmarketcontext")
public class VenMarketContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8652374934772768274L;

	/**
	 * Autogenerated unique id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Column(unique = true)
	private String name;

	private String description;
	
	private String color;

	@ManyToMany(mappedBy = "venMarketContexts")
	private Set<Ven> vens;

	public VenMarketContext() {
	}

	public VenMarketContext(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@PreRemove
	public void removeVenGroupFromMarketContext() {
		if (vens != null) {
			for (Ven ven : vens) {
				ven.removeMarketContext(this);
			}
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Ven> getVens() {
		return vens;
	}

	public void setVens(Set<Ven> vens) {
		this.vens = vens;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
