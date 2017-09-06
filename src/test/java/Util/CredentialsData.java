package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CredentialsData {
	//static String path = "C:\\Workspace\\Automation\\git\\expert-models-regression-suite\\src\\test\\java\\Util\\CredentialsData.properties";
	static String path = "src/test/java/Util/CredentialsData.properties";
	
	public static String getBaseURL() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String baseURL = prop.getProperty("baseURL");
		return baseURL;
	}
	
	public static String getApiURL() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String apiURL = prop.getProperty("apiURL");
		return apiURL;
	}
	
	public static String getApiAdminUser() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String apiAdminUser = prop.getProperty("apiAdminUser");
		return apiAdminUser;
	}
	
	public static String getApiAdminPassword() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String apiAdminPassword = prop.getProperty("apiAdminPassword");
		return apiAdminPassword;
	}
	
	public static String getZohoURL() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String zohoURL = prop.getProperty("zohoURL");
		return zohoURL;
	}
	
	public static String getZohoEmailUser() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String zohoEmailUser = prop.getProperty("zohoEmailUser");
		return zohoEmailUser;
	}
	
	public static String getZohoEmailPassword() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String zohoEmailPassword = prop.getProperty("zohoEmailPassword");
		return zohoEmailPassword;
	}
	
	public static String getFirstName() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String firstName = prop.getProperty("firstName");
		return firstName;
	}
	
	public static String getLastName() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String lastName = prop.getProperty("lastName");
		return lastName;
	}
	
	/*public static String getTestUser() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String testUser = prop.getProperty("testUser");
		return testUser;
	}*/
	
	public static String getUserPassword() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String userPassword = prop.getProperty("userPassword");
		return userPassword;
	}
	
	public static String getNewPassword() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String newPassword = prop.getProperty("newPassword");
		return newPassword;
	}
	
	public static String getQAOrganisation() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String qaOrganisation = prop.getProperty("qaOrganisation");
		return qaOrganisation;
	}
	
	public static String getOtherOrganisation() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String otherOrganisation = prop.getProperty("otherOrganisation");
		return otherOrganisation;
	}
	
	public static String getEMPermanentUser() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String emPermanentUser = prop.getProperty("emPermanentUser");
		return emPermanentUser;
	}
	
	public static String getQADevUser() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String qaDevUser = prop.getProperty("qaDevUser");
		return qaDevUser;
	}
	
	public static String getEMHeaderUser() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		String emHeaderUser = prop.getProperty("emHeaderUser");
		return emHeaderUser;
	}

	public static String getImportUser() throws IOException{
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);

		String importUser = prop.getProperty("importUser");
		return importUser;
	}

    public static String getExportUser() throws IOException{
        FileInputStream fs = new FileInputStream(path);
        Properties prop = new Properties();
        prop.load(fs);

        String exportUser = prop.getProperty("exportUser");
        return exportUser;
    }

    public static String getAccAccessUser() throws IOException{
        FileInputStream fs = new FileInputStream(path);
        Properties prop = new Properties();
        prop.load(fs);

        String accAccessUser = prop.getProperty("accAccessUser");
        return accAccessUser;
    }

    public static String getFileSystemUser() throws IOException{
        FileInputStream fs = new FileInputStream(path);
        Properties prop = new Properties();
        prop.load(fs);

        String fileSystemUser = prop.getProperty("fileSystemUser");
        return fileSystemUser;
    }
    
    
    public static String getEditorUser() throws IOException{
        FileInputStream fs = new FileInputStream(path);
        Properties prop = new Properties();
        prop.load(fs);

        String editorUser = prop.getProperty("editorUser");
        return editorUser;
    }
    
    public static String getOtherOrgUser() throws IOException{
        FileInputStream fs = new FileInputStream(path);
        Properties prop = new Properties();
        prop.load(fs);

        String otherOrgUser = prop.getProperty("otherOrgUser");
        return otherOrgUser;
    }
	
}

