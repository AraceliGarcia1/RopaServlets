package utez.edu.mx.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utez.edu.mx.Service.ConnectionMySQL;

import java.sql.*;

public class DaoUsuario {
    private Connection con;
    private CallableStatement cstm;
    private PreparedStatement pstm;
    private ResultSet rs;
    final Logger CONSOLE= LoggerFactory.getLogger(DaoPrenda.class);
    public BeanUsuario login (BeanUsuario beanUsuario) throws SQLException {
        BeanPrenda beanPrenda= null;
        boolean isOk = false;

        try {
            con = ConnectionMySQL.getConnection();
            pstm= con.prepareStatement("SELECT * FROM usuario WHERE usuario = ? AND contrasena = ?");
            pstm.setString(1, beanUsuario.getUsuario());
            pstm.setString(2, beanUsuario.getContrasena());
            rs = pstm.executeQuery();
            if(rs.next()){
                pstm = con.prepareStatement("UPDATE USUARIO SET ultimo_acceso = now() where id = ?");
                beanUsuario.setId(rs.getInt("id"));
                pstm.setInt(1, rs.getInt("id"));
                isOk = pstm.executeUpdate()==1;
            }

        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, pstm, rs);
            if(isOk) return beanUsuario;
            else return null;
        }
    }
}
