package tddmicroexercises.tirepressuremonitoringsystem;


import org.junit.*;
import static org.junit.Assert.*;

public class TestAlarm {

    private static final double LOWER_THAN_THRESHOLD = 0;
    private static final double HIGHER_THAN_THRESHOLD = 100;
    private static final double BETWEEN_THRESHOLD = 4;

    @Test
    public void given_no_check_then_alarm_is_off() {
        Alarm alarm = new Alarm();

        assertEquals(false, alarm.isAlarmOn());
    }

    @Test
    public void given_pressure_is_lower_than_threshold_when_check_then_alarm_is_on() {
        Alarm alarm = new Alarm();
        alarm.sensor = new Sensor() {
            @Override
            protected double getSamplePressure() {
                return LOWER_THAN_THRESHOLD;
            }
        };

        alarm.check();

        assertEquals(true, alarm.isAlarmOn());
    }

    @Test
    public void given_pressure_higher_than_threshold_when_check_then_alarm_is_on() {
        Alarm alarm = new Alarm();
        alarm.sensor = new Sensor() {
            @Override
            protected double getSamplePressure() {
                return HIGHER_THAN_THRESHOLD;
            }
        };

        alarm.check();

        assertEquals(true, alarm.isAlarmOn());
    }

    @Test
    public void given_pressure_in_the_threshold_when_check_then_alarm_is_on() {
        Alarm alarm = new Alarm();
        alarm.sensor = new Sensor() {
            @Override
            protected double getSamplePressure() {
                return BETWEEN_THRESHOLD;
            }
        };

        alarm.check();

        assertEquals(false, alarm.isAlarmOn());
    }
}
