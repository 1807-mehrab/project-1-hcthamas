package blott.object;

public class Post {
	int postID;
	String message;
	int threadID;
	int creatorID;

	@Override
	public String toString() {
		return "Post [postId=" + postID + ", message=" + message + ", threadId=" + threadID + ", userId=" + creatorID
				+ ", flag=" + flag + ", created=" + created + "]";
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(int postId, String message, int threadId, int userId, boolean flag, String created) {
		super();
		this.postID = postId;
		this.message = message;
		this.threadID = threadId;
		this.creatorID = userId;
		this.flag = flag;
		this.created = created;
	}

	public int getPostId() {
		return postID;
	}

	public void setPostId(int postId) {
		this.postID = postId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getThreadId() {
		return threadID;
	}

	public void setThreadId(int threadId) {
		this.threadID = threadId;
	}

	public int getUserId() {
		return creatorID;
	}

	public void setUserId(int userId) {
		this.creatorID = userId;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	boolean flag;
	String created;
}
