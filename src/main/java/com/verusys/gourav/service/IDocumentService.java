package com.verusys.gourav.service;

import java.util.List;

import com.verusys.gourav.entity.Document;

public interface IDocumentService {

	void saveDocument(Document doc);
	List<Object[]> getDocumentIdAndName();
	void deleteDocumentById(Long id);
	Document getDocumentById(Long id);
}
