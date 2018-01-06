/*import AbstractController;
import DrawableObject;
import PaintTool;
*/
import java.awt.*;

public class Vector2D extends AbstractController {
    public PaintTool ptool;
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Multiplies the elements of this vector with scalar factor and
     * returns the result. The vector is not changed by this operation!
     */
    public Vector2D mult(double factor) {
        double x = getX() * factor;
        double y = getY() * factor;
        Vector2D newVector = new Vector2D (x,y);
        return newVector;
    }
    /**
     * Adds this vector and <code>vec</code> and returns the result.Input vectors are not changed.
     */
    public Vector2D plus(Vector2D vec) {
        double x = getX() + vec.getX();
        double y = getY() + vec.getY();
        Vector2D newVector = new Vector2D (x,y);
        return newVector;
    }
    /**
     * Subtracts <code>vec</code> from this vector and returns the
     * result. Input vectors are not changed.
     */
    public Vector2D minus(Vector2D vec) {
        double x = getX() - vec.getX();
        double y = getY() - vec.getY();
        Vector2D newVector = new Vector2D (x,y);
        return newVector;
    }
    /**
     * Rotates this vector by <code>deg</code> degrees and returns the
     * result.
     */
    public Vector2D rotate(int deg) {
        double angle = deg * 2.0 * Math.PI / 360.0;
        double x = Math.cos(angle)* getX() - Math.sin(angle) * getY();
        double y = Math.sin(angle) * getX() + Math.cos(angle) * getY();
        Vector2D newVector = new Vector2D (x,y);
        return newVector;
    }
    /** Returns the Euclidean norm of this vector. */
    public double vlength() {
        double length = getX()* getX() + getY() * getY();
        return Math.sqrt(length);
    }

    public static void drawSimpleFractal1(PaintTool ptool,Vector2D vec1, Vector2D vec2) {
        ptool.setColor(Color.RED);
        ptool.addLine((int)vec1.getX(), (int)vec1.getY(),(int)vec2.getX(),(int)vec2.getY());
        Vector2D neu = vec1.constructThirdPoint(vec1,vec2);
        ptool.setColor(Color.RED);
        ptool.addLine((int)vec1.getX(), (int)vec1.getY(),(int)neu.getX(),(int)neu.getY());
        ptool.setColor(Color.RED);
        ptool.addLine((int)neu.getX(), (int)neu.getY(),(int)vec2.getX(),(int)vec2.getY());
    }

    public Vector2D constructThirdPoint(Vector2D vec1, Vector2D vec2) {
   /*     Vector2D neu = vec2.minus(vec1);
        neu.rotate(30);
        neu.vlength();
        neu.mult(Math.cos(30*(-1)));
        neu.plus(vec1);
        return neu;*/

        Vector2D neu = vec2.minus(vec1);
        Vector2D rot = neu.rotate(-30);
        neu.vlength();
        neu.mult(Math.cos(30*(-1)));
        neu.mult(neu.vlength());
        neu.plus(vec1);
        return neu;

        /*Vector2D neu = new Vector2D(vec2.getX()-vec1.getX(),vec2.getY()-vec1.getY());
        Vector2D rot = neu.rotate(-30);
        double length = Math.cos(Math.toRadians(30)*neu.vlength());
        rot = rot.mult(length/neu.vlength());
        Vector2D ret = vec1.plus(rot);
        return ret;*/
    }

    @Override
    public String toString() {
        return "Vector2D{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public String getTitle() {
        return "Fractal Demo";
    }
    @Override
    public String[] getButtonNames() {
        return new String[]
                { "30 Degree", "32 Degree", "45 Degree", "Random Degree", "Simple Fractal1",
                        "Simple Fractal2", "Pythagorastree", "?"};
    }

    @Override
    public void onButtonPressed(PaintTool ptool, int button) {
        this.ptool=ptool;
        switch (button) {
            case 4:
                Vector2D vec1 = new Vector2D(250,310);
                Vector2D vec2 = new Vector2D(450,310);
                drawSimpleFractal1(ptool, vec1,vec2);
        }
    }



    public static void main(String[] args) {
       /* Vector2D vector1 = new Vector2D(2,1);
        Vector2D vector2 = new Vector2D(10,6);
        Vector2D vec1 = vector1.mult(2);
        System.out.println("Mult: " + vec1);
        Vector2D vec2 = vector1.plus(vector2);
        System.out.println("Plus " + vec2);
        Vector2D vec3 = vector1.minus(vector2);
        System.out.println("Minus " + vec3);
        Vector2D vec4 = vector1.rotate(30);
        System.out.println("Rotate " + vec4);
        System.out.println(vector1.vlength());*/


        PaintTool ptool = new PaintTool(new Vector2D(0,0));
        ptool.setVisible(true);
    }
}
