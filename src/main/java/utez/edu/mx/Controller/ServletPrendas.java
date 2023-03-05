package utez.edu.mx.Controller;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utez.edu.mx.Model.BeanPrenda;
import utez.edu.mx.Model.DaoPrenda;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@MultipartConfig
@WebServlet(name = "ServletPrendas",  urlPatterns ={"/readPrendas","/createPrendas", "/updatePrendas","/deletePrendas"})
public class ServletPrendas extends HttpServlet{
    private Map map=new HashMap();
    final Logger CONSOLE= LoggerFactory.getLogger(ServletPrendas.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        map.put("listPrendas", new DaoPrenda().findAll());
        write(response, map);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action= request.getParameter("action");
        BeanPrenda beanPrenda=new BeanPrenda();
        DaoPrenda daoPrenda=new DaoPrenda();

        switch (action){
            case "create":
                beanPrenda.setNombre(request.getParameter("nombre"));
                beanPrenda.setMarca(request.getParameter("marca"));
                beanPrenda.setColor(request.getParameter("color"));
                beanPrenda.setTalla(request.getParameter("talla"));
                beanPrenda.setDescuento(Double.parseDouble(request.getParameter("descuento")));
                beanPrenda.setCosto(Double.parseDouble(request.getParameter("costo")));
                beanPrenda.setStock(Integer.parseInt(request.getParameter("stock")));
                boolean flag=daoPrenda.create(beanPrenda);
                if(flag){

                    map.put("message","Se registro correctamente");
                }else{
                    map.put("message", "No se registro correctamente");
                    CONSOLE.error("");
                }
                break;
            case "update":
                beanPrenda.setId(Integer.parseInt(request.getParameter("id")));
                beanPrenda.setNombre(request.getParameter("nombre"));
                beanPrenda.setMarca(request.getParameter("marca"));
                beanPrenda.setColor(request.getParameter("color"));
                beanPrenda.setTalla(request.getParameter("talla"));
                beanPrenda.setDescuento(Double.parseDouble(request.getParameter("descuento")));
                beanPrenda.setCosto(Double.parseDouble(request.getParameter("costo")));
                beanPrenda.setStock(Integer.parseInt(request.getParameter("stock")));
                boolean flag1=daoPrenda.update(beanPrenda);
                if(flag1){
                    map.put("message","Se actualizo correctamente");

                }else{
                    map.put("message", "No se actualizo correctamente");
                }
                break;

            case "delete":
                if(new DaoPrenda().delete(Integer.parseInt(request.getParameter("id")))){
                    request.setAttribute("mesage","Se ha eliminado correctamente");

                }else{
                    CONSOLE.error("No se ha eliminado");
                }

                break;

            default:
                request.getRequestDispatcher("/").forward(request,response);
                break;


        }
        response.sendRedirect(request.getContextPath()+"/readPrendas");


    }
    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException{
        response.setContentType("aplication/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(map));

    }
}
