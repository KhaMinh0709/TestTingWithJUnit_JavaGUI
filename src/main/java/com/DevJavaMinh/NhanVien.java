package com.DevJavaMinh;

import java.io.Serializable;


public class NhanVien implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int manv;
    private String honv;
    private String tennv;
    private int tuoinv;
    private boolean phainv;
    private double tienluong;

    public NhanVien(int manv, String honv, String tennv, boolean phainv, int tuoinv, Double tienluong) {
        super();
        this.manv = manv;
        this.honv = honv;
        this.tennv = tennv;
        this.tuoinv = tuoinv;
        this.phainv = phainv;
        this.tienluong = tienluong;
    }
    public NhanVien(int manv) {
        this(manv,"","",true,0,0.0);
    }

    public NhanVien() {
        this(0);
    }
    public int getManv() {
        return manv;
    }
    public void setManv(int manv) {
        this.manv = manv;
    }
    public String getHonv() {
        return honv;
    }
    public void setHonv(String honv) {
        this.honv = honv;
    }
    public String getTennv() {
        return tennv;
    }
    public void setTennv(String tennv) {
        this.tennv = tennv;
    }
    public int getTuoinv() {
        return tuoinv;
    }
    public void setTuoinv(int tuoinv) {
        this.tuoinv = tuoinv;
    }
    public boolean isPhainv() {
        return phainv;
    }
    public void setPhainv(boolean phainv) {
        this.phainv = phainv;
    }
    public double getTienluong() {
        return tienluong;
    }
    public void setTienluong(double tienluong) {
        this.tienluong = tienluong;
    }
    public int hashCode() {
        final int prime =31;
        int result =1;
        result = prime * result + manv;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        NhanVien other =(NhanVien) obj;
        if(manv != other.manv)
            return false;
        return true;
    }
    @Override
    public String toString() {
        return manv + ";"+ honv +";" +tennv + ";" +phainv +";" + tuoinv + ";" +tienluong;

    }
}
