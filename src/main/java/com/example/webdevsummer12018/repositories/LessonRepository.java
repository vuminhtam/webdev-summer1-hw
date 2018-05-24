package com.example.webdevsummer12018.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevsummer12018.models.Lesson;

public interface LessonRepository
extends CrudRepository<Lesson, Integer>{

//	@Query("SELECT l.id as id, l.title as title, l.module_id as module_id \n" + 
//			"FROM lesson l, course c, module m \n" + 
//			"WHERE c.id = :cid AND m.id = :mid AND l.module_id = :mid")
	@Procedure
	Iterable<Lesson> findLessonsByModuleviaCourse(  
	      @Param("cid") int cid, @Param("mid") int mid); 
}