package api.data;

public class Response {
    private int statusCode;
    private Object responceBody;

    public Response(int statusCode, Object responceObj) {
        this.statusCode = statusCode;
        this.responceBody = responceObj;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Object getResponceBody() {
        return responceBody;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", responceObject=" + responceBody +
                '}';
    }
}
