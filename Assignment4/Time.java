import tester.Tester;

// Assignment 4
// partner1-Adach partner1-Kacper
// partner1-kadach

class Time {
    int hour;
    int minute;
    int second;

    Time(int hour, int minute, int second) {
        if (hour >= 0 && hour <= 23) {
            this.hour = hour;
        } 
        else {
            throw new IllegalArgumentException("Invalid hour: " + Integer.toString(hour));
        }
        if (minute >= 0 && minute <= 59) {
            this.minute = minute;
        } 
        else {
            throw new IllegalArgumentException("Invalid minute: " + Integer.toString(minute));
        }
        if (second >= 0 && second <= 59) {
            this.second = second;
        } 
        else {
            throw new IllegalArgumentException("Invalid second: " + Integer.toString(second));
        }
    }

    Time(int hour, int minute) {
        this(hour, minute, 0);
    }

    Time(int hour, int minute, boolean isAM) {
        this(hour, minute);
        if (hour < 1 || hour > 12) {
            throw new IllegalArgumentException("Invalid hour: " + Integer.toString(hour));
        } 
        else {
            if (isAM) {
                if (hour == 12) {
                    this.hour = 0;
                }
            } 
            else {
                if (hour == 12) {
                    this.hour = 12;
                } 
                else {
                    this.hour += 12;
                }
            }
        }
    }

    public boolean sameTime(Time t) {
        return (this.hour == t.hour) && (this.minute == t.minute) && (this.second == t.second);
    }
}

class ExamplesTime {
    Time timeOne = new Time(10, 30, 24);
    Time timeTwo = new Time(12, 20);
    Time timeThree = new Time(12, 30, true);
    Time timeFour = new Time(12, 40, false);
    Time timeFive = new Time(12, 20, 0);

    boolean testBadConstructors(Tester t) {
        return 
                t.checkConstructorException(new IllegalArgumentException("Invalid hour: 24"), 
                        "Time", 24, 10, 10)
                && t.checkConstructorException(new IllegalArgumentException("Invalid minute: 62"), 
                        "Time", 22, 62, 10)
                && t.checkConstructorException(new IllegalArgumentException("Invalid second: -1"), 
                        "Time", 22, 10, -1)
                && t.checkConstructorException(new IllegalArgumentException("Invalid hour: 24"), 
                        "Time", 24, 10)
                && t.checkConstructorException(new IllegalArgumentException("Invalid minute: 64"), 
                        "Time", 12, 64)
                && t.checkConstructorException(new IllegalArgumentException("Invalid hour: 13"), 
                        "Time", 13, 10, true)
                && t.checkConstructorException(new IllegalArgumentException("Invalid hour: 0"), 
                        "Time", 0, 10, false);
    }

    boolean testCheckTimes(Tester t) {
        return t.checkExpect(this.timeOne.hour, 10) && t.checkExpect(this.timeTwo.second, 0)
                && t.checkExpect(this.timeThree.hour, 0) && t.checkExpect(this.timeFour.hour, 12);
    }

    boolean testSameTime(Tester t) {
        return t.checkExpect(this.timeTwo.sameTime(this.timeFive), true)
                && t.checkExpect(this.timeThree.sameTime(this.timeFour), false);
    }
}