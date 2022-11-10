//CLASE DEL BUFER DE LA IMAGEN

package paint;

import java.awt.*;
import java.awt.image.*;

public class F extends Canvas
{

    public BufferedImage mem;
    public Graphics2D memg;
    public int w, h;
    public F ( int wx, int hx )
    {
	w = wx;
	h = hx;
	mem = new BufferedImage ( wx, hx, BufferedImage.TYPE_INT_RGB );
	memg = mem.createGraphics ();
	memg.setColor ( Color.black );
	memg.setBackground ( new Color ( 255, 255, 255 ) );
	memg.clearRect ( 0, 0, w, h );

    }

    public void update ( Graphics g )
    {
	paint ( g );
    }

    public void paint ( Graphics g )
    {

	g.drawImage ( mem, 0, 0, this );

    }

    public void rp ()
    {
	Graphics g = this.getGraphics ();
	paint ( g );
    }
}
