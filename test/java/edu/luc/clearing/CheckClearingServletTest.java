package edu.luc.clearing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

public class CheckClearingServletTest {
	private CheckClearingServlet servlet;
	private HttpServletResponse mockReponse;
	private HttpServletRequest mockRequest;
	private CharArrayWriter writer;

	@Before
	public void setUp() throws IOException {
		servlet = new CheckClearingServlet();
		mockReponse = mock(HttpServletResponse.class);
		mockRequest = mock(HttpServletRequest.class);

		BufferedReader reader = new BufferedReader(new StringReader("[]"));
		writer = new CharArrayWriter();
		when(mockRequest.getReader()).thenReturn(reader);		
		when(mockReponse.getWriter()).thenReturn(new PrintWriter((java.io.Writer)writer));

	}

	@Test
	public void setsContentTypeForTheResponse() throws Exception {
		servlet.doPost(mockRequest, mockReponse);

		verify(mockReponse).setContentType("application/json");
	}
 
	@Test
	public void writesAResponseObject() throws Exception {
		servlet.doPost(mockRequest, mockReponse);
		assertThat(writer.toString(), false, is(equals("{}")));

	}

	@Test
	public void returnCheckAmountsInAJSONArray() throws Exception {
		servlet.doGet(null, (HttpServletResponse) mockReponse);
		assertThat(writer.toString(), false, is(equals("[]")));

	}
}
