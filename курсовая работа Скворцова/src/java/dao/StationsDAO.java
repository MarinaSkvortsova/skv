package dao;

import model.Stations;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StationsDAO {

    private final DataSource dataSource;
    ResultSet rs = null;

    public StationsDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Stations> list() {
        List<Stations> listObj = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM stations ORDER BY CodeStation");
                ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                Stations obj = new Stations();
                obj.setCodeStation(rs.getInt(1));
                obj.setNameStation(rs.getString("nameStation"));
                obj.setCodeCex(rs.getInt("codeCex"));
                obj.setOlder(rs.getString("older"));
                listObj.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listObj;
    }

    public void createStations(Stations obj) {
        try (Connection con = dataSource.getConnection()) {
            // заношу station в бд
            PreparedStatement ps = con.prepareStatement("INSERT INTO stations (codeStation, nameStation, codeCex, older) VALUES(?,?,?,?)");
            ps.setLong(1, obj.getCodeStation());
            ps.setString(2, obj.getNameStation());
            ps.setInt(3, obj.getCodeCex());
            ps.setString(4, obj.getOlder());
            ps.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public Stations get(int CodeStation) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM stations WHERE CodeStation = ?");) {
            ps.setInt(1, CodeStation);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Stations obj = new Stations();
                obj.setCodeStation(rs.getInt("codeStation"));
                obj.setNameStation(rs.getString("nameStation"));
                obj.setCodeCex(rs.getInt("codeCex"));
                obj.setOlder(rs.getString("older"));

                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int save(Stations obj) throws SQLException {
        if (obj.getCodeStation() == 0) {
            return insert(obj);
        } else {
            return update(obj);
        }
    }

    public int update(Stations obj) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE stations SET nameStation = ?, codeCex=?, older=? WHERE codeStation=?");
            preparedStatement.setInt(4, obj.getCodeStation());
            preparedStatement.setString(1, obj.getNameStation());
            preparedStatement.setInt(2, obj.getCodeCex());
            preparedStatement.setString(3, obj.getOlder());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int insert(Stations obj) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT COUNT(*) FROM stations");
            rs.next();
            Long idLastRecord = rs.getLong(1);
            PreparedStatement ps = con.prepareStatement("INSERT INTO stations (codeStation, nameStation, codeCex, older) VALUES(?,?,?,?)");
            ps.setLong(1, idLastRecord + 1);
            ps.setString(2, obj.getNameStation());
            ps.setInt(3, obj.getCodeCex());
            ps.setString(4, obj.getOlder());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return 0;
    }

    public String getNameStationByID(int id) {
        String nameStation = "";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stations WHERE codestation=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            nameStation = rs.getString("namestation");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return nameStation;
    }
    
    public List<String> getNameStation(int codeStation) {
        List<String> massNameCodeStation = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT codestation, namestation FROM stations WHERE codestation=?");
            ps.setInt(1, codeStation);
            rs = ps.executeQuery();
            rs.next();
            massNameCodeStation.add(rs.getString("codestation"));
            massNameCodeStation.add(rs.getString("namestation"));
            PreparedStatement ps2 = con.prepareStatement("SELECT codestation, namestation FROM stations");
            rs = ps2.executeQuery();
            while (rs.next()) {
                if (!(rs.getString(2)).equals(massNameCodeStation.get(1))) {
                    massNameCodeStation.add(rs.getString("codestation"));
                    massNameCodeStation.add(rs.getString("namestation"));
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return massNameCodeStation;
    }

    public void delete(int CodeStation) throws SQLException {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM stations WHERE codeStation = ?");) {
            preparedStatement.setInt(1, CodeStation);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
