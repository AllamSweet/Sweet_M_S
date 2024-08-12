package sweet_app;

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

   private Map<String, UserTP> Valusers = new HashMap<>();
   private String accountName;
   private boolean isPasswordUpdated = false;
   private boolean isAccountNameUpdated = false;

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
            break;
         case "forgot password":
            this.on_the_forgotpassword_page = true;
            break;
         case "account_settings":
            // Simulate navigation to account settings
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


   public void setPassNAME(String username, String password) {
      Entername = username;
      Enterpass = password;
   }

   public void loginClicked() {
      System.out.println("Attempting to log in with username: " + Entername + " and password: " + Enterpass);
      if (Valusers.containsKey(Entername) && Valusers.get(Entername).password.equals(Enterpass)) {
         message = "Welcome, " + Valusers.get(Entername).userType;
         expPage = selectpage(Valusers.get(Entername).userType);
      } else {
         message = "Invalid username or password";
         expPage = "login page";
      }
      System.out.println("Redirected to page: " + expPage);
   }


   public void changeAccountName(String newName) {
      if (Valusers.containsKey(newName)) {
         message = "This name is already taken";
         isAccountNameUpdated = false;
      } else {
         accountName = newName;
         message = "Your account name has been updated to " + newName;
         isAccountNameUpdated = true;
      }
   }

   public void changePassword(String oldPassword, String newPassword) {
      if (Valusers.containsKey(Entername) && Valusers.get(Entername).password.equals(oldPassword)) {
         Valusers.get(Entername).password = newPassword;
         message = "Your password has been successfully changed";
         isPasswordUpdated = true;
      } else {
         message = "Current password is incorrect";
         isPasswordUpdated = false;
      }
   }

   public void saveChanges() {
      // Simulate saving changes
   }

   public String getAccountName() {
      return accountName;
   }

   public boolean isAccountNameUpdated() {
      return isAccountNameUpdated;
   }

   public boolean isPasswordUpdated() {
      return isPasswordUpdated;
   }

   public String getMessage() {
      return message;
   }

   public String getRedirectedPage() {
      return expPage;
   }

   // Existing methods for sign-up validation and error handling

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

   public String[] getUsernameAndPasswordByUserType(String userType) {
      for (Map.Entry<String, UserTP> entry : Valusers.entrySet()) {
         if (entry.getValue().userType.equalsIgnoreCase(userType)) {
            String username = entry.getKey();
            String password = entry.getValue().password;
            return new String[]{username, password};
         }
      }
      // Return null if no match is found
      return null;
   }
}
