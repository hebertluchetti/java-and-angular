
package com.hebert.bloodbank.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(schema = "video", name = "playlist")
public class Playlist extends GenericEntity  {
	private static final long serialVersionUID = -8749215355465126128L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
    @NotNull(message ="Video has to be present")
    @ManyToOne
    @JoinColumn(name = "id_video")
    private Video video;
    
    @Column(name = "description")
    private String description;
    
}