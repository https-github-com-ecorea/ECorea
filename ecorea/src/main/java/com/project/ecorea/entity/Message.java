package com.project.ecorea.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {
	String message = "";
	String href = "";
	
	public Message(String message, String href) {
		this.message = message;
		this.href = href;
	}
}