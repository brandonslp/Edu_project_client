/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.apache.commons.codec.digest.DigestUtils;
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
        SQLQuery sQLQuery = session.createSQLQuery(sqlGeneratorLogin());
        sQLQuery.addEntity(Usuarios.class);
        sQLQuery.setString("user_id", user);
        sQLQuery.setString("pass", pass);
        Usuarios u = (Usuarios) sQLQuery.uniqueResult();
        if (u!=null) {
            return u.getUsrToken(); 
        }
        return "Error"; 
    }
    
    
    private String sqlGeneratorLogin(){
        String sql="select * from USUARIOS where USR_ID = :user_id and USR_PASSWORD = :pass";
        return sql;
    }
    
    
    public boolean DocentePermission(String token){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        //SQLQuery sQLQuery = session.createSQLQuery("select * from USUARIOS, DOCENTE where usr_token = '"+token+"' and usr_id = doc_cod");
        SQLQuery sQLQuery = session.createSQLQuery("select * from USUARIOS, DOCENTE where usr_token = :token and usr_id = doc_cod");
        sQLQuery.addEntity(Usuarios.class);
        sQLQuery.setString("token", token);
        if (!sQLQuery.list().isEmpty()) {
            return true;
        }else return false;
    }
    
}
