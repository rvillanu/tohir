package tohir.login;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import com.google.common.collect.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tohir.dto.Contribution;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check login credentials
		String username = request.getParameter("username");
		System.out.println("LoginServlet: User typed user_name: " + username);
		String password = request.getParameter("password");
		System.out.println("LoginServlet: User typed password: " + password);
		
		LoginService loginService = new LoginService();
		boolean result = loginService.authenticate(username, password);
		
		// if login is successful, redirect to success.jsp
		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			Multimap<String, Contribution> contributionBatch = HashMultimap.create();
			session.setAttribute("contributionBatch", contributionBatch);
			response.sendRedirect("home.jsp");
			return;
		}
		else {
			response.sendRedirect("login.jsp");
			return;
		}
		
		
	}
	
	public static void main(String[] args) {
		Multimap<String, String> hmm = HashMultimap.create();
		hmm.put("A", "insert,1,2");
		hmm.put("A", "insert,3,4");
		Map<String, Collection<String>> m = hmm.asMap();
		System.out.println(hmm.asMap());
		for (String key : m.keySet()) {
			for (String value : m.get(key)) {
				System.out.println(value);
			}
		}
		hmm.remove("A", "insert,1,2");
		System.out.println(hmm.asMap());
	}

}
