package com.DevJavaMinh;


public class Start {
    private static final String tenfile = "src/main/resources/data/danhsachnhanvien.txt";
    public static void main(String[] args) throws Exception {
        DanhSachNhanVien dsnhanvien = new DanhSachNhanVien();
        new PhanMemQLNhanVien(dsnhanvien,tenfile).setVisible(true);
    }
}

