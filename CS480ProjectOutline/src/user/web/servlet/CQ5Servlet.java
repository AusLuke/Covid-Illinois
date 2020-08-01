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

import user.dao.CQ1Dao;
import user.dao.CQ2Dao;
import user.dao.CQ5Dao;
import user.dao.InitializeDao;
import user.dao.SQ1Dao;
import user.dao.UserDao;
import user.domain.User;
import user.service.CQ1UserService;
import user.service.CQ2UserService;
import user.service.CQ5UserService;
import user.service.E3UserService;
import user.service.SQ1UserService;
import user.service.UserException;
import user.service.UserService;

/**
 * Servlet implementation class UserServlet
 */

public class CQ5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CQ5Servlet() {
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
		CQ5Dao newObj = new CQ5Dao();
		
		CQ5UserService CQ5userservice = new CQ5UserService();
		try {
			request.setAttribute("CQ5List", CQ5userservice.findall());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			List<Object> li = CQ5userservice.findall();
			for(int i = 0; i < li.size();i++){
				System.out.println(li.get(i).toString());
			}
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Queryresult/CQ5list.jsp").forward(request, response);
		
		
	}

}
