package com.example.webdevsummer12018.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer12018.models.Module;

public interface ModuleRepository
  extends CrudRepository<Module, Integer>{
	
}

