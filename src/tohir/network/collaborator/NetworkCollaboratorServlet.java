package tohir.network.collaborator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NetworkCollaboratorServlet
 */
@WebServlet("/NetworkCollaboratorServlet")
public class NetworkCollaboratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NetworkCollaboratorServlet() {
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
		String network_creator = request.getParameter("network_creator");
		String network_name = request.getParameter("network_name");
		String collaborator = request.getParameter("collaborator");
		
		System.out.println("NetworkCollaboratorServlet (network_creator, network_name, collaborator): " + network_creator + ", " + network_name + ", " + collaborator);
		
		NetworkCollaboratorService service = new NetworkCollaboratorService();
		boolean trans = service.addCollaborator(network_creator, network_name, collaborator);
		if (trans) {
			response.sendRedirect("home.jsp");
		}
		else {
			System.out.println("NetworkCollaboratorServlet: Could not add collaborator");
		}
	}

}
