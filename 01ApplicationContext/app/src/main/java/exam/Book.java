package exam;

public class Book {
    private String title;
    private int price;

    //! Bean 생성에는 기본생성자 필수!
    public Book() {

    }

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + ", price=" + price + "]";
    }

}
