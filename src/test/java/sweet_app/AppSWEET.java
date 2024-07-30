package sweet_app;

import org.checkerframework.checker.units.qual.C;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppSWEET {

   private boolean usernameAlreadyExists = false;
   private boolean ageValidationFailed = false;
   private boolean invalidPhoneNumber = false;
   private boolean allFieldsRequired = false;
   public boolean on_the_login_page = false;
   public boolean on_the_signup_page = false;
   public boolean on_the_forgotpassword_page = false;

   private String Entername;
   private String Enterpass;
   private String message;
   private String expPage;
   private String CONFpassword;
   private String age;
   private String email;
   private String PhoneNumber;

   private Map<String, UserTP> Valusers = new HashMap<String, UserTP>();

   public class UserTP {
      private String userType;
      private String password;

      public UserTP(String password, String type) {
         this.password = password;
         userType = type;
      }
   }

   public void fillSignUp(String name, String Password, String ConfirmPassword, String Email, String age, String PhoneNumber) {
      this.age = age;
      email = Email;
      CONFpassword = ConfirmPassword;
      Enterpass = Password;
      Entername = name;
      this.PhoneNumber = PhoneNumber;

   }

   public void clickLink(String LinkName) throws IllegalAccessException {
      LinkName = LinkName.trim();
      switch (LinkName) {
         case "sign_up":
            this.on_the_signup_page = true;
         case "forgot password":
            this.on_the_forgotpassword_page = true;
            break;
         default:
            throw new IllegalAccessException("Unknown link: " + LinkName);
      }
   }


   public void setValusers(List<Map<String, String>> users) {
      for (Map<String, String> user : users) {
         String username = user.get("Username");
         String password = user.get("Password");
         String userType = user.get("Role");
         Valusers.put(username, new UserTP(password, userType));
      }
   }
//   public void setValusers(List<Map<String,String>>users){
//
//      for (Map<String,String>user:users){
//         String username=user.get("username");
//         String password=user.get("password");
//         String usertype=user.get("usertype");
//         Valusers.put(username,new UserTP(password,usertype) );
//      }
//
//   }

   public String selectpage(String type) {
      switch (type) {
         case "user":
            return "user";
         case "storeowner":
            return "storeowner";
         case "rawmaterial":
            return "rawmaterial";
         default:
            return "admin";
      }
   }

   //   public String selectpage(String type){
//
//      if(type.equals("user")){
//         return "userpage";
//
//      } else if (type.equals("owner")) {
//         return "ownerpage";
//      }
//      else if(type.equals("rawmaterial")) {return "rawmaterialpage";
//      }else return "adminpage";
//   }
   public void setPassNAME(String username, String password) {
      Entername = username;
      Enterpass = password;

   }

   public void loginClicked() {
      if (Valusers.containsKey(Entername) && Valusers.get(Entername).password.equals(Enterpass)) {
         message = "Welcome, " + Valusers.get(Entername).userType;
         expPage = selectpage(Valusers.get(Entername).userType);
      } else {
         message = "Invalid username or password";
         expPage = "login page";
      }
   }

   public void signUpClicked() {
      // Reset error flags
      usernameAlreadyExists = false;
      ageValidationFailed = false;
      invalidPhoneNumber = false;
      allFieldsRequired = false;

      // Check if all fields are provided
      if (Entername == null || Entername.trim().isEmpty() ||
              Enterpass == null || Enterpass.trim().isEmpty() ||
              CONFpassword == null || CONFpassword.trim().isEmpty() ||
              email == null || email.trim().isEmpty() ||
              age == null || age.trim().isEmpty() ||
              PhoneNumber == null || PhoneNumber.trim().isEmpty()) {
         message = "All fields are required.";
         expPage = "signup page";
         return;
      }

      // Check if passwords match
      if (!Enterpass.equals(CONFpassword)) {
         message = "Passwords do not match.";
         expPage = "signup page";
         return;
      }

      // Check if the username already exists
      if (Valusers.containsKey(Entername)) {
         message = "Username already exists. Please choose another username.";
         expPage = "signup page";
         return;
      }

      // Check if age is valid (must be at least 18)
      try {
         int ageInt = Integer.parseInt(age);
         if (ageInt < 18) {
            message = "You must be at least 18 years old to sign up.";
            expPage = "signup page";
            return;
         }
      } catch (NumberFormatException e) {
         message = "Invalid age format.";
         expPage = "signup page";
         return;
      }

      // Check phone number format (basic validation)
      if (!PhoneNumber.matches("\\d{10}")) {
         message = "Invalid phone number format. Please enter a valid phone number.";
         expPage = "signup page";
         return;
      }

      // If all checks pass, proceed with sign-up
      message = "Welcome, " + Entername;
      expPage = "user";
   }




//   public void loginClicked() {
//      if (Valusers.containsKey(Entername) && Valusers.get(Entername).equals(Enterpass)) {
//         message = "Welcome, " + Entername;
//         expPage = Entername + " dashboard";
//      } else {
//         message = "Invalid username or password";
//         expPage = "login page";
//      }
//   }

   public String getErrorMessage() {
      if (usernameAlreadyExists) {
         return "Username already exists. Please choose another username.";
      } else if (ageValidationFailed) {
         return "You must be at least 18 years old to sign up.";
      } else if (invalidPhoneNumber) {
         return "Invalid phone number format. Please enter a valid phone number.";
      } else if (allFieldsRequired) {
         return "All fields are required.";
      } else {
         return "Sign-up failed. Please check your details.";
      }
   }
   public String  getMessage(){
      return message;

   }
   public String getRedirectedPage() {
      return expPage;
   }


}
