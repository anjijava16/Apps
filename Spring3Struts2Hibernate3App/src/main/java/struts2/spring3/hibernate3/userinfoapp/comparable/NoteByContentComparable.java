package struts2.spring3.hibernate3.userinfoapp.comparable;

public class NoteByContentComparable implements Comparable<NoteByContentComparable>{
	private String content;
	
	@Override
	public int compareTo(NoteByContentComparable content) {
		return this.getContent().compareTo(content.getContent());
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
