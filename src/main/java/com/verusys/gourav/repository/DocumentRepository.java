package com.verusys.gourav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.verusys.gourav.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	@Query("SELECT docId,docName FROM Document")
	List<Object[]> getDocumentIdAndName();

}
