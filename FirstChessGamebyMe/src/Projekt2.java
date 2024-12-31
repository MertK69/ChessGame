import java.util.Scanner;

public class Projekt2 {

	public static void main(String[] args) {

		String[][] newField = { 
				{ "XX", "01", "02", "03", "04", "05", "06", "07", "08", "XX" },
				{ "01", "ST", "SS", "SL", "SQ", "SK", "SL", "SS", "ST", "01" },
				{ "02", "SB", "SB", "SB", "SB", "SB", "SB", "SB", "SB", "02" },
				{ "03", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "03" },
				{ "04", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "04" },
				{ "05", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "05" },
				{ "06", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "06" },
				{ "07", "WB", "WB", "WB", "WB", "WB", "WB", "WB", "WB", "07" },
				{ "08", "WT", "WS", "WL", "WQ", "WK", "WL", "WS", "WT", "08" },
				{ "XX", "01", "02", "03", "04", "05", "06", "07", "08", "XX" }, };
		System.out.println("Funktionsweise: Man gibt zuerst die Koordinaten von der zu bewegenden Figur ein, erst in der Vertikalen dannach die Horizontale!");
		System.out.println("Dannach die Koordinaten des Ziel-Feldes im selben Format!");
		for (int c = 0; c < 300; c++) {
			int[] newfrom_X = new int [2];
			int[] newto_Y = new int [2];
			System.out.println();
			if ( c % 2 == 0) {
				System.out.println("It´s White Players Turn!");
			}
			if ( c % 2 == 1) {
				System.out.println("It´s Black Players Turn!");
			}
			for (int i = 0; i < 2;i++) {
				System.out.println("Gib Koordinate von from_X " + i + " an!");
				Scanner Eingabe_from_X = new Scanner(System.in);
				newfrom_X[i] = Eingabe_from_X.nextInt();
			}
			for (int i = 0; i < 2;i++) {
				System.out.println("Gib Koordinate von to_Y " + i + "an!");
				Scanner Eingabe_to_Y = new Scanner(System.in);
				newto_Y[i] = Eingabe_to_Y.nextInt();
			}
				Object[] result = ChessField_Move(newField, newfrom_X, newto_Y, c);
				newField = (String[][]) result[0];
				int newZugstatus = (int) result[1];
				for (int i= 0; i < 2;i++) {
					newfrom_X[i] = 0;
					newto_Y[i] = 0;
				}
			for (int k = 0;k <= newField.length ;k++) {
				if (k < newField.length - 1) {
				System.out.print("—————");
				}
				if (k == newField.length) {
					System.out.print("————");
				}
			}
			for (int i = 0; i < newField.length; i++) {
				System.out.println();
				System.out.print("|");
				for (int j = 0; j < newField[0].length; j++) {
					System.out.print(newField[i][j]);
					if (j < 9) {
					System.out.print(" | ");
					}
					if (j == 9) {
						System.out.print("|");
					}
				}
				System.out.println();
				if (i < 9) {
				for (int k = 0;k < newField.length;k++) {
					System.out.print(" ——  ");
				} 
				}
			}
			for (int k = 0;k <= newField.length ;k++) {
				if (k < newField.length - 1) {
				System.out.print("—————");
				}
				if (k == newField.length) {
					System.out.print("————");
				}
			}
			if (newZugstatus == 1) {
				c--;
			}
			newZugstatus = 0;
		}
	}	
	public static Object[] ChessField_Move(String[][] ChessField_current, int[] from_X, int[] to_Y, int ws) {
		String MyFigure = ChessField_current[from_X[0]][from_X[1]];
		String NewPosMyFigure = ChessField_current[to_Y[0]][to_Y[1]];
		int Zugreset = 0;
		if (ws % 2 == 0 && MyFigure.charAt(0) != 'W') {
			System.out.println("It´s white players turn!");
			Zugreset++;
			return new Object[] {ChessField_current, Zugreset};
		}
		if (ws % 2 == 1 && MyFigure.charAt(0) != 'S') {
			System.out.println("It´s Black players turn!");
			Zugreset++;
			return new Object[] {ChessField_current, Zugreset};
		}
		
		if (ChessField_current[from_X[0]][from_X[1]].equals("XX")) {
			System.out.println("You can´t attak/move a free Field!");
			Zugreset++;
			return new Object[] {ChessField_current, Zugreset};
		}
		
		for (int i = 0; i < 2;i++) { //Check for Field between 1-8
			if (from_X[i] >= 9 || from_X[i] <= 0) {
				System.out.println("Invalid Field, Choose a Field between 1-8!");
				return ChessField_current;
			}
			if (to_Y[i] >= 9 || to_Y[i] <= 0) {
				System.out.println("Invalid Field, Choose a Field between 1-8!");
				return ChessField_current;
			}
		}
		//Rochade Schwarz
		if (ChessField_current[from_X[0]][from_X[1]] == "SK") {
		int YAchse = Math.abs(from_X[0] - to_Y[0]);
		int XAchse = Math.abs(from_X[1] - to_Y[1]);
		if (from_X[1] - to_Y[1] == -3 && ChessField_current[from_X[0]][from_X[1]] == "SK") {
			int c = 0;
			for(int i = 1;i < XAchse;i++) {
				if (ChessField_current[from_X[0]][from_X[1] + i] != "XX") {
					c++;
				}}
			if (c == 0 && ChessField_current[1][8] == "ST") {
				ChessField_current[1][7] = "SK";
				ChessField_current[1][6] = "ST";
				ChessField_current[1][5] = "XX";
				ChessField_current[1][8] = "XX";
				return new Object[] {ChessField_current, Zugreset};
			}}
		if (from_X[1] - to_Y[1] == 2 && ChessField_current[from_X[0]][from_X[1]] == "SK") {
			int c = 0;
			for(int i = 1;i <= 3;i++) {
				if (ChessField_current[from_X[0]][from_X[1] - i] != "XX") {
					c++; 
				}}
			if (c == 0 && ChessField_current[1][8] == "ST") {
				ChessField_current[1][3] = "SK";
				ChessField_current[1][4] = "ST";
				ChessField_current[1][1] = "XX";
				ChessField_current[1][5] = "XX";
				return new Object[] {ChessField_current, Zugreset};
			}}
		} // Rochade Weiß
		if (ChessField_current[from_X[0]][from_X[1]] == "WK") {
			int YAchse = Math.abs(from_X[0] - to_Y[0]);
			int XAchse = Math.abs(from_X[1] - to_Y[1]);
			
			if (from_X[1] - to_Y[1] == -3 && ChessField_current[from_X[0]][from_X[1]] == "WK") {
				int c = 0;
				for(int i = 1;i < XAchse;i++) {
					if (ChessField_current[from_X[0]][from_X[1] + i] != "XX") {
						c++;
					}}
				if (c == 0 && ChessField_current[8][8] == "WT") {
					ChessField_current[8][7] = "WK";
					ChessField_current[8][6] = "WT";
					ChessField_current[8][5] = "XX";
					ChessField_current[8][8] = "XX";
					return new Object[] {ChessField_current, Zugreset};
				}}
			if (from_X[1] - to_Y[1] == 2 && ChessField_current[from_X[0]][from_X[1]] == "WK") {
				int c = 0;
				for(int i = 1;i <= 3;i++) {
					if (ChessField_current[from_X[0]][from_X[1] - i] != "XX") {
						c++; 
					}}
				if (c == 0 && ChessField_current[8][8] == "WT") {
					ChessField_current[8][3] = "WK";
					ChessField_current[8][4] = "WT";
					ChessField_current[8][1] = "XX";
					ChessField_current[8][5] = "XX";
					return new Object[] {ChessField_current, Zugreset};
				}}
			}
		// OWN FIGURE ATTACK BLOCK
		if (MyFigure.charAt(0) == NewPosMyFigure.charAt(0)) {
			System.out.println("You can´t attack your own Figure!");
			Zugreset++;
			return new Object[] {ChessField_current, Zugreset};
		}
		String[][] ChessField_current_lokal = new String[ChessField_current.length][ChessField_current[0].length];
		String Field_clear = "XX";
		String Field_old = ChessField_current[from_X[0]][from_X[1]];
		String Field_new = ChessField_current[to_Y[0]][to_Y[1]];
		// PAWN MOVEMENT AND ATTACK // Double Move and Attack not coded yet.
		if (ChessField_current[from_X[0]][from_X[1]] == "SB" || ChessField_current[from_X[0]][from_X[1]] == "WB") {
			if (ChessField_current[to_Y[0]][to_Y[1]] == "XX") {
				int Test = Math.abs(from_X[0] - to_Y[0]);
				if (Test == 2 && from_X[0] == 2 && ChessField_current[from_X[0]][from_X[1]] == "SB" || Test == 2 && from_X[0] == 7 && ChessField_current[from_X[0]][from_X[1]] == "WB") {
					for (int i = 0; i < ChessField_current_lokal.length; i++) {
						for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
							if (i == from_X[0] && j == from_X[1]) {
								ChessField_current_lokal[i][j] = Field_clear;
							} else if (i == to_Y[0] && j == to_Y[1]) {
								ChessField_current_lokal[i][j] = Field_old;
							} else {
								ChessField_current_lokal[i][j] = ChessField_current[i][j];
							}
						}
					}
					return new Object[] {ChessField_current_lokal, Zugreset};
				}
				if (from_X[0] - to_Y[0] == 1 || from_X[0] - to_Y[0] == -1) {
					for (int i = 0; i < ChessField_current_lokal.length; i++) {
						for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
							if (i == from_X[0] && j == from_X[1]) {
								ChessField_current_lokal[i][j] = Field_clear;
							} else if (i == to_Y[0] && j == to_Y[1]) {
								ChessField_current_lokal[i][j] = Field_old;
							} else {
								ChessField_current_lokal[i][j] = ChessField_current[i][j];
							}
						}
					}
				} else {
					Zugreset++;
					return new Object[] {ChessField_current, Zugreset};
				}
			}
			if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
				if (ChessField_current[from_X[0]][from_X[1]] == "SB") {
					if (ChessField_current[from_X[0] + 1][from_X[1] + 1] == ChessField_current[to_Y[0]][to_Y[1]]) {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
					if (ChessField_current[from_X[0] + 1][from_X[1] - 1] == ChessField_current[to_Y[0]][to_Y[1]]) {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
				}
				if (ChessField_current[from_X[0]][from_X[1]] == "WB") {
					if (ChessField_current[from_X[0] - 1][from_X[1] + 1] == ChessField_current[to_Y[0]][to_Y[1]]) {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
					if (ChessField_current[from_X[0] - 1][from_X[1] - 1] == ChessField_current[to_Y[0]][to_Y[1]]) {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}	
				}
			}
			return new Object[] {ChessField_current_lokal, Zugreset};
		}

		// TOWER MOVEMENT AND ATTACK
		if (ChessField_current[from_X[0]][from_X[1]] == "ST" || ChessField_current[from_X[0]][from_X[1]] == "WT") {
			if (from_X[0] != to_Y[0] && from_X[1] == to_Y[1]) {
				int MovingFields = Math.abs(to_Y[0] - from_X[0]);
				int c = 0;
				if (from_X[0] <= 4) {
					for (int i = from_X[0] + 1; i < MovingFields + from_X[0] + 1; i++) {
						if (ChessField_current[i][from_X[1]] != "XX") {
							c = c + 1;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c - 1;
					}
					if (0 < c) {
						System.out.println("not possible1");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					} else {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
				}
				if (from_X[0] >= 5) {
					for (int i = from_X[0] - 1; i >= to_Y[0]; i--) {
						if (ChessField_current[i][from_X[1]] != "XX") {
							c = c + 1;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c - 1;
					}
					if (0 < c) {
						System.out.println("not possible3");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					} else {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {

							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
				}
			} else if (from_X[1] != to_Y[1] && from_X[0] == to_Y[0]) {
				int MovingFields = Math.abs(from_X[1] - to_Y[1]);
				int c = 0;
				if (from_X[1] <= 4) {
					for (int i = from_X[1] + 1; i < MovingFields + from_X[1] + 1; i++) {
						if (ChessField_current[from_X[0]][i] != "XX") {
							c = c + 1;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c - 1;
					}
					if (0 < c) {
						System.out.println("Not possible2");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					} else {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {

							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
				}
				if (from_X[1] >= 5) {
					for (int i = from_X[1] - 1; i >= to_Y[1]; i--) {
						if (ChessField_current[from_X[0]][i] != "XX") {
							c = c + 1;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c - 1;
					}
					if (0 < c) {
						System.out.println("Not possible4");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					} else {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {

							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
				}
			} else {
				System.out.println("You can only move the Rook horizontally and vertically!");
				Zugreset++;
				return new Object[] {ChessField_current, Zugreset};
			}
			return new Object[] {ChessField_current_lokal, Zugreset};
		}

		// BISHOP MOVEMENT AND ATTACK
		if (ChessField_current[from_X[0]][from_X[1]] == "SL" || ChessField_current[from_X[0]][from_X[1]] == "WL") {
			int fromField = Math.abs(from_X[0] - to_Y[0]);
			int toField = Math.abs(from_X[1] - to_Y[1]);
			if (fromField == toField) { 
				if (from_X[0] < to_Y[0] && from_X[1] < to_Y[1] ) { // Links nach Rechts, Oben nach unten
					int c = 0;
					int Rechtsnachlinks = from_X[1] + 1;
					for (int i = from_X[0] + 1; i <= to_Y[0]; i++) {
						if (ChessField_current[i][Rechtsnachlinks]!= "XX") {
							c = c+1;
							Rechtsnachlinks++;
						}
						else {
						Rechtsnachlinks++;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c -1;
					}
					if (c == 0 ) {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
					else {
						System.out.println("You can´t jump Figures!");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					}
				}
				if (from_X[0] < to_Y[0] && from_X[1] > to_Y[1] ) { // Rechts nach Links, Oben nach unten
					int c = 0;
					int Rechtsnachlinks = from_X[1] - 1;
					for (int i = from_X[0] + 1; i <= to_Y[0]; i++) {
						if (ChessField_current[i][Rechtsnachlinks]!= "XX") {
							c = c+1;
							Rechtsnachlinks--;
						}
						else {
						Rechtsnachlinks--;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c -1;
					}
					if (c == 0 ) {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
					else {
						System.out.println("You can´t jump Figures!");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					}
				}
				if (from_X[0] > to_Y[0] && from_X[1] < to_Y[1] ) { // Links nach Rechts, unten nach oben
					int c = 0;
					int Rechtsnachlinks = from_X[1] + 1;
					for (int i = from_X[0] - 1; i >= to_Y[0]; i--) {
						if (ChessField_current[i][Rechtsnachlinks]!= "XX") {
							c = c+1;
							Rechtsnachlinks++;
						}
						else {
						Rechtsnachlinks++;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c -1;
					}
					if (c == 0 ) {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
					else {
						System.out.println("You can´t jump Figures!");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					}
				}
				if (from_X[0] > to_Y[0] && from_X[1] > to_Y[1] ) { // Rechts nach Links, unten nach oben
					int c = 0;
					int Rechtsnachlinks = from_X[1] - 1;
					for (int i = from_X[0] - 1; i >= to_Y[0]; i--) {
						if (ChessField_current[i][Rechtsnachlinks]!= "XX") {
							c = c+1;
							Rechtsnachlinks--;
						}
						else {
						Rechtsnachlinks--;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c -1;
					}
					if (c == 0 ) {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
					else {
						System.out.println("You can´t jump Figures!");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					}
				}
			}
				
			else {
				System.out.println("The bishop just can move diagonal!");
				Zugreset++;
				return new Object[] {ChessField_current, Zugreset};
			}
			return new Object[] {ChessField_current_lokal, Zugreset};
		}

		// KING MOVEMENT AND ATTACK
		if (ChessField_current[from_X[0]][from_X[1]] == "SK" || ChessField_current[from_X[0]][from_X[1]] == "WK") {
			int YAchse = Math.abs(from_X[0] - to_Y[0]);
			int XAchse = Math.abs(from_X[1] - to_Y[1]);
			
			// Movement Corner
			if (YAchse == 1 && XAchse == 1) {
				if (from_X[0] - to_Y[0] == 1 && from_X[1] - to_Y[1] == 1) {
					for (int i = 0; i < ChessField_current_lokal.length; i++) {
						for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
							if (i == from_X[0] && j == from_X[1]) {
								ChessField_current_lokal[i][j] = Field_clear;
							} else if (i == to_Y[0] && j == to_Y[1]) {
								ChessField_current_lokal[i][j] = Field_old;
							} else {
								ChessField_current_lokal[i][j] = ChessField_current[i][j];
							}
						}
					}
					return new Object[] {ChessField_current_lokal, Zugreset};
				}
				if (from_X[0] - to_Y[0] == 1 && from_X[1] - to_Y[1] == -1) {
					for (int i = 0; i < ChessField_current_lokal.length; i++) {
						for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
							if (i == from_X[0] && j == from_X[1]) {
								ChessField_current_lokal[i][j] = Field_clear;
							} else if (i == to_Y[0] && j == to_Y[1]) {
								ChessField_current_lokal[i][j] = Field_old;
							} else {
								ChessField_current_lokal[i][j] = ChessField_current[i][j];
							}
						}
					}
					return new Object[] {ChessField_current_lokal, Zugreset};
				}
				if (from_X[0] - to_Y[0] == -1 && from_X[1] - to_Y[1] == 1) {
					for (int i = 0; i < ChessField_current_lokal.length; i++) {
						for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
							if (i == from_X[0] && j == from_X[1]) {
								ChessField_current_lokal[i][j] = Field_clear;
							} else if (i == to_Y[0] && j == to_Y[1]) {
								ChessField_current_lokal[i][j] = Field_old;
							} else {
								ChessField_current_lokal[i][j] = ChessField_current[i][j];
							}
						}
					}
					return new Object[] {ChessField_current_lokal, Zugreset};
				}
				if (from_X[0] - to_Y[0] == -1 && from_X[1] - to_Y[1] == -1) {
					for (int i = 0; i < ChessField_current_lokal.length; i++) {
						for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
							if (i == from_X[0] && j == from_X[1]) {
								ChessField_current_lokal[i][j] = Field_clear;
							} else if (i == to_Y[0] && j == to_Y[1]) {
								ChessField_current_lokal[i][j] = Field_old;
							} else {
								ChessField_current_lokal[i][j] = ChessField_current[i][j];
							}
						}
					}
					return new Object[] {ChessField_current_lokal, Zugreset};
				}
			}
			// Movement upwards and downwards
			if (YAchse == 1 && XAchse == 0) {
				if (from_X[0] - to_Y[0] == 1) {
					for (int i = 0; i < ChessField_current_lokal.length; i++) {
						for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
							if (i == from_X[0] && j == from_X[1]) {
								ChessField_current_lokal[i][j] = Field_clear;
							} else if (i == to_Y[0] && j == to_Y[1]) {
								ChessField_current_lokal[i][j] = Field_old;
							} else {
								ChessField_current_lokal[i][j] = ChessField_current[i][j];
							}
						}
					}
					return new Object[] {ChessField_current_lokal, Zugreset};
				} else if (from_X[0] - to_Y[0] == -1) {
					for (int i = 0; i < ChessField_current_lokal.length; i++) {
						for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
							if (i == from_X[0] && j == from_X[1]) {
								ChessField_current_lokal[i][j] = Field_clear;
							} else if (i == to_Y[0] && j == to_Y[1]) {
								ChessField_current_lokal[i][j] = Field_old;
							} else {
								ChessField_current_lokal[i][j] = ChessField_current[i][j];
							}
						}
					}
					return new Object[] {ChessField_current_lokal, Zugreset};
				} else {
					System.out.println("Field not free!");
					Zugreset++;
					return new Object[] {ChessField_current, Zugreset};
				}
			}	
			// Movement to left and right
			if (YAchse == 0 && XAchse == 1) {
				if (from_X[1] - to_Y[1] == 1) {
					for (int i = 0; i < ChessField_current_lokal.length; i++) {
						for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
							if (i == from_X[0] && j == from_X[1]) {
								ChessField_current_lokal[i][j] = Field_clear;
							} else if (i == to_Y[0] && j == to_Y[1]) {
								ChessField_current_lokal[i][j] = Field_old;
							} else {
								ChessField_current_lokal[i][j] = ChessField_current[i][j];
							}
						}
					}
					return new Object[] {ChessField_current_lokal, Zugreset};
				}
				if (from_X[1] - to_Y[1] == -1) {
					for (int i = 0; i < ChessField_current_lokal.length; i++) {
						for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
							if (i == from_X[0] && j == from_X[1]) {
								ChessField_current_lokal[i][j] = Field_clear;
							} else if (i == to_Y[0] && j == to_Y[1]) {
								ChessField_current_lokal[i][j] = Field_old;
							} else {
								ChessField_current_lokal[i][j] = ChessField_current[i][j];
							}
						}
					}
					return new Object[] {ChessField_current_lokal, Zugreset};
				}
				else {
					System.out.println("Field not free!");
					Zugreset++;
					return new Object[] {ChessField_current, Zugreset};
				}
			}
			
			else {
				System.out.println("Invalid Move, try again!");
				Zugreset++;
				return new Object[] {ChessField_current, Zugreset};
			}
		}

		// KNIGHT MOVEMENT AND ATTACK
		if (ChessField_current[from_X[0]][from_X[1]] == "SS" || ChessField_current[from_X[0]][from_X[1]] == "WS") {
			int YAchse = Math.abs(from_X[0] - to_Y[0]);
			int XAchse = Math.abs(from_X[1] - to_Y[1]);
			if (YAchse == 2 && XAchse == 1 || YAchse == 1 && XAchse == 2) {
				for (int i = 0; i < ChessField_current_lokal.length; i++) {
					for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
						if (i == from_X[0] && j == from_X[1]) {
							ChessField_current_lokal[i][j] = Field_clear;
						} else if (i == to_Y[0] && j == to_Y[1]) {
							ChessField_current_lokal[i][j] = Field_old;
						} else {
							ChessField_current_lokal[i][j] = ChessField_current[i][j];
						}
					}
				}
				return new Object[] {ChessField_current_lokal, Zugreset};
			}
		}

		// QUEEN MOVEMENT AND ATTACK
		if (ChessField_current[from_X[0]][from_X[1]] == "SQ" || ChessField_current[from_X[0]][from_X[1]] == "WQ") {
			if (from_X[0] != to_Y[0] && from_X[1] == to_Y[1]) {
				int MovingFields = Math.abs(to_Y[0] - from_X[0]);
				int c = 0;
				if (from_X[0] <= 4) {
					for (int i = from_X[0] + 1; i < MovingFields + from_X[0] + 1; i++) {
						if (ChessField_current[i][from_X[1]] != "XX") {
							c = c + 1;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c - 1;
					}
					if (0 < c) {
						System.out.println("not possible1");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					} else {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {

							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
				}
				if (from_X[0] >= 5) {
					for (int i = from_X[0] - 1; i >= to_Y[0]; i--) {
						if (ChessField_current[i][from_X[1]] != "XX") {
							c = c + 1;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c - 1;
					}
					if (0 < c) {
						System.out.println("not possible3");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					} else {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {

							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
				}
			} else if (from_X[1] != to_Y[1] && from_X[0] == to_Y[0]) {
				int MovingFields = Math.abs(from_X[1] - to_Y[1]);
				int c = 0;
				if (from_X[1] <= 4) {
					for (int i = from_X[1] + 1; i < MovingFields + from_X[1] + 1; i++) {
						if (ChessField_current[from_X[0]][i] != "XX") {
							c = c + 1;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c - 1;
					}
					if (0 < c) {
						System.out.println("Not possible2");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					} else {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {

							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
				}
				if (from_X[1] >= 5) {
					for (int i = from_X[1] - 1; i >= to_Y[1]; i--) {
						if (ChessField_current[from_X[0]][i] != "XX") {
							c = c + 1;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c - 1;
					}
					if (0 < c) {
						System.out.println("Not possible4");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					} else {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {

							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
				}
			}
			int fromField = Math.abs(from_X[0] - to_Y[0]);
			int toField = Math.abs(from_X[1] - to_Y[1]);
			if (fromField == toField) { 
				if (from_X[0] < to_Y[0] && from_X[1] < to_Y[1] ) { // Links nach Rechts, Oben nach unten
					int c = 0;
					int Rechtsnachlinks = from_X[1] + 1;
					for (int i = from_X[0] + 1; i <= to_Y[0]; i++) {
						if (ChessField_current[i][Rechtsnachlinks]!= "XX") {
							c = c+1;
							Rechtsnachlinks++;
						}
						else {
						Rechtsnachlinks++;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c -1;
					}
					if (c == 0 ) {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
					else {
						System.out.println("You can´t jump Figures!");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					}
				}
				if (from_X[0] < to_Y[0] && from_X[1] > to_Y[1] ) { // Rechts nach Links, Oben nach unten
					int c = 0;
					int Rechtsnachlinks = from_X[1] - 1;
					for (int i = from_X[0] + 1; i <= to_Y[0]; i++) {
						if (ChessField_current[i][Rechtsnachlinks]!= "XX") {
							c = c+1;
							Rechtsnachlinks--;
						}
						else {
						Rechtsnachlinks--;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c -1;
					}
					if (c == 0 ) {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
					else {
						System.out.println("You can´t jump Figures!");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					}
				}
				if (from_X[0] > to_Y[0] && from_X[1] < to_Y[1] ) { // Links nach Rechts, unten nach oben
					int c = 0;
					int Rechtsnachlinks = from_X[1] + 1;
					for (int i = from_X[0] - 1; i >= to_Y[0]; i--) {
						if (ChessField_current[i][Rechtsnachlinks]!= "XX") {
							c = c+1;
							Rechtsnachlinks++;
						}
						else {
						Rechtsnachlinks++;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c -1;
					}
					if (c == 0 ) {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
					else {
						System.out.println("You can´t jump Figures!");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					}
				}
				if (from_X[0] > to_Y[0] && from_X[1] > to_Y[1] ) { // Rechts nach Links, unten nach oben
					int c = 0;
					int Rechtsnachlinks = from_X[1] - 1;
					for (int i = from_X[0] - 1; i >= to_Y[0]; i--) {
						if (ChessField_current[i][Rechtsnachlinks]!= "XX") {
							c = c+1;
							Rechtsnachlinks--;
						}
						else {
						Rechtsnachlinks--;
						}
					}
					if (ChessField_current[to_Y[0]][to_Y[1]] != "XX") {
						c = c -1;
					}
					if (c == 0 ) {
						for (int i = 0; i < ChessField_current_lokal.length; i++) {
							for (int j = 0; j < ChessField_current_lokal[0].length; j++) {
								if (i == from_X[0] && j == from_X[1]) {
									ChessField_current_lokal[i][j] = Field_clear;
								} else if (i == to_Y[0] && j == to_Y[1]) {
									ChessField_current_lokal[i][j] = Field_old;
								} else {
									ChessField_current_lokal[i][j] = ChessField_current[i][j];
								}
							}
						}
					}
					else {
						System.out.println("You can´t jump Figures!");
						Zugreset++;
						return new Object[] {ChessField_current, Zugreset};
					}
				}
			}
			return new Object[] {ChessField_current_lokal, Zugreset};
		}

		System.out.println("That was no valid move, try again!");
		Zugreset++;
		return new Object[] {ChessField_current, Zugreset};
	}

}