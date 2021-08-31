public class Planet {public double xxPos ; public double yyPos ;
    public double xxVel ; public double yyVel; public double mass ; public String imgFileName ;
    public  Planet(double xP ,double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }   
    public  Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
        
    }
    public double calcDistance(Planet p){
        double px = this.xxPos - p.xxPos;
        double py = this.yyPos - p.yyPos;
        double pr = Math.hypot(px, py);
        return pr;
    }
    public double calcForceExertedBy(Planet p){
        double prank = 6.67e-11;
        return prank*this.mass*p.mass/(this.calcDistance(p)*this.calcDistance(p));
    }
    public double calcForceExertedByX(Planet p){
        return this.calcForceExertedBy(p)*((p.xxPos - this.xxPos)/this.calcDistance(p));
    }
    public double calcForceExertedByY(Planet p){
        return this.calcForceExertedBy(p)*((p.yyPos - this.yyPos)/this.calcDistance(p));
    }
    public double calcNetForceExertedByX(Planet[] pl){
        double Netx = 0;
        for (Planet px:pl){
            if(px != this ){
            Netx += this.calcForceExertedByX(px);
            }
        }
        return Netx;
    }
    public double calcNetForceExertedByY(Planet[] pl){
        double Nety = 0;
        for (Planet py:pl){
            if(py != this ){
            Nety += this.calcForceExertedByY(py);
            }
        }
        return Nety;
    }
    public void update(double T,double Fx , double Fy){
        this.xxVel = this.xxVel + T*Fx/this.mass;
        this.yyVel = this.yyVel + T*Fy/this.mass;
        this.xxPos += this.xxVel*T;
        this.yyPos += this.yyVel*T;
    }
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }
}
