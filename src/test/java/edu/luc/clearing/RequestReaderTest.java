package edu.luc.clearing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class RequestReaderTest {

private RequestReader reader;
private DataStoreAdapter dataStore;
private DataStoreAdapter clock;

	@Before
	public void setUp() {
			dataStore = Mockito.mock(DataStoreAdapter.class);
			clock = Mockito.mock(Clock.class);
			reader = new RequestReader(dataStore, clock);
	}
	@Test
	public void shouldReturnAnEmptyObjectForAnEmptyRequest() throws Exception {
	        assertEquals("{}", reader.respond(new StringReader("[]")));
	    }
	    
	@Test
	public void shouldReturnCentsForCheckValues() throws Exception {
	        assertEquals("{\"one dollar\":100}", reader.respond(new StringReader("[\"one dollar\"]")));
	        assertEquals("{\"seven dollars 10 cents\":710}", reader.respond(new StringReader("[\"seven dollars 10 cents\"]")));
	        assertEquals("{\"seventy seven dollars thirty cents\":7730}", reader.respond(new StringReader("[\"seventy seven dollars thirty cents\"]")));
	        assertEquals("{\"94 dollars six cents\":9406}", reader.respond(new StringReader("[\"94 dollars six cents\"]")));
	        assertEquals("{\"forty 4 dollars six cents\":4406}", reader.respond(new StringReader("[\"forty 4 dollars six cents\"]")));
	        assertEquals("{\"twenty-two dollars\":2200}", reader.respond(new StringReader("[\"twenty-two dollars\"]")));
	        assertEquals("{\"thirty-seven and 27/100\":3727}", reader.respond(new StringReader("[\"thirty-seven and 27/100\"]")));
	        assertEquals("{\"thirty-seven dollars and 27/100\":3727}", reader.respond(new StringReader("[\"thirty-seven dollars and 27/100\"]")));
	        assertEquals("{\"one dollar and 100/100\":200}", reader.respond(new StringReader("[\"one dollar and 100/100\"]")));
	}
	
	
	@Test
	public void shouldIgnoreMalformedAmounts() throws Exception {
			assertEquals("{}", reader.respond(new StringReader("[\"purple\"]")));
			assertEquals("{}", reader.respond(new StringReader("[\"purple-two\"]")));
			assertEquals("{}", reader.respond(new StringReader("[\"purple-twenty-two\"]")));
			assertEquals("{}", reader.respond(new StringReader("[\"7/10\"]")));
			assertEquals("{}", reader.respond(new StringReader("[\"a/100\"]")));
			assertEquals("{}", reader.respond(new StringReader("[\"Eighty and 9a/10\"]")));
			assertEquals("{}", reader.respond(new StringReader("[\"Eighty and 9/10\"]")));
	}
	
	@Test
	public void shouldSaveAmountsInDataStore() throws Exception {
			reader.respond(new StringReader("[\"one\"]"));
			verify(dataStore).saveRow("amount", "one");
	}
	
	@Test
	public void shouldShortCircuitTheResponseIfItTakeslongerThan25Seconds() throws Exception {
	long now = System.currentTimeMillis();
	When(clock.currentTime()).thenReturn(now, now + 23 * 1000, now + 26 * 1000);
	        String response = reader.respond(new StringReader("[\"one\", \"two\", \"three\"]"));
	        assertEquals("{\"two\":200,\"one\":100}", response);
	}
	
}


