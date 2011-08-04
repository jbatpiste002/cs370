package edu.luc.clearing;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CheckHistoryTest {

		private CheckHistory history;
		private DataStoreAdapter mockDataStore;
		private Map<String, Object> check;
		private ArrayList<Map<String, Object>> checks;

		@Before
		public void setUp() {
				mockDataStore = Mockito.mock(DataStoreAdapter.class);
				history = new CheckHistory(mockDataStore);
				check = new HashMap<String, Object>();
				checks = new ArrayList<Map<String, Object>>();
				Mockito.when(mockDataStore.runQuery("Checks")).thenReturn(checks);
		}
		
		@Test
		public void getRequestReturnsAllThePreviouslyEncounteredCheckAmounts() throws Exception {
				check.put("amount", "one");
				checks.add(check);
				Mockito.when(mockDataStore.runQuery("Checks")).thenReturn(checks);
				assertEquals("[\"one\"]", history.getAmounts(null));
		}
		
		@Test
		public void doesNotLimitQueryIfNullIsPassedIn() throws Exception {
				check.put("amount", "one");
				checks.add(check);
				assertEquals("[\"one\"]", history.getAmounts(null));
		}
		@Test
		public void canLimitNumberOfChecksReturned() throws Exception {
				checks.add(createCheck("amount", "one"));
				checks.add(createCheck("amount", "two"));
				checks.add(createCheck("amount", "three"));
				assertEquals("[\"two\",\"one\"]", history.getAmounts("2"));
		}
		
		public Map<String, Object> createCheck(String amount, Object number) {
				Map<String, Object> check = new HashMap<String, Object>();
				check.put(amount, number);
				return check;
		}
		
		
}
