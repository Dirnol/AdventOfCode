package adventofcode.day07;

public class Hand implements Comparable<Hand>{

	//private char[] order = { '2', '3', '4', '5', '6',  '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
	private char[] order = { 'J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A'};
	
	private String hand;
	private int bid;
	private int strength;
	
	public Hand(String hand, int bid) {
		this.hand = hand;
		this.bid = bid;
		findStrength();
	}
	
	@Override
	public int compareTo(Hand o) {
		Hand other = (Hand) o;
		
		if(strength == other.strength ) {
			for(int i = 0; i < hand.length(); i++)
				if(hand.charAt(i) != other.hand.charAt(i)) {
					int thisHighCard = getHighCardStrength(hand.charAt(i));
					int otherHighCard = getHighCardStrength(other.hand.charAt(i));
					return Integer.compare(thisHighCard, otherHighCard);
				}
		}
		
		return Integer.compare(strength, other.strength);
	}
	
	private int getHighCardStrength(char card) {
		for(int s = 0; s < order.length; s++)
			if(order[s] == card)
				return s;
		return -1;
	}
	
	private void findStrength() {
		String temp = hand;
		String checked = "";
		
		int jCount = 0;
		for(int i =  0; i < temp.length(); i++) {
			if(temp.charAt(i) == 'J')
				jCount++;
		}
		
		if(temp.equals("JJJJ2")) {
			System.out.println("here");
		}
		
		for(int c = 0; c < hand.length(); c++) {
			char check = temp.charAt(c);
			int count = 0;
			if(!checked.contains(check+"")) {
				for(int i = 0; i < temp.length(); i++) {
					if(temp.charAt(i) == check || temp.charAt(i) == 'J') {
						count++;
					}
				}
				if(count == 5) {
					strength = 8;
					return;
				}
				else if(count == 4 && check != 'J') {
					strength = 7;
					return;
				}
				else if(count == 3) {
					if(strength == 4 && jCount < 2) {
						strength = 5;
						return;
					}
					strength += 4;
				}
				else if(count == 2) {
					strength += 1;
				}
				checked += check;
			}
		}
		if(strength == 13 || strength == 6)
			strength = 4;
		if(strength == 5) {
			for(int i = 0; i < hand.length(); i++) {
				char c = hand.charAt(i);
				if(c != 'J') {
					int count = 0;
					for(int j = 0; j < hand.length(); j++) {
						if(hand.charAt(j) == c)
							count++;
					}
					if(count == 1) {
						strength = 4;
						return;
					}
				}
			}
		}
		if(strength == 4) {
			for(int i = 0; i < hand.length(); i++) {
				char c = hand.charAt(i);
				int count = 0;
				for(int j = 0; j < hand.length(); j++) {
					if(hand.charAt(j) == c)
						count++;
				}
				if(count >= 2)
					return;
			}
			strength = 1;
		}
		
	}
	
	public int getBid() {
		return bid;
	}
	
	public String getHand() {
		return hand;
	}
	
	public int getStrength() {
		return strength;
	}

	public String getStrengthString() {
		
		switch(strength) {
		case(8):
			return "5";
		case(7):
			return "4";
		case(5):
			return "FH";
		case(4):
			return "3";
		case(2):
			return "2P";
		case(1):
			return "1P";
		default:
			return "HC";
		}
	}

}
