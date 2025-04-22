package bts.LabLune.modelo;

public class UserDTO {
    private Long id;
    private String username;
    private boolean isAdmin;

    // Constructor
    public UserDTO(Long id, String username, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}