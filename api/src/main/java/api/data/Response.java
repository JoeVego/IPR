package api.data;

public class Response {
    private final int statusCode;
    private final Object responseBody;

    public Response(int statusCode, Object responseObj) {
        this.statusCode = statusCode;
        this.responseBody = responseObj;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", responseObject=" + responseBody +
                '}';
    }
}
