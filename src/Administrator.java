import java.util.ArrayList;

public class Administrator {

    /*
    The Administrator Class maneges the administrator and their getters and setters
*/

    private String administratorFirstName;
    private String administratorLastName;
    private String staffNumberID;
    private int administratorMail;
    private String loginName;
    private String loginPassword;
    private ArrayList<Administrator> administrators = new ArrayList<> ();

    public Administrator (String administratorFirstName, String administratorLastName, String staffNumberID, int administratorMail, String loginName, String loginPassword) {
        this.administratorFirstName = administratorFirstName;
        this.administratorLastName = administratorLastName;
        this.staffNumberID = staffNumberID;
        this.administratorMail = administratorMail;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
    }

    @Override
    public String toString () {
        return "Administrator{" +
                "administratorFirstName='" + administratorFirstName + '\'' +
                ", administratorLastName='" + administratorLastName + '\'' +
                ", staffNumberID='" + staffNumberID + '\'' +
                ", administratorMail=" + administratorMail +
                ", loginName='" + loginName + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                '}';
    }

    public String getAdministratorFirstName () {
        return administratorFirstName;
    }

    public void setAdministratorFirstName (String administratorFirstName) {
        this.administratorFirstName = administratorFirstName;
    }

    public String getAdministratorLastName () {
        return administratorLastName;
    }

    public void setAdministratorLastName (String administratorLastName) {
        this.administratorLastName = administratorLastName;
    }

    public String getStaffNumberID () {
        return staffNumberID;
    }

    public void setStaffNumberID (String staffNumberID) {
        this.staffNumberID = staffNumberID;
    }

    public int getAdministratorMail () {
        return administratorMail;
    }

    public void setAdministratorMail (int administratorMail) {
        this.administratorMail = administratorMail;
    }

    public String getLoginName () {
        return loginName;
    }

    public void setLoginName (String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword () {
        return loginPassword;
    }

    public void setLoginPassword (String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
