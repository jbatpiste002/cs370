package edu.luc.clearing;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

	@SuppressWarnings("serial")
	public class CheckClearingServlet extends HttpServlet {
		private RequestReader requestReader;
		private CheckHistory checkHistory;
		
		public CheckClearingServlet() {
			requestReader = new RequestReader();
			checkHistory = new CheckHistory(new DataStoreAdapter());
		}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    resp.setContentType("application/json");
	    resp.getWriter().print((requestReader).Respond(req.getReader()));
	
		}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().print(checkHistory.getAmounts());
	}
	
	
	@SuppressWarnings("serial")
	public class CheckClearingServlet extends HttpServlet {
	private static final Map<String, Integer> AMOUNTS = new HashMap<String, Integer>();
	
	public CheckClearingServlet() {
	AMOUNTS.put("zero", 000);
	AMOUNTS.put("one", 100);
	AMOUNTS.put("two", 200);
	AMOUNTS.put("three", 300);
	AMOUNTS.put("four", 400);
	AMOUNTS.put("five", 500);
	AMOUNTS.put("six", 600);
	AMOUNTS.put("seven", 700);
	AMOUNTS.put("eight", 800);
	AMOUNTS.put("nine", 900);
	}
	
	    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	        resp.setContentType("application/json");
	        resp.getWriter().print(response(req.getReader()));
	    }
	    
	    public String response(Reader requestData){
	     Gson gson = new Gson();
	     Map<String, Integer> map = new HashMap<String, Integer>();
	     List<String> checks = gson.fromJson(requestData, requestType());
	     for(String amount : checks){
	     Integer intValue;
	    
	     try{
	     intValue = Integer.valueOf(amount);
	     }catch(NumberFormatException e){
	     intValue=null;
	     }
	    
	     if (intValue == null){
	     map.put(amount, parseStringAmount(amount));
	     }else{
	     map.put(amount, dollarsToCents(new Double(intValue)));
	     }
	     }
	     return gson.toJson(map);
	    }
	    
	    private Integer dollarsToCents(Double amount){
	     return new Integer((int)(amount*100));
	    }
	    
	    private Type requestType(){
	     return new TypeToken<List<String>>(){}.getType(); }
	
	Integer parseStringAmount(String amount) {
	return AMOUNTS.get(amount.toLowerCase());
	}
	}
	}

