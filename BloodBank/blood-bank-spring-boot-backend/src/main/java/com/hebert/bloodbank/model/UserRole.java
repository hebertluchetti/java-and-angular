package com.hebert.bloodbank.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(schema = "`user`", name = "user_role")
public class UserRole extends GenericEntity  {
	private static final long serialVersionUID = 4189620419958597365L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
 
    
    @CreationTimestamp
	@Column(name = "created_at", nullable = false)
    @NotNull(message ="createdAt has to be present")
	private LocalDateTime createdAt;
 
}