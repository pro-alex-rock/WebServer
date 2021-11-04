package webserver.model;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private String method;

    HttpMethod(String method) {
        this.method = method;
    }
}
