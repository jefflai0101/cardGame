package cardGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
//import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
//import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class cardGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	public static String cA, cB, cC;
	public static Boolean bA, bB, bC;
	public static int unavailCount;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cardGame frame = new cardGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public cardGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		//FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		final JLabel lblPickACard = new JLabel("Pick a card!");
		panel_1.add(lblPickACard);
		
		final JLabel card1 = new JLabel("");
		final JLabel card2 = new JLabel("");
		final JLabel card3 = new JLabel("");
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/cardGame/cardback.png"));
		
		card1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (unavailCount == 2)
					resetEverything(card1, card2, card3, lblPickACard);
				else {
					changeCard(card1, lblPickACard, cA);
					bA = false;
					computersMove(card2, card3,lblPickACard, cB, cC, bB, bC, 1);
				}
			}
		});
		card1.setIcon(icon);
		panel.add(card1);
		
		
		card2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (unavailCount == 2)
					resetEverything(card1, card2, card3, lblPickACard);
				else {
					changeCard(card2, lblPickACard, cB);
					bB = false;
					computersMove(card1, card3,lblPickACard, cA, cC, bA, bC, 2);
				}
			}
		});
		card2.setIcon(icon);
		panel.add(card2);
		
		card3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (unavailCount == 2)
					resetEverything(card1, card2, card3, lblPickACard);
				else {
					changeCard(card3, lblPickACard, cC);
					bC = false;
					computersMove(card1, card2, lblPickACard, cA, cB, bA, bB, 3);
				}
			}
		});
		card3.setIcon(icon);
		panel.add(card3);
		resetEverything(card1, card2, card3, lblPickACard);
		initiateGame();
		
	}

	private void initiateGame() {
		Deck deck = new Deck();
		Card C;
		for (int i = 0; i < 3; i++)
		{
		
			if (deck.getTotalCards()!=0)
			{
				C = deck.drawFromDeck();
				switch (i)
				{
					case 0:
						cA = C.toString();
						bA = true;
						break;
					case 1:
						cB = C.toString();
						bB = true;
						break;
					case 2:
						cC = C.toString();
						bC = true;
						break;
				}
			}	
		}
	}
	
	private void resetEverything(JLabel card1, JLabel card2, JLabel card3, JLabel lblPickACard) {
		// TODO Auto-generated method stub
		ImageIcon icon = new ImageIcon(getClass().getResource("/cardGame/cardback.png"));
		card1.setIcon(icon);
		card2.setIcon(icon);
		card3.setIcon(icon);
		lblPickACard.setText("Pick a card!");
		unavailCount = 0;
		initiateGame();
		
	}
	
	private void changeCard(JLabel jCard, JLabel lblpick, String cno) {
		String imageName = ("/cardGame/" + cno + ".png");
		ImageIcon icon = new ImageIcon(getClass().getResource(imageName));
		//icon.getImage().flush();
		jCard.setIcon(icon);
		unavailCount++;
	}
	
	private void computersMove(JLabel c1, JLabel c2, JLabel lblpick, String cno1, String cno2, Boolean b1, Boolean b2, int cardNumber) {
		Random generator = new Random();
		int index=0;
		String userChoice = "";
		index = generator.nextInt(2);
		
		switch (cardNumber)
		{
			case 1:
				userChoice = cA;
				break;
			case 2:
				userChoice = cB;
				break;
			case 3:
				userChoice = cC;
				break;
		}
		
		if (index == 0)
		{
			changeCard(c1, lblpick, cno1);
			b1 = false;
			judgement(lblpick, cno1, userChoice);
		} else {
			changeCard(c2, lblpick, cno2);
			b2 = false;
			judgement(lblpick, cno2, userChoice);
		}
			
	}
	
	private void judgement(JLabel lblpick, String a, String b) {
		String[] comp = new String[2];
		String[] user = new String[2];
		int whoWin = 0;
		
		comp = stripCardName(a);
		user = stripCardName(b);
		//comp[0] = "s";
		//comp[1] = "13";
		//user[0] = "s";
		//user[1] = "1";
		
		int cRank = Integer.parseInt(comp[1]);
		int uRank = Integer.parseInt(user[1]);
		
		if (cRank == 1) cRank = 14;
		if (uRank == 1) uRank = 14;
				
		if (cRank == uRank)
			whoWin = whosBigger(comp[0], user[0]);
		else {
			if (cRank > uRank)
				whoWin = 0;
			else
				whoWin = 1;
		}
		
		comp = resolveNames(comp[0], cRank);
		user = resolveNames(user[0], uRank);
		
		String cardVsCard = "AI:" + comp[0] + comp[1] + " vs Player:" + user[0] + user[1];
		
		if (whoWin == 0)
		{
			lblpick.setText(cardVsCard + ", AI win! Click any card start again");
			//System.out.println("AI:"+ comp[0] + comp[1] + " vs " + "Player:" + user[0] + user[1] + " AI win");
		} else {
			lblpick.setText(cardVsCard + ", Player win! Click any card start again");
			//System.out.println("AI:" + comp[0] + comp[1] + " vs " + "Player:" + user[0] + user[1] + " Player win");
		}
		
	}
	
	private String[] resolveNames(String a, int b) {
		String[] temp = new String[2];
		
		if (a.equals("d"))
			temp[0] = "Diamond ";
		if (a.equals("c"))
			temp[0] = "Club ";
		if (a.equals("h"))
			temp[0] = "Heart ";
		if (a.equals("s"))
			temp[0] = "Spade ";
		
		if (b < 11)
			temp[1] = Integer.toString(b);
		else {
			if (b == 11)
				temp[1] = "Jack";
			if (b == 12)
				temp[1] = "Queen";
			if (b == 13)
				temp[1] = "King";
			if (b == 14)
				temp[1] = "Ace";
		}
		
		return temp;
		
	}
	
	private int whosBigger(String a, String b) {
		
		int aSuit, bSuit;
		
		aSuit = suitOrder(a);
		bSuit = suitOrder(b);
		
		if (aSuit > bSuit)
			return 0;
		else
			return 1;
	}

	private int suitOrder(String so) {
		int temp = 0;
		if (so == "d")
			temp = 1;
		if (so == "c")
			temp = 2;
		if (so == "h")
			temp = 3;
		if (so == "s")
			temp = 4;
		return temp;
	}
	
	private String[] stripCardName(String suitRank) {
		String[] temp = new String[2];
		temp[0] = Character.toString(suitRank.charAt(0));
		temp[1] = suitRank.substring(1);
		
		return temp;
	}
	
	
	
}
