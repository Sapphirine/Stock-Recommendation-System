package stock.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.datamodel.CustomerStock;
import stock.datamodel.RecStock;
import stock.yahoo.YahooFinanceCalculate;
import stock.yahoo.YahooStockInfo;

import com.google.gson.Gson;

/**
 * Servlet implementation class stockInfoServlet
 */
@WebServlet("/searchInfoServlet")
public class searchInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		response.setContentType("application/json"); 
		String info=request.getParameter("recom");//接受请求参数
		info=info.trim();
		RecStock res=YahooStockInfo.getStockInfo(info);
		PrintWriter out=response.getWriter(); //准备输出 
		String ans=gson.toJson(res);
		out.println(ans);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		response.setContentType("application/json"); 
		String info=request.getParameter("recom");//接受请求参数 
		info=info.substring(7);
		//System.out.print(info);
		info=info.trim();
		RecStock res=YahooStockInfo.getStockInfo(info);
		PrintWriter out=response.getWriter(); //准备输出 
		String ans=gson.toJson(res);
		out.println(ans);
		out.flush();
	}

}
