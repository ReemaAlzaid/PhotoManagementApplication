public class PhotoManager {
	private BST<LinkedList<Photo>> binarytree;
	private LinkedList<Photo> Photos;
	public PhotoManager() {
		binarytree = new BST<LinkedList<Photo>>();
		Photos = new LinkedList<Photo>();

	}

	// Add a photo
	public void addPhoto(Photo p) {

		if (Checkphoto(Photos,p)) {
			return;
		}
		Photos.insert(p);

		if (p.getTags().empty()) {
			return;
		}
		LinkedList<String> tag = p.getTags();
		tag.findFirst();
		String Altag = tag.retrieve();
		for (; !tag.last();) {
			if (binarytree.findKey(Altag)) {
				LinkedList<Photo> Altphoto = binarytree.retrieve();
				Altphoto.insert(p);
			} // end if
			else {
				LinkedList<Photo> Altphoto = new LinkedList<Photo>();
				Altphoto.insert(p);
				binarytree.insert(Altag, Altphoto);
				
			} // end else
			tag.findNext();
			Altag = tag.retrieve();
		} // end while

		if (binarytree.findKey(Altag)) {
			LinkedList<Photo> Altphoto = binarytree.retrieve();
			Altphoto.insert(p);
		} // end if
		else {
			LinkedList<Photo> Altphoto = new LinkedList<Photo>();
			Altphoto.insert(p);
			binarytree.insert(Altag, Altphoto);
			
		} // end else
	}// end method

	// Delete a photo
	public void deletePhoto(String path) {
		if (path == "" || path == null) {
			return;
		}
		if (Photos.empty()) {
			return;
		}

		Photo ph = null;
		Photos.findFirst();
		while (!Photos.last()) {
			if (Photos.retrieve().getPath().equals(path)) {
				ph = Photos.retrieve();
				Photos.remove();
				break;
			} // end if
			Photos.findNext();
		} // end while

		if (Photos.retrieve().getPath().equals(path)) {
			ph = Photos.retrieve();
			Photos.remove();
		}

		if (ph == null) {
			return;
		} // end if

		LinkedList<String> tag = ph.getTags();
		
		if (tag.empty()) {
			return;
		}
		tag.findFirst();
		while (!tag.last()) {
			if (binarytree.findKey(tag.retrieve())) {
				binarytree.removeKey(tag.retrieve());
				if (tag.retrieve().isEmpty()) {
					binarytree.removeKey(tag.retrieve());
					}
			}
			tag.findNext();
		} // end while

		if (binarytree.findKey(tag.retrieve())) {
			binarytree.removeKey(tag.retrieve());
			if (tag.retrieve().isEmpty()) {
				binarytree.removeKey(tag.retrieve());
				}

		} // end if
	}

	// Return the inverted index of all managed photos
	public BST<LinkedList<Photo>> getPhotos() {
		return binarytree;
	}
	
	
	public LinkedList<Photo> getLinkedPics() {
		return Photos;
	}

	public boolean Checkphoto(LinkedList<Photo> Altphoto,Photo ph) {
		if (Altphoto.empty()) {
			return false;
		}
		Altphoto.findFirst();
		while (!Altphoto.last()) {
			if (Altphoto.retrieve().getPath().equals(ph.getPath()))
				return true;
			Altphoto.findNext();
		}
		if (Altphoto.retrieve().getPath().equals(ph.getPath()))
			return true;

		return false;
	}
	
    
}
