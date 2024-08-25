package sweet_app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Communication {

    private static Communication instance;
    private Map<String,Communication.User>users=new HashMap<>();
    private Map<String, List<Message>>messages=new HashMap<>();
    public User getUserByName(String name) {
        return users.get(name);
    }

    public static class User{
        private String uname;
        private String role;

        private List<String>inbox;
        private List<String>notification;

        public User (String uname,String role){
            this.uname=uname;
            this.role=role;
            this.inbox=new ArrayList<>();

            this.notification=new ArrayList<>();


        }


        public List<String> getInbox() {
            return inbox;
        }

        public void setInbox(List<String> inbox) {
            this.inbox = inbox;
        }

        public List<String> getNotification() {
            return notification;
        }

        public void setNotification(List<String> notification) {
            this.notification = notification;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public void addMessage(String message){
        inbox.add(message);

        }
        public void addNotification(String notifications){
            notification.add(notifications);
        }


    }
    public static Communication getInstance() {
        if (instance==null){
            instance=new Communication();
        }
        return instance;
    }


    public void addUser(User user){

        if (users.containsKey(user.getUname())){
            throw new IllegalStateException(" The user "+user.getUname()+"already exist");

        }
        users.put(user.getUname(),user);
        System.out.println("User"+user.getUname()+"is added");
    }
    public static class Message {
        private User sender;
        private User receiver;
        private String content;


        public Message(User sender, User receiver, String content) {
            this.sender = sender;
            this.receiver = receiver;
            this.content = content;
        }

        public User getSender() {
            return sender;
        }

        public User getReceiver() {
            return receiver;
        }

        public String getContent() {
            return content;
        }
    }
    public List<Message> getMessagesForUser(String uname) {
        return messages.getOrDefault(uname, new ArrayList<>());
    }
    public void sendMessage(String senderName, String receiverName, String content) {
        User sender = users.get(senderName);
        User receiver = users.get(receiverName);

        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Sender or receiver does not exist.");
        }

        Message message = new Message(sender, receiver, content);

        if (!messages.containsKey(receiverName)) {
            messages.put(receiverName, new ArrayList<>());
        }

        messages.get(receiverName).add(message);
        receiver.addMessage(content);
        receiver.addNotification("You have received a new message from " + sender.getUname());
    }

}
