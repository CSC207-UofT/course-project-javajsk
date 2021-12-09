package businessrules.outputboundaries;

import org.json.JSONObject;

/**
 * Object containing information for adapters.presenters to display
 */
public class ResponseObject {
    /**
     * The Status.
     */
    int status;
    /**
     * The Message.
     */
    String message;
    /**
     * The Contents.
     */
    Object contents;

    /**
     * Instantiates a responseObject
     *
     * @param status   integer indicating status of response
     * @param message  message to return to controller
     * @param contents object to return to controller
     */
    public ResponseObject(int status, String message, Object contents) {
        this.status = status;
        this.message = message;
        this.contents = contents;
    }

    /**
     * A method that returns the message of the responseObject
     *
     * @return message of responseObjects
     */
    public String getMessage() {
        return message;
    }

    /**
     * A method that sets the message of the responseObject
     *
     * @param newMessage new message for responseObject
     */
    public void setMessage(String newMessage) {
        this.message = newMessage;
    }

    /**
     * A method that returns the contents of the responseObject
     *
     * @return contents of responseObjects
     */
    public Object getContents() {
        return contents;
    }

    /**
     * A method that sets the contents of the responseObject
     *
     * @param newContent new contents for responseObject
     */
    public void setContents(Object newContent) {
        this.contents = newContent;
    }

    /**
     * A method that returns the status of the responseObject
     *
     * @return status of responseObjects
     */
    public int getStatus() {
        return status;
    }

    /**
     * A method that sets the status of the responseObject
     *
     * @param newStatus new contents for responseObject
     */
    public void setStatus(int newStatus) {
        this.status = newStatus;
    }

    /**
     * Method returns cart as a string representation
     *
     * @return string representation of cart
     */
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        jsonObject.put("message", message);
        jsonObject.put("contents", contents.toString());
        return jsonObject.toString();
    }
}
