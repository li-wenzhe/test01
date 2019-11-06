package test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class Demo02_xpath {
    public static void main(String[] args) {
        //得到books.xml里面所有书籍的名称  //name
        //得到所有杂志的名称    //magazine/name
        //得到属性id为book1的书籍名称    //*[@id='book1']/name


    }

    @Test
    public void test1() throws DocumentException {
        //得到books.xml里面所有书籍的名称  //name
        InputStream is = Demo02_xpath.class.getClassLoader().getResourceAsStream("books.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(is);

        List<Node> nodes = document.selectNodes("//name");//xpath表达式
        for (Node node : nodes) {
            Element element = (Element) node;//强转
            String text = element.getText();
            System.out.println(text);
        }
    }
}
