package dao;

import model.Incident;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IncidentDAO {

    private StationsDAO stationDAO;
    private EmployeeDAO employeesDAO;
    private final DataSource dataSource;
    ResultSet rs = null;

    public IncidentDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Incident> list() {
        List<Incident> listObj = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Incident ORDER BY CodeIncident");
                ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                Incident obj = new Incident();
                obj.setCodeIncident(rs.getInt(1));
                obj.setDescription(rs.getString("description"));
                obj.setCodeStation(rs.getInt("codeStation"));
                obj.setFoundEmployee(rs.getInt("foundEmployee"));
                obj.setCloseEmployee(rs.getInt("closeEmployee"));
                obj.setDetectionDate(rs.getString("detectionDate"));
                obj.setClosingPeriod(rs.getString("closingPeriod"));
                obj.setClosingDate(rs.getString("closingDate"));
                listObj.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listObj;
    }

    public void createIncident(Incident obj) {
        try (Connection con = dataSource.getConnection()) {
            // заношу инцидент в бд
            PreparedStatement ps = con.prepareStatement("INSERT INTO Incident (CodeIncident, Description,CodeStation,DetectionDate,ClosingPeriod, ClosingDate, FoundEmployee,CloseEmployee ) VALUES(?,?,?,?,?,?,?,?)");
            ps.setLong(1, obj.getCodeIncident());
            ps.setString(2, obj.getDescription());
            ps.setInt(3, obj.getCodeStation());
            ps.setString(4, obj.getDetectionDate());
            ps.setString(5, obj.getClosingPeriod());
            ps.setString(6, obj.getClosingDate());
            ps.setInt(7, obj.getFoundEmployee());
            ps.setInt(8, obj.getCloseEmployee());
            ps.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public Incident get(int CodeIncident) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Incident WHERE CodeIncident = ?");) {
            ps.setInt(1, CodeIncident);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Incident obj = new Incident();
                obj.setCodeIncident(rs.getInt("CodeIncident"));
                obj.setDescription(rs.getString("Description"));
                obj.setCodeStation(rs.getInt("CodeStation"));
                obj.setFoundEmployee(rs.getInt("FoundEmployee"));
                obj.setCloseEmployee(rs.getInt("CloseEmployee"));
                obj.setDetectionDate(rs.getString("DetectionDate"));
                obj.setClosingPeriod(rs.getString("ClosingPeriod"));
                obj.setClosingDate(rs.getString("ClosingDate"));
                obj.setStation(stationDAO.get(obj.getCodeStation()));
                obj.setEmployeeClose(employeesDAO.get(obj.getCloseEmployee()));
                obj.setEmployeeFound(employeesDAO.get(obj.getFoundEmployee()));
                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int save(Incident obj) throws SQLException {
        if (obj.getCodeIncident() == 0) {
            return insert(obj);
        } else {
            return update(obj);
        }
    }

    public int update(Incident obj) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE incident SET description = ?, codestation=?, detectiondate=?, closingperiod=?, closingdate=?, foundemployee=?, closeemployee=? WHERE codeincident=?");
            preparedStatement.setInt(8, obj.getCodeIncident());
            preparedStatement.setString(1, obj.getDescription());
            preparedStatement.setInt(2, obj.getCodeStation());
            preparedStatement.setString(3, obj.getDetectionDate());
            preparedStatement.setString(4, obj.getClosingPeriod());
            preparedStatement.setString(5, obj.getClosingDate());
            preparedStatement.setInt(6, obj.getFoundEmployee());
            preparedStatement.setInt(7, obj.getCloseEmployee());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int getLastId() {
        int id = 0;
        try (Connection con = dataSource.getConnection()) {
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT * FROM incident ORDER BY codeincident DESC");
            rs.next();
            id = rs.getInt("codeincident");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return id + 1;
    }

    public int insert(Incident obj) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT COUNT(*) FROM Incident");
            rs.next();
            Long idLastRecord = rs.getLong(1);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Incident (CodeIncident, Description,CodeStation,DetectionDate,ClosingPeriod, ClosingDate, FoundEmployee,CloseEmployee ) VALUES(?,?,?,?,?,?,?,?)");
            ps.setLong(1, idLastRecord + 1);
            ps.setString(2, obj.getDescription());
            ps.setInt(3, obj.getCodeStation());
            ps.setString(4, obj.getDetectionDate());
            ps.setString(5, obj.getClosingPeriod());
            ps.setString(6, obj.getClosingDate());
            ps.setInt(7, obj.getFoundEmployee());
            ps.setInt(8, obj.getCloseEmployee());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return 0;
    }

    public void delete(int CodeIncident) throws SQLException {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Incident WHERE CodeIncident = ?");) {
            preparedStatement.setInt(1, CodeIncident);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public StationsDAO getStationDAO() {
        return stationDAO;
    }

    public void setStationDAO(StationsDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public EmployeeDAO getEmployeeDAO() {
        return employeesDAO;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeesDAO = employeeDAO;
    }

}
