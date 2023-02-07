//Student full name: Ash Bhattarai
//Student number: 300236157

/**
 * The class <b>Point3D</b> contains instance variables and methods that
 * allow for a storage of a 3d point. Point3D is used in the class
 * Plane3D, PointCloud, and PlaneRANSAC in order to reference 3d points.
 *
 */

public class Point3D {

    //x, y, and z refer to the xyz coordinates of a 3d point
    private double x;
    private double y;
    private double z;


    //default constructor setting xyz points to 0
    public Point3D(){
        x = 0.0;
        y = 0.0;
        z = 0.0;
    }

    //constructor allowing for specificity of xyz coordinates.
    public Point3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //getter method for x coordinate
    public double getX(){
        return x;
    }

    //getter method for y coordinate
    public double getY(){
        return y;
    }

    //getter method for z coordinate
    public double getZ(){
        return z;
    }

    public String toString() {

        return "("+x+","+y+","+z+")";
    }


}
