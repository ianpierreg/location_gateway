/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.obj;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "DeviceModel")
public class DeviceModel
{
    private Long id;
    private Float latitude;
    private Float longitude;

    public DeviceModel(){
    }
    
    public DeviceModel(Float latitude, Float longitude)
    {
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
}
