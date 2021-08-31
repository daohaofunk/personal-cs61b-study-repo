import java.util.ArrayList;
import java.util.List;

public class NBody{
    public static double readRadius(String FileName){
        In in = new In(FileName);
            int num = in.readInt();
            double Radius = in.readDouble();
            return Radius;
    }
    public static Planet[] readPlanets(String FileName){
        In in = new In(FileName);
        int num = in.readInt();
        in.readDouble();
        Planet[] pl = new Planet[num];
        int i = 0;
        for(;i < num;i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            pl[i]= new Planet(xP,yP,xV,yV,m,img);

        }
        return pl;
    }
    public static void main(String[] args) {
        
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        
        double radius = NBody.readRadius(filename);
        Planet[] pl = NBody.readPlanets(filename);
        /* draw the background*/
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        String groundpicture = "./images/starfield.jpg";
        StdDraw.picture(0,0,groundpicture); 
        for(Planet shili:pl){
            shili.draw();
        }
        StdDraw.enableDoubleBuffering();
        double timecounter = 0;
        for(;timecounter <= T;timecounter += dt){
            double [] xForce = new double[pl.length];
            double [] yForce = new double[pl.length];
            for(int i=0;i<pl.length;i++){
                xForce[i] = pl[i].calcNetForceExertedByX(pl);
                yForce[i] = pl[i].calcNetForceExertedByY(pl);
                pl[i].update(dt, xForce[i], yForce[i]);

            }
            for(int i=0;i<pl.length;i++){
                
            }
            StdDraw.picture(0,0,groundpicture);
            for(Planet shili:pl){
                shili.draw();
            }
            StdDraw.show();
		    StdDraw.pause(10);
        }
    }
}