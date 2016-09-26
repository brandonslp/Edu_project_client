/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojos.Usuarios;

/**
 *
 * @author brand
 */
public class ValidatorDAO {
    
    public String validate(String user, String pass){
        pass=DigestUtils.sha512Hex(pass);
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        SQLQuery sQLQuery = session.createSQLQuery(sqlGeneratorLogin(user, pass));
        sQLQuery.addEntity(Usuarios.class);
        Usuarios u = (Usuarios) sQLQuery.uniqueResult();
        return u.getUsrToken(); 
    }
    
    
    private String sqlGeneratorLogin(String user_id, String pass){
        String sql="select * from USUARIOS where USR_ID = "+user_id+" and USR_PASSWORD = '"+ pass+"'";
        return sql;
    }
    
    
    public boolean DocentePermission(String token){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        SQLQuery sQLQuery = session.createSQLQuery("select * from USUARIOS, DOCENTE where usr_token = '"+token+"' and usr_id = doc_cod");
        if (!sQLQuery.list().isEmpty()) {
            return true;
        }else return false;
    }
    
}
