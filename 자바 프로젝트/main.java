import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main extends Frame {
    ImageIcon icon2;
    CardLayout cardLayout;
    JPanel cardPanel;

    public main() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Main2");
            JLabel background2 = new JLabel(icon2);
            cardLayout = new CardLayout();
            cardPanel = new JPanel(cardLayout);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1240, 720);
            frame.setResizable(true);
            // 배경 이미지 파일 경로

            // 배경 이미지를 갖는 JLabel 생성
            ImageIcon backgroundIcon = new ImageIcon("Main.jpg");
            Image backgroundImage = backgroundIcon.getImage();

            // 프레임 크기에 맞게 이미지 크기 조정
            Image scaledImage = backgroundImage.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel backgroundLabel = new JLabel(scaledIcon);
            frame.setContentPane(backgroundLabel);

            frame.setLayout(new FlowLayout());
            JButton start = new JButton("시작");
            JButton rule = new JButton("규칙");

            start.setBounds(50, 525, 150, 50);
            rule.setBounds(50, 600, 150, 50);

            background2.add(start);
            background2.add(rule);

            start.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            // background2 패널을 "Main2"라는 고유한 이름으로 cardPanel에 추가
            cardPanel.add(background2, "Main2");
//        cardPanel.add(new MapViewer(), "MapViewer");
            cardPanel.setLayout(cardLayout);

            // frame에 cardPanel을 추가
            frame.setLayout(new BorderLayout());
            frame.add(cardPanel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new main();
        });
    }
}
