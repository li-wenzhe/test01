package test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) throws Exception {
        //类加载器加载文件,是从类加载路径里读取的.只要文件在类加载路径里,都可以读取
        //所有IO流读取文件时,文件之间的都用/分隔
        InputStream is = Demo01.class.getClassLoader().getResourceAsStream("01xml/src/student.xml");

        //1.读取xml,得到Document对象
        //用SAXReader解析器
        SAXReader reader = new SAXReader();
        Document document = reader.read(is);

        System.out.println(document);
    }

    //要求:读取所有书籍的信息
    @Test
    public void test2() throws Exception {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("books.xml");

        //1.读取xml文件,得到Document对象
        SAXReader reader = new SAXReader();
        Document document = reader.read(is);

        //2.从Document对象里得到根标签对象
        Element rootElement = document.getRootElement();

        //3.获取根标签的所有名称为book的子标签
        List<Element> elements = rootElement.elements("book");

        //4.获取每个书籍的信息
        for (Element bookElement : elements) {
            //获取书籍的编号
            String id = bookElement.attributeValue("id");//根据属性名称得到值
            System.out.println("编号:"+id);
            //获取书籍的出版社
            String publish = bookElement.attributeValue("publish");
            System.out.println("出版社:"+publish);

            //获取书籍的名称:子标签name
            Element nameElement = bookElement.element("name");//根据子标签的名字得到标签
            String name = nameElement.getText();//得到标签里面的内容
            System.out.println("书名"+name);

            //获取书籍的作者:子标签author
            String author = bookElement.elementText("author");//根据子标签的名字得到内容
            System.out.println("作者:"+author);

            //获取书籍的价格:子标签price
            String price = bookElement.elementText("price");
            System.out.println("价格:"+price);

            //获取书籍的介绍:子标签description
            String description = bookElement.elementTextTrim("description");//Trim去掉多余的空格
            System.out.println("书籍的介绍:"+description/*.trim()*/);//trim()是去掉前后的空格,中间的不去

            System.out.println("--------------------------");
        }
    }
}
