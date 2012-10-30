package ca.liu.springtest.entity;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class UserTest {
    
    @Test
    public void SchemaExportTest() {
    	SchemaExport se = new SchemaExport(new Configuration().configure());
    	se.create(true, false);
    }
}
