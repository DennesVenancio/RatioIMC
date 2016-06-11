package kurgomobi.ratioimc.features.model;

import java.text.DecimalFormat;

/**
 * Created by Dennes on 11/06/16.
 */
public class ProcedureIMC {

    private String typePerson;
    private String id;
    private double weight;
    private double height;
    private String result;
    private TypePerson type;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getResult() {
        return result;
    }

    public String getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(String typePerson) {
        this.typePerson = typePerson;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void calculateImc(){
        DecimalFormat df = new DecimalFormat("##.##");
        setResult(df.format(weight / Math.pow(height, 2)));
    }

}
