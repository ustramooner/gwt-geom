package gwt.geom.example.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import gwt.awt.Polygon;
import gwt.awt.ScanLineConverter;
import gwt.awt.Shape;
import gwt.awt.g2d.DirectShapeRendererScanlineConverter;
import gwt.awt.geom.Area;
import gwt.awt.geom.Rectangle2D;
import gwt.g2d.client.graphics.DirectShapeRenderer;
import gwt.g2d.client.graphics.KnownColor;
import gwt.g2d.client.graphics.Surface;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * HelloWorld application.
 */
public class Geom implements EntryPoint {
	Surface surface = new Surface();
	DirectShapeRenderer path;
	ScanLineConverter converter;
	Random rnd = new Random(System.currentTimeMillis());
	int polygonCounter = 0;
	int areaCounter = 0;
	HashMap<String, Area> areas = new HashMap<String, Area>();

	private Shape createRandomPolygon(){
		Polygon p = new Polygon();
		int x,y, startX=0, startY=0;
		for ( int i=0;i<3;i++){
			x = rnd.nextInt(surface.getWidth());
			y = rnd.nextInt(surface.getHeight());
			
			if ( i==0 ){
				startX = x;
				startY = y;
			}
			p.addPoint(x, y);
		}
		p.addPoint(startX, startY);
		return p;
	}
	
	
	public void onModuleLoad() {
		//button for adding a new polygon.
		PushButton b1 = new PushButton("Add new polygon", new ClickHandler() {
			public void onClick(ClickEvent event) {
				Shape p = createRandomPolygon();
				path.beginPath();
				converter.addShape(p.getPathIterator(null));
				path.closePath().stroke().fill();
				areas.put("polygon " + polygonCounter++, new Area(p));
			}
		});
		b1.setWidth("120px");
		
		PushButton b2 = new PushButton("Join all polygons", new ClickHandler() {
			public void onClick(ClickEvent event) {
				Area area = new Area();
				for ( Area a : areas.values() )
					area.add(a);
				
				surface.clear();
				path.beginPath();
				converter.addShape(area.getPathIterator(null));
				path.closePath().stroke().fill();
				
				areas.clear();
				areas.put("area " + (areaCounter++), area);
			}
		});
		b2.setWidth("120px");
		
		//setup the path, surface, etc...
		path = new DirectShapeRenderer(surface);
		converter = new DirectShapeRendererScanlineConverter(path);
		surface.setSize(Window.getClientWidth(), 480);
		surface.setFillStyle(KnownColor.PINK);

		//surface click handler...
		surface.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int x = event.getNativeEvent().getClientX();
				int y = event.getNativeEvent().getClientY();
				x -= surface.getElement().getAbsoluteLeft();
				y -= surface.getElement().getAbsoluteTop();
				
				StringBuffer sb = new StringBuffer();
				int i=0;
				for ( Map.Entry<String, Area> e : areas.entrySet() ){
					if ( e.getValue().contains(x,y) ){
						if ( sb.length() == 0 )
							sb.append("The following areas were clicked:\n");
						sb.append( " " + e.getKey() + "\n");
					}
					i++;
				}
				if ( sb.length() > 0)
					Window.alert(sb.toString());
			}
		});

		VerticalPanel vp = new VerticalPanel();
		vp.setSize("100%", "100%");
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(b1);
		hp.add(b2);
		vp.add(hp);
		vp.add(surface);
		RootPanel.get("root").add(vp);
	}
}
