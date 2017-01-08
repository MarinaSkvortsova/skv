package dao;

import model.Cex;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class CexDAO {

    private final DataSource dataSource;
    ResultSet rs = null;

    public CexDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Cex> list() {
        List<Cex> listObj = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cex ORDER BY CodeCex");
                ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                Cex obj = new Cex();
                obj.setCodeCex(rs.getInt(1));
                obj.setNameCex(rs.getString("nameCex"));
                obj.setCountWorker(rs.getInt("countWorker"));

                listObj.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listObj;
    }

    public Cex get(int CodeCex) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Cex WHERE CodeCex = ?");) {
            ps.setInt(1, CodeCex);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cex obj = new Cex();
                obj.setCodeCex(rs.getInt("CodeCex"));
                obj.setNameCex(rs.getString("NameCex"));
                obj.setCountWorker(rs.getInt("CountWorker"));

                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int save(Cex obj) throws SQLException {
        if (obj.getCodeCex() == 0) {
            return insert(obj);
        } else {
            return update(obj);
        }
    }
    
    
     public int getLastId() {
        int id = 0;
        try (Connection con = dataSource.getConnection()) {
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT * FROM cex ORDER BY codecex DESC");
            rs.next();
            id = rs.getInt("codecex");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return id + 1;
    }

    public int insert(Cex obj) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT COUNT(*) FROM Cex");
            rs.next();
            Long idLastRecord = rs.getLong(1);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Cex (CodeCex, NameCex,CountWorker ) VALUES(?,?,?)");
            ps.setLong(1, idLastRecord + 1);
            ps.setString(2, obj.getNameCex());
            ps.setInt(3, obj.getCountWorker());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return 0;
    }

    public int update(Cex obj) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Cex SET NameCex = ?, CountWorker=? WHERE CodeCex = ?");) {
            preparedStatement.setInt(3, obj.getCodeCex());
            preparedStatement.setString(1, obj.getNameCex());
            preparedStatement.setInt(2, obj.getCountWorker());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public String getNameCex(int id) {
        String nameCex = "";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cex WHERE codecex=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            nameCex = rs.getString("namecex");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return nameCex;
    }
    
    public List<String> getNameCexUpdate(int codeCex) {
        List<String> massCodeCex = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT codecex, namecex FROM cex WHERE codecex=?");
            ps.setInt(1, codeCex);
            rs = ps.executeQuery();
            rs.next();
            massCodeCex.add(rs.getString("codecex"));
            massCodeCex.add(rs.getString("namecex"));
            PreparedStatement ps2 = con.prepareStatement("SELECT codecex, namecex FROM cex");
            rs = ps2.executeQuery();
            while (rs.next()) {
                if (!(rs.getString(2)).equals(massCodeCex.get(1))) {
                    massCodeCex.add(rs.getString("codecex"));
                    massCodeCex.add(rs.getString("namecex"));
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return massCodeCex;
    }

    public void delete(int CodeCex) throws SQLException {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Cex WHERE CodeCex = ?");) {
            preparedStatement.setInt(1, CodeCex);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
;

}
