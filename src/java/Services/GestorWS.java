
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.LocaleDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
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
}
