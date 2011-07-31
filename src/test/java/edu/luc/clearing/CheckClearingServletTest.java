	package edu.luc.clearing;
	
	import java.io.CharArrayWriter;
	import java.io.IOException;
	import java.io.StringReader;
	
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
import org.hamcrest.Matcher;
	import org.junit.Before;
	import org.junit.Test;
import static org.junit.Assert.*;
	
	public class CheckClearingServletTest {
		private CheckClearingServlet servlet;
		private HttpservletResponse mockReponse;
		private HttpServletRequest mockRequest;
		private CharArrayWriter Writer;
	
	
	@Before
	public void setUp(Object writer)  throws IOException {
		servlet = new CheckClearingServlet();
		mockReponse = mock(HttpServletRequest.class);
		mockRequest = mock(HttpServletRequest.class);
		
		BufferedReader reader = new BufferedReader(new StringReader("[]"));
		Writer = new ChartArrayWriter();
		when(mockRequest.getReader()).thenReturn(reader);
		when(mockreponse.getWriter()).thenReturn(new PrintWriter(writer));
		
	}
	@Test
	public void setsContentTypeForTheResponse() throws Exception {
		servlet.doPost(mockRequest, mockReponse);
		
		verify(mockReponse).setContentType("application/json");
	}
	    
	@Test
	public void writesAResponseObject() throws Exception {
		servlet.doPost(mockRequest, mockReponse);
		assertThat(Writer.toString().is(equals("{}")));
		
	}
	
	@Test
	public void returnCheckAmountsInAJSONArray() throws exception {
		servlet.doGet(null, (HttpServletResponse) mockReponse);
		assertThat(writer.toString(), is(equals("[]")));
		

	}
	}
	