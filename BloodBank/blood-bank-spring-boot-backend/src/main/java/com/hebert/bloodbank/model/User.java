package com.hebert.bloodbank.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(schema = "`user`", name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "uk_email")})

public class User extends GenericEntity  {
	private static final long serialVersionUID = -5972183914238042930L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
    @NotNull(message ="name has to be present")
    @NotEmpty(message ="name can not be empty")
    @Column(name = "name", nullable=false)
	private String name;
	 

    @NotNull(message ="email has to be present")
    @NotEmpty(message ="email can not be empty")
    @Column(name = "email",  nullable = false, unique=true)
    private String email;
    
    @NotNull(message ="password has to be present")
    @NotEmpty(message ="password can not be empty")
    @Column(name = "password", nullable = false)
    private String password;
    
    private boolean enabled = true;
}