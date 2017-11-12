package filetObish;

public abstract class Comment {
	//there is content and id
	public int id;
	public String content;
	public String toString() {
		return content;
	}
	public abstract void report(int id);

}
