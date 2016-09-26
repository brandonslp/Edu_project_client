
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.AsistenciaDAO;
import DAO.LocaleDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import pojos.Asistencia;
import pojos.Departamento;
import pojos.Municipio;

/**
 *
 * @author brand
 */
@WebService(serviceName = "GestorWS")
public class GestorWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllDepartments")
    public String getAllDepartments() {
        //TODO write your implementation code here:
        LocaleDAO ldao = new LocaleDAO();
        List<Departamento> listD = ldao.getAllDeparment();
        JsonArray deparments = new JsonArray();
        JsonObject  deparment;
        for (Departamento d : listD) {
            deparment = new JsonObject();
            deparment.addProperty("dep_Id", d.getDepId());
            deparment.addProperty("dep_Nombre", d.getDepNombre());
            deparments.add(deparment);
        }
        
        return new Gson().toJson(deparments);
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getMunipsById")
    public String getMunipsById(@WebParam(name = "idDept") int idDept) {
        LocaleDAO ldao = new LocaleDAO();
        List<Municipio> listM = ldao.getMunipsById(idDept);
        //String str = new Gson().toJson(listM);
        JsonArray municipios = new JsonArray();
        JsonObject  municipio;
        for (Municipio m : listM) {
            municipio = new JsonObject();
            municipio.addProperty("mun_id", m.getId().getMunId());
            municipio.addProperty("mun_nombre", m.getMunNombre());
            municipios.add(municipio);
        }
        return new Gson().toJson(municipios);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllAsistanceByAsignature")
    public String getAllAsistanceByAsignature(@WebParam(name = "DOC_COD") String DOC_COD, @WebParam(name = "INS_DCODIGO") int INS_DCODIGO, @WebParam(name = "MUN_ID") int MUN_ID, @WebParam(name = "DEP_ID") int DEP_ID, @WebParam(name = "SEC_CODIGO") int SEC_CODIGO, @WebParam(name = "ASI_COD") int ASI_COD, @WebParam(name = "CUR_COD") int CUR_COD) {
        //TODO write your implementation code here:
        AsistenciaDAO adao = new AsistenciaDAO();
        //List<Asistencia> listA = adao.getAllAsistanceByAsignature(DOC_COD, INS_DCODIGO, MUN_ID, DEP_ID, SEC_CODIGO, ASI_COD, CUR_COD);
        List<Asistencia> listA = adao.getAllAsistanceByAsignature(DOC_COD,ASI_COD);
        JsonArray asistencias = new JsonArray();
        JsonObject  asistencia;
        if (!listA.isEmpty()) {
            for (Asistencia a : listA) {
            asistencia = new JsonObject();
            asistencia.addProperty("Fecha", a.getAsisFecha().toGMTString());
            asistencia.addProperty("Est_cod", a.getEstudiante().getId().getEstCod());
            asistencia.addProperty("asistencia", a.getAsisAsistencia());
            asistencias.add(asistencia);
            }
        }else{
            asistencia = new JsonObject();
            asistencia.addProperty("Error", "No hay asistencias registradas");
            asistencias.add(asistencia);
        }
        
        return new Gson().toJson(asistencias);
    }
}
