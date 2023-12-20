package exam;

public class BookExam {
    //프로그래머가 직접 인스턴스를 생성
    public static void main(String[] args) {
        Book book = new Book("java",10000);
        System.out.println(book.getTitle());
        System.out.println(book.getPrice());

    }
}
