package exam;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;


//! Bean관리의 역할을 하므로 싱글톤으로 
//* 무조건 싱글톤은 아니다. 프로토타입이라는 경우가 있음.
public class ApplicationContext {
    Properties props;
    // ~> 싱글톤 객체를 생성해서 검사하기 위한 objectMap
    Map<String, Object> objectMap;

    private static ApplicationContext instance = null;

    public static ApplicationContext getInstance(){
        if(instance == null){
            instance = new ApplicationContext();
            return instance;
        }
        return instance;
        
    }

    private ApplicationContext() {

        props = new Properties();
        objectMap = new HashMap<>();
        try {
            props.load(new FileInputStream("app/src/main/resources/Beans.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String id) throws Exception {

        Object o1 = objectMap.get(id);
        if (o1 == null) {
            // className에 해당하는 문자열을 가지고 인스턴스 생성
            String className = props.getProperty(id);
            // classpath로 부터 className에 해당하는 클래스를 찾은 후 클래스 정보 반환
            Class<?> clazz = Class.forName(className);
            // Stream.of(clazz.getMethods()).map(Method::getName).forEach(System.out::println);

            // ClassLoader에서 가져온 생성자를 이용한 인스턴스 생성
            Object o = clazz.getDeclaredConstructor().newInstance();
            objectMap.put(id, o);
            o1 = objectMap.get(id);
        }

        return o1;
    }
}
