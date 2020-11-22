package com.example.didactic.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
	@JsonIgnore
	private UUID id;
	private String name;
	private String email;

}
