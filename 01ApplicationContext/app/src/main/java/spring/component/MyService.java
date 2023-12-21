package spring.component;


public class MyService {
    // MyService가 MyDao를 가지는 연관관계
    //? 1. setter 주입, 2. 생성자 주입
    private MyDao myDao; // 필드 


    //! 프로퍼티와 필드는 다르다. 
    //~> 프로퍼티는 메서드 
    /*
     * setter 메소드 or
     * myDao 프로퍼티 라고 부른다.
     */
    public void setMyDao(MyDao myDao) {
        this.myDao = myDao;
    }

    public void service() {
        System.out.println("MyService.service()");
        //MyDao를 주입하지 않으면 NullPointerException을 던짐.
        myDao.get();
    }
}
