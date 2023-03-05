package utez.edu.mx.Model;

import utez.edu.mx.Service.ConnectionMySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoPrenda {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final Logger CONSOLE= LoggerFactory.getLogger(DaoPrenda.class);
    public List<BeanPrenda> findAll(){
        List<BeanPrenda> listPrendas = new ArrayList<>();
        try{
            con= ConnectionMySQL.getConnection() ;
            cstm= con.prepareCall("SELECT * FROM ropa ");
            rs= cstm.executeQuery();
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
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listPrendas;
    }

    public BeanPrenda findById (int id){
        BeanPrenda beanPrenda= null;
        try {

            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM ropa  WHERE id = ?");
            cstm.setInt(1, id);
            rs = cstm.executeQuery();

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
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return beanPrenda;
    }

    public boolean create(BeanPrenda prenda){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_create(?,?,?,?,?,?,?)}");
            cstm.setString(1, prenda.getNombre());
            cstm.setString(2,prenda.getMarca());
            cstm.setString(3,prenda.getTalla());
            cstm.setString(4, prenda.getColor());
            cstm.setDouble(5, prenda.getDescuento());
            cstm.setDouble(6,prenda.getCosto());
            cstm.setInt(7, prenda.getStock());
            cstm.execute();
            flag = true;
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean update(BeanPrenda prenda){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_update(?,?,?,?,?,?,?,?,?}");
            cstm.setString(1, prenda.getNombre());
            cstm.setString(2,prenda.getMarca());
            cstm.setString(3,prenda.getTalla());
            cstm.setString(4,prenda.getColor());
            cstm.setDouble(5,prenda.getDescuento());
            cstm.setDouble(6,prenda.getCosto());
            cstm.setInt(7,prenda.getStock());
            cstm.setInt(8,prenda.getId());
            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean delete(int id){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("UPDATE ropa SET status=FALSE WHERE id=?");
            cstm.setInt(1, id);
            flag = cstm.executeUpdate()==1;
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

}
