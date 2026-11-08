package edu.psu.se411.lab14.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public record Course( 
	@Id Long id, 
	@NotBlank(message = "Course code cannot be empty") String code, 
	@NotBlank(message = "Course name cannot be empty")
	@Size(min = 2, message = "Course name must be at least 2 characters") String name, 
	String description, 
	@NotNull(message = "Course credits are required")
	@Min(value = 1, message = "Course credits must be between 1 and 10")
	@Max(value = 10, message = "Course credits must be between 1 and 10") Integer credits 
)
{}
