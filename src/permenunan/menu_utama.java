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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
  _____             _ _        _    
 |_   _| _      __(_) |_ _  _| |__ 
   | || ' \ _  (_-< | ' \ || | '_ \
   |_||_||_(_) /__/_|_||_\_,_|_.__/  
   * sihub | 03 - 27 - 2023
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
    DefaultTableModel model;
    public menu_utama() {
        initComponents();
        data = new String[6];
        con = perpluginan.koneksi.getConnection();
        // con = new Koneksi().getConnect();
        model = (DefaultTableModel)Table_DisplayUser.getModel();
        loadData();//panggil method loadData
    }
    void mencari_bayanganmu() {
        
        
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID");
        tbl.addColumn("Firstname");
        tbl.addColumn("Lastname");
        tbl.addColumn("Username");
        tbl.addColumn("Role");
        try { 
            String sql = "SELECT * FROM perloginan WHERE username like '%" + jTextField_cari.getText() + "%'" + "OR " + "id='" + jTextField_cari.getText() + "'" ;
            Connection con = (Connection) perpluginan.koneksi.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
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
    void hapus(){
        jTextField_firstname.setText("");
        jTextField_lastname.setText("");
        jTextField_username.setText("");
        jPasswordField_password.setText("");
    }

    public void loadData(){
        String sql = "SELECT * FROM perloginan" ;
        try {
            con = perpluginan.koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel)Table_DisplayUser.getModel();
            model.setRowCount(0);
            while(rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }
        } catch (Exception e) {
        }
    }          
    public menu_utama(String Uname) {
        initComponents();
        // jLabel1.setText(Uname);
        
        /* kondisi hak akses */ 
        if(role.equalsIgnoreCase("admin")) {
            tombolAdmin();
        }else { 
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
    private void tombolAdmin(){
        iniBeranda.setEnabled(true);
        iniBarang.setEnabled(true);
        iniUser.setEnabled(true);
        iniKasir.setEnabled(true);
        iniRiwayat.setEnabled(true);
        iniKeluar.setEnabled(true);
    }
     /* mendeklarasikan tombol admin */
    private void tombolKasir(){
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
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        pnRiwayat = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        pnBarang = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

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

        javax.swing.GroupLayout pnBerandaLayout = new javax.swing.GroupLayout(pnBeranda);
        pnBeranda.setLayout(pnBerandaLayout);
        pnBerandaLayout.setHorizontalGroup(
            pnBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBerandaLayout.createSequentialGroup()
                .addGroup(pnBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBerandaLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(pnBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnBerandaLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        pnBerandaLayout.setVerticalGroup(
            pnBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBerandaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(45, 45, 45)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(350, Short.MAX_VALUE))
        );

        jPanel3.add(pnBeranda, "card2");

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
            Table_DisplayUser.getColumnModel().getColumn(0).setMaxWidth(40);
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
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnUserLayout.createSequentialGroup()
                                .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField_cari))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnUserLayout.createSequentialGroup()
                                .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnUserLayout.createSequentialGroup()
                                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField_firstname, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(jTextField_username)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_lastname, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(jPasswordField_password)))
                                    .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnUserLayout.createSequentialGroup()
                                            .addComponent(jButton_tambah)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton_update)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(delete))))
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
                .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnUserLayout.createSequentialGroup()
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_tambah1))
                    .addGroup(pnUserLayout.createSequentialGroup()
                        .addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
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
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3.add(pnUser, "card3");

        jLabel3.setText("cari barang dengan kode atau nama barang : ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, "", null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NO", "ID Barang", "Nama Barang", "Harga", "Stok"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(25);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(65);
        }

        jButton1.setText("cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnKasirLayout = new javax.swing.GroupLayout(pnKasir);
        pnKasir.setLayout(pnKasirLayout);
        pnKasirLayout.setHorizontalGroup(
            pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKasirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnKasirLayout.createSequentialGroup()
                        .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(430, Short.MAX_VALUE))
        );
        pnKasirLayout.setVerticalGroup(
            pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKasirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        jPanel3.add(pnKasir, "card4");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setText("Riwayat / History / Logs");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("under construction");

        javax.swing.GroupLayout pnRiwayatLayout = new javax.swing.GroupLayout(pnRiwayat);
        pnRiwayat.setLayout(pnRiwayatLayout);
        pnRiwayatLayout.setHorizontalGroup(
            pnRiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRiwayatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnRiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(438, Short.MAX_VALUE))
        );
        pnRiwayatLayout.setVerticalGroup(
            pnRiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRiwayatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addContainerGap(493, Short.MAX_VALUE))
        );

        jPanel3.add(pnRiwayat, "card5");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Stock Barang");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("under construction");

        javax.swing.GroupLayout pnBarangLayout = new javax.swing.GroupLayout(pnBarang);
        pnBarang.setLayout(pnBarangLayout);
        pnBarangLayout.setHorizontalGroup(
            pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(538, Short.MAX_VALUE))
        );
        pnBarangLayout.setVerticalGroup(
            pnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addContainerGap(493, Short.MAX_VALUE))
        );

        jPanel3.add(pnBarang, "card6");

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
        pnBeranda.setVisible(true);
        pnUser.setVisible(false);
        pnKasir.setVisible(false);
        pnRiwayat.setVisible(false);
        //iniKeluar.setEnabled(false);
    }//GEN-LAST:event_iniBerandaActionPerformed

    private void iniUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniUserActionPerformed
        // kondisi penentu hak akses, apakah user layak membuka tombol apa tidak
        //iniKeluar.setEnabled(false);
        // tombol view mengki
        String sql = "SELECT * FROM perloginan" ;
        try {
            con = perpluginan.koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel)Table_DisplayUser.getModel();
            model.setRowCount(0);
            while(rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6)});
            }
        } catch (Exception e) {
        }
                                     

        if(role.equalsIgnoreCase("admin")){
            // this.dispose();
            pnBeranda.setVisible(false);
            pnUser.setVisible(true);
            pnBarang.setVisible(false);
            pnKasir.setVisible(false);
            pnRiwayat.setVisible(false);
//            peruseran.tambah_user user_sakkarepmu = new peruseran.tambah_user();
//            user_sakkarepmu.setVisible(true);
            
        }else {
            // JOptionPane.showMessageDialog(rootPane, "anda tidak memiliki role admin !" , "Login Error", 1);
            JOptionPane.showMessageDialog(this, "anda tidak memiliki role admin", "warning", HEIGHT);
        }
    }//GEN-LAST:event_iniUserActionPerformed

    private JFrame frame; // deklarasi var jframe
    private void iniKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniKeluarActionPerformed
        // tombol keluar
        frame = new JFrame("Keluar");
        if(JOptionPane.showConfirmDialog(frame, "Apakah anda yakin ingin kembali ke menu login ? ", "Konfirmasi",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            perloginan.login mbalek_sakkarepmu = new perloginan.login();
            mbalek_sakkarepmu.setVisible(true); 
            this.hide(); // hide menu
         // System.exit(0);
        }
    }//GEN-LAST:event_iniKeluarActionPerformed

    private void iniKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniKasirActionPerformed
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
        pnBeranda.setVisible(false);
        pnUser.setVisible(false);
        pnBarang.setVisible(false);
        pnKasir.setVisible(false);
        pnRiwayat.setVisible(true);
        //iniKeluar.setEnabled(false);
    }//GEN-LAST:event_iniRiwayatActionPerformed

    private void iniBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniBarangActionPerformed
        // tombol barang
        // kondisi penentu hak akses, apakah user layak membuka tombol apa tidak

        //iniKeluar.setEnabled(false);
        if(role.equalsIgnoreCase("admin")){
            // this.dispose();
            pnBeranda.setVisible(false);
            pnUser.setVisible(false);
            pnBarang.setVisible(true);
            pnKasir.setVisible(false);
            pnRiwayat.setVisible(false);
        }else {
            // JOptionPane.showMessageDialog(rootPane, "anda tidak memiliki role admin !" , "Login Error", 1);
            JOptionPane.showMessageDialog(this, "anda tidak memiliki role admin", "warning", HEIGHT);
        }
    }//GEN-LAST:event_iniBarangActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
        
        if (fn.equals("")||ln.equals("")||un.equals("Select")||pw.equals("")){
        JOptionPane.showMessageDialog(rootPane, "kolom harus terisi !", "Error", 1);
            }else { 
        String ambil_role = jComboBox_role.getSelectedItem(). toString();
        try {
            pst = con.prepareStatement("INSERT INTO perloginan(firstname,lastname,username,password,role)VALUES(?,?,?,?,?)");
            pst.setString(1,jTextField_firstname.getText());
            pst.setString(2,jTextField_lastname.getText());
            pst.setString(3,jTextField_username.getText());
            pst.setString(4,jPasswordField_password.getText());
            pst.setString(5,ambil_role);
            
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
        String sql = "SELECT * FROM perloginan" ;
        try {
            con = perpluginan.koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel)Table_DisplayUser.getModel();
            model.setRowCount(0);
            while(rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6)});
            }
            JOptionPane.showMessageDialog(this, "refresh berhasil !");
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
        
    }//GEN-LAST:event_Table_DisplayUserMouseClicked

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        // tombolll update / edittttssssss
                String fn = jTextField_firstname.getText();
        String ln = jTextField_lastname.getText();
        String un = jTextField_username.getText();
        String pw = jPasswordField_password.getText();
        
        if (fn.equals("")||ln.equals("")||un.equals("Select")||pw.equals("")){
        JOptionPane.showMessageDialog(rootPane, "pilih data yang ingin di update !", "Error", 1);
            }else { 
               String ambil_role = jComboBox_role.getSelectedItem(). toString();  
        try {
            pst = con.prepareStatement("update perloginan set id=?,firstname=?,lastname=?,username=?,password=?,role=? where id=?");
            pst.setString(1,jTextField_useird.getText());
            pst.setString(2,jTextField_firstname.getText());
            pst.setString(3,jTextField_lastname.getText());
            pst.setString(4,jTextField_username.getText());
            pst.setString(5,jPasswordField_password.getText());
            pst.setString(6,ambil_role);
            pst.setString(7,jTextField_useird.getText()); // parameter value 7 
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
        
        if (fn.equals("")||ln.equals("")||un.equals("Select")||pw.equals("")){
        JOptionPane.showMessageDialog(rootPane, "pilih data yang ingin di hapus !", "Error", 1);
            }else { 
        try {
            pst = con.prepareStatement("delete from perloginan where id=?");
            pst.setString(1,jTextField_useird.getText()); // parameter value 7
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
            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton delete;
    private javax.swing.JButton iniBarang;
    private javax.swing.JButton iniBeranda;
    private javax.swing.JButton iniKasir;
    private javax.swing.JButton iniKeluar;
    private javax.swing.JButton iniRiwayat;
    private javax.swing.JButton iniUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton_tambah;
    private javax.swing.JButton jButton_tambah1;
    private javax.swing.JButton jButton_update;
    private javax.swing.JComboBox<String> jComboBox_role;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordField_password;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField_cari;
    private javax.swing.JTextField jTextField_firstname;
    private javax.swing.JTextField jTextField_lastname;
    private javax.swing.JTextField jTextField_useird;
    private javax.swing.JTextField jTextField_username;
    private javax.swing.JPanel pnBarang;
    private javax.swing.JPanel pnBeranda;
    private javax.swing.JPanel pnKasir;
    private javax.swing.JPanel pnRiwayat;
    private javax.swing.JPanel pnUser;
    // End of variables declaration//GEN-END:variables
}
