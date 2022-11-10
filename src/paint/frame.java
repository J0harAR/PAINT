//ES EL CONSTRUCTOR

package paint;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class frame extends Frame implements MouseListener, MouseMotionListener
{
    F panel = new F ( 800, 800 );
    boolean nuevo = false;
    boolean Linea = false;
    boolean haspoly = false;
    boolean lineaManoAlzada =false;
    
    MenuBar menuBar = new MenuBar ();
    linea linea    = new linea ();
    lineAlz LineaManoAlzada = new lineAlz();
   
    Menu archivo = new Menu  ( "Archivo");
    Menu Lineas = new Menu  ( "Lineas");
    Menu menuColor = new Menu ( "Color");
    MenuItem Salir = new MenuItem ( "Salir" );
    MenuItem ItemNuevo = new MenuItem ( "Nuevo" );
    MenuItem ItemDibujar = new MenuItem ( "Dibujar");				
    MenuItem ItemManoAlzada = new MenuItem ( "Mano Alzada" );
    MenuItem color = new MenuItem ( "Cambiar Color" );
 

    int pxm = 0, pym = 0, radio = 0;
    int an;
    float ds[] =
	{
	0};
    public frame ()
    {
	panel.memg.setBackground ( Color.white );
	panel.memg.clearRect ( 0, 0, 800, 550 );
	panel.addMouseListener ( this );
	panel.addMouseMotionListener ( this );
	add (panel);

	
	
	
    Salir.addActionListener
	    (new ActionListener (){
	    public void actionPerformed ( ActionEvent e ){
		frame.this.windowClosed (); 
            }
        }
	);

	color.addActionListener
	    (new ActionListener ()
	{
	    public void actionPerformed ( ActionEvent e )
	    {
		if ( frame.this.nuevo )
		{
		    panel.memg.setColor(JColorChooser.showDialog(frame.this,"Selecciona tu color",panel.memg.getColor()));
		    
		}
	    }
	}
	);

	ItemDibujar.addActionListener
	    (new ActionListener ()
	{
	    public void actionPerformed ( ActionEvent e )
	    {
		if ( frame.this.nuevo )
		{
		    frame.this.Linea = true;
		    frame.this.haspoly=false;
		    frame.this.lineaManoAlzada=false;
		   
		    
		}
	    }
	}
	);
	
	ItemNuevo.addActionListener
	    (new ActionListener ()
	{
	    public void actionPerformed ( ActionEvent e )
	    {
		
		frame.this.panel.memg.clearRect ( 0, 0, 800, 550 );
		frame.this.panel.repaint ();
		frame.this.nuevo = true;
		
	    }
	}
	);
	
	ItemManoAlzada.addActionListener
	(new ActionListener ()
	{
	    public void actionPerformed ( ActionEvent e )
	    {
		if ( frame.this.nuevo )
		{
                frame.this.lineaManoAlzada=true; 
	        frame.this.Linea = false;
	        frame.this.haspoly=false;
	        
		    
		}
	    }
	}
	);
	
	setTitle ( "Paint graficacion" );
        setSize ( new Dimension ( 550, 550 ) );
	setResizable ( false );
	setMenuBar ( menuBar );
        
 
        archivo.add(ItemNuevo );
	archivo.add(Salir );
	Lineas.add(ItemDibujar );
	Lineas.add(ItemManoAlzada );
	menuColor.add(color );
	menuBar.add(archivo );
	menuBar.add(Lineas );
	menuBar.add( menuColor );
	

	


	this.addWindowListener
	    (new WindowAdapter ()
	{
	    public void windowClosing ( WindowEvent e )
	    {
		frame.this.windowClosed ();
	    }
	}
	);
    }

    public void mouseClicked ( MouseEvent e )
    {}

    public void mousePressed ( MouseEvent e )
    {
	if ( Linea )
	{
	    linea.x = e.getX ();
	    linea.y = e.getY ();
	}
	if ( haspoly )
	{
	    pxm = e.getX ();
	    pym = e.getY ();
	}

	if ( lineaManoAlzada )
	{
	    LineaManoAlzada.pxx = e.getX ();
	    LineaManoAlzada.pyy = e.getY ();
	}
    }

    public void mouseReleased ( MouseEvent e )
    {
	if ( Linea )
	{
 	    linea.x1 = e.getX ();
	    linea.y1 = e.getY ();
	    linea.dibujar (panel.memg );
	    panel.repaint ();
	}
	
}
    public void mouseDragged ( MouseEvent e )
    {
	if ( Linea )
	{
	    Graphics2D g = ( Graphics2D ) panel.getGraphics ();
	    Color c = g.getColor ();
	    g.setStroke (panel.memg.getStroke () );
	    g.setColor (panel.memg.getColor () );
	    g.setPaint (panel.memg.getPaint () );
	    g.drawImage (panel.mem, 0, 0, panel );
	    linea.x1 = e.getX ();
	    linea.y1 = e.getY ();
	    linea.dibujar ( g );
	    g.setColor ( c );

	}
	
	
        
   	if (lineaManoAlzada)
	{
		Graphics2D g = ( Graphics2D ) panel.getGraphics ();	   
	    Color c = g.getColor ();
	    g.setStroke (panel.memg.getStroke () );
	    g.setColor (panel.memg.getColor () );
	    g.setPaint (panel.memg.getPaint () );
	    g.drawImage (panel.mem, 0, 0, panel );
	    LineaManoAlzada.px=e.getX();
		LineaManoAlzada.py=e.getY();
	    LineaManoAlzada.dibujarAlz(panel.memg);		
        }
	}
    

    public void mouseMoved ( MouseEvent e )
    {}

    public void mouseEntered ( MouseEvent e )
    {
    	setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));	
    }

    public void mouseExited ( MouseEvent e )
    {}

    
    protected void windowClosed ()
    {
	System.exit ( 0 );
    }



}

class linea
{
    public int x = 0, y = 0, x1 = 0, y1 = 0;
    public linea ()
    {}

    public void dibujar ( Graphics g )
    {
	if ( g != null )
	{
	    Graphics2D g2 = ( Graphics2D ) g;
	    g2.drawLine ( x, y, x1, y1 );
	}
    }
}


class lineAlz
{
    public int px,py,pxx,pyy;
    public lineAlz ()
    {}

    public void dibujarAlz ( Graphics mm )
    {
	if ( mm != null )
	{
	    Graphics2D ma = (Graphics2D)mm;
	    ma.drawLine ( px, py, pxx, pyy );
	    pxx=px;
	    pyy=py;
	}
    }
}