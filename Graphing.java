import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Graphing extends JPanel {
    static class Node {
        int x, y;
        Node(int x, int y) {this.x = x; this.y = y;}
    }
    private static int type;
    private static int n;
    private static double prob;
    private static List<Node> nodes;
    private static boolean[][] edges;
    public Graphing() {
        generateNodes();
        generateEdges();
    }
    private void generateNodes() {
        nodes = new ArrayList<>();
        int centerX = 500;
        int centerY = 500;
        int radius = 450;
        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n;
            int x = centerX + (int)(radius * Math.cos(angle));
            int y = centerY + (int)(radius * Math.sin(angle));
            nodes.add(new Node(x, y));
        }
    }
    private void generateEdges() {
        edges = new boolean[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (rand.nextDouble() <= prob){
                    edges[i][j] = edges[j][i] = true;
                }
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.BLACK);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (edges[i][j]) {
                    g2.drawLine(nodes.get(i).x, nodes.get(i).y,
                                nodes.get(j).x, nodes.get(j).y);
                }
            }
        }

        g2.setColor(Color.BLUE);
        int r = 6;
        for (Node node : nodes) {
            g2.fillOval(node.x - r, node.y - r, 2*r, 2*r);
            }
    }
        public static void main(String[] args) {
        Scanner inputstream = new Scanner(System.in);
        System.out.println("Enter Graph Type: Complete (1), Random (2), Empty (3)");
        type = inputstream.nextInt();
        prob = 0.0;
        System.out.println("Enter Size 0,1,2,3...");
        n = inputstream.nextInt();
        if(type > 3 || type < 1){type = 3;}
        if(type == 1){prob = 1.0;}
        if(type == 2){System.out.println("Enter Edge Probability"); prob = inputstream.nextDouble();}
        if(prob > 1.0){prob = 1.0;}
        inputstream.close();
        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.add(new Graphing());
        frame.setVisible(true);
    }
}