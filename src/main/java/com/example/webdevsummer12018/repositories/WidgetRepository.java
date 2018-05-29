package com.example.webdevsummer12018.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer12018.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {

}