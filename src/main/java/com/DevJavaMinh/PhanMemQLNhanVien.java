package com.DevJavaMinh;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PhanMemQLNhanVien extends JFrame implements ActionListener, MouseListener{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public DanhSachNhanVien dsnhanvien;
    public List<NhanVien> list;
    public JTable table;
    public JTextField txt_mnv;
    public JTextField txt_ho;
    public JTextField txt_tennv;
    public JTextField txt_tuoi;
    public JRadioButton nu;
    public JTextField txt_luong;
    public JTextField txt_tim;
    public JButton btn_tim;
    public JButton btn_them;
    public JButton btn_xoa_trang;
    public JButton btn_xoa;
    public JButton btn_luu;
    public DefaultTableModel tableModel;
    public FileDocGhi fi;
    private String tenfile;
    public PhanMemQLNhanVien(DanhSachNhanVien dsnhanvien, String tenfile) throws Exception {
        super("^_^");
        this.tenfile = tenfile;
        setList(dsnhanvien.getlist());
        // Đọc dữ liệu từ tệp khi khởi động chương trình
        fi = new FileDocGhi();
        try {
            dsnhanvien = (DanhSachNhanVien) fi.Readfromfile(tenfile);
        } catch (Exception e) {
        }
        this.dsnhanvien = dsnhanvien;


        //PNorth
        JPanel PNorth = new JPanel();
        Font font = new Font("new time roman", Font.BOLD, 30);
        JLabel label = new JLabel("Thông Tin Nhân Viên");
        label.setFont(font);
        label.setForeground(Color.BLUE);
        PNorth.add(label);

        //PCenter
        JPanel PCenter = new JPanel();
        JLabel label_mnv = new JLabel("mã nhân viên:");
        txt_mnv = new JTextField(60);
        JLabel label_ho = new JLabel("họ?:");
        txt_ho = new JTextField();
        JLabel label_tennv = new JLabel("tên nhân viên:");
        txt_tennv = new JTextField();
        JLabel label_tuoi = new JLabel("tuổi:");
        txt_tuoi = new JTextField(30);
        JLabel label_phai = new JLabel("phái");
        nu = new JRadioButton("nữ");
        JLabel label_luong = new JLabel("tiền lương:");
        txt_luong = new JTextField();

        Box b1 = Box.createHorizontalBox();
        Box b2 = Box.createHorizontalBox();
        Box b3 = Box.createHorizontalBox();
        Box b4 = Box.createHorizontalBox();
        Box b5 = Box.createHorizontalBox();

        // B1: Mã nhân viên
        b1.add(label_mnv);
        b1.add(Box.createHorizontalStrut(10));
        b1.add(txt_mnv);

        // B2: H�? và tên nhân viên
        b2.add(label_ho);
        b2.add(txt_ho);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(label_tennv);
        b2.add(txt_tennv);

        // B3: Tuổi và phái
        b3.add(label_tuoi);
        b3.add(txt_tuoi);
        b3.add(Box.createHorizontalStrut(20));
        b3.add(label_phai);
        b3.add(nu);

        // B4: Ti�?n lương
        b4.add(label_luong);
        b4.add(txt_luong);

        label_ho.setPreferredSize(label_mnv.getPreferredSize());
        label_phai.setPreferredSize(label_mnv.getPreferredSize());
        label_luong.setPreferredSize(label_mnv.getPreferredSize());
        label_tuoi.setPreferredSize(label_mnv.getPreferredSize());
        //B5: BẢNG
        String[] header = "Mã NV; Họ;Tên;Phái;Tuổi;Tiền lương".split(";");
        tableModel = new DefaultTableModel(header, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, table.getRowHeight() * 7));
        b5.add(scrollPane);



        Box box = Box.createVerticalBox();
        box.add(b1);
        box.add(Box.createVerticalStrut(15));
        box.add(b2);
        box.add(Box.createVerticalStrut(15));
        box.add(b3);
        box.add(Box.createVerticalStrut(15));
        box.add(b4);
        box.add(Box.createVerticalStrut(15));
        box.add(b5);

        // ...

        PCenter.add(box);


        //Psouth
        // Tạo JPanel PSouth
        JPanel PSouth = new JPanel();

        //  JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        //leftpanel
        JPanel leftPanel = new JPanel();
        JLabel label_tim = new JLabel("nhập mã số cần tìm");
        txt_tim = new JTextField(10);
        btn_tim = new JButton("tìm");
        leftPanel.add(label_tim);
        leftPanel.add(txt_tim);
        leftPanel.add(btn_tim);
        //rightpanel
        JPanel rightPanel = new JPanel();
        btn_them = new JButton("thêm");
        btn_xoa_trang = new JButton("xóa trắng");
        btn_xoa = new JButton("xóa");
        btn_luu = new JButton("lưu");
        rightPanel.add(btn_them);
        rightPanel.add(btn_xoa_trang);
        rightPanel.add(btn_xoa);
        rightPanel.add(btn_luu);

        //setleftright
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);

        // themsu kien
        btn_them.addActionListener(this);
        btn_xoa.addActionListener(this);
        btn_xoa_trang.addActionListener(this);
        btn_tim.addActionListener(this);
        btn_luu.addActionListener(this);
        table.addMouseListener(this);

        // add
        PSouth.add(splitPane);
        //add in frame
        add(PNorth,BorderLayout.NORTH);
        add(PCenter,BorderLayout.CENTER);
        add(PSouth,BorderLayout.SOUTH);
        setSize(780,480);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        // Hiển thị dữ liệu lên bảng
        hientable();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object source = e.getSource();
        if(source.equals(btn_them))
            themAction();
        if(source.equals(btn_xoa))
            xoaAction();
        if(source.equals(btn_xoa_trang))
            xoatrangAction();
        if(source.equals(btn_tim))
            timAction();
        if(source.equals(btn_luu)) {
            fi = new FileDocGhi();
            try {
                fi.writeToFile(dsnhanvien, tenfile);
                JOptionPane.showMessageDialog(null, "Dữ liệu đã được lưu thành công.");
            } catch(Exception e1) {
                JOptionPane.showMessageDialog(null, "Có lỗi khi lưu dữ liệu. Vui lòng kiểm tra lại.");
                e1.printStackTrace();
            }
        }



    }

    public void hientable() {
        for(int i =0;i<dsnhanvien.tong();i++) {
            NhanVien nv = dsnhanvien.getNhanVien(i);
            String[] dataRow = {nv.getManv()+"",nv.getHonv(),nv.getTennv(),Boolean.toString(nv.isPhainv()),nv.getTuoinv()+"",nv.getTienluong()+""};
            tableModel.addRow(dataRow);


        }
    }
    private boolean kiemTraDuLieu() {
        try {
            // Kiểm tra mã nhân viên phải là số từ 0 đến 100
            int mnv = Integer.parseInt(txt_mnv.getText());
            if (mnv < 0 || mnv > 100) {
                JOptionPane.showMessageDialog(null, "Mã nhân viên phải từ 0 đến 100");
                txt_mnv.selectAll();
                txt_mnv.requestFocus();
                return false;
            }

            // Kiểm tra họ không được rỗng
            String ho = txt_ho.getText();
            if (ho == null || ho.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Họ không được để trống");
                txt_ho.requestFocus();
                return false;
            }

            // Kiểm tra tên không được rỗng
            String ten = txt_tennv.getText();
            if (ten == null || ten.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tên không được để trống");
                txt_tennv.requestFocus();
                return false;
            }

            // Kiểm tra tuổi phải là số từ 18 đến 50
            int tuoi = Integer.parseInt(txt_tuoi.getText());
            if (tuoi < 18 || tuoi > 50) {
                JOptionPane.showMessageDialog(null, "Tuổi phải từ 18 đến 50");
                txt_tuoi.selectAll();
                txt_tuoi.requestFocus();
                return false;
            }

            // Kiểm tra lương phải là số và không được rỗng
            double tienluong = Double.parseDouble(txt_luong.getText());
            if (tienluong < 0 || txt_luong.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lương không được để trống\n" +
                        "Và lớn hơn 0");

                txt_luong.requestFocus();
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng cho các trường số");
            return false;
        }
        return true;
    }
    private void themAction() {
        if (kiemTraDuLieu()) {
            try {
                int mnv = Integer.parseInt(txt_mnv.getText());
                String ho = txt_ho.getText();
                String ten = txt_tennv.getText();
                boolean phai = (nu.isSelected()) ? true : false;
                int tuoi = Integer.parseInt(txt_tuoi.getText());
                double tienluong = Double.parseDouble(txt_luong.getText());
                NhanVien nv = new NhanVien(mnv, ho, ten, phai, tuoi, tienluong);
                if (dsnhanvien.themnhanvien(nv)) {
                    String[] row = {Integer.toString(mnv), ho, ten, Boolean.toString(phai), Integer.toString(tuoi), tienluong + ""};
                    tableModel.addRow(row);
                    JOptionPane.showMessageDialog(null, "Thêm thành công nhân viên");
                    xoatrangAction();
                } else {
                    JOptionPane.showMessageDialog(null, "Trùng mã nhân viên");
                    txt_mnv.selectAll();
                    txt_mnv.requestFocus();
                }
            } catch (Exception ex) {
                return;
            }
        }
    }
    public void xoatrangAction() {
        txt_mnv.setText("");
        txt_ho.setText("");
        txt_tennv.setText("");
        txt_tuoi.setText("");
        txt_tim.setText("");
        txt_luong.setText("");
        nu.setSelected(false);
        txt_mnv.requestFocus();
    }
    private void xoaAction() {
        int row = table.getSelectedRow();
        if(row != -1) {
            int mnv = Integer.parseInt((String) table.getModel().getValueAt(row, 0));
            int hoi = JOptionPane.showConfirmDialog(this, "có chắc chắn xóa nhân viên này?", "waring",JOptionPane.YES_NO_OPTION);
            if(hoi == JOptionPane.YES_OPTION) {
                if (dsnhanvien.xoanhanvien(mnv))
                    tableModel.removeRow(row);
                JOptionPane.showMessageDialog(null, "xóa thành công nhân viên");
            }
        }else {
            JOptionPane.showMessageDialog(this, "hãy chọn nhân viên trên bảng để xóa !!!");
        }
    }
    private void timAction() {
        try {
            int mnv = Integer.parseInt(txt_tim.getText());
            NhanVien nv = dsnhanvien.timkiem(mnv);
            if (nv != null) {
                int row = list.indexOf(nv);
                table.setRowSelectionInterval(row, row);
                JOptionPane.showMessageDialog(null, "đã tìm thấy nhân viên");
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên có mã " + mnv);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Nhập mã số hợp lệ để tìm kiếm.");
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        int row = table.getSelectedRow();
        txt_mnv.setText(table.getValueAt(row, 0).toString());
        txt_ho.setText(table.getValueAt(row, 1).toString());
        txt_tennv.setText(table.getValueAt(row, 2).toString());
        nu.setSelected(false);
        if(table.getValueAt(row, 3).toString().equalsIgnoreCase("true"))
            nu.setSelected(true);
        txt_tuoi.setText(table.getValueAt(row, 4).toString());
        txt_luong.setText(table.getValueAt(row, 5).toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    public void setList(List<NhanVien> list) {
        this.list = list;
    }

}