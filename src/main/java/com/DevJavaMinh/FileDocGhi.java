package com.DevJavaMinh;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileDocGhi {

    public  void writeToFile(DanhSachNhanVien dsnhanvien, String file) throws Exception{
        ObjectOutputStream out = null;
        out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(dsnhanvien);
        out.close();
    }
    public Object Readfromfile(String file) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object list = ois.readObject();
        ois.close();
        return list;

    }

}
