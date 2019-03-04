package bookshop.Servlet;


import bookshop.Entity.UsersEntity;
import bookshop.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;


/**
 * Servlet implementation class UserManagerServlet
 */
@WebServlet("/UserLog")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    //System.out.println("here!\n");
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            
            System.out.println("logServlet invoke!");

            String un = (String) request.getParameter("un");
            String password = (String) request.getParameter("pwd");
            System.out.println("name；"+un+" pwd: "+password);
            //UserDao dao = new UserDao();
            UsersEntity user =(UsersEntity)session.createQuery("from UsersEntity where username = ? ")
                                .setParameter(0,un).uniqueResult();
            System.out.println(user);
            String resp;
            if (user != null){
                if(user.getPwd().equals(password)){
                    System.out.println("correct pwd!");
                    if(user.getRole()==0){ resp = "USER"; }
                    else{ resp = "ADMIN"; }
                }
                else{resp = "WRONGPWD";}
            }else{                  /*user不存在*/
                resp = "NULL";
            }

            out.print(resp);

            out.flush();
            out.close();
            //HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
            tx.commit();
            session.close();
        }
        catch (Exception ex) {
            //HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            if ( ServletException.class.isInstance( ex ) ) {
                throw ( ServletException ) ex;
            }
            else {
                throw new ServletException( ex );
            }
        }
	}

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");

            System.out.println("addServlet invoke!");

            String username = (String) request.getParameter("user");
            String pwd = (String) request.getParameter("pwd");
            String email = (String) request.getParameter("email");
            String phone = (String) request.getParameter("phone");

            UsersEntity user = (UsersEntity) session.createQuery("from UsersEntity where username = ? ")
                    .setParameter(0,username).uniqueResult();
            //UserDao dao = new UserDao();
            //UsersEntity user = dao.getByName(username);
            System.out.println(user);

            if (user != null){  /*user存在*/
                System.out.println("exist!\n");
                out.print("USERERROR");

            }else {
                System.out.println("log1");
                UsersEntity newuser = new UsersEntity();
                System.out.println("log2");
                newuser.setUsername(username);
                System.out.println("log3");
                newuser.setPwd(pwd);
                newuser.setRole(0);
                newuser.setEmail(email);
                newuser.setPhone(phone);
                newuser.setState(0);
                System.out.println(newuser);
                //dao.save(newuser);
                Serializable s =  session.save(newuser);
                System.out.println(s);
                out.print("ADDUSER");
            }
            tx.commit();
            session.close();
            //HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
            out.flush();
            out.close();

        }
        catch (Exception ex) {
            //HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            if ( ServletException.class.isInstance( ex ) ) {
                throw ( ServletException ) ex;
            }
            else {
                throw new ServletException( ex );
            }
        }
    }
}
