package main;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import sweet_app.*;
import sweet_app.StoresManager.Sale;

public class Main {
    private static void recordSale(StoresManager storesManager, Scanner scanner) {
        System.out.print("Enter store name: ");
        String storeName = scanner.nextLine();

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter quantity sold: ");
        int quantitySold = Integer.parseInt(scanner.nextLine());

        try {
            storesManager.recordSale(storeName, productName, quantitySold);
            System.out.println("Sale recorded successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error recording sale: " + e.getMessage());
        }
    }

    private static void setSellingPrice(StoresManager storesManager, Scanner scanner) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter selling price: ");
        double price = Double.parseDouble(scanner.nextLine());

        storesManager.setSelPrices(productName, price);
        System.out.println("Selling price set successfully.");
    }

    private static void setCostPrice(StoresManager storesManager, Scanner scanner) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter cost price: ");
        double price = Double.parseDouble(scanner.nextLine());

        storesManager.setCostPrices(productName, price);
        System.out.println("Cost price set successfully.");
    }

    private static void viewFinancialReport(StoresManager storesManager) {
        Map<String, Double> report = storesManager.genaratfinancialReport();
        System.out.println("Financial Report:");
        System.out.println("Total Sales: $" + report.getOrDefault("Total Sales", 0.0));
        System.out.println("Total Profit: $" + report.getOrDefault("Total profit", 0.0));
    }

    public static void main(String[] args) {
        AppSWEET app = AppSWEET.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Sweet Management System");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    String page = app.logtosystem(loginUsername, loginPassword);

                    if (!app.LoginPage()) {
                        System.out.println("Login successful! Redirecting to " + page);
                        redirectToPage(page, app);
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String signupUsername = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Confirm password: ");
                    String confirmPassword = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter type (admin/owner/user): ");
                    String type = scanner.nextLine();

                    AppSWEET.User newUser = new AppSWEET.User(signupUsername, email, password, confirmPassword, phone, age, type);

                    try {
                        app.signupUser(newUser);
                        System.out.println("Signup successful! You can now log in.");
                    } catch (IllegalStateException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }

    // Method to redirect user to their specific page
    public static void redirectToPage(String page, AppSWEET app) {
        Scanner scanner = new Scanner(System.in);
        StoresManager storesManager = StoresManager.getInstance();

        ContentManagement contentManagement = ContentManagement.getInstance();
        contentManagement.addFeedback("coky","mesh zaki",0);
        contentManagement.addPost("SWEET","did you like this sweet",List.of("ALLAM","HAITHAM"));

        switch (page) {
            case "Admin_Page":
                System.out.println("Welcome to the Admin Page");
                while (true) {
                    System.out.println("Admin Menu:");
                    System.out.println("1. manage feedback");
                    System.out.println("2. manage post");
                    System.out.println("3. set selling price");
                    System.out.println("4. manage recipes");
                    System.out.println("5. View Best Selling Products");
                    System.out.println("6. manage owners ");
                    System.out.println("7. user statistic ");
                    System.out.println("8. cost price ");
                    System.out.println("9.record sales  ");
                    System.out.println("10.view sales report");
                    System.out.println("11. loge out ");
                    System.out.print("Choose an option: ");
                    int adminOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (adminOption) {
                        case 1:
                            System.out.println("1. View Feedback");
                            System.out.println("2. Respond to Feedback");
                            System.out.println("3. Delete Feedback");
                            int feedbackOption = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            switch (feedbackOption) {
                                case 1:
                                    List<ContentManagement.Feedback> feedbackList = contentManagement.getFeedbackList();
                                    if (feedbackList.isEmpty()) {
                                        System.out.println("No feedback available.");
                                    } else {
                                        for (ContentManagement.Feedback feedback : feedbackList) {
                                            System.out.println("Recipe: " + feedback.getRecipeTitle());
                                            System.out.println("Comment: " + feedback.getComment());
                                            System.out.println("Rating: " + feedback.getRating());
                                            System.out.println("Response: " + feedback.getResponse());
                                            System.out.println("------------");
                                        }
                                    }
                                    break;
                                case 2:
                                    System.out.print("Enter Recipe Title to Respond: ");
                                    String recipeTitle = scanner.nextLine();
                                    System.out.print("Enter Response: ");
                                    String response = scanner.nextLine();
                                    contentManagement.respondToFeedback(recipeTitle, response);
                                    break;
                                case 3:
                                    System.out.print("Enter Comment to Delete Feedback: ");
                                    String comment = scanner.nextLine();
                                    contentManagement.deleteToFeedback(comment);
                                    break;
                                default:
                                    System.out.println("Invalid option. Please choose again.");
                                    break;
                            }
                            break;

                        case 2:
                            System.out.println("1. Add Post");
                            System.out.println("2. Update Post");
                            System.out.println("3. Delete Post");
                            int postOption = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            switch (postOption) {
                                case 1:
                                    System.out.print("Enter Post Title: ");
                                    String postTitle = scanner.nextLine();
                                    System.out.print("Enter Post Content: ");
                                    String postContent = scanner.nextLine();
                                    System.out.print("Enter Post Tags (comma-separated): ");
                                    List<String> postTags = List.of(scanner.nextLine().split(","));
                                    contentManagement.addPost(postTitle, postContent, postTags);
                                    break;
                                case 2:
                                    System.out.print("Enter Old Post Title: ");
                                    String oldPostTitle = scanner.nextLine();
                                    System.out.print("Enter New Post Title: ");
                                    String newPostTitle = scanner.nextLine();
                                    System.out.print("Enter New Post Content: ");
                                    String newPostContent = scanner.nextLine();
                                    System.out.print("Enter New Post Tags (comma-separated): ");
                                    List<String> newPostTags = List.of(scanner.nextLine().split(","));
                                    contentManagement.updatePost(oldPostTitle, newPostTitle, newPostContent, newPostTags);
                                    break;
                                case 3:
                                    System.out.print("Enter Post Title to Delete: ");
                                    String deletePostTitle = scanner.nextLine();
                                    contentManagement.deletePost(deletePostTitle);
                                    break;
                                default:
                                    System.out.println("Invalid option. Please choose again.");
                                    break;
                            }
                            break;


                        case 3:setSellingPrice(storesManager,scanner);
                        break;


                        case 4:
                            System.out.println("1. Add Recipe");
                            System.out.println("2. Update Recipe");
                            System.out.println("3. Delete Recipe");
                            System.out.println("4. Search Recipes");
                            int recipeOption = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            switch (recipeOption) {
                                case 1:
                                    System.out.print("Enter Recipe Title: ");
                                    String recipeTitle = scanner.nextLine();
                                    System.out.print("Enter Ingredients (comma-separated): ");
                                    String ingredients = scanner.nextLine();
                                    System.out.print("Enter Preparation Steps: ");
                                    String preparation = scanner.nextLine();
                                    System.out.print("Enter Servings: ");
                                    String servings = scanner.nextLine();
                                    System.out.print("Enter Cooking Time: ");
                                    String cookingTime = scanner.nextLine();
                                    System.out.print("Enter Tags (comma-separated): ");
                                    List<String> tags = List.of(scanner.nextLine().split(","));
                                    contentManagement.addRecipe(recipeTitle, ingredients, preparation, servings, cookingTime, tags);
                                    break;
                                case 2:
                                    System.out.print("Enter Old Recipe Title: ");
                                    String oldRecipeTitle = scanner.nextLine();
                                    System.out.print("Enter New Recipe Title: ");
                                    String newRecipeTitle = scanner.nextLine();
                                    System.out.print("Enter New Ingredients (comma-separated): ");
                                    List<String> newIngredients = List.of(scanner.nextLine().split(","));
                                    System.out.print("Enter New Preparation Steps: ");
                                    String newPreparation = scanner.nextLine();
                                    System.out.print("Enter New Servings: ");
                                    String newServings = scanner.nextLine();
                                    System.out.print("Enter New Cooking Time: ");
                                    String newCookingTime = scanner.nextLine();
                                    System.out.print("Enter New Tags (comma-separated): ");
                                    List<String> newTags = List.of(scanner.nextLine().split(","));
                                    contentManagement.updateRecipe(oldRecipeTitle, newRecipeTitle, newIngredients, newPreparation, newServings, newCookingTime, newTags);
                                    break;
                                case 3:
                                    System.out.print("Enter Recipe Title to Delete: ");
                                    String deleteRecipeTitle = scanner.nextLine();
                                    contentManagement.deleteRecipe(deleteRecipeTitle);
                                    break;
                                case 4:
                                    System.out.print("Enter Keyword to Search Recipes: ");
                                    String searchKeyword = scanner.nextLine();
                                    List<ContentManagement.Recipe> searchResults = contentManagement.searchRecipes(searchKeyword);
                                    if (searchResults.isEmpty()) {
                                        System.out.println("No recipes found.");
                                    } else {
                                        for (ContentManagement.Recipe recipe : searchResults) {
                                            System.out.println("Recipe: " + recipe.getTitle());
                                            System.out.println("Ingredients: " + recipe.getIngredients());
                                            System.out.println("Tags: " + recipe.getTags());
                                            System.out.println("------------");
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid option. Please choose again.");
                                    break;
                            }
                            break;

                        case 5:
                            System.out.println("Best Selling Products by Store: " + storesManager.getBestSellingProductsByStore());
                            break;

                        case 6:
                            System.out.println("1. Add User Account");
                            System.out.println("2. Update User Account");
                            System.out.println("3. Remove User Account");
                            int userAccountOption = scanner.nextInt();
                            scanner.nextLine();
                            switch (userAccountOption) {
                                case 1:  // Add User Account
                                    System.out.print("Enter Name: ");
                                    String name = scanner.nextLine();
                                    System.out.print("Enter Role: ");
                                    String role = scanner.nextLine();
                                    System.out.print("Enter Email: ");
                                    String email = scanner.nextLine();
                                    System.out.print("Enter Phone: ");
                                    String phone = scanner.nextLine();
                                    UserManagment.UserAcount newUser = new UserManagment.UserAcount(name, role, email, phone);
                                    try {
                                        UserManagment.getInstance().adduserAccount(newUser);
                                        System.out.println("User account added successfully.");
                                    } catch (IllegalStateException e) {
                                        System.out.println("Error: " + e.getMessage());
                                    }
                                    break;

                                case 2:  // Update User Account
                                    System.out.print("Enter Name of User to Update: ");
                                    String updateName = scanner.nextLine();
                                    UserManagment.UserAcount existingUser = UserManagment.getInstance().getUserAcount(updateName);
                                    if (existingUser != null) {
                                        System.out.print("Enter New Role: ");
                                        String newRole = scanner.nextLine();
                                        System.out.print("Enter New Email: ");
                                        String newEmail = scanner.nextLine();
                                        System.out.print("Enter New Phone: ");
                                        String newPhone = scanner.nextLine();
                                        UserManagment.UserAcount updatedUser = new UserManagment.UserAcount(updateName, newRole, newEmail, newPhone);
                                        try {
                                            UserManagment.getInstance().updateOwner(updatedUser);
                                            System.out.println("User account updated successfully.");
                                        } catch (IllegalStateException e) {
                                            System.out.println("Error: " + e.getMessage());
                                        }
                                    } else {
                                        System.out.println("User not found.");
                                    }
                                    break;

                                case 3:  // Remove User Account
                                    System.out.print("Enter Name of User to Remove: ");
                                    String removeName = scanner.nextLine();
                                    try {
                                        UserManagment.getInstance().removeAccount(removeName);
                                        System.out.println("User account removed successfully.");
                                    } catch (IllegalStateException e) {
                                        System.out.println("Error: " + e.getMessage());
                                    }
                                    break;

                                default:
                                    System.out.println("Invalid option. Please choose again.");
                                    break;
                            }
                            break;
                        case 7:  System.out.println("User Statistics Menu:");
                            System.out.println("1. Register User");
                            System.out.println("2. View User Statistics by City");
                            System.out.print("Choose an option: ");
                            int statsOption = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (statsOption) {
                                case 1: // Register User
                                    System.out.print("Enter User Name: ");
                                    String userName = scanner.nextLine();
                                    System.out.print("Enter User City: ");
                                    String userCity = scanner.nextLine();
                                    GDisplayStatistics.getInstance().addUser(userName, userCity);
                                    break;

                                case 2: // View User Statistics by City
                                    Map<String, Integer> statsByCity = GDisplayStatistics.getInstance().gatherStatisticsByCity();
                                    if (statsByCity.isEmpty()) {
                                        System.out.println("No user statistics available.");
                                    } else {
                                        System.out.println("User Statistics by City:");
                                        for (Map.Entry<String, Integer> entry : statsByCity.entrySet()) {
                                            System.out.println("City: " + entry.getKey() + " - Users: " + entry.getValue());
                                        }
                                    }
                                    break;

                                default:
                                    System.out.println("Invalid option. Please choose again.");
                                    break;
                            }
                            break;
                        case 8:setCostPrice(storesManager,scanner);
                        break;

                        case 9:recordSale(storesManager,scanner);
                        break;
                        case 10:viewFinancialReport(storesManager);
                        break;
                        case 11:


                        default:
                            System.out.println("Invalid option. Please choose again.");
                            break;
                    }
                }


            case "Owner_Page":
                System.out.println("Welcome to the Owner Page");
                // Owner-specific functionality here
                break;

            case "User_Page":
                System.out.println("Welcome to the User Page");
                // User-specific functionality here
                break;

            default:
                System.out.println("Redirecting to login page.");
                break;


        }

    }
}
