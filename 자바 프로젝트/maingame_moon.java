import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class maingame_moon {
    private static final int ANIMATION_DURATION = 20000; // Animation duration in milliseconds
    private static final int TIMER_DURATION = 20000; // Timer duration in milliseconds

    private static final String[] subwayStations = {
            "장암", "도봉산", "수락산", "마들", "노원", "중계", "하계", "공릉", "태릉입구", "먹골",
            "중화", "상봉", "면목", "사가정", "용마산", "중곡", "군자", "어린이대공원", "건대입구",
            "뚝섬유원지", "청담", "강남구청", "학동", "논현", "반포", "고속터미널", "내방", "이수",
            "남성", "숭실대입구", "상도", "장승배기", "신대방삼거리", "보라매", "신풍", "대림", "남구로",
            "가산디지털단지", "철산", "광명사거리", "천왕", "온수", "까치울", "부천종합운동장", "춘의",
            "신중동", "부천시청", "상동", "삼산체육관", "굴포천", "부평구청"
    };
    private static int currentIndex = 0;
    private static Timer timer;

    private static int score = 0;                    // 추가: 사용자의 점수를 나타내는 변수
    private static int cnt = 0;
    private static int consecutiveCorrectAnswers = 0; // 추가: 연속으로 맞춘 정답 수를 나타내는 변수
    private static JLabel scoreLabel; // 추가: 점수를 표시할 레이블
    private static JLabel leftLabel;
    private static JLabel centerLabel;
    private static JLabel rightLabel;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Timer Bar with Background Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1000);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("images\\night.png");
                Image image = imageIcon.getImage();
                int width = getWidth();
                int height = getHeight();
                g.drawImage(image, 0, 0, width, height, this);
            }
        };

        panel.setLayout(null);

        Random random = new Random();


        JProgressBar progressBar = new JProgressBar(0, 20);
        progressBar.setStringPainted(true);
        int barWidth = 1000;
        int barHeight = 50;
        int barX = (frame.getWidth() - barWidth) / 2;
        int barY = 0;
        progressBar.setBounds(barX, barY, barWidth, barHeight);

        ImageIcon trainIcon = new ImageIcon("images\\train.png");
        JLabel trainLabel = new JLabel(trainIcon);
        int trainWidth = trainIcon.getIconWidth();
        int trainHeight = trainIcon.getIconHeight();
        int trainX = -850; // Starting X coordinate for the train
        int trainY = (frame.getHeight() - trainHeight) / 2 - 100;
        trainLabel.setBounds(trainX, trainY, trainWidth, trainHeight);


        ImageIcon reverseBarIcon = new ImageIcon("images\\reverse bar.png");
        JLabel reverseBarLabel = new JLabel(reverseBarIcon);
        int reverseBarWidth = reverseBarIcon.getIconWidth();
        int reverseBarHeight = reverseBarIcon.getIconHeight();
        int reverseBarX = (frame.getWidth() - reverseBarWidth) / 2;
        int reverseBarY = trainY + trainHeight - 260;
        reverseBarLabel.setBounds(reverseBarX, reverseBarY, reverseBarWidth, reverseBarHeight);

        Font labelFont = new Font("", Font.PLAIN, 30);
        Color whiteColor = Color.WHITE;

        leftLabel = new JLabel(subwayStations[currentIndex]);
        leftLabel.setBounds(70, frame.getHeight() / 2 + 60, 200, 30);
        leftLabel.setFont(labelFont);
        leftLabel.setForeground(whiteColor);

        centerLabel = new JLabel("클릭");
        centerLabel.setBounds((frame.getWidth() - 200) / 2 + 90, frame.getHeight() / 2 + 60, 200, 30);
        centerLabel.setFont(labelFont);
        centerLabel.setForeground(whiteColor);

        centerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (TIMER_DURATION != 0) {
                    String userInput = JOptionPane.showInputDialog(frame, "현재 정거장:");

                    if (userInput != null) {
                        centerLabel.setText(userInput);

                        if (userInput.equalsIgnoreCase(subwayStations[currentIndex + 1])) {
                            JOptionPane.showMessageDialog(frame, "다음 정거장으로 이동합니다.");
                            currentIndex++;
                            consecutiveCorrectAnswers++;

                            // 연속으로 맞출 때마다 점수 부여
                            if (consecutiveCorrectAnswers >= 2) {
                                score += 2;
                            } else {
                                score += 1;
                            }

                            scoreLabel.setText("Score: " + score); // Update scoreLabel

                            updateLabels();
                            cnt++;
                        } else if ((userInput.equalsIgnoreCase(subwayStations[currentIndex + 2])) && cnt >= 1 ){
                            JOptionPane.showMessageDialog(frame, "다음 정거장으로 이동합니다.");
                            currentIndex += 2;
                            consecutiveCorrectAnswers++;

                            // 연속으로 맞출 때마다 점수 부여
                            if (consecutiveCorrectAnswers >= 2) {
                                score += 2;
                            } else {
                                score += 1;
                            }
                            cnt++;
                            scoreLabel.setText("Score: " + score); // Update scoreLabel

                            updateLabels();
                        } else {
                            JOptionPane.showMessageDialog(frame, "현재 정거장을 다시 적어주세요.");
                            consecutiveCorrectAnswers = 0;  // 틀렸을 때 연속으로 맞춘 정답 수 초기화
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "타임 오버");
                }
            }
        });

        rightLabel = new JLabel(subwayStations[currentIndex + 2]);
        rightLabel.setBounds(frame.getWidth() - 160, frame.getHeight() / 2 + 60, 200, 30);
        rightLabel.setFont(labelFont);
        rightLabel.setForeground(whiteColor);

        scoreLabel = new JLabel("Score: " + score); // 추가: 초기 점수 레이블 설정
        scoreLabel.setBounds(20, 20, 100, 30); // 추가: 레이블 위치 설정
        scoreLabel.setFont(labelFont); // 추가: 레이블 폰트 설정
        scoreLabel.setForeground(whiteColor); // 추가: 레이블 텍스트 색상 설정
        panel.add(scoreLabel); // 추가: 패널에 레이블 추가

        ImageIcon settingIcon = new ImageIcon("images\\setting.png");
        Image settingImage = settingIcon.getImage();
        int settingWidth = 50;
        int settingHeight = 50;
        settingImage = settingImage.getScaledInstance(settingWidth, settingHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledSettingIcon = new ImageIcon(settingImage);
        JLabel settingLabel = new JLabel(scaledSettingIcon);
        int settingX = frame.getWidth() - settingWidth - 100;
        int settingY = 20;
        settingLabel.setBounds(settingX, settingY, settingWidth, settingHeight);

        settingLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showSettingsDialog(frame);
            }
        });

        panel.add(progressBar);
        panel.add(trainLabel);
        panel.add(reverseBarLabel);
        panel.add(leftLabel);
        panel.add(centerLabel);
        panel.add(rightLabel);
        panel.add(settingLabel);

        frame.add(panel);

        Timer timer = new Timer(1000, new ActionListener() { // Reduced timer delay for smoother animation
            int remainingTime = TIMER_DURATION / 1000; // Convert timer duration to seconds
            int animationTime = 0;
            int destinationXCoordinate = frame.getWidth() + 100; // Define destination X coordinate

            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingTime >= 0) {
                    progressBar.setValue(remainingTime);
                    progressBar.setString(Integer.toString(remainingTime));

                    if (remainingTime == 0) {
                        ((Timer) e.getSource()).stop();

                        // Display a warning message when time is up
                        JOptionPane.showMessageDialog(frame, "시간 오버");
                        showEndGameDialog(frame, true);
                        // You can add additional actions here if needed.
                    } else if (remainingTime <= 2) {
                        progressBar.setForeground(Color.BLACK);
                    } else {
                        progressBar.setForeground(Color.RED);
                    }

                    // Update train position for animation
                    if (animationTime < ANIMATION_DURATION) {
                        int currentXCoordinate = (int) (trainX + (double) animationTime / ANIMATION_DURATION * (destinationXCoordinate - trainX));
                        trainLabel.setBounds(currentXCoordinate, trainY, trainWidth, trainHeight);
                    }

                    remainingTime--;
                    animationTime += 1000;
                }
            }
        });

        timer.start();
        frame.setVisible(true);
    }

    private static void updateLabels() {
        leftLabel.setText(subwayStations[currentIndex + 1]);
        centerLabel.setText("클릭"); // Update centerLabel
        rightLabel.setText(subwayStations[currentIndex + 3]);
    }

    private static void showEndGameDialog(JFrame parentFrame, boolean isTimeOver) {
        // 시간이 오버되면 ending.java로 점수 정보와 cnt 정보를 넘겨서 호출
        if (isTimeOver) {
            parentFrame.dispose(); // Close the main game frame
            ending.main(new String[]{Integer.toString(score), Integer.toString(cnt)});
        }
    }


    private static void showSettingsDialog(JFrame parentFrame) {
        JDialog settingsDialog = new JDialog(parentFrame, "설정", true);
        settingsDialog.setSize(600, 400);
        settingsDialog.setLocationRelativeTo(parentFrame);

        // 설정 다이얼로그의 레이아웃
        settingsDialog.setLayout(new GridLayout(3, 1));

        // 1번: 소리 조절
        JPanel soundPanel = new JPanel(new FlowLayout());
        JLabel soundLabel = new JLabel("음악 소리 조절");
        JSlider soundSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        soundSlider.setMajorTickSpacing(20);
        soundSlider.setMinorTickSpacing(5);
        soundSlider.setPaintTicks(true);
        soundSlider.setPaintLabels(true);

        soundSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO: 음악 소리 조절 처리
                int volume = soundSlider.getValue();
            }
        });

        soundPanel.add(soundLabel);
        soundPanel.add(soundSlider);

        // 2번: 다시하기
        JButton restartButton = new JButton("다시하기");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 다시하기 처리
                JOptionPane.showMessageDialog(settingsDialog, "다시하기 버튼이 클릭되었습니다.");
            }
        });

        // 3번: 종료 및 처음부터
        JPanel exitPanel = new JPanel(new GridLayout(1, 2));
        JButton exitButton = new JButton("종료");
        JButton restartFromBeginningButton = new JButton("처음부터");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 종료 처리
                settingsDialog.dispose(); // 다이얼로그 닫기
                System.exit(0); // 프로그램 종료
            }
        });

        restartFromBeginningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 처음부터 다시 시작 처리
                JOptionPane.showMessageDialog(settingsDialog, "처음부터 다시 시작 버튼이 클릭되었습니다.");
            }
        });


        exitPanel.add(exitButton);
        exitPanel.add(restartFromBeginningButton);

        // 설정 다이얼로그에 컴포넌트 추가
        settingsDialog.add(soundPanel);
        settingsDialog.add(restartButton);
        settingsDialog.add(exitPanel);

        // 엑스 표시를 누를 때 창을 닫도록 설정
        settingsDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        settingsDialog.setVisible(true);
    }
}
