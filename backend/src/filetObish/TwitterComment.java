package filetObish;

public class TwitterComment extends Comment{

	public TwitterComment(long id, String content) {
		this.id = id;
		this.content = content;
	}

	@Override
	public void report(int id) {
		System.out.println("REPORTEDDDDDD");
	}

}