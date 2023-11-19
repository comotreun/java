import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ending {
    public static void main(String[] args) {
        if (args.length >= 2) {
            // Retrieve the score and cnt from the command-line arguments
            String score = args[0];
            String cnt = args[1];

            // Print the score to the console
            System.out.println("Your final score is: " + score);
            System.out.println("Total correct answers: " + cnt);

            // Parse the score as an integer
            int numberOfStations = Integer.parseInt(score);
            int countOfStations = Integer.parseInt(cnt);

            // Create and show the GUI with the updated number of stations
            SwingUtilities.invokeLater(() -> createAndShowGUI(numberOfStations, countOfStations));
        } else {
            // Handle the case where no score is provided
            System.out.println("No score provided.");
        }
    }

    private static void createAndShowGUI(int numberOfStations, int countOfStations) {
        JFrame frame = new JFrame("배경 이미지 예제");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1000);

        // 배경 이미지를 가진 패널 생성
        final String[] stopText = {"장암에서 " + countOfStations + "개의 정거장을 이동했습니다."};

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("images\\end.jpg");
                Image image = imageIcon.getImage();
                int width = getWidth();
                int height = getHeight();
                g.drawImage(image, 0, 0, width, height, this);

                String lineText = "당신이 타신 1호선은";
                Font font = new Font("", Font.PLAIN, 40);
                g.setFont(font);
                FontMetrics fontMetrics = g.getFontMetrics();

                int lineTextWidth = fontMetrics.stringWidth(lineText);
                int xLine = (width - lineTextWidth) / 2;
                int yLine = (height / 2) - 40;
                g.setColor(Color.BLACK);
                g.drawString(lineText, xLine, yLine);

                int stopTextWidth = fontMetrics.stringWidth(stopText[0]);
                int xStop = (width - stopTextWidth) / 2;
                int yStop = yLine + 80;
                g.drawString(stopText[0], xStop, yStop);
            }
        };

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update stopText after 2 seconds
                String newStopText = "총 : " + numberOfStations + "점수를 획득하였습니다.";
                stopText[0] = newStopText;
                panel.repaint();
            }
        });

        // Start the timer
        timer.setRepeats(false); // Set to false to run only once
        timer.start();

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}