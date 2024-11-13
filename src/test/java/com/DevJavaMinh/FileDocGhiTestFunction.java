package com.DevJavaMinh;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileDocGhiTestFunction {

    private final String filename = "test_data.txt";

    @Test
    public void testWriteAndReadFromFile() throws Exception {
        DanhSachNhanVien ds = new DanhSachNhanVien();
        NhanVien nv = new NhanVien(1, "Nguyen", "A", true, 30, 5000.0);
        ds.themnhanvien(nv);

        FileDocGhi fileDocGhi = new FileDocGhi();
        fileDocGhi.writeToFile(ds, filename);

        // Đọc danh sách nhân viên từ tệp
        DanhSachNhanVien readDs = (DanhSachNhanVien) fileDocGhi.Readfromfile(filename);

        // Kiểm tra số lượng nhân viên
        assertEquals(1, readDs.tong());
        // Kiểm tra mã nhân viên
        assertEquals(nv.getManv(), readDs.getNhanVien(0).getManv());

        // Xóa tệp sau khi kiểm tra
        new File(filename).delete();
    }
}
