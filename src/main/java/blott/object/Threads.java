package blott.object;

public class Threads {
	int threadID;
	String threadName;
	int creatorID;
	String timestamp;

	@Override
	public String toString() {
		return "Thread [threadID=" + threadID + ", threadName=" + threadName + ", creatorID=" + creatorID
				+ ", timestamp=" + timestamp + "]";
	}

	public Threads(int threadID, String threadName, int creatorID, String timestamp) {
		this.threadID = threadID;
		this.threadName = threadName;
		this.creatorID = creatorID;
		this.timestamp = timestamp;
	}

	public int getThreadID() {
		return threadID;
	}

	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public int getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(int creatorID) {
		this.creatorID = creatorID;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
