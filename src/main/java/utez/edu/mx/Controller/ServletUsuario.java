package utez.edu.mx.Controller;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utez.edu.mx.Model.BeanPrenda;
import utez.edu.mx.Model.BeanUsuario;
import utez.edu.mx.Model.DaoPrenda;
import utez.edu.mx.Model.DaoUsuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@MultipartConfig
@WebServlet(name = "ServletUsuario",  urlPatterns ={"/login"})
public class ServletUsuario extends HttpServlet {
    private Map map=new HashMap();
    final Logger CONSOLE= LoggerFactory.getLogger(ServletPrendas.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action= request.getParameter("action");
        DaoUsuario daoUsuario = new DaoUsuario();
        BeanUsuario beanUsuario = new BeanUsuario();

        switch (action){
            case "login":
                beanUsuario.setUsuario(request.getParameter("usuario"));
                beanUsuario.setContrasena(request.getParameter("contrasena"));
                try {
                    beanUsuario = daoUsuario.login(beanUsuario);
                    if(beanUsuario != null){
                        map.put("message","Ok");
                        response = write(response, map);
                        HttpSession session = request.getSession();

                        session.setAttribute("id_usuario", beanUsuario.getId());
                        response.setStatus(200);
                    }else{
                        map.put("message", "Contraseña y/o usuario incorrecto");
                        write(response, map);
                        response.setStatus(200);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                request.getRequestDispatcher("/").forward(request,response);
                break;
        }
    }
    private HttpServletResponse write(HttpServletResponse response, Map<String, Object> map) throws IOException{
        response.setContentType("aplication/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(map));
        return response;
    }
}


