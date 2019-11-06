import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(urlPatterns="/a", name="Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("666");
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.dataSource);
        String sql = "SELECT username,password FROM user ";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        response.setContentType("text/html;charset=utf-8");//转码,解决登录后中文乱码的情况
        for (Map<String, Object> map : maps) {
            System.out.println("666");
            System.out.println("------------");
                if (map.get("username").equals(user)&&map.get("password").equals(pass)){
                    response.getWriter().print("登录成功");
                    return;
                }
            }
        response.getWriter().print("登录失败");
//            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
//            for (Map.Entry<String, Object> entry : entrySet) {
//                String key = entry.getKey();
//                Object value = entry.getValue();
//                if (username.equals(key)&&password.equals(value)){
//                    response.getWriter().print("true");
//                }
//                }
//            }/*response.getWriter().print("flase");*/

        }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}