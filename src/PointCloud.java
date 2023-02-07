//Student full name: Ash Bhattarai
//Student number: 300236157

/**
 * The class <b>PointCloud</b> contains an instance variable that
 * allows for a storage of a 3d points. This class also contains an inner
 * iterator class that allows for iteration of the different points contained
 * in this class. PointCloud is also used in the PlaneRANSAC class.
 *
 */

import java.util.ArrayList;
import java.lang.Math;
import java.io.*;
import java.util.Iterator;

public class PointCloud {

    //Arraylist to store Point3D
    private ArrayList<Point3D> points;

    //Constructor that takes as an input a filename of type .xyz
    PointCloud(String filename){

        this.points = new ArrayList<Point3D>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line = br.readLine();
            while((line = br.readLine()) != null){
                String[] values = line.split("\\s+");
                Point3D point = new Point3D(Double.parseDouble(values[0]),Double.parseDouble(values[1]),Double.parseDouble(values[2]));
                this.points.add(point);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    //Basic constructor that creates an empty point cloud
    PointCloud(){
        this.points = new ArrayList<Point3D>();
    }

    //Method to add point to the cloud
    public void addPoint(Point3D pt){
        points.add(pt);
    }

    //Method that returns a random point from the point cloud
    public Point3D getPoint(){

        int rand = (int)(Math.random()*(points.size()-1)+1);
        return points.get(rand);
    }

    //Method to save the current point cloud to a specific filename.
    public void save(String filename){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){

            bw.write("x y z");
            bw.newLine();
            for(int i = 0; i < points.size(); i++){
                bw.write((points.get(i).getX())+" "+(points.get(i).getY())+" "+(points.get(i).getZ()));
                bw.newLine();
            }
            bw.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //Inner iterator class. Contains methods for
    public Iterator<Point3D> iterator(){
        return new Point3DIterator();
    }

    private class Point3DIterator implements Iterator<Point3D>{

        //counter to determine when the iterator has reached the end of the cloud
        private int count;

        //default constructor
        private Point3DIterator(){
            count = 0;
        }

        //Method that returns a boolean (true) if there exists a next point in the cloud
        public boolean hasNext(){
            return (count < points.size());
        }

        //Returns the current point and iterates to the next point
        public Point3D next(){
            return points.get(count++);
        }

        //Removes point
        public void remove(){
            count--;
            points.remove(count);

        }
    }
}
