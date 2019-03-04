package bookshop.Dao;

import bookshop.Entity.BooksEntity;
import bookshop.Util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookDao {

    public BookDao(){}
    /*
     * 保存
     */
    public void save(BooksEntity book) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction(); // 开启事务
            session.save(book);
            tx.commit(); // 提交事务
        } catch (RuntimeException e) {
            session.getTransaction().rollback(); // 回滚事务
            throw e;
        } finally {
            session.close(); // 关闭session
        }
    }

    /*
     * 更新
     */
    public void update(BooksEntity book) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(book);// 操作
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /*
     * 删除
     */
    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            BooksEntity book = (BooksEntity) session.get(BooksEntity.class, id); // 要先获取到这个对象
            session.delete(book); // 删除的是实体对象

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /*
     * 根据id查询一个Book数据
     */
    public BooksEntity getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            BooksEntity book = (BooksEntity) session.createQuery("from BooksEntity where id = ? ")
                    .setParameter(0,id).uniqueResult();// 操作
            tx.commit();
            return book;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /*
     * 查询所有
     */
    public List<BooksEntity> findAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.println("enter book getmanager findall");
            // 使用SQL语句

            List<BooksEntity> list1 = (List<BooksEntity>)session.createQuery(" FROM BooksEntity ").list(); // 使用SQL查询
            System.out.println("list of book getmanager findall born");
            tx.commit();

            boolean x=list1.isEmpty();
            System.out.println(x);
            return list1;
        } catch (RuntimeException e) {
            System.out.println("error!");
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }


}
