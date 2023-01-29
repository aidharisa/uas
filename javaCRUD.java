package com.UAS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class javaCRUD {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/apotek";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    /**
     *
     */
    public static void main(String[] args) {

        try {
            // register driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                showMenu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void showMenu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Insert Data");
        System.out.println("2. Show Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertobat();
                    break;
                case 2:
                    showData();
                    break;
                case 3:
                    updateobat();
                    break;
                case 4:
                    deleteobat();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // METHOD TAMPILKAN DATA //
    static void showData() {

        String sql = "SELECT * FROM tbobat";

        try {
            rs = stmt.executeQuery(sql);

            System.out.println("+--------------------------------+");
            System.out.println("|    DATA OBAT  |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                String nama_Obat = rs.getString("nama_Obat");
                String Kegunaan = rs.getString("Kegunaan");

                System.out.println(String.format("|%s|--|%s|--|%s|--|%s|", nama_Obat, Kegunaan));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METHOD INSERT//
    public static void insertobat() {
        try {
            // ambil input dari user

            System.out.print("nama_Obat: ");
            String nama_Obat = input.readLine();
            System.out.print("Kegunaan: ");
            String Kegunaan = input.readLine().trim();

            // query simpan
            String sql = "INSERT INTO tbobat (nama_Obat, Kegunaan) VALUES('%s','%s', )";
            sql = String.format(sql, nama_Obat, Kegunaan);

            // simpan Anggota
            stmt.execute(sql);
            System.out.println("Data berhasil d insert...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METHOD UPDATE //
    static void updateobat() {
        try {

            // ambil input dari user
            System.out.print("nama_Obat: ");
            String nama_Obat = input.readLine().trim();
            System.out.print("Kegunaan: ");
            String Kegunaan = input.readLine().trim();

            // query update
            String sql = "UPDATE tbobat SET nama_Obat='%s', Kegunaan='%s'";
            sql = String.format(sql, nama_Obat, Kegunaan);

            // update data buku
            stmt.execute(sql);
            System.out.println("Data Berhasil di update...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // METHOD DELETE//
    static void deleteobat() {
        try {

            // ambil input dari user
            System.out.print("ID yang mau dihapus: ");
            int nama_Obat = Integer.parseInt(input.readLine());

            // buat query hapus
            String sql = String.format("DELETE FROM tbobat WHERE nama_Obat=%d", nama_Obat);

            // hapus data
            stmt.execute(sql);

            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}