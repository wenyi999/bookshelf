package bookshop.Entity;

/**
 * Created by Boyi on 2018/4/30.
 */
public class CartsEntity {
    private String username;
    private int bookid;
    private Integer amount;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartsEntity that = (CartsEntity) o;

        if (bookid != that.bookid) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + bookid;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
