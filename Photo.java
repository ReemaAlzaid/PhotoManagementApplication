public class Photo {
	private String path;
	private LinkedList<String> tags;

	// Constructer
	public Photo(String path, LinkedList<String> tags) {
		this.path = path;
		this.tags = tags;
	}

	// Return the path (full file name) of the photo. A photo is uniquely identified
	// by its path.
	public String getPath() {
		return this.path;
	}

	// Return all tags associated with the photo
	public LinkedList<String> getTags() {
		return this.tags;
	}

}
