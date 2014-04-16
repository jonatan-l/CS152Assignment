package frontend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerScheme {

	Scanner in;

	public ScannerScheme(File f) throws FileNotFoundException {
		in = new Scanner(f);
	}

	/**
	 * Scans entire input line by line and
	 */
	public void populateParseTree() {
		while (in.hasNextLine()) {
			String line = in.nextLine();
			line.replaceAll("(", " ( ");
			line.replaceAll(")", " ) ");
			String[] linearray = line.split(" ");
			for (int i = 0; i < linearray.length; i++) {
				if (linearray[i].compareTo(";") == 0) { //comment nullification
					i = ++linearray.length;
				} else {
					//potential sysout print place
					String item = "";
					if (linearray[i].compareTo("'") == 0) {// 0
						item += "'";
						i++;//0->1
						if (linearray[i++].compareTo("(") == 0) {// 1->2
							item += "(";
							while (linearray[i++].compareTo(")") != 0) {// 234
								item += linearray[i];
							}
							item += ")";
						} else {
							item += linearray[i];
						}
					} else {
						item = linearray[i++];
					}
					Token piece = new Token(item);
					sendpiece(piece);// todo send token to check
				}
			}
		}
	}

}
