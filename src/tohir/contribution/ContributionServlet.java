package tohir.contribution;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tohir.dto.Contribution;

import com.google.common.collect.Multimap;

/**
 * Servlet implementation class ContributionServlet
 */
@WebServlet("/ContributionServlet")
public class ContributionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContributionServlet() {
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
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Multimap<String, Contribution> contributionBatch = (Multimap<String, Contribution>) session.getAttribute("contributionBatch");
		String batchKey = request.getParameter("batchKey");
		String[] networkInfo = batchKey.split(",");
		String message = request.getParameter("message");
		
		System.out.println("ContributionServlet: username: " + username + ", batchKey: " + batchKey);
		Collection<Contribution> contributionDML = contributionBatch.get(batchKey);
		
		// PRINT //
		Object[] contributionDMLArray = contributionDML.toArray();
		System.out.println(Arrays.toString(contributionDMLArray));
		// PRINT //
		
		ContributionService service = new ContributionService();
		
		service.processContributions(username, networkInfo[0], networkInfo[1], contributionDML, message);
		
		
	}

}
