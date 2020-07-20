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
import user.dao.CRUDDao2;
import user.dao.InitializeDao;
import user.dao.UserDao;
import user.domain.User;
import user.service.E1UserService;
import user.service.E2UserService;
import user.service.UserException;
import user.service.UserService;

/**
 * Servlet implementation class UserServlet
 */

public class CRUDServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDServlet2() {
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
		CRUDDao2 newObj = new CRUDDao2();
		if ("Create".equals(request.getParameter("Create")))
			newObj.create();
		else if ("Read".equals(request.getParameter("Read"))) 
		{
		}
		else if ("Update".equals(request.getParameter("Update")))
		{
			newObj.update(request.getParameter("fips"), request.getParameter("countynum"), request.getParameter("state"), 
					      request.getParameter("county"), request.getParameter("pop"));
		}
		else if ("Delete".contentEquals(request.getParameter("Delete")))
			newObj.delete(request.getParameter("fips"), request.getParameter("countynum"));
		else
			System.out.println("Failed!");
		
		E2UserService E2userservice = new E2UserService();
		try {
			request.setAttribute("E2List", E2userservice.findall());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			List<Object> li = E2userservice.findall();
			for(int i = 0; i < li.size();i++){
				System.out.println(li.get(i).toString());
			}
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Queryresult/E2list.jsp").forward(request, response);
	}

}
