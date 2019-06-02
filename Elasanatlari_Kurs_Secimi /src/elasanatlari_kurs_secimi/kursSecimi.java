/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasanatlari_kurs_secimi;

import java.awt.Font;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author maya
 */

public class kursSecimi extends javax.swing.JFrame {

    public static String thisKursiyerEmmail = "ela@gmail.com";
    public static boolean odemeGerceklesti, iptal;
    public Kursiyer thisKursiyer;
    List<Kursiyer> Kursiyerler;

    // List<KursiyerKurslar> KursiyerKurslari;
    //   tablo modelleri
    public DefaultTableModel kurslarim_tablo_modeli = new DefaultTableModel();
    public DefaultTableModel kurs_gunleri_modeli = new DefaultTableModel();
    public DefaultTableModel acilanKursalar_modeli = new DefaultTableModel();
    public DefaultTableModel kurs_gunleri_ekle_modeli = new DefaultTableModel();
    List<AcilanKurs> acilanKurslar;
    
    ArrayList<String> kursiyerGunleri;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Kurs tempKurs;
    AcilanKurs tempAcilanKurs;
    ArrayList<BigDecimal> Kursiyer_kurs_idleri;

    /**
     * Creates new form kursSecimi
     */
    public kursSecimi() {
       
        odemeGerceklesti = false;
        iptal = false;
        Kursiyer_kurs_idleri = new ArrayList<>();
        kursiyerGunleri = new ArrayList<>();
        updateKursiyerler();
        initComponents();
        //default değerler
        jDateChooser1.setDate(new Date());
        lblName.setText(thisKursiyer.getAdi() + " " + thisKursiyer.getSoyadi());
//kurslarım tablo ayarları
        kurslarim_tablo_modeli.setColumnIdentifiers(new Object[]{"Kurs adı", "Kurs id", "Başlangıç tarihi", "Bitiş tarihi", "HAFTALIK SAAT", "Derslik", "Öğrenci Sayısı", "max öğrenci Sayısı"});
        tbl_kurslarim.setModel(kurslarim_tablo_modeli);
        tbl_kurslarim.setRowHeight(30);
        TableColumnModel tcm = tbl_kurslarim.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(200);
        tbl_kurslarim.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 18));

//kurs gunleri tablo ayarları
        tbl_kurs_gunleri.setModel(kurs_gunleri_modeli);
        kurs_gunleri_modeli.setColumnIdentifiers(new Object[]{"Gün", "Başlangıç saat", "Bitiş saat"});
        tbl_kurs_gunleri.setRowHeight(30);
        tbl_kurs_gunleri.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 18));
        //kurs ekle kısmındaki açılan kurslar tablo ayarları
        acilanKursalar_modeli.setColumnIdentifiers(new Object[]{"Kurs adı", "Kurs id", "Başlangıç tarihi", "Bitiş tarihi","HAFTALIK SAAT" , "Zaman", "Öğrenci Sayısı", "max öğrenci Sayısı", "Fiyat"});
        tbl_acilanKursalar.setModel(acilanKursalar_modeli);
        tbl_acilanKursalar.setRowHeight(30);
        tcm = tbl_acilanKursalar.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(250);
        tcm.getColumn(2).setPreferredWidth(180);
        tcm.getColumn(3).setPreferredWidth(180);
        tcm.getColumn(4).setPreferredWidth(140);
        tcm.getColumn(5).setPreferredWidth(120);
        tcm.getColumn(6).setPreferredWidth(140);
        tcm.getColumn(7).setPreferredWidth(140);
        tbl_acilanKursalar.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 18));
      

        //kurs ekle kısmında kurs günleri
        tbl_kurs_gunleri_ekle.setModel(kurs_gunleri_ekle_modeli);
        kurs_gunleri_ekle_modeli.setColumnIdentifiers(new Object[]{"Gün", "Başlangıç saat", "Bitiş saat"});
        tbl_kurs_gunleri_ekle.setRowHeight(30);
        tbl_kurs_gunleri_ekle.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 18));
        //kurslarım tablosunu doldurmak
        kurslarımTablosuDoldur();
        //açılan kurslar tablosunu doldurmak

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Elasanatlari_Kurs_SecimiPU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT K FROM AcilanKurs K ORDER BY K.basTarih");
        acilanKurslar = q.getResultList();
        //oğrencinin kursları tabolda göstermek
        acilanKursTablosuDoldur(chBoxHaftaici.isSelected(), chBoxHaftaSonu.isSelected(), Integer.parseInt(txtMax.getText()), Integer.parseInt(txtMin.getText()));

        ///////////kursiyerler
    }

    public void updateKursiyerler() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Elasanatlari_Kurs_SecimiPU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT K FROM Kursiyer K");
        Kursiyerler = q.getResultList();
        for (Kursiyer kursiyer : Kursiyerler) {
            if (kursiyer.getEmail().equals(thisKursiyerEmmail)) {
                thisKursiyer = kursiyer;
            }

        }

    }

    public void kurslarımTablosuDoldur() {
        kurslarim_tablo_modeli.setRowCount(0);
        for (KursiyerKurslar kursiyerKurslar : thisKursiyer.getKursiyerKurslarCollection()) {

            tempKurs = kursiyerKurslar.getAcilanKurs().getKursId();
            tempAcilanKurs = kursiyerKurslar.getAcilanKurs();
            Kursiyer_kurs_idleri.add(tempAcilanKurs.getId());
            kurslarim_tablo_modeli.addRow(new Object[]{
                tempKurs.getKursAdi(), tempAcilanKurs.getId(), dateFormat.format(tempAcilanKurs.getBasTarih()), dateFormat.format(tempAcilanKurs.getBitisTarih()), tempKurs.getHaftalikSaat(), tempAcilanKurs.getDerslik(), tempAcilanKurs.getKayitSayisi(),
                tempKurs.getMaxOgrenci()});

        }
    }

    public void acilanKursTablosuDoldur(boolean haftaIci, boolean haftaSonu, int max, int min) {
        //haftaIci veya haftasonu en az bi tane doğru ise tabloyu doldur
        try {
            acilanKursalar_modeli.setRowCount(0);
            if (haftaIci || haftaSonu) {
                String Zaman = "";
                if (!haftaIci && haftaSonu) {
                    Zaman = "haftasonu";
                }
                if (haftaIci && !haftaSonu) {
                    Zaman = "haftaiçi";
                }
                for (AcilanKurs acilanKurs : acilanKurslar) {
                   
                    tempKurs = acilanKurs.getKursId();
                    if (!Kursiyer_kurs_idleri.contains(acilanKurs.getId())) {
                        // hafta içi hafta sonu filterleme kontrolu
                        if (acilanKurs.getZaman().equals(Zaman) || (haftaIci && haftaSonu)) {
                            // fiyat kontrolu
                            if (Integer.parseInt(acilanKurs.getStandFiyat().toString()) <= max
                                    && Integer.parseInt(acilanKurs.getStandFiyat().toString()) >= min) {
                                //traih kontrolu

                                if (acilanKurs.getBasTarih().after(jDateChooser1.getDate())
                                        || acilanKurs.getBasTarih().compareTo(jDateChooser1.getDate()) == 0) {
                                    acilanKursalar_modeli.addRow(new Object[]{
                                        tempKurs.getKursAdi(), acilanKurs.getId(), dateFormat.format(acilanKurs.getBasTarih()), dateFormat.format(acilanKurs.getBitisTarih()), tempKurs.getHaftalikSaat(), acilanKurs.getZaman(), acilanKurs.getKayitSayisi(),
                                        tempKurs.getMaxOgrenci(), acilanKurs.getStandFiyat()});
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("converting hatası");

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_kurslarim = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_kurs_gunleri = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        chBoxHaftaSonu = new javax.swing.JCheckBox();
        chBoxHaftaici = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        txtMax = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMin = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_acilanKursalar = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_kurs_gunleri_ekle = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblName.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        lblName.setBorder(new javax.swing.border.MatteBorder(null));

        jTabbedPane1.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N

        jScrollPane1.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N

        tbl_kurslarim.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        tbl_kurslarim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_kurslarim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_kurslarimMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_kurslarim);

        tbl_kurs_gunleri.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        tbl_kurs_gunleri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbl_kurs_gunleri);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1581, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1581, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167))
        );

        jTabbedPane1.addTab("Kurslarım", jPanel2);

        jDateChooser1.setDateFormatString("dd/MM /yyyy");
        jDateChooser1.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        chBoxHaftaSonu.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        chBoxHaftaSonu.setSelected(true);
        chBoxHaftaSonu.setText("Hafta sonu");
        chBoxHaftaSonu.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chBoxHaftaSonuStateChanged(evt);
            }
        });

        chBoxHaftaici.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        chBoxHaftaici.setSelected(true);
        chBoxHaftaici.setText("Hafta içi");
        chBoxHaftaici.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chBoxHaftaiciStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        jLabel1.setText("Fiyat aralığı");

        txtMax.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        txtMax.setText("700");
        txtMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtMaxMouseEntered(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        jLabel2.setText("   =>");

        txtMin.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        txtMin.setText("100");
        txtMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtMinMouseEntered(evt);
            }
        });

        jScrollPane3.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N

        tbl_acilanKursalar.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        tbl_acilanKursalar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_acilanKursalar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_acilanKursalarMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_acilanKursalar);

        tbl_kurs_gunleri_ekle.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        tbl_kurs_gunleri_ekle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_kurs_gunleri_ekle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_kurs_gunleri_ekleMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_kurs_gunleri_ekle);

        jButton1.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        jButton1.setText("Ekle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1581, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMax, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chBoxHaftaSonu, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(chBoxHaftaici, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1593, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMin)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMax)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(chBoxHaftaSonu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chBoxHaftaici, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Kurs Ekle", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_kurslarimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_kurslarimMouseClicked
        // TODO add your handling code here:
        kurs_gunleri_modeli.setRowCount(0);
        for (KursiyerKurslar kursiyerKurslar : thisKursiyer.getKursiyerKurslarCollection()) {
            for (KursGunleri kursGunleri : kursiyerKurslar.getAcilanKurs().getKursGunleriCollection()) {
                //seçilen satrın kurs ismi kursiyerin kurslarında bir kur ismine işitse 
                if (tbl_kurslarim.getValueAt(tbl_kurslarim.getSelectedRow(), 1).toString()
                        .equals(kursiyerKurslar.getAcilanKurs().getId().toString())) {
                    kurs_gunleri_modeli.addRow(new Object[]{kursGunleri.getGun(), kursGunleri.getBasSaati(), kursGunleri.getBitisSaati()
                    });

                }

            }
        }

    }//GEN-LAST:event_tbl_kurslarimMouseClicked

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:

        if (jDateChooser1.getDate() != null) {
            acilanKursTablosuDoldur(chBoxHaftaici.isSelected(), chBoxHaftaSonu.isSelected(), Integer.parseInt(txtMax.getText()), Integer.parseInt(txtMin.getText()));
        }


    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void tbl_acilanKursalarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_acilanKursalarMouseClicked
        // TODO add your handling code here:
        kurs_gunleri_ekle_modeli.setRowCount(0);
        try {
            for (AcilanKurs acilanKurs : acilanKurslar) {
                for (KursGunleri kursGunleri : acilanKurs.getKursGunleriCollection()) {
                    if (tbl_acilanKursalar.getValueAt(tbl_acilanKursalar.getSelectedRow(), 1).toString()
                            .equals(acilanKurs.getId().toString())) {
                        kurs_gunleri_ekle_modeli.addRow(new Object[]{kursGunleri.getGun(), kursGunleri.getBasSaati(), kursGunleri.getBitisSaati()
                        });

                    }

                }
            }
        } catch (Exception e) {
            System.out.println("hata");
        }

    }//GEN-LAST:event_tbl_acilanKursalarMouseClicked

    private void chBoxHaftaSonuStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chBoxHaftaSonuStateChanged
        // TODO add your handling code here:
        acilanKursTablosuDoldur(chBoxHaftaici.isSelected(), chBoxHaftaSonu.isSelected(), Integer.parseInt(txtMax.getText()), Integer.parseInt(txtMin.getText()));
    }//GEN-LAST:event_chBoxHaftaSonuStateChanged

    private void chBoxHaftaiciStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chBoxHaftaiciStateChanged
        // TODO add your handling code here:
        acilanKursTablosuDoldur(chBoxHaftaici.isSelected(), chBoxHaftaSonu.isSelected(), Integer.parseInt(txtMax.getText()), Integer.parseInt(txtMin.getText()));
    }//GEN-LAST:event_chBoxHaftaiciStateChanged

    private void txtMinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMinMouseEntered
        // TODO add your handling code here:
        acilanKursTablosuDoldur(chBoxHaftaici.isSelected(), chBoxHaftaSonu.isSelected(), Integer.parseInt(txtMax.getText()), Integer.parseInt(txtMin.getText()));
    }//GEN-LAST:event_txtMinMouseEntered

    private void txtMaxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaxMouseEntered
        // TODO add your handling code here:
        acilanKursTablosuDoldur(chBoxHaftaici.isSelected(), chBoxHaftaSonu.isSelected(), Integer.parseInt(txtMax.getText()), Integer.parseInt(txtMin.getText()));

    }//GEN-LAST:event_txtMaxMouseEntered

    private void tbl_kurs_gunleri_ekleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_kurs_gunleri_ekleMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbl_kurs_gunleri_ekleMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (tbl_acilanKursalar.getSelectedRow() != -1) {
        // kursiyerin aldığı kursların günleri
        // seçilen kurs bilgileri
         
        AcilanKurs secilenKurs = null;
        for (AcilanKurs acilanKurs : acilanKurslar) {
            if (tbl_acilanKursalar.getValueAt(tbl_acilanKursalar.getSelectedRow(), 1).toString()
                    .equals(acilanKurs.getId().toString())) {
                secilenKurs = acilanKurs;
            }
        }
        ////////////////////
        // secile kursla aynı tarihli kursların günleri
        boolean tarihGecmis = false;
        kursiyerGunleri.clear();
       
            // set kurss guleri
            for (KursiyerKurslar kursiyerKurslar : thisKursiyer.getKursiyerKurslarCollection()) {
                for (KursGunleri kursGunleri1 : kursiyerKurslar.getAcilanKurs().getKursGunleriCollection()) {
                    // kurs bitmemişse

                    if (!kursiyerGunleri.contains(kursGunleri1.getGun())) {
                        if (checkTimeOverlaps(secilenKurs.getBasTarih(), secilenKurs.getBitisTarih(),
                                kursiyerKurslar.getAcilanKurs().getBasTarih(), kursiyerKurslar.getAcilanKurs().getBitisTarih())) {
                            kursiyerGunleri.add(kursGunleri1.getGun());
                        }
                    }

                }

            }
            // get tablodan seçilen kurs bilgileri
            boolean cakısma = false;

            dis:
            for (AcilanKurs acilanKurs : acilanKurslar) {
                for (KursGunleri kursGunleri : acilanKurs.getKursGunleriCollection()) {
                    if (tbl_acilanKursalar.getValueAt(tbl_acilanKursalar.getSelectedRow(), 1).toString()
                            .equals(acilanKurs.getId().toString())) {
                        secilenKurs = acilanKurs;
                        if (secilenKurs.getBasTarih().before(new Date())) {
                            tarihGecmis = true;
                            break dis;
                        } else {

                            if (kursiyerGunleri.contains(kursGunleri.getGun())) {

                                cakısma = true;
                                break;
                            }
                        }

                    }
                }
            }

            //oğrenci sayısı kontrolu
            boolean ogrenciSayisiKontrolu = Integer.valueOf(secilenKurs.getKursId().getMaxOgrenci().toString())
                    > Integer.valueOf(secilenKurs.getKayitSayisi().toString());

            // database ekleme
            if (ogrenciSayisiKontrolu && (!cakısma) && (!tarihGecmis)) {
                new odemeFrm().setVisible(true);
                System.out.println("eklendi");
                try {

                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Elasanatlari_Kurs_SecimiPU");

                    EntityManager em = emf.createEntityManager();
                    em.getTransaction().begin();

                    KursiyerKurslar newKurs = new KursiyerKurslar(new KursiyerKurslarPK(secilenKurs.getId().toBigInteger(), thisKursiyer.getId().toBigInteger()), new BigInteger("0"), "kredi", "", secilenKurs, thisKursiyer);
                    em.persist(newKurs);

                    em.getTransaction().commit();
                    //update tablolar
                    //kurslarım

                    thisKursiyer.getKursiyerKurslarCollection().add(newKurs);

                    kurslarımTablosuDoldur();
                    acilanKursTablosuDoldur(chBoxHaftaici.isSelected(), chBoxHaftaSonu.isSelected(), Integer.parseInt(txtMax.getText()), Integer.parseInt(txtMin.getText()));
                    //update açılan kursun kayıt sayısı                  
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    //creating connection with the database
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "maya", "hr");

                    String query = "update acilan_kurs set KAYIT_SAYISI=" + secilenKurs.getKayitSayisi().add(BigInteger.ONE) + " where id=" + secilenKurs.getId();

                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);

                    ////////
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(kursSecimi.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(kursSecimi.class.getName()).log(Level.SEVERE, null, ex);
                }
                kurs_gunleri_ekle_modeli.setRowCount(0);
            } else {
                if (tarihGecmis) {
                    JOptionPane.showMessageDialog(rootPane, "Kursun başlangıç tarihi geçmiş");
                } else if (!ogrenciSayisiKontrolu) {
                    JOptionPane.showMessageDialog(rootPane, "kontenjan doldu");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Çakışma var");

                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public static boolean checkTimeOverlaps(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
        if (startDate1 == null || endDate1 == null || startDate2 == null || endDate2 == null) {
            return false;
        }

        if ((startDate1.getTime() <= endDate2.getTime()) && (startDate2.getTime() <= endDate1.getTime())) {
            return true;
        }

        return false;
    }

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
            java.util.logging.Logger.getLogger(kursSecimi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kursSecimi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kursSecimi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kursSecimi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kursSecimi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chBoxHaftaSonu;
    private javax.swing.JCheckBox chBoxHaftaici;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JLabel lblName;
    private javax.swing.JTable tbl_acilanKursalar;
    private javax.swing.JTable tbl_kurs_gunleri;
    private javax.swing.JTable tbl_kurs_gunleri_ekle;
    private javax.swing.JTable tbl_kurslarim;
    private javax.swing.JTextField txtMax;
    private javax.swing.JTextField txtMin;
    // End of variables declaration//GEN-END:variables
}
