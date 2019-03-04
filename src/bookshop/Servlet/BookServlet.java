package bookshop.Servlet;

import bookshop.Entity.BooksEntity;
import bookshop.Util.HibernateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * Servlet implementation class UserManagerServlet
 */
@WebServlet("/getBook")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Transaction tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");

            System.out.println("This is a book getmanager");



            //BookDao dao = new BookDao();
            //List<BooksEntity> result;
            System.out.println("enter findallget\n");
            //result = dao.findAll();


            List<BooksEntity> result = HibernateUtil.getSessionFactory()
                    .getCurrentSession().createQuery("from BooksEntity").list();

            /*Iterator<BooksEntity> it = result.iterator();
            System.out.println("normal bookservlet!\n");
            //Iterator<BooksEntity> it = result.iterator();
            ArrayList<JSONObject> booksJson = new ArrayList<JSONObject>();
            while (it.hasNext()) {

                BooksEntity book = (BooksEntity) it.next();

                ArrayList<String> obj = new ArrayList<String>();
                obj.add("id:" + book.getId() + "");

                obj.add("category:" + book.getCategory());
                obj.add("title:" + book.getTitle());
                obj.add("author:" + book.getAuthor());
                obj.add("price:" + book.getPrice() + "");
                obj.add("publish:" + book.getPublish() + "");
                obj.add("stock:" + book.getStock() + "");
                obj.add("img:" + book.getImg());

                System.out.println(obj.toString());
                booksJson.add(JSONObject.fromString(obj.toString()));*/


                //book = (BooksEntity) it.next();
                //BookDao dao = new BookDao();
                //List<BooksEntity> result = dao.findAll();
                System.out.println("normal here!\n");
                Iterator<BooksEntity> it = result.iterator();

                ArrayList<JSONObject> booksJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    BooksEntity book = (BooksEntity) it.next();
                    System.out.println(book);
                    JSONObject obj = new JSONObject();
                    obj.put("id",book.getId());
                    System.out.println("normal here2!\n");
                    obj.put("category",book.getCategory());
                    obj.put("title",book.getTitle());
                    obj.put("author",book.getAuthor());
                    obj.put("price",book.getPrice());
                    obj.put("publish",book.getPublish());
                    obj.put("stock",book.getStock());
                    obj.put("img",book.getImg());

                    System.out.println(obj.toString());
                    booksJson.add(obj);
                }
                JSONArray books = JSONArray.fromArray(booksJson.toArray());

            //}
            //JSONArray books = JSONArray.fromArray(booksJson.toArray());


            System.out.println(books);

            out.println(books);
            out.flush();
            out.close();
            //tx.commit();

            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
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

