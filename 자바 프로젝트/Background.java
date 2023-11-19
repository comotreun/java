import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background extends JFrame {

    ImageIcon sunIcon = new ImageIcon("images\\sun.png");
    ImageIcon moonIcon = new ImageIcon("images\\moon.png");

    JPanel cardPanel;
    CardLayout cardLayout;
    maingame_sun sunFrame;
    maingame_moon moonFrame;

    public Background() {
        setTitle("Custom Layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 1000);

        // Load images and make the border color transparent
        sunIcon = createTransparentImageIcon("images\\sun.png", Color.WHITE);
        moonIcon = new ImageIcon("images\\moon.png");

        // 전체 레이아웃을 CardLayout으로 설정
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 페이지 1: 배경 선택 페이지
        cardPanel.add(createBackgroundSelectionPage(), "backgroundSelection");

        // 페이지 2: 다른 페이지 (Test 페이지 등)
        cardPanel.add(createOtherPage(), "otherPage");

        // 전체 레이아웃 설정
        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);
    }

    private ImageIcon createTransparentImageIcon(String imagePath, Color borderColor) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            makeColorTransparent(image, borderColor);
            Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void makeColorTransparent(BufferedImage image, Color color) {
        int colorToMakeTransparent = color.getRGB();
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (image.getRGB(x, y) == colorToMakeTransparent) {
                    image.setRGB(x, y, 0x00FFFFFF); // Make the pixel transparent
                }
            }
        }
    }

    private JPanel createBackgroundSelectionPage() {
        // 왼쪽 패널
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(600, 1000));
        leftPanel.setBackground(Color.YELLOW);

        // 가운데 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.GRAY);
        centerPanel.setPreferredSize(new Dimension(400, 1000));

        // 배경 선택 텍스트를 가운데에 배치
        JLabel textLabel = new JLabel("배경 선택");
        textLabel.setFont(new Font("", Font.PLAIN, 30));
        textLabel.setVerticalAlignment(JLabel.CENTER);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(textLabel);

        // 오른쪽 패널
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(600, 1000));
        rightPanel.setBackground(new Color(139, 69, 19)); // 갈색

        // 이미지 아이콘 생성
        JButton sunButton = new JButton(sunIcon);
        sunButton.setPreferredSize(new Dimension(150, 150));
        sunButton.setBackground(Color.YELLOW);
        sunButton.setContentAreaFilled(false);
        sunButton.setOpaque(false);
        sunButton.setBorderPainted(false);

        JButton moonButton = new JButton(moonIcon);
        moonButton.setPreferredSize(new Dimension(150, 150));  // Corrected line
        moonButton.setBackground(new Color(139, 69, 19));
        moonButton.setContentAreaFilled(false);
        moonButton.setOpaque(false);
        moonButton.setBorderPainted(false);

        sunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("아침 (sun) 버튼 클릭");
                opensunFrame(); // Test 클래스 열기
                openOtherPage(); // 다른 페이지로 전환
            }
        });

        moonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("밤 (moon) 버튼 클릭");
                openmoonFrame(); // Test 클래스 열기
                openOtherPage(); // 다른 페이지로 전환
            }
        });

        // 배치 설정
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        rightPanel.add(moonButton, gbc);  // Corrected line

        leftPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(sunButton, gbc);  // Corrected line

        // 텍스트 중앙 정렬 조정
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setVerticalAlignment(JLabel.CENTER);

        // 페이지 1의 레이아웃
        JPanel backgroundSelectionPage = new JPanel(new BorderLayout());
        backgroundSelectionPage.add(leftPanel, BorderLayout.WEST);
        backgroundSelectionPage.add(centerPanel, BorderLayout.CENTER);
        backgroundSelectionPage.add(rightPanel, BorderLayout.EAST);

        return backgroundSelectionPage;
    }

    private JPanel createOtherPage() {
        // 페이지 2의 내용을 생성하여 반환
        // 예시로 Test 페이지를 만들거나 필요한 페이지를 추가하세요.
        JPanel otherPage = new JPanel();
        otherPage.setVisible(false);
        return otherPage;
    }

    private void opensunFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    sunFrame.main(new String[]{});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void openmoonFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    moonFrame.main(new String[]{});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void openOtherPage() {
        cardLayout.show(cardPanel, "otherPage");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Background frame = new Background();
            frame.setVisible(true);
        });
    }
}