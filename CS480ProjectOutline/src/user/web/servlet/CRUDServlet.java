package user.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.CRUDDao;
import user.dao.InitializeDao;
import user.dao.UserDao;
import user.domain.User;
import user.service.E1UserService;
import user.service.UserException;
import user.service.UserService;

/**
 * Servlet implementation class UserServlet
 */

public class CRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CRUDDao newObj = new CRUDDao();
		if ("Create".equals(request.getParameter("Create")))
			newObj.create(request.getParameter("date"), request.getParameter("county"), request.getParameter("state"), 
				      request.getParameter("fips"), request.getParameter("CountyNum"), request.getParameter("cases"), request.getParameter("deaths"));

		else if ("Read".equals(request.getParameter("Read")))
		{
			
		}
		else if ("Update".equals(request.getParameter("Update")))
		{
			newObj.update(request.getParameter("date"), request.getParameter("county"), request.getParameter("state"), 
					      request.getParameter("fips"), request.getParameter("CountyNum"), request.getParameter("cases"), request.getParameter("deaths"));
		}
		else if ("Delete".contentEquals(request.getParameter("Delete")))
			newObj.delete(request.getParameter("date"), request.getParameter("fips"), request.getParameter("CountyNum"));
		else
			System.out.println("Failed!");
		
		E1UserService E1userservice = new E1UserService();
		try {
			request.setAttribute("E1List", E1userservice.findall());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			List<Object> li = E1userservice.findall();
			for(int i = 0; i < li.size();i++){
				System.out.println(li.get(i).toString());
			}
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Queryresult/E1list.jsp").forward(request, response);
	}

}
