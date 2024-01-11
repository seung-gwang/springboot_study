package com.shinhan.firstzone.repository.composite;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.vo4.MultiKeyA;
import com.shinhan.sbproject.vo4.MultiKeyAUsing;

public interface MultiKeyAUsingRepository 
extends CrudRepository<MultiKeyAUsing, MultiKeyA>{

}
