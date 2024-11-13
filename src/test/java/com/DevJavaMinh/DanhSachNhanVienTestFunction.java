package com.DevJavaMinh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DanhSachNhanVienTestFunction {
    private DanhSachNhanVien dsnhanvien;

    @BeforeEach
    public void setUp() {
        dsnhanvien = new DanhSachNhanVien();
    }

    @Test
    public void testThemNhanVien() {
        NhanVien nv = new NhanVien(1, "Nguyen", "A", true,  30, 5000.0);
        Assertions.assertTrue(dsnhanvien.themnhanvien(nv));
        assertEquals(1, dsnhanvien.tong());
    }

    @Test
    public void testThemNhanVien_MaTrung() {
        NhanVien nv1 = new NhanVien(1, "Nguyen", "A", true, 30, 5000.0);
        NhanVien nv2 = new NhanVien(1, "Nguyen", "B", false, 25, 6000.0);
        dsnhanvien.themnhanvien(nv1);
        Assertions.assertFalse(dsnhanvien.themnhanvien(nv2)); // Mã trùng
        assertEquals(1, dsnhanvien.tong());
    }

    @Test
    public void testXoaNhanVien() {
        NhanVien nv = new NhanVien(1, "Nguyen", "A", true, 30, 5000.0);
        dsnhanvien.themnhanvien(nv);
        assertTrue(dsnhanvien.xoanhanvien(1));
        assertEquals(0, dsnhanvien.tong());
    }

    @Test
    public void testXoaNhanVien_KhongTonTai() {
        assertFalse(dsnhanvien.xoanhanvien(1)); // Nhân viên không tồn tại
    }

    @Test
    public void testTimKiemNhanVien() {
        NhanVien nv = new NhanVien(1, "Nguyen", "A", true, 30, 5000.0);
        dsnhanvien.themnhanvien(nv);
        NhanVien found = dsnhanvien.timkiem(1);
        assertNotNull(found);
        assertEquals(nv.getManv(), found.getManv());
    }

    @Test
    public void testTimKiemNhanVien_KhongTonTai() {
        assertNull(dsnhanvien.timkiem(1)); // Không tìm thấy nhân viên
    }
    @Test
    public void testTong() {
        // Kiểm tra tổng số nhân viên khi danh sách trống
        assertEquals(0, dsnhanvien.tong());

        // Thêm nhân viên và kiểm tra lại
        NhanVien nv1 = new NhanVien(1, "Nguyen", "A", true, 30, 5000.0);
        dsnhanvien.themnhanvien(nv1);
        assertEquals(1, dsnhanvien.tong());

        NhanVien nv2 = new NhanVien(2, "Nguyen", "B", false, 25, 6000.0);
        dsnhanvien.themnhanvien(nv2);
        assertEquals(2, dsnhanvien.tong());
    }

    @Test
    public void testGetNhanVien() {
        // Thêm nhân viên vào danh sách
        NhanVien nv1 = new NhanVien(1, "Nguyen", "A", true, 30, 5000.0);
        dsnhanvien.themnhanvien(nv1);

        // Kiểm tra lấy nhân viên theo chỉ số
        NhanVien found = dsnhanvien.getNhanVien(0);
        assertNotNull(found);
        assertEquals(nv1.getManv(), found.getManv());

        // Kiểm tra lấy nhân viên không tồn tại
        assertNull(dsnhanvien.getNhanVien(1)); // Chỉ số ngoài phạm vi
    }
}
