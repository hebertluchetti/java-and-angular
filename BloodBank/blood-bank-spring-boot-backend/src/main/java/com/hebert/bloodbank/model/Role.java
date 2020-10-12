
package com.hebert.bloodbank.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.hebert.bloodbank.enums.UserRoleTypes;

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
@Table(schema = "`user`", name = "role")
public class Role extends GenericEntity  {

	private static final long serialVersionUID = -2853921225507839982L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
      
    @NotNull(message ="role has to be present")
    @NotEmpty(message ="role can not be empty")
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleTypes role;
    
    @Column(name = "description")
    private String description;
    
}