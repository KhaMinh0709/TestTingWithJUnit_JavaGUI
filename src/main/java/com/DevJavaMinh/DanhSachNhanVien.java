package com.DevJavaMinh;

import java.io.Serializable;
import java.util.ArrayList;


public class DanhSachNhanVien implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<NhanVien> list;

    public DanhSachNhanVien() {
        list = new ArrayList<NhanVien>();
    }
    public boolean themnhanvien(NhanVien nv) {
        for (NhanVien nhanVien : list) {  // duyệt danh sách nhân viên
            if (nhanVien.getManv() == nv.getManv()) {
                return false;
            }
        }
        list.add(nv);  // nếu không trùng, thêm nhân viên vào danh sách
        return true;  // trả về true khi thêm thành công
    }

    public boolean xoanhanvien(int manv){
        NhanVien nv = new NhanVien(manv);
        if(list.contains(nv)) {
            list.remove(nv);
            return true;
        }
        return false;
    }
    public NhanVien timkiem(int manv) {
        NhanVien nv = new NhanVien(manv);
        if(list.contains(nv))
            return list.get(list.indexOf(nv));
        return null;
    }
    public NhanVien getNhanVien(int i) {
        if(i>=0 && i<list.size())
            return list.get(i);
        return null;
    }
    public int tong() {
        return list.size();
    }

    public ArrayList<NhanVien> getlist(){
        return list;
    }

    public void clear() {
        for (NhanVien nv : list) {
            list.remove(nv);
        }
    }
}
