package tohir.network.edit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tohir.dto.Contribution;

import com.google.common.collect.*;

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
		Multimap <String, Contribution> contributionBatch = (Multimap<String, Contribution>) session.getAttribute("contributionBatch");
		
		String action = request.getParameter("action");
		String network_name = request.getParameter("network_name");
		String network_creator = request.getParameter("network_creator");
	
		if (action.equals("insert")) {
			int newProteinA  = Integer.parseInt(request.getParameter("newProteinA"));
			int newProteinB = Integer.parseInt(request.getParameter("newProteinB"));
			
			System.out.println("EditNetworkServlet: username, action, network_name, network_creator, newProteinA, newProteinB");
			System.out.println("EditNetworkServlet: " + username + ", " + action + ", " + network_name + ", " +  network_creator + ", " + Integer.toString(newProteinA) + ", " + Integer.toString(newProteinB));
			String trans = service.batchInsert(contributionBatch, network_creator, network_name, username, action, newProteinA, newProteinB); 
			
			if (trans.equals("OK")) {
				response.sendRedirect("batch.jsp");
			}
		}
	}
}
