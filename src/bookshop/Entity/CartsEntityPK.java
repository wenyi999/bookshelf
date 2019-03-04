package bookshop.Entity;

import java.io.Serializable;

/**
 * Created by Boyi on 2018/4/30.
 */
public class CartsEntityPK implements Serializable {
    private String username;
    private int bookid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartsEntityPK that = (CartsEntityPK) o;

        if (bookid != that.bookid) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + bookid;
        return result;
    }
}
