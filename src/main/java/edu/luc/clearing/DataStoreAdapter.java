package edu.luc.clearing;

import java.awt.TextArea;
import java.security.KeyFactory;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.DatatypeFactory;

import com.sun.xml.internal.stream.Entity;


public class DataStoreAdapter<DatastoreService> {
	private DatastoreService datastore;
	
	public DataStoreAdapter(){
		setDatastore(DatatypeFactory.getDataStoreService());
	}
	public List<Map<String, Object>> runQuery(String string){
		return new ArrayList<Map<String, Object>>();
	}
	public DatastoreService getDatastore() {
		return datastore;
	}
	public void setDatastore(DatastoreService datastore) {
		this.datastore = datastore;
	}



public class DataStoreWriter {
	boolean isInTesting;
	
	public DataStoreWriter() {
	isInTesting = false;
	}
	
	public void enableTestMode(){
	isInTesting = true;
	}
	
	public boolean isInTestMode(){
	return isInTesting;
	}
	
	
	public void writeRequestLog(Date receivedDate, String request, String response){
	if(isInTesting){
	System.out.println(receivedDate + request + response);
	return;
	}
	
	Key parentLogKey = KeyFactory.createKey("Log", "RequestLog");
	
	Date replyDate = new Date(0);
	
	Entity log = new Entity("Log", parentLogKey);
	log.setProperty("ReceivedDate", receivedDate);
	log.setProperty("ReplyDate", replyDate);
	log.setProperty("Request", new TextArea(request));
	log.setProperty("Reply", new TextArea(response));
	
	DatastoreService datastore = DatatypeFactory.getDatastoreService();
	         datastore).put(log);
	}
	}
}
