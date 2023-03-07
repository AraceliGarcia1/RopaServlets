package utez.edu.mx.Model;

import utez.edu.mx.Service.ConnectionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoPrenda {
    private Connection con;
    private CallableStatement cstm;
    private PreparedStatement pstm;
    private ResultSet rs;
    final Logger CONSOLE= LoggerFactory.getLogger(DaoPrenda.class);
    public List<BeanPrenda> findAll(){
        List<BeanPrenda> listPrendas = new ArrayList<>();
        try{
            con= ConnectionMySQL.getConnection() ;
            pstm= con.prepareStatement("SELECT * FROM ropa ");
            rs= pstm.executeQuery();
            while(rs.next()){
                BeanPrenda beanPrenda = new BeanPrenda();

                beanPrenda.setId(rs.getInt("id"));
                beanPrenda.setNombre(rs.getString("nombre"));
                beanPrenda.setMarca(rs.getString("marca"));
                beanPrenda.setTalla(rs.getString("talla"));
                beanPrenda.setColor(rs.getString("color"));
                beanPrenda.setDescuento(rs.getDouble("descuento"));
                beanPrenda.setCosto(rs.getDouble("costo"));
                beanPrenda.setStock(rs.getInt("stock"));
                beanPrenda.setStatus(rs.getBoolean("status"));

                listPrendas.add(beanPrenda);
            }

        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());

        }finally {
            ConnectionMySQL.closeConnections(con, pstm, rs);
        }
        return listPrendas;
    }

    public BeanPrenda findById (int id){
        BeanPrenda beanPrenda= null;
        try {

            con = ConnectionMySQL.getConnection();
            pstm= con.prepareStatement("SELECT * FROM ropa WHERE id = ?");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            if(rs.next()){
                beanPrenda= new BeanPrenda();
                beanPrenda.setId(rs.getInt("id"));
                beanPrenda.setNombre(rs.getString("nombre"));
                beanPrenda.setMarca(rs.getString("marca"));
                beanPrenda.setTalla(rs.getString("talla"));
                beanPrenda.setColor(rs.getString("color"));
                beanPrenda.setDescuento(rs.getDouble("descuento"));
                beanPrenda.setCosto(rs.getDouble("costo"));
                beanPrenda.setStock(rs.getInt("stock"));
                beanPrenda.setStatus(rs.getBoolean("status"));


            }
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, pstm, rs);
        }
        return beanPrenda;
    }

    public boolean create(BeanPrenda prenda){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            pstm= con.prepareStatement("INSERT INTO ropa (nombre,marca,talla, color,descuento,costo,stock) VALUES(?,?,?,?,?,?,?)");
            pstm.setString(1, prenda.getNombre());
            pstm.setString(2,prenda.getMarca());
            pstm.setString(3,prenda.getTalla());
            pstm.setString(4, prenda.getColor());
            pstm.setDouble(5, prenda.getDescuento());
            pstm.setDouble(6,prenda.getCosto());
            pstm.setInt(7, prenda.getStock());
            flag = pstm.executeUpdate()==1;
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, pstm);
        }
        return flag;
    }

    public boolean update(BeanPrenda prenda){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            pstm= con.prepareStatement("UPDATE ropa SET nombre=?, marca=?,talla=?,color=?,descuento=?,costo=?,stock=? WHERE id=?");
            pstm.setString(1, prenda.getNombre());
            pstm.setString(2,prenda.getMarca());
            pstm.setString(3,prenda.getTalla());
            pstm.setString(4,prenda.getColor());
            pstm.setDouble(5,prenda.getDescuento());
            pstm.setDouble(6,prenda.getCosto());
            pstm.setInt(7,prenda.getStock());
            pstm.setInt(8,prenda.getId());
            if (pstm.executeUpdate()==1) {
                flag =true;
            }

        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, pstm);
        }
        return flag;
    }

    public boolean delete(int id){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareStatement("UPDATE ropa SET status=FALSE WHERE id=?");
            pstm.setInt(1, id);
            flag = pstm.executeUpdate()==1;
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, pstm);
        }
        return flag;
    }

}
