
package com.hebert.bloodbank.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "state", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "uk_name")})
public class State  extends GenericEntity {
	private static final long serialVersionUID = 7335922672819700822L;
	
	@ApiModelProperty(value = "state id")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(value = "name")
    @NotNull(message ="name has to be present")
    @NotEmpty(message ="name can not be empty")
    @Column(name = "name", nullable=false)
	private String name;
	
	// Protection for cyclic reference in Json serialization
	// Insert this annotation we don't want the associated objects to come
	// Necessary when both are aware of the other but only one will have the domain
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="state")
	private List<City> cities = new ArrayList<>();

	public State(Long id, String nome) {
		super();
		this.id = id;
		this.name = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;	
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
