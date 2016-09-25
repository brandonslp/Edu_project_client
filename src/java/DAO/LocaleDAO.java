/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.google.gson.Gson;
import java.util.List;
import org.hibernate.Query;
import pojos.Departamento;

/**
 *
 * @author brand
 */
/*
    Controlador de Departamentos y municipios
*/
public class LocaleDAO extends DAO{

    public LocaleDAO() {
        super();
    }
    
    
    public String getAllDepartments(){
        sf=HibernateUtil.getSessionFactory();
        session = sf.openSession();
        Query query = session.createQuery("from Departamento");
        List<Departamento> listD = query.list();
        session.close();
        return new Gson().toJson(listD);
    }
    
}
