
package com.hebert.bloodbank.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.annotations.ApiModelProperty;
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
@Table(schema = "video", name = "video", uniqueConstraints = {@UniqueConstraint(columnNames = "code", name = "uk_code")})
public class Video extends GenericEntity  {
	private static final long serialVersionUID = -5704455670039814484L;

	@ApiModelProperty(value = "Video id")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
    @ApiModelProperty(value = "Code that identify the video")
    @NotNull(message ="code has to be present")
    @NotEmpty(message ="code can not be empty")
    @Column(name = "code", nullable = false)
    private String code;
      
    @ApiModelProperty(value = "Youtube Url of the video")
    @NotNull(message ="urlYouTube has to be present")
    @NotEmpty(message ="urlYouTube can not be empty")
    @Column(name = "urlYouTube", nullable = false)
    private String urlYouTube;
    
    @ApiModelProperty(value = "Description of the video")
    @Column(name = "description")
    private String description;
    
    @ApiModelProperty(value = "Date time the video was created")
    @CreationTimestamp
	@Column(name = "data_time", nullable = false)
    @NotNull(message ="DateTime has to be present")
	private LocalDateTime dateTime;
    
}