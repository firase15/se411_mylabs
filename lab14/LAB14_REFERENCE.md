# LAB14 Reference

This reference document preserves the exact current source content from the project and is intended to guide future work in this repository.

## 1) `src/main/java/edu/psu/se411/lab14/model/Course.java`

```java
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
```

## 2) `src/main/java/edu/psu/se411/lab14/repository/CourseRepository.java`

```java
package edu.psu.se411.lab14.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.psu.se411.lab14.model.Course;


@Repository 
public interface CourseRepository extends CrudRepository<Course, Long>,
PagingAndSortingRepository<Course, Long> {

}
```

## 3) `src/main/java/edu/psu/se411/lab14/service/CourseService.java`

```java
package edu.psu.se411.lab14.service;

import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import edu.psu.se411.lab14.exception.ObjectNotFoundException;
import edu.psu.se411.lab14.model.Course;
import edu.psu.se411.lab14.repository.CourseRepository;


@Service
public class CourseService {

	private CourseRepository courseRepository;

	
	public CourseService(CourseRepository courseRepository) {
		
		this.courseRepository = courseRepository;
	}
	


public Iterable<Course> getAllCourses() {
	return courseRepository.findAll();
}



public Optional<Course> getCourseById(Long id) {
	return courseRepository.findById(id);
}

public Course createCourse(Course course) {
	
	return courseRepository.save(course);
}



public void updateCourse(Long id, Course course) {
    Course existingCourse = courseRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Course Not Found"));

    
 Course updatedCourse = new Course(id,course.name(),course.description(), course.code(), course.credits());
   
    
    courseRepository.save(updatedCourse);
}


public void deleteCourse(Long id) {
	
	  Course existingCourse = courseRepository.findById(id)
      .orElseThrow(() -> new ObjectNotFoundException("Course Not Found"));
	
	  courseRepository.deleteById(id);
	
}


}
```

## 4) `src/main/java/edu/psu/se411/lab14/controller/CourseController.java`

```java

package edu.psu.se411.lab14.controller;

import java.net.URI;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import edu.psu.se411.lab14.exception.ObjectNotFoundException;
import edu.psu.se411.lab14.model.Course;
import edu.psu.se411.lab14.service.CourseService;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> op = courseService.getCourseById(id);

        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    ResponseEntity<Void> createCourse(
            @Valid @RequestBody Course course,
            UriComponentsBuilder ucb) {

        if (course.id() != null) {
            return ResponseEntity.badRequest().build();
        } else {
            Course createdCourse = courseService.createCourse(course);

            URI uri = ucb.path("/api/v1/courses/{id}")
                    .buildAndExpand(createdCourse.id()).toUri();

            return ResponseEntity.created(uri).build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateCourse(@PathVariable Long id,@RequestBody Course course) {

        if (course.id() != null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                courseService.updateCourse(id, course);
                return ResponseEntity.noContent().build();
            } catch (ObjectNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }
    }
    
    
    @DeleteMapping("/{id}")
    
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
    	
    	try {
    		courseService.deleteCourse(id);
    	    return	ResponseEntity.noContent().build();
    	}catch(ObjectNotFoundException e ) {
    		return ResponseEntity.notFound().build(); 
    	}
    	
    }
}
```

## 5) `src/main/java/edu/psu/se411/lab14/exception/ObjectNotFoundException.java`

```java
package edu.psu.se411.lab14.exception;

public class ObjectNotFoundException extends RuntimeException {
	public ObjectNotFoundException(String message) {
		super(message);
	}

}
```

## 6) `pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>4.1.2</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>edu.psu.se411</groupId>
	<artifactId>lab14</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name/>
	<description/>
	<url/>	
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>21</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jdbc</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

## 7) `src/main/resources/schema.sql`

```sql
CREATE TABLE course(

id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, 

code varchar(100) NOT NULL, 

name varchar(100) NOT NULL, 


description varchar(100), 

credits int


); 
```

## 8) `src/main/resources/data.sql`

```sql
INSERT INTO course(id,code,name,description,credits)
VALUES(default, 'SE201',
'Introduction to Software Engineering',
'Fundamentals',
3);

INSERT INTO course(id,code,name,description,credits)
VALUES(default, 'CS101',
'INTRODUCTION TO PROGRAMMING',
'FUNDAMENTALS',
4);


```

## 9) `src/main/resources/application.properties`

```ini
spring.application.name=lab14
server.port = 3000
```

## 10) Notes for future project work

Use the source files above as the authoritative implementation examples for this project. When creating or updating Java files in this repository, preserve the current package structure, annotation style, record-based model patterns, and CRUD controller/service flow shown here.

Follow the same conventions in future work:
- `controller` handles HTTP concerns and status codes
- `service` handles business logic and not-found checks
- `repository` stays as a Spring Data interface
- `model` uses immutable records with Bean Validation annotations
- `@Valid` is used on request bodies where validation should be triggered
- response status behavior should remain consistent with the existing API