package edu.unca.derive;
import java.util.Random;

public class Statements {
		private int mStatement;
		Random random = new Random();
		
		public Statements(int statement) {
			setStatement(statement);
		}
		
		public int getStatement() {
			return mStatement;
		}

		public void setStatement(int statement) {
			mStatement = statement;
		}
		
		public int randInt(int min, int max) {
			int randomNum = random.nextInt((max - min) + 1) + min;
			return randomNum;
		}

	}
