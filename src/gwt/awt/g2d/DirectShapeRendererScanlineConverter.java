package gwt.awt.g2d;


import gwt.awt.ScanLineConverter;
import gwt.awt.geom.PathIterator;
import gwt.g2d.client.graphics.DirectShapeRenderer;

public final class DirectShapeRendererScanlineConverter implements ScanLineConverter {
	/**
	 * This is used in {@link #addShape(PathIterator, boolean)} to receive the
	 * coordinates of the path.
	 */
	private float[] coords;

	private int minY;
	private int maxY;
	private int minX;
	private int maxX;

	/** the renderer to draw to */
	DirectShapeRenderer renderer;

	public DirectShapeRendererScanlineConverter(DirectShapeRenderer renderer) {
		this.renderer = renderer;
		coords = new float[6];
	}

	/* (non-Javadoc)
	 * @see gwt.awt.ScanLineConverter#addShape(gwt.awt.geom.PathIterator)
	 */
	public void addShape(PathIterator path) {
		int startX = 0;
		int startY = 0;
		int lastX = 0;
		int lastY = 0;
		while (!path.isDone()) {
			int type = path.currentSegment(coords);
			switch (type) {
			case PathIterator.SEG_MOVETO:
				startX = lastX = (int) coords[0];
				startY = lastY = (int) coords[1];
				minY = Math.min(startY, minY);
				maxY = Math.max(startY, maxY);
				minX = Math.min(startX, minX);
				maxX = Math.max(startX, maxX);
				renderer.moveTo(startX, startY);
				break;
			case PathIterator.SEG_LINETO:
				int x = (int) coords[0];
				int y = (int) coords[1];
				renderer.drawLineTo(x, y);
				lastX = x;
				lastY = y;
				minY = Math.min(lastY, minY);
				maxY = Math.max(lastY, maxY);
				minX = Math.min(lastX, minX);
				maxX = Math.max(lastX, maxX);
				break;
			case PathIterator.SEG_CLOSE:
				lastX = startX;
				lastY = startY;
				renderer.drawLineTo(lastX, lastY);
				break;
			case PathIterator.SEG_QUADTO:
			case PathIterator.SEG_CUBICTO:
				//TODO: do me...
			default:
				assert false;
			}
			path.next();
		}
	}

}
