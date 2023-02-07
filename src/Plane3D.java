//Student full name: Ash Bhattarai
//Student number: 300236157

/**
 * The class <b>Plane3D</b> contains instance variables and methods that
 * allow for a storage of a 3D plane. It has variables a, b, c, d that represent the
 * equation of a plane. Plane3D is used in PlaneRANSAC to calculate the distance between
 * points from PointCloud and the Plane.
 *
 */
public class Plane3D {

    private double a;
    private double b;
    private double c;
    private double d;

    //Constructor that calculates a, b, c, and d from three given points.
    public Plane3D(Point3D p1, Point3D p2, Point3D p3){

        this.a = ((p2.getY() - p1.getY())*(p3.getZ()-p1.getZ()))-((p3.getY()- p1.getY())*(p2.getZ()-p1.getZ()));
        this.b = ((p3.getX() - p1.getX())*(p2.getZ()-p1.getZ()))-((p2.getX()- p1.getX())*(p3.getZ()-p1.getZ()));
        this.c = ((p2.getX() - p1.getX())*(p3.getY()-p1.getY()))-((p2.getY()- p1.getY())*(p3.getX()-p1.getX()));
        this.d = ((-a)*p1.getX())-(b* p1.getY())-(c* p1.getZ());

    }

    //Constructor with pre given a, b, c, and d values
    public Plane3D(double a, double b, double c, double d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    //method that calculates the distance between a Plane object and a Point3D object
    public double getDistance(Point3D pt){

        double pD = Math.abs((a * pt.getX()+ b * pt.getY() + c * pt.getZ() + d));
        double e = Math.sqrt(a*a + b*b + c*c);
        return pD/e;

    }

    //basic toString method
    public String toString(){
        return (a+"x + "+b+"x + "+c+"x + "+d+" = 0 ");
    }

}
