package com.coda.application.Lex.design;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LexRepo extends JpaRepository<LexStreamingData, Integer> {
	
}
