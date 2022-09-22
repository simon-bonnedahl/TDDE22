import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Brute force solution. To run: java brute.java < input.txt
 *
 * @author Magnus Nielsen
 * Largely based on existing C++-laborations by Tommy Olsson and Filip Strömbäck.
 */
public class Fast {
    /**
     * Clear the window and paint all the Points in the plane.
     *
     * @param frame - The window / frame.
     * @param points - The points to render.
     */

    private static Point originPoint;

    private static void setOriginPoint(Point p){
        originPoint = p;
    }

    private static void render(JFrame frame, ArrayList<Point> points) {
        frame.removeAll();
        frame.setVisible(true);

        for (Point p : points) {
            p.paintComponent(frame.getGraphics(), frame.getWidth(), frame.getHeight());
        }
    }

    /**
     * Draw a line between two points in the window / frame.
     *
     * @param frame - The frame / window in which you wish to draw the line.
     * @param p1 - The first Point.
     * @param p2 - The second Point.
     */
    private static void renderLine(JFrame frame, Point p1, Point p2) {
        p1.lineTo(p2, frame.getGraphics(), frame.getWidth(), frame.getHeight());
    }

    /**
     * Read all the points from the buffer in the input scanner.
     *
     * @param input - Scanner containing a buffer from which to read the points.
     * @return ArrayList<Point> containing all points defined in the file / buffer.
     */
    private static ArrayList<Point> getPoints(Scanner input) {
        int count = input.nextInt();
        ArrayList<Point> res = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            res.add(new Point(input.nextInt(), input.nextInt()));
        }

        return res;
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame;
        Scanner input = null;
        File f;
        ArrayList<Point> points;

        if (args.length != 1) {
            System.out.println("Usage: java Brute <input.txt>\n" +
			       "Replace <input.txt> with your input file of preference, and possibly the path.\n" +
			       "Ex: java Brute data/input1000.txt");
            System.exit(0);
        }

        // Opening the file containing the points.
        f = new File(args[0]);
        try {
            input = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.err.println("Failed to open file. Try giving a correct file / file path.");
        }

        // Creating frame for painting.
        frame = new JFrame();
        frame.setMinimumSize(new Dimension(512, 512));
        frame.setPreferredSize(new Dimension(512,512));

        // Getting the points and painting them in the window.
        points = getPoints(input);
        render(frame, points);

	// Sorting points by natural order (lexicographic order). Makes finding end points of line segments easy.
        Collections.sort(points, new NaturalOrderComparator());
        

        long start = System.currentTimeMillis();

	//////////////////////////////////////////////////////////////////////
	// Your code goes here. Draw any lines you find using the function  //
	//  renderLine. Look at Brute.java if you are unsure if how to use  //
	//                     any functions. Good luck!                    //
	//////////////////////////////////////////////////////////////////////


        //Välj en punkt p
        //Tänk på p som origo
        //Sortera alla andra punkter utifrån lutningen de har gentemot P
        //Kontrollera om tre eller flera punkter ligger på samma linje som p
        //System.out.println(Arrays.toString(points.toArray()));
        for(int i = 0; i< points.size(); i++){
            ArrayList<Point> slopesToOrigin = new ArrayList<>();
            setOriginPoint(points.get(i));
            //System.out.print("CHANING ORIGINPOINT TO: ");
            //originPoint.printLocation();
            for(int j = i+1; j < points.size(); j++){
                slopesToOrigin.add(points.get(j));

            }
            /*for(Point slope : slopesToOrigin){
                slope.printSlopeTo(originPoint);
            }*/
            Collections.sort(slopesToOrigin, new SlopeOrder());     //Sorterar punkterna utifrån deras lutningskofficient till originPoint
            //System.out.print("After sorting: ");
            /*for(Point slope : slopesToOrigin){
                slope.printSlopeTo(originPoint);
            }*/
           
            int matches = 0;
            for(int j = 1; j< slopesToOrigin.size(); j++){
                
                if(originPoint.slopeTo(slopesToOrigin.get(j-1)) == originPoint.slopeTo(slopesToOrigin.get(j))){
                  matches++;
                  //System.out.println("MATCH: " + matches);
                  //slopesToOrigin.get(j-1).printLocation();
                  //slopesToOrigin.get(j).printLocation();
                  if(matches == 2){
                    //System.out.println("RENDERING LINE");
                    renderLine(frame, points.get(i), slopesToOrigin.get(j));
                  }
                }else{
                    matches = 0;
                }

            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Computing all the line segments took: " + (end-start) + " milliseconds.");
    }

    /**
     * Comparator class. Used to tell Collections.sort how to compare
     * objects of a non standard class. When you make your own
     * Comparator, keep in mind that this is a class like any other,
     * and can contain data and other methods if you deem it useful.
     */
    private static class NaturalOrderComparator implements Comparator<Point> {
        public int compare(Point a, Point b){
            if (a.greaterThan(b)) {
		// a is "greater" than b.
		return 1;
            } else if (a.lessThan(b)) {
		// a is "less" than b.
		return -1;
	    }
	    // our two points are equal.
	    return 0;
        }
    }

    private static class SlopeOrder implements Comparator<Point>
	{   
     
		public int compare(Point point1, Point point2)
		{
			double m1 = originPoint.slopeTo(point1);
            double m2 = originPoint.slopeTo(point2);
			int result;

			if(m1 == m2)
			{
				result =  0;
			}
			else if(m1 < m2)
			{
				result =  -1;
			}
			else
			{
				result = 1;
			}
			return result;
		}
	}
}

