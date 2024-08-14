package sweet_app;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppSWEET {
   private static AppSWEET instance;
   private Map<String, User> users=new HashMap<>();
   private String lastErrorMessage;
   private String pagec;
   private static final Logger logger = Logger.getLogger(AppSWEET.class.getName());

   public static AppSWEET getInstance() {
      if (instance == null) {
         instance = new AppSWEET();
      }
      return instance;
   }
   public void addUser(String username, String email, String password, String confirmPassword, String phone, int age, String type) {
      users.put(username, new User(username,email ,password, confirmPassword,phone,age,type));
   }

   public String logtosystem(String username, String password) {
      User user = users.get(username);
      if (user != null && user.getPassword().equals(password)) {
         switch (user.getType()) {
            case "admin":
               pagec = "Admin_Page";
               break;
            case "owner":
               pagec = "Owner_Page";
               break;
            case "user":
               pagec = "User_Page";
               break;
         }
         return pagec;
      } else {
         pagec = "login_Page";
         return pagec;
      }
   }

   public boolean LoginPage() {
      return "login_Page".equals(pagec);
   }

   public String getNextPage(String username) {
      User user = users.get(username);
      if (user != null) {
         switch (user.getType()) {
            case "admin":
               return "admin_Page";
            case "owner":
               return "owner_Page";
            case "user":
               return "user_Page";
         }
      }
      return "login_Page";
   }
   public void signupUser(User user) {
      logger.info("Attempting to sign up user: " + user);
      if (userExists(user.getUsername())) {
         String errorMessage = "User with this name: " + user.getUsername() + " is exists.";
         logger.warning(errorMessage);
         throw new IllegalStateException(errorMessage);
      }

      if (user.getUsername() == null || user.getUsername().isEmpty()||user.getEmail() == null || user.getEmail().isEmpty()||user.getPhone()==null||user.getPhone().isEmpty()||user.getPassword()==null||user.getPassword().isEmpty()||user.getConfirmPassword()==null||user.getConfirmPassword().isEmpty()||user.getAge()<=0) {
         String errorMessage = "All fields can not be empty.";
         logger.warning(errorMessage);
         throw new IllegalStateException(errorMessage);
      }



      if (user.getPassword() == null || user.getPassword().length() < 8) {
         String errorMessage = "Password must be at least 8 characters long.";
         logger.warning(errorMessage);
         throw new IllegalStateException(errorMessage);
      }

      if (user.getPhone() == null || user.getPhone().length() != 10) {
         String errorMessage = "Phone number must be  10 characters long.";
         logger.warning(errorMessage);
         throw new IllegalStateException(errorMessage);
      }

      if (!user.getPassword().equals(user.getConfirmPassword())) {
         String errorMessage = "Password and confirm password must be the same.";
         logger.warning(errorMessage);
         throw new IllegalStateException(errorMessage);
      }
      users.put(user.getUsername(), user);
      pagec = "login_Page";
      logger.info("User with name: " + user.getUsername() + " signed up successfully.");
   }
   private boolean isValidEmail(String email) {
      String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
      return email != null && email.matches(emailRegex);
   }

   private boolean userExists(String name) {
      return users.containsKey(name);
   }
   public static class User {
      private String uname;
      private String email;
      private String password;
      private String confirmPassword;
      private String phone;
      private int age;
      private String type;

      public User(String username, String email, String password, String confirmPassword, String phone, int age, String type) {
         this.uname = username;
         this.email = email;
         this.password = password;
         this.confirmPassword = confirmPassword;
         this.phone = phone;
         this.age = age;
         this.type = type;
      }


      public String getUsername() {
         return uname;
      }

      public String getEmail() {
         return email;
      }

      public String getPassword() {
         return password;
      }

      public String getConfirmPassword() {
         return confirmPassword;
      }

      public String getPhone() {
         return phone;
      }

      public int getAge() {
         return age;
      }

      public String getType() {
         return type;
      }



      public boolean isPasswordValid() {
         return password != null && password.length() >= 8;
      }

      public boolean isConfirmPasswordValid() {
         return confirmPassword != null && confirmPassword.equals(password);
      }

      public boolean isPhoneValid() {
         return phone != null && phone.matches("\\d{10}");
      }

      public boolean isEmailValid() {
         return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
      }

      public boolean areAllFieldsFilled() {
         return uname != null && !uname.isEmpty() &&
                 email != null && !email.isEmpty() &&
                 password != null && !password.isEmpty() &&
                 confirmPassword != null && !confirmPassword.isEmpty() &&
                 phone != null && !phone.isEmpty() &&
                 age > 0 && type != null && !type.isEmpty();
      }

      public boolean isValidForSignUp() {
         return isPasswordValid() &&
                 isConfirmPasswordValid() &&
                 isPhoneValid() &&
                 isEmailValid() &&
                 areAllFieldsFilled();
      }

      @Override
      public String toString() {
         return "User{" +
                 "username='" + uname + '\'' +
                 ", email='" + email + '\'' +
                 ", phone='" + phone + '\'' +
                 ", age=" + age +
                 ", type='" + type + '\'' +
                 '}';
      }
   }



}
