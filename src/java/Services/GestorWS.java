
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.AsistenciaDAO;
import DAO.LocaleDAO;
import DAO.ValidatorDAO;
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
    public String getAllAsistanceByAsignature(@WebParam(name = "DOC_COD") String DOC_COD, @WebParam(name = "ASI_COD") int ASI_COD, @WebParam(name = "token") String token) {
        //TODO write your implementation code here:
        ValidatorDAO vdao = new ValidatorDAO();
        if (vdao.DocentePermission(token)) {
            return getAsitancebyAsignature(DOC_COD, ASI_COD);
        }else{
            JsonObject jo = new JsonObject();
            jo.addProperty("error", "El usuario no tiene permisos de ejecutar esta funcion");
            return new Gson().toJson(jo);
        }
        
    }

    private String getAsitancebyAsignature(String DOC_COD, int ASI_COD){
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
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "loginUser")
    public String loginUser(@WebParam(name = "User") String User, @WebParam(name = "Pass") String Pass) {
        //TODO write your implementation code here:
        ValidatorDAO vdao = new ValidatorDAO();
        String token = vdao.validate(User, Pass);
        JsonObject jtoken = new JsonObject();
        jtoken.addProperty("token", token);
        return new Gson().toJson(jtoken);
    }

}
