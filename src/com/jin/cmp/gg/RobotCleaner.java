package com.jin.cmp.gg;

import java.util.HashSet;
import java.util.Set;

/*
http://www.1point3acres.com/bbs/thread-345555-1-1.html

Robot Cleaner
Given a robot cleaner in a room modeled as a grid. Each cell in the grid can be empty or blocked.
The robot cleaner can move forward, turn left or turn right. When it tries to move into a blocked
cell, its bumper sensor detects the obstacle and it stays on the current cell.
The control API:
Interface Robot {
  //returns true if next cell is open and robot moves into the cell.
  //returns false if next cell is obstacle and robot stays on the current cell.
  boolean Move();
  //Robot will stay on the same cell after calling Turn*. k indicates how
  //many turns to perform.
  void TurnLeft(int k);
  void TurnRight(int k);
  //Clean the current cell.
  void Clean();more.
}

TODO: not correct
 */
interface Robot{
    //returns true if next cell is open and robot moves into the cell.
    //returns false if next cell is obstacle and robot stays on the current cell.
    boolean Move();
    //Robot will stay on the same cell after calling Turn*. k indicates how
    //many turns to perform.
    void TurnLeft(int k);
    void TurnRight(int k);
    //Clean the current cell.
    void Clean();
}

class Coordination {
    int x;
    int y;

    public Coordination(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class RobotCleaner implements Robot {
    private Coordination currentCoordination;
    private int currentDirection;

    public RobotCleaner() {
        this.currentCoordination = new Coordination(0,0);
        this.currentDirection = 0;
    }

    @Override
    public boolean Move() {
        Coordination newCoordination = getNewCoordination(currentCoordination, currentDirection);
        if (newCoordination.x>=0 && newCoordination.x<=5 &&
                newCoordination.y>=0 && newCoordination.y<=5) {
            currentCoordination = newCoordination;
            return true;
        }
        return false;
    }

    @Override
    public void TurnRight(int k) {
        currentDirection = (currentDirection + 1) % 4;
    }

    @Override
    public void TurnLeft(int k) {
        if (currentDirection == 0)
            currentDirection = 3;
        else
            currentDirection -= currentDirection -1;
    }

    @Override
    public void Clean() {
        System.out.println("cleaned [" + currentCoordination.x + " " + currentCoordination.y + "]");
        return;
    }

    public static void main(String[] args) {
        Robot robot = new RobotCleaner();
        Set<Coordination> visited = new HashSet<>();

        moveHelper(robot, new Coordination(0,0), visited, 0);
    }


    public static Coordination getNewCoordination(Coordination current, int direction) {
        switch (direction) {
            case 0:
                return new Coordination(current.x + 1, current.y);
            case 1:
                return new Coordination(current.x, current.y - 1);
            case 2:
                return new Coordination(current.x - 1, current.y);
            case 3:
                return new Coordination(current.x, current.y + 1);
        }
        return current;
    }

    // use 0 (east), 1(south), 2(west), 3(north) to present direction
    public static void moveHelper(Robot robot, Coordination current, Set<Coordination> visited, int currentDirection) {
        for (int i=0; i<4; i++) {
            robot.TurnLeft(i);
            Coordination nextCoordication = getNewCoordination(current, (currentDirection + i) % 4);
            if (visited.contains(nextCoordication))
                continue;
            visited.add(nextCoordication);
            boolean success = robot.Move();
            if (success) {
                robot.Clean();
                moveHelper(robot, getNewCoordination(current, (currentDirection + i) % 4), visited, (currentDirection + i) % 4);
            } else {
                // move back to original location
                robot.TurnLeft(2);
                if (!robot.Move()) {
                    return;
                } else {
                    // move back to original direction
                    robot.TurnRight(2);
                    robot.TurnRight(i);
                }

            }
        }

    }
}
