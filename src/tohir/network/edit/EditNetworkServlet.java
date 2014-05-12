package tohir.network.edit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
	
		if (action.equals("insert")) {
			int newProteinA  = Integer.parseInt(request.getParameter("newProteinA"));
			int newProteinB = Integer.parseInt(request.getParameter("newProteinB"));
			System.out.println("EditNetworkServlet: username, action, network_name, network_creator, newProteinA, newProteinB");
			System.out.println("EditNetworkServlet: " + username + ", " + action + ", " + network_name + ", " +  network_creator + ", " + Integer.toString(newProteinA) + ", " + Integer.toString(newProteinB));
			String trans = service.networkInsert(username, network_creator, network_name, newProteinA, newProteinB); 
			if (trans.equals("OK")) {
				// you have to use requestDispatcher ... bc you need to resend network_name, network_creator
				request.setAttribute("network_name", network_name);
				request.setAttribute("network_creator", network_creator);
				RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
				dispatcher.forward(request, response);
				//response.sendRedirect("edit.jsp"); // should send to a page asking if he's done adding contributions
			}
		}
		if (action.equals("delete")) {
			int proteinA = Integer.parseInt(request.getParameter("proteinA"));
			int proteinB = Integer.parseInt(request.getParameter("proteinB"));
			String trans = service.networkDelete(username, network_creator, network_name, proteinA, proteinB);
			if (trans.equals("OK")) {
				request.setAttribute("network_name", network_name);
				request.setAttribute("network_creator", network_creator);
				RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
				dispatcher.forward(request, response);
			}
			
		}
	}

}
