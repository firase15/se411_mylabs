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