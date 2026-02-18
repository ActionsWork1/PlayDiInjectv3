package pojo_data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestDataObjects {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Password")
    private String password;

    // Constructors
    public TestDataObjects() {} // Needed for Jackson
    public TestDataObjects(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters (Required for Jackson to map data)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
