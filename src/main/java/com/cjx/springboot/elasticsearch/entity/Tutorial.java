package com.cjx.springboot.elasticsearch.entity;

/**
 * 教程
 *
 * @author chenjunxi
 */
public class Tutorial {

	private Long id;

	private String name;// 教程名称

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
