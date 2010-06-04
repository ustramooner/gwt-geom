package gwt.awt;

import gwt.awt.geom.PathIterator;

public interface ScanLineConverter {

	/**
	 * Add a shape to the scanline converter.
	 * 
	 * @param path
	 */
	public abstract void addShape(PathIterator path);

}