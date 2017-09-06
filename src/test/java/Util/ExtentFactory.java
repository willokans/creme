package Util;

import com.relevantcodes.extentreports.ExtentReports;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by will.okamuneh on 8/2/2016.
 */
public class ExtentFactory {

    public static ExtentReports getInstance() throws ParseException {

        //Date appended to Report.html
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("MM_dd_hh_mm_ss");
        String dateAsString = simpleDateFormat.format(new Date());




        ExtentReports extent;
        //String Path = "C:\\Workspace\\Automation\\Automation Results\\expertModels\\Test-Report\\" + dateAsString +".html";
        String Path = "test-output/automation-results/expert-models/test-report/"+dateAsString+".html";
        extent = new ExtentReports(Path, false);
        extent.config()
		        .documentTitle("Automation Report")
		        .reportName("Regression");
        		
        extent
                .addSystemInfo("Selenium Version", "2.52")
                .addSystemInfo("AUT", "EXPERT MODELS");
        		
        
        return extent;

    }


}
