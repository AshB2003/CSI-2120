//Student full name: Ash Bhattarai
//Student number: 300236157

/**
 *This class <b>PlaneRANSAC</b> contains the algorithm and methods to cluster 3D points from
 * class Point3D. It takes a xyz file as input for these points and outputs
 * another xyz file containing the cluster of these given points that are within an eps
 * value of the most dominant planes.
 */

import java.lang.Math;
import java.util.Iterator;

public class PlaneRANSAC {

    private double eps;
    private PointCloud pc;
    private int runCount;

    //constructor that takes as input a PointCloud reference
    public PlaneRANSAC(PointCloud pc){
        this.pc = pc;
        runCount = 0;
    }

    //setter method for Eps
    public void setEps(double eps){
         this.eps = eps;
    }

    //getter method for Eps
    public double getEps(){
        return eps;
    }

    //method that returns the estimated number of iterations required to obtain a certain level
    //of confidence to identify a plane made of a certain percentage of points
    public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane){

        double k;
        k = (Math.log(1-confidence))/(Math.log(1-(Math.pow(percentageOfPointsOnPlane, 3))));
        k = Math.ceil(k);
        return (int)k;

    }

    //Method that runs the RANSAC algorithm for identifying the dominant plane of the
    //point cloud (only one plane)
    public void run(int numberOfIterations, String filename){

        Plane3D domPlane = new Plane3D(1,1,1,1);
        int bestSupport = 0;
        Iterator<Point3D> points;
        PointCloud save = new PointCloud();

        for(int i = 0; i < numberOfIterations; i++){

            points = pc.iterator();
            int support = 0;
            Point3D rPoint1 = pc.getPoint();
            Point3D rPoint2 = pc.getPoint();
            Point3D rPoint3 = pc.getPoint();

            Plane3D rPlane = new Plane3D(rPoint1, rPoint2, rPoint3);

            while(points.hasNext()){

                if(rPlane.getDistance(points.next()) <= eps){
                    support++;
                }

            }

            if(support > bestSupport){
                domPlane = rPlane;
                bestSupport = support;
            }

        }

        points = pc.iterator();

        while (points.hasNext()){
            Point3D cP = points.next();
            if(domPlane.getDistance(cP) <= eps){

                save.addPoint(cP);
                points.remove();

            }
        }
        runCount++;
        save.save(filename+"_p"+runCount+".xyz");
    }

    //main method. Default Eps set to 0.5 and default file is PointCloud1
    public static void main(String[] args){

        String filename;
        double epsV = 0.1;

        if(args.length == 0) {
            filename = "PointCloud3";
        }
        else{
            filename = args[0];
            epsV = Double.parseDouble(args[1]);
        }

        PointCloud pc = new PointCloud(filename + ".xyz");
        PlaneRANSAC ag = new PlaneRANSAC(pc);

        ag.setEps(epsV);

        int iter = ag.getNumberOfIterations(0.99, 0.2);

        for(int i = 0; i < 3; i++) {
            ag.run(iter, filename);
        }

        pc.save(filename+"_p0.xyz");
    }
}
