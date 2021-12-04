package businessrules.outputboundaries;

public class ResponseObject {
    int status;
    String message;
    Object contents;

    public ResponseObject(int status, String message, Object contents) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



}
