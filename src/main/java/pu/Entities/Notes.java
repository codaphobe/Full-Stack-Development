package pu.Entities;

public class Notes {
    private int id;
    private int userId;
    private String title;
    private String content;
    private String createdAt;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	public Notes(int id, int userId, String title, String content, String createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
	}
	public Notes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "Notes [id=" + id + ", userId=" + userId + ", title=" + title + ", content=" + content + ", createdAt="
				+ createdAt + "]";
	}
    
    

    // Getters and Setters
    
    
	
	
}
