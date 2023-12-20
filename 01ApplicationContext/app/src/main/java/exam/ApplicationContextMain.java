package exam;

public class ApplicationContextMain {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = ApplicationContext.getInstance();
        ApplicationContext context2 = ApplicationContext.getInstance();

        if(context == context2){
            System.out.println("2개의 context는 동일");
        }
        Book book = (Book) context.getBean("book1"); //* id에 해당하는 인스턴스를 줘야함.
        book.setTitle("즐거운 자바");
        book.setPrice(5000);

        System.out.println(book.getPrice()); 
        System.out.println(book.getTitle());

        System.out.println("--------------");
        Book book2 = (Book)context.getBean("book1");
        System.out.println(book2.getTitle());

        //? Spring은 기본적으로 싱글톤으로 객체를 생성
        if(book == book2){
            System.out.println("같은 인스턴스");
        }else{
            System.out.println("다른 인스턴스");
        }
    }
}
