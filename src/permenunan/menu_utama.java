/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package permenunan;
// import java.beans.Statement;

import com.mysql.cj.protocol.Resultset;
// import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import perpluginan.koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.lang.model.util.SimpleAnnotationValueVisitor14;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * _____ _ _ _ |_ _| _ __(_) |_ _ _| |__ | || ' \ _ (_-< | ' \ || | '_ \
 * |_||_||_(_) /__/_|_||_\_,_|_.__/ sihub | 03 - 27 - 2023
 */
public class menu_utama extends javax.swing.JFrame {

    /**
     * Creates new form menu_awal
     */
    /**
     * Creates new form App1
     */
    PreparedStatement pst;
    String sql;//digunakan untuk menampung sql
    Statement s;//digunakan untuk eksekusi sql
    ResultSet rs;//digunakan untuk menampung hasil eksekusi sql
    Connection con;
    String[] data;
    String Tanggal; // gawe tanggalan
    DefaultTableModel model;

    public menu_utama() {
        initComponents();
        totalBarang();
        totalPendapatan();
        totalTerjual();
        /* --------------------- PERBARANGAN ---------------------*/
        this.setLocationRelativeTo(null);

        model = new DefaultTableModel();
        jTable_perbarangan2.setModel(model);

        model.addColumn("id_barang");
        model.addColumn("nama_barang");
        model.addColumn("harga_barang");
        model.addColumn("satuan");
        model.addColumn("stock");

        loadDataPerbarangan();
        auto_idbarang();
        btn_update.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_refresh.setEnabled(false);
        /* --------------------- PERBARANGAN END ---------------------*/
        data = new String[6];
        con = perpluginan.koneksi.getConnection();
        // con = new Koneksi().getConnect();
        model = (DefaultTableModel) jTable1_riwayat.getModel();
        model = (DefaultTableModel) Table_DisplayUser.getModel();
        model = (DefaultTableModel) jTable_perbarangan.getModel();
        model = (DefaultTableModel) jTable_perbarangan2.getModel();
        utamaPerkasiran();
        auto_idbarang();
        totalStock();
        loadDataRiwayat();
        loadDataUser();//panggil method loadData
        loadDataBarang();//panggil method loadData
        loadDataBarang2();
    }

    /* ------------------------------------------------ PENCARIAN ---------------------------------------*/
    void mencari_bayanganmu() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID");
        tbl.addColumn("Firstname");
        tbl.addColumn("Lastname");
        tbl.addColumn("Username");
        tbl.addColumn("Role");
        try {
            String sql = "SELECT * FROM perloginan WHERE username like '%" + jTextField_cari.getText() + "%'" + "OR " + "id='" + jTextField_cari.getText() + "'";
            Connection con = (Connection) perpluginan.koneksi.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("ID"),
                    rs.getString("Firstname"),
                    rs.getString("Lastname"),
                    rs.getString("Username"),
                    rs.getString("Role")
                });
                Table_DisplayUser.setModel(tbl);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error !" + e.getMessage());
        }
    }

    /* fungsi hapus agar setelah simpan data textfield dikosongi lur */
    void hapus() {
        jTextField_firstname.setText("");
        jTextField_lastname.setText("");
        jTextField_username.setText("");
        jPasswordField_password.setText("");
    }

    /* load data barang ke tabel */
    public void loadDataBarang() {
        String sql = "SELECT * FROM perbarangan";
        try {
            con = perpluginan.koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable_perbarangan.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }

        } catch (Exception e) {
        }
    }

    public void loadDataBarang2() {
        String sql = "SELECT * FROM perbarangan";
        try {
            con = perpluginan.koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable_perbarangan2.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }
        } catch (Exception e) {
        }
    }

    /* ------------------------------------------------ PERKASIRAN -------------------------------------------- */
    public void loadDataPerkasiran() {
        // get text gawe input data ndek tabel perkasiran
        DefaultTableModel model = (DefaultTableModel) jTable2_input.getModel();
        model.addRow(new Object[]{
            // jLabel31_nofak.getText(),
            jTextField2_idbrg.getText(),
            jTextField3_namabrg.getText(),
            jLabel31_satuan.getText(),
            jLabel31_harga.getText(),
            jTextField5_jmlbeli.getText(),
            jTextField6_totalharga.getText() // total biaya bisa jadi
        });
    }

    public void kosongPerkasiran() {
        DefaultTableModel model = (DefaultTableModel) jTable2_input.getModel();

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }

    public void utamaPerkasiran() {
        jLabel31_nofak.getText();
        jTextField2_idbrg.getText();
        jTextField3_namabrg.getText();
        jTextField5_jmlbeli.getText();
        jLabel31_harga.getText();
        auto_idstruk();
    }

    public void clearPerkasiran() {
        jTextField9_inputbayar.setText("0");
        jTextField10_kembalian.setText("0");
        jTextField6_totalharga.setText("0");
    }

    public void clearPerkasiran2() {
        jTextField2_idbrg.getText();
        jTextField3_namabrg.getText();
        jTextField5_jmlbeli.getText();
        jLabel31_harga.getText();
    }

    /* load data riwayat */
    public void loadDataRiwayat() {
        String sql = "SELECT * FROM penjualan_rinci";
        try {
            con = perpluginan.koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1_riwayat.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(5)});
            }
        } catch (Exception e) {
        }
    }

    /* load data tabel perloginan / user */
    public void loadDataUser() {
        String sql = "SELECT * FROM perloginan";
        try {
            con = perpluginan.koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) Table_DisplayUser.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }
        } catch (Exception e) {
        }
    }

    /* ------------------------------------------------ PERBARANGAN ------------------------------------------------------*/
    private void auto_idbarang() {
        try {
            Connection c = koneksi.getConnection();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM perbarangan ORDER BY id_barang DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String NoFaktur = r.getString("id_barang").substring(2);
                String BR = "" + (Integer.parseInt(NoFaktur) + 1);
                String Nol = "";

                if (BR.length() == 1) {
                    Nol = "00";
                } else if (BR.length() == 2) {
                    Nol = "0";
                } else if (BR.length() == 3) {
                    Nol = "";
                }

                jTextField2_idbrg1.setText("BR" + Nol + BR);
            } else {
                jTextField2_idbrg1.setText("BR001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("[ERROR] auto_idbarang " + e);
        }
    }

    public void clear_perbarangan() {
        jTextField2_idbrg1.setText("");
        jTextField4_namabrg.setText("");
        jLabel31_harga1.setText("");
//        txStok.setText("");
    }

    public void loadDataPerbarangan() {
        model.getDataVector().removeAllElements();

        model.fireTableDataChanged();

        try {
            Connection c = koneksi.getConnection();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM perbarangan";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[5];
                o[0] = r.getString("id_barang");
                o[1] = r.getString("nama_barang");
                o[2] = r.getString("harga_barang");
                o[3] = r.getString("satuan");
                o[4] = r.getString("stock");

                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("terjadi kesalahan");
        }
    }

    public void cariDataPerbarangan() {
        DefaultTableModel tabel = new DefaultTableModel();

        tabel.addColumn("ID Barang");
        tabel.addColumn("Nama");
        tabel.addColumn("Harga");
        tabel.addColumn("Satuan");
        tabel.addColumn("Stock");

        try {
            Connection c = koneksi.getConnection();
            String sql = "Select * from perbarangan where id_barang like '%" + jTextField_cari4_caridatabarang.getText() + "%'"
                    + "or nama_barang like '%" + jTextField_cari4_caridatabarang.getText() + "%'";
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),});
            }
            jTable_perbarangan2.setModel(tabel);
            loadDataUser();
        } catch (Exception e) {
            System.out.println("Cari Data Error");
        } finally {
        }
    }

    public void cariDataPerbarangan2() {
        DefaultTableModel tabel = new DefaultTableModel();

        tabel.addColumn("ID Barang");
        tabel.addColumn("Nama");
        tabel.addColumn("Harga");
        tabel.addColumn("Satuan");
        tabel.addColumn("Stock");

        try {
            Connection c = koneksi.getConnection();
            String sql = "Select * from perbarangan where id_barang like '%" + jTextField1_caridatabarang.getText() + "%'"
                    + "or nama_barang like '%" + jTextField1_caridatabarang.getText() + "%'";
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),});
            }
            jTable_perbarangan.setModel(tabel);
            loadDataUser();
        } catch (Exception e) {
            System.out.println("Cari Data Error");
        } finally {
        }
    }

    public void clearPerbarangan() {
        // jTextField2_idbrg1.setText("");
        // jTextField2_idbrg1.setText("");
        jTextField4_namabrg.setText("");
        jLabel31_harga1.setText("");
        jTextField4_stock.setText("");
    }

    /* ------------------------------------------------ PERKASIRAN ------------------------------------------------------*/
    public void totalBiaya() {
        int jumlahBaris = jTable2_input.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(jTable2_input.getValueAt(i, 3).toString()); // di tabel yang colom 4 dan 5
            hargaBarang = Integer.parseInt(jTable2_input.getValueAt(i, 4).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        jTextField6_totalharga.setText(String.valueOf(totalBiaya));
        // jTextField6_totalharga.setText("Rp " + totalBiaya + ",00");
        //
//        int jumlahBaris = jTable2_input.getRowCount();
//        int totalBiaya = 0;
//        int jumlahBarang, hargaBarang;
//        for (int i = 0; i < jumlahBaris; i++) {
//            jumlahBarang = Integer.parseInt(jTable2_input.getValueAt(i, 3).toString());
//            hargaBarang = Integer.parseInt(jTable2_input.getValueAt(i, 4).toString());
//            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
//        } // perhitungan total biaya dan segala macemnya
//        jTextField6_totalharga.setText(String.valueOf(totalBiaya));
//        jTextField6_totalharga.setText("Rp. " + totalBiaya + ",00");
    }

    /* ----------------------------------------- DASHBOARD TOTAL JUAL ----------------------------------*/
    public void execQueryTotalPendapatan(String query) {
        try {
            Connection con = koneksi.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                jLabel37_totpen.setText(Integer.toString(rs.getInt(1)));
            } else {
                System.out.println("no data");
            }

        } catch (Exception e) {
        }
    }

    public void totalPendapatan() {
        Connection c = koneksi.getConnection();
        try {
            String query = "SELECT SUM(total) FROM `penjualan` WHERE 1";
            execQueryTotalPendapatan(query);
        } catch (Exception e) {
            System.out.println("error" + e);
            e.printStackTrace();
        }
    }

    /* ----------------------------------------- DASHBOARD TOTAL JUAL ----------------------------------*/
    public void execQueryTotalJual(String query) {
        try {
            Connection con = koneksi.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                jLabel31_tojual.setText(Integer.toString(rs.getInt(1)));
            } else {
                System.out.println("no data");
            }

        } catch (Exception e) {
        }
    }

    public void totalTerjual() {
        Connection c = koneksi.getConnection();
        try {
            String query = "SELECT COUNT(*) FROM `penjualan` WHERE 1";
            execQueryTotalJual(query);
        } catch (Exception e) {
            System.out.println("error" + e);
            e.printStackTrace();
        }
    }

    /* ----------------------------------------- DASHBOARD TOTAL JUAL ----------------------------------*/
    public void execQueryTotalBarang(String query) {
        try {
            Connection con = koneksi.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                jLabel20_totbar.setText(Integer.toString(rs.getInt(1)));
            } else {
                System.out.println("no data");
            }

        } catch (Exception e) {
        }
    }

    public void totalBarang() {
        Connection c = koneksi.getConnection();
        try {
            String query = "SELECT COUNT(*) FROM `perbarangan` WHERE 1";
            execQueryTotalBarang(query);
        } catch (Exception e) {
            System.out.println("error" + e);
            e.printStackTrace();
        }
    }

    private void auto_idstruk() {
        try {
            Connection c = koneksi.getConnection();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM penjualan ORDER BY id_transaksi DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String NoFaktur = r.getString("id_transaksi").substring(2);
                String TR = "" + (Integer.parseInt(NoFaktur) + 1);
                String Nol = "";

                if (TR.length() == 1) {
                    Nol = "000";
                } else if (TR.length() == 2) {
                    Nol = "00";
                } else if (TR.length() == 3) {
                    Nol = "0";
                } else if (TR.length() == 4) {
                    Nol = "";
                }
                jLabel31_nofak.setText("TR" + Nol + TR);
            } else {
                jLabel31_nofak.setText("TR0001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("autonumber error" + e);
        }
    } //end autonumber

    /* data tabel kasiran */
    public void loadDataPenjualan() {
        DefaultTableModel model = (DefaultTableModel) jTable2_input.getModel();
        model.addRow(new Object[]{
            // jLabel31_nofak.getText(), // bisa jadi ga dipake soon !
            jTextField2_idbrg.getText(),
            jTextField3_namabrg.getText(),
            jLabel31_satuan.getText(),
            // jTextField4_stok.getText(),
            jLabel31_harga.getText(),
            jTextField5_jmlbeli.getText()

        });
    }

    /* data kosong kasiran */
    public void clearKasir() {
        DefaultTableModel model = (DefaultTableModel) jTable2_input.getModel();
        jLabel31_nofak.setText("");
        jTextField2_idbrg.setText("");
        jTextField3_namabrg.setText("");
        jTextField4_stok.setText("");
        jTextField5_jmlbeli.setText("");
        jLabel31_harga.setText("");
        jLabel31_satuan.setText("");
        jTextField6_totalharga.setText("0");
        jTextField9_inputbayar.setText("0");
        jTextField10_kembalian.setText("0");

    }

    public void clearKasir2() {
        jLabel31_nofak.setText("");
        jTextField2_idbrg.setText("");
        jTextField3_namabrg.setText("");
        jTextField4_stok.setText("0");
        jTextField5_jmlbeli.setText("0");

    }

    public void tambahTransaksiPerkasiran() {
//        int jumlah, harga, total;
//
//        jumlah = Integer.valueOf(jTextField5_jmlbeli.getText());
//        harga = Integer.valueOf(jLabel31_harga.getText());
//        total = jumlah * harga;
//
//        jTextField6_totalharga.setText(String.valueOf(total));
//
//        loadDataPerkasiran();
//        totalBiaya();
//        clearPerkasiran2();
//        jTextField2_idbrg.requestFocus();
        //
        int jumlah, harga, total;

        jumlah = Integer.valueOf(jTextField5_jmlbeli.getText());
        harga = Integer.valueOf(jLabel31_harga.getText());
        total = jumlah * harga;

        jTextField6_totalharga.setText(String.valueOf(total));

        loadDataPerkasiran();
        totalBiaya();
        clearPerkasiran2();
        jTextField2_idbrg.requestFocus();
    }

    /* clear data tabel perkasiran */

 /* dashboard total stock */
    public void totalStock() {
//        Koneksi connect = new Koneksi();
//        connect.koneksi();
        con = perpluginan.koneksi.getConnection();

        try {
            String sql = "SELECT COUNT(stock) FROM perbarangan;";
            PreparedStatement pst = con.prepareStatement(sql);
            // pst = connect.con.prepareStatement(sql);
            rs = pst.executeQuery(sql);

            rs.next();

            jLabel31_tojual.setText(String.valueOf(rs.getString(1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public menu_utama(String Uname) {
        initComponents();
        // jLabel1.setText(Uname);
        /* urusan perbarangan */
        totalBarang();
        totalTerjual();
        totalPendapatan();
        loadDataBarang();
        loadDataBarang2();
        auto_idbarang();
        btn_update.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_batal.setEnabled(false);
        /* urursan perkasiran */
        model = new DefaultTableModel();
        auto_idstruk();
        jTable2_input.setModel(model);
        model.addColumn("ID Brg");
        model.addColumn("Nama Brg");
        model.addColumn("Satuan");
        model.addColumn("Harga");
        model.addColumn("jumlah");
        model.addColumn("Total");
        utamaPerkasiran();

//        model.addColumn("Total");
//        model.addColumn("No");
// --------------------- skip dulu tanggalan gk guna 
//        utama(); 
//        Date date_sakkarepmu = new Date();
//        SimpleDateFormat s = new SimpleDateformat("dd-MM-yyyy");
        /* kondisi hak akses */
        if (role.equalsIgnoreCase("admin")) {
            tombolAdmin();
        } else {
            tombolKasir();
        }
    }
    /* penyelukan hak akses */
    private String firstname = perpluginan.akses.getFirstname();
    private String lastname = perpluginan.akses.getLastname();
    private String username = perpluginan.akses.getUsername();
    private String password = perpluginan.akses.getPassword();
    private String role = perpluginan.akses.getRole();

    /* mendeklarasikan tombol admin */
    private void tombolAdmin() {
        iniBeranda.setEnabled(true);
        iniBarang.setEnabled(true);
        iniUser.setEnabled(true);
        iniKasir.setEnabled(true);
        iniRiwayat.setEnabled(true);
        iniKeluar.setEnabled(true);
    }

    /* mendeklarasikan tombol admin */
    private void tombolKasir() {
        iniBeranda.setEnabled(true);
        iniBarang.setEnabled(false);
        iniUser.setEnabled(false);
        iniKasir.setEnabled(true);
        iniRiwayat.setEnabled(true);
        iniKeluar.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        iniBeranda = new javax.swing.JButton();
        iniUser = new javax.swing.JButton();
        iniKasir = new javax.swing.JButton();
        iniRiwayat = new javax.swing.JButton();
        iniKeluar = new javax.swing.JButton();
        iniBarang = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pnBeranda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel20_totbar = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31_tojual = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel37_totpen = new javax.swing.JLabel();
        jLabel37_totpen1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        pnBarang = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jTextField_cari4_caridatabarang = new javax.swing.JTextField();
        jButton7_caribarang = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jTextField2_idbrg1 = new javax.swing.JTextField();
        jTextField4_namabrg = new javax.swing.JTextField();
        jLabel31_harga1 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable_perbarangan2 = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jComboBox_satuan = new javax.swing.JComboBox<>();
        btn_refresh = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jTextField4_stock = new javax.swing.JTextField();
        btn_batal = new javax.swing.JToggleButton();
        pnUser = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_cari = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField_firstname = new javax.swing.JTextField();
        jTextField_lastname = new javax.swing.JTextField();
        jTextField_username = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_DisplayUser = new javax.swing.JTable();
        jButton_tambah = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jComboBox_role = new javax.swing.JComboBox<>();
        jPasswordField_password = new javax.swing.JPasswordField();
        jTextField_useird = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton_tambah1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        pnKasir = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_perbarangan = new javax.swing.JTable();
        jTextField1_caridatabarang = new javax.swing.JTextField();
        jButton1_caribarang = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton3_tambah = new javax.swing.JButton();
        jTextField2_idbrg = new javax.swing.JTextField();
        jTextField4_stok = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2_input = new javax.swing.JTable();
        jTextField5_jmlbeli = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField6_totalharga = new javax.swing.JTextField();
        jTextField9_inputbayar = new javax.swing.JTextField();
        jTextField10_kembalian = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jTextField3_namabrg = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel31_harga = new javax.swing.JTextField();
        jLabel31_satuan = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel31_nofak = new javax.swing.JTextField();
        jButton5_hapus = new javax.swing.JButton();
        pnRiwayat = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1_riwayat = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Warmed.in App ");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        iniBeranda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        iniBeranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/diagram-06_24511 (1).png"))); // NOI18N
        iniBeranda.setText("Beranda");
        iniBeranda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        iniBeranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniBerandaActionPerformed(evt);
            }
        });

        iniUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        iniUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/business_application_addmale_useradd_insert_add_user_client_2312 (1).png"))); // NOI18N
        iniUser.setText("User");
        iniUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        iniUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniUserActionPerformed(evt);
            }
        });

        iniKasir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        iniKasir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shopping_cart_caddy_ecommerce_store_icon_185958 (1).png"))); // NOI18N
        iniKasir.setText("Kasir");
        iniKasir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        iniKasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniKasirActionPerformed(evt);
            }
        });

        iniRiwayat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        iniRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cab_history_archive_archives_7219 (1).png"))); // NOI18N
        iniRiwayat.setText("Riwayat");
        iniRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        iniRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniRiwayatActionPerformed(evt);
            }
        });

        iniKeluar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        iniKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/log_logout_door_1563 (1).png"))); // NOI18N
        iniKeluar.setText("Keluar");
        iniKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        iniKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniKeluarActionPerformed(evt);
            }
        });

        iniBarang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        iniBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/businesspackage_additionalpackage_box_add_insert_negoci_2335 (1).png"))); // NOI18N
        iniBarang.setText("Barang");
        iniBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        iniBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iniBeranda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iniUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iniRiwayat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iniKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iniKasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iniBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(iniBeranda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iniBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iniUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iniKasir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iniRiwayat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iniKeluar)
                .addGap(30, 30, 30))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.CardLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Warmed's Community App");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("In Warmed we trust, no Warmed no talk !");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("created by :");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("- muhammad izzul haq syihabuddin sanni");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("- muhammad deru anggoro damar aji");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Total Barang");

        jLabel20_totbar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20_totbar.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20_totbar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20_totbar)
                .addGap(36, 36, 36)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Total Barang Terjual");

        jLabel31_tojual.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel31_tojual.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addComponent(jLabel31_tojual, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31_tojual)
                .addGap(41, 41, 41)
                .addComponent(jLabel30)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Total Penjualan");

        jLabel37_totpen.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel37_totpen.setText("0");

        jLabel37_totpen1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel37_totpen1.setText("Rp.");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel37_totpen1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37_totpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37_totpen)
                    .addComponent(jLabel37_totpen1))
                .addGap(35, 35, 35)
                .addComponent(jLabel32)
                .addContainerGap())
        );

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Special Thanks to our lecturer Annisa Dzulkarnain :)");

        javax.swing.GroupLayout pnBerandaLayout = new javax.swing.GroupLayout(pnBeranda);
        pnBeranda.setLayout(pnBerandaLayout);
        pnBerandaLayout.setHorizontalGroup(
            pnBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBerandaLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(pnBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnBerandaLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(pnBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnBerandaLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        pnBerandaLayout.setVerticalGroup(
            pnBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBerandaLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(pnBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(45, 45, 45)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        jPanel3.add(pnBeranda, "card2");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Cari user dengan kode atau dengan nama user :");

        jTextField_cari4_caridatabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_cari4_caridatabarangActionPerformed(evt);
            }
        });

        jButton7_caribarang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7_caribarang.setText("cari");
        jButton7_caribarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7_caribarangActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("ID Barang");

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel74.setText("Nama Barang");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel75.setText("Harga");

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setText("Stock");

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel77.setText("Satuam");

        jTextField2_idbrg1.setEditable(false);
        jTextField2_idbrg1.setEnabled(false);
        jTextField2_idbrg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2_idbrg1ActionPerformed(evt);
            }
        });

        jTable_perbarangan2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_perbarangan2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Nama Brg", "Harga ", "Satuan", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_perbarangan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_perbarangan2MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable_perbarangan2);
        if (jTable_perbarangan2.getColumnModel().getColumnCount() > 0) {
            jTable_perbarangan2.getColumnModel().getColumn(0).setMaxWidth(70);
            jTable_perbarangan2.getColumnModel().getColumn(3).setMaxWidth(60);
            jTable_perbarangan2.getColumnModel().getColumn(4).setMaxWidth(60);
        }

        btn_tambah.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/businesspackage_additionalpackage_box_add_insert_negoci_2335 (1).png"))); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tambahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_tambahMouseEntered(evt);
            }
        });
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/generatetables_ok_home_out_edit_generar_1491 (1).png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateMouseClicked(evt);
            }
        });
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/recycling_recyclebin_empty_delete_trash_1771 (1).png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        jComboBox_satuan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_satuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Satuan", "Pcs", "Box" }));

        btn_refresh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_refresh_15732 (1).png"))); // NOI18N
        btn_refresh.setText("Refresh");
        btn_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_refreshMouseClicked(evt);
            }
        });
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel79.setBackground(new java.awt.Color(204, 204, 204));
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("PENTING !");
        jLabel79.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel80.setBackground(new java.awt.Color(255, 0, 0));
        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel80.setText("Gunakan tombol refresh di pojok kiri bawah untuk");

        jLabel81.setBackground(new java.awt.Color(255, 0, 0));
        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel81.setText("menerapkan update data pada tabel, apabila data");

        jLabel82.setBackground(new java.awt.Color(255, 0, 0));
        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel82.setText("pada tabel tidak up to date.");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel82)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_batal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_batal.setText("BATAL");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnBarangLayout = new javax.swing.GroupLayout(pnBarang);
        pnBarang.setLayout(pnBarangLayout);
        pnBarangLayout.setHorizontalGroup(
            pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBarangLayout.createSequentialGroup()
                        .addComponent(btn_refresh)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnBarangLayout.createSequentialGroup()
                        .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnBarangLayout.createSequentialGroup()
                                .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField_cari4_caridatabarang))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7_caribarang)
                                .addGap(0, 67, Short.MAX_VALUE))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBarangLayout.createSequentialGroup()
                                .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnBarangLayout.createSequentialGroup()
                                            .addComponent(btn_tambah)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btn_update)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btn_delete)))
                                    .addGroup(pnBarangLayout.createSequentialGroup()
                                        .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField2_idbrg1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                .addComponent(jLabel31_harga1)
                                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField4_namabrg, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField4_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(30, 30, 30))
                            .addGroup(pnBarangLayout.createSequentialGroup()
                                .addComponent(jComboBox_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        pnBarangLayout.setVerticalGroup(
            pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_cari4_caridatabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7_caribarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnBarangLayout.createSequentialGroup()
                        .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel74))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2_idbrg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4_namabrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel75)
                            .addComponent(jLabel76))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31_harga1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_batal))
                        .addGap(16, 16, 16)
                        .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_tambah)
                            .addComponent(btn_delete)
                            .addComponent(btn_update))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_refresh)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.add(pnBarang, "card6");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Cari user dengan kode atau dengan nama user :");

        jTextField_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_cariActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("cari");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Firstname");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Lastname");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Username");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Password");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Role");

        jTextField_firstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_firstnameActionPerformed(evt);
            }
        });

        Table_DisplayUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Table_DisplayUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UID", "Firstname", "Lastname", "Username", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_DisplayUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_DisplayUserMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Table_DisplayUser);
        if (Table_DisplayUser.getColumnModel().getColumnCount() > 0) {
            Table_DisplayUser.getColumnModel().getColumn(0).setMaxWidth(70);
            Table_DisplayUser.getColumnModel().getColumn(3).setMaxWidth(60);
            Table_DisplayUser.getColumnModel().getColumn(4).setMaxWidth(60);
        }

        jButton_tambah.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/business_application_addmale_useradd_insert_add_user_client_2312 (1).png"))); // NOI18N
        jButton_tambah.setText("Tambah");
        jButton_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_tambahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_tambahMouseEntered(evt);
            }
        });
        jButton_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_tambahActionPerformed(evt);
            }
        });

        jButton_update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/businessapplication_edit_male_user_thepencil_theclient_negocio_2321 (1).png"))); // NOI18N
        jButton_update.setText("Update");
        jButton_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_updateMouseClicked(evt);
            }
        });
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/recycling_recyclebin_empty_delete_trash_1771 (1).png"))); // NOI18N
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jComboBox_role.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "kasir" }));

        jPasswordField_password.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTextField_useird.setEditable(false);
        jTextField_useird.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField_useird.setText("AUTO INCREMENT");
        jTextField_useird.setEnabled(false);
        jTextField_useird.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_useirdActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Userid");

        jButton_tambah1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_tambah1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_refresh_15732 (1).png"))); // NOI18N
        jButton_tambah1.setText("Refresh");
        jButton_tambah1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_tambah1MouseClicked(evt);
            }
        });
        jButton_tambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_tambah1ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setBackground(new java.awt.Color(204, 204, 204));
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("PENTING !");
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setBackground(new java.awt.Color(255, 0, 0));
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Gunakan tombol refresh di pojok kiri bawah untuk");

        jLabel18.setBackground(new java.awt.Color(255, 0, 0));
        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("menerapkan update data pada tabel, apabila data");

        jLabel19.setBackground(new java.awt.Color(255, 0, 0));
        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("pada tabel tidak up to date.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnUserLayout = new javax.swing.GroupLayout(pnUser);
        pnUser.setLayout(pnUserLayout);
        pnUserLayout.setHorizontalGroup(
            pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnUserLayout.createSequentialGroup()
                        .addComponent(jButton_tambah1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnUserLayout.createSequentialGroup()
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnUserLayout.createSequentialGroup()
                                .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField_cari))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addGap(0, 67, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnUserLayout.createSequentialGroup()
                                .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnUserLayout.createSequentialGroup()
                                            .addComponent(jButton_tambah)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton_update)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(delete)))
                                    .addGroup(pnUserLayout.createSequentialGroup()
                                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField_firstname, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                .addComponent(jTextField_username)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_lastname, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(jPasswordField_password))))
                                .addGap(30, 30, 30))
                            .addGroup(pnUserLayout.createSequentialGroup()
                                .addComponent(jComboBox_role, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jTextField_useird, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
        );
        pnUserLayout.setVerticalGroup(
            pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnUserLayout.createSequentialGroup()
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_useird, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_tambah)
                            .addComponent(delete)
                            .addComponent(jButton_update))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_tambah1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.add(pnUser, "card3");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Cari barang dengan kode atau nama barang : ");

        jTable_perbarangan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Nama Brg", "Harga", "Satuan", "Stok"
            }
        ));
        jTable_perbarangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_perbaranganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_perbarangan);

        jButton1_caribarang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1_caribarang.setText("cari");
        jButton1_caribarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_caribarangActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("ID Brg");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Nama Brg");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Stok");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Jumlah Beli");

        jButton3_tambah.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3_tambah.setText("+");
        jButton3_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3_tambahActionPerformed(evt);
            }
        });

        jTextField2_idbrg.setEditable(false);
        jTextField2_idbrg.setEnabled(false);

        jTextField4_stok.setEditable(false);
        jTextField4_stok.setText("0");
        jTextField4_stok.setEnabled(false);
        jTextField4_stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4_stokActionPerformed(evt);
            }
        });

        jTable2_input.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Brg", "Nama Brg", "Satuan", "Jumlah", "Harga", "Total"
            }
        ));
        jScrollPane3.setViewportView(jTable2_input);

        jTextField5_jmlbeli.setText("0");
        jTextField5_jmlbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5_jmlbeliActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("TOTAL HARGA");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("INPUT PEMBAYARAN");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("KEMBALIAN");

        jTextField6_totalharga.setEditable(false);
        jTextField6_totalharga.setBackground(new java.awt.Color(255, 153, 153));
        jTextField6_totalharga.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField6_totalharga.setForeground(new java.awt.Color(0, 0, 0));
        jTextField6_totalharga.setCaretColor(new java.awt.Color(255, 51, 51));
        jTextField6_totalharga.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextField6_totalharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6_totalhargaActionPerformed(evt);
            }
        });

        jTextField9_inputbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9_inputbayarActionPerformed(evt);
            }
        });

        jTextField10_kembalian.setEditable(false);
        jTextField10_kembalian.setEnabled(false);
        jTextField10_kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10_kembalianActionPerformed(evt);
            }
        });

        jToggleButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jToggleButton1.setText("SIMPAN KE DATABASE");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jTextField3_namabrg.setEditable(false);
        jTextField3_namabrg.setEnabled(false);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Harga");

        jLabel31_harga.setEditable(false);
        jLabel31_harga.setText("0");
        jLabel31_harga.setEnabled(false);

        jLabel31_satuan.setEditable(false);
        jLabel31_satuan.setEnabled(false);
        jLabel31_satuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabel31_satuanActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Satuan");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("No Faktur");

        jLabel31_nofak.setEditable(false);
        jLabel31_nofak.setEnabled(false);
        jLabel31_nofak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabel31_nofakActionPerformed(evt);
            }
        });

        jButton5_hapus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5_hapus.setText("-");
        jButton5_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5_hapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnKasirLayout = new javax.swing.GroupLayout(pnKasir);
        pnKasir.setLayout(pnKasirLayout);
        pnKasirLayout.setHorizontalGroup(
            pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKasirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnKasirLayout.createSequentialGroup()
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1_caridatabarang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1_caribarang))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnKasirLayout.createSequentialGroup()
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextField2_idbrg, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3_namabrg, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnKasirLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnKasirLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jTextField4_stok)))
                        .addGap(7, 7, 7)
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnKasirLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel31_nofak))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKasirLayout.createSequentialGroup()
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6_totalharga, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(12, 12, 12)
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnKasirLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28)
                                .addGap(58, 58, 58))
                            .addGroup(pnKasirLayout.createSequentialGroup()
                                .addComponent(jTextField9_inputbayar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField10_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKasirLayout.createSequentialGroup()
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel31_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnKasirLayout.createSequentialGroup()
                                .addComponent(jTextField5_jmlbeli, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnKasirLayout.setVerticalGroup(
            pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKasirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnKasirLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1_caridatabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1_caribarang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnKasirLayout.createSequentialGroup()
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnKasirLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2_idbrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnKasirLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3_namabrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnKasirLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4_stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnKasirLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel31_nofak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnKasirLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(28, 28, 28))
                            .addGroup(pnKasirLayout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(26, 26, 26))
                            .addGroup(pnKasirLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField5_jmlbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3_tambah)
                                    .addComponent(jButton5_hapus))))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel28)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField9_inputbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6_totalharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel3.add(pnKasir, "card4");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setText("Riwayat / History / Logs");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("riwayat penjualan secara rinci");

        jTable1_riwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Nama Barang", "Harga", "Jumlah", "Total"
            }
        ));
        jScrollPane4.setViewportView(jTable1_riwayat);

        javax.swing.GroupLayout pnRiwayatLayout = new javax.swing.GroupLayout(pnRiwayat);
        pnRiwayat.setLayout(pnRiwayatLayout);
        pnRiwayatLayout.setHorizontalGroup(
            pnRiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRiwayatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnRiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
                    .addGroup(pnRiwayatLayout.createSequentialGroup()
                        .addGroup(pnRiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnRiwayatLayout.setVerticalGroup(
            pnRiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRiwayatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(pnRiwayat, "card5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void iniBerandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniBerandaActionPerformed
        // tombol beranda cuaksss
        try {
            String query = "SELECT COUNT(*) FROM `perbarangan` WHERE 1";
            execQueryTotalBarang(query);
        } catch (Exception e) {
        }
        try {
            String query = "SELECT SUM(total) FROM `penjualan` WHERE 1";
            execQueryTotalPendapatan(query);
        } catch (Exception e) {
        }
        try {
            String query = "SELECT COUNT(*) FROM `penjualan` WHERE 1";
            execQueryTotalJual(query);
        } catch (Exception e) {
        }

        pnBeranda.setVisible(true);
        pnUser.setVisible(false);
        pnKasir.setVisible(false);
        pnBarang.setVisible(false);
        pnRiwayat.setVisible(false);

        //iniKeluar.setEnabled(false);
    }//GEN-LAST:event_iniBerandaActionPerformed

    private void iniUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniUserActionPerformed
        // kondisi penentu hak akses, apakah user layak membuka tombol apa tidak
        //iniKeluar.setEnabled(false);
        // tombol view mengki
        String sql = "SELECT * FROM perloginan";
        try {
            con = perpluginan.koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) Table_DisplayUser.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6)});
            }
        } catch (Exception e) {
        }

        if (role.equalsIgnoreCase("admin")) {
            // this.dispose();
            pnBeranda.setVisible(false);
            pnUser.setVisible(true);
            pnBarang.setVisible(false);
            pnKasir.setVisible(false);
            pnRiwayat.setVisible(false);
//            peruseran.tambah_user user_sakkarepmu = new peruseran.tambah_user();
//            user_sakkarepmu.setVisible(true);

        } else {
            // JOptionPane.showMessageDialog(rootPane, "anda tidak memiliki role admin !" , "Login Error", 1);
            JOptionPane.showMessageDialog(this, "anda tidak memiliki role admin", "warning", HEIGHT);
        }
    }//GEN-LAST:event_iniUserActionPerformed

    private JFrame frame; // deklarasi var jframe
    private void iniKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniKeluarActionPerformed
        // tombol keluar
        frame = new JFrame("Keluar");
        if (JOptionPane.showConfirmDialog(frame, "Apakah anda yakin ingin kembali ke menu login ? ", "Konfirmasi",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            perloginan.login mbalek_sakkarepmu = new perloginan.login();
            mbalek_sakkarepmu.setVisible(true);
            this.hide(); // hide menu
            // System.exit(0);
        }
    }//GEN-LAST:event_iniKeluarActionPerformed

    private void iniKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniKasirActionPerformed

        loadDataBarang(); // memanggil void data barang yang sudah di deklarasikan diatas        
        // tombol kasir
        pnBeranda.setVisible(false);
        pnUser.setVisible(false);
        pnBarang.setVisible(false);
        pnKasir.setVisible(true);
        pnRiwayat.setVisible(false);
        //iniKeluar.setEnabled(false);
    }//GEN-LAST:event_iniKasirActionPerformed

    private void iniRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniRiwayatActionPerformed
        // tombol riwayat
        loadDataRiwayat();
        pnBeranda.setVisible(false);
        pnUser.setVisible(false);
        pnBarang.setVisible(false);
        pnKasir.setVisible(false);
        pnRiwayat.setVisible(true);
        //iniKeluar.setEnabled(false);
    }//GEN-LAST:event_iniRiwayatActionPerformed

    private void iniBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniBarangActionPerformed
        // tombol barang
        auto_idbarang();
        loadDataBarang2();
        btn_tambah.setEnabled(true);
        btn_update.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_batal.setEnabled(false);
        // kondisi penentu hak akses, apakah user layak membuka tombol apa tidak

        //iniKeluar.setEnabled(false);
        if (role.equalsIgnoreCase("admin")) {
            // this.dispose();
            pnBeranda.setVisible(false);
            pnUser.setVisible(false);
            pnBarang.setVisible(true);
            pnKasir.setVisible(false);
            pnRiwayat.setVisible(false);
        } else {
            // JOptionPane.showMessageDialog(rootPane, "anda tidak memiliki role admin !" , "Login Error", 1);
            JOptionPane.showMessageDialog(this, "anda tidak memiliki role admin", "warning", HEIGHT);
        }
    }//GEN-LAST:event_iniBarangActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        mencari_bayanganmu();
//        try {
//            pst = con.prepareStatement("select * from perloginan where username=?");
//            pst.setString(1,jTextField_cari.getText());
//            rs = pst.executeQuery();
//            if(rs.next()) { 
//                jTextField_firstname.setText(rs.getString(1));
//                jTextField_lastname.setText(rs.getString(2));
//                jTextField_username.setText(rs.getString(3));
////                jTextField_firstname.setText(rs.getString(1));
////                jTextField_firstname.setText(rs.getString(1));
//            }
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_tambahMouseClicked
        // button tambah

        /* deklarasi radio button */
//        String jk = null;
//        if (jRadioButton_admin.isSelected()) { 
//            jk = "admin";
//        }else if (jRadioButton_kasir.isSelected()) {
//            jk = "kasir";
//        }
//        
        // deklarasi combobox cuok
        String fn = jTextField_firstname.getText();
        String ln = jTextField_lastname.getText();
        String un = jTextField_username.getText();
        String pw = jPasswordField_password.getText();

        if (fn.equals("") || ln.equals("") || un.equals("Select") || pw.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "kolom harus terisi !", "Error", 1);
        } else {
            String ambil_role = jComboBox_role.getSelectedItem().toString();
            try {
                pst = con.prepareStatement("INSERT INTO perloginan(firstname,lastname,username,password,role)VALUES(?,?,?,?,?)");
                pst.setString(1, jTextField_firstname.getText());
                pst.setString(2, jTextField_lastname.getText());
                pst.setString(3, jTextField_username.getText());
                pst.setString(4, jPasswordField_password.getText());
                pst.setString(5, ambil_role);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "data berhasil disimpan !");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "data gagal disimpan !" + e.getMessage());
            }
        }
        hapus(); // memanggil fungsi yg sudah di deklarasikan diatas tadi untuk menghapus textfield setelah tambah data
    }//GEN-LAST:event_jButton_tambahMouseClicked

    private void jButton_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_tambahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_tambahActionPerformed

    private void jTextField_useirdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_useirdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_useirdActionPerformed

    private void jButton_tambah1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_tambah1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_tambah1MouseClicked

    private void jButton_tambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_tambah1ActionPerformed
        // tombol refresh lorrrrrrrrrr
        String sql = "SELECT * FROM perloginan";
        try {
            con = perpluginan.koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) Table_DisplayUser.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6)});
            }
            JOptionPane.showMessageDialog(this, "refresh berhasil !");
            hapus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "refresh gagak !" + e.getMessage());
        }

    }//GEN-LAST:event_jButton_tambah1ActionPerformed

    private void jButton_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_updateMouseClicked
//        String ambil_role = jComboBox_role.getSelectedItem(). toString();  
//        try {
//            pst = con.prepareStatement("update perloginan set id=?,firstname=?,lastname=?,username=?,password=?,role=?");
//            pst.setString(1,jTextField_useird.getText());
//            pst.setString(2,jTextField_firstname.getText());
//            pst.setString(3,jTextField_lastname.getText());
//            pst.setString(4,jTextField_username.getText());
//            pst.setString(5,jPasswordField_password.getText());
//            pst.setString(5,ambil_role);
//            pst.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Data berhasil di edit !");
//        } catch (Exception e) {
//            
//        }

// tombol update / edits
//        String ambil_role = jComboBox_role.getSelectedItem(). toString();
//        try {
//            String sql = "'UPDATE perloginan SET id='" + jTextField_useird.getText() + "'firstname=" +  jTextField_firstname.getText() + "lastname =" + jTextField_lastname.getText()
//                    + "'username=" + jTextField_username.getText() + "role=" + ambil_role + "'WHERE id='" + jTextField_useird.getText() + "'";
//            Connection con = (Connection) perpluginan.koneksi.getConnection();
//            PreparedStatement pst = con.prepareStatement(sql); 
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "Data berhasil di edit !");
//        } catch (Exception e) {
//        }
//        
    }//GEN-LAST:event_jButton_updateMouseClicked

    private void Table_DisplayUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_DisplayUserMouseClicked
        // pencet tabel 

        int baris = Table_DisplayUser.rowAtPoint(evt.getPoint());
//        String id = Table_DisplayUser.getValueAt(baris, 0).toString();
        String firstname = Table_DisplayUser.getValueAt(baris, 1).toString();
        jTextField_firstname.setText(firstname);
        String lastname = Table_DisplayUser.getValueAt(baris, 2).toString();
        jTextField_lastname.setText(lastname);
        String username = Table_DisplayUser.getValueAt(baris, 3).toString();
        jTextField_username.setText(username);
        String password = Table_DisplayUser.getValueAt(baris, 3).toString();
        jPasswordField_password.setText(password);
        String userid = Table_DisplayUser.getValueAt(baris, 0).toString();
        jTextField_useird.setText(userid);
        //        String role = Table_DisplayUser.getValueAt(baris, 4).toString();
        //        jTextField_firstname.setText(role);
        jButton_tambah.setEnabled(false);
        jButton_update.setEnabled(true);
        delete.setEnabled(true);

    }//GEN-LAST:event_Table_DisplayUserMouseClicked

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        // tombolll update / edittttssssss
        String fn = jTextField_firstname.getText();
        String ln = jTextField_lastname.getText();
        String un = jTextField_username.getText();
        String pw = jPasswordField_password.getText();

        if (fn.equals("") || ln.equals("") || un.equals("Select") || pw.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "pilih data yang ingin di update !", "Error", 1);
        } else {
            String ambil_role = jComboBox_role.getSelectedItem().toString();
            try {
                pst = con.prepareStatement("update perloginan set id=?,firstname=?,lastname=?,username=?,password=?,role=? where id=?");
                pst.setString(1, jTextField_useird.getText());
                pst.setString(2, jTextField_firstname.getText());
                pst.setString(3, jTextField_lastname.getText());
                pst.setString(4, jTextField_username.getText());
                pst.setString(5, jPasswordField_password.getText());
                pst.setString(6, ambil_role);
                pst.setString(7, jTextField_useird.getText()); // parameter value 7 
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "data berhasil diupdate, jangan lupa refresh !");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "data gagal disimpan !" + e.getMessage());
            }
        }
        hapus();

    }//GEN-LAST:event_jButton_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // button delete
        String fn = jTextField_firstname.getText();
        String ln = jTextField_lastname.getText();
        String un = jTextField_username.getText();
        String pw = jPasswordField_password.getText();

        if (fn.equals("") || ln.equals("") || un.equals("Select") || pw.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "pilih data yang ingin di hapus !", "Error", 1);
        } else {
            try {
                pst = con.prepareStatement("delete from perloginan where id=?");
                pst.setString(1, jTextField_useird.getText()); // parameter value 7
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "data berhasil dihapus, jangan lupa refresh !");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "data gagal disimpan !" + e.getMessage());
            }
        }
        hapus();
    }//GEN-LAST:event_deleteActionPerformed

    private void jButton_tambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_tambahMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_tambahMouseEntered

    private void jTextField_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_cariActionPerformed

    private void jTextField4_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4_stokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4_stokActionPerformed

    private void jButton1_caribarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_caribarangActionPerformed
        // TODO add your handling code here:
//        pencarian_barang2();
        cariDataPerbarangan2();

    }//GEN-LAST:event_jButton1_caribarangActionPerformed

    private void jTextField5_jmlbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5_jmlbeliActionPerformed
        // TODO add your handling code here:
        // tombol input

        String id_brg = jTextField2_idbrg.getText();
        String nama_brg = jTextField3_namabrg.getText();
        String stok = jTextField4_stok.getText();
        String harga = jLabel31_harga.getText();
        String satuan = jLabel31_satuan.getText();

        String jumlah_beli = jTextField5_jmlbeli.getText();

        int jumlah_sakkarepmu, stok_sakkarepmu;
        jumlah_sakkarepmu = Integer.valueOf(jTextField5_jmlbeli.getText());
        stok_sakkarepmu = Integer.valueOf(jTextField4_stok.getText());
        // bayar = Integer.valueOf(jTextField9_inputbayar.getText());
//        String a = jumlah_beli > stok;
        if (id_brg.equals("") || nama_brg.equals("") || stok.equals("") || harga.equals("") || satuan.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Pilih barang terlebih dahulu !", "Error", 1);

        } else if (jumlah_beli.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Inputkan jumlah pembelian !", "Error", 1);
        } //        else if (jumlah_sakkarepmu > stok_sakkarepmu) {
        //            JOptionPane.showMessageDialog(rootPane, "Kehabisan Stok !", "Error", 1);
        //        } 
        else {
            tambahTransaksiPerkasiran();
        }
    }//GEN-LAST:event_jTextField5_jmlbeliActionPerformed

    private void jTextField6_totalhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6_totalhargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6_totalhargaActionPerformed

    private void jTextField9_inputbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9_inputbayarActionPerformed
        // kolom iput bayaran / uang pembayaran
        int total, bayar, kembalian;

        total = Integer.valueOf(jTextField6_totalharga.getText());
        bayar = Integer.valueOf(jTextField9_inputbayar.getText());

        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            jTextField10_kembalian.setText(String.valueOf(kembalian));
        }
        //
//        int total, bayar, kembalian;
//
//        total = Integer.valueOf(jTextField6_totalharga.getText());
//        bayar = Integer.valueOf(jTextField9_inputbayar.getText());
//
//        if (total > bayar) {
//            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
//        } else {
//            kembalian = bayar - total;
//            jTextField10_kembalian.setText(String.valueOf(kembalian));
//        }
//        int total, bayar, kembalian;
//
//        total = Integer.valueOf(jTextField6_totalharga.getText());
//        bayar = Integer.valueOf(jTextField9_inputbayar.getText());
//
//        if (total > bayar) {
//            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran !");
//        } else {
//            kembalian = bayar - total;
//            jTextField10_kembalian.setText(String.valueOf(kembalian));
//        }
    }//GEN-LAST:event_jTextField9_inputbayarActionPerformed

    private void jTextField10_kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10_kembalianActionPerformed

    private void jTextField_firstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_firstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_firstnameActionPerformed

    private void jTable_perbaranganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_perbaranganMouseClicked
        // pencet tabel 
        int baris = jTable_perbarangan.rowAtPoint(evt.getPoint());
//        String id = Table_DisplayUser.getValueAt(baris, 0).toString();
        String id_brg = jTable_perbarangan.getValueAt(baris, 0).toString();
        jTextField2_idbrg.setText(id_brg);
        String nama_brg = jTable_perbarangan.getValueAt(baris, 1).toString();
        jTextField3_namabrg.setText(nama_brg);
        String harga = jTable_perbarangan.getValueAt(baris, 2).toString();
        jLabel31_harga.setText(harga);
        String satuan = jTable_perbarangan.getValueAt(baris, 3).toString();
        jLabel31_satuan.setText(satuan);
        String stok = jTable_perbarangan.getValueAt(baris, 4).toString();
        jTextField4_stok.setText(stok);

        //        String password = Table_DisplayUser.getValueAt(baris, 3).toString();
        //        jPasswordField_password.setText(password);
        //        String userid = Table_DisplayUser.getValueAt(baris, 0).toString();
        //        jTextField_useird.setText(userid);
    }//GEN-LAST:event_jTable_perbaranganMouseClicked

    private void jButton3_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3_tambahActionPerformed
        // tombol input

        String id_brg = jTextField2_idbrg.getText();
        String nama_brg = jTextField3_namabrg.getText();
        String stok = jTextField4_stok.getText();
        String harga = jLabel31_harga.getText();
        String satuan = jLabel31_satuan.getText();

        String jumlah_beli = jTextField5_jmlbeli.getText();

        int stok_sakkarepmu, jumlah_sakkarepmu;
        jumlah_sakkarepmu = Integer.valueOf(jTextField5_jmlbeli.getText());
        stok_sakkarepmu = Integer.valueOf(jTextField4_stok.getText());

        if (stok_sakkarepmu < jumlah_sakkarepmu) {
            JOptionPane.showMessageDialog(rootPane, "Melebihi stok yang tersedia !", "Error", 1);
        } else if (id_brg.equals("") || nama_brg.equals("") || stok.equals("0") || harga.equals("0") || satuan.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Pilih barang terlebih dahulu !", "Error", 1);

        } else if (jumlah_beli.equals("0")) {
            JOptionPane.showMessageDialog(rootPane, "Inputkan jumlah pembelian !", "Error", 1);
        } //        else if (jumlah_sakkarepmu > stok_sakkarepmu) {
        //            JOptionPane.showMessageDialog(rootPane, "Kehabisan Stok !", "Error", 1);
        //        } 
        else {
            tambahTransaksiPerkasiran();
        }

    }//GEN-LAST:event_jButton3_tambahActionPerformed

    private void jLabel31_satuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabel31_satuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel31_satuanActionPerformed

    private void jLabel31_nofakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabel31_nofakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel31_nofakActionPerformed

    private void jTextField_cari4_caridatabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_cari4_caridatabarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_cari4_caridatabarangActionPerformed

    private void jButton7_caribarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7_caribarangActionPerformed
        // TODO add your handling code here:
//        pencarian_barang();
        cariDataPerbarangan();
    }//GEN-LAST:event_jButton7_caribarangActionPerformed

    private void jTextField2_idbrg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2_idbrg1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2_idbrg1ActionPerformed

    private void jTable_perbarangan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_perbarangan2MouseClicked
        // TODO add your handling code here:
        // pencet tabel 
        String satuan_sakkarepmu = jComboBox_satuan.getSelectedItem().toString();
        int baris = jTable_perbarangan2.rowAtPoint(evt.getPoint());
//        String id = Table_DisplayUser.getValueAt(baris, 0).toString();
        String id_barang = jTable_perbarangan2.getValueAt(baris, 0).toString();
        jTextField2_idbrg1.setText(id_barang);
        String nama_barang = jTable_perbarangan2.getValueAt(baris, 1).toString();
        jTextField4_namabrg.setText(nama_barang);
        String harga = jTable_perbarangan2.getValueAt(baris, 2).toString();
        jLabel31_harga1.setText(harga);
        String stok = jTable_perbarangan2.getValueAt(baris, 4).toString();
        jTextField4_stock.setText(stok);
        btn_tambah.setEnabled(false);
        btn_update.setEnabled(true);
        btn_delete.setEnabled(true);
        btn_batal.setEnabled(true);
    }//GEN-LAST:event_jTable_perbarangan2MouseClicked

    private void btn_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tambahMouseClicked

    private void btn_tambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tambahMouseEntered

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        String id_barang = jTextField2_idbrg1.getText();
        String nama_barang = jTextField4_namabrg.getText();
        String satuan = (String) jComboBox_satuan.getSelectedItem();
        String harga_barang = jLabel31_harga1.getText();
        String stock = jTextField4_stock.getText();

        // String satuan_sakkarepmu = jComboBox_satuan.getSelectedItem().toString();
        if (id_barang.equals("") || nama_barang.equals("") || satuan.equals("") || harga_barang.equals("") || stock.equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom harus diisi !");
        } else if (satuan.equals("Satuan")) {
            JOptionPane.showMessageDialog(null, "Pilih Satuan terlebih dahulu !");
        } else {
            try {
                Connection c = koneksi.getConnection();
                String sql = "INSERT INTO perbarangan VALUES (?, ?, ?, ?, ?)";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, id_barang);
                p.setString(2, nama_barang);
                p.setString(3, harga_barang);
                p.setString(4, satuan);
                p.setString(5, stock);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan");
                loadDataBarang();
                auto_idbarang();
                clearPerbarangan();
            } catch (Exception e) {
                System.out.println("[ERROR] gagal simpan data " + e);
            } finally {
                auto_idbarang();
                clearPerbarangan();
            }
        }


    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateMouseClicked

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        // tombol update
        String ib = jTextField2_idbrg1.getText();
        String nb = jTextField4_namabrg.getText();
        String hg = jLabel31_harga1.getText();
        String satuan_sakakarepmu = jComboBox_satuan.getSelectedItem().toString();
        String st = jTextField4_stock.getText();

        if (ib.equals("") || nb.equals("") || hg.equals("") || satuan_sakakarepmu.equals("Satuan") || st.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "pilih data yang ingin di update !", "Error", 1);
        } else {
            // String ambil_role = jComboBox_role.getSelectedItem().toString();
            try {
                pst = con.prepareStatement("update perbarangan set id_barang=?,nama_barang=?,harga_barang=?,satuan=?,stock=? where id_barang=?");
                pst.setString(1, jTextField2_idbrg1.getText());
                pst.setString(2, jTextField4_namabrg.getText());
                pst.setString(3, jLabel31_harga1.getText());
                pst.setString(4, satuan_sakakarepmu);
                pst.setString(5, jTextField4_stock.getText());
                pst.setString(6, jTextField2_idbrg1.getText()); // parameter value 6
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "data berhasil diupdate, jangan lupa refresh !");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "data gagal disimpan !" + e.getMessage());
            } finally {
                loadDataBarang();
                auto_idbarang();
            }
        }
        clearPerbarangan();
        // 
//        int i = jTable_perbarangan2.getSelectedRow();
//        if (i == -1) {
//            return;
//        }
//        String id = (String) model.getValueAt(i, 0);
//        String nama = jTextField4_namabrg.getText();
//        String satuan = (String) jComboBox_satuan.getSelectedItem();
//        String harga = jLabel31_harga1.getText();
//        String stock = jTextField4_stock.getText();
//
//        try {
//            Connection c = koneksi.getConnection();
//            String sql = "update perbarangan set id_barang=?,nama=?,harga=?,satuan=?,stock=? where id_barang=?";
//            PreparedStatement p = c.prepareStatement(sql);
//            p.setString(1, nama);
//            p.setString(2, harga);
//            p.setString(3, satuan);
//            p.setString(4, stock);
//            p.setString(5, id);
//
//            p.executeUpdate();
//            p.close();
//            JOptionPane.showMessageDialog(null, "Data Terubah");
//            btn_tambah.setEnabled(true);
//            btn_update.setEnabled(false);
//            btn_delete.setEnabled(false);
//            btn_batal.setEnabled(false);
//            clearPerbarangan();
//        } catch (Exception e) {
//            System.out.println("[ERROR] updating perbarangan " + e);
//        } finally {
//            loadDataBarang();
//            auto_idbarang();
//        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        // button delete
        //  
        String ib = jTextField2_idbrg1.getText();
        String nb = jTextField4_namabrg.getText();
        String hg = jLabel31_harga1.getText();
        String satuan_sakakarepmu = jComboBox_satuan.getSelectedItem().toString();
        String st = jTextField4_stock.getText();

        if (ib.equals("") || nb.equals("") || hg.equals("") || st.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "pilih data yang ingin di hapus !", "Error", 1);
        } else {
            try {
                pst = con.prepareStatement("delete from perbarangan where id_barang=?");
                pst.setString(1, jTextField2_idbrg1.getText()); // parameter value 7
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "data berhasil dihapus, jangan lupa refresh !");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "data gagal disimpan !" + e.getMessage());
            }
        }
        clearPerbarangan();

//
//        int i = jTable_perbarangan2.getSelectedRow();
//        if (i == -1) {
//            return;
//        }
//
//        String id = (String) model.getValueAt(i, 0);
//
//        int pernyataan = JOptionPane.showConfirmDialog(null, "Yakin Data Akan Dihapus", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
//        if (pernyataan == JOptionPane.OK_OPTION) {
//            try {
//                Connection c = koneksi.getConnection();
//                String sql = "DELETE FROM perbarangan WHERE ID_Barang = ?";
//                PreparedStatement p = c.prepareStatement(sql);
//                p.setString(1, id);
//                p.executeUpdate();
//                p.close();
//                JOptionPane.showMessageDialog(null, "Data berhasil terhapus");
//            } catch (Exception e) {
//                System.out.println("[ERROR] delete perbarangan " + e);
//            } finally {
//                btn_tambah.setEnabled(true);
//                btn_update.setEnabled(false);
//                btn_delete.setEnabled(false);
//                btn_batal.setEnabled(false);
//                loadDataBarang();
//                auto_idbarang();
//                clearPerbarangan();
//            }
//        }
//        if (pernyataan == JOptionPane.CANCEL_OPTION) {
//
//        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        String sql = "SELECT * FROM perbarangan";
        try {
            con = perpluginan.koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable_perbarangan2.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }
            JOptionPane.showMessageDialog(this, "refresh berhasil !");
            hapus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "refresh gagak !" + e.getMessage());
        }

    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_refreshMouseClicked

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        clearPerbarangan();
        loadDataBarang();
        btn_tambah.setEnabled(true);
        btn_update.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_batal.setEnabled(false);
        auto_idbarang();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void jButton5_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5_hapusActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) jTable2_input.getModel();
        int row = jTable2_input.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        jTextField9_inputbayar.setText("0");
        jTextField10_kembalian.setText("0");
    }//GEN-LAST:event_jButton5_hapusActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2_input.getModel();

        String id_transaksi = jLabel31_nofak.getText();
        String total = jTextField6_totalharga.getText();

        try {
            Connection c = koneksi.getConnection();
            String sql = "INSERT INTO penjualan VALUES (?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id_transaksi);
            p.setString(2, total);
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke database !");
        } catch (Exception e) {
            System.out.println("[ERROR] simpan penjualan error" + e);
        }

        try {
            Connection c = koneksi.getConnection();
            int baris = jTable2_input.getRowCount();

            for (int i = 0; i < baris; i++) {
                String sql = "INSERT INTO penjualan_rinci(id_barang, nama_barang, harga, jumlah, total) VALUES('"
                        + jTable2_input.getValueAt(i, 0) + "','" + jTable2_input.getValueAt(i, 1) + "','" + jTable2_input.getValueAt(i, 3)
                        + "','" + jTable2_input.getValueAt(i, 4) + "','" + jTable2_input.getValueAt(i, 5) + "')";
                PreparedStatement p = c.prepareStatement(sql);
                p.executeUpdate();
                p.close();
            }
        } catch (Exception e) {
            System.out.println("simpan penjualanrinci error " + e);
        }
        clearKasir();
        utamaPerkasiran();
        // utama();
        auto_idstruk();
        kosongPerkasiran();
        jTextField6_totalharga.setText("Rp. 0");
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu_utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table_DisplayUser;
    private javax.swing.JToggleButton btn_batal;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton delete;
    private javax.swing.JButton iniBarang;
    private javax.swing.JButton iniBeranda;
    private javax.swing.JButton iniKasir;
    private javax.swing.JButton iniKeluar;
    private javax.swing.JButton iniRiwayat;
    private javax.swing.JButton iniUser;
    private javax.swing.JButton jButton1_caribarang;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3_tambah;
    private javax.swing.JButton jButton5_hapus;
    private javax.swing.JButton jButton7_caribarang;
    private javax.swing.JButton jButton_tambah;
    private javax.swing.JButton jButton_tambah1;
    private javax.swing.JButton jButton_update;
    private javax.swing.JComboBox<String> jComboBox_role;
    private javax.swing.JComboBox<String> jComboBox_satuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel20_totbar;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JTextField jLabel31_harga;
    private javax.swing.JTextField jLabel31_harga1;
    private javax.swing.JTextField jLabel31_nofak;
    private javax.swing.JTextField jLabel31_satuan;
    private javax.swing.JLabel jLabel31_tojual;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel37_totpen;
    private javax.swing.JLabel jLabel37_totpen1;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField_password;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1_riwayat;
    private javax.swing.JTable jTable2_input;
    private javax.swing.JTable jTable_perbarangan;
    private javax.swing.JTable jTable_perbarangan2;
    private javax.swing.JTextField jTextField10_kembalian;
    private javax.swing.JTextField jTextField1_caridatabarang;
    private javax.swing.JTextField jTextField2_idbrg;
    private javax.swing.JTextField jTextField2_idbrg1;
    private javax.swing.JTextField jTextField3_namabrg;
    private javax.swing.JTextField jTextField4_namabrg;
    private javax.swing.JTextField jTextField4_stock;
    private javax.swing.JTextField jTextField4_stok;
    private javax.swing.JTextField jTextField5_jmlbeli;
    private javax.swing.JTextField jTextField6_totalharga;
    private javax.swing.JTextField jTextField9_inputbayar;
    private javax.swing.JTextField jTextField_cari;
    private javax.swing.JTextField jTextField_cari4_caridatabarang;
    private javax.swing.JTextField jTextField_firstname;
    private javax.swing.JTextField jTextField_lastname;
    private javax.swing.JTextField jTextField_useird;
    private javax.swing.JTextField jTextField_username;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel pnBarang;
    private javax.swing.JPanel pnBeranda;
    private javax.swing.JPanel pnKasir;
    private javax.swing.JPanel pnRiwayat;
    private javax.swing.JPanel pnUser;
    // End of variables declaration//GEN-END:variables
}
