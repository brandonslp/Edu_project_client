/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojos.Asistencia;

/**
 *
 * @author brand
 */
public class AsistenciaDAO {

    
    public List<Asistencia> getAllAsistanceByAsignature(String DOC_COD, int INS_DCODIGO, int MUN_ID, int DEP_ID, int SEC_CODIGO,  int ASI_COD, int CUR_COD){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        SQLQuery sQLQuery = session.createSQLQuery(sqlGenerator(DOC_COD, INS_DCODIGO, MUN_ID, DEP_ID, SEC_CODIGO, ASI_COD, CUR_COD));
        sQLQuery.addEntity(Asistencia.class);
        List<Asistencia> listA = sQLQuery.list();
        return listA;
    }
    
    public List<Asistencia> getAllAsistanceByAsignature(String DOC_COD,int ASI_COD){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        SQLQuery sQLQuery = session.createSQLQuery(sqlGenerator(DOC_COD, ASI_COD));
        sQLQuery.addEntity(Asistencia.class);
        List<Asistencia> listA = sQLQuery.list();
        return listA;
    }
    
    private String sqlGenerator(String DOC_COD, int INS_DCODIGO, int MUN_ID, int DEP_ID, int SEC_CODIGO,  int ASI_COD, int CUR_COD){
        String sql="SELECT *\n" +
                "FROM DEPARTAMENTO dpt, \n" +
                "MUNICIPIO mun, \n" +
                "SECRETARIA_EDUC sec, \n" +
                "INSTITUCIONES_EDU ins, \n" +
                "CURSO cur, DOCENTE doc, \n" +
                "ASIGNATURA asi\n" +
                "WHERE doc.DOC_COD =" +DOC_COD +" and\n" +
                "ins.INS_DCODIGO ="+ INS_DCODIGO +" and\n" +
                "mun.MUN_ID ="+ MUN_ID +" and\n" +
                "dpt.DEP_ID ="+ DEP_ID +" and\n" +
                "sec.SEC_CODIGO="+SEC_CODIGO+" and\n" +
                "asi.ASI_COD ="+ASI_COD+" and\n" +
                "cur.CUR_COD="+CUR_COD;
        
    return sql;
    }
    
    
    private String sqlGenerator(String DOC_COD, int ASI_COD){
        String sql="SELECT * from ASISTENCIA WHERE DOC_COD = "+DOC_COD+" and ASI_COD =" +ASI_COD;
        
    return sql;
    }
}
