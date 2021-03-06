package example3.web.servlet;

import example3.dao.DaoFactory;
import example3.domain.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编辑信息的Servlet
 * @noinspection Duplicates
*/
@WebServlet(urlPatterns = {"/example/example3/modify"})
public class ModifyServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		var id = Integer.parseInt(req.getParameter("id").trim());
		var stuId = req.getParameter("stuId").trim();
		var name = req.getParameter("name").trim();
		var score = Integer.parseInt(req.getParameter("score").trim());

		try {
			DaoFactory.getStudentDao().doModify(new Student(id, stuId, name, score));
		}catch (Exception e){
			e.printStackTrace();
		}

		resp.sendRedirect("/example3/list");
	}
}

