package model;

public class SimpleJsonFile {

    private String materialNumber;
    private String materialName;
    private ServiceMaterialListInner serviceMaterialListInner;

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public ServiceMaterialListInner getServiceMaterialListInner() {
        return serviceMaterialListInner;
    }

    public void setServiceMaterialListInner(ServiceMaterialListInner serviceMaterialListInner) {
        this.serviceMaterialListInner = serviceMaterialListInner;
    }
}

