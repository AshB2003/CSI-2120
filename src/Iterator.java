//Student full name: Ash Bhattarai
//Student number: 300236157

/**
 *This interface <b>Iterator</b> defines the basic methods of iterator. It is
 * implemented in the inner class of PlaneRANSAC.
 */

public interface Iterator<Point3D> {
    public abstract boolean hasNext();
    public abstract Point3D next();
    public abstract void remove();
}
