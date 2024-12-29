import java.util.Scanner;

public class Projekt2 {

	public static void main(String[] args) {

		String[][] ChessField_First = { 
				{ "XX", "01", "02", "03", "04", "05", "06", "07", "08", "XX" },
				{ "01", "ST", "SS", "SL", "SQ", "SK", "SL", "SS", "ST", "01" },
				{ "02", "SB", "SB", "SB", "SB", "SB", "SB", "SB", "SB", "02" },
				{ "03", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "03" },
				{ "04", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "04" },
				{ "05", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "05" },
				{ "06", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "06" },
				{ "07", "WB", "WB", "WB", "WB", "WB", "WB", "WB", "WB", "07" },
				{ "08", "WT", "WS", "WL", "WK", "WQ", "WL", "WS", "WT", "08" },
				{ "XX", "01", "02", "03", "04", "05", "06", "07", "08", "XX" }, };
		int[] from_X = new int[2];
		int[] to_Y = new int [2];
		System.out.println("Funktionsweise: Man gibt zuerst die Koordinaten von der zu bewegenden Figur ein, erst in der Vertikalen dannach die Horizontale.!");
		System.out.println("Dannach dann die Koordinaten des Ziel-Feldes im selben Format!");
		for (int i = 0; i < 2;i++) {
			System.out.println("Gib Koordinate von from_X " + i + " an!");
			Scanner Eingabe_from_X = new Scanner(System.in);
			from_X[i] = Eingabe_from_X.nextInt();
		}
		for (int i = 0; i < 2;i++) {
			System.out.println("Gib Koordinate von to_Y " + i + "an!");
			Scanner Eingabe_to_Y = new Scanner(System.in);
			to_Y[i] = Eingabe_to_Y.nextInt();
		}
		
		String[][] newField = ChessField_Move(ChessField_First, from_X, to_Y);
		for (int c = 0; c < 150; c++) {
			if (c >= 1) {
			int[] newfrom_X = new int [2];
			int[] newto_Y = new int [2];
			for (int i = 0; i < 2;i++) {
				System.out.println(newfrom_X[i]);
				System.out.println("Gib Koordinate von from_X " + i + " an!");
				Scanner Eingabe_from_X = new Scanner(System.in);
				newfrom_X[i] = Eingabe_from_X.nextInt();
			}
			for (int i = 0; i < 2;i++) {
				System.out.println(newto_Y[i]);
				System.out.println("Gib Koordinate von to_Y " + i + "an!");
				Scanner Eingabe_to_Y = new Scanner(System.in);
				newto_Y[i] = Eingabe_to_Y.nextInt();
			}
				newField = ChessField_Move(newField, newfrom_X, newto_Y);
				for (int i= 0; i < 2;i++) {
					newfrom_X[i] = 0;
					to_Y[i] = 0;
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
		}
	}

	public static String[][] ChessField_Move(String[][] ChessField_current, int[] from_X, int[] to_Y) {
		String MyFigure = ChessField_current[from_X[0]][from_X[1]];
		String NewPosMyFigure = ChessField_current[to_Y[0]][to_Y[1]];
		
		if (ChessField_current[from_X[0]][from_X[1]].equals("XX")) { // Check for selected free Field
			System.out.println("You can´t attak/move a free Field!");
			return ChessField_current;
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
		// OWN FIGURE ATTACK BLOCK
		if (MyFigure.charAt(0) == NewPosMyFigure.charAt(0)) {
			System.out.println("You can´t attack your own Figure!");
			return ChessField_current;
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
					return ChessField_current_lokal;
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
					return ChessField_current;
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
			return ChessField_current_lokal;
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
						return ChessField_current;
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
						return ChessField_current;
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
						return ChessField_current;
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
						return ChessField_current;
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
				return ChessField_current;
			}
			return ChessField_current_lokal;
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
						return ChessField_current;
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
						return ChessField_current;
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
						return ChessField_current;
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
						return ChessField_current;
					}
				}
			}
				
			else {
				System.out.println("The bishop just can move diagonal!");
				return ChessField_current;
			}
			return ChessField_current_lokal;
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
					return ChessField_current_lokal;
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
					return ChessField_current_lokal;
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
					return ChessField_current_lokal;
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
					return ChessField_current_lokal;
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
					return ChessField_current_lokal;
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
					return ChessField_current_lokal;
				} else {
					System.out.println("Field not free!");
					return ChessField_current;
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
					return ChessField_current_lokal;
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
					return ChessField_current_lokal;
				}
				else {
					System.out.println("Field not free!");
					return ChessField_current;
				}
			}
			
			else {
				System.out.println("Invalid Move, try again!");
				return ChessField_current;
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
				return ChessField_current_lokal;
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
						return ChessField_current;
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
						return ChessField_current;
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
						return ChessField_current;
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
						return ChessField_current;
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
						return ChessField_current;
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
						return ChessField_current;
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
						return ChessField_current;
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
						return ChessField_current;
					}
				}
			}
			return ChessField_current_lokal;
		}

		System.out.println("That was no valid move, try again!");
		return ChessField_current;
	}

}