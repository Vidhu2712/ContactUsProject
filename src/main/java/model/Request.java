package model;

public class Request {
    private int id;
    private String fullName;
    private String email;
    private String message;
    private boolean isArchived;

	public Request() {
		
	}
    public int getId() { return id; }
    
    public String getFullName() { return fullName; }
    
    public String getEmail() { return email; }
    
    public String getMessage() { return message; }
    
    public boolean isArchived() { return isArchived; }
    
    public void setId(int id) { this.id = id; }
    
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public void setEmail(String email) { this.email = email; }
    
    public void setMessage(String message) { this.message = message; }
    
    public void setArchived(boolean isArchived) { this.isArchived = isArchived; }
}

