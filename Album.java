public class Album {
	private String name;
	private String condition;
	private PhotoManager manager;

	public Album(String name, String condition, PhotoManager manager) {
		this.name = name;
		this.condition = condition;
		this.manager = manager;
	}

	// Return the name of the album
	public String getName() {
		return this.name;
	}

	public String getCondition() {
		return this.condition;
	}

	public PhotoManager getManager() {
		return this.manager;
	}

	// Return all photos that satisfy the album condition
	public LinkedList<Photo> getPhotos() {
		if (manager.getLinkedPics() == null) {
			return manager.getLinkedPics();
		} // end if

		if (condition.equals("") || condition == null)
			return manager.getLinkedPics();
		
		String[] Altcon = condition.split("AND");
		for (int i = 0; i < Altcon.length; i++)
			Altcon[i] = Altcon[i].replaceAll("\\s+", "");

		int count = 0;
		LinkedList<Photo> oldpics = manager.getLinkedPics();
		LinkedList<Photo> newpics = new LinkedList<Photo>();
		Photo ph = null;
		LinkedList<String> phototags = new LinkedList<String>();
		oldpics.findFirst();
		while (!oldpics.last()) {
			ph = oldpics.retrieve();
			if (ph.getTags() == null) {
				continue;
			}
			phototags = ph.getTags();
			phototags.findFirst();
			while (!phototags.last()) {
				for (int i = 0; i < Altcon.length; i++) {
					if (Altcon[i].equals(phototags.retrieve()))
						count++;
				} // end array
				phototags.findNext();
			} // end while
			for (int i = 0; i < Altcon.length; i++) {
				if (Altcon[i].equals(phototags.retrieve()))
					count++;
			} // end array
			if (count == Altcon.length) {
				newpics.insert(ph);
			}
			count = 0;
			oldpics.findNext();
		} // end for
		ph = oldpics.retrieve();
		phototags = ph.getTags();
		phototags.findFirst();
		while (!phototags.last()) {
			for (int i = 0; i < Altcon.length; i++) {
				if (Altcon[i].equals(phototags.retrieve()))
					count++;
			} // end array
			phototags.findNext();
		} // end while
		for (int i = 0; i < Altcon.length; i++) {
			if (Altcon[i].equals(phototags.retrieve()))
				count++;
		} // end array
		if (count == Altcon.length) {
			newpics.insert(ph);
		}
		if (newpics.empty()) {
			return newpics;
		}
		return newpics;
	}

	// Return the number of tag comparisons used to find all photos of the album
	public int getNbComps() {
		int count=0;
		if (condition.equals("") || condition == null)
			return count;
		
		String[] Altcon = condition.split("AND");
		for (int i = 0; i < Altcon.length; i++)
			Altcon[i] = Altcon[i].replaceAll("\\s+", "");
		
		int i=0;
		BST<LinkedList<Photo>> binary = manager.getPhotos();
		while(i<Altcon.length) {
			binary.findKey(Altcon[i]);
			count+=binary.getcount();
			i++;
		}
		return count;
	}
}
