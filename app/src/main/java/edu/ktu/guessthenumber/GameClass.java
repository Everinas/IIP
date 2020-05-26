package edu.ktu.guessthenumber;

public class GameClass {

    private String name, dif, result;

    public GameClass() {
    }

    public GameClass(String name, String dif, String result) {
        this.name = name;
        this.dif = dif;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDif() {
        return dif;
    }

    public void setDif(String dif) {
        this.dif = dif;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
