package filetObish;

public class TwitterComment implements Comment{

	public long id;
	public String content;
	
	public TwitterComment(long id, String content) {
		this.id = id;
		this.content = content;
	}
	
	@Override
	public void report(int id) {
		// TODO Auto-generated method stub
		
	}

}
