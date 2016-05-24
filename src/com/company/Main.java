package com.company;

import com.sun.deploy.perf.PerfRollup;

import javax.xml.bind.SchemaOutputResolver;

public class Main {

    public static void main(String[] args) {
//
//        Station hitFM = new HitFM();
//        Radio radio = new Radio();
//        radio.setStation(hitFM);
//
//        for (int i = 0; i  < 10; i++){
//            radio.play();
//            radio.nextStation();
//        }

        Person person = new Person();
        person.setState(new Work());
        for (int i = 0; i < 10; i++){
            person.doSomething();
        }
    }
}

// Example of state pattern with switching in the Context
// Context
class Radio{
    Station station;
    void setStation(Station st){
        station = st;
    }
    void nextStation(){
        if (station instanceof RadioEuropa){
            setStation(new HitFM());
        }else if(station instanceof HitFM){
            setStation(new RockFM());
        }else if(station instanceof RockFM){
            setStation(new RadioEuropa());
        }
    }
    void play(){
        station.play();
    }
}

// State
interface Station{
        void play();
}
class RadioEuropa implements Station{
        public void play(){
            System.out.println("Radio Europa is playing!");
        }
}
class HitFM implements Station{
    public void play(){
        System.out.println("HitFM is playing!");
    }
}
class RockFM implements Station{
    public void play(){
        System.out.println("RockFM is playing!");
    }
}

// Example of state pattern with switching in the State
// Context
class Person{
    private Activity state;
    public void setState(Activity s){
        this.state = s;
    }
    public void doSomething(){
        state.doSomething(this);
    };
}

// State
interface Activity{
    void doSomething(Person context);
}
class Work implements Activity{
    public void doSomething(Person context){
        System.out.println("Working...");
        context.setState(new Rest());
    }
}
class Rest implements Activity{
    private int count = 0;
    public void doSomething(Person context){
        if (count < 3){
            System.out.println("Relaxing! :)");
            count++;
        }else{
            context.setState(new Work());
        }

    }
}


