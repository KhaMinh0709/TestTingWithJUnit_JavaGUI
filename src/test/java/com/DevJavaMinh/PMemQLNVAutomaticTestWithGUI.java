package com.DevJavaMinh;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class PMemQLNVAutomaticTestWithGUI {



    private DanhSachNhanVien dsnhanvien;
    private PhanMemQLNhanVien app ;
    private NhanVien nhanVien1;
    private final String FILE_NAME = "src/main/resources/data/danhsachnhanvienTest.txt";

    public PMemQLNVAutomaticTestWithGUI() throws Exception {
    }


    @BeforeEach
    public void setUp() throws Exception {
        dsnhanvien = new DanhSachNhanVien();
        app = new PhanMemQLNhanVien(dsnhanvien,FILE_NAME);
        nhanVien1 = new NhanVien(1, "Nguyen", "A", true, 25, 5000.0);
    }

    // Test thêm nhân viên thành công
    @Test
    public void testThemNhanVien_ThanhCong() {
        app.txt_mnv.setText("1");
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên

        assertEquals(1, dsnhanvien.tong());
        assertEquals(nhanVien1.getManv(), dsnhanvien.getNhanVien(0).getManv());
    }
    @Test
    public void testThemNhanVien_TrungManv_Thatbai() {
        dsnhanvien.themnhanvien(nhanVien1); // thêm nhân viên ban đầu
        app.txt_mnv.setText("1"); // trùng mã
        app.txt_ho.setText("Pham");
        app.txt_tennv.setText("Van B");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên

        assertEquals(1, dsnhanvien.tong()); // ds nhân viên vẫn là 1
    }

    // Test xóa nhân viên thành công
    @Test
    public void testXoaNhanVien_ThanhCong() {
        app.txt_mnv.setText("1");
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick();
        app.table.setRowSelectionInterval(0, 0); // Chọn nhân viên đầu tiên
        app.btn_xoa.doClick(); // Xóa nhân viên

        assertEquals(0, dsnhanvien.tong()); // Kiểm tra nhân viên đã bị xóa
    }

    // Test xóa nhân viên khi không chọn
    @Test
    public void testXoaNhanVien_KhongChonNhanVien_ThatBai() {
        dsnhanvien.themnhanvien(nhanVien1);
        app.table.clearSelection(); // Không chọn nhân viên nào

        app.btn_xoa.doClick(); // Xóa

        assertEquals(1, dsnhanvien.tong()); // Không nhân viên nào bị xóa
    }
    @Test
    public void testXoaNhanVien_KhongXoa() {
        // Thiết lập dữ liệu nhân viên
        app.txt_mnv.setText("1");
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Thêm nhân viên vào danh sách

        // Chọn nhân viên đầu tiên
        app.table.setRowSelectionInterval(0, 0);

        app.btn_xoa.doClick(); // Gọi hành động xóa

        // Kiểm tra số lượng nhân viên trong danh sách
        assertEquals(1, dsnhanvien.tong()); // Số lượng nhân viên không thay đổi
    }

    // Test tìm kiếm nhân viên thành công
    @Test
    public void testTimKiemNhanVien_ThanhCong() {
        app.txt_mnv.setText("1");
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick();
        app.txt_tim.setText("1");
        app.btn_tim.doClick(); // Tìm kiếm

        assertEquals(1, app.table.getSelectedRow() + 1); //Kiểm tra nhân viên đã được chọn
    }

    // Test tìm kiếm nhân viên không thành công
    @Test
    public void testTimKiemNhanVien_KhongTimThay() {
        dsnhanvien.themnhanvien(nhanVien1);
        app.txt_tim.setText("2");
        app.btn_tim.doClick(); // Tìm kiếm

        assertEquals(-1, app.table.getSelectedRow()); // Không có nhân viên nào được chọn
    }

    // test khi ấn vào dòng trên bảng thì các txt cũng fill out ra
    @Test
    public void testMouseClicked_HienThiDuLieuTrenForm() {
        // Set up bảng với một hàng dữ liệu
        String[] rowData = {"1", "Nguyen", "A", "true", "25", "5000"};
        app.tableModel.addRow(rowData);

        // Giả lập chọn hàng trong bảng
        app.table.setRowSelectionInterval(0, 0);

        // Gọi phương thức mouseClicked
        app.mouseClicked(null); // Có thể truyền vào sự kiện null nếu không dùng tới.

        // Kiểm tra dữ liệu đã được hiển thị đúng trên form
        assertEquals("1", app.txt_mnv.getText());
        assertEquals("Nguyen", app.txt_ho.getText());
        assertEquals("A", app.txt_tennv.getText());
        assertTrue(app.nu.isSelected());
        assertEquals("25", app.txt_tuoi.getText());
        assertEquals("5000", app.txt_luong.getText());
    }

    // test xóa trắng tất cả các trường input
    @Test
    public void testXoaTrangAction_XoaTatCaDuLieuTrenForm() {
        // Giả lập điền dữ liệu vào form
        app.txt_mnv.setText("1");
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);

        // Gọi phương thức xóa trắng
        app.xoatrangAction();

        // Kiểm tra tất cả các trường đã được xóa sạch
        assertEquals("", app.txt_mnv.getText());
        assertEquals("", app.txt_ho.getText());
        assertEquals("", app.txt_tennv.getText());
        assertEquals("", app.txt_tuoi.getText());
        assertEquals("", app.txt_luong.getText());
        assertFalse(app.nu.isSelected());
    }


    @Test
    public void testValidInputForMaNV() {
        app.txt_mnv.setText("1"); // Mã nhân viên hợp lệ
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);

        // Gọi phương thức thêm nhân viên
        app.btn_them.doClick();

        // Kiểm tra xem nhân viên đã được thêm thành công
        assertEquals(1, dsnhanvien.tong()); // Kiểm tra số lượng nhân viên
    }
    @Test
    public void testInValidInputForMaNV() {
        // Test trường hợp mã nhân viên không phải số
        app.txt_mnv.setText("abc"); // Mã nhân viên không hợp lệ
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên

        // Kiểm tra xem không có nhân viên nào được thêm vào
        assertEquals(0, dsnhanvien.tong()); // Số lượng nhân viên vẫn là 0

        // Test trường hợp mã nhân viên nằm ngoài khoảng cho phép
        app.txt_mnv.setText("101"); // Mã nhân viên không hợp lệ
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên

        // Kiểm tra xem không có nhân viên nào được thêm vào
        assertEquals(0, dsnhanvien.tong()); // Số lượng nhân viên vẫn là 0

        // Test trường hợp mã nhân viên âm
        app.txt_mnv.setText("-1"); // Mã nhân viên không hợp lệ
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên

        // Kiểm tra xem không có nhân viên nào được thêm vào
        assertEquals(0, dsnhanvien.tong()); // Số lượng nhân viên vẫn là 0
    }

    @Test
    public void testValidInputForHo() {
        app.txt_mnv.setText("2");
        app.txt_ho.setText("Nguyen"); // Họ hợp lệ
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên

        assertEquals("Nguyen", dsnhanvien.getNhanVien(0).getHonv()); // Kiểm tra họ
    }

    @Test
    public void testInValidInputForHo() {
        app.txt_mnv.setText("2");
        app.txt_ho.setText(""); // Họ không hợp lệ (trống)
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên

        assertEquals(0, dsnhanvien.tong()); // Không có nhân viên nào được thêm vào
    }


    @Test
    public void testValidInputForTenNV() {
        app.txt_mnv.setText("3");
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A"); // Tên hợp lệ
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên

        assertEquals(1, dsnhanvien.tong()); // Kiểm tra số lượng nhân viên
        assertEquals("A", dsnhanvien.getNhanVien(0).getTennv()); // Kiểm tra tên
    }

    @Test
    public void testInValidInputForTenNV() {
        app.txt_mnv.setText("3");
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText(""); // Tên không hợp lệ (trống)
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên



        assertEquals(0, dsnhanvien.tong()); // Không có nhân viên nào được thêm vào
    }

    @Test
    public void testValidInputForTuoi() {
        app.txt_mnv.setText("4");
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25"); // Tuổi hợp lệ
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên

        assertEquals(1, dsnhanvien.tong()); // Kiểm tra số lượng nhân viên
        assertEquals(25, dsnhanvien.getNhanVien(0).getTuoinv()); // Kiểm tra tuổi
    }

    @Test
    public void testInValidInputForTuoi() {
        app.txt_mnv.setText("4");
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("-1"); // Tuổi không hợp lệ (âm)
        app.txt_luong.setText("5000");
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên


        assertEquals(0, dsnhanvien.tong()); // Không có nhân viên nào được thêm vào
    }

    @Test
    public void testValidInputForLuong() {
        app.txt_mnv.setText("5");
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000"); // Lương hợp lệ
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên


        assertEquals(1, dsnhanvien.tong()); // Kiểm tra số lượng nhân viên
        assertEquals(5000.0, dsnhanvien.getNhanVien(0).getTienluong()); // Kiểm tra lương
    }

    @Test
    public void testInValidInputForLuong() {
        app.txt_mnv.setText("5");
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("-5000"); // Lương không hợp lệ (âm)
        app.nu.setSelected(true);
        app.btn_them.doClick(); // Gọi phương thức thêm nhân viên

        assertEquals(0, dsnhanvien.tong()); // Không có nhân viên nào được thêm vào
    }

    @Test
    public void testLuuDuLieu_ThanhCong() throws Exception {
        app.txt_mnv.setText("9");
        app.txt_ho.setText("Nguyen");
        app.txt_tennv.setText("A");
        app.txt_tuoi.setText("25");
        app.txt_luong.setText("5000");
    
        app.nu.setSelected(true);

        // Thêm nhân viên
        app.btn_them.doClick(); // Nhấn nút thêm

        // Nhấn nút lưu
        app.btn_luu.doClick(); // Gọi phương thức lưu

        // Kiểm tra file đã tồn tại
        File file = new File(FILE_NAME);
        assertTrue(file.exists()); // Kiểm tra file đã tồn tại

        // Kiểm tra nội dung của file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String firstLine = br.readLine(); // Đọc dòng dữ liệu đầu tiên
            assertNotNull(firstLine); // Dòng dữ liệu không null
        } catch (Exception e) {
            fail("Có lỗi xảy ra khi đọc file");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Xóa dữ liệu trong file test sau mỗi bài kiểm tra
        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();// Xóa file để làm sạch dữ liệu
        }

    }



}

