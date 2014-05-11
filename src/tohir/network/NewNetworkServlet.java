package tohir.network;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class NewNetworkServlet
 */
@MultipartConfig
@WebServlet("/NewNetworkServlet")
public class NewNetworkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewNetworkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Part networkFile = request.getPart("networkFile");		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String networkName = request.getParameter("networkName");
		System.out.println(networkName);
		InputStream is = networkFile.getInputStream();
		String visibility = request.getParameter("visibility");
		System.out.println(visibility);
		NewNetworkService service = new NewNetworkService();
		String INSERT = service.createNewNetwork(networkName, username, networkFile, visibility);
		if (INSERT.equals("OK")) {
			System.out.println("NewNetworkServlet: OK, Network successfully created!");
			response.sendRedirect("newSuccess.jsp");
		}
		else {
			System.out.println("NewNetworkServlet: Network could not be created.");
			response.sendRedirect("newError.jsp");
		}
		
	}

}
