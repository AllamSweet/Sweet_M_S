package sweet_app;

import org.checkerframework.checker.units.qual.C;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppSWEET {


   public boolean on_the_login_page=false;
   public boolean on_the_signup_page=false;
   public boolean on_the_forgotpassword_page=false;

   private  String Entername;
   private String Enterpass;
   private String message;
   private String expPage;
   private String CONFpassword;
   private String age;
   private String email;
   private String PhoneNumber;

   private Map<String,UserTP>Valusers=new HashMap<String,UserTP>();
   public  class  UserTP{
      private String userType;
      private String password;
   public UserTP(String password,String type) {
     this.password = password;
      userType = type;
   }
   }

//public void fillSignUp(String name, String Password, String ConfirmPassword, String Email, String age, String PhoneNumber){
//      this.age=age;
//       email=Email;
//      CONFpassword= ConfirmPassword;
//       Enterpass=Password;
//      Entername=name;
//      this.PhoneNumber=PhoneNumber;
//
//}
//   public void clickLink(String LinkName) throws IllegalAccessException {
//      LinkName=LinkName.trim();
//      switch (LinkName){
//         case "sign_up":
//            this.on_the_signup_page=true;
//         case "forgot password":
//            this.on_the_forgotpassword_page=true;
//            break;
//         default:
//            throw new IllegalAccessException("Unknown link: " +LinkName);
//      }



   public void setValusers(List<Map<String,String>> users) {
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
   public void setPassNAME(String username,String password){
      Entername=username;
      Enterpass=password;

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
//   public void loginClicked() {
//      if (Valusers.containsKey(Entername) && Valusers.get(Entername).equals(Enterpass)) {
//         message = "Welcome, " + Entername;
//         expPage = Entername + " dashboard";
//      } else {
//         message = "Invalid username or password";
//         expPage = "login page";
//      }
//   }
   public String  getMessage(){
      return message;

   }
   public String getRedirectedPage() {
      return expPage;
   }


}
