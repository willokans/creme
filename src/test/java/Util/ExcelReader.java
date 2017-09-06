package Util;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

/**
 * Created by will.okamuneh on 11/14/2016.
 */
public class ExcelReader  {




    public static Workbook getWorkBook() throws IOException, BiffException {
        // 0. Assign workbook
        File src = new File("src/test/java/Util/Expert Models Test Data.xls");

        // 1. Load WorkBook
        Workbook wb = Workbook.getWorkbook(src);

        return wb;
    }


    public static String EM_URL() throws IOException, BiffException {
        String baseURL = getWorkBook().getSheet("Login_TestData").getCell(2, 2).getContents();
        return baseURL;
    }

    public static String API_URL() throws IOException, BiffException {
        String apiUrl = getWorkBook().getSheet("Login_TestData").getCell(14, 2).getContents();
        return apiUrl;
    }

    public static String djangoEmail() throws IOException, BiffException {
        String djangoEmail = getWorkBook().getSheet("Login_TestData").getCell(14, 8).getContents();
        return djangoEmail;
    }

    public static String djangoPassword() throws IOException, BiffException {
        String djangoPassword = getWorkBook().getSheet("Login_TestData").getCell(14, 9).getContents();
        return djangoPassword;
    }

    public static String django_FirstName() throws IOException, BiffException {
        String firstName = getWorkBook().getSheet("Login_TestData").getCell(14, 6).getContents();
        return firstName;
    }

    public static String django_LastName() throws IOException, BiffException {
        String lastName = getWorkBook().getSheet("Login_TestData").getCell(14, 7).getContents();
        return lastName;
    }

    public static String EM_EmailAddress() throws IOException, BiffException {
        String em_EmailAddress = getWorkBook().getSheet("Login_TestData").getCell(2, 6).getContents();
        return em_EmailAddress;
    }

    public static String django_UserEmailAddress() throws IOException, BiffException {
        String UserEmailAddress = getWorkBook().getSheet("Login_TestData").getCell(2, 6).getContents();
        return UserEmailAddress;
    }

    public static String EM_Password() throws IOException, BiffException {
        String em_Password = getWorkBook().getSheet("Login_TestData").getCell(2, 7).getContents();
        return em_Password;
    }

    public static String django_UserPassword() throws IOException, BiffException {
        String django_UserPassword = getWorkBook().getSheet("Login_TestData").getCell(2, 7).getContents();
        return django_UserPassword;
    }

    public static String EM_EmailAddress2() throws IOException, BiffException {
        String em_EmailAddress2 = getWorkBook().getSheet("Login_TestData").getCell(2, 14).getContents();
        return em_EmailAddress2;
    }

    public static String EM_Password2() throws IOException, BiffException {
        String em_Password2 = getWorkBook().getSheet("Login_TestData").getCell(2, 13).getContents();
        return em_Password2;
    }

    public static String EM_EmailAddress3() throws IOException, BiffException {
        String em_EmailAddress3 = getWorkBook().getSheet("Login_TestData").getCell(2,19).getContents();
        return em_EmailAddress3;
    }

    public static String EM_Password3() throws IOException, BiffException {
        String em_Password3 = getWorkBook().getSheet("Login_TestData").getCell(2, 20).getContents();
        return em_Password3;
    }

    public static String zoho_EmailAddress() throws IOException, BiffException {
        String zoho_EmailAddress = getWorkBook().getSheet("Login_TestData").getCell(9, 6).getContents();
        return zoho_EmailAddress;
    }

    public static String zoho_Password() throws IOException, BiffException {
        String zoho_Password = getWorkBook().getSheet("Login_TestData").getCell(9, 7).getContents();
        return zoho_Password;
    }

    public static String zoho_URL() throws IOException, BiffException {
        String zoho_URL = getWorkBook().getSheet("Login_TestData").getCell(8, 2).getContents();
        return zoho_URL;
    }

    public static String em_NewPassword() throws IOException, BiffException {
        String em_NewPassword = getWorkBook().getSheet("Login_TestData").getCell(2, 8).getContents();
        return em_NewPassword;
    }

    public static String em_NewPassword2() throws IOException, BiffException {
        String em_NewPassword2 = getWorkBook().getSheet("Login_TestData").getCell(2, 15).getContents();
        return em_NewPassword2;
    }










}
