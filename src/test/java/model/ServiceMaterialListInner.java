package model;

public class ServiceMaterialListInner {

    private Integer id;
    private Integer materialServiceNumber;
    private String materialServiceName;
    private Integer price;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaterialServiceNumber() {
        return materialServiceNumber;
    }
    public void setMaterialServiceNumber(Integer materialServiceNumber) {
        this.materialServiceNumber = materialServiceNumber;
    }

    public String getMaterialServiceName() {
        return materialServiceName;
    }
    public void setMaterialServiceName(String materialServiceName) {
        this.materialServiceName = materialServiceName;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
}
