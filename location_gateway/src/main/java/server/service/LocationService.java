package server.service;

import server.obj.DeviceModel;

import server.db.BdBean;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by IntelliJ IDEA. User: Niraj Singh Date: 3/13/13 Time: 3:58 PM To
 * change this template use File | Settings | File Templates.
 */
@Path("/locationservice/")
public class LocationService {

//protected final Logger log = LoggerFactory.getLogger(LocationService.class);
    @POST
    @Path("/getlocation/{id}")
    @Produces({"application/json"})
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
    public Response getLocation(@PathParam("id") String id) {
        DeviceModel device = null;

        BdBean bdBean = new BdBean();
        device = bdBean.selectOne(id);
        String s = "s";
                
        if (device == null) {
            return Response.ok(s).build();
        } else {
            return Response.ok(device).build();
        }
    }

    @POST
    @Path("/addlocation")
    @Produces({"application/json"})
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
    public Response addLocation(@FormParam("longitude") String longitude,
            @FormParam("latitude") String latitude) {

        DeviceModel deviceModel;
        deviceModel = new DeviceModel(Float.parseFloat(longitude), Float.parseFloat(latitude));
        BdBean bdBean = new BdBean();
   
        if (bdBean.insert(deviceModel)) {
            return Response.ok(deviceModel).build();
        } else {
           return Response.status(Response.Status.BAD_REQUEST).build(); 
        }
    }
    
     @POST
    @Path("/updatelocation")
    @Produces({"application/json"})
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
    public Response updateLocation(@FormParam("longitude") String longitude,
            @FormParam("latitude") String latitude, @FormParam("id") String id) {

        DeviceModel deviceModel;
        deviceModel = new DeviceModel(Float.parseFloat(longitude), Float.parseFloat(latitude));
        deviceModel.setId(Long.parseLong(id));
     
        BdBean bdBean = new BdBean();
   
        if (bdBean.update(deviceModel)) {
            return Response.ok(deviceModel).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
