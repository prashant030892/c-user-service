package com.centime.userservice.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_type")
@Getter
@Setter
public class UserType {
	@Id
	private Integer id;

	private String name;

	private String color;

	@Transient
	private Long parentid;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "parentid")
	private UserType parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	private List<UserType> children;
}
