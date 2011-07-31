package edu.luc.clearing;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;


public class CheckClearingServletTest2 {
	private CheckClearingServlet servlet;
	
	@Before
	public void setUp() {
		servlet = new CheckClearingServlet();
		
	}
    @Test
    public void shouldReturnAnEmptyObjectForAnEmptyRequest() throws Exception {
        assertEquals("{}", servlet.response(new StringReader("[]")));
    }
    
    @Test
    public void shouldReturnCentsForCheckValues() throws Exception {
    	assertEquals("{\"one\":100}", servlet.response(new StringReader("[\"one\"]")));
    	assertEquals("{\"seven\":700}", servlet.response(new StringReader("[\"seven\"]")));
    }
    
    @Test 
    public void shouldIgnoreMalformedAmounts() throws Exception {
    	assertEquals("{}", servlet.response(new StringReader("[\"purple\"]")));
    }
    
    @Test
    public void shouldIgnoreCase() throws Exception {
    	assertEquals(300, servlet.parseStringAmount("Three").intValue());
    }
    
    @Test
    public void shouldHandleZeroAmounts() throws Exception {
    	assertEquals(0, servlet.parseStringAmount("zero").intValue());
    }
    
    
}