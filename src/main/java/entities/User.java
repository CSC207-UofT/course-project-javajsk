package entities;

import businessrules.dai.Hasher;
import adapters.SHA512Hasher;
import businessrules.dai.Hasher;
import org.json.JSONObject;
/**
 * Abstract parent class User for Vendor and Customer entities
 */
public abstract class User{
    /**
     * The Id.
     */
    public String id;
    /**
     * The Username.
     */
    protected String userName;
    /**
     * The Hashed password.
     */
    protected String hashedPassword;
    protected Hasher hasher;

    /**
     * Instantiates a new User.
     *
     * @param id             the id
     * @param userName       the user name
     * @param hashedPassword the hashed password
     */
    public User(String id, String userName, String hashedPassword) {
        this.hasher = new SHA512Hasher();
        this.id = id;
        this.userName = userName;
        this.hashedPassword = hasher.hash(hashedPassword);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets username.
     *
     * @param userName the username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets hashed password.
     *
     * @return the hashed password
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Sets hashed password.
     *
     * @param hashedPassword the hashed password
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /**
     * Method returns User as a string representation
     *
     * @return string representation of user
     */
    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.id);
        jsonObject.put("username", this.userName);
        jsonObject.put("hashedPassword", this.hashedPassword);

        return jsonObject.toString();
    }
}
