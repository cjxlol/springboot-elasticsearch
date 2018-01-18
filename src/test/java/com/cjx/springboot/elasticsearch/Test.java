package com.cjx.springboot.elasticsearch;

import org.springframework.web.client.RestTemplate;

/**
 * @author chenjunxi
 *
 */
public class Test {
	public static void main(String[] args) {
//		add();
		query();
	}

	/**
	 * 调用添加数据接口
	 */
	public static void add() {
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("http://localhost:8080/add", String.class);
		System.out.println(result);
	}

	/**
	 * 调用查询接口
	 */
	public static void query() {
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("http://localhost:8080/query", String.class);
		System.out.println(result);
	}
}
