package web;

import dao.CexDAO;
import dao.IncidentDAO;
import dao.StationsDAO;
import dao.EmployeeDAO;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.postgresql.ds.PGPoolingDataSource;

@WebListener
public class DBInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Init data source connection");
        PGPoolingDataSource poolingDataSource = new PGPoolingDataSource();
        poolingDataSource.setDataSourceName("Laba4");
        poolingDataSource.setServerName("localhost");
        poolingDataSource.setDatabaseName("kursach");
        poolingDataSource.setUser("postgres");
        poolingDataSource.setPassword("marinapupsik");
        poolingDataSource.setMaxConnections(8);
        poolingDataSource.setInitialConnections(1);

        IncidentDAO incidentDAO = new IncidentDAO(poolingDataSource);
        StationsDAO stationsDAO = new StationsDAO(poolingDataSource);
        EmployeeDAO employeeDAO = new EmployeeDAO(poolingDataSource);
        CexDAO cexDAO = new CexDAO(poolingDataSource);
        incidentDAO.setEmployeeDAO(employeeDAO);
        incidentDAO.setStationDAO(stationsDAO);

        sce.getServletContext().setAttribute("incidentDAO", incidentDAO);
        sce.getServletContext().setAttribute("stationsDAO", stationsDAO);
        sce.getServletContext().setAttribute("employeeDAO", employeeDAO);
        sce.getServletContext().setAttribute("cexDAO", cexDAO);
        sce.getServletContext().setAttribute("datasource", poolingDataSource);
        sce.getServletContext().log("Initialized all DAOs");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        PGPoolingDataSource poolingDataSource = (PGPoolingDataSource) sce.getServletContext().getAttribute("datasource");
        poolingDataSource.close();
        sce.getServletContext().log("Closing connections pool");
    }

}
