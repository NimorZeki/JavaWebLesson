package example2;

import example2.DAO.Pagination;
import example2.DAO.StudentDAO;
import example2.POJO.Student;
import example2.Utils.DBUtils;

import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

public class TestPage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		StudentDAO stuDAO = new StudentDAO();
		int count = stuDAO.count();
		out.println("记录数：" + count);

		var con = DBUtils.getConnection();
		Pagination pagination = new Pagination();
		ArrayList<Student> studentList = new ArrayList<>();
		studentList = pagination.falsePage(pageIndex, pageSize);
		out.println("id&emsp;stuId&emsp;name&emsp;score");
		for(Student student : studentList) {
			out.println(student.getStuId() + "&emsp;" + student.getName() + "&emsp;" + student.getScore() + "<br>");
		}

		int pages;
		if(count % pageSize == 0) {
			pages = count / pageSize;
		} else {
			pages = count / pageSize + 1;
		}
		for(int i = 1; i < pages; i++) {
			out.println("<a href='page?pageIndex=" + i + "&pageSize=" + pageSize + "'>" + i + "</a>&nbsp;");
		}

		DBUtils.closeConnection(con);

	}
}
