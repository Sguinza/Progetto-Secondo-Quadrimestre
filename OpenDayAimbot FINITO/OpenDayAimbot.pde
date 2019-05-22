import pKinect.PKinect;
import processing.serial.*;
import processing.net.*;

PKinect kinect;
Serial myport;
Server myServer;        
PFont font;
String datiManuale;
boolean manuale=false;
ArrayList<SkeletonData> bodies;
int port = 10002;
String whatClientSaid;
String valX="0";
String valY="0";

void setup()
{
  size(640, 480);
  background(0);
  kinect = new PKinect(this);
  bodies = new ArrayList<SkeletonData>();
  smooth();
  font = loadFont("LucidaSans-18.vlw");
  textFont(font, 18);
  textAlign(CENTER);
  myport = new Serial(this, Serial.list()[0], 9600);
  myServer = new Server(this, port);
}

void draw() {
  fill(0, 0, 0);
  noStroke();
  rect(0, 0, width, height);
  synchronized(this) {
  }
  for (int i=0; i<bodies.size (); i++) 
  {
    drawSkeleton(bodies.get(i));
    String s1 = str(bodies.get(i).dwTrackingID);
    if (s1.equals("0")) {
    } else {
      int x = Math.round(bodies.get(i).position.x*width);
      int y = Math.round(bodies.get(i).position.y*height);
      if (manuale == false) {
        valX = Integer.toString(x);
        valY = Integer.toString(y);
      }
      if (x<100) {
        if (x<10) {
          valX="00"+valX;
        } else {
          valX="0"+valX;
        }
      }
      if (y<100) {
        if (y<10) {
          valY = "00"+valY;
        } else {
          valY = "0"+valY;
        }
      }
      Client thisClient = myServer.available();
      if (thisClient !=null) {
        whatClientSaid = thisClient.readString();
        println(whatClientSaid+ "suca");
        if (whatClientSaid.equals("0\n") || whatClientSaid.equals("1\n"))
          if (whatClientSaid.equals("0\n")) {
            manuale = false;
          } else if (whatClientSaid.equals("1\n")) {
            manuale = true;
          }
      }
      if (manuale == false) {
        String dati = valX+""+ valY+";";
        myport.write(dati);
        myport.write("");

        println(dati);

        delay(100);
      } else {
        int ValYInt = Integer.parseInt(valY);
        int ValXInt = Integer.parseInt(valX);
        char val= whatClientSaid.charAt(0);
        switch(val) {
        case 'w':
          ValYInt = ValYInt + 20;
          if (ValYInt > 400) {
            ValYInt = ValYInt - 20;
          }
          break;
        case 's':
          ValYInt = ValYInt - 20;
          if (ValYInt < 118) {
            ValYInt = ValYInt + 20;
          }
          break;
        case 'd':
          ValXInt = ValXInt + 20;
          if (ValXInt > 568) {
            ValXInt = ValXInt - 20;
          }
          break;
        case 'a':
          ValXInt = ValXInt - 20;
          if (ValXInt < 118) {
            ValXInt = ValXInt + 20;
          }
          break;
        default:
          break;
        }
        valX= Integer.toString(ValXInt);
        valY= Integer.toString(ValYInt);
        String dati = valX+""+ valY+";";
        println(dati);
        myport.write(dati);
        myport.write("");
      }
    }
  }
}
void drawSkeleton(SkeletonData _s) 
{
  // Body
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HEAD, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_CENTER);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_CENTER, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_CENTER, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_CENTER, 
  PKinect.NUI_SKELETON_POSITION_SPINE);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_LEFT, 
  PKinect.NUI_SKELETON_POSITION_SPINE);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_SPINE);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SPINE, 
  PKinect.NUI_SKELETON_POSITION_HIP_CENTER);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_CENTER, 
  PKinect.NUI_SKELETON_POSITION_HIP_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_CENTER, 
  PKinect.NUI_SKELETON_POSITION_HIP_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_LEFT, 
  PKinect.NUI_SKELETON_POSITION_HIP_RIGHT);

  // Left Arm
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_LEFT, 
  PKinect.NUI_SKELETON_POSITION_ELBOW_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_ELBOW_LEFT, 
  PKinect.NUI_SKELETON_POSITION_WRIST_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_WRIST_LEFT, 
  PKinect.NUI_SKELETON_POSITION_HAND_LEFT);

  // Right Arm
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_ELBOW_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_ELBOW_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_WRIST_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_WRIST_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_HAND_RIGHT);

  // Left Leg
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_LEFT, 
  PKinect.NUI_SKELETON_POSITION_KNEE_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_KNEE_LEFT, 
  PKinect.NUI_SKELETON_POSITION_ANKLE_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_ANKLE_LEFT, 
  PKinect.NUI_SKELETON_POSITION_FOOT_LEFT);

  // Right Leg
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_KNEE_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_KNEE_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_ANKLE_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_ANKLE_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_FOOT_RIGHT);
}

void DrawBone(SkeletonData _s, int _j1, int _j2) 
{
  noFill();
  stroke(0, 255, 0);
  if (_s.skeletonPositionTrackingState[_j1] != PKinect.NUI_SKELETON_POSITION_NOT_TRACKED &&
    _s.skeletonPositionTrackingState[_j2] != PKinect.NUI_SKELETON_POSITION_NOT_TRACKED) {
    line(_s.skeletonPositions[_j1].x*width, 
    _s.skeletonPositions[_j1].y*height, 
    _s.skeletonPositions[_j2].x*width, 
    _s.skeletonPositions[_j2].y*height);
  }
}

void appearEvent(SkeletonData _s) 
{
  if (_s.trackingState == PKinect.NUI_SKELETON_NOT_TRACKED) 
  {
    return;
  }
  synchronized(bodies) {
    bodies.add(_s);
  }
}

void disappearEvent(SkeletonData _s) 
{
  synchronized(bodies) {
    for (int i=bodies.size ()-1; i>=0; i--) 
    {
      if (_s.dwTrackingID == bodies.get(i).dwTrackingID) 
      {
        bodies.remove(i);
      }
    }
  }
}

void moveEvent(SkeletonData _b, SkeletonData _a) { 
  {
    if (_a.trackingState == PKinect.NUI_SKELETON_NOT_TRACKED) 
    {
      return;
    }
    synchronized(bodies) {
      for (int i=bodies.size ()-1; i>=0; i--) 
      {
        if (_b.dwTrackingID == bodies.get(i).dwTrackingID) 
        {
          bodies.get(i).copy(_a);
          break;
        }
      }
    }
  }
}

