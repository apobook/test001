package com.thh.demo2;

import com.thh.demo1.Person;
import com.thh.demo1.Person002;

public class Person01 implements Person, Person002 {

	private Integer test;

	public Integer getAge() {
		return test;
	}

	public void setAge(Integer age) {
		this.test = age;
	}

	public Person01() {
		super();
	}

	public Person01(Integer age) {
		super();
		this.test = age;
	}

	@Override
	public String toString() {
		
		return "Person01 [age=" + test + "]";
	}
	
}
