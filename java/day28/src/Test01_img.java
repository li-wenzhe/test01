import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(urlPatterns="/Test01", name="Test01_img")
public class Test01_img extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = this.getServletContext();//获取ServletContext对象:整个web项目封装成的对象
        InputStream is = context.getResourceAsStream("img/a.jpg");//获取a.jpg的输入流
        ServletOutputStream os = response.getOutputStream();//获取字节型的输出流

        //读取数据,通过输出流写出
        int len =-1;
        byte[] b=new byte[1024*8];
        while((len=is.read(b))!=-1){
            os.write(b,0,len);
        }
        os.close();
        is.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
