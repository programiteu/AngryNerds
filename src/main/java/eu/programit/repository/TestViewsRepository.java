package eu.programit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import eu.programit.domain.TestViews;

@Component
public interface TestViewsRepository extends CrudRepository<TestViews, Integer> {
	
}