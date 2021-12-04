package businessrules.outputboundaries;

public class ResponseObject {
    String status;
    String message;
    Object contents;

    public ResponseObject(String status, String message, Object contents) {
        this.status = status;
        this.message = message;
        this.contents = contents;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Object getContents() {
        return contents;
    }

    public void setContents(Object contents) {
        this.contents = contents;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
