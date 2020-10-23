import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class chessFloor extends Item {

    P3D position;
    P3D widthVector;
    P3D highVector;
    Color color;
    public chessFloor(P3D position,P3D widthVector,P3D highVector) {
            this.position=position;
            this.widthVector=widthVector;
            this.highVector=highVector;
    }

    //todo calculate the polygon interesct
    @Override
    public Intersect intersect(Ray ray) {
        P3D normal=widthVector.cross(highVector).normalize();
        P3D interPlane;
        double dis;
        P3D colorPos;
        // not parallel
        if (normal.dot(ray.direction) != 0) {
            double t=position.sub(ray.position).dot(normal)/(ray.direction .dot(normal));
            if (t < 0) {
                return null;
            }
            interPlane=ray.position.add(ray.direction.normalize().scale(t));
            //https://www.jianshu.com/p/f7aa8b531487?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
            //1,2,3,4 are 4 corner of the rectangl;v1,v2,v3,v4 are four vector from corner point to interPlane
            P3D v1=interPlane.sub(position);
            P3D v4=interPlane.sub(position.add(highVector));
            P3D v2=interPlane.sub(position.add(widthVector));
            P3D v3=interPlane.sub(position.add(widthVector).add(highVector));
            //judge if the point is inside of the rec(judge by 4 line)
            if (v1.dot(highVector) > 0 && v2.dot(widthVector.scale(-1)) > 0 && v3.dot(highVector.scale(-1)) > 0 && v4.dot(widthVector) > 0) {
                dis=interPlane.sub(ray.position).length();
                colorPos= interPlane.sub(position);
                if (((int) (colorPos.x)+(int) (colorPos.z)) % 2 == 0) {
                    return new Intersect(dis, interPlane, normal, this, Color.WHITE);
                } else {
                    return new Intersect(dis, interPlane, normal, this, Color.black);
                }
            } else {
                return null;
            }
        }
        return null;
    }
}