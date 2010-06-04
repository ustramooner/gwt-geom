package gwt.awt;

import gwt.awt.Polygon;
import gwt.awt.Rectangle;

import gwt.awt.geom.Arc2D;
import gwt.awt.geom.Ellipse2D;
import gwt.awt.geom.GeneralPath;
import gwt.awt.geom.Line2D;
import gwt.awt.geom.RoundRectangle2D;

/**
 * Caches certain Shape objects for reuse in AbstractGraphics2D. This avoids
 * massive creation of such objects.
 */
class ShapeCache
{

  /**
   * A cached Line2D.
   */
  public Line2D line;

  /**
   * A cached Rectangle.
   */
  public Rectangle rect;

  /**
   * A cached RoundRectangle2D.
   */
  public RoundRectangle2D roundRect;

  /**
   * A cached Ellipse2D.
   */
  public Ellipse2D ellipse;

  /**
   * A cached Arc2D.
   */
  public Arc2D arc;

  /**
   * A cached Polygon.
   */
  public Polygon polygon;

  /**
   * A cached polyline.
   */
  public GeneralPath polyline;
}
