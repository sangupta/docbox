package org.myjerry.docbox.service;

import org.myjerry.docbox.model.DocBoxAggregates;

public interface AggregateService {
	
	public DocBoxAggregates getAggregates();
	
	public void updateAggregates(int foldersAdded, int filesAdded, long sizeAdded);

}
