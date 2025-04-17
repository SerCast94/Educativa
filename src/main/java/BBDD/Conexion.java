package BBDD;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class Conexion {

    /**
     * Crear la conexión con la base de datos
     * @return un objeto Session con la conexión con la base de datos
     */
    public static Session crearConexion() {
        // Establece la conexión mediante Session gracias a los datos de hibernate.cfg.xml
        Session sesion;
        try {
            sesion = new Configuration().configure().buildSessionFactory().openSession();
        }catch (Throwable ex){
            System.err.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return sesion;
    }
}
