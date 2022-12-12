//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.font.GlyphMetrics;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame implements ItemListener {

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat timeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;

    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;
    String time;
    String day;
    String date;
    JToggleButton timezone;
    JToggleButton militaryTime;

    SimpleClock() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Digital Clock");
        this.setLayout(new FlowLayout());
        this.setSize(350, 300);
        this.setResizable(false);

        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 45));
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setOpaque(true);
        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Ink Free", Font.BOLD, 35));

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Free", Font.BOLD, 33));

        calendar.getTimeZone();
        timezone = new JToggleButton("GMT");
        timezone.addItemListener(this);
        militaryTime = new JToggleButton("24hr");
        militaryTime.addItemListener(this);

        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);
        this.setVisible(true);
        this.add(timezone);
        this.add(militaryTime);

        setTimer();
    }

    public void setTimer() {
        while (true) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new SimpleClock();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (timezone.isSelected()) {
            timezone.setText("EST");
            dayFormat = new SimpleDateFormat("EEEE");
            dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
            TimeZone.setDefault(TimeZone.getTimeZone("EST"));
        } else {
            timezone.setText("GMT");
            dayFormat = new SimpleDateFormat("EEEE");
            dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
            TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        }
        if (militaryTime.isSelected()) {
            militaryTime.setText("24hr");
            timeFormat = new SimpleDateFormat("HH:mm:ss a");
        } else {
            militaryTime.setText("12hr");
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
        }

    }
}
