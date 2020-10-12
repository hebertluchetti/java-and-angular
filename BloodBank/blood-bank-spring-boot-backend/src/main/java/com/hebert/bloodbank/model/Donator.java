package com.hebert.bloodbank.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.hebert.bloodbank.enums.BloodTypes;
import com.hebert.bloodbank.enums.GenderTypes;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "donator", uniqueConstraints = {@UniqueConstraint(columnNames = {"cpf", "identity"}, name = "uk_donator")})
public class Donator extends GenericEntity  {

	private static final long serialVersionUID = 4419788191957580617L;

	@ApiModelProperty(value = "donator id")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@ApiModelProperty(value = "name")
    @NotNull(message ="name has to be present")
    @NotEmpty(message ="name can not be empty")
    @Column(name = "name", nullable=false)
	private String name;
    
	@ApiModelProperty(value = "cpf number")
    @NotNull(message ="cpf has to be present")
    @NotEmpty(message ="cpf can not be empty")
    @Column(name = "cpf", nullable=false, unique=true)
	private String cpf;
    
	@ApiModelProperty(value = "rg registry number")
    @NotNull(message ="rg identity has to be present")
    @NotEmpty(message ="rg identity can not be empty")
    @Column(name = "identity", nullable=false, unique=true)
	private String identity;
    
	@ApiModelProperty(value = "email")
    @NotNull(message ="email has to be present")
    @NotEmpty(message ="email can not be empty")
    @Column(name = "email", nullable = false, unique=true)
    private String email;
	
	@ApiModelProperty(value = "gender")
    @NotNull(message ="gender has to be present")
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private GenderTypes gender = GenderTypes.OTHER;
    
	@ApiModelProperty(value = "blood type")
    @NotNull(message ="bloodType has to be present")
    @Column(name = "blood_type", nullable = false)
	@Enumerated(EnumType.ORDINAL)
    private BloodTypes bloodType = BloodTypes.O_POS;
    
	@ApiModelProperty(value = "donator weight")
    @NotNull(message ="weight has to be present")
    @Column(name = "weight", nullable = false)
    private BigDecimal weight = new BigDecimal(0.0);
    
	@ApiModelProperty(value = "donator height")
    @NotNull(message ="height has to be present")
    @Column(name = "height", nullable = false)
    private BigDecimal height = new BigDecimal(0.0);
    
	@ApiModelProperty(value = "father")
    @Column(name = "father")
    private String father;
    
	@ApiModelProperty(value = "mother")
    @Column(name = "mother")
    private String mother;
   
    @ApiModelProperty(value = "birth date")
	@Column(name = "birthDate", nullable = false)
    @NotNull(message ="birthDate has to be present")
	private LocalDate birthDate;
    
    @ApiModelProperty(value = "address")
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;

	public Donator(
			@NotNull(message = "name has to be present") @NotEmpty(message = "name can not be empty") String name,
			@NotNull(message = "cpf has to be present") @NotEmpty(message = "cpf can not be empty") String cpf,
			@NotNull(message = "rg identity has to be present") @NotEmpty(message = "rg identity can not be empty") String identity,
			@NotNull(message = "email has to be present") @NotEmpty(message = "email can not be empty") String email,
			@NotNull(message = "gender has to be present") GenderTypes gender,
			@NotNull(message = "bloodType has to be present") BloodTypes bloodType,
			@NotNull(message = "weight has to be present") @NotEmpty(message = "weight can not be empty") BigDecimal weight,
			@NotNull(message = "height has to be present") @NotEmpty(message = "height can not be empty") BigDecimal height,
			String father, String mother, @NotNull(message = "birthDate has to be present") LocalDate birthDate,
			Address address) {
		this.name = name;
		this.cpf = cpf;
		this.identity = identity;
		this.email = email;
		this.gender = gender;
		this.bloodType = bloodType;
		this.weight = weight;
		this.height = height;
		this.father = father;
		this.mother = mother;
		this.birthDate = birthDate;
		this.address = address;
	}

}
