package tohir.network.edit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EditNetworkServlet
 */
@WebServlet("/EditNetworkServlet")
public class EditNetworkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditNetworkServlet() {
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
		EditNetworkService service = new EditNetworkService();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String action = request.getParameter("action");
		String network_name = request.getParameter("network_name");
		String network_creator = request.getParameter("network_creator");
		int newProteinA  = Integer.parseInt(request.getParameter("newProteinA"));
		int newProteinB = Integer.parseInt(request.getParameter("newProteinB"));
		
		System.out.println("EditNetworkServlet: username, action, network_name, network_creator, newProteinA, newProteinB");
		System.out.println("EditNetworkServlet: " + username + ", " + action + ", " + network_name + ", " +  network_creator + ", " + Integer.toString(newProteinA) + ", " + Integer.toString(newProteinB));
		if (action.equals("insert")) {
			// check if session username is network_creator (or collaborator) -- check this in the service.
			//service.NetworkInsert(); 
		}
		if (action.equals("delete")) {
			
		}
	}

}
