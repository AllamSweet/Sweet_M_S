package sweet_app;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagment {
    private static UserManagment instance;
    private static final Logger logger = Logger.getLogger(UserManagment.class.getName());

    private Map<String, UserAcount > ownerDeatails = new HashMap<>();
    public UserManagment.UserAcount getUserAcount(String name) {

        return ownerDeatails.get(name);
    }

    private String errorMessage;
    private boolean adminIsLoggedIn;

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean userAccountExists(String name){
return ownerDeatails.containsKey(name);
    }
    public static UserManagment getInstance() {
        if (instance == null) {
            instance = new UserManagment();
        }
        return instance;
    }

    public static void setInstance(UserManagment instance) {
        UserManagment.instance = instance;
    }


    public UserManagment() {
       this.adminIsLoggedIn=false;
    }

    public boolean isAdminIsLoggedIn() {
        return adminIsLoggedIn;
    }

    public void setAdminIsLoggedIn(boolean adminIsLoggedIn) {
        this.adminIsLoggedIn = adminIsLoggedIn;
    }

public static class UserAcount{

         private String name;
         private String role;
         private String email;
         private String phone;


    public UserAcount(String name,String role,String email,String phone) {
        this.email=email;
        this.name=name;
        this.role=role;
        this.phone=phone;



    }

    public String getRole() {
        return role;
    }



    public String getPhone() {
        return phone;
    }



    public String getEmail() {
        return email;
    }



    public String getName() {
        return name;
    }


}
public void adduserAccount(UserAcount userAcount){
        logger.info("Attempting to add user account "+userAcount);
        if (userAccountExists(userAcount.getEmail())){
            errorMessage="User account wiht email:"+userAcount.getEmail();
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
        if (userAcount.getName()==null||userAcount.getName().isEmpty()||
                userAcount.getEmail()==null||userAcount.getEmail().isEmpty()||
                userAcount.getPhone()==null||userAcount.getPhone().isEmpty()||
                 userAcount.getRole()==null||userAcount.getRole().isEmpty()){
            logger.warning("Invalid user account details:"+userAcount);
            errorMessage="Invalid user account details.";
            throw new IllegalStateException(errorMessage);

        }


        ownerDeatails.put(userAcount.getName(),userAcount);
        logger.info("User account with email:"+userAcount.getEmail()+"is added successfully.");



}
    public void updateOwner(UserAcount userAcount ) {

        String name = userAcount.getName();
        if (!ownerDeatails.containsKey(name)) {

            errorMessage = "their is no such Owner named";
            logger.severe(errorMessage);
            throw new IllegalStateException(errorMessage);
        } else {
            if (userAcount.getName()==null||userAcount.getName().isEmpty()||
                    userAcount.getEmail()==null||userAcount.getEmail().isEmpty()||
                    userAcount.getPhone()==null||userAcount.getPhone().isEmpty()||
                    userAcount.getRole()==null||userAcount.getRole().isEmpty()) {
                logger.warning("invalid Owner details" + userAcount);
                errorMessage = "invalid details";
                throw new IllegalStateException(errorMessage);
            }
            ownerDeatails.put(name, userAcount);
            logger.info("OWNER name " + name + "update successfully");
        }
    }

    public void removeAccount(String name) {
        logger.info("deleting account :" + name);
        if (!userAccountExists(name)) {
            errorMessage = "account with name " + name + "does not exist";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
        ownerDeatails.remove(name);

        logger.info("deleting account :" + name + "is don");


    }

}


