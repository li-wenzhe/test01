import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(urlPatterns="/download", name="DonwloadServlet")
public class DonwloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename=request.getParameter("filename");
        ServletContext context = this.getServletContext();

        /*================文件下载增加的代码=======================*/
        response.setHeader("Content-Disposition","attachment;filename=" + DownloadUtils.encodeFilename(request,filename));



        InputStream is = context.getResourceAsStream("img/"+filename);
        ServletOutputStream os = response.getOutputStream();

        int len = -1;
        byte[] by=new byte[1024*8];
        while((len=is.read(by))!=-1){
            os.write(by,0,len);
        }
        os.close();
        is.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
