import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Test02 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Student student=new Student();
        Map<String,Object> map =new HashMap<>();
        map.put("name","杰克");
        map.put("gender","男");
        map.put("age",20);
        BeanUtils.populate(student,map);//把map集合添加到student里
        System.out.println(student);
    }
}
