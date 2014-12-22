package stock.servlet;
import stock.yahoo.*;
import stock.datamodel.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

import org.apache.commons.httpclient.HttpMethod;

import com.google.gson.Gson;

/**
 * Servlet implementation class InputServlet
 */
@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		response.setContentType("application/json"); 
		String info=request.getParameter("recom");//接受请求参数 
		info=info.trim();
		List<CustomerStock> res=YahooWebServiceGet.yahooGet(info);
		PrintWriter out=response.getWriter(); //准备输出 
		String ans=gson.toJson(res);
		out.println(ans);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		response.setContentType("application/json"); 
		String info=request.getParameter("recom");//接受请求参数 
		info=info.substring(7);
		System.out.println(info);
		info=info.trim();
		//List<CustomerStock> res=YahooWebServiceGet.yahooGet(info);
		List<CustomerStock> res=yahooFinanceSimilarity.findDistance(info);
		PrintWriter out=response.getWriter(); //准备输出 
		String ans=gson.toJson(res);
		out.println(ans);
		out.flush();
	}

}
