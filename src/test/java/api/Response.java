package api;

public class Response {
    private int statusCode;
    private Object responceObject;

    public Response(int statusCode, Object responceObj) {
        this.statusCode = statusCode;
        this.responceObject = responceObj;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Object getResponceObject() {
        return responceObject;
    }
}
