package TurizmAcentasi.Model;

import TurizmAcentasi.Helper.DBConnector;
import TurizmAcentasi.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.DoubleStream;

public class Otel {
    private int id;
    private String name;
    private String address;
    private String mail;
    private String phone;
    private String star;
    private String property;

    private Otel employee;

    public Otel() {
    }

    public Otel(int id, String name, String address, String mail, String phone, String star, String property) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.phone = phone;
        this.star = star;
        this.property = property;
    }

    public static boolean add(String name, String star, String property, String address, String phone, String mail) {
        String query = "INSERT INTO otel (name, star, property, address, phone, mail) VALUES (?,?,?,?,?,?)";
        Otel findOtel = Otel.getFetch(mail);
        if (findOtel != null) {
            Helper.showMessage("Bu otel daha önce sisteme eklenmiştir!");
            return false;
        } else {
                try {PreparedStatement pr=DBConnector.getInstance().prepareStatement(query);
                pr.setString(1,name);
                pr.setString(2,star);
                pr.setString(3,property);
                pr.setString(4,address);
                pr.setString(5,phone);
                pr.setString(6,mail);

                int response= pr.executeUpdate();
                if (response==-1){
                Helper.showMessage("error");
                }
                return response !=-1;
            }catch (SQLException e){
                e.printStackTrace();
            }
        return true;
        }

    }
    public static Otel getFetch(int id) {
        Otel obj = null;
        String query = "SELECT * FROM otel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Otel(rs.getInt("id"), rs.getString("name"), rs.getString("star"), rs.getString("property"), rs.getString("address"), rs.getString("phone"), rs.getString("mail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static Otel getFetch(String mail){
        Otel obj=null;
        String query="SELECT*FROM otel WHERE mail=?";
        try {
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,mail);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                obj = new Otel(rs.getInt("id"), rs.getString("name"), rs.getString("star"), rs.getString("property"), rs.getString("address"), rs.getString("phone"), rs.getString("mail"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public static ArrayList<Otel>getOtelList(){//Databaseden otel listesini çekiyoruz
        ArrayList<Otel>otelList=new ArrayList<>();
        Otel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs=st.executeQuery("SELECT*FROM otel");
            while (rs.next()){
                obj=new Otel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setStar(rs.getString("star"));
                obj.setProperty(rs.getString("property"));
                obj.setAddress(rs.getString("address"));
                obj.setPhone(rs.getString("phone"));
                obj.setMail(rs.getString("mail"));
                otelList.add(obj);

            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return otelList;
    }
    // girilen anahtar kelimeye göre search metodu
    public static ArrayList<Otel> searchOtelList(String query){
        ArrayList<Otel> otelList = new ArrayList<>();
        Otel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Otel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setStar(rs.getString("star"));
                obj.setProperty(rs.getString("property"));
                obj.setAddress(rs.getString("address"));
                obj.setPhone(rs.getString("phone"));
                obj.setMail(rs.getString("mail"));
                otelList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otelList;
    }


}
