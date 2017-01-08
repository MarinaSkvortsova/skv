package dao;

import model.Employee;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Incident;

public class EmployeeDAO {

    private final DataSource dataSource;
    ResultSet rs = null;

    public EmployeeDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

     public int getLastId() {
        int id = 0;
        try (Connection con = dataSource.getConnection()) {
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT * FROM employee ORDER BY codeemployee DESC");
            rs.next();
            id = rs.getInt("codeemployee");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return id + 1;
    }
     
    public List<Employee> list() {
        List<Employee> listObj = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee ORDER BY CodeEmployee");
                ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                Employee obj = new Employee();
                obj.setCodeEmployee(rs.getInt(1));
                obj.setFio(rs.getString("fio"));
                obj.setCodeCex(rs.getInt("codeCex"));
                obj.setPosition(rs.getString("position"));
                obj.setEmploymentDate(rs.getString("employmentDate"));

                listObj.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listObj;
    }

    public Employee get(int CodeEmployee) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Employee WHERE codeemployee = ?");) {
            ps.setInt(1, CodeEmployee);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employee obj = new Employee();
                obj.setCodeEmployee(rs.getInt("codeEmployee"));
                obj.setFio(rs.getString("fio"));
                obj.setCodeCex(rs.getInt("codeCex"));
                obj.setPosition(rs.getString("position"));
                obj.setEmploymentDate(rs.getString("employmentDate"));
//             

                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int save(Employee obj) throws SQLException {
        if (obj.getCodeEmployee() == 0) {
            return insert(obj);
        } else {
            return update(obj);
        }
    }

          public void createEmployee(Employee obj) {
        try (Connection con = dataSource.getConnection()) {
            // заношу инцидент в бд
            PreparedStatement ps = con.prepareStatement("INSERT INTO Employee (CodeEmployee, fio,CodeCex,Position,EmploymentDate ) VALUES(?,?,?,?,?)");
            ps.setLong(1, obj.getCodeEmployee());
            ps.setString(2, obj.getFio());
            ps.setInt(3, obj.getCodeCex());
            ps.setString(4, obj.getPosition());
            ps.setString(5, obj.getEmploymentDate());
      
            ps.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
          
    public int insert(Employee obj) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT COUNT(*) FROM Employees");
            rs.next();
            Long idLastRecord = rs.getLong(1);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Employee (CodeEmployee, FIO,CodeCex,Position, EmploymentDate) VALUES(?,?,?,?,?)");
            ps.setLong(1, idLastRecord + 1);
            ps.setString(2, obj.getFio());
            ps.setInt(3, obj.getCodeCex());
            ps.setString(4, obj.getPosition());
            ps.setString(5, obj.getEmploymentDate());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return 0;
    }

    public int update(Employee obj) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET fio=?, codecex=?, position=?, employmentdate=? WHERE codeemployee=?");
            preparedStatement.setInt(5, obj.getCodeEmployee());
            preparedStatement.setString(1, obj.getFio());
            preparedStatement.setInt(2, obj.getCodeCex());
            preparedStatement.setString(3, obj.getPosition());
            preparedStatement.setString(4, obj.getEmploymentDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public String getFoundEmployeeByID(int id) {
        String found = "";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee WHERE codeemployee=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            found = rs.getString("fio");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return found;
    }

    public String getCloseEmployeeByID(int id) {
        String found = "";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee WHERE codeemployee=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            found = rs.getString("fio");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return found;
    }

    // этот же метод будет возвращать список людей закрывших людей
    public List<String> getFoundEmployee(int foundEmployee) {
        List<String> massFoundEmployee = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT codeemployee, fio FROM employee WHERE codeemployee=?");
            ps.setInt(1, foundEmployee);
            rs = ps.executeQuery();
            rs.next();
            massFoundEmployee.add(rs.getString("codeemployee"));
            massFoundEmployee.add(rs.getString("fio"));
            PreparedStatement ps2 = con.prepareStatement("SELECT codeemployee, fio FROM employee");
            rs = ps2.executeQuery();
            while (rs.next()) {
                if (!(rs.getString(2)).equals(massFoundEmployee.get(1))) {
                    massFoundEmployee.add(rs.getString("codeemployee"));
                    massFoundEmployee.add(rs.getString("fio"));
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return massFoundEmployee;
    }
    
    public List<String> getFoundEmployeeStr(String foundEmployee) {
        List<String> massFoundEmployee = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT codeemployee, fio FROM employee WHERE fio=?");
            ps.setString(1, foundEmployee);
            rs = ps.executeQuery();
            rs.next();
            massFoundEmployee.add(rs.getString("codeemployee"));
            massFoundEmployee.add(rs.getString("fio"));
            PreparedStatement ps2 = con.prepareStatement("SELECT codeemployee, fio FROM employee");
            rs = ps2.executeQuery();
            while (rs.next()) {
                if (!(rs.getString(2)).equals(massFoundEmployee.get(1))) {
                    massFoundEmployee.add(rs.getString("codeemployee"));
                    massFoundEmployee.add(rs.getString("fio"));
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return massFoundEmployee;
    }

    public List<String> getPosition(int position) {
        List<String> massPosition = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT codeemployee, position FROM employee WHERE codeemployee=?");
            ps.setInt(1, position);
            rs = ps.executeQuery();
            rs.next();
            massPosition.add(rs.getString("codeemployee"));
            massPosition.add(rs.getString("position"));
            PreparedStatement ps2 = con.prepareStatement("SELECT codeemployee, position FROM employee");
            rs = ps2.executeQuery();
            while (rs.next()) {
                if (!(rs.getString(2)).equals(massPosition.get(1))) {
                    massPosition.add(rs.getString("codeemployee"));
                    massPosition.add(rs.getString("position"));
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return massPosition;
    }
    


    public void delete(int CodeEmployee) throws SQLException {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Employee WHERE CodeEmployee = ?");) {
            preparedStatement.setInt(1, CodeEmployee);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
;

}
