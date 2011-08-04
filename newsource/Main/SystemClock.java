package edu.luc.clearing;

public class SystemClock implements Clock {

public long currentTime() {
return System.currentTimeMillis();
}

}


