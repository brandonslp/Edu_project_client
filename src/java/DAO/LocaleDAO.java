/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojos.Departamento;

/**
 *
 * @author brand
 */
/*
    Controlador de Departamentos y municipios
*/
public class LocaleDAO extends DAO{
    
    
    public List<Departamento> getAllDeparment(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query  query = session.createQuery("from Departamento");
        List<Departamento> listD = query.list();
        return listD;
    
    }
    
}
