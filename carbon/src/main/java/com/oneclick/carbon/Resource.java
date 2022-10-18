package com.oneclick.carbon;


import javax.persistence.*;

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "resourceId")
    private String resourceId;

    @Column(name = "name")
    private String name;

    @Column(name = "impactGWP100_kgCO2e")
    private double impactGWP100_kgCO2e;

    @Column(name = "impactAP_kgSO2e")
    private double impactAP_kgSO2e;

    @Column(name = "quantity")
    private int quantity;


    @Column(name = "total_impact_AP_kgSO2e")
    private double total_impactAP_kgSO2e;

    @Column(name = "total_impact_GWP100_kgCO2e")
    private double total_impactGWP100_kgCO2e;

    @Column(name = "total_impact")
    private double total_impact;


    public Resource(){

    }

    public Resource(long id,String resourceId, String name,double impactGWP100_kgCO2e, double impactAP_kgSO2e,int quantity,double total_impactGWP100_kgCO2e,double total_impactAP_kgSO2e,double total_impact) {
        this.id=id;
        this.resourceId = resourceId;
        this.name = name;
        this.impactGWP100_kgCO2e = impactGWP100_kgCO2e;
        this.impactAP_kgSO2e=impactAP_kgSO2e;
        this.quantity=quantity;
        this.total_impactGWP100_kgCO2e=total_impactGWP100_kgCO2e;
        this.total_impactAP_kgSO2e=total_impactAP_kgSO2e;
        this.total_impact=total_impact;

    }

    public long getId() {
        return id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getImpactGWP100_kgCO2e() {
        return impactGWP100_kgCO2e;
    }

    public void setImpactGWP100_kgCO2e(double impactGWP100_kgCO2e) {
        this.impactGWP100_kgCO2e = impactGWP100_kgCO2e;
    }

    public double getImpactAP_kgSO2e() {
        return impactAP_kgSO2e;
    }

    public void setImpactAP_kgSO2e(double impactAP_kgSO2e) {
        this.impactAP_kgSO2e = impactAP_kgSO2e;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal_impactAP_kgSO2e() {
        return total_impactAP_kgSO2e;
    }

    public double getTotal_impactGWP100_kgCO2e() {
        return total_impactGWP100_kgCO2e;
    }

    public void setTotal_impactAP_kgSO2e(double total_impactAP_kgSO2e) {
        this.total_impactAP_kgSO2e = total_impactAP_kgSO2e;
    }

 //   public double getTotal_impactGWP100_kgCO2e() {
  //      return getImpactGWP100_kgCO2e();
   // }

    public void setTotal_impactGWP100_kgCO2e(double total_impactGWP100_kgCO2e) {
        this.total_impactGWP100_kgCO2e = total_impactGWP100_kgCO2e;
    }



    public double getTotal_impact() {
              return total_impact;
         }


    public void setTotal_impact(double total_impact) {
        this.total_impact = total_impact;
    }

    @Override
    public String toString() {
        return "Resource [id=" + id + ", resourceId=" + resourceId + ", name=" + name + ", impactGWP100_kgCO2e=" + impactGWP100_kgCO2e +", total_impactGWP100_kgCO2e=" + total_impactGWP100_kgCO2e + ", impactGWP100_kgCO2e=" + impactGWP100_kgCO2e+", Total_impactAP_kgSO2e=" + total_impactAP_kgSO2e+", total_impact=" + total_impact+"]";
    }

}
