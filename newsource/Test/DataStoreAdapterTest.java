package edu.luc.clearing;

import org.junit.Test;
import org.mockito.Mockito;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;

public class DataStoreAdapterTest {

@Test(expected=NullPointerException.class)
public void canSaveAmounts() {
DatastoreService googleStore = Mockito.mock(DatastoreService.class);
DataStoreAdapter store = new DataStoreAdapter(googleStore);
store.saveRow("Amount", "one");
Mockito.verify(googleStore).put((Entity) Mockito.anyObject());
}
}


