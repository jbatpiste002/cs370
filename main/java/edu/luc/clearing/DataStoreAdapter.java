package edu.luc.clearing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

public class DataStoreAdapter {
	boolean isInTesting;

	public DataStoreAdapter() {
		isInTesting = false;
	}

	public void enableTestMode() {
		isInTesting = true;
	}

	public boolean isInTestMode() {
		return isInTesting;
	}

	public void writeRequestLog(Date receivedDate, String request, String response) {
		if (isInTesting) {
			System.out.println(receivedDate + request + response);
			return;
		}

		Key parentLogKey = KeyFactory.createKey("Log", "RequestLog");

		Date replyDate = new Date();

		Entity log = new Entity("Log", parentLogKey);
		log.setProperty("ReceivedDate", receivedDate);
		log.setProperty("ReplyDate", replyDate);
		log.setProperty("Request", new Text(request));
		log.setProperty("Reply", new Text(response));

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.put(log);
	}
	List<Map<String, Object>>  runQuery(String Checks){
		List<Map<String, Object>> runQuery = new ArrayList<Map<String, Object>>();
		
		return runQuery;
	}
	
}