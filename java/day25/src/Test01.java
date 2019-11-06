import org.junit.Test;
@MyAnno
public class Test01 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class c = Student.class;
        System.out.println(c);
        System.out.println("------------------------");
        Student s=new Student();
        Class c2 = s.getClass();
        System.out.println(c2);
        System.out.println("-------------------------");
        Class<?> c3 = Class.forName("java.lang.String");
        System.out.println(c3);
        System.out.println("--------------------------");
        String name = c3.getSimpleName();
        System.out.println(name);
        System.out.println("---------------------------");
    }
    @Test
    public void m() {
        System.out.println("111");
    }
}
