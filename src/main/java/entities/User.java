package entities;

import businessrules.dai.Hasher;
import adapters.SHA512Hasher;
import businessrules.dai.Hasher;
import org.json.JSONObject;

public abstract class User{
    public String id;
    protected String userName;
    protected String hashedPassword;
    protected Hasher hasher;

    public User(String id, String userName, String hashedPassword) {
        this.hasher = new SHA512Hasher();
        this.id = id;
        this.userName = userName;
        this.hashedPassword = hasher.hash(hashedPassword);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.id);
        jsonObject.put("username", this.userName);
        jsonObject.put("hashedPassword", this.hashedPassword);

        return jsonObject.toString();
    }
}
