package gwt.awt.geom;

import gwt.awt.Rectangle;
import gwt.awt.Polygon;

import com.google.gwt.junit.client.GWTTestCase;

public class GeomTest extends GWTTestCase{
	@Override
	public String getModuleName() {
		return "gwt.GeomTest";
	}
	
	public void assertEquals(Rectangle r1, Rectangle r2){
		assertEquals(r1.getX(), r2.getX());
		assertEquals(r1.getY(), r2.getY());
		assertEquals(r1.getWidth(), r2.getWidth());
		assertEquals(r1.getHeight(), r2.getHeight());
	}
	public void assertEquals(String msg, Rectangle r1, Rectangle r2){
		assertEquals(msg, r1.getX(), r2.getX());
		assertEquals(msg, r1.getY(), r2.getY());
		assertEquals(msg, r1.getWidth(), r2.getWidth());
		assertEquals(msg, r1.getHeight(), r2.getHeight());
	}

	public void testSimplePolygon() throws Throwable{
		Polygon p = new Polygon();
		p.addPoint(0, 0);
		p.addPoint(0, 100);
		p.addPoint(100, 100);
		p.addPoint(100, 0);
		p.addPoint(0, 0);
		
		assertEquals("test bounds", new Rectangle(0,0,100,100), p.getBounds() );
		assertTrue("test contains", p.contains(new Point2D.Double(40,50)));
	}

	public void testSimpleArea() throws Throwable{
		Polygon p1 = new Polygon();
		p1.addPoint(0, 0);
		p1.addPoint(0, 100);
		p1.addPoint(100, 100);
		p1.addPoint(100, 0);
		p1.addPoint(0, 0);

		Polygon p2 = new Polygon();
		p2.addPoint(10, 10);
		p2.addPoint(10, 110);
		p2.addPoint(110, 110);
		p2.addPoint(110, 10);
		p2.addPoint(10, 10);
		

		Area area = new Area(p1);
		area.add(new Area(p2));
		assertEquals("test bounds", new Rectangle(0,0,110,110), area.getBounds() );
	}
	
}
