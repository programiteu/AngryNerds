package eu.programit.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import eu.programit.domain.Category;

@Component
public interface CategoryRepository extends CrudRepository<Category,Integer> {
		
		//Do we need line 14?
	    //Collection<Category> findAllByCategory(Category id);

}